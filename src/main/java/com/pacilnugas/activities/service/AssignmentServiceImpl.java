package com.pacilnugas.activities.service;

import com.pacilnugas.activities.model.Activity;
import com.pacilnugas.activities.model.Assignment;
import com.pacilnugas.activities.repository.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class AssignmentServiceImpl implements AssignmentService {
    @Autowired
    AssignmentRepository assignmentRepository;

    @Override
    public Assignment createAssignment(String title, String description, String matkul,
                                       String tahunajaran, LocalDate deadline, LocalTime time) {
        Assignment assignment = new Assignment();

        //Handle this saat diintegrasikan dengan sistem auth
        String maker_username = "budibudiman1";
        assignment.setMaker_username(maker_username);

        assignment.setTitle(title);
        assignment.setDescription(description);
        assignment.setMatkul(matkul);

        assignment.setDeadline(deadline);
        assignment.setTime(time);

        assignment.setAngkatan(2019);
        assignment.setMajor("Ilmu Komputer");

        assignmentRepository.save(assignment);
        return assignment;
    }

    @Override
    public List<List> getAllAssignment() {
        List<Assignment> allAssignment = assignmentRepository.findAll();
        List<List> assignmentList = new ArrayList<>();
        for (Assignment tugas : allAssignment) {
            assignmentList.add(tugas.buatString());
        }

        return assignmentList;
    }

    @Override
    public Assignment getAssignmentById(int idassignment) {
        Assignment assignment = assignmentRepository.findById(idassignment).get();
        //List assignmentHere = new ArrayList<>();
        //Untuk di front end, pakai thymeleaf dan akses dengan indeks array-nya
//        assignmentHere.add(assignment.getTitle()); //array[0]
//        assignmentHere.add(assignment.getMatkul());
//        assignmentHere.add(assignment.getDescription()); //[2]
//        assignmentHere.add(assignment.getDeadlineFormatted()); //3
//        assignmentHere.add(assignment.getTime()); //4
//        assignmentHere.add(assignment.getAngkatan());
//        assignmentHere.add(assignment.getMajor()); // 6
//        assignmentHere.add(assignment.getMaker_username()); //7
//        assignmentHere.add(assignment.getId_activity());

        return assignment;
    }

    @Override
    public Assignment updateAssignment(int idassignment, String title, String description,
                                       String matkul, LocalDate deadline,
                                       LocalTime time) {
        Assignment assignment = getAssignmentById(idassignment);
        assignment.setTitle(title);
        assignment.setDescription(description);
        assignment.setMatkul(matkul);
        assignment.setDeadline(deadline);
        assignment.setTime(time);
        assignment.setAngkatan(2019);
        assignment.setMajor("Ilmu Komputer");
        assignmentRepository.save(assignment);
        return assignment;
    }
}
