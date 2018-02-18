package com.saha.slnarch.common.image;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import net.coobird.thumbnailator.Thumbnails;

public class ImageHelper {

  private ImageHelper() {

  }

  public static File resizeImageAndConvert(File file, int width, int height, String outputType,
      String outputPath, float compressLevel) throws IOException {
    BufferedImage image = getFileToImage(file);
    image = resizeImage(image, width, height, getImageType(image));
    return convertImageToOutputType(image, outputPath, outputType, compressLevel);
  }

  public static BufferedImage getFileToImage(File file) throws IOException {
    return ImageIO.read(file);
  }

  public static int getImageType(BufferedImage image) {
    return image.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : image.getType();
  }

  public static BufferedImage resizeImage(BufferedImage originalImage, int width,
      int height, int type) {
    BufferedImage resizedImage = new BufferedImage(width, height, type);
    Graphics2D g = resizedImage.createGraphics();
    g.drawImage(originalImage, 0, 0, width, height, null);
    g.dispose();
    g.setComposite(AlphaComposite.Src);

    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
        RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    g.setRenderingHint(RenderingHints.KEY_RENDERING,
        RenderingHints.VALUE_RENDER_QUALITY);
    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);

    return resizedImage;
  }

  public static BufferedImage resizeImage(BufferedImage image, int width, int height) {
    BufferedImage resizedImage = new BufferedImage(width, height,
        BufferedImage.TYPE_INT_ARGB);
    Graphics2D g = resizedImage.createGraphics();
    g.drawImage(image, 0, 0, width, height, null);
    g.dispose();
    return resizedImage;
  }


  public static BufferedImage blurImage(BufferedImage image) {
    float ninth = 1.0f / 9.0f;
    float[] blurKernel = {
        ninth, ninth, ninth,
        ninth, ninth, ninth,
        ninth, ninth, ninth
    };
    Map map = new HashMap();
    map.put(RenderingHints.KEY_INTERPOLATION,
        RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    map.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
    map.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    RenderingHints hints = new RenderingHints(map);
    BufferedImageOp op = new ConvolveOp(new Kernel(3, 3, blurKernel), ConvolveOp.EDGE_NO_OP, hints);
    return op.filter(image, null);
  }

  public static File convertImageToOutputType(String filePath, String outputPath, String outputType,
      float compressLevel) throws IOException {
    return convertImageToOutputType(getFileToImage(new File(filePath)), outputPath, outputType,
        compressLevel);
  }

  public static File convertImageToOutputType(BufferedImage image, String outputPath,
      String outputType,
      float compressLevel) throws IOException {
    File compressedImageFile = new File(outputPath);
    OutputStream os = new FileOutputStream(compressedImageFile);
    Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName(outputType);
    ImageWriter writer = writers.next();
    ImageOutputStream ios = ImageIO.createImageOutputStream(os);
    writer.setOutput(ios);
    ImageWriteParam param = writer.getDefaultWriteParam();
    if (param.canWriteCompressed()) {
      param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
      param.setCompressionQuality(compressLevel);
    }
    writer.write(null, new IIOImage(image, null, null), param);
    return compressedImageFile;
  }

  public static File resizeWithThumbnails(File file, int width, int height) throws IOException {
    File outputFile = new File(getOutputFilePath(file));
    Thumbnails.of(file)
        .size(width, height)
        .outputFormat("jpg")
        .outputQuality(0.5f)
        .toFile(outputFile);
    return outputFile;
  }

  private static String getOutputFilePath(File inputFile) {
    return inputFile.getAbsolutePath().replace(".png", ".jpg");
  }
}
