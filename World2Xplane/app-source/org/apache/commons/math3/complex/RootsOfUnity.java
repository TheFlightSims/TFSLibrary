/*     */ package org.apache.commons.math3.complex;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.math3.exception.MathIllegalArgumentException;
/*     */ import org.apache.commons.math3.exception.MathIllegalStateException;
/*     */ import org.apache.commons.math3.exception.OutOfRangeException;
/*     */ import org.apache.commons.math3.exception.ZeroException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class RootsOfUnity implements Serializable {
/*     */   private static final long serialVersionUID = 20120201L;
/*     */   
/*  71 */   private int omegaCount = 0;
/*     */   
/*  72 */   private double[] omegaReal = null;
/*     */   
/*  73 */   private double[] omegaImaginaryCounterClockwise = null;
/*     */   
/*  74 */   private double[] omegaImaginaryClockwise = null;
/*     */   
/*     */   private boolean isCounterClockWise = true;
/*     */   
/*     */   public synchronized boolean isCounterClockWise() throws MathIllegalStateException {
/*  91 */     if (this.omegaCount == 0)
/*  92 */       throw new MathIllegalStateException(LocalizedFormats.ROOTS_OF_UNITY_NOT_COMPUTED_YET, new Object[0]); 
/*  95 */     return this.isCounterClockWise;
/*     */   }
/*     */   
/*     */   public synchronized void computeRoots(int n) throws ZeroException {
/* 119 */     if (n == 0)
/* 120 */       throw new ZeroException(LocalizedFormats.CANNOT_COMPUTE_0TH_ROOT_OF_UNITY, new Object[0]); 
/* 124 */     this.isCounterClockWise = (n > 0);
/* 127 */     int absN = FastMath.abs(n);
/* 129 */     if (absN == this.omegaCount)
/*     */       return; 
/* 134 */     double t = 6.283185307179586D / absN;
/* 135 */     double cosT = FastMath.cos(t);
/* 136 */     double sinT = FastMath.sin(t);
/* 137 */     this.omegaReal = new double[absN];
/* 138 */     this.omegaImaginaryCounterClockwise = new double[absN];
/* 139 */     this.omegaImaginaryClockwise = new double[absN];
/* 140 */     this.omegaReal[0] = 1.0D;
/* 141 */     this.omegaImaginaryCounterClockwise[0] = 0.0D;
/* 142 */     this.omegaImaginaryClockwise[0] = 0.0D;
/* 143 */     for (int i = 1; i < absN; i++) {
/* 144 */       this.omegaReal[i] = this.omegaReal[i - 1] * cosT - this.omegaImaginaryCounterClockwise[i - 1] * sinT;
/* 146 */       this.omegaImaginaryCounterClockwise[i] = this.omegaReal[i - 1] * sinT + this.omegaImaginaryCounterClockwise[i - 1] * cosT;
/* 148 */       this.omegaImaginaryClockwise[i] = -this.omegaImaginaryCounterClockwise[i];
/*     */     } 
/* 150 */     this.omegaCount = absN;
/*     */   }
/*     */   
/*     */   public synchronized double getReal(int k) throws MathIllegalStateException, MathIllegalArgumentException {
/* 165 */     if (this.omegaCount == 0)
/* 166 */       throw new MathIllegalStateException(LocalizedFormats.ROOTS_OF_UNITY_NOT_COMPUTED_YET, new Object[0]); 
/* 169 */     if (k < 0 || k >= this.omegaCount)
/* 170 */       throw new OutOfRangeException(LocalizedFormats.OUT_OF_RANGE_ROOT_OF_UNITY_INDEX, Integer.valueOf(k), Integer.valueOf(0), Integer.valueOf(this.omegaCount - 1)); 
/* 177 */     return this.omegaReal[k];
/*     */   }
/*     */   
/*     */   public synchronized double getImaginary(int k) throws MathIllegalStateException, OutOfRangeException {
/* 192 */     if (this.omegaCount == 0)
/* 193 */       throw new MathIllegalStateException(LocalizedFormats.ROOTS_OF_UNITY_NOT_COMPUTED_YET, new Object[0]); 
/* 196 */     if (k < 0 || k >= this.omegaCount)
/* 197 */       throw new OutOfRangeException(LocalizedFormats.OUT_OF_RANGE_ROOT_OF_UNITY_INDEX, Integer.valueOf(k), Integer.valueOf(0), Integer.valueOf(this.omegaCount - 1)); 
/* 204 */     return this.isCounterClockWise ? this.omegaImaginaryCounterClockwise[k] : this.omegaImaginaryClockwise[k];
/*     */   }
/*     */   
/*     */   public synchronized int getNumberOfRoots() {
/* 217 */     return this.omegaCount;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\complex\RootsOfUnity.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */