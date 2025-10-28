package com.example.ecolemusique.controller;

import com.example.ecolemusique.entity.Cours;
import com.example.ecolemusique.entity.Instrument;
import com.example.ecolemusique.entity.Prof;
import com.example.ecolemusique.service.CoursService;
import com.example.ecolemusique.service.InstrumentService;
import com.example.ecolemusique.service.ProfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;
import java.math.BigDecimal;

@Controller
@RequestMapping("/cours")
public class CoursController {

    @Autowired
    private CoursService coursService;

    @Autowired
    private ProfService profService;

    @Autowired
    private InstrumentService instrumentService;

    @GetMapping
    public String listCours(Model model,
                            @RequestParam(required = false) String niveau,
                            @RequestParam(required = false) String jour) {

        List<Cours> cours;

        if (niveau != null && jour != null) {
            cours = coursService.getCoursByNiveauAndJour(niveau, jour);
        } else if (niveau != null) {
            cours = coursService.getCoursByNiveau(niveau);
        } else if (jour != null) {
            cours = coursService.getCoursByJour(jour);
        } else {
            cours = coursService.getAllCours();
        }

        model.addAttribute("cours", cours);
        model.addAttribute("niveaux", getNiveaux());
        model.addAttribute("jours", getJours());
        return "cours/list";
    }

    @GetMapping("/stats")
    public String showStats(Model model) {
        model.addAttribute("statsCoursParJour", coursService.getCoursStatsByJour());
        model.addAttribute("statsCoursParNiveau", coursService.getCoursStatsByNiveau());
        model.addAttribute("populariteInstruments", coursService.getPopulariteInstruments());
        return "cours/stats";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("cours", new Cours());
        model.addAttribute("profs", profService.getAllProfs());
        model.addAttribute("instruments", instrumentService.getAllInstruments());
        model.addAttribute("niveaux", getNiveaux());
        model.addAttribute("jours", getJours());
        model.addAttribute("heures", getHeures());
        return "cours/add";
    }

    @PostMapping("/add")
    public String addCours(@ModelAttribute Cours cours,
                           @RequestParam String professeurNom,
                           @RequestParam(required = false) String instrumentNom,
                           @RequestParam BigDecimal prix) {

        Prof professeur = profService.getProfByName(professeurNom);
        cours.setProfesseur(professeur);
        cours.setPrix(prix);

        if (instrumentNom != null && !instrumentNom.isEmpty()) {
            Instrument instrument = instrumentService.getInstrumentByName(instrumentNom);
            cours.setInstrument(instrument);
        }

        coursService.saveCours(cours);
        return "redirect:/cours";
    }

    private List<String> getNiveaux() {
        return List.of("Débutant", "Intermédiaire", "Avancé", "Enfant", "Tous niveaux");
    }

    private List<String> getJours() {
        return List.of("Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche");
    }

    private List<LocalTime> getHeures() {
        return List.of(
                LocalTime.of(9, 0), LocalTime.of(10, 0), LocalTime.of(11, 0),
                LocalTime.of(14, 0), LocalTime.of(15, 0), LocalTime.of(16, 0),
                LocalTime.of(17, 0), LocalTime.of(18, 0), LocalTime.of(19, 0),
                LocalTime.of(20, 0)
        );
    }
}