package com.example.ecolemusique.controller;

import com.example.ecolemusique.entity.Prof;
import com.example.ecolemusique.service.ProfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/profs")
public class ProfController {

    @Autowired
    private ProfService profService;

    @GetMapping
    public String listProfs(Model model,
                            @RequestParam(required = false) String specialite) {

        List<Prof> profs;

        if (specialite != null) {
            profs = profService.getProfsBySpecialite(specialite);
        } else {
            profs = profService.getAllProfs();
        }

        model.addAttribute("profs", profs);
        model.addAttribute("specialites", getSpecialites());
        return "profs/list";
    }

    @GetMapping("/{nom}")
    public String viewProf(@PathVariable String nom, Model model) {
        Prof prof = profService.getProfByName(nom);
        model.addAttribute("prof", prof);
        return "profs/view";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("prof", new Prof());
        model.addAttribute("specialites", getSpecialites());
        return "profs/add";
    }

    @PostMapping("/add")
    public String addProf(@ModelAttribute Prof prof) {
        profService.saveProf(prof);
        return "redirect:/profs";
    }

    private List<String> getSpecialites() {
        return List.of("Piano", "Guitare", "Violon", "Batterie", "Vents", "Chant",
                "Piano Jazz", "Guitare Basse", "Violoncelle");
    }
}