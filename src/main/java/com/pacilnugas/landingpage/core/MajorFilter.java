package com.pacilnugas.landingpage.core;

import com.pacilnugas.activities.model.Assignment;

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
    public List<Assignment> applyFilter(List<Assignment> assignmentList) {
        String value = getValue();
        if (value.equals("")) {
            return assignmentList;
        }

        // Search for assignment that has the specified major
        List<Assignment> assignmentsByMajor = new ArrayList<>();
        for (Assignment assignment : assignmentList) {
            if (assignment.getMajor().equals(value)) {
                assignmentsByMajor.add(assignment);
            }
        }
        return assignmentsByMajor;
    }
}
