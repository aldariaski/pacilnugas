package com.pacilnugas.landingpage.core;

import com.pacilnugas.activities.model.Assignment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class ViewFilterManagerTest {
    private Assignment assignment1;
    private Assignment assignment2;
    private Assignment assignment3;
    private List<Assignment> assignmentList;

    @BeforeEach
    public void setUp() {
        assignment1 = new Assignment(
                "Group Project - Adpro", "Ilmu Komputer", 2019, LocalDateTime.now());
        assignment2 = new Assignment(
                "Lab 4 - DDAK", "Sistem Informasi", 2020, LocalDateTime.now());
        assignment3 = new Assignment(
                "Lab 4 - POK", "Ilmu Komputer", 2020, LocalDateTime.now());
        assignmentList = new ArrayList<>();
        assignmentList.add(assignment1);
        assignmentList.add(assignment2);
        assignmentList.add(assignment3);
    }

    @Test
    void testViewFilterManagerApplyFiltersWithFiltersHasNoValue() {
        List<Assignment> expectedList = (new YearFilter()).applyFilter(assignmentList);
        expectedList = (new MajorFilter()).applyFilter(expectedList);

        List<Assignment> result = ViewFilterManager.applyFilters(
                assignmentList, new YearFilter(), new MajorFilter());
        Assertions.assertIterableEquals(expectedList, result);
    }

    @Test
    void testViewFilterManagerApplyFiltersWithFilterYear2020() {
        List<Assignment> expectedList = (new YearFilter(2020)).applyFilter(assignmentList);
        expectedList = (new MajorFilter()).applyFilter(expectedList);

        List<Assignment> result = ViewFilterManager.applyFilters(
                assignmentList, new YearFilter(2020), new MajorFilter());
        Assertions.assertIterableEquals(expectedList, result);
    }

    @Test
    void testViewFilterManagerApplyFiltersWithFilterMajorIlmuKomputer() {
        List<Assignment> expectedList = (new YearFilter()).applyFilter(assignmentList);
        expectedList = (new MajorFilter("Ilmu Komputer")).applyFilter(expectedList);

        List<Assignment> result = ViewFilterManager.applyFilters(
                assignmentList, new YearFilter(), new MajorFilter("Ilmu Komputer"));
        Assertions.assertIterableEquals(expectedList, result);
    }

    @Test
    void testViewFilterManagerApplyFiltersWithFiltersYear2020AndMajorIlmuKomputer() {
        List<Assignment> expectedList = (new YearFilter(2020)).applyFilter(assignmentList);
        expectedList = (new MajorFilter("Ilmu Komputer")).applyFilter(expectedList);

        List<Assignment> result = ViewFilterManager.applyFilters(
                assignmentList, new YearFilter(2020), new MajorFilter("Ilmu Komputer"));
        Assertions.assertIterableEquals(expectedList, result);
    }
}
