/*     */ package org.apache.commons.math3.linear;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.lang.reflect.Array;
/*     */ import org.apache.commons.math3.Field;
/*     */ import org.apache.commons.math3.FieldElement;
/*     */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*     */ import org.apache.commons.math3.exception.OutOfRangeException;
/*     */ import org.apache.commons.math3.util.OpenIntToFieldHashMap;
/*     */ 
/*     */ public class SparseFieldVector<T extends FieldElement<T>> implements FieldVector<T>, Serializable {
/*     */   private static final long serialVersionUID = 7841233292190413362L;
/*     */   
/*     */   private final Field<T> field;
/*     */   
/*     */   private final OpenIntToFieldHashMap<T> entries;
/*     */   
/*     */   private final int virtualSize;
/*     */   
/*     */   public SparseFieldVector(Field<T> field) {
/*  55 */     this(field, 0);
/*     */   }
/*     */   
/*     */   public SparseFieldVector(Field<T> field, int dimension) {
/*  66 */     this.field = field;
/*  67 */     this.virtualSize = dimension;
/*  68 */     this.entries = new OpenIntToFieldHashMap(field);
/*     */   }
/*     */   
/*     */   protected SparseFieldVector(SparseFieldVector<T> v, int resize) {
/*  78 */     this.field = v.field;
/*  79 */     this.virtualSize = v.getDimension() + resize;
/*  80 */     this.entries = new OpenIntToFieldHashMap(v.entries);
/*     */   }
/*     */   
/*     */   public SparseFieldVector(Field<T> field, int dimension, int expectedSize) {
/*  92 */     this.field = field;
/*  93 */     this.virtualSize = dimension;
/*  94 */     this.entries = new OpenIntToFieldHashMap(field, expectedSize);
/*     */   }
/*     */   
/*     */   public SparseFieldVector(Field<T> field, T[] values) {
/* 105 */     this.field = field;
/* 106 */     this.virtualSize = values.length;
/* 107 */     this.entries = new OpenIntToFieldHashMap(field);
/* 108 */     for (int key = 0; key < values.length; key++) {
/* 109 */       T value = values[key];
/* 110 */       this.entries.put(key, (FieldElement)value);
/*     */     } 
/*     */   }
/*     */   
/*     */   public SparseFieldVector(SparseFieldVector<T> v) {
/* 120 */     this.field = v.field;
/* 121 */     this.virtualSize = v.getDimension();
/* 122 */     this.entries = new OpenIntToFieldHashMap(v.getEntries());
/*     */   }
/*     */   
/*     */   private OpenIntToFieldHashMap<T> getEntries() {
/* 131 */     return this.entries;
/*     */   }
/*     */   
/*     */   public FieldVector<T> add(SparseFieldVector<T> v) {
/* 143 */     checkVectorDimensions(v.getDimension());
/* 144 */     SparseFieldVector<T> res = (SparseFieldVector<T>)copy();
/* 145 */     OpenIntToFieldHashMap<T>.Iterator iter = v.getEntries().iterator();
/* 146 */     while (iter.hasNext()) {
/* 147 */       iter.advance();
/* 148 */       int key = iter.key();
/* 149 */       FieldElement fieldElement = iter.value();
/* 150 */       if (this.entries.containsKey(key)) {
/* 151 */         res.setEntry(key, (T)this.entries.get(key).add(fieldElement));
/*     */         continue;
/*     */       } 
/* 153 */       res.setEntry(key, (T)fieldElement);
/*     */     } 
/* 156 */     return res;
/*     */   }
/*     */   
/*     */   public FieldVector<T> append(SparseFieldVector<T> v) {
/* 167 */     SparseFieldVector<T> res = new SparseFieldVector(this, v.getDimension());
/* 168 */     OpenIntToFieldHashMap<T>.Iterator iter = v.entries.iterator();
/* 169 */     while (iter.hasNext()) {
/* 170 */       iter.advance();
/* 171 */       res.setEntry(iter.key() + this.virtualSize, (T)iter.value());
/*     */     } 
/* 173 */     return res;
/*     */   }
/*     */   
/*     */   public FieldVector<T> append(FieldVector<T> v) {
/* 178 */     if (v instanceof SparseFieldVector)
/* 179 */       return append((SparseFieldVector<T>)v); 
/* 181 */     int n = v.getDimension();
/* 182 */     FieldVector<T> res = new SparseFieldVector(this, n);
/* 183 */     for (int i = 0; i < n; i++)
/* 184 */       res.setEntry(i + this.virtualSize, v.getEntry(i)); 
/* 186 */     return res;
/*     */   }
/*     */   
/*     */   public FieldVector<T> append(T d) {
/* 192 */     FieldVector<T> res = new SparseFieldVector(this, 1);
/* 193 */     res.setEntry(this.virtualSize, d);
/* 194 */     return res;
/*     */   }
/*     */   
/*     */   public FieldVector<T> copy() {
/* 199 */     return new SparseFieldVector(this);
/*     */   }
/*     */   
/*     */   public T dotProduct(FieldVector<T> v) {
/* 204 */     checkVectorDimensions(v.getDimension());
/* 205 */     FieldElement fieldElement = (FieldElement)this.field.getZero();
/* 206 */     OpenIntToFieldHashMap<T>.Iterator iter = this.entries.iterator();
/* 207 */     while (iter.hasNext()) {
/* 208 */       iter.advance();
/* 209 */       fieldElement = (FieldElement)fieldElement.add(v.getEntry(iter.key()).multiply(iter.value()));
/*     */     } 
/* 211 */     return (T)fieldElement;
/*     */   }
/*     */   
/*     */   public FieldVector<T> ebeDivide(FieldVector<T> v) {
/* 216 */     checkVectorDimensions(v.getDimension());
/* 217 */     SparseFieldVector<T> res = new SparseFieldVector(this);
/* 218 */     OpenIntToFieldHashMap<T>.Iterator iter = res.entries.iterator();
/* 219 */     while (iter.hasNext()) {
/* 220 */       iter.advance();
/* 221 */       res.setEntry(iter.key(), (T)iter.value().divide(v.getEntry(iter.key())));
/*     */     } 
/* 223 */     return res;
/*     */   }
/*     */   
/*     */   public FieldVector<T> ebeMultiply(FieldVector<T> v) {
/* 228 */     checkVectorDimensions(v.getDimension());
/* 229 */     SparseFieldVector<T> res = new SparseFieldVector(this);
/* 230 */     OpenIntToFieldHashMap<T>.Iterator iter = res.entries.iterator();
/* 231 */     while (iter.hasNext()) {
/* 232 */       iter.advance();
/* 233 */       res.setEntry(iter.key(), (T)iter.value().multiply(v.getEntry(iter.key())));
/*     */     } 
/* 235 */     return res;
/*     */   }
/*     */   
/*     */   public T[] getData() {
/* 240 */     T[] res = buildArray(this.virtualSize);
/* 241 */     OpenIntToFieldHashMap<T>.Iterator iter = this.entries.iterator();
/* 242 */     while (iter.hasNext()) {
/* 243 */       iter.advance();
/* 244 */       res[iter.key()] = (T)iter.value();
/*     */     } 
/* 246 */     return res;
/*     */   }
/*     */   
/*     */   public int getDimension() {
/* 251 */     return this.virtualSize;
/*     */   }
/*     */   
/*     */   public T getEntry(int index) {
/* 256 */     checkIndex(index);
/* 257 */     return (T)this.entries.get(index);
/*     */   }
/*     */   
/*     */   public Field<T> getField() {
/* 262 */     return this.field;
/*     */   }
/*     */   
/*     */   public FieldVector<T> getSubVector(int index, int n) {
/* 267 */     checkIndex(index);
/* 268 */     checkIndex(index + n - 1);
/* 269 */     SparseFieldVector<T> res = new SparseFieldVector(this.field, n);
/* 270 */     int end = index + n;
/* 271 */     OpenIntToFieldHashMap<T>.Iterator iter = this.entries.iterator();
/* 272 */     while (iter.hasNext()) {
/* 273 */       iter.advance();
/* 274 */       int key = iter.key();
/* 275 */       if (key >= index && key < end)
/* 276 */         res.setEntry(key - index, (T)iter.value()); 
/*     */     } 
/* 279 */     return res;
/*     */   }
/*     */   
/*     */   public FieldVector<T> mapAdd(T d) {
/* 284 */     return copy().mapAddToSelf(d);
/*     */   }
/*     */   
/*     */   public FieldVector<T> mapAddToSelf(T d) {
/* 289 */     for (int i = 0; i < this.virtualSize; i++)
/* 290 */       setEntry(i, (T)getEntry(i).add(d)); 
/* 292 */     return this;
/*     */   }
/*     */   
/*     */   public FieldVector<T> mapDivide(T d) {
/* 297 */     return copy().mapDivideToSelf(d);
/*     */   }
/*     */   
/*     */   public FieldVector<T> mapDivideToSelf(T d) {
/* 302 */     OpenIntToFieldHashMap<T>.Iterator iter = this.entries.iterator();
/* 303 */     while (iter.hasNext()) {
/* 304 */       iter.advance();
/* 305 */       this.entries.put(iter.key(), (FieldElement)iter.value().divide(d));
/*     */     } 
/* 307 */     return this;
/*     */   }
/*     */   
/*     */   public FieldVector<T> mapInv() {
/* 312 */     return copy().mapInvToSelf();
/*     */   }
/*     */   
/*     */   public FieldVector<T> mapInvToSelf() {
/* 317 */     for (int i = 0; i < this.virtualSize; i++)
/* 318 */       setEntry(i, (T)((FieldElement)this.field.getOne()).divide(getEntry(i))); 
/* 320 */     return this;
/*     */   }
/*     */   
/*     */   public FieldVector<T> mapMultiply(T d) {
/* 325 */     return copy().mapMultiplyToSelf(d);
/*     */   }
/*     */   
/*     */   public FieldVector<T> mapMultiplyToSelf(T d) {
/* 330 */     OpenIntToFieldHashMap<T>.Iterator iter = this.entries.iterator();
/* 331 */     while (iter.hasNext()) {
/* 332 */       iter.advance();
/* 333 */       this.entries.put(iter.key(), (FieldElement)iter.value().multiply(d));
/*     */     } 
/* 335 */     return this;
/*     */   }
/*     */   
/*     */   public FieldVector<T> mapSubtract(T d) {
/* 340 */     return copy().mapSubtractToSelf(d);
/*     */   }
/*     */   
/*     */   public FieldVector<T> mapSubtractToSelf(T d) {
/* 345 */     return mapAddToSelf((T)((FieldElement)this.field.getZero()).subtract(d));
/*     */   }
/*     */   
/*     */   public FieldMatrix<T> outerProduct(SparseFieldVector<T> v) {
/* 356 */     int n = v.getDimension();
/* 357 */     SparseFieldMatrix<T> res = new SparseFieldMatrix<T>(this.field, this.virtualSize, n);
/* 358 */     OpenIntToFieldHashMap<T>.Iterator iter = this.entries.iterator();
/* 359 */     while (iter.hasNext()) {
/* 360 */       iter.advance();
/* 361 */       OpenIntToFieldHashMap<T>.Iterator iter2 = v.entries.iterator();
/* 362 */       while (iter2.hasNext()) {
/* 363 */         iter2.advance();
/* 364 */         res.setEntry(iter.key(), iter2.key(), (T)iter.value().multiply(iter2.value()));
/*     */       } 
/*     */     } 
/* 367 */     return res;
/*     */   }
/*     */   
/*     */   public FieldMatrix<T> outerProduct(FieldVector<T> v) {
/* 372 */     if (v instanceof SparseFieldVector)
/* 373 */       return outerProduct((SparseFieldVector<T>)v); 
/* 375 */     int n = v.getDimension();
/* 376 */     FieldMatrix<T> res = new SparseFieldMatrix<T>(this.field, this.virtualSize, n);
/* 377 */     OpenIntToFieldHashMap<T>.Iterator iter = this.entries.iterator();
/* 378 */     while (iter.hasNext()) {
/* 379 */       iter.advance();
/* 380 */       int row = iter.key();
/* 381 */       FieldElement<T> value = iter.value();
/* 382 */       for (int col = 0; col < n; col++)
/* 383 */         res.setEntry(row, col, (T)value.multiply(v.getEntry(col))); 
/*     */     } 
/* 386 */     return res;
/*     */   }
/*     */   
/*     */   public FieldVector<T> projection(FieldVector<T> v) {
/* 392 */     checkVectorDimensions(v.getDimension());
/* 393 */     return v.mapMultiply((T)dotProduct(v).divide(v.dotProduct(v)));
/*     */   }
/*     */   
/*     */   public void set(T value) {
/* 398 */     for (int i = 0; i < this.virtualSize; i++)
/* 399 */       setEntry(i, value); 
/*     */   }
/*     */   
/*     */   public void setEntry(int index, T value) {
/* 405 */     checkIndex(index);
/* 406 */     this.entries.put(index, (FieldElement)value);
/*     */   }
/*     */   
/*     */   public void setSubVector(int index, FieldVector<T> v) {
/* 411 */     checkIndex(index);
/* 412 */     checkIndex(index + v.getDimension() - 1);
/* 413 */     int n = v.getDimension();
/* 414 */     for (int i = 0; i < n; i++)
/* 415 */       setEntry(i + index, v.getEntry(i)); 
/*     */   }
/*     */   
/*     */   public SparseFieldVector<T> subtract(SparseFieldVector<T> v) {
/* 428 */     checkVectorDimensions(v.getDimension());
/* 429 */     SparseFieldVector<T> res = (SparseFieldVector<T>)copy();
/* 430 */     OpenIntToFieldHashMap<T>.Iterator iter = v.getEntries().iterator();
/* 431 */     while (iter.hasNext()) {
/* 432 */       iter.advance();
/* 433 */       int key = iter.key();
/* 434 */       if (this.entries.containsKey(key)) {
/* 435 */         res.setEntry(key, (T)this.entries.get(key).subtract(iter.value()));
/*     */         continue;
/*     */       } 
/* 437 */       res.setEntry(key, (T)((FieldElement)this.field.getZero()).subtract(iter.value()));
/*     */     } 
/* 440 */     return res;
/*     */   }
/*     */   
/*     */   public FieldVector<T> subtract(FieldVector<T> v) {
/* 445 */     if (v instanceof SparseFieldVector)
/* 446 */       return subtract((SparseFieldVector<T>)v); 
/* 448 */     int n = v.getDimension();
/* 449 */     checkVectorDimensions(n);
/* 450 */     SparseFieldVector<T> res = new SparseFieldVector(this);
/* 451 */     for (int i = 0; i < n; i++) {
/* 452 */       if (this.entries.containsKey(i)) {
/* 453 */         res.setEntry(i, (T)this.entries.get(i).subtract(v.getEntry(i)));
/*     */       } else {
/* 455 */         res.setEntry(i, (T)((FieldElement)this.field.getZero()).subtract(v.getEntry(i)));
/*     */       } 
/*     */     } 
/* 458 */     return res;
/*     */   }
/*     */   
/*     */   public T[] toArray() {
/* 464 */     return getData();
/*     */   }
/*     */   
/*     */   private void checkIndex(int index) {
/* 474 */     if (index < 0 || index >= getDimension())
/* 475 */       throw new OutOfRangeException(Integer.valueOf(index), Integer.valueOf(0), Integer.valueOf(getDimension() - 1)); 
/*     */   }
/*     */   
/*     */   protected void checkVectorDimensions(int n) {
/* 486 */     if (getDimension() != n)
/* 487 */       throw new DimensionMismatchException(getDimension(), n); 
/*     */   }
/*     */   
/*     */   public FieldVector<T> add(FieldVector<T> v) {
/* 493 */     if (v instanceof SparseFieldVector)
/* 494 */       return add((SparseFieldVector<T>)v); 
/* 496 */     int n = v.getDimension();
/* 497 */     checkVectorDimensions(n);
/* 498 */     SparseFieldVector<T> res = new SparseFieldVector(this.field, getDimension());
/* 500 */     for (int i = 0; i < n; i++)
/* 501 */       res.setEntry(i, (T)v.getEntry(i).add(getEntry(i))); 
/* 503 */     return res;
/*     */   }
/*     */   
/*     */   private T[] buildArray(int length) {
/* 515 */     return (T[])Array.newInstance(this.field.getRuntimeClass(), length);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 522 */     int prime = 31;
/* 523 */     int result = 1;
/* 524 */     result = 31 * result + ((this.field == null) ? 0 : this.field.hashCode());
/* 525 */     result = 31 * result + this.virtualSize;
/* 526 */     OpenIntToFieldHashMap<T>.Iterator iter = this.entries.iterator();
/* 527 */     while (iter.hasNext()) {
/* 528 */       iter.advance();
/* 529 */       int temp = iter.value().hashCode();
/* 530 */       result = 31 * result + temp;
/*     */     } 
/* 532 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 540 */     if (this == obj)
/* 541 */       return true; 
/* 544 */     if (!(obj instanceof SparseFieldVector))
/* 545 */       return false; 
/* 550 */     SparseFieldVector<T> other = (SparseFieldVector<T>)obj;
/* 551 */     if (this.field == null) {
/* 552 */       if (other.field != null)
/* 553 */         return false; 
/* 555 */     } else if (!this.field.equals(other.field)) {
/* 556 */       return false;
/*     */     } 
/* 558 */     if (this.virtualSize != other.virtualSize)
/* 559 */       return false; 
/* 562 */     OpenIntToFieldHashMap<T>.Iterator iter = this.entries.iterator();
/* 563 */     while (iter.hasNext()) {
/* 564 */       iter.advance();
/* 565 */       T test = other.getEntry(iter.key());
/* 566 */       if (!test.equals(iter.value()))
/* 567 */         return false; 
/*     */     } 
/* 570 */     iter = other.getEntries().iterator();
/* 571 */     while (iter.hasNext()) {
/* 572 */       iter.advance();
/* 573 */       FieldElement fieldElement = iter.value();
/* 574 */       if (!fieldElement.equals(getEntry(iter.key())))
/* 575 */         return false; 
/*     */     } 
/* 578 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\linear\SparseFieldVector.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */