/*     */ package org.apache.commons.collections.iterators;
/*     */ 
/*     */ import java.util.ListIterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.apache.commons.collections.ResettableListIterator;
/*     */ 
/*     */ public class SingletonListIterator implements ListIterator, ResettableListIterator {
/*     */   private boolean beforeFirst = true;
/*     */   
/*     */   private boolean nextCalled = false;
/*     */   
/*     */   private boolean removed = false;
/*     */   
/*     */   private Object object;
/*     */   
/*     */   public SingletonListIterator(Object object) {
/*  48 */     this.object = object;
/*     */   }
/*     */   
/*     */   public boolean hasNext() {
/*  59 */     return (this.beforeFirst && !this.removed);
/*     */   }
/*     */   
/*     */   public boolean hasPrevious() {
/*  70 */     return (!this.beforeFirst && !this.removed);
/*     */   }
/*     */   
/*     */   public int nextIndex() {
/*  80 */     return this.beforeFirst ? 0 : 1;
/*     */   }
/*     */   
/*     */   public int previousIndex() {
/*  91 */     return this.beforeFirst ? -1 : 0;
/*     */   }
/*     */   
/*     */   public Object next() {
/* 104 */     if (!this.beforeFirst || this.removed)
/* 105 */       throw new NoSuchElementException(); 
/* 107 */     this.beforeFirst = false;
/* 108 */     this.nextCalled = true;
/* 109 */     return this.object;
/*     */   }
/*     */   
/*     */   public Object previous() {
/* 122 */     if (this.beforeFirst || this.removed)
/* 123 */       throw new NoSuchElementException(); 
/* 125 */     this.beforeFirst = true;
/* 126 */     return this.object;
/*     */   }
/*     */   
/*     */   public void remove() {
/* 137 */     if (!this.nextCalled || this.removed)
/* 138 */       throw new IllegalStateException(); 
/* 140 */     this.object = null;
/* 141 */     this.removed = true;
/*     */   }
/*     */   
/*     */   public void add(Object obj) {
/* 151 */     throw new UnsupportedOperationException("add() is not supported by this iterator");
/*     */   }
/*     */   
/*     */   public void set(Object obj) {
/* 162 */     if (!this.nextCalled || this.removed)
/* 163 */       throw new IllegalStateException(); 
/* 165 */     this.object = obj;
/*     */   }
/*     */   
/*     */   public void reset() {
/* 172 */     this.beforeFirst = true;
/* 173 */     this.nextCalled = false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\iterators\SingletonListIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */