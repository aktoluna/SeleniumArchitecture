package com.saha.slnarch.core.element;

import java.util.List;
import org.openqa.selenium.WebElement;

public interface ElementController<T extends Element> {

  String text(WebElement element);

  String text(List<WebElement> elements, int index);

  String text();

  String text(int index);

  String value(WebElement element);

  String value(List<WebElement> elements, int index);

  String value();

  String value(int index);

}
