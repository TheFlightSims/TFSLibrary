/*     */ package org.jfree.chart.renderer.xy;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
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
/*     */ import org.jfree.chart.labels.XYToolTipGenerator;
/*     */ import org.jfree.chart.plot.CrosshairState;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.chart.plot.PlotRenderingInfo;
/*     */ import org.jfree.chart.plot.XYPlot;
/*     */ import org.jfree.data.xy.XYDataset;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.util.PaintUtilities;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ import org.jfree.util.ShapeUtilities;
/*     */ 
/*     */ public class XYDifferenceRenderer extends AbstractXYItemRenderer implements XYItemRenderer, Cloneable, PublicCloneable, Serializable {
/*     */   private static final long serialVersionUID = -8447915602375584857L;
/*     */   
/*     */   private transient Paint positivePaint;
/*     */   
/*     */   private transient Paint negativePaint;
/*     */   
/*     */   private boolean shapesVisible;
/*     */   
/*     */   private transient Shape legendLine;
/*     */   
/*     */   public XYDifferenceRenderer() {
/* 129 */     this(Color.green, Color.red, false);
/*     */   }
/*     */   
/*     */   public XYDifferenceRenderer(Paint positivePaint, Paint negativePaint, boolean shapes) {
/* 143 */     if (positivePaint == null)
/* 144 */       throw new IllegalArgumentException("Null 'positivePaint' argument."); 
/* 148 */     if (negativePaint == null)
/* 149 */       throw new IllegalArgumentException("Null 'negativePaint' argument."); 
/* 153 */     this.positivePaint = positivePaint;
/* 154 */     this.negativePaint = negativePaint;
/* 155 */     this.shapesVisible = shapes;
/* 156 */     this.legendLine = new Line2D.Double(-7.0D, 0.0D, 7.0D, 0.0D);
/*     */   }
/*     */   
/*     */   public Paint getPositivePaint() {
/* 165 */     return this.positivePaint;
/*     */   }
/*     */   
/*     */   public void setPositivePaint(Paint paint) {
/* 174 */     if (paint == null)
/* 175 */       throw new IllegalArgumentException("Null 'paint' argument."); 
/* 177 */     this.positivePaint = paint;
/* 178 */     notifyListeners(new RendererChangeEvent(this));
/*     */   }
/*     */   
/*     */   public Paint getNegativePaint() {
/* 187 */     return this.negativePaint;
/*     */   }
/*     */   
/*     */   public void setNegativePaint(Paint paint) {
/* 196 */     if (paint == null)
/* 197 */       throw new IllegalArgumentException("Null 'paint' argument."); 
/* 199 */     this.negativePaint = paint;
/* 200 */     notifyListeners(new RendererChangeEvent(this));
/*     */   }
/*     */   
/*     */   public boolean getShapesVisible() {
/* 210 */     return this.shapesVisible;
/*     */   }
/*     */   
/*     */   public void setShapesVisible(boolean flag) {
/* 220 */     this.shapesVisible = flag;
/* 221 */     notifyListeners(new RendererChangeEvent(this));
/*     */   }
/*     */   
/*     */   public Shape getLegendLine() {
/* 230 */     return this.legendLine;
/*     */   }
/*     */   
/*     */   public void setLegendLine(Shape line) {
/* 240 */     if (line == null)
/* 241 */       throw new IllegalArgumentException("Null 'line' argument."); 
/* 243 */     this.legendLine = line;
/* 244 */     notifyListeners(new RendererChangeEvent(this));
/*     */   }
/*     */   
/*     */   public XYItemRendererState initialise(Graphics2D g2, Rectangle2D dataArea, XYPlot plot, XYDataset data, PlotRenderingInfo info) {
/* 269 */     return super.initialise(g2, dataArea, plot, data, info);
/*     */   }
/*     */   
/*     */   public int getPassCount() {
/* 280 */     return 2;
/*     */   }
/*     */   
/*     */   public void drawItem(Graphics2D g2, XYItemRendererState state, Rectangle2D dataArea, PlotRenderingInfo info, XYPlot plot, ValueAxis domainAxis, ValueAxis rangeAxis, XYDataset dataset, int series, int item, CrosshairState crosshairState, int pass) {
/* 314 */     if (pass == 0) {
/* 315 */       drawItemPass0(g2, dataArea, info, plot, domainAxis, rangeAxis, dataset, series, item, crosshairState);
/* 320 */     } else if (pass == 1) {
/* 321 */       drawItemPass1(g2, dataArea, info, plot, domainAxis, rangeAxis, dataset, series, item, crosshairState);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void drawItemPass0(Graphics2D g2, Rectangle2D dataArea, PlotRenderingInfo info, XYPlot plot, ValueAxis domainAxis, ValueAxis rangeAxis, XYDataset dataset, int series, int item, CrosshairState crosshairState) {
/* 356 */     if (series == 0) {
/* 358 */       PlotOrientation orientation = plot.getOrientation();
/* 359 */       RectangleEdge domainAxisLocation = plot.getDomainAxisEdge();
/* 360 */       RectangleEdge rangeAxisLocation = plot.getRangeAxisEdge();
/* 362 */       double y0 = dataset.getYValue(0, item);
/* 363 */       double x1 = dataset.getXValue(1, item);
/* 364 */       double y1 = dataset.getYValue(1, item);
/* 366 */       double transY0 = rangeAxis.valueToJava2D(y0, dataArea, rangeAxisLocation);
/* 369 */       double transX1 = domainAxis.valueToJava2D(x1, dataArea, domainAxisLocation);
/* 372 */       double transY1 = rangeAxis.valueToJava2D(y1, dataArea, rangeAxisLocation);
/* 376 */       if (item > 0) {
/* 377 */         double prevx0 = dataset.getXValue(0, item - 1);
/* 378 */         double prevy0 = dataset.getYValue(0, item - 1);
/* 379 */         double prevy1 = dataset.getYValue(1, item - 1);
/* 381 */         double prevtransX0 = domainAxis.valueToJava2D(prevx0, dataArea, domainAxisLocation);
/* 384 */         double prevtransY0 = rangeAxis.valueToJava2D(prevy0, dataArea, rangeAxisLocation);
/* 387 */         double prevtransY1 = rangeAxis.valueToJava2D(prevy1, dataArea, rangeAxisLocation);
/* 391 */         Shape positive = getPositiveArea((float)prevtransX0, (float)prevtransY0, (float)prevtransY1, (float)transX1, (float)transY0, (float)transY1, orientation);
/* 397 */         if (positive != null) {
/* 398 */           g2.setPaint(getPositivePaint());
/* 399 */           g2.fill(positive);
/*     */         } 
/* 402 */         Shape negative = getNegativeArea((float)prevtransX0, (float)prevtransY0, (float)prevtransY1, (float)transX1, (float)transY0, (float)transY1, orientation);
/* 409 */         if (negative != null) {
/* 410 */           g2.setPaint(getNegativePaint());
/* 411 */           g2.fill(negative);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void drawItemPass1(Graphics2D g2, Rectangle2D dataArea, PlotRenderingInfo info, XYPlot plot, ValueAxis domainAxis, ValueAxis rangeAxis, XYDataset dataset, int series, int item, CrosshairState crosshairState) {
/* 447 */     Shape entityArea = null;
/* 448 */     EntityCollection entities = null;
/* 449 */     if (info != null)
/* 450 */       entities = info.getOwner().getEntityCollection(); 
/* 453 */     Paint seriesPaint = getItemPaint(series, item);
/* 454 */     Stroke seriesStroke = getItemStroke(series, item);
/* 455 */     g2.setPaint(seriesPaint);
/* 456 */     g2.setStroke(seriesStroke);
/* 458 */     if (series == 0) {
/* 460 */       PlotOrientation orientation = plot.getOrientation();
/* 461 */       RectangleEdge domainAxisLocation = plot.getDomainAxisEdge();
/* 462 */       RectangleEdge rangeAxisLocation = plot.getRangeAxisEdge();
/* 464 */       double x0 = dataset.getXValue(0, item);
/* 465 */       double y0 = dataset.getYValue(0, item);
/* 466 */       double x1 = dataset.getXValue(1, item);
/* 467 */       double y1 = dataset.getYValue(1, item);
/* 469 */       double transX0 = domainAxis.valueToJava2D(x0, dataArea, domainAxisLocation);
/* 472 */       double transY0 = rangeAxis.valueToJava2D(y0, dataArea, rangeAxisLocation);
/* 475 */       double transX1 = domainAxis.valueToJava2D(x1, dataArea, domainAxisLocation);
/* 478 */       double transY1 = rangeAxis.valueToJava2D(y1, dataArea, rangeAxisLocation);
/* 482 */       if (item > 0) {
/* 484 */         double prevx0 = dataset.getXValue(0, item - 1);
/* 485 */         double prevy0 = dataset.getYValue(0, item - 1);
/* 486 */         double prevx1 = dataset.getXValue(1, item - 1);
/* 487 */         double prevy1 = dataset.getYValue(1, item - 1);
/* 489 */         double prevtransX0 = domainAxis.valueToJava2D(prevx0, dataArea, domainAxisLocation);
/* 492 */         double prevtransY0 = rangeAxis.valueToJava2D(prevy0, dataArea, rangeAxisLocation);
/* 495 */         double prevtransX1 = domainAxis.valueToJava2D(prevx1, dataArea, domainAxisLocation);
/* 498 */         double prevtransY1 = rangeAxis.valueToJava2D(prevy1, dataArea, rangeAxisLocation);
/* 502 */         Line2D line0 = null;
/* 503 */         Line2D line1 = null;
/* 504 */         if (orientation == PlotOrientation.HORIZONTAL) {
/* 505 */           line0 = new Line2D.Double(transY0, transX0, prevtransY0, prevtransX0);
/* 508 */           line1 = new Line2D.Double(transY1, transX1, prevtransY1, prevtransX1);
/* 512 */         } else if (orientation == PlotOrientation.VERTICAL) {
/* 513 */           line0 = new Line2D.Double(transX0, transY0, prevtransX0, prevtransY0);
/* 516 */           line1 = new Line2D.Double(transX1, transY1, prevtransX1, prevtransY1);
/*     */         } 
/* 520 */         if (line0 != null && line0.intersects(dataArea)) {
/* 521 */           g2.setPaint(getItemPaint(series, item));
/* 522 */           g2.setStroke(getItemStroke(series, item));
/* 523 */           g2.draw(line0);
/*     */         } 
/* 525 */         if (line1 != null && line1.intersects(dataArea)) {
/* 526 */           g2.setPaint(getItemPaint(1, item));
/* 527 */           g2.setStroke(getItemStroke(1, item));
/* 528 */           g2.draw(line1);
/*     */         } 
/*     */       } 
/* 532 */       if (getShapesVisible()) {
/* 533 */         Shape shape0 = getItemShape(series, item);
/* 534 */         if (orientation == PlotOrientation.HORIZONTAL) {
/* 535 */           shape0 = ShapeUtilities.createTranslatedShape(shape0, transY0, transX0);
/*     */         } else {
/* 540 */           shape0 = ShapeUtilities.createTranslatedShape(shape0, transX0, transY0);
/*     */         } 
/* 544 */         if (shape0.intersects(dataArea)) {
/* 545 */           g2.setPaint(getItemPaint(series, item));
/* 546 */           g2.fill(shape0);
/*     */         } 
/* 548 */         entityArea = shape0;
/* 551 */         if (entities != null) {
/* 552 */           if (entityArea == null)
/* 553 */             entityArea = new Rectangle2D.Double(transX0 - 2.0D, transY0 - 2.0D, 4.0D, 4.0D); 
/* 557 */           String tip = null;
/* 558 */           XYToolTipGenerator generator = getToolTipGenerator(series, item);
/* 561 */           if (generator != null)
/* 562 */             tip = generator.generateToolTip(dataset, series, item); 
/* 564 */           String url = null;
/* 565 */           if (getURLGenerator() != null)
/* 566 */             url = getURLGenerator().generateURL(dataset, series, item); 
/* 570 */           XYItemEntity entity = new XYItemEntity(entityArea, dataset, series, item, tip, url);
/* 573 */           entities.add((ChartEntity)entity);
/*     */         } 
/* 576 */         Shape shape1 = getItemShape(series + 1, item);
/* 577 */         if (orientation == PlotOrientation.HORIZONTAL) {
/* 578 */           shape1 = ShapeUtilities.createTranslatedShape(shape1, transY1, transX1);
/*     */         } else {
/* 583 */           shape1 = ShapeUtilities.createTranslatedShape(shape1, transX1, transY1);
/*     */         } 
/* 587 */         if (shape1.intersects(dataArea)) {
/* 588 */           g2.setPaint(getItemPaint(series + 1, item));
/* 589 */           g2.fill(shape1);
/*     */         } 
/* 591 */         entityArea = shape1;
/* 594 */         if (entities != null) {
/* 595 */           if (entityArea == null)
/* 596 */             entityArea = new Rectangle2D.Double(transX1 - 2.0D, transY1 - 2.0D, 4.0D, 4.0D); 
/* 600 */           String tip = null;
/* 601 */           XYToolTipGenerator generator = getToolTipGenerator(series, item);
/* 604 */           if (generator != null)
/* 605 */             tip = generator.generateToolTip(dataset, series + 1, item); 
/* 609 */           String url = null;
/* 610 */           if (getURLGenerator() != null)
/* 611 */             url = getURLGenerator().generateURL(dataset, series + 1, item); 
/* 615 */           XYItemEntity entity = new XYItemEntity(entityArea, dataset, series + 1, item, tip, url);
/* 618 */           entities.add((ChartEntity)entity);
/*     */         } 
/*     */       } 
/* 621 */       updateCrosshairValues(crosshairState, x1, y1, transX1, transY1, orientation);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected Shape getPositiveArea(float x0, float y0A, float y0B, float x1, float y1A, float y1B, PlotOrientation orientation) {
/* 645 */     Shape result = null;
/* 647 */     boolean startsNegative = (y0A >= y0B);
/* 648 */     boolean endsNegative = (y1A >= y1B);
/* 649 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 650 */       startsNegative = (y0B >= y0A);
/* 651 */       endsNegative = (y1B >= y1A);
/*     */     } 
/* 654 */     if (startsNegative) {
/* 655 */       if (endsNegative) {
/* 657 */         result = null;
/*     */       } else {
/* 661 */         float[] p = getIntersection(x0, y0A, x1, y1A, x0, y0B, x1, y1B);
/* 662 */         GeneralPath area = new GeneralPath();
/* 663 */         if (orientation == PlotOrientation.HORIZONTAL) {
/* 664 */           area.moveTo(y1A, x1);
/* 665 */           area.lineTo(p[1], p[0]);
/* 666 */           area.lineTo(y1B, x1);
/* 667 */           area.closePath();
/* 669 */         } else if (orientation == PlotOrientation.VERTICAL) {
/* 670 */           area.moveTo(x1, y1A);
/* 671 */           area.lineTo(p[0], p[1]);
/* 672 */           area.lineTo(x1, y1B);
/* 673 */           area.closePath();
/*     */         } 
/* 675 */         result = area;
/*     */       } 
/* 679 */     } else if (endsNegative) {
/* 681 */       float[] p = getIntersection(x0, y0A, x1, y1A, x0, y0B, x1, y1B);
/* 682 */       GeneralPath area = new GeneralPath();
/* 683 */       if (orientation == PlotOrientation.HORIZONTAL) {
/* 684 */         area.moveTo(y0A, x0);
/* 685 */         area.lineTo(p[1], p[0]);
/* 686 */         area.lineTo(y0B, x0);
/* 687 */         area.closePath();
/* 689 */       } else if (orientation == PlotOrientation.VERTICAL) {
/* 690 */         area.moveTo(x0, y0A);
/* 691 */         area.lineTo(p[0], p[1]);
/* 692 */         area.lineTo(x0, y0B);
/* 693 */         area.closePath();
/*     */       } 
/* 695 */       result = area;
/*     */     } else {
/* 699 */       GeneralPath area = new GeneralPath();
/* 700 */       if (orientation == PlotOrientation.HORIZONTAL) {
/* 701 */         area.moveTo(y0A, x0);
/* 702 */         area.lineTo(y1A, x1);
/* 703 */         area.lineTo(y1B, x1);
/* 704 */         area.lineTo(y0B, x0);
/* 705 */         area.closePath();
/* 707 */       } else if (orientation == PlotOrientation.VERTICAL) {
/* 708 */         area.moveTo(x0, y0A);
/* 709 */         area.lineTo(x1, y1A);
/* 710 */         area.lineTo(x1, y1B);
/* 711 */         area.lineTo(x0, y0B);
/* 712 */         area.closePath();
/*     */       } 
/* 714 */       result = area;
/*     */     } 
/* 719 */     return result;
/*     */   }
/*     */   
/*     */   protected Shape getNegativeArea(float x0, float y0A, float y0B, float x1, float y1A, float y1B, PlotOrientation orientation) {
/* 740 */     Shape result = null;
/* 742 */     boolean startsNegative = (y0A >= y0B);
/* 743 */     boolean endsNegative = (y1A >= y1B);
/* 744 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 745 */       startsNegative = (y0B >= y0A);
/* 746 */       endsNegative = (y1B >= y1A);
/*     */     } 
/* 748 */     if (startsNegative) {
/* 749 */       if (endsNegative) {
/* 750 */         GeneralPath area = new GeneralPath();
/* 751 */         if (orientation == PlotOrientation.HORIZONTAL) {
/* 752 */           area.moveTo(y0A, x0);
/* 753 */           area.lineTo(y1A, x1);
/* 754 */           area.lineTo(y1B, x1);
/* 755 */           area.lineTo(y0B, x0);
/* 756 */           area.closePath();
/* 758 */         } else if (orientation == PlotOrientation.VERTICAL) {
/* 759 */           area.moveTo(x0, y0A);
/* 760 */           area.lineTo(x1, y1A);
/* 761 */           area.lineTo(x1, y1B);
/* 762 */           area.lineTo(x0, y0B);
/* 763 */           area.closePath();
/*     */         } 
/* 765 */         result = area;
/*     */       } else {
/* 768 */         float[] p = getIntersection(x0, y0A, x1, y1A, x0, y0B, x1, y1B);
/* 769 */         GeneralPath area = new GeneralPath();
/* 770 */         if (orientation == PlotOrientation.HORIZONTAL) {
/* 771 */           area.moveTo(y0A, x0);
/* 772 */           area.lineTo(p[1], p[0]);
/* 773 */           area.lineTo(y0B, x0);
/* 774 */           area.closePath();
/* 776 */         } else if (orientation == PlotOrientation.VERTICAL) {
/* 777 */           area.moveTo(x0, y0A);
/* 778 */           area.lineTo(p[0], p[1]);
/* 779 */           area.lineTo(x0, y0B);
/* 780 */           area.closePath();
/*     */         } 
/* 782 */         result = area;
/*     */       } 
/* 786 */     } else if (endsNegative) {
/* 788 */       float[] p = getIntersection(x0, y0A, x1, y1A, x0, y0B, x1, y1B);
/* 789 */       GeneralPath area = new GeneralPath();
/* 790 */       if (orientation == PlotOrientation.HORIZONTAL) {
/* 791 */         area.moveTo(y1A, x1);
/* 792 */         area.lineTo(p[1], p[0]);
/* 793 */         area.lineTo(y1B, x1);
/* 794 */         area.closePath();
/* 796 */       } else if (orientation == PlotOrientation.VERTICAL) {
/* 797 */         area.moveTo(x1, y1A);
/* 798 */         area.lineTo(p[0], p[1]);
/* 799 */         area.lineTo(x1, y1B);
/* 800 */         area.closePath();
/*     */       } 
/* 802 */       result = area;
/*     */     } 
/* 810 */     return result;
/*     */   }
/*     */   
/*     */   private float[] getIntersection(float x1, float y1, float x2, float y2, float x3, float y3, float x4, float y4) {
/* 831 */     float n = (x4 - x3) * (y1 - y3) - (y4 - y3) * (x1 - x3);
/* 832 */     float d = (y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1);
/* 833 */     float u = n / d;
/* 835 */     float[] result = new float[2];
/* 836 */     result[0] = x1 + u * (x2 - x1);
/* 837 */     result[1] = y1 + u * (y2 - y1);
/* 838 */     return result;
/*     */   }
/*     */   
/*     */   public LegendItem getLegendItem(int datasetIndex, int series) {
/* 852 */     LegendItem result = null;
/* 853 */     XYPlot p = getPlot();
/* 854 */     if (p != null) {
/* 855 */       XYDataset dataset = p.getDataset(datasetIndex);
/* 856 */       if (dataset != null && 
/* 857 */         getItemVisible(series, 0)) {
/* 858 */         String label = getLegendItemLabelGenerator().generateLabel(dataset, series);
/* 861 */         String description = label;
/* 862 */         String toolTipText = null;
/* 863 */         if (getLegendItemToolTipGenerator() != null)
/* 864 */           toolTipText = getLegendItemToolTipGenerator().generateLabel(dataset, series); 
/* 868 */         String urlText = null;
/* 869 */         if (getLegendItemURLGenerator() != null)
/* 870 */           urlText = getLegendItemURLGenerator().generateLabel(dataset, series); 
/* 874 */         Paint paint = getSeriesPaint(series);
/* 875 */         Stroke stroke = getSeriesStroke(series);
/* 877 */         Line2D line = new Line2D.Double(-7.0D, 0.0D, 7.0D, 0.0D);
/* 878 */         result = new LegendItem(label, description, toolTipText, urlText, line, stroke, paint);
/*     */       } 
/*     */     } 
/* 885 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 897 */     if (obj == this)
/* 898 */       return true; 
/* 900 */     if (!(obj instanceof XYDifferenceRenderer))
/* 901 */       return false; 
/* 903 */     if (!super.equals(obj))
/* 904 */       return false; 
/* 906 */     XYDifferenceRenderer that = (XYDifferenceRenderer)obj;
/* 907 */     if (!PaintUtilities.equal(this.positivePaint, that.positivePaint))
/* 908 */       return false; 
/* 910 */     if (!PaintUtilities.equal(this.negativePaint, that.negativePaint))
/* 911 */       return false; 
/* 913 */     if (this.shapesVisible != that.shapesVisible)
/* 914 */       return false; 
/* 916 */     if (!ShapeUtilities.equal(this.legendLine, that.legendLine))
/* 917 */       return false; 
/* 919 */     return true;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 930 */     return super.clone();
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream stream) throws IOException {
/* 941 */     stream.defaultWriteObject();
/* 942 */     SerialUtilities.writePaint(this.positivePaint, stream);
/* 943 */     SerialUtilities.writePaint(this.negativePaint, stream);
/* 944 */     SerialUtilities.writeShape(this.legendLine, stream);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
/* 957 */     stream.defaultReadObject();
/* 958 */     this.positivePaint = SerialUtilities.readPaint(stream);
/* 959 */     this.negativePaint = SerialUtilities.readPaint(stream);
/* 960 */     this.legendLine = SerialUtilities.readShape(stream);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\renderer\xy\XYDifferenceRenderer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */