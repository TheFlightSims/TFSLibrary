/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import java.awt.Point;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ import javax.media.jai.PlanarImage;
/*     */ import javax.media.jai.RasterFactory;
/*     */ 
/*     */ final class ConstantOpImage extends PatternOpImage {
/*     */   private static Raster makePattern(SampleModel sampleModel, Number[] bandValues) {
/*     */     int bvalues[], i, x, ivalues[], k, j;
/*     */     float[] fvalues;
/*     */     int n, m;
/*     */     double[] dvalues;
/*     */     int i2, i1;
/*  41 */     WritableRaster pattern = RasterFactory.createWritableRaster(sampleModel, new Point(0, 0));
/*  44 */     int width = sampleModel.getWidth();
/*  45 */     int height = sampleModel.getHeight();
/*  46 */     int dataType = sampleModel.getTransferType();
/*  47 */     int numBands = sampleModel.getNumBands();
/*  49 */     switch (dataType) {
/*     */       case 0:
/*  51 */         bvalues = new int[numBands];
/*  52 */         for (i = 0; i < numBands; i++)
/*  53 */           bvalues[i] = bandValues[i].intValue() & 0xFF; 
/*  57 */         for (x = 0; x < width; x++)
/*  58 */           pattern.setPixel(x, 0, bvalues); 
/*     */         break;
/*     */       case 1:
/*     */       case 2:
/*     */       case 3:
/*  65 */         ivalues = new int[numBands];
/*  66 */         for (k = 0; k < numBands; k++)
/*  67 */           ivalues[k] = bandValues[k].intValue(); 
/*  71 */         for (j = 0; j < width; j++)
/*  72 */           pattern.setPixel(j, 0, ivalues); 
/*     */         break;
/*     */       case 4:
/*  77 */         fvalues = new float[numBands];
/*  78 */         for (n = 0; n < numBands; n++)
/*  79 */           fvalues[n] = bandValues[n].floatValue(); 
/*  83 */         for (m = 0; m < width; m++)
/*  84 */           pattern.setPixel(m, 0, fvalues); 
/*     */         break;
/*     */       case 5:
/*  89 */         dvalues = new double[numBands];
/*  90 */         for (i2 = 0; i2 < numBands; i2++)
/*  91 */           dvalues[i2] = bandValues[i2].doubleValue(); 
/*  95 */         for (i1 = 0; i1 < width; i1++)
/*  96 */           pattern.setPixel(i1, 0, dvalues); 
/*     */         break;
/*     */     } 
/* 102 */     Object odata = pattern.getDataElements(0, 0, width, 1, null);
/* 105 */     for (int y = 1; y < height; y++)
/* 106 */       pattern.setDataElements(0, y, width, 1, odata); 
/* 109 */     return pattern;
/*     */   }
/*     */   
/*     */   private static SampleModel makeSampleModel(int width, int height, Number[] bandValues) {
/* 114 */     int dataType, numBands = bandValues.length;
/* 117 */     if (bandValues instanceof Byte[]) {
/* 118 */       dataType = 0;
/* 119 */     } else if (bandValues instanceof Short[]) {
/* 121 */       dataType = 1;
/* 123 */       Short[] shortValues = (Short[])bandValues;
/* 124 */       for (int i = 0; i < numBands; i++) {
/* 125 */         if (shortValues[i].shortValue() < 0) {
/* 126 */           dataType = 2;
/*     */           break;
/*     */         } 
/*     */       } 
/* 130 */     } else if (bandValues instanceof Integer[]) {
/* 131 */       dataType = 3;
/* 132 */     } else if (bandValues instanceof Float[]) {
/* 133 */       dataType = 4;
/* 134 */     } else if (bandValues instanceof Double[]) {
/* 135 */       dataType = 5;
/*     */     } else {
/* 137 */       dataType = 32;
/*     */     } 
/* 140 */     return RasterFactory.createPixelInterleavedSampleModel(dataType, width, height, numBands);
/*     */   }
/*     */   
/*     */   private static Raster patternHelper(int width, int height, Number[] bandValues) {
/* 147 */     SampleModel sampleModel = makeSampleModel(width, height, bandValues);
/* 148 */     return makePattern(sampleModel, bandValues);
/*     */   }
/*     */   
/*     */   private static ColorModel colorModelHelper(Number[] bandValues) {
/* 152 */     SampleModel sampleModel = makeSampleModel(1, 1, bandValues);
/* 153 */     return PlanarImage.createColorModel(sampleModel);
/*     */   }
/*     */   
/*     */   public ConstantOpImage(int minX, int minY, int width, int height, int tileWidth, int tileHeight, Number[] bandValues) {
/* 169 */     super(patternHelper(tileWidth, tileHeight, bandValues), colorModelHelper(bandValues), minX, minY, width, height);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\ConstantOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */