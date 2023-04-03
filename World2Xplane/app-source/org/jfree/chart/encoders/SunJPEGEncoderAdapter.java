/*     */ package org.jfree.chart.encoders;
/*     */ 
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import javax.imageio.ImageIO;
/*     */ 
/*     */ public class SunJPEGEncoderAdapter implements ImageEncoder {
/*  64 */   private float quality = 0.75F;
/*     */   
/*     */   public float getQuality() {
/*  78 */     return this.quality;
/*     */   }
/*     */   
/*     */   public void setQuality(float quality) {
/*  87 */     this.quality = quality;
/*     */   }
/*     */   
/*     */   public boolean isEncodingAlpha() {
/*  96 */     return false;
/*     */   }
/*     */   
/*     */   public void setEncodingAlpha(boolean encodingAlpha) {}
/*     */   
/*     */   public byte[] encode(BufferedImage bufferedImage) throws IOException {
/* 120 */     ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
/* 121 */     encode(bufferedImage, outputStream);
/* 122 */     return outputStream.toByteArray();
/*     */   }
/*     */   
/*     */   public void encode(BufferedImage bufferedImage, OutputStream outputStream) throws IOException {
/* 134 */     if (bufferedImage == null)
/* 135 */       throw new IllegalArgumentException("Null 'image' argument."); 
/* 137 */     if (outputStream == null)
/* 138 */       throw new IllegalArgumentException("Null 'outputStream' argument."); 
/* 140 */     ImageIO.write(bufferedImage, "jpeg", outputStream);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\encoders\SunJPEGEncoderAdapter.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */