package org.example.introspringmvc.Jpa.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.introspringmvc.Jpa.entities.GobeletEntity;
import org.example.introspringmvc.Jpa.models.GobeletForm;
import org.example.introspringmvc.Jpa.services.GobeletService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequiredArgsConstructor
public class GobeletController {

    private final GobeletService gobeletService;

    @GetMapping
    public String home(@RequestParam(required = false) Long typeId, Model model) {
        model.addAttribute("gobelets", gobeletService.getGobelets(typeId));
        model.addAttribute("types", gobeletService.getTypes());
        model.addAttribute("typeId", typeId);

        if (typeId != null){
            model.addAttribute("type", gobeletService.getType(typeId));
        }

        return "index";
    }

    @GetMapping("/gobelet/{gobeletId}")
    public String getGobelet(@PathVariable Long gobeletId, Model model){
        model.addAttribute("gobelet", gobeletService.getGobelet(gobeletId));
        return "gobelet";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("gobelet", new GobeletForm());
        model.addAttribute("types", gobeletService.getTypes());
        model.addAttribute("gobeletId", null);
        return "form";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute(name = "gobelet") GobeletForm gobelet,
                         BindingResult result,
                         Model model
    ) {
        if (result.hasErrors()){
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
        GobeletEntity gobelet = gobeletService.getGobelet(gobeletId);

        GobeletForm form = new GobeletForm();
        form.setNom(gobelet.getNom());
        form.setDescription(gobelet.getDescription());
        form.setImage(gobelet.getImage());
        form.setTypeGobelet(gobelet.getType().getTypeId());

        model.addAttribute("gobelet", form);
        model.addAttribute("types", gobeletService.getTypes());
        model.addAttribute("gobeletId", gobeletId);

        return "form";
    }

    @PostMapping("/gobelet/edit/{gobeletId}")
    public String update(@PathVariable Long gobeletId,
                         @Valid @ModelAttribute("gobelet") GobeletForm gobelet,
                         BindingResult result,
                         Model model){

        if (result.hasErrors()){
            model.addAttribute("types", gobeletService.getTypes());
            model.addAttribute("gobeletId", gobeletId);
            return "form";
        }

        gobeletService.updateGobelet(gobeletId, gobelet);
        return "redirect:/";
    }
}
