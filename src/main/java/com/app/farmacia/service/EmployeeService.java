package com.app.farmacia.service;

import com.app.farmacia.dto.EmpleadoDto;
import org.springframework.data.domain.Page;
import java.util.List;

public interface EmployeeService {
    EmpleadoDto.Response getEmployeeById(Long id);

    EmpleadoDto.Response findByEmail(String email);

    Page<EmpleadoDto.Response> listFilter(EmpleadoDto.FilterRequest request);

    List<EmpleadoDto.Response> listAll();

    void saveEmployee(EmpleadoDto.Request request);

    void updateEmployee(Long id, EmpleadoDto.Update update);

    void deleteEmployee(Long id);
}
