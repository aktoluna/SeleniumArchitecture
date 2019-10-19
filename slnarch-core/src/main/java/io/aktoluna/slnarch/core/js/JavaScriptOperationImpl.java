package io.aktoluna.slnarch.core.js;

import io.aktoluna.slnarch.core.helper.ConfigurationHelper;
import javax.inject.Inject;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class JavaScriptOperationImpl implements JavaScriptOperation {

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
  public Object executeJS(String jsStmt) {
    return getJSExecutor().executeScript(jsStmt);
  }

  @Override
  public Object executeJS(String script, Object... obj) {
    return getJSExecutor().executeScript(script, obj);
  }

  @Override
  public Object executeAsyncJS(String jsStmt) {
    return getJSExecutor().executeScript(jsStmt);
  }

  @Override
  public Object executeAsyncJS(String jsStmt, Object... obj) {
    return getJSExecutor().executeScript(jsStmt, obj);
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
