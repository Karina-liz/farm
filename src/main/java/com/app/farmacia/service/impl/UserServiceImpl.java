package com.app.farmacia.service.impl;

import com.app.farmacia.dto.MessageDto;
import com.app.farmacia.dto.NotificacionDto;
import com.app.farmacia.dto.UsuarioDto;
import com.app.farmacia.entity.Role;
import com.app.farmacia.entity.User;
import com.app.farmacia.enums.RoleName;
import com.app.farmacia.repository.RoleRepository;
import com.app.farmacia.repository.UserRepository;
import com.app.farmacia.service.NotificationService;
import com.app.farmacia.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final NotificationService notificationService;

    @Override
    public UsuarioDto.Response findByEmail(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (user != null) {
            return new UsuarioDto.Response(user);
        }

        return null;
    }

    @Override
    public UsuarioDto.Response findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (user != null) {
            return new UsuarioDto.Response(user);
        }

        return null;
    }

    @Override
    public Page<UsuarioDto.Response> listFilter(UsuarioDto.FilterRequest request) {
        Pageable pageable = PageRequest.of(request.getIndex(), request.getSize());
        Page<User> page = userRepository.findByFilters(request.getNombre(), request.getApellido(), request.getUsername(), pageable);

        return page.map(this::convertToDto);
    }

    @Override
    public List<UsuarioDto.Response> listAll() {
        List<User> list = userRepository.findAll();

        List<UsuarioDto.Response> result = null;
        if (CollectionUtils.isNotEmpty(list)) {
            result = new ArrayList<>();

            for (User user : list) {
                result.add(new UsuarioDto.Response(user));
            }
        }

        return result;
    }

    @Override
    public void saveUser(UsuarioDto.Request request) {
        try {
            User findUser = userRepository.findByUsername(request.getUsername()).orElse(null);

            if (findUser != null) {
                throw new RuntimeException("Ya existe un usuario");
            }

            User newUser = User.builder()
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .nombre(request.getNombre())
                    .apellido(request.getApellido())
                    .dni(request.getDni())
                    .telefono(request.getTelefono())
                    .direccion(request.getDireccion())
                    .fechaRegistro(LocalDateTime.now())
                    .build();

            Set<Role> roles = new HashSet<>();
            request.getRoles().forEach(r -> {
                Role role = roleRepository.findByRole(RoleName.valueOf(r))
                        .orElseThrow(() -> new RuntimeException("Role not found"));
                roles.add(role);
            });

            newUser.setRoles(roles);
            userRepository.save(newUser);

            NotificacionDto.Request requestNotify = new NotificacionDto.Request();
            requestNotify.setNombre(request.getNombre());
            requestNotify.setApellido(request.getApellido());
            requestNotify.setUsername(newUser.getUsername());
            requestNotify.setPassword(request.getPassword());

            MessageDto messageDto = notificationService.notifyNewUser(requestNotify);

        } catch (RuntimeException e) {
            log.error("Ocurrio un error al registrar el usuario {}", e.getMessage());
        }
    }

    @Override
    public void updateUser(Long id, UsuarioDto.Update update) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        user.setTelefono(update.getTelefono());
        user.setDireccion(update.getDireccion());
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    private UsuarioDto.Response convertToDto(User user) {
        return new UsuarioDto.Response(user);
    }
}
