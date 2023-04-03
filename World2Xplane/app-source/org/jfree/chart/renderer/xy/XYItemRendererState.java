/*    */ package org.jfree.chart.renderer.xy;
/*    */ 
/*    */ import java.awt.geom.Line2D;
/*    */ import org.jfree.chart.plot.PlotRenderingInfo;
/*    */ import org.jfree.chart.renderer.RendererState;
/*    */ 
/*    */ public class XYItemRendererState extends RendererState {
/*    */   public Line2D workingLine;
/*    */   
/*    */   public XYItemRendererState(PlotRenderingInfo info) {
/* 68 */     super(info);
/* 69 */     this.workingLine = new Line2D.Double();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\renderer\xy\XYItemRendererState.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */