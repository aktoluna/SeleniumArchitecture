package io.aktoluna.slnarch.core.browser.local;

import io.aktoluna.slnarch.common.helper.SystemPropertyHelper;
import io.aktoluna.slnarch.core.browser.BaseBrowser;
import io.aktoluna.slnarch.core.model.Configuration;
import java.net.MalformedURLException;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;

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
    options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,
        UnexpectedAlertBehaviour.IGNORE);
    if (proxy != null) {
      options.setProxy(proxy);
    }
    return options;
  }

  @Override
  protected FirefoxOptions getOptions(FirefoxOptions options, Proxy proxy) {
    return options == null ? getDefaultOptions(proxy) : options;
  }

  @Override
  public FirefoxDriver buildWebDriver(FirefoxOptions options, Proxy proxy)
      throws MalformedURLException {
    return setDriverPath().setTimeOut(new FirefoxDriver(getOptions(options, proxy)));
  }
}
