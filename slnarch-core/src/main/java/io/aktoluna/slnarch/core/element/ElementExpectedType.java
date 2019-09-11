package io.aktoluna.slnarch.core.element;

import io.aktoluna.slnarch.core.expected.SlnExpectedConditions;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public interface ElementExpectedType {

  default ExpectedCondition<WebElement> PRESENCE(By by) {
    return ExpectedConditions.presenceOfElementLocated(by);
  }

  default ExpectedCondition<List<WebElement>> PRESENCES(By by) {
    return ExpectedConditions.presenceOfAllElementsLocatedBy(by);
  }

  default ExpectedCondition<WebElement> CLICK(By by) {
    return ExpectedConditions.elementToBeClickable(by);
  }

  default ExpectedCondition<WebElement> CLICK(WebElement webElement) {
    return ExpectedConditions.elementToBeClickable(webElement);
  }

  default ExpectedCondition<Boolean> CLICK_AND_URL_CONTAINS(WebElement element, String url) {
    return SlnExpectedConditions.clickAndUrlChange(element, url);
  }

  default ExpectedCondition<WebElement> VISIBLE(By by) {
    return ExpectedConditions.visibilityOfElementLocated(by);
  }

  default ExpectedCondition<WebElement> VISIBLE(WebElement webElement) {
    return ExpectedConditions.visibilityOf(webElement);
  }

  default ExpectedCondition<List<WebElement>> VISIBLE_ALL(By by) {
    return ExpectedConditions.visibilityOfAllElementsLocatedBy(by);
  }

  default ExpectedCondition<List<WebElement>> VISIBLE_ALL(List<WebElement> webElements) {
    return ExpectedConditions.visibilityOfAllElements(webElements);
  }

  default ExpectedCondition<Boolean> INVISIBLE(By by) {
    return ExpectedConditions.invisibilityOfElementLocated(by);
  }

  default ExpectedCondition<Boolean> INVISIBLE(WebElement webElement) {
    return ExpectedConditions.invisibilityOf(webElement);
  }

  default ExpectedCondition<Boolean> INVISIBLE_ALL(By by) {
    return ExpectedConditions.invisibilityOfElementLocated(by);
  }

  default ExpectedCondition<Boolean> INVISIBLE_ALL(List<WebElement> webElement) {
    return ExpectedConditions.invisibilityOfAllElements(webElement);
  }

  default ExpectedCondition<Boolean> SELECTED(By by) {
    return ExpectedConditions.elementToBeSelected(by);
  }

  default ExpectedCondition<Boolean> SELECTED(WebElement webElement) {
    return ExpectedConditions.elementToBeSelected(webElement);
  }

  default ExpectedCondition<Boolean> SELECTED_STATE(By by, boolean state) {
    return ExpectedConditions.elementSelectionStateToBe(by, state);
  }

  default ExpectedCondition<Boolean> SELECTED_STATE(WebElement webElement, boolean state) {
    return ExpectedConditions.elementSelectionStateToBe(webElement, state);
  }

  default ExpectedCondition<Boolean> CONTAIN_TEXT(By by, String text) {
    return SlnExpectedConditions.textContains(by, text);
  }

  default ExpectedCondition<Boolean> CONTAIN_TEXT(WebElement element, String text) {
    return SlnExpectedConditions.textContains(element, text);
  }

  default ExpectedCondition<WebElement> CONTAIN_TEXT_ANY(By by, String text) {
    return SlnExpectedConditions.textContainsAny(by, text);
  }

  default ExpectedCondition<WebElement> CONTAIN_TEXT_ANY(List<WebElement> elements, String text) {
    return SlnExpectedConditions.textContainsAny(elements, text);
  }

  default ExpectedCondition<Boolean> EQUALS_TEXT(By by, String text) {
    return SlnExpectedConditions.textEquals(by, text);
  }

  default ExpectedCondition<Boolean> EQUALS_TEXT(WebElement element, String text) {
    return SlnExpectedConditions.textEquals(element, text);
  }

  default ExpectedCondition<WebElement> EQUALS_TEXT_ANY(By by, String text) {
    return SlnExpectedConditions.textEqualsAny(by, text);
  }

  default ExpectedCondition<WebElement> EQUALS_TEXT_ANY(List<WebElement> elements, String text) {
    return SlnExpectedConditions.textEqualsAny(elements, text);
  }
}
