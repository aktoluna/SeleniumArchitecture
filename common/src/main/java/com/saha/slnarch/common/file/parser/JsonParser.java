package com.saha.slnarch.common.file.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.saha.slnarch.common.exception.FileParseException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JsonParser implements Parser {

  @Override
  public <S> S parseFile(InputStream inputStream, Class<S> type)
      throws FileParseException, IOException {
    S t;
    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
    try {
      t = getGson().fromJson(inputStreamReader, type);
    } catch (Exception e) {
      throw new FileParseException(e.getMessage());
    } finally {
      inputStream.close();
      inputStreamReader.close();
    }
    return t;
  }

  @Override
  public <S> S parseFile(String text, Class<S> type) throws FileParseException, IOException {
    S t;
    try {
      t = getGson().fromJson(text, type);
    } catch (Exception e) {
      throw new FileParseException(e.getMessage());
    }
    return t;
  }

  private Gson getGson() {
    return new GsonBuilder().create();
  }
}
