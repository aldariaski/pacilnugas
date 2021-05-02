package com.pacilnugas.pesan.service;

import com.pacilnugas.Template.core.Pesan;
import com.pacilnugas.Template.core.PesanBebas;
import com.pacilnugas.Template.core.PesanTugas;
import com.pacilnugas.Template.core.PesanUjian;
import com.pacilnugas.Template.repository.PesanRepository;
import com.pacilnugas.Template.service.PesanServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

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
        pesan1.setNama("Maung");
        pesan1.setMotivasi("Semangat semua!");
        pesan1.setMenambahQuotes("Yes");
        pesan1.setKategoriPesan("Ujian");

        pesan2 = new PesanTugas();
        pesan2.setNama("Quanta");
        pesan2.setMotivasi("Ganbatte");
        pesan2.setMenambahQuotes("Yes");
        pesan2.setKategoriPesan("Tugas");

        pesan3 = new PesanBebas();
        pesan3.setNama("Kamu");
        pesan3.setMotivasi("Jangan stres");
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
        lenient().when(pesanService.createPesan("Ujian", "Maung", "Semangat semua!", "Yes")).thenReturn(pesan1);
        Pesan resultPesan1 = pesanService.createPesan("Ujian", "Maung", "Semangat semua!", "Yes");
        Assertions.assertEquals(pesan1.getNama(), resultPesan1.getNama());

        lenient().when(pesanService.createPesan("Tugas", "Quanta", "Ganbatte", "Yes")).thenReturn(pesan2);
        Pesan resultPesan2 = pesanService.createPesan("Tugas", "Quanta", "Ganbatte", "Yes");
        Assertions.assertEquals(pesan2.getKategoriPesan(), resultPesan2.getKategoriPesan());

        lenient().when(pesanService.createPesan("Bebas", "Kamu", "Jangan stres", "Yes")).thenReturn(pesan3);
        Pesan resultPesan3 = pesanService.createPesan("Bebas", "Kamu", "Jangan stres", "Yes");
        Assertions.assertEquals(pesan3.getMotivasi(), resultPesan3.getMotivasi());
    }

    @Test
    void testServiceGetDaftarPesan() {
        List pesanNew1 = pesan1.membuatPesan();
        List acquiredPesan1 = pesanRepository.findAll().get(0).membuatPesan();
        assertEquals(acquiredPesan1, pesanNew1);

        List pesanNew2 = pesan2.membuatPesan();
        List acquiredPesan2 = pesanRepository.findAll().get(1).membuatPesan();
        assertEquals(acquiredPesan2, pesanNew2);

        List pesanNew3 = pesan3.membuatPesan();
        List acquiredPesan3 = pesanRepository.findAll().get(2).membuatPesan();
        assertEquals(acquiredPesan3, pesanNew3);
    }

}
