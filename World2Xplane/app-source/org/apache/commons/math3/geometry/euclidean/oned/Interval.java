/*    */ package org.apache.commons.math3.geometry.euclidean.oned;
/*    */ 
/*    */ public class Interval {
/*    */   private final double lower;
/*    */   
/*    */   private final double upper;
/*    */   
/*    */   public Interval(double lower, double upper) {
/* 38 */     this.lower = lower;
/* 39 */     this.upper = upper;
/*    */   }
/*    */   
/*    */   public double getLower() {
/* 46 */     return this.lower;
/*    */   }
/*    */   
/*    */   public double getUpper() {
/* 53 */     return this.upper;
/*    */   }
/*    */   
/*    */   public double getLength() {
/* 60 */     return this.upper - this.lower;
/*    */   }
/*    */   
/*    */   public double getMidPoint() {
/* 67 */     return 0.5D * (this.lower + this.upper);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\geometry\euclidean\oned\Interval.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */