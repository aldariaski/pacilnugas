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
    }

    @Test
    public void testViewFilterManagerApplyFiltersWithFiltersHasNoValue() {
        List<AssignmentFake> expectedList = (new YearFilter()).applyFilter(assignmentList);
        expectedList = (new MajorFilter()).applyFilter(expectedList);

        List<AssignmentFake> result = ViewFilterManager.applyFilters(
                assignmentList, new YearFilter(), new MajorFilter());
        Assertions.assertIterableEquals(expectedList, result);
    }

    @Test
    public void testViewFilterManagerApplyFiltersWithFilterYear2020() {
        List<AssignmentFake> expectedList = (new YearFilter(2020)).applyFilter(assignmentList);
        expectedList = (new MajorFilter()).applyFilter(expectedList);

        List<AssignmentFake> result = ViewFilterManager.applyFilters(
                assignmentList, new YearFilter(2020), new MajorFilter());
        Assertions.assertIterableEquals(expectedList, result);
    }

    @Test
    public void testViewFilterManagerApplyFiltersWithFilterMajorIlmuKomputer() {
        List<AssignmentFake> expectedList = (new YearFilter()).applyFilter(assignmentList);
        expectedList = (new MajorFilter("Ilmu Komputer")).applyFilter(expectedList);

        List<AssignmentFake> result = ViewFilterManager.applyFilters(
                assignmentList, new YearFilter(), new MajorFilter("Ilmu Komputer"));
        Assertions.assertIterableEquals(expectedList, result);
    }

    @Test
    public void testViewFilterManagerApplyFiltersWithFiltersYear2020AndMajorIlmuKomputer() {
        List<AssignmentFake> expectedList = (new YearFilter(2020)).applyFilter(assignmentList);
        expectedList = (new MajorFilter("Ilmu Komputer")).applyFilter(expectedList);

        List<AssignmentFake> result = ViewFilterManager.applyFilters(
                assignmentList, new YearFilter(2020), new MajorFilter("Ilmu Komputer"));
        Assertions.assertIterableEquals(expectedList, result);
    }
}
