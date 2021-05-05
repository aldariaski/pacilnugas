package com.pacilnugas.landingpage.core;

import com.pacilnugas.landingpage.model.AssignmentFake;

import java.util.List;

public interface FilterStrategy {
    List<AssignmentFake> applyFilter(List<AssignmentFake> assignmentList);
}
