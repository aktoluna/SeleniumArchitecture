package com.saha.slnarch.demo;

import com.saha.slnarch.junit.InjectableJunitPageTestImpl;
import org.junit.Test;

public class HomePageTest extends InjectableJunitPageTestImpl {


  @Test
  public void pageTest() throws Exception {
    HomePage homePage = new HomePage();
    homePage.pageTest();
  }

}