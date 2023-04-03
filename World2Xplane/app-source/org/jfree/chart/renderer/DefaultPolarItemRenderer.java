/*     */ package org.jfree.chart.renderer;
/*     */ 
/*     */ import java.awt.AlphaComposite;
/*     */ import java.awt.Composite;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Point;
/*     */ import java.awt.Polygon;
/*     */ import java.awt.Shape;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.Ellipse2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.jfree.chart.LegendItem;
/*     */ import org.jfree.chart.axis.NumberTick;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.plot.DrawingSupplier;
/*     */ import org.jfree.chart.plot.PlotRenderingInfo;
/*     */ import org.jfree.chart.plot.PolarPlot;
/*     */ import org.jfree.data.xy.XYDataset;
/*     */ import org.jfree.text.TextUtilities;
/*     */ import org.jfree.ui.TextAnchor;
/*     */ import org.jfree.util.BooleanList;
/*     */ import org.jfree.util.BooleanUtilities;
/*     */ 
/*     */ public class DefaultPolarItemRenderer extends AbstractRenderer implements PolarItemRenderer {
/*     */   private PolarPlot plot;
/*     */   
/*  92 */   private BooleanList seriesFilled = new BooleanList();
/*     */   
/*     */   public DrawingSupplier getDrawingSupplier() {
/* 105 */     DrawingSupplier result = null;
/* 106 */     PolarPlot p = getPlot();
/* 107 */     if (p != null)
/* 108 */       result = p.getDrawingSupplier(); 
/* 110 */     return result;
/*     */   }
/*     */   
/*     */   public void setPlot(PolarPlot plot) {
/* 122 */     this.plot = plot;
/*     */   }
/*     */   
/*     */   public PolarPlot getPlot() {
/* 131 */     return this.plot;
/*     */   }
/*     */   
/*     */   public void drawSeries(Graphics2D g2, Rectangle2D dataArea, PlotRenderingInfo info, PolarPlot plot, XYDataset dataset, int seriesIndex) {
/* 151 */     Polygon poly = new Polygon();
/* 152 */     int numPoints = dataset.getItemCount(seriesIndex);
/* 153 */     for (int i = 0; i < numPoints; i++) {
/* 154 */       double theta = dataset.getXValue(seriesIndex, i);
/* 155 */       double radius = dataset.getYValue(seriesIndex, i);
/* 156 */       Point p = plot.translateValueThetaRadiusToJava2D(theta, radius, dataArea);
/* 159 */       poly.addPoint(p.x, p.y);
/*     */     } 
/* 161 */     g2.setPaint(getSeriesPaint(seriesIndex));
/* 162 */     g2.setStroke(getSeriesStroke(seriesIndex));
/* 163 */     if (isSeriesFilled(seriesIndex)) {
/* 164 */       Composite savedComposite = g2.getComposite();
/* 165 */       g2.setComposite(AlphaComposite.getInstance(3, 0.5F));
/* 168 */       g2.fill(poly);
/* 169 */       g2.setComposite(savedComposite);
/*     */     } else {
/* 172 */       g2.draw(poly);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isSeriesFilled(int series) {
/* 185 */     boolean result = false;
/* 186 */     Boolean b = this.seriesFilled.getBoolean(series);
/* 187 */     if (b != null)
/* 188 */       result = b.booleanValue(); 
/* 190 */     return result;
/*     */   }
/*     */   
/*     */   public void setSeriesFilled(int series, boolean filled) {
/* 200 */     this.seriesFilled.setBoolean(series, BooleanUtilities.valueOf(filled));
/*     */   }
/*     */   
/*     */   public void drawAngularGridLines(Graphics2D g2, PolarPlot plot, List ticks, Rectangle2D dataArea) {
/* 216 */     g2.setFont(plot.getAngleLabelFont());
/* 217 */     g2.setStroke(plot.getAngleGridlineStroke());
/* 218 */     g2.setPaint(plot.getAngleGridlinePaint());
/* 220 */     double axisMin = plot.getAxis().getLowerBound();
/* 221 */     double maxRadius = plot.getMaxRadius();
/* 223 */     Point center = plot.translateValueThetaRadiusToJava2D(axisMin, axisMin, dataArea);
/* 226 */     Iterator iterator = ticks.iterator();
/* 227 */     while (iterator.hasNext()) {
/* 228 */       NumberTick tick = iterator.next();
/* 229 */       Point p = plot.translateValueThetaRadiusToJava2D(tick.getNumber().doubleValue(), maxRadius, dataArea);
/* 232 */       g2.setPaint(plot.getAngleGridlinePaint());
/* 233 */       g2.drawLine(center.x, center.y, p.x, p.y);
/* 234 */       if (plot.isAngleLabelsVisible()) {
/* 235 */         int x = p.x;
/* 236 */         int y = p.y;
/* 237 */         g2.setPaint(plot.getAngleLabelPaint());
/* 238 */         TextUtilities.drawAlignedString(tick.getText(), g2, x, y, TextAnchor.CENTER);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void drawRadialGridLines(Graphics2D g2, PolarPlot plot, ValueAxis radialAxis, List ticks, Rectangle2D dataArea) {
/* 260 */     g2.setFont(radialAxis.getTickLabelFont());
/* 261 */     g2.setPaint(plot.getRadiusGridlinePaint());
/* 262 */     g2.setStroke(plot.getRadiusGridlineStroke());
/* 264 */     double axisMin = radialAxis.getLowerBound();
/* 265 */     Point center = plot.translateValueThetaRadiusToJava2D(axisMin, axisMin, dataArea);
/* 269 */     Iterator iterator = ticks.iterator();
/* 270 */     while (iterator.hasNext()) {
/* 271 */       NumberTick tick = iterator.next();
/* 272 */       Point p = plot.translateValueThetaRadiusToJava2D(90.0D, tick.getNumber().doubleValue(), dataArea);
/* 275 */       int r = p.x - center.x;
/* 276 */       int upperLeftX = center.x - r;
/* 277 */       int upperLeftY = center.y - r;
/* 278 */       int d = 2 * r;
/* 279 */       Ellipse2D ring = new Ellipse2D.Double(upperLeftX, upperLeftY, d, d);
/* 280 */       g2.setPaint(plot.getRadiusGridlinePaint());
/* 281 */       g2.draw(ring);
/*     */     } 
/*     */   }
/*     */   
/*     */   public LegendItem getLegendItem(int series) {
/* 293 */     LegendItem result = null;
/* 294 */     PolarPlot polarPlot = getPlot();
/* 295 */     if (polarPlot != null) {
/* 297 */       XYDataset dataset = polarPlot.getDataset();
/* 298 */       if (dataset != null) {
/* 299 */         String label = dataset.getSeriesKey(series).toString();
/* 300 */         String description = label;
/* 301 */         Shape shape = getSeriesShape(series);
/* 302 */         Paint paint = getSeriesPaint(series);
/* 303 */         Paint outlinePaint = getSeriesOutlinePaint(series);
/* 304 */         Stroke outlineStroke = getSeriesOutlineStroke(series);
/* 305 */         result = new LegendItem(label, description, null, null, shape, paint, outlineStroke, outlinePaint);
/*     */       } 
/*     */     } 
/* 309 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\renderer\DefaultPolarItemRenderer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */