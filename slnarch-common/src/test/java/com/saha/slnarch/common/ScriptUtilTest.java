package com.saha.slnarch.common;

import javax.script.Bindings;
import javax.script.SimpleBindings;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class ScriptUtilTest {

  @Test
  public void runScript() throws Exception {
  }

  @Test
  public void runFunctionByString() throws Exception {
    String script = "function sum(a, b) { return a + b; } sum(1,2);";
    Object result = ScriptUtil.INSTANCE.runScript(script);
    System.out.println(result.toString());
  }

  @Test
  public void runFunction() throws Exception {
    String script = "a < 5 ;";
    Bindings bindings = new SimpleBindings();
    bindings.put("a", 7);
    boolean result = (boolean) ScriptUtil.INSTANCE.runScript(script, bindings);
    Assertions.assertThat(result).isFalse();
  }

}