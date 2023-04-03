/*     */ package com.sun.media.jai.mlib;
/*     */ 
/*     */ import com.sun.media.jai.opimage.FFT;
/*     */ import com.sun.media.jai.util.MathJAI;
/*     */ import com.sun.medialib.mlib.Image;
/*     */ 
/*     */ public class FFTmediaLib extends FFT {
/*     */   private boolean specialUnitaryScaling = false;
/*     */   
/*  35 */   private static final double SQUARE_ROOT_OF_2 = Math.sqrt(2.0D);
/*     */   
/*     */   public FFTmediaLib(boolean negatedExponent, Integer scaleType, int length) {
/*  47 */     super(negatedExponent, scaleType, length);
/*     */   }
/*     */   
/*     */   public void setLength(int length) {
/*  57 */     if (this.lengthIsSet && length == this.length)
/*     */       return; 
/*  62 */     if (!MathJAI.isPositivePowerOf2(length))
/*  63 */       throw new RuntimeException(JaiI18N.getString("FFTmediaLib0")); 
/*  67 */     this.length = length;
/*  70 */     if (!this.lengthIsSet || length != this.real.length) {
/*  71 */       this.real = new double[length];
/*  72 */       this.imag = new double[length];
/*     */     } 
/*  76 */     this.lengthIsSet = true;
/*  79 */     if (this.scaleType == SCALING_UNITARY) {
/*  82 */       int exponent = 0;
/*  83 */       int powerOfTwo = 1;
/*  84 */       while (powerOfTwo < length) {
/*  85 */         powerOfTwo <<= 1;
/*  86 */         exponent++;
/*     */       } 
/*  90 */       this.specialUnitaryScaling = (exponent % 2 != 0);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void getData(int dataType, Object realArg, int offsetReal, int strideReal, Object imagArg, int offsetImag, int strideImag) {
/*     */     float[] realFloat;
/*     */     double[] realDouble;
/* 111 */     switch (dataType) {
/*     */       case 4:
/* 114 */         realFloat = (float[])realArg;
/* 115 */         if (imagArg != null) {
/* 116 */           float[] imagFloat = (float[])imagArg;
/* 117 */           if (offsetReal == offsetImag && strideReal == strideImag) {
/* 119 */             for (int i = 0; i < this.length; i++) {
/* 122 */               realFloat[offsetReal] = (float)this.real[i];
/* 123 */               imagFloat[offsetReal] = (float)this.imag[i];
/* 124 */               offsetReal += strideReal;
/*     */             } 
/*     */           } else {
/* 127 */             for (int i = 0; i < this.length; i++) {
/* 128 */               realFloat[offsetReal] = (float)this.real[i];
/* 129 */               imagFloat[offsetImag] = (float)this.imag[i];
/* 130 */               offsetReal += strideReal;
/* 131 */               offsetImag += strideImag;
/*     */             } 
/*     */           } 
/*     */         } else {
/* 135 */           for (int i = 0; i < this.length; i++) {
/* 136 */             realFloat[offsetReal] = (float)this.real[i];
/* 137 */             offsetReal += strideReal;
/*     */           } 
/*     */         } 
/*     */         return;
/*     */       case 5:
/* 144 */         realDouble = (double[])realArg;
/* 145 */         if (imagArg != null) {
/* 146 */           double[] imagDouble = (double[])imagArg;
/* 147 */           if (offsetReal == offsetImag && strideReal == strideImag) {
/* 149 */             for (int i = 0; i < this.length; i++) {
/* 150 */               realDouble[offsetReal] = this.real[i];
/* 151 */               imagDouble[offsetReal] = this.imag[i];
/* 152 */               offsetReal += strideReal;
/*     */             } 
/*     */           } else {
/* 155 */             for (int i = 0; i < this.length; i++) {
/* 156 */               realDouble[offsetReal] = this.real[i];
/* 157 */               imagDouble[offsetImag] = this.imag[i];
/* 158 */               offsetReal += strideReal;
/* 159 */               offsetImag += strideImag;
/*     */             } 
/*     */           } 
/*     */         } else {
/* 163 */           for (int i = 0; i < this.length; i++) {
/* 164 */             realDouble[offsetReal] = this.real[i];
/* 165 */             offsetReal += strideReal;
/*     */           } 
/*     */         } 
/*     */         return;
/*     */     } 
/* 176 */     throw new RuntimeException(dataType + JaiI18N.getString("FFTmediaLib1"));
/*     */   }
/*     */   
/*     */   public void transform() {
/* 184 */     if (this.exponentSign < 0) {
/* 185 */       if (this.scaleType == SCALING_NONE) {
/* 186 */         Image.FFT_1(this.real, this.imag);
/* 187 */       } else if (this.scaleType == SCALING_UNITARY) {
/* 188 */         Image.FFT_3(this.real, this.imag);
/* 190 */         if (this.specialUnitaryScaling)
/* 195 */           for (int i = 0; i < this.length; i++) {
/* 196 */             this.real[i] = this.real[i] * SQUARE_ROOT_OF_2;
/* 197 */             this.imag[i] = this.imag[i] * SQUARE_ROOT_OF_2;
/*     */           }  
/* 200 */       } else if (this.scaleType == SCALING_DIMENSIONS) {
/* 201 */         Image.FFT_2(this.real, this.imag);
/*     */       } 
/* 204 */     } else if (this.scaleType == SCALING_NONE) {
/* 205 */       Image.IFFT_2(this.real, this.imag);
/* 206 */     } else if (this.scaleType == SCALING_UNITARY) {
/* 207 */       Image.IFFT_3(this.real, this.imag);
/* 209 */       if (this.specialUnitaryScaling)
/* 214 */         for (int i = 0; i < this.length; i++) {
/* 215 */           this.real[i] = this.real[i] / SQUARE_ROOT_OF_2;
/* 216 */           this.imag[i] = this.imag[i] / SQUARE_ROOT_OF_2;
/*     */         }  
/* 219 */     } else if (this.scaleType == SCALING_DIMENSIONS) {
/* 220 */       Image.IFFT_1(this.real, this.imag);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\mlib\FFTmediaLib.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */