/*    */ package org.jfree.data.function;
/*    */ 
/*    */ public class PowerFunction2D implements Function2D {
/*    */   private double a;
/*    */   
/*    */   private double b;
/*    */   
/*    */   public PowerFunction2D(double a, double b) {
/* 64 */     this.a = a;
/* 65 */     this.b = b;
/*    */   }
/*    */   
/*    */   public double getValue(double x) {
/* 76 */     return this.a * Math.pow(x, this.b);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\function\PowerFunction2D.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */