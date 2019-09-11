package io.aktoluna.slnarch.common.script;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import javax.script.Bindings;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleBindings;

public enum ScriptHelper {
  INSTANCE;

  private final String SCRIPT_ENGINE_NASHORN = "nashorn";

  public Object runFunction(String path, String functionName, Object args)
      throws FileNotFoundException, ScriptException, NoSuchMethodException {
    ScriptEngine scriptEngine = getDefaultScriptEngine();
    scriptEngine.eval(new FileReader(path));
    Invocable invocable = (Invocable) scriptEngine;
    return invocable.invokeFunction(functionName, args);
  }

  public Object runScript(String script)
      throws ScriptException {
    return getDefaultScriptEngine().eval(script);
  }

  public Object runScript(String script, String key, Object value)
      throws ScriptException {
    Bindings bindings = new SimpleBindings();
    bindings.put(key, value);
    return getDefaultScriptEngine().eval(script, bindings);
  }

  public Object runScript(String script, HashMap<String, Object> bindingsMap)
      throws ScriptException {
    return getDefaultScriptEngine().eval(script, getBindingsByHashMap(bindingsMap));
  }

  public Bindings getBindingsByHashMap(HashMap<String, Object> bindingsMap) {
    Bindings bindings = new SimpleBindings();
    bindings.putAll(bindingsMap);
    return bindings;
  }

  public Object runScript(String script, Bindings bindings)
      throws ScriptException {
    return getDefaultScriptEngine().eval(script, bindings);
  }


  public ScriptEngineManager getScriptEngineManager() {
    return new ScriptEngineManager();
  }

  public ScriptEngine getDefaultScriptEngine() {
    return getScriptEngineManager().getEngineByName(SCRIPT_ENGINE_NASHORN);
  }
}
