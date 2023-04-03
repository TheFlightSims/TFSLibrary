/*     */ package org.apache.commons.math3.linear;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Iterator;
/*     */ import org.apache.commons.math3.exception.MathArithmeticException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ import org.apache.commons.math3.util.OpenIntToDoubleHashMap;
/*     */ 
/*     */ public class OpenMapRealVector extends SparseRealVector implements Serializable {
/*     */   public static final double DEFAULT_ZERO_TOLERANCE = 1.0E-12D;
/*     */   
/*     */   private static final long serialVersionUID = 8772222695580707260L;
/*     */   
/*     */   private final OpenIntToDoubleHashMap entries;
/*     */   
/*     */   private final int virtualSize;
/*     */   
/*     */   private final double epsilon;
/*     */   
/*     */   public OpenMapRealVector() {
/*  55 */     this(0, 1.0E-12D);
/*     */   }
/*     */   
/*     */   public OpenMapRealVector(int dimension) {
/*  64 */     this(dimension, 1.0E-12D);
/*     */   }
/*     */   
/*     */   public OpenMapRealVector(int dimension, double epsilon) {
/*  74 */     this.virtualSize = dimension;
/*  75 */     this.entries = new OpenIntToDoubleHashMap(0.0D);
/*  76 */     this.epsilon = epsilon;
/*     */   }
/*     */   
/*     */   protected OpenMapRealVector(OpenMapRealVector v, int resize) {
/*  86 */     this.virtualSize = v.getDimension() + resize;
/*  87 */     this.entries = new OpenIntToDoubleHashMap(v.entries);
/*  88 */     this.epsilon = v.epsilon;
/*     */   }
/*     */   
/*     */   public OpenMapRealVector(int dimension, int expectedSize) {
/*  98 */     this(dimension, expectedSize, 1.0E-12D);
/*     */   }
/*     */   
/*     */   public OpenMapRealVector(int dimension, int expectedSize, double epsilon) {
/* 110 */     this.virtualSize = dimension;
/* 111 */     this.entries = new OpenIntToDoubleHashMap(expectedSize, 0.0D);
/* 112 */     this.epsilon = epsilon;
/*     */   }
/*     */   
/*     */   public OpenMapRealVector(double[] values) {
/* 122 */     this(values, 1.0E-12D);
/*     */   }
/*     */   
/*     */   public OpenMapRealVector(double[] values, double epsilon) {
/* 133 */     this.virtualSize = values.length;
/* 134 */     this.entries = new OpenIntToDoubleHashMap(0.0D);
/* 135 */     this.epsilon = epsilon;
/* 136 */     for (int key = 0; key < values.length; key++) {
/* 137 */       double value = values[key];
/* 138 */       if (!isDefaultValue(value))
/* 139 */         this.entries.put(key, value); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public OpenMapRealVector(Double[] values) {
/* 151 */     this(values, 1.0E-12D);
/*     */   }
/*     */   
/*     */   public OpenMapRealVector(Double[] values, double epsilon) {
/* 162 */     this.virtualSize = values.length;
/* 163 */     this.entries = new OpenIntToDoubleHashMap(0.0D);
/* 164 */     this.epsilon = epsilon;
/* 165 */     for (int key = 0; key < values.length; key++) {
/* 166 */       double value = values[key].doubleValue();
/* 167 */       if (!isDefaultValue(value))
/* 168 */         this.entries.put(key, value); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public OpenMapRealVector(OpenMapRealVector v) {
/* 179 */     this.virtualSize = v.getDimension();
/* 180 */     this.entries = new OpenIntToDoubleHashMap(v.getEntries());
/* 181 */     this.epsilon = v.epsilon;
/*     */   }
/*     */   
/*     */   public OpenMapRealVector(RealVector v) {
/* 190 */     this.virtualSize = v.getDimension();
/* 191 */     this.entries = new OpenIntToDoubleHashMap(0.0D);
/* 192 */     this.epsilon = 1.0E-12D;
/* 193 */     for (int key = 0; key < this.virtualSize; key++) {
/* 194 */       double value = v.getEntry(key);
/* 195 */       if (!isDefaultValue(value))
/* 196 */         this.entries.put(key, value); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private OpenIntToDoubleHashMap getEntries() {
/* 207 */     return this.entries;
/*     */   }
/*     */   
/*     */   protected boolean isDefaultValue(double value) {
/* 219 */     return (FastMath.abs(value) < this.epsilon);
/*     */   }
/*     */   
/*     */   public RealVector add(RealVector v) {
/* 225 */     checkVectorDimensions(v.getDimension());
/* 226 */     if (v instanceof OpenMapRealVector)
/* 227 */       return add((OpenMapRealVector)v); 
/* 229 */     return super.add(v);
/*     */   }
/*     */   
/*     */   public OpenMapRealVector add(OpenMapRealVector v) {
/* 243 */     checkVectorDimensions(v.getDimension());
/* 244 */     boolean copyThis = (this.entries.size() > v.entries.size());
/* 245 */     OpenMapRealVector res = copyThis ? copy() : v.copy();
/* 246 */     OpenIntToDoubleHashMap.Iterator iter = copyThis ? v.entries.iterator() : this.entries.iterator();
/* 247 */     OpenIntToDoubleHashMap randomAccess = copyThis ? this.entries : v.entries;
/* 248 */     while (iter.hasNext()) {
/* 249 */       iter.advance();
/* 250 */       int key = iter.key();
/* 251 */       if (randomAccess.containsKey(key)) {
/* 252 */         res.setEntry(key, randomAccess.get(key) + iter.value());
/*     */         continue;
/*     */       } 
/* 254 */       res.setEntry(key, iter.value());
/*     */     } 
/* 257 */     return res;
/*     */   }
/*     */   
/*     */   public OpenMapRealVector append(OpenMapRealVector v) {
/* 266 */     OpenMapRealVector res = new OpenMapRealVector(this, v.getDimension());
/* 267 */     OpenIntToDoubleHashMap.Iterator iter = v.entries.iterator();
/* 268 */     while (iter.hasNext()) {
/* 269 */       iter.advance();
/* 270 */       res.setEntry(iter.key() + this.virtualSize, iter.value());
/*     */     } 
/* 272 */     return res;
/*     */   }
/*     */   
/*     */   public OpenMapRealVector append(RealVector v) {
/* 278 */     if (v instanceof OpenMapRealVector)
/* 279 */       return append((OpenMapRealVector)v); 
/* 281 */     OpenMapRealVector res = new OpenMapRealVector(this, v.getDimension());
/* 282 */     for (int i = 0; i < v.getDimension(); i++)
/* 283 */       res.setEntry(i + this.virtualSize, v.getEntry(i)); 
/* 285 */     return res;
/*     */   }
/*     */   
/*     */   public OpenMapRealVector append(double d) {
/* 292 */     OpenMapRealVector res = new OpenMapRealVector(this, 1);
/* 293 */     res.setEntry(this.virtualSize, d);
/* 294 */     return res;
/*     */   }
/*     */   
/*     */   public OpenMapRealVector copy() {
/* 303 */     return new OpenMapRealVector(this);
/*     */   }
/*     */   
/*     */   public double dotProduct(OpenMapRealVector v) {
/* 316 */     checkVectorDimensions(v.getDimension());
/* 317 */     boolean thisIsSmaller = (this.entries.size() < v.entries.size());
/* 318 */     OpenIntToDoubleHashMap.Iterator iter = thisIsSmaller ? this.entries.iterator() : v.entries.iterator();
/* 319 */     OpenIntToDoubleHashMap larger = thisIsSmaller ? v.entries : this.entries;
/* 320 */     double d = 0.0D;
/* 321 */     while (iter.hasNext()) {
/* 322 */       iter.advance();
/* 323 */       d += iter.value() * larger.get(iter.key());
/*     */     } 
/* 325 */     return d;
/*     */   }
/*     */   
/*     */   public double dotProduct(RealVector v) {
/* 331 */     if (v instanceof OpenMapRealVector)
/* 332 */       return dotProduct((OpenMapRealVector)v); 
/* 334 */     return super.dotProduct(v);
/*     */   }
/*     */   
/*     */   public OpenMapRealVector ebeDivide(RealVector v) {
/* 341 */     checkVectorDimensions(v.getDimension());
/* 342 */     OpenMapRealVector res = new OpenMapRealVector(this);
/* 343 */     OpenIntToDoubleHashMap.Iterator iter = this.entries.iterator();
/* 344 */     while (iter.hasNext()) {
/* 345 */       iter.advance();
/* 346 */       res.setEntry(iter.key(), iter.value() / v.getEntry(iter.key()));
/*     */     } 
/* 348 */     return res;
/*     */   }
/*     */   
/*     */   public OpenMapRealVector ebeMultiply(RealVector v) {
/* 354 */     checkVectorDimensions(v.getDimension());
/* 355 */     OpenMapRealVector res = new OpenMapRealVector(this);
/* 356 */     OpenIntToDoubleHashMap.Iterator iter = this.entries.iterator();
/* 357 */     while (iter.hasNext()) {
/* 358 */       iter.advance();
/* 359 */       res.setEntry(iter.key(), iter.value() * v.getEntry(iter.key()));
/*     */     } 
/* 361 */     return res;
/*     */   }
/*     */   
/*     */   public OpenMapRealVector getSubVector(int index, int n) {
/* 367 */     checkIndex(index);
/* 368 */     checkIndex(index + n - 1);
/* 369 */     OpenMapRealVector res = new OpenMapRealVector(n);
/* 370 */     int end = index + n;
/* 371 */     OpenIntToDoubleHashMap.Iterator iter = this.entries.iterator();
/* 372 */     while (iter.hasNext()) {
/* 373 */       iter.advance();
/* 374 */       int key = iter.key();
/* 375 */       if (key >= index && key < end)
/* 376 */         res.setEntry(key - index, iter.value()); 
/*     */     } 
/* 379 */     return res;
/*     */   }
/*     */   
/*     */   public int getDimension() {
/* 385 */     return this.virtualSize;
/*     */   }
/*     */   
/*     */   public double getDistance(OpenMapRealVector v) {
/* 397 */     OpenIntToDoubleHashMap.Iterator iter = this.entries.iterator();
/* 398 */     double res = 0.0D;
/* 399 */     while (iter.hasNext()) {
/* 400 */       iter.advance();
/* 401 */       int key = iter.key();
/* 403 */       double delta = iter.value() - v.getEntry(key);
/* 404 */       res += delta * delta;
/*     */     } 
/* 406 */     iter = v.getEntries().iterator();
/* 407 */     while (iter.hasNext()) {
/* 408 */       iter.advance();
/* 409 */       int key = iter.key();
/* 410 */       if (!this.entries.containsKey(key)) {
/* 411 */         double value = iter.value();
/* 412 */         res += value * value;
/*     */       } 
/*     */     } 
/* 415 */     return FastMath.sqrt(res);
/*     */   }
/*     */   
/*     */   public double getDistance(RealVector v) {
/* 421 */     checkVectorDimensions(v.getDimension());
/* 422 */     if (v instanceof OpenMapRealVector)
/* 423 */       return getDistance((OpenMapRealVector)v); 
/* 425 */     return super.getDistance(v);
/*     */   }
/*     */   
/*     */   public double getEntry(int index) {
/* 432 */     checkIndex(index);
/* 433 */     return this.entries.get(index);
/*     */   }
/*     */   
/*     */   public double getL1Distance(OpenMapRealVector v) {
/* 446 */     double max = 0.0D;
/* 447 */     OpenIntToDoubleHashMap.Iterator iter = this.entries.iterator();
/* 448 */     while (iter.hasNext()) {
/* 449 */       iter.advance();
/* 450 */       double delta = FastMath.abs(iter.value() - v.getEntry(iter.key()));
/* 451 */       max += delta;
/*     */     } 
/* 453 */     iter = v.getEntries().iterator();
/* 454 */     while (iter.hasNext()) {
/* 455 */       iter.advance();
/* 456 */       int key = iter.key();
/* 457 */       if (!this.entries.containsKey(key)) {
/* 458 */         double delta = FastMath.abs(iter.value());
/* 459 */         max += FastMath.abs(delta);
/*     */       } 
/*     */     } 
/* 462 */     return max;
/*     */   }
/*     */   
/*     */   public double getL1Distance(RealVector v) {
/* 468 */     checkVectorDimensions(v.getDimension());
/* 469 */     if (v instanceof OpenMapRealVector)
/* 470 */       return getL1Distance((OpenMapRealVector)v); 
/* 472 */     return super.getL1Distance(v);
/*     */   }
/*     */   
/*     */   private double getLInfDistance(OpenMapRealVector v) {
/* 483 */     double max = 0.0D;
/* 484 */     OpenIntToDoubleHashMap.Iterator iter = this.entries.iterator();
/* 485 */     while (iter.hasNext()) {
/* 486 */       iter.advance();
/* 487 */       double delta = FastMath.abs(iter.value() - v.getEntry(iter.key()));
/* 488 */       if (delta > max)
/* 489 */         max = delta; 
/*     */     } 
/* 492 */     iter = v.getEntries().iterator();
/* 493 */     while (iter.hasNext()) {
/* 494 */       iter.advance();
/* 495 */       int key = iter.key();
/* 496 */       if (!this.entries.containsKey(key) && 
/* 497 */         iter.value() > max)
/* 498 */         max = iter.value(); 
/*     */     } 
/* 502 */     return max;
/*     */   }
/*     */   
/*     */   public double getLInfDistance(RealVector v) {
/* 508 */     checkVectorDimensions(v.getDimension());
/* 509 */     if (v instanceof OpenMapRealVector)
/* 510 */       return getLInfDistance((OpenMapRealVector)v); 
/* 512 */     return super.getLInfDistance(v);
/*     */   }
/*     */   
/*     */   public boolean isInfinite() {
/* 519 */     boolean infiniteFound = false;
/* 520 */     OpenIntToDoubleHashMap.Iterator iter = this.entries.iterator();
/* 521 */     while (iter.hasNext()) {
/* 522 */       iter.advance();
/* 523 */       double value = iter.value();
/* 524 */       if (Double.isNaN(value))
/* 525 */         return false; 
/* 527 */       if (Double.isInfinite(value))
/* 528 */         infiniteFound = true; 
/*     */     } 
/* 531 */     return infiniteFound;
/*     */   }
/*     */   
/*     */   public boolean isNaN() {
/* 537 */     OpenIntToDoubleHashMap.Iterator iter = this.entries.iterator();
/* 538 */     while (iter.hasNext()) {
/* 539 */       iter.advance();
/* 540 */       if (Double.isNaN(iter.value()))
/* 541 */         return true; 
/*     */     } 
/* 544 */     return false;
/*     */   }
/*     */   
/*     */   public OpenMapRealVector mapAdd(double d) {
/* 550 */     return copy().mapAddToSelf(d);
/*     */   }
/*     */   
/*     */   public OpenMapRealVector mapAddToSelf(double d) {
/* 556 */     for (int i = 0; i < this.virtualSize; i++)
/* 557 */       setEntry(i, getEntry(i) + d); 
/* 559 */     return this;
/*     */   }
/*     */   
/*     */   public RealVector projection(RealVector v) {
/* 565 */     checkVectorDimensions(v.getDimension());
/* 566 */     return v.mapMultiply(dotProduct(v) / v.dotProduct(v));
/*     */   }
/*     */   
/*     */   public void setEntry(int index, double value) {
/* 572 */     checkIndex(index);
/* 573 */     if (!isDefaultValue(value)) {
/* 574 */       this.entries.put(index, value);
/* 575 */     } else if (this.entries.containsKey(index)) {
/* 576 */       this.entries.remove(index);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setSubVector(int index, RealVector v) {
/* 583 */     checkIndex(index);
/* 584 */     checkIndex(index + v.getDimension() - 1);
/* 585 */     for (int i = 0; i < v.getDimension(); i++)
/* 586 */       setEntry(i + index, v.getEntry(i)); 
/*     */   }
/*     */   
/*     */   public void set(double value) {
/* 593 */     for (int i = 0; i < this.virtualSize; i++)
/* 594 */       setEntry(i, value); 
/*     */   }
/*     */   
/*     */   public OpenMapRealVector subtract(OpenMapRealVector v) {
/* 607 */     checkVectorDimensions(v.getDimension());
/* 608 */     OpenMapRealVector res = copy();
/* 609 */     OpenIntToDoubleHashMap.Iterator iter = v.getEntries().iterator();
/* 610 */     while (iter.hasNext()) {
/* 611 */       iter.advance();
/* 612 */       int key = iter.key();
/* 613 */       if (this.entries.containsKey(key)) {
/* 614 */         res.setEntry(key, this.entries.get(key) - iter.value());
/*     */         continue;
/*     */       } 
/* 616 */       res.setEntry(key, -iter.value());
/*     */     } 
/* 619 */     return res;
/*     */   }
/*     */   
/*     */   public RealVector subtract(RealVector v) {
/* 625 */     checkVectorDimensions(v.getDimension());
/* 626 */     if (v instanceof OpenMapRealVector)
/* 627 */       return subtract((OpenMapRealVector)v); 
/* 629 */     return super.subtract(v);
/*     */   }
/*     */   
/*     */   public OpenMapRealVector unitVector() {
/* 636 */     OpenMapRealVector res = copy();
/* 637 */     res.unitize();
/* 638 */     return res;
/*     */   }
/*     */   
/*     */   public void unitize() {
/* 644 */     double norm = getNorm();
/* 645 */     if (isDefaultValue(norm))
/* 646 */       throw new MathArithmeticException(LocalizedFormats.ZERO_NORM, new Object[0]); 
/* 648 */     OpenIntToDoubleHashMap.Iterator iter = this.entries.iterator();
/* 649 */     while (iter.hasNext()) {
/* 650 */       iter.advance();
/* 651 */       this.entries.put(iter.key(), iter.value() / norm);
/*     */     } 
/*     */   }
/*     */   
/*     */   public double[] toArray() {
/* 658 */     double[] res = new double[this.virtualSize];
/* 659 */     OpenIntToDoubleHashMap.Iterator iter = this.entries.iterator();
/* 660 */     while (iter.hasNext()) {
/* 661 */       iter.advance();
/* 662 */       res[iter.key()] = iter.value();
/*     */     } 
/* 664 */     return res;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 675 */     int prime = 31;
/* 676 */     int result = 1;
/* 678 */     long temp = Double.doubleToLongBits(this.epsilon);
/* 679 */     result = 31 * result + (int)(temp ^ temp >>> 32L);
/* 680 */     result = 31 * result + this.virtualSize;
/* 681 */     OpenIntToDoubleHashMap.Iterator iter = this.entries.iterator();
/* 682 */     while (iter.hasNext()) {
/* 683 */       iter.advance();
/* 684 */       temp = Double.doubleToLongBits(iter.value());
/* 685 */       result = 31 * result + (int)(temp ^ temp >> 32L);
/*     */     } 
/* 687 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 698 */     if (this == obj)
/* 699 */       return true; 
/* 701 */     if (!(obj instanceof OpenMapRealVector))
/* 702 */       return false; 
/* 704 */     OpenMapRealVector other = (OpenMapRealVector)obj;
/* 705 */     if (this.virtualSize != other.virtualSize)
/* 706 */       return false; 
/* 708 */     if (Double.doubleToLongBits(this.epsilon) != Double.doubleToLongBits(other.epsilon))
/* 710 */       return false; 
/* 712 */     OpenIntToDoubleHashMap.Iterator iter = this.entries.iterator();
/* 713 */     while (iter.hasNext()) {
/* 714 */       iter.advance();
/* 715 */       double test = other.getEntry(iter.key());
/* 716 */       if (Double.doubleToLongBits(test) != Double.doubleToLongBits(iter.value()))
/* 717 */         return false; 
/*     */     } 
/* 720 */     iter = other.getEntries().iterator();
/* 721 */     while (iter.hasNext()) {
/* 722 */       iter.advance();
/* 723 */       double test = iter.value();
/* 724 */       if (Double.doubleToLongBits(test) != Double.doubleToLongBits(getEntry(iter.key())))
/* 725 */         return false; 
/*     */     } 
/* 728 */     return true;
/*     */   }
/*     */   
/*     */   public double getSparsity() {
/* 737 */     return this.entries.size() / getDimension();
/*     */   }
/*     */   
/*     */   public Iterator<RealVector.Entry> sparseIterator() {
/* 743 */     return new OpenMapSparseIterator();
/*     */   }
/*     */   
/*     */   protected class OpenMapEntry extends RealVector.Entry {
/*     */     private final OpenIntToDoubleHashMap.Iterator iter;
/*     */     
/*     */     protected OpenMapEntry(OpenIntToDoubleHashMap.Iterator iter) {
/* 761 */       this.iter = iter;
/*     */     }
/*     */     
/*     */     public double getValue() {
/* 767 */       return this.iter.value();
/*     */     }
/*     */     
/*     */     public void setValue(double value) {
/* 773 */       OpenMapRealVector.this.entries.put(this.iter.key(), value);
/*     */     }
/*     */     
/*     */     public int getIndex() {
/* 779 */       return this.iter.key();
/*     */     }
/*     */   }
/*     */   
/*     */   protected class OpenMapSparseIterator implements Iterator<RealVector.Entry> {
/* 797 */     private final OpenIntToDoubleHashMap.Iterator iter = OpenMapRealVector.this.entries.iterator();
/*     */     
/* 798 */     private final RealVector.Entry current = new OpenMapRealVector.OpenMapEntry(this.iter);
/*     */     
/*     */     public boolean hasNext() {
/* 803 */       return this.iter.hasNext();
/*     */     }
/*     */     
/*     */     public RealVector.Entry next() {
/* 808 */       this.iter.advance();
/* 809 */       return this.current;
/*     */     }
/*     */     
/*     */     public void remove() {
/* 814 */       throw new UnsupportedOperationException("Not supported");
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\linear\OpenMapRealVector.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */