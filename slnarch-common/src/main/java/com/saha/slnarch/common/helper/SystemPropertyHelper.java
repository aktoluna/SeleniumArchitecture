package com.saha.slnarch.common.helper;

public class SystemPropertyHelper {

  private static final String PROP_CHROME_DRIVER = "webdriver.chrome.driver";
  private static final String PROP_FIREFOX_DRIVER = "webdriver.gecko.driver";
  private static final String PROP_TESTINIUM_KEY = "key";
  private static final String PROP_ENV = "env";

  private SystemPropertyHelper() {

  }

  public static String getProperty(String propertyKey) {
    return System.getProperty(propertyKey);
  }

  public static void setProperty(String propertyKey, String propertyValue) {
    System.setProperty(propertyKey, propertyValue);
  }

  public static String getTestiniumKey() {
    return getProperty(PROP_TESTINIUM_KEY);
  }

  public static String getEnvKey() {
    return getProperty(PROP_ENV);
  }

  public static void setChromeDriverLocation(String path) {
    setProperty(PROP_CHROME_DRIVER, path);
  }

  public static void setFirefoxDriverLocation(String path) {
    setProperty(PROP_FIREFOX_DRIVER, path);
  }

}
