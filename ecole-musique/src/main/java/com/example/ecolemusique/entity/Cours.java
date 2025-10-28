package com.example.ecolemusique.entity;

import jakarta.persistence.*;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Entity
@Table(name = "cours")
@IdClass(CoursId.class)
public class Cours {
    @Id
    @Column(name = "jour", nullable = false)
    private String jour;

    @Id
    @Column(name = "heure_debut", nullable = false)
    private LocalTime heureDebut;

    @Id
    @ManyToOne
    @JoinColumn(name = "professeur_nom", nullable = false)
    private Prof professeur;

    @Column(name = "duree_min", nullable = false)
    private Integer dureeMin;

    @Column(name = "niveau", nullable = false)
    private String niveau;

    @ManyToOne
    @JoinColumn(name = "instrument_nom")
    private Instrument instrument;

    @Column(name = "salle")
    private String salle;

    @Column(name = "prix", precision = 10, scale = 2)
    private BigDecimal prix = BigDecimal.ZERO;

    @Column(name = "places_max")
    private Integer placesMax = 10;

    @Column(name = "date_creation")
    private LocalDateTime dateCreation;

    @Column(name = "date_modification")
    private LocalDateTime dateModification;

    // Constructeurs
    public Cours() {}

    public Cours(String jour, LocalTime heureDebut, Prof professeur, Integer dureeMin, String niveau) {
        this.jour = jour;
        this.heureDebut = heureDebut;
        this.professeur = professeur;
        this.dureeMin = dureeMin;
        this.niveau = niveau;
    }

    // Getters et Setters
    public String getJour() { return jour; }
    public void setJour(String jour) { this.jour = jour; }

    public LocalTime getHeureDebut() { return heureDebut; }
    public void setHeureDebut(LocalTime heureDebut) { this.heureDebut = heureDebut; }

    public Prof getProfesseur() { return professeur; }
    public void setProfesseur(Prof professeur) { this.professeur = professeur; }

    public Integer getDureeMin() { return dureeMin; }
    public void setDureeMin(Integer dureeMin) { this.dureeMin = dureeMin; }

    public String getNiveau() { return niveau; }
    public void setNiveau(String niveau) { this.niveau = niveau; }

    public Instrument getInstrument() { return instrument; }
    public void setInstrument(Instrument instrument) { this.instrument = instrument; }

    public String getSalle() { return salle; }
    public void setSalle(String salle) { this.salle = salle; }

    public BigDecimal getPrix() { return prix; }
    public void setPrix(BigDecimal prix) { this.prix = prix; }

    public Integer getPlacesMax() { return placesMax; }
    public void setPlacesMax(Integer placesMax) { this.placesMax = placesMax; }

    public LocalDateTime getDateCreation() { return dateCreation; }
    public void setDateCreation(LocalDateTime dateCreation) { this.dateCreation = dateCreation; }

    public LocalDateTime getDateModification() { return dateModification; }
    public void setDateModification(LocalDateTime dateModification) { this.dateModification = dateModification; }

    @Override
    public String toString() {
        return "Cours{jour='" + jour + "', heureDebut=" + heureDebut + ", professeur=" + professeur.getNom() +
                ", niveau='" + niveau + "', instrument=" + (instrument != null ? instrument.getNom() : "null") + "}";
    }
}