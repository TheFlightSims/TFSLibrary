/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import javax.media.jai.operator.DFTDescriptor;
/*     */ 
/*     */ public class FCT {
/*     */   protected boolean isForwardTransform;
/*     */   
/*  31 */   private FFT fft = null;
/*     */   
/*     */   public FCT() {}
/*     */   
/*     */   public FCT(boolean isForwardTransform, int length) {
/*  43 */     this.isForwardTransform = isForwardTransform;
/*  46 */     this.fft = new FFT(isForwardTransform, new Integer(DFTDescriptor.SCALING_NONE.getValue()), length);
/*     */   }
/*     */   
/*     */   public void setLength(int length) {
/*  57 */     this.fft.setLength(length);
/*     */   }
/*     */   
/*     */   public void setData(int dataType, Object data, int offset, int stride, int count) {
/*  74 */     if (this.isForwardTransform) {
/*  75 */       this.fft.setFCTData(dataType, data, offset, stride, count);
/*     */     } else {
/*  77 */       this.fft.setIFCTData(dataType, data, offset, stride, count);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void getData(int dataType, Object data, int offset, int stride) {
/*  93 */     if (this.isForwardTransform) {
/*  94 */       this.fft.getFCTData(dataType, data, offset, stride);
/*     */     } else {
/*  96 */       this.fft.getIFCTData(dataType, data, offset, stride);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void transform() {
/* 104 */     this.fft.transform();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\FCT.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */