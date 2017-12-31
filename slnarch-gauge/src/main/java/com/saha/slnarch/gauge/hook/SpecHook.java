package com.saha.slnarch.gauge.hook;

import com.thoughtworks.gauge.AfterSpec;
import com.thoughtworks.gauge.BeforeSpec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpecHook {

  Logger logger = LoggerFactory.getLogger(getClass());

  @BeforeSpec
  public void beforeSpec() {
    logger.info("Before Spec");
  }


  @AfterSpec
  public void afterSpec() {
    logger.info("After Spec");
  }

}
