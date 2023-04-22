package edu.miu.shoppingcartsystem.service;
// Java Program to Illustrate Creation Of

// Importing required classes
import edu.miu.shoppingcartsystem.model.EmailDetails;
import org.springframework.stereotype.Service;

@Service
// Interface
public interface EmailService {

    // Method
    // To send a simple email
    String sendSimpleMail(EmailDetails details);

    // Method
    // To send an email with attachment
    String sendMailWithAttachment(EmailDetails details);
}
