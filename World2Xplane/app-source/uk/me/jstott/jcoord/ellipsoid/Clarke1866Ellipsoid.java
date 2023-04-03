/*    */ package uk.me.jstott.jcoord.ellipsoid;
/*    */ 
/*    */ public class Clarke1866Ellipsoid extends Ellipsoid {
/* 31 */   private static Clarke1866Ellipsoid ref = null;
/*    */   
/*    */   private Clarke1866Ellipsoid() {
/* 40 */     super(6378206.4D, 6356583.8D);
/*    */   }
/*    */   
/*    */   public static Clarke1866Ellipsoid getInstance() {
/* 51 */     if (ref == null)
/* 52 */       ref = new Clarke1866Ellipsoid(); 
/* 54 */     return ref;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar\\uk\me\jstott\jcoord\ellipsoid\Clarke1866Ellipsoid.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */