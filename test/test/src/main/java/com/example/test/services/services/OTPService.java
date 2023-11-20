package com.example.test.services.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.example.test.entities.User;
import com.example.test.repositories.UserRepository;

import java.util.Random;

// import org.springframework.mail.MailException;
// import org.springframework.mail.MailMessage;
// import javax.mail.MessagingException;
// import javax.mail.internet.MimeMessage;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.*;
import java.time.temporal.Temporal;

@Component
public class OTPService {
  @Autowired
  public JavaMailSender javaMailSender;

  @Autowired
  public UserRepository userRepository;

  @Autowired
  public UserActivityService userActivityService;


  public void emailOTP(String email, String otp) throws MessagingException {
    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
    MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
    mimeMessageHelper.setTo(email);
    mimeMessageHelper.setSubject("OTP Verification Account");
    mimeMessageHelper.setText("""
        <div>
          <a href="http://localhost:9098/user/verify/OTP?email=%s&otp=%s" target="_blank">click link to verify</a>
        </div>
        """.formatted(email, otp), true);

    javaMailSender.send(mimeMessage);
  }


  public void emailPayment(String email, String otp, BigDecimal amount, String cardId) throws MessagingException {
    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
    MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
    mimeMessageHelper.setTo(email);
    mimeMessageHelper.setSubject("OTP Verification Amount - " + cardId);
    mimeMessageHelper.setText("""
        <div>
          <a href="http://localhost:9098/user/verify/Amount?email=%s&otp=%s" target="_blank">click link to verify</a>
        </div>
        """.formatted(email, otp), true);

    javaMailSender.send(mimeMessage);
  }

  public String createOTP() {
    Random random = new Random();
    int randomNumber = random.nextInt(123456);
    String otp = Integer.toString(randomNumber);
    while (otp.length() < 6) {
      otp = "0" + otp;
    }
    return otp;
  }

  @Transactional
  public String otpRecreate(String email) {
    String otp = createOTP();
    User user = userRepository.findUserByEmail(email);
    if (user == null) {
      return "user not found";
    }

    try {
      emailOTP(email, otp);
    } catch (MessagingException e) {
      throw new RuntimeException("cannot send otp");
    }

    user.otp = otp;
    user.otpCreated = new Timestamp(System.currentTimeMillis());
    userRepository.save(user);

    userActivityService.addActivity(user.id, "OTP RECREATE", "user recreate OTP");

    return "success, please verify account in your email in a minutes";
  }

  @Transactional
  public String verifyOTP(String email, String otp) {
    User user = userRepository.findUserByEmail(email);
    if (user == null) {
      return "user not found";
    }

    if (user.otp.equals(otp) && Duration.between((Temporal) user.otpCreated, LocalDateTime.now()).getSeconds() < 60) {
      user.status = "ACTIVE";
      userRepository.save(user);
      return "success, please login";
    }

    userActivityService.addActivity(user.id, "VERIFY OTP", "user verify OTP");

    return "create OTP again";
  }

}
