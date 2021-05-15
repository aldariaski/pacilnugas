package com.pacilnugas.activities.model;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
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
    //@OneToOne(fetch = FetchType.LAZY, mappedBy = "mahasiswa") //(attribute nanti matkul, jangan string
    @Column(name = "matkul")
    private String matkul;

    //@DateTimeFormat(pattern = "yyyy-mm-dd")
    @Column(name = "deadline")
    private String deadline;
    //private LocalDate deadline;

    public List buatString() {
        List returnan = new ArrayList<>();
        returnan.add("TUGAS\n\n");
        returnan.add("Nama tugas: " + getTitle());
        returnan.add("Nama matkul: " + getMatkul());
        returnan.add("Nama pengajar: " + getMaker_username());
        returnan.add("Deadline: " + getDeadline());
        returnan.add("Deskripsi: " + getDescription());
        return returnan;
    }
}
