package io.aktoluna.slnarch.core.browser.local;

import io.aktoluna.slnarch.common.helper.SystemPropertyHelper;
import io.aktoluna.slnarch.core.browser.BaseBrowser;
import io.aktoluna.slnarch.core.model.Configuration;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;

public class InternetExplorerBrowser extends
    BaseBrowser<InternetExplorerDriver, InternetExplorerOptions, InternetExplorerBrowser> {

  public InternetExplorerBrowser(Configuration configuration) {
    super(configuration);
  }

  @Override
  protected InternetExplorerBrowser setDriverPath() {
    SystemPropertyHelper.setInternetExplorerDriverLocation(configuration.getDriverPath());
    return this;
  }

  @Override
  protected InternetExplorerOptions getDefaultOptions(Proxy proxy) {
    InternetExplorerOptions options = new InternetExplorerOptions();
    options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,
        UnexpectedAlertBehaviour.IGNORE);
    if (proxy != null) {
      options.setProxy(proxy);
    }
    return options;
  }

  @Override
  protected InternetExplorerOptions getOptions(InternetExplorerOptions options, Proxy proxy) {
    return options == null ? getDefaultOptions(proxy) : options;
  }

  @Override
  public InternetExplorerDriver buildWebDriver(InternetExplorerOptions options, Proxy proxy) {
    return setDriverPath().setTimeOut(new InternetExplorerDriver(getOptions(options, proxy)));
  }
}
