/*    */ package org.poly2tri.geometry.primitives;
/*    */ 
/*    */ public abstract class Point {
/*    */   public abstract double getX();
/*    */   
/*    */   public abstract double getY();
/*    */   
/*    */   public abstract double getZ();
/*    */   
/*    */   public abstract float getXf();
/*    */   
/*    */   public abstract float getYf();
/*    */   
/*    */   public abstract float getZf();
/*    */   
/*    */   public abstract void set(double paramDouble1, double paramDouble2, double paramDouble3);
/*    */   
/*    */   protected static int calculateHashCode(double x, double y, double z) {
/* 39 */     int result = 17;
/* 41 */     long a = Double.doubleToLongBits(x);
/* 42 */     result += 31 * result + (int)(a ^ a >>> 32L);
/* 44 */     long b = Double.doubleToLongBits(y);
/* 45 */     result += 31 * result + (int)(b ^ b >>> 32L);
/* 47 */     long c = Double.doubleToLongBits(z);
/* 48 */     result += 31 * result + (int)(c ^ c >>> 32L);
/* 50 */     return result;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\poly2tri\geometry\primitives\Point.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */