package com.app.farmacia.controller;

import com.app.farmacia.bean.Carrito;
import com.app.farmacia.dto.CategoriaDto;
import com.app.farmacia.dto.ProductoDto;
import com.app.farmacia.dto.VentaDto;
import com.app.farmacia.entity.Producto;
import com.app.farmacia.entity.User;
import com.app.farmacia.service.CategoryService;
import com.app.farmacia.service.ProductService;
import com.app.farmacia.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;

@Controller
//@RequestMapping("carrito")
@RequiredArgsConstructor
@SessionAttributes("carrito")
public class ShoppingCartController {
    private final ProductService productService;
    private final SaleService saleService;
    private final CategoryService categoryService;

    @ModelAttribute("carrito")
    public Carrito crearCarrito() {
        return new Carrito();
    }

    @GetMapping("/carrito")
    public String verCarrito(@AuthenticationPrincipal User user,
                             @RequestParam(required = false) String nombre,
                             @RequestParam(required = false) Long categoria,
                             @ModelAttribute("carrito") Carrito carrito,
                             Model model) {
        model.addAttribute("total", carrito.getTotal());
        model.addAttribute("user", user);

//        model.addAttribute("nombre", user.getNombre());
//        model.addAttribute("apellido", user.getApellido());
//
//        ProductoDto.FilterRequest request = new ProductoDto.FilterRequest();
//        request.setNombre(nombre);
//        request.setCategoria(categoria);
//
//        List<ProductoDto.Response> productos = productService.listFilterCustom(request);
//        model.addAttribute("productos", productos);
//
//        List<CategoriaDto.Response> categorias = categoryService.listAll();
//        model.addAttribute("categorias", categorias);
        return "carrito";
    }

    @PostMapping("/carrito/agregar")
    public String agregarProducto(@AuthenticationPrincipal User user,
                                  @RequestParam Long productoId,
                                  @RequestParam Long cantidad,
                                  @ModelAttribute("carrito") Carrito carrito) {
        ProductoDto.Response productoResult = productService.findById(productoId);
//        Producto producto = Producto.builder()
//                .build()

        carrito.agregarProducto(productoResult, user.getId(), cantidad);

        return "redirect:/carrito";
    }

    @PostMapping("/carrito/eliminar")
    public String eliminarProducto(@RequestParam Long productoId,
                                   @ModelAttribute("carrito") Carrito carrito) {
        carrito.eliminarProducto(productoId);

        return "redirect:/carrito";
    }

    @PostMapping("/carrito/checkout")
    public String checkout(@ModelAttribute("carrito") Carrito carrito,
                           SessionStatus status) {
        VentaDto.Response venta = saleService.registrarVenta(carrito);
        status.setComplete();

        return "redirect:/boleta/" + venta.getId();
    }
}
