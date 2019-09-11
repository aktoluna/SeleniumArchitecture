package io.aktoluna.slnarch.core.browser;

import java.net.MalformedURLException;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;

public interface Browser<S extends WebDriver,T extends Capabilities> {

  S buildWebDriver(T options, Proxy proxy) throws MalformedURLException;

}
