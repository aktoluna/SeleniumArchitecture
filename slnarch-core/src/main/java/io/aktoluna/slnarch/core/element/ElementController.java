package io.aktoluna.slnarch.core.element;

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

  String attribute(String attributeName);

  String attribute(String attributeName, int index);

  String attribute(WebElement element, String attributeName);

  String attribute(List<WebElement> elements, String attributeName, int index);

  boolean isFound();

  boolean isEmpty();

  boolean isFounds();

  boolean isEmpties();

  boolean isEnabled();

  boolean isEnabled(int index);

  boolean isEnabled(WebElement element);

  boolean isEnabled(List<WebElement> element, int index);

  boolean isEnabledAll(List<WebElement> element);

  boolean isDisplayed();

  boolean isDisplayed(int index);

  boolean isDisplayed(WebElement element);

  boolean isDisplayed(List<WebElement> elements, int index);

  boolean isDisplayedAll(List<WebElement> elements);

  boolean isSelected();

  boolean isSelected(int index);

  boolean isSelected(WebElement element);

  boolean isSelected(List<WebElement> elements, int index);

  boolean isSelectedAll(List<WebElement> elements);

  boolean isAttributeContains(String attributeName, String text);

  boolean isAttributeContains(int index, String attributeName, String text);

  boolean isAttributeContains(WebElement element, String attributeName, String text);

  boolean isAttributeEquals(String attributeName, String text);

  boolean isAttributeEquals(int index, String attributeName, String text);

  boolean isAttributeEquals(WebElement element, String attributeName, String text);

  boolean isValueContains(String text);

  boolean isValueContains(int index, String text);

  boolean isValueContains(WebElement element, String text);

}
