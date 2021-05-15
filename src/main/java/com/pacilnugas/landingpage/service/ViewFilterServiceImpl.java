package com.pacilnugas.landingpage.service;

import com.pacilnugas.landingpage.core.MajorFilter;
import com.pacilnugas.landingpage.core.ViewFilterManager;
import com.pacilnugas.landingpage.core.YearFilter;
import com.pacilnugas.landingpage.model.AssignmentFake;
import com.pacilnugas.landingpage.repository.AssignmentFakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewFilterServiceImpl implements ViewFilterService {
    private YearFilter yearFilter = new YearFilter();
    private MajorFilter majorFilter = new MajorFilter();

    @Autowired
    private AssignmentFakeRepository assignmentRepository;

    @Override
    public List<AssignmentFake> getListAssignment(int year, String major) {
        List<AssignmentFake> filteredAssignmentList = assignmentRepository.findAll();
        yearFilter.setValue(year);
        majorFilter.setValue(major);
        return ViewFilterManager.applyFilters(filteredAssignmentList, yearFilter, majorFilter);
    }
}
