package com.app.farmacia.service;

import com.app.farmacia.dto.LocalDto;

import java.util.List;

public interface LocalService {
    List<LocalDto.Response> listAll();
}
