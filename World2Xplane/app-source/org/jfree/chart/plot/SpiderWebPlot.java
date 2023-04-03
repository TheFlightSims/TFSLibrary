/*      */ package org.jfree.chart.plot;
/*      */ 
/*      */ import java.awt.AlphaComposite;
/*      */ import java.awt.BasicStroke;
/*      */ import java.awt.Color;
/*      */ import java.awt.Composite;
/*      */ import java.awt.Font;
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.Paint;
/*      */ import java.awt.Polygon;
/*      */ import java.awt.Shape;
/*      */ import java.awt.Stroke;
/*      */ import java.awt.font.FontRenderContext;
/*      */ import java.awt.font.LineMetrics;
/*      */ import java.awt.geom.Arc2D;
/*      */ import java.awt.geom.Ellipse2D;
/*      */ import java.awt.geom.Line2D;
/*      */ import java.awt.geom.Point2D;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import org.jfree.chart.LegendItem;
/*      */ import org.jfree.chart.LegendItemCollection;
/*      */ import org.jfree.chart.event.PlotChangeEvent;
/*      */ import org.jfree.chart.labels.CategoryItemLabelGenerator;
/*      */ import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
/*      */ import org.jfree.data.category.CategoryDataset;
/*      */ import org.jfree.data.general.Dataset;
/*      */ import org.jfree.data.general.DatasetChangeEvent;
/*      */ import org.jfree.data.general.DatasetUtilities;
/*      */ import org.jfree.io.SerialUtilities;
/*      */ import org.jfree.ui.RectangleInsets;
/*      */ import org.jfree.util.ObjectUtilities;
/*      */ import org.jfree.util.PaintList;
/*      */ import org.jfree.util.PaintUtilities;
/*      */ import org.jfree.util.Rotation;
/*      */ import org.jfree.util.ShapeUtilities;
/*      */ import org.jfree.util.StrokeList;
/*      */ import org.jfree.util.TableOrder;
/*      */ 
/*      */ public class SpiderWebPlot extends Plot implements Cloneable, Serializable {
/*      */   private static final long serialVersionUID = -5376340422031599463L;
/*      */   
/*      */   public static final double DEFAULT_HEAD = 0.01D;
/*      */   
/*      */   public static final double DEFAULT_AXIS_LABEL_GAP = 0.1D;
/*      */   
/*      */   public static final double DEFAULT_INTERIOR_GAP = 0.25D;
/*      */   
/*      */   public static final double MAX_INTERIOR_GAP = 0.4D;
/*      */   
/*      */   public static final double DEFAULT_START_ANGLE = 90.0D;
/*      */   
/*  126 */   public static final Font DEFAULT_LABEL_FONT = new Font("SansSerif", 0, 10);
/*      */   
/*  131 */   public static final Paint DEFAULT_LABEL_PAINT = Color.black;
/*      */   
/*  134 */   public static final Paint DEFAULT_LABEL_BACKGROUND_PAINT = new Color(255, 255, 192);
/*      */   
/*  138 */   public static final Paint DEFAULT_LABEL_OUTLINE_PAINT = Color.black;
/*      */   
/*  141 */   public static final Stroke DEFAULT_LABEL_OUTLINE_STROKE = new BasicStroke(0.5F);
/*      */   
/*  145 */   public static final Paint DEFAULT_LABEL_SHADOW_PAINT = Color.lightGray;
/*      */   
/*      */   public static final double DEFAULT_MAX_VALUE = -1.0D;
/*      */   
/*      */   protected double headPercent;
/*      */   
/*      */   private double interiorGap;
/*      */   
/*      */   private double axisLabelGap;
/*      */   
/*      */   private CategoryDataset dataset;
/*      */   
/*      */   private double maxValue;
/*      */   
/*      */   private TableOrder dataExtractOrder;
/*      */   
/*      */   private double startAngle;
/*      */   
/*      */   private Rotation direction;
/*      */   
/*      */   private transient Shape legendItemShape;
/*      */   
/*      */   private transient Paint seriesPaint;
/*      */   
/*      */   private PaintList seriesPaintList;
/*      */   
/*      */   private transient Paint baseSeriesPaint;
/*      */   
/*      */   private transient Paint seriesOutlinePaint;
/*      */   
/*      */   private PaintList seriesOutlinePaintList;
/*      */   
/*      */   private transient Paint baseSeriesOutlinePaint;
/*      */   
/*      */   private transient Stroke seriesOutlineStroke;
/*      */   
/*      */   private StrokeList seriesOutlineStrokeList;
/*      */   
/*      */   private transient Stroke baseSeriesOutlineStroke;
/*      */   
/*      */   private Font labelFont;
/*      */   
/*      */   private transient Paint labelPaint;
/*      */   
/*      */   private CategoryItemLabelGenerator labelGenerator;
/*      */   
/*      */   private boolean webFilled = true;
/*      */   
/*      */   public SpiderWebPlot() {
/*  228 */     this((CategoryDataset)null);
/*      */   }
/*      */   
/*      */   public SpiderWebPlot(CategoryDataset dataset) {
/*  238 */     this.dataset = dataset;
/*  239 */     if (dataset != null)
/*  240 */       dataset.addChangeListener(this); 
/*  243 */     this.dataExtractOrder = TableOrder.BY_ROW;
/*  244 */     this.headPercent = 0.01D;
/*  245 */     this.axisLabelGap = 0.1D;
/*  247 */     this.interiorGap = 0.25D;
/*  248 */     this.startAngle = 90.0D;
/*  249 */     this.direction = Rotation.CLOCKWISE;
/*  250 */     this.maxValue = -1.0D;
/*  252 */     this.seriesPaint = null;
/*  253 */     this.seriesPaintList = new PaintList();
/*  254 */     this.baseSeriesPaint = null;
/*  256 */     this.seriesOutlinePaint = null;
/*  257 */     this.seriesOutlinePaintList = new PaintList();
/*  258 */     this.baseSeriesOutlinePaint = DEFAULT_OUTLINE_PAINT;
/*  260 */     this.seriesOutlineStroke = null;
/*  261 */     this.seriesOutlineStrokeList = new StrokeList();
/*  262 */     this.baseSeriesOutlineStroke = DEFAULT_OUTLINE_STROKE;
/*  264 */     this.labelFont = DEFAULT_LABEL_FONT;
/*  265 */     this.labelPaint = DEFAULT_LABEL_PAINT;
/*  266 */     this.labelGenerator = (CategoryItemLabelGenerator)new StandardCategoryItemLabelGenerator();
/*  268 */     this.legendItemShape = DEFAULT_LEGEND_ITEM_CIRCLE;
/*      */   }
/*      */   
/*      */   public SpiderWebPlot(CategoryDataset data, TableOrder type) {
/*  278 */     this(data);
/*  279 */     this.dataExtractOrder = type;
/*      */   }
/*      */   
/*      */   public String getPlotType() {
/*  289 */     return "Radar Plot";
/*      */   }
/*      */   
/*      */   public CategoryDataset getDataset() {
/*  298 */     return this.dataset;
/*      */   }
/*      */   
/*      */   public void setDataset(CategoryDataset dataset) {
/*  310 */     if (this.dataset != null)
/*  311 */       this.dataset.removeChangeListener(this); 
/*  315 */     this.dataset = dataset;
/*  316 */     if (dataset != null) {
/*  317 */       setDatasetGroup(dataset.getGroup());
/*  318 */       dataset.addChangeListener(this);
/*      */     } 
/*  322 */     datasetChanged(new DatasetChangeEvent(this, (Dataset)dataset));
/*      */   }
/*      */   
/*      */   public boolean isWebFilled() {
/*  331 */     return this.webFilled;
/*      */   }
/*      */   
/*      */   public void setWebFilled(boolean flag) {
/*  341 */     this.webFilled = flag;
/*  342 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public TableOrder getDataExtractOrder() {
/*  351 */     return this.dataExtractOrder;
/*      */   }
/*      */   
/*      */   public void setDataExtractOrder(TableOrder order) {
/*  361 */     if (order == null)
/*  362 */       throw new IllegalArgumentException("Null 'order' argument"); 
/*  364 */     this.dataExtractOrder = order;
/*  365 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public double getHeadPercent() {
/*  374 */     return this.headPercent;
/*      */   }
/*      */   
/*      */   public void setHeadPercent(double percent) {
/*  384 */     this.headPercent = percent;
/*  385 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public double getStartAngle() {
/*  397 */     return this.startAngle;
/*      */   }
/*      */   
/*      */   public void setStartAngle(double angle) {
/*  411 */     this.startAngle = angle;
/*  412 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public double getMaxValue() {
/*  421 */     return this.maxValue;
/*      */   }
/*      */   
/*      */   public void setMaxValue(double value) {
/*  431 */     this.maxValue = value;
/*  432 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Rotation getDirection() {
/*  442 */     return this.direction;
/*      */   }
/*      */   
/*      */   public void setDirection(Rotation direction) {
/*  452 */     if (direction == null)
/*  453 */       throw new IllegalArgumentException("Null 'direction' argument."); 
/*  455 */     this.direction = direction;
/*  456 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public double getInteriorGap() {
/*  466 */     return this.interiorGap;
/*      */   }
/*      */   
/*      */   public void setInteriorGap(double percent) {
/*  477 */     if (percent < 0.0D || percent > 0.4D)
/*  478 */       throw new IllegalArgumentException("Percentage outside valid range."); 
/*  482 */     if (this.interiorGap != percent) {
/*  483 */       this.interiorGap = percent;
/*  484 */       notifyListeners(new PlotChangeEvent(this));
/*      */     } 
/*      */   }
/*      */   
/*      */   public double getAxisLabelGap() {
/*  494 */     return this.axisLabelGap;
/*      */   }
/*      */   
/*      */   public void setAxisLabelGap(double gap) {
/*  504 */     this.axisLabelGap = gap;
/*  505 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Paint getSeriesPaint() {
/*  516 */     return this.seriesPaint;
/*      */   }
/*      */   
/*      */   public void setSeriesPaint(Paint paint) {
/*  527 */     this.seriesPaint = paint;
/*  528 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Paint getSeriesPaint(int series) {
/*  541 */     if (this.seriesPaint != null)
/*  542 */       return this.seriesPaint; 
/*  546 */     Paint result = this.seriesPaintList.getPaint(series);
/*  547 */     if (result == null) {
/*  548 */       DrawingSupplier supplier = getDrawingSupplier();
/*  549 */       if (supplier != null) {
/*  550 */         Paint p = supplier.getNextPaint();
/*  551 */         this.seriesPaintList.setPaint(series, p);
/*  552 */         result = p;
/*      */       } else {
/*  555 */         result = this.baseSeriesPaint;
/*      */       } 
/*      */     } 
/*  558 */     return result;
/*      */   }
/*      */   
/*      */   public void setSeriesPaint(int series, Paint paint) {
/*  570 */     this.seriesPaintList.setPaint(series, paint);
/*  571 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Paint getBaseSeriesPaint() {
/*  581 */     return this.baseSeriesPaint;
/*      */   }
/*      */   
/*      */   public void setBaseSeriesPaint(Paint paint) {
/*  590 */     if (paint == null)
/*  591 */       throw new IllegalArgumentException("Null 'paint' argument."); 
/*  593 */     this.baseSeriesPaint = paint;
/*  594 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Paint getSeriesOutlinePaint() {
/*  605 */     return this.seriesOutlinePaint;
/*      */   }
/*      */   
/*      */   public void setSeriesOutlinePaint(Paint paint) {
/*  616 */     this.seriesOutlinePaint = paint;
/*  617 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Paint getSeriesOutlinePaint(int series) {
/*  629 */     if (this.seriesOutlinePaint != null)
/*  630 */       return this.seriesOutlinePaint; 
/*  633 */     Paint result = this.seriesOutlinePaintList.getPaint(series);
/*  634 */     if (result == null)
/*  635 */       result = this.baseSeriesOutlinePaint; 
/*  637 */     return result;
/*      */   }
/*      */   
/*      */   public void setSeriesOutlinePaint(int series, Paint paint) {
/*  648 */     this.seriesOutlinePaintList.setPaint(series, paint);
/*  649 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Paint getBaseSeriesOutlinePaint() {
/*  659 */     return this.baseSeriesOutlinePaint;
/*      */   }
/*      */   
/*      */   public void setBaseSeriesOutlinePaint(Paint paint) {
/*  668 */     if (paint == null)
/*  669 */       throw new IllegalArgumentException("Null 'paint' argument."); 
/*  671 */     this.baseSeriesOutlinePaint = paint;
/*  672 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Stroke getSeriesOutlineStroke() {
/*  683 */     return this.seriesOutlineStroke;
/*      */   }
/*      */   
/*      */   public void setSeriesOutlineStroke(Stroke stroke) {
/*  694 */     this.seriesOutlineStroke = stroke;
/*  695 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Stroke getSeriesOutlineStroke(int series) {
/*  708 */     if (this.seriesOutlineStroke != null)
/*  709 */       return this.seriesOutlineStroke; 
/*  713 */     Stroke result = this.seriesOutlineStrokeList.getStroke(series);
/*  714 */     if (result == null)
/*  715 */       result = this.baseSeriesOutlineStroke; 
/*  717 */     return result;
/*      */   }
/*      */   
/*      */   public void setSeriesOutlineStroke(int series, Stroke stroke) {
/*  729 */     this.seriesOutlineStrokeList.setStroke(series, stroke);
/*  730 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Stroke getBaseSeriesOutlineStroke() {
/*  740 */     return this.baseSeriesOutlineStroke;
/*      */   }
/*      */   
/*      */   public void setBaseSeriesOutlineStroke(Stroke stroke) {
/*  749 */     if (stroke == null)
/*  750 */       throw new IllegalArgumentException("Null 'stroke' argument."); 
/*  752 */     this.baseSeriesOutlineStroke = stroke;
/*  753 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Shape getLegendItemShape() {
/*  762 */     return this.legendItemShape;
/*      */   }
/*      */   
/*      */   public void setLegendItemShape(Shape shape) {
/*  771 */     if (shape == null)
/*  772 */       throw new IllegalArgumentException("Null 'shape' argument."); 
/*  774 */     this.legendItemShape = shape;
/*  775 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Font getLabelFont() {
/*  784 */     return this.labelFont;
/*      */   }
/*      */   
/*      */   public void setLabelFont(Font font) {
/*  794 */     if (font == null)
/*  795 */       throw new IllegalArgumentException("Null 'font' argument."); 
/*  797 */     this.labelFont = font;
/*  798 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Paint getLabelPaint() {
/*  807 */     return this.labelPaint;
/*      */   }
/*      */   
/*      */   public void setLabelPaint(Paint paint) {
/*  817 */     if (paint == null)
/*  818 */       throw new IllegalArgumentException("Null 'paint' argument."); 
/*  820 */     this.labelPaint = paint;
/*  821 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public CategoryItemLabelGenerator getLabelGenerator() {
/*  830 */     return this.labelGenerator;
/*      */   }
/*      */   
/*      */   public void setLabelGenerator(CategoryItemLabelGenerator generator) {
/*  840 */     if (generator == null)
/*  841 */       throw new IllegalArgumentException("Null 'generator' argument."); 
/*  843 */     this.labelGenerator = generator;
/*      */   }
/*      */   
/*      */   public LegendItemCollection getLegendItems() {
/*  852 */     LegendItemCollection result = new LegendItemCollection();
/*  854 */     List keys = null;
/*  856 */     if (this.dataExtractOrder == TableOrder.BY_ROW) {
/*  857 */       keys = this.dataset.getRowKeys();
/*  859 */     } else if (this.dataExtractOrder == TableOrder.BY_COLUMN) {
/*  860 */       keys = this.dataset.getColumnKeys();
/*      */     } 
/*  863 */     if (keys != null) {
/*  864 */       int series = 0;
/*  865 */       Iterator iterator = keys.iterator();
/*  866 */       Shape shape = getLegendItemShape();
/*  868 */       while (iterator.hasNext()) {
/*  869 */         String label = iterator.next().toString();
/*  870 */         String description = label;
/*  872 */         Paint paint = getSeriesPaint(series);
/*  873 */         Paint outlinePaint = getSeriesOutlinePaint(series);
/*  874 */         Stroke stroke = getSeriesOutlineStroke(series);
/*  875 */         LegendItem item = new LegendItem(label, description, null, null, shape, paint, stroke, outlinePaint);
/*  877 */         result.add(item);
/*  878 */         series++;
/*      */       } 
/*      */     } 
/*  882 */     return result;
/*      */   }
/*      */   
/*      */   protected Point2D getWebPoint(Rectangle2D bounds, double angle, double length) {
/*  897 */     double angrad = Math.toRadians(angle);
/*  898 */     double x = Math.cos(angrad) * length * bounds.getWidth() / 2.0D;
/*  899 */     double y = -Math.sin(angrad) * length * bounds.getHeight() / 2.0D;
/*  901 */     return new Point2D.Double(bounds.getX() + x + bounds.getWidth() / 2.0D, bounds.getY() + y + bounds.getHeight() / 2.0D);
/*      */   }
/*      */   
/*      */   public void draw(Graphics2D g2, Rectangle2D area, Point2D anchor, PlotState parentState, PlotRenderingInfo info) {
/*  922 */     RectangleInsets insets = getInsets();
/*  923 */     insets.trim(area);
/*  925 */     if (info != null) {
/*  926 */       info.setPlotArea(area);
/*  927 */       info.setDataArea(area);
/*      */     } 
/*  930 */     drawBackground(g2, area);
/*  931 */     drawOutline(g2, area);
/*  933 */     Shape savedClip = g2.getClip();
/*  935 */     g2.clip(area);
/*  936 */     Composite originalComposite = g2.getComposite();
/*  937 */     g2.setComposite(AlphaComposite.getInstance(3, getForegroundAlpha()));
/*  943 */     if (!DatasetUtilities.isEmptyOrNull(this.dataset)) {
/*  944 */       int seriesCount = 0, catCount = 0;
/*  946 */       if (this.dataExtractOrder == TableOrder.BY_ROW) {
/*  947 */         seriesCount = this.dataset.getRowCount();
/*  948 */         catCount = this.dataset.getColumnCount();
/*      */       } else {
/*  951 */         seriesCount = this.dataset.getColumnCount();
/*  952 */         catCount = this.dataset.getRowCount();
/*      */       } 
/*  956 */       if (this.maxValue == -1.0D)
/*  957 */         calculateMaxValue(seriesCount, catCount); 
/*  963 */       double gapHorizontal = area.getWidth() * getInteriorGap();
/*  964 */       double gapVertical = area.getHeight() * getInteriorGap();
/*  966 */       double X = area.getX() + gapHorizontal / 2.0D;
/*  967 */       double Y = area.getY() + gapVertical / 2.0D;
/*  968 */       double W = area.getWidth() - gapHorizontal;
/*  969 */       double H = area.getHeight() - gapVertical;
/*  971 */       double headW = area.getWidth() * this.headPercent;
/*  972 */       double headH = area.getHeight() * this.headPercent;
/*  975 */       double min = Math.min(W, H) / 2.0D;
/*  976 */       X = (X + X + W) / 2.0D - min;
/*  977 */       Y = (Y + Y + H) / 2.0D - min;
/*  978 */       W = 2.0D * min;
/*  979 */       H = 2.0D * min;
/*  981 */       Point2D centre = new Point2D.Double(X + W / 2.0D, Y + H / 2.0D);
/*  982 */       Rectangle2D radarArea = new Rectangle2D.Double(X, Y, W, H);
/*  986 */       for (int series = 0; series < seriesCount; series++)
/*  987 */         drawRadarPoly(g2, radarArea, centre, info, series, catCount, headH, headW); 
/*      */     } else {
/*  993 */       drawNoDataMessage(g2, area);
/*      */     } 
/*  995 */     g2.clip(savedClip);
/*  996 */     g2.setComposite(originalComposite);
/*  997 */     drawOutline(g2, area);
/*      */   }
/*      */   
/*      */   private void calculateMaxValue(int seriesCount, int catCount) {
/* 1008 */     double v = 0.0D;
/* 1009 */     Number nV = null;
/* 1011 */     for (int seriesIndex = 0; seriesIndex < seriesCount; seriesIndex++) {
/* 1012 */       for (int catIndex = 0; catIndex < catCount; catIndex++) {
/* 1013 */         nV = getPlotValue(seriesIndex, catIndex);
/* 1014 */         if (nV != null) {
/* 1015 */           v = nV.doubleValue();
/* 1016 */           if (v > this.maxValue)
/* 1017 */             this.maxValue = v; 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void drawRadarPoly(Graphics2D g2, Rectangle2D plotArea, Point2D centre, PlotRenderingInfo info, int series, int catCount, double headH, double headW) {
/* 1043 */     Polygon polygon = new Polygon();
/* 1046 */     for (int cat = 0; cat < catCount; cat++) {
/* 1047 */       Number dataValue = getPlotValue(series, cat);
/* 1049 */       if (dataValue != null) {
/* 1050 */         double value = dataValue.doubleValue();
/* 1052 */         if (value > 0.0D) {
/* 1056 */           double angle = getStartAngle() + getDirection().getFactor() * cat * 360.0D / catCount;
/* 1070 */           Point2D point = getWebPoint(plotArea, angle, value / this.maxValue);
/* 1073 */           polygon.addPoint((int)point.getX(), (int)point.getY());
/* 1079 */           Paint paint1 = getSeriesPaint(series);
/* 1080 */           Paint outlinePaint = getSeriesOutlinePaint(series);
/* 1081 */           Stroke outlineStroke = getSeriesOutlineStroke(series);
/* 1083 */           Ellipse2D head = new Ellipse2D.Double(point.getX() - headW / 2.0D, point.getY() - headH / 2.0D, headW, headH);
/* 1088 */           g2.setPaint(paint1);
/* 1089 */           g2.fill(head);
/* 1090 */           g2.setStroke(outlineStroke);
/* 1091 */           g2.setPaint(outlinePaint);
/* 1092 */           g2.draw(head);
/* 1097 */           if (series == 0) {
/* 1098 */             Point2D endPoint = getWebPoint(plotArea, angle, 1.0D);
/* 1100 */             Line2D line = new Line2D.Double(centre, endPoint);
/* 1101 */             g2.draw(line);
/* 1102 */             drawLabel(g2, plotArea, value, cat, angle, 360.0D / catCount);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/* 1111 */     Paint paint = getSeriesPaint(series);
/* 1112 */     g2.setPaint(paint);
/* 1113 */     g2.draw(polygon);
/* 1117 */     if (this.webFilled) {
/* 1118 */       g2.setComposite(AlphaComposite.getInstance(3, 0.1F));
/* 1121 */       g2.fill(polygon);
/* 1122 */       g2.setComposite(AlphaComposite.getInstance(3, getForegroundAlpha()));
/*      */     } 
/*      */   }
/*      */   
/*      */   Number getPlotValue(int series, int cat) {
/* 1142 */     Number value = null;
/* 1143 */     if (this.dataExtractOrder == TableOrder.BY_ROW) {
/* 1144 */       value = this.dataset.getValue(series, cat);
/* 1146 */     } else if (this.dataExtractOrder == TableOrder.BY_COLUMN) {
/* 1147 */       value = this.dataset.getValue(cat, series);
/*      */     } 
/* 1149 */     return value;
/*      */   }
/*      */   
/*      */   protected void drawLabel(Graphics2D g2, Rectangle2D plotArea, double value, int cat, double startAngle, double extent) {
/* 1164 */     FontRenderContext frc = g2.getFontRenderContext();
/* 1166 */     String label = null;
/* 1167 */     if (this.dataExtractOrder == TableOrder.BY_ROW) {
/* 1169 */       label = this.labelGenerator.generateColumnLabel(this.dataset, cat);
/*      */     } else {
/* 1173 */       label = this.labelGenerator.generateRowLabel(this.dataset, cat);
/*      */     } 
/* 1176 */     Rectangle2D labelBounds = getLabelFont().getStringBounds(label, frc);
/* 1177 */     LineMetrics lm = getLabelFont().getLineMetrics(label, frc);
/* 1178 */     double ascent = lm.getAscent();
/* 1180 */     Point2D labelLocation = calculateLabelLocation(labelBounds, ascent, plotArea, startAngle);
/* 1184 */     Composite saveComposite = g2.getComposite();
/* 1186 */     g2.setComposite(AlphaComposite.getInstance(3, 1.0F));
/* 1189 */     g2.setPaint(getLabelPaint());
/* 1190 */     g2.setFont(getLabelFont());
/* 1191 */     g2.drawString(label, (float)labelLocation.getX(), (float)labelLocation.getY());
/* 1194 */     g2.setComposite(saveComposite);
/*      */   }
/*      */   
/*      */   protected Point2D calculateLabelLocation(Rectangle2D labelBounds, double ascent, Rectangle2D plotArea, double startAngle) {
/* 1212 */     Arc2D arc1 = new Arc2D.Double(plotArea, startAngle, 0.0D, 0);
/* 1213 */     Point2D point1 = arc1.getEndPoint();
/* 1215 */     double deltaX = -(point1.getX() - plotArea.getCenterX()) * this.axisLabelGap;
/* 1217 */     double deltaY = -(point1.getY() - plotArea.getCenterY()) * this.axisLabelGap;
/* 1220 */     double labelX = point1.getX() - deltaX;
/* 1221 */     double labelY = point1.getY() - deltaY;
/* 1223 */     if (labelX < plotArea.getCenterX())
/* 1224 */       labelX -= labelBounds.getWidth(); 
/* 1227 */     if (labelX == plotArea.getCenterX())
/* 1228 */       labelX -= labelBounds.getWidth() / 2.0D; 
/* 1231 */     if (labelY > plotArea.getCenterY())
/* 1232 */       labelY += ascent; 
/* 1235 */     return new Point2D.Double(labelX, labelY);
/*      */   }
/*      */   
/*      */   public boolean equals(Object obj) {
/* 1246 */     if (obj == this)
/* 1247 */       return true; 
/* 1249 */     if (!(obj instanceof SpiderWebPlot))
/* 1250 */       return false; 
/* 1252 */     if (!super.equals(obj))
/* 1253 */       return false; 
/* 1255 */     SpiderWebPlot that = (SpiderWebPlot)obj;
/* 1256 */     if (!this.dataExtractOrder.equals(that.dataExtractOrder))
/* 1257 */       return false; 
/* 1259 */     if (this.headPercent != that.headPercent)
/* 1260 */       return false; 
/* 1262 */     if (this.interiorGap != that.interiorGap)
/* 1263 */       return false; 
/* 1265 */     if (this.startAngle != that.startAngle)
/* 1266 */       return false; 
/* 1268 */     if (!this.direction.equals(that.direction))
/* 1269 */       return false; 
/* 1271 */     if (this.maxValue != that.maxValue)
/* 1272 */       return false; 
/* 1274 */     if (this.webFilled != that.webFilled)
/* 1275 */       return false; 
/* 1277 */     if (!ShapeUtilities.equal(this.legendItemShape, that.legendItemShape))
/* 1278 */       return false; 
/* 1280 */     if (!PaintUtilities.equal(this.seriesPaint, that.seriesPaint))
/* 1281 */       return false; 
/* 1283 */     if (!this.seriesPaintList.equals(that.seriesPaintList))
/* 1284 */       return false; 
/* 1286 */     if (!PaintUtilities.equal(this.baseSeriesPaint, that.baseSeriesPaint))
/* 1287 */       return false; 
/* 1289 */     if (!PaintUtilities.equal(this.seriesOutlinePaint, that.seriesOutlinePaint))
/* 1291 */       return false; 
/* 1293 */     if (!this.seriesOutlinePaintList.equals(that.seriesOutlinePaintList))
/* 1294 */       return false; 
/* 1296 */     if (!PaintUtilities.equal(this.baseSeriesOutlinePaint, that.baseSeriesOutlinePaint))
/* 1298 */       return false; 
/* 1300 */     if (!ObjectUtilities.equal(this.seriesOutlineStroke, that.seriesOutlineStroke))
/* 1302 */       return false; 
/* 1304 */     if (!this.seriesOutlineStrokeList.equals(that.seriesOutlineStrokeList))
/* 1306 */       return false; 
/* 1308 */     if (!this.baseSeriesOutlineStroke.equals(that.baseSeriesOutlineStroke))
/* 1310 */       return false; 
/* 1312 */     if (!this.labelFont.equals(that.labelFont))
/* 1313 */       return false; 
/* 1315 */     if (!PaintUtilities.equal(this.labelPaint, that.labelPaint))
/* 1316 */       return false; 
/* 1318 */     if (!this.labelGenerator.equals(that.labelGenerator))
/* 1319 */       return false; 
/* 1321 */     return true;
/*      */   }
/*      */   
/*      */   private void writeObject(ObjectOutputStream stream) throws IOException {
/* 1334 */     stream.defaultWriteObject();
/* 1336 */     SerialUtilities.writeShape(this.legendItemShape, stream);
/* 1337 */     SerialUtilities.writePaint(this.seriesPaint, stream);
/* 1338 */     SerialUtilities.writePaint(this.baseSeriesPaint, stream);
/* 1339 */     SerialUtilities.writePaint(this.seriesOutlinePaint, stream);
/* 1340 */     SerialUtilities.writePaint(this.baseSeriesOutlinePaint, stream);
/* 1341 */     SerialUtilities.writeStroke(this.seriesOutlineStroke, stream);
/* 1342 */     SerialUtilities.writeStroke(this.baseSeriesOutlineStroke, stream);
/* 1343 */     SerialUtilities.writePaint(this.labelPaint, stream);
/*      */   }
/*      */   
/*      */   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
/* 1359 */     stream.defaultReadObject();
/* 1361 */     this.legendItemShape = SerialUtilities.readShape(stream);
/* 1362 */     this.seriesPaint = SerialUtilities.readPaint(stream);
/* 1363 */     this.baseSeriesPaint = SerialUtilities.readPaint(stream);
/* 1364 */     this.seriesOutlinePaint = SerialUtilities.readPaint(stream);
/* 1365 */     this.baseSeriesOutlinePaint = SerialUtilities.readPaint(stream);
/* 1366 */     this.seriesOutlineStroke = SerialUtilities.readStroke(stream);
/* 1367 */     this.baseSeriesOutlineStroke = SerialUtilities.readStroke(stream);
/* 1368 */     this.labelPaint = SerialUtilities.readPaint(stream);
/* 1370 */     if (this.dataset != null)
/* 1371 */       this.dataset.addChangeListener(this); 
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\plot\SpiderWebPlot.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */