/*      */ package org.jfree.chart.renderer.category;
/*      */ 
/*      */ import java.awt.Font;
/*      */ import java.awt.GradientPaint;
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.Paint;
/*      */ import java.awt.Shape;
/*      */ import java.awt.Stroke;
/*      */ import java.awt.geom.Line2D;
/*      */ import java.awt.geom.Point2D;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.io.Serializable;
/*      */ import org.jfree.chart.LegendItem;
/*      */ import org.jfree.chart.LegendItemCollection;
/*      */ import org.jfree.chart.axis.CategoryAxis;
/*      */ import org.jfree.chart.axis.ValueAxis;
/*      */ import org.jfree.chart.entity.CategoryItemEntity;
/*      */ import org.jfree.chart.entity.ChartEntity;
/*      */ import org.jfree.chart.entity.EntityCollection;
/*      */ import org.jfree.chart.event.RendererChangeEvent;
/*      */ import org.jfree.chart.labels.CategoryItemLabelGenerator;
/*      */ import org.jfree.chart.labels.CategorySeriesLabelGenerator;
/*      */ import org.jfree.chart.labels.CategoryToolTipGenerator;
/*      */ import org.jfree.chart.labels.ItemLabelPosition;
/*      */ import org.jfree.chart.labels.StandardCategorySeriesLabelGenerator;
/*      */ import org.jfree.chart.plot.CategoryMarker;
/*      */ import org.jfree.chart.plot.CategoryPlot;
/*      */ import org.jfree.chart.plot.DrawingSupplier;
/*      */ import org.jfree.chart.plot.IntervalMarker;
/*      */ import org.jfree.chart.plot.Marker;
/*      */ import org.jfree.chart.plot.PlotOrientation;
/*      */ import org.jfree.chart.plot.PlotRenderingInfo;
/*      */ import org.jfree.chart.plot.ValueMarker;
/*      */ import org.jfree.chart.renderer.AbstractRenderer;
/*      */ import org.jfree.chart.urls.CategoryURLGenerator;
/*      */ import org.jfree.data.Range;
/*      */ import org.jfree.data.category.CategoryDataset;
/*      */ import org.jfree.data.general.DatasetUtilities;
/*      */ import org.jfree.text.TextUtilities;
/*      */ import org.jfree.ui.GradientPaintTransformer;
/*      */ import org.jfree.ui.LengthAdjustmentType;
/*      */ import org.jfree.ui.RectangleAnchor;
/*      */ import org.jfree.ui.RectangleInsets;
/*      */ import org.jfree.util.ObjectList;
/*      */ import org.jfree.util.ObjectUtilities;
/*      */ import org.jfree.util.PublicCloneable;
/*      */ 
/*      */ public abstract class AbstractCategoryItemRenderer extends AbstractRenderer implements CategoryItemRenderer, Cloneable, PublicCloneable, Serializable {
/*      */   private static final long serialVersionUID = 1247553218442497391L;
/*      */   
/*      */   private CategoryPlot plot;
/*      */   
/*  196 */   private CategoryItemLabelGenerator itemLabelGenerator = null;
/*      */   
/*  197 */   private ObjectList itemLabelGeneratorList = new ObjectList();
/*      */   
/*      */   private CategoryItemLabelGenerator baseItemLabelGenerator;
/*      */   
/*  198 */   private CategoryToolTipGenerator toolTipGenerator = null;
/*      */   
/*  199 */   private ObjectList toolTipGeneratorList = new ObjectList();
/*      */   
/*      */   private CategoryToolTipGenerator baseToolTipGenerator;
/*      */   
/*  200 */   private CategoryURLGenerator itemURLGenerator = null;
/*      */   
/*  201 */   private ObjectList itemURLGeneratorList = new ObjectList();
/*      */   
/*      */   private CategoryURLGenerator baseItemURLGenerator;
/*      */   
/*  202 */   private CategorySeriesLabelGenerator legendItemLabelGenerator = (CategorySeriesLabelGenerator)new StandardCategorySeriesLabelGenerator();
/*      */   
/*      */   private CategorySeriesLabelGenerator legendItemToolTipGenerator;
/*      */   
/*      */   private CategorySeriesLabelGenerator legendItemURLGenerator;
/*      */   
/*      */   private transient int rowCount;
/*      */   
/*      */   private transient int columnCount;
/*      */   
/*      */   public int getPassCount() {
/*  214 */     return 1;
/*      */   }
/*      */   
/*      */   public CategoryPlot getPlot() {
/*  225 */     return this.plot;
/*      */   }
/*      */   
/*      */   public void setPlot(CategoryPlot plot) {
/*  236 */     if (plot == null)
/*  237 */       throw new IllegalArgumentException("Null 'plot' argument."); 
/*  239 */     this.plot = plot;
/*      */   }
/*      */   
/*      */   public CategoryItemLabelGenerator getItemLabelGenerator(int row, int column) {
/*  257 */     return getSeriesItemLabelGenerator(row);
/*      */   }
/*      */   
/*      */   public CategoryItemLabelGenerator getSeriesItemLabelGenerator(int series) {
/*  270 */     if (this.itemLabelGenerator != null)
/*  271 */       return this.itemLabelGenerator; 
/*  275 */     CategoryItemLabelGenerator generator = (CategoryItemLabelGenerator)this.itemLabelGeneratorList.get(series);
/*  277 */     if (generator == null)
/*  278 */       generator = this.baseItemLabelGenerator; 
/*  280 */     return generator;
/*      */   }
/*      */   
/*      */   public void setItemLabelGenerator(CategoryItemLabelGenerator generator) {
/*  291 */     this.itemLabelGenerator = generator;
/*  292 */     notifyListeners(new RendererChangeEvent(this));
/*      */   }
/*      */   
/*      */   public void setSeriesItemLabelGenerator(int series, CategoryItemLabelGenerator generator) {
/*  304 */     this.itemLabelGeneratorList.set(series, generator);
/*  305 */     notifyListeners(new RendererChangeEvent(this));
/*      */   }
/*      */   
/*      */   public CategoryItemLabelGenerator getBaseItemLabelGenerator() {
/*  314 */     return this.baseItemLabelGenerator;
/*      */   }
/*      */   
/*      */   public void setBaseItemLabelGenerator(CategoryItemLabelGenerator generator) {
/*  325 */     this.baseItemLabelGenerator = generator;
/*  326 */     notifyListeners(new RendererChangeEvent(this));
/*      */   }
/*      */   
/*      */   public CategoryToolTipGenerator getToolTipGenerator(int row, int column) {
/*  345 */     CategoryToolTipGenerator result = null;
/*  346 */     if (this.toolTipGenerator != null) {
/*  347 */       result = this.toolTipGenerator;
/*      */     } else {
/*  350 */       result = getSeriesToolTipGenerator(row);
/*  351 */       if (result == null)
/*  352 */         result = this.baseToolTipGenerator; 
/*      */     } 
/*  355 */     return result;
/*      */   }
/*      */   
/*      */   public CategoryToolTipGenerator getToolTipGenerator() {
/*  365 */     return this.toolTipGenerator;
/*      */   }
/*      */   
/*      */   public void setToolTipGenerator(CategoryToolTipGenerator generator) {
/*  376 */     this.toolTipGenerator = generator;
/*  377 */     notifyListeners(new RendererChangeEvent(this));
/*      */   }
/*      */   
/*      */   public CategoryToolTipGenerator getSeriesToolTipGenerator(int series) {
/*  389 */     return (CategoryToolTipGenerator)this.toolTipGeneratorList.get(series);
/*      */   }
/*      */   
/*      */   public void setSeriesToolTipGenerator(int series, CategoryToolTipGenerator generator) {
/*  402 */     this.toolTipGeneratorList.set(series, generator);
/*  403 */     notifyListeners(new RendererChangeEvent(this));
/*      */   }
/*      */   
/*      */   public CategoryToolTipGenerator getBaseToolTipGenerator() {
/*  412 */     return this.baseToolTipGenerator;
/*      */   }
/*      */   
/*      */   public void setBaseToolTipGenerator(CategoryToolTipGenerator generator) {
/*  423 */     this.baseToolTipGenerator = generator;
/*  424 */     notifyListeners(new RendererChangeEvent(this));
/*      */   }
/*      */   
/*      */   public CategoryURLGenerator getItemURLGenerator(int row, int column) {
/*  440 */     return getSeriesItemURLGenerator(row);
/*      */   }
/*      */   
/*      */   public CategoryURLGenerator getSeriesItemURLGenerator(int series) {
/*  453 */     if (this.itemURLGenerator != null)
/*  454 */       return this.itemURLGenerator; 
/*  458 */     CategoryURLGenerator generator = (CategoryURLGenerator)this.itemURLGeneratorList.get(series);
/*  460 */     if (generator == null)
/*  461 */       generator = this.baseItemURLGenerator; 
/*  463 */     return generator;
/*      */   }
/*      */   
/*      */   public void setItemURLGenerator(CategoryURLGenerator generator) {
/*  473 */     this.itemURLGenerator = generator;
/*      */   }
/*      */   
/*      */   public void setSeriesItemURLGenerator(int series, CategoryURLGenerator generator) {
/*  484 */     this.itemURLGeneratorList.set(series, generator);
/*      */   }
/*      */   
/*      */   public CategoryURLGenerator getBaseItemURLGenerator() {
/*  493 */     return this.baseItemURLGenerator;
/*      */   }
/*      */   
/*      */   public void setBaseItemURLGenerator(CategoryURLGenerator generator) {
/*  502 */     this.baseItemURLGenerator = generator;
/*      */   }
/*      */   
/*      */   public int getRowCount() {
/*  512 */     return this.rowCount;
/*      */   }
/*      */   
/*      */   public int getColumnCount() {
/*  522 */     return this.columnCount;
/*      */   }
/*      */   
/*      */   public CategoryItemRendererState initialise(Graphics2D g2, Rectangle2D dataArea, CategoryPlot plot, int rendererIndex, PlotRenderingInfo info) {
/*  552 */     setPlot(plot);
/*  553 */     CategoryDataset data = plot.getDataset(rendererIndex);
/*  554 */     if (data != null) {
/*  555 */       this.rowCount = data.getRowCount();
/*  556 */       this.columnCount = data.getColumnCount();
/*      */     } else {
/*  559 */       this.rowCount = 0;
/*  560 */       this.columnCount = 0;
/*      */     } 
/*  562 */     return new CategoryItemRendererState(info);
/*      */   }
/*      */   
/*      */   public Range findRangeBounds(CategoryDataset dataset) {
/*  576 */     return DatasetUtilities.findRangeBounds(dataset);
/*      */   }
/*      */   
/*      */   public void drawBackground(Graphics2D g2, CategoryPlot plot, Rectangle2D dataArea) {
/*  592 */     plot.drawBackground(g2, dataArea);
/*      */   }
/*      */   
/*      */   public void drawOutline(Graphics2D g2, CategoryPlot plot, Rectangle2D dataArea) {
/*  609 */     plot.drawOutline(g2, dataArea);
/*      */   }
/*      */   
/*      */   public void drawDomainGridline(Graphics2D g2, CategoryPlot plot, Rectangle2D dataArea, double value) {
/*  631 */     Line2D line = null;
/*  632 */     PlotOrientation orientation = plot.getOrientation();
/*  634 */     if (orientation == PlotOrientation.HORIZONTAL) {
/*  635 */       line = new Line2D.Double(dataArea.getMinX(), value, dataArea.getMaxX(), value);
/*  639 */     } else if (orientation == PlotOrientation.VERTICAL) {
/*  640 */       line = new Line2D.Double(value, dataArea.getMinY(), value, dataArea.getMaxY());
/*      */     } 
/*  645 */     Paint paint = plot.getDomainGridlinePaint();
/*  646 */     if (paint == null)
/*  647 */       paint = CategoryPlot.DEFAULT_GRIDLINE_PAINT; 
/*  649 */     g2.setPaint(paint);
/*  651 */     Stroke stroke = plot.getDomainGridlineStroke();
/*  652 */     if (stroke == null)
/*  653 */       stroke = CategoryPlot.DEFAULT_GRIDLINE_STROKE; 
/*  655 */     g2.setStroke(stroke);
/*  657 */     g2.draw(line);
/*      */   }
/*      */   
/*      */   public void drawRangeGridline(Graphics2D g2, CategoryPlot plot, ValueAxis axis, Rectangle2D dataArea, double value) {
/*  678 */     Range range = axis.getRange();
/*  679 */     if (!range.contains(value))
/*      */       return; 
/*  683 */     PlotOrientation orientation = plot.getOrientation();
/*  684 */     double v = axis.valueToJava2D(value, dataArea, plot.getRangeAxisEdge());
/*  685 */     Line2D line = null;
/*  686 */     if (orientation == PlotOrientation.HORIZONTAL) {
/*  687 */       line = new Line2D.Double(v, dataArea.getMinY(), v, dataArea.getMaxY());
/*  691 */     } else if (orientation == PlotOrientation.VERTICAL) {
/*  692 */       line = new Line2D.Double(dataArea.getMinX(), v, dataArea.getMaxX(), v);
/*      */     } 
/*  697 */     Paint paint = plot.getRangeGridlinePaint();
/*  698 */     if (paint == null)
/*  699 */       paint = CategoryPlot.DEFAULT_GRIDLINE_PAINT; 
/*  701 */     g2.setPaint(paint);
/*  703 */     Stroke stroke = plot.getRangeGridlineStroke();
/*  704 */     if (stroke == null)
/*  705 */       stroke = CategoryPlot.DEFAULT_GRIDLINE_STROKE; 
/*  707 */     g2.setStroke(stroke);
/*  709 */     g2.draw(line);
/*      */   }
/*      */   
/*      */   public void drawDomainMarker(Graphics2D g2, CategoryPlot plot, CategoryAxis axis, CategoryMarker marker, Rectangle2D dataArea) {
/*  728 */     Comparable category = marker.getKey();
/*  729 */     CategoryDataset dataset = plot.getDataset(plot.getIndexOf(this));
/*  730 */     int columnIndex = dataset.getColumnIndex(category);
/*  731 */     if (columnIndex < 0)
/*      */       return; 
/*  734 */     PlotOrientation orientation = plot.getOrientation();
/*  735 */     Rectangle2D bounds = null;
/*  736 */     if (marker.getDrawAsLine()) {
/*  737 */       double v = axis.getCategoryMiddle(columnIndex, dataset.getColumnCount(), dataArea, plot.getDomainAxisEdge());
/*  741 */       Line2D line = null;
/*  742 */       if (orientation == PlotOrientation.HORIZONTAL) {
/*  743 */         line = new Line2D.Double(dataArea.getMinX(), v, dataArea.getMaxX(), v);
/*  747 */       } else if (orientation == PlotOrientation.VERTICAL) {
/*  748 */         line = new Line2D.Double(v, dataArea.getMinY(), v, dataArea.getMaxY());
/*      */       } 
/*  753 */       g2.setPaint(marker.getPaint());
/*  754 */       g2.setStroke(marker.getStroke());
/*  755 */       g2.draw(line);
/*  756 */       bounds = line.getBounds2D();
/*      */     } else {
/*  759 */       double v0 = axis.getCategoryStart(columnIndex, dataset.getColumnCount(), dataArea, plot.getDomainAxisEdge());
/*  763 */       double v1 = axis.getCategoryEnd(columnIndex, dataset.getColumnCount(), dataArea, plot.getDomainAxisEdge());
/*  767 */       Rectangle2D area = null;
/*  768 */       if (orientation == PlotOrientation.HORIZONTAL) {
/*  769 */         area = new Rectangle2D.Double(dataArea.getMinX(), v0, dataArea.getWidth(), v1 - v0);
/*  773 */       } else if (orientation == PlotOrientation.VERTICAL) {
/*  774 */         area = new Rectangle2D.Double(v0, dataArea.getMinY(), v1 - v0, dataArea.getHeight());
/*      */       } 
/*  778 */       g2.setPaint(marker.getPaint());
/*  779 */       g2.fill(area);
/*  780 */       bounds = area;
/*      */     } 
/*  783 */     String label = marker.getLabel();
/*  784 */     RectangleAnchor anchor = marker.getLabelAnchor();
/*  785 */     if (label != null) {
/*  786 */       Font labelFont = marker.getLabelFont();
/*  787 */       g2.setFont(labelFont);
/*  788 */       g2.setPaint(marker.getLabelPaint());
/*  789 */       Point2D coordinates = calculateDomainMarkerTextAnchorPoint(g2, orientation, dataArea, bounds, marker.getLabelOffset(), marker.getLabelOffsetType(), anchor);
/*  793 */       TextUtilities.drawAlignedString(label, g2, (float)coordinates.getX(), (float)coordinates.getY(), marker.getLabelTextAnchor());
/*      */     } 
/*      */   }
/*      */   
/*      */   public void drawRangeMarker(Graphics2D g2, CategoryPlot plot, ValueAxis axis, Marker marker, Rectangle2D dataArea) {
/*  816 */     if (marker instanceof ValueMarker) {
/*  817 */       ValueMarker vm = (ValueMarker)marker;
/*  818 */       double value = vm.getValue();
/*  819 */       Range range = axis.getRange();
/*  821 */       if (!range.contains(value))
/*      */         return; 
/*  825 */       PlotOrientation orientation = plot.getOrientation();
/*  826 */       double v = axis.valueToJava2D(value, dataArea, plot.getRangeAxisEdge());
/*  829 */       Line2D line = null;
/*  830 */       if (orientation == PlotOrientation.HORIZONTAL) {
/*  831 */         line = new Line2D.Double(v, dataArea.getMinY(), v, dataArea.getMaxY());
/*  835 */       } else if (orientation == PlotOrientation.VERTICAL) {
/*  836 */         line = new Line2D.Double(dataArea.getMinX(), v, dataArea.getMaxX(), v);
/*      */       } 
/*  841 */       g2.setPaint(marker.getPaint());
/*  842 */       g2.setStroke(marker.getStroke());
/*  843 */       g2.draw(line);
/*  845 */       String label = marker.getLabel();
/*  846 */       RectangleAnchor anchor = marker.getLabelAnchor();
/*  847 */       if (label != null) {
/*  848 */         Font labelFont = marker.getLabelFont();
/*  849 */         g2.setFont(labelFont);
/*  850 */         g2.setPaint(marker.getLabelPaint());
/*  851 */         Point2D coordinates = calculateRangeMarkerTextAnchorPoint(g2, orientation, dataArea, line.getBounds2D(), marker.getLabelOffset(), LengthAdjustmentType.EXPAND, anchor);
/*  855 */         TextUtilities.drawAlignedString(label, g2, (float)coordinates.getX(), (float)coordinates.getY(), marker.getLabelTextAnchor());
/*      */       } 
/*  862 */     } else if (marker instanceof IntervalMarker) {
/*  864 */       IntervalMarker im = (IntervalMarker)marker;
/*  865 */       double start = im.getStartValue();
/*  866 */       double end = im.getEndValue();
/*  867 */       Range range = axis.getRange();
/*  868 */       if (!range.intersects(start, end))
/*      */         return; 
/*  873 */       start = range.constrain(start);
/*  874 */       end = range.constrain(end);
/*  876 */       double v0 = axis.valueToJava2D(start, dataArea, plot.getRangeAxisEdge());
/*  879 */       double v1 = axis.valueToJava2D(end, dataArea, plot.getRangeAxisEdge());
/*  883 */       PlotOrientation orientation = plot.getOrientation();
/*  884 */       Rectangle2D rect = null;
/*  885 */       if (orientation == PlotOrientation.HORIZONTAL) {
/*  886 */         rect = new Rectangle2D.Double(v0, dataArea.getMinY(), v1 - v0, dataArea.getHeight());
/*  890 */       } else if (orientation == PlotOrientation.VERTICAL) {
/*  891 */         rect = new Rectangle2D.Double(dataArea.getMinX(), Math.min(v0, v1), dataArea.getWidth(), Math.abs(v1 - v0));
/*      */       } 
/*  896 */       Paint p = marker.getPaint();
/*  897 */       if (p instanceof GradientPaint) {
/*  898 */         GradientPaint gp = (GradientPaint)p;
/*  899 */         GradientPaintTransformer t = im.getGradientPaintTransformer();
/*  900 */         if (t != null)
/*  901 */           gp = t.transform(gp, rect); 
/*  903 */         g2.setPaint(gp);
/*      */       } else {
/*  906 */         g2.setPaint(p);
/*      */       } 
/*  908 */       g2.fill(rect);
/*  910 */       String label = marker.getLabel();
/*  911 */       RectangleAnchor anchor = marker.getLabelAnchor();
/*  912 */       if (label != null) {
/*  913 */         Font labelFont = marker.getLabelFont();
/*  914 */         g2.setFont(labelFont);
/*  915 */         g2.setPaint(marker.getLabelPaint());
/*  916 */         Point2D coordinates = calculateRangeMarkerTextAnchorPoint(g2, orientation, dataArea, rect, marker.getLabelOffset(), marker.getLabelOffsetType(), anchor);
/*  921 */         TextUtilities.drawAlignedString(label, g2, (float)coordinates.getX(), (float)coordinates.getY(), marker.getLabelTextAnchor());
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   protected Point2D calculateDomainMarkerTextAnchorPoint(Graphics2D g2, PlotOrientation orientation, Rectangle2D dataArea, Rectangle2D markerArea, RectangleInsets markerOffset, LengthAdjustmentType labelOffsetType, RectangleAnchor anchor) {
/*  954 */     Rectangle2D anchorRect = null;
/*  955 */     if (orientation == PlotOrientation.HORIZONTAL) {
/*  956 */       anchorRect = markerOffset.createAdjustedRectangle(markerArea, LengthAdjustmentType.CONTRACT, labelOffsetType);
/*  960 */     } else if (orientation == PlotOrientation.VERTICAL) {
/*  961 */       anchorRect = markerOffset.createAdjustedRectangle(markerArea, labelOffsetType, LengthAdjustmentType.CONTRACT);
/*      */     } 
/*  965 */     return RectangleAnchor.coordinates(anchorRect, anchor);
/*      */   }
/*      */   
/*      */   protected Point2D calculateRangeMarkerTextAnchorPoint(Graphics2D g2, PlotOrientation orientation, Rectangle2D dataArea, Rectangle2D markerArea, RectangleInsets markerOffset, LengthAdjustmentType labelOffsetType, RectangleAnchor anchor) {
/*  990 */     Rectangle2D anchorRect = null;
/*  991 */     if (orientation == PlotOrientation.HORIZONTAL) {
/*  992 */       anchorRect = markerOffset.createAdjustedRectangle(markerArea, labelOffsetType, LengthAdjustmentType.CONTRACT);
/*  996 */     } else if (orientation == PlotOrientation.VERTICAL) {
/*  997 */       anchorRect = markerOffset.createAdjustedRectangle(markerArea, LengthAdjustmentType.CONTRACT, labelOffsetType);
/*      */     } 
/* 1001 */     return RectangleAnchor.coordinates(anchorRect, anchor);
/*      */   }
/*      */   
/*      */   public LegendItem getLegendItem(int datasetIndex, int series) {
/* 1015 */     CategoryPlot p = getPlot();
/* 1016 */     if (p == null)
/* 1017 */       return null; 
/* 1021 */     CategoryDataset dataset = p.getDataset(datasetIndex);
/* 1022 */     String label = this.legendItemLabelGenerator.generateLabel(dataset, series);
/* 1025 */     String description = label;
/* 1026 */     String toolTipText = null;
/* 1027 */     if (this.legendItemToolTipGenerator != null)
/* 1028 */       toolTipText = this.legendItemToolTipGenerator.generateLabel(dataset, series); 
/* 1032 */     String urlText = null;
/* 1033 */     if (this.legendItemURLGenerator != null)
/* 1034 */       urlText = this.legendItemURLGenerator.generateLabel(dataset, series); 
/* 1038 */     Shape shape = getSeriesShape(series);
/* 1039 */     Paint paint = getSeriesPaint(series);
/* 1040 */     Paint outlinePaint = getSeriesOutlinePaint(series);
/* 1041 */     Stroke outlineStroke = getSeriesOutlineStroke(series);
/* 1043 */     return new LegendItem(label, description, toolTipText, urlText, shape, paint, outlineStroke, outlinePaint);
/*      */   }
/*      */   
/*      */   public boolean equals(Object obj) {
/* 1057 */     if (obj == this)
/* 1058 */       return true; 
/* 1060 */     if (!(obj instanceof AbstractCategoryItemRenderer))
/* 1061 */       return false; 
/* 1063 */     if (!super.equals(obj))
/* 1064 */       return false; 
/* 1067 */     AbstractCategoryItemRenderer that = (AbstractCategoryItemRenderer)obj;
/* 1069 */     if (!ObjectUtilities.equal(this.itemLabelGenerator, that.itemLabelGenerator))
/* 1071 */       return false; 
/* 1073 */     if (!ObjectUtilities.equal(this.itemLabelGeneratorList, that.itemLabelGeneratorList))
/* 1076 */       return false; 
/* 1078 */     if (!ObjectUtilities.equal(this.baseItemLabelGenerator, that.baseItemLabelGenerator))
/* 1081 */       return false; 
/* 1083 */     if (!ObjectUtilities.equal(this.toolTipGenerator, that.toolTipGenerator))
/* 1086 */       return false; 
/* 1088 */     if (!ObjectUtilities.equal(this.toolTipGeneratorList, that.toolTipGeneratorList))
/* 1091 */       return false; 
/* 1093 */     if (!ObjectUtilities.equal(this.baseToolTipGenerator, that.baseToolTipGenerator))
/* 1096 */       return false; 
/* 1098 */     if (!ObjectUtilities.equal(this.itemURLGenerator, that.itemURLGenerator))
/* 1101 */       return false; 
/* 1103 */     if (!ObjectUtilities.equal(this.itemURLGeneratorList, that.itemURLGeneratorList))
/* 1106 */       return false; 
/* 1108 */     if (!ObjectUtilities.equal(this.baseItemURLGenerator, that.baseItemURLGenerator))
/* 1111 */       return false; 
/* 1114 */     return true;
/*      */   }
/*      */   
/*      */   public int hashCode() {
/* 1124 */     int result = super.hashCode();
/* 1125 */     return result;
/*      */   }
/*      */   
/*      */   public DrawingSupplier getDrawingSupplier() {
/* 1134 */     DrawingSupplier result = null;
/* 1135 */     CategoryPlot cp = getPlot();
/* 1136 */     if (cp != null)
/* 1137 */       result = cp.getDrawingSupplier(); 
/* 1139 */     return result;
/*      */   }
/*      */   
/*      */   protected void drawItemLabel(Graphics2D g2, PlotOrientation orientation, CategoryDataset dataset, int row, int column, double x, double y, boolean negative) {
/* 1162 */     CategoryItemLabelGenerator generator = getItemLabelGenerator(row, column);
/* 1164 */     if (generator != null) {
/* 1165 */       Font labelFont = getItemLabelFont(row, column);
/* 1166 */       Paint paint = getItemLabelPaint(row, column);
/* 1167 */       g2.setFont(labelFont);
/* 1168 */       g2.setPaint(paint);
/* 1169 */       String label = generator.generateLabel(dataset, row, column);
/* 1170 */       ItemLabelPosition position = null;
/* 1171 */       if (!negative) {
/* 1172 */         position = getPositiveItemLabelPosition(row, column);
/*      */       } else {
/* 1175 */         position = getNegativeItemLabelPosition(row, column);
/*      */       } 
/* 1177 */       Point2D anchorPoint = calculateLabelAnchorPoint(position.getItemLabelAnchor(), x, y, orientation);
/* 1180 */       TextUtilities.drawRotatedString(label, g2, (float)anchorPoint.getX(), (float)anchorPoint.getY(), position.getTextAnchor(), position.getAngle(), position.getRotationAnchor());
/*      */     } 
/*      */   }
/*      */   
/*      */   public Object clone() throws CloneNotSupportedException {
/* 1202 */     AbstractCategoryItemRenderer clone = (AbstractCategoryItemRenderer)super.clone();
/* 1205 */     if (this.itemLabelGenerator != null)
/* 1206 */       if (this.itemLabelGenerator instanceof PublicCloneable) {
/* 1207 */         PublicCloneable pc = (PublicCloneable)this.itemLabelGenerator;
/* 1208 */         clone.itemLabelGenerator = (CategoryItemLabelGenerator)pc.clone();
/*      */       } else {
/* 1212 */         throw new CloneNotSupportedException("ItemLabelGenerator not cloneable.");
/*      */       }  
/* 1218 */     if (this.itemLabelGeneratorList != null)
/* 1219 */       clone.itemLabelGeneratorList = (ObjectList)this.itemLabelGeneratorList.clone(); 
/* 1223 */     if (this.baseItemLabelGenerator != null)
/* 1224 */       if (this.baseItemLabelGenerator instanceof PublicCloneable) {
/* 1225 */         PublicCloneable pc = (PublicCloneable)this.baseItemLabelGenerator;
/* 1227 */         clone.baseItemLabelGenerator = (CategoryItemLabelGenerator)pc.clone();
/*      */       } else {
/* 1231 */         throw new CloneNotSupportedException("ItemLabelGenerator not cloneable.");
/*      */       }  
/* 1237 */     if (this.toolTipGenerator != null)
/* 1238 */       if (this.toolTipGenerator instanceof PublicCloneable) {
/* 1239 */         PublicCloneable pc = (PublicCloneable)this.toolTipGenerator;
/* 1240 */         clone.toolTipGenerator = (CategoryToolTipGenerator)pc.clone();
/*      */       } else {
/* 1243 */         throw new CloneNotSupportedException("Tool tip generator not cloneable.");
/*      */       }  
/* 1249 */     if (this.toolTipGeneratorList != null)
/* 1250 */       clone.toolTipGeneratorList = (ObjectList)this.toolTipGeneratorList.clone(); 
/* 1254 */     if (this.baseToolTipGenerator != null)
/* 1255 */       if (this.baseToolTipGenerator instanceof PublicCloneable) {
/* 1256 */         PublicCloneable pc = (PublicCloneable)this.baseToolTipGenerator;
/* 1258 */         clone.baseToolTipGenerator = (CategoryToolTipGenerator)pc.clone();
/*      */       } else {
/* 1262 */         throw new CloneNotSupportedException("Base tool tip generator not cloneable.");
/*      */       }  
/* 1268 */     if (this.itemURLGenerator != null)
/* 1269 */       if (this.itemURLGenerator instanceof PublicCloneable) {
/* 1270 */         PublicCloneable pc = (PublicCloneable)this.itemURLGenerator;
/* 1271 */         clone.itemURLGenerator = (CategoryURLGenerator)pc.clone();
/*      */       } else {
/* 1274 */         throw new CloneNotSupportedException("Item URL generator not cloneable.");
/*      */       }  
/* 1280 */     if (this.itemURLGeneratorList != null)
/* 1281 */       clone.itemURLGeneratorList = (ObjectList)this.itemURLGeneratorList.clone(); 
/* 1285 */     if (this.baseItemURLGenerator != null)
/* 1286 */       if (this.baseItemURLGenerator instanceof PublicCloneable) {
/* 1287 */         PublicCloneable pc = (PublicCloneable)this.baseItemURLGenerator;
/* 1289 */         clone.baseItemURLGenerator = (CategoryURLGenerator)pc.clone();
/*      */       } else {
/* 1292 */         throw new CloneNotSupportedException("Base item URL generator not cloneable.");
/*      */       }  
/* 1298 */     return clone;
/*      */   }
/*      */   
/*      */   protected CategoryAxis getDomainAxis(CategoryPlot plot, int index) {
/* 1310 */     CategoryAxis result = plot.getDomainAxis(index);
/* 1311 */     if (result == null)
/* 1312 */       result = plot.getDomainAxis(); 
/* 1314 */     return result;
/*      */   }
/*      */   
/*      */   protected ValueAxis getRangeAxis(CategoryPlot plot, int index) {
/* 1326 */     ValueAxis result = plot.getRangeAxis(index);
/* 1327 */     if (result == null)
/* 1328 */       result = plot.getRangeAxis(); 
/* 1330 */     return result;
/*      */   }
/*      */   
/*      */   public LegendItemCollection getLegendItems() {
/* 1340 */     if (this.plot == null)
/* 1341 */       return new LegendItemCollection(); 
/* 1343 */     LegendItemCollection result = new LegendItemCollection();
/* 1344 */     int index = this.plot.getIndexOf(this);
/* 1345 */     CategoryDataset dataset = this.plot.getDataset(index);
/* 1346 */     if (dataset != null) {
/* 1347 */       int seriesCount = dataset.getRowCount();
/* 1348 */       for (int i = 0; i < seriesCount; i++) {
/* 1349 */         LegendItem item = getLegendItem(index, i);
/* 1350 */         if (item != null)
/* 1351 */           result.add(item); 
/*      */       } 
/*      */     } 
/* 1356 */     return result;
/*      */   }
/*      */   
/*      */   public CategorySeriesLabelGenerator getLegendItemLabelGenerator() {
/* 1365 */     return this.legendItemLabelGenerator;
/*      */   }
/*      */   
/*      */   public void setLegendItemLabelGenerator(CategorySeriesLabelGenerator generator) {
/* 1375 */     if (generator == null)
/* 1376 */       throw new IllegalArgumentException("Null 'generator' argument."); 
/* 1378 */     this.legendItemLabelGenerator = generator;
/*      */   }
/*      */   
/*      */   public CategorySeriesLabelGenerator getLegendItemToolTipGenerator() {
/* 1387 */     return this.legendItemToolTipGenerator;
/*      */   }
/*      */   
/*      */   public void setLegendItemToolTipGenerator(CategorySeriesLabelGenerator generator) {
/* 1397 */     this.legendItemToolTipGenerator = generator;
/*      */   }
/*      */   
/*      */   public CategorySeriesLabelGenerator getLegendItemURLGenerator() {
/* 1406 */     return this.legendItemURLGenerator;
/*      */   }
/*      */   
/*      */   public void setLegendItemURLGenerator(CategorySeriesLabelGenerator generator) {
/* 1416 */     this.legendItemURLGenerator = generator;
/*      */   }
/*      */   
/*      */   protected void addItemEntity(EntityCollection entities, CategoryDataset dataset, int row, int column, Shape hotspot) {
/* 1433 */     String tip = null;
/* 1434 */     CategoryToolTipGenerator tipster = getToolTipGenerator(row, column);
/* 1435 */     if (tipster != null)
/* 1436 */       tip = tipster.generateToolTip(dataset, row, column); 
/* 1438 */     String url = null;
/* 1439 */     CategoryURLGenerator urlster = getItemURLGenerator(row, column);
/* 1440 */     if (urlster != null)
/* 1441 */       url = urlster.generateURL(dataset, row, column); 
/* 1443 */     CategoryItemEntity entity = new CategoryItemEntity(hotspot, tip, url, dataset, row, dataset.getColumnKey(column), column);
/* 1447 */     entities.add((ChartEntity)entity);
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\renderer\category\AbstractCategoryItemRenderer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */