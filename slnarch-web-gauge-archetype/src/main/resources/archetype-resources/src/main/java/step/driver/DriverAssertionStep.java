package ${groupId}.step.driver;

public interface DriverAssertionStep {

  void pageSourceContainsText(String text);

  void pageSourceNotContainsText(String text);

  void pageTitleContainsText(String text);

  void pageTitleNotContainsText(String text);

  void pageTitleEqualsText(String text);

  void pageUrlContainsText(String text);

  void pageUrlNotContainsText(String text);

  void pageUrlEqualsText(String text);

  void anyCookieValueEqualsText(String text);

  void anyCookieValueNotEqualsText(String text);


}
