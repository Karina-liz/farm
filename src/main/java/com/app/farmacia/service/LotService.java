package com.app.farmacia.service;

import com.app.farmacia.dto.LoteDto;

import java.util.List;

public interface LotService {
    List<LoteDto.Response> listAll();
}
