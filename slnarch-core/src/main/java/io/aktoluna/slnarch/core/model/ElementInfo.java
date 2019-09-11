package io.aktoluna.slnarch.core.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.aktoluna.slnarch.core.element.by.ByType;

public class ElementInfo {

  @SerializedName("key")
  @Expose
  private String key;
  @SerializedName("type")
  @Expose
  private ByType type;
  @SerializedName("value")
  @Expose
  private String value;

  public ByType getType() {
    return type;
  }

  public void setType(ByType type) {
    this.type = type;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}
