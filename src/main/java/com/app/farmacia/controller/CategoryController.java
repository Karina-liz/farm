package com.app.farmacia.controller;

import com.app.farmacia.dto.CategoriaDto;
import com.app.farmacia.entity.User;
import com.app.farmacia.service.CategoryService;
import com.app.farmacia.util.Util;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/categoria")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/lista")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String list(
            @RequestParam(required = false, defaultValue = "0") Integer index,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @RequestParam(required = false) String nombre,
            @AuthenticationPrincipal User user,
            Model model) {



        if (user != null) {
            return "redirect:/login";
        }
        CategoriaDto.FilterRequest request = new CategoriaDto.FilterRequest();
        request.setIndex(index);
        request.setSize(size);

        if (StringUtils.isNoneBlank(nombre) && Util.isValidFilter(nombre)) {
            request.setNombre(nombre);
        }

        Page<CategoriaDto.Response> page = categoryService.listFilter(request);

        model.addAttribute("categorias", page.getContent());
        model.addAttribute("currentPage", index);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        return "categorias";
    }

    @GetMapping("/registrar")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String showFromRegister(Model model) {
        CategoriaDto.Request request = new CategoriaDto.Request();
        model.addAttribute("categoria", request);

        return "createCategory";
    }

    @GetMapping("/editar/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String showFromEdit(@PathVariable("id") Long id, Model model) {
        CategoriaDto.Response response = categoryService.getCategoryById(id);
        if (response != null) {
            model.addAttribute("categoria", response);
            return "createCategory";
        }

        return "redirect:/categoria/lista";
    }

    @PostMapping("/registrar")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String saveCategory(@Valid CategoriaDto.Request request) {
        categoryService.saveCategory(request);

        return "redirect:/categoria/lista";
    }

    @PostMapping("/editar/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String editCategory(@PathVariable("id") Long id,
                               @ModelAttribute CategoriaDto.Update update) {
        categoryService.updateCategory(id, update);

        return "redirect:/categoria/lista";
    }

    @GetMapping("/eliminar/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deleteCategory(@PathVariable("id") Long id) {
        categoryService.deleteCategory(id);

        return "redirect:/categoria/lista";
    }
}
