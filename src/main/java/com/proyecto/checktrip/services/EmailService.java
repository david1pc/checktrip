package com.proyecto.checktrip.services;

import com.proyecto.checktrip.exceptions.JavaMailError;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EmailService {
    private final JavaMailSender javaMailSender;

    public Boolean enviarCorreo(String asunto, String contenido, String destinatario){
        MimeMessage mensaje = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mensaje);
        try{
            helper.setSubject(asunto);
            helper.setText(contenido, true);
            helper.setTo(destinatario);
            helper.setFrom("no_reply@checktrip.com");
            javaMailSender.send(mensaje);
            return true;
        }catch(Exception e){
            throw new JavaMailError("Ha ocurrido un error durante el envio del correo.");
        }
    }
}
