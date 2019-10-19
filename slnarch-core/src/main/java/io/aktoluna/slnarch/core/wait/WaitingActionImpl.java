package io.aktoluna.slnarch.core.wait;

import io.aktoluna.slnarch.common.log.LogHelper;
import io.aktoluna.slnarch.core.js.JavaScriptOperation;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;

public final class WaitingActionImpl implements WaitingAction<WaitingAction> {

  private final Logger log = LogHelper.getSlnLogger();
  private static final long SLEEP_MILLIS = 250;

  JavaScriptOperation javaScriptOperation;
  FluentWait<WebDriver> driverWait;
  WebDriverWait frameWait;

  @Inject
  public WaitingActionImpl(FluentWait<WebDriver> driverWait, WebDriverWait frameWait,
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
    try {
      driverWait.until(
          driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState")
              .toString()
              .equals("complete"));
    } catch (Throwable error) {
      log.error("Page Wait Exception", error);
    }
    return this;
  }

  @Override
  public WaitingAction waitForAngularLoad() {
    Boolean existAngular = false;
    try {
      existAngular = (Boolean) javaScriptOperation
          .executeJS("return (typeof(angular) != 'undefined')");
    } catch (Throwable e) {
      log.error("Angular Bool Wait Exception", e);
    }
    if (existAngular) {
      try {
        driverWait.until(driver -> ((Boolean) ((JavascriptExecutor) driver).executeScript(
            "return angular.element(document).injector().get('$http').pendingRequests.length === 0")));
      } catch (Throwable error) {
        log.error("Angular Wait Exception", error);
      }
    }
    return this;
  }

  @Override
  public WaitingAction waitJQueryComplete() {
    Boolean existJquery = false;
    try {
      existJquery = (Boolean) javaScriptOperation
          .executeJS("return (typeof(jQuery) != 'undefined')");
    } catch (Throwable error) {
      log.error("Jquery Boolean Wait Exception", error);
    }
    if (existJquery) {
      try {
        driverWait.until(driver -> (Boolean) ((JavascriptExecutor) driver)
            .executeScript("return jQuery.active == 0"));
      } catch (Throwable e) {
        log.error("Jquery Wait Exception", e);
      }
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

  @Override
  public <S, V> S expected(ExpectedCondition<V> expectedCondition) {
    S s = null;
    try {
      log.debug("{}", expectedCondition.toString());
      s = waitUntil(expectedCondition);
    } catch (Exception e) {
      log.error("Expected Condition={}", expectedCondition.toString(), e);
    }
    return s;
  }

  @Override
  public <S, V> S expected(ExpectedCondition<V>... expectedConditions) {
    S result = null;
    try {
      for (ExpectedCondition<V> expectedCondition : expectedConditions) {
        result = expected(expectedCondition);
      }
    } catch (Exception e) {
      log.error("Expected Conditions={}",
          Arrays.stream(expectedConditions).map(Object::toString).collect(
              Collectors.toList()), e);
    }
    return result;
  }

  @Override
  public <S, V> List<S> expects(ExpectedCondition<List<V>> expectedCondition) {
    List<S> sList = null;
    try {
      log.debug("{}", expectedCondition.toString());
      sList = waitUntil(expectedCondition);
    } catch (Exception e) {
      log.error("Expected Conditions={}", expectedCondition.toString(), e);
    }
    return sList;
  }

  @Override
  public <S, V> List<S> expects(ExpectedCondition<List<V>>... expectedConditions) {
    List<S> sList = null;
    try {
      for (ExpectedCondition<List<V>> expectedCondition : expectedConditions) {
        sList = expects(expectedCondition);
      }
    } catch (Exception e) {
      log.error("Expected Conditions={}",
          Arrays.stream(expectedConditions).map(Object::toString).collect(
              Collectors.toList()), e);
    }

    return sList;
  }

  @Override
  public <S> boolean expectedByBoolean(ExpectedCondition<S> expectedCondition) {
    boolean result = false;
    try {
      log.debug("{}", expectedCondition.toString());
      result = waitUntil(expectedCondition);
    } catch (Exception e) {
      log.error("Expected Not Success Conditions={}", expectedCondition.toString(), e);
    }
    return result;
  }
}
