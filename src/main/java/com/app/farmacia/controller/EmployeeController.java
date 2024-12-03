package com.app.farmacia.controller;

import com.app.farmacia.dto.EmpleadoDto;
import com.app.farmacia.dto.LocalDto;
import com.app.farmacia.entity.Local;
import com.app.farmacia.service.EmployeeService;
import com.app.farmacia.service.LocalService;
import com.app.farmacia.util.Util;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
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
@RequestMapping("/empleado")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;
    private final LocalService localService;

    @GetMapping("/lista")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String getEmployees(@RequestParam(required = false, defaultValue = "0") Integer index,
                               @RequestParam(required = false, defaultValue = "10") Integer size,
                               @RequestParam(required = false) String nombre,
                               @RequestParam(required = false) String apellido,
                               @RequestParam(required = false) String puesto,
                               Model model) {
        EmpleadoDto.FilterRequest request = new EmpleadoDto.FilterRequest();
        request.setIndex(index);
        request.setSize(size);
        if (StringUtils.isNoneBlank(nombre) && Util.isValidFilter(nombre)) {
            request.setNombre(nombre);
        }

        if (StringUtils.isNoneBlank(apellido) && Util.isValidFilter(apellido)) {
            request.setApellido(apellido);
        }

        if (StringUtils.isNoneBlank(puesto) && Util.isValidFilter(puesto)) {
            request.setPuesto(puesto);
        }

        Page<EmpleadoDto.Response> page = employeeService.listFilter(request);
        model.addAttribute("empleados", page.getContent());
        model.addAttribute("currentPage", index);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        return "employees";
    }

    @GetMapping("/registrar")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String showFromRegister(Model model) {
        EmpleadoDto.Request request = new EmpleadoDto.Request();
        model.addAttribute("empleado", request);

        List<LocalDto.Response> localList = localService.listAll();
        if (CollectionUtils.isNotEmpty(localList)) {
            model.addAttribute("locales", localList);
        }
        return "createEmployee";
    }

    @PostMapping("/registrar")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String register(@Valid @ModelAttribute("empleado") EmpleadoDto.Request request,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "createEmployee";
        }

        try {
            employeeService.saveEmployee(request);
            redirectAttributes.addFlashAttribute("msExito", "Te has registrado exitosamente");

            return "redirect:/empleado/lista";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msError", "Error al registrar empleado");

            return "createEmployee";
        }
    }

    @GetMapping("/editar/{id}")
    public String showFromEdit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("empleado", employeeService.getEmployeeById(id));

        List<LocalDto.Response> localList = localService.listAll();
        if (CollectionUtils.isNotEmpty(localList)) {
            model.addAttribute("locales", localList);
        }

        return "editEmployee";
    }

    @PostMapping("/editar/{id}")
    public String updateEmployee(@PathVariable("id") Long id, @ModelAttribute("empleado") EmpleadoDto.Update update) {
        employeeService.updateEmployee(id, update);

        return "redirect:/empleado/lista";
    }

    @GetMapping("/eliminar/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);

        return "redirect:/empleado/lista";
    }
}
