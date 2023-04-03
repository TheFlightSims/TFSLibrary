/*     */ package org.jfree.chart.renderer.xy;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Shape;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.Ellipse2D;
/*     */ import java.awt.geom.Line2D;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.entity.ChartEntity;
/*     */ import org.jfree.chart.entity.EntityCollection;
/*     */ import org.jfree.chart.entity.XYItemEntity;
/*     */ import org.jfree.chart.event.RendererChangeEvent;
/*     */ import org.jfree.chart.labels.BoxAndWhiskerXYToolTipGenerator;
/*     */ import org.jfree.chart.labels.XYToolTipGenerator;
/*     */ import org.jfree.chart.plot.CrosshairState;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.chart.plot.PlotRenderingInfo;
/*     */ import org.jfree.chart.plot.XYPlot;
/*     */ import org.jfree.chart.renderer.Outlier;
/*     */ import org.jfree.chart.renderer.OutlierList;
/*     */ import org.jfree.chart.renderer.OutlierListCollection;
/*     */ import org.jfree.data.statistics.BoxAndWhiskerXYDataset;
/*     */ import org.jfree.data.xy.XYDataset;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.util.PaintUtilities;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ public class XYBoxAndWhiskerRenderer extends AbstractXYItemRenderer implements XYItemRenderer, Cloneable, PublicCloneable, Serializable {
/*     */   private static final long serialVersionUID = -8020170108532232324L;
/*     */   
/*     */   private double boxWidth;
/*     */   
/*     */   private transient Paint boxPaint;
/*     */   
/*     */   private boolean fillBox;
/*     */   
/* 143 */   private transient Paint artifactPaint = Color.black;
/*     */   
/*     */   public XYBoxAndWhiskerRenderer() {
/* 149 */     this(-1.0D);
/*     */   }
/*     */   
/*     */   public XYBoxAndWhiskerRenderer(double boxWidth) {
/* 162 */     this.boxWidth = boxWidth;
/* 163 */     this.boxPaint = Color.green;
/* 164 */     this.fillBox = true;
/* 165 */     setToolTipGenerator((XYToolTipGenerator)new BoxAndWhiskerXYToolTipGenerator());
/*     */   }
/*     */   
/*     */   public double getBoxWidth() {
/* 174 */     return this.boxWidth;
/*     */   }
/*     */   
/*     */   public void setBoxWidth(double width) {
/* 186 */     if (width != this.boxWidth) {
/* 187 */       this.boxWidth = width;
/* 188 */       notifyListeners(new RendererChangeEvent(this));
/*     */     } 
/*     */   }
/*     */   
/*     */   public Paint getBoxPaint() {
/* 198 */     return this.boxPaint;
/*     */   }
/*     */   
/*     */   public void setBoxPaint(Paint paint) {
/* 208 */     this.boxPaint = paint;
/* 209 */     notifyListeners(new RendererChangeEvent(this));
/*     */   }
/*     */   
/*     */   public boolean getFillBox() {
/* 218 */     return this.fillBox;
/*     */   }
/*     */   
/*     */   public void setFillBox(boolean flag) {
/* 228 */     this.fillBox = flag;
/* 229 */     notifyListeners(new RendererChangeEvent(this));
/*     */   }
/*     */   
/*     */   public Paint getArtifactPaint() {
/* 239 */     return this.artifactPaint;
/*     */   }
/*     */   
/*     */   public void setArtifactPaint(Paint artifactPaint) {
/* 249 */     this.artifactPaint = artifactPaint;
/*     */   }
/*     */   
/*     */   public void drawItem(Graphics2D g2, XYItemRendererState state, Rectangle2D dataArea, PlotRenderingInfo info, XYPlot plot, ValueAxis domainAxis, ValueAxis rangeAxis, XYDataset dataset, int series, int item, CrosshairState crosshairState, int pass) {
/* 283 */     PlotOrientation orientation = plot.getOrientation();
/* 285 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 286 */       drawHorizontalItem(g2, dataArea, info, plot, domainAxis, rangeAxis, dataset, series, item, crosshairState, pass);
/* 291 */     } else if (orientation == PlotOrientation.VERTICAL) {
/* 292 */       drawVerticalItem(g2, dataArea, info, plot, domainAxis, rangeAxis, dataset, series, item, crosshairState, pass);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void drawHorizontalItem(Graphics2D g2, Rectangle2D dataArea, PlotRenderingInfo info, XYPlot plot, ValueAxis domainAxis, ValueAxis rangeAxis, XYDataset dataset, int series, int item, CrosshairState crosshairState, int pass) {
/* 330 */     EntityCollection entities = null;
/* 331 */     if (info != null)
/* 332 */       entities = info.getOwner().getEntityCollection(); 
/* 335 */     BoxAndWhiskerXYDataset boxAndWhiskerData = (BoxAndWhiskerXYDataset)dataset;
/* 338 */     Number x = boxAndWhiskerData.getX(series, item);
/* 339 */     Number yMax = boxAndWhiskerData.getMaxRegularValue(series, item);
/* 340 */     Number yMin = boxAndWhiskerData.getMinRegularValue(series, item);
/* 341 */     Number yQ1Median = boxAndWhiskerData.getQ1Value(series, item);
/* 342 */     Number yQ3Median = boxAndWhiskerData.getQ3Value(series, item);
/* 344 */     double xx = domainAxis.valueToJava2D(x.doubleValue(), dataArea, plot.getDomainAxisEdge());
/* 348 */     RectangleEdge location = plot.getRangeAxisEdge();
/* 349 */     double yyMax = rangeAxis.valueToJava2D(yMax.doubleValue(), dataArea, location);
/* 352 */     double yyMin = rangeAxis.valueToJava2D(yMin.doubleValue(), dataArea, location);
/* 356 */     double yyQ1Median = rangeAxis.valueToJava2D(yQ1Median.doubleValue(), dataArea, location);
/* 359 */     double yyQ3Median = rangeAxis.valueToJava2D(yQ3Median.doubleValue(), dataArea, location);
/* 363 */     double exactCandleWidth = getBoxWidth();
/* 364 */     double thisCandleWidth = exactCandleWidth;
/* 365 */     if (exactCandleWidth <= 0.0D) {
/* 366 */       int itemCount = boxAndWhiskerData.getItemCount(series);
/* 367 */       exactCandleWidth = dataArea.getHeight() / itemCount * 4.5D / 7.0D;
/* 368 */       if (exactCandleWidth < 1.0D)
/* 369 */         exactCandleWidth = 1.0D; 
/* 371 */       thisCandleWidth = exactCandleWidth;
/* 372 */       if (thisCandleWidth < 3.0D)
/* 373 */         thisCandleWidth = 3.0D; 
/*     */     } 
/* 377 */     Stroke s = getItemStroke(series, item);
/* 379 */     g2.setStroke(s);
/* 382 */     if (yyMax > yyQ1Median && yyMax > yyQ3Median)
/* 383 */       g2.draw(new Line2D.Double(yyMax, xx, Math.max(yyQ1Median, yyQ3Median), xx)); 
/* 390 */     if (yyMin < yyQ1Median && yyMin < yyQ3Median)
/* 391 */       g2.draw(new Line2D.Double(yyMin, xx, Math.min(yyQ1Median, yyQ3Median), xx)); 
/* 399 */     Shape box = null;
/* 400 */     if (yyQ1Median < yyQ3Median) {
/* 401 */       box = new Rectangle2D.Double(yyQ1Median, xx - thisCandleWidth / 2.0D, yyQ3Median - yyQ1Median, thisCandleWidth);
/*     */     } else {
/* 407 */       box = new Rectangle2D.Double(yyQ3Median, xx - thisCandleWidth / 2.0D, yyQ1Median - yyQ3Median, thisCandleWidth);
/* 411 */       if (getBoxPaint() != null)
/* 412 */         g2.setPaint(getBoxPaint()); 
/* 414 */       if (this.fillBox)
/* 415 */         g2.fill(box); 
/* 417 */       g2.draw(box);
/*     */     } 
/* 421 */     if (entities != null) {
/* 422 */       String tip = null;
/* 423 */       XYToolTipGenerator generator = getToolTipGenerator(series, item);
/* 424 */       if (generator != null)
/* 425 */         tip = generator.generateToolTip(dataset, series, item); 
/* 427 */       String url = null;
/* 428 */       if (getURLGenerator() != null)
/* 429 */         url = getURLGenerator().generateURL(dataset, series, item); 
/* 431 */       XYItemEntity entity = new XYItemEntity(box, dataset, series, item, tip, url);
/* 433 */       entities.add((ChartEntity)entity);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void drawVerticalItem(Graphics2D g2, Rectangle2D dataArea, PlotRenderingInfo info, XYPlot plot, ValueAxis domainAxis, ValueAxis rangeAxis, XYDataset dataset, int series, int item, CrosshairState crosshairState, int pass) {
/* 468 */     EntityCollection entities = null;
/* 469 */     if (info != null)
/* 470 */       entities = info.getOwner().getEntityCollection(); 
/* 473 */     BoxAndWhiskerXYDataset boxAndWhiskerData = (BoxAndWhiskerXYDataset)dataset;
/* 476 */     Number x = boxAndWhiskerData.getX(series, item);
/* 477 */     Number yMax = boxAndWhiskerData.getMaxRegularValue(series, item);
/* 478 */     Number yMin = boxAndWhiskerData.getMinRegularValue(series, item);
/* 479 */     Number yMedian = boxAndWhiskerData.getMedianValue(series, item);
/* 480 */     Number yAverage = boxAndWhiskerData.getMeanValue(series, item);
/* 481 */     Number yQ1Median = boxAndWhiskerData.getQ1Value(series, item);
/* 482 */     Number yQ3Median = boxAndWhiskerData.getQ3Value(series, item);
/* 483 */     List yOutliers = boxAndWhiskerData.getOutliers(series, item);
/* 485 */     double xx = domainAxis.valueToJava2D(x.doubleValue(), dataArea, plot.getDomainAxisEdge());
/* 488 */     RectangleEdge location = plot.getRangeAxisEdge();
/* 489 */     double yyMax = rangeAxis.valueToJava2D(yMax.doubleValue(), dataArea, location);
/* 491 */     double yyMin = rangeAxis.valueToJava2D(yMin.doubleValue(), dataArea, location);
/* 493 */     double yyMedian = rangeAxis.valueToJava2D(yMedian.doubleValue(), dataArea, location);
/* 495 */     double yyAverage = 0.0D;
/* 496 */     if (yAverage != null)
/* 497 */       yyAverage = rangeAxis.valueToJava2D(yAverage.doubleValue(), dataArea, location); 
/* 500 */     double yyQ1Median = rangeAxis.valueToJava2D(yQ1Median.doubleValue(), dataArea, location);
/* 502 */     double yyQ3Median = rangeAxis.valueToJava2D(yQ3Median.doubleValue(), dataArea, location);
/* 507 */     double exactBoxWidth = getBoxWidth();
/* 508 */     double width = exactBoxWidth;
/* 509 */     double dataAreaX = dataArea.getMaxX() - dataArea.getMinX();
/* 510 */     double maxBoxPercent = 0.1D;
/* 511 */     double maxBoxWidth = dataAreaX * maxBoxPercent;
/* 512 */     if (exactBoxWidth <= 0.0D) {
/* 513 */       int itemCount = boxAndWhiskerData.getItemCount(series);
/* 514 */       exactBoxWidth = dataAreaX / itemCount * 4.5D / 7.0D;
/* 515 */       if (exactBoxWidth < 3.0D) {
/* 516 */         width = 3.0D;
/* 518 */       } else if (exactBoxWidth > maxBoxWidth) {
/* 519 */         width = maxBoxWidth;
/*     */       } else {
/* 522 */         width = exactBoxWidth;
/*     */       } 
/*     */     } 
/* 526 */     Paint p = getBoxPaint();
/* 527 */     if (p != null)
/* 528 */       g2.setPaint(p); 
/* 530 */     Stroke s = getItemStroke(series, item);
/* 532 */     g2.setStroke(s);
/* 535 */     g2.draw(new Line2D.Double(xx, yyMax, xx, yyQ3Median));
/* 536 */     g2.draw(new Line2D.Double(xx - width / 2.0D, yyMax, xx + width / 2.0D, yyMax));
/* 540 */     g2.draw(new Line2D.Double(xx, yyMin, xx, yyQ1Median));
/* 541 */     g2.draw(new Line2D.Double(xx - width / 2.0D, yyMin, xx + width / 2.0D, yyMin));
/* 545 */     Shape box = null;
/* 546 */     if (yyQ1Median > yyQ3Median) {
/* 547 */       box = new Rectangle2D.Double(xx - width / 2.0D, yyQ3Median, width, yyQ1Median - yyQ3Median);
/*     */     } else {
/* 552 */       box = new Rectangle2D.Double(xx - width / 2.0D, yyQ1Median, width, yyQ3Median - yyQ1Median);
/*     */     } 
/* 556 */     if (this.fillBox)
/* 557 */       g2.fill(box); 
/* 559 */     g2.draw(box);
/* 562 */     g2.setPaint(getArtifactPaint());
/* 563 */     g2.draw(new Line2D.Double(xx - width / 2.0D, yyMedian, xx + width / 2.0D, yyMedian));
/* 566 */     double aRadius = 0.0D;
/* 567 */     double oRadius = width / 3.0D;
/* 570 */     if (yAverage != null) {
/* 571 */       aRadius = width / 4.0D;
/* 572 */       Ellipse2D.Double avgEllipse = new Ellipse2D.Double(xx - aRadius, yyAverage - aRadius, aRadius * 2.0D, aRadius * 2.0D);
/* 575 */       g2.fill(avgEllipse);
/* 576 */       g2.draw(avgEllipse);
/*     */     } 
/* 579 */     List outliers = new ArrayList();
/* 580 */     OutlierListCollection outlierListCollection = new OutlierListCollection();
/* 588 */     for (int i = 0; i < yOutliers.size(); i++) {
/* 589 */       double outlier = ((Number)yOutliers.get(i)).doubleValue();
/* 590 */       if (outlier > boxAndWhiskerData.getMaxOutlier(series, item).doubleValue()) {
/* 592 */         outlierListCollection.setHighFarOut(true);
/* 594 */       } else if (outlier < boxAndWhiskerData.getMinOutlier(series, item).doubleValue()) {
/* 596 */         outlierListCollection.setLowFarOut(true);
/* 598 */       } else if (outlier > boxAndWhiskerData.getMaxRegularValue(series, item).doubleValue()) {
/* 600 */         double yyOutlier = rangeAxis.valueToJava2D(outlier, dataArea, location);
/* 602 */         outliers.add(new Outlier(xx, yyOutlier, oRadius));
/* 604 */       } else if (outlier < boxAndWhiskerData.getMinRegularValue(series, item).doubleValue()) {
/* 606 */         double yyOutlier = rangeAxis.valueToJava2D(outlier, dataArea, location);
/* 608 */         outliers.add(new Outlier(xx, yyOutlier, oRadius));
/*     */       } 
/* 610 */       Collections.sort(outliers);
/*     */     } 
/* 615 */     for (Iterator iterator = outliers.iterator(); iterator.hasNext(); ) {
/* 616 */       Outlier outlier = iterator.next();
/* 617 */       outlierListCollection.add(outlier);
/*     */     } 
/* 621 */     double maxAxisValue = rangeAxis.valueToJava2D(rangeAxis.getUpperBound(), dataArea, location) + aRadius;
/* 624 */     double minAxisValue = rangeAxis.valueToJava2D(rangeAxis.getLowerBound(), dataArea, location) - aRadius;
/* 629 */     Iterator iterator1 = outlierListCollection.iterator();
/* 630 */     while (iterator1.hasNext()) {
/* 631 */       OutlierList list = iterator1.next();
/* 632 */       Outlier outlier = list.getAveragedOutlier();
/* 633 */       Point2D point = outlier.getPoint();
/* 635 */       if (list.isMultiple()) {
/* 636 */         drawMultipleEllipse(point, width, oRadius, g2);
/*     */         continue;
/*     */       } 
/* 639 */       drawEllipse(point, oRadius, g2);
/*     */     } 
/* 644 */     if (outlierListCollection.isHighFarOut())
/* 645 */       drawHighFarOut(aRadius, g2, xx, maxAxisValue); 
/* 648 */     if (outlierListCollection.isLowFarOut())
/* 649 */       drawLowFarOut(aRadius, g2, xx, minAxisValue); 
/* 653 */     if (entities != null) {
/* 654 */       String tip = null;
/* 655 */       XYToolTipGenerator generator = getToolTipGenerator(series, item);
/* 656 */       if (generator != null)
/* 657 */         tip = generator.generateToolTip(dataset, series, item); 
/* 659 */       String url = null;
/* 660 */       if (getURLGenerator() != null)
/* 661 */         url = getURLGenerator().generateURL(dataset, series, item); 
/* 663 */       XYItemEntity entity = new XYItemEntity(box, dataset, series, item, tip, url);
/* 665 */       entities.add((ChartEntity)entity);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void drawEllipse(Point2D point, double oRadius, Graphics2D g2) {
/* 678 */     Ellipse2D.Double dot = new Ellipse2D.Double(point.getX() + oRadius / 2.0D, point.getY(), oRadius, oRadius);
/* 681 */     g2.draw(dot);
/*     */   }
/*     */   
/*     */   protected void drawMultipleEllipse(Point2D point, double boxWidth, double oRadius, Graphics2D g2) {
/* 695 */     Ellipse2D.Double dot1 = new Ellipse2D.Double(point.getX() - boxWidth / 2.0D + oRadius, point.getY(), oRadius, oRadius);
/* 699 */     Ellipse2D.Double dot2 = new Ellipse2D.Double(point.getX() + boxWidth / 2.0D, point.getY(), oRadius, oRadius);
/* 702 */     g2.draw(dot1);
/* 703 */     g2.draw(dot2);
/*     */   }
/*     */   
/*     */   protected void drawHighFarOut(double aRadius, Graphics2D g2, double xx, double m) {
/* 717 */     double side = aRadius * 2.0D;
/* 718 */     g2.draw(new Line2D.Double(xx - side, m + side, xx + side, m + side));
/* 719 */     g2.draw(new Line2D.Double(xx - side, m + side, xx, m));
/* 720 */     g2.draw(new Line2D.Double(xx + side, m + side, xx, m));
/*     */   }
/*     */   
/*     */   protected void drawLowFarOut(double aRadius, Graphics2D g2, double xx, double m) {
/* 733 */     double side = aRadius * 2.0D;
/* 734 */     g2.draw(new Line2D.Double(xx - side, m - side, xx + side, m - side));
/* 735 */     g2.draw(new Line2D.Double(xx - side, m - side, xx, m));
/* 736 */     g2.draw(new Line2D.Double(xx + side, m - side, xx, m));
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 747 */     if (obj == this)
/* 748 */       return true; 
/* 750 */     if (!(obj instanceof XYBoxAndWhiskerRenderer))
/* 751 */       return false; 
/* 753 */     if (!super.equals(obj))
/* 754 */       return false; 
/* 756 */     XYBoxAndWhiskerRenderer that = (XYBoxAndWhiskerRenderer)obj;
/* 757 */     if (this.boxWidth != that.getBoxWidth())
/* 758 */       return false; 
/* 760 */     if (!PaintUtilities.equal(this.boxPaint, that.boxPaint))
/* 761 */       return false; 
/* 763 */     if (!PaintUtilities.equal(this.artifactPaint, that.artifactPaint))
/* 764 */       return false; 
/* 766 */     if (this.fillBox != that.fillBox)
/* 767 */       return false; 
/* 769 */     return true;
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream stream) throws IOException {
/* 782 */     stream.defaultWriteObject();
/* 783 */     SerialUtilities.writePaint(this.boxPaint, stream);
/* 784 */     SerialUtilities.writePaint(this.artifactPaint, stream);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
/* 799 */     stream.defaultReadObject();
/* 800 */     this.boxPaint = SerialUtilities.readPaint(stream);
/* 801 */     this.artifactPaint = SerialUtilities.readPaint(stream);
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 813 */     return super.clone();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\renderer\xy\XYBoxAndWhiskerRenderer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */