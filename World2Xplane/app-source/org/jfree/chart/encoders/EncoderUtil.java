/*     */ package org.jfree.chart.encoders;
/*     */ 
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ 
/*     */ public class EncoderUtil {
/*     */   public static byte[] encode(BufferedImage image, String format) throws IOException {
/*  68 */     ImageEncoder imageEncoder = ImageEncoderFactory.newInstance(format);
/*  69 */     return imageEncoder.encode(image);
/*     */   }
/*     */   
/*     */   public static byte[] encode(BufferedImage image, String format, boolean encodeAlpha) throws IOException {
/*  84 */     ImageEncoder imageEncoder = ImageEncoderFactory.newInstance(format, encodeAlpha);
/*  86 */     return imageEncoder.encode(image);
/*     */   }
/*     */   
/*     */   public static byte[] encode(BufferedImage image, String format, float quality) throws IOException {
/* 101 */     ImageEncoder imageEncoder = ImageEncoderFactory.newInstance(format, quality);
/* 103 */     return imageEncoder.encode(image);
/*     */   }
/*     */   
/*     */   public static byte[] encode(BufferedImage image, String format, float quality, boolean encodeAlpha) throws IOException {
/* 121 */     ImageEncoder imageEncoder = ImageEncoderFactory.newInstance(format, quality, encodeAlpha);
/* 123 */     return imageEncoder.encode(image);
/*     */   }
/*     */   
/*     */   public static void writeBufferedImage(BufferedImage image, String format, OutputStream outputStream) throws IOException {
/* 136 */     ImageEncoder imageEncoder = ImageEncoderFactory.newInstance(format);
/* 137 */     imageEncoder.encode(image, outputStream);
/*     */   }
/*     */   
/*     */   public static void writeBufferedImage(BufferedImage image, String format, OutputStream outputStream, float quality) throws IOException {
/* 152 */     ImageEncoder imageEncoder = ImageEncoderFactory.newInstance(format, quality);
/* 154 */     imageEncoder.encode(image, outputStream);
/*     */   }
/*     */   
/*     */   public static void writeBufferedImage(BufferedImage image, String format, OutputStream outputStream, boolean encodeAlpha) throws IOException {
/* 169 */     ImageEncoder imageEncoder = ImageEncoderFactory.newInstance(format, encodeAlpha);
/* 171 */     imageEncoder.encode(image, outputStream);
/*     */   }
/*     */   
/*     */   public static void writeBufferedImage(BufferedImage image, String format, OutputStream outputStream, float quality, boolean encodeAlpha) throws IOException {
/* 189 */     ImageEncoder imageEncoder = ImageEncoderFactory.newInstance(format, quality, encodeAlpha);
/* 191 */     imageEncoder.encode(image, outputStream);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\encoders\EncoderUtil.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */