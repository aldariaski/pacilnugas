package com.pacilnugas.activities.service;

import com.pacilnugas.activities.model.Matkul;

import java.util.List;

public interface MatkulService {
    Matkul createMatkul(String title, String description,
            int tahun, String semester);

    List<List> getAllMatkul();
}

