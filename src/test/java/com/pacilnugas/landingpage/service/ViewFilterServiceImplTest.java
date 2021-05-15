package com.pacilnugas.landingpage.service;

import com.pacilnugas.landingpage.model.AssignmentFake;
import com.pacilnugas.landingpage.repository.AssignmentFakeRepository;
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
    private AssignmentFakeRepository assignmentRepository;

    private AssignmentFake assignment1;
    private AssignmentFake assignment2;
    private AssignmentFake assignment3;
    private List<AssignmentFake> filteredAssignmentList;
    private List<AssignmentFake> assignmentList;

    @BeforeEach
    public void setUp() {
        assignment1 = new AssignmentFake(
                "Group Project - Adpro", "Ilmu Komputer", 2019, LocalDateTime.now());
        assignment2 = new AssignmentFake(
                "Lab 4 - DDAK", "Sistem Informasi", 2020, LocalDateTime.now());
        assignment3 = new AssignmentFake(
                "Lab 4 - POK", "Ilmu Komputer", 2020, LocalDateTime.now());
        assignmentList = new ArrayList<>();
        assignmentList.add(assignment1);
        assignmentList.add(assignment2);
        assignmentList.add(assignment3);

        filteredAssignmentList = new ArrayList<>();
        lenient().when(assignmentRepository.findAll()).thenReturn(assignmentList);
    }

    @Test
    void testServiceGetListAssignmentWithoutFilter() {
        filteredAssignmentList.add(assignment1);
        filteredAssignmentList.add(assignment2);
        filteredAssignmentList.add(assignment3);

        List<AssignmentFake> result = viewFilterService.getListAssignment(0, "");
        Assertions.assertIterableEquals(filteredAssignmentList, result);
    }

    @Test
    void testServiceGetListAssignmentWithYearFilter() {
        filteredAssignmentList.add(assignment2);
        filteredAssignmentList.add(assignment3);

        List<AssignmentFake> result = viewFilterService.getListAssignment(2020, "");
        Assertions.assertIterableEquals(filteredAssignmentList, result);
    }

    @Test
    void testServiceGetListAssignmentWithMajorFilter() {
        filteredAssignmentList.add(assignment1);
        filteredAssignmentList.add(assignment3);

        List<AssignmentFake> result = viewFilterService.getListAssignment(0, "Ilmu Komputer");
        Assertions.assertIterableEquals(filteredAssignmentList, result);
    }

    @Test
    void testServiceGetListAssignmentWithYearAndMajorFilter() {
        filteredAssignmentList.add(assignment2);

        List<AssignmentFake> result = viewFilterService.getListAssignment(2020, "Sistem Informasi");
        Assertions.assertIterableEquals(filteredAssignmentList, result);
    }
}
