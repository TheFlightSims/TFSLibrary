/*    */ package uk.me.jstott.jcoord.datum.nad27;
/*    */ 
/*    */ import uk.me.jstott.jcoord.datum.Datum;
/*    */ 
/*    */ public class NAD27AlaskaDatum extends Datum {
/* 34 */   private static NAD27AlaskaDatum ref = null;
/*    */   
/*    */   public static NAD27AlaskaDatum getInstance() {
/* 62 */     if (ref == null)
/* 63 */       ref = new NAD27AlaskaDatum(); 
/* 65 */     return ref;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar\\uk\me\jstott\jcoord\datum\nad27\NAD27AlaskaDatum.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */