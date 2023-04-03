/*    */ package org.jfree.chart.axis;
/*    */ 
/*    */ import org.jfree.ui.TextAnchor;
/*    */ 
/*    */ public class NumberTick extends ValueTick {
/*    */   private Number number;
/*    */   
/*    */   public NumberTick(Number number, String label, TextAnchor textAnchor, TextAnchor rotationAnchor, double angle) {
/* 69 */     super(number.doubleValue(), label, textAnchor, rotationAnchor, angle);
/* 70 */     this.number = number;
/*    */   }
/*    */   
/*    */   public Number getNumber() {
/* 80 */     return this.number;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\axis\NumberTick.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */