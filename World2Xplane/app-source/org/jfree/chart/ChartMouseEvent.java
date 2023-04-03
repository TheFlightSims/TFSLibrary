/*     */ package org.jfree.chart;
/*     */ 
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.io.Serializable;
/*     */ import java.util.EventObject;
/*     */ import org.jfree.chart.entity.ChartEntity;
/*     */ 
/*     */ public class ChartMouseEvent extends EventObject implements Serializable {
/*     */   private static final long serialVersionUID = -682393837314562149L;
/*     */   
/*     */   private JFreeChart chart;
/*     */   
/*     */   private MouseEvent trigger;
/*     */   
/*     */   private ChartEntity entity;
/*     */   
/*     */   public ChartMouseEvent(JFreeChart chart, MouseEvent trigger, ChartEntity entity) {
/*  83 */     super(chart);
/*  84 */     this.chart = chart;
/*  85 */     this.trigger = trigger;
/*  86 */     this.entity = entity;
/*     */   }
/*     */   
/*     */   public JFreeChart getChart() {
/*  95 */     return this.chart;
/*     */   }
/*     */   
/*     */   public MouseEvent getTrigger() {
/* 104 */     return this.trigger;
/*     */   }
/*     */   
/*     */   public ChartEntity getEntity() {
/* 113 */     return this.entity;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\ChartMouseEvent.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */