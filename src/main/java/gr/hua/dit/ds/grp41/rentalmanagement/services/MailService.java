package gr.hua.dit.ds.grp41.rentalmanagement.services;

import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.InputStream;
import java.util.Properties;

@Service
public class MailService {

    private JavaMailSender mailSender = getJavaMailSender();

    SimpleMailMessage message = new SimpleMailMessage();

    public MailService(){
    }

    public void sendMail(String to, String subject, String context){
        message.setFrom("vasiliskokotakis2004@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(context);
    }

    public JavaMailSender getMailSender() {
        return mailSender;
    }

    public void sendMail(){
        mailSender.send(message);
    }

        public JavaMailSender getJavaMailSender() {
            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
            mailSender.setHost("smtp.gmail.com");
            mailSender.setPort(587);

            mailSender.setUsername("vasiliskokotakis2004@gmail.com");
            mailSender.setPassword("mmfnmhkonvaoyane");

            Properties props = mailSender.getJavaMailProperties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.debug", "true");

            return mailSender;
        }
}
