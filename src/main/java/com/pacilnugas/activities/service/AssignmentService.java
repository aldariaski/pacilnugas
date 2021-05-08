package com.pacilnugas.activities.service;

import com.pacilnugas.activities.model.Assignment;

public interface AssignmentService {
    //Assignment createAssignment(Assignment assignment);
    Assignment createAssignment(String title, String matkul, String tahunajaran, String deadline);
}

