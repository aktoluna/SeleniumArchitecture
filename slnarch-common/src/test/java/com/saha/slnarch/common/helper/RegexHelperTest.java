package com.saha.slnarch.common.helper;

import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class RegexHelperTest {

  @Test
  public void getStringInOnlyNumber() throws Exception {
    String text = "12 test";
    int expected = 12;
    int actual = RegexHelper.getStringInOnlyNumber(text);
    Assertions.assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void getStringInOnlyNumbers() throws Exception {
    String text = "12 24 test";
    List<Integer> expected = new ArrayList<>();
    expected.add(12);
    expected.add(24);
    List<Integer> actual = RegexHelper.getStringInOnlyNumbers(text);
    Assertions.assertThat(actual).isEqualTo(expected);
  }

}