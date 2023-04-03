/*    */ package org.geotools.temporal.object;
/*    */ 
/*    */ import org.geotools.util.Utilities;
/*    */ import org.opengis.temporal.IndeterminateValue;
/*    */ import org.opengis.temporal.OrdinalEra;
/*    */ import org.opengis.temporal.OrdinalPosition;
/*    */ import org.opengis.temporal.TemporalReferenceSystem;
/*    */ 
/*    */ public class DefaultOrdinalPosition extends DefaultTemporalPosition implements OrdinalPosition {
/*    */   private OrdinalEra ordinalPosition;
/*    */   
/*    */   public DefaultOrdinalPosition(TemporalReferenceSystem frame, IndeterminateValue indeterminatePosition, OrdinalEra ordinalPosition) {
/* 43 */     super(frame, indeterminatePosition);
/* 44 */     this.ordinalPosition = ordinalPosition;
/*    */   }
/*    */   
/*    */   public OrdinalEra getOrdinalPosition() {
/* 53 */     return this.ordinalPosition;
/*    */   }
/*    */   
/*    */   public void setOrdinalPosition(OrdinalEra ordinalPosition) {
/* 57 */     this.ordinalPosition = ordinalPosition;
/*    */   }
/*    */   
/*    */   public boolean equals(Object object) {
/* 62 */     if (object == this)
/* 63 */       return true; 
/* 65 */     if (object instanceof DefaultOrdinalPosition && super.equals(object)) {
/* 66 */       DefaultOrdinalPosition that = (DefaultOrdinalPosition)object;
/* 68 */       return Utilities.equals(this.ordinalPosition, that.ordinalPosition);
/*    */     } 
/* 70 */     return false;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 75 */     int hash = 5;
/* 76 */     hash = 37 * hash + ((this.ordinalPosition != null) ? this.ordinalPosition.hashCode() : 0);
/* 77 */     return hash;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 82 */     StringBuilder s = (new StringBuilder("OrdinalPosition:")).append('\n');
/* 83 */     if (this.ordinalPosition != null)
/* 84 */       s.append("ordinalPosition:").append(this.ordinalPosition).append('\n'); 
/* 86 */     return s.toString();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\temporal\object\DefaultOrdinalPosition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */