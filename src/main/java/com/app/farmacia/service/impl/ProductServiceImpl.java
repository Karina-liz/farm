package com.app.farmacia.service.impl;

import com.app.farmacia.dto.ProductoDto;
import com.app.farmacia.entity.Categoria;
import com.app.farmacia.entity.Lote;
import com.app.farmacia.entity.Producto;
import com.app.farmacia.repository.CategoriaRepository;
import com.app.farmacia.repository.LoteRepository;
import com.app.farmacia.repository.ProductoRepository;
import com.app.farmacia.service.ProductService;
import com.app.farmacia.service.UploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;
    private final LoteRepository loteRepository;
    private final UploadService uploadService;

    @Override
    public ProductoDto.Response findById(Long id) {
        return null;
    }

    @Override
    public List<ProductoDto.Response> findByNombre(String nombreProducto) {
        List<Producto> productos = productoRepository.findByNombreProductoContainingIgnoreCase(nombreProducto);

        List<ProductoDto.Response> result = null;
        if (CollectionUtils.isNotEmpty(productos)) {
            result = new ProductoDto.Response().list(productos);
        }

        return result;
    }

    @Override
    public List<ProductoDto.Response> findByCategoria(String categria) {
        List<Producto> productos = productoRepository.findByNombreProductoContainingIgnoreCase(categria);
        if (CollectionUtils.isNotEmpty(productos)) {
            return new ProductoDto.Response().list(productos);
        }

        return null;
    }

    @Override
    public Page<ProductoDto.Response> listFilter(ProductoDto.FilterRequest request) {
        Pageable pageable = PageRequest.of(request.getIndex(), request.getSize());
        Page<Producto> page = productoRepository.findByFilters(request.getNombre(), request.getCategoria(), pageable);

        return page.map(this::convertToDto);
    }

    @Override
    public List<ProductoDto.Response> listFilterCustom(ProductoDto.FilterRequest request) {
        List<Producto> productos = productoRepository.findByFiltersCustom(request.getNombre(), request.getCategoria());
        return new ProductoDto.Response().list(productos);
    }

    @Override
    public void saveProduct(ProductoDto.Request request, MultipartFile file) throws IOException {
        String image = uploadService.saveUpload(file);
        request.setFoto(image);

        Producto producto = Producto.builder()
                .nombreProducto(request.getNombreProducto())
                .precioCompra(request.getPrecioCompra())
                .precioVenta(request.getPrecioVenta())
                .principioActivo(request.getPrincipioActivo())
                .precentacion(request.getPrecentacion())
                .laboratorio(request.getLaboratorio())
                .foto(image)
                .fechaVencimento(request.getFechaVencimiento())
                .build();

        Lote lote = loteRepository.findById(request.getLoteId())
                .orElseThrow(() -> new RuntimeException("Lote no encontrado"));

        Categoria categoria = categoriaRepository.findById(request.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria no encontrado"));

        producto.setLote(lote);
        producto.setCategoria(categoria);
        productoRepository.save(producto);
    }

    @Override
    public void updateProduct(Long id, ProductoDto.Update update, MultipartFile file) throws IOException {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        if (!file.isEmpty()) {
            producto.setFoto(uploadService.saveUpload(file));
        }

        productoRepository.save(producto);
    }

    @Override
    public void deleteProduct(Long id) {
        productoRepository.deleteById(id);
    }

    private ProductoDto.Response convertToDto(Producto producto) {
        return new ProductoDto.Response(producto);
    }
}
