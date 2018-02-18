package com.saha.slnarch.common.file;

import static org.hamcrest.Matchers.is;

import com.saha.slnarch.common.file.Prop.PropType;
import java.util.Properties;
import org.junit.Assert;
import org.junit.Test;

public class PropertyHelperTest {


  @Test
  public void readProperties() throws Exception {
    Properties expected = new Properties();
    expected.setProperty("ali", "5");
    Properties properties = PropertyHelper.readProperties(true, "welcome.properties");
    Assert.assertThat(properties.getProperty("ali"), is(properties.getProperty("ali")));
  }

  @Test
  public void propertiesToClass() throws Exception {
    TestProp expected = new TestProp();
    expected.setAli("5");
    expected.setTest("10");
    expected.setDeneme("ali");
    TestProp testProp = PropertyHelper
        .propertiesToClass(PropertyHelper.readProperties(true, "welcome.properties"),
            TestProp.class);
    Assert.assertThat(testProp.getAli(), is(expected.getAli()));
    Assert.assertThat(testProp.getTest(), is(expected.getTest()));
    Assert.assertThat(testProp.getDeneme(), is(expected.getDeneme()));
  }

  @Test
  public void propertiesToClassWithAnnotation() throws Exception {
    TestPropAnnotation expected = new TestPropAnnotation();
    expected.setAlpha("ali");
    expected.setExtend(10.55);
    expected.setA(1);
    expected.setYesNo(true);
    TestPropAnnotation actual = PropertyHelper.propertiesToClassWithAnnotation(
        PropertyHelper.readProperties(true, "welcome-annotation.properties"),
        TestPropAnnotation.class);
    Assert.assertThat(expected.getAlpha(), is(actual.getAlpha()));
    Assert.assertThat(expected.getA(), is(actual.getA()));
    Assert.assertThat(expected.getExtend(), is(actual.getExtend()));
    Assert.assertThat(expected.isYesNo(), is(actual.isYesNo()));
  }

  public static class TestProp {

    String ali;
    String deneme;
    String test;

    public String getAli() {
      return ali;
    }

    public void setAli(String ali) {
      this.ali = ali;
    }

    public String getDeneme() {
      return deneme;
    }

    public void setDeneme(String deneme) {
      this.deneme = deneme;
    }

    public String getTest() {
      return test;
    }

    public void setTest(String test) {
      this.test = test;
    }
  }

  public static class TestPropAnnotation {

    @Prop(key = "a")
    private String alpha;
    @Prop(key = "b",type = PropType.DOUBLE)
    private double extend;
    @Prop(key = "c",type = PropType.INT)
    private int a;
    @Prop(key = "d",type = PropType.BOOL)
    private boolean yesNo;

    public String getAlpha() {
      return alpha;
    }

    public void setAlpha(String alpha) {
      this.alpha = alpha;
    }

    public double getExtend() {
      return extend;
    }

    public void setExtend(double extend) {
      this.extend = extend;
    }

    public int getA() {
      return a;
    }

    public void setA(int a) {
      this.a = a;
    }

    public boolean isYesNo() {
      return yesNo;
    }

    public void setYesNo(boolean yesNo) {
      this.yesNo = yesNo;
    }
  }

}