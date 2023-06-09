package net.azeti.challenge.recipe.service;

import net.azeti.challenge.recipe.converter.UserConverter;
import net.azeti.challenge.recipe.dto.auth.RegistrationRequest;
import net.azeti.challenge.recipe.dto.auth.RegistrationResult;
import net.azeti.challenge.recipe.model.User;
import net.azeti.challenge.recipe.repository.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    @DisplayName("Should register user given valid registration request.")
    public void shouldRegisterUserGivenValidRegistrationRequest() {
        //given
        RegistrationRequest request = RegistrationRequest.builder()
                .email("test@test.com")
                .username("username")
                .password("password")
                .build();
        Mockito.when(userRepository.findByUsername(request.getUsername())).thenReturn(null);
        Mockito.when(userRepository.findByEmail(request.getEmail())).thenReturn(null);
        //when
        RegistrationResult actual = userService.registerUser(request);

        //then
        assertThat(actual).isNotNull();
        assertThat(actual.getMessage()).isEqualTo(String.format(UserServiceImpl.RESPONSE_MESSAGE_USER_REGISTERED_SUCCESSFULLY, request.getUsername()));
    }
}
