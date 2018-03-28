package com.saha.slnarch.core.expected;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DriverExpectedConditionsType {

  public static ExpectedCondition<Boolean> URL_TO_BE(String url) {
    return ExpectedConditions.urlToBe(url);
  }

  public static ExpectedCondition<Boolean> URL_CONTAINS(String url) {
    return ExpectedConditions.urlContains(url);
  }

  public static ExpectedCondition<Boolean> URL_MATCHES(String url) {
    return ExpectedConditions.urlMatches(url);
  }

  public static ExpectedCondition<Boolean> TITLE_EQUALS(String title) {
    return ExpectedConditions.titleIs(title);
  }

  public static ExpectedCondition<Boolean> TITLE_CONTAINS(String title) {
    return ExpectedConditions.titleContains(title);
  }

  public static ExpectedCondition<Boolean> TITLE_MATCHES(String title) {
    return SlnExpectedConditions.titleMatches(title);
  }

  public static ExpectedCondition<Boolean> PAGE_SOURCE_CONTAINS(String searchText) {
    return SlnExpectedConditions.pageSourcesContains(searchText);
  }

  public static ExpectedCondition<Boolean> PAGE_SOURCE_MATCHES(String searchText) {
    return SlnExpectedConditions.pageSourcesMatches(searchText);
  }

  public static ExpectedCondition<WebDriver> FRAME_AVAILABLE(By by) {
    return ExpectedConditions.frameToBeAvailableAndSwitchToIt(by);
  }

  public static ExpectedCondition<WebDriver> FRAME_AVAILABLE(WebElement webElement) {
    return ExpectedConditions.frameToBeAvailableAndSwitchToIt(webElement);
  }

  public static ExpectedCondition<WebDriver> FRAME_AVAILABLE(String frameLocator) {
    return ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator);
  }

  public static ExpectedCondition<WebDriver> FRAME_AVAILABLE(int frameLocator) {
    return ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator);
  }

  public static ExpectedCondition<Alert> ALERT_PRESENT() {
    return ExpectedConditions.alertIsPresent();
  }


}
