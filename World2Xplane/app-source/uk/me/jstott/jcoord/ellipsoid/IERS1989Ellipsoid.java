/*    */ package uk.me.jstott.jcoord.ellipsoid;
/*    */ 
/*    */ public class IERS1989Ellipsoid extends Ellipsoid {
/* 31 */   private static IERS1989Ellipsoid ref = null;
/*    */   
/*    */   private IERS1989Ellipsoid() {
/* 40 */     super(6378136.0D, 6356751.302D);
/*    */   }
/*    */   
/*    */   public static IERS1989Ellipsoid getInstance() {
/* 51 */     if (ref == null)
/* 52 */       ref = new IERS1989Ellipsoid(); 
/* 54 */     return ref;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar\\uk\me\jstott\jcoord\ellipsoid\IERS1989Ellipsoid.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */