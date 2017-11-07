package com.saha.slnarch.core.element;

import com.saha.slnarch.core.element.by.ByCreate;
import com.saha.slnarch.core.element.by.ByType;
import com.saha.slnarch.core.model.ElementInfo;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public interface ElementAction<T> {

  ByCreate getByCreate();

  void setByCreate(ByCreate byCreate);

  WebElement find(By by);

  WebElement find(String name);

  WebElement find(ByType byType, String name);

  WebElement find(ElementInfo elementInfo);

  WebElement find(By by, int index);

  WebElement find(String name, int index);

  WebElement findIn(WebElement parent, By by, int index);

  WebElement findIn(WebElement parent, String name, int index);

  List<WebElement> finds(By by);

  List<WebElement> finds(String name);

  T sendKeys(WebElement webElement, String keys);

  T sendKeys(String name, String keys);

  T sendKeys(By by, String keys);

  T click(WebElement webElement);

  T click(By by);

  T click(String name);

  T click(ByType byType, String name);

  T click(ElementInfo elementInfo);

  T hover(WebElement element);

  T hover(By by);

  T hover(String name);

  T hoverByNth(By by, int index);

  T hoverByNth(String name, int index);

  T scrollTo(WebElement element);

  T scrollTo(By by);

  T scrollTo(String name);

  T scrollToByNth(By by, int... index);

  T scrollToByNth(String name, int... index);

  T scrollTo(WebElement element, int x, int y);

  T scrollTo(By by, int x, int y);

  T scrollTo(String name, int x, int y);

  T selectComboItem(WebElement element, String value);

  T selectComboItem(By by, String value);

  T selectComboItem(String name, String value);

  String getAttributeValue(String name, int... attributeIndex);

}
