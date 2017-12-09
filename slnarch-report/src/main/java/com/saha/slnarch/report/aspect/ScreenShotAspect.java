package com.saha.slnarch.report.aspect;

import com.saha.slnarch.common.helper.StringHelper;
import com.saha.slnarch.core.driver.DriverAction;
import com.saha.slnarch.di.Injectable;
import com.saha.slnarch.di.helper.InjectionHelper;
import com.saha.slnarch.report.ReportManager;
import com.saha.slnarch.report.annotation.ScreenShot;
import java.io.File;
import javax.inject.Inject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class ScreenShotAspect implements Injectable {

  Logger logger = LoggerFactory.getLogger(getClass());

  @Inject
  DriverAction driverAction;

  public ScreenShotAspect() {
  }

  @Pointcut("@annotation(screenShot)")
  public void annotationPointCutDefinition(ScreenShot screenShot) {
  }

  @Pointcut("execution(* *(..))")
  public void atExecution() {
  }

  @Around("annotationPointCutDefinition(screenShot) && atExecution()")
  public Object aroundAdvice(ProceedingJoinPoint joinPoint,
      ScreenShot screenShot) throws Throwable {
    Object returnObject = null;
    try {
      addScreenShotBefore(screenShot.message(), screenShot.before());
      returnObject = joinPoint.proceed();
    } catch (Throwable throwable) {
      throw throwable;
    } finally {
      addScreenShotBefore(screenShot.message(), screenShot.after());
    }
    return returnObject;
  }

  private void addScreenShotBefore(String message, boolean takePhoto) {
    addScreenShot(message, true, takePhoto);
  }

  private void addScreenShotAfter(String message, boolean takePhoto) {
    addScreenShot(message, false, takePhoto);
  }

  private void addScreenShot(String message, boolean before, boolean takePhoto) {
    if (!takePhoto) {
      return;
    }
    inject();
    try {
      File file = driverAction.takeScreenShotAndCompress();
      if (StringHelper.isEmpty(message)) {
        ReportManager.getInstance().getExtentTest().info(message,
            ReportManager.getInstance().createMediaEntity(file));
      } else {
        ReportManager.getInstance().getExtentTest().info(message,
            ReportManager.getInstance().createMediaEntity(file));
      }
    } catch (Exception e) {
      logger.error("Take Screen Shot Error " + (before ? "Before" : "After"), e);
    }
  }

  @Override
  public void inject() {
    InjectionHelper.getInstance().getFeather().injectFields(this);
  }
}
