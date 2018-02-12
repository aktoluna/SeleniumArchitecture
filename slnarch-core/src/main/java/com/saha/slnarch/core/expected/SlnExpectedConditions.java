package com.saha.slnarch.core.expected;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SlnExpectedConditions {

  private static Logger logger = LoggerFactory.getLogger(SlnExpectedConditions.class.getName());

  private SlnExpectedConditions() {

  }

  public static ExpectedCondition<Boolean> textContains(final By locator, final String value) {
    return new ExpectedCondition<Boolean>() {
      private String currentValue = null;

      @Override
      public Boolean apply(WebDriver driver) {
        try {
          currentValue = findElement(locator, driver).getText();
          return currentValue.contains(value);
        } catch (Exception e) {
          return false;
        }
      }

      @Override
      public String toString() {
        return String.format("text contains \"%s\". Current text: \"%s\"", value, currentValue);
      }
    };
  }

  public static ExpectedCondition<Boolean> textContains(final WebElement element,
      final String value) {
    return new ExpectedCondition<Boolean>() {
      private String currentValue = null;

      @Override
      public Boolean apply(WebDriver driver) {
        try {
          currentValue = element.getText();
          return currentValue.contains(value);
        } catch (Exception e) {
          return false;
        }
      }

      @Override
      public String toString() {
        return String.format("text contains \"%s\". Current text: \"%s\"", value, currentValue);
      }
    };
  }

  public static ExpectedCondition<WebElement> textContainsAny(final By locator,
      final String value) {
    return new ExpectedCondition<WebElement>() {
      private String currentValue = null;

      @Override
      public WebElement apply(WebDriver driver) {
        WebElement foundElement = null;
        List<WebElement> elements = findElements(locator, driver);
        for (WebElement element : elements) {
          if (!element.isDisplayed()) {
            return null;
          }
          if (element.getText().toUpperCase()
              .contains(value.toUpperCase())) {
            foundElement = element;
          }
        }
        return foundElement != null && elements.size() > 0 ? foundElement : null;
      }

      @Override
      public String toString() {
        return String.format("text contains any \"%s\". Current text: \"%s\"", value, currentValue);
      }
    };
  }

  public static ExpectedCondition<WebElement> textContainsAny(final List<WebElement> elements,
      final String value) {
    return new ExpectedCondition<WebElement>() {
      private String currentValue = null;

      @Override
      public WebElement apply(WebDriver driver) {
        WebElement foundElement = null;
        for (WebElement element : elements) {
          if (!element.isDisplayed()) {
            return null;
          }
          if (element.getText().toUpperCase()
              .contains(value.toUpperCase())) {
            foundElement = element;
            break;
          }
        }
        return foundElement != null && elements.size() > 0 ? foundElement : null;
      }

      @Override
      public String toString() {
        return String.format("text contains any \"%s\". Current text: \"%s\"", value, currentValue);
      }
    };
  }

  public static ExpectedCondition<Boolean> textEquals(final By locator, final String value) {
    return new ExpectedCondition<Boolean>() {
      private String currentValue = null;

      @Override
      public Boolean apply(WebDriver driver) {
        try {
          currentValue = findElement(locator, driver).getText();
          return currentValue.equals(value);
        } catch (Exception e) {
          return false;
        }
      }

      @Override
      public String toString() {
        return String.format("text equals \"%s\". Current text: \"%s\"", value, currentValue);
      }
    };
  }

  public static ExpectedCondition<Boolean> textEquals(final WebElement element,
      final String value) {
    return new ExpectedCondition<Boolean>() {
      private String currentValue = null;

      @Override
      public Boolean apply(WebDriver driver) {
        try {
          currentValue = element.getText();
          return currentValue.equals(value);
        } catch (Exception e) {
          return false;
        }
      }

      @Override
      public String toString() {
        return String.format("text equals \"%s\". Current text: \"%s\"", value, currentValue);
      }
    };
  }

  public static ExpectedCondition<WebElement> textEqualsAny(final By locator, final String value) {
    return new ExpectedCondition<WebElement>() {
      private String currentValue = null;

      @Override
      public WebElement apply(WebDriver driver) {
        WebElement foundElement = null;
        List<WebElement> elements = findElements(locator, driver);
        for (WebElement element : elements) {
          if (!element.isDisplayed()) {
            return null;
          }
          if (element.getText().equals(value)) {
            foundElement = element;
            break;
          }
        }
        return foundElement != null && elements.size() > 0 ? foundElement : null;
      }

      @Override
      public String toString() {
        return String.format("text equals any \"%s\". Current text: \"%s\"", value, currentValue);
      }
    };
  }

  public static ExpectedCondition<WebElement> textEqualsAny(final List<WebElement> elements,
      final String value) {
    return new ExpectedCondition<WebElement>() {
      private String currentValue = null;

      @Override
      public WebElement apply(WebDriver driver) {
        WebElement foundElement = null;
        for (WebElement element : elements) {
          if (!element.isDisplayed()) {
            return null;
          }
          if (element.getText().equals(value)) {
            foundElement = element;
            break;
          }
        }
        return foundElement != null && elements.size() > 0 ? foundElement : null;
      }

      @Override
      public String toString() {
        return String.format("text equals any \"%s\". Current text: \"%s\"", value, currentValue);
      }
    };
  }

  public static ExpectedCondition<Boolean> clickAndUrlChange(final WebElement element,
      String newUrl) {
    return new ExpectedCondition<Boolean>() {
      private String currentUrl = "";

      @Override
      public Boolean apply(WebDriver driver) {
        try {
          currentUrl = driver.getCurrentUrl();
          if (element != null && element.isEnabled()) {
            element.click();
          }
          return currentUrl != null && currentUrl.contains(newUrl);
        } catch (StaleElementReferenceException e) {
          return null;
        }
      }

      @Override
      public String toString() {
        return "element click: " + element + String
            .format(" url to contain \"%s\". Current url: \"%s\"", newUrl, currentUrl);
      }
    };
  }


  private static WebElement findElement(By by, WebDriver driver) {
    try {
      return findElements(by, driver).stream().findFirst().orElseThrow(
          () -> new NoSuchElementException("Cannot locate an element using " + by));
    } catch (NoSuchElementException e) {
      throw e;
    } catch (WebDriverException e) {
      logger.warn(String.format("WebDriverException thrown by findElement(%s)", by), e);
      throw e;
    }
  }

  private static List<WebElement> findElements(By by, WebDriver driver) {
    try {
      return driver.findElements(by);
    } catch (WebDriverException e) {
      logger.warn(String.format("WebDriverException thrown by findElements(%s)", by), e);
      throw e;
    }
  }

}
