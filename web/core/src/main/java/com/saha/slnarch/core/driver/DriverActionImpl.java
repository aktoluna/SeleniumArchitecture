package com.saha.slnarch.core.driver;

import static com.saha.slnarch.core.expected.DriverExpectedConditionsType.ALERT_PRESENT;
import static com.saha.slnarch.core.expected.DriverExpectedConditionsType.FRAME_AVAILABLE;
import static com.saha.slnarch.core.expected.DriverExpectedConditionsType.PAGE_SOURCE_CONTAINS;
import static com.saha.slnarch.core.expected.DriverExpectedConditionsType.PAGE_SOURCE_MATCHES;
import static com.saha.slnarch.core.expected.DriverExpectedConditionsType.TITLE_CONTAINS;
import static com.saha.slnarch.core.expected.DriverExpectedConditionsType.TITLE_EQUALS;
import static com.saha.slnarch.core.expected.DriverExpectedConditionsType.TITLE_MATCHES;
import static com.saha.slnarch.core.expected.DriverExpectedConditionsType.URL_CONTAINS;
import static com.saha.slnarch.core.expected.DriverExpectedConditionsType.URL_MATCHES;
import static com.saha.slnarch.core.expected.DriverExpectedConditionsType.URL_TO_BE;

import com.saha.slnarch.common.image.ImageHelper;
import com.saha.slnarch.common.log.LogHelper;
import com.saha.slnarch.core.wait.WaitingAction;
import com.thoughtworks.gauge.Step;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;


public final class DriverActionImpl implements DriverAction {

  private final WebDriver driver;
  private final WaitingAction waitingAction;

  @Inject
  public DriverActionImpl(WebDriver driver, WaitingAction waitingAction) {
    this.driver = driver;
    this.waitingAction = waitingAction;
  }

  @Override
  public DriverAction addCookie(String name, String value, String domain, String path,
      Date expiry) {
    driver.manage().addCookie(new Cookie(name, value, domain, path, expiry));
    return this;
  }

  @Override
  public DriverAction deleteCookie(String cookieName) {
    driver.manage().deleteCookieNamed(cookieName);
    return this;
  }

  @Override
  public DriverAction callPage(String page) {
    driver.get(page);
    return this;
  }

  @Step("Return Back Page")
  @Override
  public DriverAction goBack() {
    driver.navigate().back();
    return this;
  }

  @Step("Navigate To <url>")
  @Override
  public DriverAction navigateTo(String url) {
    driver.navigate().to(url);
    return this;
  }

  @Step("Refresh Page")
  @Override
  public DriverAction refreshTo() {
    driver.navigate().refresh();
    return this;
  }

  @Step("Set Implicitly Time Out <time>")
  @Override
  public DriverAction setImplicitlyTimeOut(long time) {
    driver.manage().timeouts().implicitlyWait(time, TimeUnit.MILLISECONDS);
    return this;
  }

  @Step("Set Page Load Time Out <time>")
  @Override
  public DriverAction setPageLoadTimeOut(long time) {
    driver.manage().timeouts().pageLoadTimeout(time, TimeUnit.MILLISECONDS);
    return this;
  }

  @Step("Set Script Time Out <time>")
  @Override
  public DriverAction setScriptTimeOut(long time) {
    driver.manage().timeouts().setScriptTimeout(time, TimeUnit.MILLISECONDS);
    return this;
  }

  @Override
  public DriverAction alertPopup(boolean accept) {
    return alertPopup(accept, false);
  }

  @Override
  public DriverAction alertPopup(boolean accept, boolean exist) {
    Alert alert = getAlertIsPresent();
    if (exist) {
      if (alert != null) {
        alertPopupHandle(alert, accept);
      }
    } else {
      alertPopupHandle(alert, accept);
    }
    return this;
  }

  @Step("Alert popup action <accept>")
  @Override
  public DriverAction alertPopupHandle(Alert alert, boolean accept) {
    if (accept) {
      alert.accept();
    } else {
      alert.dismiss();
    }
    return this;
  }


  @Override
  public Alert getAlertIsPresent() {
    return ExpectedConditions.alertIsPresent().apply(driver);
  }

  @Step("Switch To Main Content")
  @Override
  public DriverAction switchToMain() {
    driver.switchTo().defaultContent();
    return this;
  }

  @Step("Switch To Frame <frameName>")
  @Override
  public DriverAction switchToFrame(String frameName) {
    driver.switchTo().frame(frameName);
    return this;
  }

  @Override
  public DriverAction switchToFrame(By byFrameName) {
    driver.switchTo().frame(driver.findElement(byFrameName));
    return this;
  }

  @Override
  public DriverAction switchToFrameElement(WebElement element) {
    driver.switchTo().frame(element);
    return this;
  }


  @Step("Close Driver")
  @Override
  public void close() {
    driver.close();
  }

  @Step("Quit Driver")
  @Override
  public void quit() {
    driver.quit();
  }

  @Step("Get Current Url")
  @Override
  public String getCurrentUrl() {
    return driver.getCurrentUrl();
  }

  @Step("Get Page Source")
  @Override
  public String getPageSource() {
    return driver.getPageSource();
  }

  @Step("Get Page Title")
  @Override
  public String getTitle() {
    return driver.getTitle();
  }

  @Override
  public File takeScreenShot() {
    return ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
  }

  @Override
  public File takeScreenShotAndCompress() {
    File file = takeScreenShot();
    try {
      file = ImageHelper.resizeWithThumbnails(file, 1200, 600);
    } catch (IOException e) {
      LogHelper.getSlnLogger().error("Image Resize Error", e);
    }
    return file;
  }

  @Override
  public Object takeScreenShotByType(OutputType outputType) {
    return ((TakesScreenshot) driver).getScreenshotAs(outputType);
  }

  @Step("Page Url Equals To <url>")
  @Override
  public boolean urlEquals(String url) {
    return waitingAction.expectedByBoolean(URL_TO_BE(url));
  }

  @Step("Page Url Contains To <url>")
  @Override
  public boolean urlContains(String url) {
    return waitingAction.expectedByBoolean(URL_CONTAINS(url));
  }

  @Step("Page Url Matches Pattern <urlPattern>")
  @Override
  public boolean urlPatternMatches(String url) {
    return waitingAction.expectedByBoolean(URL_MATCHES(url));
  }

  @Step("Page Title Equals To <title>")
  @Override
  public boolean titleEquals(String url) {
    return waitingAction.expectedByBoolean(TITLE_EQUALS(url));
  }

  @Step("Page Title Contains To <title>")
  @Override
  public boolean titleContains(String url) {
    return waitingAction.expectedByBoolean(TITLE_CONTAINS(url));
  }

  @Step("Page Title Matches Pattern <titlePattern>")
  @Override
  public boolean titlePatternMatches(String url) {
    return waitingAction.expectedByBoolean(TITLE_MATCHES(url));
  }

  @Step("Page Source Contains <source>")
  @Override
  public boolean pageSourceContains(String url) {
    return waitingAction.expectedByBoolean(PAGE_SOURCE_CONTAINS(url));
  }

  @Step("Page Source Matches Pattern <sourcePattern>")
  @Override
  public boolean pageSourceContainsPatternMatches(String url) {
    return waitingAction.expectedByBoolean(PAGE_SOURCE_MATCHES(url));
  }

  @Override
  public boolean isAlertPresent() {
    return waitingAction.expectedByBoolean(ALERT_PRESENT());
  }

  @Override
  public DriverAction frameAvailableSwitchTo(By by) {
    waitingAction.expected(FRAME_AVAILABLE(by));
    return this;
  }

  @Override
  public DriverAction frameAvailableSwitchTo(WebElement webElement) {
    waitingAction.expected(FRAME_AVAILABLE(webElement));
    return this;
  }

  @Override
  public DriverAction frameAvailableSwitchTo(String frameLocator) {
    waitingAction.expected(FRAME_AVAILABLE(frameLocator));
    return this;
  }

  @Override
  public DriverAction frameAvailableSwitchTo(int frameLocator) {
    waitingAction.expected(FRAME_AVAILABLE(frameLocator));
    return this;
  }
}
