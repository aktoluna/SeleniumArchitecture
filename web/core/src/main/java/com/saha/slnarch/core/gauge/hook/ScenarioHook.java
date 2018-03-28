package com.saha.slnarch.core.gauge.hook;

import com.saha.slnarch.core.helper.ConfigurationHelper;
import com.saha.slnarch.core.page.PageTestImpl;
import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;

public class ScenarioHook extends PageTestImpl {

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
