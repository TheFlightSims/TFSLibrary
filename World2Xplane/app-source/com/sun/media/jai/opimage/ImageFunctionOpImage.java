/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import javax.media.jai.ImageFunction;
/*     */ import javax.media.jai.ImageLayout;
/*     */ import javax.media.jai.PlanarImage;
/*     */ import javax.media.jai.RasterFactory;
/*     */ import javax.media.jai.SourcelessOpImage;
/*     */ 
/*     */ final class ImageFunctionOpImage extends SourcelessOpImage {
/*     */   protected ImageFunction function;
/*     */   
/*     */   protected float xScale;
/*     */   
/*     */   protected float yScale;
/*     */   
/*     */   protected float xTrans;
/*     */   
/*     */   protected float yTrans;
/*     */   
/*     */   private static SampleModel sampleModelHelper(int numBands, ImageLayout layout) {
/*     */     SampleModel sampleModel;
/*  51 */     if (layout != null && layout.isValid(256)) {
/*  52 */       sampleModel = layout.getSampleModel(null);
/*  54 */       if (sampleModel.getNumBands() != numBands)
/*  55 */         throw new RuntimeException(JaiI18N.getString("ImageFunctionRIF0")); 
/*     */     } else {
/*  59 */       sampleModel = RasterFactory.createBandedSampleModel(4, 1, 1, numBands);
/*     */     } 
/*  65 */     return sampleModel;
/*     */   }
/*     */   
/*     */   public ImageFunctionOpImage(ImageFunction function, int minX, int minY, int width, int height, float xScale, float yScale, float xTrans, float yTrans, Map config, ImageLayout layout) {
/*  81 */     super(layout, config, sampleModelHelper(function.getNumElements() * (function.isComplex() ? 2 : 1), layout), minX, minY, width, height);
/*  89 */     this.function = function;
/*  90 */     this.xScale = xScale;
/*  91 */     this.yScale = yScale;
/*  92 */     this.xTrans = xTrans;
/*  93 */     this.yTrans = yTrans;
/*     */   }
/*     */   
/*     */   protected void computeRect(PlanarImage[] sources, WritableRaster dest, Rectangle destRect) {
/*     */     Object data;
/* 104 */     int dataType = this.sampleModel.getTransferType();
/* 105 */     int numBands = this.sampleModel.getNumBands();
/* 108 */     int length = this.width * this.height;
/* 110 */     if (dataType == 5) {
/* 111 */       data = this.function.isComplex() ? new double[2][length] : new double[length];
/*     */     } else {
/* 114 */       data = this.function.isComplex() ? new float[2][length] : new float[length];
/*     */     } 
/* 118 */     if (dataType == 5) {
/* 119 */       double[] real = this.function.isComplex() ? ((double[][])data)[0] : (double[])data;
/* 121 */       double[] imag = this.function.isComplex() ? ((double[][])data)[1] : null;
/* 124 */       int element = 0;
/* 125 */       for (int band = 0; band < numBands; band++) {
/* 126 */         this.function.getElements((this.xScale * (destRect.x - this.xTrans)), (this.yScale * (destRect.y - this.yTrans)), this.xScale, this.yScale, destRect.width, destRect.height, element++, real, imag);
/* 132 */         dest.setSamples(destRect.x, destRect.y, destRect.width, destRect.height, band, real);
/* 135 */         if (this.function.isComplex())
/* 136 */           dest.setSamples(destRect.x, destRect.y, destRect.width, destRect.height, ++band, imag); 
/*     */       } 
/*     */     } else {
/* 143 */       float[] real = this.function.isComplex() ? ((float[][])data)[0] : (float[])data;
/* 145 */       float[] imag = this.function.isComplex() ? ((float[][])data)[1] : null;
/* 148 */       int element = 0;
/* 149 */       for (int band = 0; band < numBands; band++) {
/* 150 */         this.function.getElements(this.xScale * (destRect.x - this.xTrans), this.yScale * (destRect.y - this.yTrans), this.xScale, this.yScale, destRect.width, destRect.height, element++, real, imag);
/* 156 */         dest.setSamples(destRect.x, destRect.y, destRect.width, destRect.height, band, real);
/* 159 */         if (this.function.isComplex())
/* 160 */           dest.setSamples(destRect.x, destRect.y, destRect.width, destRect.height, ++band, imag); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\ImageFunctionOpImage.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */