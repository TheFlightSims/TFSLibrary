/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.IndexColorModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.LookupTableJAI;
/*     */ import javax.media.jai.PixelAccessor;
/*     */ import javax.media.jai.PointOpImage;
/*     */ import javax.media.jai.ROI;
/*     */ import javax.media.jai.RasterFactory;
/*     */ import javax.media.jai.UnpackedImageData;
/*     */ 
/*     */ abstract class ColorQuantizerOpImage extends PointOpImage {
/*     */   private static final int NBANDS = 3;
/*     */   
/*     */   private static final int NGRAYS = 256;
/*     */   
/*     */   protected PixelAccessor srcPA;
/*     */   
/*     */   protected int srcSampleType;
/*     */   
/*     */   protected boolean isInitialized = false;
/*     */   
/*     */   protected PixelAccessor destPA;
/*     */   
/*     */   protected LookupTableJAI colorMap;
/*     */   
/*     */   protected int maxColorNum;
/*     */   
/*     */   protected int xPeriod;
/*     */   
/*     */   protected int yPeriod;
/*     */   
/*     */   protected ROI roi;
/*     */   
/*     */   private int numBandsSource;
/*     */   
/*     */   protected boolean checkForSkippedTiles = false;
/*     */   
/*     */   static final int startPosition(int pos, int start, int period) {
/* 103 */     int t = (pos - start) % period;
/* 104 */     return (t == 0) ? pos : (pos + period - t);
/*     */   }
/*     */   
/*     */   private static ImageLayout layoutHelper(ImageLayout layout, RenderedImage source) {
/* 113 */     ImageLayout il = (layout == null) ? new ImageLayout() : (ImageLayout)layout.clone();
/* 117 */     il.setMinX(source.getMinX());
/* 118 */     il.setMinY(source.getMinY());
/* 119 */     il.setWidth(source.getWidth());
/* 120 */     il.setHeight(source.getHeight());
/* 123 */     SampleModel sm = il.getSampleModel(source);
/* 126 */     if (sm.getNumBands() != 1) {
/* 127 */       sm = RasterFactory.createComponentSampleModel(sm, sm.getTransferType(), sm.getWidth(), sm.getHeight(), 1);
/* 133 */       il.setSampleModel(sm);
/*     */     } 
/* 136 */     il.setColorModel(null);
/* 138 */     return il;
/*     */   }
/*     */   
/*     */   public ColorQuantizerOpImage(RenderedImage source, Map config, ImageLayout layout, int maxColorNum, ROI roi, int xPeriod, int yPeriod) {
/* 161 */     super(source, layoutHelper(layout, source), config, true);
/* 164 */     SampleModel srcSampleModel = source.getSampleModel();
/* 167 */     this.numBandsSource = srcSampleModel.getNumBands();
/* 169 */     this.maxColorNum = maxColorNum;
/* 170 */     this.xPeriod = xPeriod;
/* 171 */     this.yPeriod = yPeriod;
/* 172 */     this.roi = roi;
/* 173 */     this.checkForSkippedTiles = (xPeriod > this.tileWidth || yPeriod > this.tileHeight);
/*     */   }
/*     */   
/*     */   protected void computeRect(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 180 */     if (this.colorMap == null)
/* 181 */       train(); 
/* 183 */     if (!this.isInitialized) {
/* 184 */       this.srcPA = new PixelAccessor((RenderedImage)getSourceImage(0));
/* 185 */       this.srcSampleType = (this.srcPA.sampleType == -1) ? 0 : this.srcPA.sampleType;
/* 187 */       this.isInitialized = true;
/*     */     } 
/* 190 */     UnpackedImageData uid = this.srcPA.getPixels(sources[0], destRect, this.srcSampleType, false);
/* 193 */     Rectangle rect = uid.rect;
/* 194 */     byte[][] data = uid.getByteData();
/* 195 */     int srcLineStride = uid.lineStride;
/* 196 */     int srcPixelStride = uid.pixelStride;
/* 197 */     byte[] rBand = data[0];
/* 198 */     byte[] gBand = data[1];
/* 199 */     byte[] bBand = data[2];
/* 201 */     int lastLine = rect.height * srcLineStride + uid.bandOffsets[0];
/* 203 */     if (this.destPA == null)
/* 204 */       this.destPA = new PixelAccessor((RenderedImage)this); 
/* 206 */     UnpackedImageData destUid = this.destPA.getPixels(dest, destRect, this.sampleModel.getDataType(), false);
/* 210 */     int destLineOffset = destUid.bandOffsets[0];
/* 211 */     int destLineStride = destUid.lineStride;
/* 212 */     byte[] d = destUid.getByteData(0);
/* 214 */     int[] currentPixel = new int[3];
/*     */     int lo;
/* 215 */     for (lo = uid.bandOffsets[0]; lo < lastLine; lo += srcLineStride) {
/* 216 */       int lastPixel = lo + rect.width * srcPixelStride - uid.bandOffsets[0];
/* 218 */       int dstPixelOffset = destLineOffset;
/*     */       int po;
/* 219 */       for (po = lo - uid.bandOffsets[0]; po < lastPixel; 
/* 220 */         po += srcPixelStride) {
/* 221 */         d[dstPixelOffset] = findNearestEntry(rBand[po + uid.bandOffsets[0]] & 0xFF, gBand[po + uid.bandOffsets[1]] & 0xFF, bBand[po + uid.bandOffsets[2]] & 0xFF);
/* 226 */         dstPixelOffset += destUid.pixelStride;
/*     */       } 
/* 228 */       destLineOffset += destLineStride;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object getProperty(String name) {
/* 234 */     int numBands = this.sampleModel.getNumBands();
/* 236 */     if (name.equals("JAI.LookupTable") || name.equals("LUT")) {
/* 238 */       if (this.colorMap == null)
/* 239 */         train(); 
/* 240 */       return this.colorMap;
/*     */     } 
/* 243 */     return super.getProperty(name);
/*     */   }
/*     */   
/*     */   protected abstract void train();
/*     */   
/*     */   public ColorModel getColorModel() {
/* 249 */     if (this.colorMap == null)
/* 250 */       train(); 
/* 251 */     if (this.colorModel == null)
/* 252 */       this.colorModel = new IndexColorModel(8, (this.colorMap.getByteData(0)).length, this.colorMap.getByteData(0), this.colorMap.getByteData(1), this.colorMap.getByteData(2)); 
/* 257 */     return this.colorModel;
/*     */   }
/*     */   
/*     */   protected byte findNearestEntry(int r, int g, int b) {
/* 261 */     byte[] red = this.colorMap.getByteData(0);
/* 262 */     byte[] green = this.colorMap.getByteData(1);
/* 263 */     byte[] blue = this.colorMap.getByteData(2);
/* 264 */     int index = 0;
/* 266 */     int dr = r - (red[0] & 0xFF);
/* 267 */     int dg = g - (green[0] & 0xFF);
/* 268 */     int db = b - (blue[0] & 0xFF);
/* 269 */     int minDistance = dr * dr + dg * dg + db * db;
/* 273 */     for (int i = 1; i < red.length; i++) {
/* 274 */       dr = r - (red[i] & 0xFF);
/* 275 */       int distance = dr * dr;
/* 276 */       if (distance <= minDistance) {
/* 278 */         dg = g - (green[i] & 0xFF);
/* 279 */         distance += dg * dg;
/* 281 */         if (distance <= minDistance) {
/* 283 */           db = b - (blue[i] & 0xFF);
/* 284 */           distance += db * db;
/* 285 */           if (distance < minDistance) {
/* 286 */             minDistance = distance;
/* 287 */             index = i;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 290 */     return (byte)index;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\ColorQuantizerOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */