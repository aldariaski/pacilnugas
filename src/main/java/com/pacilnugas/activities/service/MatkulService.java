package com.pacilnugas.activities.service;

import com.pacilnugas.activities.model.Assignment;
import com.pacilnugas.activities.model.Matkul;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface MatkulService {
    Matkul createMatkul(String title, String description,
            int tahun, String semester);

    List<List> getAllMatkul();

    List<List> getAllMatkulOfMahasiswa(String username);

    List getAllMatkulObject();

    Matkul getMatkulById(int idmatkul);

    Matkul getMatkulByNama(String nama);

    Matkul updateMatkul(int idassignment, String title, String description,
                                String matkul, LocalDate deadline,
                                LocalTime time);
}

