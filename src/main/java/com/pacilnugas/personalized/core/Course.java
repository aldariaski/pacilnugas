package com.pacilnugas.personalized.core;

import java.util.ArrayList;
import java.util.List;

public class Course {
    protected Observer observer;
    protected String name;
    private List<Task> tasks = new ArrayList<>();

    public Course(String name, Observer observer) {
        this.name = name;
        this.observer = observer;
    }

    public String getName() {
        return name;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void update() {
        getTasks().add(observer.getTask());
    }
}
