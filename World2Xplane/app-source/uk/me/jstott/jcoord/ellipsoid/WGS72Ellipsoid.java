/*    */ package uk.me.jstott.jcoord.ellipsoid;
/*    */ 
/*    */ public class WGS72Ellipsoid extends Ellipsoid {
/* 31 */   private static WGS72Ellipsoid ref = null;
/*    */   
/*    */   private WGS72Ellipsoid() {
/* 40 */     super(6378135.0D, 6356750.5D);
/*    */   }
/*    */   
/*    */   public static WGS72Ellipsoid getInstance() {
/* 51 */     if (ref == null)
/* 52 */       ref = new WGS72Ellipsoid(); 
/* 54 */     return ref;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar\\uk\me\jstott\jcoord\ellipsoid\WGS72Ellipsoid.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */