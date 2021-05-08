package com.pacilnugas.activities.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "assignment")
public class Assignment extends Activity {
    @NotNull
    @Column(name = "matkul")
    private String matkul;

    @NotNull
    @Column(name = "angkatan")
    private int angkatan;

    @NotNull
    @Column(name = "deadline")
    private LocalDateTime deadline;

    public Assignment(String title, String matkul, int angkatan, LocalDateTime deadline) {
        this.matkul = matkul;
        this.angkatan = angkatan;
        this.deadline = deadline;
        //this.pengajar = pengajar;
    }
}
