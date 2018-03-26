package com.saha.slnarch.core.browser.remote;

import com.saha.slnarch.common.helper.StringHelper;
import com.saha.slnarch.common.helper.SystemPropertyHelper;
import com.saha.slnarch.core.browser.BaseBrowser;
import com.saha.slnarch.core.model.Configuration;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class JenkinsBrowser extends
    BaseBrowser<RemoteWebDriver, DesiredCapabilities, JenkinsBrowser> {

  private final String TESTINIUM_URL = "http://hub.testinium.io/wd/hub";

  public JenkinsBrowser(Configuration configuration) {
    super(configuration);
  }

  @Override
  protected JenkinsBrowser setDriverPath() {
    return null;
  }

  @Override
  protected DesiredCapabilities getDefaultOptions(Proxy proxy) {
    DesiredCapabilities capabilities = DesiredCapabilities.chrome();
    capabilities.setCapability("key", SystemPropertyHelper.getJenkinsTestiniumKey());
    capabilities.setCapability(CapabilityType.PLATFORM, SystemPropertyHelper.getPlatform());
    capabilities.setCapability(CapabilityType.BROWSER_NAME, SystemPropertyHelper.getBrowserName());
    capabilities.setCapability(CapabilityType.VERSION, SystemPropertyHelper.getBrowserVersion());
    capabilities
        .setCapability(CapabilityType.TAKES_SCREENSHOT,
            StringHelper.convertStringToBoolean(SystemPropertyHelper.getTakeScreenShot()));
    capabilities.setCapability("recordsVideo",
        StringHelper.convertStringToBoolean(SystemPropertyHelper.getRecordVideo()));
    capabilities.setCapability("screenResolution", SystemPropertyHelper.getScreenResolution());
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
        StringHelper.isEmpty(configuration.getHubUrl()) ? TESTINIUM_URL
            : configuration.getHubUrl()), getOptions(options, proxy)));
  }
}
