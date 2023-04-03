/*    */ package math.utils;
/*    */ 
/*    */ public final class EqualUtils {
/*    */   public static boolean areEqual(boolean aThis, boolean aThat) {
/* 34 */     return (aThis == aThat);
/*    */   }
/*    */   
/*    */   public static boolean areEqual(char aThis, char aThat) {
/* 39 */     return (aThis == aThat);
/*    */   }
/*    */   
/*    */   public static boolean areEqual(long aThis, long aThat) {
/* 49 */     return (aThis == aThat);
/*    */   }
/*    */   
/*    */   public static boolean areEqual(float aThis, float aThat) {
/* 54 */     return (Float.floatToIntBits(aThis) == Float.floatToIntBits(aThat));
/*    */   }
/*    */   
/*    */   public static boolean areEqual(double aThis, double aThat) {
/* 59 */     return (Double.doubleToLongBits(aThis) == Double.doubleToLongBits(aThat));
/*    */   }
/*    */   
/*    */   public static boolean areEqual(Object aThis, Object aThat) {
/* 70 */     return (aThis == null) ? ((aThat == null)) : aThis.equals(aThat);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\mat\\utils\EqualUtils.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */