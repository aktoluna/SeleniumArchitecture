package com.saha.slnarch.common.mail;

import javax.mail.MessagingException;

public interface MailSender {

  MailSender createMail(String from, String to, String subject) throws MessagingException;

  MailSender createMail(String from, String to, String subject, String cc)
      throws MessagingException;

  MailSender createMail(String from, String to, String subject, String cc, String bcc)
      throws MessagingException;

  MailSender setMessage(String message) throws MessagingException;

  MailSender addAttachment(String path, String sendName) throws MessagingException;

  MailSender addAttachment(String path) throws MessagingException;

  boolean send();

}
