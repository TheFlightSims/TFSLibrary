/*      */ package org.jfree.chart.plot;
/*      */ 
/*      */ import java.awt.AlphaComposite;
/*      */ import java.awt.BasicStroke;
/*      */ import java.awt.Color;
/*      */ import java.awt.Composite;
/*      */ import java.awt.Font;
/*      */ import java.awt.FontMetrics;
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.Paint;
/*      */ import java.awt.Polygon;
/*      */ import java.awt.Shape;
/*      */ import java.awt.Stroke;
/*      */ import java.awt.geom.Arc2D;
/*      */ import java.awt.geom.Ellipse2D;
/*      */ import java.awt.geom.Line2D;
/*      */ import java.awt.geom.Point2D;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import java.text.NumberFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collections;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.ResourceBundle;
/*      */ import org.jfree.chart.LegendItem;
/*      */ import org.jfree.chart.LegendItemCollection;
/*      */ import org.jfree.chart.event.PlotChangeEvent;
/*      */ import org.jfree.data.Range;
/*      */ import org.jfree.data.general.Dataset;
/*      */ import org.jfree.data.general.DatasetChangeEvent;
/*      */ import org.jfree.data.general.ValueDataset;
/*      */ import org.jfree.io.SerialUtilities;
/*      */ import org.jfree.text.TextUtilities;
/*      */ import org.jfree.ui.RectangleInsets;
/*      */ import org.jfree.ui.TextAnchor;
/*      */ import org.jfree.util.ObjectUtilities;
/*      */ import org.jfree.util.PaintUtilities;
/*      */ 
/*      */ public class MeterPlot extends Plot implements Serializable, Cloneable {
/*      */   private static final long serialVersionUID = 2987472457734470962L;
/*      */   
/*  135 */   static final Paint DEFAULT_DIAL_BACKGROUND_PAINT = Color.black;
/*      */   
/*  138 */   static final Paint DEFAULT_NEEDLE_PAINT = Color.green;
/*      */   
/*  141 */   static final Font DEFAULT_VALUE_FONT = new Font("SansSerif", 1, 12);
/*      */   
/*  144 */   static final Paint DEFAULT_VALUE_PAINT = Color.yellow;
/*      */   
/*      */   public static final int DEFAULT_METER_ANGLE = 270;
/*      */   
/*      */   public static final float DEFAULT_BORDER_SIZE = 3.0F;
/*      */   
/*      */   public static final float DEFAULT_CIRCLE_SIZE = 10.0F;
/*      */   
/*  156 */   public static final Font DEFAULT_LABEL_FONT = new Font("SansSerif", 1, 10);
/*      */   
/*      */   private ValueDataset dataset;
/*      */   
/*      */   private DialShape shape;
/*      */   
/*      */   private int meterAngle;
/*      */   
/*      */   private Range range;
/*      */   
/*      */   private double tickSize;
/*      */   
/*      */   private Paint tickPaint;
/*      */   
/*      */   private String units;
/*      */   
/*      */   private Font valueFont;
/*      */   
/*      */   private transient Paint valuePaint;
/*      */   
/*      */   private boolean drawBorder;
/*      */   
/*      */   private transient Paint dialOutlinePaint;
/*      */   
/*      */   private transient Paint dialBackgroundPaint;
/*      */   
/*      */   private transient Paint needlePaint;
/*      */   
/*      */   private boolean tickLabelsVisible;
/*      */   
/*      */   private Font tickLabelFont;
/*      */   
/*      */   private Paint tickLabelPaint;
/*      */   
/*      */   private NumberFormat tickLabelFormat;
/*      */   
/*  211 */   protected static ResourceBundle localizationResources = ResourceBundle.getBundle("org.jfree.chart.plot.LocalizationBundle");
/*      */   
/*      */   private List intervals;
/*      */   
/*      */   public MeterPlot() {
/*  225 */     this((ValueDataset)null);
/*      */   }
/*      */   
/*      */   public MeterPlot(ValueDataset dataset) {
/*  235 */     this.shape = DialShape.CIRCLE;
/*  236 */     this.meterAngle = 270;
/*  237 */     this.range = new Range(0.0D, 100.0D);
/*  238 */     this.tickSize = 10.0D;
/*  239 */     this.tickPaint = Color.white;
/*  240 */     this.units = "Units";
/*  241 */     this.needlePaint = DEFAULT_NEEDLE_PAINT;
/*  242 */     this.tickLabelsVisible = true;
/*  243 */     this.tickLabelFont = DEFAULT_LABEL_FONT;
/*  244 */     this.tickLabelPaint = Color.black;
/*  245 */     this.tickLabelFormat = NumberFormat.getInstance();
/*  246 */     this.valueFont = DEFAULT_VALUE_FONT;
/*  247 */     this.valuePaint = DEFAULT_VALUE_PAINT;
/*  248 */     this.dialBackgroundPaint = DEFAULT_DIAL_BACKGROUND_PAINT;
/*  249 */     this.intervals = new ArrayList();
/*  250 */     setDataset(dataset);
/*      */   }
/*      */   
/*      */   public DialShape getDialShape() {
/*  259 */     return this.shape;
/*      */   }
/*      */   
/*      */   public void setDialShape(DialShape shape) {
/*  269 */     if (shape == null)
/*  270 */       throw new IllegalArgumentException("Null 'shape' argument."); 
/*  272 */     this.shape = shape;
/*  273 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public int getMeterAngle() {
/*  283 */     return this.meterAngle;
/*      */   }
/*      */   
/*      */   public void setMeterAngle(int angle) {
/*  293 */     if (angle < 1 || angle > 360)
/*  294 */       throw new IllegalArgumentException("Invalid 'angle' (" + angle + ")"); 
/*  298 */     this.meterAngle = angle;
/*  299 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Range getRange() {
/*  308 */     return this.range;
/*      */   }
/*      */   
/*      */   public void setRange(Range range) {
/*  319 */     if (range == null)
/*  320 */       throw new IllegalArgumentException("Null 'range' argument."); 
/*  322 */     if (range.getLength() <= 0.0D)
/*  323 */       throw new IllegalArgumentException("Range length must be positive."); 
/*  327 */     this.range = range;
/*  328 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public double getTickSize() {
/*  337 */     return this.tickSize;
/*      */   }
/*      */   
/*      */   public void setTickSize(double size) {
/*  347 */     if (size <= 0.0D)
/*  348 */       throw new IllegalArgumentException("Requires 'size' > 0."); 
/*  350 */     this.tickSize = size;
/*  351 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Paint getTickPaint() {
/*  361 */     return this.tickPaint;
/*      */   }
/*      */   
/*      */   public void setTickPaint(Paint paint) {
/*  370 */     if (paint == null)
/*  371 */       throw new IllegalArgumentException("Null 'paint' argument."); 
/*  373 */     this.tickPaint = paint;
/*  374 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public String getUnits() {
/*  383 */     return this.units;
/*      */   }
/*      */   
/*      */   public void setUnits(String units) {
/*  393 */     this.units = units;
/*  394 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Paint getNeedlePaint() {
/*  403 */     return this.needlePaint;
/*      */   }
/*      */   
/*      */   public void setNeedlePaint(Paint paint) {
/*  413 */     if (paint == null)
/*  414 */       throw new IllegalArgumentException("Null 'paint' argument."); 
/*  416 */     this.needlePaint = paint;
/*  417 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public boolean getTickLabelsVisible() {
/*  426 */     return this.tickLabelsVisible;
/*      */   }
/*      */   
/*      */   public void setTickLabelsVisible(boolean visible) {
/*  436 */     if (this.tickLabelsVisible != visible) {
/*  437 */       this.tickLabelsVisible = visible;
/*  438 */       notifyListeners(new PlotChangeEvent(this));
/*      */     } 
/*      */   }
/*      */   
/*      */   public Font getTickLabelFont() {
/*  448 */     return this.tickLabelFont;
/*      */   }
/*      */   
/*      */   public void setTickLabelFont(Font font) {
/*  458 */     if (font == null)
/*  459 */       throw new IllegalArgumentException("Null 'font' argument."); 
/*  461 */     if (!this.tickLabelFont.equals(font)) {
/*  462 */       this.tickLabelFont = font;
/*  463 */       notifyListeners(new PlotChangeEvent(this));
/*      */     } 
/*      */   }
/*      */   
/*      */   public Paint getTickLabelPaint() {
/*  473 */     return this.tickLabelPaint;
/*      */   }
/*      */   
/*      */   public void setTickLabelPaint(Paint paint) {
/*  483 */     if (paint == null)
/*  484 */       throw new IllegalArgumentException("Null 'paint' argument."); 
/*  486 */     if (!this.tickLabelPaint.equals(paint)) {
/*  487 */       this.tickLabelPaint = paint;
/*  488 */       notifyListeners(new PlotChangeEvent(this));
/*      */     } 
/*      */   }
/*      */   
/*      */   public NumberFormat getTickLabelFormat() {
/*  498 */     return this.tickLabelFormat;
/*      */   }
/*      */   
/*      */   public void setTickLabelFormat(NumberFormat format) {
/*  508 */     if (format == null)
/*  509 */       throw new IllegalArgumentException("Null 'format' argument."); 
/*  511 */     this.tickLabelFormat = format;
/*  512 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Font getValueFont() {
/*  521 */     return this.valueFont;
/*      */   }
/*      */   
/*      */   public void setValueFont(Font font) {
/*  531 */     if (font == null)
/*  532 */       throw new IllegalArgumentException("Null 'font' argument."); 
/*  534 */     this.valueFont = font;
/*  535 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Paint getValuePaint() {
/*  544 */     return this.valuePaint;
/*      */   }
/*      */   
/*      */   public void setValuePaint(Paint paint) {
/*  554 */     if (paint == null)
/*  555 */       throw new IllegalArgumentException("Null 'paint' argument."); 
/*  557 */     this.valuePaint = paint;
/*  558 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Paint getDialBackgroundPaint() {
/*  567 */     return this.dialBackgroundPaint;
/*      */   }
/*      */   
/*      */   public void setDialBackgroundPaint(Paint paint) {
/*  577 */     this.dialBackgroundPaint = paint;
/*  578 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public boolean getDrawBorder() {
/*  588 */     return this.drawBorder;
/*      */   }
/*      */   
/*      */   public void setDrawBorder(boolean draw) {
/*  600 */     this.drawBorder = draw;
/*  601 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Paint getDialOutlinePaint() {
/*  610 */     return this.dialOutlinePaint;
/*      */   }
/*      */   
/*      */   public void setDialOutlinePaint(Paint paint) {
/*  620 */     this.dialOutlinePaint = paint;
/*  621 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public ValueDataset getDataset() {
/*  630 */     return this.dataset;
/*      */   }
/*      */   
/*      */   public void setDataset(ValueDataset dataset) {
/*  643 */     ValueDataset existing = this.dataset;
/*  644 */     if (existing != null)
/*  645 */       existing.removeChangeListener(this); 
/*  649 */     this.dataset = dataset;
/*  650 */     if (dataset != null) {
/*  651 */       setDatasetGroup(dataset.getGroup());
/*  652 */       dataset.addChangeListener(this);
/*      */     } 
/*  656 */     DatasetChangeEvent event = new DatasetChangeEvent(this, (Dataset)dataset);
/*  657 */     datasetChanged(event);
/*      */   }
/*      */   
/*      */   public List getIntervals() {
/*  667 */     return Collections.unmodifiableList(this.intervals);
/*      */   }
/*      */   
/*      */   public void addInterval(MeterInterval interval) {
/*  677 */     if (interval == null)
/*  678 */       throw new IllegalArgumentException("Null 'interval' argument."); 
/*  680 */     this.intervals.add(interval);
/*  681 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public void clearIntervals() {
/*  689 */     this.intervals.clear();
/*  690 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public LegendItemCollection getLegendItems() {
/*  699 */     LegendItemCollection result = new LegendItemCollection();
/*  700 */     Iterator iterator = this.intervals.iterator();
/*  701 */     while (iterator.hasNext()) {
/*  702 */       MeterInterval mi = iterator.next();
/*  703 */       Paint color = mi.getBackgroundPaint();
/*  704 */       if (color == null)
/*  705 */         color = mi.getOutlinePaint(); 
/*  707 */       LegendItem item = new LegendItem(mi.getLabel(), mi.getLabel(), null, null, new Rectangle2D.Double(-4.0D, -4.0D, 8.0D, 8.0D), color);
/*  710 */       result.add(item);
/*      */     } 
/*  712 */     return result;
/*      */   }
/*      */   
/*      */   public void draw(Graphics2D g2, Rectangle2D area, Point2D anchor, PlotState parentState, PlotRenderingInfo info) {
/*  729 */     if (info != null)
/*  730 */       info.setPlotArea(area); 
/*  734 */     RectangleInsets insets = getInsets();
/*  735 */     insets.trim(area);
/*  737 */     area.setRect(area.getX() + 4.0D, area.getY() + 4.0D, area.getWidth() - 8.0D, area.getHeight() - 8.0D);
/*  743 */     if (this.drawBorder)
/*  744 */       drawBackground(g2, area); 
/*  748 */     double gapHorizontal = 6.0D;
/*  749 */     double gapVertical = 6.0D;
/*  750 */     double meterX = area.getX() + gapHorizontal / 2.0D;
/*  751 */     double meterY = area.getY() + gapVertical / 2.0D;
/*  752 */     double meterW = area.getWidth() - gapHorizontal;
/*  753 */     double meterH = area.getHeight() - gapVertical + ((this.meterAngle <= 180 && this.shape != DialShape.CIRCLE) ? (area.getHeight() / 1.25D) : 0.0D);
/*  757 */     double min = Math.min(meterW, meterH) / 2.0D;
/*  758 */     meterX = (meterX + meterX + meterW) / 2.0D - min;
/*  759 */     meterY = (meterY + meterY + meterH) / 2.0D - min;
/*  760 */     meterW = 2.0D * min;
/*  761 */     meterH = 2.0D * min;
/*  763 */     Rectangle2D meterArea = new Rectangle2D.Double(meterX, meterY, meterW, meterH);
/*  767 */     Rectangle2D.Double originalArea = new Rectangle2D.Double(meterArea.getX() - 4.0D, meterArea.getY() - 4.0D, meterArea.getWidth() + 8.0D, meterArea.getHeight() + 8.0D);
/*  772 */     double meterMiddleX = meterArea.getCenterX();
/*  773 */     double meterMiddleY = meterArea.getCenterY();
/*  776 */     ValueDataset data = getDataset();
/*  777 */     if (data != null) {
/*  778 */       double dataMin = this.range.getLowerBound();
/*  779 */       double dataMax = this.range.getUpperBound();
/*  781 */       Shape savedClip = g2.getClip();
/*  782 */       g2.clip(originalArea);
/*  783 */       Composite originalComposite = g2.getComposite();
/*  784 */       g2.setComposite(AlphaComposite.getInstance(3, getForegroundAlpha()));
/*  788 */       if (this.dialBackgroundPaint != null)
/*  789 */         fillArc(g2, originalArea, dataMin, dataMax, this.dialBackgroundPaint, true); 
/*  794 */       drawTicks(g2, meterArea, dataMin, dataMax);
/*  795 */       drawArcForInterval(g2, meterArea, new MeterInterval("", this.range, this.dialOutlinePaint, new BasicStroke(1.0F), null));
/*  803 */       Iterator iterator = this.intervals.iterator();
/*  804 */       while (iterator.hasNext()) {
/*  805 */         MeterInterval interval = iterator.next();
/*  806 */         drawArcForInterval(g2, meterArea, interval);
/*      */       } 
/*  809 */       Number n = data.getValue();
/*  810 */       if (n != null) {
/*  811 */         double value = n.doubleValue();
/*  812 */         drawValueLabel(g2, meterArea);
/*  814 */         if (this.range.contains(value)) {
/*  815 */           g2.setPaint(this.needlePaint);
/*  816 */           g2.setStroke(new BasicStroke(2.0F));
/*  818 */           double radius = meterArea.getWidth() / 2.0D + 3.0D + 15.0D;
/*  820 */           double valueAngle = valueToAngle(value);
/*  821 */           double valueP1 = meterMiddleX + radius * Math.cos(Math.PI * valueAngle / 180.0D);
/*  823 */           double valueP2 = meterMiddleY - radius * Math.sin(Math.PI * valueAngle / 180.0D);
/*  826 */           Polygon arrow = new Polygon();
/*  827 */           if ((valueAngle > 135.0D && valueAngle < 225.0D) || (valueAngle < 45.0D && valueAngle > -45.0D)) {
/*  830 */             double valueP3 = meterMiddleY - 2.5D;
/*  832 */             double valueP4 = meterMiddleY + 2.5D;
/*  834 */             arrow.addPoint((int)meterMiddleX, (int)valueP3);
/*  835 */             arrow.addPoint((int)meterMiddleX, (int)valueP4);
/*      */           } else {
/*  839 */             arrow.addPoint((int)(meterMiddleX - 2.5D), (int)meterMiddleY);
/*  843 */             arrow.addPoint((int)(meterMiddleX + 2.5D), (int)meterMiddleY);
/*      */           } 
/*  848 */           arrow.addPoint((int)valueP1, (int)valueP2);
/*  849 */           g2.fill(arrow);
/*  851 */           Ellipse2D circle = new Ellipse2D.Double(meterMiddleX - 5.0D, meterMiddleY - 5.0D, 10.0D, 10.0D);
/*  856 */           g2.fill(circle);
/*      */         } 
/*      */       } 
/*  861 */       g2.clip(savedClip);
/*  862 */       g2.setComposite(originalComposite);
/*      */     } 
/*  865 */     if (this.drawBorder)
/*  866 */       drawOutline(g2, area); 
/*      */   }
/*      */   
/*      */   protected void drawArcForInterval(Graphics2D g2, Rectangle2D meterArea, MeterInterval interval) {
/*  881 */     double minValue = interval.getRange().getLowerBound();
/*  882 */     double maxValue = interval.getRange().getUpperBound();
/*  883 */     Paint outlinePaint = interval.getOutlinePaint();
/*  884 */     Stroke outlineStroke = interval.getOutlineStroke();
/*  885 */     Paint backgroundPaint = interval.getBackgroundPaint();
/*  887 */     if (backgroundPaint != null)
/*  888 */       fillArc(g2, meterArea, minValue, maxValue, backgroundPaint, false); 
/*  890 */     if (outlinePaint != null) {
/*  891 */       if (outlineStroke != null)
/*  892 */         drawArc(g2, meterArea, minValue, maxValue, outlinePaint, outlineStroke); 
/*  897 */       drawTick(g2, meterArea, minValue, true);
/*  898 */       drawTick(g2, meterArea, maxValue, true);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void drawArc(Graphics2D g2, Rectangle2D area, double minValue, double maxValue, Paint paint, Stroke stroke) {
/*  915 */     double startAngle = valueToAngle(maxValue);
/*  916 */     double endAngle = valueToAngle(minValue);
/*  917 */     double extent = endAngle - startAngle;
/*  919 */     double x = area.getX();
/*  920 */     double y = area.getY();
/*  921 */     double w = area.getWidth();
/*  922 */     double h = area.getHeight();
/*  923 */     g2.setPaint(paint);
/*  924 */     g2.setStroke(stroke);
/*  926 */     if (paint != null && stroke != null) {
/*  927 */       Arc2D.Double arc = new Arc2D.Double(x, y, w, h, startAngle, extent, 0);
/*  930 */       g2.setPaint(paint);
/*  931 */       g2.setStroke(stroke);
/*  932 */       g2.draw(arc);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void fillArc(Graphics2D g2, Rectangle2D area, double minValue, double maxValue, Paint paint, boolean dial) {
/*  951 */     if (paint == null)
/*  952 */       throw new IllegalArgumentException("Null 'paint' argument"); 
/*  954 */     double startAngle = valueToAngle(maxValue);
/*  955 */     double endAngle = valueToAngle(minValue);
/*  956 */     double extent = endAngle - startAngle;
/*  958 */     double x = area.getX();
/*  959 */     double y = area.getY();
/*  960 */     double w = area.getWidth();
/*  961 */     double h = area.getHeight();
/*  962 */     int joinType = 0;
/*  963 */     if (this.shape == DialShape.PIE) {
/*  964 */       joinType = 2;
/*  966 */     } else if (this.shape == DialShape.CHORD) {
/*  967 */       if (dial && this.meterAngle > 180) {
/*  968 */         joinType = 1;
/*      */       } else {
/*  971 */         joinType = 2;
/*      */       } 
/*  974 */     } else if (this.shape == DialShape.CIRCLE) {
/*  975 */       joinType = 2;
/*  976 */       if (dial)
/*  977 */         extent = 360.0D; 
/*      */     } else {
/*  981 */       throw new IllegalStateException("DialShape not recognised.");
/*      */     } 
/*  984 */     g2.setPaint(paint);
/*  985 */     Arc2D.Double arc = new Arc2D.Double(x, y, w, h, startAngle, extent, joinType);
/*  988 */     g2.fill(arc);
/*      */   }
/*      */   
/*      */   public double valueToAngle(double value) {
/*  999 */     value -= this.range.getLowerBound();
/* 1000 */     double baseAngle = (180 + (this.meterAngle - 180) / 2);
/* 1001 */     return baseAngle - value / this.range.getLength() * this.meterAngle;
/*      */   }
/*      */   
/*      */   protected void drawTicks(Graphics2D g2, Rectangle2D meterArea, double minValue, double maxValue) {
/*      */     double v;
/* 1014 */     for (v = minValue; v <= maxValue; v += this.tickSize)
/* 1015 */       drawTick(g2, meterArea, v); 
/*      */   }
/*      */   
/*      */   protected void drawTick(Graphics2D g2, Rectangle2D meterArea, double value) {
/* 1028 */     drawTick(g2, meterArea, value, false);
/*      */   }
/*      */   
/*      */   protected void drawTick(Graphics2D g2, Rectangle2D meterArea, double value, boolean label) {
/* 1042 */     double valueAngle = valueToAngle(value);
/* 1044 */     double meterMiddleX = meterArea.getCenterX();
/* 1045 */     double meterMiddleY = meterArea.getCenterY();
/* 1047 */     g2.setPaint(this.tickPaint);
/* 1048 */     g2.setStroke(new BasicStroke(2.0F));
/* 1050 */     double valueP2X = 0.0D;
/* 1051 */     double valueP2Y = 0.0D;
/* 1053 */     double radius = meterArea.getWidth() / 2.0D + 3.0D;
/* 1054 */     double radius1 = radius - 15.0D;
/* 1056 */     double valueP1X = meterMiddleX + radius * Math.cos(Math.PI * valueAngle / 180.0D);
/* 1058 */     double valueP1Y = meterMiddleY - radius * Math.sin(Math.PI * valueAngle / 180.0D);
/* 1061 */     valueP2X = meterMiddleX + radius1 * Math.cos(Math.PI * valueAngle / 180.0D);
/* 1063 */     valueP2Y = meterMiddleY - radius1 * Math.sin(Math.PI * valueAngle / 180.0D);
/* 1066 */     Line2D.Double line = new Line2D.Double(valueP1X, valueP1Y, valueP2X, valueP2Y);
/* 1068 */     g2.draw(line);
/* 1070 */     if (this.tickLabelsVisible && label) {
/* 1072 */       String tickLabel = this.tickLabelFormat.format(value);
/* 1073 */       g2.setFont(this.tickLabelFont);
/* 1074 */       g2.setPaint(this.tickLabelPaint);
/* 1076 */       FontMetrics fm = g2.getFontMetrics();
/* 1077 */       Rectangle2D tickLabelBounds = TextUtilities.getTextBounds(tickLabel, g2, fm);
/* 1080 */       double x = valueP2X;
/* 1081 */       double y = valueP2Y;
/* 1082 */       if (valueAngle == 90.0D || valueAngle == 270.0D) {
/* 1083 */         x -= tickLabelBounds.getWidth() / 2.0D;
/* 1085 */       } else if (valueAngle < 90.0D || valueAngle > 270.0D) {
/* 1086 */         x -= tickLabelBounds.getWidth();
/*      */       } 
/* 1088 */       if ((valueAngle > 135.0D && valueAngle < 225.0D) || valueAngle > 315.0D || valueAngle < 45.0D) {
/* 1090 */         y -= tickLabelBounds.getHeight() / 2.0D;
/*      */       } else {
/* 1093 */         y += tickLabelBounds.getHeight() / 2.0D;
/*      */       } 
/* 1095 */       g2.drawString(tickLabel, (float)x, (float)y);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void drawValueLabel(Graphics2D g2, Rectangle2D area) {
/* 1106 */     g2.setFont(this.valueFont);
/* 1107 */     g2.setPaint(this.valuePaint);
/* 1108 */     String valueStr = "No value";
/* 1109 */     if (this.dataset != null) {
/* 1110 */       Number n = this.dataset.getValue();
/* 1111 */       if (n != null)
/* 1112 */         valueStr = this.tickLabelFormat.format(n.doubleValue()) + " " + this.units; 
/*      */     } 
/* 1116 */     float x = (float)area.getCenterX();
/* 1117 */     float y = (float)area.getCenterY() + 10.0F;
/* 1118 */     TextUtilities.drawAlignedString(valueStr, g2, x, y, TextAnchor.TOP_CENTER);
/*      */   }
/*      */   
/*      */   public String getPlotType() {
/* 1128 */     return localizationResources.getString("Meter_Plot");
/*      */   }
/*      */   
/*      */   public void zoom(double percent) {}
/*      */   
/*      */   public boolean equals(Object obj) {
/* 1151 */     if (obj == this)
/* 1152 */       return true; 
/* 1154 */     if (!(obj instanceof MeterPlot))
/* 1155 */       return false; 
/* 1157 */     if (!super.equals(obj))
/* 1158 */       return false; 
/* 1160 */     MeterPlot that = (MeterPlot)obj;
/* 1161 */     if (!ObjectUtilities.equal(this.units, that.units))
/* 1162 */       return false; 
/* 1164 */     if (!ObjectUtilities.equal(this.range, that.range))
/* 1165 */       return false; 
/* 1167 */     if (!ObjectUtilities.equal(this.intervals, that.intervals))
/* 1168 */       return false; 
/* 1170 */     if (!PaintUtilities.equal(this.dialOutlinePaint, that.dialOutlinePaint))
/* 1172 */       return false; 
/* 1174 */     if (this.shape != that.shape)
/* 1175 */       return false; 
/* 1177 */     if (!PaintUtilities.equal(this.dialBackgroundPaint, that.dialBackgroundPaint))
/* 1179 */       return false; 
/* 1181 */     if (!PaintUtilities.equal(this.needlePaint, that.needlePaint))
/* 1182 */       return false; 
/* 1184 */     if (!ObjectUtilities.equal(this.valueFont, that.valueFont))
/* 1185 */       return false; 
/* 1187 */     if (!PaintUtilities.equal(this.valuePaint, that.valuePaint))
/* 1188 */       return false; 
/* 1190 */     if (!PaintUtilities.equal(this.tickPaint, that.tickPaint))
/* 1191 */       return false; 
/* 1193 */     if (this.tickSize != that.tickSize)
/* 1194 */       return false; 
/* 1196 */     if (this.tickLabelsVisible != that.tickLabelsVisible)
/* 1197 */       return false; 
/* 1199 */     if (!ObjectUtilities.equal(this.tickLabelFont, that.tickLabelFont))
/* 1200 */       return false; 
/* 1202 */     if (!PaintUtilities.equal(this.tickLabelPaint, that.tickLabelPaint))
/* 1203 */       return false; 
/* 1205 */     if (!ObjectUtilities.equal(this.tickLabelFormat, that.tickLabelFormat))
/* 1207 */       return false; 
/* 1209 */     if (this.drawBorder != that.drawBorder)
/* 1210 */       return false; 
/* 1212 */     if (this.meterAngle != that.meterAngle)
/* 1213 */       return false; 
/* 1215 */     return true;
/*      */   }
/*      */   
/*      */   private void writeObject(ObjectOutputStream stream) throws IOException {
/* 1226 */     stream.defaultWriteObject();
/* 1227 */     SerialUtilities.writePaint(this.dialBackgroundPaint, stream);
/* 1228 */     SerialUtilities.writePaint(this.needlePaint, stream);
/* 1229 */     SerialUtilities.writePaint(this.valuePaint, stream);
/*      */   }
/*      */   
/*      */   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
/* 1242 */     stream.defaultReadObject();
/* 1243 */     this.dialBackgroundPaint = SerialUtilities.readPaint(stream);
/* 1244 */     this.needlePaint = SerialUtilities.readPaint(stream);
/* 1245 */     this.valuePaint = SerialUtilities.readPaint(stream);
/* 1246 */     if (this.dataset != null)
/* 1247 */       this.dataset.addChangeListener(this); 
/*      */   }
/*      */   
/*      */   public Object clone() throws CloneNotSupportedException {
/* 1262 */     MeterPlot clone = (MeterPlot)super.clone();
/* 1263 */     clone.tickLabelFormat = (NumberFormat)this.tickLabelFormat.clone();
/* 1265 */     clone.intervals = new ArrayList(this.intervals);
/* 1266 */     if (clone.dataset != null)
/* 1267 */       clone.dataset.addChangeListener(clone); 
/* 1269 */     return clone;
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\plot\MeterPlot.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */