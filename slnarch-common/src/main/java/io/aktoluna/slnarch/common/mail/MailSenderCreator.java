package io.aktoluna.slnarch.common.mail;

public class MailSenderCreator {

  private MailSenderCreator() {

  }

  public static MailSender createMailSender(MailSendType mailSendType, String host, int port,
      String userName, String password, boolean auth) {
    MailSender mailSender = null;
    if (mailSendType == MailSendType.BASIC) {
      mailSender = new SimpleMailSender(host, userName, password, port, auth);
    } else if (mailSendType == MailSendType.SSL) {
      mailSender = new SslMailSender(host, userName, password, port, auth);
    } else if (mailSendType == MailSendType.TSL) {
      mailSender = new TslMailSender(host, userName, password, port, auth);
    }
    return mailSender;
  }


}
