package com.saha.slnarch.core.browser.local;

import com.saha.slnarch.common.helper.SystemPropertyHelper;
import com.saha.slnarch.core.browser.BaseBrowser;
import com.saha.slnarch.core.model.Configuration;
import java.net.MalformedURLException;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxBrowser extends BaseBrowser<FirefoxDriver, FirefoxOptions, FirefoxBrowser> {

  public FirefoxBrowser(Configuration configuration) {
    super(configuration);

  }

  @Override
  protected FirefoxBrowser setDriverPath() {
    SystemPropertyHelper.setFirefoxDriverLocation(configuration.getDriverPath());
    return this;
  }

  @Override
  protected FirefoxOptions getDefaultOptions(Proxy proxy) {
    FirefoxOptions options = new FirefoxOptions();
    return options;
  }


  @Override
  public FirefoxDriver buildWebDriver(FirefoxOptions options, Proxy proxy)
      throws MalformedURLException {
    return setDriverPath().setTimeOut(new FirefoxDriver(getOptions(options, proxy)));
  }
}
