package com.saha.slnarch.gauge.hook;

import com.saha.slnarch.core.di.page.InjectablePageTestImpl;
import com.saha.slnarch.core.helper.ConfigurationHelper;
import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;

public class ScenarioHook extends InjectablePageTestImpl {

  public ScenarioHook() {
    super();
    inject();
  }

  @BeforeScenario
  public void beforeScenario() {
    logger.debug("Before Scenario");
    getDriver().navigate().to(ConfigurationHelper.INSTANCE.getBaseUrl());
  }

  @AfterScenario
  public void afterScenario() {
    logger.debug("After Scenario");
    getDriver().quit();
  }

}
