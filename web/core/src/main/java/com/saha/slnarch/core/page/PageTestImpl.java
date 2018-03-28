package com.saha.slnarch.core.page;

import com.saha.slnarch.common.log.LogHelper;
import com.saha.slnarch.core.browser.BrowserFactory;
import com.saha.slnarch.core.di.module.DriverModule;
import com.saha.slnarch.core.helper.ConfigurationHelper;
import com.saha.slnarch.core.listener.WaitEventListener;
import com.saha.slnarch.core.model.Configuration;
import com.saha.slnarch.di.InjectionHelper;
import com.saha.slnarch.driver.event.EventDriver;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import org.codejargon.feather.Feather;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;

public abstract class PageTestImpl implements PageTest {

  protected final Logger logger = LogHelper.getSlnLogger();
  private WebDriver driver;
  private Configuration configuration;

  protected PageTestImpl() {
    initWebDriver();
    if (useEventDriver()) {
      driver = createEventDriver();
    }
    initFeather();
    setEventListener();
  }

  private void initWebDriver() {

    configuration = ConfigurationHelper.INSTANCE.getConfiguration();
    try {
      driver = new BrowserFactory().getWebDriver(getCapabilities(), getProxy(), configuration);
    } catch (MalformedURLException e) {
      logger.error(e.getMessage());
      LogHelper.getSlnLogger().error("Init WebDriver Error", e);
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
    return new EventDriver(driver);
  }


  @Override
  public boolean useEventDriver() {
    return true;
  }

  protected Configuration getConfiguration() {
    return configuration;
  }


  private void initFeather() {
    logger.info("Init Feather Injections");
    List<Object> moduleList = getModule() == null ? new ArrayList<>() : getModule();
    moduleList.add(new DriverModule(getDriver()));
    setFeather(Feather.with(moduleList));
  }

  protected List<Object> getModule() {
    return new ArrayList<>();
  }

  protected Feather getFeather() {
    return InjectionHelper.getInstance().getFeather();
  }


  protected void setFeather(Feather feather) {
    InjectionHelper.getInstance().setFeather(feather);
  }

  @Override
  public void setEventListener() {
    if (useEventDriver()) {
      WaitEventListener waitEventListener = getFeather().instance(WaitEventListener.class);
      waitEventListener.setConfiguration(getConfiguration());
      ((EventDriver) getDriver()).register(waitEventListener);
    }
  }
}
