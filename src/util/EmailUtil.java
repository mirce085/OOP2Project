package util;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
public class EmailUtil {
    public static void sendEmail(String to, String subject, String body) {
        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.example.com");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("user@example.com", "password");
                }
            });
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("noreply@example.com"));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            msg.setSubject(subject);
            msg.setText(body);
            Transport.send(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
