package com.saha.slnarch.common.file;

public interface FileParser {
  <S> S parseFile(String filePath, Class<S> output);

  <S> S parseFile(String filePath, Class<S> output,boolean classPath);
}
