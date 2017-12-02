package com.saha.slnarch.report.screenshot;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScreenShotInvocationHandler implements InvocationHandler {

  Logger logger = LoggerFactory.getLogger(getClass());

  private Object proxied;

  public ScreenShotInvocationHandler(Object proxied) {
    this.proxied = proxied;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    Method m = proxied.getClass().getMethod(method.getName(), method.getParameterTypes());
    TakeScreenShot takeScreenShot = null;
    if (m.isAnnotationPresent(TakeScreenShot.class)) {
      takeScreenShot = m.getAnnotation(TakeScreenShot.class);
    }
    try {
      if (takeScreenShot != null && takeScreenShot.before()) {
        logger.info("TakeScreenShot before invoke");
      }
      logger.info("method run");
      return method.invoke(proxied, args);
    } finally {
      if (takeScreenShot != null && takeScreenShot.after()) {
        logger.info("TakeScreenShot after invoke");
      }
    }
  }
}
