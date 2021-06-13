package com.pacilnugas.activities.repository;

import com.pacilnugas.activities.model.Assignment;
import com.pacilnugas.activities.model.Matkul;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatkulRepository extends JpaRepository<Matkul, Integer> {
    Matkul findByTitle(String title);
}
