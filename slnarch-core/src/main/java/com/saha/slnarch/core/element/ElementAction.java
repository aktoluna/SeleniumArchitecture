package com.saha.slnarch.core.element;

import com.saha.slnarch.core.element.by.ByCreate;
import com.saha.slnarch.core.element.by.ByType;
import com.saha.slnarch.core.model.ElementInfo;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public interface ElementAction<T extends ElementAction> {

  ByCreate getByCreate();

  void setByCreate(ByCreate byCreate);

  WebElement findElement(By by);

  WebElement findElement(String name);

  WebElement findElement(ByType byType, String name);

  WebElement findElement(ElementInfo elementInfo);

  WebElement findElement(By by, int index);

  WebElement findElement(String name, int index);

  WebElement findElementIn(WebElement parent, By by, int index);

  WebElement findElementIn(WebElement parent, String name, int index);

  List<WebElement> findElements(By by);

  List<WebElement> findElements(String name);

  T sendKeys(WebElement webElement, String keys);

  T sendKeys(String name, String keys);

  T sendKeys(By by, String keys);

  T clickElement(WebElement webElement);

  T clickElement(By by);

  T clickElement(String name);

  T clickElement(ByType byType, String name);

  T clickElement(ElementInfo elementInfo);

  T hoverElement(WebElement element);

  T hoverElement(By by);

  T hoverElement(String name);

  T hoverElementByNth(By by, int index);

  T hoverElementByNth(String name, int index);

  T scrollToElement(WebElement element);

  T scrollToElement(By by);

  T scrollToElement(String name);

  T scrollToElementByNth(By by, int... index);

  T scrollToElementByNth(String name, int... index);

  T scrollToElement(WebElement element, int x, int y);

  T scrollToElement(By by, int x, int y);

  T scrollToElement(String name, int x, int y);


  T selectComboItem(WebElement element, String value);

  T selectComboItem(By by, String value);

  T selectComboItem(String name, String value);

  String getAttributeValue(String name, int... attributeIndex);

}
