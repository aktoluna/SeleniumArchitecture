package com.saha.slnarch.di.page;

import com.saha.slnarch.core.listener.WaitEventListener;
import com.saha.slnarch.core.page.PageTestImpl;
import com.saha.slnarch.di.helper.InjectionHelper;
import com.saha.slnarch.di.module.CommonModule;
import com.saha.slnarch.di.module.DriverModule;
import org.codejargon.feather.Feather;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public abstract class InjectablePageTestImpl extends PageTestImpl {

  protected InjectablePageTestImpl() {
    super();
    initFeather();
    setEventListener();
  }


  private void initFeather() {
    logger.info("Init Feather Injections");
    InjectionHelper.getInstance()
        .setFeather(Feather.with(new DriverModule(getDriver()), new CommonModule()));
  }


  public Feather getFeather() {
    return InjectionHelper.getInstance().getFeather();
  }


  public void setFeather(Feather feather) {
    InjectionHelper.getInstance().setFeather(feather);
  }

  @Override
  public void setEventListener() {
    if (useEventDriver()) {
      WaitEventListener waitEventListener = getFeather().instance(WaitEventListener.class);
      ((EventFiringWebDriver) getDriver()).register(waitEventListener);
    }
  }

}
