package com.saha.slnarch.report;

import com.saha.slnarch.common.helper.SystemPropertyHelper;
import org.junit.Test;

public class ReportPatternTest {

  @Test
  public void parse() {
    String reportPattern = "Report%rp%date";
    SystemPropertyHelper.INSTANCE.setProperty("reportPattern", "_DOMESTIC_PREP");
    System.out.println(ReportPattern.parse(reportPattern));
  }
}