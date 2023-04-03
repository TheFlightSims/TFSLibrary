/*    */ package uk.me.jstott.jcoord.datum;
/*    */ 
/*    */ public class OSGB36Datum extends Datum {
/* 30 */   private static OSGB36Datum ref = null;
/*    */   
/*    */   public static OSGB36Datum getInstance() {
/* 58 */     if (ref == null)
/* 59 */       ref = new OSGB36Datum(); 
/* 61 */     return ref;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar\\uk\me\jstott\jcoord\datum\OSGB36Datum.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */