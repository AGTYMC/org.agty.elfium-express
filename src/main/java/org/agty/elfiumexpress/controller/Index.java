package org.agty.elfiumexpress.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class Index {
    @GetMapping
    public String express(Model model) {
        return "redirect:/express";
    }
}
