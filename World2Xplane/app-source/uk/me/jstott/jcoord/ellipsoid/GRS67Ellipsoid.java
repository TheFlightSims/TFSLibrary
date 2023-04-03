/*    */ package uk.me.jstott.jcoord.ellipsoid;
/*    */ 
/*    */ public class GRS67Ellipsoid extends Ellipsoid {
/* 31 */   private static GRS67Ellipsoid ref = null;
/*    */   
/*    */   private GRS67Ellipsoid() {
/* 40 */     super(6378160.0D, 6356774.51609D);
/*    */   }
/*    */   
/*    */   public static GRS67Ellipsoid getInstance() {
/* 51 */     if (ref == null)
/* 52 */       ref = new GRS67Ellipsoid(); 
/* 54 */     return ref;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar\\uk\me\jstott\jcoord\ellipsoid\GRS67Ellipsoid.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */