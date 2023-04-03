/*    */ package uk.me.jstott.jcoord.ellipsoid;
/*    */ 
/*    */ public class AustralianNational1966Ellipsoid extends Ellipsoid {
/* 31 */   private static AustralianNational1966Ellipsoid ref = null;
/*    */   
/*    */   private AustralianNational1966Ellipsoid() {
/* 40 */     super(6378160.0D, 6356774.719D);
/*    */   }
/*    */   
/*    */   public static AustralianNational1966Ellipsoid getInstance() {
/* 51 */     if (ref == null)
/* 52 */       ref = new AustralianNational1966Ellipsoid(); 
/* 54 */     return ref;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar\\uk\me\jstott\jcoord\ellipsoid\AustralianNational1966Ellipsoid.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */