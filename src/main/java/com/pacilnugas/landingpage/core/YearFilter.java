package com.pacilnugas.landingpage.core;

import com.pacilnugas.activities.model.Assignment;

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
    public List<Assignment> applyFilter(List<Assignment> assignmentList) {
        int value = getValue();
        if (value == 0) {
            return assignmentList;
        }

        // Search for assignment that is related to specified year
        List<Assignment> assignmentsByYear = new ArrayList<>();
        for (Assignment assignment : assignmentList) {
            if (assignment.getAngkatan() == value) {
                assignmentsByYear.add(assignment);
            }
        }
        return assignmentsByYear;
    }
}
