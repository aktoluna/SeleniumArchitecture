package io.aktoluna.slnarch.core.di.page;

import io.aktoluna.slnarch.common.log.LogHelper;
import io.aktoluna.slnarch.core.di.Injectable;
import org.slf4j.Logger;

public abstract class InjectablePage implements Injectable {

  protected Logger logger = LogHelper.getSlnLogger();

  protected InjectablePage() {
    inject();
  }

}
