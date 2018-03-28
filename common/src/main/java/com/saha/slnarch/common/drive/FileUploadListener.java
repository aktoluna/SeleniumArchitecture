package com.saha.slnarch.common.drive;

import com.google.api.client.googleapis.media.MediaHttpUploader;
import com.google.api.client.googleapis.media.MediaHttpUploaderProgressListener;
import com.saha.slnarch.common.log.LogHelper;
import java.io.IOException;
import java.text.NumberFormat;
import org.slf4j.Logger;

public class FileUploadListener implements MediaHttpUploaderProgressListener {

  Logger logger = LogHelper.getSlnLogger();

  @Override
  public void progressChanged(MediaHttpUploader mediaHttpUploader) throws IOException {
    switch (mediaHttpUploader.getUploadState()) {
      case INITIATION_STARTED:
        logger.info("Upload Initiation has started.");
        break;
      case INITIATION_COMPLETE:
        logger.info("Upload Initiation is Complete.");
        break;
      case MEDIA_IN_PROGRESS:
        logger.info("Upload is In Progress: " + NumberFormat.getPercentInstance()
            .format(mediaHttpUploader.getProgress()));
        break;
      case MEDIA_COMPLETE:
        logger.info("Upload is Complete!");
        break;
    }
  }
}
