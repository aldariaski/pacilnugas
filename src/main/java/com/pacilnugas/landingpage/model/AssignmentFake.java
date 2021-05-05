package com.pacilnugas.landingpage.model;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "assignment")
@NoArgsConstructor
public class AssignmentFake {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_assignment", updatable = false)
    private int idAssignment;

    @NotNull
    @Column(name = "title")
    private String title;

    @NotNull
    @Column(name = "major")
    private String major;

    @NotNull
    @Column(name = "angkatan")
    private int angkatan;

    @NotNull
    @Column(name = "deadline")
    private LocalDateTime deadline;

    public AssignmentFake(String title, String major, int angkatan, LocalDateTime deadline) {
        this.title = title;
        this.major = major;
        this.angkatan = angkatan;
        this.deadline = deadline;
    }

    public int getAngkatan() {
        return angkatan;
    }

    public String getMajor() {
        return major;
    }
}
