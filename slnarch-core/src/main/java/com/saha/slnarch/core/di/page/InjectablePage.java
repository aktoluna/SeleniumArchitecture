package com.saha.slnarch.core.di.page;

import com.saha.slnarch.common.log.LogHelper;
import com.saha.slnarch.core.di.Injectable;
import org.slf4j.Logger;

public abstract class InjectablePage implements Injectable {

  protected Logger logger = LogHelper.getSlnLogger();

  protected InjectablePage() {
    inject();
  }

}
