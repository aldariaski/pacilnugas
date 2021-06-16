package com.pacilnugas.account.security;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class PasswordCrypterTest {

    @Test
    void testEncryptMethod() {
        PasswordCrypter passwordCrypter;
        try {
            passwordCrypter = new PasswordCrypter("DESede");
        } catch (Exception e) {
            passwordCrypter = null;
        }
        assertEquals(passwordCrypter.encrypt("12345678", false), "8mzCNJUNtvSJJSpQl9fUJg==");
        assertEquals(passwordCrypter.encrypt("12345678", true), "");
    }
}
