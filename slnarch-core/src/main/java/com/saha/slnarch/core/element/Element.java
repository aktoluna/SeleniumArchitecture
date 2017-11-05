package com.saha.slnarch.core.element;

public interface Element<T extends Element> extends ElementOperation<T>, ElementFind<T>,
    JavaScriptAction<T> {

}
