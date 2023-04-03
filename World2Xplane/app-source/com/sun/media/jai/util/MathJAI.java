/*    */ package com.sun.media.jai.util;
/*    */ 
/*    */ public class MathJAI {
/*    */   public static final int nextPositivePowerOf2(int n) {
/* 26 */     if (n < 2)
/* 27 */       return 2; 
/* 30 */     int power = 1;
/* 31 */     while (power < n)
/* 32 */       power <<= 1; 
/* 35 */     return power;
/*    */   }
/*    */   
/*    */   public static final boolean isPositivePowerOf2(int n) {
/* 45 */     return (n == nextPositivePowerOf2(n));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\ja\\util\MathJAI.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */