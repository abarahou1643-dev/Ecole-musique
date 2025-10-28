package com.example.ecolemusique.repository;

import com.example.ecolemusique.entity.Prof;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfRepository extends JpaRepository<Prof, String> {

    List<Prof> findBySpecialite(String specialite);

    List<Prof> findByActif(Boolean actif);

    @Query("SELECT p.specialite, COUNT(p) FROM Prof p GROUP BY p.specialite")
    List<Object[]> countProfsBySpecialite();
}