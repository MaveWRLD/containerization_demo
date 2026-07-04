package org.mave.containerization_demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class LabController {

    @GetMapping("/lab")
    public String labForm(Model model) {
        model.addAttribute("fullName", "");
        model.addAttribute("labName", "");
        model.addAttribute("submitted", false);
        return "lab";
    }

    @PostMapping("/lab")
    public String labSubmit(
            @RequestParam String fullName,
            @RequestParam String labName,
            Model model) {
        model.addAttribute("fullName", fullName);
        model.addAttribute("labName", labName);
        model.addAttribute("submitted", true);
        return "lab";
    }
}
