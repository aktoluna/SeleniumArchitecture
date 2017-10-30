package com.saha.slnarch.core.wait;

import com.saha.slnarch.core.js.JavaScriptOperation;
import javax.inject.Inject;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class WaitingActionImpl implements WaitingAction<WaitingAction> {

  private final Logger log = LoggerFactory.getLogger(WaitingActionImpl.class);
  private static final long SLEEP_MILLIS = 250;

  JavaScriptOperation javaScriptOperation;
  WebDriverWait driverWait;

  @Inject
  public WaitingActionImpl(WebDriverWait driverWait, JavaScriptOperation javaScriptOperation) {
    this.javaScriptOperation = javaScriptOperation;
    this.driverWait = driverWait;
  }


  @Override
  public WaitingAction waitAjaxComplete() {
    javaScriptOperation.executeJS("var callback = arguments[arguments.length - 1];"
        + "var xhr = new XMLHttpRequest();" + "xhr.open('GET', '/Ajax_call', true);"
        + "xhr.onreadystatechange = function() {" + "  if (xhr.readyState == 4) {"
        + "    callback(xhr.responseText);" + "  }" + "};" + "xhr.send();");
    return this;
  }

  @Override
  public WaitingAction waitPageLoadComplete() {
    ExpectedCondition<Boolean> expectation = driver -> javaScriptOperation
        .executeJS("return document.readyState", true).toString().equals("complete");
    try {
      waitByMs(SLEEP_MILLIS);
      driverWait.until(expectation);
    } catch (Throwable error) {
      log.error("Page Wait Exception", error);
    }
    return this;
  }

  @Override
  public WaitingAction waitForAngularLoad() {
    ExpectedCondition<Boolean> expectation = driver -> javaScriptOperation.executeJS(
        "return angular.element(document).injector().get('$http').pendingRequests.length === 0",
        true).toString().equals("true");
    try {
      waitByMs(SLEEP_MILLIS);
      driverWait.until(expectation);
    } catch (Throwable error) {
      log.error("Angular Wait Exception", error);
    }
    return this;
  }

  @Override
  public WaitingAction waitJQueryComplete() {
    try {
      javaScriptOperation.executeJS("window.jQuery");
      if (javaScriptOperation.executeJS("return jQuery.active").toString().equals("0")) {
        System.out.println("Page Is loaded.");
        return this;
      }

      for (int i = 0; i < 25; i++) {
        waitByMs(SLEEP_MILLIS);
        System.out.println("i " + i);
        if (javaScriptOperation.executeJS("return jQuery.active").toString().equals("0")) {
          break;
        }
      }
    } catch (Exception e) {
      log.error("Jquery Wait Exception", e);
    }
    return this;
  }

  @Override
  public void waitAll() {
    waitPageLoadComplete().waitAjaxComplete().waitJQueryComplete().waitForAngularLoad();
  }

  @Override
  public void waitByMs(long millis) throws NullPointerException, InterruptedException {
    Thread.sleep(millis);
  }
}
