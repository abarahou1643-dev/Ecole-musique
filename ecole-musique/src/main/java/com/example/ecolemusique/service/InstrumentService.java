package com.example.ecolemusique.service;

import com.example.ecolemusique.entity.Instrument;
import com.example.ecolemusique.repository.InstrumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class InstrumentService {

    @Autowired
    private InstrumentRepository instrumentRepository;

    public List<Instrument> getAllInstruments() {
        return instrumentRepository.findAll();
    }

    public Instrument getInstrumentByName(String nom) {
        return instrumentRepository.findById(nom).orElse(null);
    }

    public List<Instrument> getInstrumentsByFamille(String famille) {
        return instrumentRepository.findByFamille(famille);
    }

    public List<Instrument> getAvailableInstruments() {
        return instrumentRepository.findByDisponibilite(true);
    }

    public List<Instrument> getInstrumentsByFamilleAndDisponibilite(String famille, Boolean disponibilite) {
        return instrumentRepository.findByFamilleAndDisponibilite(famille, disponibilite);
    }

    public Map<String, Long> getInstrumentStatsByFamille() {
        List<Object[]> results = instrumentRepository.countInstrumentsByFamille();
        return results.stream()
                .collect(Collectors.toMap(
                        obj -> (String) obj[0],
                        obj -> (Long) obj[1]
                ));
    }

    public Instrument saveInstrument(Instrument instrument) {
        return instrumentRepository.save(instrument);
    }

    public void deleteInstrument(String nom) {
        instrumentRepository.deleteById(nom);
    }
}