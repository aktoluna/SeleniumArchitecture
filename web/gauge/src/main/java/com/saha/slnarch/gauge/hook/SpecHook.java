package com.saha.slnarch.gauge.hook;

import com.saha.slnarch.common.log.LogHelper;
import com.thoughtworks.gauge.AfterSpec;
import com.thoughtworks.gauge.BeforeSpec;
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
