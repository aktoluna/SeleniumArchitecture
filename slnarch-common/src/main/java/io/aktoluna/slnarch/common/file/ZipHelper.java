package io.aktoluna.slnarch.common.file;

import java.io.File;
import org.zeroturnaround.zip.ZipUtil;

public class ZipHelper {

  public static void zipDirectory(String directoryPath, String outputPath) {
    zipDirectory(new File(directoryPath), new File(outputPath));
  }

  public static void zipDirectory(File directoryPath, File outputPath) {
    zipDirectory(directoryPath, outputPath, ZipUtil.DEFAULT_COMPRESSION_LEVEL);
  }

  public static void zipDirectory(File directoryPath, File outputPath, int compressLevel) {
    ZipUtil.pack(directoryPath, outputPath, compressLevel);
  }
}
