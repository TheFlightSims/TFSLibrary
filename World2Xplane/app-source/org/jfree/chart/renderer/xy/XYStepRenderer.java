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
/*     */ import org.jfree.chart.urls.XYURLGenerator;
/*     */ import org.jfree.data.xy.XYDataset;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ public class XYStepRenderer extends XYLineAndShapeRenderer implements XYItemRenderer, Cloneable, PublicCloneable, Serializable {
/*     */   private static final long serialVersionUID = -8918141928884796108L;
/*     */   
/*     */   public XYStepRenderer() {
/* 102 */     this((XYToolTipGenerator)null, (XYURLGenerator)null);
/*     */   }
/*     */   
/*     */   public XYStepRenderer(XYToolTipGenerator toolTipGenerator, XYURLGenerator urlGenerator) {
/* 114 */     setBaseToolTipGenerator(toolTipGenerator);
/* 115 */     setURLGenerator(urlGenerator);
/* 116 */     setShapesVisible(false);
/*     */   }
/*     */   
/*     */   public void drawItem(Graphics2D g2, XYItemRendererState state, Rectangle2D dataArea, PlotRenderingInfo info, XYPlot plot, ValueAxis domainAxis, ValueAxis rangeAxis, XYDataset dataset, int series, int item, CrosshairState crosshairState, int pass) {
/* 151 */     if (!getItemVisible(series, item))
/*     */       return; 
/* 155 */     PlotOrientation orientation = plot.getOrientation();
/* 157 */     Paint seriesPaint = getItemPaint(series, item);
/* 158 */     Stroke seriesStroke = getItemStroke(series, item);
/* 159 */     g2.setPaint(seriesPaint);
/* 160 */     g2.setStroke(seriesStroke);
/* 163 */     double x1 = dataset.getXValue(series, item);
/* 164 */     double y1 = dataset.getYValue(series, item);
/* 165 */     if (Double.isNaN(y1))
/*     */       return; 
/* 169 */     RectangleEdge xAxisLocation = plot.getDomainAxisEdge();
/* 170 */     RectangleEdge yAxisLocation = plot.getRangeAxisEdge();
/* 171 */     double transX1 = domainAxis.valueToJava2D(x1, dataArea, xAxisLocation);
/* 172 */     double transY1 = rangeAxis.valueToJava2D(y1, dataArea, yAxisLocation);
/* 174 */     if (item > 0) {
/* 176 */       double x0 = dataset.getXValue(series, item - 1);
/* 177 */       double y0 = dataset.getYValue(series, item - 1);
/* 178 */       if (!Double.isNaN(y0)) {
/* 179 */         double transX0 = domainAxis.valueToJava2D(x0, dataArea, xAxisLocation);
/* 181 */         double transY0 = rangeAxis.valueToJava2D(y0, dataArea, yAxisLocation);
/* 184 */         Line2D line = state.workingLine;
/* 185 */         if (orientation == PlotOrientation.HORIZONTAL) {
/* 186 */           if (transY0 == transY1) {
/* 188 */             line.setLine(transY0, transX0, transY1, transX1);
/* 189 */             g2.draw(line);
/*     */           } else {
/* 192 */             line.setLine(transY0, transX0, transY1, transX0);
/* 193 */             g2.draw(line);
/* 194 */             line.setLine(transY1, transX0, transY1, transX1);
/* 195 */             g2.draw(line);
/*     */           } 
/* 198 */         } else if (orientation == PlotOrientation.VERTICAL) {
/* 199 */           if (transY0 == transY1) {
/* 201 */             line.setLine(transX0, transY0, transX1, transY1);
/* 202 */             g2.draw(line);
/*     */           } else {
/* 205 */             line.setLine(transX0, transY0, transX1, transY0);
/* 206 */             g2.draw(line);
/* 207 */             line.setLine(transX1, transY0, transX1, transY1);
/* 208 */             g2.draw(line);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 215 */     updateCrosshairValues(crosshairState, x1, y1, transX1, transY1, orientation);
/* 219 */     if (state.getInfo() != null) {
/* 220 */       EntityCollection entities = state.getEntityCollection();
/* 221 */       if (entities != null) {
/* 222 */         int r = getDefaultEntityRadius();
/* 223 */         Shape shape = (orientation == PlotOrientation.VERTICAL) ? new Rectangle2D.Double(transX1 - r, transY1 - r, (2 * r), (2 * r)) : new Rectangle2D.Double(transY1 - r, transX1 - r, (2 * r), (2 * r));
/* 228 */         if (shape != null) {
/* 229 */           String tip = null;
/* 230 */           XYToolTipGenerator generator = getToolTipGenerator(series, item);
/* 232 */           if (generator != null)
/* 233 */             tip = generator.generateToolTip(dataset, series, item); 
/* 235 */           String url = null;
/* 236 */           if (getURLGenerator() != null)
/* 237 */             url = getURLGenerator().generateURL(dataset, series, item); 
/* 240 */           XYItemEntity entity = new XYItemEntity(shape, dataset, series, item, tip, url);
/* 242 */           entities.add((ChartEntity)entity);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 256 */     return super.clone();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\renderer\xy\XYStepRenderer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */