package com.saha.slnarch.common.date;

import com.saha.slnarch.common.log.LogHelper;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;
import org.slf4j.Logger;

public class DateAndTimeHelper {

  private static final Logger logger = LogHelper.getSlnLogger();
  private static final String dd_MM_YYYY = "dd-MM-yyyy";
  private static final String HH_mm_ss = "HH:mm:ss";
  private static final String dd_MM_YYYY_HH_mm_ss = dd_MM_YYYY + " " + HH_mm_ss;

  private DateAndTimeHelper() {

  }

  public static Date getNowDate() {
    return getCalendar().getTime();
  }

  public static Calendar getCalendar() {
    return Calendar.getInstance();
  }


  public static DateFormat getDateFormat(String format) {
    return new SimpleDateFormat(format);
  }

  public static String formatDate(Date date, String format) {
    return getDateFormat(format).format(date);
  }

  public static Date parseDate(String date, String format) {
    Date outputDate = null;
    try {
      outputDate = getDateFormat(format).parse(date);
    } catch (ParseException e) {
      logger.error("Date Parse Exception", e);
    }
    return outputDate;
  }

  public static String getNowDateAsString() {
    return formatDate(getNowDate(), dd_MM_YYYY_HH_mm_ss);
  }

  public static String convertDate(String date, String inputFormat, String outputFormat) {
    return formatDate(parseDate(date, inputFormat), outputFormat);
  }

  public static Date parsePeriod(String period) {
    return parsePeriod(period, getStandardPeriodFormatter());
  }


  public static Date parsePeriod(String period, PeriodFormatter periodFormatter) {
    Period p = periodFormatter.parsePeriod(period);
    Calendar calendar = getCalendar();
    calendar.setTimeInMillis(calendar.getTimeInMillis() + p.toStandardDuration().getMillis());
    return calendar.getTime();
  }

  private static PeriodFormatter getStandardPeriodFormatter() {
    return new PeriodFormatterBuilder()
        .appendDays().appendSuffix("d")
        .appendSeparator(" ")
        .appendHours().appendSuffix("h")
        .toFormatter();
  }


}
