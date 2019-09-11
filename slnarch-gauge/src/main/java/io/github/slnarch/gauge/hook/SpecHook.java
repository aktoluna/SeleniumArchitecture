package io.github.slnarch.gauge.hook;

import com.thoughtworks.gauge.AfterSpec;
import com.thoughtworks.gauge.BeforeSpec;
import io.aktoluna.slnarch.common.log.LogHelper;
import org.slf4j.Logger;

public class SpecHook {

  Logger logger = LogHelper.getSlnLogger();

  @BeforeSpec
  public void beforeSpec() {
    logger.debug("Before Spec");
  }


  @AfterSpec
  public void afterSpec() {
    logger.debug("After Spec");
  }

}
