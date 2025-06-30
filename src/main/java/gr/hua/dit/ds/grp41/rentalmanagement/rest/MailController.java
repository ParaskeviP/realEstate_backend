package gr.hua.dit.ds.grp41.rentalmanagement.rest;

import gr.hua.dit.ds.grp41.rentalmanagement.services.MailService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class MailController {

    private final MailService mailService;

    public MailController(MailService emailService){
        this.mailService = emailService;
    }

    @RequestMapping("/send-mail")
    public void sendMail(String to, String subject, String context){
        mailService.sendMail(to, subject, context);
    }
}
