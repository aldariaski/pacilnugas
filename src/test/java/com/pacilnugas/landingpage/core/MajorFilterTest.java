package com.pacilnugas.landingpage.core;

import com.pacilnugas.activities.model.Assignment;
import com.pacilnugas.activities.model.Matkul;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class MajorFilterTest {
    private MajorFilter majorFilter;
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
    void testMajorFilterWithoutValue() {
        filteredAssignmentList = assignmentList;

        majorFilter = new MajorFilter();
        List<Assignment> result = majorFilter.applyFilter(assignmentList);
        Assertions.assertIterableEquals(filteredAssignmentList, result);
    }

    @Test
    void testMajorFilterWithValue() {
        filteredAssignmentList.add(assignment1);
        filteredAssignmentList.add(assignment3);

        majorFilter = new MajorFilter("Ilmu Komputer");
        List<Assignment> result = majorFilter.applyFilter(assignmentList);
        Assertions.assertIterableEquals(filteredAssignmentList, result);
    }
}
