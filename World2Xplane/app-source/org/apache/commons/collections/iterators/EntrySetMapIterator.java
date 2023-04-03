/*     */ package org.apache.commons.collections.iterators;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.collections.MapIterator;
/*     */ import org.apache.commons.collections.ResettableIterator;
/*     */ 
/*     */ public class EntrySetMapIterator implements MapIterator, ResettableIterator {
/*     */   private final Map map;
/*     */   
/*     */   private Iterator iterator;
/*     */   
/*     */   private Map.Entry last;
/*     */   
/*     */   private boolean canRemove = false;
/*     */   
/*     */   public EntrySetMapIterator(Map map) {
/*  56 */     this.map = map;
/*  57 */     this.iterator = map.entrySet().iterator();
/*     */   }
/*     */   
/*     */   public boolean hasNext() {
/*  67 */     return this.iterator.hasNext();
/*     */   }
/*     */   
/*     */   public Object next() {
/*  77 */     this.last = this.iterator.next();
/*  78 */     this.canRemove = true;
/*  79 */     return this.last.getKey();
/*     */   }
/*     */   
/*     */   public void remove() {
/*  94 */     if (!this.canRemove)
/*  95 */       throw new IllegalStateException("Iterator remove() can only be called once after next()"); 
/*  97 */     this.iterator.remove();
/*  98 */     this.last = null;
/*  99 */     this.canRemove = false;
/*     */   }
/*     */   
/*     */   public Object getKey() {
/* 111 */     if (this.last == null)
/* 112 */       throw new IllegalStateException("Iterator getKey() can only be called after next() and before remove()"); 
/* 114 */     return this.last.getKey();
/*     */   }
/*     */   
/*     */   public Object getValue() {
/* 125 */     if (this.last == null)
/* 126 */       throw new IllegalStateException("Iterator getValue() can only be called after next() and before remove()"); 
/* 128 */     return this.last.getValue();
/*     */   }
/*     */   
/*     */   public Object setValue(Object value) {
/* 142 */     if (this.last == null)
/* 143 */       throw new IllegalStateException("Iterator setValue() can only be called after next() and before remove()"); 
/* 145 */     return this.last.setValue(value);
/*     */   }
/*     */   
/*     */   public void reset() {
/* 153 */     this.iterator = this.map.entrySet().iterator();
/* 154 */     this.last = null;
/* 155 */     this.canRemove = false;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 164 */     if (this.last != null)
/* 165 */       return "MapIterator[" + getKey() + "=" + getValue() + "]"; 
/* 167 */     return "MapIterator[]";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\iterators\EntrySetMapIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */