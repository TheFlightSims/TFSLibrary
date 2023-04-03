/*     */ package org.apache.commons.collections.iterators;
/*     */ 
/*     */ import java.lang.reflect.Array;
/*     */ import java.util.ListIterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.apache.commons.collections.ResettableListIterator;
/*     */ 
/*     */ public class ArrayListIterator extends ArrayIterator implements ListIterator, ResettableListIterator {
/*  57 */   protected int lastItemIndex = -1;
/*     */   
/*     */   public ArrayListIterator() {}
/*     */   
/*     */   public ArrayListIterator(Object array) {
/*  80 */     super(array);
/*     */   }
/*     */   
/*     */   public ArrayListIterator(Object array, int startIndex) {
/*  94 */     super(array, startIndex);
/*  95 */     this.startIndex = startIndex;
/*     */   }
/*     */   
/*     */   public ArrayListIterator(Object array, int startIndex, int endIndex) {
/* 111 */     super(array, startIndex, endIndex);
/* 112 */     this.startIndex = startIndex;
/*     */   }
/*     */   
/*     */   public boolean hasPrevious() {
/* 123 */     return (this.index > this.startIndex);
/*     */   }
/*     */   
/*     */   public Object previous() {
/* 133 */     if (!hasPrevious())
/* 134 */       throw new NoSuchElementException(); 
/* 136 */     this.lastItemIndex = --this.index;
/* 137 */     return Array.get(this.array, this.index);
/*     */   }
/*     */   
/*     */   public Object next() {
/* 147 */     if (!hasNext())
/* 148 */       throw new NoSuchElementException(); 
/* 150 */     this.lastItemIndex = this.index;
/* 151 */     return Array.get(this.array, this.index++);
/*     */   }
/*     */   
/*     */   public int nextIndex() {
/* 160 */     return this.index - this.startIndex;
/*     */   }
/*     */   
/*     */   public int previousIndex() {
/* 169 */     return this.index - this.startIndex - 1;
/*     */   }
/*     */   
/*     */   public void add(Object o) {
/* 180 */     throw new UnsupportedOperationException("add() method is not supported");
/*     */   }
/*     */   
/*     */   public void set(Object o) {
/* 199 */     if (this.lastItemIndex == -1)
/* 200 */       throw new IllegalStateException("must call next() or previous() before a call to set()"); 
/* 203 */     Array.set(this.array, this.lastItemIndex, o);
/*     */   }
/*     */   
/*     */   public void reset() {
/* 210 */     super.reset();
/* 211 */     this.lastItemIndex = -1;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\iterators\ArrayListIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */