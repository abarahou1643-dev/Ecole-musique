package com.example.ecolemusique.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "instruments")
public class Instrument {
    @Id
    private String nom;

    @Column(name = "famille", nullable = false)
    private String famille;

    @Column(name = "disponibilite")
    private Boolean disponibilite = true;

    @Column(name = "date_creation")
    private LocalDateTime dateCreation;

    @Column(name = "date_modification")
    private LocalDateTime dateModification;

    // Constructeurs
    public Instrument() {}

    public Instrument(String nom, String famille, Boolean disponibilite) {
        this.nom = nom;
        this.famille = famille;
        this.disponibilite = disponibilite;
    }

    // Getters et Setters
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getFamille() { return famille; }
    public void setFamille(String famille) { this.famille = famille; }

    public Boolean getDisponibilite() { return disponibilite; }
    public void setDisponibilite(Boolean disponibilite) { this.disponibilite = disponibilite; }

    public LocalDateTime getDateCreation() { return dateCreation; }
    public void setDateCreation(LocalDateTime dateCreation) { this.dateCreation = dateCreation; }

    public LocalDateTime getDateModification() { return dateModification; }
    public void setDateModification(LocalDateTime dateModification) { this.dateModification = dateModification; }

    @Override
    public String toString() {
        return "Instrument{nom='" + nom + "', famille='" + famille + "', disponibilite=" + disponibilite + "}";
    }
}