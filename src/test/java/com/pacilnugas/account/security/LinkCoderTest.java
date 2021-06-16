package com.pacilnugas.account.security;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class LinkCoderTest {

    @Test
    void testEmptyConstructor() {
        LinkCoder linkCoder = new LinkCoder();
        assertEquals(linkCoder.encode("12345678", ""), "");
        assertEquals(linkCoder.decode("12345678", ""), "");
    }
}
