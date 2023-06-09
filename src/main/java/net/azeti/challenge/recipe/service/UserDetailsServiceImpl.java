package net.azeti.challenge.recipe.service;

import lombok.RequiredArgsConstructor;
import net.azeti.challenge.recipe.model.User;
import net.azeti.challenge.recipe.dto.auth.UserDetailsImpl;
import net.azeti.challenge.recipe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    public static final String ERROR_MESSAGE_USERNAME_SHOULD_NOT_BE_NULL_OR_BLANK = "username should not be null or blank.";

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(username == null || username.isBlank()) {
            throw new IllegalArgumentException(ERROR_MESSAGE_USERNAME_SHOULD_NOT_BE_NULL_OR_BLANK);
        }

        User user = userRepository.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("User Not Found with username: " + username);
        }

        return UserDetailsImpl.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .authorities(List.of(new SimpleGrantedAuthority("USER")))
                .build();
    }
}
