package io.aktoluna.slnarch.core.driver;

import io.aktoluna.slnarch.core.expected.SlnExpectedConditions;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public interface DriverExpectedConditionsType {

  default ExpectedCondition<Boolean> URL_TO_BE(String url) {
    return ExpectedConditions.urlToBe(url);
  }

  default ExpectedCondition<Boolean> URL_CONTAINS(String url) {
    return ExpectedConditions.urlContains(url);
  }

  default ExpectedCondition<Boolean> URL_MATCHES(String url) {
    return ExpectedConditions.urlMatches(url);
  }

  default ExpectedCondition<Boolean> TITLE_EQUALS(String title) {
    return ExpectedConditions.titleIs(title);
  }

  default ExpectedCondition<Boolean> TITLE_CONTAINS(String title) {
    return ExpectedConditions.titleContains(title);
  }

  default ExpectedCondition<Boolean> TITLE_MATCHES(String title) {
    return SlnExpectedConditions.titleMatches(title);
  }

  default ExpectedCondition<Boolean> PAGE_SOURCE_CONTAINS(String searchText) {
    return SlnExpectedConditions.pageSourcesContains(searchText);
  }

  default ExpectedCondition<Boolean> PAGE_SOURCE_MATCHES(String searchText) {
    return SlnExpectedConditions.pageSourcesMatches(searchText);
  }

  default ExpectedCondition<WebDriver> FRAME_AVAILABLE(By by) {
    return ExpectedConditions.frameToBeAvailableAndSwitchToIt(by);
  }

  default ExpectedCondition<WebDriver> FRAME_AVAILABLE(WebElement webElement) {
    return ExpectedConditions.frameToBeAvailableAndSwitchToIt(webElement);
  }

  default ExpectedCondition<WebDriver> FRAME_AVAILABLE(String frameLocator) {
    return ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator);
  }

  default ExpectedCondition<WebDriver> FRAME_AVAILABLE(int frameLocator) {
    return ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator);
  }

  default ExpectedCondition<Alert> ALERT_PRESENT() {
    return ExpectedConditions.alertIsPresent();
  }


}
