package com.pacilnugas.activities.service;

import com.pacilnugas.activities.model.Assignment;
import com.pacilnugas.activities.model.Matkul;
import com.pacilnugas.activities.repository.AssignmentRepository;
import com.pacilnugas.activities.repository.MatkulRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AssignmentServiceImpl implements AssignmentService {
    @Autowired
    AssignmentRepository assignmentRepository;
    @Autowired
    MatkulRepository matkulRepository;
    @Autowired
    MatkulService matkulService;

    @Override
    public Assignment createAssignment(String title, String description, String matkul,
                                       String tahunajaran, LocalDate deadline, LocalTime time) {
        Assignment assignment = new Assignment();

        //Handle this saat diintegrasikan dengan sistem auth
        String maker_username = "budibudiman1";
        assignment.setMaker_username(maker_username);

        Matkul thisMatkul = matkulRepository.findByTitle(matkul);

        assignment.setTitle(title);
        assignment.setDescription(description);
        assignment.setMatkul(matkul);
        assignment.setMatkulObject(thisMatkul);

        assignment.setDeadline(deadline);
        assignment.setTime(time);

        assignmentRepository.save(assignment);
        return assignment;
    }

    @Override
    public Assignment createAssignment2(Assignment assignment) {
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
        Matkul thisMatkul = matkulService.getMatkulByNama(matkul);
        assignment.setMatkulObject(thisMatkul);
        assignmentRepository.save(assignment);
        return assignment;
    }

    @Override
    public Assignment getAssignmentByNama(String nama) {
        List<Assignment> assList = assignmentRepository.findAll();
        for (Assignment tiapAss : assList) {
            if (tiapAss.getTitle().equals(nama)) {
                return tiapAss;
            }
        }
        return null;
    }
}
