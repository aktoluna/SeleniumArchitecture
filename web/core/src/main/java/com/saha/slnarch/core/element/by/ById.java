package com.saha.slnarch.core.element.by;

import org.openqa.selenium.By;

public class ById implements ByCreate<By> {

  @Override
  public By createBy(String name) {
    return By.id(name);
  }
}
