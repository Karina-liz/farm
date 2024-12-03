package com.app.farmacia.service.impl;

import com.app.farmacia.dto.LoteDto;
import com.app.farmacia.entity.Lote;
import com.app.farmacia.repository.LoteRepository;
import com.app.farmacia.service.LotService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LotServiceImpl implements LotService {
    private final LoteRepository loteRepository;

    @Override
    public List<LoteDto.Response> listAll() {
        List<Lote> list = loteRepository.findAll();
        if (CollectionUtils.isNotEmpty(list)) {
            return new LoteDto.Response().list(list);
        }

        return null;
    }
}
