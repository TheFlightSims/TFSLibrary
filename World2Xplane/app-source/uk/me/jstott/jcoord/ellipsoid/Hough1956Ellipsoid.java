/*    */ package uk.me.jstott.jcoord.ellipsoid;
/*    */ 
/*    */ public class Hough1956Ellipsoid extends Ellipsoid {
/* 31 */   private static Hough1956Ellipsoid ref = null;
/*    */   
/*    */   private Hough1956Ellipsoid() {
/* 40 */     super(6378270.0D, 6356794.34D);
/*    */   }
/*    */   
/*    */   public static Hough1956Ellipsoid getInstance() {
/* 51 */     if (ref == null)
/* 52 */       ref = new Hough1956Ellipsoid(); 
/* 54 */     return ref;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar\\uk\me\jstott\jcoord\ellipsoid\Hough1956Ellipsoid.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */