package com.saha.slnarch.core.di.page;

import com.saha.slnarch.core.di.Injectable;
import com.saha.slnarch.core.di.InjectionHelper;
import com.saha.slnarch.core.di.module.CommonModule;
import com.saha.slnarch.core.di.module.DriverModule;
import com.saha.slnarch.core.listener.WaitEventListener;
import com.saha.slnarch.core.page.PageTestImpl;
import java.util.ArrayList;
import java.util.List;
import org.codejargon.feather.Feather;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public abstract class InjectablePageTestImpl extends PageTestImpl implements Injectable {

  protected InjectablePageTestImpl() {
    super();
    initFeather();
    setEventListener();
  }


  private void initFeather() {
    logger.info("Init Feather Injections");
    List<Object> moduleList = getModule() == null ? new ArrayList<>() : getModule();
    moduleList.add(new DriverModule(getDriver()));
    moduleList.add(new CommonModule());
    InjectionHelper.getInstance()
        .setFeather(Feather.with(moduleList));
  }

  protected List<Object> getModule() {
    return new ArrayList<>();
  }

  protected Feather getFeather() {
    return InjectionHelper.getInstance().getFeather();
  }


  protected void setFeather(Feather feather) {
    InjectionHelper.getInstance().setFeather(feather);
  }

  @Override
  public void setEventListener() {
    if (useEventDriver()) {
      WaitEventListener waitEventListener = getFeather().instance(WaitEventListener.class);
      waitEventListener.setConfiguration(getConfiguration());
      ((EventFiringWebDriver) getDriver()).register(waitEventListener);
    }
  }

}
