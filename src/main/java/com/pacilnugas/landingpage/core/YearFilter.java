package com.pacilnugas.landingpage.core;

import com.pacilnugas.landingpage.model.AssignmentFake;

import java.util.ArrayList;
import java.util.List;

public class YearFilter extends ViewFilter<Integer> {
    public YearFilter(int value) {
        super(value);
    }

    public YearFilter() {
        this(0);
    }

    @Override
    public List<AssignmentFake> applyFilter(List<AssignmentFake> assignmentList) {
        int value = getValue();
        // If the filter is null or 0 then we can just return the given list
        if (value == 0) return assignmentList;

        // Search for assignment that is related to specified year
        List<AssignmentFake> assignmentsByYear = new ArrayList<>();
        for (AssignmentFake assignment : assignmentList) {
            if (assignment.getAngkatan() == value) {
                assignmentsByYear.add(assignment);
            }
        }
        return assignmentsByYear;
    }
}
