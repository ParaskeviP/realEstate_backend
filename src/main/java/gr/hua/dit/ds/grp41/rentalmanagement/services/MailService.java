package gr.hua.dit.ds.grp41.rentalmanagement.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    private final JavaMailSender mailSender;

    public MailService(JavaMailSender mailSender){
        this.mailSender = mailSender;
    }

    public void sendMail(String to, String subject, String context){
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("it2022040@hua.gr");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(context);

        mailSender.send(message);
    }
}
