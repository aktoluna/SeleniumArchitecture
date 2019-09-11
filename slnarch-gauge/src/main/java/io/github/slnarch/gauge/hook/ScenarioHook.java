package io.github.slnarch.gauge.hook;

import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import io.aktoluna.slnarch.core.di.page.InjectablePageTestImpl;
import io.aktoluna.slnarch.core.helper.ConfigurationHelper;

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
