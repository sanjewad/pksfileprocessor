package com.fxdms.ams.notification.impl;

import com.fxdms.ams.dto.SubscriberNotificationDto;
import com.fxdms.ams.notification.dto.ClientIncidentDto;
import com.fxdms.ams.notification.endpoint.EmailDispatcherService;
import com.fxdms.ams.notification.factory.IConsumerProcessor;
import com.fxdms.ams.notification.util.Mail;
import freemarker.template.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


@Component
public class SubscriptionEmailProcessor implements IConsumerProcessor {

    private static final Logger logger = LoggerFactory.getLogger(SubscriptionEmailProcessor.class);
    private static final String[] EMAIL_HEADER_PARAMETER_NAMES = {"description", "ciname", "cistate", "source", "eventtimestamp"};

    @Autowired
    private EmailDispatcherService emailDispatcherService;
    @Autowired
    private Configuration freemarkerConfig;

    @Override
    public String name() {
        return "EMAIL";
    }


    @Override
    public String processAndDeliverContents(Set<ClientIncidentDto> clientIncidentDtoList) {
        clientIncidentDtoList.stream().forEach(notificationDto -> {
            Mail mail = new Mail();
            mail.setFrom("no-reply@memorynotfound.com");
            mail.setTo("info@memorynotfound.com");
            mail.setSubject("Sending Email with Freemarker HTML Template Example");

            Map model = new HashMap();
            model.put(EMAIL_HEADER_PARAMETER_NAMES[0], notificationDto.getAlertSignature());
            model.put(EMAIL_HEADER_PARAMETER_NAMES[1], notificationDto.getEventId());
            model.put(EMAIL_HEADER_PARAMETER_NAMES[2], notificationDto.getStatus());
            model.put(EMAIL_HEADER_PARAMETER_NAMES[3], notificationDto.getDescription());
            model.put(EMAIL_HEADER_PARAMETER_NAMES[4], LocalDateTime.now());
            mail.setModel(model);
            try {
                emailDispatcherService.sendSimpleMessage(mail);
            } catch (Exception e) {
                logger.error("Error occurred while processing email {} with error message {}", model, e.getMessage());
            }
        });
        return null;
    }

    @Override
    public SubscriptionResponse processAndDeliverContent(ClientIncidentDto clientIncidentDto) {
        return null;
    }
}
