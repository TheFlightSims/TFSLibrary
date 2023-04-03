/*    */ package uk.me.jstott.jcoord.ellipsoid;
/*    */ 
/*    */ public class Hayford1910Ellipsoid extends Ellipsoid {
/* 31 */   private static Hayford1910Ellipsoid ref = null;
/*    */   
/*    */   private Hayford1910Ellipsoid() {
/* 40 */     super(6378388.0D, 6356911.946D);
/*    */   }
/*    */   
/*    */   public static Hayford1910Ellipsoid getInstance() {
/* 51 */     if (ref == null)
/* 52 */       ref = new Hayford1910Ellipsoid(); 
/* 54 */     return ref;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar\\uk\me\jstott\jcoord\ellipsoid\Hayford1910Ellipsoid.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */