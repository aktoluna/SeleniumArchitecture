package com.saha.slnarch.report;

import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.saha.slnarch.core.driver.DriverAction;
import com.saha.slnarch.core.driver.DriverActionImpl;
import java.io.File;
import org.junit.AssumptionViolatedException;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.openqa.selenium.WebDriver;

public class ExtentJunitListener extends TestWatcher {

  ReportManager reportManager = ReportManager.getInstance();

  DriverAction driverAction;

  public ExtentJunitListener(WebDriver driver) {
    this.driverAction = new DriverActionImpl(driver);
  }

  @Override
  public Statement apply(Statement base, Description description) {
    return super.apply(base, description);
  }

  @Override
  protected void succeeded(Description description) {
    super.succeeded(description);
    reportManager.getExtentTest()
        .info(String.format("Success Test=%s", description.getMethodName()));
  }

  @Override
  protected void failed(Throwable e, Description description) {
    super.failed(e, description);
    reportManager.getExtentTest()
        .fail(MarkupHelper.createCodeBlock(e.getMessage()))
        .fail(String.format("Fail Test=%s clause=%s", description.getMethodName(), e.getMessage()),
            reportManager.createMediaEntity(takeScreenShotWithSave()));
  }

  @Override
  protected void skipped(AssumptionViolatedException e, Description description) {
    super.skipped(e, description);
    reportManager.getExtentTest()
        .skip(MarkupHelper.createCodeBlock(e.getMessage()))
        .skip(String.format("Skip Test=%s cause=%s", description.getMethodName(), e.getMessage()),
            reportManager.createMediaEntity(takeScreenShotWithSave()));
  }

  @Override
  protected void starting(Description description) {
    super.starting(description);
    reportManager.createNewExtentTest(description.getMethodName());
    reportManager.getExtentTest()
        .info(String.format("Starting Test=%s", description.getMethodName()));
    reportManager.setAuthor(description);
    reportManager.setCategory(description);
  }

  @Override
  protected void finished(Description description) {
    super.finished(description);
    driverAction.quit();
    reportManager.getExtentTest()
        .info(String.format("Finished Test=%s", description.getMethodName()));
  }

  private File takeScreenShotWithSave() {
    return driverAction.takeScreenShot();
  }


}
