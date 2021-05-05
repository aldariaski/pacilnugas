package com.pacilnugas.template.core;

import javax.persistence.Entity;

@Entity
public class PesanTugas extends Pesan {
    @Override
    String pembukaan() {
        return "coba pembukaan tugas";
    }

    @Override
    String harapan() {
        return "As long as you keep going, you'll be fine. We're here "
                + "to support you if you need anything!";
    }

    @Override
    String quotes() {
        return "“You don't get rich off your day job, you get "
                + "rich off your homework.” - Daymond John";
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
