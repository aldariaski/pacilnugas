package com.pacilnugas.activities.repository;

import com.pacilnugas.activities.model.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentRepository extends JpaRepository<Assignment, Integer> {
}
