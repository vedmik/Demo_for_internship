package ua.coparts.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ua.coparts.demo.models.User;
import ua.coparts.demo.models.UserRole;
import ua.coparts.demo.repo.UserRepository;

import java.util.Collections;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/registration")
    public String registration(Model model){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model){
        User userFromDB = userRepository.findByUsername(user.getUsername());
        if(userFromDB != null ){
            model.addAttribute("message", "Такии користувач вже існує");
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(UserRole.USER));
        userRepository.save(user);

        return "redirect:/login";
    }
}
