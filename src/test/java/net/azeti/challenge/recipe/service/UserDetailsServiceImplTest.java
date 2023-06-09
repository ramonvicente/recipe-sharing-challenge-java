package net.azeti.challenge.recipe.service;

import net.azeti.challenge.recipe.dto.auth.UserDetailsImpl;
import net.azeti.challenge.recipe.model.User;
import net.azeti.challenge.recipe.repository.UserRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class UserDetailsServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Test
    @Ignore
    @DisplayName("Should return userDetail when LoadUerByUsername given valid username.")
    public void shouldReturnUserDetailsWhenLoadUserByUsernameGivenValidUsername() {
        //given
        String username = "username";
        User user = User.builder().email("email@email.com").username("username").build();
        Mockito.when(userRepository.findByUsername(username)).thenReturn(user);

        //when
        UserDetails actual = userDetailsService.loadUserByUsername(username);

        //then
        assertThat(actual).isNotNull();
        assertThat(actual.getUsername()).isEqualTo(username);
    }
}
