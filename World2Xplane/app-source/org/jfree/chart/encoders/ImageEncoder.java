package org.jfree.chart.encoders;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

public interface ImageEncoder {
  byte[] encode(BufferedImage paramBufferedImage) throws IOException;
  
  void encode(BufferedImage paramBufferedImage, OutputStream paramOutputStream) throws IOException;
  
  float getQuality();
  
  void setQuality(float paramFloat);
  
  boolean isEncodingAlpha();
  
  void setEncodingAlpha(boolean paramBoolean);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\encoders\ImageEncoder.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */