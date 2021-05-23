package com.pacilnugas.personalized.core;

import java.util.ArrayList;
import java.util.List;

public class Observer {
    private List<Course> courses = new ArrayList<>();
    private Task task;

    public void add(Course course) {
        courses.add(course);
    }

    public void addTask(Task task) {
        this.task = task;
        broadcast();
    }

    public String getTaskType() {
        return task.getType();
    }

    public List<Course> getCourses() {
        return courses;
    }

    public Task getTask() {
        return task;
    }

    private void broadcast() {
        for (Course course: courses) {
            if (this.task.getType().equalsIgnoreCase(course.getName())) {
                course.update();
                break;
            }
        }
    }
}
