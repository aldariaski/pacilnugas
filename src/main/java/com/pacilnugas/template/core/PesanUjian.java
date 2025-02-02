package com.pacilnugas.template.core;

import javax.persistence.Entity;

@Entity
public class PesanUjian extends  Pesan {
    @Override
    String pembukaan() {
        return "Perasaan baru saja kemarin selesai ujian...";
    }

    @Override
    String harapan() {
        return "Wishing you all the good luck in the world for "
                + "your exam. Ace it and crush the scoreboard!";
    }

    @Override
    String quotes() {
        return "“In order to succeed, your desire for success should be greater than your fear "
                + "of failure.”- Bill Cosby";
    }

    @Override
    public boolean inginMenambahQuotes() {
        if (getMenambahQuotes().equalsIgnoreCase("yes")) {
            return true;
        } else {
            return false;
        }
    }

}
