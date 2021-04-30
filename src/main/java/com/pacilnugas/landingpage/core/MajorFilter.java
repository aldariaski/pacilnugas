package com.pacilnugas.landingpage.core;

import com.pacilnugas.landingpage.model.AssignmentFake;

import java.util.ArrayList;
import java.util.List;

public class MajorFilter extends ViewFilter<String> {
    public MajorFilter(String value) {
        super(value);
    }

    public MajorFilter() {
        this("");
    }

    @Override
    public List<AssignmentFake> applyFilter(List<AssignmentFake> assignmentList) {
        String value = getValue();
        // If the filter is null or "" then we can just return the given list
        if (value == null || value.equals("")) return assignmentList;

        // Search for assignment that has the specified major
        List<AssignmentFake> assignmentsByMajor = new ArrayList<>();
        for (AssignmentFake assignment : assignmentList) {
            if (assignment.getMajor().equals(value)) {
                assignmentsByMajor.add(assignment);
            }
        }
        return assignmentsByMajor;
    }
}
