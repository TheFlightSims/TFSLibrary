/*    */ package uk.me.jstott.jcoord.ellipsoid;
/*    */ 
/*    */ public class Helmert1906Ellipsoid extends Ellipsoid {
/* 31 */   private static Helmert1906Ellipsoid ref = null;
/*    */   
/*    */   private Helmert1906Ellipsoid() {
/* 40 */     super(6378200.0D, 6356818.17D);
/*    */   }
/*    */   
/*    */   public static Helmert1906Ellipsoid getInstance() {
/* 51 */     if (ref == null)
/* 52 */       ref = new Helmert1906Ellipsoid(); 
/* 54 */     return ref;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar\\uk\me\jstott\jcoord\ellipsoid\Helmert1906Ellipsoid.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */