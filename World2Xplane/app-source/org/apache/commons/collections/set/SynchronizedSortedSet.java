/*     */ package org.apache.commons.collections.set;
/*     */ 
/*     */ import java.util.Comparator;
/*     */ import java.util.SortedSet;
/*     */ import org.apache.commons.collections.collection.SynchronizedCollection;
/*     */ 
/*     */ public class SynchronizedSortedSet extends SynchronizedCollection implements SortedSet {
/*     */   private static final long serialVersionUID = 2775582861954500111L;
/*     */   
/*     */   public static SortedSet decorate(SortedSet set) {
/*  49 */     return new SynchronizedSortedSet(set);
/*     */   }
/*     */   
/*     */   protected SynchronizedSortedSet(SortedSet set) {
/*  60 */     super(set);
/*     */   }
/*     */   
/*     */   protected SynchronizedSortedSet(SortedSet set, Object lock) {
/*  71 */     super(set, lock);
/*     */   }
/*     */   
/*     */   protected SortedSet getSortedSet() {
/*  80 */     return (SortedSet)this.collection;
/*     */   }
/*     */   
/*     */   public SortedSet subSet(Object fromElement, Object toElement) {
/*  85 */     synchronized (this.lock) {
/*  86 */       SortedSet set = getSortedSet().subSet(fromElement, toElement);
/*  89 */       return new SynchronizedSortedSet(set, this.lock);
/*     */     } 
/*     */   }
/*     */   
/*     */   public SortedSet headSet(Object toElement) {
/*  94 */     synchronized (this.lock) {
/*  95 */       SortedSet set = getSortedSet().headSet(toElement);
/*  98 */       return new SynchronizedSortedSet(set, this.lock);
/*     */     } 
/*     */   }
/*     */   
/*     */   public SortedSet tailSet(Object fromElement) {
/* 103 */     synchronized (this.lock) {
/* 104 */       SortedSet set = getSortedSet().tailSet(fromElement);
/* 107 */       return new SynchronizedSortedSet(set, this.lock);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object first() {
/* 112 */     synchronized (this.lock) {
/* 113 */       return getSortedSet().first();
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object last() {
/* 118 */     synchronized (this.lock) {
/* 119 */       return getSortedSet().last();
/*     */     } 
/*     */   }
/*     */   
/*     */   public Comparator comparator() {
/* 124 */     synchronized (this.lock) {
/* 125 */       return getSortedSet().comparator();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\set\SynchronizedSortedSet.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */