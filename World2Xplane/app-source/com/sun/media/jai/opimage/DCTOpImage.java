/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import com.sun.media.jai.util.JDKWorkarounds;
/*     */ import com.sun.media.jai.util.MathJAI;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.RasterAccessor;
/*     */ import javax.media.jai.RasterFactory;
/*     */ import javax.media.jai.RasterFormatTag;
/*     */ import javax.media.jai.UntiledOpImage;
/*     */ 
/*     */ public class DCTOpImage extends UntiledOpImage {
/*     */   private FCT fct;
/*     */   
/*     */   private static ImageLayout layoutHelper(ImageLayout layout, RenderedImage source) {
/*  66 */     ImageLayout il = (layout == null) ? new ImageLayout() : (ImageLayout)layout.clone();
/*  70 */     il.setMinX(source.getMinX());
/*  71 */     il.setMinY(source.getMinY());
/*  76 */     boolean createNewSampleModel = false;
/*  77 */     int w = il.getWidth(source);
/*  78 */     if (w > 1) {
/*  79 */       int newWidth = MathJAI.nextPositivePowerOf2(w);
/*  80 */       if (newWidth != w) {
/*  81 */         il.setWidth(w = newWidth);
/*  82 */         createNewSampleModel = true;
/*     */       } 
/*     */     } 
/*  85 */     int h = il.getHeight(source);
/*  86 */     if (h > 1) {
/*  87 */       int newHeight = MathJAI.nextPositivePowerOf2(h);
/*  88 */       if (newHeight != h) {
/*  89 */         il.setHeight(h = newHeight);
/*  90 */         createNewSampleModel = true;
/*     */       } 
/*     */     } 
/*  95 */     SampleModel sm = il.getSampleModel(source);
/*  96 */     int dataType = sm.getTransferType();
/*  97 */     if (dataType != 4 && dataType != 5) {
/*  99 */       dataType = 4;
/* 100 */       createNewSampleModel = true;
/*     */     } 
/* 104 */     if (createNewSampleModel) {
/* 105 */       sm = RasterFactory.createComponentSampleModel(sm, dataType, w, h, sm.getNumBands());
/* 107 */       il.setSampleModel(sm);
/* 110 */       ColorModel cm = il.getColorModel(null);
/* 111 */       if (cm != null && !JDKWorkarounds.areCompatibleDataModels(sm, cm))
/* 114 */         il.unsetValid(512); 
/*     */     } 
/* 118 */     return il;
/*     */   }
/*     */   
/*     */   public DCTOpImage(RenderedImage source, Map config, ImageLayout layout, FCT fct) {
/* 138 */     super(source, config, layoutHelper(layout, source));
/* 141 */     this.fct = fct;
/*     */   }
/*     */   
/*     */   public Point2D mapDestPoint(Point2D destPt) {
/* 158 */     if (destPt == null)
/* 159 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 162 */     return null;
/*     */   }
/*     */   
/*     */   public Point2D mapSourcePoint(Point2D sourcePt) {
/* 176 */     if (sourcePt == null)
/* 177 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 180 */     return null;
/*     */   }
/*     */   
/*     */   protected void computeImage(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 193 */     Raster source = sources[0];
/* 196 */     if (destRect.width == 1 && destRect.height == 1) {
/* 197 */       double[] pixel = source.getPixel(destRect.x, destRect.y, (double[])null);
/* 199 */       dest.setPixel(destRect.x, destRect.y, pixel);
/*     */       return;
/*     */     } 
/* 204 */     this.fct.setLength((destRect.width > 1) ? getWidth() : getHeight());
/* 207 */     int srcWidth = source.getWidth();
/* 208 */     int srcHeight = source.getHeight();
/* 209 */     int srcX = source.getMinX();
/* 210 */     int srcY = source.getMinY();
/* 213 */     RasterFormatTag[] formatTags = getFormatTags();
/* 215 */     RasterAccessor srcAccessor = new RasterAccessor(source, new Rectangle(srcX, srcY, srcWidth, srcHeight), formatTags[0], getSourceImage(0).getColorModel());
/* 221 */     RasterAccessor dstAccessor = new RasterAccessor(dest, destRect, formatTags[1], getColorModel());
/* 226 */     int srcDataType = srcAccessor.getDataType();
/* 227 */     int dstDataType = dstAccessor.getDataType();
/* 230 */     int srcPixelStride = srcAccessor.getPixelStride();
/* 231 */     int srcScanlineStride = srcAccessor.getScanlineStride();
/* 232 */     int dstPixelStride = dstAccessor.getPixelStride();
/* 233 */     int dstScanlineStride = dstAccessor.getScanlineStride();
/* 236 */     int numBands = this.sampleModel.getNumBands();
/* 237 */     for (int band = 0; band < numBands; band++) {
/* 239 */       Object srcData = srcAccessor.getDataArray(band);
/* 240 */       Object dstData = dstAccessor.getDataArray(band);
/* 242 */       if (destRect.width > 1) {
/* 244 */         this.fct.setLength(getWidth());
/* 247 */         int srcOffset = srcAccessor.getBandOffset(band);
/* 248 */         int dstOffset = dstAccessor.getBandOffset(band);
/* 251 */         for (int row = 0; row < srcHeight; row++) {
/* 253 */           this.fct.setData(srcDataType, srcData, srcOffset, srcPixelStride, srcWidth);
/* 258 */           this.fct.transform();
/* 261 */           this.fct.getData(dstDataType, dstData, dstOffset, dstPixelStride);
/* 265 */           srcOffset += srcScanlineStride;
/* 266 */           dstOffset += dstScanlineStride;
/*     */         } 
/*     */       } 
/* 270 */       if (destRect.width == 1) {
/* 272 */         int srcOffset = srcAccessor.getBandOffset(band);
/* 273 */         int dstOffset = dstAccessor.getBandOffset(band);
/* 276 */         this.fct.setData(srcDataType, srcData, srcOffset, srcScanlineStride, srcHeight);
/* 281 */         this.fct.transform();
/* 284 */         this.fct.getData(dstDataType, dstData, dstOffset, dstScanlineStride);
/* 286 */       } else if (destRect.height > 1) {
/* 288 */         this.fct.setLength(getHeight());
/* 291 */         int dstOffset = dstAccessor.getBandOffset(band);
/* 294 */         for (int col = 0; col < destRect.width; col++) {
/* 296 */           this.fct.setData(dstDataType, dstData, dstOffset, dstScanlineStride, destRect.height);
/* 301 */           this.fct.transform();
/* 304 */           this.fct.getData(dstDataType, dstData, dstOffset, dstScanlineStride);
/* 308 */           dstOffset += dstPixelStride;
/*     */         } 
/*     */       } 
/*     */     } 
/* 313 */     if (dstAccessor.needsClamping())
/* 314 */       dstAccessor.clampDataArrays(); 
/* 318 */     dstAccessor.copyDataToRaster();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\DCTOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */