package com.saha.slnarch.common.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogHelper {

  private LogHelper() {
  }

  public static Logger getSlnLogger() {
    return LoggerFactory.getLogger("com.saha.slnarch");
  }
}
