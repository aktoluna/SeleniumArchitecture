package com.saha.slnarch.core.element;

import com.saha.slnarch.core.element.by.ByCreate;
import com.saha.slnarch.core.element.by.ByFactory;
import com.saha.slnarch.core.element.by.ByType;
import com.saha.slnarch.core.js.JavaScriptOperation;
import com.saha.slnarch.core.model.ElementInfo;
import java.util.List;
import javax.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

class TElementActionImpl implements
    ElementAction<ElementAction> {

  final WebDriver driver;
  final JavaScriptOperation javaScriptOperation;
  ByCreate byCreate;

  @Inject
  public TElementActionImpl(WebDriver driver, JavaScriptOperation javaScriptOperation,
      ByCreate byCreate) {
    this.driver = driver;
    this.javaScriptOperation = javaScriptOperation;
    this.byCreate = byCreate;
  }

  @Inject
  public TElementActionImpl(WebDriver driver, JavaScriptOperation javaScriptOperation) {
    this(driver, javaScriptOperation, ByFactory.buildBy(ByType.CSS));
  }


  @Override
  public ByCreate getByCreate() {
    return byCreate;
  }

  @Override
  public void setByCreate(ByCreate byCreate) {
    this.byCreate = byCreate;
  }

  @Override
  public WebElement findElement(String name) {
    return findElement(getByCreate().createBy(name));
  }

  @Override
  public WebElement findElement(ByType byType, String name) {
    return findElement(ByFactory.buildBy(byType).createBy(name));
  }

  @Override
  public WebElement findElement(ElementInfo elementInfo) {
    return findElement(elementInfo.getType(), elementInfo.getKey());
  }

  @Override
  public WebElement findElement(By by) {
    return driver.findElement(by);
  }

  @Override
  public WebElement findElement(By by, int index) {
    return driver.findElements(by).get(index);
  }

  @Override
  public WebElement findElement(String name, int index) {
    return findElement(getByCreate().createBy(name), index);
  }

  @Override
  public WebElement findElementIn(WebElement parent, By by, int index) {
    return parent.findElements(by).get(index);
  }

  @Override
  public WebElement findElementIn(WebElement parent, String name, int index) {
    return findElementIn(parent, getByCreate().createBy(name), index);
  }

  @Override
  public List<WebElement> findElements(By by) {
    return driver.findElements(by);
  }

  @Override
  public List<WebElement> findElements(String name) {
    return findElements(getByCreate().createBy(name));
  }

  @Override
  public ElementAction sendKeys(WebElement webElement, String keys) {
    webElement.sendKeys(keys);
    return this;
  }

  @Override
  public ElementAction sendKeys(String name, String keys) {
    sendKeys(findElement(name), keys);
    return this;
  }

  @Override
  public ElementAction sendKeys(By by, String keys) {
    sendKeys(findElement(by), keys);
    return this;
  }

  @Override
  public ElementAction clickElement(WebElement webElement) {
    webElement.click();
    return this;
  }

  @Override
  public ElementAction clickElement(By by) {
    clickElement(findElement(by));
    return this;
  }

  @Override
  public ElementAction clickElement(String name) {
    clickElement(findElement(name));
    return this;
  }

  @Override
  public ElementAction clickElement(ByType byType, String name) {
    clickElement(findElement(byType, name));
    return this;
  }

  @Override
  public ElementAction clickElement(ElementInfo elementInfo) {
    clickElement(findElement(elementInfo));
    return this;
  }

  @Override
  public ElementAction hoverElement(WebElement element) {
    new Actions(driver).moveToElement(element).build().perform();
    return this;
  }

  @Override
  public ElementAction hoverElement(By by) {
    hoverElement(findElement(by));
    return this;
  }

  @Override
  public ElementAction hoverElement(String name) {
    hoverElement(findElement(name));
    return this;
  }

  @Override
  public ElementAction hoverElementByNth(By by, int index) {
    hoverElement(findElement(by, index));
    return this;
  }

  @Override
  public ElementAction hoverElementByNth(String name, int index) {
    hoverElement(findElement(name, index));
    return this;
  }

  @Override
  public ElementAction scrollToElement(WebElement element) {
//    scrollToWithJs(element.getLocation().getX(), element.getLocation().getY());
    return this;
  }

  @Override
  public ElementAction scrollToElement(By by) {
    scrollToElement(findElement(by));
    return this;
  }

  @Override
  public ElementAction scrollToElement(String name) {
    scrollToElement(findElement(name));
    return this;
  }

  @Override
  public ElementAction scrollToElementByNth(By by, int... index) {
    return scrollToElement(findElements(by).get(index[0]));
  }

  @Override
  public ElementAction scrollToElementByNth(String name, int... index) {
    return scrollToElement(findElements(name).get(index[0]));
  }

  @Override
  public ElementAction scrollToElement(WebElement element, int x, int y) {
    return null;
  }

  @Override
  public ElementAction scrollToElement(By by, int x, int y) {
    return null;
  }

  @Override
  public ElementAction scrollToElement(String name, int x, int y) {
    return null;
  }

  @Override
  public ElementAction selectComboItem(WebElement element, String value) {
    new Select(element).selectByVisibleText(value);
    return this;
  }

  @Override
  public ElementAction selectComboItem(By by, String value) {
    return selectComboItem(findElement(by), value);
  }

  @Override
  public ElementAction selectComboItem(String name, String value) {
    return selectComboItem(findElement(name), value);
  }

  @Override
  public String getAttributeValue(String name, int... attributeIndex) {
    return null;
  }


}
