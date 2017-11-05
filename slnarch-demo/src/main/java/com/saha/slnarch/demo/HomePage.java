package com.saha.slnarch.demo;

import com.saha.slnarch.core.driver.DriverAction;
import com.saha.slnarch.core.element.Element;
import com.saha.slnarch.di.page.InjectablePage;
import javax.inject.Inject;
import org.openqa.selenium.Keys;

public class HomePage extends InjectablePage {

  @Inject
  DriverAction driverAction;
  @Inject
  Element elementAction;

  public HomePage() {
    super();
  }

  public void pageTest() {
    CharSequence cs = "ali";
    driverAction.navigateTo("https://www.google.com.tr");
    elementAction.findElement("#lst-ib")
        .sendKeys(cs)
        .findElement("#lst-ib")
        .sendKeys(Keys.ENTER);
  }

}
