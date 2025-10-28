package com.example.ecolemusique.repository;

import com.example.ecolemusique.entity.Cours;
import com.example.ecolemusique.entity.Prof;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;

@Repository
public interface CoursRepository extends JpaRepository<Cours, String> {

    List<Cours> findByNiveau(String niveau);

    List<Cours> findByJour(String jour);

    List<Cours> findByNiveauAndJour(String niveau, String jour);

    List<Cours> findByProfesseur(Prof professeur);

    @Query("SELECT c.jour, COUNT(c) FROM Cours c GROUP BY c.jour ORDER BY COUNT(c) DESC")
    List<Object[]> countCoursByJour();

    @Query("SELECT c.niveau, COUNT(c) FROM Cours c GROUP BY c.niveau")
    List<Object[]> countCoursByNiveau();

    @Query("SELECT c.instrument.nom, COUNT(c) FROM Cours c WHERE c.instrument IS NOT NULL GROUP BY c.instrument.nom ORDER BY COUNT(c) DESC")
    List<Object[]> countCoursByInstrument();

    @Query("SELECT c FROM Cours c WHERE c.jour = :jour AND c.heureDebut = :heureDebut AND c.professeur.nom = :professeurNom")
    Cours findByCompositeKey(@Param("jour") String jour,
                             @Param("heureDebut") LocalTime heureDebut,
                             @Param("professeurNom") String professeurNom);
}