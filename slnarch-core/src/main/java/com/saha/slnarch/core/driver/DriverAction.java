package com.saha.slnarch.core.driver;

import java.io.File;
import java.util.Date;
import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;

public interface DriverAction<T extends DriverAction> {

  T addCookie(String name, String value, String domain, String path, Date expiry);

  T deleteCookie(String cookieName);

  T callPage(String page);

  T goBack();

  T navigateTo(String url);

  T refreshTo();

  T setImplicitlyTimeOut(long time);

  T setPageLoadTimeOut(long time);

  T setScriptTimeOut(long time);

  T alertPopup(boolean accept);

  T alertPopup(boolean accept, boolean exist);

  T alertPopupHandle(Alert alert, boolean accept);

  Alert getAlertIsPresent();

  void close();

  void quit();

  String getCurrentUrl();

  String getPageSource();

  String getTitle();

  File takeScreenShot();

  File takeScreenShotAndCompress();

  <X> X takeScreenShotByType(OutputType<X> outputType);

}
