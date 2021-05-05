package com.pacilnugas.pesan.service;

import com.pacilnugas.template.core.Pesan;
import com.pacilnugas.template.core.PesanBebas;
import com.pacilnugas.template.core.PesanTugas;
import com.pacilnugas.template.core.PesanUjian;
import com.pacilnugas.template.repository.PesanRepository;
import com.pacilnugas.template.service.PesanServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class PesanServiceImplTest {
    @Mock
    private PesanRepository pesanRepository;

    @InjectMocks
    private PesanServiceImpl pesanService;

    private PesanUjian pesan1;
    private PesanTugas pesan2;
    private PesanBebas pesan3;
    private List<Pesan> pesans;

    @BeforeEach
    public void setUp() {
        pesan1 = new PesanUjian();
        pesan1.setNama("M");
        pesan1.setMotivasi("Senyum");
        pesan1.setMenambahQuotes("Yes");
        pesan1.setKategoriPesan("Ujian");

        pesan2 = new PesanTugas();
        pesan2.setNama("Quan");
        pesan2.setMotivasi("Bisa");
        pesan2.setMenambahQuotes("Yes");
        pesan2.setKategoriPesan("Tugas");

        pesan3 = new PesanBebas();
        pesan3.setNama("All");
        pesan3.setMotivasi("Chill");
        pesan3.setMenambahQuotes("Yes");
        pesan3.setKategoriPesan("Bebas");

        pesans = new ArrayList<>();
        pesans.add(pesan1);
        pesans.add(pesan2);
        pesans.add(pesan3);
        lenient().when(pesanRepository.findAll()).thenReturn(pesans);
    }

    @Test
    void testServiceGetListPesan() {
        List<Pesan> listPesan = pesanRepository.findAll();
        lenient().when(pesanService.getListPesan()).thenReturn(listPesan);
        List<Pesan> listPesanResult = pesanService.getListPesan();
        Assertions.assertIterableEquals(listPesan, listPesanResult);
    }

    @Test
    void testServiceCreatePesan() {
        lenient().when(pesanService.createPesan("Ujian", "M", "Senyum", "Yes")).thenReturn(pesan1);
        Pesan resultPesan1 = pesanService.createPesan("Ujian", "M", "Senyum", "Yes");
        assertEquals(pesan1.getNama(), resultPesan1.getNama());

        lenient().when(pesanService.createPesan("Tugas", "Quan", "Bisa", "Yes")).thenReturn(pesan2);
        Pesan resultPesan2 = pesanService.createPesan("Tugas", "Quan", "Bisa", "Yes");
        assertEquals(pesan2.getKategoriPesan(), resultPesan2.getKategoriPesan());

        lenient().when(pesanService.createPesan("Bebas", "All", "Chill", "Yes")).thenReturn(pesan3);
        Pesan resultPesan3 = pesanService.createPesan("Bebas", "All", "Chill", "Yes");
        assertEquals(pesan3.getMotivasi(), resultPesan3.getMotivasi());
    }

    @Test
    void testServiceGetDaftarPesan() {
        List<List> resultPesan = new ArrayList<>();
        List pesanNew1 = pesan1.membuatPesan();
        List acquiredPesan1 = pesanRepository.findAll().get(0).membuatPesan();
        assertEquals(acquiredPesan1, pesanNew1);
        resultPesan.add(pesanNew1);
        List pesanNew2 = pesan2.membuatPesan();
        List acquiredPesan2 = pesanRepository.findAll().get(1).membuatPesan();
        assertEquals(acquiredPesan2, pesanNew2);
        resultPesan.add(pesanNew2);
        List pesanNew3 = pesan3.membuatPesan();
        List acquiredPesan3 = pesanRepository.findAll().get(2).membuatPesan();
        assertEquals(acquiredPesan3, pesanNew3);
        resultPesan.add(pesanNew3);
        List<List> ofPesan = pesanService.getDaftarPesan();
        assertEquals(ofPesan, resultPesan);

    }

}
