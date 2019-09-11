package io.aktoluna.slnarch.common.file;

import java.io.File;

public interface FileWriter {

  boolean writeToFile(String path, String data);

  boolean writeToFile(File file, String data);
}
