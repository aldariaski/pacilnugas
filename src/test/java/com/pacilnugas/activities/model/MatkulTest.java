package com.pacilnugas.activities.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class MatkulTest {
    private Matkul matkul;

    @BeforeEach
    public void setUp() {
        matkul = new Matkul();
        LocalDate date = LocalDate.now();
        matkul.setPengajar("Budi Boom");
        matkul.setTitle("Advanced Programming");
        matkul.setDescription("description");
        matkul.setMulai(date);
        matkul.setTahun(date.getYear());
        matkul.setSemester("Genap");
        matkul.setMajor("Ilmu Komputer");
        matkul.setAngkatan(2019);
    }

    @Test
    void testMethodBuatString() {
        List expected = new ArrayList<>();
        expected.add("MATKUL\n\n");
        expected.add("Nama matkul: " + matkul.getTitle());
        expected.add("Nama pengajar: " + matkul.getPengajar());
        expected.add("Jurusan: " + matkul.getMajor());
        expected.add("Angkatan Tujuan: " + matkul.getAngkatan());
        expected.add("Tanggal Mulai: " + matkul.getDateFormatted());
        expected.add("Tahun ajaran: " + matkul.getTahun() + " - " + matkul.getSemester());
        expected.add("Deskripsi: " + matkul.getDescription());
        List<String> result = matkul.buatString();
        Assertions.assertIterableEquals(expected, result);
    }

    @Test
    void testAssignmentEquals() {
        Matkul matkul2 = new Matkul("TBA");
        Assertions.assertEquals(false, matkul.equals(matkul2));
        Assertions.assertEquals(true, matkul.equals(matkul));
    }
}
