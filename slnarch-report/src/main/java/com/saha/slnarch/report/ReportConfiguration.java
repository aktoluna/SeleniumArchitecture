package com.saha.slnarch.report;

import com.saha.slnarch.common.helper.Prop;
import com.saha.slnarch.common.helper.Prop.PropType;

public class ReportConfiguration {

  @Prop(key = "reportDirectory")
  String reportDirectory;
  @Prop(key = "reportFileName")
  String reportFileName;
  @Prop(key = "screenShotPrefix")
  String screenShotPrefix;
  @Prop(key = "sendEmail", type = PropType.BOOL)
  boolean sendEmail;
  @Prop(key = "deleteEachTestResult", type = PropType.BOOL)
  boolean deleteEachTestResult;
  @Prop(key = "deleteZipEachTestResult", type = PropType.BOOL)
  boolean deleteZipEachTestResult;
  @Prop(key = "host")
  String host;
  @Prop(key = "port", type = PropType.INT)
  int port;
  @Prop(key = "username")
  String username;
  @Prop(key = "password")
  String password;
  @Prop(key = "auth", type = PropType.BOOL)
  boolean auth;
  @Prop(key = "mailType")
  String mailType;
  @Prop(key = "from")
  String from;
  @Prop(key = "to")
  String to;
  @Prop(key = "cc")
  String cc;
  @Prop(key = "bcc")
  String bcc;

  public String getReportDirectory() {
    return reportDirectory;
  }

  public void setReportDirectory(String reportDirectory) {
    this.reportDirectory = reportDirectory;
  }

  public String getReportFileName() {
    return reportFileName;
  }

  public void setReportFileName(String reportFileName) {
    this.reportFileName = reportFileName;
  }

  public String getScreenShotPrefix() {
    return screenShotPrefix;
  }

  public void setScreenShotPrefix(String screenShotPrefix) {
    this.screenShotPrefix = screenShotPrefix;
  }

  public boolean isSendEmail() {
    return sendEmail;
  }

  public void setSendEmail(boolean sendEmail) {
    this.sendEmail = sendEmail;
  }

  public boolean isDeleteEachTestResult() {
    return deleteEachTestResult;
  }

  public void setDeleteEachTestResult(boolean deleteEachTestResult) {
    this.deleteEachTestResult = deleteEachTestResult;
  }

  public boolean isDeleteZipEachTestResult() {
    return deleteZipEachTestResult;
  }

  public void setDeleteZipEachTestResult(boolean deleteZipEachTestResult) {
    this.deleteZipEachTestResult = deleteZipEachTestResult;
  }

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public boolean isAuth() {
    return auth;
  }

  public void setAuth(boolean auth) {
    this.auth = auth;
  }

  public String getMailType() {
    return mailType;
  }

  public void setMailType(String mailType) {
    this.mailType = mailType;
  }

  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public String getTo() {
    return to;
  }

  public void setTo(String to) {
    this.to = to;
  }

  public String getCc() {
    return cc;
  }

  public void setCc(String cc) {
    this.cc = cc;
  }

  public String getBcc() {
    return bcc;
  }

  public void setBcc(String bcc) {
    this.bcc = bcc;
  }
}
