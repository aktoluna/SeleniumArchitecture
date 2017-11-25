package com.saha.slnarch.demo;

import com.saha.slnarch.report.ExtentReportTest;
import com.saha.slnarch.report.TestAuthor;
import com.saha.slnarch.report.TestCategory;
import org.junit.Test;

@TestAuthor(authors = "Ali Aktolun")
public class HomePageTest extends ExtentReportTest {


  @Test
  @TestCategory(categories = "Demo")
  public void pageTest() throws Exception {
    HomePage homePage = new HomePage();
    homePage.pageTest();
  }

}