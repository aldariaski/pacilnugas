package com.pacilnugas.activities.model;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


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

    @Column(name = "deadline")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate deadline;
}
