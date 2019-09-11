package io.aktoluna.slnarch.common.mail;

import java.util.Properties;

public class SslMailSender extends BaseMailSender {


  public SslMailSender(String host, String userName, String password, int port, boolean auth) {
    super(host, userName, password, port, auth);
  }

  @Override
  protected Properties createProperties() {
    Properties props = new Properties();
    props.put("mail.smtp.host", host);
    props.put("mail.smtp.socketFactory.port", port);
    props.put("mail.smtp.socketFactory.class",
        "javax.net.ssl.SSLSocketFactory");
    props.put("mail.smtp.auth", auth);
    props.put("mail.smtp.port", port);
    return props;
  }
}
