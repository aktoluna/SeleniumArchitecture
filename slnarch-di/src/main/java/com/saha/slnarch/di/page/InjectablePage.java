package com.saha.slnarch.di.page;

import com.saha.slnarch.di.Injectable;
import com.saha.slnarch.di.helper.InjectionHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class InjectablePage implements Injectable {

  protected Logger logger = LoggerFactory.getLogger(getClass());

  protected InjectablePage() {
    inject();
  }


  @Override
  public void inject() {
    logger.info("Inject All Objects");
    InjectionHelper.getInstance().getFeather().injectFields(this);
  }
}
