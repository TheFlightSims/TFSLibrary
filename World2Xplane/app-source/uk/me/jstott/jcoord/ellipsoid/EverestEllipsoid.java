/*    */ package uk.me.jstott.jcoord.ellipsoid;
/*    */ 
/*    */ public class EverestEllipsoid extends Ellipsoid {
/* 31 */   private static EverestEllipsoid ref = null;
/*    */   
/*    */   private EverestEllipsoid() {
/* 40 */     super(6377276.34518D, 6356075.41511D);
/*    */   }
/*    */   
/*    */   public static EverestEllipsoid getInstance() {
/* 51 */     if (ref == null)
/* 52 */       ref = new EverestEllipsoid(); 
/* 54 */     return ref;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar\\uk\me\jstott\jcoord\ellipsoid\EverestEllipsoid.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */