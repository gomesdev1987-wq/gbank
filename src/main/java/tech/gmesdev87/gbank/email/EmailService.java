package tech.gmesdev87.gbank.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    // ✅ E-mail simples (texto)
    public void enviarSimples(String de, String para, String assunto, String mensagem) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom(de);
        email.setTo(para);
        email.setSubject(assunto);
        email.setText(mensagem);
        mailSender.send(email);
    }

    // ✅ E-mail com HTML
    public void enviarHtml(String de, String para, String assunto, String conteudoHtml) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        helper.setFrom(de);
        helper.setTo(para);
        helper.setSubject(assunto);
        helper.setText(conteudoHtml, true); // true = habilita HTML
        mailSender.send(mimeMessage);
    }
}
