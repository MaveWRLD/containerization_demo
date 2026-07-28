package org.mave.containerization_demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LabController {

    @GetMapping("/ecs-change")
    public String labForm() {
        return "lab";
    }
}
