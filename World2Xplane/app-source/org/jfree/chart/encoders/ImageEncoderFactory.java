/*     */ package org.jfree.chart.encoders;
/*     */ 
/*     */ import java.util.Hashtable;
/*     */ 
/*     */ public class ImageEncoderFactory {
/*  57 */   private static Hashtable encoders = null;
/*     */   
/*     */   static {
/*  60 */     init();
/*     */   }
/*     */   
/*     */   private static void init() {
/*  68 */     encoders = new Hashtable();
/*  69 */     encoders.put("jpeg", "org.jfree.chart.encoders.SunJPEGEncoderAdapter");
/*     */     try {
/*  72 */       Class.forName("javax.imageio.ImageIO");
/*  74 */       Class.forName("org.jfree.chart.encoders.SunPNGEncoderAdapter");
/*  75 */       encoders.put("png", "org.jfree.chart.encoders.SunPNGEncoderAdapter");
/*  77 */       encoders.put("jpeg", "org.jfree.chart.encoders.SunJPEGEncoderAdapter");
/*  80 */     } catch (ClassNotFoundException e) {
/*  81 */       encoders.put("png", "org.jfree.chart.encoders.KeypointPNGEncoderAdapter");
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void setImageEncoder(String format, String imageEncoderClassName) {
/*  94 */     encoders.put(format, imageEncoderClassName);
/*     */   }
/*     */   
/*     */   public static ImageEncoder newInstance(String format) {
/* 105 */     ImageEncoder imageEncoder = null;
/* 106 */     String className = (String)encoders.get(format);
/* 107 */     if (className == null)
/* 108 */       throw new IllegalArgumentException("Unsupported image format - " + format); 
/*     */     try {
/* 112 */       Class imageEncoderClass = Class.forName(className);
/* 113 */       imageEncoder = (ImageEncoder)imageEncoderClass.newInstance();
/* 115 */     } catch (Exception e) {
/* 116 */       throw new IllegalArgumentException(e.toString());
/*     */     } 
/* 118 */     return imageEncoder;
/*     */   }
/*     */   
/*     */   public static ImageEncoder newInstance(String format, float quality) {
/* 130 */     ImageEncoder imageEncoder = newInstance(format);
/* 131 */     imageEncoder.setQuality(quality);
/* 132 */     return imageEncoder;
/*     */   }
/*     */   
/*     */   public static ImageEncoder newInstance(String format, boolean encodingAlpha) {
/* 145 */     ImageEncoder imageEncoder = newInstance(format);
/* 146 */     imageEncoder.setEncodingAlpha(encodingAlpha);
/* 147 */     return imageEncoder;
/*     */   }
/*     */   
/*     */   public static ImageEncoder newInstance(String format, float quality, boolean encodingAlpha) {
/* 161 */     ImageEncoder imageEncoder = newInstance(format);
/* 162 */     imageEncoder.setQuality(quality);
/* 163 */     imageEncoder.setEncodingAlpha(encodingAlpha);
/* 164 */     return imageEncoder;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\encoders\ImageEncoderFactory.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */