package com.helpe.YoHelper.service;

import com.helpe.YoHelper.service.abstractService.IEmailService;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.apache.commons.io.IOUtils;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


@Service
@Slf4j
public class EmailService implements IEmailService {
    public static final String EMAIL_SENDER = "potskhverashvilimaia218@gmail.com";
    public static final String API_KEY = "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";

    @Override
    public void sendWelcomeEmailTo(String to, String name) {
        try {
            String emailSubject = "Bienvenidos a Helper Assist";
            String emailContent=WelcomeEmailTemplate(name);
            log.debug("Email Content: {}", emailContent);
            Content body = new Content("text/html", emailContent);

            sendEmail(to, emailSubject, body);
            log.info("Welcome : {}", to);
        } catch (Exception ex) {
            log.error("Error sending welcome email to {}: {}", to, ex.getMessage());
        }
    }
    @Override
    public void sendEmail(String emailTo, String emailSubject, Content body) {
        Email from = new Email(EMAIL_SENDER);
        Email to = new Email(emailTo);
        String subject = emailSubject;
        Content content = body;

        Mail mail = new Mail(from, subject, to, content);
        SendGrid sgKey = new SendGrid(API_KEY);
        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sgKey.api(request);
            if (response != null && response.getStatusCode() >= 200 && response.getStatusCode() < 300) {
                log.info("Mail enviado a {}", emailTo);
            } else {
                log.error("Error al enviar correo a {}: {}", emailTo, response == null ? "Respuesta nula" : response.getBody());
            }

        } catch (IOException ex) {
            log.error("Error al enviar mail a {}: {}", emailTo, ex.getMessage());
            ex.printStackTrace();
        }
    }
    @Override
    public String WelcomeEmailTemplate(String name) {
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Template para Gmail</title>\n" +
                "</head>\n" +
                "<body style=\"margin: 0; padding: 0; background-image: url('https://ichef.bbci.co.uk/news/800/cpsprodpb/F800/production/_118088436_for_press_release.jpg'); background-repeat: no-repeat; background-position: center top; background-size: cover\">\n" +
                "\n" +
                "    <table role=\"presentation\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"width: 100%; max-width: 600px;\" >\n" +
                "        <tr>\n" +
                "            <td align=\"center\"  style=\"padding: 40px 0;\">\n" +
                "                <!--[if mso]>\n" +
                "                <v:rect xmlns:v=\"urn:schemas-microsoft-com:vml\" fill=\"true\" stroke=\"false\" style=\"width: 100%; height: 100%;\">\n" +
                "                <v:fill type=\"tile\" src=\"https://ichef.bbci.co.uk/news/800/cpsprodpb/F800/production/_118088436_for_press_release.jpg\" color=\"#f7f7f7\" />\n" +
                "                <v:textbox style=\"mso-fit-shape-to-text: true;\" inset=\"0,0,0,0\">\n" +
                "                <![endif]-->\n" +
                "                <div>\n" +
                "                    <table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"margin: 0 auto; width: 100%; max-width: 600px; background-color: transparent; padding: 20px;\">\n" +
                "                        <tr>\n" +
                "                            <td align=\"center\" style=\"padding: 0 0 20px;\">\n" +
                "                                <h1 style=\"color: #fff; font-size: 24px; margin: 0;\">¡Bienvenido a Helper Asssit !</h1>\n" +
                "                            </td>\n" +
                "                        </tr>\n" +
                "                        <tr>\n" +
                "                            <td align=\"center\" style=\"padding: 0;\">\n" +
                "                                <p style=\"color: #fff; font-size: 16px; line-height: 24px; margin: 0;\">Gracias por suscribirte a nuestra comunidad. Estamos emocionados de compartir contigo las últimas noticias y actualizaciones.</p>\n" +
                "                            </td>\n" +
                "                        </tr>\n" +
                "                        <tr>\n" +
                "                            <td align=\"center\" style=\"padding: 20px 0;\">\n" +
                "                                <a href=\"https://chakruk.quinto.site/\" target=\"_blank\" style=\"display: inline-block; background-color: #007bff; color: #ffffff; text-decoration: none; font-size: 18px; padding: 10px 20px; border-radius: 5px;\">Visitar nuestro sitio web</a>\n" +
                "                            </td>\n" +
                "                        </tr>\n" +
                "                    </table>\n" +
                "                </div>\n" +
                "                <!--[if mso]>\n" +
                "                </v:textbox>\n" +
                "                </v:rect>\n" +
                "                <![endif]-->\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "    </table>\n" +
                "\n" +
                "</body>\n" +
                "\n" +
                "</html>";
    }



}
