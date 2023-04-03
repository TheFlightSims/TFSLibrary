/*     */ package org.apache.commons.collections.iterators;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.apache.commons.collections.ResettableIterator;
/*     */ 
/*     */ public class SingletonIterator implements Iterator, ResettableIterator {
/*     */   private final boolean removeAllowed;
/*     */   
/*     */   private boolean beforeFirst = true;
/*     */   
/*     */   private boolean removed = false;
/*     */   
/*     */   private Object object;
/*     */   
/*     */   public SingletonIterator(Object object) {
/*  54 */     this(object, true);
/*     */   }
/*     */   
/*     */   public SingletonIterator(Object object, boolean removeAllowed) {
/*  67 */     this.object = object;
/*  68 */     this.removeAllowed = removeAllowed;
/*     */   }
/*     */   
/*     */   public boolean hasNext() {
/*  80 */     return (this.beforeFirst && !this.removed);
/*     */   }
/*     */   
/*     */   public Object next() {
/*  93 */     if (!this.beforeFirst || this.removed)
/*  94 */       throw new NoSuchElementException(); 
/*  96 */     this.beforeFirst = false;
/*  97 */     return this.object;
/*     */   }
/*     */   
/*     */   public void remove() {
/* 110 */     if (this.removeAllowed) {
/* 111 */       if (this.removed || this.beforeFirst)
/* 112 */         throw new IllegalStateException(); 
/* 114 */       this.object = null;
/* 115 */       this.removed = true;
/*     */     } else {
/* 118 */       throw new UnsupportedOperationException();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void reset() {
/* 126 */     this.beforeFirst = true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\iterators\SingletonIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */