package com.saha.slnarch.report.screenshot;

import java.lang.reflect.Proxy;

public class ScreenShotProxy {

  public static <T> T getNewProxy(Object proxied, Class<T> interfaze) {
    Object proxy = Proxy.newProxyInstance(
        ScreenShotInvocationHandler.class.getClassLoader(),
        new Class[]{interfaze},
        new ScreenShotInvocationHandler(proxied));
    return (T) proxy;
  }
}
