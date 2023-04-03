/*     */ package org.jfree.chart.renderer.category;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.GradientPaint;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Shape;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.Line2D;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.LegendItem;
/*     */ import org.jfree.chart.axis.CategoryAxis;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.entity.EntityCollection;
/*     */ import org.jfree.chart.event.RendererChangeEvent;
/*     */ import org.jfree.chart.labels.CategoryItemLabelGenerator;
/*     */ import org.jfree.chart.labels.ItemLabelAnchor;
/*     */ import org.jfree.chart.labels.ItemLabelPosition;
/*     */ import org.jfree.chart.plot.CategoryPlot;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.chart.plot.PlotRenderingInfo;
/*     */ import org.jfree.data.category.CategoryDataset;
/*     */ import org.jfree.text.TextUtilities;
/*     */ import org.jfree.ui.GradientPaintTransformer;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.ui.StandardGradientPaintTransformer;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ public class BarRenderer extends AbstractCategoryItemRenderer implements Cloneable, PublicCloneable, Serializable {
/* 174 */   private double base = 0.0D;
/*     */   
/* 175 */   private double itemMargin = 0.2D;
/*     */   
/*     */   private boolean drawBarOutline = true;
/*     */   
/* 177 */   private double maximumBarWidth = 1.0D;
/*     */   
/* 179 */   private ItemLabelPosition positiveItemLabelPositionFallback = null;
/*     */   
/* 180 */   private ItemLabelPosition negativeItemLabelPositionFallback = null;
/*     */   
/* 181 */   private GradientPaintTransformer gradientPaintTransformer = (GradientPaintTransformer)new StandardGradientPaintTransformer();
/*     */   
/* 182 */   private double minimumBarLength = 0.0D;
/*     */   
/*     */   private static final long serialVersionUID = 6000649414965887481L;
/*     */   
/*     */   public static final double DEFAULT_ITEM_MARGIN = 0.2D;
/*     */   
/*     */   public static final double BAR_OUTLINE_WIDTH_THRESHOLD = 3.0D;
/*     */   
/*     */   private double upperClip;
/*     */   
/*     */   private double lowerClip;
/*     */   
/*     */   public double getBase() {
/* 191 */     return this.base;
/*     */   }
/*     */   
/*     */   public void setBase(double base) {
/* 201 */     this.base = base;
/* 202 */     notifyListeners(new RendererChangeEvent(this));
/*     */   }
/*     */   
/*     */   public double getItemMargin() {
/* 212 */     return this.itemMargin;
/*     */   }
/*     */   
/*     */   public void setItemMargin(double percent) {
/* 224 */     this.itemMargin = percent;
/* 225 */     notifyListeners(new RendererChangeEvent(this));
/*     */   }
/*     */   
/*     */   public boolean isDrawBarOutline() {
/* 234 */     return this.drawBarOutline;
/*     */   }
/*     */   
/*     */   public void setDrawBarOutline(boolean draw) {
/* 244 */     this.drawBarOutline = draw;
/* 245 */     notifyListeners(new RendererChangeEvent(this));
/*     */   }
/*     */   
/*     */   public double getMaximumBarWidth() {
/* 255 */     return this.maximumBarWidth;
/*     */   }
/*     */   
/*     */   public void setMaximumBarWidth(double percent) {
/* 266 */     this.maximumBarWidth = percent;
/* 267 */     notifyListeners(new RendererChangeEvent(this));
/*     */   }
/*     */   
/*     */   public double getMinimumBarLength() {
/* 276 */     return this.minimumBarLength;
/*     */   }
/*     */   
/*     */   public void setMinimumBarLength(double min) {
/* 288 */     this.minimumBarLength = min;
/* 289 */     notifyListeners(new RendererChangeEvent(this));
/*     */   }
/*     */   
/*     */   public GradientPaintTransformer getGradientPaintTransformer() {
/* 299 */     return this.gradientPaintTransformer;
/*     */   }
/*     */   
/*     */   public void setGradientPaintTransformer(GradientPaintTransformer transformer) {
/* 310 */     this.gradientPaintTransformer = transformer;
/* 311 */     notifyListeners(new RendererChangeEvent(this));
/*     */   }
/*     */   
/*     */   public ItemLabelPosition getPositiveItemLabelPositionFallback() {
/* 321 */     return this.positiveItemLabelPositionFallback;
/*     */   }
/*     */   
/*     */   public void setPositiveItemLabelPositionFallback(ItemLabelPosition position) {
/* 333 */     this.positiveItemLabelPositionFallback = position;
/* 334 */     notifyListeners(new RendererChangeEvent(this));
/*     */   }
/*     */   
/*     */   public ItemLabelPosition getNegativeItemLabelPositionFallback() {
/* 344 */     return this.negativeItemLabelPositionFallback;
/*     */   }
/*     */   
/*     */   public void setNegativeItemLabelPositionFallback(ItemLabelPosition position) {
/* 356 */     this.negativeItemLabelPositionFallback = position;
/* 357 */     notifyListeners(new RendererChangeEvent(this));
/*     */   }
/*     */   
/*     */   public double getLowerClip() {
/* 368 */     return this.lowerClip;
/*     */   }
/*     */   
/*     */   public double getUpperClip() {
/* 379 */     return this.upperClip;
/*     */   }
/*     */   
/*     */   public CategoryItemRendererState initialise(Graphics2D g2, Rectangle2D dataArea, CategoryPlot plot, int rendererIndex, PlotRenderingInfo info) {
/* 401 */     CategoryItemRendererState state = super.initialise(g2, dataArea, plot, rendererIndex, info);
/* 406 */     ValueAxis rangeAxis = getRangeAxis(plot, rendererIndex);
/* 407 */     this.lowerClip = rangeAxis.getRange().getLowerBound();
/* 408 */     this.upperClip = rangeAxis.getRange().getUpperBound();
/* 411 */     calculateBarWidth(plot, dataArea, rendererIndex, state);
/* 413 */     return state;
/*     */   }
/*     */   
/*     */   protected void calculateBarWidth(CategoryPlot plot, Rectangle2D dataArea, int rendererIndex, CategoryItemRendererState state) {
/* 430 */     CategoryAxis domainAxis = getDomainAxis(plot, rendererIndex);
/* 431 */     CategoryDataset dataset = plot.getDataset(rendererIndex);
/* 432 */     if (dataset != null) {
/* 433 */       int columns = dataset.getColumnCount();
/* 434 */       int rows = dataset.getRowCount();
/* 435 */       double space = 0.0D;
/* 436 */       PlotOrientation orientation = plot.getOrientation();
/* 437 */       if (orientation == PlotOrientation.HORIZONTAL) {
/* 438 */         space = dataArea.getHeight();
/* 440 */       } else if (orientation == PlotOrientation.VERTICAL) {
/* 441 */         space = dataArea.getWidth();
/*     */       } 
/* 443 */       double maxWidth = space * getMaximumBarWidth();
/* 444 */       double categoryMargin = 0.0D;
/* 445 */       double currentItemMargin = 0.0D;
/* 446 */       if (columns > 1)
/* 447 */         categoryMargin = domainAxis.getCategoryMargin(); 
/* 449 */       if (rows > 1)
/* 450 */         currentItemMargin = getItemMargin(); 
/* 452 */       double used = space * (1.0D - domainAxis.getLowerMargin() - domainAxis.getUpperMargin() - categoryMargin - currentItemMargin);
/* 455 */       if (rows * columns > 0) {
/* 456 */         state.setBarWidth(Math.min(used / (rows * columns), maxWidth));
/*     */       } else {
/* 459 */         state.setBarWidth(Math.min(used, maxWidth));
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected double calculateBarW0(CategoryPlot plot, PlotOrientation orientation, Rectangle2D dataArea, CategoryAxis domainAxis, CategoryItemRendererState state, int row, int column) {
/* 487 */     double space = 0.0D;
/* 488 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 489 */       space = dataArea.getHeight();
/*     */     } else {
/* 492 */       space = dataArea.getWidth();
/*     */     } 
/* 494 */     double barW0 = domainAxis.getCategoryStart(column, getColumnCount(), dataArea, plot.getDomainAxisEdge());
/* 497 */     int seriesCount = getRowCount();
/* 498 */     int categoryCount = getColumnCount();
/* 499 */     if (seriesCount > 1) {
/* 500 */       double seriesGap = space * getItemMargin() / (categoryCount * (seriesCount - 1));
/* 502 */       double seriesW = calculateSeriesWidth(space, domainAxis, categoryCount, seriesCount);
/* 505 */       barW0 = barW0 + row * (seriesW + seriesGap) + seriesW / 2.0D - state.getBarWidth() / 2.0D;
/*     */     } else {
/* 509 */       barW0 = domainAxis.getCategoryMiddle(column, getColumnCount(), dataArea, plot.getDomainAxisEdge()) - state.getBarWidth() / 2.0D;
/*     */     } 
/* 513 */     return barW0;
/*     */   }
/*     */   
/*     */   protected double[] calculateBarL0L1(double value) {
/* 526 */     double lclip = getLowerClip();
/* 527 */     double uclip = getUpperClip();
/* 528 */     double bb = this.base;
/* 529 */     if (uclip <= 0.0D) {
/* 530 */       if (value >= uclip)
/* 531 */         return null; 
/* 533 */       bb = uclip;
/* 534 */       if (value <= lclip)
/* 535 */         value = lclip; 
/* 538 */     } else if (lclip <= 0.0D) {
/* 539 */       if (value >= uclip) {
/* 540 */         value = uclip;
/* 543 */       } else if (value <= lclip) {
/* 544 */         value = lclip;
/*     */       } 
/*     */     } else {
/* 549 */       if (value <= lclip)
/* 550 */         return null; 
/* 552 */       bb = lclip;
/* 553 */       if (value >= uclip)
/* 554 */         value = uclip; 
/*     */     } 
/* 557 */     return new double[] { bb, value };
/*     */   }
/*     */   
/*     */   public LegendItem getLegendItem(int datasetIndex, int series) {
/* 570 */     CategoryPlot cp = getPlot();
/* 571 */     if (cp == null)
/* 572 */       return null; 
/* 576 */     CategoryDataset dataset = cp.getDataset(datasetIndex);
/* 577 */     String label = getLegendItemLabelGenerator().generateLabel(dataset, series);
/* 579 */     String description = label;
/* 580 */     String toolTipText = null;
/* 581 */     if (getLegendItemToolTipGenerator() != null)
/* 582 */       toolTipText = getLegendItemToolTipGenerator().generateLabel(dataset, series); 
/* 585 */     String urlText = null;
/* 586 */     if (getLegendItemURLGenerator() != null)
/* 587 */       urlText = getLegendItemURLGenerator().generateLabel(dataset, series); 
/* 590 */     Shape shape = new Rectangle2D.Double(-4.0D, -4.0D, 8.0D, 8.0D);
/* 591 */     Paint paint = getSeriesPaint(series);
/* 592 */     Paint outlinePaint = getSeriesOutlinePaint(series);
/* 593 */     Stroke outlineStroke = getSeriesOutlineStroke(series);
/* 595 */     return new LegendItem(label, description, toolTipText, urlText, true, shape, true, paint, isDrawBarOutline(), outlinePaint, outlineStroke, false, new Line2D.Float(), new BasicStroke(1.0F), Color.BLACK);
/*     */   }
/*     */   
/*     */   public void drawItem(Graphics2D g2, CategoryItemRendererState state, Rectangle2D dataArea, CategoryPlot plot, CategoryAxis domainAxis, ValueAxis rangeAxis, CategoryDataset dataset, int row, int column, int pass) {
/* 628 */     Number dataValue = dataset.getValue(row, column);
/* 629 */     if (dataValue == null)
/*     */       return; 
/* 633 */     double value = dataValue.doubleValue();
/* 635 */     PlotOrientation orientation = plot.getOrientation();
/* 636 */     double barW0 = calculateBarW0(plot, orientation, dataArea, domainAxis, state, row, column);
/* 639 */     double[] barL0L1 = calculateBarL0L1(value);
/* 640 */     if (barL0L1 == null)
/*     */       return; 
/* 644 */     RectangleEdge edge = plot.getRangeAxisEdge();
/* 645 */     double transL0 = rangeAxis.valueToJava2D(barL0L1[0], dataArea, edge);
/* 646 */     double transL1 = rangeAxis.valueToJava2D(barL0L1[1], dataArea, edge);
/* 647 */     double barL0 = Math.min(transL0, transL1);
/* 648 */     double barLength = Math.max(Math.abs(transL1 - transL0), getMinimumBarLength());
/* 653 */     Rectangle2D bar = null;
/* 654 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 655 */       bar = new Rectangle2D.Double(barL0, barW0, barLength, state.getBarWidth());
/*     */     } else {
/* 660 */       bar = new Rectangle2D.Double(barW0, barL0, state.getBarWidth(), barLength);
/*     */     } 
/* 664 */     Paint itemPaint = getItemPaint(row, column);
/* 665 */     GradientPaintTransformer t = getGradientPaintTransformer();
/* 666 */     if (t != null && itemPaint instanceof GradientPaint)
/* 667 */       itemPaint = t.transform((GradientPaint)itemPaint, bar); 
/* 669 */     g2.setPaint(itemPaint);
/* 670 */     g2.fill(bar);
/* 673 */     if (isDrawBarOutline() && state.getBarWidth() > 3.0D) {
/* 675 */       Stroke stroke = getItemOutlineStroke(row, column);
/* 676 */       Paint paint = getItemOutlinePaint(row, column);
/* 677 */       if (stroke != null && paint != null) {
/* 678 */         g2.setStroke(stroke);
/* 679 */         g2.setPaint(paint);
/* 680 */         g2.draw(bar);
/*     */       } 
/*     */     } 
/* 684 */     CategoryItemLabelGenerator generator = getItemLabelGenerator(row, column);
/* 686 */     if (generator != null && isItemLabelVisible(row, column))
/* 687 */       drawItemLabel(g2, dataset, row, column, plot, generator, bar, (value < 0.0D)); 
/* 693 */     EntityCollection entities = state.getEntityCollection();
/* 694 */     if (entities != null)
/* 695 */       addItemEntity(entities, dataset, row, column, bar); 
/*     */   }
/*     */   
/*     */   protected double calculateSeriesWidth(double space, CategoryAxis axis, int categories, int series) {
/* 712 */     double factor = 1.0D - getItemMargin() - axis.getLowerMargin() - axis.getUpperMargin();
/* 714 */     if (categories > 1)
/* 715 */       factor -= axis.getCategoryMargin(); 
/* 717 */     return space * factor / (categories * series);
/*     */   }
/*     */   
/*     */   protected void drawItemLabel(Graphics2D g2, CategoryDataset data, int row, int column, CategoryPlot plot, CategoryItemLabelGenerator generator, Rectangle2D bar, boolean negative) {
/* 742 */     String label = generator.generateLabel(data, row, column);
/* 743 */     if (label == null)
/*     */       return; 
/* 747 */     Font labelFont = getItemLabelFont(row, column);
/* 748 */     g2.setFont(labelFont);
/* 749 */     Paint paint = getItemLabelPaint(row, column);
/* 750 */     g2.setPaint(paint);
/* 753 */     ItemLabelPosition position = null;
/* 754 */     if (!negative) {
/* 755 */       position = getPositiveItemLabelPosition(row, column);
/*     */     } else {
/* 758 */       position = getNegativeItemLabelPosition(row, column);
/*     */     } 
/* 762 */     Point2D anchorPoint = calculateLabelAnchorPoint(position.getItemLabelAnchor(), bar, plot.getOrientation());
/* 766 */     if (isInternalAnchor(position.getItemLabelAnchor())) {
/* 767 */       Shape bounds = TextUtilities.calculateRotatedStringBounds(label, g2, (float)anchorPoint.getX(), (float)anchorPoint.getY(), position.getTextAnchor(), position.getAngle(), position.getRotationAnchor());
/* 776 */       if (bounds != null && 
/* 777 */         !bar.contains(bounds.getBounds2D())) {
/* 778 */         if (!negative) {
/* 779 */           position = getPositiveItemLabelPositionFallback();
/*     */         } else {
/* 782 */           position = getNegativeItemLabelPositionFallback();
/*     */         } 
/* 784 */         if (position != null)
/* 785 */           anchorPoint = calculateLabelAnchorPoint(position.getItemLabelAnchor(), bar, plot.getOrientation()); 
/*     */       } 
/*     */     } 
/* 795 */     if (position != null)
/* 796 */       TextUtilities.drawRotatedString(label, g2, (float)anchorPoint.getX(), (float)anchorPoint.getY(), position.getTextAnchor(), position.getAngle(), position.getRotationAnchor()); 
/*     */   }
/*     */   
/*     */   private Point2D calculateLabelAnchorPoint(ItemLabelAnchor anchor, Rectangle2D bar, PlotOrientation orientation) {
/* 818 */     Point2D result = null;
/* 819 */     double offset = getItemLabelAnchorOffset();
/* 820 */     double x0 = bar.getX() - offset;
/* 821 */     double x1 = bar.getX();
/* 822 */     double x2 = bar.getX() + offset;
/* 823 */     double x3 = bar.getCenterX();
/* 824 */     double x4 = bar.getMaxX() - offset;
/* 825 */     double x5 = bar.getMaxX();
/* 826 */     double x6 = bar.getMaxX() + offset;
/* 828 */     double y0 = bar.getMaxY() + offset;
/* 829 */     double y1 = bar.getMaxY();
/* 830 */     double y2 = bar.getMaxY() - offset;
/* 831 */     double y3 = bar.getCenterY();
/* 832 */     double y4 = bar.getMinY() + offset;
/* 833 */     double y5 = bar.getMinY();
/* 834 */     double y6 = bar.getMinY() - offset;
/* 836 */     if (anchor == ItemLabelAnchor.CENTER) {
/* 837 */       result = new Point2D.Double(x3, y3);
/* 839 */     } else if (anchor == ItemLabelAnchor.INSIDE1) {
/* 840 */       result = new Point2D.Double(x4, y4);
/* 842 */     } else if (anchor == ItemLabelAnchor.INSIDE2) {
/* 843 */       result = new Point2D.Double(x4, y4);
/* 845 */     } else if (anchor == ItemLabelAnchor.INSIDE3) {
/* 846 */       result = new Point2D.Double(x4, y3);
/* 848 */     } else if (anchor == ItemLabelAnchor.INSIDE4) {
/* 849 */       result = new Point2D.Double(x4, y2);
/* 851 */     } else if (anchor == ItemLabelAnchor.INSIDE5) {
/* 852 */       result = new Point2D.Double(x4, y2);
/* 854 */     } else if (anchor == ItemLabelAnchor.INSIDE6) {
/* 855 */       result = new Point2D.Double(x3, y2);
/* 857 */     } else if (anchor == ItemLabelAnchor.INSIDE7) {
/* 858 */       result = new Point2D.Double(x2, y2);
/* 860 */     } else if (anchor == ItemLabelAnchor.INSIDE8) {
/* 861 */       result = new Point2D.Double(x2, y2);
/* 863 */     } else if (anchor == ItemLabelAnchor.INSIDE9) {
/* 864 */       result = new Point2D.Double(x2, y3);
/* 866 */     } else if (anchor == ItemLabelAnchor.INSIDE10) {
/* 867 */       result = new Point2D.Double(x2, y4);
/* 869 */     } else if (anchor == ItemLabelAnchor.INSIDE11) {
/* 870 */       result = new Point2D.Double(x2, y4);
/* 872 */     } else if (anchor == ItemLabelAnchor.INSIDE12) {
/* 873 */       result = new Point2D.Double(x3, y4);
/* 875 */     } else if (anchor == ItemLabelAnchor.OUTSIDE1) {
/* 876 */       result = new Point2D.Double(x5, y6);
/* 878 */     } else if (anchor == ItemLabelAnchor.OUTSIDE2) {
/* 879 */       result = new Point2D.Double(x6, y5);
/* 881 */     } else if (anchor == ItemLabelAnchor.OUTSIDE3) {
/* 882 */       result = new Point2D.Double(x6, y3);
/* 884 */     } else if (anchor == ItemLabelAnchor.OUTSIDE4) {
/* 885 */       result = new Point2D.Double(x6, y1);
/* 887 */     } else if (anchor == ItemLabelAnchor.OUTSIDE5) {
/* 888 */       result = new Point2D.Double(x5, y0);
/* 890 */     } else if (anchor == ItemLabelAnchor.OUTSIDE6) {
/* 891 */       result = new Point2D.Double(x3, y0);
/* 893 */     } else if (anchor == ItemLabelAnchor.OUTSIDE7) {
/* 894 */       result = new Point2D.Double(x1, y0);
/* 896 */     } else if (anchor == ItemLabelAnchor.OUTSIDE8) {
/* 897 */       result = new Point2D.Double(x0, y1);
/* 899 */     } else if (anchor == ItemLabelAnchor.OUTSIDE9) {
/* 900 */       result = new Point2D.Double(x0, y3);
/* 902 */     } else if (anchor == ItemLabelAnchor.OUTSIDE10) {
/* 903 */       result = new Point2D.Double(x0, y5);
/* 905 */     } else if (anchor == ItemLabelAnchor.OUTSIDE11) {
/* 906 */       result = new Point2D.Double(x1, y6);
/* 908 */     } else if (anchor == ItemLabelAnchor.OUTSIDE12) {
/* 909 */       result = new Point2D.Double(x3, y6);
/*     */     } 
/* 912 */     return result;
/*     */   }
/*     */   
/*     */   private boolean isInternalAnchor(ItemLabelAnchor anchor) {
/* 924 */     return (anchor == ItemLabelAnchor.CENTER || anchor == ItemLabelAnchor.INSIDE1 || anchor == ItemLabelAnchor.INSIDE2 || anchor == ItemLabelAnchor.INSIDE3 || anchor == ItemLabelAnchor.INSIDE4 || anchor == ItemLabelAnchor.INSIDE5 || anchor == ItemLabelAnchor.INSIDE6 || anchor == ItemLabelAnchor.INSIDE7 || anchor == ItemLabelAnchor.INSIDE8 || anchor == ItemLabelAnchor.INSIDE9 || anchor == ItemLabelAnchor.INSIDE10 || anchor == ItemLabelAnchor.INSIDE11 || anchor == ItemLabelAnchor.INSIDE12);
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 948 */     if (obj == this)
/* 949 */       return true; 
/* 951 */     if (!(obj instanceof BarRenderer))
/* 952 */       return false; 
/* 954 */     if (!super.equals(obj))
/* 955 */       return false; 
/* 957 */     BarRenderer that = (BarRenderer)obj;
/* 958 */     if (this.base != that.base)
/* 959 */       return false; 
/* 961 */     if (this.itemMargin != that.itemMargin)
/* 962 */       return false; 
/* 964 */     if (this.drawBarOutline != that.drawBarOutline)
/* 965 */       return false; 
/* 967 */     if (this.maximumBarWidth != that.maximumBarWidth)
/* 968 */       return false; 
/* 970 */     if (this.minimumBarLength != that.minimumBarLength)
/* 971 */       return false; 
/* 973 */     if (!ObjectUtilities.equal(this.gradientPaintTransformer, that.gradientPaintTransformer))
/* 975 */       return false; 
/* 977 */     if (!ObjectUtilities.equal(this.positiveItemLabelPositionFallback, that.positiveItemLabelPositionFallback))
/* 981 */       return false; 
/* 983 */     if (!ObjectUtilities.equal(this.negativeItemLabelPositionFallback, that.negativeItemLabelPositionFallback))
/* 987 */       return false; 
/* 990 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\renderer\category\BarRenderer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */