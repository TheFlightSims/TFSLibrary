/*    */ package com.vividsolutions.jts.index.bintree;
/*    */ 
/*    */ import com.vividsolutions.jts.index.quadtree.DoubleBits;
/*    */ 
/*    */ public class Key {
/*    */   public static int computeLevel(Interval interval) {
/* 51 */     double dx = interval.getWidth();
/* 53 */     int level = DoubleBits.exponent(dx) + 1;
/* 54 */     return level;
/*    */   }
/*    */   
/* 59 */   private double pt = 0.0D;
/*    */   
/* 60 */   private int level = 0;
/*    */   
/*    */   private Interval interval;
/*    */   
/*    */   public Key(Interval interval) {
/* 66 */     computeKey(interval);
/*    */   }
/*    */   
/*    */   public double getPoint() {
/* 69 */     return this.pt;
/*    */   }
/*    */   
/*    */   public int getLevel() {
/* 70 */     return this.level;
/*    */   }
/*    */   
/*    */   public Interval getInterval() {
/* 71 */     return this.interval;
/*    */   }
/*    */   
/*    */   public void computeKey(Interval itemInterval) {
/* 79 */     this.level = computeLevel(itemInterval);
/* 80 */     this.interval = new Interval();
/* 81 */     computeInterval(this.level, itemInterval);
/* 83 */     while (!this.interval.contains(itemInterval)) {
/* 84 */       this.level++;
/* 85 */       computeInterval(this.level, itemInterval);
/*    */     } 
/*    */   }
/*    */   
/*    */   private void computeInterval(int level, Interval itemInterval) {
/* 91 */     double size = DoubleBits.powerOf2(level);
/* 93 */     this.pt = Math.floor(itemInterval.getMin() / size) * size;
/* 94 */     this.interval.init(this.pt, this.pt + size);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\index\bintree\Key.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */