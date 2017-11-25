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
import java.util.Arrays;
import javax.mail.MessagingException;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReportManager {

  private Logger logger = LoggerFactory.getLogger(getClass());
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
      logger.warn("Report Properties Error", e);
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
    createDirectory(reportConfiguration.isBeforeDeleteEachTestResult());
    ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(getReportFilePath());
    htmlReporter.setAppendExisting(reportConfiguration.isAppendExistingReport());
    htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
    htmlReporter.config().setChartVisibilityOnOpen(true);
    htmlReporter.config().setTheme(Theme.STANDARD);
    htmlReporter.config().setDocumentTitle(getReportFileName());
    htmlReporter.config().setEncoding("utf-8");
    htmlReporter.config().setReportName(getReportFileName());
    return htmlReporter;
  }

  public void saveReport() throws MessagingException, IOException {
    extentReport.flush();
    if (reportConfiguration.isSendEmail()) {
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


  public boolean sendEmail() throws MessagingException, IOException {
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
    if (reportConfiguration.isAfterDeleteEachTestResult()) {
      FileHelper.deleteDirectory(getReportDirectory());
    }
    return result;
  }

  public void setAuthor(Description description) {
    setAuthor(extentTest, description);
  }

  public void setAuthor(ExtentTest extentTest, Description description) {
    if (!setAuthorByMethod(extentTest, description)) {
      setAuthorByClass(extentTest, description);
    }
  }

  public boolean setAuthorByMethod(Description description) {
    return setAuthorByMethod(extentTest, description);
  }

  public boolean setAuthorByMethod(ExtentTest extentTest, Description description) {
    boolean success = false;
    try {
      TestAuthor testAuthor = Arrays.stream(description.getTestClass().getMethods())
          .filter(method -> description.getMethodName().startsWith(method.getName()))
          .filter(method -> method.isAnnotationPresent(TestAuthor.class))
          .map(method -> method.getAnnotation(TestAuthor.class))
          .findFirst()
          .get();
      setAuthor(extentTest, testAuthor);
      logger.info("Author Set By Method");
      success = true;
    } catch (Exception e) {
      logger.warn("Author Annotation Method Not Found");
    }
    return success;
  }

  public void setAuthorByClass(Description description) {
    setAuthorByClass(extentTest, description);
  }

  public void setAuthorByClass(ExtentTest extentTest, Description description) {
    TestAuthor testAuthor;
    if (description.getTestClass().isAnnotationPresent(TestAuthor.class)) {
      testAuthor = description.getTestClass()
          .getAnnotation(TestAuthor.class);
      setAuthor(extentTest, testAuthor);
      logger.info("Author Set By Class");
    }
  }

  private void setAuthor(ExtentTest extentTest, TestAuthor testAuthor) {
    extentTest.assignAuthor(testAuthor.authors());
  }


  public void setCategory(Description description) {
    setCategory(extentTest, description);
  }

  public void setCategory(ExtentTest extentTest, Description description) {
    if (!setCategoryByMethod(extentTest, description)) {
      setCategoryByClass(extentTest, description);
    }
  }

  public boolean setCategoryByMethod(Description description) {
    return setCategoryByMethod(extentTest, description);
  }

  public boolean setCategoryByMethod(ExtentTest extentTest, Description description) {
    boolean success = false;
//    int index = description.getMethodName().indexOf('[');
//    String methodName = index > -1 ? description.getMethodName().substring(0, index)
//        : description.getMethodName();
//    logger.info("Category Method Name={}", methodName);
    try {
      TestCategory testCategory = Arrays.stream(description.getTestClass().getMethods())
//          .filter(method1 -> method1.getName().equals(methodName))
          .filter(method -> description.getMethodName().startsWith(method.getName()))
          .filter(method1 -> method1.isAnnotationPresent(TestCategory.class))
          .map(method1 -> method1.getAnnotation(TestCategory.class))
          .findFirst()
          .get();
      setCategory(extentTest, testCategory);
      logger.info("Category Set By Method");
      success = true;
    } catch (Exception e) {
      logger.warn("Category Annotation Method Not Found");
    }
    return success;
  }

  public void setCategoryByClass(Description description) {
    setCategoryByClass(extentTest, description);
  }

  public void setCategoryByClass(ExtentTest extentTest, Description description) {
    TestCategory testCategory;
    if (description.getTestClass().isAnnotationPresent(TestCategory.class)) {
      testCategory = description.getTestClass().getAnnotation(TestCategory.class);
      setCategory(extentTest, testCategory);
      logger.info("Category Set By Class");
    }
  }

  private void setCategory(ExtentTest extentTest, TestCategory testCategory) {
    extentTest.assignCategory(testCategory.categories());
  }


}
