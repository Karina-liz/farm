package com.app.farmacia.service;

import com.app.farmacia.dto.CategoriaDto;
import com.app.farmacia.entity.Categoria;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {
    CategoriaDto.Response getCategoryById(Long id);

    Page<CategoriaDto.Response> listFilter(CategoriaDto.FilterRequest request);

    List<CategoriaDto.Response> listAll();

    void saveCategory(CategoriaDto.Request request);

    void updateCategory(Long id, CategoriaDto.Update update);

    void deleteCategory(Long id);
}
