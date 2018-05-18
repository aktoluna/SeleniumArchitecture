package ${groupId}.step.wait;

import com.thoughtworks.gauge.Step;

public class WaitStepImpl implements WaitStep {

  @Step("<seconds> seconds wait")
  @Override
  public void waitSeconds(int seconds) throws InterruptedException {
    waitMilliSecond(seconds * 1000);
  }

  @Step("<seconds> milliseconds wait")
  @Override
  public void waitMilliSecond(int milliSeconds) throws InterruptedException {
    Thread.sleep(milliSeconds);
  }
}
