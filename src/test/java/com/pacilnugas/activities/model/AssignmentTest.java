package com.pacilnugas.activities.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class AssignmentTest {
    private Assignment assignment1;
    private Assignment assignment2;
    private Matkul matkul;

    @BeforeEach
    void setUp() {
        matkul = new Matkul();
        matkul.setMajor("Ilmu Komputer");
        matkul.setAngkatan(2019);
        assignment1 = new Assignment("Group Project - Adpro", matkul, LocalDateTime.now());
    }

    @Test
    void testMethodBuatString() {
        List<String> expected = new ArrayList<>();
        expected.add("TUGAS #" + assignment1.getId_activity() + "\n\n");
        expected.add("Nama tugas: " + assignment1.getTitle());
        expected.add("Nama matkul: " + assignment1.getMatkul());
        expected.add("Nama pengajar: " + assignment1.getMaker_username());
        expected.add("Deadline: " + assignment1.getDeadlineFormatted()
                + ", " + assignment1.getTime());
        expected.add("Deskripsi: " + assignment1.getDescription());
        expected.add("Major:" + assignment1.getMajor());
        expected.add("Angkatan Tujuan:" + assignment1.getAngkatan());
        List<String> result = assignment1.buatString();
        Assertions.assertIterableEquals(expected, result);
    }

    @Test
    void testAssignmentEquals() {
        assignment2 = new Assignment("Group Project - Adpro", matkul, LocalDateTime.now());
        Assertions.assertEquals(true, assignment1.equals(assignment2));
    }
}
