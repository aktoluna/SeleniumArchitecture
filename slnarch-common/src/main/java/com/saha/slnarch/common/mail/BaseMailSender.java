package com.saha.slnarch.common.mail;

import com.saha.slnarch.common.helper.DateAndTimeHelper;
import com.saha.slnarch.common.helper.StringHelper;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseMailSender implements MailSender {

  protected Logger logger = LoggerFactory.getLogger(getClass());
  protected final String CHARSET_UTF8 = "UTF-8";
  protected String host;
  protected String userName;
  protected String password;
  protected int port;
  protected boolean auth;

  protected MimeMessage mimeMessage;
  protected Multipart multipart;

  public BaseMailSender(String host, String userName, String password, int port, boolean auth) {
    this.host = host;
    this.userName = userName;
    this.password = password;
    this.port = port;
    this.auth = auth;
  }

  protected abstract Properties createProperties();

  protected Authenticator createAuth() {
    if (!auth) {
      return null;
    }
    return new Authenticator() {
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(userName, password);
      }
    };
  }

  protected Session createSession() {
    logger.info("Create New Session");
    return Session.getDefaultInstance(createProperties(), createAuth());
  }


  @Override
  public MailSender createMail(String from, String to, String subject) throws MessagingException {
    return createMail(from, to, subject, null, null);
  }

  @Override
  public MailSender createMail(String from, String to, String subject, String cc)
      throws MessagingException {
    return createMail(from, to, subject, cc, null);
  }

  @Override
  public MailSender createMail(String from, String to, String subject, String cc, String bcc)
      throws MessagingException {
    mimeMessage = new MimeMessage(createSession());
    mimeMessage.setFrom(from);
    mimeMessage.setSubject(subject, CHARSET_UTF8);
    mimeMessage.addHeader("Content-type", "text/HTML; charset=UTF-8");
//    mimeMessage.addHeader("format", "flowed");
//    mimeMessage.addHeader("Content-Transfer-Encoding", "8bit");
    mimeMessage.setSentDate(DateAndTimeHelper.getNowDate());
    addAddress(mimeMessage, RecipientType.TO, to);
    addAddress(mimeMessage, RecipientType.CC, cc);
    addAddress(mimeMessage, RecipientType.BCC, bcc);
    logger.info("Message Create from={} to={}", from, to);
    return this;
  }

  private void addAddress(MimeMessage mimeMessage, RecipientType recipientType, String address)
      throws MessagingException {
    InternetAddress[] addresses = parseAddress(address);
    if (addresses != null) {
      logger.info("add {} address={}", recipientType.toString(), address);
      mimeMessage.addRecipients(recipientType, addresses);
    }
  }

  private InternetAddress[] parseAddress(String address) throws AddressException {
    InternetAddress[] internetAddress = null;
    if (!StringHelper.isEmpty(address)) {
      internetAddress = InternetAddress.parse(address, false);
    }
    return internetAddress;

  }

  @Override
  public MailSender setMessage(String message) throws MessagingException {
    BodyPart bodyPart = new MimeBodyPart();
    bodyPart.setText(message);
    addBodyPart(bodyPart);
    logger.info("Body Message Add  message={}", message);
    return this;
  }

  @Override
  public MailSender addAttachment(String path) throws MessagingException {
    logger.info("Attachment File path={}", path);
    BodyPart bodyPart = new MimeBodyPart();
    DataSource dataSource = new FileDataSource(path);
    bodyPart.setFileName(dataSource.getName());
    bodyPart.setDataHandler(new DataHandler(dataSource));
    addBodyPart(bodyPart);
    logger.info("Attachment File");
    return this;
  }

  @Override
  public MailSender addAttachment(String path, String sendName) throws MessagingException {
    logger.info("Attachment File path={} sendFileName={}", path, sendName);
    BodyPart bodyPart = new MimeBodyPart();
    DataSource dataSource = new FileDataSource(path);
    bodyPart.setFileName(sendName);
    bodyPart.setDataHandler(new DataHandler(dataSource));
    addBodyPart(bodyPart);
    logger.info("Attachment File");
    return this;
  }

  @Override
  public boolean send() {
    boolean success = false;
    logger.info("Send Mail Start");
    try {
      mimeMessage.setContent(multipart);
      Transport.send(mimeMessage);
      logger.info("Mail Send Success");
      success = true;
    } catch (MessagingException e) {
      logger.warn("Mail Send Failed", e);
    } finally {
      mimeMessage = null;
      multipart = null;
    }
    return success;
  }

  private void addBodyPart(BodyPart bodyPart) throws MessagingException {
    if (multipart == null) {
      multipart = new MimeMultipart();
    }
    multipart.addBodyPart(bodyPart);
  }
}
