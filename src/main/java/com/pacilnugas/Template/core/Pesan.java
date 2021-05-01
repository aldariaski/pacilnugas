package com.pacilnugas.Template.core;

import java.util.ArrayList;
import java.util.List;

public abstract class Pesan {
    private String nama;
    private String motivasi;
    private String menambahQuotes;
    private String kategoriPesan;

    public final List membuatPesan() {
        List list = new ArrayList();
        list.add(untukSiapa());
        list.add(pembukaan()); //
        list.add(pesanMotivasi());
        if (inginMenambahQuotes()) { //
            list.add(quotes());
        }
        list.add(harapan()); //
        return list;
    }

    abstract String pembukaan();
    abstract String harapan();
    abstract String quotes();

    public String untukSiapa() {
        String helloNama = "Hello, " + this.getNama();
        return helloNama;
    }

    public String pesanMotivasi() {
        return getMotivasi();
    }

    //quotes
    public boolean inginMenambahQuotes() {
        return true; //hook method
    }

    public String getMenambahQuotes() {
        return menambahQuotes;
    }

    public void setMenambahQuotes(String menambahQuotes) {
        this.menambahQuotes = menambahQuotes;
    }

    //nama
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    //motivasi
    public String getMotivasi() {
        return motivasi;
    }

    public void setMotivasi(String motivasi) {
        this.motivasi = motivasi;
    }

    //kategori
    public String getKategoriPesan() {
        return kategoriPesan;
    }

    public void setKategoriPesan(String kategoriPesan) {
        this.kategoriPesan = kategoriPesan;
    }


}
