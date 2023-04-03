/*     */ package org.apache.commons.math3.linear;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.lang.reflect.Array;
/*     */ import java.util.Arrays;
/*     */ import org.apache.commons.math3.Field;
/*     */ import org.apache.commons.math3.FieldElement;
/*     */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*     */ import org.apache.commons.math3.exception.NullArgumentException;
/*     */ import org.apache.commons.math3.exception.NumberIsTooLargeException;
/*     */ import org.apache.commons.math3.exception.OutOfRangeException;
/*     */ import org.apache.commons.math3.exception.ZeroException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ 
/*     */ public class ArrayFieldVector<T extends FieldElement<T>> implements FieldVector<T>, Serializable {
/*     */   private static final long serialVersionUID = 7648186910365927050L;
/*     */   
/*     */   private T[] data;
/*     */   
/*     */   private final Field<T> field;
/*     */   
/*     */   public ArrayFieldVector(Field<T> field) {
/*  59 */     this(field, 0);
/*     */   }
/*     */   
/*     */   public ArrayFieldVector(Field<T> field, int size) {
/*  69 */     this.field = field;
/*  70 */     this.data = buildArray(size);
/*  71 */     Arrays.fill((Object[])this.data, field.getZero());
/*     */   }
/*     */   
/*     */   public ArrayFieldVector(int size, T preset) {
/*  81 */     this(preset.getField(), size);
/*  82 */     Arrays.fill((Object[])this.data, preset);
/*     */   }
/*     */   
/*     */   public ArrayFieldVector(T[] d) {
/*  98 */     if (d == null)
/*  99 */       throw new NullArgumentException(); 
/*     */     try {
/* 102 */       this.field = d[0].getField();
/* 103 */       this.data = (T[])d.clone();
/* 104 */     } catch (ArrayIndexOutOfBoundsException e) {
/* 105 */       throw new ZeroException(LocalizedFormats.VECTOR_MUST_HAVE_AT_LEAST_ONE_ELEMENT, new Object[0]);
/*     */     } 
/*     */   }
/*     */   
/*     */   public ArrayFieldVector(Field<T> field, T[] d) {
/* 118 */     if (d == null)
/* 119 */       throw new NullArgumentException(); 
/* 121 */     this.field = field;
/* 122 */     this.data = (T[])d.clone();
/*     */   }
/*     */   
/*     */   public ArrayFieldVector(T[] d, boolean copyArray) {
/* 147 */     if (d == null)
/* 148 */       throw new NullArgumentException(); 
/* 150 */     if (d.length == 0)
/* 151 */       throw new ZeroException(LocalizedFormats.VECTOR_MUST_HAVE_AT_LEAST_ONE_ELEMENT, new Object[0]); 
/* 153 */     this.field = d[0].getField();
/* 154 */     this.data = copyArray ? (T[])d.clone() : d;
/*     */   }
/*     */   
/*     */   public ArrayFieldVector(Field<T> field, T[] d, boolean copyArray) {
/* 173 */     if (d == null)
/* 174 */       throw new NullArgumentException(); 
/* 176 */     this.field = field;
/* 177 */     this.data = copyArray ? (T[])d.clone() : d;
/*     */   }
/*     */   
/*     */   public ArrayFieldVector(T[] d, int pos, int size) {
/* 191 */     if (d == null)
/* 192 */       throw new NullArgumentException(); 
/* 194 */     if (d.length < pos + size)
/* 195 */       throw new NumberIsTooLargeException(Integer.valueOf(pos + size), Integer.valueOf(d.length), true); 
/* 197 */     this.field = d[0].getField();
/* 198 */     this.data = buildArray(size);
/* 199 */     System.arraycopy(d, pos, this.data, 0, size);
/*     */   }
/*     */   
/*     */   public ArrayFieldVector(Field<T> field, T[] d, int pos, int size) {
/* 214 */     if (d == null)
/* 215 */       throw new NullArgumentException(); 
/* 217 */     if (d.length < pos + size)
/* 218 */       throw new NumberIsTooLargeException(Integer.valueOf(pos + size), Integer.valueOf(d.length), true); 
/* 220 */     this.field = field;
/* 221 */     this.data = buildArray(size);
/* 222 */     System.arraycopy(d, pos, this.data, 0, size);
/*     */   }
/*     */   
/*     */   public ArrayFieldVector(FieldVector<T> v) {
/* 232 */     if (v == null)
/* 233 */       throw new NullArgumentException(); 
/* 235 */     this.field = v.getField();
/* 236 */     this.data = buildArray(v.getDimension());
/* 237 */     for (int i = 0; i < this.data.length; i++)
/* 238 */       this.data[i] = v.getEntry(i); 
/*     */   }
/*     */   
/*     */   public ArrayFieldVector(ArrayFieldVector<T> v) {
/* 249 */     if (v == null)
/* 250 */       throw new NullArgumentException(); 
/* 252 */     this.field = v.getField();
/* 253 */     this.data = (T[])v.data.clone();
/*     */   }
/*     */   
/*     */   public ArrayFieldVector(ArrayFieldVector<T> v, boolean deep) {
/* 265 */     if (v == null)
/* 266 */       throw new NullArgumentException(); 
/* 268 */     this.field = v.getField();
/* 269 */     this.data = deep ? (T[])v.data.clone() : v.data;
/*     */   }
/*     */   
/*     */   public ArrayFieldVector(ArrayFieldVector<T> v1, ArrayFieldVector<T> v2) {
/* 281 */     if (v1 == null || v2 == null)
/* 283 */       throw new NullArgumentException(); 
/* 285 */     this.field = v1.getField();
/* 286 */     this.data = buildArray(v1.data.length + v2.data.length);
/* 287 */     System.arraycopy(v1.data, 0, this.data, 0, v1.data.length);
/* 288 */     System.arraycopy(v2.data, 0, this.data, v1.data.length, v2.data.length);
/*     */   }
/*     */   
/*     */   public ArrayFieldVector(ArrayFieldVector<T> v1, T[] v2) {
/* 300 */     if (v1 == null || v2 == null)
/* 302 */       throw new NullArgumentException(); 
/* 304 */     this.field = v1.getField();
/* 305 */     this.data = buildArray(v1.data.length + v2.length);
/* 306 */     System.arraycopy(v1.data, 0, this.data, 0, v1.data.length);
/* 307 */     System.arraycopy(v2, 0, this.data, v1.data.length, v2.length);
/*     */   }
/*     */   
/*     */   public ArrayFieldVector(T[] v1, ArrayFieldVector<T> v2) {
/* 319 */     if (v1 == null || v2 == null)
/* 321 */       throw new NullArgumentException(); 
/* 323 */     this.field = v2.getField();
/* 324 */     this.data = buildArray(v1.length + v2.data.length);
/* 325 */     System.arraycopy(v1, 0, this.data, 0, v1.length);
/* 326 */     System.arraycopy(v2.data, 0, this.data, v1.length, v2.data.length);
/*     */   }
/*     */   
/*     */   public ArrayFieldVector(T[] v1, T[] v2) {
/* 345 */     if (v1 == null || v2 == null)
/* 347 */       throw new NullArgumentException(); 
/* 349 */     if (v1.length + v2.length == 0)
/* 350 */       throw new ZeroException(LocalizedFormats.VECTOR_MUST_HAVE_AT_LEAST_ONE_ELEMENT, new Object[0]); 
/* 352 */     this.data = buildArray(v1.length + v2.length);
/* 353 */     System.arraycopy(v1, 0, this.data, 0, v1.length);
/* 354 */     System.arraycopy(v2, 0, this.data, v1.length, v2.length);
/* 355 */     this.field = this.data[0].getField();
/*     */   }
/*     */   
/*     */   public ArrayFieldVector(Field<T> field, T[] v1, T[] v2) {
/* 370 */     if (v1.length + v2.length == 0)
/* 371 */       throw new ZeroException(LocalizedFormats.VECTOR_MUST_HAVE_AT_LEAST_ONE_ELEMENT, new Object[0]); 
/* 373 */     this.data = buildArray(v1.length + v2.length);
/* 374 */     System.arraycopy(v1, 0, this.data, 0, v1.length);
/* 375 */     System.arraycopy(v2, 0, this.data, v1.length, v2.length);
/* 376 */     this.field = field;
/*     */   }
/*     */   
/*     */   private T[] buildArray(int length) {
/* 387 */     return (T[])Array.newInstance(this.field.getRuntimeClass(), length);
/*     */   }
/*     */   
/*     */   public Field<T> getField() {
/* 392 */     return this.field;
/*     */   }
/*     */   
/*     */   public FieldVector<T> copy() {
/* 397 */     return new ArrayFieldVector(this, true);
/*     */   }
/*     */   
/*     */   public FieldVector<T> add(FieldVector<T> v) {
/*     */     try {
/* 403 */       return add((ArrayFieldVector<T>)v);
/* 404 */     } catch (ClassCastException cce) {
/* 405 */       checkVectorDimensions(v);
/* 406 */       T[] out = buildArray(this.data.length);
/* 407 */       for (int i = 0; i < this.data.length; i++)
/* 408 */         out[i] = (T)this.data[i].add(v.getEntry(i)); 
/* 410 */       return new ArrayFieldVector(this.field, out, false);
/*     */     } 
/*     */   }
/*     */   
/*     */   public ArrayFieldVector<T> add(ArrayFieldVector<T> v) {
/* 421 */     checkVectorDimensions(v.data.length);
/* 422 */     T[] out = buildArray(this.data.length);
/* 423 */     for (int i = 0; i < this.data.length; i++)
/* 424 */       out[i] = (T)this.data[i].add(v.data[i]); 
/* 426 */     return new ArrayFieldVector(this.field, out, false);
/*     */   }
/*     */   
/*     */   public FieldVector<T> subtract(FieldVector<T> v) {
/*     */     try {
/* 432 */       return subtract((ArrayFieldVector<T>)v);
/* 433 */     } catch (ClassCastException cce) {
/* 434 */       checkVectorDimensions(v);
/* 435 */       T[] out = buildArray(this.data.length);
/* 436 */       for (int i = 0; i < this.data.length; i++)
/* 437 */         out[i] = (T)this.data[i].subtract(v.getEntry(i)); 
/* 439 */       return new ArrayFieldVector(this.field, out, false);
/*     */     } 
/*     */   }
/*     */   
/*     */   public ArrayFieldVector<T> subtract(ArrayFieldVector<T> v) {
/* 450 */     checkVectorDimensions(v.data.length);
/* 451 */     T[] out = buildArray(this.data.length);
/* 452 */     for (int i = 0; i < this.data.length; i++)
/* 453 */       out[i] = (T)this.data[i].subtract(v.data[i]); 
/* 455 */     return new ArrayFieldVector(this.field, out, false);
/*     */   }
/*     */   
/*     */   public FieldVector<T> mapAdd(T d) {
/* 460 */     T[] out = buildArray(this.data.length);
/* 461 */     for (int i = 0; i < this.data.length; i++)
/* 462 */       out[i] = (T)this.data[i].add(d); 
/* 464 */     return new ArrayFieldVector(this.field, out, false);
/*     */   }
/*     */   
/*     */   public FieldVector<T> mapAddToSelf(T d) {
/* 469 */     for (int i = 0; i < this.data.length; i++)
/* 470 */       this.data[i] = (T)this.data[i].add(d); 
/* 472 */     return this;
/*     */   }
/*     */   
/*     */   public FieldVector<T> mapSubtract(T d) {
/* 477 */     T[] out = buildArray(this.data.length);
/* 478 */     for (int i = 0; i < this.data.length; i++)
/* 479 */       out[i] = (T)this.data[i].subtract(d); 
/* 481 */     return new ArrayFieldVector(this.field, out, false);
/*     */   }
/*     */   
/*     */   public FieldVector<T> mapSubtractToSelf(T d) {
/* 486 */     for (int i = 0; i < this.data.length; i++)
/* 487 */       this.data[i] = (T)this.data[i].subtract(d); 
/* 489 */     return this;
/*     */   }
/*     */   
/*     */   public FieldVector<T> mapMultiply(T d) {
/* 494 */     T[] out = buildArray(this.data.length);
/* 495 */     for (int i = 0; i < this.data.length; i++)
/* 496 */       out[i] = (T)this.data[i].multiply(d); 
/* 498 */     return new ArrayFieldVector(this.field, out, false);
/*     */   }
/*     */   
/*     */   public FieldVector<T> mapMultiplyToSelf(T d) {
/* 503 */     for (int i = 0; i < this.data.length; i++)
/* 504 */       this.data[i] = (T)this.data[i].multiply(d); 
/* 506 */     return this;
/*     */   }
/*     */   
/*     */   public FieldVector<T> mapDivide(T d) {
/* 511 */     T[] out = buildArray(this.data.length);
/* 512 */     for (int i = 0; i < this.data.length; i++)
/* 513 */       out[i] = (T)this.data[i].divide(d); 
/* 515 */     return new ArrayFieldVector(this.field, out, false);
/*     */   }
/*     */   
/*     */   public FieldVector<T> mapDivideToSelf(T d) {
/* 520 */     for (int i = 0; i < this.data.length; i++)
/* 521 */       this.data[i] = (T)this.data[i].divide(d); 
/* 523 */     return this;
/*     */   }
/*     */   
/*     */   public FieldVector<T> mapInv() {
/* 528 */     T[] out = buildArray(this.data.length);
/* 529 */     FieldElement fieldElement = (FieldElement)this.field.getOne();
/* 530 */     for (int i = 0; i < this.data.length; i++)
/* 531 */       out[i] = (T)fieldElement.divide(this.data[i]); 
/* 533 */     return new ArrayFieldVector(this.field, out, false);
/*     */   }
/*     */   
/*     */   public FieldVector<T> mapInvToSelf() {
/* 538 */     FieldElement fieldElement = (FieldElement)this.field.getOne();
/* 539 */     for (int i = 0; i < this.data.length; i++)
/* 540 */       this.data[i] = (T)fieldElement.divide(this.data[i]); 
/* 542 */     return this;
/*     */   }
/*     */   
/*     */   public FieldVector<T> ebeMultiply(FieldVector<T> v) {
/*     */     try {
/* 548 */       return ebeMultiply((ArrayFieldVector<T>)v);
/* 549 */     } catch (ClassCastException cce) {
/* 550 */       checkVectorDimensions(v);
/* 551 */       T[] out = buildArray(this.data.length);
/* 552 */       for (int i = 0; i < this.data.length; i++)
/* 553 */         out[i] = (T)this.data[i].multiply(v.getEntry(i)); 
/* 555 */       return new ArrayFieldVector(this.field, out, false);
/*     */     } 
/*     */   }
/*     */   
/*     */   public ArrayFieldVector<T> ebeMultiply(ArrayFieldVector<T> v) {
/* 566 */     checkVectorDimensions(v.data.length);
/* 567 */     T[] out = buildArray(this.data.length);
/* 568 */     for (int i = 0; i < this.data.length; i++)
/* 569 */       out[i] = (T)this.data[i].multiply(v.data[i]); 
/* 571 */     return new ArrayFieldVector(this.field, out, false);
/*     */   }
/*     */   
/*     */   public FieldVector<T> ebeDivide(FieldVector<T> v) {
/*     */     try {
/* 577 */       return ebeDivide((ArrayFieldVector<T>)v);
/* 578 */     } catch (ClassCastException cce) {
/* 579 */       checkVectorDimensions(v);
/* 580 */       T[] out = buildArray(this.data.length);
/* 581 */       for (int i = 0; i < this.data.length; i++)
/* 582 */         out[i] = (T)this.data[i].divide(v.getEntry(i)); 
/* 584 */       return new ArrayFieldVector(this.field, out, false);
/*     */     } 
/*     */   }
/*     */   
/*     */   public ArrayFieldVector<T> ebeDivide(ArrayFieldVector<T> v) {
/* 595 */     checkVectorDimensions(v.data.length);
/* 596 */     T[] out = buildArray(this.data.length);
/* 597 */     for (int i = 0; i < this.data.length; i++)
/* 598 */       out[i] = (T)this.data[i].divide(v.data[i]); 
/* 600 */     return new ArrayFieldVector(this.field, out, false);
/*     */   }
/*     */   
/*     */   public T[] getData() {
/* 605 */     return (T[])this.data.clone();
/*     */   }
/*     */   
/*     */   public T[] getDataRef() {
/* 614 */     return this.data;
/*     */   }
/*     */   
/*     */   public T dotProduct(FieldVector<T> v) {
/*     */     try {
/* 620 */       return dotProduct((ArrayFieldVector<T>)v);
/* 621 */     } catch (ClassCastException cce) {
/* 622 */       checkVectorDimensions(v);
/* 623 */       FieldElement fieldElement = (FieldElement)this.field.getZero();
/* 624 */       for (int i = 0; i < this.data.length; i++)
/* 625 */         fieldElement = (FieldElement)fieldElement.add(this.data[i].multiply(v.getEntry(i))); 
/* 627 */       return (T)fieldElement;
/*     */     } 
/*     */   }
/*     */   
/*     */   public T dotProduct(ArrayFieldVector<T> v) {
/* 638 */     checkVectorDimensions(v.data.length);
/* 639 */     FieldElement fieldElement = (FieldElement)this.field.getZero();
/* 640 */     for (int i = 0; i < this.data.length; i++)
/* 641 */       fieldElement = (FieldElement)fieldElement.add(this.data[i].multiply(v.data[i])); 
/* 643 */     return (T)fieldElement;
/*     */   }
/*     */   
/*     */   public FieldVector<T> projection(FieldVector<T> v) {
/* 648 */     return v.mapMultiply((T)dotProduct(v).divide(v.dotProduct(v)));
/*     */   }
/*     */   
/*     */   public ArrayFieldVector<T> projection(ArrayFieldVector<T> v) {
/* 657 */     return (ArrayFieldVector<T>)v.mapMultiply((T)dotProduct(v).divide(v.dotProduct(v)));
/*     */   }
/*     */   
/*     */   public FieldMatrix<T> outerProduct(FieldVector<T> v) {
/*     */     try {
/* 663 */       return outerProduct((ArrayFieldVector<T>)v);
/* 664 */     } catch (ClassCastException cce) {
/* 665 */       int m = this.data.length;
/* 666 */       int n = v.getDimension();
/* 667 */       FieldMatrix<T> out = new Array2DRowFieldMatrix<T>(this.field, m, n);
/* 668 */       for (int i = 0; i < m; i++) {
/* 669 */         for (int j = 0; j < n; j++)
/* 670 */           out.setEntry(i, j, (T)this.data[i].multiply(v.getEntry(j))); 
/*     */       } 
/* 673 */       return out;
/*     */     } 
/*     */   }
/*     */   
/*     */   public FieldMatrix<T> outerProduct(ArrayFieldVector<T> v) {
/* 684 */     int m = this.data.length;
/* 685 */     int n = v.data.length;
/* 686 */     FieldMatrix<T> out = new Array2DRowFieldMatrix<T>(this.field, m, n);
/* 687 */     for (int i = 0; i < m; i++) {
/* 688 */       for (int j = 0; j < n; j++)
/* 689 */         out.setEntry(i, j, (T)this.data[i].multiply(v.data[j])); 
/*     */     } 
/* 692 */     return out;
/*     */   }
/*     */   
/*     */   public T getEntry(int index) {
/* 697 */     return this.data[index];
/*     */   }
/*     */   
/*     */   public int getDimension() {
/* 702 */     return this.data.length;
/*     */   }
/*     */   
/*     */   public FieldVector<T> append(FieldVector<T> v) {
/*     */     try {
/* 708 */       return append((ArrayFieldVector<T>)v);
/* 709 */     } catch (ClassCastException cce) {
/* 710 */       return new ArrayFieldVector(this, new ArrayFieldVector(v));
/*     */     } 
/*     */   }
/*     */   
/*     */   public ArrayFieldVector<T> append(ArrayFieldVector<T> v) {
/* 720 */     return new ArrayFieldVector(this, v);
/*     */   }
/*     */   
/*     */   public FieldVector<T> append(T in) {
/* 725 */     T[] out = buildArray(this.data.length + 1);
/* 726 */     System.arraycopy(this.data, 0, out, 0, this.data.length);
/* 727 */     out[this.data.length] = in;
/* 728 */     return new ArrayFieldVector(this.field, out, false);
/*     */   }
/*     */   
/*     */   public FieldVector<T> getSubVector(int index, int n) {
/* 733 */     ArrayFieldVector<T> out = new ArrayFieldVector(this.field, n);
/*     */     try {
/* 735 */       System.arraycopy(this.data, index, out.data, 0, n);
/* 736 */     } catch (IndexOutOfBoundsException e) {
/* 737 */       checkIndex(index);
/* 738 */       checkIndex(index + n - 1);
/*     */     } 
/* 740 */     return out;
/*     */   }
/*     */   
/*     */   public void setEntry(int index, T value) {
/*     */     try {
/* 746 */       this.data[index] = value;
/* 747 */     } catch (IndexOutOfBoundsException e) {
/* 748 */       checkIndex(index);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setSubVector(int index, FieldVector<T> v) {
/*     */     try {
/*     */       try {
/* 756 */         set(index, (ArrayFieldVector<T>)v);
/* 757 */       } catch (ClassCastException cce) {
/* 758 */         for (int i = index; i < index + v.getDimension(); i++)
/* 759 */           this.data[i] = v.getEntry(i - index); 
/*     */       } 
/* 762 */     } catch (IndexOutOfBoundsException e) {
/* 763 */       checkIndex(index);
/* 764 */       checkIndex(index + v.getDimension() - 1);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void set(int index, ArrayFieldVector<T> v) {
/*     */     try {
/* 778 */       System.arraycopy(v.data, 0, this.data, index, v.data.length);
/* 779 */     } catch (IndexOutOfBoundsException e) {
/* 780 */       checkIndex(index);
/* 781 */       checkIndex(index + v.data.length - 1);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void set(T value) {
/* 787 */     Arrays.fill((Object[])this.data, value);
/*     */   }
/*     */   
/*     */   public T[] toArray() {
/* 792 */     return (T[])this.data.clone();
/*     */   }
/*     */   
/*     */   protected void checkVectorDimensions(FieldVector<T> v) {
/* 802 */     checkVectorDimensions(v.getDimension());
/*     */   }
/*     */   
/*     */   protected void checkVectorDimensions(int n) {
/* 813 */     if (this.data.length != n)
/* 814 */       throw new DimensionMismatchException(this.data.length, n); 
/*     */   }
/*     */   
/*     */   public boolean equals(Object other) {
/* 827 */     if (this == other)
/* 828 */       return true; 
/* 830 */     if (other == null)
/* 831 */       return false; 
/*     */     try {
/* 836 */       FieldVector<T> rhs = (FieldVector<T>)other;
/* 837 */       if (this.data.length != rhs.getDimension())
/* 838 */         return false; 
/* 841 */       for (int i = 0; i < this.data.length; i++) {
/* 842 */         if (!this.data[i].equals(rhs.getEntry(i)))
/* 843 */           return false; 
/*     */       } 
/* 846 */       return true;
/* 847 */     } catch (ClassCastException ex) {
/* 849 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 860 */     int h = 3542;
/* 861 */     for (T a : this.data)
/* 862 */       h ^= a.hashCode(); 
/* 864 */     return h;
/*     */   }
/*     */   
/*     */   private void checkIndex(int index) {
/* 874 */     if (index < 0 || index >= getDimension())
/* 875 */       throw new OutOfRangeException(LocalizedFormats.INDEX, Integer.valueOf(index), Integer.valueOf(0), Integer.valueOf(getDimension() - 1)); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\linear\ArrayFieldVector.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */