package io.aktoluna.slnarch.core.listener;

import io.aktoluna.slnarch.core.element.JavaScriptAction;
import io.aktoluna.slnarch.core.js.JavaScriptOperation;
import io.aktoluna.slnarch.core.model.Configuration;
import io.aktoluna.slnarch.core.wait.WaitingAction;
import javax.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

public class WaitEventListener extends BaseListener implements WebDriverEventListener {

  private WaitingAction waitingAction;

  private JavaScriptAction javaScriptAction;

  private Configuration configuration;

  private JavaScriptOperation javaScriptOperation;

  @Inject
  public WaitEventListener(WaitingAction waitingAction,
      JavaScriptAction javaScriptAction, JavaScriptOperation javaScriptOperation) {
    this.waitingAction = waitingAction;
    this.javaScriptAction = javaScriptAction;
    this.javaScriptOperation = javaScriptOperation;
  }

  public void setConfiguration(Configuration configuration) {
    this.configuration = configuration;
    logWaitSelected();
  }

  @Override
  public void beforeAlertAccept(WebDriver webDriver) {

  }

  @Override
  public void afterAlertAccept(WebDriver webDriver) {
    logger.info("Alert Popup Accept");
    waitRequest();
  }

  @Override
  public void afterAlertDismiss(WebDriver webDriver) {
    logger.info("Alert Popup Dismiss");
    waitRequest();
  }

  @Override
  public void beforeAlertDismiss(WebDriver webDriver) {

  }

  @Override
  public void beforeNavigateTo(String s, WebDriver webDriver) {

  }

  @Override
  public void afterNavigateTo(String s, WebDriver webDriver) {
    logger.info("Navigate To Page Url={}", s);
    waitRequest();
  }

  @Override
  public void beforeNavigateBack(WebDriver webDriver) {

  }

  @Override
  public void afterNavigateBack(WebDriver webDriver) {
    logger.info("Navigate Back Page Url={}", webDriver.getCurrentUrl());
  }

  @Override
  public void beforeNavigateForward(WebDriver webDriver) {
  }

  @Override
  public void afterNavigateForward(WebDriver webDriver) {
    logger.info("Navigate Forward Page Url={}", webDriver.getCurrentUrl());
  }

  @Override
  public void beforeNavigateRefresh(WebDriver webDriver) {
    logger.info("Refresh Page Url={}", webDriver.getCurrentUrl());
    waitRequest();
  }

  @Override
  public void afterNavigateRefresh(WebDriver webDriver) {
    logger.info("Refresh Page Url={}", webDriver.getCurrentUrl());
    waitRequest();
  }

  @Override
  public void beforeFindBy(By by, WebElement webElement, WebDriver webDriver) {
    logger.info("Finding Element  {}", by.toString());
    waitRequest();
  }

  @Override
  public void afterFindBy(By by, WebElement webElement, WebDriver webDriver) {
    if (webElement != null) {
      logger
          .info("Find Element Success ByName={} TagName={}", by.toString(),
              webElement.getTagName());
      scroll(webElement);
    }
  }

  @Override
  public void beforeClickOn(WebElement webElement, WebDriver webDriver) {
    scroll(webElement);
    changeElementBorder(webElement);
  }

  @Override
  public void afterClickOn(WebElement webElement, WebDriver webDriver) {
    waitRequest();
  }

  @Override
  public void beforeChangeValueOf(WebElement webElement, WebDriver webDriver,
      CharSequence[] charSequences) {
    scroll(webElement);
    changeElementBorder(webElement);
    waitingAction.waitByMs(150);
    if (charSequences != null) {
      logger.info("Set Value {}", charSequences.toString());
    }
  }

  @Override
  public void afterChangeValueOf(WebElement webElement, WebDriver webDriver,
      CharSequence[] charSequences) {
    waitRequest();
  }

  @Override
  public void beforeScript(String s, WebDriver webDriver) {
  }

  @Override
  public void afterScript(String s, WebDriver webDriver) {
  }

  @Override public void beforeSwitchToWindow(String windowName, WebDriver driver) {

  }

  @Override public void afterSwitchToWindow(String windowName, WebDriver driver) {
    waitRequest();
  }

  @Override
  public void onException(Throwable throwable, WebDriver webDriver) {

  }

  @Override public <X> void beforeGetScreenshotAs(OutputType<X> target) {

  }

  @Override public <X> void afterGetScreenshotAs(OutputType<X> target, X screenshot) {

  }

  @Override public void beforeGetText(WebElement element, WebDriver driver) {
    waitRequest();
  }

  @Override public void afterGetText(WebElement element, WebDriver driver, String text) {

  }

  private void waitRequest() {
    if (configuration.isWaitPageLoad()) {
      waitingAction.waitPageLoadComplete();
    }
    if (configuration.isWaitAjax()) {
      waitingAction.waitJQueryComplete();
    }
    if (configuration.isWaitAngular()) {
      waitingAction.waitForAngularLoad();
    }
  }

  private void logWaitSelected() {
    logger.info("Wait Configuration\npage load={}\najax load={}\nangular load={}",
        configuration.isWaitPageLoad(),
        configuration.isWaitAjax(),
        configuration.isWaitAngular());
  }

  private void scroll(WebElement element) {
    try {
      javaScriptAction.scrollIntoView(element);
    } catch (Exception e) {
      logger.warn("Scroll Failed ", e);
    }
  }

  private void changeElementBorder(WebElement element) {
    javaScriptOperation.executeJS(
        "arguments[0].setAttribute('style', 'border: 2px solid red;');", element);
  }
}
