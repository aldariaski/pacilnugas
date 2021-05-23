package com.pacilnugas.personalized.service;

import com.pacilnugas.personalized.core.Course;
import com.pacilnugas.personalized.core.Observer;
import com.pacilnugas.personalized.core.Task;
import com.pacilnugas.personalized.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObserverServiceImpl {
    private final TaskRepository taskRepository;
    private final Observer observer;

    public ObserverServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
        this.observer = new Observer();
        observer.add(new Course("Basis Data", this.observer));
        observer.add(new Course("Pemrograman Lanjut", this.observer));
        observer.add(new Course("Sistem Operasi", this.observer));
        observer.add(new Course("Statistika dan Probabilitas", this.observer));
        observer.add(new Course("Teori Bahasa dan Automata", this.observer));
    }

    public void addTask(Task task) {
        this.observer.addTask(task);
        this.taskRepository.save(task);
    }

    public List<Course> getCourses() {
        return observer.getCourses();
    }
}
