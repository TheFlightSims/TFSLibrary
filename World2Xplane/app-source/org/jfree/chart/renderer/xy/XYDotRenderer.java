/*     */ package org.jfree.chart.renderer.xy;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.plot.CrosshairState;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.chart.plot.PlotRenderingInfo;
/*     */ import org.jfree.chart.plot.XYPlot;
/*     */ import org.jfree.data.xy.XYDataset;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ public class XYDotRenderer extends AbstractXYItemRenderer implements XYItemRenderer, Cloneable, PublicCloneable, Serializable {
/*     */   private static final long serialVersionUID = -2764344339073566425L;
/*     */   
/*     */   public void drawItem(Graphics2D g2, XYItemRendererState state, Rectangle2D dataArea, PlotRenderingInfo info, XYPlot plot, ValueAxis domainAxis, ValueAxis rangeAxis, XYDataset dataset, int series, int item, CrosshairState crosshairState, int pass) {
/* 117 */     double x = dataset.getXValue(series, item);
/* 118 */     double y = dataset.getYValue(series, item);
/* 119 */     if (!Double.isNaN(y)) {
/* 120 */       RectangleEdge xAxisLocation = plot.getDomainAxisEdge();
/* 121 */       RectangleEdge yAxisLocation = plot.getRangeAxisEdge();
/* 122 */       double transX = domainAxis.valueToJava2D(x, dataArea, xAxisLocation);
/* 125 */       double transY = rangeAxis.valueToJava2D(y, dataArea, yAxisLocation);
/* 129 */       g2.setPaint(getItemPaint(series, item));
/* 130 */       PlotOrientation orientation = plot.getOrientation();
/* 131 */       if (orientation == PlotOrientation.HORIZONTAL) {
/* 132 */         g2.drawRect((int)transY, (int)transX, 1, 1);
/* 134 */       } else if (orientation == PlotOrientation.VERTICAL) {
/* 135 */         g2.drawRect((int)transX, (int)transY, 1, 1);
/*     */       } 
/* 138 */       updateCrosshairValues(crosshairState, x, y, transX, transY, orientation);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 153 */     return super.clone();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\renderer\xy\XYDotRenderer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */