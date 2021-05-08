package com.pacilnugas.activities.service;

import com.pacilnugas.activities.model.Assignment;
import com.pacilnugas.activities.repository.AssignmentRepository;
import org.springframework.stereotype.Service;

@Service
public class AssignmentServiceImpl implements AssignmentService {
    AssignmentRepository assignmentRepository;

    /*@Override
    public Assignment createAssignment(Assignment assignment) {
        assignmentRepository.save(assignment);
        return assignment;
    }*/

    public Assignment createAssignment(Assignment assignment) {
        assignmentRepository.save(assignment);
        return assignment;
    }

}
