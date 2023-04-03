/*      */ package org.jfree.chart.plot;
/*      */ 
/*      */ import java.awt.BasicStroke;
/*      */ import java.awt.Color;
/*      */ import java.awt.Font;
/*      */ import java.awt.FontMetrics;
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.Paint;
/*      */ import java.awt.Stroke;
/*      */ import java.awt.geom.Area;
/*      */ import java.awt.geom.Ellipse2D;
/*      */ import java.awt.geom.Line2D;
/*      */ import java.awt.geom.Point2D;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.awt.geom.RoundRectangle2D;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import java.text.DecimalFormat;
/*      */ import java.text.NumberFormat;
/*      */ import java.util.Arrays;
/*      */ import java.util.ResourceBundle;
/*      */ import org.jfree.chart.LegendItemCollection;
/*      */ import org.jfree.chart.axis.NumberAxis;
/*      */ import org.jfree.chart.axis.ValueAxis;
/*      */ import org.jfree.chart.event.PlotChangeEvent;
/*      */ import org.jfree.data.Range;
/*      */ import org.jfree.data.general.Dataset;
/*      */ import org.jfree.data.general.DatasetChangeEvent;
/*      */ import org.jfree.data.general.DefaultValueDataset;
/*      */ import org.jfree.data.general.ValueDataset;
/*      */ import org.jfree.io.SerialUtilities;
/*      */ import org.jfree.ui.RectangleEdge;
/*      */ import org.jfree.ui.RectangleInsets;
/*      */ import org.jfree.util.ObjectUtilities;
/*      */ import org.jfree.util.PaintUtilities;
/*      */ import org.jfree.util.UnitType;
/*      */ 
/*      */ public class ThermometerPlot extends Plot implements ValueAxisPlot, Zoomable, Cloneable, Serializable {
/*      */   private static final long serialVersionUID = 4087093313147984390L;
/*      */   
/*      */   public static final int UNITS_NONE = 0;
/*      */   
/*      */   public static final int UNITS_FAHRENHEIT = 1;
/*      */   
/*      */   public static final int UNITS_CELCIUS = 2;
/*      */   
/*      */   public static final int UNITS_KELVIN = 3;
/*      */   
/*      */   public static final int NONE = 0;
/*      */   
/*      */   public static final int RIGHT = 1;
/*      */   
/*      */   public static final int LEFT = 2;
/*      */   
/*      */   public static final int BULB = 3;
/*      */   
/*      */   public static final int NORMAL = 0;
/*      */   
/*      */   public static final int WARNING = 1;
/*      */   
/*      */   public static final int CRITICAL = 2;
/*      */   
/*      */   protected static final int BULB_RADIUS = 40;
/*      */   
/*      */   protected static final int BULB_DIAMETER = 80;
/*      */   
/*      */   protected static final int COLUMN_RADIUS = 20;
/*      */   
/*      */   protected static final int COLUMN_DIAMETER = 40;
/*      */   
/*      */   protected static final int GAP_RADIUS = 5;
/*      */   
/*      */   protected static final int GAP_DIAMETER = 10;
/*      */   
/*      */   protected static final int AXIS_GAP = 10;
/*      */   
/*  200 */   protected static final String[] UNITS = new String[] { "", "°F", "°C", "°K" };
/*      */   
/*      */   protected static final int RANGE_LOW = 0;
/*      */   
/*      */   protected static final int RANGE_HIGH = 1;
/*      */   
/*      */   protected static final int DISPLAY_LOW = 2;
/*      */   
/*      */   protected static final int DISPLAY_HIGH = 3;
/*      */   
/*      */   protected static final double DEFAULT_LOWER_BOUND = 0.0D;
/*      */   
/*      */   protected static final double DEFAULT_UPPER_BOUND = 100.0D;
/*      */   
/*      */   private ValueDataset dataset;
/*      */   
/*      */   private ValueAxis rangeAxis;
/*      */   
/*  228 */   private double lowerBound = 0.0D;
/*      */   
/*  231 */   private double upperBound = 100.0D;
/*      */   
/*      */   private RectangleInsets padding;
/*      */   
/*  239 */   private transient Stroke thermometerStroke = new BasicStroke(1.0F);
/*      */   
/*  242 */   private transient Paint thermometerPaint = Color.black;
/*      */   
/*  245 */   private int units = 2;
/*      */   
/*  248 */   private int valueLocation = 3;
/*      */   
/*  251 */   private int axisLocation = 2;
/*      */   
/*  254 */   private Font valueFont = new Font("SansSerif", 1, 16);
/*      */   
/*  257 */   private transient Paint valuePaint = Color.white;
/*      */   
/*  260 */   private NumberFormat valueFormat = new DecimalFormat();
/*      */   
/*  263 */   private transient Paint mercuryPaint = Color.lightGray;
/*      */   
/*      */   private boolean showValueLines = false;
/*      */   
/*  269 */   private int subrange = -1;
/*      */   
/*  272 */   private double[][] subrangeInfo = new double[][] { { 0.0D, 50.0D, 0.0D, 50.0D }, { 50.0D, 75.0D, 50.0D, 75.0D }, { 75.0D, 100.0D, 75.0D, 100.0D } };
/*      */   
/*      */   private boolean followDataInSubranges = false;
/*      */   
/*      */   private boolean useSubrangePaint = true;
/*      */   
/*  291 */   private Paint[] subrangePaint = new Paint[] { Color.green, Color.orange, Color.red };
/*      */   
/*      */   private boolean subrangeIndicatorsVisible = true;
/*      */   
/*  301 */   private transient Stroke subrangeIndicatorStroke = new BasicStroke(2.0F);
/*      */   
/*  304 */   private transient Stroke rangeIndicatorStroke = new BasicStroke(3.0F);
/*      */   
/*  307 */   protected static ResourceBundle localizationResources = ResourceBundle.getBundle("org.jfree.chart.plot.LocalizationBundle");
/*      */   
/*      */   public ThermometerPlot() {
/*  314 */     this((ValueDataset)new DefaultValueDataset());
/*      */   }
/*      */   
/*      */   public ThermometerPlot(ValueDataset dataset) {
/*  326 */     this.padding = new RectangleInsets(UnitType.RELATIVE, 0.05D, 0.05D, 0.05D, 0.05D);
/*  329 */     this.dataset = dataset;
/*  330 */     if (dataset != null)
/*  331 */       dataset.addChangeListener(this); 
/*  333 */     NumberAxis axis = new NumberAxis(null);
/*  334 */     axis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
/*  335 */     axis.setAxisLineVisible(false);
/*  337 */     setRangeAxis((ValueAxis)axis);
/*  338 */     setAxisRange();
/*      */   }
/*      */   
/*      */   public ValueDataset getDataset() {
/*  347 */     return this.dataset;
/*      */   }
/*      */   
/*      */   public void setDataset(ValueDataset dataset) {
/*  360 */     ValueDataset existing = this.dataset;
/*  361 */     if (existing != null)
/*  362 */       existing.removeChangeListener(this); 
/*  366 */     this.dataset = dataset;
/*  367 */     if (dataset != null) {
/*  368 */       setDatasetGroup(dataset.getGroup());
/*  369 */       dataset.addChangeListener(this);
/*      */     } 
/*  373 */     DatasetChangeEvent event = new DatasetChangeEvent(this, (Dataset)dataset);
/*  374 */     datasetChanged(event);
/*      */   }
/*      */   
/*      */   public ValueAxis getRangeAxis() {
/*  384 */     return this.rangeAxis;
/*      */   }
/*      */   
/*      */   public void setRangeAxis(ValueAxis axis) {
/*  394 */     if (axis != null) {
/*  395 */       axis.setPlot(this);
/*  396 */       axis.addChangeListener(this);
/*      */     } 
/*  400 */     if (this.rangeAxis != null)
/*  401 */       this.rangeAxis.removeChangeListener(this); 
/*  404 */     this.rangeAxis = axis;
/*      */   }
/*      */   
/*      */   public double getLowerBound() {
/*  416 */     return this.lowerBound;
/*      */   }
/*      */   
/*      */   public void setLowerBound(double lower) {
/*  425 */     this.lowerBound = lower;
/*  426 */     setAxisRange();
/*      */   }
/*      */   
/*      */   public double getUpperBound() {
/*  436 */     return this.upperBound;
/*      */   }
/*      */   
/*      */   public void setUpperBound(double upper) {
/*  445 */     this.upperBound = upper;
/*  446 */     setAxisRange();
/*      */   }
/*      */   
/*      */   public void setRange(double lower, double upper) {
/*  456 */     this.lowerBound = lower;
/*  457 */     this.upperBound = upper;
/*  458 */     setAxisRange();
/*      */   }
/*      */   
/*      */   public RectangleInsets getPadding() {
/*  468 */     return this.padding;
/*      */   }
/*      */   
/*      */   public void setPadding(RectangleInsets padding) {
/*  477 */     this.padding = padding;
/*  478 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Stroke getThermometerStroke() {
/*  487 */     return this.thermometerStroke;
/*      */   }
/*      */   
/*      */   public void setThermometerStroke(Stroke s) {
/*  496 */     if (s != null) {
/*  497 */       this.thermometerStroke = s;
/*  498 */       notifyListeners(new PlotChangeEvent(this));
/*      */     } 
/*      */   }
/*      */   
/*      */   public Paint getThermometerPaint() {
/*  508 */     return this.thermometerPaint;
/*      */   }
/*      */   
/*      */   public void setThermometerPaint(Paint paint) {
/*  517 */     if (paint != null) {
/*  518 */       this.thermometerPaint = paint;
/*  519 */       notifyListeners(new PlotChangeEvent(this));
/*      */     } 
/*      */   }
/*      */   
/*      */   public int getUnits() {
/*  529 */     return this.units;
/*      */   }
/*      */   
/*      */   public void setUnits(int u) {
/*  547 */     if (u >= 0 && u < UNITS.length && 
/*  548 */       this.units != u) {
/*  549 */       this.units = u;
/*  550 */       notifyListeners(new PlotChangeEvent(this));
/*      */     } 
/*      */   }
/*      */   
/*      */   public void setUnits(String u) {
/*  561 */     if (u == null)
/*      */       return; 
/*  565 */     u = u.toUpperCase().trim();
/*  566 */     for (int i = 0; i < UNITS.length; i++) {
/*  567 */       if (u.equals(UNITS[i].toUpperCase().trim())) {
/*  568 */         setUnits(i);
/*  569 */         i = UNITS.length;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public int getValueLocation() {
/*  580 */     return this.valueLocation;
/*      */   }
/*      */   
/*      */   public void setValueLocation(int location) {
/*  595 */     if (location >= 0 && location < 4) {
/*  596 */       this.valueLocation = location;
/*  597 */       notifyListeners(new PlotChangeEvent(this));
/*      */     } else {
/*  600 */       throw new IllegalArgumentException("Location not recognised.");
/*      */     } 
/*      */   }
/*      */   
/*      */   public void setAxisLocation(int location) {
/*  616 */     if (location >= 0 && location < 3) {
/*  617 */       this.axisLocation = location;
/*  618 */       notifyListeners(new PlotChangeEvent(this));
/*      */     } else {
/*  621 */       throw new IllegalArgumentException("Location not recognised.");
/*      */     } 
/*      */   }
/*      */   
/*      */   public int getAxisLocation() {
/*  631 */     return this.axisLocation;
/*      */   }
/*      */   
/*      */   public Font getValueFont() {
/*  640 */     return this.valueFont;
/*      */   }
/*      */   
/*      */   public void setValueFont(Font f) {
/*  649 */     if (f != null && !this.valueFont.equals(f)) {
/*  650 */       this.valueFont = f;
/*  651 */       notifyListeners(new PlotChangeEvent(this));
/*      */     } 
/*      */   }
/*      */   
/*      */   public Paint getValuePaint() {
/*  661 */     return this.valuePaint;
/*      */   }
/*      */   
/*      */   public void setValuePaint(Paint p) {
/*  670 */     if (p != null && !this.valuePaint.equals(p)) {
/*  671 */       this.valuePaint = p;
/*  672 */       notifyListeners(new PlotChangeEvent(this));
/*      */     } 
/*      */   }
/*      */   
/*      */   public void setValueFormat(NumberFormat formatter) {
/*  682 */     if (formatter != null) {
/*  683 */       this.valueFormat = formatter;
/*  684 */       notifyListeners(new PlotChangeEvent(this));
/*      */     } 
/*      */   }
/*      */   
/*      */   public Paint getMercuryPaint() {
/*  694 */     return this.mercuryPaint;
/*      */   }
/*      */   
/*      */   public void setMercuryPaint(Paint paint) {
/*  703 */     this.mercuryPaint = paint;
/*  704 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public boolean getShowValueLines() {
/*  713 */     return this.showValueLines;
/*      */   }
/*      */   
/*      */   public void setShowValueLines(boolean b) {
/*  722 */     this.showValueLines = b;
/*  723 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public void setSubrangeInfo(int range, double low, double hi) {
/*  734 */     setSubrangeInfo(range, low, hi, low, hi);
/*      */   }
/*      */   
/*      */   public void setSubrangeInfo(int range, double rangeLow, double rangeHigh, double displayLow, double displayHigh) {
/*  750 */     if (range >= 0 && range < 3) {
/*  751 */       setSubrange(range, rangeLow, rangeHigh);
/*  752 */       setDisplayRange(range, displayLow, displayHigh);
/*  753 */       setAxisRange();
/*  754 */       notifyListeners(new PlotChangeEvent(this));
/*      */     } 
/*      */   }
/*      */   
/*      */   public void setSubrange(int range, double low, double high) {
/*  767 */     if (range >= 0 && range < 3) {
/*  768 */       this.subrangeInfo[range][1] = high;
/*  769 */       this.subrangeInfo[range][0] = low;
/*      */     } 
/*      */   }
/*      */   
/*      */   public void setDisplayRange(int range, double low, double high) {
/*  782 */     if (range >= 0 && range < this.subrangeInfo.length && isValidNumber(high) && isValidNumber(low))
/*  785 */       if (high > low) {
/*  786 */         this.subrangeInfo[range][3] = high;
/*  787 */         this.subrangeInfo[range][2] = low;
/*      */       } else {
/*  790 */         this.subrangeInfo[range][3] = low;
/*  791 */         this.subrangeInfo[range][2] = high;
/*      */       }  
/*      */   }
/*      */   
/*      */   public Paint getSubrangePaint(int range) {
/*  806 */     if (range >= 0 && range < this.subrangePaint.length)
/*  807 */       return this.subrangePaint[range]; 
/*  810 */     return this.mercuryPaint;
/*      */   }
/*      */   
/*      */   public void setSubrangePaint(int range, Paint paint) {
/*  821 */     if (range >= 0 && range < this.subrangePaint.length && paint != null) {
/*  823 */       this.subrangePaint[range] = paint;
/*  824 */       notifyListeners(new PlotChangeEvent(this));
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean getFollowDataInSubranges() {
/*  835 */     return this.followDataInSubranges;
/*      */   }
/*      */   
/*      */   public void setFollowDataInSubranges(boolean flag) {
/*  845 */     this.followDataInSubranges = flag;
/*  846 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public boolean getUseSubrangePaint() {
/*  856 */     return this.useSubrangePaint;
/*      */   }
/*      */   
/*      */   public void setUseSubrangePaint(boolean flag) {
/*  865 */     this.useSubrangePaint = flag;
/*  866 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */   public void draw(Graphics2D g2, Rectangle2D area, Point2D anchor, PlotState parentState, PlotRenderingInfo info) {
/*  883 */     RoundRectangle2D outerStem = new RoundRectangle2D.Double();
/*  884 */     RoundRectangle2D innerStem = new RoundRectangle2D.Double();
/*  885 */     RoundRectangle2D mercuryStem = new RoundRectangle2D.Double();
/*  886 */     Ellipse2D outerBulb = new Ellipse2D.Double();
/*  887 */     Ellipse2D innerBulb = new Ellipse2D.Double();
/*  888 */     String temp = null;
/*  889 */     FontMetrics metrics = null;
/*  890 */     if (info != null)
/*  891 */       info.setPlotArea(area); 
/*  895 */     RectangleInsets insets = getInsets();
/*  896 */     insets.trim(area);
/*  897 */     drawBackground(g2, area);
/*  901 */     int midX = (int)(area.getX() + area.getWidth() / 2.0D);
/*  902 */     int midY = (int)(area.getY() + area.getHeight() / 2.0D);
/*  903 */     int stemTop = (int)(area.getMinY() + 40.0D);
/*  904 */     int stemBottom = (int)(area.getMaxY() - 80.0D);
/*  905 */     Rectangle2D dataArea = new Rectangle2D.Double((midX - 20), stemTop, 20.0D, (stemBottom - stemTop));
/*  909 */     outerBulb.setFrame((midX - 40), stemBottom, 80.0D, 80.0D);
/*  913 */     outerStem.setRoundRect((midX - 20), area.getMinY(), 40.0D, (stemBottom + 80 - stemTop), 40.0D, 40.0D);
/*  919 */     Area outerThermometer = new Area(outerBulb);
/*  920 */     Area tempArea = new Area(outerStem);
/*  921 */     outerThermometer.add(tempArea);
/*  923 */     innerBulb.setFrame((midX - 40 + 5), (stemBottom + 5), 70.0D, 70.0D);
/*  928 */     innerStem.setRoundRect((midX - 20 + 5), area.getMinY() + 5.0D, 30.0D, (stemBottom + 80 - 10 - stemTop), 30.0D, 30.0D);
/*  935 */     Area innerThermometer = new Area(innerBulb);
/*  936 */     tempArea = new Area(innerStem);
/*  937 */     innerThermometer.add(tempArea);
/*  939 */     if (this.dataset != null && this.dataset.getValue() != null) {
/*      */       String valueString;
/*      */       int stringWidth;
/*  940 */       double current = this.dataset.getValue().doubleValue();
/*  941 */       double ds = this.rangeAxis.valueToJava2D(current, dataArea, RectangleEdge.LEFT);
/*  945 */       int i = 30;
/*  946 */       int j = 15;
/*  947 */       int l = i / 2;
/*  948 */       int k = (int)Math.round(ds);
/*  949 */       if (k < 5.0D + area.getMinY()) {
/*  950 */         k = (int)(5.0D + area.getMinY());
/*  951 */         l = 40;
/*      */       } 
/*  954 */       Area mercury = new Area(innerBulb);
/*  956 */       if (k < stemBottom + 40) {
/*  957 */         mercuryStem.setRoundRect((midX - j), k, i, (stemBottom + 40 - k), l, l);
/*  960 */         tempArea = new Area(mercuryStem);
/*  961 */         mercury.add(tempArea);
/*      */       } 
/*  964 */       g2.setPaint(getCurrentPaint());
/*  965 */       g2.fill(mercury);
/*  968 */       if (this.subrangeIndicatorsVisible) {
/*  969 */         g2.setStroke(this.subrangeIndicatorStroke);
/*  970 */         Range range = this.rangeAxis.getRange();
/*  973 */         double value = this.subrangeInfo[0][0];
/*  974 */         if (range.contains(value)) {
/*  975 */           double x = (midX + 20 + 2);
/*  976 */           double y = this.rangeAxis.valueToJava2D(value, dataArea, RectangleEdge.LEFT);
/*  979 */           Line2D line = new Line2D.Double(x, y, x + 10.0D, y);
/*  980 */           g2.setPaint(this.subrangePaint[0]);
/*  981 */           g2.draw(line);
/*      */         } 
/*  985 */         value = this.subrangeInfo[1][0];
/*  986 */         if (range.contains(value)) {
/*  987 */           double x = (midX + 20 + 2);
/*  988 */           double y = this.rangeAxis.valueToJava2D(value, dataArea, RectangleEdge.LEFT);
/*  991 */           Line2D line = new Line2D.Double(x, y, x + 10.0D, y);
/*  992 */           g2.setPaint(this.subrangePaint[1]);
/*  993 */           g2.draw(line);
/*      */         } 
/*  997 */         value = this.subrangeInfo[2][0];
/*  998 */         if (range.contains(value)) {
/*  999 */           double x = (midX + 20 + 2);
/* 1000 */           double y = this.rangeAxis.valueToJava2D(value, dataArea, RectangleEdge.LEFT);
/* 1003 */           Line2D line = new Line2D.Double(x, y, x + 10.0D, y);
/* 1004 */           g2.setPaint(this.subrangePaint[2]);
/* 1005 */           g2.draw(line);
/*      */         } 
/*      */       } 
/* 1010 */       if (this.rangeAxis != null && this.axisLocation != 0) {
/*      */         Rectangle2D drawArea;
/* 1011 */         int drawWidth = 10;
/* 1012 */         if (this.showValueLines)
/* 1013 */           drawWidth += 40; 
/* 1016 */         double cursor = 0.0D;
/* 1018 */         switch (this.axisLocation) {
/*      */           case 1:
/* 1020 */             cursor = (midX + 20);
/* 1021 */             drawArea = new Rectangle2D.Double(cursor, stemTop, drawWidth, (stemBottom - stemTop + 1));
/* 1027 */             this.rangeAxis.draw(g2, cursor, area, drawArea, RectangleEdge.RIGHT, null);
/*      */             break;
/*      */           default:
/* 1036 */             cursor = (midX - 20);
/* 1037 */             drawArea = new Rectangle2D.Double(cursor, stemTop, drawWidth, (stemBottom - stemTop + 1));
/* 1043 */             this.rangeAxis.draw(g2, cursor, area, drawArea, RectangleEdge.LEFT, null);
/*      */             break;
/*      */         } 
/*      */       } 
/* 1053 */       g2.setFont(this.valueFont);
/* 1054 */       g2.setPaint(this.valuePaint);
/* 1055 */       metrics = g2.getFontMetrics();
/* 1056 */       switch (this.valueLocation) {
/*      */         case 1:
/* 1058 */           g2.drawString(this.valueFormat.format(current), midX + 20 + 5, midY);
/*      */           break;
/*      */         case 2:
/* 1064 */           valueString = this.valueFormat.format(current);
/* 1065 */           stringWidth = metrics.stringWidth(valueString);
/* 1066 */           g2.drawString(valueString, midX - 20 - 5 - stringWidth, midY);
/*      */           break;
/*      */         case 3:
/* 1072 */           temp = this.valueFormat.format(current);
/* 1073 */           i = metrics.stringWidth(temp) / 2;
/* 1074 */           g2.drawString(temp, midX - i, stemBottom + 40 + 5);
/*      */           break;
/*      */       } 
/*      */     } 
/* 1084 */     g2.setPaint(this.thermometerPaint);
/* 1085 */     g2.setFont(this.valueFont);
/* 1088 */     metrics = g2.getFontMetrics();
/* 1089 */     int tickX1 = midX - 20 - 10 - metrics.stringWidth(UNITS[this.units]);
/* 1091 */     if (tickX1 > area.getMinX())
/* 1092 */       g2.drawString(UNITS[this.units], tickX1, (int)(area.getMinY() + 20.0D)); 
/* 1098 */     g2.setStroke(this.thermometerStroke);
/* 1099 */     g2.draw(outerThermometer);
/* 1100 */     g2.draw(innerThermometer);
/* 1102 */     drawOutline(g2, area);
/*      */   }
/*      */   
/*      */   public void zoom(double percent) {}
/*      */   
/*      */   public String getPlotType() {
/* 1122 */     return localizationResources.getString("Thermometer_Plot");
/*      */   }
/*      */   
/*      */   public void datasetChanged(DatasetChangeEvent event) {
/* 1131 */     Number vn = this.dataset.getValue();
/* 1132 */     if (vn != null) {
/* 1133 */       double value = vn.doubleValue();
/* 1134 */       if (inSubrange(0, value)) {
/* 1135 */         this.subrange = 0;
/* 1137 */       } else if (inSubrange(1, value)) {
/* 1138 */         this.subrange = 1;
/* 1140 */       } else if (inSubrange(2, value)) {
/* 1141 */         this.subrange = 2;
/*      */       } else {
/* 1144 */         this.subrange = -1;
/*      */       } 
/* 1146 */       setAxisRange();
/*      */     } 
/* 1148 */     super.datasetChanged(event);
/*      */   }
/*      */   
/*      */   public Number getMinimumVerticalDataValue() {
/* 1159 */     return new Double(this.lowerBound);
/*      */   }
/*      */   
/*      */   public Number getMaximumVerticalDataValue() {
/* 1170 */     return new Double(this.upperBound);
/*      */   }
/*      */   
/*      */   public Range getDataRange(ValueAxis axis) {
/* 1181 */     return new Range(this.lowerBound, this.upperBound);
/*      */   }
/*      */   
/*      */   protected void setAxisRange() {
/* 1188 */     if (this.subrange >= 0 && this.followDataInSubranges) {
/* 1189 */       this.rangeAxis.setRange(new Range(this.subrangeInfo[this.subrange][2], this.subrangeInfo[this.subrange][3]));
/*      */     } else {
/* 1195 */       this.rangeAxis.setRange(this.lowerBound, this.upperBound);
/*      */     } 
/*      */   }
/*      */   
/*      */   public LegendItemCollection getLegendItems() {
/* 1205 */     return null;
/*      */   }
/*      */   
/*      */   public PlotOrientation getOrientation() {
/* 1214 */     return PlotOrientation.VERTICAL;
/*      */   }
/*      */   
/*      */   protected static boolean isValidNumber(double d) {
/* 1226 */     return (!Double.isNaN(d) && !Double.isInfinite(d));
/*      */   }
/*      */   
/*      */   private boolean inSubrange(int subrange, double value) {
/* 1238 */     return (value > this.subrangeInfo[subrange][0] && value <= this.subrangeInfo[subrange][1]);
/*      */   }
/*      */   
/*      */   private Paint getCurrentPaint() {
/* 1249 */     Paint result = this.mercuryPaint;
/* 1250 */     if (this.useSubrangePaint) {
/* 1251 */       double value = this.dataset.getValue().doubleValue();
/* 1252 */       if (inSubrange(0, value)) {
/* 1253 */         result = this.subrangePaint[0];
/* 1255 */       } else if (inSubrange(1, value)) {
/* 1256 */         result = this.subrangePaint[1];
/* 1258 */       } else if (inSubrange(2, value)) {
/* 1259 */         result = this.subrangePaint[2];
/*      */       } 
/*      */     } 
/* 1262 */     return result;
/*      */   }
/*      */   
/*      */   public boolean equals(Object obj) {
/* 1274 */     if (obj == this)
/* 1275 */       return true; 
/* 1277 */     if (!(obj instanceof ThermometerPlot))
/* 1278 */       return false; 
/* 1280 */     ThermometerPlot that = (ThermometerPlot)obj;
/* 1281 */     if (!super.equals(obj))
/* 1282 */       return false; 
/* 1284 */     if (!ObjectUtilities.equal(this.rangeAxis, that.rangeAxis))
/* 1285 */       return false; 
/* 1287 */     if (this.axisLocation != that.axisLocation)
/* 1288 */       return false; 
/* 1290 */     if (this.lowerBound != that.lowerBound)
/* 1291 */       return false; 
/* 1293 */     if (this.upperBound != that.upperBound)
/* 1294 */       return false; 
/* 1296 */     if (!ObjectUtilities.equal(this.padding, that.padding))
/* 1297 */       return false; 
/* 1299 */     if (!ObjectUtilities.equal(this.thermometerStroke, that.thermometerStroke))
/* 1302 */       return false; 
/* 1304 */     if (!PaintUtilities.equal(this.thermometerPaint, that.thermometerPaint))
/* 1307 */       return false; 
/* 1309 */     if (this.units != that.units)
/* 1310 */       return false; 
/* 1312 */     if (this.valueLocation != that.valueLocation)
/* 1313 */       return false; 
/* 1315 */     if (!ObjectUtilities.equal(this.valueFont, that.valueFont))
/* 1316 */       return false; 
/* 1318 */     if (!PaintUtilities.equal(this.valuePaint, that.valuePaint))
/* 1319 */       return false; 
/* 1321 */     if (!ObjectUtilities.equal(this.valueFormat, that.valueFormat))
/* 1322 */       return false; 
/* 1324 */     if (!PaintUtilities.equal(this.mercuryPaint, that.mercuryPaint))
/* 1325 */       return false; 
/* 1327 */     if (this.showValueLines != that.showValueLines)
/* 1328 */       return false; 
/* 1330 */     if (this.subrange != that.subrange)
/* 1331 */       return false; 
/* 1333 */     if (this.followDataInSubranges != that.followDataInSubranges)
/* 1334 */       return false; 
/* 1336 */     if (!equal(this.subrangeInfo, that.subrangeInfo))
/* 1337 */       return false; 
/* 1339 */     if (this.useSubrangePaint != that.useSubrangePaint)
/* 1340 */       return false; 
/* 1342 */     for (int i = 0; i < this.subrangePaint.length; i++) {
/* 1343 */       if (!PaintUtilities.equal(this.subrangePaint[i], that.subrangePaint[i]))
/* 1345 */         return false; 
/*      */     } 
/* 1348 */     return true;
/*      */   }
/*      */   
/*      */   private static boolean equal(double[][] array1, double[][] array2) {
/* 1360 */     if (array1 == null)
/* 1361 */       return (array2 == null); 
/* 1363 */     if (array2 == null)
/* 1364 */       return false; 
/* 1366 */     if (array1.length != array2.length)
/* 1367 */       return false; 
/* 1369 */     for (int i = 0; i < array1.length; i++) {
/* 1370 */       if (!Arrays.equals(array1[i], array2[i]))
/* 1371 */         return false; 
/*      */     } 
/* 1374 */     return true;
/*      */   }
/*      */   
/*      */   public Object clone() throws CloneNotSupportedException {
/* 1386 */     ThermometerPlot clone = (ThermometerPlot)super.clone();
/* 1388 */     if (clone.dataset != null)
/* 1389 */       clone.dataset.addChangeListener(clone); 
/* 1391 */     clone.rangeAxis = (ValueAxis)ObjectUtilities.clone(this.rangeAxis);
/* 1392 */     if (clone.rangeAxis != null) {
/* 1393 */       clone.rangeAxis.setPlot(clone);
/* 1394 */       clone.rangeAxis.addChangeListener(clone);
/*      */     } 
/* 1396 */     clone.valueFormat = (NumberFormat)this.valueFormat.clone();
/* 1397 */     clone.subrangePaint = (Paint[])this.subrangePaint.clone();
/* 1399 */     return clone;
/*      */   }
/*      */   
/*      */   private void writeObject(ObjectOutputStream stream) throws IOException {
/* 1411 */     stream.defaultWriteObject();
/* 1412 */     SerialUtilities.writeStroke(this.thermometerStroke, stream);
/* 1413 */     SerialUtilities.writePaint(this.thermometerPaint, stream);
/* 1414 */     SerialUtilities.writePaint(this.valuePaint, stream);
/* 1415 */     SerialUtilities.writePaint(this.mercuryPaint, stream);
/* 1416 */     SerialUtilities.writeStroke(this.subrangeIndicatorStroke, stream);
/* 1417 */     SerialUtilities.writeStroke(this.rangeIndicatorStroke, stream);
/*      */   }
/*      */   
/*      */   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
/* 1430 */     stream.defaultReadObject();
/* 1431 */     this.thermometerStroke = SerialUtilities.readStroke(stream);
/* 1432 */     this.thermometerPaint = SerialUtilities.readPaint(stream);
/* 1433 */     this.valuePaint = SerialUtilities.readPaint(stream);
/* 1434 */     this.mercuryPaint = SerialUtilities.readPaint(stream);
/* 1435 */     this.subrangeIndicatorStroke = SerialUtilities.readStroke(stream);
/* 1436 */     this.rangeIndicatorStroke = SerialUtilities.readStroke(stream);
/* 1438 */     if (this.rangeAxis != null)
/* 1439 */       this.rangeAxis.addChangeListener(this); 
/*      */   }
/*      */   
/*      */   public void zoomDomainAxes(double factor, PlotRenderingInfo state, Point2D source) {}
/*      */   
/*      */   public void zoomRangeAxes(double factor, PlotRenderingInfo state, Point2D source) {
/* 1464 */     this.rangeAxis.resizeRange(factor);
/*      */   }
/*      */   
/*      */   public void zoomDomainAxes(double lowerPercent, double upperPercent, PlotRenderingInfo state, Point2D source) {}
/*      */   
/*      */   public void zoomRangeAxes(double lowerPercent, double upperPercent, PlotRenderingInfo state, Point2D source) {
/* 1490 */     this.rangeAxis.zoomRange(lowerPercent, upperPercent);
/*      */   }
/*      */   
/*      */   public boolean isDomainZoomable() {
/* 1499 */     return false;
/*      */   }
/*      */   
/*      */   public boolean isRangeZoomable() {
/* 1508 */     return true;
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\plot\ThermometerPlot.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */