package com.saha.slnarch.core.element;

import java.util.List;
import org.openqa.selenium.WebElement;

public interface ElementController<T extends Element> {

  T text(WebElement element);

  T text(List<WebElement> elements, int index);

  T text();

  T text(int index);

}
