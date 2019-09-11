package io.aktoluna.slnarch.common.file;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Prop {
  String key();

  PropType type() default PropType.STRING;

  enum PropType {
    STRING,
    INT,
    DOUBLE,
    FLOAT,
    BOOL
  }
}
