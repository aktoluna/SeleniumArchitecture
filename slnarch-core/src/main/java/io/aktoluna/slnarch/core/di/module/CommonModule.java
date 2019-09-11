package io.aktoluna.slnarch.core.di.module;

import io.aktoluna.slnarch.common.file.FileHelper;
import io.aktoluna.slnarch.common.file.FileParser;
import io.aktoluna.slnarch.common.file.FileReader;
import io.aktoluna.slnarch.common.file.FileWriter;
import javax.inject.Singleton;
import org.codejargon.feather.Provides;

public class CommonModule {

  @Provides
  @Singleton
  public FileParser provideFileParser() {
    return FileHelper.getInstance();
  }

  @Provides
  @Singleton
  public FileReader provideFileReader() {
    return FileHelper.getInstance();
  }

  @Provides
  @Singleton
  public FileWriter provideFileWrite() {
    return FileHelper.getInstance();
  }

}
