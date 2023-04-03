/*     */ package org.apache.commons.math3.transform;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.lang.reflect.Array;
/*     */ import org.apache.commons.math3.analysis.FunctionUtils;
/*     */ import org.apache.commons.math3.analysis.UnivariateFunction;
/*     */ import org.apache.commons.math3.complex.Complex;
/*     */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*     */ import org.apache.commons.math3.exception.MathIllegalArgumentException;
/*     */ import org.apache.commons.math3.exception.MathIllegalStateException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.util.ArithmeticUtils;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ import org.apache.commons.math3.util.MathArrays;
/*     */ 
/*     */ public class FastFourierTransformer implements Serializable {
/*     */   static final long serialVersionUID = 20120210L;
/*     */   
/*  68 */   private static final double[] W_SUB_N_R = new double[] { 
/*  68 */       1.0D, -1.0D, 6.123233995736766E-17D, 0.7071067811865476D, 0.9238795325112867D, 0.9807852804032304D, 0.9951847266721969D, 0.9987954562051724D, 0.9996988186962042D, 0.9999247018391445D, 
/*  68 */       0.9999811752826011D, 0.9999952938095762D, 0.9999988234517019D, 0.9999997058628822D, 0.9999999264657179D, 0.9999999816164293D, 0.9999999954041073D, 0.9999999988510269D, 0.9999999997127567D, 0.9999999999281892D, 
/*  68 */       0.9999999999820472D, 0.9999999999955118D, 0.999999999998878D, 0.9999999999997194D, 0.9999999999999298D, 0.9999999999999825D, 0.9999999999999957D, 0.9999999999999989D, 0.9999999999999998D, 0.9999999999999999D, 
/*  68 */       1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 
/*  68 */       1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 
/*  68 */       1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 1.0D, 
/*  68 */       1.0D, 1.0D, 1.0D };
/*     */   
/*  91 */   private static final double[] W_SUB_N_I = new double[] { 
/*  91 */       2.4492935982947064E-16D, -1.2246467991473532E-16D, -1.0D, -0.7071067811865475D, -0.3826834323650898D, -0.19509032201612825D, -0.0980171403295606D, -0.049067674327418015D, -0.024541228522912288D, -0.012271538285719925D, 
/*  91 */       -0.006135884649154475D, -0.003067956762965976D, -0.0015339801862847655D, -7.669903187427045E-4D, -3.8349518757139556E-4D, -1.917475973107033E-4D, -9.587379909597734E-5D, -4.793689960306688E-5D, -2.396844980841822E-5D, -1.1984224905069705E-5D, 
/*  91 */       -5.9921124526424275E-6D, -2.996056226334661E-6D, -1.4980281131690111E-6D, -7.490140565847157E-7D, -3.7450702829238413E-7D, -1.8725351414619535E-7D, -9.362675707309808E-8D, -4.681337853654909E-8D, -2.340668926827455E-8D, -1.1703344634137277E-8D, 
/*  91 */       -5.8516723170686385E-9D, -2.9258361585343192E-9D, -1.4629180792671596E-9D, -7.314590396335798E-10D, -3.657295198167899E-10D, -1.8286475990839495E-10D, -9.143237995419748E-11D, -4.571618997709874E-11D, -2.285809498854937E-11D, -1.1429047494274685E-11D, 
/*  91 */       -5.714523747137342E-12D, -2.857261873568671E-12D, -1.4286309367843356E-12D, -7.143154683921678E-13D, -3.571577341960839E-13D, -1.7857886709804195E-13D, -8.928943354902097E-14D, -4.4644716774510487E-14D, -2.2322358387255243E-14D, -1.1161179193627622E-14D, 
/*  91 */       -5.580589596813811E-15D, -2.7902947984069054E-15D, -1.3951473992034527E-15D, -6.975736996017264E-16D, -3.487868498008632E-16D, -1.743934249004316E-16D, -8.71967124502158E-17D, -4.35983562251079E-17D, -2.179917811255395E-17D, -1.0899589056276974E-17D, 
/*  91 */       -5.449794528138487E-18D, -2.7248972640692436E-18D, -1.3624486320346218E-18D };
/*     */   
/*     */   private final DftNormalization normalization;
/*     */   
/*     */   public FastFourierTransformer(DftNormalization normalization) {
/* 120 */     this.normalization = normalization;
/*     */   }
/*     */   
/*     */   private static void bitReversalShuffle2(double[] a, double[] b) {
/* 134 */     int n = a.length;
/* 135 */     assert b.length == n;
/* 136 */     int halfOfN = n >> 1;
/* 138 */     int j = 0;
/* 139 */     for (int i = 0; i < n; i++) {
/* 140 */       if (i < j) {
/* 142 */         double temp = a[i];
/* 143 */         a[i] = a[j];
/* 144 */         a[j] = temp;
/* 146 */         temp = b[i];
/* 147 */         b[i] = b[j];
/* 148 */         b[j] = temp;
/*     */       } 
/* 151 */       int k = halfOfN;
/* 152 */       while (k <= j && k > 0) {
/* 153 */         j -= k;
/* 154 */         k >>= 1;
/*     */       } 
/* 156 */       j += k;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void normalizeTransformedData(double[][] dataRI, DftNormalization normalization, TransformType type) {
/*     */     double scaleFactor;
/*     */     int i;
/* 171 */     double[] dataR = dataRI[0];
/* 172 */     double[] dataI = dataRI[1];
/* 173 */     int n = dataR.length;
/* 174 */     assert dataI.length == n;
/* 176 */     switch (normalization) {
/*     */       case STANDARD:
/* 178 */         if (type == TransformType.INVERSE) {
/* 179 */           double d = 1.0D / n;
/* 180 */           for (int j = 0; j < n; j++) {
/* 181 */             dataR[j] = dataR[j] * d;
/* 182 */             dataI[j] = dataI[j] * d;
/*     */           } 
/*     */         } 
/*     */         return;
/*     */       case UNITARY:
/* 187 */         scaleFactor = 1.0D / FastMath.sqrt(n);
/* 188 */         for (i = 0; i < n; i++) {
/* 189 */           dataR[i] = dataR[i] * scaleFactor;
/* 190 */           dataI[i] = dataI[i] * scaleFactor;
/*     */         } 
/*     */         return;
/*     */     } 
/* 200 */     throw new MathIllegalStateException();
/*     */   }
/*     */   
/*     */   public static void transformInPlace(double[][] dataRI, DftNormalization normalization, TransformType type) {
/* 227 */     if (dataRI.length != 2)
/* 228 */       throw new DimensionMismatchException(dataRI.length, 2); 
/* 230 */     double[] dataR = dataRI[0];
/* 231 */     double[] dataI = dataRI[1];
/* 232 */     if (dataR.length != dataI.length)
/* 233 */       throw new DimensionMismatchException(dataI.length, dataR.length); 
/* 236 */     int n = dataR.length;
/* 237 */     if (!ArithmeticUtils.isPowerOfTwo(n))
/* 238 */       throw new MathIllegalArgumentException(LocalizedFormats.NOT_POWER_OF_TWO_CONSIDER_PADDING, new Object[] { Integer.valueOf(n) }); 
/* 243 */     if (n == 1)
/*     */       return; 
/* 245 */     if (n == 2) {
/* 246 */       double srcR0 = dataR[0];
/* 247 */       double srcI0 = dataI[0];
/* 248 */       double srcR1 = dataR[1];
/* 249 */       double srcI1 = dataI[1];
/* 252 */       dataR[0] = srcR0 + srcR1;
/* 253 */       dataI[0] = srcI0 + srcI1;
/* 255 */       dataR[1] = srcR0 - srcR1;
/* 256 */       dataI[1] = srcI0 - srcI1;
/* 258 */       normalizeTransformedData(dataRI, normalization, type);
/*     */       return;
/*     */     } 
/* 262 */     bitReversalShuffle2(dataR, dataI);
/* 265 */     if (type == TransformType.INVERSE) {
/* 266 */       for (int i0 = 0; i0 < n; i0 += 4) {
/* 267 */         int i1 = i0 + 1;
/* 268 */         int i2 = i0 + 2;
/* 269 */         int i3 = i0 + 3;
/* 271 */         double srcR0 = dataR[i0];
/* 272 */         double srcI0 = dataI[i0];
/* 273 */         double srcR1 = dataR[i2];
/* 274 */         double srcI1 = dataI[i2];
/* 275 */         double srcR2 = dataR[i1];
/* 276 */         double srcI2 = dataI[i1];
/* 277 */         double srcR3 = dataR[i3];
/* 278 */         double srcI3 = dataI[i3];
/* 282 */         dataR[i0] = srcR0 + srcR1 + srcR2 + srcR3;
/* 283 */         dataI[i0] = srcI0 + srcI1 + srcI2 + srcI3;
/* 285 */         dataR[i1] = srcR0 - srcR2 + srcI3 - srcI1;
/* 286 */         dataI[i1] = srcI0 - srcI2 + srcR1 - srcR3;
/* 288 */         dataR[i2] = srcR0 - srcR1 + srcR2 - srcR3;
/* 289 */         dataI[i2] = srcI0 - srcI1 + srcI2 - srcI3;
/* 291 */         dataR[i3] = srcR0 - srcR2 + srcI1 - srcI3;
/* 292 */         dataI[i3] = srcI0 - srcI2 + srcR3 - srcR1;
/*     */       } 
/*     */     } else {
/* 295 */       for (int i0 = 0; i0 < n; i0 += 4) {
/* 296 */         int i1 = i0 + 1;
/* 297 */         int i2 = i0 + 2;
/* 298 */         int i3 = i0 + 3;
/* 300 */         double srcR0 = dataR[i0];
/* 301 */         double srcI0 = dataI[i0];
/* 302 */         double srcR1 = dataR[i2];
/* 303 */         double srcI1 = dataI[i2];
/* 304 */         double srcR2 = dataR[i1];
/* 305 */         double srcI2 = dataI[i1];
/* 306 */         double srcR3 = dataR[i3];
/* 307 */         double srcI3 = dataI[i3];
/* 311 */         dataR[i0] = srcR0 + srcR1 + srcR2 + srcR3;
/* 312 */         dataI[i0] = srcI0 + srcI1 + srcI2 + srcI3;
/* 314 */         dataR[i1] = srcR0 - srcR2 + srcI1 - srcI3;
/* 315 */         dataI[i1] = srcI0 - srcI2 + srcR3 - srcR1;
/* 317 */         dataR[i2] = srcR0 - srcR1 + srcR2 - srcR3;
/* 318 */         dataI[i2] = srcI0 - srcI1 + srcI2 - srcI3;
/* 320 */         dataR[i3] = srcR0 - srcR2 + srcI3 - srcI1;
/* 321 */         dataI[i3] = srcI0 - srcI2 + srcR1 - srcR3;
/*     */       } 
/*     */     } 
/* 325 */     int lastN0 = 4;
/* 326 */     int lastLogN0 = 2;
/* 327 */     while (lastN0 < n) {
/* 328 */       int n0 = lastN0 << 1;
/* 329 */       int logN0 = lastLogN0 + 1;
/* 330 */       double wSubN0R = W_SUB_N_R[logN0];
/* 331 */       double wSubN0I = W_SUB_N_I[logN0];
/* 332 */       if (type == TransformType.INVERSE)
/* 333 */         wSubN0I = -wSubN0I; 
/*     */       int destEvenStartIndex;
/* 337 */       for (destEvenStartIndex = 0; destEvenStartIndex < n; destEvenStartIndex += n0) {
/* 338 */         int destOddStartIndex = destEvenStartIndex + lastN0;
/* 340 */         double wSubN0ToRR = 1.0D;
/* 341 */         double wSubN0ToRI = 0.0D;
/* 343 */         for (int r = 0; r < lastN0; r++) {
/* 344 */           double grR = dataR[destEvenStartIndex + r];
/* 345 */           double grI = dataI[destEvenStartIndex + r];
/* 346 */           double hrR = dataR[destOddStartIndex + r];
/* 347 */           double hrI = dataI[destOddStartIndex + r];
/* 350 */           dataR[destEvenStartIndex + r] = grR + wSubN0ToRR * hrR - wSubN0ToRI * hrI;
/* 351 */           dataI[destEvenStartIndex + r] = grI + wSubN0ToRR * hrI + wSubN0ToRI * hrR;
/* 353 */           dataR[destOddStartIndex + r] = grR - wSubN0ToRR * hrR - wSubN0ToRI * hrI;
/* 354 */           dataI[destOddStartIndex + r] = grI - wSubN0ToRR * hrI + wSubN0ToRI * hrR;
/* 357 */           double nextWsubN0ToRR = wSubN0ToRR * wSubN0R - wSubN0ToRI * wSubN0I;
/* 358 */           double nextWsubN0ToRI = wSubN0ToRR * wSubN0I + wSubN0ToRI * wSubN0R;
/* 359 */           wSubN0ToRR = nextWsubN0ToRR;
/* 360 */           wSubN0ToRI = nextWsubN0ToRI;
/*     */         } 
/*     */       } 
/* 364 */       lastN0 = n0;
/* 365 */       lastLogN0 = logN0;
/*     */     } 
/* 368 */     normalizeTransformedData(dataRI, normalization, type);
/*     */   }
/*     */   
/*     */   public Complex[] transform(double[] f, TransformType type) {
/* 381 */     double[][] dataRI = { MathArrays.copyOf(f, f.length), new double[f.length] };
/* 385 */     transformInPlace(dataRI, this.normalization, type);
/* 387 */     return TransformUtils.createComplexArray(dataRI);
/*     */   }
/*     */   
/*     */   public Complex[] transform(UnivariateFunction f, double min, double max, int n, TransformType type) {
/* 411 */     double[] data = FunctionUtils.sample(f, min, max, n);
/* 412 */     return transform(data, type);
/*     */   }
/*     */   
/*     */   public Complex[] transform(Complex[] f, TransformType type) {
/* 426 */     double[][] dataRI = TransformUtils.createRealImaginaryArray(f);
/* 428 */     transformInPlace(dataRI, this.normalization, type);
/* 430 */     return TransformUtils.createComplexArray(dataRI);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public Object mdfft(Object mdca, TransformType type) {
/* 452 */     MultiDimensionalComplexMatrix mdcm = (MultiDimensionalComplexMatrix)(new MultiDimensionalComplexMatrix(mdca)).clone();
/* 454 */     int[] dimensionSize = mdcm.getDimensionSizes();
/* 456 */     for (int i = 0; i < dimensionSize.length; i++)
/* 457 */       mdfft(mdcm, type, i, new int[0]); 
/* 459 */     return mdcm.getArray();
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   private void mdfft(MultiDimensionalComplexMatrix mdcm, TransformType type, int d, int[] subVector) {
/* 476 */     int[] dimensionSize = mdcm.getDimensionSizes();
/* 478 */     if (subVector.length == dimensionSize.length) {
/* 479 */       Complex[] temp = new Complex[dimensionSize[d]];
/*     */       int i;
/* 480 */       for (i = 0; i < dimensionSize[d]; i++) {
/* 482 */         subVector[d] = i;
/* 483 */         temp[i] = mdcm.get(subVector);
/*     */       } 
/* 486 */       temp = transform(temp, type);
/* 488 */       for (i = 0; i < dimensionSize[d]; i++) {
/* 489 */         subVector[d] = i;
/* 490 */         mdcm.set(temp[i], subVector);
/*     */       } 
/*     */     } else {
/* 493 */       int[] vector = new int[subVector.length + 1];
/* 494 */       System.arraycopy(subVector, 0, vector, 0, subVector.length);
/* 495 */       if (subVector.length == d) {
/* 498 */         vector[d] = 0;
/* 499 */         mdfft(mdcm, type, d, vector);
/*     */       } else {
/* 501 */         for (int i = 0; i < dimensionSize[subVector.length]; i++) {
/* 502 */           vector[subVector.length] = i;
/* 504 */           mdfft(mdcm, type, d, vector);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   private static class MultiDimensionalComplexMatrix implements Cloneable {
/*     */     protected int[] dimensionSize;
/*     */     
/*     */     protected Object multiDimensionalComplexArray;
/*     */     
/*     */     public MultiDimensionalComplexMatrix(Object multiDimensionalComplexArray) {
/* 538 */       this.multiDimensionalComplexArray = multiDimensionalComplexArray;
/* 541 */       int numOfDimensions = 0;
/* 542 */       Object lastDimension = multiDimensionalComplexArray;
/* 543 */       while (lastDimension instanceof Object[]) {
/* 544 */         Object[] array = (Object[])lastDimension;
/* 545 */         numOfDimensions++;
/* 546 */         lastDimension = array[0];
/*     */       } 
/* 550 */       this.dimensionSize = new int[numOfDimensions];
/* 553 */       numOfDimensions = 0;
/* 554 */       lastDimension = multiDimensionalComplexArray;
/* 555 */       while (lastDimension instanceof Object[]) {
/* 556 */         Object[] array = (Object[])lastDimension;
/* 557 */         this.dimensionSize[numOfDimensions++] = array.length;
/* 558 */         lastDimension = array[0];
/*     */       } 
/*     */     }
/*     */     
/*     */     public Complex get(int... vector) throws DimensionMismatchException {
/* 573 */       if (vector == null) {
/* 574 */         if (this.dimensionSize.length > 0)
/* 575 */           throw new DimensionMismatchException(0, this.dimensionSize.length); 
/* 579 */         return null;
/*     */       } 
/* 581 */       if (vector.length != this.dimensionSize.length)
/* 582 */         throw new DimensionMismatchException(vector.length, this.dimensionSize.length); 
/* 587 */       Object lastDimension = this.multiDimensionalComplexArray;
/* 589 */       for (int i = 0; i < this.dimensionSize.length; i++)
/* 590 */         lastDimension = ((Object[])lastDimension)[vector[i]]; 
/* 592 */       return (Complex)lastDimension;
/*     */     }
/*     */     
/*     */     public Complex set(Complex magnitude, int... vector) throws DimensionMismatchException {
/* 606 */       if (vector == null) {
/* 607 */         if (this.dimensionSize.length > 0)
/* 608 */           throw new DimensionMismatchException(0, this.dimensionSize.length); 
/* 612 */         return null;
/*     */       } 
/* 614 */       if (vector.length != this.dimensionSize.length)
/* 615 */         throw new DimensionMismatchException(vector.length, this.dimensionSize.length); 
/* 620 */       Object[] lastDimension = (Object[])this.multiDimensionalComplexArray;
/* 621 */       for (int i = 0; i < this.dimensionSize.length - 1; i++)
/* 622 */         lastDimension = (Object[])lastDimension[vector[i]]; 
/* 625 */       Complex lastValue = (Complex)lastDimension[vector[this.dimensionSize.length - 1]];
/* 626 */       lastDimension[vector[this.dimensionSize.length - 1]] = magnitude;
/* 628 */       return lastValue;
/*     */     }
/*     */     
/*     */     public int[] getDimensionSizes() {
/* 637 */       return (int[])this.dimensionSize.clone();
/*     */     }
/*     */     
/*     */     public Object getArray() {
/* 646 */       return this.multiDimensionalComplexArray;
/*     */     }
/*     */     
/*     */     public Object clone() {
/* 652 */       MultiDimensionalComplexMatrix mdcm = new MultiDimensionalComplexMatrix(Array.newInstance(Complex.class, this.dimensionSize));
/* 655 */       clone(mdcm);
/* 656 */       return mdcm;
/*     */     }
/*     */     
/*     */     private void clone(MultiDimensionalComplexMatrix mdcm) {
/* 666 */       int[] vector = new int[this.dimensionSize.length];
/* 667 */       int size = 1;
/* 668 */       for (int i = 0; i < this.dimensionSize.length; i++)
/* 669 */         size *= this.dimensionSize[i]; 
/* 671 */       int[][] vectorList = new int[size][this.dimensionSize.length];
/* 672 */       for (int[] nextVector : vectorList) {
/* 673 */         System.arraycopy(vector, 0, nextVector, 0, this.dimensionSize.length);
/* 675 */         for (int j = 0; j < this.dimensionSize.length; j++) {
/* 676 */           vector[j] = vector[j] + 1;
/* 677 */           if (vector[j] < this.dimensionSize[j])
/*     */             break; 
/* 680 */           vector[j] = 0;
/*     */         } 
/*     */       } 
/* 685 */       for (int[] nextVector : vectorList)
/* 686 */         mdcm.set(get(nextVector), nextVector); 
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\transform\FastFourierTransformer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */