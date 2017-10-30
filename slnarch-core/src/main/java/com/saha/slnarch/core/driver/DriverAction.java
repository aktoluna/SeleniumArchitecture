package com.saha.slnarch.core.driver;

import java.util.Date;

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

  void close();

  void quit();

  String getCurrentUrl();

  String getPageSource();

  String getTitle();
}
