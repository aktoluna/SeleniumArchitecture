package ${groupId}.step.driver;

import com.saha.slnarch.core.di.Injectable;
import com.saha.slnarch.core.driver.DriverAction;
import com.thoughtworks.gauge.Step;
import javax.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

public class DriverStepImpl implements DriverStep, Injectable {

  @Inject
  DriverAction driverAction;
  @Inject
  WebDriver driver;

  public DriverStepImpl() {
    inject();
  }

  @Step("Go to url <url>")
  @Override
  public void goToURL(String url) {
    driverAction.navigateTo(url);
  }

  @Step("Refresh page")
  @Override
  public void refresh() {
    driverAction.refreshTo();
  }

  @Step("Navigate to back page")
  @Override
  public void back() {
    driverAction.goBack();
  }

  @Step("Navigate to next page")
  @Override
  public void next() {
    driver.navigate().forward();
  }

  @Step("Switch to frame by id <id>")
  @Override
  public void switchToFrameById(String id) {
    driverAction.switchToFrame(By.id(id));
  }

  @Step("Switch to frame by key <key>")
  @Override
  public void switchToFrameByKey(String key) {
//    driverAction.switchToFrame(By.id(id));
  }

  @Step("Switch to frame by index <index>")
  @Override
  public void switchToFrameByIndex(int index) {
    driver.switchTo().frame(index);
  }

  @Step("Switch to main frame")
  @Override
  public void switchToMainFrame() {
    driverAction.switchToMain();
  }

  @Step("Switch to next tab")
  @Override
  public void switchToNextTab() {
    int counter = 0;
    for (String s : driver.getWindowHandles()) {
      if (driver.getWindowHandle().equals(s)) {
        counter++;
      }
    }

    if ((counter + 1) < driver.getWindowHandles().size()) {
      driver.switchTo().window(driver.getWindowHandles().stream().skip(counter).findFirst().get());
    }
  }

  @Step("Switch to previous tab")
  @Override
  public void switchToPrevTab() {
    int counter = 0;
    for (String s : driver.getWindowHandles()) {
      if (driver.getWindowHandle().equals(s)) {
        counter++;
      }
    }

    if ((counter - 1) > 0) {
      driver.switchTo()
          .window(driver.getWindowHandles().stream().skip(counter - 1).findFirst().get());
    }
  }

  @Step("Close tab")
  @Override
  public void closeTab() {
    close();
    switchToPrevTab();
  }

  @Step("Close driver")
  @Override
  public void close() {
    driver.close();
  }

  @Step("Quit driver")
  @Override
  public void quit() {
    driver.quit();
  }

  @Step("Add cookie <cookieName> and <cookieValue>")
  @Override
  public void addCookie(String cookieName, String cookieValue) {
    driver.manage().addCookie(new Cookie(cookieName, cookieValue));
  }

  @Step("Remove cookie by name <cookieName>")
  @Override
  public void removeCookie(String cookieName) {
    driver.manage().deleteCookieNamed(cookieName);
  }

  @Step("Accept alert popup")
  @Override
  public void acceptAlert() {
    driverAction.alertPopup(true);
  }

  @Step("Decline alert popup")
  @Override
  public void declineAlert() {
    driverAction.alertPopup(false);
  }

  @Step("Handle alert <acceptOrDecline>")
  @Override
  public void handleAlert(boolean accept) {
    driverAction.alertPopup(accept);
  }
}
