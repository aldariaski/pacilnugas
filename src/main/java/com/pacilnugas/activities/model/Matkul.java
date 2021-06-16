package com.pacilnugas.activities.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    @Column(name = "title", unique = true)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "tahun")
    private int tahun;

    @Column(name = "mulai")
    private LocalDate mulai;

    @Column(name = "semester")
    private String semester;

    @Column(name = "major", columnDefinition = "character varying(20) default 'Ilmu Komputer'")
    private String major;

    //Angkatan intended siapa yang seharusnya mendapatkan.
    //Bisa juga ada misal angkatan 2018 yang mengulang mengambil matkul u/ 2019
    @Column(name = "angkatan", columnDefinition = "integer default 2019")
    private int angkatan;

    @Column(name = "pengajar")
    private String pengajar; //username pengajar itu

    @OneToMany(mappedBy = "matkulObject")
    @JsonManagedReference
    private List<Assignment> listAssignment;

    public String getDateFormatted() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        String formattedString = getMulai().format(formatter);
        return formattedString;
    }

    public List buatString() {
        List returnan = new ArrayList<>();
        returnan.add("MATKUL\n\n");
        returnan.add("Nama matkul: " + getTitle());
        returnan.add("Nama pengajar: " + getPengajar());
        returnan.add("Jurusan: " + getMajor());
        returnan.add("Angkatan Tujuan: " + getAngkatan());
        returnan.add("Tanggal Mulai: " + getDateFormatted());
        returnan.add("Tahun ajaran: " + getTahun() + " - " + getSemester());
        returnan.add("Deskripsi: " + getDescription());
        return returnan;
    }

    public Matkul(String title) {
        this.setTitle(title);
    }
}