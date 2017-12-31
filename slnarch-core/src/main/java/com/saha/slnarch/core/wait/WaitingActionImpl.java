package com.saha.slnarch.core.wait;

import com.saha.slnarch.core.js.JavaScriptOperation;
import javax.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class WaitingActionImpl implements WaitingAction<WaitingAction> {

  private final Logger log = LoggerFactory.getLogger(WaitingActionImpl.class);
  private static final long SLEEP_MILLIS = 250;

  JavaScriptOperation javaScriptOperation;
  WebDriverWait driverWait;
  WebDriverWait frameWait;

  @Inject
  public WaitingActionImpl(WebDriverWait driverWait, WebDriverWait frameWait,
      JavaScriptOperation javaScriptOperation) {
    this.javaScriptOperation = javaScriptOperation;
    this.driverWait = driverWait;
    this.frameWait = frameWait;
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
  public WaitingAction waitPageScrollingComplete() {
    ExpectedCondition<Boolean> expectedCondition = driver -> {
      long currentPosition = (long) javaScriptOperation
          .executeJS("var currentPosition=window.pageYOffset;");
      waitByMs(150);
      return javaScriptOperation
          .executeJS(
              "return Math.abs(arguments[0]-window.pageYOffset) == 0; ", currentPosition)
          .toString()
          .equals("true");
    };
    try {
      waitUntil(expectedCondition);
    } catch (Exception e) {
      log.error("Page Scroll Complete Exception", e);
    }
    return this;
  }

  @Override
  public WaitingAction stopPageLoad() {
    javaScriptOperation.executeJS("window.stop();");
    return this;
  }

  @Override
  public WaitingAction switchToFrame(String frameName) {
    waitUntilByFrame(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameName));
    return this;
  }

  @Override
  public WaitingAction switchToFrameElement(By byFrameName) {
    waitUntilByFrame(ExpectedConditions.frameToBeAvailableAndSwitchToIt(byFrameName));
    return this;
  }

  @Override
  public WaitingAction switchToFrameElement(WebElement element) {
    waitUntilByFrame(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
    return this;
  }

  @Override
  public <V> V waitUntil(ExpectedCondition expectedCondition) {
    return (V) driverWait.until(expectedCondition);
  }

  @Override
  public <V> V waitUntilByFrame(ExpectedCondition expectedCondition) {
    return (V) frameWait.until(expectedCondition);
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
