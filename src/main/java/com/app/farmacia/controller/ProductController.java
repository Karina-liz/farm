package com.app.farmacia.controller;

import com.app.farmacia.dto.CategoriaDto;
import com.app.farmacia.dto.LoteDto;
import com.app.farmacia.dto.ProductoDto;
import com.app.farmacia.entity.Categoria;
import com.app.farmacia.entity.Cliente;
import com.app.farmacia.entity.Producto;
import com.app.farmacia.entity.User;
import com.app.farmacia.repository.CategoriaRepository;
import com.app.farmacia.service.CategoryService;
import com.app.farmacia.service.LotService;
import com.app.farmacia.service.ProductService;
import com.app.farmacia.service.UserService;
import com.app.farmacia.util.Util;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final LotService lotService;

    @GetMapping("/producto/lista")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String listProduct(@RequestParam(required = false, defaultValue = "0") Integer index,
                              @RequestParam(required = false, defaultValue = "10") Integer size,
                              @RequestParam(required = false) String nombre,
                              @RequestParam(required = false) Long categoria,
                        Model model) {
        ProductoDto.FilterRequest request = new ProductoDto.FilterRequest();
        request.setIndex(index);
        request.setSize(size);
        if (StringUtils.isNoneBlank(nombre) && Util.isValidFilter(nombre)) {
            request.setNombre(nombre);
        }

        if (categoria != null) {
            request.setCategoria(categoria);
        }

        Page<ProductoDto.Response> page = productService.listFilter(request);
        model.addAttribute("productos", page.getContent());
        model.addAttribute("currentPage", index);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        return "products";
    }

    @GetMapping("/producto/registrar")
    public String showFromRegistrar(Model model) {
        model.addAttribute("producto", new ProductoDto.Request());

        List<CategoriaDto.Response> categorias = categoryService.listAll();
        if (CollectionUtils.isNotEmpty(categorias)) {
            model.addAttribute("categorias", categorias);
        }

        List<LoteDto.Response> loteList = lotService.listAll();
        if (CollectionUtils.isNotEmpty(loteList)) {
            model.addAttribute("lotes", loteList);
        }

        return "createProduct";
    }

    @PostMapping("/producto/registrar")
    public String register(@RequestPart MultipartFile file,
                           @Valid @ModelAttribute("producto") ProductoDto.Request request) throws IOException {
        productService.saveProduct(request, file);

        return "redirect:/producto/lista";
    }

    @GetMapping("/producto/editar/{id}")
    public String showFromEdit(@PathVariable("id") Long id,
                               Model model) {
        model.addAttribute("producto", productService.findById(id));

        List<CategoriaDto.Response> categorias = categoryService.listAll();
        model.addAttribute("categorias", categorias);

        return "editProduct";
    }

    @PostMapping("/producto/editar/{id}")
    public String update(@PathVariable("id") Long id,
                         @RequestPart MultipartFile file,
                         @Valid @ModelAttribute("producto") ProductoDto.Update update) throws IOException {
        CategoriaDto.Response categoria = categoryService.getCategoryById(update.getCategoriaId());
        productService.updateProduct(id, update, file);

        return "redirect:/producto/lista";
    }

    @GetMapping("/producto/eliminar/{id}")
    public String delete(@PathVariable("id") Long id) throws IOException {
        productService.deleteProduct(id);

        return "redirect:/producto/lista";
    }

    @GetMapping("/catalogo")
public String mostrarCatalogo(
        @RequestParam(required = false) String buscar,
        @RequestParam(required = false) String categoria,
        Model model,
        @AuthenticationPrincipal User user) {

    // Agregar información del usuario autenticado al modelo
    if (user != null) {
        model.addAttribute("nombreCliente", user.getNombre());
    }

    // Filtrar productos por búsqueda y/o categoría
    List<ProductoDto.Response> productos;
    if (buscar != null && !buscar.trim().isEmpty() && categoria != null && !categoria.trim().isEmpty()) {
        productos = productService.listFilterCustom(new ProductoDto.FilterRequest());
    } else if (buscar != null && !buscar.trim().isEmpty()) {
        productos = productService.findByNombre(buscar);
    } else if (categoria != null && !categoria.trim().isEmpty()) {
        productos = productService.buscarPorCategoria(categoria);
    } else {
        productos = productService.listFilterCustom(new ProductoDto.FilterRequest());
    }

    // Enviar los productos al modelo
    model.addAttribute("productos", productos);

    // Obtener las categorías disponibles
    List<Categoria> categorias = productService.listarCategorias();
    model.addAttribute("categorias", categorias);

    // Devolver la vista del catálogo
    return "catalogo";
}


}
