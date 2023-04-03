/*    */ package uk.me.jstott.jcoord.ellipsoid;
/*    */ 
/*    */ public class SouthAmerican1969Ellipsoid extends Ellipsoid {
/* 31 */   private static SouthAmerican1969Ellipsoid ref = null;
/*    */   
/*    */   private SouthAmerican1969Ellipsoid() {
/* 40 */     super(6378160.0D, 6356774.7192D);
/*    */   }
/*    */   
/*    */   public static SouthAmerican1969Ellipsoid getInstance() {
/* 51 */     if (ref == null)
/* 52 */       ref = new SouthAmerican1969Ellipsoid(); 
/* 54 */     return ref;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar\\uk\me\jstott\jcoord\ellipsoid\SouthAmerican1969Ellipsoid.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */