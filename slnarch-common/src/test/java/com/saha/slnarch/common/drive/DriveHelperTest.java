package com.saha.slnarch.common.drive;

import java.io.File;
import java.io.IOException;
import org.junit.Test;

public class DriveHelperTest {

  @Test
  public void uploadFile() throws IOException {
    DriveHelper driveHelper = new DriveHelper();
    driveHelper.createDriver();
    com.google.api.services.drive.model.File file = driveHelper
        .uploadFile(new File("src/test/resources/welcome.json"));
    driveHelper.shareFile(file, "ali.aktolun@sahabt.com");

  }
}