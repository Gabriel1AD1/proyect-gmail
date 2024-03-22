package com.gmail.backcaso05.service;

import com.gmail.backcaso05.dto.Correo;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender emailSender;

    public String message(String name){
        return """
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Correo Electrónico HTML</title>
                </head>
                <body>
                    <div style="background-color: #f2f2f2; padding: 20px;">
                        <h2 style="color: #333;">¡Bienvenido a nuestro boletín informativo!</h2>
                        <p style="color: #666;">Estimado/a %s,</p>
                        <p style="color: #666;">Gracias por suscribirte a nuestro boletín informativo. Aquí tienes algunas noticias recientes:</p>
                        <ul style="color: #666;">
                            <li>Actualización sobre nuestro último producto.</li>
                            <li>Eventos próximos y promociones especiales.</li>
                            <li>Consejos y trucos para maximizar el uso de nuestros productos.</li>
                        </ul>
                        <p style="color: #666;">¡Esperamos que encuentres útiles nuestras actualizaciones! No dudes en contactarnos si tienes alguna pregunta.</p>
                        <p style="color: #666;">Saludos cordiales,</p>
                        <p style="color: #666;">Gabriel AD cerroteberes@gmail.com</p>
                    </div>
                </body>
                </html>
                """.formatted(name);

    }



    public String SendEmail(Correo correo){
        try {
            MimeMessage mimeMessage = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setTo("cerroteberes@gmail.com");
            helper.setSubject(correo.getEmail());
            String contenidoHtml = "<html><body><h2>Nombre: " + correo.getName() + "</h2><p>Mi mensaje para ti es: " + correo.getMessage() + "</p></body></html>";
            helper.setText(contenidoHtml,true);
            emailSender.send(mimeMessage);


            MimeMessage mimeMessage1 = emailSender.createMimeMessage();
            MimeMessageHelper helper2 = new MimeMessageHelper(mimeMessage1, true);
            helper2.setTo(correo.getEmail());
            helper2.setSubject("INFORMACION SOBRE NUESTROS PRODUCTOS");
            helper2.setText( message(correo.getName()),true);

            emailSender.send(mimeMessage1);
            return "Correo enviado correctamente a " + correo.getEmail();
        } catch (MessagingException e) {
            e.printStackTrace();
            return "Error al enviar el correo electrónico";
        }
    }

}
