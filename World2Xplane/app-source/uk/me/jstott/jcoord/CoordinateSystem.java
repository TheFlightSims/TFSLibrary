/*    */ package uk.me.jstott.jcoord;
/*    */ 
/*    */ import uk.me.jstott.jcoord.datum.Datum;
/*    */ 
/*    */ public abstract class CoordinateSystem {
/*    */   private Datum datum;
/*    */   
/*    */   public CoordinateSystem(Datum datum) {
/* 33 */     setDatum(datum);
/*    */   }
/*    */   
/*    */   public abstract LatLng toLatLng();
/*    */   
/*    */   public void setDatum(Datum datum) {
/* 54 */     this.datum = datum;
/*    */   }
/*    */   
/*    */   public Datum getDatum() {
/* 65 */     return this.datum;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar\\uk\me\jstott\jcoord\CoordinateSystem.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */