package com.pacilnugas.landingpage.repository;

import com.pacilnugas.landingpage.model.AssignmentFake;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentRepository extends JpaRepository<AssignmentFake, Integer> {
}
