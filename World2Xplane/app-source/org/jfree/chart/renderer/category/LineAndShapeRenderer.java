/*     */ package org.jfree.chart.renderer.category;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Shape;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.Line2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.LegendItem;
/*     */ import org.jfree.chart.axis.CategoryAxis;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.entity.EntityCollection;
/*     */ import org.jfree.chart.event.RendererChangeEvent;
/*     */ import org.jfree.chart.plot.CategoryPlot;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.data.category.CategoryDataset;
/*     */ import org.jfree.util.BooleanList;
/*     */ import org.jfree.util.BooleanUtilities;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ import org.jfree.util.ShapeUtilities;
/*     */ 
/*     */ public class LineAndShapeRenderer extends AbstractCategoryItemRenderer implements Cloneable, PublicCloneable, Serializable {
/*     */   private static final long serialVersionUID = -197749519869226398L;
/*     */   
/*     */   private Boolean linesVisible;
/*     */   
/*     */   private BooleanList seriesLinesVisible;
/*     */   
/*     */   private boolean baseLinesVisible;
/*     */   
/*     */   private Boolean shapesVisible;
/*     */   
/*     */   private BooleanList seriesShapesVisible;
/*     */   
/*     */   private boolean baseShapesVisible;
/*     */   
/*     */   private Boolean shapesFilled;
/*     */   
/*     */   private BooleanList seriesShapesFilled;
/*     */   
/*     */   private boolean baseShapesFilled;
/*     */   
/*     */   private boolean useFillPaint;
/*     */   
/*     */   private boolean drawOutlines;
/*     */   
/*     */   private boolean useOutlinePaint;
/*     */   
/*     */   public LineAndShapeRenderer() {
/* 180 */     this(true, true);
/*     */   }
/*     */   
/*     */   public LineAndShapeRenderer(boolean lines, boolean shapes) {
/* 191 */     this.linesVisible = null;
/* 192 */     this.seriesLinesVisible = new BooleanList();
/* 193 */     this.baseLinesVisible = lines;
/* 194 */     this.shapesVisible = null;
/* 195 */     this.seriesShapesVisible = new BooleanList();
/* 196 */     this.baseShapesVisible = shapes;
/* 197 */     this.shapesFilled = null;
/* 198 */     this.seriesShapesFilled = new BooleanList();
/* 199 */     this.baseShapesFilled = true;
/* 200 */     this.useFillPaint = false;
/* 201 */     this.drawOutlines = true;
/* 202 */     this.useOutlinePaint = false;
/*     */   }
/*     */   
/*     */   public boolean getItemLineVisible(int series, int item) {
/* 217 */     Boolean flag = this.linesVisible;
/* 218 */     if (flag == null)
/* 219 */       flag = getSeriesLinesVisible(series); 
/* 221 */     if (flag != null)
/* 222 */       return flag.booleanValue(); 
/* 225 */     return this.baseLinesVisible;
/*     */   }
/*     */   
/*     */   public Boolean getLinesVisible() {
/* 237 */     return this.linesVisible;
/*     */   }
/*     */   
/*     */   public void setLinesVisible(Boolean visible) {
/* 249 */     this.linesVisible = visible;
/* 250 */     notifyListeners(new RendererChangeEvent(this));
/*     */   }
/*     */   
/*     */   public void setLinesVisible(boolean visible) {
/* 261 */     setLinesVisible(BooleanUtilities.valueOf(visible));
/*     */   }
/*     */   
/*     */   public Boolean getSeriesLinesVisible(int series) {
/* 273 */     return this.seriesLinesVisible.getBoolean(series);
/*     */   }
/*     */   
/*     */   public void setSeriesLinesVisible(int series, Boolean flag) {
/* 283 */     this.seriesLinesVisible.setBoolean(series, flag);
/* 284 */     notifyListeners(new RendererChangeEvent(this));
/*     */   }
/*     */   
/*     */   public void setSeriesLinesVisible(int series, boolean visible) {
/* 294 */     setSeriesLinesVisible(series, BooleanUtilities.valueOf(visible));
/*     */   }
/*     */   
/*     */   public boolean getBaseLinesVisible() {
/* 303 */     return this.baseLinesVisible;
/*     */   }
/*     */   
/*     */   public void setBaseLinesVisible(boolean flag) {
/* 312 */     this.baseLinesVisible = flag;
/* 313 */     notifyListeners(new RendererChangeEvent(this));
/*     */   }
/*     */   
/*     */   public boolean getItemShapeVisible(int series, int item) {
/* 328 */     Boolean flag = this.shapesVisible;
/* 329 */     if (flag == null)
/* 330 */       flag = getSeriesShapesVisible(series); 
/* 332 */     if (flag != null)
/* 333 */       return flag.booleanValue(); 
/* 336 */     return this.baseShapesVisible;
/*     */   }
/*     */   
/*     */   public Boolean getShapesVisible() {
/* 347 */     return this.shapesVisible;
/*     */   }
/*     */   
/*     */   public void setShapesVisible(Boolean visible) {
/* 357 */     this.shapesVisible = visible;
/* 358 */     notifyListeners(new RendererChangeEvent(this));
/*     */   }
/*     */   
/*     */   public void setShapesVisible(boolean visible) {
/* 368 */     setShapesVisible(BooleanUtilities.valueOf(visible));
/*     */   }
/*     */   
/*     */   public Boolean getSeriesShapesVisible(int series) {
/* 380 */     return this.seriesShapesVisible.getBoolean(series);
/*     */   }
/*     */   
/*     */   public void setSeriesShapesVisible(int series, boolean visible) {
/* 391 */     setSeriesShapesVisible(series, BooleanUtilities.valueOf(visible));
/*     */   }
/*     */   
/*     */   public void setSeriesShapesVisible(int series, Boolean flag) {
/* 402 */     this.seriesShapesVisible.setBoolean(series, flag);
/* 403 */     notifyListeners(new RendererChangeEvent(this));
/*     */   }
/*     */   
/*     */   public boolean getBaseShapesVisible() {
/* 412 */     return this.baseShapesVisible;
/*     */   }
/*     */   
/*     */   public void setBaseShapesVisible(boolean flag) {
/* 421 */     this.baseShapesVisible = flag;
/* 422 */     notifyListeners(new RendererChangeEvent(this));
/*     */   }
/*     */   
/*     */   public boolean getDrawOutlines() {
/* 432 */     return this.drawOutlines;
/*     */   }
/*     */   
/*     */   public void setDrawOutlines(boolean flag) {
/* 446 */     this.drawOutlines = flag;
/* 447 */     notifyListeners(new RendererChangeEvent(this));
/*     */   }
/*     */   
/*     */   public boolean getUseOutlinePaint() {
/* 457 */     return this.useOutlinePaint;
/*     */   }
/*     */   
/*     */   public void setUseOutlinePaint(boolean use) {
/* 467 */     this.useOutlinePaint = use;
/*     */   }
/*     */   
/*     */   public boolean getItemShapeFilled(int series, int item) {
/* 484 */     return getSeriesShapesFilled(series);
/*     */   }
/*     */   
/*     */   public boolean getSeriesShapesFilled(int series) {
/* 498 */     if (this.shapesFilled != null)
/* 499 */       return this.shapesFilled.booleanValue(); 
/* 503 */     Boolean flag = this.seriesShapesFilled.getBoolean(series);
/* 504 */     if (flag != null)
/* 505 */       return flag.booleanValue(); 
/* 508 */     return this.baseShapesFilled;
/*     */   }
/*     */   
/*     */   public Boolean getShapesFilled() {
/* 520 */     return this.shapesFilled;
/*     */   }
/*     */   
/*     */   public void setShapesFilled(boolean filled) {
/* 529 */     if (filled) {
/* 530 */       setShapesFilled(Boolean.TRUE);
/*     */     } else {
/* 533 */       setShapesFilled(Boolean.FALSE);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setShapesFilled(Boolean filled) {
/* 543 */     this.shapesFilled = filled;
/*     */   }
/*     */   
/*     */   public void setSeriesShapesFilled(int series, Boolean filled) {
/* 553 */     this.seriesShapesFilled.setBoolean(series, filled);
/*     */   }
/*     */   
/*     */   public void setSeriesShapesFilled(int series, boolean filled) {
/* 563 */     this.seriesShapesFilled.setBoolean(series, BooleanUtilities.valueOf(filled));
/*     */   }
/*     */   
/*     */   public boolean getBaseShapesFilled() {
/* 574 */     return this.baseShapesFilled;
/*     */   }
/*     */   
/*     */   public void setBaseShapesFilled(boolean flag) {
/* 583 */     this.baseShapesFilled = flag;
/*     */   }
/*     */   
/*     */   public boolean getUseFillPaint() {
/* 594 */     return this.useFillPaint;
/*     */   }
/*     */   
/*     */   public void setUseFillPaint(boolean flag) {
/* 605 */     this.useFillPaint = flag;
/* 606 */     notifyListeners(new RendererChangeEvent(this));
/*     */   }
/*     */   
/*     */   public LegendItem getLegendItem(int datasetIndex, int series) {
/* 619 */     CategoryPlot cp = getPlot();
/* 620 */     if (cp == null)
/* 621 */       return null; 
/* 624 */     if (isSeriesVisible(series) && isSeriesVisibleInLegend(series)) {
/* 626 */       CategoryDataset dataset = cp.getDataset(datasetIndex);
/* 627 */       String label = getLegendItemLabelGenerator().generateLabel(dataset, series);
/* 630 */       String description = label;
/* 631 */       String toolTipText = null;
/* 632 */       if (getLegendItemToolTipGenerator() != null)
/* 633 */         toolTipText = getLegendItemToolTipGenerator().generateLabel(dataset, series); 
/* 637 */       String urlText = null;
/* 638 */       if (getLegendItemURLGenerator() != null)
/* 639 */         urlText = getLegendItemURLGenerator().generateLabel(dataset, series); 
/* 643 */       Shape shape = getSeriesShape(series);
/* 644 */       Paint paint = getSeriesPaint(series);
/* 645 */       Paint fillPaint = this.useFillPaint ? getItemFillPaint(series, 0) : paint;
/* 647 */       boolean shapeOutlineVisible = this.drawOutlines;
/* 648 */       Paint outlinePaint = this.useOutlinePaint ? getItemOutlinePaint(series, 0) : paint;
/* 650 */       Stroke outlineStroke = getSeriesOutlineStroke(series);
/* 651 */       boolean lineVisible = getItemLineVisible(series, 0);
/* 652 */       boolean shapeVisible = getItemShapeVisible(series, 0);
/* 653 */       return new LegendItem(label, description, toolTipText, urlText, shapeVisible, shape, getItemShapeFilled(series, 0), fillPaint, shapeOutlineVisible, outlinePaint, outlineStroke, lineVisible, new Line2D.Double(-7.0D, 0.0D, 7.0D, 0.0D), getItemStroke(series, 0), getItemPaint(series, 0));
/*     */     } 
/* 659 */     return null;
/*     */   }
/*     */   
/*     */   public int getPassCount() {
/* 669 */     return 2;
/*     */   }
/*     */   
/*     */   public void drawItem(Graphics2D g2, CategoryItemRendererState state, Rectangle2D dataArea, CategoryPlot plot, CategoryAxis domainAxis, ValueAxis rangeAxis, CategoryDataset dataset, int row, int column, int pass) {
/* 698 */     if (!getItemVisible(row, column))
/*     */       return; 
/* 703 */     Number v = dataset.getValue(row, column);
/* 704 */     if (v == null)
/*     */       return; 
/* 708 */     PlotOrientation orientation = plot.getOrientation();
/* 711 */     double x1 = domainAxis.getCategoryMiddle(column, getColumnCount(), dataArea, plot.getDomainAxisEdge());
/* 714 */     double value = v.doubleValue();
/* 715 */     double y1 = rangeAxis.valueToJava2D(value, dataArea, plot.getRangeAxisEdge());
/* 719 */     if (pass == 0 && getItemLineVisible(row, column) && 
/* 720 */       column != 0) {
/* 721 */       Number previousValue = dataset.getValue(row, column - 1);
/* 722 */       if (previousValue != null) {
/* 724 */         double previous = previousValue.doubleValue();
/* 725 */         double x0 = domainAxis.getCategoryMiddle(column - 1, getColumnCount(), dataArea, plot.getDomainAxisEdge());
/* 729 */         double y0 = rangeAxis.valueToJava2D(previous, dataArea, plot.getRangeAxisEdge());
/* 733 */         Line2D line = null;
/* 734 */         if (orientation == PlotOrientation.HORIZONTAL) {
/* 735 */           line = new Line2D.Double(y0, x0, y1, x1);
/* 737 */         } else if (orientation == PlotOrientation.VERTICAL) {
/* 738 */           line = new Line2D.Double(x0, y0, x1, y1);
/*     */         } 
/* 740 */         g2.setPaint(getItemPaint(row, column));
/* 741 */         g2.setStroke(getItemStroke(row, column));
/* 742 */         g2.draw(line);
/*     */       } 
/*     */     } 
/* 747 */     if (pass == 1) {
/* 748 */       Shape shape = getItemShape(row, column);
/* 749 */       if (orientation == PlotOrientation.HORIZONTAL) {
/* 750 */         shape = ShapeUtilities.createTranslatedShape(shape, y1, x1);
/* 752 */       } else if (orientation == PlotOrientation.VERTICAL) {
/* 753 */         shape = ShapeUtilities.createTranslatedShape(shape, x1, y1);
/*     */       } 
/* 756 */       if (getItemShapeVisible(row, column)) {
/* 757 */         if (getItemShapeFilled(row, column)) {
/* 758 */           if (this.useFillPaint) {
/* 759 */             g2.setPaint(getItemFillPaint(row, column));
/*     */           } else {
/* 762 */             g2.setPaint(getItemPaint(row, column));
/*     */           } 
/* 764 */           g2.fill(shape);
/*     */         } 
/* 766 */         if (this.drawOutlines) {
/* 767 */           if (this.useOutlinePaint) {
/* 768 */             g2.setPaint(getItemOutlinePaint(row, column));
/*     */           } else {
/* 771 */             g2.setPaint(getItemPaint(row, column));
/*     */           } 
/* 773 */           g2.setStroke(getItemOutlineStroke(row, column));
/* 774 */           g2.draw(shape);
/*     */         } 
/*     */       } 
/* 779 */       if (isItemLabelVisible(row, column))
/* 780 */         if (orientation == PlotOrientation.HORIZONTAL) {
/* 781 */           drawItemLabel(g2, orientation, dataset, row, column, y1, x1, (value < 0.0D));
/* 786 */         } else if (orientation == PlotOrientation.VERTICAL) {
/* 787 */           drawItemLabel(g2, orientation, dataset, row, column, x1, y1, (value < 0.0D));
/*     */         }  
/* 795 */       EntityCollection entities = state.getEntityCollection();
/* 796 */       if (entities != null)
/* 797 */         addItemEntity(entities, dataset, row, column, shape); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 813 */     if (obj == this)
/* 814 */       return true; 
/* 816 */     if (!(obj instanceof LineAndShapeRenderer))
/* 817 */       return false; 
/* 820 */     LineAndShapeRenderer that = (LineAndShapeRenderer)obj;
/* 821 */     if (this.baseLinesVisible != that.baseLinesVisible)
/* 822 */       return false; 
/* 824 */     if (!ObjectUtilities.equal(this.seriesLinesVisible, that.seriesLinesVisible))
/* 826 */       return false; 
/* 828 */     if (!ObjectUtilities.equal(this.linesVisible, that.linesVisible))
/* 829 */       return false; 
/* 831 */     if (this.baseShapesVisible != that.baseShapesVisible)
/* 832 */       return false; 
/* 834 */     if (!ObjectUtilities.equal(this.seriesShapesVisible, that.seriesShapesVisible))
/* 836 */       return false; 
/* 838 */     if (!ObjectUtilities.equal(this.shapesVisible, that.shapesVisible))
/* 839 */       return false; 
/* 841 */     if (!ObjectUtilities.equal(this.shapesFilled, that.shapesFilled))
/* 842 */       return false; 
/* 844 */     if (!ObjectUtilities.equal(this.seriesShapesFilled, that.seriesShapesFilled))
/* 847 */       return false; 
/* 849 */     if (this.baseShapesFilled != that.baseShapesFilled)
/* 850 */       return false; 
/* 852 */     if (this.useOutlinePaint != that.useOutlinePaint)
/* 853 */       return false; 
/* 855 */     if (!super.equals(obj))
/* 856 */       return false; 
/* 858 */     return true;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 869 */     LineAndShapeRenderer clone = (LineAndShapeRenderer)super.clone();
/* 870 */     clone.seriesLinesVisible = (BooleanList)this.seriesLinesVisible.clone();
/* 872 */     clone.seriesShapesVisible = (BooleanList)this.seriesLinesVisible.clone();
/* 874 */     clone.seriesShapesFilled = (BooleanList)this.seriesShapesFilled.clone();
/* 876 */     return clone;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\renderer\category\LineAndShapeRenderer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */