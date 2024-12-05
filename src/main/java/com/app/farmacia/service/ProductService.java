package com.app.farmacia.service;

import com.app.farmacia.dto.ProductoDto;
import com.app.farmacia.entity.Producto;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    Producto searchById(Long id);

    ProductoDto.Response findById(Long id);

    List<ProductoDto.Response> findByNombre(String nombreProducto);

    List<ProductoDto.Response> findByCategoria(String categria);

    Page<ProductoDto.Response> listFilter(ProductoDto.FilterRequest request);

    List<ProductoDto.Response> listFilterCustom(ProductoDto.FilterRequest request);

    void saveProduct(ProductoDto.Request request, MultipartFile file) throws IOException;

    void updateProduct(Long id, ProductoDto.Update update, MultipartFile file) throws IOException;

    void deleteProduct(Long id);
}
