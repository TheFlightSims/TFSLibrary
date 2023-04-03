/*    */ package uk.me.jstott.jcoord.ellipsoid;
/*    */ 
/*    */ public class Fischer1968Ellipsoid extends Ellipsoid {
/* 31 */   private static Fischer1968Ellipsoid ref = null;
/*    */   
/*    */   private Fischer1968Ellipsoid() {
/* 40 */     super(6378150.0D, 6356768.337D);
/*    */   }
/*    */   
/*    */   public static Fischer1968Ellipsoid getInstance() {
/* 51 */     if (ref == null)
/* 52 */       ref = new Fischer1968Ellipsoid(); 
/* 54 */     return ref;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar\\uk\me\jstott\jcoord\ellipsoid\Fischer1968Ellipsoid.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */