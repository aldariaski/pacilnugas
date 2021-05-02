package com.pacilnugas.personalized.service;

import com.pacilnugas.personalized.core.Course;
import com.pacilnugas.personalized.core.Task;

import java.util.List;

public interface ObserverService {
    void addTask(Task task);
    List<Course> getCourses();
}
