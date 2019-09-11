package io.aktoluna.slnarch.common.mail;

import java.util.Properties;

public class SimpleMailSender extends BaseMailSender {

  public SimpleMailSender(String host, String userName, String password, int port, boolean auth) {
    super(host, userName, password, port, auth);
  }

  @Override
  protected Properties createProperties() {
    Properties props = new Properties();
    props.put("mail.smtp.host", host);
    return props;
  }
}
