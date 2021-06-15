package com.pacilnugas.account.service;

import com.pacilnugas.account.repository.AccountRepository;
import com.pacilnugas.activities.repository.MatkulRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {
    @Mock
    private AccountRepository accountRepository;

    @Mock
    private MatkulRepository matkulRepository;

    @InjectMocks
    private AccountService accountService;

    @BeforeEach
    private void setUp() {
        accountService.registration("sasfort", "12345678", "12345678", "Student");
    }

//    @Test
//    void testRegistration() {
//        String redirect = accountService.registration("sasfort", "12345678", "12345678",
//                "Student");
//        assertEquals("existingAccount", redirect);
//        redirect = accountService.registration("sasfort", "12345678", "12345678", "Student");
//        assertEquals("registrationUsed", redirect);
//        redirect = accountService.registration("sasfort", "12345678", "12345679", "Student");
//        assertEquals("registrationError", redirect);
//    }
}
