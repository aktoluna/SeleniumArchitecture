package com.saha.slnarch.core.browser;

import com.saha.slnarch.common.helper.SystemPropertyHelper;
import com.saha.slnarch.core.model.TimeOut;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;

public abstract class BaseBrowser<S extends WebDriver, T extends Capabilities,V extends BaseBrowser> implements
    Browser<S, T> {

  private final int PAGE_TIME_OUT = 60;
  private final int SCRIPT_TIME_OUT = 60;
  private final int IMPLICITLY_WAIT = 30;

  protected TimeOut getTimeout() {
    return TimeOut.TimeOutBuilder.aTimeOut()
        .withImplicitly(SystemPropertyHelper.getImplicitlyWait() == 0 ? IMPLICITLY_WAIT
            : SystemPropertyHelper.getImplicitlyWait())
        .withScript(SystemPropertyHelper.getScriptTimeOut() == 0 ? SCRIPT_TIME_OUT
            : SystemPropertyHelper.getScriptTimeOut())
        .withPageLoad(SystemPropertyHelper.getPageLoadTimeOut() == 0 ? PAGE_TIME_OUT
            : SystemPropertyHelper.getPageLoadTimeOut())
        .build();
  }

  protected S setTimeOut(S driver) {
    TimeOut timeOut = getTimeout();
    driver.manage().timeouts().implicitlyWait(timeOut.getImplicitly(), TimeUnit.SECONDS);
    driver.manage().timeouts().setScriptTimeout(timeOut.getScript(), TimeUnit.SECONDS);
    driver.manage().timeouts().pageLoadTimeout(timeOut.getPageLoad(), TimeUnit.SECONDS);
    return driver;
  }


  protected abstract V setDriverPath();

  protected abstract T getDefaultOptions(Proxy proxy);

  protected abstract T getOptions(T options, Proxy proxy);

//  protected boolean isLocalBrowser() {
//    return true;
//  }
}
