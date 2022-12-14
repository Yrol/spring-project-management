package com.yrol.pma.controllers;

import com.yrol.pma.entities.UserAccount;
import com.yrol.pma.enums.useraccount.Roles;
import com.yrol.pma.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


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

    @PostMapping("/register")
    public String register(Model model, @Valid UserAccount user, BindingResult result) {

        if(result.hasErrors()) {
            model.addAttribute("userAccount", user);
            return "security/register";
        }

        //encrypting the password
        user.setPassword(bCryptEncoder.encode(user.getPassword()));

        //Making the very first user as admin
        if(securityService.userCount() == 0) {
            user.setRole(Roles.ADMIN);
        }

        securityService.save(user);

        return "redirect:/register";
    }
}
