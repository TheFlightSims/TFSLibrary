/*     */ package org.apache.commons.collections.bag;
/*     */ 
/*     */ import java.util.Comparator;
/*     */ import org.apache.commons.collections.Bag;
/*     */ import org.apache.commons.collections.SortedBag;
/*     */ 
/*     */ public class SynchronizedSortedBag extends SynchronizedBag implements SortedBag {
/*     */   private static final long serialVersionUID = 722374056718497858L;
/*     */   
/*     */   public static SortedBag decorate(SortedBag bag) {
/*  52 */     return new SynchronizedSortedBag(bag);
/*     */   }
/*     */   
/*     */   protected SynchronizedSortedBag(SortedBag bag) {
/*  63 */     super((Bag)bag);
/*     */   }
/*     */   
/*     */   protected SynchronizedSortedBag(Bag bag, Object lock) {
/*  74 */     super(bag, lock);
/*     */   }
/*     */   
/*     */   protected SortedBag getSortedBag() {
/*  83 */     return (SortedBag)this.collection;
/*     */   }
/*     */   
/*     */   public synchronized Object first() {
/*  88 */     synchronized (this.lock) {
/*  89 */       return getSortedBag().first();
/*     */     } 
/*     */   }
/*     */   
/*     */   public synchronized Object last() {
/*  94 */     synchronized (this.lock) {
/*  95 */       return getSortedBag().last();
/*     */     } 
/*     */   }
/*     */   
/*     */   public synchronized Comparator comparator() {
/* 100 */     synchronized (this.lock) {
/* 101 */       return getSortedBag().comparator();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\bag\SynchronizedSortedBag.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */