package com.saha.slnarch.junit;

import com.saha.slnarch.core.page.PageTestImpl;
import com.saha.slnarch.junit.rule.SlnTestWatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;

public abstract class JunitPageTestImpl extends PageTestImpl {

  @Rule
  public SlnTestWatcher slnTestWatcher = new SlnTestWatcher();

  @Before
  public void beforeTest() {
    getDriver().get(getConfiguration().getBaseUrl());
  }

  @After
  public void afterTest() {
    getDriver().quit();
  }
}
