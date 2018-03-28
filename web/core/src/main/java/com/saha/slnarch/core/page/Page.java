package com.saha.slnarch.core.page;

import com.saha.slnarch.common.log.LogHelper;
import com.saha.slnarch.di.Injectable;
import org.slf4j.Logger;

public abstract class Page implements Injectable {

  protected Logger logger = LogHelper.getSlnLogger();

  protected Page() {
    inject();
  }

}
