package com.pacilnugas.activities.core;

import com.pacilnugas.activities.model.Assignment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class YearFilterTest {
    private YearFilter yearFilter;
    private Assignment assignment1;
    private Assignment assignment2;
    private Assignment assignment3;
    private List<Assignment> filteredAssignmentList;
    private List<Assignment> assignmentList;

    @BeforeEach
    public void setUp() {
        assignment1 = new Assignment("Group Project - Adpro", "Ilmu Komputer", 2019, LocalDateTime.now());
        assignment2 = new Assignment("Lab 4 - DDAK", "Sistem Informasi", 2020, LocalDateTime.now());
        assignment3 = new Assignment("Lab 4 - POK", "Ilmu Komputer", 2020, LocalDateTime.now());
        assignmentList = new ArrayList<>();
        assignmentList.add(assignment1);
        assignmentList.add(assignment2);
        assignmentList.add(assignment3);

        filteredAssignmentList = new ArrayList<>();
    }

    @Test
    public void TestYearFilterWithoutValue() {
        filteredAssignmentList = assignmentList;

        yearFilter = new YearFilter();
        List<Assignment> result = yearFilter.applyFilter(assignmentList);
        Assertions.assertIterableEquals(filteredAssignmentList, result);
    }

    @Test
    public void TestYearFilterWithValue() {
        filteredAssignmentList.add(assignment2);
        filteredAssignmentList.add(assignment3);

        yearFilter = new YearFilter(2020);
        List<Assignment> result = yearFilter.applyFilter(assignmentList);
        Assertions.assertIterableEquals(filteredAssignmentList, result);
    }
}
