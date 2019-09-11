package io.aktoluna.slnarch.common.log;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.slf4j.Logger;

public class LogHelperTest {

  @Test
  public void getSlnLogger() {
    Logger logger = LogHelper.getSlnLogger();
    Assertions.assertThat(logger.getName()).isEqualTo("io.github.slnarch");
  }
}