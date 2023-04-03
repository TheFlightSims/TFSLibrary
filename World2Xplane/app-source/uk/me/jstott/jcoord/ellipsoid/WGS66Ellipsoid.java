/*    */ package uk.me.jstott.jcoord.ellipsoid;
/*    */ 
/*    */ public class WGS66Ellipsoid extends Ellipsoid {
/* 31 */   private static WGS66Ellipsoid ref = null;
/*    */   
/*    */   private WGS66Ellipsoid() {
/* 40 */     super(6378145.0D, 6356759.77D);
/*    */   }
/*    */   
/*    */   public static WGS66Ellipsoid getInstance() {
/* 51 */     if (ref == null)
/* 52 */       ref = new WGS66Ellipsoid(); 
/* 54 */     return ref;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar\\uk\me\jstott\jcoord\ellipsoid\WGS66Ellipsoid.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */