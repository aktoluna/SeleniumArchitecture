package com.saha.slnarch.report;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
@RunWith(Parameterized.class)
public class TestReport extends ExtentReportTest {



  private int fInput;

  private int fExpected;

  public TestReport(int input, int expected) {
    fInput= input;
    fExpected= expected;
  }

  @Parameters
  public static Collection<Object[]> data() {
    return Arrays.asList(new Object[][] {
        { 0, 0 }, { 1, 1 }, { 2, 1 }, { 3, 2 }, { 4, 3 }, { 5, 5 }, { 6, 8 }
    });
  }

  @Test
  public void test1() {
//    Assert.assertFalse("Not equal", "1".equals("1"));
    assertEquals(fExpected,fInput);
  }

}
