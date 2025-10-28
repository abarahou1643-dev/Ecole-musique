package com.example.ecolemusique.service;

import com.example.ecolemusique.entity.Prof;
import com.example.ecolemusique.repository.ProfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProfService {

    @Autowired
    private ProfRepository profRepository;

    public List<Prof> getAllProfs() {
        return profRepository.findAll();
    }

    public Prof getProfByName(String nom) {
        return profRepository.findById(nom).orElse(null);
    }

    public List<Prof> getProfsBySpecialite(String specialite) {
        return profRepository.findBySpecialite(specialite);
    }

    public List<Prof> getActiveProfs() {
        return profRepository.findByActif(true);
    }

    public Map<String, Long> getProfStatsBySpecialite() {
        List<Object[]> results = profRepository.countProfsBySpecialite();
        return results.stream()
                .collect(Collectors.toMap(
                        obj -> (String) obj[0],
                        obj -> (Long) obj[1]
                ));
    }

    public Prof saveProf(Prof prof) {
        return profRepository.save(prof);
    }

    public void deleteProf(String nom) {
        profRepository.deleteById(nom);
    }
}