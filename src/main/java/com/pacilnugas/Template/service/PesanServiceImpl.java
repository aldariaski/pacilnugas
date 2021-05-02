package com.pacilnugas.Template.service;

import com.pacilnugas.Template.core.Pesan;
import com.pacilnugas.Template.core.PesanBebas;
import com.pacilnugas.Template.core.PesanTugas;
import com.pacilnugas.Template.repository.PesanRepository;
import com.pacilnugas.Template.core.PesanUjian;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PesanServiceImpl implements PesanService{
    @Autowired
    private PesanRepository repo;

    @Override
    public List<Pesan> getListPesan() {
        return repo.findAll();
    }

    @Override
    public Pesan createPesan(String kategori, String nama, String motivasi, String nambahQuotes) {
        if (kategori.equalsIgnoreCase("Ujian")) {
            PesanUjian res = new PesanUjian();
            res.setNama(nama);
            res.setMotivasi(motivasi);
            res.setMenambahQuotes(nambahQuotes);
            res.setKategoriPesan(kategori);
            repo.save(res);
            return res;
        } else if (kategori.equalsIgnoreCase("Tugas")) {
            PesanTugas res = new PesanTugas();
            res.setNama(nama);
            res.setMotivasi(motivasi);
            res.setMenambahQuotes(nambahQuotes);
            res.setKategoriPesan(kategori);
            repo.save(res);
            return res;
        } else if (kategori.equalsIgnoreCase("Bebas")) {
            PesanBebas res = new PesanBebas();
            res.setNama(nama);
            res.setMotivasi(motivasi);
            res.setMenambahQuotes(nambahQuotes);
            res.setKategoriPesan(kategori);
            repo.save(res);
            return res;
        }
        return null;
    }

    @Override
    public List<List> getDaftarPesan() {
        List<Pesan> list = getListPesan();
        List<List> pesanList = new ArrayList<>();
        for (Pesan pesan : list) {
            pesanList.add(pesan.membuatPesan());
        }
        return pesanList;
    }

}
