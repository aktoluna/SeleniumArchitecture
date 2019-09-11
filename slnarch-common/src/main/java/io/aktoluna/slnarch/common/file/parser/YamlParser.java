package io.aktoluna.slnarch.common.file.parser;

import io.aktoluna.slnarch.common.exception.FileParseException;
import java.io.IOException;
import java.io.InputStream;
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

public class YamlParser implements Parser {

  @Override
  public <S> S parseFile(String text, Class<S> type) throws FileParseException, IOException {
    S t;
    try {
      t = getNewInstance(type).loadAs(text, type);
    } catch (Exception e) {
      throw new FileParseException(e.getMessage());
    }
    return t;
  }

  @Override
  public <S> S parseFile(InputStream inputStream, Class<S> type)
      throws FileParseException, IOException {
    S t;
    try {
      t = getNewInstance(type).loadAs(inputStream, type);
    } catch (Exception e) {
      throw new FileParseException(e.getMessage());
    } finally {
      inputStream.close();
    }
    return t;
  }

  private Yaml getNewInstance(Class<?> clazz) {
    Constructor constructor = new Constructor(clazz);
    TypeDescription typeDescription = new TypeDescription(clazz);
    constructor.addTypeDescription(typeDescription);
    return new Yaml(constructor);
  }
}
