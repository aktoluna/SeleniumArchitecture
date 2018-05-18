package ${groupId}.step.element;

public interface WebElementStep {

  void clickElement();

  void clickElementById(String id);

  void clickElementByCss(String css);

  void clickElementByKey(String key);

  void sendKeys(String keys);

  void sendKeysById(String id, String keys);

  void sendKeysByCss(String css, String keys);

  void sendKeysByKey(String key, String keys);

  void pressKeyCode(String keys);

  void pressKeyCodeById(String id, String keys);

  void pressKeyCodeByCss(String css, String keys);

  void pressKeyCodeByKey(String key, String keys);

  void selectOptionValue(String value);

  void selectOptionValueById(String id, String value);

  void selectOptionValueByCss(String css, String value);

  void selectOptionValueByKey(String key, String value);

  void hoverElement();

  void hoverElementById(String id);

  void hoverElementByCss(String css);

  void hoverElementByKey(String key);

}
