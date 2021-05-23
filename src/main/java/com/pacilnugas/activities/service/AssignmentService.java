package com.pacilnugas.activities.service;

import com.pacilnugas.activities.model.Assignment;
import java.util.List;

public interface AssignmentService {
    Assignment createAssignment(String title, String description,
            String matkul, String tahunajaran, String deadline);

    List<List> getAllAssignment();
}

