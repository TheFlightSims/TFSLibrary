/*    */ package org.jfree.chart.event;
/*    */ 
/*    */ import org.jfree.chart.axis.Axis;
/*    */ 
/*    */ public class AxisChangeEvent extends ChartChangeEvent {
/*    */   private Axis axis;
/*    */   
/*    */   public AxisChangeEvent(Axis axis) {
/* 63 */     super(axis);
/* 64 */     this.axis = axis;
/*    */   }
/*    */   
/*    */   public Axis getAxis() {
/* 73 */     return this.axis;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\event\AxisChangeEvent.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */