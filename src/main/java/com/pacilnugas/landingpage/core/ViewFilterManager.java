package com.pacilnugas.landingpage.core;

import com.pacilnugas.landingpage.model.AssignmentFake;

import java.util.List;

public class ViewFilterManager {
    /**
     * This method is used to apply all given filters to a list of assignments.
     *
     * @param assignmentList contains list of all assignments
     * @param filterStrategies contains all view filter that will be applied
     * @return a list containing filtered assignments
     */
    public static List<AssignmentFake> applyFilters(
            List<AssignmentFake> assignmentList,
            FilterStrategy... filterStrategies) {
        List<AssignmentFake> filteredAssignmentList = assignmentList;

        // Apply all filter
        for (FilterStrategy filterStrategy : filterStrategies) {
            filteredAssignmentList = filterStrategy.applyFilter(filteredAssignmentList);
        }
        return filteredAssignmentList;
    }
}
