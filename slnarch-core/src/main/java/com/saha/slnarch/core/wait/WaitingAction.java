package com.saha.slnarch.core.wait;

public interface WaitingAction<T extends WaitingAction> {

  T waitAjaxComplete();

  T waitPageLoadComplete();

  T waitForAngularLoad();

  T waitJQueryComplete();

  void waitAll();

  void waitByMs(long millis) throws NullPointerException, InterruptedException;

}
