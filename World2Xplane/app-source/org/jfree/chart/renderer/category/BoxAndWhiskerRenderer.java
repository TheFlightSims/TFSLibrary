/*     */ package org.jfree.chart.renderer.category;
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
/*     */ import org.jfree.chart.LegendItem;
/*     */ import org.jfree.chart.axis.CategoryAxis;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.entity.CategoryItemEntity;
/*     */ import org.jfree.chart.entity.ChartEntity;
/*     */ import org.jfree.chart.entity.EntityCollection;
/*     */ import org.jfree.chart.event.RendererChangeEvent;
/*     */ import org.jfree.chart.labels.CategoryToolTipGenerator;
/*     */ import org.jfree.chart.plot.CategoryPlot;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.chart.plot.PlotRenderingInfo;
/*     */ import org.jfree.chart.renderer.Outlier;
/*     */ import org.jfree.chart.renderer.OutlierList;
/*     */ import org.jfree.chart.renderer.OutlierListCollection;
/*     */ import org.jfree.data.category.CategoryDataset;
/*     */ import org.jfree.data.statistics.BoxAndWhiskerCategoryDataset;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.util.PaintUtilities;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ public class BoxAndWhiskerRenderer extends AbstractCategoryItemRenderer implements Cloneable, PublicCloneable, Serializable {
/*     */   private static final long serialVersionUID = 632027470694481177L;
/*     */   
/* 126 */   private transient Paint artifactPaint = Color.black;
/*     */   
/*     */   private boolean fillBox = true;
/*     */   
/* 128 */   private double itemMargin = 0.2D;
/*     */   
/*     */   public Paint getArtifactPaint() {
/* 137 */     return this.artifactPaint;
/*     */   }
/*     */   
/*     */   public void setArtifactPaint(Paint paint) {
/* 146 */     this.artifactPaint = paint;
/*     */   }
/*     */   
/*     */   public boolean getFillBox() {
/* 155 */     return this.fillBox;
/*     */   }
/*     */   
/*     */   public void setFillBox(boolean flag) {
/* 165 */     this.fillBox = flag;
/* 166 */     notifyListeners(new RendererChangeEvent(this));
/*     */   }
/*     */   
/*     */   public double getItemMargin() {
/* 176 */     return this.itemMargin;
/*     */   }
/*     */   
/*     */   public void setItemMargin(double margin) {
/* 185 */     this.itemMargin = margin;
/*     */   }
/*     */   
/*     */   public LegendItem getLegendItem(int datasetIndex, int series) {
/* 198 */     CategoryPlot cp = getPlot();
/* 199 */     if (cp == null)
/* 200 */       return null; 
/* 204 */     CategoryDataset dataset = cp.getDataset(datasetIndex);
/* 205 */     String label = getLegendItemLabelGenerator().generateLabel(dataset, series);
/* 208 */     String description = label;
/* 209 */     String toolTipText = null;
/* 210 */     if (getLegendItemToolTipGenerator() != null)
/* 211 */       toolTipText = getLegendItemToolTipGenerator().generateLabel(dataset, series); 
/* 215 */     String urlText = null;
/* 216 */     if (getLegendItemURLGenerator() != null)
/* 217 */       urlText = getLegendItemURLGenerator().generateLabel(dataset, series); 
/* 221 */     Shape shape = new Rectangle2D.Double(-4.0D, -4.0D, 8.0D, 8.0D);
/* 222 */     Paint paint = getSeriesPaint(series);
/* 223 */     Paint outlinePaint = getSeriesOutlinePaint(series);
/* 224 */     Stroke outlineStroke = getSeriesOutlineStroke(series);
/* 226 */     return new LegendItem(label, description, toolTipText, urlText, shape, paint, outlineStroke, outlinePaint);
/*     */   }
/*     */   
/*     */   public CategoryItemRendererState initialise(Graphics2D g2, Rectangle2D dataArea, CategoryPlot plot, int rendererIndex, PlotRenderingInfo info) {
/* 249 */     CategoryItemRendererState state = super.initialise(g2, dataArea, plot, rendererIndex, info);
/* 254 */     CategoryAxis domainAxis = getDomainAxis(plot, rendererIndex);
/* 255 */     CategoryDataset dataset = plot.getDataset(rendererIndex);
/* 256 */     if (dataset != null) {
/* 257 */       int columns = dataset.getColumnCount();
/* 258 */       int rows = dataset.getRowCount();
/* 259 */       double space = 0.0D;
/* 260 */       PlotOrientation orientation = plot.getOrientation();
/* 261 */       if (orientation == PlotOrientation.HORIZONTAL) {
/* 262 */         space = dataArea.getHeight();
/* 264 */       } else if (orientation == PlotOrientation.VERTICAL) {
/* 265 */         space = dataArea.getWidth();
/*     */       } 
/* 267 */       double categoryMargin = 0.0D;
/* 268 */       double currentItemMargin = 0.0D;
/* 269 */       if (columns > 1)
/* 270 */         categoryMargin = domainAxis.getCategoryMargin(); 
/* 272 */       if (rows > 1)
/* 273 */         currentItemMargin = getItemMargin(); 
/* 275 */       double used = space * (1.0D - domainAxis.getLowerMargin() - domainAxis.getUpperMargin() - categoryMargin - currentItemMargin);
/* 278 */       if (rows * columns > 0) {
/* 279 */         state.setBarWidth(used / (dataset.getColumnCount() * dataset.getRowCount()));
/*     */       } else {
/* 284 */         state.setBarWidth(used);
/*     */       } 
/*     */     } 
/* 288 */     return state;
/*     */   }
/*     */   
/*     */   public void drawItem(Graphics2D g2, CategoryItemRendererState state, Rectangle2D dataArea, CategoryPlot plot, CategoryAxis domainAxis, ValueAxis rangeAxis, CategoryDataset dataset, int row, int column, int pass) {
/* 317 */     if (!(dataset instanceof BoxAndWhiskerCategoryDataset))
/* 318 */       throw new IllegalArgumentException("BoxAndWhiskerRenderer.drawItem() : the data should be of type BoxAndWhiskerCategoryDataset only."); 
/* 324 */     PlotOrientation orientation = plot.getOrientation();
/* 326 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 327 */       drawHorizontalItem(g2, state, dataArea, plot, domainAxis, rangeAxis, dataset, row, column);
/* 332 */     } else if (orientation == PlotOrientation.VERTICAL) {
/* 333 */       drawVerticalItem(g2, state, dataArea, plot, domainAxis, rangeAxis, dataset, row, column);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void drawHorizontalItem(Graphics2D g2, CategoryItemRendererState state, Rectangle2D dataArea, CategoryPlot plot, CategoryAxis domainAxis, ValueAxis rangeAxis, CategoryDataset dataset, int row, int column) {
/* 366 */     BoxAndWhiskerCategoryDataset bawDataset = (BoxAndWhiskerCategoryDataset)dataset;
/* 369 */     double categoryEnd = domainAxis.getCategoryEnd(column, getColumnCount(), dataArea, plot.getDomainAxisEdge());
/* 372 */     double categoryStart = domainAxis.getCategoryStart(column, getColumnCount(), dataArea, plot.getDomainAxisEdge());
/* 375 */     double categoryWidth = Math.abs(categoryEnd - categoryStart);
/* 377 */     double yy = categoryStart;
/* 378 */     int seriesCount = getRowCount();
/* 379 */     int categoryCount = getColumnCount();
/* 381 */     if (seriesCount > 1) {
/* 382 */       double seriesGap = dataArea.getWidth() * getItemMargin() / (categoryCount * (seriesCount - 1));
/* 384 */       double usedWidth = state.getBarWidth() * seriesCount + seriesGap * (seriesCount - 1);
/* 388 */       double offset = (categoryWidth - usedWidth) / 2.0D;
/* 389 */       yy = yy + offset + row * (state.getBarWidth() + seriesGap);
/*     */     } else {
/* 394 */       double offset = (categoryWidth - state.getBarWidth()) / 2.0D;
/* 395 */       yy += offset;
/*     */     } 
/* 398 */     Paint p = getItemPaint(row, column);
/* 399 */     if (p != null)
/* 400 */       g2.setPaint(p); 
/* 402 */     Stroke s = getItemStroke(row, column);
/* 403 */     g2.setStroke(s);
/* 405 */     RectangleEdge location = plot.getRangeAxisEdge();
/* 407 */     Number xQ1 = bawDataset.getQ1Value(row, column);
/* 408 */     Number xQ3 = bawDataset.getQ3Value(row, column);
/* 409 */     Number xMax = bawDataset.getMaxRegularValue(row, column);
/* 410 */     Number xMin = bawDataset.getMinRegularValue(row, column);
/* 412 */     Shape box = null;
/* 413 */     if (xQ1 != null && xQ3 != null && xMax != null && xMin != null) {
/* 415 */       double xxQ1 = rangeAxis.valueToJava2D(xQ1.doubleValue(), dataArea, location);
/* 418 */       double xxQ3 = rangeAxis.valueToJava2D(xQ3.doubleValue(), dataArea, location);
/* 421 */       double xxMax = rangeAxis.valueToJava2D(xMax.doubleValue(), dataArea, location);
/* 424 */       double xxMin = rangeAxis.valueToJava2D(xMin.doubleValue(), dataArea, location);
/* 427 */       double yymid = yy + state.getBarWidth() / 2.0D;
/* 430 */       g2.draw(new Line2D.Double(xxMax, yymid, xxQ3, yymid));
/* 431 */       g2.draw(new Line2D.Double(xxMax, yy, xxMax, yy + state.getBarWidth()));
/* 436 */       g2.draw(new Line2D.Double(xxMin, yymid, xxQ1, yymid));
/* 437 */       g2.draw(new Line2D.Double(xxMin, yy, xxMin, yy + state.getBarWidth()));
/* 442 */       box = new Rectangle2D.Double(Math.min(xxQ1, xxQ3), yy, Math.abs(xxQ1 - xxQ3), state.getBarWidth());
/* 446 */       if (this.fillBox)
/* 447 */         g2.fill(box); 
/* 449 */       g2.draw(box);
/*     */     } 
/* 453 */     g2.setPaint(this.artifactPaint);
/* 454 */     double aRadius = 0.0D;
/* 457 */     Number xMean = bawDataset.getMeanValue(row, column);
/* 458 */     if (xMean != null) {
/* 459 */       double xxMean = rangeAxis.valueToJava2D(xMean.doubleValue(), dataArea, location);
/* 462 */       aRadius = state.getBarWidth() / 4.0D;
/* 463 */       Ellipse2D.Double avgEllipse = new Ellipse2D.Double(xxMean - aRadius, yy + aRadius, aRadius * 2.0D, aRadius * 2.0D);
/* 466 */       g2.fill(avgEllipse);
/* 467 */       g2.draw(avgEllipse);
/*     */     } 
/* 471 */     Number xMedian = bawDataset.getMedianValue(row, column);
/* 472 */     if (xMedian != null) {
/* 473 */       double xxMedian = rangeAxis.valueToJava2D(xMedian.doubleValue(), dataArea, location);
/* 476 */       g2.draw(new Line2D.Double(xxMedian, yy, xxMedian, yy + state.getBarWidth()));
/*     */     } 
/* 484 */     if (state.getInfo() != null) {
/* 485 */       EntityCollection entities = state.getEntityCollection();
/* 486 */       if (entities != null) {
/* 487 */         String tip = null;
/* 488 */         CategoryToolTipGenerator tipster = getToolTipGenerator(row, column);
/* 490 */         if (tipster != null)
/* 491 */           tip = tipster.generateToolTip(dataset, row, column); 
/* 493 */         String url = null;
/* 494 */         if (getItemURLGenerator(row, column) != null)
/* 495 */           url = getItemURLGenerator(row, column).generateURL(dataset, row, column); 
/* 499 */         CategoryItemEntity entity = new CategoryItemEntity(box, tip, url, dataset, row, dataset.getColumnKey(column), column);
/* 503 */         entities.add((ChartEntity)entity);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void drawVerticalItem(Graphics2D g2, CategoryItemRendererState state, Rectangle2D dataArea, CategoryPlot plot, CategoryAxis domainAxis, ValueAxis rangeAxis, CategoryDataset dataset, int row, int column) {
/* 534 */     BoxAndWhiskerCategoryDataset bawDataset = (BoxAndWhiskerCategoryDataset)dataset;
/* 537 */     double categoryEnd = domainAxis.getCategoryEnd(column, getColumnCount(), dataArea, plot.getDomainAxisEdge());
/* 540 */     double categoryStart = domainAxis.getCategoryStart(column, getColumnCount(), dataArea, plot.getDomainAxisEdge());
/* 543 */     double categoryWidth = categoryEnd - categoryStart;
/* 545 */     double xx = categoryStart;
/* 546 */     int seriesCount = getRowCount();
/* 547 */     int categoryCount = getColumnCount();
/* 549 */     if (seriesCount > 1) {
/* 550 */       double seriesGap = dataArea.getWidth() * getItemMargin() / (categoryCount * (seriesCount - 1));
/* 552 */       double usedWidth = state.getBarWidth() * seriesCount + seriesGap * (seriesCount - 1);
/* 556 */       double offset = (categoryWidth - usedWidth) / 2.0D;
/* 557 */       xx = xx + offset + row * (state.getBarWidth() + seriesGap);
/*     */     } else {
/* 562 */       double offset = (categoryWidth - state.getBarWidth()) / 2.0D;
/* 563 */       xx += offset;
/*     */     } 
/* 566 */     double yyAverage = 0.0D;
/* 569 */     Paint p = getItemPaint(row, column);
/* 570 */     if (p != null)
/* 571 */       g2.setPaint(p); 
/* 573 */     Stroke s = getItemStroke(row, column);
/* 574 */     g2.setStroke(s);
/* 576 */     double aRadius = 0.0D;
/* 578 */     RectangleEdge location = plot.getRangeAxisEdge();
/* 580 */     Number yQ1 = bawDataset.getQ1Value(row, column);
/* 581 */     Number yQ3 = bawDataset.getQ3Value(row, column);
/* 582 */     Number yMax = bawDataset.getMaxRegularValue(row, column);
/* 583 */     Number yMin = bawDataset.getMinRegularValue(row, column);
/* 584 */     Shape box = null;
/* 585 */     if (yQ1 != null && yQ3 != null && yMax != null && yMin != null) {
/* 587 */       double yyQ1 = rangeAxis.valueToJava2D(yQ1.doubleValue(), dataArea, location);
/* 590 */       double yyQ3 = rangeAxis.valueToJava2D(yQ3.doubleValue(), dataArea, location);
/* 593 */       double yyMax = rangeAxis.valueToJava2D(yMax.doubleValue(), dataArea, location);
/* 596 */       double yyMin = rangeAxis.valueToJava2D(yMin.doubleValue(), dataArea, location);
/* 599 */       double xxmid = xx + state.getBarWidth() / 2.0D;
/* 602 */       g2.draw(new Line2D.Double(xxmid, yyMax, xxmid, yyQ3));
/* 603 */       g2.draw(new Line2D.Double(xx, yyMax, xx + state.getBarWidth(), yyMax));
/* 608 */       g2.draw(new Line2D.Double(xxmid, yyMin, xxmid, yyQ1));
/* 609 */       g2.draw(new Line2D.Double(xx, yyMin, xx + state.getBarWidth(), yyMin));
/* 614 */       box = new Rectangle2D.Double(xx, Math.min(yyQ1, yyQ3), state.getBarWidth(), Math.abs(yyQ1 - yyQ3));
/* 618 */       if (this.fillBox)
/* 619 */         g2.fill(box); 
/* 621 */       g2.draw(box);
/*     */     } 
/* 625 */     g2.setPaint(this.artifactPaint);
/* 628 */     Number yMean = bawDataset.getMeanValue(row, column);
/* 629 */     if (yMean != null) {
/* 630 */       yyAverage = rangeAxis.valueToJava2D(yMean.doubleValue(), dataArea, location);
/* 633 */       aRadius = state.getBarWidth() / 4.0D;
/* 634 */       Ellipse2D.Double avgEllipse = new Ellipse2D.Double(xx + aRadius, yyAverage - aRadius, aRadius * 2.0D, aRadius * 2.0D);
/* 637 */       g2.fill(avgEllipse);
/* 638 */       g2.draw(avgEllipse);
/*     */     } 
/* 642 */     Number yMedian = bawDataset.getMedianValue(row, column);
/* 643 */     if (yMedian != null) {
/* 644 */       double yyMedian = rangeAxis.valueToJava2D(yMedian.doubleValue(), dataArea, location);
/* 647 */       g2.draw(new Line2D.Double(xx, yyMedian, xx + state.getBarWidth(), yyMedian));
/*     */     } 
/* 655 */     double maxAxisValue = rangeAxis.valueToJava2D(rangeAxis.getUpperBound(), dataArea, location) + aRadius;
/* 658 */     double minAxisValue = rangeAxis.valueToJava2D(rangeAxis.getLowerBound(), dataArea, location) - aRadius;
/* 662 */     g2.setPaint(p);
/* 665 */     double oRadius = state.getBarWidth() / 3.0D;
/* 666 */     List outliers = new ArrayList();
/* 667 */     OutlierListCollection outlierListCollection = new OutlierListCollection();
/* 673 */     List yOutliers = bawDataset.getOutliers(row, column);
/* 674 */     if (yOutliers != null) {
/* 675 */       for (int i = 0; i < yOutliers.size(); i++) {
/* 676 */         double outlier = ((Number)yOutliers.get(i)).doubleValue();
/* 677 */         Number minOutlier = bawDataset.getMinOutlier(row, column);
/* 678 */         Number maxOutlier = bawDataset.getMaxOutlier(row, column);
/* 679 */         Number minRegular = bawDataset.getMinRegularValue(row, column);
/* 680 */         Number maxRegular = bawDataset.getMaxRegularValue(row, column);
/* 681 */         if (outlier > maxOutlier.doubleValue()) {
/* 682 */           outlierListCollection.setHighFarOut(true);
/* 684 */         } else if (outlier < minOutlier.doubleValue()) {
/* 685 */           outlierListCollection.setLowFarOut(true);
/* 687 */         } else if (outlier > maxRegular.doubleValue()) {
/* 688 */           double yyOutlier = rangeAxis.valueToJava2D(outlier, dataArea, location);
/* 691 */           outliers.add(new Outlier(xx + state.getBarWidth() / 2.0D, yyOutlier, oRadius));
/* 697 */         } else if (outlier < minRegular.doubleValue()) {
/* 698 */           double yyOutlier = rangeAxis.valueToJava2D(outlier, dataArea, location);
/* 701 */           outliers.add(new Outlier(xx + state.getBarWidth() / 2.0D, yyOutlier, oRadius));
/*     */         } 
/* 707 */         Collections.sort(outliers);
/*     */       } 
/* 712 */       for (Iterator iterator1 = outliers.iterator(); iterator1.hasNext(); ) {
/* 713 */         Outlier outlier = iterator1.next();
/* 714 */         outlierListCollection.add(outlier);
/*     */       } 
/* 717 */       Iterator iterator = outlierListCollection.iterator();
/* 718 */       while (iterator.hasNext()) {
/* 719 */         OutlierList list = iterator.next();
/* 720 */         Outlier outlier = list.getAveragedOutlier();
/* 721 */         Point2D point = outlier.getPoint();
/* 723 */         if (list.isMultiple()) {
/* 724 */           drawMultipleEllipse(point, state.getBarWidth(), oRadius, g2);
/*     */           continue;
/*     */         } 
/* 729 */         drawEllipse(point, oRadius, g2);
/*     */       } 
/* 734 */       if (outlierListCollection.isHighFarOut())
/* 735 */         drawHighFarOut(aRadius / 2.0D, g2, xx + state.getBarWidth() / 2.0D, maxAxisValue); 
/* 741 */       if (outlierListCollection.isLowFarOut())
/* 742 */         drawLowFarOut(aRadius / 2.0D, g2, xx + state.getBarWidth() / 2.0D, minAxisValue); 
/*     */     } 
/* 749 */     if (state.getInfo() != null) {
/* 750 */       EntityCollection entities = state.getEntityCollection();
/* 751 */       if (entities != null) {
/* 752 */         String tip = null;
/* 753 */         CategoryToolTipGenerator tipster = getToolTipGenerator(row, column);
/* 755 */         if (tipster != null)
/* 756 */           tip = tipster.generateToolTip(dataset, row, column); 
/* 758 */         String url = null;
/* 759 */         if (getItemURLGenerator(row, column) != null)
/* 760 */           url = getItemURLGenerator(row, column).generateURL(dataset, row, column); 
/* 764 */         CategoryItemEntity entity = new CategoryItemEntity(box, tip, url, dataset, row, dataset.getColumnKey(column), column);
/* 768 */         entities.add((ChartEntity)entity);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void drawEllipse(Point2D point, double oRadius, Graphics2D g2) {
/* 782 */     Ellipse2D dot = new Ellipse2D.Double(point.getX() + oRadius / 2.0D, point.getY(), oRadius, oRadius);
/* 785 */     g2.draw(dot);
/*     */   }
/*     */   
/*     */   private void drawMultipleEllipse(Point2D point, double boxWidth, double oRadius, Graphics2D g2) {
/* 799 */     Ellipse2D dot1 = new Ellipse2D.Double(point.getX() - boxWidth / 2.0D + oRadius, point.getY(), oRadius, oRadius);
/* 803 */     Ellipse2D dot2 = new Ellipse2D.Double(point.getX() + boxWidth / 2.0D, point.getY(), oRadius, oRadius);
/* 806 */     g2.draw(dot1);
/* 807 */     g2.draw(dot2);
/*     */   }
/*     */   
/*     */   private void drawHighFarOut(double aRadius, Graphics2D g2, double xx, double m) {
/* 820 */     double side = aRadius * 2.0D;
/* 821 */     g2.draw(new Line2D.Double(xx - side, m + side, xx + side, m + side));
/* 822 */     g2.draw(new Line2D.Double(xx - side, m + side, xx, m));
/* 823 */     g2.draw(new Line2D.Double(xx + side, m + side, xx, m));
/*     */   }
/*     */   
/*     */   private void drawLowFarOut(double aRadius, Graphics2D g2, double xx, double m) {
/* 836 */     double side = aRadius * 2.0D;
/* 837 */     g2.draw(new Line2D.Double(xx - side, m - side, xx + side, m - side));
/* 838 */     g2.draw(new Line2D.Double(xx - side, m - side, xx, m));
/* 839 */     g2.draw(new Line2D.Double(xx + side, m - side, xx, m));
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 850 */     if (obj == this)
/* 851 */       return true; 
/* 853 */     if (!(obj instanceof BoxAndWhiskerRenderer))
/* 854 */       return false; 
/* 856 */     if (!super.equals(obj))
/* 857 */       return false; 
/* 859 */     BoxAndWhiskerRenderer that = (BoxAndWhiskerRenderer)obj;
/* 860 */     if (!PaintUtilities.equal(this.artifactPaint, that.artifactPaint))
/* 861 */       return false; 
/* 863 */     if (this.fillBox != that.fillBox)
/* 864 */       return false; 
/* 866 */     if (this.itemMargin != that.itemMargin)
/* 867 */       return false; 
/* 869 */     return true;
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream stream) throws IOException {
/* 880 */     stream.defaultWriteObject();
/* 881 */     SerialUtilities.writePaint(this.artifactPaint, stream);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
/* 894 */     stream.defaultReadObject();
/* 895 */     this.artifactPaint = SerialUtilities.readPaint(stream);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\renderer\category\BoxAndWhiskerRenderer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */