/*     */ package org.apache.commons.collections.iterators;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import org.apache.commons.collections.ResettableListIterator;
/*     */ 
/*     */ public class ReverseListIterator implements ResettableListIterator {
/*     */   private final List list;
/*     */   
/*     */   private ListIterator iterator;
/*     */   
/*     */   private boolean validForUpdate = true;
/*     */   
/*     */   public ReverseListIterator(List list) {
/*  58 */     this.list = list;
/*  59 */     this.iterator = list.listIterator(list.size());
/*     */   }
/*     */   
/*     */   public boolean hasNext() {
/*  69 */     return this.iterator.hasPrevious();
/*     */   }
/*     */   
/*     */   public Object next() {
/*  79 */     Object obj = this.iterator.previous();
/*  80 */     this.validForUpdate = true;
/*  81 */     return obj;
/*     */   }
/*     */   
/*     */   public int nextIndex() {
/*  90 */     return this.iterator.previousIndex();
/*     */   }
/*     */   
/*     */   public boolean hasPrevious() {
/*  99 */     return this.iterator.hasNext();
/*     */   }
/*     */   
/*     */   public Object previous() {
/* 109 */     Object obj = this.iterator.next();
/* 110 */     this.validForUpdate = true;
/* 111 */     return obj;
/*     */   }
/*     */   
/*     */   public int previousIndex() {
/* 120 */     return this.iterator.nextIndex();
/*     */   }
/*     */   
/*     */   public void remove() {
/* 130 */     if (!this.validForUpdate)
/* 131 */       throw new IllegalStateException("Cannot remove from list until next() or previous() called"); 
/* 133 */     this.iterator.remove();
/*     */   }
/*     */   
/*     */   public void set(Object obj) {
/* 144 */     if (!this.validForUpdate)
/* 145 */       throw new IllegalStateException("Cannot set to list until next() or previous() called"); 
/* 147 */     this.iterator.set(obj);
/*     */   }
/*     */   
/*     */   public void add(Object obj) {
/* 160 */     if (!this.validForUpdate)
/* 161 */       throw new IllegalStateException("Cannot add to list until next() or previous() called"); 
/* 163 */     this.validForUpdate = false;
/* 164 */     this.iterator.add(obj);
/* 165 */     this.iterator.previous();
/*     */   }
/*     */   
/*     */   public void reset() {
/* 173 */     this.iterator = this.list.listIterator(this.list.size());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\iterators\ReverseListIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */