package io.aktoluna.slnarch.core.driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public interface DriverExpectedConditions<T extends DriverAction> extends DriverExpectedConditionsType{

  boolean urlEquals(String url);

  boolean urlContains(String url);

  boolean urlPatternMatches(String url);

  boolean titleEquals(String url);

  boolean titleContains(String url);

  boolean titlePatternMatches(String url);

  boolean pageSourceContains(String url);

  boolean pageSourceContainsPatternMatches(String url);

  boolean isAlertPresent();

  T frameAvailableSwitchTo(By by);

  T frameAvailableSwitchTo(WebElement webElement);

  T frameAvailableSwitchTo(String frameLocator);

  T frameAvailableSwitchTo(int frameLocator);

}
