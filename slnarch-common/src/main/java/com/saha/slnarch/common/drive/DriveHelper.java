package com.saha.slnarch.common.drive;


import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.batch.BatchRequest;
import com.google.api.client.googleapis.batch.json.JsonBatchCallback;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.googleapis.media.MediaHttpUploader;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.Permission;
import com.saha.slnarch.common.log.LogHelper;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import org.slf4j.Logger;

public class DriveHelper {

  private Logger logger = LogHelper.getSlnLogger();
  private String APPLICATION_NAME = "FlyPgsWeb";
  private HttpTransport HTTP_TRANSPORT;
  private final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
  private FileDataStoreFactory DATA_STORE_FACTORY;
  private final java.io.File DATA_STORE_DIR =
      new java.io.File(System.getProperty("user.home"), ".store/drive_sample");

  private Drive drive;


  public Credential authorize() throws IOException {
    return GoogleCredential
        .fromStream(DriveHelper.class.getResourceAsStream("/client-secrets.json"))
        .createScoped(Collections.singleton(DriveScopes.DRIVE));
  }

  public void createDriver() throws IOException {
    try {
      HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
    } catch (GeneralSecurityException e) {
      e.printStackTrace();
    }
    DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
    Credential credential = authorize();
    drive = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).setApplicationName(
        APPLICATION_NAME).build();
  }

  public File uploadFile(java.io.File file) throws IOException {
    File fileMetadata = new File();
    fileMetadata.setTitle(file.getName());

    FileContent mediaContent = new FileContent("*/*", file);
    Drive.Files.Insert insert = drive.files().insert(fileMetadata, mediaContent);
    MediaHttpUploader uploader = insert.getMediaHttpUploader();
    uploader.setDirectUploadEnabled(false);
    uploader.setProgressListener(new FileUploadListener());
    return insert.execute();
  }


  public void shareFile(File file, String to) throws IOException {
    BatchRequest batch = drive.batch();
    Permission userPermission = new Permission()
        .setType("user")
        .setRole("writer")
        .setValue(to);
    drive.permissions().insert(file.getId(), userPermission)
        .setSendNotificationEmails(true)
        .setFields("id")
        .queue(batch, callback);
    batch.execute();


  }

  JsonBatchCallback<Permission> callback = new JsonBatchCallback<Permission>() {
    @Override
    public void onFailure(GoogleJsonError e,
        HttpHeaders responseHeaders) {
      logger.warn("Share Link Fail {}", e);
    }

    @Override
    public void onSuccess(Permission permission,
        HttpHeaders responseHeaders) {
      logger.info("Shared File Success  id={}  emails={}", permission.getId(),
          permission.getEmailAddress());
    }
  };

}
