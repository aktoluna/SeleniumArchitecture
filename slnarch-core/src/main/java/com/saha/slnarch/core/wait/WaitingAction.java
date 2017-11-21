package com.saha.slnarch.core.wait;

import org.openqa.selenium.support.ui.ExpectedCondition;

public interface WaitingAction<T extends WaitingAction> {

  T waitAjaxComplete();

  T waitPageLoadComplete();

  T waitForAngularLoad();

  T waitJQueryComplete();

  T waitPageScrollingComplete();

  void waitUntil(ExpectedCondition expectedCondition);

  void waitAll();

  void waitByMs(long millis);
}
