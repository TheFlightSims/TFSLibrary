/*    */ package org.jfree.data.function;
/*    */ 
/*    */ public class LineFunction2D implements Function2D {
/*    */   private double a;
/*    */   
/*    */   private double b;
/*    */   
/*    */   public LineFunction2D(double a, double b) {
/* 64 */     this.a = a;
/* 65 */     this.b = b;
/*    */   }
/*    */   
/*    */   public double getValue(double x) {
/* 76 */     return this.a + this.b * x;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\function\LineFunction2D.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */