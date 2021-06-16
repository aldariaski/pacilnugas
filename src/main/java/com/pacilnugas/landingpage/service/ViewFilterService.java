package com.pacilnugas.landingpage.service;

import com.pacilnugas.activities.model.Assignment;

import java.util.List;

public interface ViewFilterService {
    List<Assignment> getListAssignment(int year, String major);
}
