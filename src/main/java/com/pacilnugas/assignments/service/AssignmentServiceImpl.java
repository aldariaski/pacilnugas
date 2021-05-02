package com.pacilnugas.assignments.service;

import com.pacilnugas.assignments.model.Assignment;
import com.pacilnugas.assignments.repository.AssignmentRepository;

public class AssignmentServiceImpl {
    @Override
    public Mahasiswa createMahasiswa(Assignment assignment) {
        assignmentRepository.save(assignment);
        return mahasiswa;
    }

}
