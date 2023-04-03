/*    */ package uk.me.jstott.jcoord.datum;
/*    */ 
/*    */ public class ETRF89Datum extends Datum {
/* 32 */   private static ETRF89Datum ref = null;
/*    */   
/*    */   public static ETRF89Datum getInstance() {
/* 60 */     if (ref == null)
/* 61 */       ref = new ETRF89Datum(); 
/* 63 */     return ref;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar\\uk\me\jstott\jcoord\datum\ETRF89Datum.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */