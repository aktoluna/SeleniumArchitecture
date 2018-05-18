package ${groupId}.step.element;

public interface WebElementAssertionStep {

  void elementIsPresent();

  void elementIsPresentById(String id);

  void elementIsPresentByCss(String css);

  void elementIsPresentByKey(String key);

  void elementIsNotPresent();

  void elementIsNotPresentById(String id);

  void elementIsNotPresentByCss(String css);

  void elementIsNotPresentByKey(String key);

  void elementIsClickable();

  void elementIsClickableById(String id);

  void elementIsClickableByCss(String css);

  void elementIsClickableByKey(String key);

  void elementIsNotClickable();

  void elementIsNotClickableById(String id);

  void elementIsNotClickableByCss(String css);

  void elementIsNotClickableByKey(String key);

  void elementIsEnabled();

  void elementIsEnabledById(String id);

  void elementIsEnabledByCss(String css);

  void elementIsEnabledByKey(String key);

  void elementIsNotEnabled();

  void elementIsNotEnabledById(String id);

  void elementIsNotEnabledByCss(String css);

  void elementIsNotEnabledByKey(String key);

  void elementIsVisible();

  void elementIsVisibleById(String id);

  void elementIsVisibleByCss(String css);

  void elementIsVisibleByKey(String key);

  void elementIsNotVisible();

  void elementIsNotVisibleById(String id);

  void elementIsNotVisibleByCss(String css);

  void elementIsNotVisibleByKey(String key);

  void elementIsEqualsText(String text);

  void elementIsEqualsTextById(String id, String text);

  void elementIsEqualsTextByCss(String css, String text);

  void elementIsEqualsTextByKey(String key, String text);

  void elementIsNotEqualsText(String text);

  void elementIsNotEqualsTextById(String id, String text);

  void elementIsNotEqualsTextByCss(String css, String text);

  void elementIsNotEqualsTextByKey(String key, String text);

  void elementIsContainsText(String text);

  void elementIsContainsTextById(String id, String text);

  void elementIsContainsTextByCss(String css, String text);

  void elementIsContainsTextByKey(String key, String text);

  void elementIsNotContainsText(String text);

  void elementIsNotContainsTextById(String id, String text);

  void elementIsNotContainsTextByCss(String css, String text);

  void elementIsNotContainsTextByKey(String key, String text);

  void elementAttributeIsEqualsText(String attributeName, String attributeValue);

  void elementAttributeIsEqualsTextById(String id, String attributeName, String attributeValue);

  void elementAttributeIsEqualsTextByCss(String css, String attributeName, String attributeValue);

  void elementAttributeIsEqualsTextByKey(String key, String attributeName, String attributeValue);

  void elementAttributeIsNotEqualsText(String attributeName, String attributeValue);

  void elementAttributeIsNotEqualsTextById(String id, String attributeName, String attributeValue);

  void elementAttributeIsNotEqualsTextByCss(String css, String attributeName,
      String attributeValue);

  void elementAttributeIsNotEqualsTextByKey(String key, String attributeName,
      String attributeValue);

  void elementAttributeContainsText(String attributeName, String attributeValue);

  void elementAttributeContainsTextById(String id, String attributeName, String attributeValue);

  void elementAttributeContainsTextByCss(String css, String attributeName, String attributeValue);

  void elementAttributeContainsTextByKey(String key, String attributeName, String attributeValue);

  void elementAttributeNotContainsText(String attributeName, String attributeValue);

  void elementAttributeNotContainsTextById(String id, String attributeName, String attributeValue);

  void elementAttributeNotContainsTextByCss(String css, String attributeName,
      String attributeValue);

  void elementAttributeNotContainsTextByKey(String key, String attributeName,
      String attributeValue);

  void elementCssValueIsEqualsText(String cssName, String cssValue);

  void elementCssValueIsEqualsTextById(String id, String cssName, String cssValue);

  void elementCssValueIsEqualsTextByCss(String css, String cssName, String cssValue);

  void elementCssValueIsEqualsTextByKey(String key, String cssName, String cssValue);

  void elementCssValueIsNotEqualsText(String cssName, String cssValue);

  void elementCssValueIsNotEqualsTextById(String id, String cssName, String cssValue);

  void elementCssValueIsNotEqualsTextByCss(String css, String cssName, String cssValue);

  void elementCssValueIsNotEqualsTextByKey(String key, String cssName, String cssValue);

  void elementCssValueContainsText(String cssName, String cssValue);

  void elementCssValueContainsTextById(String id, String cssName, String cssValue);

  void elementCssValueContainsTextByCss(String css, String cssName, String cssValue);

  void elementCssValueContainsTextByKey(String key, String cssName, String cssValue);

  void elementCssValueNotContainsText(String cssName, String cssValue);

  void elementCssValueNotContainsTextById(String id, String cssName, String cssValue);

  void elementCssValueNotContainsTextByCss(String css, String cssName, String cssValue);

  void elementCssValueNotContainsTextByKey(String key, String cssName, String cssValue);
}
