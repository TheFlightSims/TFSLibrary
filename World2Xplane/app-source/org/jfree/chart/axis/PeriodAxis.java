/*      */ package org.jfree.chart.axis;
/*      */ 
/*      */ import java.awt.BasicStroke;
/*      */ import java.awt.Color;
/*      */ import java.awt.FontMetrics;
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.Paint;
/*      */ import java.awt.Stroke;
/*      */ import java.awt.geom.Line2D;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import java.lang.reflect.Constructor;
/*      */ import java.text.DateFormat;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collections;
/*      */ import java.util.Date;
/*      */ import java.util.List;
/*      */ import java.util.TimeZone;
/*      */ import org.jfree.chart.event.AxisChangeEvent;
/*      */ import org.jfree.chart.plot.Plot;
/*      */ import org.jfree.chart.plot.PlotRenderingInfo;
/*      */ import org.jfree.chart.plot.ValueAxisPlot;
/*      */ import org.jfree.data.Range;
/*      */ import org.jfree.data.time.Day;
/*      */ import org.jfree.data.time.Month;
/*      */ import org.jfree.data.time.RegularTimePeriod;
/*      */ import org.jfree.data.time.Year;
/*      */ import org.jfree.io.SerialUtilities;
/*      */ import org.jfree.text.TextUtilities;
/*      */ import org.jfree.ui.RectangleEdge;
/*      */ import org.jfree.ui.TextAnchor;
/*      */ import org.jfree.util.PublicCloneable;
/*      */ 
/*      */ public class PeriodAxis extends ValueAxis implements Cloneable, PublicCloneable, Serializable {
/*      */   private static final long serialVersionUID = 8353295532075872069L;
/*      */   
/*      */   private RegularTimePeriod first;
/*      */   
/*      */   private RegularTimePeriod last;
/*      */   
/*      */   private TimeZone timeZone;
/*      */   
/*      */   private Class autoRangeTimePeriodClass;
/*      */   
/*      */   private Class majorTickTimePeriodClass;
/*      */   
/*      */   private boolean minorTickMarksVisible;
/*      */   
/*      */   private Class minorTickTimePeriodClass;
/*      */   
/*  140 */   private float minorTickMarkInsideLength = 0.0F;
/*      */   
/*  143 */   private float minorTickMarkOutsideLength = 2.0F;
/*      */   
/*  146 */   private transient Stroke minorTickMarkStroke = new BasicStroke(0.5F);
/*      */   
/*  149 */   private transient Paint minorTickMarkPaint = Color.black;
/*      */   
/*      */   private PeriodAxisLabelInfo[] labelInfo;
/*      */   
/*      */   public PeriodAxis(String label) {
/*  160 */     this(label, (RegularTimePeriod)new Day(), (RegularTimePeriod)new Day());
/*      */   }
/*      */   
/*      */   public PeriodAxis(String label, RegularTimePeriod first, RegularTimePeriod last) {
/*  174 */     this(label, first, last, TimeZone.getDefault());
/*      */   }
/*      */   
/*      */   public PeriodAxis(String label, RegularTimePeriod first, RegularTimePeriod last, TimeZone timeZone) {
/*  191 */     super(label, (TickUnitSource)null);
/*  192 */     this.first = first;
/*  193 */     this.last = last;
/*  194 */     this.timeZone = timeZone;
/*  195 */     this.autoRangeTimePeriodClass = first.getClass();
/*  196 */     this.majorTickTimePeriodClass = first.getClass();
/*  197 */     this.minorTickMarksVisible = false;
/*  198 */     this.minorTickTimePeriodClass = RegularTimePeriod.downsize(this.majorTickTimePeriodClass);
/*  201 */     setAutoRange(true);
/*  202 */     this.labelInfo = new PeriodAxisLabelInfo[2];
/*  203 */     this.labelInfo[0] = new PeriodAxisLabelInfo(Month.class, new SimpleDateFormat("MMM"));
/*  206 */     this.labelInfo[1] = new PeriodAxisLabelInfo(Year.class, new SimpleDateFormat("yyyy"));
/*      */   }
/*      */   
/*      */   public RegularTimePeriod getFirst() {
/*  218 */     return this.first;
/*      */   }
/*      */   
/*      */   public void setFirst(RegularTimePeriod first) {
/*  228 */     if (first == null)
/*  229 */       throw new IllegalArgumentException("Null 'first' argument."); 
/*  231 */     this.first = first;
/*  232 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */   public RegularTimePeriod getLast() {
/*  241 */     return this.last;
/*      */   }
/*      */   
/*      */   public void setLast(RegularTimePeriod last) {
/*  251 */     if (last == null)
/*  252 */       throw new IllegalArgumentException("Null 'last' argument."); 
/*  254 */     this.last = last;
/*  255 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */   public TimeZone getTimeZone() {
/*  265 */     return this.timeZone;
/*      */   }
/*      */   
/*      */   public void setTimeZone(TimeZone zone) {
/*  275 */     if (zone == null)
/*  276 */       throw new IllegalArgumentException("Null 'zone' argument."); 
/*  278 */     this.timeZone = zone;
/*  279 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Class getAutoRangeTimePeriodClass() {
/*  289 */     return this.autoRangeTimePeriodClass;
/*      */   }
/*      */   
/*      */   public void setAutoRangeTimePeriodClass(Class c) {
/*  300 */     if (c == null)
/*  301 */       throw new IllegalArgumentException("Null 'c' argument."); 
/*  303 */     this.autoRangeTimePeriodClass = c;
/*  304 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Class getMajorTickTimePeriodClass() {
/*  313 */     return this.majorTickTimePeriodClass;
/*      */   }
/*      */   
/*      */   public void setMajorTickTimePeriodClass(Class c) {
/*  324 */     if (c == null)
/*  325 */       throw new IllegalArgumentException("Null 'c' argument."); 
/*  327 */     this.majorTickTimePeriodClass = c;
/*  328 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */   public boolean isMinorTickMarksVisible() {
/*  338 */     return this.minorTickMarksVisible;
/*      */   }
/*      */   
/*      */   public void setMinorTickMarksVisible(boolean visible) {
/*  349 */     this.minorTickMarksVisible = visible;
/*  350 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Class getMinorTickTimePeriodClass() {
/*  359 */     return this.minorTickTimePeriodClass;
/*      */   }
/*      */   
/*      */   public void setMinorTickTimePeriodClass(Class c) {
/*  370 */     if (c == null)
/*  371 */       throw new IllegalArgumentException("Null 'c' argument."); 
/*  373 */     this.minorTickTimePeriodClass = c;
/*  374 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Stroke getMinorTickMarkStroke() {
/*  384 */     return this.minorTickMarkStroke;
/*      */   }
/*      */   
/*      */   public void setMinorTickMarkStroke(Stroke stroke) {
/*  395 */     if (stroke == null)
/*  396 */       throw new IllegalArgumentException("Null 'stroke' argument."); 
/*  398 */     this.minorTickMarkStroke = stroke;
/*  399 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */   public Paint getMinorTickMarkPaint() {
/*  409 */     return this.minorTickMarkPaint;
/*      */   }
/*      */   
/*      */   public void setMinorTickMarkPaint(Paint paint) {
/*  420 */     if (paint == null)
/*  421 */       throw new IllegalArgumentException("Null 'paint' argument."); 
/*  423 */     this.minorTickMarkPaint = paint;
/*  424 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */   public float getMinorTickMarkInsideLength() {
/*  433 */     return this.minorTickMarkInsideLength;
/*      */   }
/*      */   
/*      */   public void setMinorTickMarkInsideLength(float length) {
/*  443 */     this.minorTickMarkInsideLength = length;
/*  444 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */   public float getMinorTickMarkOutsideLength() {
/*  453 */     return this.minorTickMarkOutsideLength;
/*      */   }
/*      */   
/*      */   public void setMinorTickMarkOutsideLength(float length) {
/*  463 */     this.minorTickMarkOutsideLength = length;
/*  464 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */   public PeriodAxisLabelInfo[] getLabelInfo() {
/*  473 */     return this.labelInfo;
/*      */   }
/*      */   
/*      */   public void setLabelInfo(PeriodAxisLabelInfo[] info) {
/*  482 */     this.labelInfo = info;
/*      */   }
/*      */   
/*      */   public Range getRange() {
/*  492 */     return new Range(this.first.getFirstMillisecond(this.timeZone), this.last.getLastMillisecond(this.timeZone));
/*      */   }
/*      */   
/*      */   public void setRange(Range range, boolean turnOffAutoRange, boolean notify) {
/*  511 */     super.setRange(range, turnOffAutoRange, false);
/*  512 */     long upper = Math.round(range.getUpperBound());
/*  513 */     long lower = Math.round(range.getLowerBound());
/*  514 */     this.first = createInstance(this.autoRangeTimePeriodClass, new Date(lower), this.timeZone);
/*  517 */     this.last = createInstance(this.autoRangeTimePeriodClass, new Date(upper), this.timeZone);
/*      */   }
/*      */   
/*      */   public void configure() {
/*  527 */     if (isAutoRange())
/*  528 */       autoAdjustRange(); 
/*      */   }
/*      */   
/*      */   public AxisSpace reserveSpace(Graphics2D g2, Plot plot, Rectangle2D plotArea, RectangleEdge edge, AxisSpace space) {
/*  549 */     if (space == null)
/*  550 */       space = new AxisSpace(); 
/*  554 */     if (!isVisible())
/*  555 */       return space; 
/*  559 */     double dimension = getFixedDimension();
/*  560 */     if (dimension > 0.0D)
/*  561 */       space.ensureAtLeast(dimension, edge); 
/*  565 */     Rectangle2D labelEnclosure = getLabelEnclosure(g2, edge);
/*  566 */     double labelHeight = 0.0D;
/*  567 */     double labelWidth = 0.0D;
/*  568 */     double tickLabelBandsDimension = 0.0D;
/*  570 */     for (int i = 0; i < this.labelInfo.length; i++) {
/*  571 */       PeriodAxisLabelInfo info = this.labelInfo[i];
/*  572 */       FontMetrics fm = g2.getFontMetrics(info.getLabelFont());
/*  573 */       tickLabelBandsDimension += info.getPadding().extendHeight(fm.getHeight());
/*      */     } 
/*  577 */     if (RectangleEdge.isTopOrBottom(edge)) {
/*  578 */       labelHeight = labelEnclosure.getHeight();
/*  579 */       space.add(labelHeight + tickLabelBandsDimension, edge);
/*  581 */     } else if (RectangleEdge.isLeftOrRight(edge)) {
/*  582 */       labelWidth = labelEnclosure.getWidth();
/*  583 */       space.add(labelWidth + tickLabelBandsDimension, edge);
/*      */     } 
/*  587 */     double tickMarkSpace = 0.0D;
/*  588 */     if (isTickMarksVisible())
/*  589 */       tickMarkSpace = getTickMarkOutsideLength(); 
/*  591 */     if (this.minorTickMarksVisible)
/*  592 */       tickMarkSpace = Math.max(tickMarkSpace, this.minorTickMarkOutsideLength); 
/*  596 */     space.add(tickMarkSpace, edge);
/*  597 */     return space;
/*      */   }
/*      */   
/*      */   public AxisState draw(Graphics2D g2, double cursor, Rectangle2D plotArea, Rectangle2D dataArea, RectangleEdge edge, PlotRenderingInfo plotState) {
/*  621 */     AxisState axisState = new AxisState(cursor);
/*  622 */     if (isAxisLineVisible())
/*  623 */       drawAxisLine(g2, cursor, dataArea, edge); 
/*  625 */     drawTickMarks(g2, axisState, dataArea, edge);
/*  626 */     for (int band = 0; band < this.labelInfo.length; band++)
/*  627 */       axisState = drawTickLabels(band, g2, axisState, dataArea, edge); 
/*  632 */     axisState = drawLabel(getLabel(), g2, plotArea, dataArea, edge, axisState);
/*  635 */     return axisState;
/*      */   }
/*      */   
/*      */   protected void drawTickMarks(Graphics2D g2, AxisState state, Rectangle2D dataArea, RectangleEdge edge) {
/*  650 */     if (RectangleEdge.isTopOrBottom(edge)) {
/*  651 */       drawTickMarksHorizontal(g2, state, dataArea, edge);
/*  653 */     } else if (RectangleEdge.isLeftOrRight(edge)) {
/*  654 */       drawTickMarksVertical(g2, state, dataArea, edge);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void drawTickMarksHorizontal(Graphics2D g2, AxisState state, Rectangle2D dataArea, RectangleEdge edge) {
/*  670 */     List ticks = new ArrayList();
/*  671 */     double x0 = dataArea.getX();
/*  672 */     double y0 = state.getCursor();
/*  673 */     double insideLength = getTickMarkInsideLength();
/*  674 */     double outsideLength = getTickMarkOutsideLength();
/*  675 */     RegularTimePeriod t = RegularTimePeriod.createInstance(this.majorTickTimePeriodClass, this.first.getStart(), getTimeZone());
/*  678 */     long t0 = t.getFirstMillisecond(getTimeZone());
/*  679 */     Line2D inside = null;
/*  680 */     Line2D outside = null;
/*  681 */     long firstOnAxis = getFirst().getFirstMillisecond(getTimeZone());
/*  682 */     long lastOnAxis = getLast().getLastMillisecond(getTimeZone());
/*  683 */     while (t0 <= lastOnAxis) {
/*  684 */       ticks.add(new NumberTick(new Double(t0), "", TextAnchor.CENTER, TextAnchor.CENTER, 0.0D));
/*  685 */       x0 = valueToJava2D(t0, dataArea, edge);
/*  686 */       if (edge == RectangleEdge.TOP) {
/*  687 */         inside = new Line2D.Double(x0, y0, x0, y0 + insideLength);
/*  688 */         outside = new Line2D.Double(x0, y0, x0, y0 - outsideLength);
/*  690 */       } else if (edge == RectangleEdge.BOTTOM) {
/*  691 */         inside = new Line2D.Double(x0, y0, x0, y0 - insideLength);
/*  692 */         outside = new Line2D.Double(x0, y0, x0, y0 + outsideLength);
/*      */       } 
/*  694 */       if (t0 > firstOnAxis) {
/*  695 */         g2.setPaint(getTickMarkPaint());
/*  696 */         g2.setStroke(getTickMarkStroke());
/*  697 */         g2.draw(inside);
/*  698 */         g2.draw(outside);
/*      */       } 
/*  701 */       if (this.minorTickMarksVisible) {
/*  702 */         RegularTimePeriod tminor = RegularTimePeriod.createInstance(this.minorTickTimePeriodClass, new Date(t0), getTimeZone());
/*  705 */         long tt0 = tminor.getFirstMillisecond(getTimeZone());
/*  707 */         while (tt0 < t.getLastMillisecond(getTimeZone()) && tt0 < lastOnAxis) {
/*  708 */           double xx0 = valueToJava2D(tt0, dataArea, edge);
/*  709 */           if (edge == RectangleEdge.TOP) {
/*  710 */             inside = new Line2D.Double(xx0, y0, xx0, y0 + this.minorTickMarkInsideLength);
/*  713 */             outside = new Line2D.Double(xx0, y0, xx0, y0 - this.minorTickMarkOutsideLength);
/*  717 */           } else if (edge == RectangleEdge.BOTTOM) {
/*  718 */             inside = new Line2D.Double(xx0, y0, xx0, y0 - this.minorTickMarkInsideLength);
/*  721 */             outside = new Line2D.Double(xx0, y0, xx0, y0 + this.minorTickMarkOutsideLength);
/*      */           } 
/*  725 */           if (tt0 >= firstOnAxis) {
/*  726 */             g2.setPaint(this.minorTickMarkPaint);
/*  727 */             g2.setStroke(this.minorTickMarkStroke);
/*  728 */             g2.draw(inside);
/*  729 */             g2.draw(outside);
/*      */           } 
/*  731 */           tminor = tminor.next();
/*  732 */           tt0 = tminor.getFirstMillisecond(getTimeZone());
/*      */         } 
/*      */       } 
/*  735 */       t = t.next();
/*  736 */       t0 = t.getFirstMillisecond(getTimeZone());
/*      */     } 
/*  738 */     if (edge == RectangleEdge.TOP) {
/*  739 */       state.cursorUp(Math.max(outsideLength, this.minorTickMarkOutsideLength));
/*  743 */     } else if (edge == RectangleEdge.BOTTOM) {
/*  744 */       state.cursorDown(Math.max(outsideLength, this.minorTickMarkOutsideLength));
/*      */     } 
/*  748 */     state.setTicks(ticks);
/*      */   }
/*      */   
/*      */   protected void drawTickMarksVertical(Graphics2D g2, AxisState state, Rectangle2D dataArea, RectangleEdge edge) {}
/*      */   
/*      */   protected AxisState drawTickLabels(int band, Graphics2D g2, AxisState state, Rectangle2D dataArea, RectangleEdge edge) {
/*  781 */     double delta1 = 0.0D;
/*  782 */     FontMetrics fm = g2.getFontMetrics(this.labelInfo[band].getLabelFont());
/*  783 */     if (edge == RectangleEdge.BOTTOM) {
/*  784 */       delta1 = this.labelInfo[band].getPadding().calculateTopOutset(fm.getHeight());
/*  788 */     } else if (edge == RectangleEdge.TOP) {
/*  789 */       delta1 = this.labelInfo[band].getPadding().calculateBottomOutset(fm.getHeight());
/*      */     } 
/*  793 */     state.moveCursor(delta1, edge);
/*  794 */     long axisMin = this.first.getFirstMillisecond(this.timeZone);
/*  795 */     long axisMax = this.last.getLastMillisecond(this.timeZone);
/*  796 */     g2.setFont(this.labelInfo[band].getLabelFont());
/*  797 */     g2.setPaint(this.labelInfo[band].getLabelPaint());
/*  800 */     RegularTimePeriod p1 = this.labelInfo[band].createInstance(new Date(axisMin), this.timeZone);
/*  803 */     RegularTimePeriod p2 = this.labelInfo[band].createInstance(new Date(axisMax), this.timeZone);
/*  806 */     String label1 = this.labelInfo[band].getDateFormat().format(new Date(p1.getMiddleMillisecond(this.timeZone)));
/*  809 */     String label2 = this.labelInfo[band].getDateFormat().format(new Date(p2.getMiddleMillisecond(this.timeZone)));
/*  812 */     Rectangle2D b1 = TextUtilities.getTextBounds(label1, g2, g2.getFontMetrics());
/*  815 */     Rectangle2D b2 = TextUtilities.getTextBounds(label2, g2, g2.getFontMetrics());
/*  818 */     double w = Math.max(b1.getWidth(), b2.getWidth());
/*  819 */     long ww = Math.round(java2DToValue(dataArea.getX() + w + 5.0D, dataArea, edge)) - axisMin;
/*  822 */     long length = p1.getLastMillisecond(this.timeZone) - p1.getFirstMillisecond(this.timeZone);
/*  824 */     int periods = (int)(ww / length) + 1;
/*  826 */     RegularTimePeriod p = this.labelInfo[band].createInstance(new Date(axisMin), this.timeZone);
/*  829 */     Rectangle2D b = null;
/*  830 */     long lastXX = 0L;
/*  831 */     float y = (float)state.getCursor();
/*  832 */     TextAnchor anchor = TextAnchor.TOP_CENTER;
/*  833 */     float yDelta = (float)b1.getHeight();
/*  834 */     if (edge == RectangleEdge.TOP) {
/*  835 */       anchor = TextAnchor.BOTTOM_CENTER;
/*  836 */       yDelta = -yDelta;
/*      */     } 
/*  838 */     while (p.getFirstMillisecond(this.timeZone) <= axisMax) {
/*  839 */       float x = (float)valueToJava2D(p.getMiddleMillisecond(this.timeZone), dataArea, edge);
/*  842 */       DateFormat df = this.labelInfo[band].getDateFormat();
/*  843 */       String label = df.format(new Date(p.getMiddleMillisecond(this.timeZone)));
/*  846 */       long first = p.getFirstMillisecond(this.timeZone);
/*  847 */       long last = p.getLastMillisecond(this.timeZone);
/*  848 */       if (last > axisMax) {
/*  851 */         Rectangle2D bb = TextUtilities.getTextBounds(label, g2, g2.getFontMetrics());
/*  854 */         if (x + bb.getWidth() / 2.0D > dataArea.getMaxX()) {
/*  855 */           float xstart = (float)valueToJava2D(Math.max(first, axisMin), dataArea, edge);
/*  858 */           if (bb.getWidth() < dataArea.getMaxX() - xstart) {
/*  859 */             x = ((float)dataArea.getMaxX() + xstart) / 2.0F;
/*      */           } else {
/*  862 */             label = null;
/*      */           } 
/*      */         } 
/*      */       } 
/*  866 */       if (first < axisMin) {
/*  869 */         Rectangle2D bb = TextUtilities.getTextBounds(label, g2, g2.getFontMetrics());
/*  872 */         if (x - bb.getWidth() / 2.0D < dataArea.getX()) {
/*  873 */           float xlast = (float)valueToJava2D(Math.min(last, axisMax), dataArea, edge);
/*  876 */           if (bb.getWidth() < xlast - dataArea.getX()) {
/*  877 */             x = (xlast + (float)dataArea.getX()) / 2.0F;
/*      */           } else {
/*  880 */             label = null;
/*      */           } 
/*      */         } 
/*      */       } 
/*  885 */       if (label != null) {
/*  886 */         g2.setPaint(this.labelInfo[band].getLabelPaint());
/*  887 */         b = TextUtilities.drawAlignedString(label, g2, x, y, anchor);
/*      */       } 
/*  889 */       if (lastXX > 0L && 
/*  890 */         this.labelInfo[band].getDrawDividers()) {
/*  891 */         long nextXX = p.getFirstMillisecond(this.timeZone);
/*  892 */         long mid = (lastXX + nextXX) / 2L;
/*  893 */         float mid2d = (float)valueToJava2D(mid, dataArea, edge);
/*  894 */         g2.setStroke(this.labelInfo[band].getDividerStroke());
/*  895 */         g2.setPaint(this.labelInfo[band].getDividerPaint());
/*  896 */         g2.draw(new Line2D.Float(mid2d, y, mid2d, y + yDelta));
/*      */       } 
/*  901 */       lastXX = last;
/*  902 */       for (int i = 0; i < periods; i++)
/*  903 */         p = p.next(); 
/*      */     } 
/*  906 */     double used = 0.0D;
/*  907 */     if (b != null) {
/*  908 */       used = b.getHeight();
/*  910 */       if (edge == RectangleEdge.BOTTOM) {
/*  911 */         used += this.labelInfo[band].getPadding().calculateBottomOutset(fm.getHeight());
/*  915 */       } else if (edge == RectangleEdge.TOP) {
/*  916 */         used += this.labelInfo[band].getPadding().calculateTopOutset(fm.getHeight());
/*      */       } 
/*      */     } 
/*  921 */     state.moveCursor(used, edge);
/*  922 */     return state;
/*      */   }
/*      */   
/*      */   public List refreshTicks(Graphics2D g2, AxisState state, Rectangle2D dataArea, RectangleEdge edge) {
/*  940 */     return Collections.EMPTY_LIST;
/*      */   }
/*      */   
/*      */   public double valueToJava2D(double value, Rectangle2D area, RectangleEdge edge) {
/*  959 */     double result = Double.NaN;
/*  960 */     double axisMin = this.first.getFirstMillisecond(this.timeZone);
/*  961 */     double axisMax = this.last.getLastMillisecond(this.timeZone);
/*  962 */     if (RectangleEdge.isTopOrBottom(edge)) {
/*  963 */       double minX = area.getX();
/*  964 */       double maxX = area.getMaxX();
/*  965 */       if (isInverted()) {
/*  966 */         result = maxX + (value - axisMin) / (axisMax - axisMin) * (minX - maxX);
/*      */       } else {
/*  970 */         result = minX + (value - axisMin) / (axisMax - axisMin) * (maxX - minX);
/*      */       } 
/*  974 */     } else if (RectangleEdge.isLeftOrRight(edge)) {
/*  975 */       double minY = area.getMinY();
/*  976 */       double maxY = area.getMaxY();
/*  977 */       if (isInverted()) {
/*  978 */         result = minY + (value - axisMin) / (axisMax - axisMin) * (maxY - minY);
/*      */       } else {
/*  982 */         result = maxY - (value - axisMin) / (axisMax - axisMin) * (maxY - minY);
/*      */       } 
/*      */     } 
/*  986 */     return result;
/*      */   }
/*      */   
/*      */   public double java2DToValue(double java2DValue, Rectangle2D area, RectangleEdge edge) {
/* 1004 */     double result = Double.NaN;
/* 1005 */     double min = 0.0D;
/* 1006 */     double max = 0.0D;
/* 1007 */     double axisMin = this.first.getFirstMillisecond(this.timeZone);
/* 1008 */     double axisMax = this.last.getLastMillisecond(this.timeZone);
/* 1009 */     if (RectangleEdge.isTopOrBottom(edge)) {
/* 1010 */       min = area.getX();
/* 1011 */       max = area.getMaxX();
/* 1013 */     } else if (RectangleEdge.isLeftOrRight(edge)) {
/* 1014 */       min = area.getMaxY();
/* 1015 */       max = area.getY();
/*      */     } 
/* 1017 */     if (isInverted()) {
/* 1018 */       result = axisMax - (java2DValue - min) / (max - min) * (axisMax - axisMin);
/*      */     } else {
/* 1022 */       result = axisMin + (java2DValue - min) / (max - min) * (axisMax - axisMin);
/*      */     } 
/* 1025 */     return result;
/*      */   }
/*      */   
/*      */   protected void autoAdjustRange() {
/* 1033 */     Plot plot = getPlot();
/* 1034 */     if (plot == null)
/*      */       return; 
/* 1038 */     if (plot instanceof ValueAxisPlot) {
/* 1039 */       ValueAxisPlot vap = (ValueAxisPlot)plot;
/* 1041 */       Range r = vap.getDataRange(this);
/* 1042 */       if (r == null)
/* 1043 */         r = new Range(0.0D, 1.0D); 
/* 1046 */       long upper = Math.round(r.getUpperBound());
/* 1047 */       long lower = Math.round(r.getLowerBound());
/* 1048 */       this.first = createInstance(this.autoRangeTimePeriodClass, new Date(lower), this.timeZone);
/* 1051 */       this.last = createInstance(this.autoRangeTimePeriodClass, new Date(upper), this.timeZone);
/* 1054 */       setRange(r, false, false);
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean equals(Object obj) {
/* 1067 */     if (obj == this)
/* 1068 */       return true; 
/* 1070 */     if (obj instanceof PeriodAxis && super.equals(obj)) {
/* 1071 */       PeriodAxis that = (PeriodAxis)obj;
/* 1072 */       if (!this.first.equals(that.first))
/* 1073 */         return false; 
/* 1075 */       if (!this.last.equals(that.last))
/* 1076 */         return false; 
/* 1078 */       if (!this.timeZone.equals(that.timeZone))
/* 1079 */         return false; 
/* 1081 */       if (!this.autoRangeTimePeriodClass.equals(that.autoRangeTimePeriodClass))
/* 1084 */         return false; 
/* 1086 */       if (isMinorTickMarksVisible() != that.isMinorTickMarksVisible())
/* 1088 */         return false; 
/* 1090 */       if (!this.majorTickTimePeriodClass.equals(that.majorTickTimePeriodClass))
/* 1092 */         return false; 
/* 1094 */       if (!this.minorTickTimePeriodClass.equals(that.minorTickTimePeriodClass))
/* 1096 */         return false; 
/* 1098 */       if (!this.minorTickMarkPaint.equals(that.minorTickMarkPaint))
/* 1099 */         return false; 
/* 1101 */       if (!this.minorTickMarkStroke.equals(that.minorTickMarkStroke))
/* 1102 */         return false; 
/* 1104 */       if (!Arrays.equals((Object[])this.labelInfo, (Object[])that.labelInfo))
/* 1105 */         return false; 
/* 1107 */       return true;
/*      */     } 
/* 1109 */     return false;
/*      */   }
/*      */   
/*      */   public int hashCode() {
/* 1118 */     if (getLabel() != null)
/* 1119 */       return getLabel().hashCode(); 
/* 1122 */     return 0;
/*      */   }
/*      */   
/*      */   public Object clone() throws CloneNotSupportedException {
/* 1135 */     PeriodAxis clone = (PeriodAxis)super.clone();
/* 1136 */     clone.timeZone = (TimeZone)this.timeZone.clone();
/* 1137 */     clone.labelInfo = new PeriodAxisLabelInfo[this.labelInfo.length];
/* 1138 */     for (int i = 0; i < this.labelInfo.length; i++)
/* 1139 */       clone.labelInfo[i] = this.labelInfo[i]; 
/* 1142 */     return clone;
/*      */   }
/*      */   
/*      */   private RegularTimePeriod createInstance(Class periodClass, Date millisecond, TimeZone zone) {
/* 1158 */     RegularTimePeriod result = null;
/*      */     try {
/* 1160 */       Constructor c = periodClass.getDeclaredConstructor(new Class[] { Date.class, TimeZone.class });
/* 1163 */       result = c.newInstance(new Object[] { millisecond, zone });
/* 1167 */     } catch (Exception e) {}
/* 1170 */     return result;
/*      */   }
/*      */   
/*      */   private void writeObject(ObjectOutputStream stream) throws IOException {
/* 1181 */     stream.defaultWriteObject();
/* 1182 */     SerialUtilities.writeStroke(this.minorTickMarkStroke, stream);
/* 1183 */     SerialUtilities.writePaint(this.minorTickMarkPaint, stream);
/*      */   }
/*      */   
/*      */   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
/* 1196 */     stream.defaultReadObject();
/* 1197 */     this.minorTickMarkStroke = SerialUtilities.readStroke(stream);
/* 1198 */     this.minorTickMarkPaint = SerialUtilities.readPaint(stream);
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\axis\PeriodAxis.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */