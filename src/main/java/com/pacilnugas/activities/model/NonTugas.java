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
@Table(name = "nontugas")
public class NonTugas extends Activity {
    @NotNull
    @Column(name = "waktu")
    private LocalDateTime waktu;

    //Nanti dihubungkan dengan user2 buatan Syabib
    //@NotNull
    //@Column(name = "pengajar")
    //private User pengajar;

    public NonTugas(String title, String matkul, int angkatan, LocalDateTime waktu) {
        this.waktu = waktu;
        //this.pengajar = pengajar;
    }
}
