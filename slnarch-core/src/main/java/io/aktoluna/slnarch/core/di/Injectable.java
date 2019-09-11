package io.aktoluna.slnarch.core.di;

import io.aktoluna.slnarch.common.log.LogHelper;

public interface Injectable {

  default void inject() {
    LogHelper.getSlnLogger().info("Inject Starting...");
    InjectionHelper.getInstance().getFeather().injectFields(this);
  }
}
