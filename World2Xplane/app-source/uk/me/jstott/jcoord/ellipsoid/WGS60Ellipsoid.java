/*    */ package uk.me.jstott.jcoord.ellipsoid;
/*    */ 
/*    */ public class WGS60Ellipsoid extends Ellipsoid {
/* 31 */   private static WGS60Ellipsoid ref = null;
/*    */   
/*    */   private WGS60Ellipsoid() {
/* 40 */     super(6378165.0D, 6356783.287D);
/*    */   }
/*    */   
/*    */   public static WGS60Ellipsoid getInstance() {
/* 51 */     if (ref == null)
/* 52 */       ref = new WGS60Ellipsoid(); 
/* 54 */     return ref;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar\\uk\me\jstott\jcoord\ellipsoid\WGS60Ellipsoid.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */