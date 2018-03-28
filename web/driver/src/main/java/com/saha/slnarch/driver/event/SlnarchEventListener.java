package com.saha.slnarch.driver.event;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

public interface SlnarchEventListener extends WebDriverEventListener {


  /**
   * Called before {@link WebDriver#findElements WebDriver.findElements(...)}, or
   * {@link WebDriver#findElements WebDriver.findElements(...)}, or {@link WebElement#findElements
   * WebElement.findElements(...)}, or {@link WebElement#findElements WebElement.findElements(...)}.
   *
   * @param element will be <code>null</code>, if a find method of <code>WebDriver</code> is called.
   * @param by locator being used
   * @param driver WebDriver
   */
  void beforeFindsBy(By by, WebElement element, WebDriver driver);

  /**
   * Called after {@link WebDriver#findElements WebDriver.findElements(...)}, or
   * {@link WebDriver#findElements WebDriver.findElements(...)}, or {@link WebElement#findElements
   * WebElement.findElements(...)}, or {@link WebElement#findElements WebElement.findElements(...)}.
   *
   * @param element will be <code>null</code>, if a find method of <code>WebDriver</code> is called.
   * @param by locator being used
   * @param driver WebDriver
   */
  void afterFindsBy(By by, List<WebElement> element, WebDriver driver);

}
