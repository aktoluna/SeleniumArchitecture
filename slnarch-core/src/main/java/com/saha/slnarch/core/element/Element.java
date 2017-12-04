package com.saha.slnarch.core.element;

import org.openqa.selenium.WebElement;

public interface Element<T extends Element> extends ElementOperation<T>, ElementFind<T, WebElement>,
    JavaScriptAction<T>, ElementController<T> {

}
