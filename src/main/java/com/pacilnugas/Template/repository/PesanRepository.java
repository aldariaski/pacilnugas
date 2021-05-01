package com.pacilnugas.Template.repository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PesanRepository {
    private List<List> list;

    public PesanRepository() {
        this.list = new ArrayList<>();
    }

    public List<List> getPesan() {
        return list;
    }

    public List add(List pesan) {
        list.add(pesan);
        return pesan;
    }
}
