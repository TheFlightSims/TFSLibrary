/*     */ package com.sun.media.jai.util;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.DataBuffer;
/*     */ import java.awt.image.MultiPixelPackedSampleModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ 
/*     */ public final class JDKWorkarounds {
/*     */   private static boolean setRectBilevel(WritableRaster dstRaster, Raster srcRaster, int dx, int dy) {
/*  33 */     int width = srcRaster.getWidth();
/*  34 */     int height = srcRaster.getHeight();
/*  35 */     int srcOffX = srcRaster.getMinX();
/*  36 */     int srcOffY = srcRaster.getMinY();
/*  37 */     int dstOffX = dx + srcOffX;
/*  38 */     int dstOffY = dy + srcOffY;
/*  40 */     int dminX = dstRaster.getMinX();
/*  41 */     int dminY = dstRaster.getMinY();
/*  42 */     int dwidth = dstRaster.getWidth();
/*  43 */     int dheight = dstRaster.getHeight();
/*  46 */     if (dstOffX + width > dminX + dwidth)
/*  47 */       width = dminX + dwidth - dstOffX; 
/*  49 */     if (dstOffY + height > dminY + dheight)
/*  50 */       height = dminY + dheight - dstOffY; 
/*  68 */     Rectangle rect = new Rectangle(dstOffX, dstOffY, width, height);
/*  69 */     byte[] binaryData = ImageUtil.getPackedBinaryData(srcRaster, rect);
/*  70 */     ImageUtil.setPackedBinaryData(binaryData, dstRaster, rect);
/* 245 */     return true;
/*     */   }
/*     */   
/*     */   public static void setRect(WritableRaster dstRaster, Raster srcRaster) {
/* 252 */     setRect(dstRaster, srcRaster, 0, 0);
/*     */   }
/*     */   
/*     */   public static void setRect(WritableRaster dstRaster, Raster srcRaster, int dx, int dy) {
/*     */     int iData[], startY;
/*     */     float[] fData;
/*     */     int i;
/*     */     double[] dData;
/*     */     int j;
/* 258 */     SampleModel srcSampleModel = srcRaster.getSampleModel();
/* 259 */     SampleModel dstSampleModel = dstRaster.getSampleModel();
/* 260 */     if (srcSampleModel instanceof MultiPixelPackedSampleModel && dstSampleModel instanceof MultiPixelPackedSampleModel) {
/* 262 */       MultiPixelPackedSampleModel srcMPPSM = (MultiPixelPackedSampleModel)srcSampleModel;
/* 264 */       MultiPixelPackedSampleModel dstMPPSM = (MultiPixelPackedSampleModel)dstSampleModel;
/* 267 */       DataBuffer srcDB = srcRaster.getDataBuffer();
/* 268 */       DataBuffer dstDB = srcRaster.getDataBuffer();
/* 270 */       if (srcDB instanceof java.awt.image.DataBufferByte && dstDB instanceof java.awt.image.DataBufferByte && srcMPPSM.getPixelBitStride() == 1 && dstMPPSM.getPixelBitStride() == 1)
/* 274 */         if (setRectBilevel(dstRaster, srcRaster, dx, dy))
/*     */           return;  
/*     */     } 
/* 282 */     int dataType = dstRaster.getSampleModel().getDataType();
/* 283 */     if (dataType != 4 && dataType != 5) {
/* 285 */       dstRaster.setRect(dx, dy, srcRaster);
/*     */       return;
/*     */     } 
/* 289 */     int width = srcRaster.getWidth();
/* 290 */     int height = srcRaster.getHeight();
/* 291 */     int srcOffX = srcRaster.getMinX();
/* 292 */     int srcOffY = srcRaster.getMinY();
/* 293 */     int dstOffX = dx + srcOffX;
/* 294 */     int dstOffY = dy + srcOffY;
/* 296 */     int dminX = dstRaster.getMinX();
/* 297 */     int dminY = dstRaster.getMinY();
/* 298 */     int dwidth = dstRaster.getWidth();
/* 299 */     int dheight = dstRaster.getHeight();
/* 302 */     if (dstOffX + width > dminX + dwidth)
/* 303 */       width = dminX + dwidth - dstOffX; 
/* 305 */     if (dstOffY + height > dminY + dheight)
/* 306 */       height = dminY + dheight - dstOffY; 
/* 309 */     switch (srcRaster.getSampleModel().getDataType()) {
/*     */       case 0:
/*     */       case 1:
/*     */       case 2:
/*     */       case 3:
/* 314 */         iData = null;
/* 315 */         for (startY = 0; startY < height; startY++) {
/* 317 */           iData = srcRaster.getPixels(srcOffX, srcOffY + startY, width, 1, iData);
/* 320 */           dstRaster.setPixels(dstOffX, dstOffY + startY, width, 1, iData);
/*     */         } 
/*     */         break;
/*     */       case 4:
/* 325 */         fData = null;
/* 326 */         for (i = 0; i < height; i++) {
/* 327 */           fData = srcRaster.getPixels(srcOffX, srcOffY + i, width, 1, fData);
/* 330 */           dstRaster.setPixels(dstOffX, dstOffY + i, width, 1, fData);
/*     */         } 
/*     */         break;
/*     */       case 5:
/* 335 */         dData = null;
/* 336 */         for (j = 0; j < height; j++) {
/* 338 */           dData = srcRaster.getPixels(srcOffX, srcOffY + j, width, 1, dData);
/* 341 */           dstRaster.setPixels(dstOffX, dstOffY + j, width, 1, dData);
/*     */         } 
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean areCompatibleDataModels(SampleModel sm, ColorModel cm) {
/* 362 */     if (sm == null || cm == null)
/* 363 */       throw new IllegalArgumentException(JaiI18N.getString("JDKWorkarounds0")); 
/* 370 */     if (!cm.isCompatibleSampleModel(sm))
/* 371 */       return false; 
/* 379 */     if (cm instanceof java.awt.image.ComponentColorModel) {
/* 381 */       int numBands = sm.getNumBands();
/* 382 */       if (numBands != cm.getNumComponents())
/* 383 */         return false; 
/* 392 */       for (int b = 0; b < numBands; b++) {
/* 393 */         if (sm.getSampleSize(b) < cm.getComponentSize(b))
/* 394 */           return false; 
/*     */       } 
/*     */     } 
/* 400 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\ja\\util\JDKWorkarounds.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */