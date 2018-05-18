package ${groupId}.step.element;

import com.thoughtworks.gauge.Step;
import org.assertj.core.api.Assertions;

public class WebElementAssertionStepImpl extends
    FindElementStepImpl<WebElementAssertionStep> implements WebElementAssertionStep {

  @Override
  public WebElementAssertionStep getT() {
    return this;
  }

  @Step("Check element present")
  @Override
  public void elementIsPresent() {
    Assertions.assertThat(elementAction.getElement())
        .isNotNull();
  }

  @Step("Check element present by id <id>")
  @Override
  public void elementIsPresentById(String id) {
    findElementById(id).elementIsPresent();
  }

  @Step("Check element present by css <css>")
  @Override
  public void elementIsPresentByCss(String css) {
    findElementByCss(css).elementIsPresent();
  }

  @Step("Check element present by key <key>")
  @Override
  public void elementIsPresentByKey(String key) {
    findElementByKey(key).elementIsPresent();
  }


  @Step("Check element not present")
  @Override
  public void elementIsNotPresent() {
    Assertions.assertThat(elementAction.getElement())
        .isNull();
  }

  @Step("Check element not present by id <id>")
  @Override
  public void elementIsNotPresentById(String id) {
    findElementById(id).elementIsNotPresent();
  }

  @Step("Check element not present by css <css>")
  @Override
  public void elementIsNotPresentByCss(String css) {
    findElementByCss(css).elementIsNotPresent();
  }

  @Step("Check element not present by key <key>")
  @Override
  public void elementIsNotPresentByKey(String key) {
    findElementByKey(key).elementIsNotPresent();
  }

  @Step("Check element clickable")
  @Override
  public void elementIsClickable() {
    Assertions.assertThat(elementAction.getElement().isEnabled())
        .isTrue();
  }

  @Step("Check element clickable by id <id>")
  @Override
  public void elementIsClickableById(String id) {
    findElementById(id).elementIsClickable();
  }

  @Step("Check element clickable by css <css>")
  @Override
  public void elementIsClickableByCss(String css) {
    findElementByCss(css).elementIsClickable();
  }

  @Step("Check element clickable by key <key>")
  @Override
  public void elementIsClickableByKey(String key) {
    findElementByKey(key).elementIsClickable();
  }

  @Step("Check element not clickable")
  @Override
  public void elementIsNotClickable() {
    Assertions.assertThat(elementAction.getElement().isEnabled())
        .isFalse();
  }

  @Step("Check element not clickable by id <id>")
  @Override
  public void elementIsNotClickableById(String id) {
    findElementById(id).elementIsNotClickable();
  }

  @Step("Check element not clickable by css <css>")
  @Override
  public void elementIsNotClickableByCss(String css) {
    findElementByCss(css).elementIsNotClickable();
  }

  @Step("Check element not clickable by key <key>")
  @Override
  public void elementIsNotClickableByKey(String key) {
    findElementByKey(key).elementIsNotClickable();
  }

  @Step("Check element enabled")
  @Override
  public void elementIsEnabled() {
    Assertions.assertThat(elementAction.isEnabled()).isTrue();
  }

  @Step("Check element enable by id <id>")
  @Override
  public void elementIsEnabledById(String id) {
    findElementById(id).elementIsEnabled();
  }

  @Step("Check element enable by css <css>")
  @Override
  public void elementIsEnabledByCss(String css) {
    findElementByCss(css).elementIsEnabled();
  }

  @Step("Check element enable by key <key>")
  @Override
  public void elementIsEnabledByKey(String key) {
    findElementByKey(key).elementIsEnabled();
  }

  @Step("Check element not enable")
  @Override
  public void elementIsNotEnabled() {
    Assertions.assertThat(elementAction.isEnabled()).isFalse();
  }

  @Step("Check element not enable by id <id>")
  @Override
  public void elementIsNotEnabledById(String id) {
    findElementById(id).elementIsNotEnabled();
  }

  @Step("Check element not enable by css <css>")
  @Override
  public void elementIsNotEnabledByCss(String css) {
    findElementByCss(css).elementIsNotEnabled();
  }

  @Step("Check element not enable by key <key>")
  @Override
  public void elementIsNotEnabledByKey(String key) {
    findElementByKey(key).elementIsNotEnabled();
  }

  @Step("Check element visible")
  @Override
  public void elementIsVisible() {
    Assertions.assertThat(elementAction.getElement().isDisplayed()).isTrue();
  }

  @Step("Check element visible by id <id>")
  @Override
  public void elementIsVisibleById(String id) {
    findElementById(id).elementIsVisible();
  }

  @Step("Check element visible by css <css>")
  @Override
  public void elementIsVisibleByCss(String css) {
    findElementByCss(css).elementIsVisible();
  }

  @Step("Check element visible by key <key>")
  @Override
  public void elementIsVisibleByKey(String key) {
    findElementByKey(key).elementIsVisible();
  }

  @Step("Check element not visible")
  @Override
  public void elementIsNotVisible() {
    Assertions.assertThat(elementAction.getElement().isDisplayed()).isFalse();
  }

  @Step("Check element not visible by id <id>")
  @Override
  public void elementIsNotVisibleById(String id) {
    findElementById(id).elementIsNotVisible();
  }

  @Step("Check element not visible by css <css>")
  @Override
  public void elementIsNotVisibleByCss(String css) {
    findElementByCss(css).elementIsNotVisible();
  }

  @Step("Check element not visible by key <key>")
  @Override
  public void elementIsNotVisibleByKey(String key) {
    findElementByKey(key).elementIsNotVisible();
  }

  @Step("Check element text equals <text>")
  @Override
  public void elementIsEqualsText(String text) {
    Assertions.assertThat(elementAction.getElement().getText())
        .isEqualTo(text);
  }

  @Step("Check element text equals by id <id> and <text>")
  @Override
  public void elementIsEqualsTextById(String id, String text) {
    findElementById(id).elementIsEqualsText(text);
  }

  @Step("Check element text equals by css <css> and <text>")
  @Override
  public void elementIsEqualsTextByCss(String css, String text) {
    findElementByCss(css).elementIsEqualsText(text);
  }

  @Step("Check element text equals by key <key> and <text>")
  @Override
  public void elementIsEqualsTextByKey(String key, String text) {
    findElementByKey(key).elementIsEqualsText(text);
  }

  @Step("Check element text not equals <text>")
  @Override
  public void elementIsNotEqualsText(String text) {
    Assertions.assertThat(elementAction.getElement().getText())
        .isNotEqualTo(text);
  }

  @Step("Check element text not equals by id <id> and <text>")
  @Override
  public void elementIsNotEqualsTextById(String id, String text) {
    findElementById(id).elementIsNotEqualsText(text);
  }

  @Step("Check element text not equals by css <css> and <text>")
  @Override
  public void elementIsNotEqualsTextByCss(String css, String text) {
    findElementByCss(css).elementIsNotEqualsText(text);
  }

  @Step("Check element text not equals by key <key> and <text>")
  @Override
  public void elementIsNotEqualsTextByKey(String key, String text) {
    findElementByKey(key).elementIsNotEqualsText(text);
  }

  @Step("Check element text contains <text>")
  @Override
  public void elementIsContainsText(String text) {
    Assertions.assertThat(elementAction.getElement().getText())
        .contains(text);
  }

  @Step("Check element text contains by id <id> and <text>")
  @Override
  public void elementIsContainsTextById(String id, String text) {
    findElementById(id).elementIsContainsText(text);
  }

  @Step("Check element text contains by css <css> and <text>")
  @Override
  public void elementIsContainsTextByCss(String css, String text) {
    findElementByCss(css).elementIsContainsText(text);
  }

  @Step("Check element text contains by key <key> and <text>")
  @Override
  public void elementIsContainsTextByKey(String key, String text) {
    findElementByKey(key).elementIsContainsText(text);
  }

  @Step("Check element text not contains <text>")
  @Override
  public void elementIsNotContainsText(String text) {
    Assertions.assertThat(elementAction.getElement().getText())
        .doesNotContain(text);
  }

  @Step("Check element text not contains by id <id> and <text>")
  @Override
  public void elementIsNotContainsTextById(String id, String text) {
    findElementById(id).elementIsNotContainsText(text);
  }

  @Step("Check element text not contains by css <css> and <text>")
  @Override
  public void elementIsNotContainsTextByCss(String css, String text) {
    findElementByCss(css).elementIsNotContainsText(text);
  }

  @Step("Check element text not contains by key <key> and <text>")
  @Override
  public void elementIsNotContainsTextByKey(String key, String text) {
    findElementByKey(key).elementIsNotContainsText(text);
  }

  @Step("Check element <attributeName> value equals <attributeValue>")
  @Override
  public void elementAttributeIsEqualsText(String attributeName, String attributeValue) {
    Assertions.assertThat(elementAction.getElement().getAttribute(attributeName))
        .isEqualTo(attributeValue);
  }

  @Step("Check element by id <id> <attributeName> value equals <attributeValue>")
  @Override
  public void elementAttributeIsEqualsTextById(String id, String attributeName,
      String attributeValue) {
    findElementById(id).elementAttributeIsEqualsText(attributeName, attributeValue);
  }

  @Step("Check element by css <css> <attributeName> value equals <attributeValue>")
  @Override
  public void elementAttributeIsEqualsTextByCss(String css, String attributeName,
      String attributeValue) {
    findElementByCss(css).elementAttributeIsEqualsText(attributeName, attributeValue);
  }

  @Step("Check element by key <key> <attributeName> value equals <attributeValue>")
  @Override
  public void elementAttributeIsEqualsTextByKey(String key, String attributeName,
      String attributeValue) {
    findElementByKey(key).elementAttributeIsEqualsText(attributeName, attributeValue);
  }

  @Step("Check element by key <key> <attributeName> value not equals <attributeValue>")
  @Override
  public void elementAttributeIsNotEqualsText(String attributeName, String attributeValue) {
    Assertions.assertThat(elementAction.getElement().getAttribute(attributeName))
        .isNotEqualTo(attributeValue);
  }

  @Step("Check element by key <key> <attributeName> value not equals <attributeValue>")
  @Override
  public void elementAttributeIsNotEqualsTextById(String id, String attributeName,
      String attributeValue) {
    findElementById(id).elementAttributeIsNotEqualsText(attributeName, attributeValue);
  }

  @Step("Check element by key <key> <attributeName> value not equals <attributeValue>")
  @Override
  public void elementAttributeIsNotEqualsTextByCss(String css, String attributeName,
      String attributeValue) {
    findElementByCss(css).elementAttributeIsNotEqualsText(attributeName, attributeValue);
  }

  @Step("Check element by key <key> <attributeName> value not equals <attributeValue>")
  @Override
  public void elementAttributeIsNotEqualsTextByKey(String key, String attributeName,
      String attributeValue) {
    findElementByKey(key).elementAttributeIsNotEqualsText(attributeName, attributeValue);
  }

  @Step("Check element <attributeName> value contains <attributeValue>")
  @Override
  public void elementAttributeContainsText(String attributeName, String attributeValue) {
    Assertions.assertThat(elementAction.getElement().getAttribute(attributeName))
        .contains(attributeValue);
  }

  @Step("Check element by id <id> <attributeName> value contains <attributeValue>")
  @Override
  public void elementAttributeContainsTextById(String id, String attributeName,
      String attributeValue) {
    findElementById(id).elementAttributeContainsText(attributeName, attributeValue);
  }

  @Step("Check element by css <css> <attributeName> value contains <attributeValue>")
  @Override
  public void elementAttributeContainsTextByCss(String css, String attributeName,
      String attributeValue) {
    findElementByCss(css).elementAttributeContainsText(attributeName, attributeValue);
  }

  @Step("Check element by key <key> <attributeName> value contains <attributeValue>")
  @Override
  public void elementAttributeContainsTextByKey(String key, String attributeName,
      String attributeValue) {
    findElementByKey(key).elementAttributeContainsText(attributeName, attributeValue);
  }

  @Step("Check element <attributeName> value not contains <attributeValue>")
  @Override
  public void elementAttributeNotContainsText(String attributeName, String attributeValue) {
    Assertions.assertThat(elementAction.getElement().getAttribute(attributeName))
        .doesNotContain(attributeValue);
  }

  @Step("Check element by id <id> <attributeName> value not contains <attributeValue>")
  @Override
  public void elementAttributeNotContainsTextById(String id, String attributeName,
      String attributeValue) {
    findElementById(id).elementAttributeNotContainsText(attributeName, attributeValue);
  }

  @Step("Check element by css <css> <attributeName> value not contains <attributeValue>")
  @Override
  public void elementAttributeNotContainsTextByCss(String css, String attributeName,
      String attributeValue) {
    findElementByCss(css).elementAttributeNotContainsText(attributeName, attributeValue);
  }

  @Step("Check element by key <key> <attributeName> value not contains <attributeValue>")
  @Override
  public void elementAttributeNotContainsTextByKey(String key, String attributeName,
      String attributeValue) {
    findElementByKey(key).elementAttributeNotContainsText(attributeName, attributeValue);
  }

  @Step("Check element <cssName> value equals <cssValue>")
  @Override
  public void elementCssValueIsEqualsText(String cssName, String cssValue) {
    Assertions.assertThat(elementAction.getElement().getCssValue(cssName))
        .isEqualTo(cssValue);
  }

  @Step("Check element by id <id> <cssName> value equals <cssValue>")
  @Override
  public void elementCssValueIsEqualsTextById(String id, String cssName, String cssValue) {
    findElementById(id).elementCssValueIsEqualsText(cssName, cssValue);
  }

  @Step("Check element by css <css> <cssName> value equals <cssValue>")
  @Override
  public void elementCssValueIsEqualsTextByCss(String css, String cssName, String cssValue) {
    findElementByCss(css).elementCssValueIsEqualsText(cssName, cssValue);
  }

  @Step("Check element by key <key> <cssName> value equals <cssValue>")
  @Override
  public void elementCssValueIsEqualsTextByKey(String key, String cssName, String cssValue) {
    findElementByKey(key).elementCssValueIsEqualsText(cssName, cssValue);
  }

  @Step("Check element <cssName> value not equals <cssValue>")
  @Override
  public void elementCssValueIsNotEqualsText(String cssName, String cssValue) {
    Assertions.assertThat(elementAction.getElement().getCssValue(cssName))
        .isNotEqualTo(cssValue);
  }

  @Step("Check element by id <id> <cssName> value not equals <cssValue>")
  @Override
  public void elementCssValueIsNotEqualsTextById(String id, String cssName, String cssValue) {
    findElementById(id).elementCssValueIsNotEqualsText(cssName, cssValue);
  }

  @Step("Check element by css <css> <cssName> value not equals <cssValue>")
  @Override
  public void elementCssValueIsNotEqualsTextByCss(String css, String cssName, String cssValue) {
    findElementByCss(css).elementCssValueIsNotEqualsText(cssName, cssValue);
  }

  @Step("Check element by key <key> <cssName> value not equals <cssValue>")
  @Override
  public void elementCssValueIsNotEqualsTextByKey(String key, String cssName, String cssValue) {
    findElementByKey(key).elementCssValueIsNotEqualsText(cssName, cssValue);
  }

  @Step("Check element <cssName> value contains <cssValue>")
  @Override
  public void elementCssValueContainsText(String cssName, String cssValue) {
    Assertions.assertThat(elementAction.getElement().getCssValue(cssName))
        .contains(cssValue);
  }

  @Step("Check element by id <id> <cssName> value contains <cssValue>")
  @Override
  public void elementCssValueContainsTextById(String id, String cssName, String cssValue) {
    findElementById(id).elementCssValueContainsText(cssName, cssValue);
  }

  @Step("Check element by css <css> <cssName> value contains <cssValue>")
  @Override
  public void elementCssValueContainsTextByCss(String css, String cssName, String cssValue) {
    findElementByCss(css).elementCssValueContainsText(cssName, cssValue);
  }

  @Step("Check element by key <key> <cssName> value contains <cssValue>")
  @Override
  public void elementCssValueContainsTextByKey(String key, String cssName, String cssValue) {
    findElementByKey(key).elementCssValueContainsText(cssName, cssValue);
  }

  @Step("Check element <cssName> value not contains <cssValue>")
  @Override
  public void elementCssValueNotContainsText(String cssName, String cssValue) {
    Assertions.assertThat(elementAction.getElement().getCssValue(cssName))
        .doesNotContain(cssValue);
  }

  @Step("Check element by id <id> <cssName> value not contains <cssValue>")
  @Override
  public void elementCssValueNotContainsTextById(String id, String cssName, String cssValue) {
    findElementById(id).elementCssValueNotContainsText(cssName, cssValue);
  }

  @Step("Check element by css <css> <cssName> value not contains <cssValue>")
  @Override
  public void elementCssValueNotContainsTextByCss(String css, String cssName, String cssValue) {
    findElementByCss(css).elementCssValueNotContainsText(cssName, cssValue);
  }

  @Step("Check element by key <key> <cssName> value not contains <cssValue>")
  @Override
  public void elementCssValueNotContainsTextByKey(String key, String cssName, String cssValue) {
    findElementByKey(key).elementCssValueNotContainsText(cssName, cssValue);
  }
}
