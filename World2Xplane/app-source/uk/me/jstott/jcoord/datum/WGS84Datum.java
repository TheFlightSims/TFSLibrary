/*    */ package uk.me.jstott.jcoord.datum;
/*    */ 
/*    */ public class WGS84Datum extends Datum {
/* 31 */   private static WGS84Datum ref = null;
/*    */   
/*    */   public static WGS84Datum getInstance() {
/* 59 */     if (ref == null)
/* 60 */       ref = new WGS84Datum(); 
/* 62 */     return ref;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar\\uk\me\jstott\jcoord\datum\WGS84Datum.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */