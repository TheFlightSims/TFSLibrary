/*     */ package org.apache.commons.collections.iterators;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.apache.commons.collections.ResettableIterator;
/*     */ 
/*     */ public class LoopingIterator implements ResettableIterator {
/*     */   private Collection collection;
/*     */   
/*     */   private Iterator iterator;
/*     */   
/*     */   public LoopingIterator(Collection coll) {
/*  58 */     if (coll == null)
/*  59 */       throw new NullPointerException("The collection must not be null"); 
/*  61 */     this.collection = coll;
/*  62 */     reset();
/*     */   }
/*     */   
/*     */   public boolean hasNext() {
/*  74 */     return (this.collection.size() > 0);
/*     */   }
/*     */   
/*     */   public Object next() {
/*  86 */     if (this.collection.size() == 0)
/*  87 */       throw new NoSuchElementException("There are no elements for this iterator to loop on"); 
/*  89 */     if (!this.iterator.hasNext())
/*  90 */       reset(); 
/*  92 */     return this.iterator.next();
/*     */   }
/*     */   
/*     */   public void remove() {
/* 108 */     this.iterator.remove();
/*     */   }
/*     */   
/*     */   public void reset() {
/* 115 */     this.iterator = this.collection.iterator();
/*     */   }
/*     */   
/*     */   public int size() {
/* 124 */     return this.collection.size();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\iterators\LoopingIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */