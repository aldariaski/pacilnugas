package com.pacilnugas.activities.service;

import com.pacilnugas.activities.model.Assignment;
import com.pacilnugas.activities.model.Matkul;
import com.pacilnugas.activities.repository.AssignmentRepository;
import com.pacilnugas.activities.repository.MatkulRepository;
import com.pacilnugas.activities.service.AssignmentService;
import com.pacilnugas.activities.service.MatkulService;
import com.pacilnugas.template.core.PesanBebas;
import com.pacilnugas.template.core.PesanTugas;
import com.pacilnugas.template.core.PesanUjian;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class MatkulAssignServiceTest {
    @Mock
    private AssignmentRepository assignmentRepository;
    @Mock
    private MatkulRepository matkulRepository;
    @InjectMocks
    private AssignmentService assignmentService;
    @InjectMocks
    private MatkulService matkulService;

    private Matkul matkul1;
    private Matkul matkul2;
    private Assignment assignment1;
    private Assignment assignment2;

    @BeforeEach
    private void setUp() {
        //Setup matkul first
        LocalDate waktumatkul1 = LocalDate.of(2020, 1, 8);
        matkulService.createMatkul("Adpro", "Belajar programming",
                waktumatkul1, 2020, "Ganjil", "Ilmu Komputer", 2019);


        //Assignments
        LocalDate deadline1 = LocalDate.of(2020, 1, 8);
        LocalTime time1 = LocalTime.of(12, 5);
        assignmentService.createAssignment("Tugas 1 Adpro", "Belajar Pattern", "Adpro",
                "2019", deadline1, time1);
        assignment1 = assignmentService.getAssignmentByNama("Tugas 1 Adpro");

    }

    void testMatkulLombokDataHashcode() {
        int hashcode1 = assignment1.hashCode();
        assertEquals(1, hashcode1);
    }
}
