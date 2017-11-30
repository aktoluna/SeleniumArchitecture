package com.saha.slnarch.common;

import org.junit.Test;

public class ScriptUtilTest {

  @Test
  public void runFunctionByString() throws Exception {
    String script = "function sum(a, b) { return a + b; } sum(1,2);";
    Object result = ScriptUtil.INSTANCE.runScript(script);
    System.out.println(result.toString());
  }

  @Test
  public void runFunction() throws Exception {
  }

}