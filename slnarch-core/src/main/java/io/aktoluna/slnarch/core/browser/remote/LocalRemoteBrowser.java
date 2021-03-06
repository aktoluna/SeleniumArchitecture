package io.aktoluna.slnarch.core.browser.remote;

import io.aktoluna.slnarch.common.helper.StringHelper;
import io.aktoluna.slnarch.common.helper.SystemPropertyHelper;
import io.aktoluna.slnarch.core.browser.BaseBrowser;
import io.aktoluna.slnarch.core.model.Configuration;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class LocalRemoteBrowser extends
    BaseBrowser<RemoteWebDriver, DesiredCapabilities, LocalRemoteBrowser> {

  private final String LOCAL_URL = "http://localhost:4444/wd/hub";

  public LocalRemoteBrowser(Configuration configuration) {
    super(configuration);
  }

  @Override
  protected LocalRemoteBrowser setDriverPath() {
    return this;
  }

  @Override
  protected DesiredCapabilities getDefaultOptions(Proxy proxy) {
    DesiredCapabilities capabilities = DesiredCapabilities.chrome();
    capabilities.setCapability("key", SystemPropertyHelper.getProperty("key"));
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
    return setTimeOut(new RemoteWebDriver(new URL(
        StringHelper.isEmpty(configuration.getHubUrl()) ? LOCAL_URL : configuration.getHubUrl()),
        getOptions(options, proxy)));
  }
}
