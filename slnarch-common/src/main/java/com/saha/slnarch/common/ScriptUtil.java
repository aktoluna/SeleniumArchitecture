package com.saha.slnarch.common;

import java.io.FileNotFoundException;
import java.io.FileReader;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public enum ScriptUtil {
  INSTANCE;

  public Object runFunction(String path, String functionName, Object args)
      throws FileNotFoundException, ScriptException, NoSuchMethodException {
    ScriptEngine scriptEngine = getDefaultScriptEngine();
    scriptEngine.eval(new FileReader(path));
    Invocable invocable = (Invocable) scriptEngine;
    Object result = invocable.invokeFunction(functionName, args);
    return result;
  }

  public Object runScript(String script)
      throws ScriptException, NoSuchMethodException {
    ScriptEngine scriptEngine = getDefaultScriptEngine();
    Object result = scriptEngine.eval(script);
//    Invocable invocable = (Invocable) scriptEngine;
//    invocable.in
    return result;
  }

  public ScriptEngineManager getScriptEngineManager() {
    return new ScriptEngineManager();
  }

  public ScriptEngine getDefaultScriptEngine() {
    return getScriptEngineManager().getEngineByName("nashorn");
  }
}
