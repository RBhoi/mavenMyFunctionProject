package com.deloitte.publish.mail;

import com.deloitte.publish.Publisher;
import com.deloitte.publish.PublisherException;
import com.example.FunctionInput;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

public class MailPublisher implements Publisher {

    private static final String MAIL_SMTP_HOST = "mail.smtp.host";
    private Properties properties;
    private String user;
    private String password;
    private String from;
    private String to;

    public MailPublisher(FunctionInput functionInput) {
        properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.user.agent", "ART Demo 1.0");
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.debug", "true");

        String smtp = functionInput.getSmtp();

        properties.put(MAIL_SMTP_HOST, smtp);
        properties.put("mail.smtp.ssl.trust", smtp);

        if("smtp.gmail.com".equals(smtp)) {
            properties.put("mail.smtp.starttls.enable", true);
        }

        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.socketFactory.port", "587");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        this.user = functionInput.getUser();
        this.password = functionInput.getPassword();
        this.from = functionInput.getFrom();
        this.to = functionInput.getTo();
    }

    @Override
    public void send(File file) {
        if(properties.getProperty(MAIL_SMTP_HOST) == null || user == null || password == null) {
            System.out.println("Mail not configured...");
            return;
        }

        try {
            System.out.println("Start sending email...");

            Session session = getSession(properties, user, password);
            session.setDebug(true);

            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("ART report");

            Multipart multipart = new MimeMultipart();

            BodyPart messageBody = new MimeBodyPart();
            messageBody.setText("Hello, File generated at " + new Date());

            multipart.addBodyPart(messageBody);

            MimeBodyPart attachmentpart = new MimeBodyPart();
            attachmentpart.attachFile(file);
            multipart.addBodyPart(attachmentpart);
            System.out.println("File attached..");

            message.setContent(multipart);

            System.out.println("Sending email...");
            //Send
            Transport.send(message);
            System.out.println("Email Sent");
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
            throw new PublisherException("Unable to send email", e);
        }
    }

    private Session getSession(Properties properties, String user, String password) {
        return Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });
    }
}
