package com.pacilnugas.activities.service;

import com.pacilnugas.activities.model.Assignment;

import java.time.*;
import java.util.List;

public interface AssignmentService {
    Assignment createAssignment(String title, String description,
            String matkul, String tahunajaran, LocalDate deadline, LocalTime time);

    List<List> getAllAssignment();

    List getAssignmentById(int idassignment);
}

