package com.app.farmacia.controller;

import static com.app.farmacia.util.Constants.ROLE_USER;

import com.app.farmacia.dto.UsuarioDto;
import com.app.farmacia.service.UserService;
import com.app.farmacia.util.Util;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/cliente")
@RequiredArgsConstructor
public class ClientController {
    private final UserService userService;

    @GetMapping("/lista")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String listClients(@RequestParam(required = false, defaultValue = "0") Integer index,
                              @RequestParam(required = false, defaultValue = "10") Integer size,
                              @RequestParam(required = false) String nombre,
                              @RequestParam(required = false) String apellido,
                              @RequestParam(required = false) String username,
                              Model model) {
        UsuarioDto.FilterRequest request = new UsuarioDto.FilterRequest();
        request.setIndex(index);
        request.setSize(size);
        if (StringUtils.isNoneBlank(nombre) && Util.isValidFilter(nombre)) {
            request.setNombre(nombre);
        }

        if (StringUtils.isNoneBlank(apellido) && Util.isValidFilter(apellido)) {
            request.setApellido(apellido);
        }

        if (StringUtils.isNoneBlank(username) && Util.isValidFilter(username)) {
            request.setUsername(username);
        }

        Page<UsuarioDto.Response> page = userService.listFilter(request);
        model.addAttribute("clientes", page.getContent());
        model.addAttribute("currentPage", index);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        return "clients";
    }

    @GetMapping("/registrar")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String showFromRegister(Model model) {
        UsuarioDto.Request request = new UsuarioDto.Request();
        model.addAttribute("cliente", request);
        return "createClient";
    }

    @GetMapping("/nuevo")
    public String showFromRegisterNewUser(Model model) {
        UsuarioDto.Request request = new UsuarioDto.Request();
        model.addAttribute("cliente", request);
        return "createNewUser";
    }

//    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    public String delete(@PathVariable("id") String id) {
//        return "redirect:/cliente/lista";
//    }

    @PostMapping("/registrar")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String register(@Valid UsuarioDto.Request request,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "createClient";
        }

        try {
            userService.saveUser(request);
            redirectAttributes.addFlashAttribute("msExito", "Te has registrado exitosamente");

            return "redirect:/cliente/lista";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msError", "Error al registrar usuario");

            return "createClient";
        }
    }

    @PostMapping("/nuevo")
    public String registerNewUser(@Valid UsuarioDto.Request request,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "createClient";
        }

        try {
            List<String> roles = List.of(ROLE_USER);
            request.setRoles(roles);
            userService.saveUser(request);
            redirectAttributes.addFlashAttribute("msExito", "Te has registrado exitosamente");

            return "redirect:/login";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msError", "Error al registrar usuario");

            return "createNewUser";
        }
    }

    @GetMapping("/eliminar/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);

        return "redirect:/cliente/lista";
    }
}
