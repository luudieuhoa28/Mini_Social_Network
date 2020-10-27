/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoald.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author dell
 */
public class Util {

    public static String getSHA256(String text) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] result = md.digest(text.getBytes());
        return new String(result);
    }

    public static String generateCode(int codeLength) {
        Random r = new Random();
        String alphabet = "1234567890abcdefghijklmnopqrstuvwxyz";
        String result = "";
        for (int i = 0; i < codeLength; i++) {
            result += alphabet.charAt(r.nextInt(alphabet.length()));
        }
        return result.toUpperCase();
    }

    public static boolean sendMail(String userMail, String messageContent) {
        String myEmail = "luudieuhoa020800@gmail.com";
        String password = "luuwdieeujhoa28";

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myEmail, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(userMail));
            message.setSubject("Verify Account");
            message.setText("Your code is: " + messageContent);
            Transport.send(message);
            return true;
        } catch (Exception e) {
            Logger.getLogger("Error at SendiMail");
        }
        return false;
    }

}
