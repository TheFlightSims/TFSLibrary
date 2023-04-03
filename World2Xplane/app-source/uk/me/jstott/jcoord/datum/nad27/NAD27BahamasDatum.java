/*    */ package uk.me.jstott.jcoord.datum.nad27;
/*    */ 
/*    */ import uk.me.jstott.jcoord.datum.Datum;
/*    */ 
/*    */ public class NAD27BahamasDatum extends Datum {
/* 34 */   private static NAD27BahamasDatum ref = null;
/*    */   
/*    */   public static NAD27BahamasDatum getInstance() {
/* 62 */     if (ref == null)
/* 63 */       ref = new NAD27BahamasDatum(); 
/* 65 */     return ref;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar\\uk\me\jstott\jcoord\datum\nad27\NAD27BahamasDatum.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */