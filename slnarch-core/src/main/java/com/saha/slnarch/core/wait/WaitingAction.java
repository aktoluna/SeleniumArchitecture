package com.saha.slnarch.core.wait;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

public interface WaitingAction<T extends WaitingAction> {

  T waitAjaxComplete();

  T waitPageLoadComplete();

  T waitForAngularLoad();

  T waitJQueryComplete();

  T waitPageScrollingComplete();

  T stopPageLoad();

  T switchToFrame(String frameName);

  T switchToFrameElement(By byFrameName);

  T switchToFrameElement(WebElement element);

  <V> V waitUntil(ExpectedCondition expectedCondition);

  <V> V waitUntilByFrame(ExpectedCondition expectedCondition);

  void waitAll();

  void waitByMs(long millis);
}
