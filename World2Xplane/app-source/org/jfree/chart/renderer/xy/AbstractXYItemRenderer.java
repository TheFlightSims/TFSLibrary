/*      */ package org.jfree.chart.renderer.xy;
/*      */ 
/*      */ import java.awt.Font;
/*      */ import java.awt.GradientPaint;
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.Paint;
/*      */ import java.awt.Shape;
/*      */ import java.awt.Stroke;
/*      */ import java.awt.geom.Ellipse2D;
/*      */ import java.awt.geom.Line2D;
/*      */ import java.awt.geom.Point2D;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.io.Serializable;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import org.jfree.chart.LegendItem;
/*      */ import org.jfree.chart.LegendItemCollection;
/*      */ import org.jfree.chart.annotations.XYAnnotation;
/*      */ import org.jfree.chart.axis.ValueAxis;
/*      */ import org.jfree.chart.entity.ChartEntity;
/*      */ import org.jfree.chart.entity.EntityCollection;
/*      */ import org.jfree.chart.entity.XYItemEntity;
/*      */ import org.jfree.chart.event.RendererChangeEvent;
/*      */ import org.jfree.chart.labels.ItemLabelPosition;
/*      */ import org.jfree.chart.labels.StandardXYSeriesLabelGenerator;
/*      */ import org.jfree.chart.labels.XYItemLabelGenerator;
/*      */ import org.jfree.chart.labels.XYSeriesLabelGenerator;
/*      */ import org.jfree.chart.labels.XYToolTipGenerator;
/*      */ import org.jfree.chart.plot.CrosshairState;
/*      */ import org.jfree.chart.plot.DrawingSupplier;
/*      */ import org.jfree.chart.plot.IntervalMarker;
/*      */ import org.jfree.chart.plot.Marker;
/*      */ import org.jfree.chart.plot.Plot;
/*      */ import org.jfree.chart.plot.PlotOrientation;
/*      */ import org.jfree.chart.plot.PlotRenderingInfo;
/*      */ import org.jfree.chart.plot.ValueMarker;
/*      */ import org.jfree.chart.plot.XYPlot;
/*      */ import org.jfree.chart.renderer.AbstractRenderer;
/*      */ import org.jfree.chart.urls.XYURLGenerator;
/*      */ import org.jfree.data.Range;
/*      */ import org.jfree.data.general.DatasetUtilities;
/*      */ import org.jfree.data.xy.XYDataset;
/*      */ import org.jfree.text.TextUtilities;
/*      */ import org.jfree.ui.GradientPaintTransformer;
/*      */ import org.jfree.ui.Layer;
/*      */ import org.jfree.ui.LengthAdjustmentType;
/*      */ import org.jfree.ui.RectangleAnchor;
/*      */ import org.jfree.ui.RectangleInsets;
/*      */ import org.jfree.util.ObjectList;
/*      */ import org.jfree.util.ObjectUtilities;
/*      */ import org.jfree.util.PublicCloneable;
/*      */ 
/*      */ public abstract class AbstractXYItemRenderer extends AbstractRenderer implements XYItemRenderer, Cloneable, Serializable {
/*      */   private static final long serialVersionUID = 8019124836026607990L;
/*      */   
/*      */   private XYPlot plot;
/*      */   
/*  203 */   private XYItemLabelGenerator itemLabelGenerator = null;
/*      */   
/*  204 */   private ObjectList itemLabelGeneratorList = new ObjectList();
/*      */   
/*      */   private XYItemLabelGenerator baseItemLabelGenerator;
/*      */   
/*  205 */   private XYToolTipGenerator toolTipGenerator = null;
/*      */   
/*  206 */   private ObjectList toolTipGeneratorList = new ObjectList();
/*      */   
/*      */   private XYToolTipGenerator baseToolTipGenerator;
/*      */   
/*  207 */   private XYURLGenerator urlGenerator = null;
/*      */   
/*  208 */   private List backgroundAnnotations = new ArrayList();
/*      */   
/*  209 */   private List foregroundAnnotations = new ArrayList();
/*      */   
/*  210 */   private int defaultEntityRadius = 3;
/*      */   
/*  211 */   private XYSeriesLabelGenerator legendItemLabelGenerator = (XYSeriesLabelGenerator)new StandardXYSeriesLabelGenerator("{0}");
/*      */   
/*      */   private XYSeriesLabelGenerator legendItemToolTipGenerator;
/*      */   
/*      */   private XYSeriesLabelGenerator legendItemURLGenerator;
/*      */   
/*      */   public int getPassCount() {
/*  223 */     return 1;
/*      */   }
/*      */   
/*      */   public XYPlot getPlot() {
/*  232 */     return this.plot;
/*      */   }
/*      */   
/*      */   public void setPlot(XYPlot plot) {
/*  241 */     this.plot = plot;
/*      */   }
/*      */   
/*      */   public XYItemRendererState initialise(Graphics2D g2, Rectangle2D dataArea, XYPlot plot, XYDataset data, PlotRenderingInfo info) {
/*  267 */     XYItemRendererState state = new XYItemRendererState(info);
/*  268 */     return state;
/*      */   }
/*      */   
/*      */   public XYItemLabelGenerator getItemLabelGenerator(int row, int column) {
/*  286 */     return getSeriesItemLabelGenerator(row);
/*      */   }
/*      */   
/*      */   public XYItemLabelGenerator getSeriesItemLabelGenerator(int series) {
/*  299 */     if (this.itemLabelGenerator != null)
/*  300 */       return this.itemLabelGenerator; 
/*  304 */     XYItemLabelGenerator generator = (XYItemLabelGenerator)this.itemLabelGeneratorList.get(series);
/*  306 */     if (generator == null)
/*  307 */       generator = this.baseItemLabelGenerator; 
/*  309 */     return generator;
/*      */   }
/*      */   
/*      */   public void setItemLabelGenerator(XYItemLabelGenerator generator) {
/*  320 */     this.itemLabelGenerator = generator;
/*  321 */     notifyListeners(new RendererChangeEvent(this));
/*      */   }
/*      */   
/*      */   public void setSeriesItemLabelGenerator(int series, XYItemLabelGenerator generator) {
/*  333 */     this.itemLabelGeneratorList.set(series, generator);
/*  334 */     notifyListeners(new RendererChangeEvent(this));
/*      */   }
/*      */   
/*      */   public XYItemLabelGenerator getBaseItemLabelGenerator() {
/*  343 */     return this.baseItemLabelGenerator;
/*      */   }
/*      */   
/*      */   public void setBaseItemLabelGenerator(XYItemLabelGenerator generator) {
/*  353 */     this.baseItemLabelGenerator = generator;
/*  354 */     notifyListeners(new RendererChangeEvent(this));
/*      */   }
/*      */   
/*      */   public XYToolTipGenerator getToolTipGenerator(int row, int column) {
/*  371 */     return getSeriesToolTipGenerator(row);
/*      */   }
/*      */   
/*      */   public XYToolTipGenerator getSeriesToolTipGenerator(int series) {
/*  384 */     if (this.toolTipGenerator != null)
/*  385 */       return this.toolTipGenerator; 
/*  389 */     XYToolTipGenerator generator = (XYToolTipGenerator)this.toolTipGeneratorList.get(series);
/*  391 */     if (generator == null)
/*  392 */       generator = this.baseToolTipGenerator; 
/*  394 */     return generator;
/*      */   }
/*      */   
/*      */   public void setToolTipGenerator(XYToolTipGenerator generator) {
/*  405 */     this.toolTipGenerator = generator;
/*  406 */     notifyListeners(new RendererChangeEvent(this));
/*      */   }
/*      */   
/*      */   public void setSeriesToolTipGenerator(int series, XYToolTipGenerator generator) {
/*  418 */     this.toolTipGeneratorList.set(series, generator);
/*  419 */     notifyListeners(new RendererChangeEvent(this));
/*      */   }
/*      */   
/*      */   public XYToolTipGenerator getBaseToolTipGenerator() {
/*  428 */     return this.baseToolTipGenerator;
/*      */   }
/*      */   
/*      */   public void setBaseToolTipGenerator(XYToolTipGenerator generator) {
/*  438 */     this.baseToolTipGenerator = generator;
/*  439 */     notifyListeners(new RendererChangeEvent(this));
/*      */   }
/*      */   
/*      */   public XYURLGenerator getURLGenerator() {
/*  450 */     return this.urlGenerator;
/*      */   }
/*      */   
/*      */   public void setURLGenerator(XYURLGenerator urlGenerator) {
/*  459 */     this.urlGenerator = urlGenerator;
/*  460 */     notifyListeners(new RendererChangeEvent(this));
/*      */   }
/*      */   
/*      */   public void addAnnotation(XYAnnotation annotation) {
/*  472 */     addAnnotation(annotation, Layer.FOREGROUND);
/*      */   }
/*      */   
/*      */   public void addAnnotation(XYAnnotation annotation, Layer layer) {
/*  482 */     if (annotation == null)
/*  483 */       throw new IllegalArgumentException("Null 'annotation' argument."); 
/*  485 */     if (layer.equals(Layer.FOREGROUND)) {
/*  486 */       this.foregroundAnnotations.add(annotation);
/*  487 */       notifyListeners(new RendererChangeEvent(this));
/*  489 */     } else if (layer.equals(Layer.BACKGROUND)) {
/*  490 */       this.backgroundAnnotations.add(annotation);
/*  491 */       notifyListeners(new RendererChangeEvent(this));
/*      */     } else {
/*  495 */       throw new RuntimeException("Unknown layer.");
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean removeAnnotation(XYAnnotation annotation) {
/*  509 */     boolean removed = this.foregroundAnnotations.remove(annotation);
/*  510 */     removed &= this.backgroundAnnotations.remove(annotation);
/*  511 */     notifyListeners(new RendererChangeEvent(this));
/*  512 */     return removed;
/*      */   }
/*      */   
/*      */   public void removeAnnotations() {
/*  520 */     this.foregroundAnnotations.clear();
/*  521 */     this.backgroundAnnotations.clear();
/*  522 */     notifyListeners(new RendererChangeEvent(this));
/*      */   }
/*      */   
/*      */   public int getDefaultEntityRadius() {
/*  532 */     return this.defaultEntityRadius;
/*      */   }
/*      */   
/*      */   public void setDefaultEntityRadius(int radius) {
/*  542 */     this.defaultEntityRadius = radius;
/*      */   }
/*      */   
/*      */   public XYSeriesLabelGenerator getLegendItemLabelGenerator() {
/*  551 */     return this.legendItemLabelGenerator;
/*      */   }
/*      */   
/*      */   public void setLegendItemLabelGenerator(XYSeriesLabelGenerator generator) {
/*  560 */     if (generator == null)
/*  561 */       throw new IllegalArgumentException("Null 'generator' argument."); 
/*  563 */     this.legendItemLabelGenerator = generator;
/*      */   }
/*      */   
/*      */   public XYSeriesLabelGenerator getLegendItemToolTipGenerator() {
/*  572 */     return this.legendItemToolTipGenerator;
/*      */   }
/*      */   
/*      */   public void setLegendItemToolTipGenerator(XYSeriesLabelGenerator generator) {
/*  582 */     this.legendItemToolTipGenerator = generator;
/*      */   }
/*      */   
/*      */   public XYSeriesLabelGenerator getLegendItemURLGenerator() {
/*  591 */     return this.legendItemURLGenerator;
/*      */   }
/*      */   
/*      */   public void setLegendItemURLGenerator(XYSeriesLabelGenerator generator) {
/*  601 */     this.legendItemURLGenerator = generator;
/*      */   }
/*      */   
/*      */   public Range findDomainBounds(XYDataset dataset) {
/*  614 */     if (dataset != null)
/*  615 */       return DatasetUtilities.findDomainBounds(dataset, false); 
/*  618 */     return null;
/*      */   }
/*      */   
/*      */   public Range findRangeBounds(XYDataset dataset) {
/*  632 */     if (dataset != null)
/*  633 */       return DatasetUtilities.findRangeBounds(dataset, false); 
/*  636 */     return null;
/*      */   }
/*      */   
/*      */   public LegendItemCollection getLegendItems() {
/*  647 */     if (this.plot == null)
/*  648 */       return new LegendItemCollection(); 
/*  650 */     LegendItemCollection result = new LegendItemCollection();
/*  651 */     int index = this.plot.getIndexOf(this);
/*  652 */     XYDataset dataset = this.plot.getDataset(index);
/*  653 */     if (dataset != null) {
/*  654 */       int seriesCount = dataset.getSeriesCount();
/*  655 */       for (int i = 0; i < seriesCount; i++) {
/*  656 */         if (isSeriesVisibleInLegend(i)) {
/*  657 */           LegendItem item = getLegendItem(index, i);
/*  658 */           if (item != null)
/*  659 */             result.add(item); 
/*      */         } 
/*      */       } 
/*      */     } 
/*  665 */     return result;
/*      */   }
/*      */   
/*      */   public LegendItem getLegendItem(int datasetIndex, int series) {
/*  678 */     LegendItem result = null;
/*  679 */     XYPlot xyplot = getPlot();
/*  680 */     if (xyplot != null) {
/*  681 */       XYDataset dataset = xyplot.getDataset(datasetIndex);
/*  682 */       if (dataset != null) {
/*  683 */         String label = this.legendItemLabelGenerator.generateLabel(dataset, series);
/*  686 */         String description = label;
/*  687 */         String toolTipText = null;
/*  688 */         if (getLegendItemToolTipGenerator() != null)
/*  689 */           toolTipText = getLegendItemToolTipGenerator().generateLabel(dataset, series); 
/*  693 */         String urlText = null;
/*  694 */         if (getLegendItemURLGenerator() != null)
/*  695 */           urlText = getLegendItemURLGenerator().generateLabel(dataset, series); 
/*  699 */         Shape shape = getSeriesShape(series);
/*  700 */         Paint paint = getSeriesPaint(series);
/*  701 */         Paint outlinePaint = getSeriesOutlinePaint(series);
/*  702 */         Stroke outlineStroke = getSeriesOutlineStroke(series);
/*  703 */         result = new LegendItem(label, description, toolTipText, urlText, shape, paint, outlineStroke, outlinePaint);
/*      */       } 
/*      */     } 
/*  707 */     return result;
/*      */   }
/*      */   
/*      */   public void fillDomainGridBand(Graphics2D g2, XYPlot plot, ValueAxis axis, Rectangle2D dataArea, double start, double end) {
/*  727 */     double x1 = axis.valueToJava2D(start, dataArea, plot.getDomainAxisEdge());
/*  730 */     double x2 = axis.valueToJava2D(end, dataArea, plot.getDomainAxisEdge());
/*  735 */     Rectangle2D band = new Rectangle2D.Double(x1, dataArea.getMinY(), x2 - x1, dataArea.getMaxY() - dataArea.getMinY());
/*  739 */     Paint paint = plot.getDomainTickBandPaint();
/*  741 */     if (paint != null) {
/*  742 */       g2.setPaint(paint);
/*  743 */       g2.fill(band);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void fillRangeGridBand(Graphics2D g2, XYPlot plot, ValueAxis axis, Rectangle2D dataArea, double start, double end) {
/*  765 */     double y1 = axis.valueToJava2D(start, dataArea, plot.getRangeAxisEdge());
/*  768 */     double y2 = axis.valueToJava2D(end, dataArea, plot.getRangeAxisEdge());
/*  771 */     Rectangle2D band = new Rectangle2D.Double(dataArea.getMinX(), y2, dataArea.getWidth(), y1 - y2);
/*  774 */     Paint paint = plot.getRangeTickBandPaint();
/*  776 */     if (paint != null) {
/*  777 */       g2.setPaint(paint);
/*  778 */       g2.fill(band);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void drawDomainGridLine(Graphics2D g2, XYPlot plot, ValueAxis axis, Rectangle2D dataArea, double value) {
/*  799 */     Range range = axis.getRange();
/*  800 */     if (!range.contains(value))
/*      */       return; 
/*  804 */     PlotOrientation orientation = plot.getOrientation();
/*  805 */     double v = axis.valueToJava2D(value, dataArea, plot.getDomainAxisEdge());
/*  808 */     Line2D line = null;
/*  809 */     if (orientation == PlotOrientation.HORIZONTAL) {
/*  810 */       line = new Line2D.Double(dataArea.getMinX(), v, dataArea.getMaxX(), v);
/*  814 */     } else if (orientation == PlotOrientation.VERTICAL) {
/*  815 */       line = new Line2D.Double(v, dataArea.getMinY(), v, dataArea.getMaxY());
/*      */     } 
/*  820 */     Paint paint = plot.getDomainGridlinePaint();
/*  821 */     Stroke stroke = plot.getDomainGridlineStroke();
/*  822 */     g2.setPaint((paint != null) ? paint : Plot.DEFAULT_OUTLINE_PAINT);
/*  823 */     g2.setStroke((stroke != null) ? stroke : Plot.DEFAULT_OUTLINE_STROKE);
/*  824 */     g2.draw(line);
/*      */   }
/*      */   
/*      */   public void drawRangeLine(Graphics2D g2, XYPlot plot, ValueAxis axis, Rectangle2D dataArea, double value, Paint paint, Stroke stroke) {
/*  848 */     Range range = axis.getRange();
/*  849 */     if (!range.contains(value))
/*      */       return; 
/*  853 */     PlotOrientation orientation = plot.getOrientation();
/*  854 */     Line2D line = null;
/*  855 */     double v = axis.valueToJava2D(value, dataArea, plot.getRangeAxisEdge());
/*  856 */     if (orientation == PlotOrientation.HORIZONTAL) {
/*  857 */       line = new Line2D.Double(v, dataArea.getMinY(), v, dataArea.getMaxY());
/*  861 */     } else if (orientation == PlotOrientation.VERTICAL) {
/*  862 */       line = new Line2D.Double(dataArea.getMinX(), v, dataArea.getMaxX(), v);
/*      */     } 
/*  867 */     g2.setPaint(paint);
/*  868 */     g2.setStroke(stroke);
/*  869 */     g2.draw(line);
/*      */   }
/*      */   
/*      */   public void drawDomainMarker(Graphics2D g2, XYPlot plot, ValueAxis domainAxis, Marker marker, Rectangle2D dataArea) {
/*  888 */     if (marker instanceof ValueMarker) {
/*  889 */       ValueMarker vm = (ValueMarker)marker;
/*  890 */       double value = vm.getValue();
/*  891 */       Range range = domainAxis.getRange();
/*  892 */       if (!range.contains(value))
/*      */         return; 
/*  896 */       double v = domainAxis.valueToJava2D(value, dataArea, plot.getDomainAxisEdge());
/*  900 */       PlotOrientation orientation = plot.getOrientation();
/*  901 */       Line2D line = null;
/*  902 */       if (orientation == PlotOrientation.HORIZONTAL) {
/*  903 */         line = new Line2D.Double(dataArea.getMinX(), v, dataArea.getMaxX(), v);
/*  907 */       } else if (orientation == PlotOrientation.VERTICAL) {
/*  908 */         line = new Line2D.Double(v, dataArea.getMinY(), v, dataArea.getMaxY());
/*      */       } 
/*  913 */       g2.setPaint(marker.getPaint());
/*  914 */       g2.setStroke(marker.getStroke());
/*  915 */       g2.draw(line);
/*  917 */       String label = marker.getLabel();
/*  918 */       RectangleAnchor anchor = marker.getLabelAnchor();
/*  919 */       if (label != null) {
/*  920 */         Font labelFont = marker.getLabelFont();
/*  921 */         g2.setFont(labelFont);
/*  922 */         g2.setPaint(marker.getLabelPaint());
/*  923 */         Point2D coordinates = calculateDomainMarkerTextAnchorPoint(g2, orientation, dataArea, line.getBounds2D(), marker.getLabelOffset(), LengthAdjustmentType.EXPAND, anchor);
/*  928 */         TextUtilities.drawAlignedString(label, g2, (float)coordinates.getX(), (float)coordinates.getY(), marker.getLabelTextAnchor());
/*      */       } 
/*  935 */     } else if (marker instanceof IntervalMarker) {
/*  936 */       IntervalMarker im = (IntervalMarker)marker;
/*  937 */       double start = im.getStartValue();
/*  938 */       double end = im.getEndValue();
/*  939 */       Range range = domainAxis.getRange();
/*  940 */       if (!range.intersects(start, end))
/*      */         return; 
/*  945 */       start = range.constrain(start);
/*  946 */       end = range.constrain(end);
/*  948 */       double v0 = domainAxis.valueToJava2D(start, dataArea, plot.getDomainAxisEdge());
/*  951 */       double v1 = domainAxis.valueToJava2D(end, dataArea, plot.getDomainAxisEdge());
/*  955 */       PlotOrientation orientation = plot.getOrientation();
/*  956 */       Rectangle2D rect = null;
/*  957 */       if (orientation == PlotOrientation.HORIZONTAL) {
/*  958 */         rect = new Rectangle2D.Double(dataArea.getMinX(), Math.min(v0, v1), dataArea.getWidth(), Math.abs(v1 - v0));
/*  963 */       } else if (orientation == PlotOrientation.VERTICAL) {
/*  964 */         rect = new Rectangle2D.Double(Math.min(v0, v1), dataArea.getMinY(), Math.abs(v1 - v0), dataArea.getHeight());
/*      */       } 
/*  970 */       Paint p = marker.getPaint();
/*  971 */       if (p instanceof GradientPaint) {
/*  972 */         GradientPaint gp = (GradientPaint)p;
/*  973 */         GradientPaintTransformer t = im.getGradientPaintTransformer();
/*  974 */         if (t != null)
/*  975 */           gp = t.transform(gp, rect); 
/*  977 */         g2.setPaint(gp);
/*      */       } else {
/*  980 */         g2.setPaint(p);
/*      */       } 
/*  982 */       g2.fill(rect);
/*  984 */       String label = marker.getLabel();
/*  985 */       RectangleAnchor anchor = marker.getLabelAnchor();
/*  986 */       if (label != null) {
/*  987 */         Font labelFont = marker.getLabelFont();
/*  988 */         g2.setFont(labelFont);
/*  989 */         g2.setPaint(marker.getLabelPaint());
/*  990 */         Point2D coordinates = calculateDomainMarkerTextAnchorPoint(g2, orientation, dataArea, rect, marker.getLabelOffset(), marker.getLabelOffsetType(), anchor);
/*  994 */         TextUtilities.drawAlignedString(label, g2, (float)coordinates.getX(), (float)coordinates.getY(), marker.getLabelTextAnchor());
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   protected Point2D calculateDomainMarkerTextAnchorPoint(Graphics2D g2, PlotOrientation orientation, Rectangle2D dataArea, Rectangle2D markerArea, RectangleInsets markerOffset, LengthAdjustmentType labelOffsetType, RectangleAnchor anchor) {
/* 1026 */     Rectangle2D anchorRect = null;
/* 1027 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 1028 */       anchorRect = markerOffset.createAdjustedRectangle(markerArea, LengthAdjustmentType.CONTRACT, labelOffsetType);
/* 1032 */     } else if (orientation == PlotOrientation.VERTICAL) {
/* 1033 */       anchorRect = markerOffset.createAdjustedRectangle(markerArea, labelOffsetType, LengthAdjustmentType.CONTRACT);
/*      */     } 
/* 1037 */     return RectangleAnchor.coordinates(anchorRect, anchor);
/*      */   }
/*      */   
/*      */   public void drawRangeMarker(Graphics2D g2, XYPlot plot, ValueAxis rangeAxis, Marker marker, Rectangle2D dataArea) {
/* 1056 */     if (marker instanceof ValueMarker) {
/* 1057 */       ValueMarker vm = (ValueMarker)marker;
/* 1058 */       double value = vm.getValue();
/* 1059 */       Range range = rangeAxis.getRange();
/* 1060 */       if (!range.contains(value))
/*      */         return; 
/* 1064 */       double v = rangeAxis.valueToJava2D(value, dataArea, plot.getRangeAxisEdge());
/* 1067 */       PlotOrientation orientation = plot.getOrientation();
/* 1068 */       Line2D line = null;
/* 1069 */       if (orientation == PlotOrientation.HORIZONTAL) {
/* 1070 */         line = new Line2D.Double(v, dataArea.getMinY(), v, dataArea.getMaxY());
/* 1074 */       } else if (orientation == PlotOrientation.VERTICAL) {
/* 1075 */         line = new Line2D.Double(dataArea.getMinX(), v, dataArea.getMaxX(), v);
/*      */       } 
/* 1079 */       g2.setPaint(marker.getPaint());
/* 1080 */       g2.setStroke(marker.getStroke());
/* 1081 */       g2.draw(line);
/* 1083 */       String label = marker.getLabel();
/* 1084 */       RectangleAnchor anchor = marker.getLabelAnchor();
/* 1085 */       if (label != null) {
/* 1086 */         Font labelFont = marker.getLabelFont();
/* 1087 */         g2.setFont(labelFont);
/* 1088 */         g2.setPaint(marker.getLabelPaint());
/* 1089 */         Point2D coordinates = calculateRangeMarkerTextAnchorPoint(g2, orientation, dataArea, line.getBounds2D(), marker.getLabelOffset(), LengthAdjustmentType.EXPAND, anchor);
/* 1094 */         TextUtilities.drawAlignedString(label, g2, (float)coordinates.getX(), (float)coordinates.getY(), marker.getLabelTextAnchor());
/*      */       } 
/* 1101 */     } else if (marker instanceof IntervalMarker) {
/* 1103 */       IntervalMarker im = (IntervalMarker)marker;
/* 1104 */       double start = im.getStartValue();
/* 1105 */       double end = im.getEndValue();
/* 1106 */       Range range = rangeAxis.getRange();
/* 1107 */       if (!range.intersects(start, end))
/*      */         return; 
/* 1112 */       start = range.constrain(start);
/* 1113 */       end = range.constrain(end);
/* 1115 */       double v0 = rangeAxis.valueToJava2D(start, dataArea, plot.getRangeAxisEdge());
/* 1118 */       double v1 = rangeAxis.valueToJava2D(end, dataArea, plot.getRangeAxisEdge());
/* 1122 */       PlotOrientation orientation = plot.getOrientation();
/* 1123 */       Rectangle2D rect = null;
/* 1124 */       if (orientation == PlotOrientation.HORIZONTAL) {
/* 1125 */         rect = new Rectangle2D.Double(Math.min(v0, v1), dataArea.getMinY(), Math.abs(v1 - v0), dataArea.getHeight());
/* 1130 */       } else if (orientation == PlotOrientation.VERTICAL) {
/* 1131 */         rect = new Rectangle2D.Double(dataArea.getMinX(), Math.min(v0, v1), dataArea.getWidth(), Math.abs(v0 - v1));
/*      */       } 
/* 1137 */       Paint p = marker.getPaint();
/* 1138 */       if (p instanceof GradientPaint) {
/* 1139 */         GradientPaint gp = (GradientPaint)p;
/* 1140 */         GradientPaintTransformer t = im.getGradientPaintTransformer();
/* 1141 */         if (t != null)
/* 1142 */           gp = t.transform(gp, rect); 
/* 1144 */         g2.setPaint(gp);
/*      */       } else {
/* 1147 */         g2.setPaint(p);
/*      */       } 
/* 1149 */       g2.fill(rect);
/* 1150 */       String label = marker.getLabel();
/* 1151 */       RectangleAnchor anchor = marker.getLabelAnchor();
/* 1152 */       if (label != null) {
/* 1153 */         Font labelFont = marker.getLabelFont();
/* 1154 */         g2.setFont(labelFont);
/* 1155 */         g2.setPaint(marker.getLabelPaint());
/* 1156 */         Point2D coordinates = calculateRangeMarkerTextAnchorPoint(g2, orientation, dataArea, rect, marker.getLabelOffset(), marker.getLabelOffsetType(), anchor);
/* 1160 */         TextUtilities.drawAlignedString(label, g2, (float)coordinates.getX(), (float)coordinates.getY(), marker.getLabelTextAnchor());
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private Point2D calculateRangeMarkerTextAnchorPoint(Graphics2D g2, PlotOrientation orientation, Rectangle2D dataArea, Rectangle2D markerArea, RectangleInsets markerOffset, LengthAdjustmentType labelOffsetForRange, RectangleAnchor anchor) {
/* 1189 */     Rectangle2D anchorRect = null;
/* 1190 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 1191 */       anchorRect = markerOffset.createAdjustedRectangle(markerArea, labelOffsetForRange, LengthAdjustmentType.CONTRACT);
/* 1195 */     } else if (orientation == PlotOrientation.VERTICAL) {
/* 1196 */       anchorRect = markerOffset.createAdjustedRectangle(markerArea, LengthAdjustmentType.CONTRACT, labelOffsetForRange);
/*      */     } 
/* 1200 */     return RectangleAnchor.coordinates(anchorRect, anchor);
/*      */   }
/*      */   
/*      */   protected Object clone() throws CloneNotSupportedException {
/* 1213 */     AbstractXYItemRenderer clone = (AbstractXYItemRenderer)super.clone();
/* 1215 */     if (this.itemLabelGenerator != null && this.itemLabelGenerator instanceof PublicCloneable) {
/* 1217 */       PublicCloneable pc = (PublicCloneable)this.itemLabelGenerator;
/* 1218 */       clone.itemLabelGenerator = (XYItemLabelGenerator)pc.clone();
/*      */     } 
/* 1220 */     return clone;
/*      */   }
/*      */   
/*      */   public boolean equals(Object obj) {
/* 1232 */     if (obj == null)
/* 1233 */       return false; 
/* 1236 */     if (obj == this)
/* 1237 */       return true; 
/* 1240 */     if (!(obj instanceof AbstractXYItemRenderer))
/* 1241 */       return false; 
/* 1244 */     AbstractXYItemRenderer renderer = (AbstractXYItemRenderer)obj;
/* 1245 */     if (!super.equals(obj))
/* 1246 */       return false; 
/* 1248 */     if (!ObjectUtilities.equal(this.itemLabelGenerator, renderer.itemLabelGenerator))
/* 1251 */       return false; 
/* 1253 */     if (!ObjectUtilities.equal(this.urlGenerator, renderer.urlGenerator))
/* 1254 */       return false; 
/* 1256 */     return true;
/*      */   }
/*      */   
/*      */   public DrawingSupplier getDrawingSupplier() {
/* 1265 */     DrawingSupplier result = null;
/* 1266 */     XYPlot p = getPlot();
/* 1267 */     if (p != null)
/* 1268 */       result = p.getDrawingSupplier(); 
/* 1270 */     return result;
/*      */   }
/*      */   
/*      */   protected void updateCrosshairValues(CrosshairState crosshairState, double x, double y, double transX, double transY, PlotOrientation orientation) {
/* 1292 */     if (orientation == null)
/* 1293 */       throw new IllegalArgumentException("Null 'orientation' argument."); 
/* 1296 */     if (crosshairState != null)
/* 1298 */       if (this.plot.isDomainCrosshairLockedOnData()) {
/* 1299 */         if (this.plot.isRangeCrosshairLockedOnData()) {
/* 1301 */           crosshairState.updateCrosshairPoint(x, y, transX, transY, orientation);
/*      */         } else {
/* 1307 */           crosshairState.updateCrosshairX(x);
/*      */         } 
/* 1311 */       } else if (this.plot.isRangeCrosshairLockedOnData()) {
/* 1313 */         crosshairState.updateCrosshairY(y);
/*      */       }  
/*      */   }
/*      */   
/*      */   protected void drawItemLabel(Graphics2D g2, PlotOrientation orientation, XYDataset dataset, int series, int item, double x, double y, boolean negative) {
/* 1342 */     XYItemLabelGenerator generator = getItemLabelGenerator(series, item);
/* 1343 */     if (generator != null) {
/* 1344 */       Font labelFont = getItemLabelFont(series, item);
/* 1345 */       Paint paint = getItemLabelPaint(series, item);
/* 1346 */       g2.setFont(labelFont);
/* 1347 */       g2.setPaint(paint);
/* 1348 */       String label = generator.generateLabel(dataset, series, item);
/* 1351 */       ItemLabelPosition position = null;
/* 1352 */       if (!negative) {
/* 1353 */         position = getPositiveItemLabelPosition(series, item);
/*      */       } else {
/* 1356 */         position = getNegativeItemLabelPosition(series, item);
/*      */       } 
/* 1360 */       Point2D anchorPoint = calculateLabelAnchorPoint(position.getItemLabelAnchor(), x, y, orientation);
/* 1363 */       TextUtilities.drawRotatedString(label, g2, (float)anchorPoint.getX(), (float)anchorPoint.getY(), position.getTextAnchor(), position.getAngle(), position.getRotationAnchor());
/*      */     } 
/*      */   }
/*      */   
/*      */   public void drawAnnotations(Graphics2D g2, Rectangle2D dataArea, ValueAxis domainAxis, ValueAxis rangeAxis, Layer layer, PlotRenderingInfo info) {
/* 1390 */     Iterator iterator = null;
/* 1391 */     if (layer.equals(Layer.FOREGROUND)) {
/* 1392 */       iterator = this.foregroundAnnotations.iterator();
/* 1394 */     } else if (layer.equals(Layer.BACKGROUND)) {
/* 1395 */       iterator = this.backgroundAnnotations.iterator();
/*      */     } else {
/* 1399 */       throw new RuntimeException("Unknown layer.");
/*      */     } 
/* 1401 */     while (iterator.hasNext()) {
/* 1402 */       XYAnnotation annotation = iterator.next();
/* 1403 */       annotation.draw(g2, this.plot, dataArea, domainAxis, rangeAxis, 0, info);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void addEntity(EntityCollection entities, Shape area, XYDataset dataset, int series, int item, double entityX, double entityY) {
/* 1425 */     if (!getItemCreateEntity(series, item))
/*      */       return; 
/* 1428 */     if (area == null)
/* 1429 */       area = new Ellipse2D.Double(entityX - this.defaultEntityRadius, entityY - this.defaultEntityRadius, (this.defaultEntityRadius * 2), (this.defaultEntityRadius * 2)); 
/* 1435 */     String tip = null;
/* 1436 */     XYToolTipGenerator generator = getToolTipGenerator(series, item);
/* 1437 */     if (generator != null)
/* 1438 */       tip = generator.generateToolTip(dataset, series, item); 
/* 1440 */     String url = null;
/* 1441 */     if (getURLGenerator() != null)
/* 1442 */       url = getURLGenerator().generateURL(dataset, series, item); 
/* 1444 */     XYItemEntity entity = new XYItemEntity(area, dataset, series, item, tip, url);
/* 1447 */     entities.add((ChartEntity)entity);
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\renderer\xy\AbstractXYItemRenderer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */