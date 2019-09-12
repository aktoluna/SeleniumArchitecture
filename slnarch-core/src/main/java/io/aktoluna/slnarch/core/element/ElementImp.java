package io.aktoluna.slnarch.core.element;

import io.aktoluna.slnarch.common.log.LogHelper;
import io.aktoluna.slnarch.core.element.by.ByCreate;
import io.aktoluna.slnarch.core.element.by.ByFactory;
import io.aktoluna.slnarch.core.element.by.ByType;
import io.aktoluna.slnarch.core.js.JavaScriptOperation;
import io.aktoluna.slnarch.core.model.ElementInfo;
import io.aktoluna.slnarch.core.wait.WaitingAction;
import io.aktoluna.slnarch.core.wait.WaitingActionImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;

public class ElementImp implements Element<ElementImp> {

  private static Logger logger = LogHelper.getSlnLogger();
  private List<WebElement> elementList;

  private final WebDriver driver;
  private final JavaScriptOperation javaScriptOperation;
  private final WaitingActionImpl waitingAction;
  private ByCreate byCreate;

  @Inject
  public ElementImp(WebDriver driver, JavaScriptOperation javaScriptOperation,
      WaitingAction waitingAction) {
    this(driver, javaScriptOperation, waitingAction, ByFactory.buildBy(ByType.CSS));
  }

  @Inject
  public ElementImp(WebDriver driver, JavaScriptOperation javaScriptOperation,
      WaitingAction waitingAction,
      ByCreate byCreate) {
    this.driver = driver;

    this.javaScriptOperation = javaScriptOperation;
    this.waitingAction = (WaitingActionImpl) waitingAction;
    this.byCreate = byCreate;
    this.elementList = new ArrayList<>();
  }

  @Override
  public ByCreate getByCreate() {
    return byCreate;
  }

  @Override
  public ElementImp setByCreate(ByCreate byCreate) {
    this.byCreate = byCreate;
    return this;
  }

  @Override
  public ElementImp find(By by) {
    clearElementList();
    WebElement element;
    try {
      element = driver.findElement(by);
      setElementList(element);
    } catch (Exception e) {
      logger.error("Element Not Found By={}", by.toString(), e);
    }
    return this;
  }

  @Override
  public ElementImp find(String name) {
    return find(getByCreate().createBy(name));
  }

  @Override
  public ElementImp find(ByType byType, String name) {
    return find(ByFactory.buildBy(byType).createBy(name));
  }

  @Override
  public ElementImp find(ElementInfo elementInfo) {
    return find(ByFactory.buildBy(elementInfo.getType()).createBy(elementInfo.getKey()));
  }

  @Override
  public ElementImp find(By by, int index) {
    clearElementList();
    try {
      setElementList(findElements(by).get(index));
    } catch (Exception e) {
      logger.error("Element Not Found By={}", by.toString(), e);
    }
    return this;
  }

  @Override
  public ElementImp find(String name, int index) {
    return find(getByCreate().createBy(name), index);
  }

  @Override
  public ElementImp findIn(WebElement parent, By by, int index) {
    clearElementList();
    setElementList(parent.findElements(by).get(index));
    return this;
  }

  @Override
  public ElementImp findIn(WebElement parent, String name, int index) {
    return findIn(parent, getByCreate().createBy(name), index);
  }

  @Override
  public ElementImp finds(By by) {
    return setElementList(findElements(by));
  }

  @Override
  public ElementImp finds(String name) {
    return finds(getByCreate().createBy(name));
  }

  //  @Override
  //  public ElementImp finds(By by, String searchText) {
  //    clearElementList();
  //    List<WebElement> elements = null;
  //    try {
  ////      elements = waitingAction.waitUntil(visibilityAndTextOfAllElementsLocatedBy(by, searchText));
  //      elements = driver.findElements(by);
  //      setElementList(elements);
  //    } catch (Exception e) {
  //      logger.error("Element Not Found By={} Reason={}", by.toString(), e);
  //    }
  //
  //    return this;
  //  }

  //  @Override
  //  public ElementImp finds(String name, String searchText) {
  //    return finds(byCreate.createBy(name), searchText);
  //  }

  //  @Override
  //  public ElementImp find(By by, String searchText) {
  //    WebElement element = null;
  //    try {
  //      element = waitingAction.waitUntil(visibilityAndTextOfElementLocatedBy(by, searchText));
  //      setElementList(element);
  //    } catch (Exception e) {
  //      logger.error("Elements Not Found By={}", e, by.toString());
  //    }
  //    return this;
  //  }
  //
  //  @Override
  //  public ElementImp find(String name, String searchText) {
  //    return find(byCreate.createBy(name), searchText);
  //  }

  @Override
  public List<WebElement> findElements(By by) {
    clearElementList();
    List<WebElement> elements = null;
    try {
      elements = waitingAction.waitUntil(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    } catch (Exception e) {
      logger.error("Element Not Found By={}", by.toString(), e);
    }
    return elements;
  }

  @Override
  public List<WebElement> findElements(String name) {
    return findElements(getByCreate().createBy(name));
  }

  @Override
  public ElementImp setElementList(WebElement element) {
    return clearElementList().addElementList(element);
  }

  @Override
  public ElementImp setElementList(List<WebElement> elements) {
    return clearElementList().addElementList(elements);
  }

  @Override
  public ElementImp addElementList(WebElement element) {
    if (element != null) {
      elementList.add(element);
    }
    return this;
  }

  @Override
  public ElementImp addElementList(List<WebElement> elements) {
    if (elements != null) {
      elementList.addAll(elements);
    }
    return this;
  }

  @Override
  public ElementImp clearElementList() {
    elementList.clear();
    return this;
  }

  @Override
  public List<WebElement> getElements() {
    return elementList;
  }

  @Override
  public WebElement getElement(int index) {
    return index >= elementList.size() ? null : elementList.get(index);
  }

  @Override
  public WebElement getElement() {
    return getElement(0);
  }

  @Override
  public Actions newAction() {
    return new Actions(driver);
  }

  @Override
  public Select newSelect(WebElement element) {
    return new Select(element);
  }

  @Override
  public ElementImp click(WebElement element) {
    ((WebElement) waitingAction.waitUntil(ExpectedConditions.elementToBeClickable(element)))
        .click();
    return this;
  }

  @Override
  public ElementImp click(List<WebElement> elements, int index) {
    return click(elements.get(index));
  }

  @Override
  public ElementImp click() {
    return click(getElement());
  }

  @Override
  public ElementImp click(int index) {
    return click(getElements(), index);
  }

  @Override
  public ElementImp clear(WebElement element) {
    element.clear();
    return this;
  }

  @Override
  public ElementImp clear(List<WebElement> elements, int index) {
    return clear(elements.get(index));
  }

  @Override
  public ElementImp clear() {
    return clear(getElement());
  }

  @Override
  public ElementImp clear(int index) {
    return clear(getElements(), index);
  }

  @Override
  public ElementImp sendKeys(WebElement element, CharSequence... keys) {
    element.sendKeys(keys);
    return this;
  }

  @Override
  public ElementImp sendKeys(CharSequence... keys) {
    return sendKeys(getElement(), keys);
  }

  @Override
  public ElementImp sendKeys(List<WebElement> element, int index, CharSequence... keys) {
    return sendKeys(element.get(index), keys);
  }

  @Override
  public ElementImp sendKeys(int index, CharSequence... keys) {
    return sendKeys(getElements(), index, keys);
  }

  @Override
  public ElementImp sendKeysWithWait(WebElement element, long waitMillis, CharSequence... keys) {
    for (CharSequence key : keys) {
      element.sendKeys(key);
      waitingAction.waitByMs(waitMillis);
    }
    return this;
  }

  @Override public ElementImp sendKeysWithWait(long waitMillis, CharSequence... keys) {
    return sendKeysWithWait(getElement(), waitMillis, keys);
  }

  @Override public ElementImp sendKeysWithWait(List<WebElement> element, int index, long waitMillis,
      CharSequence... keys) {
    return sendKeysWithWait(element.get(index), waitMillis, keys);
  }

  @Override public ElementImp sendKeysWithWait(int index, long waitMillis, CharSequence... keys) {
    return sendKeysWithWait(getElement(index), waitMillis, keys);
  }

  @Override
  public ElementImp selectComboItem(WebElement element, String value) {
    newSelect(element).selectByValue(value);
    return this;
  }

  @Override
  public ElementImp selectComboItem(List<WebElement> elements, int index, String value) {
    return selectComboItem(elements.get(index), value);
  }

  @Override
  public ElementImp selectComboItem(String value) {
    return selectComboItem(getElement(), value);
  }

  @Override
  public ElementImp selectComboItem(String value, int index) {
    return selectComboItem(getElements(), index, value);
  }

  @Override
  public ElementImp hover(WebElement element) {
    newAction().moveToElement(element).build().perform();
    return this;
  }

  @Override
  public ElementImp hover(List<WebElement> elements, int index) {
    return hover(elements.get(index));
  }

  @Override
  public ElementImp hover() {
    return hover(getElement());
  }

  @Override
  public ElementImp hover(int index) {
    return hover(getElements(), index);
  }

  @Override
  public ElementImp scrollTo(WebElement element) {
    logger.info("Scroll To Element");
    newAction().moveToElement(element).build().perform();
    return this;
  }

  @Override
  public ElementImp scrollTo(List<WebElement> elements, int index) {
    return scrollTo(elements.get(index));
  }

  @Override
  public ElementImp scrollTo() {
    return scrollTo(getElement());
  }

  @Override
  public ElementImp scrollTo(int index) {
    return scrollTo(getElements(), index);
  }

  @Override
  public ElementImp clickWithJs(WebDriver driver, WebElement element) {
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    return this;
  }

  @Override
  public ElementImp clickWithJs(WebElement element) {
    javaScriptOperation.executeJS("arguments[0].click();", element);
    return this;
  }

  @Override
  public ElementImp clickWithJs() {
    return clickWithJs(getElement());
  }

  @Override
  public ElementImp clickWithJs(int index) {
    return clickWithJs(getElement(index));
  }

  @Override
  public ElementImp clickWithJs(By by, int... index) {
    return clickWithJs(driver, find(by, index[0]).getElement());
  }

  @Override
  public ElementImp clickWithJs(List<WebElement> elements, int index) {
    return clickWithJs(elements.get(index));
  }

  @Override
  public ElementImp hoverElementWithJs(WebElement element) {
    javaScriptOperation.executeJS("var element = arguments[0];"
        + "var mouseEventObj = document.createEvent('MouseEvents');"
        + "mouseEventObj.initEvent( 'mouseover', true, true );"
        + "element.dispatchEvent(mouseEventObj);", element);
    return this;
  }

  @Override
  public ElementImp highlightElementWithJs(WebElement element) {
    javaScriptOperation.executeJS("arguments[0].setAttribute('style', arguments[1]);", element,
        "color: red;border: 1px dashed red; border");
    return this;
  }

  @Override
  public ElementImp scrollToWithJs(int x, int y) {
    javaScriptOperation.executeJS(String.format("window.scrollTo(%d, %d);", x, y), true);
    return this;
  }

  @Override
  public ElementImp scrollToWithJs(WebElement element) {
    javaScriptOperation.executeJS(
        "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'})",
        element);
    return this;
  }

  @Override
  public ElementImp scrollToPageUpWithJs() {
    javaScriptOperation.executeJS("window.scrollTo(document.body.scrollHeight, 0)", true);
    return this;
  }

  @Override
  public ElementImp scrollToPageEndWithJs() {
    javaScriptOperation.executeJS("window.scrollTo(0, document.body.scrollHeight)", true);
    return this;
  }

  @Override
  public ElementImp scrollToJs(WebElement element) {
    return scrollToWithJs(element);
  }

  @Override
  public ElementImp scrollToJs(List<WebElement> elements, int index) {
    return scrollToJs(elements.get(index));
  }

  @Override
  public ElementImp scrollToJs(int index) {
    return scrollToJs(getElements(), index);
  }

  @Override
  public ElementImp scrollToJs() {
    return scrollToJs(getElement());
  }

  @Override public ElementImp scrollIntoView() {
    ElementImp elementImp = scrollIntoView(getElement());
    return elementImp;
  }

  @Override public ElementImp scrollIntoView(int index) {
    return scrollIntoView(getElement(index));
  }

  @Override public ElementImp scrollIntoView(List<WebElement> elements, int index) {
    return scrollIntoView(elements.get(index));
  }

  @Override public ElementImp scrollIntoView(WebElement element) {
    javaScriptOperation.executeJS(
        "arguments[0].scrollIntoView({behavior: 'auto', block: 'center', inline: 'center'});",
        element);
    return this;
  }

  @Override
  public String text(WebElement element) {
    return element.getText();
  }

  @Override
  public String text(List<WebElement> elements, int index) {
    return text(elements.get(index));
  }

  @Override
  public String text() {
    return text(getElement());
  }

  @Override
  public String text(int index) {
    return text(getElement(index));
  }

  @Override
  public String value(WebElement element) {
    return element.getAttribute("value");
  }

  @Override
  public String value(List<WebElement> elements, int index) {
    return value(elements.get(index));
  }

  @Override
  public String value() {
    return value(getElement());
  }

  @Override
  public String value(int index) {
    return value(getElements(), index);
  }

  @Override
  public String attribute(String attributeName) {
    return attribute(getElement(), attributeName);
  }

  @Override
  public String attribute(String attributeName, int index) {
    return attribute(getElement(index), attributeName);
  }

  @Override
  public String attribute(WebElement element, String attributeName) {
    return element.getAttribute(attributeName);
  }

  @Override
  public String attribute(List<WebElement> elements, String attributeName, int index) {
    return attribute(elements.get(index), attributeName);
  }

  @Override
  public boolean isFound() {
    return !isEmpty();
  }

  @Override
  public boolean isEmpty() {
    return getElement() == null;
  }

  @Override
  public boolean isFounds() {
    return !isEmpties();
  }

  @Override
  public boolean isEmpties() {
    return getElements().size() == 0;
  }

  @Override
  public boolean isEnabled() {
    return isEnabled(getElement());
  }

  @Override
  public boolean isEnabled(int index) {
    return isEnabled(getElement(index));
  }

  @Override
  public boolean isEnabled(WebElement element) {
    return element.isEnabled();
  }

  @Override
  public boolean isEnabled(List<WebElement> element, int index) {
    boolean allIsEnabled = true;
    for (WebElement webElement : element) {
      if (!isEnabled(webElement)) {
        allIsEnabled = false;
        break;
      }
    }
    return allIsEnabled;
  }

  @Override
  public boolean isEnabledAll(List<WebElement> element) {
    return false;
  }

  @Override
  public boolean isDisplayed() {
    return isDisplayed(getElement());
  }

  @Override
  public boolean isDisplayed(int index) {
    return isDisplayed(getElement(index));
  }

  @Override
  public boolean isDisplayed(WebElement element) {
    return element.isDisplayed();
  }

  @Override
  public boolean isDisplayed(List<WebElement> elements, int index) {
    return isDisplayed(elements.get(index));
  }

  @Override
  public boolean isDisplayedAll(List<WebElement> elements) {
    boolean allIsDisplay = true;
    for (WebElement webElement : elements) {
      if (!isDisplayed(webElement)) {
        allIsDisplay = false;
        break;
      }
    }
    return allIsDisplay;
  }

  @Override
  public boolean isSelected() {
    return isSelected(getElement());
  }

  @Override
  public boolean isSelected(int index) {
    return isSelected(getElement(index));
  }

  @Override
  public boolean isSelected(WebElement element) {
    return element.isSelected();
  }

  @Override
  public boolean isSelected(List<WebElement> elements, int index) {
    return isSelected(elements.get(index));
  }

  @Override
  public boolean isSelectedAll(List<WebElement> elements) {
    boolean allIsSelected = true;
    for (WebElement webElement : elements) {
      if (!isSelected(webElement)) {
        allIsSelected = false;
        break;
      }
    }
    return allIsSelected;
  }

  @Override
  public boolean isAttributeContains(String attributeName, String text) {
    return isAttributeContains(getElement(), attributeName, text);
  }

  @Override
  public boolean isAttributeContains(int index, String attributeName, String text) {
    return isAttributeContains(getElement(index), attributeName, text);
  }

  @Override
  public boolean isAttributeContains(WebElement element, String attributeName, String text) {
    return element.getAttribute(attributeName).contains(text);
  }

  @Override
  public boolean isAttributeEquals(String attributeName, String text) {
    return isAttributeEquals(getElement(), attributeName, text);
  }

  @Override
  public boolean isAttributeEquals(int index, String attributeName, String text) {
    return isAttributeEquals(getElement(index), attributeName, text);
  }

  @Override
  public boolean isAttributeEquals(WebElement element, String attributeName, String text) {
    return element.getAttribute(attributeName).equals(text);
  }

  @Override
  public boolean isValueContains(String text) {
    return isValueContains(getElement(), text);
  }

  @Override
  public boolean isValueContains(int index, String text) {
    return isValueContains(getElement(index), text);
  }

  @Override
  public boolean isValueContains(WebElement element, String text) {
    return value(element).contains(text);
  }

  @Override
  public ElementImp findByExpected(ExpectedCondition<WebElement> expectedCondition) {
    clearElementList();
    WebElement element;
    element = waitingAction.expected(expectedCondition);
    setElementList(element);
    return this;
  }

  @SafeVarargs
  @Override
  public final ElementImp findByExpects(ExpectedCondition<WebElement>... expectedConditions) {
    clearElementList();
    WebElement element;
    element = waitingAction.expected(expectedConditions);
    setElementList(element);
    return this;
  }

  @Override
  public ElementImp findsByExpected(ExpectedCondition<List<WebElement>> expectedCondition) {
    clearElementList();
    List<WebElement> elements;
    elements = waitingAction
        .expects(expectedCondition);
    setElementList(elements);
    return this;
  }

  @Override
  public ElementImp findsByExpects(ExpectedCondition<List<WebElement>>... expectedConditions) {
    clearElementList();
    List<WebElement> elements;
    elements = waitingAction
        .expects(expectedConditions);
    setElementList(elements);
    return this;
  }

  @Override
  public ElementImp findByPresence(By by) {
    return findByExpected(PRESENCE(by));
  }

  @Override
  public ElementImp findByPresence(String name) {
    return findByPresence(byCreate.createBy(name));
  }

  @Override
  public ElementImp findByPresence(ByType byType, String name) {
    return findByPresence(Objects.requireNonNull(ByFactory.buildBy(byType)).createBy(name));
  }

  @Override
  public ElementImp findByPresence(ElementInfo elementInfo) {
    return findByPresence(Objects.requireNonNull(ByFactory.buildBy(elementInfo.getType()))
        .createBy(elementInfo.getKey()));
  }

  @Override
  public ElementImp findsByPresence(By by) {
    return findsByExpected(PRESENCES(by));
  }

  @Override
  public ElementImp findsByPresence(String name) {
    return findsByPresence(byCreate.createBy(name));
  }

  @Override
  public ElementImp findsByPresence(ByType byType, String name) {
    return findsByPresence(ByFactory.buildBy(byType).createBy(name));
  }

  @Override
  public ElementImp findsByPresence(ElementInfo elementInfo) {
    return findsByPresence(ByFactory.buildBy(elementInfo.getType()).createBy(elementInfo.getKey()));
  }

  @Override
  public ElementImp findByClickable(By by) {
    return findByExpected(CLICK(by));
  }

  @Override
  public ElementImp findByClickable(String name) {
    return findByClickable(byCreate.createBy(name));
  }

  @Override
  public ElementImp findByClickable(ByType byType, String name) {
    return findByClickable(ByFactory.buildBy(byType).createBy(name));
  }

  @Override
  public ElementImp findByClickable(ElementInfo elementInfo) {
    return findByClickable(ByFactory.buildBy(elementInfo.getType()).createBy(elementInfo.getKey()));
  }

  @Override
  public ElementImp findByClickable(WebElement webElement) {
    return findByExpected(CLICK(webElement));
  }

  @Override
  public ElementImp findByClickableAndUrlChange(WebElement webElement, String url) {
    waitingAction.expectedByBoolean(CLICK_AND_URL_CONTAINS(webElement, url));
    return this;
  }

  @Override
  public ElementImp findByVisibility(By by) {
    return findByExpected(VISIBLE(by));
  }

  @Override
  public ElementImp findByVisibility(String name) {
    return findByVisibility(byCreate.createBy(name));
  }

  @Override
  public ElementImp findByVisibility(ByType byType, String name) {
    return findByVisibility(ByFactory.buildBy(byType).createBy(name));
  }

  @Override
  public ElementImp findByVisibility(ElementInfo elementInfo) {
    return findByVisibility(
        ByFactory.buildBy(elementInfo.getType()).createBy(elementInfo.getKey()));
  }

  @Override
  public ElementImp findByVisibility(WebElement webElement) {
    return findByExpected(VISIBLE(webElement));
  }

  @Override
  public ElementImp findsByVisibility(By by) {
    return findsByExpected(VISIBLE_ALL(by));
  }

  @Override
  public ElementImp findsByVisibility(String name) {
    return findsByVisibility(byCreate.createBy(name));
  }

  @Override
  public ElementImp findsByVisibility(ByType byType, String name) {
    return findsByVisibility(ByFactory.buildBy(byType).createBy(name));
  }

  @Override
  public ElementImp findsByVisibility(ElementInfo elementInfo) {
    return findsByVisibility(
        ByFactory.buildBy(elementInfo.getType()).createBy(elementInfo.getKey()));
  }

  @Override
  public ElementImp findsByVisibility(List<WebElement> webElement) {
    return findsByExpected(VISIBLE_ALL(webElement));
  }

  @Override
  public boolean elementIsInvisibility(By by) {
    return waitingAction.expectedByBoolean(INVISIBLE(by));
  }

  @Override
  public boolean elementIsInvisibility(String name) {
    return elementIsInvisibility(byCreate.createBy(name));
  }

  @Override
  public boolean elementIsInvisibility(ByType byType, String name) {
    return elementIsInvisibility(ByFactory.buildBy(byType).createBy(name));
  }

  @Override
  public boolean elementIsInvisibility(ElementInfo elementInfo) {
    return elementIsInvisibility(
        ByFactory.buildBy(elementInfo.getType()).createBy(elementInfo.getKey()));
  }

  @Override
  public boolean elementIsInvisibility(WebElement webElement) {
    return waitingAction.expectedByBoolean(INVISIBLE(webElement));
  }

  @Override
  public boolean elementsIsInvisibility(By by) {
    return waitingAction.expectedByBoolean(INVISIBLE_ALL(by));
  }

  @Override
  public boolean elementsIsInvisibility(String name) {
    return elementsIsInvisibility(byCreate.createBy(name));
  }

  @Override
  public boolean elementsIsInvisibility(ByType byType, String name) {
    return elementsIsInvisibility(ByFactory.buildBy(byType).createBy(name));
  }

  @Override
  public boolean elementsIsInvisibility(ElementInfo elementInfo) {
    return elementsIsInvisibility(
        ByFactory.buildBy(elementInfo.getType()).createBy(elementInfo.getKey()));
  }

  @Override
  public boolean elementsIsInvisibility(List<WebElement> webElement) {
    return waitingAction.expectedByBoolean(INVISIBLE_ALL(webElement));
  }

  @Override
  public boolean elementIsSelectable(By by) {
    return waitingAction.expectedByBoolean(SELECTED(by));
  }

  @Override
  public boolean elementIsSelectable(String name) {
    return elementIsSelectable(byCreate.createBy(name));
  }

  @Override
  public boolean elementIsSelectable(ByType byType, String name) {
    return elementIsSelectable(ByFactory.buildBy(byType).createBy(name));
  }

  @Override
  public boolean elementIsSelectable(ElementInfo elementInfo) {
    return elementIsSelectable(
        ByFactory.buildBy(elementInfo.getType()).createBy(elementInfo.getKey()));
  }

  @Override
  public boolean elementIsSelectable(WebElement webElement) {
    return waitingAction.expectedByBoolean(SELECTED(webElement));
  }

  @Override
  public boolean elementIsSelectableState(By by, boolean state) {
    return waitingAction.expectedByBoolean(SELECTED_STATE(by, state));
  }

  @Override
  public boolean elementIsSelectableState(String name, boolean state) {
    return elementIsSelectableState(byCreate.createBy(name), state);
  }

  @Override
  public boolean elementIsSelectableState(ByType byType, String name, boolean state) {
    return elementIsSelectableState(ByFactory.buildBy(byType).createBy(name), state);
  }

  @Override
  public boolean elementIsSelectableState(ElementInfo elementInfo, boolean state) {
    return elementIsSelectableState(
        ByFactory.buildBy(elementInfo.getType()).createBy(elementInfo.getKey()), false);
  }

  @Override
  public boolean elementIsSelectableState(WebElement webElement, boolean state) {
    return waitingAction.expectedByBoolean(SELECTED_STATE(webElement, state));
  }

  @Override
  public boolean elementIsContainsText(By by, String text) {
    return waitingAction.expectedByBoolean(CONTAIN_TEXT(by, text));
  }

  @Override
  public boolean elementIsContainsText(String name, String text) {
    return elementIsContainsText(byCreate.createBy(name), text);
  }

  @Override
  public boolean elementIsContainsText(ByType byType, String name, String text) {
    return elementIsContainsText(ByFactory.buildBy(byType).createBy(name), text);
  }

  @Override
  public boolean elementIsContainsText(ElementInfo elementInfo, String text) {
    return elementIsContainsText(
        ByFactory.buildBy(elementInfo.getType()).createBy(elementInfo.getKey()), text);
  }

  @Override
  public boolean elementIsContainsText(WebElement element, String text) {
    return waitingAction.expectedByBoolean(CONTAIN_TEXT(element, text));
  }

  @Override
  public ElementImp findByContainsAnyText(By by, String text) {
    return findByExpected(CONTAIN_TEXT_ANY(by, text));
  }

  @Override
  public ElementImp findByContainsAnyText(String name, String text) {
    return findByContainsAnyText(byCreate.createBy(name), text);
  }

  @Override
  public ElementImp findByContainsAnyText(ByType byType, String name, String text) {
    return findByContainsAnyText(ByFactory.buildBy(byType).createBy(name), text);
  }

  @Override
  public ElementImp findByContainsAnyText(ElementInfo elementInfo, String text) {
    return findByContainsAnyText(
        ByFactory.buildBy(elementInfo.getType()).createBy(elementInfo.getKey()), text);
  }

  @Override
  public ElementImp findByContainsAnyText(List<WebElement> element, String text) {
    return findByExpected(CONTAIN_TEXT_ANY(element, text));
  }

  @Override
  public boolean elementIsEqualsText(By by, String text) {
    return waitingAction.expectedByBoolean(EQUALS_TEXT(by, text));
  }

  @Override
  public boolean elementIsEqualsText(String name, String text) {
    return elementIsEqualsText(byCreate.createBy(name), text);
  }

  @Override
  public boolean elementIsEqualsText(ByType byType, String name, String text) {
    return elementIsEqualsText(ByFactory.buildBy(byType).createBy(name), text);
  }

  @Override
  public boolean elementIsEqualsText(ElementInfo elementInfo, String text) {
    return elementIsEqualsText(
        ByFactory.buildBy(elementInfo.getType()).createBy(elementInfo.getKey()), text);
  }

  @Override
  public boolean elementIsEqualsText(WebElement element, String text) {
    return waitingAction.expectedByBoolean(EQUALS_TEXT(element, text));
  }

  @Override
  public ElementImp findByEqualsAnyText(By by, String text) {
    return findByExpected(EQUALS_TEXT_ANY(by, text));
  }

  @Override
  public ElementImp findByEqualsAnyText(String name, String text) {
    return findByEqualsAnyText(byCreate.createBy(name), text);
  }

  @Override
  public ElementImp findByEqualsAnyText(ByType byType, String name, String text) {
    return findByEqualsAnyText(ByFactory.buildBy(byType).createBy(name), text);
  }

  @Override
  public ElementImp findByEqualsAnyText(ElementInfo elementInfo, String text) {
    return findByEqualsAnyText(
        ByFactory.buildBy(elementInfo.getType()).createBy(elementInfo.getKey()), text);
  }

  @Override
  public ElementImp findByEqualsAnyText(List<WebElement> element, String text) {
    return findByExpected(EQUALS_TEXT_ANY(element, text));
  }
}
