/*     */ package org.jfree.chart.axis;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.Effect3D;
/*     */ import org.jfree.chart.plot.CategoryPlot;
/*     */ import org.jfree.chart.plot.PlotRenderingInfo;
/*     */ import org.jfree.chart.renderer.category.CategoryItemRenderer;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ 
/*     */ public class CategoryAxis3D extends CategoryAxis implements Cloneable, Serializable {
/*     */   private static final long serialVersionUID = 4114732251353700972L;
/*     */   
/*     */   public CategoryAxis3D() {
/*  79 */     this((String)null);
/*     */   }
/*     */   
/*     */   public CategoryAxis3D(String label) {
/*  88 */     super(label);
/*     */   }
/*     */   
/*     */   public AxisState draw(Graphics2D g2, double cursor, Rectangle2D plotArea, Rectangle2D dataArea, RectangleEdge edge, PlotRenderingInfo plotState) {
/* 115 */     if (!isVisible())
/* 116 */       return new AxisState(cursor); 
/* 122 */     CategoryPlot plot = (CategoryPlot)getPlot();
/* 124 */     Rectangle2D adjustedDataArea = new Rectangle2D.Double();
/* 125 */     if (plot.getRenderer() instanceof Effect3D) {
/* 126 */       Effect3D e3D = (Effect3D)plot.getRenderer();
/* 127 */       double adjustedX = dataArea.getMinX();
/* 128 */       double adjustedY = dataArea.getMinY();
/* 129 */       double adjustedW = dataArea.getWidth() - e3D.getXOffset();
/* 130 */       double adjustedH = dataArea.getHeight() - e3D.getYOffset();
/* 132 */       if (edge == RectangleEdge.LEFT || edge == RectangleEdge.BOTTOM) {
/* 133 */         adjustedY += e3D.getYOffset();
/* 135 */       } else if (edge == RectangleEdge.RIGHT || edge == RectangleEdge.TOP) {
/* 136 */         adjustedX += e3D.getXOffset();
/*     */       } 
/* 138 */       adjustedDataArea.setRect(adjustedX, adjustedY, adjustedW, adjustedH);
/*     */     } else {
/* 143 */       adjustedDataArea.setRect(dataArea);
/*     */     } 
/* 147 */     AxisState state = new AxisState(cursor);
/* 148 */     state = drawCategoryLabels(g2, adjustedDataArea, edge, state, plotState);
/* 151 */     state = drawLabel(getLabel(), g2, plotArea, dataArea, edge, state);
/* 153 */     return state;
/*     */   }
/*     */   
/*     */   public double getCategoryJava2DCoordinate(CategoryAnchor anchor, int category, int categoryCount, Rectangle2D area, RectangleEdge edge) {
/* 174 */     double result = 0.0D;
/* 175 */     Rectangle2D adjustedArea = area;
/* 176 */     CategoryPlot plot = (CategoryPlot)getPlot();
/* 177 */     CategoryItemRenderer renderer = plot.getRenderer();
/* 178 */     if (renderer instanceof Effect3D) {
/* 179 */       Effect3D e3D = (Effect3D)renderer;
/* 180 */       double adjustedX = area.getMinX();
/* 181 */       double adjustedY = area.getMinY();
/* 182 */       double adjustedW = area.getWidth() - e3D.getXOffset();
/* 183 */       double adjustedH = area.getHeight() - e3D.getYOffset();
/* 185 */       if (edge == RectangleEdge.LEFT || edge == RectangleEdge.BOTTOM) {
/* 186 */         adjustedY += e3D.getYOffset();
/* 188 */       } else if (edge == RectangleEdge.RIGHT || edge == RectangleEdge.TOP) {
/* 189 */         adjustedX += e3D.getXOffset();
/*     */       } 
/* 191 */       adjustedArea = new Rectangle2D.Double(adjustedX, adjustedY, adjustedW, adjustedH);
/*     */     } 
/* 196 */     if (anchor == CategoryAnchor.START) {
/* 197 */       result = getCategoryStart(category, categoryCount, adjustedArea, edge);
/* 201 */     } else if (anchor == CategoryAnchor.MIDDLE) {
/* 202 */       result = getCategoryMiddle(category, categoryCount, adjustedArea, edge);
/* 206 */     } else if (anchor == CategoryAnchor.END) {
/* 207 */       result = getCategoryEnd(category, categoryCount, adjustedArea, edge);
/*     */     } 
/* 211 */     return result;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 224 */     return super.clone();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\axis\CategoryAxis3D.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */