package com.saha.slnarch.core.model;

import com.saha.slnarch.common.helper.Prop;
import com.saha.slnarch.common.helper.Prop.PropType;

public class Configuration {

  @Prop(key = "driverPath")
  private String driverPath;
  @Prop(key = "browserType")
  private String browserType;
  @Prop(key = "baseUrl")
  private String baseUrl;
  @Prop(key = "pageTimeOut", type = PropType.INT)
  private int pageTimeOut;
  @Prop(key = "scriptTimeOut", type = PropType.INT)
  private int scriptTimeOut;
  @Prop(key = "implicitlyTimeOut", type = PropType.INT)
  private int implicitlyTimeOut;


  public String getDriverPath() {
    return driverPath;
  }

  public void setDriverPath(String driverPath) {
    this.driverPath = driverPath;
  }

  public String getBrowserType() {
    return browserType;
  }

  public void setBrowserType(String browserType) {
    this.browserType = browserType;
  }

  public int getPageTimeOut() {
    return pageTimeOut;
  }

  public void setPageTimeOut(int pageTimeOut) {
    this.pageTimeOut = pageTimeOut;
  }

  public int getScriptTimeOut() {
    return scriptTimeOut;
  }

  public void setScriptTimeOut(int scriptTimeOut) {
    this.scriptTimeOut = scriptTimeOut;
  }

  public int getImplicitlyTimeOut() {
    return implicitlyTimeOut;
  }

  public void setImplicitlyTimeOut(int implicitlyTimeOut) {
    this.implicitlyTimeOut = implicitlyTimeOut;
  }

  public String getBaseUrl() {
    return baseUrl;
  }

  public void setBaseUrl(String baseUrl) {
    this.baseUrl = baseUrl;
  }
}
