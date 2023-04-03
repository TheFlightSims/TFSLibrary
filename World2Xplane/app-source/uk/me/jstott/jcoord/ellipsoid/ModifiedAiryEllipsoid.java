/*    */ package uk.me.jstott.jcoord.ellipsoid;
/*    */ 
/*    */ public class ModifiedAiryEllipsoid extends Ellipsoid {
/* 31 */   private static ModifiedAiryEllipsoid ref = null;
/*    */   
/*    */   private ModifiedAiryEllipsoid() {
/* 40 */     super(6377340.189D, Double.NaN, 0.00667054015D);
/*    */   }
/*    */   
/*    */   public static ModifiedAiryEllipsoid getInstance() {
/* 51 */     if (ref == null)
/* 52 */       ref = new ModifiedAiryEllipsoid(); 
/* 54 */     return ref;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar\\uk\me\jstott\jcoord\ellipsoid\ModifiedAiryEllipsoid.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */