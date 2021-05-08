package com.pacilnugas.activities.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Entity
@Data
@Table(name = "matkul")
@NoArgsConstructor
public class Matkul {
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
    @Column(name = "angkatan")
    private int angkatan;

    @NotNull
    @Column(name = "deadline")
    private LocalDateTime deadline;

    //Nanti dihubungkan dengan user2 buatan Syabib
    //@NotNull
    //@Column(name = "pengajar")
    //private User pengajar;

    public Assignment(String title, int angkatan, LocalDateTime deadline) {
        this.title = title;
        this.angkatan = angkatan;
        this.deadline = deadline;
        //this.pengajar = pengajar;
    }
}
