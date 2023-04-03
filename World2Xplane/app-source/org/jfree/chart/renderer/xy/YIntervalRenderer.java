/*     */ package org.jfree.chart.renderer.xy;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Shape;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.Line2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.entity.ChartEntity;
/*     */ import org.jfree.chart.entity.EntityCollection;
/*     */ import org.jfree.chart.entity.XYItemEntity;
/*     */ import org.jfree.chart.labels.XYToolTipGenerator;
/*     */ import org.jfree.chart.plot.CrosshairState;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.chart.plot.PlotRenderingInfo;
/*     */ import org.jfree.chart.plot.XYPlot;
/*     */ import org.jfree.data.xy.IntervalXYDataset;
/*     */ import org.jfree.data.xy.XYDataset;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ import org.jfree.util.ShapeUtilities;
/*     */ 
/*     */ public class YIntervalRenderer extends AbstractXYItemRenderer implements XYItemRenderer, Cloneable, PublicCloneable, Serializable {
/*     */   private static final long serialVersionUID = -2951586537224143260L;
/*     */   
/*     */   public void drawItem(Graphics2D g2, XYItemRendererState state, Rectangle2D dataArea, PlotRenderingInfo info, XYPlot plot, ValueAxis domainAxis, ValueAxis rangeAxis, XYDataset dataset, int series, int item, CrosshairState crosshairState, int pass) {
/* 125 */     Shape entityArea = null;
/* 126 */     EntityCollection entities = null;
/* 127 */     if (info != null)
/* 128 */       entities = info.getOwner().getEntityCollection(); 
/* 131 */     IntervalXYDataset intervalDataset = (IntervalXYDataset)dataset;
/* 133 */     double x = intervalDataset.getXValue(series, item);
/* 134 */     double yLow = intervalDataset.getStartYValue(series, item);
/* 135 */     double yHigh = intervalDataset.getEndYValue(series, item);
/* 137 */     RectangleEdge xAxisLocation = plot.getDomainAxisEdge();
/* 138 */     RectangleEdge yAxisLocation = plot.getRangeAxisEdge();
/* 140 */     double xx = domainAxis.valueToJava2D(x, dataArea, xAxisLocation);
/* 141 */     double yyLow = rangeAxis.valueToJava2D(yLow, dataArea, yAxisLocation);
/* 142 */     double yyHigh = rangeAxis.valueToJava2D(yHigh, dataArea, yAxisLocation);
/* 144 */     Paint p = getItemPaint(series, item);
/* 145 */     Stroke s = getItemStroke(series, item);
/* 147 */     Line2D line = null;
/* 148 */     Shape shape = getItemShape(series, item);
/* 149 */     Shape top = null;
/* 150 */     Shape bottom = null;
/* 151 */     PlotOrientation orientation = plot.getOrientation();
/* 152 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 153 */       line = new Line2D.Double(yyLow, xx, yyHigh, xx);
/* 154 */       top = ShapeUtilities.createTranslatedShape(shape, yyHigh, xx);
/* 155 */       bottom = ShapeUtilities.createTranslatedShape(shape, yyLow, xx);
/* 157 */     } else if (orientation == PlotOrientation.VERTICAL) {
/* 158 */       line = new Line2D.Double(xx, yyLow, xx, yyHigh);
/* 159 */       top = ShapeUtilities.createTranslatedShape(shape, xx, yyHigh);
/* 160 */       bottom = ShapeUtilities.createTranslatedShape(shape, xx, yyLow);
/*     */     } 
/* 162 */     g2.setPaint(p);
/* 163 */     g2.setStroke(s);
/* 164 */     g2.draw(line);
/* 166 */     g2.fill(top);
/* 167 */     g2.fill(bottom);
/* 170 */     if (entities != null) {
/* 171 */       if (entityArea == null)
/* 172 */         entityArea = line.getBounds(); 
/* 174 */       String tip = null;
/* 175 */       XYToolTipGenerator generator = getToolTipGenerator(series, item);
/* 176 */       if (generator != null)
/* 177 */         tip = generator.generateToolTip(dataset, series, item); 
/* 179 */       String url = null;
/* 180 */       if (getURLGenerator() != null)
/* 181 */         url = getURLGenerator().generateURL(dataset, series, item); 
/* 183 */       XYItemEntity entity = new XYItemEntity(entityArea, dataset, series, item, tip, url);
/* 186 */       entities.add((ChartEntity)entity);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 199 */     return super.clone();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\renderer\xy\YIntervalRenderer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */