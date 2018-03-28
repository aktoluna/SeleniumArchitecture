package com.saha.slnarch.core.element;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface JavaScriptAction<T extends Element> {

  T clickWithJs(WebDriver driver, WebElement element);

  T clickWithJs(WebElement element);

  T clickWithJs();

  T clickWithJs(int index);

  T clickWithJs(By by, int... index);

  T clickWithJs(List<WebElement> elements, int index);

  T hoverElementWithJs(WebElement element);

  T highlightElementWithJs(WebElement element);

  T scrollToWithJs(int x, int y);

  T scrollToWithJs(WebElement element);

  T scrollToPageUpWithJs();

  T scrollToPageEndWithJs();

  T scrollToJs(WebElement element);

  T scrollToJs(List<WebElement> elements, int index);

  T scrollToJs(int index);

  T scrollToJs();

}