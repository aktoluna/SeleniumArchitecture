package com.saha.slnarch.core.expected;

import com.saha.slnarch.core.expected.SlnExpectedConditions;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ElementExpectedType {

  public static ExpectedCondition<WebElement> PRESENCE(By by) {
    return ExpectedConditions.presenceOfElementLocated(by);
  }

  public static ExpectedCondition<List<WebElement>> PRESENCES(By by) {
    return ExpectedConditions.presenceOfAllElementsLocatedBy(by);
  }

  public static ExpectedCondition<WebElement> CLICK(By by) {
    return ExpectedConditions.elementToBeClickable(by);
  }

  public static ExpectedCondition<WebElement> CLICK(WebElement webElement) {
    return ExpectedConditions.elementToBeClickable(webElement);
  }

  public static ExpectedCondition<Boolean> CLICK_AND_URL_CONTAINS(WebElement element, String url) {
    return SlnExpectedConditions.clickAndUrlChange(element, url);
  }

  public static ExpectedCondition<WebElement> VISIBLE(By by) {
    return ExpectedConditions.visibilityOfElementLocated(by);
  }

  public static ExpectedCondition<WebElement> VISIBLE(WebElement webElement) {
    return ExpectedConditions.visibilityOf(webElement);
  }

  public static ExpectedCondition<List<WebElement>> VISIBLE_ALL(By by) {
    return ExpectedConditions.visibilityOfAllElementsLocatedBy(by);
  }

  public static ExpectedCondition<List<WebElement>> VISIBLE_ALL(List<WebElement> webElements) {
    return ExpectedConditions.visibilityOfAllElements(webElements);
  }

  public static ExpectedCondition<Boolean> INVISIBLE(By by) {
    return ExpectedConditions.invisibilityOfElementLocated(by);
  }

  public static ExpectedCondition<Boolean> INVISIBLE(WebElement webElement) {
    return ExpectedConditions.invisibilityOf(webElement);
  }

  public static ExpectedCondition<Boolean> INVISIBLE_ALL(By by) {
    return ExpectedConditions.invisibilityOfElementLocated(by);
  }

  public static ExpectedCondition<Boolean> INVISIBLE_ALL(List<WebElement> webElement) {
    return ExpectedConditions.invisibilityOfAllElements(webElement);
  }

  public static ExpectedCondition<Boolean> SELECTED(By by) {
    return ExpectedConditions.elementToBeSelected(by);
  }

  public static ExpectedCondition<Boolean> SELECTED(WebElement webElement) {
    return ExpectedConditions.elementToBeSelected(webElement);
  }

  public static ExpectedCondition<Boolean> SELECTED_STATE(By by, boolean state) {
    return ExpectedConditions.elementSelectionStateToBe(by, state);
  }

  public static ExpectedCondition<Boolean> SELECTED_STATE(WebElement webElement, boolean state) {
    return ExpectedConditions.elementSelectionStateToBe(webElement, state);
  }

  public static ExpectedCondition<Boolean> CONTAIN_TEXT(By by, String text) {
    return SlnExpectedConditions.textContains(by, text);
  }

  public static ExpectedCondition<Boolean> CONTAIN_TEXT(WebElement element, String text) {
    return SlnExpectedConditions.textContains(element, text);
  }

  public static ExpectedCondition<WebElement> CONTAIN_TEXT_ANY(By by, String text) {
    return SlnExpectedConditions.textContainsAny(by, text);
  }

  public static ExpectedCondition<WebElement> CONTAIN_TEXT_ANY(List<WebElement> elements, String text) {
    return SlnExpectedConditions.textContainsAny(elements, text);
  }

  public static ExpectedCondition<Boolean> EQUALS_TEXT(By by, String text) {
    return SlnExpectedConditions.textEquals(by, text);
  }

  public static ExpectedCondition<Boolean> EQUALS_TEXT(WebElement element, String text) {
    return SlnExpectedConditions.textEquals(element, text);
  }

  public static ExpectedCondition<WebElement> EQUALS_TEXT_ANY(By by, String text) {
    return SlnExpectedConditions.textEqualsAny(by, text);
  }

  public static ExpectedCondition<WebElement> EQUALS_TEXT_ANY(List<WebElement> elements, String text) {
    return SlnExpectedConditions.textEqualsAny(elements, text);
  }
}
