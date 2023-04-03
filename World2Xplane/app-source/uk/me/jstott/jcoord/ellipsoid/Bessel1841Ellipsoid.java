/*    */ package uk.me.jstott.jcoord.ellipsoid;
/*    */ 
/*    */ public class Bessel1841Ellipsoid extends Ellipsoid {
/* 31 */   private static Bessel1841Ellipsoid ref = null;
/*    */   
/*    */   private Bessel1841Ellipsoid() {
/* 40 */     super(6377397.155D, 6356078.9629D);
/*    */   }
/*    */   
/*    */   public static Bessel1841Ellipsoid getInstance() {
/* 51 */     if (ref == null)
/* 52 */       ref = new Bessel1841Ellipsoid(); 
/* 54 */     return ref;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar\\uk\me\jstott\jcoord\ellipsoid\Bessel1841Ellipsoid.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */