package org.mave.containerization_demo.trip;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/trips")
@RequiredArgsConstructor
public class TripMemoryController {

    private final TripMemoryService service;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("trips", service.findAll());
        return "trips/list";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("trip", new TripMemory());
        return "trips/form";
    }

    @PostMapping
    public String create(@ModelAttribute TripMemory trip,
                         @RequestParam("imageFile") MultipartFile imageFile) throws IOException {
        service.save(trip, imageFile);
        return "redirect:/trips";
    }

    @GetMapping("/{id}")
    public String view(@PathVariable Long id, Model model) {
        model.addAttribute("trip", service.findById(id));
        return "trips/view";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("trip", service.findById(id));
        return "trips/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @ModelAttribute TripMemory trip,
                         @RequestParam("imageFile") MultipartFile imageFile) throws IOException {
        TripMemory existing = service.findById(id);
        trip.setId(id);
        if (imageFile.isEmpty()) {
            trip.setImagePath(existing.getImagePath());
        }
        service.save(trip, imageFile.isEmpty() ? null : imageFile);
        return "redirect:/trips/" + id;
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/trips";
    }
}
