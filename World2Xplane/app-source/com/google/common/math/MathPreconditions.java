/*    */ package com.google.common.math;
/*    */ 
/*    */ import com.google.common.annotations.GwtCompatible;
/*    */ import java.math.BigInteger;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ @GwtCompatible
/*    */ final class MathPreconditions {
/*    */   static int checkPositive(@Nullable String role, int x) {
/* 31 */     if (x <= 0)
/* 32 */       throw new IllegalArgumentException(role + " (" + x + ") must be > 0"); 
/* 34 */     return x;
/*    */   }
/*    */   
/*    */   static long checkPositive(@Nullable String role, long x) {
/* 38 */     if (x <= 0L)
/* 39 */       throw new IllegalArgumentException(role + " (" + x + ") must be > 0"); 
/* 41 */     return x;
/*    */   }
/*    */   
/*    */   static BigInteger checkPositive(@Nullable String role, BigInteger x) {
/* 45 */     if (x.signum() <= 0)
/* 46 */       throw new IllegalArgumentException(role + " (" + x + ") must be > 0"); 
/* 48 */     return x;
/*    */   }
/*    */   
/*    */   static int checkNonNegative(@Nullable String role, int x) {
/* 52 */     if (x < 0)
/* 53 */       throw new IllegalArgumentException(role + " (" + x + ") must be >= 0"); 
/* 55 */     return x;
/*    */   }
/*    */   
/*    */   static long checkNonNegative(@Nullable String role, long x) {
/* 59 */     if (x < 0L)
/* 60 */       throw new IllegalArgumentException(role + " (" + x + ") must be >= 0"); 
/* 62 */     return x;
/*    */   }
/*    */   
/*    */   static BigInteger checkNonNegative(@Nullable String role, BigInteger x) {
/* 66 */     if (x.signum() < 0)
/* 67 */       throw new IllegalArgumentException(role + " (" + x + ") must be >= 0"); 
/* 69 */     return x;
/*    */   }
/*    */   
/*    */   static double checkNonNegative(@Nullable String role, double x) {
/* 73 */     if (x < 0.0D)
/* 74 */       throw new IllegalArgumentException(role + " (" + x + ") must be >= 0"); 
/* 76 */     return x;
/*    */   }
/*    */   
/*    */   static void checkRoundingUnnecessary(boolean condition) {
/* 80 */     if (!condition)
/* 81 */       throw new ArithmeticException("mode was UNNECESSARY, but rounding was necessary"); 
/*    */   }
/*    */   
/*    */   static void checkInRange(boolean condition) {
/* 86 */     if (!condition)
/* 87 */       throw new ArithmeticException("not in range"); 
/*    */   }
/*    */   
/*    */   static void checkNoOverflow(boolean condition) {
/* 92 */     if (!condition)
/* 93 */       throw new ArithmeticException("overflow"); 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\google\common\math\MathPreconditions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */