package com.pacilnugas.template.service;

import com.pacilnugas.template.core.Pesan;

import java.util.List;

public interface PesanService {
    List<Pesan> getListPesan();

    Pesan createPesan(String kategori, String nama, String motivasi, String nambahQuotes);

    List<List> getDaftarPesan();
}
