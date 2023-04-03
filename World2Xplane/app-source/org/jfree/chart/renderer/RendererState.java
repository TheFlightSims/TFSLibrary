/*    */ package org.jfree.chart.renderer;
/*    */ 
/*    */ import org.jfree.chart.ChartRenderingInfo;
/*    */ import org.jfree.chart.entity.EntityCollection;
/*    */ import org.jfree.chart.plot.PlotRenderingInfo;
/*    */ 
/*    */ public class RendererState {
/*    */   private PlotRenderingInfo info;
/*    */   
/*    */   public RendererState(PlotRenderingInfo info) {
/* 65 */     this.info = info;
/*    */   }
/*    */   
/*    */   public PlotRenderingInfo getInfo() {
/* 74 */     return this.info;
/*    */   }
/*    */   
/*    */   public EntityCollection getEntityCollection() {
/* 85 */     EntityCollection result = null;
/* 86 */     if (this.info != null) {
/* 87 */       ChartRenderingInfo owner = this.info.getOwner();
/* 88 */       if (owner != null)
/* 89 */         result = owner.getEntityCollection(); 
/*    */     } 
/* 92 */     return result;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\renderer\RendererState.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */