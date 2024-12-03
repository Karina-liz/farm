package com.app.farmacia.service;

import com.app.farmacia.dto.UsuarioDto;
import org.springframework.data.domain.Page;
import java.util.List;

public interface UserService {
    UsuarioDto.Response findByEmail(String username);

    UsuarioDto.Response findById(Long id);

    Page<UsuarioDto.Response> listFilter(UsuarioDto.FilterRequest request);

    List<UsuarioDto.Response> listAll();

    void saveUser(UsuarioDto.Request request);

    void updateUser(Long id, UsuarioDto.Update update);

    void deleteUser(Long id);
}
