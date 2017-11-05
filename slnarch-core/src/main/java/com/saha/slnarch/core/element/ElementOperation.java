package com.saha.slnarch.core.element;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public interface ElementOperation<T extends Element> {

  Actions newAction();

  Select newSelect(WebElement element);

  T clickElement(WebElement element);

  T clickElement(List<WebElement> elements, int index);

  T clickElement();

  T clickElement(int index);

  T sendKeys(WebElement element, CharSequence... keys);

  T sendKeys(CharSequence... keys);

  T sendKeys(List<WebElement> element, int index, CharSequence... keys);

  T sendKeys(int index, CharSequence... keys);

  T selectComboItem(WebElement element, String value);

  T selectComboItem(List<WebElement> elements, int index, String value);

  T selectComboItem(String value);

  T selectComboItem(String value, int index);

  T hoverElement(WebElement element);

  T hoverElement(List<WebElement> elements, int index);

  T hoverElement();

  T hoverElement(int index);
}
