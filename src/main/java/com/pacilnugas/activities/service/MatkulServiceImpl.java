package com.pacilnugas.activities.service;

import com.pacilnugas.activities.model.Assignment;
import com.pacilnugas.activities.model.Matkul;
import com.pacilnugas.activities.repository.MatkulRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MatkulServiceImpl implements MatkulService {
    @Autowired
    MatkulRepository matkulRepository;

    @Override
    public List<List> getAllMatkulOfMahasiswa(String username) {
        List<Matkul> allMatkul = matkulRepository.findAll();
        List<List> matkulList = new ArrayList<>();
        for (Matkul matkul : allMatkul) {
            matkulList.add(matkul.buatString());
        }

        return matkulList;
    }

    @Override
    public Matkul createMatkul(String title, String description, LocalDate mulai, int tahun,
                               String semester, String major, int angkatan) {
        Matkul matkul = new Matkul();

        //Handle this saat diintegrasikan dengan sistem auth
        String maker_username = "budibudiman1";
        matkul.setPengajar(maker_username);

        matkul.setTitle(title);
        matkul.setDescription(description);
        matkul.setMulai(mulai);
        matkul.setTahun(tahun);
        matkul.setSemester(semester);
        matkul.setMajor(major);
        matkul.setAngkatan(angkatan);

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

    @Override
    public int getIdByTitle(String title) {
        int idMatkul = 0;
        List<Matkul> matkulList = matkulRepository.findAll();
        for (Matkul matkul : matkulList) {
            if (matkul.getTitle().equals(title)) {
                return matkul.getIdAssignment();
            }
        }
        return idMatkul;
    }

    @Override
    public List getAllMatkulObject() {
        List<Matkul> matkulList = matkulRepository.findAll();
        return matkulList;
    }

    @Override
    public Matkul getMatkulById(int id) {
        Matkul matkul = matkulRepository.findById(id).get();
        return matkul;
    }

    @Override
    public Matkul getMatkulByNama(String nama) {
        List<Matkul> matkulList = matkulRepository.findAll();
        for (Matkul tiapMatkul: matkulList) {
            if (tiapMatkul.getTitle().equals(nama)) {
                return tiapMatkul;
            }
        }
        return null;
    }

    @Override
    public Matkul updateMatkul(int idassignment, String title, String description, String matkul, LocalDate deadline,
                                LocalTime time){
        Matkul matkulReturn = matkulRepository.findById(idassignment).get();
        return matkulReturn;
    }

}
