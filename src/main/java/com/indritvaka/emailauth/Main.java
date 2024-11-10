package com.indritvaka.emailauth;

import jakarta.mail.*;
import jakarta.mail.internet.*;

import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final String SMTP_SERVER = "smtp.gmail.com"; // Replace with your SMTP server
    private static final String USERNAME = "emailHere"; // Replace with your email
    private static final String PASSWORD = "your passowrd"; // Replace with your email password
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        String email = getUserEmail();
        // Step 1: Generate a random 6-digit code
        int code = generateVerificationCode();
        // Step 2: Send the code to the user's email
        sendVerificationEmail(email, code);

        // Step 3: Prompt the user to enter the code
        int userCode = getUserCode();

        // Step 4: Verify the code
        if (userCode == code) {
            System.out.println("Authentication successful!");
        } else {
            System.out.println("Invalid code. Authentication failed.");
        }
    }

    private static int getUserCode() {
        System.out.print("Enter the verification code sent to your email: ");
        return Main.scanner.nextInt();
    }

    public static String getUserEmail() {
        String email;
        while (true) {
            System.out.print("Enter your email: ");
            email = scanner.nextLine();
            if (email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                break;
            } else {
                System.out.println("Invalid email format. Please try again.");
            }
        }
        return email;
    }
    // Generate a random 6-digit verification code
    private static int generateVerificationCode() {
        Random random = new Random();
        return 100000 + random.nextInt(900000);
    }
    // Send an email with the verification code
    private static void sendVerificationEmail(String toEmail, int code) {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", SMTP_SERVER);
        prop.put("mail.smtp.port", "587");

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Your Verification Code");
            message.setText("Your verification code is: " + code);

            Transport.send(message);
            System.out.println("Verification email sent successfully!");

        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Failed to send email. Exiting...");
            System.exit(1);
        }
    }
}