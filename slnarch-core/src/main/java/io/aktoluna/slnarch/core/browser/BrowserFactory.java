package io.aktoluna.slnarch.core.browser;

import io.aktoluna.slnarch.common.exception.SlnException;
import io.aktoluna.slnarch.common.helper.StringHelper;
import io.aktoluna.slnarch.common.helper.SystemPropertyHelper;
import io.aktoluna.slnarch.core.browser.local.ChromeBrowser;
import io.aktoluna.slnarch.core.browser.local.FirefoxBrowser;
import io.aktoluna.slnarch.core.browser.local.InternetExplorerBrowser;
import io.aktoluna.slnarch.core.browser.local.SafariBrowser;
import io.aktoluna.slnarch.core.browser.remote.JenkinsBrowser;
import io.aktoluna.slnarch.core.browser.remote.LocalRemoteBrowser;
import io.aktoluna.slnarch.core.browser.remote.TestiniumBrowser;
import io.aktoluna.slnarch.core.model.Configuration;
import java.net.MalformedURLException;
import javax.annotation.Nullable;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;

public class BrowserFactory {

  public WebDriver getWebDriver(@Nullable Capabilities capabilities, @Nullable Proxy proxy,
      Configuration configuration)
      throws MalformedURLException {
    Browser browser = null;
    if (!StringHelper.isEmpty(SystemPropertyHelper.getTestiniumKey())) {
      browser = new TestiniumBrowser(configuration);
    } else if (!StringHelper.isEmpty(SystemPropertyHelper.getJenkinsTestiniumKey())) {
      browser = new JenkinsBrowser(configuration);
    } else {
      if (configuration.getBrowserType().equals("Chrome")) {
        browser = new ChromeBrowser(configuration);
      } else if (configuration.getBrowserType().equals("Firefox")) {
        browser = new FirefoxBrowser(configuration);
      } else if (configuration.getBrowserType().equals("Safari")) {
        browser = new SafariBrowser(configuration);
      } else if (configuration.getBrowserType().equals("IE")) {
        browser = new InternetExplorerBrowser(configuration);
      } else if (configuration.getBrowserType().equals("Remote")) {
        browser = new LocalRemoteBrowser(configuration);
      } else {
        throw new SlnException("Browser Type Not Found");
      }
    }
    return browser.buildWebDriver(capabilities, proxy);
  }

}
