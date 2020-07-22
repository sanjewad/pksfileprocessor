package com.fxdms.ams.notification.endpoint;

import com.fxdms.ams.notification.util.Mail;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
public class EmailDispatcherService {

    private static final Logger logger = LoggerFactory.getLogger(EmailDispatcherService.class);
    private static final String EMAIL_TEMPLATE_FTL_NAME = "email-template.ftl";

    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    private Configuration freemarkerConfig;

    public void sendSimpleMessage(Mail mail) throws MessagingException, IOException, TemplateException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
        // mimeMessageHelper.addAttachment("logo.png", new ClassPathResource("memorynotfound-logo.png"));
        mimeMessageHelper.setSubject(mail.getSubject());
        mimeMessageHelper.setFrom(mail.getFrom());
        mimeMessageHelper.setTo(mail.getTo());
        mail.setContent(geContentFromTemplate(mail.getModel()));
        mimeMessageHelper.setText(mail.getContent(), true);
        emailSender.send(mimeMessageHelper.getMimeMessage());
        logger.debug("mail sender processed with mail message {}", mail.getContent());
    }


    public String geContentFromTemplate(Map<String, Object> model) {
        StringBuffer content = new StringBuffer();
        try {
            content.append(FreeMarkerTemplateUtils.processTemplateIntoString(freemarkerConfig.getTemplate(EMAIL_TEMPLATE_FTL_NAME), model));
        } catch (Exception e) {
            logger.error("Exception occurred while processing email {}", model);
        }
        return content.toString();
    }
}
