package com.pacilnugas.authentication.service;

import com.pacilnugas.authentication.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        userService.createUser("Dek Syabib", "00000000", "Student");
        userService.createUser("Pak Syabib", "11111111", "Lecturer");
        userService.createUser("Bang Syabib", "22222222", "Teaching Assistant");
    }

    @Test
    void testFindUser() {
//        assertEquals(userService.getUser("Dek Syabib", "00000000").getType(), "Student");
//        assertEquals(userService.getUser("Pak Syabib", "11111111").getType(), "Lecturer");
//        assertEquals(userService.getUser("Bang Syabib", "22222222").getType(), "Teaching Assistant");
    }
}
