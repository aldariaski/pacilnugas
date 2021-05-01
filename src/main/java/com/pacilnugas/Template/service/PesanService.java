package com.pacilnugas.Template.service;

import com.pacilnugas.Template.core.Pesan;

import java.util.List;

public interface PesanService {
    List<Pesan> getListPesan();
    Pesan createPesan(String kategori, String nama, String motivasi, String nambahQuotes);
    List<List> getDaftarPesan();
}
