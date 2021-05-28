package com.pacilnugas.activities.model;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "assignment")
public class Assignment extends Activity {
    //Jika sudah bisa nanti memakai
    //@JsonManagedReference
    //@ManyToOne(fetch = FetchType.LAZY, mappedBy = "mahasiswa") //(attribute nanti matkul, jangan string
    @Column(name = "matkul")
    private String matkul;

    @Column(name = "deadline")
    private LocalDate deadline;

    @Column(name = "time")
    private LocalTime time;

    @Column(name = "major", columnDefinition = "character varying(20) default 'Ilmu Komputer'")
    private String major;

    @Column(name = "angkatan", columnDefinition = "integer default 2019")
    private int angkatan;

    public String getDeadlineFormatted() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        String formattedString = getDeadline().format(formatter);
        return formattedString;
    }

    public List buatString() {
        List returnan = new ArrayList<>();
        returnan.add("TUGAS #"+ getId_activity() + "\n\n");
        returnan.add("Nama tugas: " + getTitle());
        returnan.add("Nama matkul: " + getMatkul());
        returnan.add("Nama pengajar: " + getMaker_username());
        returnan.add("Deadline: " + getDeadlineFormatted() + ", " + getTime());
        returnan.add("Deskripsi: " + getDescription());
        return returnan;
    }

    public Assignment (String title, String major, int angkatan, LocalDate deadline) {
        super();
        this.setTitle(title);
        this.major = major;
        this.angkatan = angkatan;
        this.deadline = deadline;
    }
}
