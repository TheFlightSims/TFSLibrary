/*     */ package org.apache.commons.collections.iterators;
/*     */ 
/*     */ import java.lang.reflect.Array;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.apache.commons.collections.ResettableIterator;
/*     */ 
/*     */ public class ArrayIterator implements ResettableIterator {
/*     */   protected Object array;
/*     */   
/*  49 */   protected int startIndex = 0;
/*     */   
/*  51 */   protected int endIndex = 0;
/*     */   
/*  53 */   protected int index = 0;
/*     */   
/*     */   public ArrayIterator() {}
/*     */   
/*     */   public ArrayIterator(Object array) {
/*  77 */     setArray(array);
/*     */   }
/*     */   
/*     */   public ArrayIterator(Object array, int startIndex) {
/*  92 */     setArray(array);
/*  93 */     checkBound(startIndex, "start");
/*  94 */     this.startIndex = startIndex;
/*  95 */     this.index = startIndex;
/*     */   }
/*     */   
/*     */   public ArrayIterator(Object array, int startIndex, int endIndex) {
/* 111 */     setArray(array);
/* 112 */     checkBound(startIndex, "start");
/* 113 */     checkBound(endIndex, "end");
/* 114 */     if (endIndex < startIndex)
/* 115 */       throw new IllegalArgumentException("End index must not be less than start index."); 
/* 117 */     this.startIndex = startIndex;
/* 118 */     this.endIndex = endIndex;
/* 119 */     this.index = startIndex;
/*     */   }
/*     */   
/*     */   protected void checkBound(int bound, String type) {
/* 130 */     if (bound > this.endIndex)
/* 131 */       throw new ArrayIndexOutOfBoundsException("Attempt to make an ArrayIterator that " + type + "s beyond the end of the array. "); 
/* 136 */     if (bound < 0)
/* 137 */       throw new ArrayIndexOutOfBoundsException("Attempt to make an ArrayIterator that " + type + "s before the start of the array. "); 
/*     */   }
/*     */   
/*     */   public boolean hasNext() {
/* 152 */     return (this.index < this.endIndex);
/*     */   }
/*     */   
/*     */   public Object next() {
/* 163 */     if (!hasNext())
/* 164 */       throw new NoSuchElementException(); 
/* 166 */     return Array.get(this.array, this.index++);
/*     */   }
/*     */   
/*     */   public void remove() {
/* 175 */     throw new UnsupportedOperationException("remove() method is not supported");
/*     */   }
/*     */   
/*     */   public Object getArray() {
/* 188 */     return this.array;
/*     */   }
/*     */   
/*     */   public void setArray(Object array) {
/* 210 */     this.endIndex = Array.getLength(array);
/* 211 */     this.startIndex = 0;
/* 212 */     this.array = array;
/* 213 */     this.index = 0;
/*     */   }
/*     */   
/*     */   public void reset() {
/* 220 */     this.index = this.startIndex;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\iterators\ArrayIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */