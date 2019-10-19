package io.aktoluna.slnarch.common.helper;

public class SystemPropertyHelper {

  private static final String PROP_CHROME_DRIVER = "webdriver.chrome.driver";
  private static final String PROP_FIREFOX_DRIVER = "webdriver.gecko.driver";
  private static final String PROP_INTERNET_EXPLORER_DRIVER = "webdriver.gecko.driver";
  private static final String PROP_JENKINS_KEY = "jenkinsKey";
  private static final String PROP_ENV = "env";
  private static final String PROP_PLATFORM = "platform";
  private static final String PROP_BROWSER_NAME = "browserName";
  private static final String PROP_BROWSER_VERSION = "browserVersion";
  private static final String PROP_RECORDS_VIDEO = "recordVideo";
  private static final String PROP_SCREEN_RESOLUTION = "screenResolution";
  private static final String PROP_TAKE_SCREEN_SHOT = "takeScreenShot";

  private SystemPropertyHelper() {

  }

  public static String getProperty(String propertyKey) {
    return System.getProperty(propertyKey);
  }

  public static void setProperty(String propertyKey, String propertyValue) {
    System.setProperty(propertyKey, propertyValue);
  }

  public static String getJenkinsTestiniumKey() {
    return getProperty(PROP_JENKINS_KEY);
  }

  public static String getPlatform() {
    return getProperty(PROP_PLATFORM);
  }

  public static String getBrowserName() {
    return getProperty(PROP_BROWSER_NAME);
  }

  public static String getBrowserVersion() {
    return getProperty(PROP_BROWSER_VERSION);
  }

  public static String getRecordVideo() {
    return getProperty(PROP_RECORDS_VIDEO);
  }

  public static String getTakeScreenShot() {
    return getProperty(PROP_TAKE_SCREEN_SHOT);
  }

  public static String getScreenResolution() {
    return getProperty(PROP_SCREEN_RESOLUTION);
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

  public static void setInternetExplorerDriverLocation(String path) {
    setProperty(PROP_INTERNET_EXPLORER_DRIVER, path);
  }
}
