package com.pacilnugas.account.core;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class AccountTest {

    @Test
    void testEmptyConstructor() {
        Student student = new Student();
        TeachingAssistant teachingAssistant = new TeachingAssistant();
        Lecturer lecturer = new Lecturer();

        student = new Student("syabib", "8mzCNJUNtvSJJSpQl9fUJg==");
        teachingAssistant = new TeachingAssistant("jc", "8mzCNJUNtvSJJSpQl9fUJg==");
        lecturer = new Lecturer("bapak", "8mzCNJUNtvSJJSpQl9fUJg==");

        assertEquals(student.getType(), "Student");
        assertEquals(teachingAssistant.getType(), "Teaching Assistant");
        assertEquals(lecturer.getType(), "Lecturer");
    }
}
