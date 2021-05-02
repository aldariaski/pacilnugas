package com.pacilnugas.assignments.model;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "assignment")
@NoArgsConstructor
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_assignment", updatable = false)
    private int idAssignment;

    @NotNull
    @Column(name = "title")
    private String title;

    @NotNull
    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "major")
    private String major;

    @NotNull
    @Column(name = "tahunajaran")
    private int tahunajaran;

    @NotNull
    @Column(name = "deadline")
    private LocalDateTime deadline;

    //Nanti dihubungkan dengan user2 buatan Syabib
    @NotNull
    @Column(name = "pengajar")
    private User pengajar;

    public Assignment(String title, String major, int angkatan, LocalDateTime deadline) {
        this.title = title;
        this.major = major;
        this.tahunajaran = tahunajaran;
        this.deadline = deadline;
        this.pengajar = pengajar;
    }

    public int getAngkatan() {
        return angkatan;
    }

    public String getMajor() {
        return major;
    }
}
