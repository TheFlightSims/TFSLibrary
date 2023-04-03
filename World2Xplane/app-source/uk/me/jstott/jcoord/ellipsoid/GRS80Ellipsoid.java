/*    */ package uk.me.jstott.jcoord.ellipsoid;
/*    */ 
/*    */ public class GRS80Ellipsoid extends Ellipsoid {
/* 31 */   private static GRS80Ellipsoid ref = null;
/*    */   
/*    */   private GRS80Ellipsoid() {
/* 40 */     super(6378137.0D, 6356752.3141D);
/*    */   }
/*    */   
/*    */   public static GRS80Ellipsoid getInstance() {
/* 51 */     if (ref == null)
/* 52 */       ref = new GRS80Ellipsoid(); 
/* 54 */     return ref;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar\\uk\me\jstott\jcoord\ellipsoid\GRS80Ellipsoid.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */