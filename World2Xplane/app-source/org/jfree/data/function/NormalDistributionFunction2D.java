/*    */ package org.jfree.data.function;
/*    */ 
/*    */ public class NormalDistributionFunction2D implements Function2D {
/*    */   private double mean;
/*    */   
/*    */   private double std;
/*    */   
/*    */   public NormalDistributionFunction2D(double mean, double std) {
/* 64 */     this.mean = mean;
/* 65 */     this.std = std;
/*    */   }
/*    */   
/*    */   public double getMean() {
/* 74 */     return this.mean;
/*    */   }
/*    */   
/*    */   public double getStandardDeviation() {
/* 83 */     return this.std;
/*    */   }
/*    */   
/*    */   public double getValue(double x) {
/* 94 */     return Math.exp(-1.0D * (x - this.mean) * (x - this.mean) / 2.0D * this.std * this.std) / Math.sqrt(6.283185307179586D * this.std * this.std);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\function\NormalDistributionFunction2D.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */