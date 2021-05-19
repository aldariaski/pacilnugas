package com.pacilnugas.landingpage.core;

import com.pacilnugas.landingpage.model.AssignmentFake;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class YearFilterTest {
    private YearFilter yearFilter;
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
    }

    @Test
    void testYearFilterWithoutValue() {
        filteredAssignmentList = assignmentList;

        yearFilter = new YearFilter();
        List<AssignmentFake> result = yearFilter.applyFilter(assignmentList);
        Assertions.assertIterableEquals(filteredAssignmentList, result);
    }

    @Test
    void testYearFilterWithValue() {
        filteredAssignmentList.add(assignment2);
        filteredAssignmentList.add(assignment3);

        yearFilter = new YearFilter(2020);
        List<AssignmentFake> result = yearFilter.applyFilter(assignmentList);
        Assertions.assertIterableEquals(filteredAssignmentList, result);
    }
}
