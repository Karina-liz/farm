package com.app.farmacia.service.impl;

import com.app.farmacia.bean.SendgridProperties;
import com.app.farmacia.dto.MessageDto;
import com.app.farmacia.dto.NotificacionDto;
import com.app.farmacia.dto.SendGridDto;
import com.app.farmacia.service.NotificationService;
import com.app.farmacia.util.Constants;
import com.google.gson.Gson;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final SendgridProperties sendgridProperties;
    private final SendGrid sendGrid;


    @Override
    public MessageDto notifyNewUser(NotificacionDto.Request request) {
        Personalization per = new Personalization();
        per.addDynamicTemplateData(Constants.NOMBRE, request.getNombre());
        per.addDynamicTemplateData(Constants.APELLIDO, request.getApellido());
        per.addDynamicTemplateData(Constants.USERNAME, request.getUsername());
        per.addDynamicTemplateData(Constants.PASSWORD, request.getPassword());
        per.addTo(new Email(request.getUsername(), request.getNombre()));

        return sendEmail(per, sendgridProperties.getTemplate().getNewUser());
    }

    private MessageDto sendEmail(Personalization personalization,
                                 String templateId) {
        Email fromEmail = new Email(sendgridProperties.getEmisorCorreo(),
                sendgridProperties.getEmisorNombre());
        Mail mail = new Mail();
        mail.setFrom(fromEmail);
        mail.addPersonalization(personalization);
        mail.setTemplateId(templateId);

        try {
            Request request = new Request();
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sendGrid.api(request);

            if (response == null) {
                log.error("Error al enviar notificación.");
                throw new RuntimeException("Fallo al enviar notificación.");
            }

            if (response.getStatusCode() < 200 || response.getStatusCode() > 204) {
                SendGridDto.ErrorResponse data = new Gson()
                        .fromJson(response.getBody(), SendGridDto.ErrorResponse.class);

                log.error(Constants.SENGRID_ERROR_RESPONSE, new Gson().toJson(data));
                throw new RuntimeException(data.getErrors().getFirst().getMessage());
            }
            return new MessageDto(Constants.SUCCESS_MESSAGE);
        } catch (IOException e) {
            log.error(Constants.EXCEPTION_MESSAGE, ExceptionUtils.getStackTrace(e));
            throw new RuntimeException(Constants.ERROR_MESSAGE);
        }
    }
}
