package org.example.introspringmvc.controllers;

import org.example.introspringmvc.enums.TypeAnimal;
import org.example.introspringmvc.models.GobeletEntity;
import org.example.introspringmvc.services.ListeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ListController {

    private final ListeService listeService;

    public ListController(ListeService listeService) {
        this.listeService = listeService;
    }

    @GetMapping
    public String home(@RequestParam(required = false) TypeAnimal type,
                       Model model) {
        model.addAttribute("gobelets", listeService.getGobelets(type));
        model.addAttribute("type", type);
        return "index";
    }

    @GetMapping("/gobelet/{name}")
    public String getGobelet(@PathVariable String name, Model model){
        GobeletEntity gobelet = listeService.getGobeletByName(name);

        if (gobelet == null) {
            return "redirect:/";
        }

        model.addAttribute("gobelet", gobelet);
        return "gobelet";
    }
}
