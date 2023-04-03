/*     */ package org.jfree.chart.event;
/*     */ 
/*     */ import java.util.EventObject;
/*     */ import org.jfree.chart.JFreeChart;
/*     */ 
/*     */ public class ChartProgressEvent extends EventObject {
/*     */   public static final int DRAWING_STARTED = 1;
/*     */   
/*     */   public static final int DRAWING_FINISHED = 2;
/*     */   
/*     */   private int type;
/*     */   
/*     */   private int percent;
/*     */   
/*     */   private JFreeChart chart;
/*     */   
/*     */   public ChartProgressEvent(Object source, JFreeChart chart, int type, int percent) {
/*  79 */     super(source);
/*  80 */     this.chart = chart;
/*  81 */     this.type = type;
/*     */   }
/*     */   
/*     */   public JFreeChart getChart() {
/*  90 */     return this.chart;
/*     */   }
/*     */   
/*     */   public void setChart(JFreeChart chart) {
/*  99 */     this.chart = chart;
/*     */   }
/*     */   
/*     */   public int getType() {
/* 108 */     return this.type;
/*     */   }
/*     */   
/*     */   public void setType(int type) {
/* 117 */     this.type = type;
/*     */   }
/*     */   
/*     */   public int getPercent() {
/* 126 */     return this.percent;
/*     */   }
/*     */   
/*     */   public void setPercent(int percent) {
/* 135 */     this.percent = percent;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\event\ChartProgressEvent.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */