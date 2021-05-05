package com.pacilnugas.template.core;

import javax.persistence.Entity;

@Entity
public class PesanBebas extends Pesan {
    @Override
    String pembukaan() {
        return "coba pembukaan bebas";
    }

    @Override
    String harapan() {
        return "Don't be afraid to invest your time. Take full advantage of "
                + "this moment to mold and shape the “you” of the future. Good luck!";
    }

    @Override
    String quotes() {
        return "“It is well to remind ourselves that anxiety signifies a "
                + "conflict, and so long as a conflict is going on, a "
                + "constructive solution is possible.” - Rollo May";
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
