package com.pacilnugas.activities.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_activity", updatable = false)
    private int id_activity;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "maker_username")
    private String maker_username;

    public String getTitle() {
        return title;
    }
}
