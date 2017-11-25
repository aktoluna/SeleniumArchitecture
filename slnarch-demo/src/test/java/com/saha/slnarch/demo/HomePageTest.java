package com.saha.slnarch.demo;

import com.saha.slnarch.report.ExtentReportTest;
import org.junit.Test;

public class HomePageTest extends ExtentReportTest {


  @Test
  public void pageTest() throws Exception {
    HomePage homePage = new HomePage();
    homePage.pageTest();
  }

}