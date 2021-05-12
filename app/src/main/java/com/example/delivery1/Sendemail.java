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
            final String messageToSend;
            if (receiveremial == "hw3g20@soton.ac.uk"){
                messageToSend = " Dear Haoyu :\n    You have been allocated a new delivery job,Please check!! ";
            }
            else{
                messageToSend = " Dear driver :\n    You have been registered to our delivery service, pleas log in and fill your info!! ";
            }
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
            }catch (MessagingException e){
                throw new RuntimeException(e);
            }

    }


}
