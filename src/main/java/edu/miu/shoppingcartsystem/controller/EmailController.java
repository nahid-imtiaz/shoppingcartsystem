package edu.miu.shoppingcartsystem.controller;

// Java Program to Create Rest Controller that
// Defines various API for Sending Mail


// Importing required classes
import edu.miu.shoppingcartsystem.model.EmailDetails;
import edu.miu.shoppingcartsystem.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// Annotation
@RestController
// Class
public class EmailController {

    @Autowired private EmailService emailService;

    // Sending a simple Email
    @PostMapping(value = "/sendMail", produces = MediaType.APPLICATION_JSON_VALUE)
    public String
    sendMail(@RequestBody EmailDetails details){
        String status = emailService.sendSimpleMail(details);
        return status;
    }

    // Sending email with attachment
    @PostMapping(value = "/sendMailWithAttachment", produces = MediaType.APPLICATION_JSON_VALUE)
    public String sendMailWithAttachment(@RequestBody EmailDetails details){
        String status = emailService.sendMailWithAttachment(details);
        return status;
    }
}
