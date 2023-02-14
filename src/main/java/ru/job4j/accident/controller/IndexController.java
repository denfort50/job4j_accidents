package ru.job4j.accident.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentService;

import java.util.List;

@Controller
@AllArgsConstructor
public class IndexController {

    private final AccidentService accidentService;

    @GetMapping("/index")
    public String index(Model model) {
        List<Accident> accidents = accidentService.findAll();
        model.addAttribute("allAccidents", accidents);
        model.addAttribute("user", "Denis Kalchenko");
        return "index";
    }
}
