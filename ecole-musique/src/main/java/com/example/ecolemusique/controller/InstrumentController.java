package com.example.ecolemusique.controller;

import com.example.ecolemusique.entity.Instrument;
import com.example.ecolemusique.service.InstrumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/instruments")
public class InstrumentController {

    @Autowired
    private InstrumentService instrumentService;

    @GetMapping
    public String listInstruments(Model model,
                                  @RequestParam(required = false) String famille,
                                  @RequestParam(required = false) Boolean disponibilite) {

        List<Instrument> instruments;

        if (famille != null && disponibilite != null) {
            instruments = instrumentService.getInstrumentsByFamilleAndDisponibilite(famille, disponibilite);
        } else if (famille != null) {
            instruments = instrumentService.getInstrumentsByFamille(famille);
        } else if (disponibilite != null) {
            instruments = disponibilite ?
                    instrumentService.getAvailableInstruments() :
                    instrumentService.getAllInstruments().stream()
                            .filter(i -> !i.getDisponibilite())
                            .toList();
        } else {
            instruments = instrumentService.getAllInstruments();
        }

        model.addAttribute("instruments", instruments);
        model.addAttribute("familles", getFamilles());
        return "instruments/list";
    }

    @GetMapping("/{nom}")
    public String viewInstrument(@PathVariable String nom, Model model) {
        Instrument instrument = instrumentService.getInstrumentByName(nom);
        model.addAttribute("instrument", instrument);
        return "instruments/view";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("instrument", new Instrument());
        model.addAttribute("familles", getFamilles());
        return "instruments/add";
    }

    @PostMapping("/add")
    public String addInstrument(@ModelAttribute Instrument instrument) {
        instrumentService.saveInstrument(instrument);
        return "redirect:/instruments";
    }

    private List<String> getFamilles() {
        return List.of("Cordes", "Vents", "Claviers", "Percussions");
    }
}