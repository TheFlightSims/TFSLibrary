/*     */ package org.jfree.chart.renderer.xy;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Polygon;
/*     */ import java.awt.Shape;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.entity.ChartEntity;
/*     */ import org.jfree.chart.entity.EntityCollection;
/*     */ import org.jfree.chart.entity.XYItemEntity;
/*     */ import org.jfree.chart.event.RendererChangeEvent;
/*     */ import org.jfree.chart.labels.XYToolTipGenerator;
/*     */ import org.jfree.chart.plot.CrosshairState;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.chart.plot.PlotRenderingInfo;
/*     */ import org.jfree.chart.plot.XYPlot;
/*     */ import org.jfree.chart.urls.XYURLGenerator;
/*     */ import org.jfree.data.xy.XYDataset;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ import org.jfree.util.ShapeUtilities;
/*     */ 
/*     */ public class XYStepAreaRenderer extends AbstractXYItemRenderer implements XYItemRenderer, Cloneable, PublicCloneable, Serializable {
/*     */   private static final long serialVersionUID = -7311560779702649635L;
/*     */   
/*     */   public static final int SHAPES = 1;
/*     */   
/*     */   public static final int AREA = 2;
/*     */   
/*     */   public static final int AREA_AND_SHAPES = 3;
/*     */   
/*     */   private boolean shapesVisible;
/*     */   
/*     */   private boolean shapesFilled;
/*     */   
/*     */   private boolean plotArea;
/*     */   
/*     */   private boolean showOutline;
/*     */   
/* 110 */   protected transient Polygon pArea = null;
/*     */   
/*     */   private double rangeBase;
/*     */   
/*     */   public XYStepAreaRenderer() {
/* 122 */     this(2);
/*     */   }
/*     */   
/*     */   public XYStepAreaRenderer(int type) {
/* 131 */     this(type, (XYToolTipGenerator)null, (XYURLGenerator)null);
/*     */   }
/*     */   
/*     */   public XYStepAreaRenderer(int type, XYToolTipGenerator toolTipGenerator, XYURLGenerator urlGenerator) {
/* 150 */     setBaseToolTipGenerator(toolTipGenerator);
/* 151 */     setURLGenerator(urlGenerator);
/* 153 */     if (type == 2) {
/* 154 */       this.plotArea = true;
/* 156 */     } else if (type == 1) {
/* 157 */       this.shapesVisible = true;
/* 159 */     } else if (type == 3) {
/* 160 */       this.plotArea = true;
/* 161 */       this.shapesVisible = true;
/*     */     } 
/* 163 */     this.showOutline = false;
/*     */   }
/*     */   
/*     */   public boolean isOutline() {
/* 173 */     return this.showOutline;
/*     */   }
/*     */   
/*     */   public void setOutline(boolean show) {
/* 184 */     this.showOutline = show;
/* 185 */     notifyListeners(new RendererChangeEvent(this));
/*     */   }
/*     */   
/*     */   public boolean getShapesVisible() {
/* 194 */     return this.shapesVisible;
/*     */   }
/*     */   
/*     */   public void setShapesVisible(boolean flag) {
/* 205 */     this.shapesVisible = flag;
/* 206 */     notifyListeners(new RendererChangeEvent(this));
/*     */   }
/*     */   
/*     */   public boolean isShapesFilled() {
/* 215 */     return this.shapesFilled;
/*     */   }
/*     */   
/*     */   public void setShapesFilled(boolean filled) {
/* 224 */     this.shapesFilled = filled;
/* 225 */     notifyListeners(new RendererChangeEvent(this));
/*     */   }
/*     */   
/*     */   public boolean getPlotArea() {
/* 234 */     return this.plotArea;
/*     */   }
/*     */   
/*     */   public void setPlotArea(boolean flag) {
/* 244 */     this.plotArea = flag;
/* 245 */     notifyListeners(new RendererChangeEvent(this));
/*     */   }
/*     */   
/*     */   public double getRangeBase() {
/* 256 */     return this.rangeBase;
/*     */   }
/*     */   
/*     */   public void setRangeBase(double val) {
/* 268 */     this.rangeBase = val;
/* 269 */     notifyListeners(new RendererChangeEvent(this));
/*     */   }
/*     */   
/*     */   public XYItemRendererState initialise(Graphics2D g2, Rectangle2D dataArea, XYPlot plot, XYDataset data, PlotRenderingInfo info) {
/* 291 */     return super.initialise(g2, dataArea, plot, data, info);
/*     */   }
/*     */   
/*     */   public void drawItem(Graphics2D g2, XYItemRendererState state, Rectangle2D dataArea, PlotRenderingInfo info, XYPlot plot, ValueAxis domainAxis, ValueAxis rangeAxis, XYDataset dataset, int series, int item, CrosshairState crosshairState, int pass) {
/* 327 */     PlotOrientation orientation = plot.getOrientation();
/* 331 */     int itemCount = dataset.getItemCount(series);
/* 333 */     Paint paint = getItemPaint(series, item);
/* 334 */     Stroke seriesStroke = getItemStroke(series, item);
/* 335 */     g2.setPaint(paint);
/* 336 */     g2.setStroke(seriesStroke);
/* 339 */     Number x1 = dataset.getX(series, item);
/* 340 */     Number y1 = dataset.getY(series, item);
/* 341 */     double x = x1.doubleValue();
/* 342 */     double y = (y1 == null) ? getRangeBase() : y1.doubleValue();
/* 343 */     double transX1 = domainAxis.valueToJava2D(x, dataArea, plot.getDomainAxisEdge());
/* 346 */     double transY1 = rangeAxis.valueToJava2D(y, dataArea, plot.getRangeAxisEdge());
/* 351 */     transY1 = restrictValueToDataArea(transY1, plot, dataArea);
/* 353 */     if (this.pArea == null && y1 != null) {
/* 356 */       this.pArea = new Polygon();
/* 359 */       double transY2 = rangeAxis.valueToJava2D(getRangeBase(), dataArea, plot.getRangeAxisEdge());
/* 364 */       transY2 = restrictValueToDataArea(transY2, plot, dataArea);
/* 367 */       if (orientation == PlotOrientation.VERTICAL) {
/* 368 */         this.pArea.addPoint((int)transX1, (int)transY2);
/* 370 */       } else if (orientation == PlotOrientation.HORIZONTAL) {
/* 371 */         this.pArea.addPoint((int)transY2, (int)transX1);
/*     */       } 
/*     */     } 
/* 375 */     double transX0 = 0.0D;
/* 376 */     double transY0 = restrictValueToDataArea(getRangeBase(), plot, dataArea);
/* 380 */     Number x0 = null;
/* 381 */     Number y0 = null;
/* 382 */     if (item > 0) {
/* 384 */       x0 = dataset.getX(series, item - 1);
/* 385 */       y0 = (y1 == null) ? null : dataset.getY(series, item - 1);
/* 387 */       x = x0.doubleValue();
/* 388 */       y = (y0 == null) ? getRangeBase() : y0.doubleValue();
/* 389 */       transX0 = domainAxis.valueToJava2D(x, dataArea, plot.getDomainAxisEdge());
/* 392 */       transY0 = rangeAxis.valueToJava2D(y, dataArea, plot.getRangeAxisEdge());
/* 397 */       transY0 = restrictValueToDataArea(transY0, plot, dataArea);
/* 399 */       if (y1 == null) {
/* 402 */         transX1 = transX0;
/* 403 */         transY0 = transY1;
/*     */       } 
/* 405 */       if (transY0 != transY1)
/* 407 */         if (orientation == PlotOrientation.VERTICAL) {
/* 408 */           this.pArea.addPoint((int)transX1, (int)transY0);
/* 410 */         } else if (orientation == PlotOrientation.HORIZONTAL) {
/* 411 */           this.pArea.addPoint((int)transY0, (int)transX1);
/*     */         }  
/*     */     } 
/* 416 */     Shape shape = null;
/* 417 */     if (y1 != null) {
/* 419 */       if (orientation == PlotOrientation.VERTICAL) {
/* 420 */         this.pArea.addPoint((int)transX1, (int)transY1);
/* 422 */       } else if (orientation == PlotOrientation.HORIZONTAL) {
/* 423 */         this.pArea.addPoint((int)transY1, (int)transX1);
/*     */       } 
/* 426 */       if (getShapesVisible()) {
/* 427 */         shape = getItemShape(series, item);
/* 428 */         if (orientation == PlotOrientation.VERTICAL) {
/* 429 */           shape = ShapeUtilities.createTranslatedShape(shape, transX1, transY1);
/* 433 */         } else if (orientation == PlotOrientation.HORIZONTAL) {
/* 434 */           shape = ShapeUtilities.createTranslatedShape(shape, transY1, transX1);
/*     */         } 
/* 438 */         if (isShapesFilled()) {
/* 439 */           g2.fill(shape);
/*     */         } else {
/* 442 */           g2.draw(shape);
/*     */         } 
/* 446 */       } else if (orientation == PlotOrientation.VERTICAL) {
/* 447 */         shape = new Rectangle2D.Double(transX1 - 2.0D, transY1 - 2.0D, 4.0D, 4.0D);
/* 451 */       } else if (orientation == PlotOrientation.HORIZONTAL) {
/* 452 */         shape = new Rectangle2D.Double(transY1 - 2.0D, transX1 - 2.0D, 4.0D, 4.0D);
/*     */       } 
/*     */     } 
/* 462 */     if (getPlotArea() && item > 0 && this.pArea != null && (item == itemCount - 1 || y1 == null)) {
/* 465 */       double transY2 = rangeAxis.valueToJava2D(getRangeBase(), dataArea, plot.getRangeAxisEdge());
/* 470 */       transY2 = restrictValueToDataArea(transY2, plot, dataArea);
/* 472 */       if (orientation == PlotOrientation.VERTICAL) {
/* 474 */         this.pArea.addPoint((int)transX1, (int)transY2);
/* 476 */       } else if (orientation == PlotOrientation.HORIZONTAL) {
/* 478 */         this.pArea.addPoint((int)transY2, (int)transX1);
/*     */       } 
/* 482 */       g2.fill(this.pArea);
/* 485 */       if (isOutline()) {
/* 486 */         g2.setStroke(plot.getOutlineStroke());
/* 487 */         g2.setPaint(plot.getOutlinePaint());
/* 488 */         g2.draw(this.pArea);
/*     */       } 
/* 492 */       this.pArea = null;
/*     */     } 
/* 496 */     if (y1 != null)
/* 497 */       updateCrosshairValues(crosshairState, x1.doubleValue(), y1.doubleValue(), transX1, transY1, orientation); 
/* 504 */     if (state.getInfo() != null) {
/* 505 */       EntityCollection entities = state.getEntityCollection();
/* 506 */       if (entities != null && shape != null) {
/* 507 */         String tip = null;
/* 508 */         XYToolTipGenerator generator = getToolTipGenerator(series, item);
/* 510 */         if (generator != null)
/* 511 */           tip = generator.generateToolTip(dataset, series, item); 
/* 513 */         String url = null;
/* 514 */         if (getURLGenerator() != null)
/* 515 */           url = getURLGenerator().generateURL(dataset, series, item); 
/* 517 */         XYItemEntity entity = new XYItemEntity(shape, dataset, series, item, tip, url);
/* 520 */         entities.add((ChartEntity)entity);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 533 */     return super.clone();
/*     */   }
/*     */   
/*     */   protected static double restrictValueToDataArea(double value, XYPlot plot, Rectangle2D dataArea) {
/* 554 */     double min = 0.0D;
/* 555 */     double max = 0.0D;
/* 556 */     if (plot.getOrientation() == PlotOrientation.VERTICAL) {
/* 557 */       min = dataArea.getMinY();
/* 558 */       max = dataArea.getMaxY();
/* 560 */     } else if (plot.getOrientation() == PlotOrientation.HORIZONTAL) {
/* 561 */       min = dataArea.getMinX();
/* 562 */       max = dataArea.getMaxX();
/*     */     } 
/* 564 */     if (value < min) {
/* 565 */       value = min;
/* 567 */     } else if (value > max) {
/* 568 */       value = max;
/*     */     } 
/* 570 */     return value;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\renderer\xy\XYStepAreaRenderer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */