package gr.hua.dit.ds.grp41.rentalmanagement.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class MailService {

    private final JavaMailSender mailSender;

    public MailService(JavaMailSender mailSender){
        this.mailSender = mailSender;
    }

    public void sendMail(String to, String subject, String context){
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("vasiliskokotakis2004@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(context);

        mailSender.send(message);
    }
}
