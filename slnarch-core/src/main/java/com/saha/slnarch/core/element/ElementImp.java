package com.saha.slnarch.core.element;

import com.saha.slnarch.core.element.by.ByCreate;
import com.saha.slnarch.core.element.by.ByFactory;
import com.saha.slnarch.core.element.by.ByType;
import com.saha.slnarch.core.js.JavaScriptOperation;
import com.saha.slnarch.core.model.ElementInfo;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ElementImp implements Element<ElementImp> {

  private Logger logger = LoggerFactory.getLogger(ElementImp.class);
  private List<WebElement> elementList;

  final WebDriver driver;
  final JavaScriptOperation javaScriptOperation;
  ByCreate byCreate;

  @Inject
  public ElementImp(WebDriver driver, JavaScriptOperation javaScriptOperation,
      ByCreate byCreate) {
    this.driver = driver;
    this.javaScriptOperation = javaScriptOperation;
    this.byCreate = byCreate;
    this.elementList = new ArrayList<>();
  }

  @Inject
  public ElementImp(WebDriver driver, JavaScriptOperation javaScriptOperation) {
    this(driver, javaScriptOperation, ByFactory.buildBy(ByType.CSS));
  }

  @Override
  public ByCreate getByCreate() {
    return byCreate;
  }

  @Override
  public ElementImp setByCreate(ByCreate byCreate) {
    this.byCreate = byCreate;
    return this;
  }

  @Override
  public ElementImp findElement(By by) {
    logger.info("Find Element {}", by);
    set(driver.findElement(by));
    return this;
  }

  @Override
  public ElementImp findElement(String name) {
    return findElement(getByCreate().createBy(name));
  }

  @Override
  public ElementImp findElement(ByType byType, String name) {
    return findElement(ByFactory.buildBy(byType).createBy(name));
  }

  @Override
  public ElementImp findElement(ElementInfo elementInfo) {
    return findElement(ByFactory.buildBy(elementInfo.getType()).createBy(elementInfo.getKey()));
  }

  @Override
  public ElementImp findElement(By by, int index) {
    set(driver.findElements(by).get(index));
    return this;
  }

  @Override
  public ElementImp findElement(String name, int index) {
    return findElement(getByCreate().createBy(name), index);
  }

  @Override
  public ElementImp findElementIn(WebElement parent, By by, int index) {
    set(parent.findElements(by).get(index));
    return this;
  }

  @Override
  public ElementImp findElementIn(WebElement parent, String name, int index) {
    return findElementIn(parent, getByCreate().createBy(name), index);
  }

  @Override
  public ElementImp findElements(By by) {
    set(driver.findElements(by));
    return this;
  }

  @Override
  public ElementImp findElements(String name) {
    return findElements(getByCreate().createBy(name));
  }

  @Override
  public ElementImp set(WebElement element) {
    logger.info("Set Element Name={} Tag={}", element.getText(), element.getTagName());
    return clear().add(element);
  }

  @Override
  public ElementImp set(List<WebElement> elements) {
    return clear().add(elements);
  }

  @Override
  public ElementImp add(WebElement element) {
    elementList.add(element);
    return this;
  }

  @Override
  public ElementImp add(List<WebElement> elements) {
    elementList.addAll(elements);
    return this;
  }

  @Override
  public ElementImp clear() {
    elementList.clear();
    return this;
  }

  @Override
  public List<WebElement> gets() {
    return elementList;
  }

  @Override
  public WebElement gets(int index) {
    return elementList.get(index);
  }

  @Override
  public WebElement get() {
    return gets(0);
  }


  @Override
  public Actions newAction() {
    return new Actions(driver);
  }

  @Override
  public Select newSelect(WebElement element) {
    return new Select(element);
  }

  @Override
  public ElementImp clickElement(WebElement element) {
    logger.info("Click Element={}", element.getTagName());
    element.click();
    return this;
  }

  @Override
  public ElementImp clickElement(List<WebElement> elements, int index) {
    return clickElement(elements.get(index));
  }

  @Override
  public ElementImp clickElement() {
    return clickElement(get());
  }

  @Override
  public ElementImp clickElement(int index) {
    return clickElement(gets(), index);
  }

  @Override
  public ElementImp sendKeys(WebElement element, CharSequence... keys) {
    logger.info("Send Keys Element={} Value={}", element.getTagName(), keys);
    element.sendKeys(keys);
    return this;
  }

  @Override
  public ElementImp sendKeys(CharSequence... keys) {
    return sendKeys(get(), keys);
  }

  @Override
  public ElementImp sendKeys(List<WebElement> element, int index, CharSequence... keys) {
    return sendKeys(element.get(index), keys);
  }

  @Override
  public ElementImp sendKeys(int index, CharSequence... keys) {
    return sendKeys(gets(), index, keys);
  }

  @Override
  public ElementImp selectComboItem(WebElement element, String value) {
    newSelect(element).selectByValue(value);
    return this;
  }

  @Override
  public ElementImp selectComboItem(List<WebElement> elements, int index, String value) {
    return selectComboItem(elements.get(index), value);
  }

  @Override
  public ElementImp selectComboItem(String value) {
    return selectComboItem(get(), value);
  }

  @Override
  public ElementImp selectComboItem(String value, int index) {
    return selectComboItem(gets(), index, value);
  }

  @Override
  public ElementImp hoverElement(WebElement element) {
    newAction().moveToElement(element).build().perform();
    return this;
  }

  @Override
  public ElementImp hoverElement(List<WebElement> elements, int index) {
    return hoverElement(elements.get(index));
  }

  @Override
  public ElementImp hoverElement() {
    return hoverElement(get());
  }

  @Override
  public ElementImp hoverElement(int index) {
    return hoverElement(gets(), index);
  }

  @Override
  public ElementImp clickWithJs(WebDriver driver, WebElement element) {
    javaScriptOperation.executeJS("arguments[0].click();", element);
    return this;
  }

  @Override
  public ElementImp clickWithJs(By by, int... index) {
    return clickWithJs(driver, findElement(by, index[0]).get());
  }

  @Override
  public ElementImp hoverElementWithJs(WebElement element) {
    javaScriptOperation.executeJS("var element = arguments[0];"
        + "var mouseEventObj = document.createEvent('MouseEvents');"
        + "mouseEventObj.initEvent( 'mouseover', true, true );"
        + "element.dispatchEvent(mouseEventObj);", element);
    return this;
  }

  @Override
  public ElementImp highlightElementWithJs(WebElement element) {
    javaScriptOperation.executeJS("arguments[0].setAttribute('style', arguments[1]);", element,
        "color: red;border: 1px dashed red; border");
    return this;
  }

  @Override
  public ElementImp scrollToWithJs(int x, int y) {
    javaScriptOperation.executeJS(String.format("window.scrollTo(%d, %d);", x, y), true);
    return this;
  }

  @Override
  public ElementImp scrollToPageUpWithJs() {
    javaScriptOperation.executeJS("window.scrollTo(document.body.scrollHeight, 0)", true);
    return this;
  }

  @Override
  public ElementImp scrollToPageEndWithJs() {
    javaScriptOperation.executeJS("window.scrollTo(0, document.body.scrollHeight)", true);
    return this;
  }
}
