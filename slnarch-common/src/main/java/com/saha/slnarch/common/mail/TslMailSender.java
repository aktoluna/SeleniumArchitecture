package com.saha.slnarch.common.mail;

import java.nio.charset.Charset;
import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class TslMailSender extends BaseMailSender {

  public TslMailSender(String host, String userName, String password, int port, boolean auth) {
    super(host, userName, password, port, auth);
  }

  @Override
  protected Properties createProperties() {
    Properties props = new Properties();
    props.put("mail.smtp.host", host);
    props.put("mail.smtp.auth", auth);
    props.put("mail.smtp.port", port);
    props.put("mail.smtp.starttls.enable", true);
    return props;
  }


}
