package com.app.farmacia.controller;

import com.app.farmacia.entity.Boleta;
import com.app.farmacia.repository.BoletaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class BoletaController {
    private final BoletaRepository boletaRepository;

    @GetMapping("/boleta/{ventaId}")
    public String verBoleta(@PathVariable Long ventaId, Model model) {
        Boleta boleta = boletaRepository.findByVentaId(ventaId)
                .orElseThrow(() -> new RuntimeException("Boleta no encontrada"));
        model.addAttribute("boleta", boleta);
        return "boleta";
    }
}
