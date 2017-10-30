package com.saha.slnarch.core.element;

import org.openqa.selenium.By;

public interface ElementController<T> {

  boolean checkPlaceHolder(By by, String value, int... index);


}
