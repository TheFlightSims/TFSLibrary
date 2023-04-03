/*     */ package org.jfree.chart.axis;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import org.jfree.chart.Effect3D;
/*     */ import org.jfree.chart.plot.CategoryPlot;
/*     */ import org.jfree.chart.plot.PlotRenderingInfo;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ 
/*     */ public class NumberAxis3D extends NumberAxis implements Serializable {
/*     */   private static final long serialVersionUID = -1790205852569123512L;
/*     */   
/*     */   public NumberAxis3D() {
/*  95 */     this((String)null);
/*     */   }
/*     */   
/*     */   public NumberAxis3D(String label) {
/* 104 */     super(label);
/* 105 */     setAxisLineVisible(false);
/*     */   }
/*     */   
/*     */   public AxisState draw(Graphics2D g2, double cursor, Rectangle2D plotArea, Rectangle2D dataArea, RectangleEdge edge, PlotRenderingInfo plotState) {
/* 131 */     if (!isVisible()) {
/* 132 */       AxisState state = new AxisState(cursor);
/* 135 */       List ticks = refreshTicks(g2, state, dataArea, edge);
/* 136 */       state.setTicks(ticks);
/* 137 */       return state;
/*     */     } 
/* 141 */     CategoryPlot plot = (CategoryPlot)getPlot();
/* 143 */     Effect3D e3D = (Effect3D)plot.getRenderer();
/* 144 */     double adjustedX = dataArea.getMinX();
/* 145 */     double adjustedY = dataArea.getMinY();
/* 146 */     double adjustedW = dataArea.getWidth() - e3D.getXOffset();
/* 147 */     double adjustedH = dataArea.getHeight() - e3D.getYOffset();
/* 149 */     if (edge == RectangleEdge.LEFT || edge == RectangleEdge.BOTTOM) {
/* 150 */       adjustedY += e3D.getYOffset();
/* 152 */     } else if (edge == RectangleEdge.RIGHT || edge == RectangleEdge.TOP) {
/* 153 */       adjustedX += e3D.getXOffset();
/*     */     } 
/* 155 */     Rectangle2D adjustedDataArea = new Rectangle2D.Double(adjustedX, adjustedY, adjustedW, adjustedH);
/* 160 */     AxisState info = drawTickMarksAndLabels(g2, cursor, plotArea, adjustedDataArea, edge);
/* 165 */     info = drawLabel(getLabel(), g2, plotArea, dataArea, edge, info);
/* 167 */     return info;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\axis\NumberAxis3D.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */