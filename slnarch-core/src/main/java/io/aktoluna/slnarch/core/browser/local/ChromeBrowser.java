package io.aktoluna.slnarch.core.browser.local;

import io.aktoluna.slnarch.common.helper.SystemPropertyHelper;
import io.aktoluna.slnarch.core.browser.BaseBrowser;
import io.aktoluna.slnarch.core.model.Configuration;
import java.net.MalformedURLException;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;

public class ChromeBrowser extends BaseBrowser<ChromeDriver, ChromeOptions, ChromeBrowser> {


  public ChromeBrowser(Configuration configuration) {
    super(configuration);
  }

  @Override
  protected ChromeBrowser setDriverPath() {
    SystemPropertyHelper.setChromeDriverLocation(configuration.getDriverPath());
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
