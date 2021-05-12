package com.example.delivery1;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Sendemail {

    public Sendemail(){}

    public void Sendemail(String receiveremial){
            final String username = "deliverysoton@gmail.com";
            final String password = "Ybzfy55330...";
            String messageToSend = " Dear driver :\n    You have been allocated a new delivery job,Please check!! ";
            Properties props = new Properties();
            props.put("mail.smtp.auth","true");
            props.put("mail.smtp.starttls.enable","true");
            props.put("mail.smtp.host","smtp.gmail.com");
            props.put("mail.smtp.port","587");
            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator(){
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username,password);
                        }
                    });
            try{
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username));
                message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(receiveremial.toString()));
                message.setSubject("A New delivery job, Please check!! ");
                message.setText(messageToSend);
                Transport.send(message);
//                Toast.makeText(getApplicationContext(),"emial send successfully",Toast.LENGTH_LONG).show();

            }catch (MessagingException e){
                throw new RuntimeException(e);
            }

//            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//            StrictMode.setThreadPolicy(policy);
    }


}
