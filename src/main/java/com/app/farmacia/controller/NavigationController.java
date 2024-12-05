package com.app.farmacia.controller;

import com.app.farmacia.dto.CategoriaDto;
import com.app.farmacia.dto.ProductoDto;
import com.app.farmacia.entity.User;
import com.app.farmacia.service.CategoryService;
import com.app.farmacia.service.EmployeeService;
import com.app.farmacia.service.ProductService;
import com.app.farmacia.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class NavigationController {
    private final UserService userService;
    private final EmployeeService employeeService;
    private final MessageSource messageSource;
    private final ProductService productService;
    private final CategoryService categoryService;

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

    @GetMapping("/catalogo")
    public String showCatalog(@AuthenticationPrincipal User user,
                              @RequestParam(required = false) String nombre,
                              @RequestParam(required = false) Long categoria,
                              Model model) {
        model.addAttribute("nombre", user.getNombre());
        model.addAttribute("apellido", user.getApellido());

        ProductoDto.FilterRequest request = new ProductoDto.FilterRequest();
        request.setNombre(nombre);
        request.setCategoria(categoria);

        List<ProductoDto.Response> productos = productService.listFilterCustom(request);
        model.addAttribute("productos", productos);

        List<CategoriaDto.Response> categorias = categoryService.listAll();
        model.addAttribute("categorias", categorias);

        return "catalogo";
    }
}
