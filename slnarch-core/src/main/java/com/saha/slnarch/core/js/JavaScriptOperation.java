package com.saha.slnarch.core.js;

import org.openqa.selenium.JavascriptExecutor;

public interface JavaScriptOperation {

  JavascriptExecutor getJSExecutor();

  Object executeJS(String jsStmt);

  Object executeJS(String jsStmt, Object... obj);

  Object executeAsyncJS(String jsStmt);

  Object executeAsyncJS(String jsStmt, Object... obj);

  boolean executeBoolJS(String jsStmt);

  String executeStringJS(String jsStmt);
}
