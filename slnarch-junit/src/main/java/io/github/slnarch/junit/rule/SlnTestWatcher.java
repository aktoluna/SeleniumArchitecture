package io.github.slnarch.junit.rule;

import com.google.common.base.Stopwatch;
import io.aktoluna.slnarch.common.log.LogHelper;
import java.util.concurrent.TimeUnit;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.slf4j.Logger;

public class SlnTestWatcher extends TestWatcher {

  private Stopwatch stopwatch = Stopwatch.createUnstarted();
  private Logger logger = LogHelper.getSlnLogger();


  @Override
  protected void failed(Throwable e, Description description) {
    logger.info(String.format("%s is failed", description.getMethodName()));
  }

  @Override
  protected void succeeded(Description description) {
    logger.info(String.format("%s is successful", description.getMethodName()));
  }

  @Override
  protected void starting(Description description) {
    super.starting(description);
    logger.info(String.format("Start %s ", description.getMethodName()));
    stopwatch.start();
  }

  @Override
  protected void finished(Description description) {
    super.finished(description);
    stopwatch.stop();
    logger.info(String.format("Time = %d sn.", stopwatch.elapsed(TimeUnit.SECONDS)));
    logger.info(String.format("End %s", description.getMethodName()));
    stopwatch.reset();
  }
}
