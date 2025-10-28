package com.example.ecolemusique.repository;

import com.example.ecolemusique.entity.Instrument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstrumentRepository extends JpaRepository<Instrument, String> {

    List<Instrument> findByFamille(String famille);

    List<Instrument> findByDisponibilite(Boolean disponibilite);

    List<Instrument> findByFamilleAndDisponibilite(String famille, Boolean disponibilite);

    @Query("SELECT i.famille, COUNT(i) FROM Instrument i GROUP BY i.famille")
    List<Object[]> countInstrumentsByFamille();
}