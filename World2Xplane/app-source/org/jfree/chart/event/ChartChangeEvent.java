/*     */ package org.jfree.chart.event;
/*     */ 
/*     */ import java.util.EventObject;
/*     */ import org.jfree.chart.JFreeChart;
/*     */ 
/*     */ public class ChartChangeEvent extends EventObject {
/*     */   private ChartChangeEventType type;
/*     */   
/*     */   private JFreeChart chart;
/*     */   
/*     */   public ChartChangeEvent(Object source) {
/*  71 */     this(source, null, ChartChangeEventType.GENERAL);
/*     */   }
/*     */   
/*     */   public ChartChangeEvent(Object source, JFreeChart chart) {
/*  82 */     this(source, chart, ChartChangeEventType.GENERAL);
/*     */   }
/*     */   
/*     */   public ChartChangeEvent(Object source, JFreeChart chart, ChartChangeEventType type) {
/*  95 */     super(source);
/*  96 */     this.chart = chart;
/*  97 */     this.type = type;
/*     */   }
/*     */   
/*     */   public JFreeChart getChart() {
/* 106 */     return this.chart;
/*     */   }
/*     */   
/*     */   public void setChart(JFreeChart chart) {
/* 115 */     this.chart = chart;
/*     */   }
/*     */   
/*     */   public ChartChangeEventType getType() {
/* 124 */     return this.type;
/*     */   }
/*     */   
/*     */   public void setType(ChartChangeEventType type) {
/* 133 */     this.type = type;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\event\ChartChangeEvent.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */