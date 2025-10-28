package com.example.ecolemusique.service;

import com.example.ecolemusique.entity.Cours;
import com.example.ecolemusique.repository.CoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CoursService {

    @Autowired
    private CoursRepository coursRepository;

    public List<Cours> getAllCours() {
        return coursRepository.findAll();
    }

    public List<Cours> getCoursByNiveau(String niveau) {
        return coursRepository.findByNiveau(niveau);
    }

    public List<Cours> getCoursByJour(String jour) {
        return coursRepository.findByJour(jour);
    }

    public List<Cours> getCoursByNiveauAndJour(String niveau, String jour) {
        return coursRepository.findByNiveauAndJour(niveau, jour);
    }

    public Map<String, Long> getCoursStatsByJour() {
        List<Object[]> results = coursRepository.countCoursByJour();
        return results.stream()
                .collect(Collectors.toMap(
                        obj -> (String) obj[0],
                        obj -> (Long) obj[1]
                ));
    }

    public Map<String, Long> getCoursStatsByNiveau() {
        List<Object[]> results = coursRepository.countCoursByNiveau();
        return results.stream()
                .collect(Collectors.toMap(
                        obj -> (String) obj[0],
                        obj -> (Long) obj[1]
                ));
    }

    public Map<String, Long> getPopulariteInstruments() {
        List<Object[]> results = coursRepository.countCoursByInstrument();
        return results.stream()
                .collect(Collectors.toMap(
                        obj -> (String) obj[0],
                        obj -> (Long) obj[1]
                ));
    }

    public Cours saveCours(Cours cours) {
        return coursRepository.save(cours);
    }

    public void deleteCours(Cours cours) {
        coursRepository.delete(cours);
    }
}