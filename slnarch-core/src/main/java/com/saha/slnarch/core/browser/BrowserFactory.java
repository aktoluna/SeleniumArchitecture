package com.saha.slnarch.core.browser;

import com.saha.slnarch.common.helper.StringHelper;
import com.saha.slnarch.common.helper.SystemPropertyHelper;
import com.saha.slnarch.core.browser.local.ChromeBrowser;
import com.saha.slnarch.core.browser.remote.TestiniumBrowser;
import java.net.MalformedURLException;
import javax.annotation.Nullable;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;

public class BrowserFactory {

  public WebDriver getWebDriver(@Nullable Capabilities capabilities, @Nullable Proxy proxy)
      throws MalformedURLException {
    Browser browser;
    if (!StringHelper.isEmpty(SystemPropertyHelper.getTestiniumKey())) {
      browser = new TestiniumBrowser();
    } else {
      browser = new ChromeBrowser();
    }
    return browser.buildWebDriver(capabilities, proxy);
  }

}
