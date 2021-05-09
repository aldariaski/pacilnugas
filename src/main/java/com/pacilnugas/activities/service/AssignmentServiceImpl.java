package com.pacilnugas.activities.service;

import com.pacilnugas.activities.model.Assignment;
import com.pacilnugas.activities.repository.AssignmentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AssignmentServiceImpl implements AssignmentService {
    AssignmentRepository assignmentRepository;

    @Override
    public Assignment createAssignment(String title, String description, String matkul, String tahunajaran, String deadline) {
        Assignment assignment = new Assignment();

        //Handle this saat diintegrasikan dengan sistem auth
        String maker_username = "budibudiman1";
        assignment.setMaker_username(maker_username);

        assignment.setTitle(title);
        assignment.setDescription(description);
        assignment.setMatkul(matkul);

        LocalDate parsed = LocalDate.parse(deadline);
        assignment.setDeadline(parsed);

        assignmentRepository.save(assignment);
        return assignment;
    }

    @Override
    public List<List> getAllAssignment() {
        List<Assignment> allAssignment = assignmentRepository.findAll();
        List<List> assignmentList = new ArrayList<>();
        return assignmentList;
    }


}
