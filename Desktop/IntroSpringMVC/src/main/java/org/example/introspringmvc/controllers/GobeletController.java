package org.example.introspringmvc.controllers;

import jakarta.validation.Valid;
import org.example.introspringmvc.enums.TypeGobelet;
import org.example.introspringmvc.models.GobeletEntity;
import org.example.introspringmvc.models.GobeletForm;
import org.example.introspringmvc.services.GobeletService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class GobeletController {

    private final GobeletService gobeletService;

    public GobeletController(GobeletService gobeletService) {
        this.gobeletService = gobeletService;
    }

    @GetMapping
    public String home(@RequestParam(required = false) TypeGobelet type,
                       Model model) {
        model.addAttribute("gobelets", gobeletService.getGobelets(type));
        model.addAttribute("type", type);
        return "index";
    }

    @GetMapping("/gobelet/{id}")
    public String getGobelet(@PathVariable Long id, Model model){
        GobeletEntity gobelet = gobeletService.getGobelet(id);
        model.addAttribute("gobelet", gobelet);
        return "gobelet";
    }

    @GetMapping("/create")
    public String formCreate(Model model){
        model.addAttribute("gobelet", new GobeletForm());
        model.addAttribute("types", TypeGobelet.values());
        model.addAttribute("id", null);
        return "form";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute(name = "gobelet") GobeletForm gobelet,
                         BindingResult result,
                         Model model
    ){
        if(result.hasErrors()){
            model.addAttribute("gobelet", gobelet);
            model.addAttribute("id", null);
            return "form";
        }

        gobeletService.addGobelet(gobelet);
        return "redirect:/";
    }

    @PostMapping("/gobelet/delete/{id}")
    public String delete(@PathVariable Long id){
        gobeletService.deleteGobelet(id);
        return "redirect:/";
    }

    @GetMapping("/gobelet/edit/{id}")
    public String formUpdate(@PathVariable Long id, Model model){
        GobeletEntity gobelet = gobeletService.getGobelet(id);

        GobeletForm form = new GobeletForm();
        form.setNom(gobelet.getNom());
        form.setDescription(gobelet.getDescription());
        form.setImage(gobelet.getImage());
        form.setTypeGobelet(gobelet.getTypeGobelet());

        model.addAttribute("gobelet", form);
        model.addAttribute("types", TypeGobelet.values());
        model.addAttribute("id", id);

        return "form";
    }

    @PostMapping("/gobelet/edit/{id}")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("gobelet") GobeletForm gobelet,
                         BindingResult result,
                         Model model){

        if (result.hasErrors()){
            model.addAttribute("types", TypeGobelet.values());
            model.addAttribute("id", id);
            return "form";
        }

        gobeletService.updateGobelet(id, gobelet);
        return "redirect:/";
    }
}