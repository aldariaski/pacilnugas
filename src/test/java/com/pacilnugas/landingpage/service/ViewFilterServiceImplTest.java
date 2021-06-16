package com.pacilnugas.landingpage.service;

import com.pacilnugas.activities.model.Assignment;
import com.pacilnugas.activities.model.Matkul;
import com.pacilnugas.activities.repository.AssignmentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class ViewFilterServiceImplTest {
    @InjectMocks
    private ViewFilterServiceImpl viewFilterService;

    @Mock
    private AssignmentRepository assignmentRepository;

    private Assignment assignment1;
    private Assignment assignment2;
    private Assignment assignment3;
    private List<Assignment> filteredAssignmentList;
    private List<Assignment> assignmentList;
    private Matkul matkulIlmuKomputer2019;
    private Matkul matkulIlmuKomputer2020;
    private Matkul matkulSistemInformasi2020;

    @BeforeEach
    public void setUp() {
        setUpMatkul();
        assignment1 = new Assignment(
                "Group Project - Adpro", matkulIlmuKomputer2019, LocalDateTime.now());
        assignment2 = new Assignment(
                "Lab 4 - DDAK", matkulSistemInformasi2020, LocalDateTime.now());
        assignment3 = new Assignment(
                "Lab 4 - POK", matkulIlmuKomputer2020, LocalDateTime.now());
        assignmentList = new ArrayList<>();
        assignmentList.add(assignment1);
        assignmentList.add(assignment2);
        assignmentList.add(assignment3);

        filteredAssignmentList = new ArrayList<>();
        lenient().when(assignmentRepository.findAll()).thenReturn(assignmentList);
    }

    private void setUpMatkul() {
        matkulIlmuKomputer2019 = new Matkul();
        matkulIlmuKomputer2019.setMajor("Ilmu Komputer");
        matkulIlmuKomputer2019.setAngkatan(2019);

        matkulIlmuKomputer2020 = new Matkul();
        matkulIlmuKomputer2020.setMajor("Ilmu Komputer");
        matkulIlmuKomputer2020.setAngkatan(2020);

        matkulSistemInformasi2020 = new Matkul();
        matkulSistemInformasi2020.setMajor("Sistem Informasi");
        matkulSistemInformasi2020.setAngkatan(2020);
    }

    @Test
    void testServiceGetListAssignmentWithoutFilter() {
        filteredAssignmentList.add(assignment1);
        filteredAssignmentList.add(assignment2);
        filteredAssignmentList.add(assignment3);

        List<Assignment> result = viewFilterService.getListAssignment(0, "");
        Assertions.assertIterableEquals(filteredAssignmentList, result);
    }

    @Test
    void testServiceGetListAssignmentWithYearFilter() {
        filteredAssignmentList.add(assignment2);
        filteredAssignmentList.add(assignment3);

        List<Assignment> result = viewFilterService.getListAssignment(2020, "");
        Assertions.assertIterableEquals(filteredAssignmentList, result);
    }

    @Test
    void testServiceGetListAssignmentWithMajorFilter() {
        filteredAssignmentList.add(assignment1);
        filteredAssignmentList.add(assignment3);

        List<Assignment> result = viewFilterService.getListAssignment(0, "Ilmu Komputer");
        Assertions.assertIterableEquals(filteredAssignmentList, result);
    }

    @Test
    void testServiceGetListAssignmentWithYearAndMajorFilter() {
        filteredAssignmentList.add(assignment2);

        List<Assignment> result = viewFilterService.getListAssignment(2020, "Sistem Informasi");
        Assertions.assertIterableEquals(filteredAssignmentList, result);
    }
}
