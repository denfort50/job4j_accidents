package ru.job4j.accident.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.service.AccidentService;

import java.util.List;

@Controller
@AllArgsConstructor
public class AccidentController {

    private final AccidentService accidentService;

    @GetMapping("/createAccident")
    public String viewCreateAccident(Model model) {
        List<AccidentType> types = accidentService.getAccidentTypes();
        model.addAttribute("types", types);
        return "createAccident";
    }

    @PostMapping("/saveAccident")
    public String create(@ModelAttribute Accident accident) {
        accidentService.create(accident);
        return "redirect:/index";
    }

    @GetMapping("/editAccident")
    public String edit(@RequestParam("id") int id, Model model) {
        model.addAttribute("accident", accidentService.findById(id).get());
        return "editAccident";
    }

    @PostMapping("/updateAccident")
    public String update(@ModelAttribute Accident accident) {
        accidentService.save(accident);
        return "redirect:/index";
    }
}
