package ${groupId}.step.driver;

import com.saha.slnarch.core.di.Injectable;
import com.saha.slnarch.core.driver.DriverAction;
import com.thoughtworks.gauge.Step;
import javax.inject.Inject;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;

public class DriverAssertionStepImpl implements DriverAssertionStep, Injectable {

  @Inject
  DriverAction driverAction;
  @Inject
  WebDriver driver;

  public DriverAssertionStepImpl() {
    inject();
  }

  @Step("Page source contains text <text>")
  @Override
  public void pageSourceContainsText(String text) {
    Assertions.assertThat(driverAction.getPageSource())
        .contains(text);
  }

  @Step("Page source does not contains text <text>")
  @Override
  public void pageSourceNotContainsText(String text) {
    Assertions.assertThat(driverAction.getPageSource())
        .doesNotContain(text);
  }

  @Step("Page title contains text <text>")
  @Override
  public void pageTitleContainsText(String text) {
    Assertions.assertThat(driverAction.getTitle())
        .contains(text);
  }

  @Step("Page title does not contains text <text>")
  @Override
  public void pageTitleNotContainsText(String text) {
    Assertions.assertThat(driverAction.getTitle())
        .doesNotContain(text);
  }

  @Step("Page title equals text <text>")
  @Override
  public void pageTitleEqualsText(String text) {
    Assertions.assertThat(driverAction.getTitle())
        .isEqualTo(text);
  }

  @Step("Page url contains text <text>")
  @Override
  public void pageUrlContainsText(String text) {
    Assertions.assertThat(driverAction.getCurrentUrl())
        .contains(text);
  }

  @Step("Page url does not contains text <text>")
  @Override
  public void pageUrlNotContainsText(String text) {
    Assertions.assertThat(driverAction.getCurrentUrl())
        .doesNotContain(text);
  }

  @Step("Page url equals text <text>")
  @Override
  public void pageUrlEqualsText(String text) {
    Assertions.assertThat(driverAction.getCurrentUrl())
        .isEqualTo(text);
  }

  @Step("Cookie values contains text")
  @Override
  public void anyCookieValueEqualsText(String text) {
    Assertions.assertThat(
        driver.manage().getCookies().stream().filter(cookie -> cookie.getValue().equals(text))
            .findFirst().isPresent())
        .isTrue();
  }

  @Step("Cookie values does not contains text")
  @Override
  public void anyCookieValueNotEqualsText(String text) {
    Assertions.assertThat(
        driver.manage().getCookies().stream().filter(cookie -> cookie.getValue().equals(text))
            .findFirst().isPresent())
        .isFalse();
  }
}
