/*    */ package uk.me.jstott.jcoord.ellipsoid;
/*    */ 
/*    */ public class ModifiedEverestEllipsoid extends Ellipsoid {
/* 31 */   private static ModifiedEverestEllipsoid ref = null;
/*    */   
/*    */   public ModifiedEverestEllipsoid() {
/* 39 */     super(6377304.063D, 6356103.039D);
/*    */   }
/*    */   
/*    */   public static ModifiedEverestEllipsoid getInstance() {
/* 50 */     if (ref == null)
/* 51 */       ref = new ModifiedEverestEllipsoid(); 
/* 53 */     return ref;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar\\uk\me\jstott\jcoord\ellipsoid\ModifiedEverestEllipsoid.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */