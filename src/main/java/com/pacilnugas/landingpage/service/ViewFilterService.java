package com.pacilnugas.landingpage.service;

import com.pacilnugas.landingpage.model.AssignmentFake;

import java.util.List;

public interface ViewFilterService {
    List<AssignmentFake> getListAssignment(int year, String major);
}
