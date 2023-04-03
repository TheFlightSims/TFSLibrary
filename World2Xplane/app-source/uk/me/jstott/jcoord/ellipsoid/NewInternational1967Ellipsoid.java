/*    */ package uk.me.jstott.jcoord.ellipsoid;
/*    */ 
/*    */ public class NewInternational1967Ellipsoid extends Ellipsoid {
/* 31 */   private static NewInternational1967Ellipsoid ref = null;
/*    */   
/*    */   private NewInternational1967Ellipsoid() {
/* 40 */     super(6378157.5D, 6356772.2D);
/*    */   }
/*    */   
/*    */   public static NewInternational1967Ellipsoid getInstance() {
/* 51 */     if (ref == null)
/* 52 */       ref = new NewInternational1967Ellipsoid(); 
/* 54 */     return ref;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar\\uk\me\jstott\jcoord\ellipsoid\NewInternational1967Ellipsoid.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */