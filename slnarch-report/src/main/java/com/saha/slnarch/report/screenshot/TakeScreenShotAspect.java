package com.saha.slnarch.report.screenshot;

import com.saha.slnarch.common.helper.StringHelper;
import com.saha.slnarch.core.driver.DriverAction;
import com.saha.slnarch.di.Injectable;
import com.saha.slnarch.di.helper.InjectionHelper;
import com.saha.slnarch.report.ReportManager;
import java.io.File;
import javax.inject.Inject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class TakeScreenShotAspect implements Injectable {

  Logger logger = LoggerFactory.getLogger(getClass());

  @Inject
  DriverAction driverAction;

  public TakeScreenShotAspect() {
    inject();
  }

  @Pointcut("@annotation(takeScreenShot)")
  public void annotationPointCutDefinition(TakeScreenShot takeScreenShot) {
  }

  @Pointcut("execution(* *(..))")
  public void atExecution() {
  }

  @Around("annotationPointCutDefinition(takeScreenShot) && atExecution()")
  public Object aroundAdvice(ProceedingJoinPoint joinPoint,
      TakeScreenShot takeScreenShot) throws Throwable {
    Object returnObject = null;
    logger.info("Test");
    logger.info("test");
    try {
      addScreenShotBefore(takeScreenShot.logMessage(), takeScreenShot.before());
      returnObject = joinPoint.proceed();
    } catch (Throwable throwable) {
      throw throwable;
    } finally {
      addScreenShotBefore(takeScreenShot.logMessage(), takeScreenShot.after());
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
    try {
      File file = driverAction.takeScreenShot();
      if (StringHelper.isEmpty(message)) {
        ReportManager.getInstance().addScreenShot(file);
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
