package com.saha.slnarch.di.module;

import com.saha.slnarch.core.driver.DriverAction;
import com.saha.slnarch.core.driver.DriverActionImpl;
import com.saha.slnarch.core.element.ElementAction;
import com.saha.slnarch.core.element.ElementActionImpl;
import com.saha.slnarch.core.js.JavaScriptAction;
import com.saha.slnarch.core.js.JavaScriptOperation;
import com.saha.slnarch.core.js.JavaScriptOperationImpl;
import com.saha.slnarch.core.wait.WaitingAction;
import com.saha.slnarch.core.wait.WaitingActionImpl;
import javax.inject.Singleton;
import org.codejargon.feather.Provides;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverModule {

  private final WebDriver driver;
  private static final long WAIT_TIME = 10;

  public DriverModule(WebDriver driver) {
    this.driver = driver;
  }

  @Provides
  @Singleton
  public WebDriver provideWebDriver() {
    return driver;
  }

  @Provides
  @Singleton
  public WebDriverWait provideWebDriverWait() {
    return new WebDriverWait(driver, WAIT_TIME);
  }

  @Provides
  @Singleton
  public JavaScriptOperation provideJavaScriptOperation(WebDriver driver) {
    return new JavaScriptOperationImpl(driver);
  }

  @Provides
  @Singleton
  public DriverAction provideDriverAction(WebDriver driver) {
    return new DriverActionImpl(driver);
  }

  @Provides
  @Singleton
  public WaitingAction provideWaitingAction(WebDriverWait wait,
      JavaScriptOperation javaScriptOperation) {
    return new WaitingActionImpl(wait, javaScriptOperation);
  }

  @Provides
  @Singleton
  public ElementAction provideElementAction(WebDriver driver,
      JavaScriptOperation javaScriptOperation) {
    return new ElementActionImpl(driver, javaScriptOperation);
  }

  @Provides
  @Singleton
  public JavaScriptAction provideJavaScriptAction(WebDriver driver,
      JavaScriptOperation javaScriptOperation) {
    return new ElementActionImpl(driver, javaScriptOperation);
  }
}
