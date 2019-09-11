package io.aktoluna.slnarch.core.model;

public class TimeOut {

  private int pageLoad;
  private int script;
  private int implicitly;

  public int getPageLoad() {
    return pageLoad;
  }

  public int getScript() {
    return script;
  }

  public int getImplicitly() {
    return implicitly;
  }

  public static final class TimeOutBuilder {

    private int pageLoad;
    private int script;
    private int implicitly;

    private TimeOutBuilder() {
    }

    public static TimeOutBuilder aTimeOut() {
      return new TimeOutBuilder();
    }

    public TimeOutBuilder withPageLoad(int pageLoad) {
      this.pageLoad = pageLoad;
      return this;
    }

    public TimeOutBuilder withScript(int script) {
      this.script = script;
      return this;
    }

    public TimeOutBuilder withImplicitly(int implicitly) {
      this.implicitly = implicitly;
      return this;
    }

    public TimeOut build() {
      TimeOut timeOut = new TimeOut();
      timeOut.pageLoad = this.pageLoad;
      timeOut.implicitly = this.implicitly;
      timeOut.script = this.script;
      return timeOut;
    }
  }
}
