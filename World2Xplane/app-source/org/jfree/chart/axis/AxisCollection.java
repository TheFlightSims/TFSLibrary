/*     */ package org.jfree.chart.axis;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ 
/*     */ public class AxisCollection {
/*  72 */   private List axesAtTop = new ArrayList();
/*     */   
/*  73 */   private List axesAtBottom = new ArrayList();
/*     */   
/*  74 */   private List axesAtLeft = new ArrayList();
/*     */   
/*  75 */   private List axesAtRight = new ArrayList();
/*     */   
/*     */   public List getAxesAtTop() {
/*  85 */     return this.axesAtTop;
/*     */   }
/*     */   
/*     */   public List getAxesAtBottom() {
/*  95 */     return this.axesAtBottom;
/*     */   }
/*     */   
/*     */   public List getAxesAtLeft() {
/* 105 */     return this.axesAtLeft;
/*     */   }
/*     */   
/*     */   public List getAxesAtRight() {
/* 115 */     return this.axesAtRight;
/*     */   }
/*     */   
/*     */   public void add(Axis axis, RectangleEdge edge) {
/* 126 */     if (axis == null)
/* 127 */       throw new IllegalArgumentException("Null 'axis' argument."); 
/* 129 */     if (edge == null)
/* 130 */       throw new IllegalArgumentException("Null 'edge' argument."); 
/* 132 */     if (edge == RectangleEdge.TOP) {
/* 133 */       this.axesAtTop.add(axis);
/* 135 */     } else if (edge == RectangleEdge.BOTTOM) {
/* 136 */       this.axesAtBottom.add(axis);
/* 138 */     } else if (edge == RectangleEdge.LEFT) {
/* 139 */       this.axesAtLeft.add(axis);
/* 141 */     } else if (edge == RectangleEdge.RIGHT) {
/* 142 */       this.axesAtRight.add(axis);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\axis\AxisCollection.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */