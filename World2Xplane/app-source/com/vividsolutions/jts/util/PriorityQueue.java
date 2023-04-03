/*     */ package com.vividsolutions.jts.util;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class PriorityQueue {
/*     */   private int size;
/*     */   
/*     */   private ArrayList items;
/*     */   
/*     */   public PriorityQueue() {
/*  52 */     this.size = 0;
/*  53 */     this.items = new ArrayList();
/*  55 */     this.items.add(null);
/*     */   }
/*     */   
/*     */   public void add(Comparable x) {
/*  66 */     this.items.add(null);
/*  70 */     int hole = ++this.size;
/*  72 */     this.items.set(0, x);
/*  75 */     for (; x.compareTo(this.items.get(hole / 2)) < 0; hole /= 2)
/*  76 */       this.items.set(hole, this.items.get(hole / 2)); 
/*  79 */     this.items.set(hole, x);
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*  97 */     return (this.size == 0);
/*     */   }
/*     */   
/*     */   public int size() {
/* 105 */     return this.size;
/*     */   }
/*     */   
/*     */   public void clear() {
/* 112 */     this.size = 0;
/* 113 */     this.items.clear();
/*     */   }
/*     */   
/*     */   public Object poll() {
/* 122 */     if (isEmpty())
/* 123 */       return null; 
/* 124 */     Object minItem = this.items.get(1);
/* 125 */     this.items.set(1, this.items.get(this.size));
/* 126 */     this.size--;
/* 127 */     reorder(1);
/* 129 */     return minItem;
/*     */   }
/*     */   
/*     */   private void reorder(int hole) {
/* 140 */     Object tmp = this.items.get(hole);
/* 142 */     while (hole * 2 <= this.size) {
/* 143 */       int child = hole * 2;
/* 144 */       if (child != this.size && ((Comparable)this.items.get(child + 1)).compareTo(this.items.get(child)) < 0)
/* 146 */         child++; 
/* 147 */       if (((Comparable<Object>)this.items.get(child)).compareTo(tmp) < 0) {
/* 148 */         this.items.set(hole, this.items.get(child));
/*     */         hole = child;
/*     */       } 
/*     */     } 
/* 152 */     this.items.set(hole, tmp);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jt\\util\PriorityQueue.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */