package io.aktoluna.slnarch.core.element.by;

import org.openqa.selenium.By;

public class ByClass implements ByCreate<By> {

  @Override
  public By createBy(String name) {
    return By.className(name);
  }
}
