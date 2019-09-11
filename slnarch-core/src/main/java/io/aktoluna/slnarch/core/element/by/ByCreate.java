package io.aktoluna.slnarch.core.element.by;

import org.openqa.selenium.By;

public interface ByCreate<T extends By> {

  T createBy(String name);
}
