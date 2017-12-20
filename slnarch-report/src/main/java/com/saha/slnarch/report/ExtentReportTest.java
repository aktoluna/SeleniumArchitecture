package com.saha.slnarch.report;

import ch.qos.logback.classic.PatternLayout;
import com.saha.slnarch.junit.InjectableJunitPageTestImpl;
import java.io.IOException;
import javax.mail.MessagingException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;

public abstract class ExtentReportTest extends InjectableJunitPageTestImpl {

  @Rule
  public ExtentJunitListener extentJunitListener = new ExtentJunitListener(getDriver());

  private static ReportManager reportManager;

  @BeforeClass
  public static void setUpClass()
      throws IllegalAccessException, IOException, InstantiationException {
    reportManager = ReportManager.getInstance();
    reportManager.createExtentReport();
  }

  public static ReportManager getReportManager() {
    return reportManager;
  }

  @Override
  public void afterTest() {
    //Disable after driver quit
  }

  @AfterClass
  public static void afterClass() throws MessagingException, IOException {
    reportManager.saveReport();
  }


}
