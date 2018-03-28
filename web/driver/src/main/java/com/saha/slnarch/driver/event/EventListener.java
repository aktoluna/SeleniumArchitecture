package com.saha.slnarch.driver.event;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

public interface EventListener extends WebDriverEventListener {

  void beforeFindsBy(By by, WebDriver driver);

  void afterFindsBy(By by, List<WebElement> element, WebDriver driver);

  void beforeFindByInElement(By by, WebElement element, WebDriver driver);

  void afterFindByInElement(By by,WebElement element, WebElement innerElement, WebDriver driver);

  void beforeFindsByInElement(By by, WebElement element, WebDriver driver);

  void afterFindsByInElement(By by, WebElement element,List<WebElement> innerElements, WebDriver driver);

}
