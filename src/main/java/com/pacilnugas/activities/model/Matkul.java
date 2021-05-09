package com.pacilnugas.activities.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@Table(name = "matkul")
@NoArgsConstructor
public class Matkul {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_assignment", updatable = false)
    private int idAssignment;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "tahun")
    private int tahun;

    @Column(name = "semester")
    private String semester;

    @Column(name = "pengajar")
    private String pengajar; //username pengajar itu
    // private List<String> list_pengajar; //pakai ini jika sudah bisa menghandle banyak pengajar di satu matkul

    public List buatString() {
        List returnan = new ArrayList<>();
        returnan.add("MATKUL\n\n");
        returnan.add("Nama matkul: " + getTitle());
        returnan.add("Nama pengajar: " + getPengajar());
        returnan.add("Tahun ajaran: " + getTahun() + " - " + getSemester());
        returnan.add("Deskripsi: " + getDescription());
        return returnan;
    }
}
