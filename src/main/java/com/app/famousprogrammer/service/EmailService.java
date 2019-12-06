package com.app.famousprogrammer.service;

import com.app.famousprogrammer.dto.EmailDto;
import com.app.famousprogrammer.exceptions.MyException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Service
@RequiredArgsConstructor
@Transactional
public class EmailService {

    private final JavaMailSender javaMailSender;

    public String sendEmail(EmailDto emailDto){
        if (emailDto.getEmail() == null){
            throw new MyException("Email is null");
        }

        if (emailDto.getName() == null){
            throw new MyException("Name is null");
        }

        if (emailDto.getMessage() == null){
            throw new MyException("Message is null");
        }

        String name = emailDto.getName();
        String email = emailDto.getEmail();
        String message = emailDto.getMessage();

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo("siwy247@gmail.com");
        simpleMailMessage.setSubject("Message from " + name + " ("+ email +")");
        simpleMailMessage.setText(message);

        javaMailSender.send(simpleMailMessage);

        return "Email sent";
    }

}
