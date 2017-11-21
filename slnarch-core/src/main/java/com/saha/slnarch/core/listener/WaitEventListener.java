package com.saha.slnarch.core.listener;

import com.saha.slnarch.core.element.JavaScriptAction;
import com.saha.slnarch.core.model.Configuration;
import com.saha.slnarch.core.wait.WaitingAction;
import javax.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class WaitEventListener extends BaseListener implements WebDriverEventListener {


  private WaitingAction waitingAction;

  private JavaScriptAction javaScriptAction;

  private Configuration configuration;

  @Inject
  public WaitEventListener(WaitingAction waitingAction,
      JavaScriptAction javaScriptAction) {
    this.waitingAction = waitingAction;
    this.javaScriptAction = javaScriptAction;
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
  }

  @Override
  public void afterAlertDismiss(WebDriver webDriver) {
    logger.info("Alert Popup Dismiss");
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
    waitRequest();
  }

  @Override
  public void afterFindBy(By by, WebElement webElement, WebDriver webDriver) {
    logger.info("Find Element {}", by.toString());
  }

  @Override
  public void beforeClickOn(WebElement webElement, WebDriver webDriver) {
    javaScriptAction.scrollToJs(webElement);
  }

  @Override
  public void afterClickOn(WebElement webElement, WebDriver webDriver) {

  }

  @Override
  public void beforeChangeValueOf(WebElement webElement, WebDriver webDriver,
      CharSequence[] charSequences) {
    logger.info("Set Value {}", charSequences);
  }

  @Override
  public void afterChangeValueOf(WebElement webElement, WebDriver webDriver,
      CharSequence[] charSequences) {

  }

  @Override
  public void beforeScript(String s, WebDriver webDriver) {
    logger.info("Run script {}", s);
  }

  @Override
  public void afterScript(String s, WebDriver webDriver) {

  }

  @Override
  public void onException(Throwable throwable, WebDriver webDriver) {
  }

  private void waitRequest() {
    if (configuration.isWaitPageLoad()) {
      waitingAction.waitPageLoadComplete();
    }
    if (configuration.isWaitAjax()) {
      waitingAction.waitAjaxComplete().waitJQueryComplete();
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
}
