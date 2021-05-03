package com.pacilnugas.landingpage.core;

import com.pacilnugas.landingpage.model.AssignmentFake;

import java.util.List;

public class ViewFilterManager {
    public static List<AssignmentFake> applyFilters(List<AssignmentFake> assignmentList, FilterStrategy... filterStrategies) {
        List<AssignmentFake> filteredAssignmentList = assignmentList;
        // Apply all filter
        for (FilterStrategy filterStrategy : filterStrategies) {
            filteredAssignmentList = filterStrategy.applyFilter(filteredAssignmentList);
        }
        return filteredAssignmentList;
    }
}
