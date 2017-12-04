package com.saha.slnarch.demo;

import com.saha.slnarch.core.element.Element;
import com.saha.slnarch.core.wait.WaitingAction;
import com.saha.slnarch.di.page.InjectablePage;
import com.saha.slnarch.report.annotation.ScreenShot;
import javax.inject.Inject;
import org.openqa.selenium.Keys;

public class HomePage extends InjectablePage {

  @Inject
  Element elementAction;
  @Inject
  WaitingAction waitingAction;

  public HomePage() {
    super();
  }

  @SuppressWarnings("unchecked")
  @ScreenShot(before = true)
  public void pageTest() {
    CharSequence cs = "ali";
    elementAction.find("#lst-ib")
        .sendKeys(cs)
        .find("#lst-ib")
        .sendKeys(Keys.ENTER);
    waitingAction.waitPageLoadComplete();
  }

}
