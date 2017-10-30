package com.saha.slnarch.di.page;

import com.saha.slnarch.core.page.PageTestImpl;
import com.saha.slnarch.di.helper.InjectionHelper;
import com.saha.slnarch.di.module.DriverModule;
import org.codejargon.feather.Feather;
import org.openqa.selenium.support.events.WebDriverEventListener;

public abstract class InjectablePageTestImpl extends PageTestImpl implements
    InjectablePageTest {

  protected InjectablePageTestImpl() {
    super();
    initFeather();
  }

  @Override
  public void initFeather() {
    logger.info("Init Feather Injections");
    InjectionHelper.getInstance().setFeather(Feather.with(new DriverModule(getDriver())));
  }

  @Override
  public Feather getFeather() {
    return InjectionHelper.getInstance().getFeather();
  }

  @Override
  public void setFeather(Feather feather) {
    InjectionHelper.getInstance().setFeather(feather);
  }

  @Override
  public boolean useEventDriver() {
    return true;
  }
}
