package com.saha.slnarch.core.element.by;

import org.openqa.selenium.By;

public final class ByTag implements ByCreate<By> {

  @Override
  public By createBy(String name) {
    return By.tagName(name);
  }
}
