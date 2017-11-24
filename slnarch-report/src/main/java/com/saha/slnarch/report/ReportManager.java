package com.saha.slnarch.report;

import static com.saha.slnarch.common.file.FileHelper.deleteAndCreateDirectory;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.saha.slnarch.common.file.FileHelper;
import java.io.File;
import java.io.IOException;

public class ReportManager {

  private static ReportManager instance;
  private final String REPORT_DIR = "reports";
  private final String REPORT_FILE = "slnarchReport.html";
  private ExtentReports extentReport;
  private ExtentTest extentTest;

  public static ReportManager getInstance() {
    synchronized (ReportManager.class) {
      if (instance == null) {
        instance = new ReportManager();
      }
    }
    return instance;
  }


  public ReportManager() {
    if (extentReport == null) {
      try {
        deleteAndCreateDirectory(REPORT_DIR);
      } catch (IOException e) {
        e.printStackTrace();
      }
      createExtentReport(REPORT_DIR + "/" + REPORT_FILE);
    }

  }


  private ExtentReports createExtentReport(String fileName) {
    ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
    htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
    htmlReporter.config().setChartVisibilityOnOpen(true);
    htmlReporter.config().setTheme(Theme.STANDARD);
    htmlReporter.config().setDocumentTitle(fileName);
    htmlReporter.config().setEncoding("utf-8");
    htmlReporter.config().setReportName(fileName);

    extentReport = new ExtentReports();
    extentReport.attachReporter(htmlReporter);

    return extentReport;
  }

  public void closeReport() {
    extentReport.flush();
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
    String filePath = String.format("%s/%s", REPORT_DIR, file.getName());
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

  public String getReportDir() {
    return REPORT_DIR;
  }

}
