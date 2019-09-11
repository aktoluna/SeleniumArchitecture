package io.aktoluna.slnarch.core.page;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;

public interface PageTest {

  Proxy getProxy();

  Capabilities getCapabilities();

  boolean useEventDriver();

  WebDriver createEventDriver();

  void setEventListener();

}
