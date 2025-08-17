package com.automa.notifier;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class AutomatedGameNotifier {

    private static final String USERNAME = "your_email@gmail.com";
    private static final String PASSWORD = "your_password";
    private static final String RECIPIENT = "recipient_email@example.com";
    private static final String GAME_NAME = "Epic Quest";
    private static final String GAME_STATUS = "New Prototype Released!";

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(USERNAME, PASSWORD);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(RECIPIENT));
            message.setSubject("New Prototype Released: " + GAME_NAME);
            message.setText("Dear Game Developer,\n"
                    + "We are excited to inform you that a new prototype for " + GAME_NAME + " is ready for testing.\n"
                    + "The current status is: " + GAME_STATUS + "\n"
                    + "Please find the prototype at: https://example.com/prototypes/" + GAME_NAME + "\n"
                    + "Best regards,\nThe Automation Team");

            Transport.send(message);

            System.out.println("Email sent successfully...");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}