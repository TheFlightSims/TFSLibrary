/*    */ package uk.me.jstott.jcoord.ellipsoid;
/*    */ 
/*    */ public class Clarke1880Ellipsoid extends Ellipsoid {
/* 31 */   private static Clarke1880Ellipsoid ref = null;
/*    */   
/*    */   private Clarke1880Ellipsoid() {
/* 40 */     super(6378249.145D, 6356514.8696D);
/*    */   }
/*    */   
/*    */   public static Clarke1880Ellipsoid getInstance() {
/* 51 */     if (ref == null)
/* 52 */       ref = new Clarke1880Ellipsoid(); 
/* 54 */     return ref;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar\\uk\me\jstott\jcoord\ellipsoid\Clarke1880Ellipsoid.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */