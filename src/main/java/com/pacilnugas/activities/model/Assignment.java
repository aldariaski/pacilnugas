package com.pacilnugas.activities.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "assignment")
public class Assignment extends Activity {
    @ManyToOne
    @JoinColumn(name = "idAssignment")
    @JsonBackReference
    private Matkul matkulObject;

    @Column(name = "matkul")
    private String matkul;

    @Column(name = "deadline")
    private LocalDate deadline;

    @Column(name = "time")
    private LocalTime time;

    public String getDeadlineFormatted() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        String formattedString = getDeadline().format(formatter);
        return formattedString;
    }

    public String getMajor() {
        return getMatkulObject().getMajor();
    }

    public int getAngkatan() {
        return getMatkulObject().getAngkatan();
    }

    public List buatString() {
        List returnan = new ArrayList<>();
        returnan.add("TUGAS #" + getId_activity() + "\n\n");
        returnan.add("Nama tugas: " + getTitle());
        returnan.add("Nama matkul: " + getMatkul());
        returnan.add("Nama pengajar: " + getMaker_username());
        returnan.add("Deadline: " + getDeadlineFormatted() + ", " + getTime());
        returnan.add("Deskripsi: " + getDescription());
        returnan.add("Major:" + getMajor());
        returnan.add("Angkatan Tujuan:" + getAngkatan());
        return returnan;
    }

    public Assignment(String title, Matkul matkul, LocalDateTime deadline) {
        super();
        this.setTitle(title);
        this.matkulObject = matkul;
        this.deadline = deadline.toLocalDate();
        this.time = deadline.toLocalTime();
    }
}
