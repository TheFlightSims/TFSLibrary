/*    */ package org.jfree.chart.axis;
/*    */ 
/*    */ import java.text.DecimalFormat;
/*    */ 
/*    */ public class StandardTickUnitSource implements TickUnitSource {
/* 55 */   private static final double LOG_10_VALUE = Math.log(10.0D);
/*    */   
/*    */   public TickUnit getLargerTickUnit(TickUnit unit) {
/* 65 */     double x = unit.getSize();
/* 66 */     double log = Math.log(x) / LOG_10_VALUE;
/* 67 */     double higher = Math.ceil(log);
/* 68 */     return new NumberTickUnit(Math.pow(10.0D, higher), new DecimalFormat("0.0E0"));
/*    */   }
/*    */   
/*    */   public TickUnit getCeilingTickUnit(TickUnit unit) {
/* 82 */     return getLargerTickUnit(unit);
/*    */   }
/*    */   
/*    */   public TickUnit getCeilingTickUnit(double size) {
/* 94 */     double log = Math.log(size) / LOG_10_VALUE;
/* 95 */     double higher = Math.ceil(log);
/* 96 */     return new NumberTickUnit(Math.pow(10.0D, higher), new DecimalFormat("0.0E0"));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\axis\StandardTickUnitSource.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */