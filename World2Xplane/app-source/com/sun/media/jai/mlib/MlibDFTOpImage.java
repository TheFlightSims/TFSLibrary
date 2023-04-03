/*     */ package com.sun.media.jai.mlib;
/*     */ 
/*     */ import com.sun.media.jai.util.JDKWorkarounds;
/*     */ import com.sun.media.jai.util.MathJAI;
/*     */ import com.sun.medialib.mlib.Image;
/*     */ import com.sun.medialib.mlib.mediaLibImage;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.ComponentSampleModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.EnumeratedParameter;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.RasterFactory;
/*     */ import javax.media.jai.UntiledOpImage;
/*     */ import javax.media.jai.operator.DFTDescriptor;
/*     */ 
/*     */ final class MlibDFTOpImage extends UntiledOpImage {
/*     */   private int DFTMode;
/*     */   
/*     */   private static ImageLayout layoutHelper(ImageLayout layout, RenderedImage source, EnumeratedParameter dataNature) {
/*     */     int newWidth, newHeight;
/*  51 */     boolean isComplexSource = !dataNature.equals(DFTDescriptor.REAL_TO_COMPLEX);
/*  53 */     boolean isComplexDest = !dataNature.equals(DFTDescriptor.COMPLEX_TO_REAL);
/*  57 */     SampleModel srcSampleModel = source.getSampleModel();
/*  58 */     int numSourceBands = srcSampleModel.getNumBands();
/*  61 */     if ((isComplexSource && numSourceBands != 2) || (!isComplexSource && numSourceBands != 1))
/*  65 */       throw new RuntimeException(JaiI18N.getString("MlibDFTOpImage0")); 
/*  69 */     ImageLayout il = (layout == null) ? new ImageLayout() : (ImageLayout)layout.clone();
/*  73 */     il.setMinX(source.getMinX());
/*  74 */     il.setMinY(source.getMinY());
/*  79 */     int currentWidth = il.getWidth(source);
/*  80 */     int currentHeight = il.getHeight(source);
/*  83 */     if (currentWidth == 1 && currentHeight == 1) {
/*  84 */       newWidth = newHeight = 1;
/*  85 */     } else if (currentWidth == 1 && currentHeight > 1) {
/*  86 */       newWidth = 1;
/*  87 */       newHeight = MathJAI.nextPositivePowerOf2(currentHeight);
/*  88 */     } else if (currentWidth > 1 && currentHeight == 1) {
/*  89 */       newWidth = MathJAI.nextPositivePowerOf2(currentWidth);
/*  90 */       newHeight = 1;
/*     */     } else {
/*  92 */       newWidth = MathJAI.nextPositivePowerOf2(currentWidth);
/*  93 */       newHeight = MathJAI.nextPositivePowerOf2(currentHeight);
/*     */     } 
/*  95 */     il.setWidth(newWidth);
/*  96 */     il.setHeight(newHeight);
/*  99 */     boolean createNewSampleModel = false;
/* 102 */     int requiredNumBands = numSourceBands;
/* 103 */     if (isComplexSource && !isComplexDest) {
/* 104 */       requiredNumBands /= 2;
/* 105 */     } else if (!isComplexSource && isComplexDest) {
/* 106 */       requiredNumBands *= 2;
/*     */     } 
/* 110 */     SampleModel sm = il.getSampleModel(source);
/* 111 */     int numBands = sm.getNumBands();
/* 112 */     if (numBands != requiredNumBands) {
/* 113 */       numBands = requiredNumBands;
/* 114 */       createNewSampleModel = true;
/*     */     } 
/* 118 */     int dataType = sm.getTransferType();
/* 119 */     if (dataType != 4 && dataType != 5) {
/* 121 */       dataType = 4;
/* 122 */       createNewSampleModel = true;
/*     */     } 
/* 126 */     if (createNewSampleModel) {
/* 127 */       int[] bandOffsets = new int[numBands];
/* 131 */       for (int b = 0; b < numBands; b++)
/* 132 */         bandOffsets[b] = b; 
/* 135 */       int lineStride = newWidth * numBands;
/* 136 */       sm = RasterFactory.createPixelInterleavedSampleModel(dataType, newWidth, newHeight, numBands, lineStride, bandOffsets);
/* 142 */       il.setSampleModel(sm);
/* 145 */       ColorModel cm = il.getColorModel(null);
/* 146 */       if (cm != null && !JDKWorkarounds.areCompatibleDataModels(sm, cm))
/* 149 */         il.unsetValid(512); 
/*     */     } 
/* 153 */     return il;
/*     */   }
/*     */   
/*     */   public MlibDFTOpImage(RenderedImage source, Map config, ImageLayout layout, EnumeratedParameter dataNature, boolean isForward, EnumeratedParameter scaleType) {
/* 174 */     super(source, config, layoutHelper(layout, source, dataNature));
/* 176 */     if (scaleType.equals(DFTDescriptor.SCALING_NONE)) {
/* 177 */       this.DFTMode = isForward ? 0 : 3;
/* 180 */     } else if (scaleType.equals(DFTDescriptor.SCALING_UNITARY)) {
/* 181 */       this.DFTMode = isForward ? 2 : 5;
/* 184 */     } else if (scaleType.equals(DFTDescriptor.SCALING_DIMENSIONS)) {
/* 185 */       this.DFTMode = isForward ? 1 : 4;
/*     */     } else {
/* 191 */       throw new RuntimeException(JaiI18N.getString("MlibDFTOpImage1"));
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean isAcceptableSampleModel(SampleModel sm) {
/* 205 */     if (!(sm instanceof ComponentSampleModel))
/* 206 */       return true; 
/* 209 */     ComponentSampleModel csm = (ComponentSampleModel)sm;
/* 211 */     int[] bandOffsets = csm.getBandOffsets();
/* 213 */     if (bandOffsets.length == 2 && bandOffsets[1] == bandOffsets[0] + 1)
/* 215 */       return true; 
/* 218 */     return false;
/*     */   }
/*     */   
/*     */   public Point2D mapDestPoint(Point2D destPt) {
/* 235 */     if (destPt == null)
/* 236 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 239 */     return null;
/*     */   }
/*     */   
/*     */   public Point2D mapSourcePoint(Point2D sourcePt) {
/* 253 */     if (sourcePt == null)
/* 254 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 257 */     return null;
/*     */   }
/*     */   
/*     */   protected void computeImage(Raster[] sources, WritableRaster dest, Rectangle destRect) {
/* 271 */     Raster source = sources[0];
/* 273 */     int formatTag = MediaLibAccessor.findCompatibleTag(new Raster[] { source }, dest);
/* 276 */     MediaLibAccessor srcAccessor = new MediaLibAccessor(source, mapDestRect(destRect, 0), formatTag);
/* 278 */     MediaLibAccessor dstAccessor = new MediaLibAccessor(dest, destRect, formatTag);
/* 281 */     mediaLibImage[] srcML = srcAccessor.getMediaLibImages();
/* 282 */     mediaLibImage[] dstML = dstAccessor.getMediaLibImages();
/* 284 */     for (int i = 0; i < dstML.length; i++)
/* 285 */       Image.FourierTransform(dstML[i], srcML[i], this.DFTMode); 
/* 289 */     if (dstAccessor.isDataCopy()) {
/* 290 */       dstAccessor.clampDataArrays();
/* 291 */       dstAccessor.copyDataToRaster();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\MlibDFTOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */