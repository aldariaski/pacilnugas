package com.pacilnugas.activities.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Table(name = "nontugas")
public class NonTugas extends Activity {
    @Column(name = "date")
    private LocalDate date;

    @Column(name = "time")
    private LocalTime time;

    public NonTugas (String title, LocalDateTime fulldate) {
        super();
        this.setTitle(title);
        this.date = fulldate.toLocalDate();
        this.time = fulldate.toLocalTime();
    }
}
