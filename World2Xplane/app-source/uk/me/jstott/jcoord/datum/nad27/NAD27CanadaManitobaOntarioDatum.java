/*    */ package uk.me.jstott.jcoord.datum.nad27;
/*    */ 
/*    */ import uk.me.jstott.jcoord.datum.Datum;
/*    */ 
/*    */ public class NAD27CanadaManitobaOntarioDatum extends Datum {
/* 34 */   private static NAD27CanadaManitobaOntarioDatum ref = null;
/*    */   
/*    */   public static NAD27CanadaManitobaOntarioDatum getInstance() {
/* 62 */     if (ref == null)
/* 63 */       ref = new NAD27CanadaManitobaOntarioDatum(); 
/* 65 */     return ref;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar\\uk\me\jstott\jcoord\datum\nad27\NAD27CanadaManitobaOntarioDatum.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */