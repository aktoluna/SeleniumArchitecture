package com.saha.slnarch.demo;

import com.saha.slnarch.junit.JunitPageTestImpl;
import com.saha.slnarch.report.annotation.TestAuthor;
import com.saha.slnarch.report.annotation.TestCategory;
import org.junit.Test;

@TestAuthor(authors = "Ali Aktolun")
public class HomePageTest extends JunitPageTestImpl {

  @Test
  @TestCategory(categories = "Demo")
  public void pageTest() {
//    HomePage homePage = new HomePage();
//    homePage.pageTest();
  }

}