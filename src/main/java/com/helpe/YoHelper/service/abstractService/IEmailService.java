package com.helpe.YoHelper.service.abstractService;


import com.sendgrid.helpers.mail.objects.Content;

import java.io.IOException;

public interface IEmailService {
    void sendWelcomeEmailTo(String to, String name );

    String WelcomeEmailTemplate(String name) throws IOException;

    void sendEmail(String emailTo, String emailSubject, Content body);
}
