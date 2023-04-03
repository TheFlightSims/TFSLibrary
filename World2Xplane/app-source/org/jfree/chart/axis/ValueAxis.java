/*      */ package org.jfree.chart.axis;
/*      */ 
/*      */ import java.awt.Font;
/*      */ import java.awt.FontMetrics;
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.Polygon;
/*      */ import java.awt.Shape;
/*      */ import java.awt.font.LineMetrics;
/*      */ import java.awt.geom.AffineTransform;
/*      */ import java.awt.geom.Line2D;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import org.jfree.chart.event.AxisChangeEvent;
/*      */ import org.jfree.chart.plot.Plot;
/*      */ import org.jfree.data.Range;
/*      */ import org.jfree.io.SerialUtilities;
/*      */ import org.jfree.text.TextUtilities;
/*      */ import org.jfree.ui.RectangleEdge;
/*      */ import org.jfree.ui.RectangleInsets;
/*      */ import org.jfree.util.ObjectUtilities;
/*      */ import org.jfree.util.PublicCloneable;
/*      */ 
/*      */ public abstract class ValueAxis extends Axis implements Cloneable, PublicCloneable, Serializable {
/*      */   private static final long serialVersionUID = 3698345477322391456L;
/*      */   
/*  141 */   public static final Range DEFAULT_RANGE = new Range(0.0D, 1.0D);
/*      */   
/*      */   public static final boolean DEFAULT_AUTO_RANGE = true;
/*      */   
/*      */   public static final boolean DEFAULT_INVERTED = false;
/*      */   
/*      */   public static final double DEFAULT_AUTO_RANGE_MINIMUM_SIZE = 1.0E-8D;
/*      */   
/*      */   public static final double DEFAULT_LOWER_MARGIN = 0.05D;
/*      */   
/*      */   public static final double DEFAULT_UPPER_MARGIN = 0.05D;
/*      */   
/*      */   public static final double DEFAULT_LOWER_BOUND = 0.0D;
/*      */   
/*      */   public static final double DEFAULT_UPPER_BOUND = 1.0D;
/*      */   
/*      */   public static final boolean DEFAULT_AUTO_TICK_UNIT_SELECTION = true;
/*      */   
/*      */   public static final int MAXIMUM_TICK_COUNT = 500;
/*      */   
/*      */   private boolean positiveArrowVisible;
/*      */   
/*      */   private boolean negativeArrowVisible;
/*      */   
/*      */   private transient Shape upArrow;
/*      */   
/*      */   private transient Shape downArrow;
/*      */   
/*      */   private transient Shape leftArrow;
/*      */   
/*      */   private transient Shape rightArrow;
/*      */   
/*      */   private boolean inverted;
/*      */   
/*      */   private Range range;
/*      */   
/*      */   private boolean autoRange;
/*      */   
/*      */   private double autoRangeMinimumSize;
/*      */   
/*      */   private double upperMargin;
/*      */   
/*      */   private double lowerMargin;
/*      */   
/*      */   private double fixedAutoRange;
/*      */   
/*      */   private boolean autoTickUnitSelection;
/*      */   
/*      */   private TickUnitSource standardTickUnits;
/*      */   
/*      */   private int autoTickIndex;
/*      */   
/*      */   private boolean verticalTickLabels;
/*      */   
/*      */   protected ValueAxis(String label, TickUnitSource standardTickUnits) {
/*  254 */     super(label);
/*  256 */     this.positiveArrowVisible = false;
/*  257 */     this.negativeArrowVisible = false;
/*  259 */     this.range = DEFAULT_RANGE;
/*  260 */     this.autoRange = true;
/*  262 */     this.inverted = false;
/*  263 */     this.autoRangeMinimumSize = 1.0E-8D;
/*  265 */     this.lowerMargin = 0.05D;
/*  266 */     this.upperMargin = 0.05D;
/*  268 */     this.fixedAutoRange = 0.0D;
/*  270 */     this.autoTickUnitSelection = true;
/*  271 */     this.standardTickUnits = standardTickUnits;
/*  273 */     Polygon p1 = new Polygon();
/*  274 */     p1.addPoint(0, 0);
/*  275 */     p1.addPoint(-2, 2);
/*  276 */     p1.addPoint(2, 2);
/*  278 */     this.upArrow = p1;
/*  280 */     Polygon p2 = new Polygon();
/*  281 */     p2.addPoint(0, 0);
/*  282 */     p2.addPoint(-2, -2);
/*  283 */     p2.addPoint(2, -2);
/*  285 */     this.downArrow = p2;
/*  287 */     Polygon p3 = new Polygon();
/*  288 */     p3.addPoint(0, 0);
/*  289 */     p3.addPoint(-2, -2);
/*  290 */     p3.addPoint(-2, 2);
/*  292 */     this.rightArrow = p3;
/*  294 */     Polygon p4 = new Polygon();
/*  295 */     p4.addPoint(0, 0);
/*  296 */     p4.addPoint(2, -2);
/*  297 */     p4.addPoint(2, 2);
/*  299 */     this.leftArrow = p4;
/*  301 */     this.verticalTickLabels = false;
/*      */   }
/*      */   
/*      */   public boolean isVerticalTickLabels() {
/*  312 */     return this.verticalTickLabels;
/*      */   }
/*      */   
/*      */   public void setVerticalTickLabels(boolean flag) {
/*  324 */     if (this.verticalTickLabels != flag) {
/*  325 */       this.verticalTickLabels = flag;
/*  326 */       notifyListeners(new AxisChangeEvent(this));
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean isPositiveArrowVisible() {
/*  337 */     return this.positiveArrowVisible;
/*      */   }
/*      */   
/*      */   public void setPositiveArrowVisible(boolean visible) {
/*  348 */     this.positiveArrowVisible = visible;
/*  349 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */   public boolean isNegativeArrowVisible() {
/*  359 */     return this.negativeArrowVisible;
/*      */   }
/*      */   
/*      */   public void setNegativeArrowVisible(boolean visible) {
/*  370 */     this.negativeArrowVisible = visible;
/*  371 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Shape getUpArrow() {
/*  381 */     return this.upArrow;
/*      */   }
/*      */   
/*      */   public void setUpArrow(Shape arrow) {
/*  392 */     if (arrow == null)
/*  393 */       throw new IllegalArgumentException("Null 'arrow' argument."); 
/*  395 */     this.upArrow = arrow;
/*  396 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Shape getDownArrow() {
/*  406 */     return this.downArrow;
/*      */   }
/*      */   
/*      */   public void setDownArrow(Shape arrow) {
/*  417 */     if (arrow == null)
/*  418 */       throw new IllegalArgumentException("Null 'arrow' argument."); 
/*  420 */     this.downArrow = arrow;
/*  421 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Shape getLeftArrow() {
/*  431 */     return this.leftArrow;
/*      */   }
/*      */   
/*      */   public void setLeftArrow(Shape arrow) {
/*  442 */     if (arrow == null)
/*  443 */       throw new IllegalArgumentException("Null 'arrow' argument."); 
/*  445 */     this.leftArrow = arrow;
/*  446 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Shape getRightArrow() {
/*  456 */     return this.rightArrow;
/*      */   }
/*      */   
/*      */   public void setRightArrow(Shape arrow) {
/*  467 */     if (arrow == null)
/*  468 */       throw new IllegalArgumentException("Null 'arrow' argument."); 
/*  470 */     this.rightArrow = arrow;
/*  471 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */   protected void drawAxisLine(Graphics2D g2, double cursor, Rectangle2D dataArea, RectangleEdge edge) {
/*  484 */     Line2D axisLine = null;
/*  485 */     if (edge == RectangleEdge.TOP) {
/*  486 */       axisLine = new Line2D.Double(dataArea.getX(), cursor, dataArea.getMaxX(), cursor);
/*  490 */     } else if (edge == RectangleEdge.BOTTOM) {
/*  491 */       axisLine = new Line2D.Double(dataArea.getX(), cursor, dataArea.getMaxX(), cursor);
/*  495 */     } else if (edge == RectangleEdge.LEFT) {
/*  496 */       axisLine = new Line2D.Double(cursor, dataArea.getY(), cursor, dataArea.getMaxY());
/*  500 */     } else if (edge == RectangleEdge.RIGHT) {
/*  501 */       axisLine = new Line2D.Double(cursor, dataArea.getY(), cursor, dataArea.getMaxY());
/*      */     } 
/*  505 */     g2.setPaint(getAxisLinePaint());
/*  506 */     g2.setStroke(getAxisLineStroke());
/*  507 */     g2.draw(axisLine);
/*  509 */     boolean drawUpOrRight = false;
/*  510 */     boolean drawDownOrLeft = false;
/*  511 */     if (this.positiveArrowVisible)
/*  512 */       if (this.inverted) {
/*  513 */         drawDownOrLeft = true;
/*      */       } else {
/*  516 */         drawUpOrRight = true;
/*      */       }  
/*  519 */     if (this.negativeArrowVisible)
/*  520 */       if (this.inverted) {
/*  521 */         drawUpOrRight = true;
/*      */       } else {
/*  524 */         drawDownOrLeft = true;
/*      */       }  
/*  527 */     if (drawUpOrRight) {
/*  528 */       double x = 0.0D;
/*  529 */       double y = 0.0D;
/*  530 */       Shape arrow = null;
/*  531 */       if (edge == RectangleEdge.TOP || edge == RectangleEdge.BOTTOM) {
/*  532 */         x = dataArea.getMaxX();
/*  533 */         y = cursor;
/*  534 */         arrow = this.rightArrow;
/*  536 */       } else if (edge == RectangleEdge.LEFT || edge == RectangleEdge.RIGHT) {
/*  538 */         x = cursor;
/*  539 */         y = dataArea.getMinY();
/*  540 */         arrow = this.upArrow;
/*      */       } 
/*  544 */       AffineTransform transformer = new AffineTransform();
/*  545 */       transformer.setToTranslation(x, y);
/*  546 */       Shape shape = transformer.createTransformedShape(arrow);
/*  547 */       g2.fill(shape);
/*  548 */       g2.draw(shape);
/*      */     } 
/*  551 */     if (drawDownOrLeft) {
/*  552 */       double x = 0.0D;
/*  553 */       double y = 0.0D;
/*  554 */       Shape arrow = null;
/*  555 */       if (edge == RectangleEdge.TOP || edge == RectangleEdge.BOTTOM) {
/*  556 */         x = dataArea.getMinX();
/*  557 */         y = cursor;
/*  558 */         arrow = this.leftArrow;
/*  560 */       } else if (edge == RectangleEdge.LEFT || edge == RectangleEdge.RIGHT) {
/*  562 */         x = cursor;
/*  563 */         y = dataArea.getMaxY();
/*  564 */         arrow = this.downArrow;
/*      */       } 
/*  568 */       AffineTransform transformer = new AffineTransform();
/*  569 */       transformer.setToTranslation(x, y);
/*  570 */       Shape shape = transformer.createTransformedShape(arrow);
/*  571 */       g2.fill(shape);
/*  572 */       g2.draw(shape);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected float[] calculateAnchorPoint(ValueTick tick, double cursor, Rectangle2D dataArea, RectangleEdge edge) {
/*  592 */     RectangleInsets insets = getTickLabelInsets();
/*  593 */     float[] result = new float[2];
/*  594 */     if (edge == RectangleEdge.TOP) {
/*  595 */       result[0] = (float)valueToJava2D(tick.getValue(), dataArea, edge);
/*  596 */       result[1] = (float)(cursor - insets.getBottom() - 2.0D);
/*  598 */     } else if (edge == RectangleEdge.BOTTOM) {
/*  599 */       result[0] = (float)valueToJava2D(tick.getValue(), dataArea, edge);
/*  600 */       result[1] = (float)(cursor + insets.getTop() + 2.0D);
/*  602 */     } else if (edge == RectangleEdge.LEFT) {
/*  603 */       result[0] = (float)(cursor - insets.getLeft() - 2.0D);
/*  604 */       result[1] = (float)valueToJava2D(tick.getValue(), dataArea, edge);
/*  606 */     } else if (edge == RectangleEdge.RIGHT) {
/*  607 */       result[0] = (float)(cursor + insets.getRight() + 2.0D);
/*  608 */       result[1] = (float)valueToJava2D(tick.getValue(), dataArea, edge);
/*      */     } 
/*  610 */     return result;
/*      */   }
/*      */   
/*      */   protected AxisState drawTickMarksAndLabels(Graphics2D g2, double cursor, Rectangle2D plotArea, Rectangle2D dataArea, RectangleEdge edge) {
/*  630 */     AxisState state = new AxisState(cursor);
/*  632 */     if (isAxisLineVisible())
/*  633 */       drawAxisLine(g2, cursor, dataArea, edge); 
/*  636 */     double ol = getTickMarkOutsideLength();
/*  637 */     double il = getTickMarkInsideLength();
/*  639 */     List ticks = refreshTicks(g2, state, dataArea, edge);
/*  640 */     state.setTicks(ticks);
/*  641 */     g2.setFont(getTickLabelFont());
/*  642 */     Iterator iterator = ticks.iterator();
/*  643 */     while (iterator.hasNext()) {
/*  644 */       ValueTick tick = iterator.next();
/*  645 */       if (isTickLabelsVisible()) {
/*  646 */         g2.setPaint(getTickLabelPaint());
/*  647 */         float[] anchorPoint = calculateAnchorPoint(tick, cursor, dataArea, edge);
/*  650 */         TextUtilities.drawRotatedString(tick.getText(), g2, anchorPoint[0], anchorPoint[1], tick.getTextAnchor(), tick.getAngle(), tick.getRotationAnchor());
/*      */       } 
/*  659 */       if (isTickMarksVisible()) {
/*  660 */         float xx = (float)valueToJava2D(tick.getValue(), dataArea, edge);
/*  663 */         Line2D mark = null;
/*  664 */         g2.setStroke(getTickMarkStroke());
/*  665 */         g2.setPaint(getTickMarkPaint());
/*  666 */         if (edge == RectangleEdge.LEFT) {
/*  667 */           mark = new Line2D.Double(cursor - ol, xx, cursor + il, xx);
/*  669 */         } else if (edge == RectangleEdge.RIGHT) {
/*  670 */           mark = new Line2D.Double(cursor + ol, xx, cursor - il, xx);
/*  672 */         } else if (edge == RectangleEdge.TOP) {
/*  673 */           mark = new Line2D.Double(xx, cursor - ol, xx, cursor + il);
/*  675 */         } else if (edge == RectangleEdge.BOTTOM) {
/*  676 */           mark = new Line2D.Double(xx, cursor + ol, xx, cursor - il);
/*      */         } 
/*  678 */         g2.draw(mark);
/*      */       } 
/*      */     } 
/*  684 */     double used = 0.0D;
/*  685 */     if (isTickLabelsVisible())
/*  686 */       if (edge == RectangleEdge.LEFT) {
/*  687 */         used += findMaximumTickLabelWidth(ticks, g2, plotArea, isVerticalTickLabels());
/*  690 */         state.cursorLeft(used);
/*  692 */       } else if (edge == RectangleEdge.RIGHT) {
/*  693 */         used = findMaximumTickLabelWidth(ticks, g2, plotArea, isVerticalTickLabels());
/*  696 */         state.cursorRight(used);
/*  698 */       } else if (edge == RectangleEdge.TOP) {
/*  699 */         used = findMaximumTickLabelHeight(ticks, g2, plotArea, isVerticalTickLabels());
/*  702 */         state.cursorUp(used);
/*  704 */       } else if (edge == RectangleEdge.BOTTOM) {
/*  705 */         used = findMaximumTickLabelHeight(ticks, g2, plotArea, isVerticalTickLabels());
/*  708 */         state.cursorDown(used);
/*      */       }  
/*  712 */     return state;
/*      */   }
/*      */   
/*      */   public AxisSpace reserveSpace(Graphics2D g2, Plot plot, Rectangle2D plotArea, RectangleEdge edge, AxisSpace space) {
/*  732 */     if (space == null)
/*  733 */       space = new AxisSpace(); 
/*  737 */     if (!isVisible())
/*  738 */       return space; 
/*  742 */     double dimension = getFixedDimension();
/*  743 */     if (dimension > 0.0D)
/*  744 */       space.ensureAtLeast(dimension, edge); 
/*  748 */     double tickLabelHeight = 0.0D;
/*  749 */     double tickLabelWidth = 0.0D;
/*  750 */     if (isTickLabelsVisible()) {
/*  751 */       g2.setFont(getTickLabelFont());
/*  752 */       List ticks = refreshTicks(g2, new AxisState(), plotArea, edge);
/*  753 */       if (RectangleEdge.isTopOrBottom(edge)) {
/*  754 */         tickLabelHeight = findMaximumTickLabelHeight(ticks, g2, plotArea, isVerticalTickLabels());
/*  758 */       } else if (RectangleEdge.isLeftOrRight(edge)) {
/*  759 */         tickLabelWidth = findMaximumTickLabelWidth(ticks, g2, plotArea, isVerticalTickLabels());
/*      */       } 
/*      */     } 
/*  766 */     Rectangle2D labelEnclosure = getLabelEnclosure(g2, edge);
/*  767 */     double labelHeight = 0.0D;
/*  768 */     double labelWidth = 0.0D;
/*  769 */     if (RectangleEdge.isTopOrBottom(edge)) {
/*  770 */       labelHeight = labelEnclosure.getHeight();
/*  771 */       space.add(labelHeight + tickLabelHeight, edge);
/*  773 */     } else if (RectangleEdge.isLeftOrRight(edge)) {
/*  774 */       labelWidth = labelEnclosure.getWidth();
/*  775 */       space.add(labelWidth + tickLabelWidth, edge);
/*      */     } 
/*  778 */     return space;
/*      */   }
/*      */   
/*      */   protected double findMaximumTickLabelHeight(List ticks, Graphics2D g2, Rectangle2D drawArea, boolean vertical) {
/*  798 */     RectangleInsets insets = getTickLabelInsets();
/*  799 */     Font font = getTickLabelFont();
/*  800 */     double maxHeight = 0.0D;
/*  801 */     if (vertical) {
/*  802 */       FontMetrics fm = g2.getFontMetrics(font);
/*  803 */       Iterator iterator = ticks.iterator();
/*  804 */       while (iterator.hasNext()) {
/*  805 */         Tick tick = iterator.next();
/*  806 */         Rectangle2D labelBounds = TextUtilities.getTextBounds(tick.getText(), g2, fm);
/*  809 */         if (labelBounds.getWidth() + insets.getTop() + insets.getBottom() > maxHeight)
/*  811 */           maxHeight = labelBounds.getWidth() + insets.getTop() + insets.getBottom(); 
/*      */       } 
/*      */     } else {
/*  817 */       LineMetrics metrics = font.getLineMetrics("ABCxyz", g2.getFontRenderContext());
/*  820 */       maxHeight = metrics.getHeight() + insets.getTop() + insets.getBottom();
/*      */     } 
/*  823 */     return maxHeight;
/*      */   }
/*      */   
/*      */   protected double findMaximumTickLabelWidth(List ticks, Graphics2D g2, Rectangle2D drawArea, boolean vertical) {
/*  843 */     RectangleInsets insets = getTickLabelInsets();
/*  844 */     Font font = getTickLabelFont();
/*  845 */     double maxWidth = 0.0D;
/*  846 */     if (!vertical) {
/*  847 */       FontMetrics fm = g2.getFontMetrics(font);
/*  848 */       Iterator iterator = ticks.iterator();
/*  849 */       while (iterator.hasNext()) {
/*  850 */         Tick tick = iterator.next();
/*  851 */         Rectangle2D labelBounds = TextUtilities.getTextBounds(tick.getText(), g2, fm);
/*  854 */         if (labelBounds.getWidth() + insets.getLeft() + insets.getRight() > maxWidth)
/*  856 */           maxWidth = labelBounds.getWidth() + insets.getLeft() + insets.getRight(); 
/*      */       } 
/*      */     } else {
/*  862 */       LineMetrics metrics = font.getLineMetrics("ABCxyz", g2.getFontRenderContext());
/*  865 */       maxWidth = metrics.getHeight() + insets.getTop() + insets.getBottom();
/*      */     } 
/*  868 */     return maxWidth;
/*      */   }
/*      */   
/*      */   public boolean isInverted() {
/*  882 */     return this.inverted;
/*      */   }
/*      */   
/*      */   public void setInverted(boolean flag) {
/*  893 */     if (this.inverted != flag) {
/*  894 */       this.inverted = flag;
/*  895 */       notifyListeners(new AxisChangeEvent(this));
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean isAutoRange() {
/*  907 */     return this.autoRange;
/*      */   }
/*      */   
/*      */   public void setAutoRange(boolean auto) {
/*  918 */     setAutoRange(auto, true);
/*      */   }
/*      */   
/*      */   protected void setAutoRange(boolean auto, boolean notify) {
/*  929 */     if (this.autoRange != auto) {
/*  930 */       this.autoRange = auto;
/*  931 */       if (this.autoRange)
/*  932 */         autoAdjustRange(); 
/*  934 */       if (notify)
/*  935 */         notifyListeners(new AxisChangeEvent(this)); 
/*      */     } 
/*      */   }
/*      */   
/*      */   public double getAutoRangeMinimumSize() {
/*  947 */     return this.autoRangeMinimumSize;
/*      */   }
/*      */   
/*      */   public void setAutoRangeMinimumSize(double size) {
/*  957 */     setAutoRangeMinimumSize(size, true);
/*      */   }
/*      */   
/*      */   public void setAutoRangeMinimumSize(double size, boolean notify) {
/*  973 */     if (size <= 0.0D)
/*  974 */       throw new IllegalArgumentException("NumberAxis.setAutoRangeMinimumSize(double): must be > 0.0."); 
/*  979 */     if (this.autoRangeMinimumSize != size) {
/*  980 */       this.autoRangeMinimumSize = size;
/*  981 */       if (this.autoRange)
/*  982 */         autoAdjustRange(); 
/*  984 */       if (notify)
/*  985 */         notifyListeners(new AxisChangeEvent(this)); 
/*      */     } 
/*      */   }
/*      */   
/*      */   public double getLowerMargin() {
/* 1000 */     return this.lowerMargin;
/*      */   }
/*      */   
/*      */   public void setLowerMargin(double margin) {
/* 1012 */     this.lowerMargin = margin;
/* 1013 */     if (isAutoRange())
/* 1014 */       autoAdjustRange(); 
/* 1016 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */   public double getUpperMargin() {
/* 1028 */     return this.upperMargin;
/*      */   }
/*      */   
/*      */   public void setUpperMargin(double margin) {
/* 1040 */     this.upperMargin = margin;
/* 1041 */     if (isAutoRange())
/* 1042 */       autoAdjustRange(); 
/* 1044 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */   public double getFixedAutoRange() {
/* 1053 */     return this.fixedAutoRange;
/*      */   }
/*      */   
/*      */   public void setFixedAutoRange(double length) {
/* 1063 */     this.fixedAutoRange = length;
/* 1064 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */   public double getLowerBound() {
/* 1074 */     return this.range.getLowerBound();
/*      */   }
/*      */   
/*      */   public void setLowerBound(double min) {
/* 1084 */     if (this.range.getUpperBound() > min) {
/* 1085 */       setRange(new Range(min, this.range.getUpperBound()));
/*      */     } else {
/* 1088 */       setRange(new Range(min, min + 1.0D));
/*      */     } 
/*      */   }
/*      */   
/*      */   public double getUpperBound() {
/* 1098 */     return this.range.getUpperBound();
/*      */   }
/*      */   
/*      */   public void setUpperBound(double max) {
/* 1109 */     if (this.range.getLowerBound() < max) {
/* 1110 */       setRange(new Range(this.range.getLowerBound(), max));
/*      */     } else {
/* 1113 */       setRange(max - 1.0D, max);
/*      */     } 
/*      */   }
/*      */   
/*      */   public Range getRange() {
/* 1124 */     return this.range;
/*      */   }
/*      */   
/*      */   public void setRange(Range range) {
/* 1136 */     setRange(range, true, true);
/*      */   }
/*      */   
/*      */   public void setRange(Range range, boolean turnOffAutoRange, boolean notify) {
/* 1152 */     if (range == null)
/* 1153 */       throw new IllegalArgumentException("Null 'range' argument."); 
/* 1155 */     if (turnOffAutoRange)
/* 1156 */       this.autoRange = false; 
/* 1158 */     this.range = range;
/* 1159 */     if (notify)
/* 1160 */       notifyListeners(new AxisChangeEvent(this)); 
/*      */   }
/*      */   
/*      */   public void setRange(double lower, double upper) {
/* 1173 */     setRange(new Range(lower, upper));
/*      */   }
/*      */   
/*      */   public void setRangeWithMargins(Range range) {
/* 1184 */     setRangeWithMargins(range, true, true);
/*      */   }
/*      */   
/*      */   public void setRangeWithMargins(Range range, boolean turnOffAutoRange, boolean notify) {
/* 1202 */     if (range == null)
/* 1203 */       throw new IllegalArgumentException("Null 'range' argument."); 
/* 1205 */     setRange(Range.expand(range, getLowerMargin(), getUpperMargin()), turnOffAutoRange, notify);
/*      */   }
/*      */   
/*      */   public void setRangeWithMargins(double lower, double upper) {
/* 1220 */     setRangeWithMargins(new Range(lower, upper));
/*      */   }
/*      */   
/*      */   public void setRangeAboutValue(double value, double length) {
/* 1231 */     setRange(new Range(value - length / 2.0D, value + length / 2.0D));
/*      */   }
/*      */   
/*      */   public boolean isAutoTickUnitSelection() {
/* 1242 */     return this.autoTickUnitSelection;
/*      */   }
/*      */   
/*      */   public void setAutoTickUnitSelection(boolean flag) {
/* 1253 */     setAutoTickUnitSelection(flag, true);
/*      */   }
/*      */   
/*      */   public void setAutoTickUnitSelection(boolean flag, boolean notify) {
/* 1265 */     if (this.autoTickUnitSelection != flag) {
/* 1266 */       this.autoTickUnitSelection = flag;
/* 1267 */       if (notify)
/* 1268 */         notifyListeners(new AxisChangeEvent(this)); 
/*      */     } 
/*      */   }
/*      */   
/*      */   public TickUnitSource getStandardTickUnits() {
/* 1279 */     return this.standardTickUnits;
/*      */   }
/*      */   
/*      */   public void setStandardTickUnits(TickUnitSource source) {
/* 1293 */     this.standardTickUnits = source;
/* 1294 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */   public abstract double valueToJava2D(double paramDouble, Rectangle2D paramRectangle2D, RectangleEdge paramRectangleEdge);
/*      */   
/*      */   public double lengthToJava2D(double length, Rectangle2D area, RectangleEdge edge) {
/* 1324 */     double zero = valueToJava2D(0.0D, area, edge);
/* 1325 */     double l = valueToJava2D(length, area, edge);
/* 1326 */     return Math.abs(l - zero);
/*      */   }
/*      */   
/*      */   public abstract double java2DToValue(double paramDouble, Rectangle2D paramRectangle2D, RectangleEdge paramRectangleEdge);
/*      */   
/*      */   protected abstract void autoAdjustRange();
/*      */   
/*      */   public void centerRange(double value) {
/* 1359 */     double central = this.range.getCentralValue();
/* 1360 */     Range adjusted = new Range(this.range.getLowerBound() + value - central, this.range.getUpperBound() + value - central);
/* 1364 */     setRange(adjusted);
/*      */   }
/*      */   
/*      */   public void resizeRange(double percent) {
/* 1379 */     resizeRange(percent, this.range.getCentralValue());
/*      */   }
/*      */   
/*      */   public void resizeRange(double percent, double anchorValue) {
/* 1395 */     if (percent > 0.0D) {
/* 1396 */       double halfLength = this.range.getLength() * percent / 2.0D;
/* 1397 */       Range adjusted = new Range(anchorValue - halfLength, anchorValue + halfLength);
/* 1400 */       setRange(adjusted);
/*      */     } else {
/* 1403 */       setAutoRange(true);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void zoomRange(double lowerPercent, double upperPercent) {
/* 1415 */     double start = this.range.getLowerBound();
/* 1416 */     double length = this.range.getLength();
/* 1417 */     Range adjusted = null;
/* 1418 */     if (isInverted()) {
/* 1419 */       adjusted = new Range(start + length * (1.0D - upperPercent), start + length * (1.0D - lowerPercent));
/*      */     } else {
/* 1423 */       adjusted = new Range(start + length * lowerPercent, start + length * upperPercent);
/*      */     } 
/* 1427 */     setRange(adjusted);
/*      */   }
/*      */   
/*      */   protected int getAutoTickIndex() {
/* 1436 */     return this.autoTickIndex;
/*      */   }
/*      */   
/*      */   protected void setAutoTickIndex(int index) {
/* 1445 */     this.autoTickIndex = index;
/*      */   }
/*      */   
/*      */   public boolean equals(Object obj) {
/* 1457 */     if (obj == this)
/* 1458 */       return true; 
/* 1461 */     if (!(obj instanceof ValueAxis))
/* 1462 */       return false; 
/* 1464 */     if (!super.equals(obj))
/* 1465 */       return false; 
/* 1467 */     ValueAxis that = (ValueAxis)obj;
/* 1470 */     if (this.positiveArrowVisible != that.positiveArrowVisible)
/* 1471 */       return false; 
/* 1473 */     if (this.negativeArrowVisible != that.negativeArrowVisible)
/* 1474 */       return false; 
/* 1476 */     if (this.inverted != that.inverted)
/* 1477 */       return false; 
/* 1479 */     if (!ObjectUtilities.equal(this.range, that.range))
/* 1480 */       return false; 
/* 1482 */     if (this.autoRange != that.autoRange)
/* 1483 */       return false; 
/* 1485 */     if (this.autoRangeMinimumSize != that.autoRangeMinimumSize)
/* 1486 */       return false; 
/* 1488 */     if (this.upperMargin != that.upperMargin)
/* 1489 */       return false; 
/* 1491 */     if (this.lowerMargin != that.lowerMargin)
/* 1492 */       return false; 
/* 1494 */     if (this.fixedAutoRange != that.fixedAutoRange)
/* 1495 */       return false; 
/* 1497 */     if (this.autoTickUnitSelection != that.autoTickUnitSelection)
/* 1498 */       return false; 
/* 1500 */     if (!ObjectUtilities.equal(this.standardTickUnits, that.standardTickUnits))
/* 1502 */       return false; 
/* 1504 */     if (this.verticalTickLabels != that.verticalTickLabels)
/* 1505 */       return false; 
/* 1508 */     return true;
/*      */   }
/*      */   
/*      */   public Object clone() throws CloneNotSupportedException {
/* 1521 */     ValueAxis clone = (ValueAxis)super.clone();
/* 1522 */     return clone;
/*      */   }
/*      */   
/*      */   private void writeObject(ObjectOutputStream stream) throws IOException {
/* 1534 */     stream.defaultWriteObject();
/* 1535 */     SerialUtilities.writeShape(this.upArrow, stream);
/* 1536 */     SerialUtilities.writeShape(this.downArrow, stream);
/* 1537 */     SerialUtilities.writeShape(this.leftArrow, stream);
/* 1538 */     SerialUtilities.writeShape(this.rightArrow, stream);
/*      */   }
/*      */   
/*      */   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
/* 1553 */     stream.defaultReadObject();
/* 1554 */     this.upArrow = SerialUtilities.readShape(stream);
/* 1555 */     this.downArrow = SerialUtilities.readShape(stream);
/* 1556 */     this.leftArrow = SerialUtilities.readShape(stream);
/* 1557 */     this.rightArrow = SerialUtilities.readShape(stream);
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\axis\ValueAxis.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */