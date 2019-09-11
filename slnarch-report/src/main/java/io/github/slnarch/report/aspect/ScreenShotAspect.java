package io.github.slnarch.report.aspect;

import io.aktoluna.slnarch.common.helper.StringHelper;
import io.aktoluna.slnarch.common.log.LogHelper;
import io.aktoluna.slnarch.core.di.Injectable;
import io.aktoluna.slnarch.core.driver.DriverAction;
import io.github.slnarch.report.ReportManager;
import io.github.slnarch.report.annotation.ScreenShot;
import java.io.File;
import javax.inject.Inject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;

@Aspect
public class ScreenShotAspect implements Injectable {

  Logger logger = LogHelper.getSlnLogger();

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
    Object returnObject;
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
}
