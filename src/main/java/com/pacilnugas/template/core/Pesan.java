package com.pacilnugas.template.core;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Pesan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pesanId;

    private String nama;
    private String motivasi;
    private String menambahQuotes;
    private String kategoriPesan;

    /**
     * Represents a procedure of making a pesan.
     */
    public final List membuatPesan() {
        List list = new ArrayList();
        list.add(untukSiapa());
        list.add(pembukaan());
        list.add(pesanMotivasi());
        if (inginMenambahQuotes()) {
            list.add(quotes());
        }
        list.add(harapan());
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
