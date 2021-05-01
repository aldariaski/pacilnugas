package com.pacilnugas.Template.service;

import java.util.List;

public interface PesanService {
    List<List> getPesan();
    List createPesan(String kategori, String nama, String motivasi, String nambahQuotes);
}
