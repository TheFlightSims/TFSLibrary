/*     */ package org.jfree.chart.axis;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ 
/*     */ public class AxisState {
/*     */   private double cursor;
/*     */   
/*     */   private List ticks;
/*     */   
/*     */   private double max;
/*     */   
/*     */   public AxisState() {
/*  72 */     this(0.0D);
/*     */   }
/*     */   
/*     */   public AxisState(double cursor) {
/*  81 */     this.cursor = cursor;
/*  82 */     this.ticks = new ArrayList();
/*     */   }
/*     */   
/*     */   public double getCursor() {
/*  91 */     return this.cursor;
/*     */   }
/*     */   
/*     */   public void setCursor(double cursor) {
/* 100 */     this.cursor = cursor;
/*     */   }
/*     */   
/*     */   public void moveCursor(double units, RectangleEdge edge) {
/* 110 */     if (edge == RectangleEdge.TOP) {
/* 111 */       cursorUp(units);
/* 113 */     } else if (edge == RectangleEdge.BOTTOM) {
/* 114 */       cursorDown(units);
/* 116 */     } else if (edge == RectangleEdge.LEFT) {
/* 117 */       cursorLeft(units);
/* 119 */     } else if (edge == RectangleEdge.RIGHT) {
/* 120 */       cursorRight(units);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void cursorUp(double units) {
/* 130 */     this.cursor -= units;
/*     */   }
/*     */   
/*     */   public void cursorDown(double units) {
/* 139 */     this.cursor += units;
/*     */   }
/*     */   
/*     */   public void cursorLeft(double units) {
/* 148 */     this.cursor -= units;
/*     */   }
/*     */   
/*     */   public void cursorRight(double units) {
/* 157 */     this.cursor += units;
/*     */   }
/*     */   
/*     */   public List getTicks() {
/* 166 */     return this.ticks;
/*     */   }
/*     */   
/*     */   public void setTicks(List ticks) {
/* 175 */     this.ticks = ticks;
/*     */   }
/*     */   
/*     */   public double getMax() {
/* 184 */     return this.max;
/*     */   }
/*     */   
/*     */   public void setMax(double max) {
/* 193 */     this.max = max;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\axis\AxisState.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */