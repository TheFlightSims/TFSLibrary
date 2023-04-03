/*     */ package org.apache.commons.math3.util;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Arrays;
/*     */ import org.apache.commons.math3.exception.MathIllegalArgumentException;
/*     */ import org.apache.commons.math3.exception.MathIllegalStateException;
/*     */ import org.apache.commons.math3.exception.NullArgumentException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ 
/*     */ public class ResizableDoubleArray implements DoubleArray, Serializable {
/*     */   public static final int ADDITIVE_MODE = 1;
/*     */   
/*     */   public static final int MULTIPLICATIVE_MODE = 0;
/*     */   
/*     */   private static final long serialVersionUID = -3485529955529426875L;
/*     */   
/*  93 */   private float contractionCriteria = 2.5F;
/*     */   
/* 103 */   private float expansionFactor = 2.0F;
/*     */   
/* 109 */   private int expansionMode = 0;
/*     */   
/* 115 */   private int initialCapacity = 16;
/*     */   
/*     */   private double[] internalArray;
/*     */   
/* 126 */   private int numElements = 0;
/*     */   
/* 134 */   private int startIndex = 0;
/*     */   
/*     */   public ResizableDoubleArray() {
/* 146 */     this.internalArray = new double[this.initialCapacity];
/*     */   }
/*     */   
/*     */   public ResizableDoubleArray(int initialCapacity) {
/* 161 */     setInitialCapacity(initialCapacity);
/* 162 */     this.internalArray = new double[this.initialCapacity];
/*     */   }
/*     */   
/*     */   public ResizableDoubleArray(double[] initialArray) {
/* 183 */     if (initialArray == null) {
/* 184 */       this.internalArray = new double[this.initialCapacity];
/*     */     } else {
/* 186 */       this.internalArray = new double[initialArray.length];
/* 187 */       System.arraycopy(initialArray, 0, this.internalArray, 0, initialArray.length);
/* 188 */       this.initialCapacity = initialArray.length;
/* 189 */       this.numElements = initialArray.length;
/*     */     } 
/*     */   }
/*     */   
/*     */   public ResizableDoubleArray(int initialCapacity, float expansionFactor) {
/* 216 */     this.expansionFactor = expansionFactor;
/* 217 */     setInitialCapacity(initialCapacity);
/* 218 */     this.internalArray = new double[initialCapacity];
/* 219 */     setContractionCriteria(expansionFactor + 0.5F);
/*     */   }
/*     */   
/*     */   public ResizableDoubleArray(int initialCapacity, float expansionFactor, float contractionCriteria) {
/* 243 */     this.expansionFactor = expansionFactor;
/* 244 */     setContractionCriteria(contractionCriteria);
/* 245 */     setInitialCapacity(initialCapacity);
/* 246 */     this.internalArray = new double[initialCapacity];
/*     */   }
/*     */   
/*     */   public ResizableDoubleArray(int initialCapacity, float expansionFactor, float contractionCriteria, int expansionMode) {
/* 272 */     this.expansionFactor = expansionFactor;
/* 273 */     setContractionCriteria(contractionCriteria);
/* 274 */     setInitialCapacity(initialCapacity);
/* 275 */     setExpansionMode(expansionMode);
/* 276 */     this.internalArray = new double[initialCapacity];
/*     */   }
/*     */   
/*     */   public ResizableDoubleArray(ResizableDoubleArray original) throws NullArgumentException {
/* 291 */     MathUtils.checkNotNull(original);
/* 292 */     copy(original, this);
/*     */   }
/*     */   
/*     */   public synchronized void addElement(double value) {
/* 301 */     this.numElements++;
/* 302 */     if (this.startIndex + this.numElements > this.internalArray.length)
/* 303 */       expand(); 
/* 305 */     this.internalArray[this.startIndex + this.numElements - 1] = value;
/* 306 */     if (shouldContract())
/* 307 */       contract(); 
/*     */   }
/*     */   
/*     */   public synchronized void addElements(double[] values) {
/* 318 */     double[] tempArray = new double[this.numElements + values.length + 1];
/* 319 */     System.arraycopy(this.internalArray, this.startIndex, tempArray, 0, this.numElements);
/* 320 */     System.arraycopy(values, 0, tempArray, this.numElements, values.length);
/* 321 */     this.internalArray = tempArray;
/* 322 */     this.startIndex = 0;
/* 323 */     this.numElements += values.length;
/*     */   }
/*     */   
/*     */   public synchronized double addElementRolling(double value) {
/* 343 */     double discarded = this.internalArray[this.startIndex];
/* 345 */     if (this.startIndex + this.numElements + 1 > this.internalArray.length)
/* 346 */       expand(); 
/* 349 */     this.startIndex++;
/* 352 */     this.internalArray[this.startIndex + this.numElements - 1] = value;
/* 355 */     if (shouldContract())
/* 356 */       contract(); 
/* 358 */     return discarded;
/*     */   }
/*     */   
/*     */   public synchronized double substituteMostRecentElement(double value) {
/* 372 */     if (this.numElements < 1)
/* 373 */       throw new MathIllegalStateException(LocalizedFormats.CANNOT_SUBSTITUTE_ELEMENT_FROM_EMPTY_ARRAY, new Object[0]); 
/* 377 */     double discarded = this.internalArray[this.startIndex + this.numElements - 1];
/* 379 */     this.internalArray[this.startIndex + this.numElements - 1] = value;
/* 381 */     return discarded;
/*     */   }
/*     */   
/*     */   protected void checkContractExpand(float contraction, float expansion) {
/* 397 */     if (contraction < expansion)
/* 398 */       throw new MathIllegalArgumentException(LocalizedFormats.CONTRACTION_CRITERIA_SMALLER_THAN_EXPANSION_FACTOR, new Object[] { Float.valueOf(contraction), Float.valueOf(expansion) }); 
/* 403 */     if (contraction <= 1.0D)
/* 404 */       throw new MathIllegalArgumentException(LocalizedFormats.CONTRACTION_CRITERIA_SMALLER_THAN_ONE, new Object[] { Float.valueOf(contraction) }); 
/* 409 */     if (expansion <= 1.0D)
/* 410 */       throw new MathIllegalArgumentException(LocalizedFormats.EXPANSION_FACTOR_SMALLER_THAN_ONE, new Object[] { Float.valueOf(expansion) }); 
/*     */   }
/*     */   
/*     */   public synchronized void clear() {
/* 421 */     this.numElements = 0;
/* 422 */     this.startIndex = 0;
/* 423 */     this.internalArray = new double[this.initialCapacity];
/*     */   }
/*     */   
/*     */   public synchronized void contract() {
/* 432 */     double[] tempArray = new double[this.numElements + 1];
/* 435 */     System.arraycopy(this.internalArray, this.startIndex, tempArray, 0, this.numElements);
/* 436 */     this.internalArray = tempArray;
/* 439 */     this.startIndex = 0;
/*     */   }
/*     */   
/*     */   public synchronized void discardFrontElements(int i) {
/* 455 */     discardExtremeElements(i, true);
/*     */   }
/*     */   
/*     */   public synchronized void discardMostRecentElements(int i) {
/* 472 */     discardExtremeElements(i, false);
/*     */   }
/*     */   
/*     */   private synchronized void discardExtremeElements(int i, boolean front) {
/* 496 */     if (i > this.numElements)
/* 497 */       throw new MathIllegalArgumentException(LocalizedFormats.TOO_MANY_ELEMENTS_TO_DISCARD_FROM_ARRAY, new Object[] { Integer.valueOf(i), Integer.valueOf(this.numElements) }); 
/* 500 */     if (i < 0)
/* 501 */       throw new MathIllegalArgumentException(LocalizedFormats.CANNOT_DISCARD_NEGATIVE_NUMBER_OF_ELEMENTS, new Object[] { Integer.valueOf(i) }); 
/* 506 */     this.numElements -= i;
/* 507 */     if (front)
/* 508 */       this.startIndex += i; 
/* 511 */     if (shouldContract())
/* 512 */       contract(); 
/*     */   }
/*     */   
/*     */   protected synchronized void expand() {
/* 532 */     int newSize = 0;
/* 533 */     if (this.expansionMode == 0) {
/* 534 */       newSize = (int)FastMath.ceil((this.internalArray.length * this.expansionFactor));
/*     */     } else {
/* 536 */       newSize = this.internalArray.length + FastMath.round(this.expansionFactor);
/*     */     } 
/* 538 */     double[] tempArray = new double[newSize];
/* 541 */     System.arraycopy(this.internalArray, 0, tempArray, 0, this.internalArray.length);
/* 542 */     this.internalArray = tempArray;
/*     */   }
/*     */   
/*     */   private synchronized void expandTo(int size) {
/* 551 */     double[] tempArray = new double[size];
/* 553 */     System.arraycopy(this.internalArray, 0, tempArray, 0, this.internalArray.length);
/* 554 */     this.internalArray = tempArray;
/*     */   }
/*     */   
/*     */   public float getContractionCriteria() {
/* 570 */     return this.contractionCriteria;
/*     */   }
/*     */   
/*     */   public synchronized double getElement(int index) {
/* 582 */     if (index >= this.numElements)
/* 583 */       throw new ArrayIndexOutOfBoundsException(index); 
/* 584 */     if (index >= 0)
/* 585 */       return this.internalArray[this.startIndex + index]; 
/* 587 */     throw new ArrayIndexOutOfBoundsException(index);
/*     */   }
/*     */   
/*     */   public synchronized double[] getElements() {
/* 599 */     double[] elementArray = new double[this.numElements];
/* 600 */     System.arraycopy(this.internalArray, this.startIndex, elementArray, 0, this.numElements);
/* 602 */     return elementArray;
/*     */   }
/*     */   
/*     */   public float getExpansionFactor() {
/* 618 */     return this.expansionFactor;
/*     */   }
/*     */   
/*     */   public int getExpansionMode() {
/* 629 */     return this.expansionMode;
/*     */   }
/*     */   
/*     */   synchronized int getInternalLength() {
/* 641 */     return this.internalArray.length;
/*     */   }
/*     */   
/*     */   public synchronized int getNumElements() {
/* 651 */     return this.numElements;
/*     */   }
/*     */   
/*     */   public synchronized double[] getInternalValues() {
/* 667 */     return this.internalArray;
/*     */   }
/*     */   
/*     */   public void setContractionCriteria(float contractionCriteria) {
/* 676 */     checkContractExpand(contractionCriteria, getExpansionFactor());
/* 677 */     synchronized (this) {
/* 678 */       this.contractionCriteria = contractionCriteria;
/*     */     } 
/*     */   }
/*     */   
/*     */   public synchronized void setElement(int index, double value) {
/* 696 */     if (index < 0)
/* 697 */       throw new ArrayIndexOutOfBoundsException(index); 
/* 699 */     if (index + 1 > this.numElements)
/* 700 */       this.numElements = index + 1; 
/* 702 */     if (this.startIndex + index >= this.internalArray.length)
/* 703 */       expandTo(this.startIndex + index + 1); 
/* 705 */     this.internalArray[this.startIndex + index] = value;
/*     */   }
/*     */   
/*     */   public void setExpansionFactor(float expansionFactor) {
/* 720 */     checkContractExpand(getContractionCriteria(), expansionFactor);
/* 722 */     synchronized (this) {
/* 723 */       this.expansionFactor = expansionFactor;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setExpansionMode(int expansionMode) {
/* 735 */     if (expansionMode != 0 && expansionMode != 1)
/* 737 */       throw new MathIllegalArgumentException(LocalizedFormats.UNSUPPORTED_EXPANSION_MODE, new Object[] { Integer.valueOf(expansionMode), Integer.valueOf(0), "MULTIPLICATIVE_MODE", Integer.valueOf(1), "ADDITIVE_MODE" }); 
/* 742 */     synchronized (this) {
/* 743 */       this.expansionMode = expansionMode;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void setInitialCapacity(int initialCapacity) {
/* 755 */     if (initialCapacity > 0) {
/* 756 */       synchronized (this) {
/* 757 */         this.initialCapacity = initialCapacity;
/*     */       } 
/*     */     } else {
/* 760 */       throw new MathIllegalArgumentException(LocalizedFormats.INITIAL_CAPACITY_NOT_POSITIVE, new Object[] { Integer.valueOf(initialCapacity) });
/*     */     } 
/*     */   }
/*     */   
/*     */   public synchronized void setNumElements(int i) {
/* 777 */     if (i < 0)
/* 778 */       throw new MathIllegalArgumentException(LocalizedFormats.INDEX_NOT_POSITIVE, new Object[] { Integer.valueOf(i) }); 
/* 785 */     if (this.startIndex + i > this.internalArray.length)
/* 786 */       expandTo(this.startIndex + i); 
/* 790 */     this.numElements = i;
/*     */   }
/*     */   
/*     */   private synchronized boolean shouldContract() {
/* 800 */     if (this.expansionMode == 0)
/* 801 */       return (this.internalArray.length / this.numElements > this.contractionCriteria); 
/* 803 */     return ((this.internalArray.length - this.numElements) > this.contractionCriteria);
/*     */   }
/*     */   
/*     */   public synchronized int start() {
/* 817 */     return this.startIndex;
/*     */   }
/*     */   
/*     */   public static void copy(ResizableDoubleArray source, ResizableDoubleArray dest) throws NullArgumentException {
/* 839 */     MathUtils.checkNotNull(source);
/* 840 */     MathUtils.checkNotNull(dest);
/* 841 */     synchronized (source) {
/* 842 */       synchronized (dest) {
/* 843 */         dest.initialCapacity = source.initialCapacity;
/* 844 */         dest.contractionCriteria = source.contractionCriteria;
/* 845 */         dest.expansionFactor = source.expansionFactor;
/* 846 */         dest.expansionMode = source.expansionMode;
/* 847 */         dest.internalArray = new double[source.internalArray.length];
/* 848 */         System.arraycopy(source.internalArray, 0, dest.internalArray, 0, dest.internalArray.length);
/* 850 */         dest.numElements = source.numElements;
/* 851 */         dest.startIndex = source.startIndex;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public synchronized ResizableDoubleArray copy() {
/* 865 */     ResizableDoubleArray result = new ResizableDoubleArray();
/* 866 */     copy(this, result);
/* 867 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 881 */     if (object == this)
/* 882 */       return true; 
/* 884 */     if (!(object instanceof ResizableDoubleArray))
/* 885 */       return false; 
/* 887 */     synchronized (this) {
/* 888 */       synchronized (object) {
/* 889 */         boolean result = true;
/* 890 */         ResizableDoubleArray other = (ResizableDoubleArray)object;
/* 891 */         result = (result && other.initialCapacity == this.initialCapacity);
/* 892 */         result = (result && other.contractionCriteria == this.contractionCriteria);
/* 893 */         result = (result && other.expansionFactor == this.expansionFactor);
/* 894 */         result = (result && other.expansionMode == this.expansionMode);
/* 895 */         result = (result && other.numElements == this.numElements);
/* 896 */         result = (result && other.startIndex == this.startIndex);
/* 897 */         if (!result)
/* 898 */           return false; 
/* 900 */         return Arrays.equals(this.internalArray, other.internalArray);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public synchronized int hashCode() {
/* 914 */     int[] hashData = new int[7];
/* 915 */     hashData[0] = (new Float(this.expansionFactor)).hashCode();
/* 916 */     hashData[1] = (new Float(this.contractionCriteria)).hashCode();
/* 917 */     hashData[2] = this.expansionMode;
/* 918 */     hashData[3] = Arrays.hashCode(this.internalArray);
/* 919 */     hashData[4] = this.initialCapacity;
/* 920 */     hashData[5] = this.numElements;
/* 921 */     hashData[6] = this.startIndex;
/* 922 */     return Arrays.hashCode(hashData);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math\\util\ResizableDoubleArray.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */