package com.example.ecolemusique.controller;

import com.example.ecolemusique.service.CoursService;
import com.example.ecolemusique.service.InstrumentService;
import com.example.ecolemusique.service.ProfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private CoursService coursService;

    @Autowired
    private InstrumentService instrumentService;

    @Autowired
    private ProfService profService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "École de Musique - Accueil");
        model.addAttribute("statsCours", coursService.getCoursStatsByJour());
        model.addAttribute("statsInstruments", instrumentService.getInstrumentStatsByFamille());
        model.addAttribute("populariteInstruments", coursService.getPopulariteInstruments());
        return "index";
    }
}