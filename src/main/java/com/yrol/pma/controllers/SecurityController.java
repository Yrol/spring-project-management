package com.yrol.pma.controllers;

import com.yrol.pma.entities.UserAccount;
import com.yrol.pma.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class SecurityController {

    @Autowired
    BCryptPasswordEncoder bCryptEncoder;

    @Autowired
    SecurityService securityService;

    @GetMapping("/register")
    public String displayRegister(Model model) {
        UserAccount userAccount = new UserAccount();
        model.addAttribute("userAccount", userAccount);
        return "security/register";
    }

    @PostMapping("/register/save")
    public String register(Model model, UserAccount user, RedirectAttributes redirectAttributes) {

        //Temporary fix for prevent saving duplicate accounts (should to introduce proper validation).
        if(securityService.getUsersByEmailOrUsername(user).size() > 0) {
            return "redirect:/register";
        }

        //encrypting the password
        user.setPassword(bCryptEncoder.encode(user.getPassword()));

        securityService.save(user);

        return "redirect:/register";
    }
}
