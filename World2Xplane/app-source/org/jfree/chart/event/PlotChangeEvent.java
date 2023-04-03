/*    */ package org.jfree.chart.event;
/*    */ 
/*    */ import org.jfree.chart.plot.Plot;
/*    */ 
/*    */ public class PlotChangeEvent extends ChartChangeEvent {
/*    */   private Plot plot;
/*    */   
/*    */   public PlotChangeEvent(Plot plot) {
/* 66 */     super(plot);
/* 67 */     this.plot = plot;
/*    */   }
/*    */   
/*    */   public Plot getPlot() {
/* 76 */     return this.plot;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\event\PlotChangeEvent.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */