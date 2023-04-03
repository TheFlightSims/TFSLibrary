/*    */ package uk.me.jstott.jcoord.ellipsoid;
/*    */ 
/*    */ public class GRS75Ellipsoid extends Ellipsoid {
/* 31 */   private static GRS75Ellipsoid ref = null;
/*    */   
/*    */   private GRS75Ellipsoid() {
/* 40 */     super(6378140.0D, 6356755.288D);
/*    */   }
/*    */   
/*    */   public static GRS75Ellipsoid getInstance() {
/* 51 */     if (ref == null)
/* 52 */       ref = new GRS75Ellipsoid(); 
/* 54 */     return ref;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar\\uk\me\jstott\jcoord\ellipsoid\GRS75Ellipsoid.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */