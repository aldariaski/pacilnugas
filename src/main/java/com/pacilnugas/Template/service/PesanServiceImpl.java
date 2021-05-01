package com.pacilnugas.Template.service;

import com.pacilnugas.Template.core.PesanBebas;
import com.pacilnugas.Template.core.PesanTugas;
import com.pacilnugas.Template.repository.PesanRepository;
import com.pacilnugas.Template.core.PesanUjian;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PesanServiceImpl implements PesanService{
    private PesanRepository repo;

    public PesanServiceImpl(PesanRepository repo) {
        this.repo = repo;
    }

    public PesanServiceImpl() {
        this(new PesanRepository());
    }

    @Override
    public List<List> getPesan() {
        return repo.getPesan();
    }

    @Override
    public List createPesan(String kategori, String nama, String motivasi, String nambahQuotes) {
        //Pesan res = null; ga bisa soalnya Pesan abstract class
        if (kategori.equalsIgnoreCase("Ujian")) {
            PesanUjian res = new PesanUjian();
            res.setNama(nama);
            res.setMotivasi(motivasi);
            res.setMenambahQuotes(nambahQuotes);
            return repo.add(res.membuatPesan());
        } else if (kategori.equalsIgnoreCase("Tugas")) {
            PesanTugas res = new PesanTugas();
            res.setNama(nama);
            res.setMotivasi(motivasi);
            res.setMenambahQuotes(nambahQuotes);
            return repo.add(res.membuatPesan());
        } else if (kategori.equalsIgnoreCase("Bebas")) {
            PesanBebas res = new PesanBebas();
            res.setNama(nama);
            res.setMotivasi(motivasi);
            res.setMenambahQuotes(nambahQuotes);
            return repo.add(res.membuatPesan());
        }
        return null;
    }

}
