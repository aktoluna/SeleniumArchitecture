package com.saha.slnarch.core.js;

import org.openqa.selenium.JavascriptExecutor;

public interface JavaScriptOperation {

  JavascriptExecutor getJSExecutor();

  Object executeJS(String jsStmt, boolean wait);

  Object executeJS(String jsStmt, boolean wait, Object... obj);

  Object executeJS(String jsStmt);

  Object executeJS(String script, Object... obj);

  Object executeJSAsync(String jsStmt);

  Object executeJSAsync(String script, Object... obj);

  boolean executeBoolJS(String jsStmt);

  String executeStringJS(String jsStmt);
}
