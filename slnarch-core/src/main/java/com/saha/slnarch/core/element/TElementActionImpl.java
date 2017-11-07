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
  public WebElement find(String name) {
    return find(getByCreate().createBy(name));
  }

  @Override
  public WebElement find(ByType byType, String name) {
    return find(ByFactory.buildBy(byType).createBy(name));
  }

  @Override
  public WebElement find(ElementInfo elementInfo) {
    return find(elementInfo.getType(), elementInfo.getKey());
  }

  @Override
  public WebElement find(By by) {
    return driver.findElement(by);
  }

  @Override
  public WebElement find(By by, int index) {
    return driver.findElements(by).get(index);
  }

  @Override
  public WebElement find(String name, int index) {
    return find(getByCreate().createBy(name), index);
  }

  @Override
  public WebElement findIn(WebElement parent, By by, int index) {
    return parent.findElements(by).get(index);
  }

  @Override
  public WebElement findIn(WebElement parent, String name, int index) {
    return findIn(parent, getByCreate().createBy(name), index);
  }

  @Override
  public List<WebElement> finds(By by) {
    return driver.findElements(by);
  }

  @Override
  public List<WebElement> finds(String name) {
    return finds(getByCreate().createBy(name));
  }

  @Override
  public ElementAction sendKeys(WebElement webElement, String keys) {
    webElement.sendKeys(keys);
    return this;
  }

  @Override
  public ElementAction sendKeys(String name, String keys) {
    sendKeys(find(name), keys);
    return this;
  }

  @Override
  public ElementAction sendKeys(By by, String keys) {
    sendKeys(find(by), keys);
    return this;
  }

  @Override
  public ElementAction click(WebElement webElement) {
    webElement.click();
    return this;
  }

  @Override
  public ElementAction click(By by) {
    click(find(by));
    return this;
  }

  @Override
  public ElementAction click(String name) {
    click(find(name));
    return this;
  }

  @Override
  public ElementAction click(ByType byType, String name) {
    click(find(byType, name));
    return this;
  }

  @Override
  public ElementAction click(ElementInfo elementInfo) {
    click(find(elementInfo));
    return this;
  }

  @Override
  public ElementAction hover(WebElement element) {
    new Actions(driver).moveToElement(element).build().perform();
    return this;
  }

  @Override
  public ElementAction hover(By by) {
    hover(find(by));
    return this;
  }

  @Override
  public ElementAction hover(String name) {
    hover(find(name));
    return this;
  }

  @Override
  public ElementAction hoverByNth(By by, int index) {
    hover(find(by, index));
    return this;
  }

  @Override
  public ElementAction hoverByNth(String name, int index) {
    hover(find(name, index));
    return this;
  }

  @Override
  public ElementAction scrollTo(WebElement element) {
//    scrollToWithJs(element.getLocation().getX(), element.getLocation().getY());
    return this;
  }

  @Override
  public ElementAction scrollTo(By by) {
    scrollTo(find(by));
    return this;
  }

  @Override
  public ElementAction scrollTo(String name) {
    scrollTo(find(name));
    return this;
  }

  @Override
  public ElementAction scrollToByNth(By by, int... index) {
    return scrollTo(finds(by).get(index[0]));
  }

  @Override
  public ElementAction scrollToByNth(String name, int... index) {
    return scrollTo(finds(name).get(index[0]));
  }

  @Override
  public ElementAction scrollTo(WebElement element, int x, int y) {
    return null;
  }

  @Override
  public ElementAction scrollTo(By by, int x, int y) {
    return null;
  }

  @Override
  public ElementAction scrollTo(String name, int x, int y) {
    return null;
  }

  @Override
  public ElementAction selectComboItem(WebElement element, String value) {
    new Select(element).selectByVisibleText(value);
    return this;
  }

  @Override
  public ElementAction selectComboItem(By by, String value) {
    return selectComboItem(find(by), value);
  }

  @Override
  public ElementAction selectComboItem(String name, String value) {
    return selectComboItem(find(name), value);
  }

  @Override
  public String getAttributeValue(String name, int... attributeIndex) {
    return null;
  }


}
