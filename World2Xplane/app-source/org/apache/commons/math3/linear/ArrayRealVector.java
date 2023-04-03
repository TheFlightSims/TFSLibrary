/*     */ package org.apache.commons.math3.linear;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ import org.apache.commons.math3.analysis.UnivariateFunction;
/*     */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*     */ import org.apache.commons.math3.exception.MathArithmeticException;
/*     */ import org.apache.commons.math3.exception.NullArgumentException;
/*     */ import org.apache.commons.math3.exception.NumberIsTooLargeException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ import org.apache.commons.math3.util.MathUtils;
/*     */ 
/*     */ public class ArrayRealVector extends RealVector implements Serializable {
/*     */   private static final long serialVersionUID = -1097961340710804027L;
/*     */   
/*  41 */   private static final RealVectorFormat DEFAULT_FORMAT = RealVectorFormat.getInstance();
/*     */   
/*     */   private double[] data;
/*     */   
/*     */   public ArrayRealVector() {
/*  55 */     this.data = new double[0];
/*     */   }
/*     */   
/*     */   public ArrayRealVector(int size) {
/*  64 */     this.data = new double[size];
/*     */   }
/*     */   
/*     */   public ArrayRealVector(int size, double preset) {
/*  74 */     this.data = new double[size];
/*  75 */     Arrays.fill(this.data, preset);
/*     */   }
/*     */   
/*     */   public ArrayRealVector(double[] d) {
/*  85 */     this.data = (double[])d.clone();
/*     */   }
/*     */   
/*     */   public ArrayRealVector(double[] d, boolean copyArray) {
/* 103 */     if (d == null)
/* 104 */       throw new NullArgumentException(); 
/* 106 */     this.data = copyArray ? (double[])d.clone() : d;
/*     */   }
/*     */   
/*     */   public ArrayRealVector(double[] d, int pos, int size) {
/* 120 */     if (d == null)
/* 121 */       throw new NullArgumentException(); 
/* 123 */     if (d.length < pos + size)
/* 124 */       throw new NumberIsTooLargeException(Integer.valueOf(pos + size), Integer.valueOf(d.length), true); 
/* 126 */     this.data = new double[size];
/* 127 */     System.arraycopy(d, pos, this.data, 0, size);
/*     */   }
/*     */   
/*     */   public ArrayRealVector(Double[] d) {
/* 136 */     this.data = new double[d.length];
/* 137 */     for (int i = 0; i < d.length; i++)
/* 138 */       this.data[i] = d[i].doubleValue(); 
/*     */   }
/*     */   
/*     */   public ArrayRealVector(Double[] d, int pos, int size) {
/* 153 */     if (d == null)
/* 154 */       throw new NullArgumentException(); 
/* 156 */     if (d.length < pos + size)
/* 157 */       throw new NumberIsTooLargeException(Integer.valueOf(pos + size), Integer.valueOf(d.length), true); 
/* 159 */     this.data = new double[size];
/* 160 */     for (int i = pos; i < pos + size; i++)
/* 161 */       this.data[i - pos] = d[i].doubleValue(); 
/*     */   }
/*     */   
/*     */   public ArrayRealVector(RealVector v) {
/* 172 */     if (v == null)
/* 173 */       throw new NullArgumentException(); 
/* 175 */     this.data = new double[v.getDimension()];
/* 176 */     for (int i = 0; i < this.data.length; i++)
/* 177 */       this.data[i] = v.getEntry(i); 
/*     */   }
/*     */   
/*     */   public ArrayRealVector(ArrayRealVector v) {
/* 188 */     this(v, true);
/*     */   }
/*     */   
/*     */   public ArrayRealVector(ArrayRealVector v, boolean deep) {
/* 199 */     this.data = deep ? (double[])v.data.clone() : v.data;
/*     */   }
/*     */   
/*     */   public ArrayRealVector(ArrayRealVector v1, ArrayRealVector v2) {
/* 208 */     this.data = new double[v1.data.length + v2.data.length];
/* 209 */     System.arraycopy(v1.data, 0, this.data, 0, v1.data.length);
/* 210 */     System.arraycopy(v2.data, 0, this.data, v1.data.length, v2.data.length);
/*     */   }
/*     */   
/*     */   public ArrayRealVector(ArrayRealVector v1, RealVector v2) {
/* 219 */     int l1 = v1.data.length;
/* 220 */     int l2 = v2.getDimension();
/* 221 */     this.data = new double[l1 + l2];
/* 222 */     System.arraycopy(v1.data, 0, this.data, 0, l1);
/* 223 */     for (int i = 0; i < l2; i++)
/* 224 */       this.data[l1 + i] = v2.getEntry(i); 
/*     */   }
/*     */   
/*     */   public ArrayRealVector(RealVector v1, ArrayRealVector v2) {
/* 234 */     int l1 = v1.getDimension();
/* 235 */     int l2 = v2.data.length;
/* 236 */     this.data = new double[l1 + l2];
/* 237 */     for (int i = 0; i < l1; i++)
/* 238 */       this.data[i] = v1.getEntry(i); 
/* 240 */     System.arraycopy(v2.data, 0, this.data, l1, l2);
/*     */   }
/*     */   
/*     */   public ArrayRealVector(ArrayRealVector v1, double[] v2) {
/* 249 */     int l1 = v1.getDimension();
/* 250 */     int l2 = v2.length;
/* 251 */     this.data = new double[l1 + l2];
/* 252 */     System.arraycopy(v1.data, 0, this.data, 0, l1);
/* 253 */     System.arraycopy(v2, 0, this.data, l1, l2);
/*     */   }
/*     */   
/*     */   public ArrayRealVector(double[] v1, ArrayRealVector v2) {
/* 262 */     int l1 = v1.length;
/* 263 */     int l2 = v2.getDimension();
/* 264 */     this.data = new double[l1 + l2];
/* 265 */     System.arraycopy(v1, 0, this.data, 0, l1);
/* 266 */     System.arraycopy(v2.data, 0, this.data, l1, l2);
/*     */   }
/*     */   
/*     */   public ArrayRealVector(double[] v1, double[] v2) {
/* 275 */     int l1 = v1.length;
/* 276 */     int l2 = v2.length;
/* 277 */     this.data = new double[l1 + l2];
/* 278 */     System.arraycopy(v1, 0, this.data, 0, l1);
/* 279 */     System.arraycopy(v2, 0, this.data, l1, l2);
/*     */   }
/*     */   
/*     */   public ArrayRealVector copy() {
/* 285 */     return new ArrayRealVector(this, true);
/*     */   }
/*     */   
/*     */   public ArrayRealVector add(RealVector v) {
/* 291 */     if (v instanceof ArrayRealVector) {
/* 292 */       double[] vData = ((ArrayRealVector)v).data;
/* 293 */       int dim = vData.length;
/* 294 */       checkVectorDimensions(dim);
/* 295 */       ArrayRealVector result = new ArrayRealVector(dim);
/* 296 */       double[] resultData = result.data;
/* 297 */       for (int i = 0; i < dim; i++)
/* 298 */         resultData[i] = this.data[i] + vData[i]; 
/* 300 */       return result;
/*     */     } 
/* 302 */     checkVectorDimensions(v);
/* 303 */     double[] out = (double[])this.data.clone();
/* 304 */     Iterator<RealVector.Entry> it = v.sparseIterator();
/*     */     RealVector.Entry e;
/* 306 */     while (it.hasNext() && (e = it.next()) != null)
/* 307 */       out[e.getIndex()] = out[e.getIndex()] + e.getValue(); 
/* 309 */     return new ArrayRealVector(out, false);
/*     */   }
/*     */   
/*     */   public ArrayRealVector subtract(RealVector v) {
/* 316 */     if (v instanceof ArrayRealVector) {
/* 317 */       double[] vData = ((ArrayRealVector)v).data;
/* 318 */       int dim = vData.length;
/* 319 */       checkVectorDimensions(dim);
/* 320 */       ArrayRealVector result = new ArrayRealVector(dim);
/* 321 */       double[] resultData = result.data;
/* 322 */       for (int i = 0; i < dim; i++)
/* 323 */         resultData[i] = this.data[i] - vData[i]; 
/* 325 */       return result;
/*     */     } 
/* 327 */     checkVectorDimensions(v);
/* 328 */     double[] out = (double[])this.data.clone();
/* 329 */     Iterator<RealVector.Entry> it = v.sparseIterator();
/*     */     RealVector.Entry e;
/* 331 */     while (it.hasNext() && (e = it.next()) != null)
/* 332 */       out[e.getIndex()] = out[e.getIndex()] - e.getValue(); 
/* 334 */     return new ArrayRealVector(out, false);
/*     */   }
/*     */   
/*     */   public ArrayRealVector map(UnivariateFunction function) {
/* 341 */     return copy().mapToSelf(function);
/*     */   }
/*     */   
/*     */   public ArrayRealVector mapToSelf(UnivariateFunction function) {
/* 347 */     for (int i = 0; i < this.data.length; i++)
/* 348 */       this.data[i] = function.value(this.data[i]); 
/* 350 */     return this;
/*     */   }
/*     */   
/*     */   public RealVector mapAddToSelf(double d) {
/* 356 */     for (int i = 0; i < this.data.length; i++)
/* 357 */       this.data[i] = this.data[i] + d; 
/* 359 */     return this;
/*     */   }
/*     */   
/*     */   public RealVector mapSubtractToSelf(double d) {
/* 365 */     for (int i = 0; i < this.data.length; i++)
/* 366 */       this.data[i] = this.data[i] - d; 
/* 368 */     return this;
/*     */   }
/*     */   
/*     */   public RealVector mapMultiplyToSelf(double d) {
/* 374 */     for (int i = 0; i < this.data.length; i++)
/* 375 */       this.data[i] = this.data[i] * d; 
/* 377 */     return this;
/*     */   }
/*     */   
/*     */   public RealVector mapDivideToSelf(double d) {
/* 383 */     for (int i = 0; i < this.data.length; i++)
/* 384 */       this.data[i] = this.data[i] / d; 
/* 386 */     return this;
/*     */   }
/*     */   
/*     */   public ArrayRealVector ebeMultiply(RealVector v) {
/* 392 */     if (v instanceof ArrayRealVector) {
/* 393 */       double[] vData = ((ArrayRealVector)v).data;
/* 394 */       int dim = vData.length;
/* 395 */       checkVectorDimensions(dim);
/* 396 */       ArrayRealVector result = new ArrayRealVector(dim);
/* 397 */       double[] resultData = result.data;
/* 398 */       for (int j = 0; j < dim; j++)
/* 399 */         resultData[j] = this.data[j] * vData[j]; 
/* 401 */       return result;
/*     */     } 
/* 403 */     checkVectorDimensions(v);
/* 404 */     double[] out = (double[])this.data.clone();
/* 405 */     for (int i = 0; i < this.data.length; i++)
/* 406 */       out[i] = out[i] * v.getEntry(i); 
/* 408 */     return new ArrayRealVector(out, false);
/*     */   }
/*     */   
/*     */   public ArrayRealVector ebeDivide(RealVector v) {
/* 415 */     if (v instanceof ArrayRealVector) {
/* 416 */       double[] vData = ((ArrayRealVector)v).data;
/* 417 */       int dim = vData.length;
/* 418 */       checkVectorDimensions(dim);
/* 419 */       ArrayRealVector result = new ArrayRealVector(dim);
/* 420 */       double[] resultData = result.data;
/* 421 */       for (int j = 0; j < dim; j++)
/* 422 */         resultData[j] = this.data[j] / vData[j]; 
/* 424 */       return result;
/*     */     } 
/* 426 */     checkVectorDimensions(v);
/* 427 */     double[] out = (double[])this.data.clone();
/* 428 */     for (int i = 0; i < this.data.length; i++)
/* 429 */       out[i] = out[i] / v.getEntry(i); 
/* 431 */     return new ArrayRealVector(out, false);
/*     */   }
/*     */   
/*     */   public double[] getDataRef() {
/* 442 */     return this.data;
/*     */   }
/*     */   
/*     */   public double dotProduct(RealVector v) {
/* 448 */     if (v instanceof ArrayRealVector) {
/* 449 */       double[] vData = ((ArrayRealVector)v).data;
/* 450 */       checkVectorDimensions(vData.length);
/* 451 */       double d = 0.0D;
/* 452 */       for (int i = 0; i < this.data.length; i++)
/* 453 */         d += this.data[i] * vData[i]; 
/* 455 */       return d;
/*     */     } 
/* 457 */     checkVectorDimensions(v);
/* 458 */     double dot = 0.0D;
/* 459 */     Iterator<RealVector.Entry> it = v.sparseIterator();
/*     */     RealVector.Entry e;
/* 461 */     while (it.hasNext() && (e = it.next()) != null)
/* 462 */       dot += this.data[e.getIndex()] * e.getValue(); 
/* 464 */     return dot;
/*     */   }
/*     */   
/*     */   public double getNorm() {
/* 471 */     double sum = 0.0D;
/* 472 */     for (double a : this.data)
/* 473 */       sum += a * a; 
/* 475 */     return FastMath.sqrt(sum);
/*     */   }
/*     */   
/*     */   public double getL1Norm() {
/* 481 */     double sum = 0.0D;
/* 482 */     for (double a : this.data)
/* 483 */       sum += FastMath.abs(a); 
/* 485 */     return sum;
/*     */   }
/*     */   
/*     */   public double getLInfNorm() {
/* 491 */     double max = 0.0D;
/* 492 */     for (double a : this.data)
/* 493 */       max = FastMath.max(max, FastMath.abs(a)); 
/* 495 */     return max;
/*     */   }
/*     */   
/*     */   public double getDistance(RealVector v) {
/* 501 */     if (v instanceof ArrayRealVector) {
/* 502 */       double[] vData = ((ArrayRealVector)v).data;
/* 503 */       checkVectorDimensions(vData.length);
/* 504 */       double d = 0.0D;
/* 505 */       for (int j = 0; j < this.data.length; j++) {
/* 506 */         double delta = this.data[j] - vData[j];
/* 507 */         d += delta * delta;
/*     */       } 
/* 509 */       return FastMath.sqrt(d);
/*     */     } 
/* 511 */     checkVectorDimensions(v);
/* 512 */     double sum = 0.0D;
/* 513 */     for (int i = 0; i < this.data.length; i++) {
/* 514 */       double delta = this.data[i] - v.getEntry(i);
/* 515 */       sum += delta * delta;
/*     */     } 
/* 517 */     return FastMath.sqrt(sum);
/*     */   }
/*     */   
/*     */   public double getL1Distance(RealVector v) {
/* 524 */     if (v instanceof ArrayRealVector) {
/* 525 */       double[] vData = ((ArrayRealVector)v).data;
/* 526 */       checkVectorDimensions(vData.length);
/* 527 */       double d = 0.0D;
/* 528 */       for (int j = 0; j < this.data.length; j++) {
/* 529 */         double delta = this.data[j] - vData[j];
/* 530 */         d += FastMath.abs(delta);
/*     */       } 
/* 532 */       return d;
/*     */     } 
/* 534 */     checkVectorDimensions(v);
/* 535 */     double sum = 0.0D;
/* 536 */     for (int i = 0; i < this.data.length; i++) {
/* 537 */       double delta = this.data[i] - v.getEntry(i);
/* 538 */       sum += FastMath.abs(delta);
/*     */     } 
/* 540 */     return sum;
/*     */   }
/*     */   
/*     */   public double getLInfDistance(RealVector v) {
/* 547 */     if (v instanceof ArrayRealVector) {
/* 548 */       double[] vData = ((ArrayRealVector)v).data;
/* 549 */       checkVectorDimensions(vData.length);
/* 550 */       double d = 0.0D;
/* 551 */       for (int j = 0; j < this.data.length; j++) {
/* 552 */         double delta = this.data[j] - vData[j];
/* 553 */         d = FastMath.max(d, FastMath.abs(delta));
/*     */       } 
/* 555 */       return d;
/*     */     } 
/* 557 */     checkVectorDimensions(v);
/* 558 */     double max = 0.0D;
/* 559 */     for (int i = 0; i < this.data.length; i++) {
/* 560 */       double delta = this.data[i] - v.getEntry(i);
/* 561 */       max = FastMath.max(max, FastMath.abs(delta));
/*     */     } 
/* 563 */     return max;
/*     */   }
/*     */   
/*     */   public RealVector unitVector() {
/* 570 */     double norm = getNorm();
/* 571 */     if (norm == 0.0D)
/* 572 */       throw new MathArithmeticException(LocalizedFormats.ZERO_NORM, new Object[0]); 
/* 574 */     return mapDivide(norm);
/*     */   }
/*     */   
/*     */   public void unitize() {
/* 580 */     double norm = getNorm();
/* 581 */     if (norm == 0.0D)
/* 582 */       throw new MathArithmeticException(LocalizedFormats.ZERO_NORM, new Object[0]); 
/* 584 */     mapDivideToSelf(norm);
/*     */   }
/*     */   
/*     */   public RealVector projection(RealVector v) {
/* 590 */     return v.mapMultiply(dotProduct(v) / v.dotProduct(v));
/*     */   }
/*     */   
/*     */   public RealMatrix outerProduct(RealVector v) {
/* 596 */     if (v instanceof ArrayRealVector) {
/* 597 */       double[] vData = ((ArrayRealVector)v).data;
/* 598 */       int j = this.data.length;
/* 599 */       int k = vData.length;
/* 600 */       RealMatrix realMatrix = MatrixUtils.createRealMatrix(j, k);
/* 601 */       for (int i1 = 0; i1 < j; i1++) {
/* 602 */         for (int i2 = 0; i2 < k; i2++)
/* 603 */           realMatrix.setEntry(i1, i2, this.data[i1] * vData[i2]); 
/*     */       } 
/* 606 */       return realMatrix;
/*     */     } 
/* 608 */     int m = this.data.length;
/* 609 */     int n = v.getDimension();
/* 610 */     RealMatrix out = MatrixUtils.createRealMatrix(m, n);
/* 611 */     for (int i = 0; i < m; i++) {
/* 612 */       for (int j = 0; j < n; j++)
/* 613 */         out.setEntry(i, j, this.data[i] * v.getEntry(j)); 
/*     */     } 
/* 616 */     return out;
/*     */   }
/*     */   
/*     */   public double getEntry(int index) {
/* 623 */     return this.data[index];
/*     */   }
/*     */   
/*     */   public int getDimension() {
/* 629 */     return this.data.length;
/*     */   }
/*     */   
/*     */   public RealVector append(RealVector v) {
/*     */     try {
/* 636 */       return new ArrayRealVector(this, (ArrayRealVector)v);
/* 637 */     } catch (ClassCastException cce) {
/* 638 */       return new ArrayRealVector(this, v);
/*     */     } 
/*     */   }
/*     */   
/*     */   public ArrayRealVector append(ArrayRealVector v) {
/* 649 */     return new ArrayRealVector(this, v);
/*     */   }
/*     */   
/*     */   public RealVector append(double in) {
/* 655 */     double[] out = new double[this.data.length + 1];
/* 656 */     System.arraycopy(this.data, 0, out, 0, this.data.length);
/* 657 */     out[this.data.length] = in;
/* 658 */     return new ArrayRealVector(out, false);
/*     */   }
/*     */   
/*     */   public RealVector getSubVector(int index, int n) {
/* 664 */     ArrayRealVector out = new ArrayRealVector(n);
/*     */     try {
/* 666 */       System.arraycopy(this.data, index, out.data, 0, n);
/* 667 */     } catch (IndexOutOfBoundsException e) {
/* 668 */       checkIndex(index);
/* 669 */       checkIndex(index + n - 1);
/*     */     } 
/* 671 */     return out;
/*     */   }
/*     */   
/*     */   public void setEntry(int index, double value) {
/*     */     try {
/* 678 */       this.data[index] = value;
/* 679 */     } catch (IndexOutOfBoundsException e) {
/* 680 */       checkIndex(index);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addToEntry(int index, double increment) {
/* 687 */     this.data[index] = this.data[index] + increment;
/*     */   }
/*     */   
/*     */   public void setSubVector(int index, RealVector v) {
/* 693 */     if (v instanceof ArrayRealVector) {
/* 694 */       setSubVector(index, ((ArrayRealVector)v).data);
/*     */     } else {
/*     */       try {
/* 697 */         for (int i = index; i < index + v.getDimension(); i++)
/* 698 */           this.data[i] = v.getEntry(i - index); 
/* 700 */       } catch (IndexOutOfBoundsException e) {
/* 701 */         checkIndex(index);
/* 702 */         checkIndex(index + v.getDimension() - 1);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setSubVector(int index, double[] v) {
/*     */     try {
/* 717 */       System.arraycopy(v, 0, this.data, index, v.length);
/* 718 */     } catch (IndexOutOfBoundsException e) {
/* 719 */       checkIndex(index);
/* 720 */       checkIndex(index + v.length - 1);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void set(double value) {
/* 727 */     Arrays.fill(this.data, value);
/*     */   }
/*     */   
/*     */   public double[] toArray() {
/* 733 */     return (double[])this.data.clone();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 739 */     return DEFAULT_FORMAT.format(this);
/*     */   }
/*     */   
/*     */   protected void checkVectorDimensions(RealVector v) {
/* 751 */     checkVectorDimensions(v.getDimension());
/*     */   }
/*     */   
/*     */   protected void checkVectorDimensions(int n) {
/* 763 */     if (this.data.length != n)
/* 764 */       throw new DimensionMismatchException(this.data.length, n); 
/*     */   }
/*     */   
/*     */   public boolean isNaN() {
/* 776 */     for (double v : this.data) {
/* 777 */       if (Double.isNaN(v))
/* 778 */         return true; 
/*     */     } 
/* 781 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isInfinite() {
/* 793 */     if (isNaN())
/* 794 */       return false; 
/* 797 */     for (double v : this.data) {
/* 798 */       if (Double.isInfinite(v))
/* 799 */         return true; 
/*     */     } 
/* 803 */     return false;
/*     */   }
/*     */   
/*     */   public boolean equals(Object other) {
/* 822 */     if (this == other)
/* 823 */       return true; 
/* 826 */     if (other == null || !(other instanceof RealVector))
/* 827 */       return false; 
/* 830 */     RealVector rhs = (RealVector)other;
/* 831 */     if (this.data.length != rhs.getDimension())
/* 832 */       return false; 
/* 835 */     if (rhs.isNaN())
/* 836 */       return isNaN(); 
/* 839 */     for (int i = 0; i < this.data.length; i++) {
/* 840 */       if (this.data[i] != rhs.getEntry(i))
/* 841 */         return false; 
/*     */     } 
/* 844 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 855 */     if (isNaN())
/* 856 */       return 9; 
/* 858 */     return MathUtils.hash(this.data);
/*     */   }
/*     */   
/*     */   public ArrayRealVector combine(double a, double b, RealVector y) {
/* 864 */     return copy().combineToSelf(a, b, y);
/*     */   }
/*     */   
/*     */   public ArrayRealVector combineToSelf(double a, double b, RealVector y) {
/* 870 */     if (y instanceof ArrayRealVector) {
/* 871 */       double[] yData = ((ArrayRealVector)y).data;
/* 872 */       checkVectorDimensions(yData.length);
/* 873 */       for (int i = 0; i < this.data.length; i++)
/* 874 */         this.data[i] = a * this.data[i] + b * yData[i]; 
/*     */     } else {
/* 877 */       checkVectorDimensions(y);
/* 878 */       for (int i = 0; i < this.data.length; i++)
/* 879 */         this.data[i] = a * this.data[i] + b * y.getEntry(i); 
/*     */     } 
/* 882 */     return this;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\linear\ArrayRealVector.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */