/*    */ package com.vividsolutions.jts.index.sweepline;
/*    */ 
/*    */ public class SweepLineInterval {
/*    */   private double min;
/*    */   
/*    */   private double max;
/*    */   
/*    */   private Object item;
/*    */   
/*    */   public SweepLineInterval(double min, double max) {
/* 47 */     this(min, max, null);
/*    */   }
/*    */   
/*    */   public SweepLineInterval(double min, double max, Object item) {
/* 52 */     this.min = (min < max) ? min : max;
/* 53 */     this.max = (max > min) ? max : min;
/* 54 */     this.item = item;
/*    */   }
/*    */   
/*    */   public double getMin() {
/* 57 */     return this.min;
/*    */   }
/*    */   
/*    */   public double getMax() {
/* 58 */     return this.max;
/*    */   }
/*    */   
/*    */   public Object getItem() {
/* 59 */     return this.item;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\index\sweepline\SweepLineInterval.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */