/*     */ package org.apache.commons.collections.iterators;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.apache.commons.collections.ResettableIterator;
/*     */ 
/*     */ public class ObjectArrayIterator implements Iterator, ResettableIterator {
/*  47 */   protected Object[] array = null;
/*     */   
/*  49 */   protected int startIndex = 0;
/*     */   
/*  51 */   protected int endIndex = 0;
/*     */   
/*  53 */   protected int index = 0;
/*     */   
/*     */   public ObjectArrayIterator() {}
/*     */   
/*     */   public ObjectArrayIterator(Object[] array) {
/*  73 */     this(array, 0, array.length);
/*     */   }
/*     */   
/*     */   public ObjectArrayIterator(Object[] array, int start) {
/*  86 */     this(array, start, array.length);
/*     */   }
/*     */   
/*     */   public ObjectArrayIterator(Object[] array, int start, int end) {
/* 102 */     if (start < 0)
/* 103 */       throw new ArrayIndexOutOfBoundsException("Start index must not be less than zero"); 
/* 105 */     if (end > array.length)
/* 106 */       throw new ArrayIndexOutOfBoundsException("End index must not be greater than the array length"); 
/* 108 */     if (start > array.length)
/* 109 */       throw new ArrayIndexOutOfBoundsException("Start index must not be greater than the array length"); 
/* 111 */     if (end < start)
/* 112 */       throw new IllegalArgumentException("End index must not be less than start index"); 
/* 114 */     this.array = array;
/* 115 */     this.startIndex = start;
/* 116 */     this.endIndex = end;
/* 117 */     this.index = start;
/*     */   }
/*     */   
/*     */   public boolean hasNext() {
/* 129 */     return (this.index < this.endIndex);
/*     */   }
/*     */   
/*     */   public Object next() {
/* 140 */     if (!hasNext())
/* 141 */       throw new NoSuchElementException(); 
/* 143 */     return this.array[this.index++];
/*     */   }
/*     */   
/*     */   public void remove() {
/* 152 */     throw new UnsupportedOperationException("remove() method is not supported for an ObjectArrayIterator");
/*     */   }
/*     */   
/*     */   public Object[] getArray() {
/* 166 */     return this.array;
/*     */   }
/*     */   
/*     */   public void setArray(Object[] array) {
/* 182 */     if (this.array != null)
/* 183 */       throw new IllegalStateException("The array to iterate over has already been set"); 
/* 185 */     this.array = array;
/* 186 */     this.startIndex = 0;
/* 187 */     this.endIndex = array.length;
/* 188 */     this.index = 0;
/*     */   }
/*     */   
/*     */   public int getStartIndex() {
/* 197 */     return this.startIndex;
/*     */   }
/*     */   
/*     */   public int getEndIndex() {
/* 206 */     return this.endIndex;
/*     */   }
/*     */   
/*     */   public void reset() {
/* 213 */     this.index = this.startIndex;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\iterators\ObjectArrayIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */