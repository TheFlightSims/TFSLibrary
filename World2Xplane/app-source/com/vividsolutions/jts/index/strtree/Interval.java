/*    */ package com.vividsolutions.jts.index.strtree;
/*    */ 
/*    */ import com.vividsolutions.jts.util.Assert;
/*    */ 
/*    */ public class Interval {
/*    */   private double min;
/*    */   
/*    */   private double max;
/*    */   
/*    */   public Interval(Interval other) {
/* 47 */     this(other.min, other.max);
/*    */   }
/*    */   
/*    */   public Interval(double min, double max) {
/* 51 */     Assert.isTrue((min <= max));
/* 52 */     this.min = min;
/* 53 */     this.max = max;
/*    */   }
/*    */   
/*    */   public double getCentre() {
/* 59 */     return (this.min + this.max) / 2.0D;
/*    */   }
/*    */   
/*    */   public Interval expandToInclude(Interval other) {
/* 65 */     this.max = Math.max(this.max, other.max);
/* 66 */     this.min = Math.min(this.min, other.min);
/* 67 */     return this;
/*    */   }
/*    */   
/*    */   public boolean intersects(Interval other) {
/* 71 */     return (other.min <= this.max && other.max >= this.min);
/*    */   }
/*    */   
/*    */   public boolean equals(Object o) {
/* 74 */     if (!(o instanceof Interval))
/* 74 */       return false; 
/* 75 */     Interval other = (Interval)o;
/* 76 */     return (this.min == other.min && this.max == other.max);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\index\strtree\Interval.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */