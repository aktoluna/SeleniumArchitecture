package ${groupId}.step.wait;

public interface WaitStep {

  void waitSeconds(int seconds) throws InterruptedException;

  void waitMilliSecond(int milliSeconds) throws InterruptedException;

}
