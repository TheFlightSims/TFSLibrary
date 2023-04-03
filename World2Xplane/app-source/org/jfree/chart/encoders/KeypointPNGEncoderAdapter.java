/*     */ package org.jfree.chart.encoders;
/*     */ 
/*     */ import com.keypoint.PngEncoder;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ 
/*     */ public class KeypointPNGEncoderAdapter implements ImageEncoder {
/*  59 */   private int quality = 9;
/*     */   
/*     */   private boolean encodingAlpha = false;
/*     */   
/*     */   public float getQuality() {
/*  70 */     return this.quality;
/*     */   }
/*     */   
/*     */   public void setQuality(float quality) {
/*  82 */     this.quality = (int)quality;
/*     */   }
/*     */   
/*     */   public boolean isEncodingAlpha() {
/*  91 */     return this.encodingAlpha;
/*     */   }
/*     */   
/*     */   public void setEncodingAlpha(boolean encodingAlpha) {
/* 101 */     this.encodingAlpha = encodingAlpha;
/*     */   }
/*     */   
/*     */   public byte[] encode(BufferedImage bufferedImage) throws IOException {
/* 112 */     if (bufferedImage == null)
/* 113 */       throw new IllegalArgumentException("Null 'image' argument."); 
/* 115 */     PngEncoder encoder = new PngEncoder(bufferedImage, this.encodingAlpha, 0, this.quality);
/* 117 */     return encoder.pngEncode();
/*     */   }
/*     */   
/*     */   public void encode(BufferedImage bufferedImage, OutputStream outputStream) throws IOException {
/* 130 */     if (bufferedImage == null)
/* 131 */       throw new IllegalArgumentException("Null 'image' argument."); 
/* 133 */     if (outputStream == null)
/* 134 */       throw new IllegalArgumentException("Null 'outputStream' argument."); 
/* 136 */     PngEncoder encoder = new PngEncoder(bufferedImage, this.encodingAlpha, 0, this.quality);
/* 138 */     outputStream.write(encoder.pngEncode());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\encoders\KeypointPNGEncoderAdapter.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */