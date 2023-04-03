/*    */ package org.geotools.temporal.object;
/*    */ 
/*    */ import org.geotools.util.Utilities;
/*    */ import org.opengis.temporal.IndeterminateValue;
/*    */ import org.opengis.temporal.TemporalCoordinate;
/*    */ import org.opengis.temporal.TemporalReferenceSystem;
/*    */ 
/*    */ public class DefaultTemporalCoordinate extends DefaultTemporalPosition implements TemporalCoordinate {
/*    */   private Number coordinateValue;
/*    */   
/*    */   public DefaultTemporalCoordinate(TemporalReferenceSystem frame, IndeterminateValue indeterminatePosition, Number coordinateValue) {
/* 42 */     super(frame, indeterminatePosition);
/* 43 */     this.coordinateValue = coordinateValue;
/*    */   }
/*    */   
/*    */   public Number getCoordinateValue() {
/* 53 */     return this.coordinateValue;
/*    */   }
/*    */   
/*    */   public void setCoordinateValue(Number coordinateValue) {
/* 57 */     this.coordinateValue = coordinateValue;
/*    */   }
/*    */   
/*    */   public boolean equals(Object object) {
/* 62 */     if (object == this)
/* 63 */       return true; 
/* 65 */     if (object instanceof DefaultTemporalCoordinate && super.equals(object)) {
/* 66 */       DefaultTemporalCoordinate that = (DefaultTemporalCoordinate)object;
/* 68 */       return Utilities.equals(this.coordinateValue, that.coordinateValue);
/*    */     } 
/* 70 */     return false;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 75 */     int hash = super.hashCode();
/* 76 */     hash = 37 * hash + ((this.coordinateValue != null) ? this.coordinateValue.hashCode() : 0);
/* 77 */     return hash;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 82 */     StringBuilder s = (new StringBuilder("TemporalCoordinate:")).append('\n');
/* 83 */     if (getFrame() != null)
/* 84 */       s.append("TemporalReferenceSystem:").append(getFrame()).append('\n'); 
/* 86 */     if (getIndeterminatePosition() != null)
/* 87 */       s.append("IndeterminateValue:").append(getIndeterminatePosition()).append('\n'); 
/* 89 */     if (this.coordinateValue != null)
/* 90 */       s.append("coordinateValue:").append(this.coordinateValue).append('\n'); 
/* 92 */     return s.toString();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\temporal\object\DefaultTemporalCoordinate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */