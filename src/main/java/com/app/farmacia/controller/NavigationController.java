package com.app.farmacia.controller;

import com.app.farmacia.entity.User;
import com.app.farmacia.service.EmployeeService;
import com.app.farmacia.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class NavigationController {
    private final UserService userService;
    private final EmployeeService employeeService;
    private final MessageSource messageSource;

    @GetMapping("/nosotros")
    public String nostros(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("nombre", user.getNombre());
        model.addAttribute("apellido", user.getApellido());

        return "nosotros";
    }

    @GetMapping("/lista-empleados")
    public String listEmployees(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("nombre", user.getNombre());
        model.addAttribute("apellido", user.getApellido());

        return "nosotros";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }

    @GetMapping("/inicio")
    public String showHome(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("nombre", user.getNombre());
        model.addAttribute("apellido", user.getApellido());

        return "inicio";
    }
}
