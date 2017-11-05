package com.saha.slnarch.core.element;

import com.saha.slnarch.core.element.by.ByCreate;
import com.saha.slnarch.core.element.by.ByType;
import com.saha.slnarch.core.model.ElementInfo;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public interface ElementFind<T extends Element> {
  ByCreate getByCreate();

  T setByCreate(ByCreate byCreate);

  T findElement(By by);

  T findElement(String name);

  T findElement(ByType byType, String name);

  T findElement(ElementInfo elementInfo);

  T findElement(By by, int index);

  T findElement(String name, int index);

  T findElementIn(WebElement parent, By by, int index);

  T findElementIn(WebElement parent, String name, int index);

  T findElements(By by);

  T findElements(String name);

  T set(WebElement element);

  T set(List<WebElement> elements);

  T add(WebElement element);

  T add(List<WebElement> elements);

  T clear();

  List<WebElement> gets();

  WebElement gets(int index);

  WebElement get();
}
