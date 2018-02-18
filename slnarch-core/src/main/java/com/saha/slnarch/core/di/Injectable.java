package com.saha.slnarch.core.di;

import com.saha.slnarch.common.log.LogHelper;

public interface Injectable {

  default void inject() {
    LogHelper.getSlnLogger().info("Inject Starting...");
    InjectionHelper.getInstance().getFeather().injectFields(this);
  }
}
