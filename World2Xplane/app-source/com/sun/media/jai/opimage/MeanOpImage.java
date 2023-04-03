/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import java.awt.Image;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.util.LinkedList;
/*     */ import java.util.ListIterator;
/*     */ import javax.media.jai.PixelAccessor;
/*     */ import javax.media.jai.ROI;
/*     */ import javax.media.jai.StatisticsOpImage;
/*     */ import javax.media.jai.UnpackedImageData;
/*     */ 
/*     */ public class MeanOpImage extends StatisticsOpImage {
/*     */   private boolean isInitialized = false;
/*     */   
/*     */   private double[] totalPixelValue;
/*     */   
/*     */   private int totalPixelCount;
/*     */   
/*     */   private PixelAccessor srcPA;
/*     */   
/*     */   private int srcSampleType;
/*     */   
/*     */   private final boolean tileIntersectsROI(int tileX, int tileY) {
/*  57 */     if (this.roi == null)
/*  58 */       return true; 
/*  60 */     return this.roi.intersects(tileXToX(tileX), tileYToY(tileY), this.tileWidth, this.tileHeight);
/*     */   }
/*     */   
/*     */   public MeanOpImage(RenderedImage source, ROI roi, int xStart, int yStart, int xPeriod, int yPeriod) {
/*  76 */     super(source, roi, xStart, yStart, xPeriod, yPeriod);
/*     */   }
/*     */   
/*     */   protected String[] getStatisticsNames() {
/*  80 */     return new String[] { "mean" };
/*     */   }
/*     */   
/*     */   protected Object createStatistics(String name) {
/*     */     Object stats;
/*  86 */     if (name.equalsIgnoreCase("mean")) {
/*  87 */       stats = new double[this.sampleModel.getNumBands()];
/*     */     } else {
/*  89 */       stats = Image.UndefinedProperty;
/*     */     } 
/*  91 */     return stats;
/*     */   }
/*     */   
/*     */   private final int startPosition(int pos, int start, int period) {
/*  95 */     int t = (pos - start) % period;
/*  96 */     if (t == 0)
/*  97 */       return pos; 
/*  99 */     return pos + period - t;
/*     */   }
/*     */   
/*     */   protected void accumulateStatistics(String name, Raster source, Object stats) {
/*     */     LinkedList rectList;
/* 106 */     if (!this.isInitialized) {
/* 107 */       this.srcPA = new PixelAccessor((RenderedImage)getSourceImage(0));
/* 108 */       this.srcSampleType = (this.srcPA.sampleType == -1) ? 0 : this.srcPA.sampleType;
/* 111 */       this.totalPixelValue = new double[this.srcPA.numBands];
/* 112 */       this.totalPixelCount = 0;
/* 113 */       this.isInitialized = true;
/*     */     } 
/* 116 */     Rectangle srcBounds = getSourceImage(0).getBounds().intersection(source.getBounds());
/* 120 */     if (this.roi == null) {
/* 121 */       rectList = new LinkedList();
/* 122 */       rectList.addLast(srcBounds);
/*     */     } else {
/* 124 */       rectList = this.roi.getAsRectangleList(srcBounds.x, srcBounds.y, srcBounds.width, srcBounds.height);
/* 128 */       if (rectList == null)
/*     */         return; 
/*     */     } 
/* 132 */     ListIterator iterator = rectList.listIterator(0);
/* 134 */     while (iterator.hasNext()) {
/* 135 */       Rectangle rect = srcBounds.intersection(iterator.next());
/* 136 */       int tx = rect.x;
/* 137 */       int ty = rect.y;
/* 140 */       rect.x = startPosition(tx, this.xStart, this.xPeriod);
/* 141 */       rect.y = startPosition(ty, this.yStart, this.yPeriod);
/* 142 */       rect.width = tx + rect.width - rect.x;
/* 143 */       rect.height = ty + rect.height - rect.y;
/* 145 */       if (rect.isEmpty())
/*     */         continue; 
/* 149 */       UnpackedImageData uid = this.srcPA.getPixels(source, rect, this.srcSampleType, false);
/* 152 */       switch (uid.type) {
/*     */         case 0:
/* 154 */           accumulateStatisticsByte(uid);
/*     */         case 1:
/* 157 */           accumulateStatisticsUShort(uid);
/*     */         case 2:
/* 160 */           accumulateStatisticsShort(uid);
/*     */         case 3:
/* 163 */           accumulateStatisticsInt(uid);
/*     */         case 4:
/* 166 */           accumulateStatisticsFloat(uid);
/*     */         case 5:
/* 169 */           accumulateStatisticsDouble(uid);
/*     */       } 
/*     */     } 
/* 174 */     if (name.equalsIgnoreCase("mean")) {
/* 177 */       double[] mean = (double[])stats;
/* 178 */       if (this.totalPixelCount != 0)
/* 179 */         for (int i = 0; i < this.srcPA.numBands; i++)
/* 180 */           mean[i] = this.totalPixelValue[i] / this.totalPixelCount;  
/*     */     } 
/*     */   }
/*     */   
/*     */   private void accumulateStatisticsByte(UnpackedImageData uid) {
/* 188 */     Rectangle rect = uid.rect;
/* 189 */     byte[][] data = uid.getByteData();
/* 190 */     int lineStride = uid.lineStride;
/* 191 */     int pixelStride = uid.pixelStride;
/* 193 */     int lineInc = lineStride * this.yPeriod;
/* 194 */     int pixelInc = pixelStride * this.xPeriod;
/* 196 */     for (int b = 0; b < this.srcPA.numBands; b++) {
/* 197 */       byte[] d = data[b];
/* 198 */       int lastLine = uid.bandOffsets[b] + rect.height * lineStride;
/*     */       int lo;
/* 200 */       for (lo = uid.bandOffsets[b]; lo < lastLine; lo += lineInc) {
/* 201 */         int lastPixel = lo + rect.width * pixelStride;
/*     */         int po;
/* 203 */         for (po = lo; po < lastPixel; po += pixelInc)
/* 204 */           this.totalPixelValue[b] = this.totalPixelValue[b] + (d[po] & 0xFF); 
/*     */       } 
/*     */     } 
/* 208 */     this.totalPixelCount += (int)Math.ceil(rect.height / this.yPeriod) * (int)Math.ceil(rect.width / this.xPeriod);
/*     */   }
/*     */   
/*     */   private void accumulateStatisticsUShort(UnpackedImageData uid) {
/* 213 */     Rectangle rect = uid.rect;
/* 214 */     short[][] data = uid.getShortData();
/* 215 */     int lineStride = uid.lineStride;
/* 216 */     int pixelStride = uid.pixelStride;
/* 218 */     int lineInc = lineStride * this.yPeriod;
/* 219 */     int pixelInc = pixelStride * this.xPeriod;
/* 221 */     for (int b = 0; b < this.srcPA.numBands; b++) {
/* 222 */       short[] d = data[b];
/* 223 */       int lastLine = uid.bandOffsets[b] + rect.height * lineStride;
/*     */       int lo;
/* 225 */       for (lo = uid.bandOffsets[b]; lo < lastLine; lo += lineInc) {
/* 226 */         int lastPixel = lo + rect.width * pixelStride;
/*     */         int po;
/* 228 */         for (po = lo; po < lastPixel; po += pixelInc)
/* 229 */           this.totalPixelValue[b] = this.totalPixelValue[b] + (d[po] & 0xFFFF); 
/*     */       } 
/*     */     } 
/* 233 */     this.totalPixelCount += (int)Math.ceil(rect.height / this.yPeriod) * (int)Math.ceil(rect.width / this.xPeriod);
/*     */   }
/*     */   
/*     */   private void accumulateStatisticsShort(UnpackedImageData uid) {
/* 238 */     Rectangle rect = uid.rect;
/* 239 */     short[][] data = uid.getShortData();
/* 240 */     int lineStride = uid.lineStride;
/* 241 */     int pixelStride = uid.pixelStride;
/* 243 */     int lineInc = lineStride * this.yPeriod;
/* 244 */     int pixelInc = pixelStride * this.xPeriod;
/* 246 */     for (int b = 0; b < this.srcPA.numBands; b++) {
/* 247 */       short[] d = data[b];
/* 248 */       int lastLine = uid.bandOffsets[b] + rect.height * lineStride;
/*     */       int lo;
/* 250 */       for (lo = uid.bandOffsets[b]; lo < lastLine; lo += lineInc) {
/* 251 */         int lastPixel = lo + rect.width * pixelStride;
/*     */         int po;
/* 253 */         for (po = lo; po < lastPixel; po += pixelInc)
/* 254 */           this.totalPixelValue[b] = this.totalPixelValue[b] + d[po]; 
/*     */       } 
/*     */     } 
/* 258 */     this.totalPixelCount += (int)Math.ceil(rect.height / this.yPeriod) * (int)Math.ceil(rect.width / this.xPeriod);
/*     */   }
/*     */   
/*     */   private void accumulateStatisticsInt(UnpackedImageData uid) {
/* 263 */     Rectangle rect = uid.rect;
/* 264 */     int[][] data = uid.getIntData();
/* 265 */     int lineStride = uid.lineStride;
/* 266 */     int pixelStride = uid.pixelStride;
/* 268 */     int lineInc = lineStride * this.yPeriod;
/* 269 */     int pixelInc = pixelStride * this.xPeriod;
/* 271 */     for (int b = 0; b < this.srcPA.numBands; b++) {
/* 272 */       int[] d = data[b];
/* 273 */       int lastLine = uid.bandOffsets[b] + rect.height * lineStride;
/*     */       int lo;
/* 275 */       for (lo = uid.bandOffsets[b]; lo < lastLine; lo += lineInc) {
/* 276 */         int lastPixel = lo + rect.width * pixelStride;
/*     */         int po;
/* 278 */         for (po = lo; po < lastPixel; po += pixelInc)
/* 279 */           this.totalPixelValue[b] = this.totalPixelValue[b] + d[po]; 
/*     */       } 
/*     */     } 
/* 283 */     this.totalPixelCount += (int)Math.ceil(rect.height / this.yPeriod) * (int)Math.ceil(rect.width / this.xPeriod);
/*     */   }
/*     */   
/*     */   private void accumulateStatisticsFloat(UnpackedImageData uid) {
/* 288 */     Rectangle rect = uid.rect;
/* 289 */     float[][] data = uid.getFloatData();
/* 290 */     int lineStride = uid.lineStride;
/* 291 */     int pixelStride = uid.pixelStride;
/* 293 */     int lineInc = lineStride * this.yPeriod;
/* 294 */     int pixelInc = pixelStride * this.xPeriod;
/* 296 */     for (int b = 0; b < this.srcPA.numBands; b++) {
/* 297 */       float[] d = data[b];
/* 298 */       int lastLine = uid.bandOffsets[b] + rect.height * lineStride;
/*     */       int lo;
/* 300 */       for (lo = uid.bandOffsets[b]; lo < lastLine; lo += lineInc) {
/* 301 */         int lastPixel = lo + rect.width * pixelStride;
/*     */         int po;
/* 303 */         for (po = lo; po < lastPixel; po += pixelInc)
/* 304 */           this.totalPixelValue[b] = this.totalPixelValue[b] + d[po]; 
/*     */       } 
/*     */     } 
/* 308 */     this.totalPixelCount += (int)Math.ceil(rect.height / this.yPeriod) * (int)Math.ceil(rect.width / this.xPeriod);
/*     */   }
/*     */   
/*     */   private void accumulateStatisticsDouble(UnpackedImageData uid) {
/* 313 */     Rectangle rect = uid.rect;
/* 314 */     double[][] data = uid.getDoubleData();
/* 315 */     int lineStride = uid.lineStride;
/* 316 */     int pixelStride = uid.pixelStride;
/* 318 */     int lineInc = lineStride * this.yPeriod;
/* 319 */     int pixelInc = pixelStride * this.xPeriod;
/* 321 */     for (int b = 0; b < this.srcPA.numBands; b++) {
/* 322 */       double[] d = data[b];
/* 323 */       int lastLine = uid.bandOffsets[b] + rect.height * lineStride;
/*     */       int lo;
/* 325 */       for (lo = uid.bandOffsets[b]; lo < lastLine; lo += lineInc) {
/* 326 */         int lastPixel = lo + rect.width * pixelStride;
/*     */         int po;
/* 328 */         for (po = lo; po < lastPixel; po += pixelInc)
/* 329 */           this.totalPixelValue[b] = this.totalPixelValue[b] + d[po]; 
/*     */       } 
/*     */     } 
/* 333 */     this.totalPixelCount += (int)Math.ceil(rect.height / this.yPeriod) * (int)Math.ceil(rect.width / this.xPeriod);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\MeanOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */