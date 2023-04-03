/*     */ package com.vividsolutions.jts.index.strtree;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.util.PriorityQueue;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ class BoundablePair implements Comparable {
/*     */   private Boundable boundable1;
/*     */   
/*     */   private Boundable boundable2;
/*     */   
/*     */   private double distance;
/*     */   
/*     */   private ItemDistance itemDistance;
/*     */   
/*     */   public BoundablePair(Boundable boundable1, Boundable boundable2, ItemDistance itemDistance) {
/*  65 */     this.boundable1 = boundable1;
/*  66 */     this.boundable2 = boundable2;
/*  67 */     this.itemDistance = itemDistance;
/*  68 */     this.distance = distance();
/*     */   }
/*     */   
/*     */   public Boundable getBoundable(int i) {
/*  80 */     if (i == 0)
/*  80 */       return this.boundable1; 
/*  81 */     return this.boundable2;
/*     */   }
/*     */   
/*     */   private double distance() {
/*  96 */     if (isLeaves())
/*  97 */       return this.itemDistance.distance((ItemBoundable)this.boundable1, (ItemBoundable)this.boundable2); 
/* 101 */     return ((Envelope)this.boundable1.getBounds()).distance((Envelope)this.boundable2.getBounds());
/*     */   }
/*     */   
/*     */   public double getDistance() {
/* 145 */     return this.distance;
/*     */   }
/*     */   
/*     */   public int compareTo(Object o) {
/* 152 */     BoundablePair nd = (BoundablePair)o;
/* 153 */     if (this.distance < nd.distance)
/* 153 */       return -1; 
/* 154 */     if (this.distance > nd.distance)
/* 154 */       return 1; 
/* 155 */     return 0;
/*     */   }
/*     */   
/*     */   public boolean isLeaves() {
/* 165 */     return (!isComposite(this.boundable1) && !isComposite(this.boundable2));
/*     */   }
/*     */   
/*     */   public static boolean isComposite(Object item) {
/* 170 */     return item instanceof AbstractNode;
/*     */   }
/*     */   
/*     */   private static double area(Boundable b) {
/* 175 */     return ((Envelope)b.getBounds()).getArea();
/*     */   }
/*     */   
/*     */   public void expandToQueue(PriorityQueue priQ, double minDistance) {
/* 187 */     boolean isComp1 = isComposite(this.boundable1);
/* 188 */     boolean isComp2 = isComposite(this.boundable2);
/* 195 */     if (isComp1 && isComp2) {
/* 196 */       if (area(this.boundable1) > area(this.boundable2)) {
/* 197 */         expand(this.boundable1, this.boundable2, priQ, minDistance);
/*     */         return;
/*     */       } 
/* 201 */       expand(this.boundable2, this.boundable1, priQ, minDistance);
/*     */       return;
/*     */     } 
/* 205 */     if (isComp1) {
/* 206 */       expand(this.boundable1, this.boundable2, priQ, minDistance);
/*     */       return;
/*     */     } 
/* 209 */     if (isComp2) {
/* 210 */       expand(this.boundable2, this.boundable1, priQ, minDistance);
/*     */       return;
/*     */     } 
/* 214 */     throw new IllegalArgumentException("neither boundable is composite");
/*     */   }
/*     */   
/*     */   private void expand(Boundable bndComposite, Boundable bndOther, PriorityQueue priQ, double minDistance) {
/* 220 */     List children = ((AbstractNode)bndComposite).getChildBoundables();
/* 221 */     for (Iterator<Boundable> i = children.iterator(); i.hasNext(); ) {
/* 222 */       Boundable child = i.next();
/* 223 */       BoundablePair bp = new BoundablePair(child, bndOther, this.itemDistance);
/* 226 */       if (bp.getDistance() < minDistance)
/* 227 */         priQ.add(bp); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\index\strtree\BoundablePair.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */