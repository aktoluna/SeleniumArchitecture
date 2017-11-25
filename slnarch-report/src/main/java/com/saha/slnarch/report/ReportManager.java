package com.saha.slnarch.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.saha.slnarch.common.file.FileHelper;
import com.saha.slnarch.common.helper.PropertyHelper;
import com.saha.slnarch.common.helper.ZipHelper;
import com.saha.slnarch.common.mail.MailSendType;
import com.saha.slnarch.common.mail.MailSenderCreator;
import java.io.File;
import java.io.IOException;
import javax.mail.MessagingException;

public class ReportManager {

  private static ReportManager instance;
  private String reportDirectory = "reports";
  private String reportFileName = "slnarchReport.html";
  private ExtentReports extentReport;
  private ExtentTest extentTest;
  private ReportConfiguration reportConfiguration;

  private ReportManager() {
    try {
      this.reportConfiguration = PropertyHelper
          .propertiesToClassWithAnnotation("report.properties", ReportConfiguration.class);
    } catch (IllegalAccessException | InstantiationException | IOException e) {
      e.printStackTrace();
    }
  }

  public static ReportManager getInstance() {
    synchronized (ReportManager.class) {
      if (instance == null) {
        instance = new ReportManager();
      }
    }
    return instance;
  }


  public void setReportDirectory(String reportDirectory) {
    this.reportDirectory = reportDirectory;
  }

  public String getReportDirectory() {
    return reportDirectory;
  }

  public void setReportFileName(String reportFileName) {
    this.reportFileName = reportFileName;
  }

  public String getReportFileName() {
    return reportFileName;
  }

  public String getReportFilePath() {
    return String.format("%s/%s", reportDirectory, reportFileName);
  }


  public ExtentReports createExtentReport() throws IOException {
    extentReport = new ExtentReports();
    extentReport.attachReporter(createExtentHtmlReport());
    return extentReport;
  }

  private void createDirectory(boolean delete) throws IOException {
    if (delete) {
      FileHelper.deleteDirectory(reportDirectory);
    }
    if (!FileHelper.isFileExist(reportDirectory)) {
      FileHelper.createDirectory(reportDirectory);
    }
  }

  private ExtentHtmlReporter createExtentHtmlReport() throws IOException {
    createDirectory(reportConfiguration.isDeleteEachTestResult());
    ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(getReportFilePath());
    htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
    htmlReporter.config().setChartVisibilityOnOpen(true);
    htmlReporter.config().setTheme(Theme.STANDARD);
    htmlReporter.config().setDocumentTitle(getReportFileName());
    htmlReporter.config().setEncoding("utf-8");
    htmlReporter.config().setReportName(getReportFileName());
    return htmlReporter;
  }

  public void saveReport() throws MessagingException {
    extentReport.flush();
    if (reportConfiguration.sendEmail) {
      sendEmail();
    }
  }

  public ExtentTest getExtentTest() {
    return extentTest;
  }

  public ExtentTest createNewExtentTest(String testName) {
    extentTest = extentReport.createTest(testName);
    return extentTest;
  }

  public ExtentTest createNewExtentTest(String testName, String description) {
    extentTest = extentReport.createTest(testName, description);
    return extentTest;
  }

  public void removeTest() {
    extentReport.removeTest(extentTest);
  }

  public void removeTest(ExtentTest extentTest) {
    extentReport.removeTest(extentTest);
  }

  public void setAuthor(String author) {
    extentTest.assignAuthor(author);
  }

  public void setAuthor(ExtentTest extentTest, String author) {
    extentTest.assignAuthor(author);
  }

  public void setCategory(String... category) {
    extentTest.assignCategory(category);
  }

  public void setCategory(ExtentTest extentTest, String... author) {
    extentTest.assignCategory(author);
  }

  public void info(String message) {
    extentTest.info(message);
  }

  public void info(ExtentTest extentTest, String message) {
    extentTest.info(message);
  }

  public void addScreenShot(File file) throws IOException {
    addScreenShot(file, "");
  }

  public void addScreenShot(File file, String title) throws IOException {
    addScreenShot(extentTest, file, title);
  }

  public void addScreenShot(ExtentTest extentTest, File file) throws IOException {
    addScreenShot(extentTest, file, "");
  }

  public void addScreenShot(ExtentTest extentTest, File file, String title) throws IOException {
    String filePath = String.format("%s/%s", reportDirectory, file.getName());
    FileHelper.copyFile(file, new File(filePath));
    addScreenShot(extentTest, file.getName(), title);
  }


  public void addScreenShot(String imagePath) throws IOException {
    addScreenShot(extentTest, imagePath);
  }

  public void addScreenShot(ExtentTest extentTest, String imagePath)
      throws IOException {
    extentTest.addScreenCaptureFromPath(imagePath);
  }

  public void addScreenShot(String imagePath, String title) throws IOException {
    addScreenShot(extentTest, imagePath, title);
  }

  public void addScreenShot(ExtentTest extentTest, String imagePath, String title)
      throws IOException {
    extentTest.addScreenCaptureFromPath(imagePath, title);
  }

  public String getZipFilePath() {
    return String.format("%s.zip", reportDirectory);
  }

  public File createZip() {
    ZipHelper.zipDirectory(getReportDirectory(), getZipFilePath());
    return FileHelper.toFile(getZipFilePath());
  }


  public boolean sendEmail() throws MessagingException {
    File file = createZip();
    boolean result = MailSenderCreator
        .createMailSender(MailSendType.valueOf(reportConfiguration.getMailType()),
            reportConfiguration.getHost(), reportConfiguration.getPort(),
            reportConfiguration.getUsername(),
            reportConfiguration.getPassword(), reportConfiguration.isAuth())
        .createMail(reportConfiguration.getFrom(), reportConfiguration.getTo(), "Test Result",
            reportConfiguration.getCc(), reportConfiguration.getBcc())
        .setMessage("Test Result ")
        .addAttachment(file.getPath())
        .send();

    if (reportConfiguration.isDeleteZipEachTestResult()) {
      FileHelper.deleteFile(file);
    }
    return result;
  }


}
