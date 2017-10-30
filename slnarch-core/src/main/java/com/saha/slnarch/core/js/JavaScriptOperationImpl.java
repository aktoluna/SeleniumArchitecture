package com.saha.slnarch.core.js;

import javax.inject.Inject;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public final class JavaScriptOperationImpl implements JavaScriptOperation {

  private final WebDriver driver;

  @Inject
  public JavaScriptOperationImpl(WebDriver driver) {
    this.driver = driver;
  }


  @Override
  public JavascriptExecutor getJSExecutor() {
    return (JavascriptExecutor) driver;
  }

  @Override
  public Object executeJS(String jsStmt, boolean wait) {
    return wait ? executeJS(jsStmt) : executeJSAsync(jsStmt);
  }

  @Override
  public Object executeJS(String jsStmt, boolean wait, Object... obj) {
    return wait ? executeJS(jsStmt, obj) : executeJSAsync(jsStmt, obj);
  }

  @Override
  public Object executeJS(String jsStmt) {

    return getJSExecutor().executeScript(jsStmt);
  }

  @Override
  public Object executeJS(String script, Object... obj) {
    return getJSExecutor().executeScript(script, obj);
  }

  @Override
  public Object executeJSAsync(String jsStmt) {
    return getJSExecutor().executeAsyncScript(jsStmt);
  }

  @Override
  public Object executeJSAsync(String script, Object... obj) {
    return getJSExecutor().executeAsyncScript(script, obj);
  }

  @Override
  public boolean executeBoolJS(String jsStmt) {
    return (boolean) executeJS(jsStmt, true);
  }

  @Override
  public String executeStringJS(String jsStmt) {
    return (String) executeJS(jsStmt, true);
  }
}
