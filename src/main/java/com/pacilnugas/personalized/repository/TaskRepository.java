package com.pacilnugas.personalized.repository;

import com.pacilnugas.personalized.core.Task;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class TaskRepository {
    private Map<String, Task> tasks = new HashMap<>();

    public Map<String, Task> getTasks() {
        return tasks;
    }

    public Task save(Task savedTask) {
        Task existingTask = tasks.get(savedTask.getTitle());
        if (existingTask == null) {
            tasks.put(savedTask.getTitle(), savedTask);
            return savedTask;
        } else {
            return null;
        }
    }
}
