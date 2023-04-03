/*     */ package org.geotools.util;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.AbstractList;
/*     */ import java.util.Arrays;
/*     */ import java.util.RandomAccess;
/*     */ import org.geotools.resources.XArray;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.opengis.util.Cloneable;
/*     */ 
/*     */ public class IntegerList extends AbstractList<Integer> implements RandomAccess, Serializable, Cloneable {
/*     */   private static final long serialVersionUID = 1241962316404811189L;
/*     */   
/*     */   private static final int VALUE_SIZE = 64;
/*     */   
/*     */   private static final int BASE_SHIFT = 6;
/*     */   
/*     */   private static final int OFFSET_MASK = 63;
/*     */   
/*     */   private long[] values;
/*     */   
/*     */   private final int bitCount;
/*     */   
/*     */   private final int mask;
/*     */   
/*     */   private int size;
/*     */   
/*     */   public IntegerList(int initialCapacity, int maximalValue) {
/*  94 */     this(initialCapacity, maximalValue, false);
/*     */   }
/*     */   
/*     */   public IntegerList(int initialCapacity, int maximalValue, boolean fill) {
/* 107 */     if (initialCapacity <= 0)
/* 108 */       throw new IllegalArgumentException(Errors.format(125, Integer.valueOf(initialCapacity))); 
/* 111 */     if (maximalValue <= 0)
/* 112 */       throw new IllegalArgumentException(Errors.format(125, Integer.valueOf(maximalValue))); 
/* 115 */     int bitCount = 0;
/*     */     while (true) {
/* 117 */       bitCount++;
/* 118 */       maximalValue >>>= 1;
/* 119 */       if (maximalValue == 0) {
/* 120 */         this.bitCount = bitCount;
/* 121 */         this.mask = (1 << bitCount) - 1;
/* 122 */         this.values = new long[length(initialCapacity)];
/* 123 */         if (fill)
/* 124 */           this.size = initialCapacity; 
/*     */         return;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private int length(int size) {
/* 135 */     size *= this.bitCount;
/* 136 */     int length = size >>> 6;
/* 137 */     if ((size & 0x3F) != 0)
/* 138 */       length++; 
/* 140 */     return length;
/*     */   }
/*     */   
/*     */   public int maximalValue() {
/* 150 */     return this.mask;
/*     */   }
/*     */   
/*     */   public int size() {
/* 159 */     return this.size;
/*     */   }
/*     */   
/*     */   public void resize(int size) {
/* 170 */     if (size < 0)
/* 171 */       throw new IllegalArgumentException(); 
/* 173 */     if (size > this.size) {
/* 174 */       int base = this.size * this.bitCount;
/* 175 */       int offset = base & 0x3F;
/* 176 */       base >>>= 6;
/* 177 */       if (offset != 0 && base < this.values.length) {
/* 178 */         this.values[base] = this.values[base] & (1L << offset) - 1L;
/* 179 */         base++;
/*     */       } 
/* 181 */       int length = length(size);
/* 182 */       Arrays.fill(this.values, base, Math.min(length, this.values.length), 0L);
/* 183 */       if (length > this.values.length)
/* 184 */         this.values = XArray.resize(this.values, length); 
/*     */     } 
/* 187 */     this.size = size;
/*     */   }
/*     */   
/*     */   public void fill(int value) {
/*     */     long p;
/* 198 */     if (value < 0 || value > this.mask)
/* 199 */       throw new IllegalArgumentException(Errors.format(201, Integer.valueOf(value), Integer.valueOf(0), Integer.valueOf(this.mask))); 
/* 203 */     if (value == 0) {
/* 204 */       p = 0L;
/* 205 */     } else if (value == this.mask) {
/* 206 */       p = -1L;
/*     */     } else {
/*     */       int i;
/* 207 */       switch (this.bitCount) {
/*     */         case 1:
/* 208 */           value |= value << 1;
/*     */         case 2:
/* 209 */           value |= value << 2;
/*     */         case 4:
/* 210 */           value |= value << 4;
/*     */         case 8:
/* 211 */           value |= value << 8;
/*     */         case 16:
/* 212 */           value |= value << 16;
/*     */         case 32:
/* 213 */           p = value & 0xFFFFFFFFL | value << 32L;
/*     */           break;
/*     */         default:
/* 215 */           for (i = 0; i < this.size; i++)
/* 216 */             setUnchecked(i, value); 
/*     */           return;
/*     */       } 
/*     */     } 
/* 221 */     Arrays.fill(this.values, 0, length(this.size), p);
/*     */   }
/*     */   
/*     */   public void clear() {
/* 229 */     this.size = 0;
/*     */   }
/*     */   
/*     */   public boolean add(Integer value) throws IllegalArgumentException {
/* 241 */     addInteger(value.intValue());
/* 242 */     return true;
/*     */   }
/*     */   
/*     */   public void addInteger(int value) throws IllegalArgumentException {
/* 252 */     if (value < 0 || value > this.mask)
/* 253 */       throw new IllegalArgumentException(Errors.format(201, Integer.valueOf(value), Integer.valueOf(0), Integer.valueOf(this.mask))); 
/* 256 */     int length = length(++this.size);
/* 257 */     if (length > this.values.length)
/* 258 */       this.values = XArray.resize(this.values, 2 * this.values.length); 
/* 260 */     setUnchecked(this.size - 1, value);
/*     */   }
/*     */   
/*     */   public Integer get(int index) throws IndexOutOfBoundsException {
/* 271 */     return Integer.valueOf(getInteger(index));
/*     */   }
/*     */   
/*     */   public int getInteger(int index) throws IndexOutOfBoundsException {
/* 282 */     if (index < 0 || index >= this.size)
/* 283 */       throw new IndexOutOfBoundsException(Errors.format(79, Integer.valueOf(index))); 
/* 285 */     index *= this.bitCount;
/* 286 */     int base = index >>> 6;
/* 287 */     int offset = index & 0x3F;
/* 288 */     int value = (int)(this.values[base] >>> offset);
/* 289 */     offset = 64 - offset;
/* 290 */     if (offset < this.bitCount) {
/* 291 */       int high = (int)this.values[++base];
/* 292 */       value |= high << offset;
/*     */     } 
/* 294 */     value &= this.mask;
/* 295 */     return value;
/*     */   }
/*     */   
/*     */   public Integer set(int index, Integer value) throws IndexOutOfBoundsException {
/* 310 */     Integer old = get(index);
/* 311 */     setInteger(index, value.intValue());
/* 312 */     return old;
/*     */   }
/*     */   
/*     */   public void setInteger(int index, int value) throws IndexOutOfBoundsException {
/* 324 */     if (index < 0 || index >= this.size)
/* 325 */       throw new IndexOutOfBoundsException(Errors.format(79, Integer.valueOf(index))); 
/* 327 */     if (value < 0 || value > this.mask)
/* 328 */       throw new IllegalArgumentException(Errors.format(201, Integer.valueOf(value), Integer.valueOf(0), Integer.valueOf(this.mask))); 
/* 331 */     setUnchecked(index, value);
/*     */   }
/*     */   
/*     */   private void setUnchecked(int index, int value) {
/* 344 */     index *= this.bitCount;
/* 345 */     int base = index >>> 6;
/* 346 */     int offset = index & 0x3F;
/* 347 */     this.values[base] = this.values[base] & (this.mask << offset ^ 0xFFFFFFFFFFFFFFFFL);
/* 348 */     this.values[base] = this.values[base] | value << offset;
/* 349 */     offset = 64 - offset;
/* 350 */     if (offset < this.bitCount) {
/* 351 */       value >>>= offset;
/* 352 */       this.values[++base] = this.values[++base] & (this.mask >>> offset ^ 0xFFFFFFFFFFFFFFFFL);
/* 353 */       this.values[base] = this.values[base] | value;
/*     */     } 
/*     */   }
/*     */   
/*     */   public int occurence(int value) {
/* 364 */     int count = 0;
/* 365 */     int size = size();
/* 366 */     for (int i = 0; i < size; i++) {
/* 367 */       if (getInteger(i) == value)
/* 368 */         count++; 
/*     */     } 
/* 371 */     return count;
/*     */   }
/*     */   
/*     */   public void trimToSize() {
/* 378 */     this.values = XArray.resize(this.values, length(this.size));
/*     */   }
/*     */   
/*     */   public IntegerList clone() {
/*     */     IntegerList clone;
/*     */     try {
/* 390 */       clone = (IntegerList)super.clone();
/* 391 */     } catch (CloneNotSupportedException e) {
/* 392 */       throw new AssertionError(e);
/*     */     } 
/* 394 */     clone.values = (long[])clone.values.clone();
/* 395 */     return clone;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\IntegerList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */