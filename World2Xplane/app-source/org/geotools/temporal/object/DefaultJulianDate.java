/*    */ package org.geotools.temporal.object;
/*    */ 
/*    */ import org.opengis.temporal.IndeterminateValue;
/*    */ import org.opengis.temporal.JulianDate;
/*    */ import org.opengis.temporal.TemporalReferenceSystem;
/*    */ 
/*    */ public class DefaultJulianDate extends DefaultTemporalCoordinate implements JulianDate {
/*    */   public DefaultJulianDate(TemporalReferenceSystem frame, IndeterminateValue indeterminatePosition, Number coordinateValue) {
/* 43 */     super(frame, indeterminatePosition, coordinateValue);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\temporal\object\DefaultJulianDate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */