/*      */ package org.jfree.chart.axis;
/*      */ 
/*      */ import java.awt.BasicStroke;
/*      */ import java.awt.Color;
/*      */ import java.awt.Font;
/*      */ import java.awt.FontMetrics;
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.Paint;
/*      */ import java.awt.Shape;
/*      */ import java.awt.Stroke;
/*      */ import java.awt.geom.AffineTransform;
/*      */ import java.awt.geom.Line2D;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import java.util.Arrays;
/*      */ import java.util.EventListener;
/*      */ import java.util.List;
/*      */ import javax.swing.event.EventListenerList;
/*      */ import org.jfree.chart.event.AxisChangeEvent;
/*      */ import org.jfree.chart.event.AxisChangeListener;
/*      */ import org.jfree.chart.plot.Plot;
/*      */ import org.jfree.chart.plot.PlotRenderingInfo;
/*      */ import org.jfree.io.SerialUtilities;
/*      */ import org.jfree.text.TextUtilities;
/*      */ import org.jfree.ui.RectangleEdge;
/*      */ import org.jfree.ui.RectangleInsets;
/*      */ import org.jfree.ui.TextAnchor;
/*      */ import org.jfree.util.ObjectUtilities;
/*      */ import org.jfree.util.PaintUtilities;
/*      */ 
/*      */ public abstract class Axis implements Cloneable, Serializable {
/*      */   private static final long serialVersionUID = 7719289504573298271L;
/*      */   
/*      */   public static final boolean DEFAULT_AXIS_VISIBLE = true;
/*      */   
/*  129 */   public static final Font DEFAULT_AXIS_LABEL_FONT = new Font("SansSerif", 0, 12);
/*      */   
/*  133 */   public static final Paint DEFAULT_AXIS_LABEL_PAINT = Color.black;
/*      */   
/*  136 */   public static final RectangleInsets DEFAULT_AXIS_LABEL_INSETS = new RectangleInsets(3.0D, 3.0D, 3.0D, 3.0D);
/*      */   
/*  140 */   public static final Paint DEFAULT_AXIS_LINE_PAINT = Color.gray;
/*      */   
/*  143 */   public static final Stroke DEFAULT_AXIS_LINE_STROKE = new BasicStroke(1.0F);
/*      */   
/*      */   public static final boolean DEFAULT_TICK_LABELS_VISIBLE = true;
/*      */   
/*  149 */   public static final Font DEFAULT_TICK_LABEL_FONT = new Font("SansSerif", 0, 10);
/*      */   
/*  153 */   public static final Paint DEFAULT_TICK_LABEL_PAINT = Color.black;
/*      */   
/*  156 */   public static final RectangleInsets DEFAULT_TICK_LABEL_INSETS = new RectangleInsets(2.0D, 4.0D, 2.0D, 4.0D);
/*      */   
/*      */   public static final boolean DEFAULT_TICK_MARKS_VISIBLE = true;
/*      */   
/*  163 */   public static final Stroke DEFAULT_TICK_MARK_STROKE = new BasicStroke(1.0F);
/*      */   
/*  166 */   public static final Paint DEFAULT_TICK_MARK_PAINT = Color.gray;
/*      */   
/*      */   public static final float DEFAULT_TICK_MARK_INSIDE_LENGTH = 0.0F;
/*      */   
/*      */   public static final float DEFAULT_TICK_MARK_OUTSIDE_LENGTH = 2.0F;
/*      */   
/*      */   private boolean visible;
/*      */   
/*      */   private String label;
/*      */   
/*      */   private Font labelFont;
/*      */   
/*      */   private transient Paint labelPaint;
/*      */   
/*      */   private RectangleInsets labelInsets;
/*      */   
/*      */   private double labelAngle;
/*      */   
/*      */   private boolean axisLineVisible;
/*      */   
/*      */   private transient Stroke axisLineStroke;
/*      */   
/*      */   private transient Paint axisLinePaint;
/*      */   
/*      */   private boolean tickLabelsVisible;
/*      */   
/*      */   private Font tickLabelFont;
/*      */   
/*      */   private transient Paint tickLabelPaint;
/*      */   
/*      */   private RectangleInsets tickLabelInsets;
/*      */   
/*      */   private boolean tickMarksVisible;
/*      */   
/*      */   private float tickMarkInsideLength;
/*      */   
/*      */   private float tickMarkOutsideLength;
/*      */   
/*      */   private transient Stroke tickMarkStroke;
/*      */   
/*      */   private transient Paint tickMarkPaint;
/*      */   
/*      */   private double fixedDimension;
/*      */   
/*      */   private transient Plot plot;
/*      */   
/*      */   private transient EventListenerList listenerList;
/*      */   
/*      */   protected Axis(String label) {
/*  253 */     this.label = label;
/*  254 */     this.visible = true;
/*  255 */     this.labelFont = DEFAULT_AXIS_LABEL_FONT;
/*  256 */     this.labelPaint = DEFAULT_AXIS_LABEL_PAINT;
/*  257 */     this.labelInsets = DEFAULT_AXIS_LABEL_INSETS;
/*  258 */     this.labelAngle = 0.0D;
/*  260 */     this.axisLineVisible = true;
/*  261 */     this.axisLinePaint = DEFAULT_AXIS_LINE_PAINT;
/*  262 */     this.axisLineStroke = DEFAULT_AXIS_LINE_STROKE;
/*  264 */     this.tickLabelsVisible = true;
/*  265 */     this.tickLabelFont = DEFAULT_TICK_LABEL_FONT;
/*  266 */     this.tickLabelPaint = DEFAULT_TICK_LABEL_PAINT;
/*  267 */     this.tickLabelInsets = DEFAULT_TICK_LABEL_INSETS;
/*  269 */     this.tickMarksVisible = true;
/*  270 */     this.tickMarkStroke = DEFAULT_TICK_MARK_STROKE;
/*  271 */     this.tickMarkPaint = DEFAULT_TICK_MARK_PAINT;
/*  272 */     this.tickMarkInsideLength = 0.0F;
/*  273 */     this.tickMarkOutsideLength = 2.0F;
/*  275 */     this.plot = null;
/*  277 */     this.listenerList = new EventListenerList();
/*      */   }
/*      */   
/*      */   public boolean isVisible() {
/*  288 */     return this.visible;
/*      */   }
/*      */   
/*      */   public void setVisible(boolean flag) {
/*  298 */     if (flag != this.visible) {
/*  299 */       this.visible = flag;
/*  300 */       notifyListeners(new AxisChangeEvent(this));
/*      */     } 
/*      */   }
/*      */   
/*      */   public String getLabel() {
/*  310 */     return this.label;
/*      */   }
/*      */   
/*      */   public void setLabel(String label) {
/*  321 */     String existing = this.label;
/*  322 */     if (existing != null) {
/*  323 */       if (!existing.equals(label)) {
/*  324 */         this.label = label;
/*  325 */         notifyListeners(new AxisChangeEvent(this));
/*      */       } 
/*  329 */     } else if (label != null) {
/*  330 */       this.label = label;
/*  331 */       notifyListeners(new AxisChangeEvent(this));
/*      */     } 
/*      */   }
/*      */   
/*      */   public Font getLabelFont() {
/*  343 */     return this.labelFont;
/*      */   }
/*      */   
/*      */   public void setLabelFont(Font font) {
/*  353 */     if (font == null)
/*  354 */       throw new IllegalArgumentException("Null 'font' argument."); 
/*  356 */     if (!this.labelFont.equals(font)) {
/*  357 */       this.labelFont = font;
/*  358 */       notifyListeners(new AxisChangeEvent(this));
/*      */     } 
/*      */   }
/*      */   
/*      */   public Paint getLabelPaint() {
/*  368 */     return this.labelPaint;
/*      */   }
/*      */   
/*      */   public void setLabelPaint(Paint paint) {
/*  378 */     if (paint == null)
/*  379 */       throw new IllegalArgumentException("Null 'paint' argument."); 
/*  381 */     this.labelPaint = paint;
/*  382 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */   public RectangleInsets getLabelInsets() {
/*  392 */     return this.labelInsets;
/*      */   }
/*      */   
/*      */   public void setLabelInsets(RectangleInsets insets) {
/*  402 */     if (insets == null)
/*  403 */       throw new IllegalArgumentException("Null 'insets' argument."); 
/*  405 */     if (!insets.equals(this.labelInsets)) {
/*  406 */       this.labelInsets = insets;
/*  407 */       notifyListeners(new AxisChangeEvent(this));
/*      */     } 
/*      */   }
/*      */   
/*      */   public double getLabelAngle() {
/*  417 */     return this.labelAngle;
/*      */   }
/*      */   
/*      */   public void setLabelAngle(double angle) {
/*  427 */     this.labelAngle = angle;
/*  428 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */   public boolean isAxisLineVisible() {
/*  437 */     return this.axisLineVisible;
/*      */   }
/*      */   
/*      */   public void setAxisLineVisible(boolean visible) {
/*  447 */     this.axisLineVisible = visible;
/*  448 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Paint getAxisLinePaint() {
/*  457 */     return this.axisLinePaint;
/*      */   }
/*      */   
/*      */   public void setAxisLinePaint(Paint paint) {
/*  467 */     if (paint == null)
/*  468 */       throw new IllegalArgumentException("Null 'paint' argument."); 
/*  470 */     this.axisLinePaint = paint;
/*  471 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Stroke getAxisLineStroke() {
/*  480 */     return this.axisLineStroke;
/*      */   }
/*      */   
/*      */   public void setAxisLineStroke(Stroke stroke) {
/*  490 */     if (stroke == null)
/*  491 */       throw new IllegalArgumentException("Null 'stroke' argument."); 
/*  493 */     this.axisLineStroke = stroke;
/*  494 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */   public boolean isTickLabelsVisible() {
/*  503 */     return this.tickLabelsVisible;
/*      */   }
/*      */   
/*      */   public void setTickLabelsVisible(boolean flag) {
/*  515 */     if (flag != this.tickLabelsVisible) {
/*  516 */       this.tickLabelsVisible = flag;
/*  517 */       notifyListeners(new AxisChangeEvent(this));
/*      */     } 
/*      */   }
/*      */   
/*      */   public Font getTickLabelFont() {
/*  528 */     return this.tickLabelFont;
/*      */   }
/*      */   
/*      */   public void setTickLabelFont(Font font) {
/*  540 */     if (font == null)
/*  541 */       throw new IllegalArgumentException("Null 'font' argument."); 
/*  545 */     if (!this.tickLabelFont.equals(font)) {
/*  546 */       this.tickLabelFont = font;
/*  547 */       notifyListeners(new AxisChangeEvent(this));
/*      */     } 
/*      */   }
/*      */   
/*      */   public Paint getTickLabelPaint() {
/*  558 */     return this.tickLabelPaint;
/*      */   }
/*      */   
/*      */   public void setTickLabelPaint(Paint paint) {
/*  568 */     if (paint == null)
/*  569 */       throw new IllegalArgumentException("Null 'paint' argument."); 
/*  571 */     this.tickLabelPaint = paint;
/*  572 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */   public RectangleInsets getTickLabelInsets() {
/*  581 */     return this.tickLabelInsets;
/*      */   }
/*      */   
/*      */   public void setTickLabelInsets(RectangleInsets insets) {
/*  591 */     if (insets == null)
/*  592 */       throw new IllegalArgumentException("Null 'insets' argument."); 
/*  594 */     if (!this.tickLabelInsets.equals(insets)) {
/*  595 */       this.tickLabelInsets = insets;
/*  596 */       notifyListeners(new AxisChangeEvent(this));
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean isTickMarksVisible() {
/*  608 */     return this.tickMarksVisible;
/*      */   }
/*      */   
/*      */   public void setTickMarksVisible(boolean flag) {
/*  618 */     if (flag != this.tickMarksVisible) {
/*  619 */       this.tickMarksVisible = flag;
/*  620 */       notifyListeners(new AxisChangeEvent(this));
/*      */     } 
/*      */   }
/*      */   
/*      */   public float getTickMarkInsideLength() {
/*  630 */     return this.tickMarkInsideLength;
/*      */   }
/*      */   
/*      */   public void setTickMarkInsideLength(float length) {
/*  640 */     this.tickMarkInsideLength = length;
/*  641 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */   public float getTickMarkOutsideLength() {
/*  650 */     return this.tickMarkOutsideLength;
/*      */   }
/*      */   
/*      */   public void setTickMarkOutsideLength(float length) {
/*  660 */     this.tickMarkOutsideLength = length;
/*  661 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Stroke getTickMarkStroke() {
/*  670 */     return this.tickMarkStroke;
/*      */   }
/*      */   
/*      */   public void setTickMarkStroke(Stroke stroke) {
/*  680 */     if (stroke == null)
/*  681 */       throw new IllegalArgumentException("Null 'stroke' argument."); 
/*  683 */     if (!this.tickMarkStroke.equals(stroke)) {
/*  684 */       this.tickMarkStroke = stroke;
/*  685 */       notifyListeners(new AxisChangeEvent(this));
/*      */     } 
/*      */   }
/*      */   
/*      */   public Paint getTickMarkPaint() {
/*  695 */     return this.tickMarkPaint;
/*      */   }
/*      */   
/*      */   public void setTickMarkPaint(Paint paint) {
/*  705 */     if (paint == null)
/*  706 */       throw new IllegalArgumentException("Null 'paint' argument."); 
/*  708 */     this.tickMarkPaint = paint;
/*  709 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Plot getPlot() {
/*  720 */     return this.plot;
/*      */   }
/*      */   
/*      */   public void setPlot(Plot plot) {
/*  731 */     this.plot = plot;
/*  732 */     configure();
/*      */   }
/*      */   
/*      */   public double getFixedDimension() {
/*  741 */     return this.fixedDimension;
/*      */   }
/*      */   
/*      */   public void setFixedDimension(double dimension) {
/*  756 */     this.fixedDimension = dimension;
/*      */   }
/*      */   
/*      */   public void addChangeListener(AxisChangeListener listener) {
/*  826 */     this.listenerList.add(AxisChangeListener.class, listener);
/*      */   }
/*      */   
/*      */   public void removeChangeListener(AxisChangeListener listener) {
/*  835 */     this.listenerList.remove(AxisChangeListener.class, listener);
/*      */   }
/*      */   
/*      */   public boolean hasListener(EventListener listener) {
/*  848 */     List list = Arrays.asList(this.listenerList.getListenerList());
/*  849 */     return list.contains(listener);
/*      */   }
/*      */   
/*      */   protected void notifyListeners(AxisChangeEvent event) {
/*  860 */     Object[] listeners = this.listenerList.getListenerList();
/*  861 */     for (int i = listeners.length - 2; i >= 0; i -= 2) {
/*  862 */       if (listeners[i] == AxisChangeListener.class)
/*  863 */         ((AxisChangeListener)listeners[i + 1]).axisChanged(event); 
/*      */     } 
/*      */   }
/*      */   
/*      */   protected Rectangle2D getLabelEnclosure(Graphics2D g2, RectangleEdge edge) {
/*  880 */     Rectangle2D result = new Rectangle2D.Double();
/*  881 */     String axisLabel = getLabel();
/*  882 */     if (axisLabel != null && !axisLabel.equals("")) {
/*  883 */       FontMetrics fm = g2.getFontMetrics(getLabelFont());
/*  884 */       Rectangle2D bounds = TextUtilities.getTextBounds(axisLabel, g2, fm);
/*  885 */       RectangleInsets insets = getLabelInsets();
/*  886 */       bounds = insets.createOutsetRectangle(bounds);
/*  887 */       double angle = getLabelAngle();
/*  888 */       if (edge == RectangleEdge.LEFT || edge == RectangleEdge.RIGHT)
/*  889 */         angle -= 1.5707963267948966D; 
/*  891 */       double x = bounds.getCenterX();
/*  892 */       double y = bounds.getCenterY();
/*  893 */       AffineTransform transformer = AffineTransform.getRotateInstance(angle, x, y);
/*  895 */       Shape labelBounds = transformer.createTransformedShape(bounds);
/*  896 */       result = labelBounds.getBounds2D();
/*      */     } 
/*  899 */     return result;
/*      */   }
/*      */   
/*      */   protected AxisState drawLabel(String label, Graphics2D g2, Rectangle2D plotArea, Rectangle2D dataArea, RectangleEdge edge, AxisState state) {
/*  923 */     if (state == null)
/*  924 */       throw new IllegalArgumentException("Null 'state' argument."); 
/*  927 */     if (label == null || label.equals(""))
/*  928 */       return state; 
/*  931 */     Font font = getLabelFont();
/*  932 */     RectangleInsets insets = getLabelInsets();
/*  933 */     g2.setFont(font);
/*  934 */     g2.setPaint(getLabelPaint());
/*  935 */     FontMetrics fm = g2.getFontMetrics();
/*  936 */     Rectangle2D labelBounds = TextUtilities.getTextBounds(label, g2, fm);
/*  938 */     if (edge == RectangleEdge.TOP) {
/*  940 */       AffineTransform t = AffineTransform.getRotateInstance(getLabelAngle(), labelBounds.getCenterX(), labelBounds.getCenterY());
/*  944 */       Shape rotatedLabelBounds = t.createTransformedShape(labelBounds);
/*  945 */       labelBounds = rotatedLabelBounds.getBounds2D();
/*  946 */       double labelx = dataArea.getCenterX();
/*  947 */       double labely = state.getCursor() - insets.getBottom() - labelBounds.getHeight() / 2.0D;
/*  950 */       TextUtilities.drawRotatedString(label, g2, (float)labelx, (float)labely, TextAnchor.CENTER, getLabelAngle(), TextAnchor.CENTER);
/*  954 */       state.cursorUp(insets.getTop() + labelBounds.getHeight() + insets.getBottom());
/*  959 */     } else if (edge == RectangleEdge.BOTTOM) {
/*  961 */       AffineTransform t = AffineTransform.getRotateInstance(getLabelAngle(), labelBounds.getCenterX(), labelBounds.getCenterY());
/*  965 */       Shape rotatedLabelBounds = t.createTransformedShape(labelBounds);
/*  966 */       labelBounds = rotatedLabelBounds.getBounds2D();
/*  967 */       double labelx = dataArea.getCenterX();
/*  968 */       double labely = state.getCursor() + insets.getTop() + labelBounds.getHeight() / 2.0D;
/*  970 */       TextUtilities.drawRotatedString(label, g2, (float)labelx, (float)labely, TextAnchor.CENTER, getLabelAngle(), TextAnchor.CENTER);
/*  974 */       state.cursorDown(insets.getTop() + labelBounds.getHeight() + insets.getBottom());
/*  979 */     } else if (edge == RectangleEdge.LEFT) {
/*  981 */       AffineTransform t = AffineTransform.getRotateInstance(getLabelAngle() - 1.5707963267948966D, labelBounds.getCenterX(), labelBounds.getCenterY());
/*  985 */       Shape rotatedLabelBounds = t.createTransformedShape(labelBounds);
/*  986 */       labelBounds = rotatedLabelBounds.getBounds2D();
/*  987 */       double labelx = state.getCursor() - insets.getRight() - labelBounds.getWidth() / 2.0D;
/*  989 */       double labely = dataArea.getCenterY();
/*  990 */       TextUtilities.drawRotatedString(label, g2, (float)labelx, (float)labely, TextAnchor.CENTER, getLabelAngle() - 1.5707963267948966D, TextAnchor.CENTER);
/*  994 */       state.cursorLeft(insets.getLeft() + labelBounds.getWidth() + insets.getRight());
/*  998 */     } else if (edge == RectangleEdge.RIGHT) {
/* 1000 */       AffineTransform t = AffineTransform.getRotateInstance(getLabelAngle() + 1.5707963267948966D, labelBounds.getCenterX(), labelBounds.getCenterY());
/* 1004 */       Shape rotatedLabelBounds = t.createTransformedShape(labelBounds);
/* 1005 */       labelBounds = rotatedLabelBounds.getBounds2D();
/* 1006 */       double labelx = state.getCursor() + insets.getLeft() + labelBounds.getWidth() / 2.0D;
/* 1008 */       double labely = dataArea.getY() + dataArea.getHeight() / 2.0D;
/* 1009 */       TextUtilities.drawRotatedString(label, g2, (float)labelx, (float)labely, TextAnchor.CENTER, getLabelAngle() + 1.5707963267948966D, TextAnchor.CENTER);
/* 1013 */       state.cursorRight(insets.getLeft() + labelBounds.getWidth() + insets.getRight());
/*      */     } 
/* 1019 */     return state;
/*      */   }
/*      */   
/*      */   protected void drawAxisLine(Graphics2D g2, double cursor, Rectangle2D dataArea, RectangleEdge edge) {
/* 1034 */     Line2D axisLine = null;
/* 1035 */     if (edge == RectangleEdge.TOP) {
/* 1036 */       axisLine = new Line2D.Double(dataArea.getX(), cursor, dataArea.getMaxX(), cursor);
/* 1040 */     } else if (edge == RectangleEdge.BOTTOM) {
/* 1041 */       axisLine = new Line2D.Double(dataArea.getX(), cursor, dataArea.getMaxX(), cursor);
/* 1045 */     } else if (edge == RectangleEdge.LEFT) {
/* 1046 */       axisLine = new Line2D.Double(cursor, dataArea.getY(), cursor, dataArea.getMaxY());
/* 1050 */     } else if (edge == RectangleEdge.RIGHT) {
/* 1051 */       axisLine = new Line2D.Double(cursor, dataArea.getY(), cursor, dataArea.getMaxY());
/*      */     } 
/* 1055 */     g2.setPaint(this.axisLinePaint);
/* 1056 */     g2.setStroke(this.axisLineStroke);
/* 1057 */     g2.draw(axisLine);
/*      */   }
/*      */   
/*      */   public Object clone() throws CloneNotSupportedException {
/* 1070 */     Axis clone = (Axis)super.clone();
/* 1072 */     clone.plot = null;
/* 1073 */     clone.listenerList = new EventListenerList();
/* 1074 */     return clone;
/*      */   }
/*      */   
/*      */   public boolean equals(Object obj) {
/* 1085 */     if (obj == this)
/* 1086 */       return true; 
/* 1088 */     if (!(obj instanceof Axis))
/* 1089 */       return false; 
/* 1091 */     Axis that = (Axis)obj;
/* 1092 */     if (this.visible != that.visible)
/* 1093 */       return false; 
/* 1095 */     if (!ObjectUtilities.equal(this.label, that.label))
/* 1096 */       return false; 
/* 1098 */     if (!ObjectUtilities.equal(this.labelFont, that.labelFont))
/* 1099 */       return false; 
/* 1101 */     if (!PaintUtilities.equal(this.labelPaint, that.labelPaint))
/* 1102 */       return false; 
/* 1104 */     if (!ObjectUtilities.equal(this.labelInsets, that.labelInsets))
/* 1105 */       return false; 
/* 1107 */     if (this.labelAngle != that.labelAngle)
/* 1108 */       return false; 
/* 1110 */     if (this.axisLineVisible != that.axisLineVisible)
/* 1111 */       return false; 
/* 1113 */     if (!ObjectUtilities.equal(this.axisLineStroke, that.axisLineStroke))
/* 1114 */       return false; 
/* 1116 */     if (!PaintUtilities.equal(this.axisLinePaint, that.axisLinePaint))
/* 1117 */       return false; 
/* 1119 */     if (this.tickLabelsVisible != that.tickLabelsVisible)
/* 1120 */       return false; 
/* 1122 */     if (!ObjectUtilities.equal(this.tickLabelFont, that.tickLabelFont))
/* 1123 */       return false; 
/* 1125 */     if (!PaintUtilities.equal(this.tickLabelPaint, that.tickLabelPaint))
/* 1126 */       return false; 
/* 1128 */     if (!ObjectUtilities.equal(this.tickLabelInsets, that.tickLabelInsets))
/* 1131 */       return false; 
/* 1133 */     if (this.tickMarksVisible != that.tickMarksVisible)
/* 1134 */       return false; 
/* 1136 */     if (this.tickMarkInsideLength != that.tickMarkInsideLength)
/* 1137 */       return false; 
/* 1139 */     if (this.tickMarkOutsideLength != that.tickMarkOutsideLength)
/* 1140 */       return false; 
/* 1142 */     if (!PaintUtilities.equal(this.tickMarkPaint, that.tickMarkPaint))
/* 1143 */       return false; 
/* 1145 */     if (!ObjectUtilities.equal(this.tickMarkStroke, that.tickMarkStroke))
/* 1146 */       return false; 
/* 1148 */     if (this.fixedDimension != that.fixedDimension)
/* 1149 */       return false; 
/* 1151 */     return true;
/*      */   }
/*      */   
/*      */   private void writeObject(ObjectOutputStream stream) throws IOException {
/* 1162 */     stream.defaultWriteObject();
/* 1163 */     SerialUtilities.writePaint(this.labelPaint, stream);
/* 1164 */     SerialUtilities.writePaint(this.tickLabelPaint, stream);
/* 1165 */     SerialUtilities.writeStroke(this.axisLineStroke, stream);
/* 1166 */     SerialUtilities.writePaint(this.axisLinePaint, stream);
/* 1167 */     SerialUtilities.writeStroke(this.tickMarkStroke, stream);
/* 1168 */     SerialUtilities.writePaint(this.tickMarkPaint, stream);
/*      */   }
/*      */   
/*      */   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
/* 1181 */     stream.defaultReadObject();
/* 1182 */     this.labelPaint = SerialUtilities.readPaint(stream);
/* 1183 */     this.tickLabelPaint = SerialUtilities.readPaint(stream);
/* 1184 */     this.axisLineStroke = SerialUtilities.readStroke(stream);
/* 1185 */     this.axisLinePaint = SerialUtilities.readPaint(stream);
/* 1186 */     this.tickMarkStroke = SerialUtilities.readStroke(stream);
/* 1187 */     this.tickMarkPaint = SerialUtilities.readPaint(stream);
/* 1188 */     this.listenerList = new EventListenerList();
/*      */   }
/*      */   
/*      */   public abstract void configure();
/*      */   
/*      */   public abstract AxisSpace reserveSpace(Graphics2D paramGraphics2D, Plot paramPlot, Rectangle2D paramRectangle2D, RectangleEdge paramRectangleEdge, AxisSpace paramAxisSpace);
/*      */   
/*      */   public abstract AxisState draw(Graphics2D paramGraphics2D, double paramDouble, Rectangle2D paramRectangle2D1, Rectangle2D paramRectangle2D2, RectangleEdge paramRectangleEdge, PlotRenderingInfo paramPlotRenderingInfo);
/*      */   
/*      */   public abstract List refreshTicks(Graphics2D paramGraphics2D, AxisState paramAxisState, Rectangle2D paramRectangle2D, RectangleEdge paramRectangleEdge);
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\axis\Axis.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */