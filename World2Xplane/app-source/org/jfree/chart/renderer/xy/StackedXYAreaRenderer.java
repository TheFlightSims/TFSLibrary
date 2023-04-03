/*     */ package org.jfree.chart.renderer.xy;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Point;
/*     */ import java.awt.Polygon;
/*     */ import java.awt.Shape;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.Line2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Stack;
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
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.data.general.DatasetUtilities;
/*     */ import org.jfree.data.xy.TableXYDataset;
/*     */ import org.jfree.data.xy.XYDataset;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ import org.jfree.util.PaintUtilities;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ import org.jfree.util.ShapeUtilities;
/*     */ 
/*     */ public class StackedXYAreaRenderer extends XYAreaRenderer implements Cloneable, PublicCloneable, Serializable {
/*     */   private static final long serialVersionUID = 5217394318178570889L;
/*     */   
/*     */   static class StackedXYAreaRendererState extends XYItemRendererState {
/*     */     private Polygon seriesArea;
/*     */     
/*     */     private Line2D line;
/*     */     
/*     */     private Stack lastSeriesPoints;
/*     */     
/*     */     private Stack currentSeriesPoints;
/*     */     
/*     */     public StackedXYAreaRendererState(PlotRenderingInfo info) {
/* 132 */       super(info);
/* 133 */       this.seriesArea = null;
/* 134 */       this.line = null;
/* 135 */       this.lastSeriesPoints = new Stack();
/* 136 */       this.currentSeriesPoints = new Stack();
/*     */     }
/*     */     
/*     */     public Polygon getSeriesArea() {
/* 145 */       return this.seriesArea;
/*     */     }
/*     */     
/*     */     public void setSeriesArea(Polygon area) {
/* 154 */       this.seriesArea = area;
/*     */     }
/*     */     
/*     */     public Line2D getLine() {
/* 163 */       return this.line;
/*     */     }
/*     */     
/*     */     public Stack getCurrentSeriesPoints() {
/* 172 */       return this.currentSeriesPoints;
/*     */     }
/*     */     
/*     */     public void setCurrentSeriesPoints(Stack points) {
/* 181 */       this.currentSeriesPoints = points;
/*     */     }
/*     */     
/*     */     public Stack getLastSeriesPoints() {
/* 190 */       return this.lastSeriesPoints;
/*     */     }
/*     */     
/*     */     public void setLastSeriesPoints(Stack points) {
/* 199 */       this.lastSeriesPoints = points;
/*     */     }
/*     */   }
/*     */   
/* 207 */   private transient Paint shapePaint = null;
/*     */   
/* 213 */   private transient Stroke shapeStroke = null;
/*     */   
/*     */   public StackedXYAreaRenderer() {
/* 219 */     this(4);
/*     */   }
/*     */   
/*     */   public StackedXYAreaRenderer(int type) {
/* 227 */     this(type, (XYToolTipGenerator)null, (XYURLGenerator)null);
/*     */   }
/*     */   
/*     */   public StackedXYAreaRenderer(int type, XYToolTipGenerator labelGenerator, XYURLGenerator urlGenerator) {
/* 245 */     super(type, labelGenerator, urlGenerator);
/*     */   }
/*     */   
/*     */   public Paint getShapePaint() {
/* 255 */     return this.shapePaint;
/*     */   }
/*     */   
/*     */   public Stroke getShapeStroke() {
/* 265 */     return this.shapeStroke;
/*     */   }
/*     */   
/*     */   public void setShapePaint(Paint shapePaint) {
/* 274 */     this.shapePaint = shapePaint;
/*     */   }
/*     */   
/*     */   public void setShapeStroke(Stroke shapeStroke) {
/* 283 */     this.shapeStroke = shapeStroke;
/*     */   }
/*     */   
/*     */   public XYItemRendererState initialise(Graphics2D g2, Rectangle2D dataArea, XYPlot plot, XYDataset data, PlotRenderingInfo info) {
/* 307 */     return new StackedXYAreaRendererState(info);
/*     */   }
/*     */   
/*     */   public int getPassCount() {
/* 317 */     return 2;
/*     */   }
/*     */   
/*     */   public Range findRangeBounds(XYDataset dataset) {
/* 330 */     if (dataset != null)
/* 331 */       return DatasetUtilities.findStackedRangeBounds((TableXYDataset)dataset); 
/* 336 */     return null;
/*     */   }
/*     */   
/*     */   public void drawItem(Graphics2D g2, XYItemRendererState state, Rectangle2D dataArea, PlotRenderingInfo info, XYPlot plot, ValueAxis domainAxis, ValueAxis rangeAxis, XYDataset dataset, int series, int item, CrosshairState crosshairState, int pass) {
/* 370 */     PlotOrientation orientation = plot.getOrientation();
/* 371 */     StackedXYAreaRendererState areaState = (StackedXYAreaRendererState)state;
/* 375 */     TableXYDataset tdataset = (TableXYDataset)dataset;
/* 376 */     int itemCount = tdataset.getItemCount();
/* 379 */     double x1 = dataset.getXValue(series, item);
/* 380 */     double y1 = dataset.getYValue(series, item);
/* 381 */     boolean nullPoint = false;
/* 382 */     if (Double.isNaN(y1)) {
/* 383 */       y1 = 0.0D;
/* 384 */       nullPoint = true;
/*     */     } 
/* 388 */     double ph1 = getPreviousHeight(tdataset, series, item);
/* 389 */     double transX1 = domainAxis.valueToJava2D(x1, dataArea, plot.getDomainAxisEdge());
/* 392 */     double transY1 = rangeAxis.valueToJava2D(y1 + ph1, dataArea, plot.getRangeAxisEdge());
/* 397 */     Paint seriesPaint = getItemPaint(series, item);
/* 398 */     Stroke seriesStroke = getItemStroke(series, item);
/* 400 */     if (pass == 0) {
/* 403 */       if (item == 0) {
/* 405 */         areaState.setSeriesArea(new Polygon());
/* 406 */         areaState.setLastSeriesPoints(areaState.getCurrentSeriesPoints());
/* 409 */         areaState.setCurrentSeriesPoints(new Stack());
/* 412 */         double transY2 = rangeAxis.valueToJava2D(ph1, dataArea, plot.getRangeAxisEdge());
/* 417 */         if (orientation == PlotOrientation.VERTICAL) {
/* 418 */           areaState.getSeriesArea().addPoint((int)transX1, (int)transY2);
/* 422 */         } else if (orientation == PlotOrientation.HORIZONTAL) {
/* 423 */           areaState.getSeriesArea().addPoint((int)transY2, (int)transX1);
/*     */         } 
/*     */       } 
/* 430 */       if (orientation == PlotOrientation.VERTICAL) {
/* 431 */         Point point = new Point((int)transX1, (int)transY1);
/* 432 */         areaState.getSeriesArea().addPoint((int)point.getX(), (int)point.getY());
/* 435 */         areaState.getCurrentSeriesPoints().push(point);
/* 437 */       } else if (orientation == PlotOrientation.HORIZONTAL) {
/* 438 */         areaState.getSeriesArea().addPoint((int)transY1, (int)transX1);
/*     */       } 
/* 443 */       if (getPlotLines() && 
/* 444 */         item > 0) {
/* 446 */         double x0 = dataset.getXValue(series, item - 1);
/* 447 */         double y0 = dataset.getYValue(series, item - 1);
/* 448 */         double ph0 = getPreviousHeight(tdataset, series, item - 1);
/* 449 */         double transX0 = domainAxis.valueToJava2D(x0, dataArea, plot.getDomainAxisEdge());
/* 452 */         double transY0 = rangeAxis.valueToJava2D(y0 + ph0, dataArea, plot.getRangeAxisEdge());
/* 456 */         if (orientation == PlotOrientation.VERTICAL) {
/* 457 */           areaState.getLine().setLine(transX0, transY0, transX1, transY1);
/* 461 */         } else if (orientation == PlotOrientation.HORIZONTAL) {
/* 462 */           areaState.getLine().setLine(transY0, transX0, transY1, transX1);
/*     */         } 
/* 466 */         g2.draw(areaState.getLine());
/*     */       } 
/* 472 */       if (getPlotArea() && item > 0 && item == itemCount - 1) {
/* 474 */         double transY2 = rangeAxis.valueToJava2D(ph1, dataArea, plot.getRangeAxisEdge());
/* 478 */         if (orientation == PlotOrientation.VERTICAL) {
/* 480 */           areaState.getSeriesArea().addPoint((int)transX1, (int)transY2);
/* 484 */         } else if (orientation == PlotOrientation.HORIZONTAL) {
/* 486 */           areaState.getSeriesArea().addPoint((int)transY2, (int)transX1);
/*     */         } 
/* 493 */         if (series != 0) {
/* 494 */           Stack points = areaState.getLastSeriesPoints();
/* 495 */           while (!points.empty()) {
/* 496 */             Point point = points.pop();
/* 497 */             areaState.getSeriesArea().addPoint((int)point.getX(), (int)point.getY());
/*     */           } 
/*     */         } 
/* 504 */         g2.setPaint(seriesPaint);
/* 505 */         g2.setStroke(seriesStroke);
/* 506 */         g2.fill(areaState.getSeriesArea());
/* 509 */         if (isOutline()) {
/* 510 */           g2.setStroke(getSeriesOutlineStroke(series));
/* 511 */           g2.setPaint(getSeriesOutlinePaint(series));
/* 512 */           g2.draw(areaState.getSeriesArea());
/*     */         } 
/*     */       } 
/* 516 */       updateCrosshairValues(crosshairState, x1, y1, transX1, transY1, orientation);
/* 521 */     } else if (pass == 1) {
/* 525 */       Shape shape = null;
/* 526 */       if (getPlotShapes()) {
/* 527 */         shape = getItemShape(series, item);
/* 528 */         if (plot.getOrientation() == PlotOrientation.VERTICAL) {
/* 529 */           shape = ShapeUtilities.createTranslatedShape(shape, transX1, transY1);
/* 533 */         } else if (plot.getOrientation() == PlotOrientation.HORIZONTAL) {
/* 534 */           shape = ShapeUtilities.createTranslatedShape(shape, transY1, transX1);
/*     */         } 
/* 538 */         if (!nullPoint) {
/* 539 */           if (getShapePaint() != null) {
/* 540 */             g2.setPaint(getShapePaint());
/*     */           } else {
/* 543 */             g2.setPaint(seriesPaint);
/*     */           } 
/* 545 */           if (getShapeStroke() != null) {
/* 546 */             g2.setStroke(getShapeStroke());
/*     */           } else {
/* 549 */             g2.setStroke(seriesStroke);
/*     */           } 
/* 551 */           g2.draw(shape);
/*     */         } 
/* 555 */       } else if (plot.getOrientation() == PlotOrientation.VERTICAL) {
/* 556 */         shape = new Rectangle2D.Double(transX1 - 3.0D, transY1 - 3.0D, 6.0D, 6.0D);
/* 560 */       } else if (plot.getOrientation() == PlotOrientation.HORIZONTAL) {
/* 561 */         shape = new Rectangle2D.Double(transY1 - 3.0D, transX1 - 3.0D, 6.0D, 6.0D);
/*     */       } 
/* 568 */       if (state.getInfo() != null) {
/* 569 */         EntityCollection entities = state.getEntityCollection();
/* 570 */         if (entities != null && shape != null && !nullPoint) {
/* 571 */           String tip = null;
/* 572 */           XYToolTipGenerator generator = getToolTipGenerator(series, item);
/* 574 */           if (generator != null)
/* 575 */             tip = generator.generateToolTip(dataset, series, item); 
/* 577 */           String url = null;
/* 578 */           if (getURLGenerator() != null)
/* 579 */             url = getURLGenerator().generateURL(dataset, series, item); 
/* 583 */           XYItemEntity entity = new XYItemEntity(shape, dataset, series, item, tip, url);
/* 586 */           entities.add((ChartEntity)entity);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected double getPreviousHeight(TableXYDataset dataset, int series, int index) {
/* 607 */     double result = 0.0D;
/* 608 */     for (int i = 0; i < series; i++) {
/* 609 */       double value = dataset.getYValue(i, index);
/* 610 */       if (!Double.isNaN(value))
/* 611 */         result += value; 
/*     */     } 
/* 614 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 625 */     if (obj == this)
/* 626 */       return true; 
/* 628 */     if (!(obj instanceof StackedXYAreaRenderer) || !super.equals(obj))
/* 629 */       return false; 
/* 631 */     StackedXYAreaRenderer that = (StackedXYAreaRenderer)obj;
/* 632 */     if (!PaintUtilities.equal(this.shapePaint, that.shapePaint))
/* 633 */       return false; 
/* 635 */     if (!ObjectUtilities.equal(this.shapeStroke, that.shapeStroke))
/* 636 */       return false; 
/* 638 */     return true;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 649 */     return super.clone();
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
/* 662 */     stream.defaultReadObject();
/* 663 */     this.shapePaint = SerialUtilities.readPaint(stream);
/* 664 */     this.shapeStroke = SerialUtilities.readStroke(stream);
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream stream) throws IOException {
/* 675 */     stream.defaultWriteObject();
/* 676 */     SerialUtilities.writePaint(this.shapePaint, stream);
/* 677 */     SerialUtilities.writeStroke(this.shapeStroke, stream);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\renderer\xy\StackedXYAreaRenderer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */