package com.saha.slnarch.core.page;

import com.saha.slnarch.common.helper.PropertyHelper;
import com.saha.slnarch.core.browser.BrowserFactory;
import com.saha.slnarch.core.js.JavaScriptOperationImpl;
import com.saha.slnarch.core.listener.WaitEventListener;
import com.saha.slnarch.core.model.Configuration;
import com.saha.slnarch.core.wait.WaitingActionImpl;
import java.io.IOException;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class PageTestImpl implements PageTest {

  protected final Logger logger = LoggerFactory.getLogger(getClass());
  private WebDriver driver;

  protected PageTestImpl() {
    initWebDriver();
    if (useEventDriver()) {
      driver = createEventDriver();
    }
  }

  private void initWebDriver() {
    Configuration configuration;
    try {
      configuration = PropertyHelper
          .propertiesToClassWithAnnotation(PropertyHelper.readProperties("slnarch.properties"),
              Configuration.class);
      driver = new BrowserFactory().getWebDriver(getCapabilities(), getProxy(), configuration);
    } catch (IllegalAccessException | IOException | InstantiationException e) {
      e.printStackTrace();
    }
  }

  protected WebDriver getDriver() {
    return driver;
  }

  @Override
  public Proxy getProxy() {
    return null;
  }

  @Override
  public Capabilities getCapabilities() {
    return null;
  }

  @Override
  public WebDriver createEventDriver() {
    EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(driver);
    WebDriverEventListener eventListener = getEventListener();
    if (eventListener != null) {
      eventFiringWebDriver.register(eventListener);
    }
    return eventFiringWebDriver;
  }


  @Override
  public WebDriverEventListener getEventListener() {
    return new WaitEventListener(
        new WaitingActionImpl(new WebDriverWait(getDriver(), 30),
            new JavaScriptOperationImpl(getDriver())));
  }

  @Override
  public boolean useEventDriver() {
    return false;
  }

}
