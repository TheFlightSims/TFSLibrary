/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import java.util.Arrays;
/*     */ import javax.media.jai.CRIFImpl;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.KernelJAI;
/*     */ 
/*     */ final class FilterCRIF extends CRIFImpl {
/*     */   private static final int STEPSIZE = 5;
/*     */   
/*     */   private static final KernelJAI createKernel(double p) {
/*     */     int size;
/*     */     float[] data;
/*  41 */     int STEPSIZE = 5;
/*  43 */     if (p == 0.0D)
/*  44 */       return null; 
/*  47 */     double pAbs = Math.abs(p);
/*  48 */     int idx = (int)pAbs / STEPSIZE;
/*  49 */     double frac = (10.0F / STEPSIZE) * (pAbs - (idx * STEPSIZE));
/*  50 */     double blend = 0.010101010101010102D * (Math.pow(10.0D, 0.2D * frac) - 1.0D);
/*  55 */     if ((idx * STEPSIZE) == pAbs) {
/*  58 */       size = 2 * idx + 1;
/*  59 */       data = new float[size * size];
/*  60 */       float val = 1.0F / (size * size);
/*  61 */       Arrays.fill(data, val);
/*     */     } else {
/*  64 */       int size1 = 2 * idx + 1;
/*  65 */       size = size1 + 2;
/*  66 */       data = new float[size * size];
/*  67 */       float val1 = 1.0F / (size1 * size1) * (1.0F - (float)blend);
/*  68 */       int row = size;
/*  69 */       for (int j = 1; j < size - 1; j++) {
/*  70 */         for (int k = 1; k < size - 1; k++)
/*  71 */           data[row + k] = val1; 
/*  73 */         row += size;
/*     */       } 
/*  75 */       float val2 = 1.0F / (size * size) * (float)blend;
/*  76 */       for (int i = 0; i < data.length; i++)
/*  77 */         data[i] = data[i] + val2; 
/*     */     } 
/*  82 */     if (p > 0.0D) {
/*  84 */       for (int i = 0; i < data.length; i++)
/*  85 */         data[i] = (float)(data[i] * -1.0D); 
/*  87 */       data[data.length / 2] = data[data.length / 2] + 2.0F;
/*     */     } 
/*  90 */     return new KernelJAI(size, size, data);
/*     */   }
/*     */   
/*     */   public RenderedImage create(ParameterBlock paramBlock, RenderingHints renderHints) {
/* 103 */     KernelJAI kernel = createKernel(paramBlock.getFloatParameter(0));
/* 105 */     return (kernel == null) ? paramBlock.getRenderedSource(0) : (RenderedImage)JAI.create("convolve", paramBlock.getRenderedSource(0), kernel);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\FilterCRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */