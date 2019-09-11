package io.aktoluna.slnarch.core.element.by;

import com.google.gson.annotations.SerializedName;

public enum ByType {
  @SerializedName("Css")
  CSS,
  @SerializedName("Xpath")
  XPATH,
  @SerializedName("Tag")
  TAG,
  @SerializedName("Name")
  NAME,
  @SerializedName("Class")
  CLASS,
  @SerializedName("Id")
  ID;
}
