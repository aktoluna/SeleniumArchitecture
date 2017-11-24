package com.saha.slnarch.common.file;

import com.google.common.base.Preconditions;
import com.saha.slnarch.common.file.parser.JsonParser;
import com.saha.slnarch.common.file.parser.Parser;
import com.saha.slnarch.common.file.parser.YamlParser;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.opc.internal.ZipHelper;

public class FileHelper implements FileParser, FileReader, FileWriter {

  private static FileHelper instance;
  private static Object lock = new Object();

  private static final Charset UTF8 = StandardCharsets.UTF_8;
  private static final String DOT = ".";
  private static final String YAML = DOT + "yaml";
  private static final String JSON = DOT + "json";
  private static final String XML = DOT + "xml";
  private static final String PROP = DOT + "properties";

  private FileHelper() {

  }

  public static FileHelper getInstance() {
    synchronized (lock) {
      if (instance == null) {
        instance = new FileHelper();
      }
    }
    return instance;
  }

  @Override
  public <T> T parseFile(String filePath, Class<T> output) {
    return parseFile(filePath, output, true);
  }

  @Override
  public <S> S parseFile(String filePath, Class<S> output, boolean classPath) {
    Preconditions.checkNotNull(filePath);
    S t = null;
    try {
      t = getParser(filePath).parseFile(getFileStream(filePath, classPath), output);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return t;
  }

  private Parser getParser(String filePath) throws Exception {
    Parser parser = null;
    if (filePath.endsWith(YAML)) {
      parser = new YamlParser();
    } else if (filePath.endsWith(JSON)) {
      parser = new JsonParser();
    } else {
      throw new Exception("File Type Not Found");
    }
    return parser;
  }


  @Override
  public String readFileAsString(String filePath) {
    InputStream inputStream = null;
    try {
      inputStream = getFileStream(filePath);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return toString(inputStream);
  }

  @Override
  public String readFileAsString(File file) {
    return null;
  }

  @Override
  public String readFileAsString(URI uri) {
    return null;
  }

  public InputStream getFileStream(String filePath) throws Exception {
    return getFileStream(filePath, true);
  }


  public InputStream getFileStream(String filePath, boolean classPath) throws Exception {
    try {
      return classPath ? getClass().getClassLoader().getResourceAsStream(filePath)
          : new FileInputStream(filePath);
    } catch (Exception e) {
      throw new Exception(e);
    }
  }


  private File toFile(String path) {
    return new File(path);
  }

  private URI toUrI(String path) {
    return toFile(path).getAbsoluteFile().toURI();
  }

  private URI toUrI(File file) {
    return file.getAbsoluteFile().toURI();
  }


  private ByteArrayOutputStream toByteStream(InputStream is) {
    ByteArrayOutputStream result = new ByteArrayOutputStream();
    byte[] buffer = new byte[1024];
    int length;
    try {
      while ((length = is.read(buffer)) != -1) {
        result.write(buffer, 0, length);
      }
      return result;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }


  private String toString(InputStream is) {
    try {
      return toByteStream(is).toString(UTF8.name());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private String toString(byte[] bytes) {
    if (bytes == null) {
      return null;
    }
    return new String(bytes, UTF8);
  }

  private byte[] toBytes(InputStream is) {
    return toByteStream(is).toByteArray();
  }


  @Override
  public boolean writeToFile(String path, String data) {
    return false;
  }

  @Override
  public boolean writeToFile(File file, String data) {
    return false;
  }

  public static void deleteFile(File file) {
    file.deleteOnExit();
  }

  public static void copyFile(File file, File destFile) throws IOException {
    FileUtils.copyFile(file, destFile);
  }

  public static void moveFile(File file, File destFile) throws IOException {
    copyFile(file, destFile);
    deleteFile(file);
  }

  public static boolean createDirectory(String path) throws IOException {
    File file = new File(path);
    if (!file.exists()) {
      return true;
    }
    return file.mkdir();
  }

  public static void deleteDirectory(String path) throws IOException {
    FileUtils.deleteDirectory(new File(path));
  }

  public static boolean deleteAndCreateDirectory(String path) throws IOException {
    deleteDirectory(path);
    return createDirectory(path);
  }
}
