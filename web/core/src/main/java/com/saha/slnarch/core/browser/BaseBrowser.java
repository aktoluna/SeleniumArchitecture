package com.saha.slnarch.core.browser;

import com.saha.slnarch.core.model.Configuration;
import com.saha.slnarch.core.model.TimeOut;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;

public abstract class BaseBrowser<S extends WebDriver, T extends Capabilities, V extends BaseBrowser> implements
    Browser<S, T> {

  private final int PAGE_TIME_OUT = 30;
  private final int SCRIPT_TIME_OUT = 30;

  protected Configuration configuration;

  public BaseBrowser(Configuration configuration) {
    this.configuration = configuration;
  }

  protected TimeOut getTimeout() {
    return TimeOut.TimeOutBuilder.aTimeOut()
        .withImplicitly(configuration.getImplicitlyTimeOut())
        .withScript(configuration.getScriptTimeOut() <= 0 ? SCRIPT_TIME_OUT
            : configuration.getScriptTimeOut())
        .withPageLoad(configuration.getPageTimeOut() <= 0 ? PAGE_TIME_OUT
            : configuration.getPageTimeOut())
        .build();
  }

  protected S setTimeOut(S driver) {
    TimeOut timeOut = getTimeout();
    driver.manage().timeouts().implicitlyWait(timeOut.getImplicitly(), TimeUnit.SECONDS);
    driver.manage().timeouts().setScriptTimeout(timeOut.getScript(), TimeUnit.SECONDS);
    driver.manage().timeouts().pageLoadTimeout(timeOut.getPageLoad(), TimeUnit.SECONDS);
    return driver;
  }


  protected abstract V setDriverPath();

  protected abstract T getDefaultOptions(Proxy proxy);

  protected T getOptions(T options, Proxy proxy) {
    if (options == null) {
      options = getDefaultOptions(proxy);
    }
    if (capabilitiesHasProxy(options)) {
      capabilitiesAddProxy(options, proxy);
    }
    return options;
  }

  protected boolean capabilitiesHasProxy(Capabilities capabilities) {
    return capabilities.getCapability(CapabilityType.PROXY) != null;
  }

  protected void capabilitiesAddProxy(Capabilities capabilities, Proxy proxy) {
    if (proxy != null) {
      ((MutableCapabilities) capabilities).setCapability(CapabilityType.PROXY, proxy);
    }
  }

//  protected boolean isLocalBrowser() {
//    return true;
//  }
}
