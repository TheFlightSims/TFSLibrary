/*     */ package org.jfree.chart.encoders;
/*     */ 
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import javax.imageio.ImageIO;
/*     */ 
/*     */ public class SunPNGEncoderAdapter implements ImageEncoder {
/*     */   public float getQuality() {
/*  65 */     return 0.0F;
/*     */   }
/*     */   
/*     */   public void setQuality(float quality) {}
/*     */   
/*     */   public boolean isEncodingAlpha() {
/*  84 */     return false;
/*     */   }
/*     */   
/*     */   public void setEncodingAlpha(boolean encodingAlpha) {}
/*     */   
/*     */   public byte[] encode(BufferedImage bufferedImage) throws IOException {
/* 108 */     ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
/* 109 */     encode(bufferedImage, outputStream);
/* 110 */     return outputStream.toByteArray();
/*     */   }
/*     */   
/*     */   public void encode(BufferedImage bufferedImage, OutputStream outputStream) throws IOException {
/* 122 */     if (bufferedImage == null)
/* 123 */       throw new IllegalArgumentException("Null 'image' argument."); 
/* 125 */     if (outputStream == null)
/* 126 */       throw new IllegalArgumentException("Null 'outputStream' argument."); 
/* 128 */     ImageIO.write(bufferedImage, "png", outputStream);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\encoders\SunPNGEncoderAdapter.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */