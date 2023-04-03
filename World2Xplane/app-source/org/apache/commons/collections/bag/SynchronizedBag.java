/*     */ package org.apache.commons.collections.bag;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections.Bag;
/*     */ import org.apache.commons.collections.collection.SynchronizedCollection;
/*     */ import org.apache.commons.collections.set.SynchronizedSet;
/*     */ 
/*     */ public class SynchronizedBag extends SynchronizedCollection implements Bag {
/*     */   private static final long serialVersionUID = 8084674570753837109L;
/*     */   
/*     */   public static Bag decorate(Bag bag) {
/*  53 */     return new SynchronizedBag(bag);
/*     */   }
/*     */   
/*     */   protected SynchronizedBag(Bag bag) {
/*  64 */     super((Collection)bag);
/*     */   }
/*     */   
/*     */   protected SynchronizedBag(Bag bag, Object lock) {
/*  75 */     super((Collection)bag, lock);
/*     */   }
/*     */   
/*     */   protected Bag getBag() {
/*  84 */     return (Bag)this.collection;
/*     */   }
/*     */   
/*     */   public boolean add(Object object, int count) {
/*  89 */     synchronized (this.lock) {
/*  90 */       return getBag().add(object, count);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean remove(Object object, int count) {
/*  95 */     synchronized (this.lock) {
/*  96 */       return getBag().remove(object, count);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Set uniqueSet() {
/* 101 */     synchronized (this.lock) {
/* 102 */       Set set = getBag().uniqueSet();
/* 103 */       return (Set)new SynchronizedBagSet(this, set, this.lock);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getCount(Object object) {
/* 108 */     synchronized (this.lock) {
/* 109 */       return getBag().getCount(object);
/*     */     } 
/*     */   }
/*     */   
/*     */   class SynchronizedBagSet extends SynchronizedSet {
/*     */     private final SynchronizedBag this$0;
/*     */     
/*     */     SynchronizedBagSet(SynchronizedBag this$0, Set set, Object lock) {
/* 124 */       super(set, lock);
/*     */       this.this$0 = this$0;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\bag\SynchronizedBag.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */