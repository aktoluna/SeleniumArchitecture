package ${groupId}.step.element;

import com.saha.slnarch.core.di.Injectable;
import com.saha.slnarch.core.element.Element;
import com.saha.slnarch.core.element.by.ByType;
import javax.inject.Inject;
import org.openqa.selenium.By;

public class FindElementStepImpl<T> implements FindElementStep<T>, Injectable {

  @Inject
  Element elementAction;

  public FindElementStepImpl() {
    inject();
  }


  @Override
  public T findElementById(String id) {
    elementAction.find(ByType.ID, id);
    return getT();
  }

  @Override
  public T findElementByCss(String css) {
    elementAction.find(ByType.CSS, css);
    return getT();
  }

  @Override
  public T findElementByKey(String key) {
    elementAction.find(By.cssSelector(key));
    return getT();
  }

  @Override
  public T findElementsById(String id) {
    elementAction.finds(By.id(id));
    return getT();
  }

  @Override
  public T findElementsByCss(String css) {
    elementAction.finds(By.cssSelector(css));
    return getT();
  }

  @Override
  public T findElementsByKey(String key) {
    elementAction.finds(By.cssSelector(key));
    return getT();
  }

  @Override
  public T getT() {
    return null;
  }
}
