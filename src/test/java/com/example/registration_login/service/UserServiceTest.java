package com.example.registration_login.service;

import com.example.registration_login.dto.UserRegistrationDto;
import com.example.registration_login.model.Role;
import com.example.registration_login.model.User;
import com.example.registration_login.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    private UserService userService;

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @BeforeEach
    public void setUp() throws Exception {
        userRepository = Mockito.mock(UserRepository.class);
        passwordEncoder = Mockito.mock(BCryptPasswordEncoder.class);

        userService = new UserService(userRepository, passwordEncoder);
    }

    @Test
    public void newUserRegistration(){

        UserRegistrationDto userRegistrationDto = generateUserRegistrationDto();

        User user = generateUser(userRegistrationDto);

        Mockito.doNothing().when(userRepository.save(user));

        Mockito.verify(userRepository).save(user);

    }


    private User generateUser(UserRegistrationDto userRegistrationDto){
        User user = new User(userRegistrationDto.getFirstName(),
                userRegistrationDto.getLastName(),
                userRegistrationDto.getEmail(),
                passwordEncoder.encode(userRegistrationDto.getPassword()),
                Arrays.asList(new Role("ROLE_USER")));

        return user;
    }
    private UserRegistrationDto generateUserRegistrationDto() {
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
        userRegistrationDto.setFirstName("edin");
        userRegistrationDto.setLastName("dzeko");
        userRegistrationDto.setEmail("edindzeko");
        userRegistrationDto.setPassword("9");

        return userRegistrationDto;
    }
}