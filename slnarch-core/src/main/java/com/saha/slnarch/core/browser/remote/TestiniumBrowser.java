package com.saha.slnarch.core.browser.remote;

import com.saha.slnarch.common.helper.SystemPropertyHelper;
import com.saha.slnarch.core.browser.BaseBrowser;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public final class TestiniumBrowser extends
    BaseBrowser<RemoteWebDriver, DesiredCapabilities, TestiniumBrowser> {

  //  private final String PROXY_SERVER = "ec2-54-154-66-64.eu-west-1.compute.amazonaws.com:3128";
  private final String TESTINIUM_URL = "http://hub.testinium.io/wd/hub";

  @Override
  protected TestiniumBrowser setDriverPath() {
    return null;
  }

  @Override
  protected DesiredCapabilities getDefaultOptions(Proxy proxy) {
    DesiredCapabilities capabilities = DesiredCapabilities.chrome();
    capabilities.setCapability("key", SystemPropertyHelper.getTestiniumKey());
    if (proxy != null) {
      capabilities.setCapability(CapabilityType.PROXY, proxy);
    }
    return capabilities;
  }

  @Override
  protected DesiredCapabilities getOptions(DesiredCapabilities options, Proxy proxy) {
    return options != null ? options : getDefaultOptions(proxy);
  }

  @Override
  public RemoteWebDriver buildWebDriver(DesiredCapabilities options, Proxy proxy)
      throws MalformedURLException {
    return setTimeOut(new RemoteWebDriver(new URL(TESTINIUM_URL), getOptions(options, proxy)));
  }
}
