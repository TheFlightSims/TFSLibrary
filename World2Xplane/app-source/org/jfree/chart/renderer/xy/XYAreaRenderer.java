/*     */ package org.jfree.chart.renderer.xy;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Polygon;
/*     */ import java.awt.Shape;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.Line2D;
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
/*     */ import org.jfree.util.ShapeUtilities;
/*     */ 
/*     */ public class XYAreaRenderer extends AbstractXYItemRenderer implements XYItemRenderer, Cloneable, PublicCloneable, Serializable {
/*     */   private static final long serialVersionUID = -4481971353973876747L;
/*     */   
/*     */   public static final int SHAPES = 1;
/*     */   
/*     */   public static final int LINES = 2;
/*     */   
/*     */   public static final int SHAPES_AND_LINES = 3;
/*     */   
/*     */   public static final int AREA = 4;
/*     */   
/*     */   public static final int AREA_AND_SHAPES = 5;
/*     */   
/*     */   private boolean plotShapes;
/*     */   
/*     */   private boolean plotLines;
/*     */   
/*     */   private boolean plotArea;
/*     */   
/*     */   private boolean showOutline;
/*     */   
/*     */   private transient Shape legendArea;
/*     */   
/*     */   static class XYAreaRendererState extends XYItemRendererState {
/*     */     public Polygon area;
/*     */     
/*     */     public Line2D line;
/*     */     
/*     */     public XYAreaRendererState(PlotRenderingInfo info) {
/* 137 */       super(info);
/* 138 */       this.area = new Polygon();
/* 139 */       this.line = new Line2D.Double();
/*     */     }
/*     */   }
/*     */   
/*     */   public XYAreaRenderer() {
/* 185 */     this(4);
/*     */   }
/*     */   
/*     */   public XYAreaRenderer(int type) {
/* 194 */     this(type, (XYToolTipGenerator)null, (XYURLGenerator)null);
/*     */   }
/*     */   
/*     */   public XYAreaRenderer(int type, XYToolTipGenerator toolTipGenerator, XYURLGenerator urlGenerator) {
/* 212 */     setBaseToolTipGenerator(toolTipGenerator);
/* 213 */     setURLGenerator(urlGenerator);
/* 215 */     if (type == 1)
/* 216 */       this.plotShapes = true; 
/* 218 */     if (type == 2)
/* 219 */       this.plotLines = true; 
/* 221 */     if (type == 3) {
/* 222 */       this.plotShapes = true;
/* 223 */       this.plotLines = true;
/*     */     } 
/* 225 */     if (type == 4)
/* 226 */       this.plotArea = true; 
/* 228 */     if (type == 5) {
/* 229 */       this.plotArea = true;
/* 230 */       this.plotShapes = true;
/*     */     } 
/* 232 */     this.showOutline = false;
/* 233 */     GeneralPath area = new GeneralPath();
/* 234 */     area.moveTo(0.0F, -4.0F);
/* 235 */     area.lineTo(3.0F, -2.0F);
/* 236 */     area.lineTo(4.0F, 4.0F);
/* 237 */     area.lineTo(-4.0F, 4.0F);
/* 238 */     area.lineTo(-3.0F, -2.0F);
/* 239 */     area.closePath();
/* 240 */     this.legendArea = area;
/*     */   }
/*     */   
/*     */   public boolean isOutline() {
/* 251 */     return this.showOutline;
/*     */   }
/*     */   
/*     */   public void setOutline(boolean show) {
/* 260 */     this.showOutline = show;
/*     */   }
/*     */   
/*     */   public boolean getPlotShapes() {
/* 269 */     return this.plotShapes;
/*     */   }
/*     */   
/*     */   public boolean getPlotLines() {
/* 278 */     return this.plotLines;
/*     */   }
/*     */   
/*     */   public boolean getPlotArea() {
/* 287 */     return this.plotArea;
/*     */   }
/*     */   
/*     */   public Shape getLegendArea() {
/* 296 */     return this.legendArea;
/*     */   }
/*     */   
/*     */   public void setLegendArea(Shape area) {
/* 306 */     if (area == null)
/* 307 */       throw new IllegalArgumentException("Null 'area' argument."); 
/* 309 */     this.legendArea = area;
/* 310 */     notifyListeners(new RendererChangeEvent(this));
/*     */   }
/*     */   
/*     */   public XYItemRendererState initialise(Graphics2D g2, Rectangle2D dataArea, XYPlot plot, XYDataset data, PlotRenderingInfo info) {
/* 331 */     XYAreaRendererState state = new XYAreaRendererState(info);
/* 332 */     return state;
/*     */   }
/*     */   
/*     */   public LegendItem getLegendItem(int datasetIndex, int series) {
/* 345 */     LegendItem result = null;
/* 346 */     XYPlot xyplot = getPlot();
/* 347 */     if (xyplot != null) {
/* 348 */       XYDataset dataset = xyplot.getDataset(datasetIndex);
/* 349 */       if (dataset != null) {
/* 350 */         XYSeriesLabelGenerator lg = getLegendItemLabelGenerator();
/* 351 */         String label = lg.generateLabel(dataset, series);
/* 352 */         String description = label;
/* 353 */         String toolTipText = null;
/* 354 */         if (getLegendItemToolTipGenerator() != null)
/* 355 */           toolTipText = getLegendItemToolTipGenerator().generateLabel(dataset, series); 
/* 359 */         String urlText = null;
/* 360 */         if (getLegendItemURLGenerator() != null)
/* 361 */           urlText = getLegendItemURLGenerator().generateLabel(dataset, series); 
/* 365 */         Paint paint = getSeriesPaint(series);
/* 366 */         result = new LegendItem(label, description, toolTipText, urlText, this.legendArea, paint);
/*     */       } 
/*     */     } 
/* 370 */     return result;
/*     */   }
/*     */   
/*     */   public void drawItem(Graphics2D g2, XYItemRendererState state, Rectangle2D dataArea, PlotRenderingInfo info, XYPlot plot, ValueAxis domainAxis, ValueAxis rangeAxis, XYDataset dataset, int series, int item, CrosshairState crosshairState, int pass) {
/* 404 */     if (!getItemVisible(series, item))
/*     */       return; 
/* 407 */     XYAreaRendererState areaState = (XYAreaRendererState)state;
/* 410 */     double x1 = dataset.getXValue(series, item);
/* 411 */     double y1 = dataset.getYValue(series, item);
/* 412 */     if (Double.isNaN(y1))
/* 413 */       y1 = 0.0D; 
/* 415 */     double transX1 = domainAxis.valueToJava2D(x1, dataArea, plot.getDomainAxisEdge());
/* 418 */     double transY1 = rangeAxis.valueToJava2D(y1, dataArea, plot.getRangeAxisEdge());
/* 424 */     int itemCount = dataset.getItemCount(series);
/* 425 */     double x0 = dataset.getXValue(series, Math.max(item - 1, 0));
/* 426 */     double y0 = dataset.getYValue(series, Math.max(item - 1, 0));
/* 427 */     if (Double.isNaN(y0))
/* 428 */       y0 = 0.0D; 
/* 430 */     double transX0 = domainAxis.valueToJava2D(x0, dataArea, plot.getDomainAxisEdge());
/* 433 */     double transY0 = rangeAxis.valueToJava2D(y0, dataArea, plot.getRangeAxisEdge());
/* 437 */     double x2 = dataset.getXValue(series, Math.min(item + 1, itemCount - 1));
/* 440 */     double y2 = dataset.getYValue(series, Math.min(item + 1, itemCount - 1));
/* 443 */     if (Double.isNaN(y2))
/* 444 */       y2 = 0.0D; 
/* 446 */     double transX2 = domainAxis.valueToJava2D(x2, dataArea, plot.getDomainAxisEdge());
/* 449 */     double transY2 = rangeAxis.valueToJava2D(y2, dataArea, plot.getRangeAxisEdge());
/* 453 */     double transZero = rangeAxis.valueToJava2D(0.0D, dataArea, plot.getRangeAxisEdge());
/* 456 */     Polygon hotspot = null;
/* 457 */     if (plot.getOrientation() == PlotOrientation.HORIZONTAL) {
/* 458 */       hotspot = new Polygon();
/* 459 */       hotspot.addPoint((int)transZero, (int)((transX0 + transX1) / 2.0D));
/* 462 */       hotspot.addPoint((int)((transY0 + transY1) / 2.0D), (int)((transX0 + transX1) / 2.0D));
/* 466 */       hotspot.addPoint((int)transY1, (int)transX1);
/* 467 */       hotspot.addPoint((int)((transY1 + transY2) / 2.0D), (int)((transX1 + transX2) / 2.0D));
/* 471 */       hotspot.addPoint((int)transZero, (int)((transX1 + transX2) / 2.0D));
/*     */     } else {
/* 476 */       hotspot = new Polygon();
/* 477 */       hotspot.addPoint((int)((transX0 + transX1) / 2.0D), (int)transZero);
/* 480 */       hotspot.addPoint((int)((transX0 + transX1) / 2.0D), (int)((transY0 + transY1) / 2.0D));
/* 484 */       hotspot.addPoint((int)transX1, (int)transY1);
/* 485 */       hotspot.addPoint((int)((transX1 + transX2) / 2.0D), (int)((transY1 + transY2) / 2.0D));
/* 489 */       hotspot.addPoint((int)((transX1 + transX2) / 2.0D), (int)transZero);
/*     */     } 
/* 494 */     if (item == 0) {
/* 495 */       areaState.area = new Polygon();
/* 497 */       double zero = rangeAxis.valueToJava2D(0.0D, dataArea, plot.getRangeAxisEdge());
/* 500 */       if (plot.getOrientation() == PlotOrientation.VERTICAL) {
/* 501 */         areaState.area.addPoint((int)transX1, (int)zero);
/* 503 */       } else if (plot.getOrientation() == PlotOrientation.HORIZONTAL) {
/* 504 */         areaState.area.addPoint((int)zero, (int)transX1);
/*     */       } 
/*     */     } 
/* 509 */     if (plot.getOrientation() == PlotOrientation.VERTICAL) {
/* 510 */       areaState.area.addPoint((int)transX1, (int)transY1);
/* 512 */     } else if (plot.getOrientation() == PlotOrientation.HORIZONTAL) {
/* 513 */       areaState.area.addPoint((int)transY1, (int)transX1);
/*     */     } 
/* 516 */     PlotOrientation orientation = plot.getOrientation();
/* 517 */     Paint paint = getItemPaint(series, item);
/* 518 */     Stroke stroke = getItemStroke(series, item);
/* 519 */     g2.setPaint(paint);
/* 520 */     g2.setStroke(stroke);
/* 522 */     Shape shape = null;
/* 523 */     if (getPlotShapes()) {
/* 524 */       shape = getItemShape(series, item);
/* 525 */       if (orientation == PlotOrientation.VERTICAL) {
/* 526 */         shape = ShapeUtilities.createTranslatedShape(shape, transX1, transY1);
/* 530 */       } else if (orientation == PlotOrientation.HORIZONTAL) {
/* 531 */         shape = ShapeUtilities.createTranslatedShape(shape, transY1, transX1);
/*     */       } 
/* 535 */       g2.draw(shape);
/*     */     } 
/* 538 */     if (getPlotLines() && 
/* 539 */       item > 0) {
/* 540 */       if (plot.getOrientation() == PlotOrientation.VERTICAL) {
/* 541 */         areaState.line.setLine(transX0, transY0, transX1, transY1);
/* 543 */       } else if (plot.getOrientation() == PlotOrientation.HORIZONTAL) {
/* 544 */         areaState.line.setLine(transY0, transX0, transY1, transX1);
/*     */       } 
/* 546 */       g2.draw(areaState.line);
/*     */     } 
/* 552 */     if (getPlotArea() && item > 0 && item == itemCount - 1) {
/* 554 */       if (orientation == PlotOrientation.VERTICAL) {
/* 556 */         areaState.area.addPoint((int)transX1, (int)transZero);
/* 558 */       } else if (orientation == PlotOrientation.HORIZONTAL) {
/* 560 */         areaState.area.addPoint((int)transZero, (int)transX1);
/*     */       } 
/* 563 */       g2.fill(areaState.area);
/* 566 */       if (isOutline()) {
/* 567 */         g2.setStroke(getItemOutlineStroke(series, item));
/* 568 */         g2.setPaint(getItemOutlinePaint(series, item));
/* 569 */         g2.draw(areaState.area);
/*     */       } 
/*     */     } 
/* 573 */     updateCrosshairValues(crosshairState, x1, y1, transX1, transY1, orientation);
/* 578 */     if (state.getInfo() != null) {
/* 579 */       EntityCollection entities = state.getEntityCollection();
/* 580 */       if (entities != null && hotspot != null) {
/* 581 */         String tip = null;
/* 582 */         XYToolTipGenerator generator = getToolTipGenerator(series, item);
/* 584 */         if (generator != null)
/* 585 */           tip = generator.generateToolTip(dataset, series, item); 
/* 587 */         String url = null;
/* 588 */         if (getURLGenerator() != null)
/* 589 */           url = getURLGenerator().generateURL(dataset, series, item); 
/* 591 */         XYItemEntity entity = new XYItemEntity(hotspot, dataset, series, item, tip, url);
/* 594 */         entities.add((ChartEntity)entity);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 608 */     return super.clone();
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 619 */     if (obj == this)
/* 620 */       return true; 
/* 622 */     if (!(obj instanceof XYAreaRenderer))
/* 623 */       return false; 
/* 625 */     XYAreaRenderer that = (XYAreaRenderer)obj;
/* 626 */     if (this.plotArea != that.plotArea)
/* 627 */       return false; 
/* 629 */     if (this.plotLines != that.plotLines)
/* 630 */       return false; 
/* 632 */     if (this.plotShapes != that.plotShapes)
/* 633 */       return false; 
/* 635 */     if (this.showOutline != that.showOutline)
/* 636 */       return false; 
/* 638 */     if (!ShapeUtilities.equal(this.legendArea, that.legendArea))
/* 639 */       return false; 
/* 641 */     return true;
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
/* 654 */     stream.defaultReadObject();
/* 655 */     this.legendArea = SerialUtilities.readShape(stream);
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream stream) throws IOException {
/* 666 */     stream.defaultWriteObject();
/* 667 */     SerialUtilities.writeShape(this.legendArea, stream);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\renderer\xy\XYAreaRenderer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */