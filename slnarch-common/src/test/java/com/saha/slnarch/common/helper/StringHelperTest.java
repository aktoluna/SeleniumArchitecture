package com.saha.slnarch.common.helper;

import static org.junit.Assert.assertEquals;

import com.saha.slnarch.common.helper.StringHelper;
import org.junit.Test;


public class StringHelperTest {

  @Test
  public void isEmpty() throws Exception {
    String empty="";
    assertEquals(true, StringHelper.isEmpty(empty));
    assertEquals(false,StringHelper.isEmpty("a"));
  }

  @Test
  public void convertStringToInt() throws Exception {
    int a=5;
    String aNumber="5";
    assertEquals(a,StringHelper.convertStringToInt(aNumber));
  }

}