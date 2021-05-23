package com.pacilnugas.activities.service;

import com.pacilnugas.activities.model.Matkul;
import com.pacilnugas.activities.repository.MatkulRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MatkulServiceImpl implements MatkulService {
    @Autowired
    MatkulRepository matkulRepository;

    @Override
    public Matkul createMatkul(String title, String description, int tahun, String semester) {
        Matkul matkul = new Matkul();

        //Handle this saat diintegrasikan dengan sistem auth
        String maker_username = "budibudiman1";
        matkul.setPengajar(maker_username);

        matkul.setTitle(title);
        matkul.setDescription(description);
        matkul.setTahun(tahun);
        matkul.setSemester(semester);

        matkulRepository.save(matkul);
        return matkul;
    }

    @Override
    public List<List> getAllMatkul() {
        List<Matkul> allMatkul = matkulRepository.findAll();
        List<List> matkulList = new ArrayList<>();
        for (Matkul matkul : allMatkul) {
            matkulList.add(matkul.buatString());
        }

        return matkulList;
    }
}
