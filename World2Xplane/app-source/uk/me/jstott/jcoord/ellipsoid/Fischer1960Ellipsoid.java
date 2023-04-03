/*    */ package uk.me.jstott.jcoord.ellipsoid;
/*    */ 
/*    */ public class Fischer1960Ellipsoid extends Ellipsoid {
/* 31 */   private static Fischer1960Ellipsoid ref = null;
/*    */   
/*    */   private Fischer1960Ellipsoid() {
/* 40 */     super(6378166.0D, 6356784.284D);
/*    */   }
/*    */   
/*    */   public static Fischer1960Ellipsoid getInstance() {
/* 51 */     if (ref == null)
/* 52 */       ref = new Fischer1960Ellipsoid(); 
/* 54 */     return ref;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar\\uk\me\jstott\jcoord\ellipsoid\Fischer1960Ellipsoid.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */