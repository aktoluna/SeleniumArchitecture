package com.saha.slnarch.report;

import com.saha.slnarch.di.Injectable;
import com.saha.slnarch.di.page.InjectablePage;
import com.saha.slnarch.report.screenshot.ScreenShotProxy;

public abstract class InjectableReportPage<T extends InjectableReportPage<T>> extends
    InjectablePage {

  public InjectableReportPage() {
    super();
    Injectable injectable = ScreenShotProxy.getNewProxy(this, Injectable.class);
  }

}
