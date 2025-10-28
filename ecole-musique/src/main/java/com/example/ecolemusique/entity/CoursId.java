package com.example.ecolemusique.entity;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Objects;

public class CoursId implements Serializable {
    private String jour;
    private LocalTime heureDebut;
    private String professeur;

    public CoursId() {}

    public CoursId(String jour, LocalTime heureDebut, String professeur) {
        this.jour = jour;
        this.heureDebut = heureDebut;
        this.professeur = professeur;
    }

    // Getters et Setters
    public String getJour() { return jour; }
    public void setJour(String jour) { this.jour = jour; }

    public LocalTime getHeureDebut() { return heureDebut; }
    public void setHeureDebut(LocalTime heureDebut) { this.heureDebut = heureDebut; }

    public String getProfesseur() { return professeur; }
    public void setProfesseur(String professeur) { this.professeur = professeur; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoursId coursId = (CoursId) o;
        return Objects.equals(jour, coursId.jour) &&
                Objects.equals(heureDebut, coursId.heureDebut) &&
                Objects.equals(professeur, coursId.professeur);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jour, heureDebut, professeur);
    }
}