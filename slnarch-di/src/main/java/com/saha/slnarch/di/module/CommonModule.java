package com.saha.slnarch.di.module;

import com.saha.slnarch.common.file.FileHelper;
import com.saha.slnarch.common.file.FileParser;
import com.saha.slnarch.common.file.FileReader;
import com.saha.slnarch.common.file.FileWriter;
import com.saha.slnarch.core.model.Configuration;
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
