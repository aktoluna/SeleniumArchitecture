package com.saha.slnarch.demogauge;

import com.saha.slnarch.core.element.Element;
import com.saha.slnarch.core.wait.WaitingAction;
import com.saha.slnarch.core.di.page.InjectablePage;
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
  public void pageTest() {
    CharSequence cs = "ali";
    elementAction.find("#lst-ib")
        .sendKeys(cs)
        .find("#lst-ib")
        .sendKeys(Keys.ENTER);
    waitingAction.waitPageLoadComplete();
  }
}
