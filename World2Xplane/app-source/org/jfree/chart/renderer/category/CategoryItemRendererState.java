/*     */ package org.jfree.chart.renderer.category;
/*     */ 
/*     */ import org.jfree.chart.plot.PlotRenderingInfo;
/*     */ import org.jfree.chart.renderer.RendererState;
/*     */ 
/*     */ public class CategoryItemRendererState extends RendererState {
/*     */   private double barWidth;
/*     */   
/*     */   private double seriesRunningTotal;
/*     */   
/*     */   public CategoryItemRendererState(PlotRenderingInfo info) {
/*  67 */     super(info);
/*  68 */     this.barWidth = 0.0D;
/*  69 */     this.seriesRunningTotal = 0.0D;
/*     */   }
/*     */   
/*     */   public double getBarWidth() {
/*  78 */     return this.barWidth;
/*     */   }
/*     */   
/*     */   public void setBarWidth(double width) {
/*  88 */     this.barWidth = width;
/*     */   }
/*     */   
/*     */   public double getSeriesRunningTotal() {
/*  97 */     return this.seriesRunningTotal;
/*     */   }
/*     */   
/*     */   void setSeriesRunningTotal(double total) {
/* 107 */     this.seriesRunningTotal = total;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\renderer\category\CategoryItemRendererState.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */