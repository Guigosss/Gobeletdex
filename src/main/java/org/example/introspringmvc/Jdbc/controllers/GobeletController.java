package org.example.introspringmvc.Jdbc.controllers;

import jakarta.validation.Valid;
import org.example.introspringmvc.Jdbc.enums.TypeGobelet;
import org.example.introspringmvc.Jdbc.models.Gobelet;
import org.example.introspringmvc.Jdbc.models.GobeletForm;
import org.example.introspringmvc.Jdbc.services.IGobeletService;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

// @Controller
public class GobeletController {

    private final IGobeletService gobeletService;

    public GobeletController(IGobeletService gobeletService) {
        this.gobeletService = gobeletService;
    }

    @GetMapping
    public String home(@RequestParam(required = false) TypeGobelet type,
                       Model model) {
        model.addAttribute("gobelets", gobeletService.getGobelets(type));
        model.addAttribute("type", type);
        model.addAttribute("types", TypeGobelet.values());
        return "index";
    }

    @GetMapping("/gobelet/{gobeletId}")
    public String getGobelet(@PathVariable Long gobeletId, Model model){
        Gobelet gobelet = gobeletService.getGobelet(gobeletId);
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
            model.addAttribute("gobeletId", null);
            return "form";
        }

        gobeletService.addGobelet(gobelet);
        return "redirect:/";
    }

    @PostMapping("/gobelet/delete/{gobeletId}")
    public String delete(@PathVariable Long gobeletId){
        gobeletService.deleteGobelet(gobeletId);
        return "redirect:/";
    }

    @GetMapping("/gobelet/edit/{gobeletId}")
    public String formUpdate(@PathVariable Long gobeletId, Model model){
        Gobelet gobelet = gobeletService.getGobelet(gobeletId);

        GobeletForm form = new GobeletForm();
        form.setNom(gobelet.getNom());
        form.setDescription(gobelet.getDescription());
        form.setImage(gobelet.getImage());
        form.setTypeGobelet(gobelet.getTypeGobelet());

        model.addAttribute("gobelet", form);
        model.addAttribute("types", TypeGobelet.values());
        model.addAttribute("gobeletId", gobeletId);

        return "form";
    }

    @PostMapping("/gobelet/edit/{gobeletId}")
    public String update(@PathVariable Long gobeletId,
                         @Valid @ModelAttribute("gobelet") GobeletForm gobelet,
                         BindingResult result,
                         Model model){

        if (result.hasErrors()){
            model.addAttribute("types", TypeGobelet.values());
            model.addAttribute("gobeletId", gobeletId);
            return "form";
        }

        gobeletService.updateGobelet(gobeletId, gobelet);
        return "redirect:/";
    }
}