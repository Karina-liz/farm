package com.app.farmacia.service.impl;

import com.app.farmacia.dto.EmpleadoDto;
import com.app.farmacia.entity.Empleado;
import com.app.farmacia.entity.Local;
import com.app.farmacia.repository.EmpleadoRepository;
import com.app.farmacia.repository.LocalRepository;
import com.app.farmacia.service.EmployeeService;
import com.app.farmacia.util.Util;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmpleadoRepository empleadoRepository;
    private final LocalRepository localRepository;

    @Override
    public EmpleadoDto.Response getEmployeeById(Long id) {
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        if (empleado != null) {
            return new EmpleadoDto.Response(empleado);
        }

        return null;
    }

    @Override
    public EmpleadoDto.Response findByEmail(String email) {
        Empleado empleado = empleadoRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        if (empleado != null) {
            return new EmpleadoDto.Response(empleado);
        }

        return null;
    }

    @Override
    public Page<EmpleadoDto.Response> listFilter(EmpleadoDto.FilterRequest request) {
        log.info("index {} size {}", request.getIndex(), request.getSize());
        Pageable pageable = PageRequest.of(request.getIndex(), request.getSize());
        Page<Empleado> page = empleadoRepository.findByFilters(request.getNombre(), request.getApellido(), request.getPuesto(), pageable);

        return page.map(this::convertToDto);
    }

    @Override
    public List<EmpleadoDto.Response> listAll() {
        List<Empleado> list = empleadoRepository.findAll();

        if (CollectionUtils.isNotEmpty(list)) {
            return new EmpleadoDto.Response().list(list);
        }

        return null;
    }

    @Override
    public void saveEmployee(EmpleadoDto.Request request) {
        try {
            Empleado empleado = Empleado.builder()
                    .nombre(request.getNombre())
                    .apellido(request.getApellido())
                    .email(request.getEmail())
                    .telefono(request.getTelefono())
                    .puesto(Util.toUpperString(request.getPuesto()))
                    .dni(request.getDni())
                    .salario(request.getSalario())
                    .fechaRegistro(LocalDateTime.now())
                    .build();
            Local local = localRepository.findById(request.getLocalId())
                    .orElseThrow(() -> new RuntimeException("Local no encontrado"));

            empleado.setLocal(local);
            empleadoRepository.save(empleado);
        } catch (RuntimeException e) {
            log.error("Ocurrio un error al registrar el empleado {}", e.getMessage());
        }
    }

    @Override
    public void updateEmployee(Long id, EmpleadoDto.Update update) {
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        empleado.setNombre(update.getNombre());
        empleado.setApellido(update.getApellido());
        empleado.setDni(update.getDni());
        empleado.setEmail(update.getEmail());
        empleado.setTelefono(update.getTelefono());
        empleado.setPuesto(update.getPuesto());
        empleado.setSalario(update.getSalario());
        empleadoRepository.save(empleado);
    }

    @Override
    public void deleteEmployee(Long id) {
        empleadoRepository.deleteById(id);
    }

    private EmpleadoDto.Response convertToDto(Empleado empleado) {
        return new EmpleadoDto.Response(empleado);
    }
}
