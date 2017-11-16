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
  public ElementImp find(By by) {
    WebElement element = null;
    try {
      element = driver.findElement(by);
      setElementList(element);
    } catch (Exception e) {
      logger.error("Element Not Found By={}", e, by.toString());
    }
    return this;
  }

  @Override
  public ElementImp find(String name) {
    return find(getByCreate().createBy(name));
  }

  @Override
  public ElementImp find(ByType byType, String name) {
    return find(ByFactory.buildBy(byType).createBy(name));
  }

  @Override
  public ElementImp find(ElementInfo elementInfo) {
    return find(ByFactory.buildBy(elementInfo.getType()).createBy(elementInfo.getKey()));
  }

  @Override
  public ElementImp find(By by, int index) {
    WebElement element = null;
    try {
      element = driver.findElements(by).get(index);
      setElementList(element);
    } catch (Exception e) {
      logger.error("Element Not Found By={}", e, by.toString());
    }
    return this;
  }

  @Override
  public ElementImp find(String name, int index) {
    return find(getByCreate().createBy(name), index);
  }

  @Override
  public ElementImp findIn(WebElement parent, By by, int index) {
    setElementList(parent.findElements(by).get(index));
    return this;
  }

  @Override
  public ElementImp findIn(WebElement parent, String name, int index) {
    return findIn(parent, getByCreate().createBy(name), index);
  }

  @Override
  public ElementImp finds(By by) {
    List<WebElement> elements = null;
    try {
      elements = driver.findElements(by);
      setElementList(elements);
    } catch (Exception e) {
      logger.error("Elements Not Found By={}", e, by.toString());
    }
    return this;
  }

  @Override
  public ElementImp finds(String name) {
    return finds(getByCreate().createBy(name));
  }

  @Override
  public ElementImp setElementList(WebElement element) {
    return clearElementList().addElementList(element);
  }

  @Override
  public ElementImp setElementList(List<WebElement> elements) {
    return clearElementList().addElementList(elements);
  }

  @Override
  public ElementImp addElementList(WebElement element) {
    elementList.add(element);
    return this;
  }

  @Override
  public ElementImp addElementList(List<WebElement> elements) {
    elementList.addAll(elements);
    return this;
  }

  @Override
  public ElementImp clearElementList() {
    elementList.clear();
    return this;
  }

  @Override
  public List<WebElement> getElements() {
    return elementList;
  }

  @Override
  public WebElement getElement(int index) {
    return elementList.get(index);
  }

  @Override
  public WebElement getElement() {
    return getElement(0);
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
  public ElementImp click(WebElement element) {
    element.click();
    return this;
  }

  @Override
  public ElementImp click(List<WebElement> elements, int index) {
    return click(elements.get(index));
  }

  @Override
  public ElementImp click() {
    return click(getElement());
  }

  @Override
  public ElementImp click(int index) {
    return click(getElements(), index);
  }

  @Override
  public ElementImp clear(WebElement element) {
    element.clear();
    return this;
  }

  @Override
  public ElementImp clear(List<WebElement> elements, int index) {
    return clear(elements.get(index));
  }

  @Override
  public ElementImp clear() {
    return clear(getElement());
  }

  @Override
  public ElementImp clear(int index) {
    return clear(getElements(), index);
  }

  @Override
  public ElementImp sendKeys(WebElement element, CharSequence... keys) {
    element.sendKeys(keys);
    return this;
  }

  @Override
  public ElementImp sendKeys(CharSequence... keys) {
    return sendKeys(getElement(), keys);
  }

  @Override
  public ElementImp sendKeys(List<WebElement> element, int index, CharSequence... keys) {
    return sendKeys(element.get(index), keys);
  }

  @Override
  public ElementImp sendKeys(int index, CharSequence... keys) {
    return sendKeys(getElements(), index, keys);
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
    return selectComboItem(getElement(), value);
  }

  @Override
  public ElementImp selectComboItem(String value, int index) {
    return selectComboItem(getElements(), index, value);
  }

  @Override
  public ElementImp hover(WebElement element) {
    newAction().moveToElement(element).build().perform();
    return this;
  }

  @Override
  public ElementImp hover(List<WebElement> elements, int index) {
    return hover(elements.get(index));
  }

  @Override
  public ElementImp hover() {
    return hover(getElement());
  }

  @Override
  public ElementImp hover(int index) {
    return hover(getElements(), index);
  }

  @Override
  public ElementImp scrollTo(WebElement element) {
    logger.info("Scroll To Element");
    newAction().moveToElement(element).build().perform();
    return this;
  }

  @Override
  public ElementImp scrollTo(List<WebElement> elements, int index) {
    return scrollTo(elements.get(index));
  }

  @Override
  public ElementImp scrollTo() {
    return scrollTo(getElement());
  }

  @Override
  public ElementImp scrollTo(int index) {
    return scrollTo(getElements(), index);
  }

  @Override
  public ElementImp clickWithJs(WebDriver driver, WebElement element) {
    javaScriptOperation.executeJS("arguments[0].click();", element);
    return this;
  }

  @Override
  public ElementImp clickWithJs(By by, int... index) {
    return clickWithJs(driver, find(by, index[0]).getElement());
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

  @Override
  public ElementImp scrollToJs(WebElement element) {
    return scrollToWithJs(element.getLocation().getX(), element.getLocation().getY());
  }

  @Override
  public ElementImp scrollToJs(List<WebElement> elements, int index) {
    return scrollToJs(elements.get(index));
  }

  @Override
  public ElementImp scrollToJs(int index) {
    return scrollToJs(getElements(), index);
  }

  @Override
  public ElementImp scrollToJs() {
    return scrollToJs(getElement());
  }
}
