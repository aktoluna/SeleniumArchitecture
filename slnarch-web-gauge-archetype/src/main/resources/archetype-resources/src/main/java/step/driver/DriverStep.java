package ${groupId}.step.driver;

public interface DriverStep {

  void goToURL(String url);

  void refresh();

  void back();

  void next();

  void switchToFrameById(String id);

  void switchToFrameByKey(String key);

  void switchToFrameByIndex(int index);

  void switchToMainFrame();

  void switchToNextTab();

  void switchToPrevTab();

  void closeTab();

  void close();

  void quit();

  void addCookie(String cookieName, String cookieValue);

  void removeCookie(String cookieName);

  void acceptAlert();

  void declineAlert();

  void handleAlert(boolean accept);

}
