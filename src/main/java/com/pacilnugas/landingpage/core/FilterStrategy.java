package com.pacilnugas.landingpage.core;

import com.pacilnugas.activities.model.Assignment;

import java.util.List;

public interface FilterStrategy {
    List<Assignment> applyFilter(List<Assignment> assignmentList);
}
