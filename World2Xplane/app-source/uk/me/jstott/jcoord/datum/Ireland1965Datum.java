/*    */ package uk.me.jstott.jcoord.datum;
/*    */ 
/*    */ public class Ireland1965Datum extends Datum {
/* 32 */   private static Ireland1965Datum ref = null;
/*    */   
/*    */   public static Ireland1965Datum getInstance() {
/* 60 */     if (ref == null)
/* 61 */       ref = new Ireland1965Datum(); 
/* 63 */     return ref;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar\\uk\me\jstott\jcoord\datum\Ireland1965Datum.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */