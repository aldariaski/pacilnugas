package com.pacilnugas.activities.repository;

import com.pacilnugas.activities.model.Activity;
import com.pacilnugas.activities.model.Assignment;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

//TODO: Add content repo
public interface AssignmentRepository extends JpaRepository<Assignment, Integer> {
}
