/*    */ package org.geotools.temporal.object;
/*    */ 
/*    */ import org.geotools.util.Utilities;
/*    */ import org.opengis.temporal.ClockTime;
/*    */ import org.opengis.temporal.IndeterminateValue;
/*    */ import org.opengis.temporal.TemporalReferenceSystem;
/*    */ 
/*    */ public class DefaultClockTime extends DefaultTemporalPosition implements ClockTime {
/*    */   private Number[] clockTime;
/*    */   
/*    */   public DefaultClockTime(TemporalReferenceSystem frame, IndeterminateValue indeterminatePosition, Number[] clockTime) {
/* 45 */     super(frame, indeterminatePosition);
/* 46 */     this.clockTime = clockTime;
/*    */   }
/*    */   
/*    */   public Number[] getClockTime() {
/* 58 */     return this.clockTime;
/*    */   }
/*    */   
/*    */   public void setClockTime(Number[] clockTime) {
/* 62 */     this.clockTime = clockTime;
/*    */   }
/*    */   
/*    */   public boolean equals(Object object) {
/* 67 */     if (object == this)
/* 68 */       return true; 
/* 70 */     if (object instanceof DefaultClockTime && super.equals(object)) {
/* 71 */       DefaultClockTime that = (DefaultClockTime)object;
/* 73 */       return Utilities.equals(this.clockTime, that.clockTime);
/*    */     } 
/* 75 */     return false;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 80 */     int hash = 5;
/* 81 */     hash = 37 * hash + ((this.clockTime != null) ? this.clockTime.hashCode() : 0);
/* 82 */     return hash;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 87 */     StringBuilder s = (new StringBuilder("ClockTime:")).append('\n');
/* 88 */     if (this.clockTime != null)
/* 89 */       s.append("clockTime:").append(this.clockTime).append('\n'); 
/* 91 */     return s.toString();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\temporal\object\DefaultClockTime.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */