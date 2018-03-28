package com.saha.slnarch.core.model;

import com.saha.slnarch.common.file.Prop;
import com.saha.slnarch.common.file.Prop.PropType;

public class Configuration {

  @Prop(key = "driverPath")
  private String driverPath;
  @Prop(key = "browserType")
  private String browserType;
  @Prop(key = "hubUrl")
  private String hubUrl;
  @Prop(key = "testUrl")
  private String testUrl;
  @Prop(key = "prepUrl")
  private String prepUrl;
  @Prop(key = "liveUrl")
  private String liveUrl;
  @Prop(key = "baseUrl")
  private String baseUrl;
  @Prop(key = "pageTimeOut", type = PropType.INT)
  private int pageTimeOut;
  @Prop(key = "scriptTimeOut", type = PropType.INT)
  private int scriptTimeOut;
  @Prop(key = "implicitlyTimeOut", type = PropType.INT)
  private int implicitlyTimeOut;
  @Prop(key = "explicitTimeOut", type = PropType.INT)
  private int explicitTimeOut;
  /*WebDriverWait Polling Milli Seconds */
  @Prop(key = "pollingTime", type = PropType.INT)
  private int pollingTime;
  @Prop(key = "waitPageLoad", type = PropType.BOOL)
  private boolean waitPageLoad;
  @Prop(key = "waitAjax", type = PropType.BOOL)
  private boolean waitAjax;
  @Prop(key = "waitAngular", type = PropType.BOOL)
  private boolean waitAngular;


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

  public boolean isWaitPageLoad() {
    return waitPageLoad;
  }

  public void setWaitPageLoad(boolean waitPageLoad) {
    this.waitPageLoad = waitPageLoad;
  }

  public boolean isWaitAjax() {
    return waitAjax;
  }

  public void setWaitAjax(boolean waitAjax) {
    this.waitAjax = waitAjax;
  }

  public boolean isWaitAngular() {
    return waitAngular;
  }

  public void setWaitAngular(boolean waitAngular) {
    this.waitAngular = waitAngular;
  }

  public void setExplicitTimeOut(int explicitTimeOut) {
    this.explicitTimeOut = explicitTimeOut;
  }

  public int getExplicitTimeOut() {
    return explicitTimeOut;
  }

  public int getPollingTime() {
    return pollingTime;
  }

  public void setPollingTime(int pollingTime) {
    this.pollingTime = pollingTime;
  }

  public String getLiveUrl() {
    return liveUrl;
  }

  public void setLiveUrl(String liveUrl) {
    this.liveUrl = liveUrl;
  }

  public String getPrepUrl() {
    return prepUrl;
  }

  public void setPrepUrl(String prepUrl) {
    this.prepUrl = prepUrl;
  }

  public String getTestUrl() {
    return testUrl;
  }

  public void setTestUrl(String testUrl) {
    this.testUrl = testUrl;
  }

  public String getHubUrl() {
    return hubUrl;
  }

  public void setHubUrl(String hubUrl) {
    this.hubUrl = hubUrl;
  }
}
