package com.app.farmacia.service;

import com.app.farmacia.dto.MessageDto;
import com.app.farmacia.dto.NotificacionDto;

public interface NotificationService {
    MessageDto notifyNewUser(NotificacionDto.Request request);
}
