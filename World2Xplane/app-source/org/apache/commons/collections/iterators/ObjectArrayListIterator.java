/*     */ package org.apache.commons.collections.iterators;
/*     */ 
/*     */ import java.util.ListIterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.apache.commons.collections.ResettableListIterator;
/*     */ 
/*     */ public class ObjectArrayListIterator extends ObjectArrayIterator implements ListIterator, ResettableListIterator {
/*  53 */   protected int lastItemIndex = -1;
/*     */   
/*     */   public ObjectArrayListIterator() {}
/*     */   
/*     */   public ObjectArrayListIterator(Object[] array) {
/*  73 */     super(array);
/*     */   }
/*     */   
/*     */   public ObjectArrayListIterator(Object[] array, int start) {
/*  86 */     super(array, start);
/*     */   }
/*     */   
/*     */   public ObjectArrayListIterator(Object[] array, int start, int end) {
/* 101 */     super(array, start, end);
/*     */   }
/*     */   
/*     */   public boolean hasPrevious() {
/* 113 */     return (this.index > this.startIndex);
/*     */   }
/*     */   
/*     */   public Object previous() {
/* 123 */     if (!hasPrevious())
/* 124 */       throw new NoSuchElementException(); 
/* 126 */     this.lastItemIndex = --this.index;
/* 127 */     return this.array[this.index];
/*     */   }
/*     */   
/*     */   public Object next() {
/* 137 */     if (!hasNext())
/* 138 */       throw new NoSuchElementException(); 
/* 140 */     this.lastItemIndex = this.index;
/* 141 */     return this.array[this.index++];
/*     */   }
/*     */   
/*     */   public int nextIndex() {
/* 150 */     return this.index - this.startIndex;
/*     */   }
/*     */   
/*     */   public int previousIndex() {
/* 159 */     return this.index - this.startIndex - 1;
/*     */   }
/*     */   
/*     */   public void add(Object obj) {
/* 170 */     throw new UnsupportedOperationException("add() method is not supported");
/*     */   }
/*     */   
/*     */   public void set(Object obj) {
/* 191 */     if (this.lastItemIndex == -1)
/* 192 */       throw new IllegalStateException("must call next() or previous() before a call to set()"); 
/* 195 */     this.array[this.lastItemIndex] = obj;
/*     */   }
/*     */   
/*     */   public void reset() {
/* 202 */     super.reset();
/* 203 */     this.lastItemIndex = -1;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\iterators\ObjectArrayListIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */