package com.saha.slnarch.common.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogHelper {

  private static Logger logger = LoggerFactory.getLogger(LogHelper.class);

  public static void error(String message, Throwable throwable) {
    logger.error(message, throwable);
  }
}
