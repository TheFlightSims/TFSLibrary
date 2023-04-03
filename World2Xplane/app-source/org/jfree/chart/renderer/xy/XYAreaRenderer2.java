/*     */ package org.jfree.chart.renderer.xy;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Polygon;
/*     */ import java.awt.Shape;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.LegendItem;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.entity.ChartEntity;
/*     */ import org.jfree.chart.entity.EntityCollection;
/*     */ import org.jfree.chart.entity.XYItemEntity;
/*     */ import org.jfree.chart.event.RendererChangeEvent;
/*     */ import org.jfree.chart.labels.XYSeriesLabelGenerator;
/*     */ import org.jfree.chart.labels.XYToolTipGenerator;
/*     */ import org.jfree.chart.plot.CrosshairState;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.chart.plot.PlotRenderingInfo;
/*     */ import org.jfree.chart.plot.XYPlot;
/*     */ import org.jfree.chart.urls.XYURLGenerator;
/*     */ import org.jfree.data.xy.XYDataset;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ public class XYAreaRenderer2 extends AbstractXYItemRenderer implements XYItemRenderer, Cloneable, PublicCloneable, Serializable {
/*     */   private static final long serialVersionUID = -7378069681579984133L;
/*     */   
/*     */   private boolean plotLines;
/*     */   
/*     */   private boolean showOutline;
/*     */   
/*     */   private transient Shape legendArea;
/*     */   
/*     */   public XYAreaRenderer2() {
/* 133 */     this((XYToolTipGenerator)null, (XYURLGenerator)null);
/*     */   }
/*     */   
/*     */   public XYAreaRenderer2(XYToolTipGenerator labelGenerator, XYURLGenerator urlGenerator) {
/* 149 */     this.plotLines = false;
/* 150 */     this.showOutline = false;
/* 151 */     setBaseToolTipGenerator(labelGenerator);
/* 152 */     setURLGenerator(urlGenerator);
/* 153 */     GeneralPath area = new GeneralPath();
/* 154 */     area.moveTo(0.0F, -4.0F);
/* 155 */     area.lineTo(3.0F, -2.0F);
/* 156 */     area.lineTo(4.0F, 4.0F);
/* 157 */     area.lineTo(-4.0F, 4.0F);
/* 158 */     area.lineTo(-3.0F, -2.0F);
/* 159 */     area.closePath();
/* 160 */     this.legendArea = area;
/*     */   }
/*     */   
/*     */   public boolean isOutline() {
/* 170 */     return this.showOutline;
/*     */   }
/*     */   
/*     */   public void setOutline(boolean show) {
/* 179 */     this.showOutline = show;
/*     */   }
/*     */   
/*     */   public boolean getPlotLines() {
/* 188 */     return this.plotLines;
/*     */   }
/*     */   
/*     */   public Shape getLegendArea() {
/* 197 */     return this.legendArea;
/*     */   }
/*     */   
/*     */   public void setLegendArea(Shape area) {
/* 207 */     if (area == null)
/* 208 */       throw new IllegalArgumentException("Null 'area' argument."); 
/* 210 */     this.legendArea = area;
/* 211 */     notifyListeners(new RendererChangeEvent(this));
/*     */   }
/*     */   
/*     */   public LegendItem getLegendItem(int datasetIndex, int series) {
/* 224 */     LegendItem result = null;
/* 225 */     XYPlot xyplot = getPlot();
/* 226 */     if (xyplot != null) {
/* 227 */       XYDataset dataset = xyplot.getDataset(datasetIndex);
/* 228 */       if (dataset != null) {
/* 229 */         XYSeriesLabelGenerator lg = getLegendItemLabelGenerator();
/* 230 */         String label = lg.generateLabel(dataset, series);
/* 231 */         String description = label;
/* 232 */         String toolTipText = null;
/* 233 */         if (getLegendItemToolTipGenerator() != null)
/* 234 */           toolTipText = getLegendItemToolTipGenerator().generateLabel(dataset, series); 
/* 238 */         String urlText = null;
/* 239 */         if (getLegendItemURLGenerator() != null)
/* 240 */           urlText = getLegendItemURLGenerator().generateLabel(dataset, series); 
/* 244 */         Paint paint = getSeriesPaint(series);
/* 245 */         result = new LegendItem(label, description, toolTipText, urlText, this.legendArea, paint);
/*     */       } 
/*     */     } 
/* 249 */     return result;
/*     */   }
/*     */   
/*     */   public void drawItem(Graphics2D g2, XYItemRendererState state, Rectangle2D dataArea, PlotRenderingInfo info, XYPlot plot, ValueAxis domainAxis, ValueAxis rangeAxis, XYDataset dataset, int series, int item, CrosshairState crosshairState, int pass) {
/* 283 */     if (!getItemVisible(series, item))
/*     */       return; 
/* 287 */     double x1 = dataset.getXValue(series, item);
/* 288 */     double y1 = dataset.getYValue(series, item);
/* 289 */     if (Double.isNaN(y1))
/* 290 */       y1 = 0.0D; 
/* 293 */     double transX1 = domainAxis.valueToJava2D(x1, dataArea, plot.getDomainAxisEdge());
/* 296 */     double transY1 = rangeAxis.valueToJava2D(y1, dataArea, plot.getRangeAxisEdge());
/* 302 */     double x0 = dataset.getXValue(series, Math.max(item - 1, 0));
/* 303 */     double y0 = dataset.getYValue(series, Math.max(item - 1, 0));
/* 304 */     if (Double.isNaN(y0))
/* 305 */       y0 = 0.0D; 
/* 307 */     double transX0 = domainAxis.valueToJava2D(x0, dataArea, plot.getDomainAxisEdge());
/* 310 */     double transY0 = rangeAxis.valueToJava2D(y0, dataArea, plot.getRangeAxisEdge());
/* 314 */     int itemCount = dataset.getItemCount(series);
/* 315 */     double x2 = dataset.getXValue(series, Math.min(item + 1, itemCount - 1));
/* 318 */     double y2 = dataset.getYValue(series, Math.min(item + 1, itemCount - 1));
/* 321 */     if (Double.isNaN(y2))
/* 322 */       y2 = 0.0D; 
/* 324 */     double transX2 = domainAxis.valueToJava2D(x2, dataArea, plot.getDomainAxisEdge());
/* 327 */     double transY2 = rangeAxis.valueToJava2D(y2, dataArea, plot.getRangeAxisEdge());
/* 331 */     double transZero = rangeAxis.valueToJava2D(0.0D, dataArea, plot.getRangeAxisEdge());
/* 334 */     Polygon hotspot = null;
/* 335 */     if (plot.getOrientation() == PlotOrientation.HORIZONTAL) {
/* 336 */       hotspot = new Polygon();
/* 337 */       hotspot.addPoint((int)transZero, (int)((transX0 + transX1) / 2.0D));
/* 340 */       hotspot.addPoint((int)((transY0 + transY1) / 2.0D), (int)((transX0 + transX1) / 2.0D));
/* 344 */       hotspot.addPoint((int)transY1, (int)transX1);
/* 345 */       hotspot.addPoint((int)((transY1 + transY2) / 2.0D), (int)((transX1 + transX2) / 2.0D));
/* 349 */       hotspot.addPoint((int)transZero, (int)((transX1 + transX2) / 2.0D));
/*     */     } else {
/* 354 */       hotspot = new Polygon();
/* 355 */       hotspot.addPoint((int)((transX0 + transX1) / 2.0D), (int)transZero);
/* 358 */       hotspot.addPoint((int)((transX0 + transX1) / 2.0D), (int)((transY0 + transY1) / 2.0D));
/* 362 */       hotspot.addPoint((int)transX1, (int)transY1);
/* 363 */       hotspot.addPoint((int)((transX1 + transX2) / 2.0D), (int)((transY1 + transY2) / 2.0D));
/* 367 */       hotspot.addPoint((int)((transX1 + transX2) / 2.0D), (int)transZero);
/*     */     } 
/* 372 */     PlotOrientation orientation = plot.getOrientation();
/* 373 */     Paint paint = getItemPaint(series, item);
/* 374 */     Stroke stroke = getItemStroke(series, item);
/* 375 */     g2.setPaint(paint);
/* 376 */     g2.setStroke(stroke);
/* 378 */     if (getPlotLines() && 
/* 379 */       item > 0) {
/* 380 */       if (plot.getOrientation() == PlotOrientation.VERTICAL) {
/* 381 */         state.workingLine.setLine(transX0, transY0, transX1, transY1);
/* 385 */       } else if (plot.getOrientation() == PlotOrientation.HORIZONTAL) {
/* 386 */         state.workingLine.setLine(transY0, transX0, transY1, transX1);
/*     */       } 
/* 390 */       g2.draw(state.workingLine);
/*     */     } 
/* 396 */     g2.fill(hotspot);
/* 399 */     if (isOutline()) {
/* 400 */       g2.setStroke(getSeriesOutlineStroke(series));
/* 401 */       g2.setPaint(getSeriesOutlinePaint(series));
/* 402 */       g2.draw(hotspot);
/*     */     } 
/* 404 */     updateCrosshairValues(crosshairState, x1, y1, transX1, transY1, orientation);
/* 409 */     if (state.getInfo() != null) {
/* 410 */       EntityCollection entities = state.getEntityCollection();
/* 411 */       if (entities != null && hotspot != null) {
/* 412 */         String tip = null;
/* 413 */         XYToolTipGenerator generator = getToolTipGenerator(series, item);
/* 416 */         if (generator != null)
/* 417 */           tip = generator.generateToolTip(dataset, series, item); 
/* 419 */         String url = null;
/* 420 */         if (getURLGenerator() != null)
/* 421 */           url = getURLGenerator().generateURL(dataset, series, item); 
/* 423 */         XYItemEntity entity = new XYItemEntity(hotspot, dataset, series, item, tip, url);
/* 426 */         entities.add((ChartEntity)entity);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 440 */     return super.clone();
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
/* 453 */     stream.defaultReadObject();
/* 454 */     this.legendArea = SerialUtilities.readShape(stream);
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream stream) throws IOException {
/* 465 */     stream.defaultWriteObject();
/* 466 */     SerialUtilities.writeShape(this.legendArea, stream);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\renderer\xy\XYAreaRenderer2.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */