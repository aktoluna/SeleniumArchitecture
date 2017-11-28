package com.saha.slnarch.common.helper;

import com.google.common.base.Strings;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringHelper {

  private static Logger logger = LoggerFactory.getLogger(StringHelper.class);

  private StringHelper() {

  }

  public static boolean isEmpty(String value) {
    return Strings.isNullOrEmpty(value);
  }

  public static int convertStringToInt(String value) {
    int converted = 0;
    if (!isEmpty(value)) {
      try {
        converted = Integer.parseInt(value);
      } catch (Exception e) {
        logger.error("String is not int", e);
      }
    }
    return converted;
  }

  public static double convertStringToDouble(String value) {
    double converted = 0;
    if (!isEmpty(value)) {
      try {
        converted = Double.parseDouble(value);
      } catch (Exception e) {
        logger.error("String is not double", e);
      }
    }
    return converted;
  }

  public static String convertIntToString(int value) {
    String converted = "";
    try {
      converted = String.valueOf(value);
    } catch (Exception e) {
      logger.error("Int Convert String Error", e);
    }
    return converted;
  }


  public static boolean isNumeric(String text) {
    boolean result = false;
    try {
      result = StringUtils.isNumeric(text);
    } catch (Exception e) {
      logger.error("Is Number Error", e);
    }
    return result;
  }

  public static boolean isAlpha(String text) {
    boolean result = false;
    try {
      result = StringUtils.isAlpha(text);
    } catch (Exception e) {
      logger.error("Is Alpha Error", e);
    }
    return result;
  }

  public static boolean contains(String text, String containText) {
    boolean result = false;
    try {
      result = StringUtils.contains(text, containText);
    } catch (Exception e) {
      logger.error("Is Contains Error", e);
    }
    return result;
  }

  public static String removeStringInText(String text, String removeText) {
    String value = "";
    try {
      value = text.replace(removeText, "");
    } catch (Exception e) {
      logger.error("Remove Text ", e);
    }
    return value;
  }

  public static String trimString(String text) {
    return text.trim();
  }

  public static String getObjectValueAsString(Object object) {
    return object.toString();
  }

  public static int getObjectValueAsInt(Object object) {
    return Integer.parseInt(object.toString());
  }

  public static boolean getObjectValueAsBool(Object object) {
    return Boolean.parseBoolean(object.toString());
  }

  public static double getObjectValueAsDouble(Object object) {
    return Double.parseDouble(object.toString());
  }

  public static float getObjectValueAsFloat(Object object) {
    return Float.parseFloat(object.toString());
  }
}
