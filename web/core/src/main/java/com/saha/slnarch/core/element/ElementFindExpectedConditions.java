package com.saha.slnarch.core.element;

import com.saha.slnarch.core.element.by.ByType;
import com.saha.slnarch.core.model.ElementInfo;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

public interface ElementFindExpectedConditions<T extends Element, S extends WebElement> extends
    ElementExpectedType {

  T findByExpected(ExpectedCondition<S> expectedCondition);

  T findByExpects(ExpectedCondition<S>... expectedConditions);

  T findsByExpected(ExpectedCondition<List<S>> expectedCondition);

  T findsByExpects(ExpectedCondition<List<S>>... expectedConditions);

  T findByPresence(By by);

  T findByPresence(String name);

  T findByPresence(ByType byType, String name);

  T findByPresence(ElementInfo elementInfo);

  T findsByPresence(By by);

  T findsByPresence(String name);

  T findsByPresence(ByType byType, String name);

  T findsByPresence(ElementInfo elementInfo);

  T findByClickable(By by);

  T findByClickable(String name);

  T findByClickable(ByType byType, String name);

  T findByClickable(ElementInfo elementInfo);

  T findByClickable(WebElement webElement);

  T findByClickableAndUrlChange(WebElement webElement,String url);

//  T findByClickable(int index);

  T findByVisibility(By by);

  T findByVisibility(String name);

  T findByVisibility(ByType byType, String name);

  T findByVisibility(ElementInfo elementInfo);

  T findByVisibility(WebElement webElement);

  T findsByVisibility(By by);

  T findsByVisibility(String name);

  T findsByVisibility(ByType byType, String name);

  T findsByVisibility(ElementInfo elementInfo);

  T findsByVisibility(List<WebElement> webElement);

  boolean elementIsInvisibility(By by);

  boolean elementIsInvisibility(String name);

  boolean elementIsInvisibility(ByType byType, String name);

  boolean elementIsInvisibility(ElementInfo elementInfo);

  boolean elementIsInvisibility(WebElement webElement);

  boolean elementsIsInvisibility(By by);

  boolean elementsIsInvisibility(String name);

  boolean elementsIsInvisibility(ByType byType, String name);

  boolean elementsIsInvisibility(ElementInfo elementInfo);

  boolean elementsIsInvisibility(List<WebElement> webElement);

  boolean elementIsSelectable(By by);

  boolean elementIsSelectable(String name);

  boolean elementIsSelectable(ByType byType, String name);

  boolean elementIsSelectable(ElementInfo elementInfo);

  boolean elementIsSelectable(WebElement webElement);

  boolean elementIsSelectableState(By by, boolean state);

  boolean elementIsSelectableState(String name, boolean state);

  boolean elementIsSelectableState(ByType byType, String name, boolean state);

  boolean elementIsSelectableState(ElementInfo elementInfo, boolean state);

  boolean elementIsSelectableState(WebElement webElement, boolean state);

  boolean elementIsContainsText(By by, String text);

  boolean elementIsContainsText(String name, String text);

  boolean elementIsContainsText(ByType byType, String name, String text);

  boolean elementIsContainsText(ElementInfo elementInfo, String text);

  boolean elementIsContainsText(WebElement element, String text);

  T findByContainsAnyText(By by, String text);

  T findByContainsAnyText(String name, String text);

  T findByContainsAnyText(ByType byType, String name, String text);

  T findByContainsAnyText(ElementInfo elementInfo, String text);

  T findByContainsAnyText(List<WebElement> element, String text);

  boolean elementIsEqualsText(By by, String text);

  boolean elementIsEqualsText(String name, String text);

  boolean elementIsEqualsText(ByType byType, String name, String text);

  boolean elementIsEqualsText(ElementInfo elementInfo, String text);

  boolean elementIsEqualsText(WebElement element, String text);

  T findByEqualsAnyText(By by, String text);

  T findByEqualsAnyText(String name, String text);

  T findByEqualsAnyText(ByType byType, String name, String text);

  T findByEqualsAnyText(ElementInfo elementInfo, String text);

  T findByEqualsAnyText(List<WebElement> element, String text);


}
