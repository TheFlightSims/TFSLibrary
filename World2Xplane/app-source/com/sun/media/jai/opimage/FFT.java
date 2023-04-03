/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import com.sun.media.jai.util.MathJAI;
/*     */ import java.text.NumberFormat;
/*     */ import java.util.Arrays;
/*     */ import java.util.Locale;
/*     */ import javax.media.jai.operator.DFTDescriptor;
/*     */ 
/*     */ public class FFT {
/*  30 */   public static final int SCALING_NONE = DFTDescriptor.SCALING_NONE.getValue();
/*     */   
/*  37 */   public static final int SCALING_UNITARY = DFTDescriptor.SCALING_UNITARY.getValue();
/*     */   
/*  44 */   public static final int SCALING_DIMENSIONS = DFTDescriptor.SCALING_DIMENSIONS.getValue();
/*     */   
/*     */   protected boolean lengthIsSet = false;
/*     */   
/*     */   protected int exponentSign;
/*     */   
/*     */   protected int scaleType;
/*     */   
/*     */   protected int length;
/*     */   
/*     */   private int nbits;
/*     */   
/*     */   private int[] index;
/*     */   
/*     */   private double scaleFactor;
/*     */   
/*     */   private double[] wr;
/*     */   
/*     */   private double[] wi;
/*     */   
/*     */   private double[] wrFCT;
/*     */   
/*     */   private double[] wiFCT;
/*     */   
/*     */   protected double[] real;
/*     */   
/*     */   protected double[] imag;
/*     */   
/*     */   public FFT(boolean negatedExponent, Integer scaleType, int length) {
/*  95 */     this.exponentSign = negatedExponent ? -1 : 1;
/*  98 */     this.scaleType = scaleType.intValue();
/* 101 */     setLength(length);
/*     */   }
/*     */   
/*     */   public void setLength(int length) {
/* 111 */     if (this.lengthIsSet && length == this.length)
/*     */       return; 
/* 116 */     if (!MathJAI.isPositivePowerOf2(length))
/* 117 */       throw new RuntimeException(JaiI18N.getString("FFT0")); 
/* 121 */     this.length = length;
/* 124 */     if (this.scaleType == SCALING_NONE) {
/* 125 */       this.scaleFactor = 1.0D;
/* 126 */     } else if (this.scaleType == SCALING_UNITARY) {
/* 127 */       this.scaleFactor = 1.0D / Math.sqrt(length);
/* 128 */     } else if (this.scaleType == SCALING_DIMENSIONS) {
/* 129 */       this.scaleFactor = 1.0D / length;
/*     */     } else {
/* 133 */       throw new RuntimeException(JaiI18N.getString("FFT1"));
/*     */     } 
/* 137 */     int power = 1;
/* 138 */     this.nbits = 0;
/* 139 */     while (power < length) {
/* 140 */       this.nbits++;
/* 141 */       power <<= 1;
/*     */     } 
/* 145 */     initBitReversalLUT();
/* 148 */     calculateCoefficientLUTs();
/* 151 */     if (!this.lengthIsSet || length > this.real.length) {
/* 152 */       this.real = new double[length];
/* 153 */       this.imag = new double[length];
/*     */     } 
/* 157 */     this.lengthIsSet = true;
/*     */   }
/*     */   
/*     */   private void initBitReversalLUT() {
/* 165 */     this.index = new int[this.length];
/* 166 */     for (int i = 0; i < this.length; i++) {
/* 167 */       int l = i;
/* 168 */       int power = this.length >> 1;
/* 169 */       int irev = 0;
/* 170 */       for (int k = 0; k < this.nbits; k++) {
/* 171 */         int j = l & 0x1;
/* 172 */         if (j != 0)
/* 173 */           irev += power; 
/* 175 */         l >>= 1;
/* 176 */         power >>= 1;
/* 178 */         this.index[i] = irev;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void calculateCoefficientLUTs() {
/* 187 */     this.wr = new double[this.nbits];
/* 188 */     this.wi = new double[this.nbits];
/* 190 */     int inode = 1;
/* 191 */     double cons = this.exponentSign * Math.PI;
/* 193 */     for (int bit = 0; bit < this.nbits; bit++) {
/* 194 */       this.wr[bit] = Math.cos(cons / inode);
/* 195 */       this.wi[bit] = Math.sin(cons / inode);
/* 196 */       inode *= 2;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void calculateFCTLUTs() {
/* 204 */     this.wrFCT = new double[this.length];
/* 205 */     this.wiFCT = new double[this.length];
/* 207 */     for (int i = 0; i < this.length; i++) {
/* 208 */       double factor = (i == 0) ? Math.sqrt(1.0D / this.length) : Math.sqrt(2.0D / this.length);
/* 211 */       double freq = Math.PI * i / 2.0D * this.length;
/* 212 */       this.wrFCT[i] = factor * Math.cos(freq);
/* 213 */       this.wiFCT[i] = factor * Math.sin(freq);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setData(int dataType, Object realArg, int offsetReal, int strideReal, Object imagArg, int offsetImag, int strideImag, int count) {
/*     */     float[] realFloat;
/*     */     double[] realDouble;
/*     */     int i;
/* 236 */     switch (dataType) {
/*     */       case 4:
/* 239 */         realFloat = (float[])realArg;
/* 240 */         if (imagArg != null) {
/* 241 */           float[] imagFloat = (float[])imagArg;
/* 242 */           if (offsetReal == offsetImag && strideReal == strideImag) {
/* 244 */             for (int k = 0; k < count; k++) {
/* 245 */               this.real[k] = realFloat[offsetReal];
/* 246 */               this.imag[k] = imagFloat[offsetReal];
/* 247 */               offsetReal += strideReal;
/*     */             } 
/*     */             break;
/*     */           } 
/* 250 */           for (int j = 0; j < count; j++) {
/* 251 */             this.real[j] = realFloat[offsetReal];
/* 252 */             this.imag[j] = imagFloat[offsetImag];
/* 253 */             offsetReal += strideReal;
/* 254 */             offsetImag += strideImag;
/*     */           } 
/*     */           break;
/*     */         } 
/* 258 */         for (i = 0; i < count; i++) {
/* 259 */           this.real[i] = realFloat[offsetReal];
/* 260 */           offsetReal += strideReal;
/*     */         } 
/*     */         break;
/*     */       case 5:
/* 267 */         realDouble = (double[])realArg;
/* 268 */         if (strideReal == 1 && strideImag == 1) {
/* 269 */           System.arraycopy(realDouble, offsetReal, this.real, 0, count);
/* 271 */           if (imagArg != null)
/* 272 */             System.arraycopy(imagArg, offsetImag, this.imag, 0, count); 
/*     */           break;
/*     */         } 
/* 275 */         if (imagArg != null) {
/* 276 */           double[] imagDouble = (double[])imagArg;
/* 277 */           if (offsetReal == offsetImag && strideReal == strideImag) {
/* 279 */             for (int k = 0; k < count; k++) {
/* 280 */               this.real[k] = realDouble[offsetReal];
/* 281 */               this.imag[k] = imagDouble[offsetReal];
/* 282 */               offsetReal += strideReal;
/*     */             } 
/*     */             break;
/*     */           } 
/* 285 */           for (int j = 0; j < count; j++) {
/* 286 */             this.real[j] = realDouble[offsetReal];
/* 287 */             this.imag[j] = imagDouble[offsetImag];
/* 288 */             offsetReal += strideReal;
/* 289 */             offsetImag += strideImag;
/*     */           } 
/*     */           break;
/*     */         } 
/* 293 */         for (i = 0; i < count; i++) {
/* 294 */           this.real[i] = realDouble[offsetReal];
/* 295 */           offsetReal += strideReal;
/*     */         } 
/*     */         break;
/*     */       default:
/* 306 */         throw new RuntimeException(dataType + JaiI18N.getString("FFT2"));
/*     */     } 
/* 310 */     if (count < this.length) {
/* 311 */       Arrays.fill(this.real, count, this.length, 0.0D);
/* 312 */       if (imagArg != null)
/* 313 */         Arrays.fill(this.imag, count, this.length, 0.0D); 
/*     */     } 
/* 317 */     if (imagArg == null)
/* 318 */       Arrays.fill(this.imag, 0, this.length, 0.0D); 
/*     */   }
/*     */   
/*     */   public void getData(int dataType, Object realArg, int offsetReal, int strideReal, Object imagArg, int offsetImag, int strideImag) {
/*     */     float[] realFloat;
/*     */     double[] realDouble;
/* 339 */     switch (dataType) {
/*     */       case 4:
/* 342 */         realFloat = (float[])realArg;
/* 343 */         if (imagArg != null) {
/* 344 */           float[] imagFloat = (float[])imagArg;
/* 345 */           if (offsetReal == offsetImag && strideReal == strideImag) {
/* 347 */             for (int i = 0; i < this.length; i++) {
/* 348 */               int idx = this.index[i];
/* 349 */               realFloat[offsetReal] = (float)this.real[idx];
/* 350 */               imagFloat[offsetReal] = (float)this.imag[idx];
/* 351 */               offsetReal += strideReal;
/*     */             } 
/*     */           } else {
/* 354 */             for (int i = 0; i < this.length; i++) {
/* 355 */               int idx = this.index[i];
/* 356 */               realFloat[offsetReal] = (float)this.real[idx];
/* 357 */               imagFloat[offsetImag] = (float)this.imag[idx];
/* 358 */               offsetReal += strideReal;
/* 359 */               offsetImag += strideImag;
/*     */             } 
/*     */           } 
/*     */         } else {
/* 363 */           for (int i = 0; i < this.length; i++) {
/* 364 */             realFloat[offsetReal] = (float)this.real[this.index[i]];
/* 365 */             offsetReal += strideReal;
/*     */           } 
/*     */         } 
/*     */         return;
/*     */       case 5:
/* 372 */         realDouble = (double[])realArg;
/* 373 */         if (imagArg != null) {
/* 374 */           double[] imagDouble = (double[])imagArg;
/* 375 */           if (offsetReal == offsetImag && strideReal == strideImag) {
/* 377 */             for (int i = 0; i < this.length; i++) {
/* 378 */               int idx = this.index[i];
/* 379 */               realDouble[offsetReal] = this.real[idx];
/* 380 */               imagDouble[offsetReal] = this.imag[idx];
/* 381 */               offsetReal += strideReal;
/*     */             } 
/*     */           } else {
/* 384 */             for (int i = 0; i < this.length; i++) {
/* 385 */               int idx = this.index[i];
/* 386 */               realDouble[offsetReal] = this.real[idx];
/* 387 */               imagDouble[offsetImag] = this.imag[idx];
/* 388 */               offsetReal += strideReal;
/* 389 */               offsetImag += strideImag;
/*     */             } 
/*     */           } 
/*     */         } else {
/* 393 */           for (int i = 0; i < this.length; i++) {
/* 394 */             realDouble[offsetReal] = this.real[this.index[i]];
/* 395 */             offsetReal += strideReal;
/*     */           } 
/*     */         } 
/*     */         return;
/*     */     } 
/* 406 */     throw new RuntimeException(dataType + JaiI18N.getString("FFT2"));
/*     */   }
/*     */   
/*     */   public void setFCTData(int dataType, Object data, int offset, int stride, int count) {
/*     */     float[] realFloat;
/*     */     double[] realDouble;
/*     */     int n, m, i, k, j, i1;
/* 426 */     switch (dataType) {
/*     */       case 4:
/* 429 */         realFloat = (float[])data;
/* 430 */         for (n = 0; n < count; n++) {
/* 431 */           this.imag[n] = realFloat[offset];
/* 432 */           offset += stride;
/*     */         } 
/* 434 */         for (n = count; n < this.length; n++)
/* 435 */           this.imag[n] = 0.0D; 
/* 437 */         m = this.length - 1;
/* 438 */         j = 0;
/* 439 */         for (i1 = 0; i1 < m; i1++) {
/* 440 */           this.real[i1] = this.imag[j++];
/* 441 */           this.real[m--] = this.imag[j++];
/*     */         } 
/*     */         break;
/*     */       case 5:
/* 447 */         realDouble = (double[])data;
/* 448 */         for (i = 0; i < count; i++) {
/* 449 */           this.imag[i] = realDouble[offset];
/* 450 */           offset += stride;
/*     */         } 
/* 452 */         for (i = count; i < this.length; i++)
/* 453 */           this.imag[i] = 0.0D; 
/* 455 */         k = this.length - 1;
/* 456 */         j = 0;
/* 457 */         for (i1 = 0; i1 < k; i1++) {
/* 458 */           this.real[i1] = this.imag[j++];
/* 459 */           this.real[k--] = this.imag[j++];
/*     */         } 
/*     */         break;
/*     */       default:
/* 469 */         throw new RuntimeException(dataType + JaiI18N.getString("FFT2"));
/*     */     } 
/* 473 */     Arrays.fill(this.imag, 0, this.length, 0.0D);
/*     */   }
/*     */   
/*     */   public void getFCTData(int dataType, Object data, int offset, int stride) {
/*     */     float[] realFloat;
/*     */     double[] realDouble;
/*     */     int i;
/* 490 */     if (this.wrFCT == null || this.wrFCT.length != this.length)
/* 491 */       calculateFCTLUTs(); 
/* 494 */     switch (dataType) {
/*     */       case 4:
/* 497 */         realFloat = (float[])data;
/* 498 */         for (i = 0; i < this.length; i++) {
/* 499 */           int idx = this.index[i];
/* 500 */           realFloat[offset] = (float)(this.wrFCT[i] * this.real[idx] + this.wiFCT[i] * this.imag[idx]);
/* 502 */           offset += stride;
/*     */         } 
/*     */         return;
/*     */       case 5:
/* 508 */         realDouble = (double[])data;
/* 509 */         for (i = 0; i < this.length; i++) {
/* 510 */           int idx = this.index[i];
/* 511 */           realDouble[offset] = this.wrFCT[i] * this.real[idx] + this.wiFCT[i] * this.imag[idx];
/* 513 */           offset += stride;
/*     */         } 
/*     */         return;
/*     */     } 
/* 523 */     throw new RuntimeException(dataType + JaiI18N.getString("FFT2"));
/*     */   }
/*     */   
/*     */   public void setIFCTData(int dataType, Object data, int offset, int stride, int count) {
/*     */     float[] realFloat;
/*     */     double[] realDouble;
/*     */     int i;
/* 542 */     if (this.wrFCT == null || this.wrFCT.length != this.length)
/* 543 */       calculateFCTLUTs(); 
/* 547 */     switch (dataType) {
/*     */       case 4:
/* 550 */         realFloat = (float[])data;
/* 551 */         for (i = 0; i < count; i++) {
/* 552 */           float r = realFloat[offset];
/* 553 */           this.real[i] = r * this.wrFCT[i];
/* 554 */           this.imag[i] = r * this.wiFCT[i];
/* 555 */           offset += stride;
/*     */         } 
/*     */         break;
/*     */       case 5:
/* 561 */         realDouble = (double[])data;
/* 562 */         for (i = 0; i < count; i++) {
/* 563 */           double r = realDouble[offset];
/* 564 */           this.real[i] = r * this.wrFCT[i];
/* 565 */           this.imag[i] = r * this.wiFCT[i];
/* 566 */           offset += stride;
/*     */         } 
/*     */         break;
/*     */       default:
/* 576 */         throw new RuntimeException(dataType + JaiI18N.getString("FFT2"));
/*     */     } 
/* 580 */     if (count < this.length) {
/* 581 */       Arrays.fill(this.real, count, this.length, 0.0D);
/* 582 */       Arrays.fill(this.imag, count, this.length, 0.0D);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void getIFCTData(int dataType, Object data, int offset, int stride) {
/*     */     float[] realFloat;
/*     */     double[] realDouble;
/*     */     int k;
/*     */     int i;
/* 600 */     switch (dataType) {
/*     */       case 4:
/* 603 */         realFloat = (float[])data;
/* 604 */         k = this.length - 1;
/* 605 */         for (i = 0; i < k; i++) {
/* 606 */           realFloat[offset] = (float)this.real[this.index[i]];
/* 607 */           offset += stride;
/* 608 */           realFloat[offset] = (float)this.real[this.index[k--]];
/* 609 */           offset += stride;
/*     */         } 
/*     */         return;
/*     */       case 5:
/* 615 */         realDouble = (double[])data;
/* 616 */         k = this.length - 1;
/* 617 */         for (i = 0; i < k; i++) {
/* 618 */           realDouble[offset] = (float)this.real[this.index[i]];
/* 619 */           offset += stride;
/* 620 */           realDouble[offset] = (float)this.real[this.index[k--]];
/* 621 */           offset += stride;
/*     */         } 
/*     */         return;
/*     */     } 
/* 631 */     throw new RuntimeException(dataType + JaiI18N.getString("FFT2"));
/*     */   }
/*     */   
/*     */   public void transform() {
/* 641 */     Integer i18n = new Integer(this.length);
/* 642 */     NumberFormat numberFormatter = NumberFormat.getNumberInstance(Locale.getDefault());
/* 644 */     if (this.real.length < this.length || this.imag.length < this.length)
/* 645 */       throw new RuntimeException(numberFormatter.format(i18n) + JaiI18N.getString("FFT3")); 
/* 648 */     int inode = 1;
/* 650 */     for (int l = 0; l < this.nbits; l++) {
/* 651 */       double cosp = 1.0D;
/* 652 */       double sinp = 0.0D;
/* 653 */       int ipair = 2 * inode;
/* 654 */       for (int k = 0; k < inode; k++) {
/* 655 */         for (int i = k; i < this.length; i += ipair) {
/* 656 */           int j = i + inode;
/* 657 */           int iIndex = this.index[i];
/* 658 */           int jIndex = this.index[j];
/* 659 */           double rtemp = this.real[jIndex] * cosp - this.imag[jIndex] * sinp;
/* 660 */           double itemp = this.imag[jIndex] * cosp + this.real[jIndex] * sinp;
/* 661 */           this.real[jIndex] = this.real[iIndex] - rtemp;
/* 662 */           this.imag[jIndex] = this.imag[iIndex] - itemp;
/* 663 */           this.real[iIndex] = this.real[iIndex] + rtemp;
/* 664 */           this.imag[iIndex] = this.imag[iIndex] + itemp;
/*     */         } 
/* 666 */         double costmp = cosp;
/* 667 */         cosp = cosp * this.wr[l] - sinp * this.wi[l];
/* 668 */         sinp = costmp * this.wi[l] + sinp * this.wr[l];
/*     */       } 
/* 670 */       inode *= 2;
/*     */     } 
/* 673 */     if (this.scaleFactor != 1.0D)
/* 674 */       for (int i = 0; i < this.length; i++) {
/* 675 */         this.real[i] = this.real[i] * this.scaleFactor;
/* 676 */         this.imag[i] = this.imag[i] * this.scaleFactor;
/*     */       }  
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\FFT.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */