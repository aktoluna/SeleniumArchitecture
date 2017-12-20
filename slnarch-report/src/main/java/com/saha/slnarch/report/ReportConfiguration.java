package com.saha.slnarch.report;

import com.saha.slnarch.common.helper.Prop;
import com.saha.slnarch.common.helper.Prop.PropType;

public class ReportConfiguration {

  @Prop(key = "reportDirectory")
  private
  String reportDirectory;
  @Prop(key = "reportFileName")
  private
  String reportFileName;
  @Prop(key = "screenShotPrefix")
  private
  String screenShotPrefix;
  @Prop(key = "sendEmail", type = PropType.BOOL)
  private
  boolean sendEmail;
  @Prop(key = "beforeDeleteEachTestResult", type = PropType.BOOL)
  private
  boolean beforeDeleteEachTestResult;
  @Prop(key = "afterDeleteEachTestResult", type = PropType.BOOL)
  private
  boolean afterDeleteEachTestResult;
  @Prop(key = "deleteZipEachTestResult", type = PropType.BOOL)
  private
  boolean deleteZipEachTestResult;
  @Prop(key = "appendExistingReport", type = PropType.BOOL)
  private
  boolean appendExistingReport;
  @Prop(key = "host")
  private
  String host;
  @Prop(key = "port", type = PropType.INT)
  private
  int port;
  @Prop(key = "username")
  private
  String username;
  @Prop(key = "password")
  private
  String password;
  @Prop(key = "auth", type = PropType.BOOL)
  private
  boolean auth;
  @Prop(key = "mailType")
  private
  String mailType;
  @Prop(key = "from")
  private
  String from;
  @Prop(key = "to")
  private
  String to;
  @Prop(key = "cc")
  private
  String cc;
  @Prop(key = "bcc")
  private
  String bcc;
  @Prop(key = "uploadDrive", type = PropType.BOOL)
  private boolean uploadDrive;


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

  public boolean isBeforeDeleteEachTestResult() {
    return beforeDeleteEachTestResult;
  }

  public void setBeforeDeleteEachTestResult(boolean beforeDeleteEachTestResult) {
    this.beforeDeleteEachTestResult = beforeDeleteEachTestResult;
  }

  public boolean isAfterDeleteEachTestResult() {
    return afterDeleteEachTestResult;
  }

  public void setAfterDeleteEachTestResult(boolean afterDeleteEachTestResult) {
    this.afterDeleteEachTestResult = afterDeleteEachTestResult;
  }

  public boolean isDeleteZipEachTestResult() {
    return deleteZipEachTestResult;
  }

  public void setDeleteZipEachTestResult(boolean deleteZipEachTestResult) {
    this.deleteZipEachTestResult = deleteZipEachTestResult;
  }

  public boolean isAppendExistingReport() {
    return appendExistingReport;
  }

  public void setAppendExistingReport(boolean appendExistingReport) {
    this.appendExistingReport = appendExistingReport;
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

  public boolean isUploadDrive() {
    return uploadDrive;
  }
}
