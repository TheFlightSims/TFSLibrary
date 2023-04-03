/*    */ package uk.me.jstott.jcoord.ellipsoid;
/*    */ 
/*    */ public class Krassovsky1940Ellipsoid extends Ellipsoid {
/* 31 */   private static Krassovsky1940Ellipsoid ref = null;
/*    */   
/*    */   private Krassovsky1940Ellipsoid() {
/* 40 */     super(6378245.0D, 6356863.019D);
/*    */   }
/*    */   
/*    */   public static Krassovsky1940Ellipsoid getInstance() {
/* 51 */     if (ref == null)
/* 52 */       ref = new Krassovsky1940Ellipsoid(); 
/* 54 */     return ref;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar\\uk\me\jstott\jcoord\ellipsoid\Krassovsky1940Ellipsoid.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */