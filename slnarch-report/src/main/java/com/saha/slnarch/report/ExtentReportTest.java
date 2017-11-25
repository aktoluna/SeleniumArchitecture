package com.saha.slnarch.report;

import com.saha.slnarch.junit.InjectableJunitPageTestImpl;
import java.io.IOException;
import javax.mail.MessagingException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;

public abstract class ExtentReportTest extends InjectableJunitPageTestImpl {

  @Rule
  public ExtentJunitListener extentJunitListener = new ExtentJunitListener(getDriver());

  protected static ReportManager reportManager;

  @BeforeClass
  public static void setUpClass()
      throws IllegalAccessException, IOException, InstantiationException {
    reportManager = ReportManager.getInstance();
    reportManager.createExtentReport();
  }


  @Override
  public void afterTest() {

  }

  @AfterClass
  public static void afterClass() throws MessagingException {
    reportManager.saveReport();
  }


}
