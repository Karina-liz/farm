package com.app.farmacia.service.impl;

import com.app.farmacia.dto.LocalDto;
import com.app.farmacia.entity.Local;
import com.app.farmacia.repository.LocalRepository;
import com.app.farmacia.service.LocalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LocalServiceImpl implements LocalService {
    private final LocalRepository localRepository;

    @Override
    public List<LocalDto.Response> listAll() {
        List<Local> localList = localRepository.findAll();
        if (CollectionUtils.isNotEmpty(localList)) {
            return new LocalDto.Response().list(localList);
        }

        return null;
    }
}
