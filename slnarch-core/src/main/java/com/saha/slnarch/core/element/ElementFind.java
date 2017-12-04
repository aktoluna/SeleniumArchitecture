package com.saha.slnarch.core.element;

import com.saha.slnarch.core.element.by.ByCreate;
import com.saha.slnarch.core.element.by.ByType;
import com.saha.slnarch.core.model.ElementInfo;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public interface ElementFind<T extends Element,S extends WebElement> {

  ByCreate getByCreate();

  T setByCreate(ByCreate byCreate);

  T find(By by);

  T find(String name);

  T find(ByType byType, String name);

  T find(ElementInfo elementInfo);

  T find(By by, int index);

  T find(String name, int index);

  T findIn(WebElement parent, By by, int index);

  T findIn(WebElement parent, String name, int index);

  T finds(By by);

  T finds(String name);

  List<WebElement> findElements(By by);

  List<WebElement> findElements(String name);

  T setElementList(WebElement element);

  T setElementList(List<WebElement> elements);

  T addElementList(WebElement element);

  T addElementList(List<WebElement> elements);

  T clearElementList();

  List<WebElement> getElements();

  WebElement getElement(int index);

  WebElement getElement();
}
