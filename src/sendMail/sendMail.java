package sendMail;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class sendMail {

    public static void main(String[] args){

        String to = "MyCrowSawftBurner@gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.host","smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected  PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication("MyCrowSawftBurner@gmail.com", "CS3250TEAM4");
            }
        });



        try{
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(("MyCrowSawftBurner@gmail.com")));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            message.setSubject("Hello");
            message.setText("Testinngngg");

            Transport.send(message);

            System.out.println("Message sent");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }


}
