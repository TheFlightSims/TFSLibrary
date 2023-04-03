/*    */ package uk.me.jstott.jcoord.ellipsoid;
/*    */ 
/*    */ public class InternationalEllipsoid extends Ellipsoid {
/* 31 */   private static InternationalEllipsoid ref = null;
/*    */   
/*    */   private InternationalEllipsoid() {
/* 40 */     super(6378388.0D, 6356911.9462D);
/*    */   }
/*    */   
/*    */   public static InternationalEllipsoid getInstance() {
/* 51 */     if (ref == null)
/* 52 */       ref = new InternationalEllipsoid(); 
/* 54 */     return ref;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar\\uk\me\jstott\jcoord\ellipsoid\InternationalEllipsoid.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */