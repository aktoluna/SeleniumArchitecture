package com.saha.slnarch.demo;

import com.saha.slnarch.core.driver.DriverAction;
import com.saha.slnarch.core.element.ElementAction;
import com.saha.slnarch.di.page.InjectablePage;
import javax.inject.Inject;
import org.openqa.selenium.Keys;

public class HomePage extends InjectablePage {

  @Inject
  DriverAction driverAction;
  @Inject
  ElementAction elementAction;

  public void pageTest() {
    driverAction.navigateTo("https://www.google.com.tr");
    elementAction.findElement("#lst-ib")
        .sendKeys("ali");
    elementAction.findElement("#lst-ib")
        .sendKeys(Keys.ENTER);
  }

}
