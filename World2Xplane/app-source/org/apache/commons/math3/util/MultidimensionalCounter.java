/*     */ package org.apache.commons.math3.util;
/*     */ 
/*     */ import org.apache.commons.math3.exception.DimensionMismatchException;
/*     */ import org.apache.commons.math3.exception.NotStrictlyPositiveException;
/*     */ import org.apache.commons.math3.exception.OutOfRangeException;
/*     */ 
/*     */ public class MultidimensionalCounter implements Iterable<Integer> {
/*     */   private final int dimension;
/*     */   
/*     */   private final int[] uniCounterOffset;
/*     */   
/*     */   private final int[] size;
/*     */   
/*     */   private final int totalSize;
/*     */   
/*     */   private final int last;
/*     */   
/*     */   public class Iterator implements java.util.Iterator<Integer> {
/*  74 */     private final int[] counter = new int[MultidimensionalCounter.this.dimension];
/*     */     
/*  78 */     private int count = -1;
/*     */     
/*     */     Iterator() {
/*  85 */       this.counter[MultidimensionalCounter.this.last] = -1;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/*  92 */       for (int i = 0; i < MultidimensionalCounter.this.dimension; i++) {
/*  93 */         if (this.counter[i] != MultidimensionalCounter.this.size[i] - 1)
/*  94 */           return true; 
/*     */       } 
/*  97 */       return false;
/*     */     }
/*     */     
/*     */     public Integer next() {
/* 105 */       for (int i = MultidimensionalCounter.this.last; i >= 0; i--) {
/* 106 */         if (this.counter[i] == MultidimensionalCounter.this.size[i] - 1) {
/* 107 */           this.counter[i] = 0;
/*     */         } else {
/* 109 */           this.counter[i] = this.counter[i] + 1;
/*     */           break;
/*     */         } 
/*     */       } 
/* 114 */       return Integer.valueOf(++this.count);
/*     */     }
/*     */     
/*     */     public int getCount() {
/* 123 */       return this.count;
/*     */     }
/*     */     
/*     */     public int[] getCounts() {
/* 131 */       return MathArrays.copyOf(this.counter);
/*     */     }
/*     */     
/*     */     public int getCount(int dim) {
/* 146 */       return this.counter[dim];
/*     */     }
/*     */     
/*     */     public void remove() {
/* 153 */       throw new UnsupportedOperationException();
/*     */     }
/*     */   }
/*     */   
/*     */   public MultidimensionalCounter(int... size) {
/* 165 */     this.dimension = size.length;
/* 166 */     this.size = MathArrays.copyOf(size);
/* 168 */     this.uniCounterOffset = new int[this.dimension];
/* 170 */     this.last = this.dimension - 1;
/* 171 */     int tS = size[this.last];
/* 172 */     for (int i = 0; i < this.last; i++) {
/* 173 */       int count = 1;
/* 174 */       for (int j = i + 1; j < this.dimension; j++)
/* 175 */         count *= size[j]; 
/* 177 */       this.uniCounterOffset[i] = count;
/* 178 */       tS *= size[i];
/*     */     } 
/* 180 */     this.uniCounterOffset[this.last] = 0;
/* 182 */     if (tS <= 0)
/* 183 */       throw new NotStrictlyPositiveException(Integer.valueOf(tS)); 
/* 186 */     this.totalSize = tS;
/*     */   }
/*     */   
/*     */   public Iterator iterator() {
/* 195 */     return new Iterator();
/*     */   }
/*     */   
/*     */   public int getDimension() {
/* 204 */     return this.dimension;
/*     */   }
/*     */   
/*     */   public int[] getCounts(int index) {
/* 216 */     if (index < 0 || index >= this.totalSize)
/* 218 */       throw new OutOfRangeException(Integer.valueOf(index), Integer.valueOf(0), Integer.valueOf(this.totalSize)); 
/* 221 */     int[] indices = new int[this.dimension];
/* 223 */     int count = 0;
/* 224 */     for (int i = 0; i < this.last; i++) {
/* 225 */       int idx = 0;
/* 226 */       int offset = this.uniCounterOffset[i];
/* 227 */       while (count <= index) {
/* 228 */         count += offset;
/* 229 */         idx++;
/*     */       } 
/* 231 */       idx--;
/* 232 */       count -= offset;
/* 233 */       indices[i] = idx;
/*     */     } 
/* 236 */     indices[this.last] = index - count;
/* 238 */     return indices;
/*     */   }
/*     */   
/*     */   public int getCount(int... c) throws OutOfRangeException {
/* 253 */     if (c.length != this.dimension)
/* 254 */       throw new DimensionMismatchException(c.length, this.dimension); 
/* 256 */     int count = 0;
/* 257 */     for (int i = 0; i < this.dimension; i++) {
/* 258 */       int index = c[i];
/* 259 */       if (index < 0 || index >= this.size[i])
/* 261 */         throw new OutOfRangeException(Integer.valueOf(index), Integer.valueOf(0), Integer.valueOf(this.size[i] - 1)); 
/* 263 */       count += this.uniCounterOffset[i] * c[i];
/*     */     } 
/* 265 */     return count + c[this.last];
/*     */   }
/*     */   
/*     */   public int getSize() {
/* 274 */     return this.totalSize;
/*     */   }
/*     */   
/*     */   public int[] getSizes() {
/* 282 */     return MathArrays.copyOf(this.size);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 290 */     StringBuilder sb = new StringBuilder();
/* 291 */     for (int i = 0; i < this.dimension; i++) {
/* 292 */       sb.append("[").append(getCount(new int[] { i })).append("]");
/*     */     } 
/* 294 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math\\util\MultidimensionalCounter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */