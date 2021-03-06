package ${groupId}.step.js;

import io.github.slnarch.core.di.Injectable;
import io.github.slnarch.core.js.JavaScriptOperation;
import com.thoughtworks.gauge.Step;
import javax.inject.Inject;

public class JavaScriptStepImpl implements JavaScriptStep, Injectable {

  @Inject
  JavaScriptOperation javaScriptOperation;

  public JavaScriptStepImpl() {
    inject();
  }

  @Step("Execute javascript <script")
  @Override
  public void executeScript(String script) {
    javaScriptOperation.executeJS(script);
  }

  @Step("Execute async javascript <script")
  @Override
  public void executeAsyncScript(String script) {
    javaScriptOperation.executeAsyncJS(script);
  }
}
