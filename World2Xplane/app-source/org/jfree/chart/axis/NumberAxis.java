/*      */ package org.jfree.chart.axis;
/*      */ 
/*      */ import java.awt.Font;
/*      */ import java.awt.FontMetrics;
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.font.FontRenderContext;
/*      */ import java.awt.font.LineMetrics;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.io.Serializable;
/*      */ import java.text.DecimalFormat;
/*      */ import java.text.NumberFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.List;
/*      */ import java.util.Locale;
/*      */ import org.jfree.chart.event.AxisChangeEvent;
/*      */ import org.jfree.chart.plot.Plot;
/*      */ import org.jfree.chart.plot.PlotRenderingInfo;
/*      */ import org.jfree.chart.plot.ValueAxisPlot;
/*      */ import org.jfree.data.Range;
/*      */ import org.jfree.data.RangeType;
/*      */ import org.jfree.ui.RectangleEdge;
/*      */ import org.jfree.ui.RectangleInsets;
/*      */ import org.jfree.ui.TextAnchor;
/*      */ import org.jfree.util.ObjectUtilities;
/*      */ 
/*      */ public class NumberAxis extends ValueAxis implements Cloneable, Serializable {
/*      */   private static final long serialVersionUID = 2805933088476185789L;
/*      */   
/*      */   public static final boolean DEFAULT_AUTO_RANGE_INCLUDES_ZERO = true;
/*      */   
/*      */   public static final boolean DEFAULT_AUTO_RANGE_STICKY_ZERO = true;
/*      */   
/*  139 */   public static final NumberTickUnit DEFAULT_TICK_UNIT = new NumberTickUnit(1.0D, new DecimalFormat("0"));
/*      */   
/*      */   public static final boolean DEFAULT_VERTICAL_TICK_LABELS = false;
/*      */   
/*      */   private RangeType rangeType;
/*      */   
/*      */   private boolean autoRangeIncludesZero;
/*      */   
/*      */   private boolean autoRangeStickyZero;
/*      */   
/*      */   private NumberTickUnit tickUnit;
/*      */   
/*      */   private NumberFormat numberFormatOverride;
/*      */   
/*      */   private MarkerAxisBand markerBand;
/*      */   
/*      */   public NumberAxis() {
/*  177 */     this((String)null);
/*      */   }
/*      */   
/*      */   public NumberAxis(String label) {
/*  186 */     super(label, createStandardTickUnits());
/*  187 */     this.rangeType = RangeType.FULL;
/*  188 */     this.autoRangeIncludesZero = true;
/*  189 */     this.autoRangeStickyZero = true;
/*  190 */     this.tickUnit = DEFAULT_TICK_UNIT;
/*  191 */     this.numberFormatOverride = null;
/*  192 */     this.markerBand = null;
/*      */   }
/*      */   
/*      */   public RangeType getRangeType() {
/*  201 */     return this.rangeType;
/*      */   }
/*      */   
/*      */   public void setRangeType(RangeType rangeType) {
/*  210 */     if (rangeType == null)
/*  211 */       throw new IllegalArgumentException("Null 'rangeType' argument."); 
/*  213 */     this.rangeType = rangeType;
/*  214 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */   public boolean getAutoRangeIncludesZero() {
/*  224 */     return this.autoRangeIncludesZero;
/*      */   }
/*      */   
/*      */   public void setAutoRangeIncludesZero(boolean flag) {
/*  239 */     if (this.autoRangeIncludesZero != flag) {
/*  240 */       this.autoRangeIncludesZero = flag;
/*  241 */       if (isAutoRange())
/*  242 */         autoAdjustRange(); 
/*  244 */       notifyListeners(new AxisChangeEvent(this));
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean getAutoRangeStickyZero() {
/*  255 */     return this.autoRangeStickyZero;
/*      */   }
/*      */   
/*      */   public void setAutoRangeStickyZero(boolean flag) {
/*  265 */     if (this.autoRangeStickyZero != flag) {
/*  266 */       this.autoRangeStickyZero = flag;
/*  267 */       if (isAutoRange())
/*  268 */         autoAdjustRange(); 
/*  270 */       notifyListeners(new AxisChangeEvent(this));
/*      */     } 
/*      */   }
/*      */   
/*      */   public NumberTickUnit getTickUnit() {
/*  280 */     return this.tickUnit;
/*      */   }
/*      */   
/*      */   public void setTickUnit(NumberTickUnit unit) {
/*  294 */     setTickUnit(unit, true, true);
/*      */   }
/*      */   
/*      */   public void setTickUnit(NumberTickUnit unit, boolean notify, boolean turnOffAutoSelect) {
/*  311 */     if (unit == null)
/*  312 */       throw new IllegalArgumentException("Null 'unit' argument."); 
/*  314 */     this.tickUnit = unit;
/*  315 */     if (turnOffAutoSelect)
/*  316 */       setAutoTickUnitSelection(false, false); 
/*  318 */     if (notify)
/*  319 */       notifyListeners(new AxisChangeEvent(this)); 
/*      */   }
/*      */   
/*      */   public NumberFormat getNumberFormatOverride() {
/*  331 */     return this.numberFormatOverride;
/*      */   }
/*      */   
/*      */   public void setNumberFormatOverride(NumberFormat formatter) {
/*  341 */     this.numberFormatOverride = formatter;
/*  342 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */   public MarkerAxisBand getMarkerBand() {
/*  351 */     return this.markerBand;
/*      */   }
/*      */   
/*      */   public void setMarkerBand(MarkerAxisBand band) {
/*  363 */     this.markerBand = band;
/*  364 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */   public void configure() {
/*  372 */     if (isAutoRange())
/*  373 */       autoAdjustRange(); 
/*      */   }
/*      */   
/*      */   protected void autoAdjustRange() {
/*  382 */     Plot plot = getPlot();
/*  383 */     if (plot == null)
/*      */       return; 
/*  387 */     if (plot instanceof ValueAxisPlot) {
/*  388 */       ValueAxisPlot vap = (ValueAxisPlot)plot;
/*  390 */       Range r = vap.getDataRange(this);
/*  391 */       if (r == null)
/*  392 */         r = new Range(0.0D, 1.0D); 
/*  395 */       double upper = r.getUpperBound();
/*  396 */       double lower = r.getLowerBound();
/*  397 */       if (this.rangeType == RangeType.POSITIVE) {
/*  398 */         lower = Math.max(0.0D, lower);
/*  399 */         upper = Math.max(0.0D, upper);
/*  401 */       } else if (this.rangeType == RangeType.NEGATIVE) {
/*  402 */         lower = Math.min(0.0D, lower);
/*  403 */         upper = Math.min(0.0D, upper);
/*      */       } 
/*  406 */       if (getAutoRangeIncludesZero()) {
/*  407 */         lower = Math.min(lower, 0.0D);
/*  408 */         upper = Math.max(upper, 0.0D);
/*      */       } 
/*  410 */       double range = upper - lower;
/*  413 */       double fixedAutoRange = getFixedAutoRange();
/*  414 */       if (fixedAutoRange > 0.0D) {
/*  415 */         lower = upper - fixedAutoRange;
/*      */       } else {
/*  419 */         double minRange = getAutoRangeMinimumSize();
/*  420 */         if (range < minRange) {
/*  421 */           double expand = (minRange - range) / 2.0D;
/*  422 */           upper += expand;
/*  423 */           lower -= expand;
/*  424 */           if (this.rangeType == RangeType.POSITIVE) {
/*  425 */             if (lower < 0.0D) {
/*  426 */               upper -= lower;
/*  427 */               lower = 0.0D;
/*      */             } 
/*  430 */           } else if (this.rangeType == RangeType.NEGATIVE && 
/*  431 */             upper > 0.0D) {
/*  432 */             lower -= upper;
/*  433 */             upper = 0.0D;
/*      */           } 
/*      */         } 
/*  438 */         if (getAutoRangeStickyZero()) {
/*  439 */           if (upper <= 0.0D) {
/*  440 */             upper = Math.min(0.0D, upper + getUpperMargin() * range);
/*      */           } else {
/*  443 */             upper += getUpperMargin() * range;
/*      */           } 
/*  445 */           if (lower >= 0.0D) {
/*  446 */             lower = Math.max(0.0D, lower - getLowerMargin() * range);
/*      */           } else {
/*  449 */             lower -= getLowerMargin() * range;
/*      */           } 
/*      */         } else {
/*  453 */           upper += getUpperMargin() * range;
/*  454 */           lower -= getLowerMargin() * range;
/*      */         } 
/*      */       } 
/*  458 */       setRange(new Range(lower, upper), false, false);
/*      */     } 
/*      */   }
/*      */   
/*      */   public double valueToJava2D(double value, Rectangle2D area, RectangleEdge edge) {
/*  478 */     Range range = getRange();
/*  479 */     double axisMin = range.getLowerBound();
/*  480 */     double axisMax = range.getUpperBound();
/*  482 */     double min = 0.0D;
/*  483 */     double max = 0.0D;
/*  484 */     if (RectangleEdge.isTopOrBottom(edge)) {
/*  485 */       min = area.getX();
/*  486 */       max = area.getMaxX();
/*  488 */     } else if (RectangleEdge.isLeftOrRight(edge)) {
/*  489 */       max = area.getMinY();
/*  490 */       min = area.getMaxY();
/*      */     } 
/*  492 */     if (isInverted())
/*  493 */       return max - (value - axisMin) / (axisMax - axisMin) * (max - min); 
/*  497 */     return min + (value - axisMin) / (axisMax - axisMin) * (max - min);
/*      */   }
/*      */   
/*      */   public double java2DToValue(double java2DValue, Rectangle2D area, RectangleEdge edge) {
/*  516 */     Range range = getRange();
/*  517 */     double axisMin = range.getLowerBound();
/*  518 */     double axisMax = range.getUpperBound();
/*  520 */     double min = 0.0D;
/*  521 */     double max = 0.0D;
/*  522 */     if (RectangleEdge.isTopOrBottom(edge)) {
/*  523 */       min = area.getX();
/*  524 */       max = area.getMaxX();
/*  526 */     } else if (RectangleEdge.isLeftOrRight(edge)) {
/*  527 */       min = area.getMaxY();
/*  528 */       max = area.getY();
/*      */     } 
/*  530 */     if (isInverted())
/*  531 */       return axisMax - (java2DValue - min) / (max - min) * (axisMax - axisMin); 
/*  535 */     return axisMin + (java2DValue - min) / (max - min) * (axisMax - axisMin);
/*      */   }
/*      */   
/*      */   protected double calculateLowestVisibleTickValue() {
/*  548 */     double unit = getTickUnit().getSize();
/*  549 */     double index = Math.ceil(getRange().getLowerBound() / unit);
/*  550 */     return index * unit;
/*      */   }
/*      */   
/*      */   protected double calculateHighestVisibleTickValue() {
/*  561 */     double unit = getTickUnit().getSize();
/*  562 */     double index = Math.floor(getRange().getUpperBound() / unit);
/*  563 */     return index * unit;
/*      */   }
/*      */   
/*      */   protected int calculateVisibleTickCount() {
/*  574 */     double unit = getTickUnit().getSize();
/*  575 */     Range range = getRange();
/*  576 */     return (int)(Math.floor(range.getUpperBound() / unit) - Math.ceil(range.getLowerBound() / unit) + 1.0D);
/*      */   }
/*      */   
/*      */   public AxisState draw(Graphics2D g2, double cursor, Rectangle2D plotArea, Rectangle2D dataArea, RectangleEdge edge, PlotRenderingInfo plotState) {
/*  604 */     AxisState state = null;
/*  606 */     if (!isVisible()) {
/*  607 */       state = new AxisState(cursor);
/*  610 */       List ticks = refreshTicks(g2, state, dataArea, edge);
/*  611 */       state.setTicks(ticks);
/*  612 */       return state;
/*      */     } 
/*  616 */     state = drawTickMarksAndLabels(g2, cursor, plotArea, dataArea, edge);
/*  627 */     state = drawLabel(getLabel(), g2, plotArea, dataArea, edge, state);
/*  629 */     return state;
/*      */   }
/*      */   
/*      */   public static TickUnitSource createStandardTickUnits() {
/*  644 */     TickUnits units = new TickUnits();
/*  645 */     DecimalFormat df0 = new DecimalFormat("0.00000000");
/*  646 */     DecimalFormat df1 = new DecimalFormat("0.0000000");
/*  647 */     DecimalFormat df2 = new DecimalFormat("0.000000");
/*  648 */     DecimalFormat df3 = new DecimalFormat("0.00000");
/*  649 */     DecimalFormat df4 = new DecimalFormat("0.0000");
/*  650 */     DecimalFormat df5 = new DecimalFormat("0.000");
/*  651 */     DecimalFormat df6 = new DecimalFormat("0.00");
/*  652 */     DecimalFormat df7 = new DecimalFormat("0.0");
/*  653 */     DecimalFormat df8 = new DecimalFormat("#,##0");
/*  654 */     DecimalFormat df9 = new DecimalFormat("#,###,##0");
/*  655 */     DecimalFormat df10 = new DecimalFormat("#,###,###,##0");
/*  659 */     units.add(new NumberTickUnit(1.0E-7D, df1));
/*  660 */     units.add(new NumberTickUnit(1.0E-6D, df2));
/*  661 */     units.add(new NumberTickUnit(1.0E-5D, df3));
/*  662 */     units.add(new NumberTickUnit(1.0E-4D, df4));
/*  663 */     units.add(new NumberTickUnit(0.001D, df5));
/*  664 */     units.add(new NumberTickUnit(0.01D, df6));
/*  665 */     units.add(new NumberTickUnit(0.1D, df7));
/*  666 */     units.add(new NumberTickUnit(1.0D, df8));
/*  667 */     units.add(new NumberTickUnit(10.0D, df8));
/*  668 */     units.add(new NumberTickUnit(100.0D, df8));
/*  669 */     units.add(new NumberTickUnit(1000.0D, df8));
/*  670 */     units.add(new NumberTickUnit(10000.0D, df8));
/*  671 */     units.add(new NumberTickUnit(100000.0D, df8));
/*  672 */     units.add(new NumberTickUnit(1000000.0D, df9));
/*  673 */     units.add(new NumberTickUnit(1.0E7D, df9));
/*  674 */     units.add(new NumberTickUnit(1.0E8D, df9));
/*  675 */     units.add(new NumberTickUnit(1.0E9D, df10));
/*  676 */     units.add(new NumberTickUnit(1.0E10D, df10));
/*  677 */     units.add(new NumberTickUnit(1.0E11D, df10));
/*  679 */     units.add(new NumberTickUnit(2.5E-7D, df0));
/*  680 */     units.add(new NumberTickUnit(2.5E-6D, df1));
/*  681 */     units.add(new NumberTickUnit(2.5E-5D, df2));
/*  682 */     units.add(new NumberTickUnit(2.5E-4D, df3));
/*  683 */     units.add(new NumberTickUnit(0.0025D, df4));
/*  684 */     units.add(new NumberTickUnit(0.025D, df5));
/*  685 */     units.add(new NumberTickUnit(0.25D, df6));
/*  686 */     units.add(new NumberTickUnit(2.5D, df7));
/*  687 */     units.add(new NumberTickUnit(25.0D, df8));
/*  688 */     units.add(new NumberTickUnit(250.0D, df8));
/*  689 */     units.add(new NumberTickUnit(2500.0D, df8));
/*  690 */     units.add(new NumberTickUnit(25000.0D, df8));
/*  691 */     units.add(new NumberTickUnit(250000.0D, df8));
/*  692 */     units.add(new NumberTickUnit(2500000.0D, df9));
/*  693 */     units.add(new NumberTickUnit(2.5E7D, df9));
/*  694 */     units.add(new NumberTickUnit(2.5E8D, df9));
/*  695 */     units.add(new NumberTickUnit(2.5E9D, df10));
/*  696 */     units.add(new NumberTickUnit(2.5E10D, df10));
/*  697 */     units.add(new NumberTickUnit(2.5E11D, df10));
/*  699 */     units.add(new NumberTickUnit(5.0E-7D, df1));
/*  700 */     units.add(new NumberTickUnit(5.0E-6D, df2));
/*  701 */     units.add(new NumberTickUnit(5.0E-5D, df3));
/*  702 */     units.add(new NumberTickUnit(5.0E-4D, df4));
/*  703 */     units.add(new NumberTickUnit(0.005D, df5));
/*  704 */     units.add(new NumberTickUnit(0.05D, df6));
/*  705 */     units.add(new NumberTickUnit(0.5D, df7));
/*  706 */     units.add(new NumberTickUnit(5.0D, df8));
/*  707 */     units.add(new NumberTickUnit(50.0D, df8));
/*  708 */     units.add(new NumberTickUnit(500.0D, df8));
/*  709 */     units.add(new NumberTickUnit(5000.0D, df8));
/*  710 */     units.add(new NumberTickUnit(50000.0D, df8));
/*  711 */     units.add(new NumberTickUnit(500000.0D, df8));
/*  712 */     units.add(new NumberTickUnit(5000000.0D, df9));
/*  713 */     units.add(new NumberTickUnit(5.0E7D, df9));
/*  714 */     units.add(new NumberTickUnit(5.0E8D, df9));
/*  715 */     units.add(new NumberTickUnit(5.0E9D, df10));
/*  716 */     units.add(new NumberTickUnit(5.0E10D, df10));
/*  717 */     units.add(new NumberTickUnit(5.0E11D, df10));
/*  719 */     return units;
/*      */   }
/*      */   
/*      */   public static TickUnitSource createIntegerTickUnits() {
/*  730 */     TickUnits units = new TickUnits();
/*  731 */     DecimalFormat df0 = new DecimalFormat("0");
/*  732 */     DecimalFormat df1 = new DecimalFormat("#,##0");
/*  733 */     units.add(new NumberTickUnit(1.0D, df0));
/*  734 */     units.add(new NumberTickUnit(2.0D, df0));
/*  735 */     units.add(new NumberTickUnit(5.0D, df0));
/*  736 */     units.add(new NumberTickUnit(10.0D, df0));
/*  737 */     units.add(new NumberTickUnit(20.0D, df0));
/*  738 */     units.add(new NumberTickUnit(50.0D, df0));
/*  739 */     units.add(new NumberTickUnit(100.0D, df0));
/*  740 */     units.add(new NumberTickUnit(200.0D, df0));
/*  741 */     units.add(new NumberTickUnit(500.0D, df0));
/*  742 */     units.add(new NumberTickUnit(1000.0D, df1));
/*  743 */     units.add(new NumberTickUnit(2000.0D, df1));
/*  744 */     units.add(new NumberTickUnit(5000.0D, df1));
/*  745 */     units.add(new NumberTickUnit(10000.0D, df1));
/*  746 */     units.add(new NumberTickUnit(20000.0D, df1));
/*  747 */     units.add(new NumberTickUnit(50000.0D, df1));
/*  748 */     units.add(new NumberTickUnit(100000.0D, df1));
/*  749 */     units.add(new NumberTickUnit(200000.0D, df1));
/*  750 */     units.add(new NumberTickUnit(500000.0D, df1));
/*  751 */     units.add(new NumberTickUnit(1000000.0D, df1));
/*  752 */     units.add(new NumberTickUnit(2000000.0D, df1));
/*  753 */     units.add(new NumberTickUnit(5000000.0D, df1));
/*  754 */     units.add(new NumberTickUnit(1.0E7D, df1));
/*  755 */     units.add(new NumberTickUnit(2.0E7D, df1));
/*  756 */     units.add(new NumberTickUnit(5.0E7D, df1));
/*  757 */     units.add(new NumberTickUnit(1.0E8D, df1));
/*  758 */     units.add(new NumberTickUnit(2.0E8D, df1));
/*  759 */     units.add(new NumberTickUnit(5.0E8D, df1));
/*  760 */     units.add(new NumberTickUnit(1.0E9D, df1));
/*  761 */     units.add(new NumberTickUnit(2.0E9D, df1));
/*  762 */     units.add(new NumberTickUnit(5.0E9D, df1));
/*  763 */     units.add(new NumberTickUnit(1.0E10D, df1));
/*  765 */     return units;
/*      */   }
/*      */   
/*      */   public static TickUnitSource createStandardTickUnits(Locale locale) {
/*  784 */     TickUnits units = new TickUnits();
/*  786 */     NumberFormat numberFormat = NumberFormat.getNumberInstance(locale);
/*  790 */     units.add(new NumberTickUnit(1.0E-7D, numberFormat));
/*  791 */     units.add(new NumberTickUnit(1.0E-6D, numberFormat));
/*  792 */     units.add(new NumberTickUnit(1.0E-5D, numberFormat));
/*  793 */     units.add(new NumberTickUnit(1.0E-4D, numberFormat));
/*  794 */     units.add(new NumberTickUnit(0.001D, numberFormat));
/*  795 */     units.add(new NumberTickUnit(0.01D, numberFormat));
/*  796 */     units.add(new NumberTickUnit(0.1D, numberFormat));
/*  797 */     units.add(new NumberTickUnit(1.0D, numberFormat));
/*  798 */     units.add(new NumberTickUnit(10.0D, numberFormat));
/*  799 */     units.add(new NumberTickUnit(100.0D, numberFormat));
/*  800 */     units.add(new NumberTickUnit(1000.0D, numberFormat));
/*  801 */     units.add(new NumberTickUnit(10000.0D, numberFormat));
/*  802 */     units.add(new NumberTickUnit(100000.0D, numberFormat));
/*  803 */     units.add(new NumberTickUnit(1000000.0D, numberFormat));
/*  804 */     units.add(new NumberTickUnit(1.0E7D, numberFormat));
/*  805 */     units.add(new NumberTickUnit(1.0E8D, numberFormat));
/*  806 */     units.add(new NumberTickUnit(1.0E9D, numberFormat));
/*  807 */     units.add(new NumberTickUnit(1.0E10D, numberFormat));
/*  809 */     units.add(new NumberTickUnit(2.5E-7D, numberFormat));
/*  810 */     units.add(new NumberTickUnit(2.5E-6D, numberFormat));
/*  811 */     units.add(new NumberTickUnit(2.5E-5D, numberFormat));
/*  812 */     units.add(new NumberTickUnit(2.5E-4D, numberFormat));
/*  813 */     units.add(new NumberTickUnit(0.0025D, numberFormat));
/*  814 */     units.add(new NumberTickUnit(0.025D, numberFormat));
/*  815 */     units.add(new NumberTickUnit(0.25D, numberFormat));
/*  816 */     units.add(new NumberTickUnit(2.5D, numberFormat));
/*  817 */     units.add(new NumberTickUnit(25.0D, numberFormat));
/*  818 */     units.add(new NumberTickUnit(250.0D, numberFormat));
/*  819 */     units.add(new NumberTickUnit(2500.0D, numberFormat));
/*  820 */     units.add(new NumberTickUnit(25000.0D, numberFormat));
/*  821 */     units.add(new NumberTickUnit(250000.0D, numberFormat));
/*  822 */     units.add(new NumberTickUnit(2500000.0D, numberFormat));
/*  823 */     units.add(new NumberTickUnit(2.5E7D, numberFormat));
/*  824 */     units.add(new NumberTickUnit(2.5E8D, numberFormat));
/*  825 */     units.add(new NumberTickUnit(2.5E9D, numberFormat));
/*  826 */     units.add(new NumberTickUnit(2.5E10D, numberFormat));
/*  828 */     units.add(new NumberTickUnit(5.0E-7D, numberFormat));
/*  829 */     units.add(new NumberTickUnit(5.0E-6D, numberFormat));
/*  830 */     units.add(new NumberTickUnit(5.0E-5D, numberFormat));
/*  831 */     units.add(new NumberTickUnit(5.0E-4D, numberFormat));
/*  832 */     units.add(new NumberTickUnit(0.005D, numberFormat));
/*  833 */     units.add(new NumberTickUnit(0.05D, numberFormat));
/*  834 */     units.add(new NumberTickUnit(0.5D, numberFormat));
/*  835 */     units.add(new NumberTickUnit(5.0D, numberFormat));
/*  836 */     units.add(new NumberTickUnit(50.0D, numberFormat));
/*  837 */     units.add(new NumberTickUnit(500.0D, numberFormat));
/*  838 */     units.add(new NumberTickUnit(5000.0D, numberFormat));
/*  839 */     units.add(new NumberTickUnit(50000.0D, numberFormat));
/*  840 */     units.add(new NumberTickUnit(500000.0D, numberFormat));
/*  841 */     units.add(new NumberTickUnit(5000000.0D, numberFormat));
/*  842 */     units.add(new NumberTickUnit(5.0E7D, numberFormat));
/*  843 */     units.add(new NumberTickUnit(5.0E8D, numberFormat));
/*  844 */     units.add(new NumberTickUnit(5.0E9D, numberFormat));
/*  845 */     units.add(new NumberTickUnit(5.0E10D, numberFormat));
/*  847 */     return units;
/*      */   }
/*      */   
/*      */   public static TickUnitSource createIntegerTickUnits(Locale locale) {
/*  861 */     TickUnits units = new TickUnits();
/*  863 */     NumberFormat numberFormat = NumberFormat.getNumberInstance(locale);
/*  865 */     units.add(new NumberTickUnit(1.0D, numberFormat));
/*  866 */     units.add(new NumberTickUnit(2.0D, numberFormat));
/*  867 */     units.add(new NumberTickUnit(5.0D, numberFormat));
/*  868 */     units.add(new NumberTickUnit(10.0D, numberFormat));
/*  869 */     units.add(new NumberTickUnit(20.0D, numberFormat));
/*  870 */     units.add(new NumberTickUnit(50.0D, numberFormat));
/*  871 */     units.add(new NumberTickUnit(100.0D, numberFormat));
/*  872 */     units.add(new NumberTickUnit(200.0D, numberFormat));
/*  873 */     units.add(new NumberTickUnit(500.0D, numberFormat));
/*  874 */     units.add(new NumberTickUnit(1000.0D, numberFormat));
/*  875 */     units.add(new NumberTickUnit(2000.0D, numberFormat));
/*  876 */     units.add(new NumberTickUnit(5000.0D, numberFormat));
/*  877 */     units.add(new NumberTickUnit(10000.0D, numberFormat));
/*  878 */     units.add(new NumberTickUnit(20000.0D, numberFormat));
/*  879 */     units.add(new NumberTickUnit(50000.0D, numberFormat));
/*  880 */     units.add(new NumberTickUnit(100000.0D, numberFormat));
/*  881 */     units.add(new NumberTickUnit(200000.0D, numberFormat));
/*  882 */     units.add(new NumberTickUnit(500000.0D, numberFormat));
/*  883 */     units.add(new NumberTickUnit(1000000.0D, numberFormat));
/*  884 */     units.add(new NumberTickUnit(2000000.0D, numberFormat));
/*  885 */     units.add(new NumberTickUnit(5000000.0D, numberFormat));
/*  886 */     units.add(new NumberTickUnit(1.0E7D, numberFormat));
/*  887 */     units.add(new NumberTickUnit(2.0E7D, numberFormat));
/*  888 */     units.add(new NumberTickUnit(5.0E7D, numberFormat));
/*  889 */     units.add(new NumberTickUnit(1.0E8D, numberFormat));
/*  890 */     units.add(new NumberTickUnit(2.0E8D, numberFormat));
/*  891 */     units.add(new NumberTickUnit(5.0E8D, numberFormat));
/*  892 */     units.add(new NumberTickUnit(1.0E9D, numberFormat));
/*  893 */     units.add(new NumberTickUnit(2.0E9D, numberFormat));
/*  894 */     units.add(new NumberTickUnit(5.0E9D, numberFormat));
/*  895 */     units.add(new NumberTickUnit(1.0E10D, numberFormat));
/*  897 */     return units;
/*      */   }
/*      */   
/*      */   protected double estimateMaximumTickLabelHeight(Graphics2D g2) {
/*  910 */     RectangleInsets tickLabelInsets = getTickLabelInsets();
/*  911 */     double result = tickLabelInsets.getTop() + tickLabelInsets.getBottom();
/*  913 */     Font tickLabelFont = getTickLabelFont();
/*  914 */     FontRenderContext frc = g2.getFontRenderContext();
/*  915 */     result += tickLabelFont.getLineMetrics("123", frc).getHeight();
/*  916 */     return result;
/*      */   }
/*      */   
/*      */   protected double estimateMaximumTickLabelWidth(Graphics2D g2, TickUnit unit) {
/*  936 */     RectangleInsets tickLabelInsets = getTickLabelInsets();
/*  937 */     double result = tickLabelInsets.getLeft() + tickLabelInsets.getRight();
/*  939 */     if (isVerticalTickLabels()) {
/*  942 */       FontRenderContext frc = g2.getFontRenderContext();
/*  943 */       LineMetrics lm = getTickLabelFont().getLineMetrics("0", frc);
/*  944 */       result += lm.getHeight();
/*      */     } else {
/*  948 */       FontMetrics fm = g2.getFontMetrics(getTickLabelFont());
/*  949 */       Range range = getRange();
/*  950 */       double lower = range.getLowerBound();
/*  951 */       double upper = range.getUpperBound();
/*  952 */       String lowerStr = unit.valueToString(lower);
/*  953 */       String upperStr = unit.valueToString(upper);
/*  954 */       double w1 = fm.stringWidth(lowerStr);
/*  955 */       double w2 = fm.stringWidth(upperStr);
/*  956 */       result += Math.max(w1, w2);
/*      */     } 
/*  959 */     return result;
/*      */   }
/*      */   
/*      */   protected void selectAutoTickUnit(Graphics2D g2, Rectangle2D dataArea, RectangleEdge edge) {
/*  976 */     if (RectangleEdge.isTopOrBottom(edge)) {
/*  977 */       selectHorizontalAutoTickUnit(g2, dataArea, edge);
/*  979 */     } else if (RectangleEdge.isLeftOrRight(edge)) {
/*  980 */       selectVerticalAutoTickUnit(g2, dataArea, edge);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void selectHorizontalAutoTickUnit(Graphics2D g2, Rectangle2D dataArea, RectangleEdge edge) {
/*  998 */     double tickLabelWidth = estimateMaximumTickLabelWidth(g2, getTickUnit());
/* 1003 */     TickUnitSource tickUnits = getStandardTickUnits();
/* 1004 */     TickUnit unit1 = tickUnits.getCeilingTickUnit(getTickUnit());
/* 1005 */     double unit1Width = lengthToJava2D(unit1.getSize(), dataArea, edge);
/* 1008 */     double guess = tickLabelWidth / unit1Width * unit1.getSize();
/* 1010 */     NumberTickUnit unit2 = (NumberTickUnit)tickUnits.getCeilingTickUnit(guess);
/* 1012 */     double unit2Width = lengthToJava2D(unit2.getSize(), dataArea, edge);
/* 1014 */     tickLabelWidth = estimateMaximumTickLabelWidth(g2, unit2);
/* 1015 */     if (tickLabelWidth > unit2Width)
/* 1016 */       unit2 = (NumberTickUnit)tickUnits.getLargerTickUnit(unit2); 
/* 1019 */     setTickUnit(unit2, false, false);
/*      */   }
/*      */   
/*      */   protected void selectVerticalAutoTickUnit(Graphics2D g2, Rectangle2D dataArea, RectangleEdge edge) {
/* 1036 */     double tickLabelHeight = estimateMaximumTickLabelHeight(g2);
/* 1039 */     TickUnitSource tickUnits = getStandardTickUnits();
/* 1040 */     TickUnit unit1 = tickUnits.getCeilingTickUnit(getTickUnit());
/* 1041 */     double unitHeight = lengthToJava2D(unit1.getSize(), dataArea, edge);
/* 1044 */     double guess = tickLabelHeight / unitHeight * unit1.getSize();
/* 1046 */     NumberTickUnit unit2 = (NumberTickUnit)tickUnits.getCeilingTickUnit(guess);
/* 1048 */     double unit2Height = lengthToJava2D(unit2.getSize(), dataArea, edge);
/* 1050 */     tickLabelHeight = estimateMaximumTickLabelHeight(g2);
/* 1051 */     if (tickLabelHeight > unit2Height)
/* 1052 */       unit2 = (NumberTickUnit)tickUnits.getLargerTickUnit(unit2); 
/* 1055 */     setTickUnit(unit2, false, false);
/*      */   }
/*      */   
/*      */   public List refreshTicks(Graphics2D g2, AxisState state, Rectangle2D dataArea, RectangleEdge edge) {
/* 1076 */     List result = new ArrayList();
/* 1077 */     if (RectangleEdge.isTopOrBottom(edge)) {
/* 1078 */       result = refreshTicksHorizontal(g2, dataArea, edge);
/* 1080 */     } else if (RectangleEdge.isLeftOrRight(edge)) {
/* 1081 */       result = refreshTicksVertical(g2, dataArea, edge);
/*      */     } 
/* 1083 */     return result;
/*      */   }
/*      */   
/*      */   protected List refreshTicksHorizontal(Graphics2D g2, Rectangle2D dataArea, RectangleEdge edge) {
/* 1101 */     List result = new ArrayList();
/* 1103 */     Font tickLabelFont = getTickLabelFont();
/* 1104 */     g2.setFont(tickLabelFont);
/* 1106 */     if (isAutoTickUnitSelection())
/* 1107 */       selectAutoTickUnit(g2, dataArea, edge); 
/* 1110 */     double size = getTickUnit().getSize();
/* 1111 */     int count = calculateVisibleTickCount();
/* 1112 */     double lowestTickValue = calculateLowestVisibleTickValue();
/* 1114 */     if (count <= 500)
/* 1115 */       for (int i = 0; i < count; i++) {
/*      */         String tickLabel;
/* 1116 */         double currentTickValue = lowestTickValue + i * size;
/* 1118 */         NumberFormat formatter = getNumberFormatOverride();
/* 1119 */         if (formatter != null) {
/* 1120 */           tickLabel = formatter.format(currentTickValue);
/*      */         } else {
/* 1123 */           tickLabel = getTickUnit().valueToString(currentTickValue);
/*      */         } 
/* 1125 */         TextAnchor anchor = null;
/* 1126 */         TextAnchor rotationAnchor = null;
/* 1127 */         double angle = 0.0D;
/* 1128 */         if (isVerticalTickLabels()) {
/* 1129 */           anchor = TextAnchor.CENTER_RIGHT;
/* 1130 */           rotationAnchor = TextAnchor.CENTER_RIGHT;
/* 1131 */           if (edge == RectangleEdge.TOP) {
/* 1132 */             angle = 1.5707963267948966D;
/*      */           } else {
/* 1135 */             angle = -1.5707963267948966D;
/*      */           } 
/* 1139 */         } else if (edge == RectangleEdge.TOP) {
/* 1140 */           anchor = TextAnchor.BOTTOM_CENTER;
/* 1141 */           rotationAnchor = TextAnchor.BOTTOM_CENTER;
/*      */         } else {
/* 1144 */           anchor = TextAnchor.TOP_CENTER;
/* 1145 */           rotationAnchor = TextAnchor.TOP_CENTER;
/*      */         } 
/* 1149 */         Tick tick = new NumberTick(new Double(currentTickValue), tickLabel, anchor, rotationAnchor, angle);
/* 1153 */         result.add(tick);
/*      */       }  
/* 1156 */     return result;
/*      */   }
/*      */   
/*      */   protected List refreshTicksVertical(Graphics2D g2, Rectangle2D dataArea, RectangleEdge edge) {
/* 1175 */     List result = new ArrayList();
/* 1176 */     result.clear();
/* 1178 */     Font tickLabelFont = getTickLabelFont();
/* 1179 */     g2.setFont(tickLabelFont);
/* 1180 */     if (isAutoTickUnitSelection())
/* 1181 */       selectAutoTickUnit(g2, dataArea, edge); 
/* 1184 */     double size = getTickUnit().getSize();
/* 1185 */     int count = calculateVisibleTickCount();
/* 1186 */     double lowestTickValue = calculateLowestVisibleTickValue();
/* 1188 */     if (count <= 500)
/* 1189 */       for (int i = 0; i < count; i++) {
/*      */         String tickLabel;
/* 1190 */         double currentTickValue = lowestTickValue + i * size;
/* 1192 */         NumberFormat formatter = getNumberFormatOverride();
/* 1193 */         if (formatter != null) {
/* 1194 */           tickLabel = formatter.format(currentTickValue);
/*      */         } else {
/* 1197 */           tickLabel = getTickUnit().valueToString(currentTickValue);
/*      */         } 
/* 1200 */         TextAnchor anchor = null;
/* 1201 */         TextAnchor rotationAnchor = null;
/* 1202 */         double angle = 0.0D;
/* 1203 */         if (isVerticalTickLabels()) {
/* 1204 */           if (edge == RectangleEdge.LEFT) {
/* 1205 */             anchor = TextAnchor.BOTTOM_CENTER;
/* 1206 */             rotationAnchor = TextAnchor.BOTTOM_CENTER;
/* 1207 */             angle = -1.5707963267948966D;
/*      */           } else {
/* 1210 */             anchor = TextAnchor.BOTTOM_CENTER;
/* 1211 */             rotationAnchor = TextAnchor.BOTTOM_CENTER;
/* 1212 */             angle = 1.5707963267948966D;
/*      */           } 
/* 1216 */         } else if (edge == RectangleEdge.LEFT) {
/* 1217 */           anchor = TextAnchor.CENTER_RIGHT;
/* 1218 */           rotationAnchor = TextAnchor.CENTER_RIGHT;
/*      */         } else {
/* 1221 */           anchor = TextAnchor.CENTER_LEFT;
/* 1222 */           rotationAnchor = TextAnchor.CENTER_LEFT;
/*      */         } 
/* 1226 */         Tick tick = new NumberTick(new Double(currentTickValue), tickLabel, anchor, rotationAnchor, angle);
/* 1230 */         result.add(tick);
/*      */       }  
/* 1233 */     return result;
/*      */   }
/*      */   
/*      */   public Object clone() throws CloneNotSupportedException {
/* 1246 */     NumberAxis clone = (NumberAxis)super.clone();
/* 1247 */     if (this.numberFormatOverride != null)
/* 1248 */       clone.numberFormatOverride = (NumberFormat)this.numberFormatOverride.clone(); 
/* 1251 */     return clone;
/*      */   }
/*      */   
/*      */   public boolean equals(Object obj) {
/* 1262 */     if (obj == this)
/* 1263 */       return true; 
/* 1265 */     if (!(obj instanceof NumberAxis))
/* 1266 */       return false; 
/* 1268 */     if (!super.equals(obj))
/* 1269 */       return false; 
/* 1271 */     NumberAxis that = (NumberAxis)obj;
/* 1272 */     if (this.autoRangeIncludesZero != that.autoRangeIncludesZero)
/* 1273 */       return false; 
/* 1275 */     if (this.autoRangeStickyZero != that.autoRangeStickyZero)
/* 1276 */       return false; 
/* 1278 */     if (!ObjectUtilities.equal(this.tickUnit, that.tickUnit))
/* 1279 */       return false; 
/* 1281 */     if (!ObjectUtilities.equal(this.numberFormatOverride, that.numberFormatOverride))
/* 1283 */       return false; 
/* 1285 */     return true;
/*      */   }
/*      */   
/*      */   public int hashCode() {
/* 1294 */     if (getLabel() != null)
/* 1295 */       return getLabel().hashCode(); 
/* 1298 */     return 0;
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\axis\NumberAxis.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */