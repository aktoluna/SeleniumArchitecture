package io.aktoluna.slnarch.core.helper;

import io.aktoluna.slnarch.common.file.PropertyHelper;
import io.aktoluna.slnarch.common.helper.StringHelper;
import io.aktoluna.slnarch.common.helper.SystemPropertyHelper;
import io.aktoluna.slnarch.common.log.LogHelper;
import io.aktoluna.slnarch.core.model.Configuration;
import java.io.IOException;
import org.slf4j.Logger;

public enum ConfigurationHelper {
  INSTANCE;

  Logger logger = LogHelper.getSlnLogger();

  Configuration configuration;

  ConfigurationHelper() {
    try {
      configuration = PropertyHelper
          .propertiesToClassWithAnnotation(PropertyHelper.readProperties("slnarch.properties"),
              Configuration.class);
      setBaseUrl();
    } catch (IllegalAccessException | InstantiationException | IOException e) {
      logger.error(e.getMessage());
    }
  }

  private void setBaseUrl() {
    String env = SystemPropertyHelper.getEnvKey();
    if (!StringHelper.isEmpty(env)) {
      if (env.equals("test")) {
        configuration.setBaseUrl(configuration.getTestUrl());
      } else if (env.equals("prep")) {
        configuration.setBaseUrl(configuration.getPrepUrl());
      } else if (env.equals("live")) {
        configuration.setBaseUrl(configuration.getLiveUrl());
      }
    } else {
      logger.warn("Env System Property Not Found Default Use Test Url");
      configuration.setBaseUrl(configuration.getTestUrl());
    }
  }

  public Configuration getConfiguration() {
    return configuration;
  }

  public String getBaseUrl() {
    return configuration.getBaseUrl();
  }
}
