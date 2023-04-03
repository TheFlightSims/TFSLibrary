/*     */ package com.vividsolutions.jts.index.bintree;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ public class Bintree {
/*     */   private Root root;
/*     */   
/*     */   public static Interval ensureExtent(Interval itemInterval, double minExtent) {
/*  80 */     double min = itemInterval.getMin();
/*  81 */     double max = itemInterval.getMax();
/*  83 */     if (min != max)
/*  83 */       return itemInterval; 
/*  86 */     if (min == max) {
/*  87 */       min -= minExtent / 2.0D;
/*  88 */       max = min + minExtent / 2.0D;
/*     */     } 
/*  90 */     return new Interval(min, max);
/*     */   }
/*     */   
/* 104 */   private double minExtent = 1.0D;
/*     */   
/*     */   public Bintree() {
/* 108 */     this.root = new Root();
/*     */   }
/*     */   
/*     */   public int depth() {
/* 113 */     if (this.root != null)
/* 113 */       return this.root.depth(); 
/* 114 */     return 0;
/*     */   }
/*     */   
/*     */   public int size() {
/* 118 */     if (this.root != null)
/* 118 */       return this.root.size(); 
/* 119 */     return 0;
/*     */   }
/*     */   
/*     */   public int nodeSize() {
/* 128 */     if (this.root != null)
/* 128 */       return this.root.nodeSize(); 
/* 129 */     return 0;
/*     */   }
/*     */   
/*     */   public void insert(Interval itemInterval, Object item) {
/* 134 */     collectStats(itemInterval);
/* 135 */     Interval insertInterval = ensureExtent(itemInterval, this.minExtent);
/* 137 */     this.root.insert(insertInterval, item);
/*     */   }
/*     */   
/*     */   public boolean remove(Interval itemInterval, Object item) {
/* 158 */     Interval insertInterval = ensureExtent(itemInterval, this.minExtent);
/* 159 */     return this.root.remove(insertInterval, item);
/*     */   }
/*     */   
/*     */   public Iterator iterator() {
/* 164 */     List foundItems = new ArrayList();
/* 165 */     this.root.addAllItems(foundItems);
/* 166 */     return foundItems.iterator();
/*     */   }
/*     */   
/*     */   public List query(double x) {
/* 171 */     return query(new Interval(x, x));
/*     */   }
/*     */   
/*     */   public List query(Interval interval) {
/* 187 */     List foundItems = new ArrayList();
/* 188 */     query(interval, foundItems);
/* 189 */     return foundItems;
/*     */   }
/*     */   
/*     */   public void query(Interval interval, Collection foundItems) {
/* 202 */     this.root.addAllItemsFromOverlapping(interval, foundItems);
/*     */   }
/*     */   
/*     */   private void collectStats(Interval interval) {
/* 207 */     double del = interval.getWidth();
/* 208 */     if (del < this.minExtent && del > 0.0D)
/* 209 */       this.minExtent = del; 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\index\bintree\Bintree.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */