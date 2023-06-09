package net.azeti.challenge.recipe.config;

import net.azeti.challenge.recipe.repository.UserRepository;
import net.azeti.challenge.recipe.service.UserService;
import net.azeti.challenge.recipe.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserConfig {

    @Bean
    public UserService userService(UserRepository repository, PasswordEncoder passwordEncoder) {
        return new UserServiceImpl(repository, passwordEncoder);
    }
}
