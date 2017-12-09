package com.saha.slnarch.core.driver;


import com.saha.slnarch.common.ImageHelper;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;


public final class DriverActionImpl implements DriverAction {

  private final WebDriver driver;

  @Inject
  public DriverActionImpl(WebDriver driver) {
    this.driver = driver;
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

  @Override
  public DriverAction goBack() {
    driver.navigate().back();
    return this;
  }

  @Override
  public DriverAction navigateTo(String url) {
    driver.navigate().to(url);
    return this;
  }

  @Override
  public DriverAction refreshTo() {
    driver.navigate().refresh();
    return this;
  }

  @Override
  public DriverAction setImplicitlyTimeOut(long time) {
    driver.manage().timeouts().implicitlyWait(time, TimeUnit.MILLISECONDS);
    return this;
  }

  @Override
  public DriverAction setPageLoadTimeOut(long time) {
    driver.manage().timeouts().pageLoadTimeout(time, TimeUnit.MILLISECONDS);
    return this;
  }

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

  @Override
  public void close() {
    driver.close();
  }

  @Override
  public void quit() {
    driver.quit();
  }

  @Override
  public String getCurrentUrl() {
    return driver.getCurrentUrl();
  }

  @Override
  public String getPageSource() {
    return driver.getPageSource();
  }

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
      e.printStackTrace();
    }
    return file;
  }

  @Override
  public Object takeScreenShotByType(OutputType outputType) {
    return ((TakesScreenshot) driver).getScreenshotAs(outputType);
  }

}
