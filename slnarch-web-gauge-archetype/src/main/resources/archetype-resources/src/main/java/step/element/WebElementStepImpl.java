package ${groupId}.step.element;

import com.thoughtworks.gauge.Step;
import org.openqa.selenium.Keys;

public class WebElementStepImpl extends FindElementStepImpl<WebElementStep> implements
    WebElementStep {

  @Override
  public WebElementStep getT() {
    return this;
  }

  @Step("Click element")
  @Override
  public void clickElement() {
    elementAction.click();
  }

  @Step("Click element by id <id>")
  @Override
  public void clickElementById(String id) {
    findElementById(id).clickElement();
  }

  @Step("Click element by css <css>")
  @Override
  public void clickElementByCss(String css) {
    findElementByCss(css).clickElement();
  }

  @Step("Click element by key <key>")
  @Override
  public void clickElementByKey(String key) {
    findElementByKey(key).clickElement();
  }

  @Step("Send keys to element <keys>")
  @Override
  public void sendKeys(String keys) {
    elementAction.sendKeys(keys);
  }

  @Step("Send keys to element by id <id> and <keys>")
  @Override
  public void sendKeysById(String id, String keys) {
    findElementById(id).sendKeys(keys);
  }

  @Step("Send keys to element by css <css> and <keys>")
  @Override
  public void sendKeysByCss(String css, String keys) {
    findElementByCss(css).sendKeys(keys);
  }

  @Step("Send keys to element by key <key> and <keys>")
  @Override
  public void sendKeysByKey(String key, String keys) {
    findElementByKey(key).sendKeys(keys);
  }

  @Step("Press key code <keyCode>")
  @Override
  public void pressKeyCode(String keys) {
    elementAction.sendKeys(Keys.valueOf(keys));
  }

  @Step("Press key code by id <id> and <keyCode>")
  @Override
  public void pressKeyCodeById(String id, String keys) {
    findElementById(id).pressKeyCode(keys);
  }

  @Step("Press key code by css <css> and <keyCode>")
  @Override
  public void pressKeyCodeByCss(String css, String keys) {
    findElementByCss(css).pressKeyCode(keys);
  }

  @Step("Press key code by key <key> and <keyCode>")
  @Override
  public void pressKeyCodeByKey(String key, String keys) {
    findElementByKey(key).pressKeyCode(keys);
  }

  @Step("Select option value <value>")
  @Override
  public void selectOptionValue(String value) {
    elementAction.selectComboItem(value);
  }

  @Step("Select option value by id <id> and <value>")
  @Override
  public void selectOptionValueById(String id, String value) {
    findElementById(id).selectOptionValue(value);
  }

  @Step("Select option value by css <css> and <value>")
  @Override
  public void selectOptionValueByCss(String css, String value) {
    findElementByCss(css).selectOptionValue(value);
  }

  @Step("Select option value by key <key> and <value>")
  @Override
  public void selectOptionValueByKey(String key, String value) {
    findElementByKey(key).selectOptionValue(value);
  }

  @Step("Hover element")
  @Override
  public void hoverElement() {
    elementAction.hover();
  }

  @Step("Hover element by id <id>")
  @Override
  public void hoverElementById(String id) {
    findElementById(id).hoverElement();
  }

  @Step("Hover element by css <css>")
  @Override
  public void hoverElementByCss(String css) {
    findElementByCss(css).hoverElement();
  }

  @Step("Hover element by key <key>")
  @Override
  public void hoverElementByKey(String key) {
    findElementByKey(key).hoverElement();
  }

}
