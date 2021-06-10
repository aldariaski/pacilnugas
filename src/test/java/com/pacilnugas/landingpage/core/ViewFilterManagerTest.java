package com.pacilnugas.landingpage.core;

import com.pacilnugas.activities.model.Assignment;
import com.pacilnugas.activities.model.Matkul;
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
