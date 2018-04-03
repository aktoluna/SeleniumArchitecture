package com.saha.slnarch.core.browser.remote;

import com.saha.slnarch.common.helper.StringHelper;
import com.saha.slnarch.core.browser.BaseBrowser;
import com.saha.slnarch.core.model.Configuration;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class RemoteBrowser extends
    BaseBrowser<RemoteWebDriver, DesiredCapabilities, RemoteBrowser> {

  private final String LOCAL_URL = "http://localhost:4444/wd/hub";

  public RemoteBrowser(Configuration configuration) {
    super(configuration);
  }

  @Override
  protected RemoteBrowser setDriverPath() {
    return this;
  }

  @Override
  protected DesiredCapabilities getDefaultOptions(Proxy proxy) {
    return DesiredCapabilities.chrome();
  }

  @Override
  public RemoteWebDriver buildWebDriver(DesiredCapabilities options, Proxy proxy)
      throws MalformedURLException {
    return setTimeOut(new RemoteWebDriver(new URL(
        StringHelper.isEmpty(getConfiguration().getHubUrl()) ? LOCAL_URL : getConfiguration().getHubUrl()),
        getOptions(options, proxy)));
  }
}
