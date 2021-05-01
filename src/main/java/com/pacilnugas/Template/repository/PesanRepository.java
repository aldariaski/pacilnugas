package com.pacilnugas.Template.repository;

import com.pacilnugas.Template.core.Pesan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface PesanRepository extends JpaRepository<Pesan, Integer> {
//    private List<List> list;
//
//    public PesanRepository() {
//        this.list = new ArrayList<>();
//    }
//
//    public List<List> getPesan() {
//        return list;
//    }
//
//    public List add(List pesan) {
//        list.add(pesan);
//        return pesan;
//    }
}
