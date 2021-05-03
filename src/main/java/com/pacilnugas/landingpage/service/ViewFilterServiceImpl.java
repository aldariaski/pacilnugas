package com.pacilnugas.landingpage.service;

import com.pacilnugas.landingpage.core.*;
import com.pacilnugas.landingpage.model.AssignmentFake;
import com.pacilnugas.landingpage.repository.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewFilterServiceImpl implements ViewFilterService{
    private YearFilter yearFilter = new YearFilter();
    private MajorFilter majorFilter = new MajorFilter();

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Override
    public List<AssignmentFake> getListAssignment(int year, String major) {
        List<AssignmentFake> filteredAssignmentList = assignmentRepository.findAll();
        yearFilter.setValue(year);
        majorFilter.setValue(major);
        return ViewFilterManager.applyFilters(filteredAssignmentList, yearFilter, majorFilter);
    }
}
