/*    */ package org.geotools.measure;
/*    */ 
/*    */ import javax.measure.quantity.Angle;
/*    */ import javax.measure.quantity.Dimensionless;
/*    */ import javax.measure.unit.NonSI;
/*    */ import javax.measure.unit.Unit;
/*    */ import javax.measure.unit.UnitFormat;
/*    */ 
/*    */ public final class Units {
/* 54 */   public static final Unit<Angle> SEXAGESIMAL_DMS = NonSI.DEGREE_ANGLE.transform(SexagesimalConverter.FRACTIONAL.inverse()).asType(Angle.class);
/*    */   
/* 68 */   public static final Unit<Angle> DEGREE_MINUTE_SECOND = NonSI.DEGREE_ANGLE.transform(SexagesimalConverter.INTEGER.inverse()).asType(Angle.class);
/*    */   
/* 74 */   public static final Unit<Dimensionless> PPM = Unit.ONE.times(1.0E-6D);
/*    */   
/*    */   static {
/* 80 */     UnitFormat format = UnitFormat.getInstance();
/* 81 */     format.label(SEXAGESIMAL_DMS, "D.MS");
/* 82 */     format.label(DEGREE_MINUTE_SECOND, "DMS");
/* 83 */     format.label(PPM, "ppm");
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\measure\Units.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */