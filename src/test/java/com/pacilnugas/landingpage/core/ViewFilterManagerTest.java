package com.pacilnugas.landingpage.core;

import com.pacilnugas.landingpage.model.AssignmentFake;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ViewFilterManagerTest {
    private AssignmentFake assignment1;
    private AssignmentFake assignment2;
    private AssignmentFake assignment3;
    private List<AssignmentFake> assignmentList;

    @BeforeEach
    public void setUp() {
        assignment1 = new AssignmentFake("Group Project - Adpro", "Ilmu Komputer", 2019, LocalDateTime.now());
        assignment2 = new AssignmentFake("Lab 4 - DDAK", "Sistem Informasi", 2020, LocalDateTime.now());
        assignment3 = new AssignmentFake("Lab 4 - POK", "Ilmu Komputer", 2020, LocalDateTime.now());
        assignmentList = new ArrayList<>();
        assignmentList.add(assignment1);
        assignmentList.add(assignment2);
        assignmentList.add(assignment3);
    }

    @Test
    public void TestViewFilterManagerApplyFiltersWithFiltersHasNoValue() {
        List<AssignmentFake> expectedAssignmentList = (new YearFilter()).applyFilter(assignmentList);
        expectedAssignmentList = (new MajorFilter()).applyFilter(expectedAssignmentList);

        List<AssignmentFake> result = ViewFilterManager.applyFilters(assignmentList, new YearFilter(), new MajorFilter());
        Assertions.assertIterableEquals(expectedAssignmentList, result);
    }

    @Test
    public void TestViewFilterManagerApplyFiltersWithFilterYearHasValue2020() {
        List<AssignmentFake> expectedAssignmentList = (new YearFilter(2020)).applyFilter(assignmentList);
        expectedAssignmentList = (new MajorFilter()).applyFilter(expectedAssignmentList);

        List<AssignmentFake> result = ViewFilterManager.applyFilters(assignmentList, new YearFilter(2020), new MajorFilter());
        Assertions.assertIterableEquals(expectedAssignmentList, result);
    }

    @Test
    public void TestViewFilterManagerApplyFiltersWithFilterMajorHasValueIlmuKomputer() {
        List<AssignmentFake> expectedAssignmentList = (new YearFilter()).applyFilter(assignmentList);
        expectedAssignmentList = (new MajorFilter("Ilmu Komputer")).applyFilter(expectedAssignmentList);

        List<AssignmentFake> result = ViewFilterManager.applyFilters(assignmentList, new YearFilter(), new MajorFilter("Ilmu Komputer"));
        Assertions.assertIterableEquals(expectedAssignmentList, result);
    }

    @Test
    public void TestViewFilterManagerApplyFiltersWithFiltersYearHasValue2020AndMajorHasValueIlmuKomputer() {
        List<AssignmentFake> expectedAssignmentList = (new YearFilter(2020)).applyFilter(assignmentList);
        expectedAssignmentList = (new MajorFilter("Ilmu Komputer")).applyFilter(expectedAssignmentList);

        List<AssignmentFake> result = ViewFilterManager.applyFilters(assignmentList, new YearFilter(2020), new MajorFilter("Ilmu Komputer"));
        Assertions.assertIterableEquals(expectedAssignmentList, result);
    }
}
