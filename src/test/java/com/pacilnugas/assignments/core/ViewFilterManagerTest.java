package com.pacilnugas.assignments.core;

import com.pacilnugas.assignments.model.Assignment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ViewFilterManagerTest {
    private Assignment assignment1;
    private Assignment assignment2;
    private Assignment assignment3;
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
    }

    @Test
    public void TestViewFilterManagerApplyFiltersWithFiltersHasNoValue() {
        List<Assignment> expectedAssignmentList = (new YearFilter()).applyFilter(assignmentList);
        expectedAssignmentList = (new MajorFilter()).applyFilter(expectedAssignmentList);

        List<Assignment> result = ViewFilterManager.applyFilters(assignmentList, new YearFilter(), new MajorFilter());
        Assertions.assertIterableEquals(expectedAssignmentList, result);
    }

    @Test
    public void TestViewFilterManagerApplyFiltersWithFilterYearHasValue2020() {
        List<Assignment> expectedAssignmentList = (new YearFilter(2020)).applyFilter(assignmentList);
        expectedAssignmentList = (new MajorFilter()).applyFilter(expectedAssignmentList);

        List<Assignment> result = ViewFilterManager.applyFilters(assignmentList, new YearFilter(2020), new MajorFilter());
        Assertions.assertIterableEquals(expectedAssignmentList, result);
    }

    @Test
    public void TestViewFilterManagerApplyFiltersWithFilterMajorHasValueIlmuKomputer() {
        List<Assignment> expectedAssignmentList = (new YearFilter()).applyFilter(assignmentList);
        expectedAssignmentList = (new MajorFilter("Ilmu Komputer")).applyFilter(expectedAssignmentList);

        List<Assignment> result = ViewFilterManager.applyFilters(assignmentList, new YearFilter(), new MajorFilter("Ilmu Komputer"));
        Assertions.assertIterableEquals(expectedAssignmentList, result);
    }

    @Test
    public void TestViewFilterManagerApplyFiltersWithFiltersYearHasValue2020AndMajorHasValueIlmuKomputer() {
        List<Assignment> expectedAssignmentList = (new YearFilter(2020)).applyFilter(assignmentList);
        expectedAssignmentList = (new MajorFilter("Ilmu Komputer")).applyFilter(expectedAssignmentList);

        List<Assignment> result = ViewFilterManager.applyFilters(assignmentList, new YearFilter(2020), new MajorFilter("Ilmu Komputer"));
        Assertions.assertIterableEquals(expectedAssignmentList, result);
    }
}
