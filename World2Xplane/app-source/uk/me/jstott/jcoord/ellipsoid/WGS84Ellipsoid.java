/*    */ package uk.me.jstott.jcoord.ellipsoid;
/*    */ 
/*    */ public class WGS84Ellipsoid extends Ellipsoid {
/* 31 */   private static WGS84Ellipsoid ref = null;
/*    */   
/*    */   private WGS84Ellipsoid() {
/* 39 */     super(6378137.0D, 6356752.3142D);
/*    */   }
/*    */   
/*    */   public static WGS84Ellipsoid getInstance() {
/* 50 */     if (ref == null)
/* 51 */       ref = new WGS84Ellipsoid(); 
/* 53 */     return ref;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar\\uk\me\jstott\jcoord\ellipsoid\WGS84Ellipsoid.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */