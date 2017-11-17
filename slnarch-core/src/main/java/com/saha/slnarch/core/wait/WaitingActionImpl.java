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
      waitUntil(expectation);
    } catch (Throwable error) {
      log.error("Page Wait Exception");
    }
    return this;
  }

  @Override
  public WaitingAction waitForAngularLoad() {
    ExpectedCondition<Boolean> expectation = driver -> javaScriptOperation.executeJS(
        "return angular.element(document).injector().getElement('$http').pendingRequests.length === 0",
        true).toString().equals("true");
    try {
      waitUntil(expectation);
    } catch (Throwable error) {
      log.error("Angular Wait Exception");
    }
    return this;
  }

  @Override
  public WaitingAction waitJQueryComplete() {
    ExpectedCondition<Boolean> expectation = driver -> javaScriptOperation.executeJS(
        "return jQuery.active",
        true).toString().equals("0");
    try {
      javaScriptOperation.executeJS("window.jQuery");
      waitUntil(expectation);
    } catch (Exception e) {
      log.error("Jquery Wait Exception");
    }
    return this;
  }

  @Override
  public void waitUntil(ExpectedCondition expectedCondition) {
    driverWait.until(expectedCondition);
  }

  @Override
  public void waitAll() {
    waitPageLoadComplete().waitAjaxComplete().waitJQueryComplete().waitForAngularLoad();
  }

  @Override
  public void waitByMs(long millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      log.error("Sleep Exception", e);
    }
  }
}
