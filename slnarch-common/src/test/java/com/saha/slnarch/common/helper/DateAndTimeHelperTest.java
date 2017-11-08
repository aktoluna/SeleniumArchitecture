package com.saha.slnarch.common.helper;

import static org.hamcrest.Matchers.is;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.junit.Assert;
import org.junit.Test;

public class DateAndTimeHelperTest {

  private String dateFormat = "MM-dd-YYYY";
  private String nowDateFormat = "dd-MM-yyyy HH:mm:ss";

  @Test
  public void getNowDate() throws Exception {
//    Date expected = Calendar.getInstance().getTime();
//    Assert.assertThat(DateAndTimeHelper.getNowDate(), is(expected));
  }

  @Test
  public void getCalendar() throws Exception {
    Calendar expected = Calendar.getInstance();
    Assert.assertThat(DateAndTimeHelper.getCalendar(), is(expected));
  }

  @Test
  public void getDateFormat() throws Exception {
    SimpleDateFormat expected = new SimpleDateFormat(dateFormat);
    Assert.assertThat(expected.getCalendar().getTimeInMillis(),
        is(DateAndTimeHelper.getDateFormat(dateFormat).getCalendar().getTimeInMillis()));
  }

  @Test
  public void formatDate() throws Exception {
    String expected = new SimpleDateFormat(dateFormat).format(Calendar.getInstance().getTime());
    Assert.assertThat(DateAndTimeHelper.formatDate(DateAndTimeHelper.getNowDate(), dateFormat),
        is(expected));
  }

  @Test
  public void parseDate() throws Exception {
    String input = "11-10-2017";
    Date expected = new SimpleDateFormat(dateFormat).parse(input);
    Assert.assertThat(DateAndTimeHelper.parseDate(input, dateFormat), is(expected));
  }

  @Test
  public void getNowDateAsString() throws Exception {
//    String expected = new SimpleDateFormat(nowDateFormat).format(Calendar.getInstance().getTime());

  }

  @Test
  public void convertDate() throws Exception {
    String input = "11-10-2017";
    Date output = new SimpleDateFormat(dateFormat).parse(input);
    String expected = new SimpleDateFormat(nowDateFormat).format(output);
    Assert
        .assertThat(DateAndTimeHelper.convertDate(input, dateFormat, nowDateFormat), is(expected));
  }

}