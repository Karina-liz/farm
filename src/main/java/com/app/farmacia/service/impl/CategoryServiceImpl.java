package com.app.farmacia.service.impl;

import com.app.farmacia.dto.CategoriaDto;
import com.app.farmacia.entity.Categoria;
import com.app.farmacia.repository.CategoriaRepository;
import com.app.farmacia.service.CategoryService;
import com.app.farmacia.util.Util;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoriaRepository categoriaRepository;

    @Override
    public CategoriaDto.Response getCategoryById(Long id) {
        Categoria categoria = categoriaRepository.findById(id).orElse(null);

        if (categoria != null) {
            return new CategoriaDto.Response(categoria);
        }

        return null;
    }

    @Override
    public Page<CategoriaDto.Response> listFilter(CategoriaDto.FilterRequest request) {
        Pageable pageable = PageRequest.of(request.getIndex(), request.getSize());
        Page<Categoria> page = categoriaRepository.findByNombreCustom(request.getNombre(), pageable);

        return page.map(this::convertToDto);
    }

    @Override
    public List<CategoriaDto.Response> listAll() {
//        log.info("Buscar Categoria");
        List<Categoria> list = categoriaRepository.findAll();

        List<CategoriaDto.Response> result = null;
        if (CollectionUtils.isNotEmpty(list)) {
//            log.info("Cantidad {}", list.size());
            result = new ArrayList<>();

            for (Categoria categoria : list) {
                result.add(new CategoriaDto.Response(categoria));
            }
        }

        return result;
    }

    @Override
    public void saveCategory(CategoriaDto.Request request) {
        Categoria categoria = Categoria.builder()
                .nombre(Util.toUpperString(request.getNombre()))
                .build();

        categoriaRepository.save(categoria);
    }

    @Override
    public void updateCategory(Long id, CategoriaDto.Update update) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));

        categoria.setNombre(update.getNombre());
        categoriaRepository.save(categoria);
    }

    @Override
    public void deleteCategory(Long id) {
        categoriaRepository.deleteById(id);
    }

    private CategoriaDto.Response convertToDto(Categoria categoria) {
        return new CategoriaDto.Response(categoria);
    }
}
