/*    */ package org.jfree.chart.event;
/*    */ 
/*    */ public class RendererChangeEvent extends ChartChangeEvent {
/*    */   private Object renderer;
/*    */   
/*    */   public RendererChangeEvent(Object renderer) {
/* 61 */     super(renderer);
/* 62 */     this.renderer = renderer;
/*    */   }
/*    */   
/*    */   public Object getRenderer() {
/* 71 */     return this.renderer;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\event\RendererChangeEvent.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */