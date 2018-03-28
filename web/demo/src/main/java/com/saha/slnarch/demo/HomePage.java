package com.saha.slnarch.demo;

import com.saha.slnarch.core.element.Element;
import com.saha.slnarch.core.page.Page;
import com.saha.slnarch.core.wait.WaitingAction;
import com.saha.slnarch.report.annotation.ScreenShot;
import javax.inject.Inject;
import org.openqa.selenium.Keys;

public class HomePage extends Page {

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
//    CharSequence cs = "ali";
    elementAction.find(".btn-padre")
        .click()
        .findByClickable(".btn-hijo>ul>li:nth-of-type(3)>a")
        .click();
    try {
      Thread.sleep(10000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    waitingAction.waitPageLoadComplete();
  }

}
