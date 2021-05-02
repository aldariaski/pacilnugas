package com.pacilnugas.assignments.repository;

import com.pacilnugas.assignments.model.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

//TODO: Add content repo
public interface AssignmentRepository extends JpaRepository<Assignment, Integer> {
}
