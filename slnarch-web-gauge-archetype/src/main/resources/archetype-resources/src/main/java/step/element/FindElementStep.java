package ${groupId}.step.element;

public interface FindElementStep<T> {

  T findElementById(String id);

  T findElementByCss(String css);

  T findElementByKey(String key);

  T findElementsById(String id);

  T findElementsByCss(String css);

  T findElementsByKey(String key);

  T getT();

}
