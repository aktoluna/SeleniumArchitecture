package io.github.slnarch.report;

import io.aktoluna.slnarch.common.helper.SystemPropertyHelper;
import org.junit.Test;

public class ReportPatternTest {

  @Test
  public void parse() {
    String reportPattern = "Report%rp%date";
    SystemPropertyHelper.setProperty("reportPattern", "_DOMESTIC_PREP");
    System.out.println(ReportPattern.parse(reportPattern));
  }
}