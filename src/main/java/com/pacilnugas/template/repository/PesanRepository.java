package com.pacilnugas.template.repository;

import com.pacilnugas.template.core.Pesan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PesanRepository extends JpaRepository<Pesan, Integer> {
}
