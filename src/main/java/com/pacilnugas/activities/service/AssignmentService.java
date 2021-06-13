package com.pacilnugas.activities.service;

import com.pacilnugas.activities.model.Assignment;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface AssignmentService {
    Assignment createAssignment(String title, String description,
            String matkul, String tahunajaran, LocalDate deadline, LocalTime time);

    Assignment createAssignment2(Assignment assignment);

    List<List> getAllAssignment();

    Assignment getAssignmentById(int idassignment);

    Assignment updateAssignment(int idassignment, String title, String description,
                                String matkul, LocalDate deadline,
                                LocalTime time);

    Assignment getAssignmentByNama(String nama);
}

