/*    */ package uk.me.jstott.jcoord.ellipsoid;
/*    */ 
/*    */ public class Airy1830Ellipsoid extends Ellipsoid {
/* 31 */   private static Airy1830Ellipsoid ref = null;
/*    */   
/*    */   private Airy1830Ellipsoid() {
/* 40 */     super(6377563.396D, 6356256.909D);
/*    */   }
/*    */   
/*    */   public static Airy1830Ellipsoid getInstance() {
/* 51 */     if (ref == null)
/* 52 */       ref = new Airy1830Ellipsoid(); 
/* 54 */     return ref;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar\\uk\me\jstott\jcoord\ellipsoid\Airy1830Ellipsoid.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */