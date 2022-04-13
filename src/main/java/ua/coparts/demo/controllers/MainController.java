package ua.coparts.demo.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.coparts.demo.models.User;

@Controller
public class MainController {

    @GetMapping("/")
    public String home(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("title", "Головна сторінка");
        model.addAttribute("user", user);
        return "home";
    }
}