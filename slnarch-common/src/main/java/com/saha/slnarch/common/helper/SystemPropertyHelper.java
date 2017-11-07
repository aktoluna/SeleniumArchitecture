package com.saha.slnarch.common.helper;

public class SystemPropertyHelper {

  private static final String PROP_CHROME_DRIVER = "webdriver.chrome.driver";
  private static final String PROP_TESTINIUM_KEY = "key";
//  private static final String PROP_PAGE_TIMEOUT = "page.load.timeout";
//  private static final String PROP_SCRIPT_TIMEOUT = "script.timeout";
//  private static final String PROP_IMPLICITLY_TIMEOUT = "implicitly.wait";

  private SystemPropertyHelper() {

  }

  private static String getProperty(String propertyKey) {
    return System.getProperty(propertyKey);
  }

  private static void setProperty(String propertyKey, String propertyValue) {
    System.setProperty(propertyKey, propertyValue);
  }

  public static String getTestiniumKey() {
    return getProperty(PROP_TESTINIUM_KEY);
  }

  public static void setChromeDriverLocation(String path) {
    setProperty(PROP_CHROME_DRIVER, path);
  }

}
