package com.pacilnugas.landingpage.service;

import com.pacilnugas.activities.model.Assignment;
import com.pacilnugas.activities.repository.AssignmentRepository;
import com.pacilnugas.landingpage.core.MajorFilter;
import com.pacilnugas.landingpage.core.ViewFilterManager;
import com.pacilnugas.landingpage.core.YearFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewFilterServiceImpl implements ViewFilterService {
    private YearFilter yearFilter = new YearFilter();
    private MajorFilter majorFilter = new MajorFilter();

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Override
    public List<Assignment> getListAssignment(int year, String major) {
        List<Assignment> filteredAssignmentList = assignmentRepository.findAll();
        yearFilter.setValue(year);
        majorFilter.setValue(major);
        return ViewFilterManager.applyFilters(filteredAssignmentList, yearFilter, majorFilter);
    }
}
