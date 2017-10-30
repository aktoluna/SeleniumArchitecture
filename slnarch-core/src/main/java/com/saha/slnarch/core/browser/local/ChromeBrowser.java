package com.saha.slnarch.core.browser.local;

import com.saha.slnarch.common.helper.SystemPropertyHelper;
import com.saha.slnarch.core.browser.BaseBrowser;
import java.net.MalformedURLException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;

public class ChromeBrowser extends BaseBrowser<ChromeDriver, ChromeOptions, ChromeBrowser> {


  @Override
  protected ChromeBrowser setDriverPath() {
    Platform platform = Platform.getCurrent();
    if (platform == Platform.MAC) {
      SystemPropertyHelper.setChromeDriverLocationForMac();
    } else if (platform == Platform.WINDOWS) {
      SystemPropertyHelper.setChromeDriverLocationForWindows();
    }
    return this;
  }

  @Override
  protected ChromeOptions getDefaultOptions(Proxy proxy) {
    ChromeOptions options = new ChromeOptions();
    options.addArguments("test-type");
    options.addArguments("disable-popup-blocking");
    options.addArguments("ignore-certificate-errors");
    options.addArguments("disable-translate");
    if (proxy != null) {
      options.setCapability(CapabilityType.PROXY, proxy);
    }
    return options;
  }


  @Override
  protected ChromeOptions getOptions(ChromeOptions options, Proxy proxy) {
    return options == null ? getDefaultOptions(proxy) : options;
  }

  @Override
  public ChromeDriver buildWebDriver(ChromeOptions options, Proxy proxy)
      throws MalformedURLException {
    return setDriverPath().setTimeOut(new ChromeDriver(getOptions(options, proxy)));
  }
}
