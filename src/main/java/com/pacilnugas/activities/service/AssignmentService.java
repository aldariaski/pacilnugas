package com.pacilnugas.activities.service;

import com.pacilnugas.activities.model.Assignment;

import java.time.LocalDate;
import java.util.List;

public interface AssignmentService {
    Assignment createAssignment(String title, String description,
            String matkul, String tahunajaran, LocalDate deadline);

    List<List> getAllAssignment();

    List getAssignmentById(int idassignment);
}

