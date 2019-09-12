package io.aktoluna.slnarch.core.di.module;

import io.aktoluna.slnarch.common.file.PropertyHelper;
import io.aktoluna.slnarch.core.driver.DriverAction;
import io.aktoluna.slnarch.core.driver.DriverActionImpl;
import io.aktoluna.slnarch.core.element.Element;
import io.aktoluna.slnarch.core.element.ElementImp;
import io.aktoluna.slnarch.core.element.JavaScriptAction;
import io.aktoluna.slnarch.core.js.JavaScriptOperation;
import io.aktoluna.slnarch.core.js.JavaScriptOperationImpl;
import io.aktoluna.slnarch.core.listener.WaitEventListener;
import io.aktoluna.slnarch.core.model.Configuration;
import io.aktoluna.slnarch.core.wait.WaitingAction;
import io.aktoluna.slnarch.core.wait.WaitingActionImpl;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.inject.Named;
import javax.inject.Singleton;
import org.codejargon.feather.Provides;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverModule {

  private final WebDriver driver;
  private final static int POLLING_TIME = 250;
  private final static int EXPLICIT_TIME = 10;

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
  @Named("default")
  public WebDriverWait provideWebDriverWait(WebDriver driver, Configuration configuration) {
    WebDriverWait webDriverWait = new WebDriverWait(driver,
        configuration.getExplicitTimeOut() < 0 ? EXPLICIT_TIME
            : configuration.getExplicitTimeOut(),
        configuration.getPollingTime() < 0 ? POLLING_TIME : configuration.getPollingTime());
    webDriverWait
        .ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
    return webDriverWait;
  }

  @Provides
  @Singleton
  @Named("frameWait")
  public WebDriverWait provideWebDriverWaitByFrame(WebDriver driver, Configuration configuration) {
    WebDriverWait webDriverWait = new WebDriverWait(driver,
        configuration.getPageTimeOut(),
        configuration.getPollingTime() < 0 ? POLLING_TIME : configuration.getPollingTime());
    webDriverWait
        .ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
    return webDriverWait;
  }

  @Provides
  @Singleton
  @Named("defaultFluent")
  public FluentWait<WebDriver> provideFluentWebDriverWait(WebDriver driver,
      Configuration configuration) {
    return new FluentWait<>(driver)
        .withTimeout(configuration.getExplicitTimeOut() <= 0 ? EXPLICIT_TIME
            : configuration.getExplicitTimeOut(), TimeUnit.SECONDS)
        .pollingEvery(
            configuration.getPollingTime() <= 0 ? POLLING_TIME : configuration.getPollingTime(),
            TimeUnit.MILLISECONDS)
        .ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
  }

  @Provides
  @Singleton
  public JavaScriptOperation provideJavaScriptOperation(WebDriver driver) {
    return new JavaScriptOperationImpl(driver);
  }

  @Provides
  public DriverAction provideDriverAction(WebDriver driver,
      WaitingAction waitingAction) {
    return new DriverActionImpl(driver, waitingAction);
  }

  @Provides
  @Singleton
  public WaitingAction provideWaitingAction(@Named("defaultFluent") FluentWait<WebDriver> wait,
      @Named("frameWait") WebDriverWait frameWait,
      JavaScriptOperation javaScriptOperation) {
    return new WaitingActionImpl(wait, frameWait, javaScriptOperation);
  }

  @Provides
  @Singleton
  public Element provideElement(WebDriver driver,
      JavaScriptOperation javaScriptOperation, WaitingAction waitingAction) {
    return new ElementImp(driver, javaScriptOperation, waitingAction);
  }

  @Provides
  @Singleton
  public JavaScriptAction provideJavaScriptAction(WebDriver driver,
      JavaScriptOperation javaScriptOperation, WaitingAction waitingAction) {
    return new ElementImp(driver, javaScriptOperation, waitingAction);
  }

  @Provides
  @Singleton
  public WaitEventListener provideWaitEventListener(WaitingAction waitingAction,
      JavaScriptAction javaScriptAction,JavaScriptOperation javaScriptOperation) {
    return new WaitEventListener(waitingAction, javaScriptAction,javaScriptOperation);
  }

  @Provides
  @Singleton
  public Configuration provideConfiguration()
      throws IOException, InstantiationException, IllegalAccessException {
    return PropertyHelper
        .propertiesToClassWithAnnotation(PropertyHelper.readProperties("slnarch.properties"),
            Configuration.class);
  }
}
