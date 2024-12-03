package com.app.farmacia.controller;

import com.app.farmacia.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AuthenticationController {
    private final MessageSource messageSource;

//    @GetMapping("/login")
//    public String login() {
//        return "login";
//    }

//    @PostMapping("/login")
//    public String validate() {
//
//    }
//
//    @GetMapping("/nosotros")
//    public String nostros(@AuthenticationPrincipal User user, Model model) {
//        model.addAttribute("nombre", user.getNombre());
//        model.addAttribute("apellido", user.getApellido());
//
//        return "nosotros";
//    }
}
