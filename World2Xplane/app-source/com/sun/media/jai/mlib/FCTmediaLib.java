/*     */ package com.sun.media.jai.mlib;
/*     */ 
/*     */ import com.sun.media.jai.opimage.FCT;
/*     */ import com.sun.media.jai.util.MathJAI;
/*     */ import com.sun.medialib.mlib.Image;
/*     */ import java.util.Arrays;
/*     */ 
/*     */ public class FCTmediaLib extends FCT {
/*     */   private int length;
/*     */   
/*     */   private boolean lengthIsSet = false;
/*     */   
/*     */   private double[] wr;
/*     */   
/*     */   private double[] wi;
/*     */   
/*     */   protected double[] real;
/*     */   
/*     */   protected double[] imag;
/*     */   
/*     */   public FCTmediaLib(boolean isForwardTransform, int length) {
/*  52 */     this.isForwardTransform = isForwardTransform;
/*  53 */     setLength(length);
/*     */   }
/*     */   
/*     */   public void setLength(int length) {
/*  65 */     if (this.lengthIsSet && length == this.length)
/*     */       return; 
/*  70 */     if (!MathJAI.isPositivePowerOf2(length))
/*  71 */       throw new RuntimeException(JaiI18N.getString("FCTmediaLib0")); 
/*  75 */     this.length = length;
/*  78 */     if (this.real == null || length != this.real.length) {
/*  79 */       this.real = new double[length];
/*  80 */       this.imag = new double[length];
/*     */     } 
/*  84 */     calculateFCTLUTs();
/*  87 */     this.lengthIsSet = true;
/*     */   }
/*     */   
/*     */   private void calculateFCTLUTs() {
/*  94 */     this.wr = new double[this.length];
/*  95 */     this.wi = new double[this.length];
/*  97 */     for (int i = 0; i < this.length; i++) {
/*  98 */       double factor = (i == 0) ? Math.sqrt(1.0D / this.length) : Math.sqrt(2.0D / this.length);
/* 101 */       double freq = Math.PI * i / 2.0D * this.length;
/* 102 */       this.wr[i] = factor * Math.cos(freq);
/* 103 */       this.wi[i] = factor * Math.sin(freq);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setData(int dataType, Object data, int offset, int stride, int count) {
/* 121 */     if (this.isForwardTransform) {
/* 122 */       setFCTData(dataType, data, offset, stride, count);
/*     */     } else {
/* 124 */       setIFCTData(dataType, data, offset, stride, count);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void getData(int dataType, Object data, int offset, int stride) {
/* 140 */     if (this.isForwardTransform) {
/* 141 */       getFCTData(dataType, data, offset, stride);
/*     */     } else {
/* 143 */       getIFCTData(dataType, data, offset, stride);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void setFCTData(int dataType, Object data, int offset, int stride, int count) {
/*     */     float[] realFloat;
/*     */     double[] realDouble;
/*     */     int n, m, i, k, j, i1;
/* 162 */     switch (dataType) {
/*     */       case 4:
/* 165 */         realFloat = (float[])data;
/* 166 */         for (n = 0; n < count; n++) {
/* 167 */           this.imag[n] = realFloat[offset];
/* 168 */           offset += stride;
/*     */         } 
/* 170 */         for (n = count; n < this.length; n++)
/* 171 */           this.imag[n] = 0.0D; 
/* 173 */         m = this.length - 1;
/* 174 */         j = 0;
/* 175 */         for (i1 = 0; i1 < m; i1++) {
/* 176 */           this.real[i1] = this.imag[j++];
/* 177 */           this.real[m--] = this.imag[j++];
/*     */         } 
/*     */         break;
/*     */       case 5:
/* 183 */         realDouble = (double[])data;
/* 184 */         for (i = 0; i < count; i++) {
/* 185 */           this.imag[i] = realDouble[offset];
/* 186 */           offset += stride;
/*     */         } 
/* 188 */         for (i = count; i < this.length; i++)
/* 189 */           this.imag[i] = 0.0D; 
/* 191 */         k = this.length - 1;
/* 192 */         j = 0;
/* 193 */         for (i1 = 0; i1 < k; i1++) {
/* 194 */           this.real[i1] = this.imag[j++];
/* 195 */           this.real[k--] = this.imag[j++];
/*     */         } 
/*     */         break;
/*     */       default:
/* 205 */         throw new RuntimeException(dataType + JaiI18N.getString("FCTmediaLib1"));
/*     */     } 
/* 209 */     Arrays.fill(this.imag, 0, this.length, 0.0D);
/*     */   }
/*     */   
/*     */   private void getFCTData(int dataType, Object data, int offset, int stride) {
/*     */     float[] realFloat;
/*     */     double[] realDouble;
/*     */     int i;
/* 225 */     switch (dataType) {
/*     */       case 4:
/* 228 */         realFloat = (float[])data;
/* 229 */         for (i = 0; i < this.length; i++) {
/* 230 */           realFloat[offset] = (float)(this.wr[i] * this.real[i] + this.wi[i] * this.imag[i]);
/* 232 */           offset += stride;
/*     */         } 
/*     */         return;
/*     */       case 5:
/* 238 */         realDouble = (double[])data;
/* 239 */         for (i = 0; i < this.length; i++) {
/* 240 */           realDouble[offset] = this.wr[i] * this.real[i] + this.wi[i] * this.imag[i];
/* 242 */           offset += stride;
/*     */         } 
/*     */         return;
/*     */     } 
/* 252 */     throw new RuntimeException(dataType + JaiI18N.getString("FCTmediaLib1"));
/*     */   }
/*     */   
/*     */   private void setIFCTData(int dataType, Object data, int offset, int stride, int count) {
/*     */     float[] realFloat;
/*     */     double[] realDouble;
/*     */     int i;
/* 271 */     switch (dataType) {
/*     */       case 4:
/* 274 */         realFloat = (float[])data;
/* 275 */         for (i = 0; i < count; i++) {
/* 276 */           float r = realFloat[offset];
/* 277 */           this.real[i] = r * this.wr[i];
/* 278 */           this.imag[i] = r * this.wi[i];
/* 279 */           offset += stride;
/*     */         } 
/*     */         break;
/*     */       case 5:
/* 285 */         realDouble = (double[])data;
/* 286 */         for (i = 0; i < count; i++) {
/* 287 */           double r = realDouble[offset];
/* 288 */           this.real[i] = r * this.wr[i];
/* 289 */           this.imag[i] = r * this.wi[i];
/* 290 */           offset += stride;
/*     */         } 
/*     */         break;
/*     */       default:
/* 300 */         throw new RuntimeException(dataType + JaiI18N.getString("FCTmediaLib1"));
/*     */     } 
/* 304 */     if (count < this.length) {
/* 305 */       Arrays.fill(this.real, count, this.length, 0.0D);
/* 306 */       Arrays.fill(this.imag, count, this.length, 0.0D);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void getIFCTData(int dataType, Object data, int offset, int stride) {
/*     */     float[] realFloat;
/*     */     double[] realDouble;
/*     */     int k;
/*     */     int i;
/* 323 */     switch (dataType) {
/*     */       case 4:
/* 326 */         realFloat = (float[])data;
/* 327 */         k = this.length - 1;
/* 328 */         for (i = 0; i < k; i++) {
/* 329 */           realFloat[offset] = (float)this.real[i];
/* 330 */           offset += stride;
/* 331 */           realFloat[offset] = (float)this.real[k--];
/* 332 */           offset += stride;
/*     */         } 
/*     */         return;
/*     */       case 5:
/* 338 */         realDouble = (double[])data;
/* 339 */         k = this.length - 1;
/* 340 */         for (i = 0; i < k; i++) {
/* 341 */           realDouble[offset] = (float)this.real[i];
/* 342 */           offset += stride;
/* 343 */           realDouble[offset] = (float)this.real[k--];
/* 344 */           offset += stride;
/*     */         } 
/*     */         return;
/*     */     } 
/* 354 */     throw new RuntimeException(dataType + JaiI18N.getString("FCTmediaLib1"));
/*     */   }
/*     */   
/*     */   public void transform() {
/* 362 */     if (this.isForwardTransform) {
/* 363 */       Image.FFT_1(this.real, this.imag);
/*     */     } else {
/* 365 */       Image.IFFT_2(this.real, this.imag);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\FCTmediaLib.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */