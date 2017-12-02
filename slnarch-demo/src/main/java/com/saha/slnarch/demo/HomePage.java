package com.saha.slnarch.demo;

import com.saha.slnarch.core.element.Element;
import com.saha.slnarch.core.wait.WaitingAction;
import com.saha.slnarch.report.InjectableReportPage;
import com.saha.slnarch.report.screenshot.TakeScreenShot;
import javax.inject.Inject;
import org.openqa.selenium.Keys;

public class HomePage extends InjectableReportPage {

  @Inject
  Element elementAction;
  @Inject
  WaitingAction waitingAction;

  public HomePage() {
    super();
  }

  @TakeScreenShot(before = true)
  public void pageTest() {
    CharSequence cs = "ali";
    elementAction.find("#lst-ib")
        .sendKeys(cs)
        .find("#lst-ib")
        .sendKeys(Keys.ENTER);
//    Assertions.assertThat("1").as("Bunlar Farkli").startsWith("2");
    waitingAction.waitPageLoadComplete();
  }

}
