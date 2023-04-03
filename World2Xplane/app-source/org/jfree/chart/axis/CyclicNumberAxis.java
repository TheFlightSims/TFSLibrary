/*      */ package org.jfree.chart.axis;
/*      */ 
/*      */ import java.awt.BasicStroke;
/*      */ import java.awt.Color;
/*      */ import java.awt.Font;
/*      */ import java.awt.FontMetrics;
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.Paint;
/*      */ import java.awt.Stroke;
/*      */ import java.awt.geom.Line2D;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.text.NumberFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.List;
/*      */ import org.jfree.chart.plot.Plot;
/*      */ import org.jfree.chart.plot.PlotRenderingInfo;
/*      */ import org.jfree.data.Range;
/*      */ import org.jfree.io.SerialUtilities;
/*      */ import org.jfree.text.TextUtilities;
/*      */ import org.jfree.ui.RectangleEdge;
/*      */ import org.jfree.ui.TextAnchor;
/*      */ import org.jfree.util.ObjectUtilities;
/*      */ import org.jfree.util.PaintUtilities;
/*      */ 
/*      */ public class CyclicNumberAxis extends NumberAxis {
/*  130 */   public static Stroke DEFAULT_ADVANCE_LINE_STROKE = new BasicStroke(1.0F);
/*      */   
/*  133 */   public static final Paint DEFAULT_ADVANCE_LINE_PAINT = Color.gray;
/*      */   
/*      */   protected double offset;
/*      */   
/*      */   protected double period;
/*      */   
/*      */   protected boolean boundMappedToLastCycle;
/*      */   
/*      */   protected boolean advanceLineVisible;
/*      */   
/*  148 */   protected transient Stroke advanceLineStroke = DEFAULT_ADVANCE_LINE_STROKE;
/*      */   
/*      */   protected transient Paint advanceLinePaint;
/*      */   
/*      */   private transient boolean internalMarkerWhenTicksOverlap;
/*      */   
/*      */   private transient Tick internalMarkerCycleBoundTick;
/*      */   
/*      */   public CyclicNumberAxis(double period) {
/*  162 */     this(period, 0.0D);
/*      */   }
/*      */   
/*      */   public CyclicNumberAxis(double period, double offset) {
/*  172 */     this(period, offset, (String)null);
/*      */   }
/*      */   
/*      */   public CyclicNumberAxis(double period, String label) {
/*  182 */     this(0.0D, period, label);
/*      */   }
/*      */   
/*      */   public CyclicNumberAxis(double period, double offset, String label) {
/*  193 */     super(label);
/*  194 */     this.period = period;
/*  195 */     this.offset = offset;
/*  196 */     setFixedAutoRange(period);
/*  197 */     this.advanceLineVisible = true;
/*  198 */     this.advanceLinePaint = DEFAULT_ADVANCE_LINE_PAINT;
/*      */   }
/*      */   
/*      */   public boolean isAdvanceLineVisible() {
/*  208 */     return this.advanceLineVisible;
/*      */   }
/*      */   
/*      */   public void setAdvanceLineVisible(boolean visible) {
/*  218 */     this.advanceLineVisible = visible;
/*      */   }
/*      */   
/*      */   public Paint getAdvanceLinePaint() {
/*  228 */     return this.advanceLinePaint;
/*      */   }
/*      */   
/*      */   public void setAdvanceLinePaint(Paint paint) {
/*  238 */     if (paint == null)
/*  239 */       throw new IllegalArgumentException("Null 'paint' argument."); 
/*  241 */     this.advanceLinePaint = paint;
/*      */   }
/*      */   
/*      */   public Stroke getAdvanceLineStroke() {
/*  251 */     return this.advanceLineStroke;
/*      */   }
/*      */   
/*      */   public void setAdvanceLineStroke(Stroke stroke) {
/*  260 */     if (stroke == null)
/*  261 */       throw new IllegalArgumentException("Null 'stroke' argument."); 
/*  263 */     this.advanceLineStroke = stroke;
/*      */   }
/*      */   
/*      */   public boolean isBoundMappedToLastCycle() {
/*  281 */     return this.boundMappedToLastCycle;
/*      */   }
/*      */   
/*      */   public void setBoundMappedToLastCycle(boolean boundMappedToLastCycle) {
/*  298 */     this.boundMappedToLastCycle = boundMappedToLastCycle;
/*      */   }
/*      */   
/*      */   protected void selectHorizontalAutoTickUnit(Graphics2D g2, Rectangle2D drawArea, Rectangle2D dataArea, RectangleEdge edge) {
/*  314 */     double tickLabelWidth = estimateMaximumTickLabelWidth(g2, getTickUnit());
/*  318 */     double n = getRange().getLength() * tickLabelWidth / dataArea.getWidth();
/*  321 */     setTickUnit((NumberTickUnit)getStandardTickUnits().getCeilingTickUnit(n), false, false);
/*      */   }
/*      */   
/*      */   protected void selectVerticalAutoTickUnit(Graphics2D g2, Rectangle2D drawArea, Rectangle2D dataArea, RectangleEdge edge) {
/*  341 */     double tickLabelWidth = estimateMaximumTickLabelWidth(g2, getTickUnit());
/*  345 */     double n = getRange().getLength() * tickLabelWidth / dataArea.getHeight();
/*  348 */     setTickUnit((NumberTickUnit)getStandardTickUnits().getCeilingTickUnit(n), false, false);
/*      */   }
/*      */   
/*      */   protected static class CycleBoundTick extends NumberTick {
/*      */     public boolean mapToLastCycle;
/*      */     
/*      */     public CycleBoundTick(boolean mapToLastCycle, Number number, String label, TextAnchor textAnchor, TextAnchor rotationAnchor, double angle) {
/*  379 */       super(number, label, textAnchor, rotationAnchor, angle);
/*  380 */       this.mapToLastCycle = mapToLastCycle;
/*      */     }
/*      */   }
/*      */   
/*      */   protected float[] calculateAnchorPoint(ValueTick tick, double cursor, Rectangle2D dataArea, RectangleEdge edge) {
/*  397 */     if (tick instanceof CycleBoundTick) {
/*  398 */       boolean mapsav = this.boundMappedToLastCycle;
/*  399 */       this.boundMappedToLastCycle = ((CycleBoundTick)tick).mapToLastCycle;
/*  401 */       float[] ret = super.calculateAnchorPoint(tick, cursor, dataArea, edge);
/*  404 */       this.boundMappedToLastCycle = mapsav;
/*  405 */       return ret;
/*      */     } 
/*  407 */     return super.calculateAnchorPoint(tick, cursor, dataArea, edge);
/*      */   }
/*      */   
/*      */   protected List refreshTicksHorizontal(Graphics2D g2, Rectangle2D dataArea, RectangleEdge edge) {
/*  426 */     List result = new ArrayList();
/*  428 */     Font tickLabelFont = getTickLabelFont();
/*  429 */     g2.setFont(tickLabelFont);
/*  431 */     if (isAutoTickUnitSelection())
/*  432 */       selectAutoTickUnit(g2, dataArea, edge); 
/*  435 */     double unit = getTickUnit().getSize();
/*  436 */     double cycleBound = getCycleBound();
/*  437 */     double currentTickValue = Math.ceil(cycleBound / unit) * unit;
/*  438 */     double upperValue = getRange().getUpperBound();
/*  439 */     boolean cycled = false;
/*  441 */     boolean boundMapping = this.boundMappedToLastCycle;
/*  442 */     this.boundMappedToLastCycle = false;
/*  444 */     CycleBoundTick lastTick = null;
/*  445 */     float lastX = 0.0F;
/*  447 */     if (upperValue == cycleBound) {
/*  448 */       currentTickValue = calculateLowestVisibleTickValue();
/*  449 */       cycled = true;
/*  450 */       this.boundMappedToLastCycle = true;
/*      */     } 
/*  453 */     while (currentTickValue <= upperValue) {
/*      */       String tickLabel;
/*  456 */       boolean cyclenow = false;
/*  457 */       if (currentTickValue + unit > upperValue && !cycled)
/*  458 */         cyclenow = true; 
/*  461 */       double xx = valueToJava2D(currentTickValue, dataArea, edge);
/*  463 */       NumberFormat formatter = getNumberFormatOverride();
/*  464 */       if (formatter != null) {
/*  465 */         tickLabel = formatter.format(currentTickValue);
/*      */       } else {
/*  468 */         tickLabel = getTickUnit().valueToString(currentTickValue);
/*      */       } 
/*  470 */       float x = (float)xx;
/*  471 */       TextAnchor anchor = null;
/*  472 */       TextAnchor rotationAnchor = null;
/*  473 */       double angle = 0.0D;
/*  474 */       if (isVerticalTickLabels()) {
/*  475 */         if (edge == RectangleEdge.TOP) {
/*  476 */           angle = 1.5707963267948966D;
/*      */         } else {
/*  479 */           angle = -1.5707963267948966D;
/*      */         } 
/*  481 */         anchor = TextAnchor.CENTER_RIGHT;
/*  483 */         if (lastTick != null && lastX == x && currentTickValue != cycleBound) {
/*  485 */           anchor = isInverted() ? TextAnchor.TOP_RIGHT : TextAnchor.BOTTOM_RIGHT;
/*  487 */           result.remove(result.size() - 1);
/*  488 */           result.add(new CycleBoundTick(this.boundMappedToLastCycle, lastTick.getNumber(), lastTick.getText(), anchor, anchor, lastTick.getAngle()));
/*  493 */           this.internalMarkerWhenTicksOverlap = true;
/*  494 */           anchor = isInverted() ? TextAnchor.BOTTOM_RIGHT : TextAnchor.TOP_RIGHT;
/*      */         } 
/*  497 */         rotationAnchor = anchor;
/*  500 */       } else if (edge == RectangleEdge.TOP) {
/*  501 */         anchor = TextAnchor.BOTTOM_CENTER;
/*  502 */         if (lastTick != null && lastX == x && currentTickValue != cycleBound) {
/*  504 */           anchor = isInverted() ? TextAnchor.BOTTOM_LEFT : TextAnchor.BOTTOM_RIGHT;
/*  506 */           result.remove(result.size() - 1);
/*  507 */           result.add(new CycleBoundTick(this.boundMappedToLastCycle, lastTick.getNumber(), lastTick.getText(), anchor, anchor, lastTick.getAngle()));
/*  512 */           this.internalMarkerWhenTicksOverlap = true;
/*  513 */           anchor = isInverted() ? TextAnchor.BOTTOM_RIGHT : TextAnchor.BOTTOM_LEFT;
/*      */         } 
/*  516 */         rotationAnchor = anchor;
/*      */       } else {
/*  519 */         anchor = TextAnchor.TOP_CENTER;
/*  520 */         if (lastTick != null && lastX == x && currentTickValue != cycleBound) {
/*  522 */           anchor = isInverted() ? TextAnchor.TOP_LEFT : TextAnchor.TOP_RIGHT;
/*  524 */           result.remove(result.size() - 1);
/*  525 */           result.add(new CycleBoundTick(this.boundMappedToLastCycle, lastTick.getNumber(), lastTick.getText(), anchor, anchor, lastTick.getAngle()));
/*  530 */           this.internalMarkerWhenTicksOverlap = true;
/*  531 */           anchor = isInverted() ? TextAnchor.TOP_RIGHT : TextAnchor.TOP_LEFT;
/*      */         } 
/*  534 */         rotationAnchor = anchor;
/*      */       } 
/*  538 */       CycleBoundTick tick = new CycleBoundTick(this.boundMappedToLastCycle, new Double(currentTickValue), tickLabel, anchor, rotationAnchor, angle);
/*  543 */       if (currentTickValue == cycleBound)
/*  544 */         this.internalMarkerCycleBoundTick = tick; 
/*  546 */       result.add(tick);
/*  547 */       lastTick = tick;
/*  548 */       lastX = x;
/*  550 */       currentTickValue += unit;
/*  552 */       if (cyclenow) {
/*  553 */         currentTickValue = calculateLowestVisibleTickValue();
/*  554 */         upperValue = cycleBound;
/*  555 */         cycled = true;
/*  556 */         this.boundMappedToLastCycle = true;
/*      */       } 
/*      */     } 
/*  560 */     this.boundMappedToLastCycle = boundMapping;
/*  561 */     return result;
/*      */   }
/*      */   
/*      */   protected List refreshVerticalTicks(Graphics2D g2, Rectangle2D dataArea, RectangleEdge edge) {
/*  579 */     List result = new ArrayList();
/*  580 */     result.clear();
/*  582 */     Font tickLabelFont = getTickLabelFont();
/*  583 */     g2.setFont(tickLabelFont);
/*  584 */     if (isAutoTickUnitSelection())
/*  585 */       selectAutoTickUnit(g2, dataArea, edge); 
/*  588 */     double unit = getTickUnit().getSize();
/*  589 */     double cycleBound = getCycleBound();
/*  590 */     double currentTickValue = Math.ceil(cycleBound / unit) * unit;
/*  591 */     double upperValue = getRange().getUpperBound();
/*  592 */     boolean cycled = false;
/*  594 */     boolean boundMapping = this.boundMappedToLastCycle;
/*  595 */     this.boundMappedToLastCycle = true;
/*  597 */     NumberTick lastTick = null;
/*  598 */     float lastY = 0.0F;
/*  600 */     if (upperValue == cycleBound) {
/*  601 */       currentTickValue = calculateLowestVisibleTickValue();
/*  602 */       cycled = true;
/*  603 */       this.boundMappedToLastCycle = true;
/*      */     } 
/*  606 */     while (currentTickValue <= upperValue) {
/*      */       String tickLabel;
/*  609 */       boolean cyclenow = false;
/*  610 */       if (currentTickValue + unit > upperValue && !cycled)
/*  611 */         cyclenow = true; 
/*  614 */       double yy = valueToJava2D(currentTickValue, dataArea, edge);
/*  616 */       NumberFormat formatter = getNumberFormatOverride();
/*  617 */       if (formatter != null) {
/*  618 */         tickLabel = formatter.format(currentTickValue);
/*      */       } else {
/*  621 */         tickLabel = getTickUnit().valueToString(currentTickValue);
/*      */       } 
/*  624 */       float y = (float)yy;
/*  625 */       TextAnchor anchor = null;
/*  626 */       TextAnchor rotationAnchor = null;
/*  627 */       double angle = 0.0D;
/*  628 */       if (isVerticalTickLabels()) {
/*  630 */         if (edge == RectangleEdge.LEFT) {
/*  631 */           anchor = TextAnchor.BOTTOM_CENTER;
/*  632 */           if (lastTick != null && lastY == y && currentTickValue != cycleBound) {
/*  634 */             anchor = isInverted() ? TextAnchor.BOTTOM_LEFT : TextAnchor.BOTTOM_RIGHT;
/*  636 */             result.remove(result.size() - 1);
/*  637 */             result.add(new CycleBoundTick(this.boundMappedToLastCycle, lastTick.getNumber(), lastTick.getText(), anchor, anchor, lastTick.getAngle()));
/*  642 */             this.internalMarkerWhenTicksOverlap = true;
/*  643 */             anchor = isInverted() ? TextAnchor.BOTTOM_RIGHT : TextAnchor.BOTTOM_LEFT;
/*      */           } 
/*  646 */           rotationAnchor = anchor;
/*  647 */           angle = -1.5707963267948966D;
/*      */         } else {
/*  650 */           anchor = TextAnchor.BOTTOM_CENTER;
/*  651 */           if (lastTick != null && lastY == y && currentTickValue != cycleBound) {
/*  653 */             anchor = isInverted() ? TextAnchor.BOTTOM_RIGHT : TextAnchor.BOTTOM_LEFT;
/*  655 */             result.remove(result.size() - 1);
/*  656 */             result.add(new CycleBoundTick(this.boundMappedToLastCycle, lastTick.getNumber(), lastTick.getText(), anchor, anchor, lastTick.getAngle()));
/*  661 */             this.internalMarkerWhenTicksOverlap = true;
/*  662 */             anchor = isInverted() ? TextAnchor.BOTTOM_LEFT : TextAnchor.BOTTOM_RIGHT;
/*      */           } 
/*  665 */           rotationAnchor = anchor;
/*  666 */           angle = 1.5707963267948966D;
/*      */         } 
/*  670 */       } else if (edge == RectangleEdge.LEFT) {
/*  671 */         anchor = TextAnchor.CENTER_RIGHT;
/*  672 */         if (lastTick != null && lastY == y && currentTickValue != cycleBound) {
/*  674 */           anchor = isInverted() ? TextAnchor.BOTTOM_RIGHT : TextAnchor.TOP_RIGHT;
/*  676 */           result.remove(result.size() - 1);
/*  677 */           result.add(new CycleBoundTick(this.boundMappedToLastCycle, lastTick.getNumber(), lastTick.getText(), anchor, anchor, lastTick.getAngle()));
/*  682 */           this.internalMarkerWhenTicksOverlap = true;
/*  683 */           anchor = isInverted() ? TextAnchor.TOP_RIGHT : TextAnchor.BOTTOM_RIGHT;
/*      */         } 
/*  686 */         rotationAnchor = anchor;
/*      */       } else {
/*  689 */         anchor = TextAnchor.CENTER_LEFT;
/*  690 */         if (lastTick != null && lastY == y && currentTickValue != cycleBound) {
/*  692 */           anchor = isInverted() ? TextAnchor.BOTTOM_LEFT : TextAnchor.TOP_LEFT;
/*  694 */           result.remove(result.size() - 1);
/*  695 */           result.add(new CycleBoundTick(this.boundMappedToLastCycle, lastTick.getNumber(), lastTick.getText(), anchor, anchor, lastTick.getAngle()));
/*  700 */           this.internalMarkerWhenTicksOverlap = true;
/*  701 */           anchor = isInverted() ? TextAnchor.TOP_LEFT : TextAnchor.BOTTOM_LEFT;
/*      */         } 
/*  704 */         rotationAnchor = anchor;
/*      */       } 
/*  708 */       CycleBoundTick tick = new CycleBoundTick(this.boundMappedToLastCycle, new Double(currentTickValue), tickLabel, anchor, rotationAnchor, angle);
/*  712 */       if (currentTickValue == cycleBound)
/*  713 */         this.internalMarkerCycleBoundTick = tick; 
/*  715 */       result.add(tick);
/*  716 */       lastTick = tick;
/*  717 */       lastY = y;
/*  719 */       if (currentTickValue == cycleBound)
/*  720 */         this.internalMarkerCycleBoundTick = tick; 
/*  723 */       currentTickValue += unit;
/*  725 */       if (cyclenow) {
/*  726 */         currentTickValue = calculateLowestVisibleTickValue();
/*  727 */         upperValue = cycleBound;
/*  728 */         cycled = true;
/*  729 */         this.boundMappedToLastCycle = false;
/*      */       } 
/*      */     } 
/*  733 */     this.boundMappedToLastCycle = boundMapping;
/*  734 */     return result;
/*      */   }
/*      */   
/*      */   public double java2DToValue(double java2DValue, Rectangle2D dataArea, RectangleEdge edge) {
/*  748 */     Range range = getRange();
/*  750 */     double vmax = range.getUpperBound();
/*  751 */     double vp = getCycleBound();
/*  753 */     double jmin = 0.0D;
/*  754 */     double jmax = 0.0D;
/*  755 */     if (RectangleEdge.isTopOrBottom(edge)) {
/*  756 */       jmin = dataArea.getMinX();
/*  757 */       jmax = dataArea.getMaxX();
/*  759 */     } else if (RectangleEdge.isLeftOrRight(edge)) {
/*  760 */       jmin = dataArea.getMaxY();
/*  761 */       jmax = dataArea.getMinY();
/*      */     } 
/*  764 */     if (isInverted()) {
/*  765 */       double d = jmax - (vmax - vp) * (jmax - jmin) / this.period;
/*  766 */       if (java2DValue >= d)
/*  767 */         return vp + (jmax - java2DValue) * this.period / (jmax - jmin); 
/*  770 */       return vp - (java2DValue - jmin) * this.period / (jmax - jmin);
/*      */     } 
/*  774 */     double jbreak = (vmax - vp) * (jmax - jmin) / this.period + jmin;
/*  775 */     if (java2DValue <= jbreak)
/*  776 */       return vp + (java2DValue - jmin) * this.period / (jmax - jmin); 
/*  779 */     return vp - (jmax - java2DValue) * this.period / (jmax - jmin);
/*      */   }
/*      */   
/*      */   public double valueToJava2D(double value, Rectangle2D dataArea, RectangleEdge edge) {
/*  795 */     Range range = getRange();
/*  797 */     double vmin = range.getLowerBound();
/*  798 */     double vmax = range.getUpperBound();
/*  799 */     double vp = getCycleBound();
/*  801 */     if (value < vmin || value > vmax)
/*  802 */       return Double.NaN; 
/*  806 */     double jmin = 0.0D;
/*  807 */     double jmax = 0.0D;
/*  808 */     if (RectangleEdge.isTopOrBottom(edge)) {
/*  809 */       jmin = dataArea.getMinX();
/*  810 */       jmax = dataArea.getMaxX();
/*  812 */     } else if (RectangleEdge.isLeftOrRight(edge)) {
/*  813 */       jmax = dataArea.getMinY();
/*  814 */       jmin = dataArea.getMaxY();
/*      */     } 
/*  817 */     if (isInverted()) {
/*  818 */       if (value == vp)
/*  819 */         return this.boundMappedToLastCycle ? jmin : jmax; 
/*  821 */       if (value > vp)
/*  822 */         return jmax - (value - vp) * (jmax - jmin) / this.period; 
/*  825 */       return jmin + (vp - value) * (jmax - jmin) / this.period;
/*      */     } 
/*  829 */     if (value == vp)
/*  830 */       return this.boundMappedToLastCycle ? jmax : jmin; 
/*  832 */     if (value >= vp)
/*  833 */       return jmin + (value - vp) * (jmax - jmin) / this.period; 
/*  836 */     return jmax - (vp - value) * (jmax - jmin) / this.period;
/*      */   }
/*      */   
/*      */   public void centerRange(double value) {
/*  847 */     setRange(value - this.period / 2.0D, value + this.period / 2.0D);
/*      */   }
/*      */   
/*      */   public void setAutoRangeMinimumSize(double size, boolean notify) {
/*  862 */     if (size > this.period)
/*  863 */       this.period = size; 
/*  865 */     super.setAutoRangeMinimumSize(size, notify);
/*      */   }
/*      */   
/*      */   public void setFixedAutoRange(double length) {
/*  877 */     this.period = length;
/*  878 */     super.setFixedAutoRange(length);
/*      */   }
/*      */   
/*      */   public void setRange(Range range, boolean turnOffAutoRange, boolean notify) {
/*  893 */     double size = range.getUpperBound() - range.getLowerBound();
/*  894 */     if (size > this.period)
/*  895 */       this.period = size; 
/*  897 */     super.setRange(range, turnOffAutoRange, notify);
/*      */   }
/*      */   
/*      */   public double getCycleBound() {
/*  911 */     return Math.floor((getRange().getUpperBound() - this.offset) / this.period) * this.period + this.offset;
/*      */   }
/*      */   
/*      */   public double getOffset() {
/*  927 */     return this.offset;
/*      */   }
/*      */   
/*      */   public void setOffset(double offset) {
/*  941 */     this.offset = offset;
/*      */   }
/*      */   
/*      */   public double getPeriod() {
/*  955 */     return this.period;
/*      */   }
/*      */   
/*      */   public void setPeriod(double period) {
/*  969 */     this.period = period;
/*      */   }
/*      */   
/*      */   protected AxisState drawTickMarksAndLabels(Graphics2D g2, double cursor, Rectangle2D plotArea, Rectangle2D dataArea, RectangleEdge edge) {
/*  987 */     this.internalMarkerWhenTicksOverlap = false;
/*  988 */     AxisState ret = super.drawTickMarksAndLabels(g2, cursor, plotArea, dataArea, edge);
/*  993 */     if (!this.internalMarkerWhenTicksOverlap)
/*  994 */       return ret; 
/*  997 */     double ol = getTickMarkOutsideLength();
/*  998 */     FontMetrics fm = g2.getFontMetrics(getTickLabelFont());
/* 1000 */     if (isVerticalTickLabels()) {
/* 1001 */       ol = fm.getMaxAdvance();
/*      */     } else {
/* 1004 */       ol = fm.getHeight();
/*      */     } 
/* 1007 */     double il = 0.0D;
/* 1008 */     if (isTickMarksVisible()) {
/* 1009 */       float xx = (float)valueToJava2D(getRange().getUpperBound(), dataArea, edge);
/* 1012 */       Line2D mark = null;
/* 1013 */       g2.setStroke(getTickMarkStroke());
/* 1014 */       g2.setPaint(getTickMarkPaint());
/* 1015 */       if (edge == RectangleEdge.LEFT) {
/* 1016 */         mark = new Line2D.Double(cursor - ol, xx, cursor + il, xx);
/* 1018 */       } else if (edge == RectangleEdge.RIGHT) {
/* 1019 */         mark = new Line2D.Double(cursor + ol, xx, cursor - il, xx);
/* 1021 */       } else if (edge == RectangleEdge.TOP) {
/* 1022 */         mark = new Line2D.Double(xx, cursor - ol, xx, cursor + il);
/* 1024 */       } else if (edge == RectangleEdge.BOTTOM) {
/* 1025 */         mark = new Line2D.Double(xx, cursor + ol, xx, cursor - il);
/*      */       } 
/* 1027 */       g2.draw(mark);
/*      */     } 
/* 1029 */     return ret;
/*      */   }
/*      */   
/*      */   public AxisState draw(Graphics2D g2, double cursor, Rectangle2D plotArea, Rectangle2D dataArea, RectangleEdge edge, PlotRenderingInfo plotState) {
/* 1052 */     AxisState ret = super.draw(g2, cursor, plotArea, dataArea, edge, plotState);
/* 1055 */     if (isAdvanceLineVisible()) {
/* 1056 */       double xx = valueToJava2D(getRange().getUpperBound(), dataArea, edge);
/* 1059 */       Line2D mark = null;
/* 1060 */       g2.setStroke(getAdvanceLineStroke());
/* 1061 */       g2.setPaint(getAdvanceLinePaint());
/* 1062 */       if (edge == RectangleEdge.LEFT) {
/* 1063 */         mark = new Line2D.Double(cursor, xx, cursor + dataArea.getWidth(), xx);
/* 1067 */       } else if (edge == RectangleEdge.RIGHT) {
/* 1068 */         mark = new Line2D.Double(cursor - dataArea.getWidth(), xx, cursor, xx);
/* 1072 */       } else if (edge == RectangleEdge.TOP) {
/* 1073 */         mark = new Line2D.Double(xx, cursor + dataArea.getHeight(), xx, cursor);
/* 1077 */       } else if (edge == RectangleEdge.BOTTOM) {
/* 1078 */         mark = new Line2D.Double(xx, cursor, xx, cursor - dataArea.getHeight());
/*      */       } 
/* 1082 */       g2.draw(mark);
/*      */     } 
/* 1084 */     return ret;
/*      */   }
/*      */   
/*      */   public AxisSpace reserveSpace(Graphics2D g2, Plot plot, Rectangle2D plotArea, RectangleEdge edge, AxisSpace space) {
/* 1105 */     this.internalMarkerCycleBoundTick = null;
/* 1106 */     AxisSpace ret = super.reserveSpace(g2, plot, plotArea, edge, space);
/* 1107 */     if (this.internalMarkerCycleBoundTick == null)
/* 1108 */       return ret; 
/* 1111 */     FontMetrics fm = g2.getFontMetrics(getTickLabelFont());
/* 1112 */     Rectangle2D r = TextUtilities.getTextBounds(this.internalMarkerCycleBoundTick.getText(), g2, fm);
/* 1116 */     if (RectangleEdge.isTopOrBottom(edge)) {
/* 1117 */       if (isVerticalTickLabels()) {
/* 1118 */         space.add(r.getHeight() / 2.0D, RectangleEdge.RIGHT);
/*      */       } else {
/* 1121 */         space.add(r.getWidth() / 2.0D, RectangleEdge.RIGHT);
/*      */       } 
/* 1124 */     } else if (RectangleEdge.isLeftOrRight(edge)) {
/* 1125 */       if (isVerticalTickLabels()) {
/* 1126 */         space.add(r.getWidth() / 2.0D, RectangleEdge.TOP);
/*      */       } else {
/* 1129 */         space.add(r.getHeight() / 2.0D, RectangleEdge.TOP);
/*      */       } 
/*      */     } 
/* 1133 */     return ret;
/*      */   }
/*      */   
/*      */   private void writeObject(ObjectOutputStream stream) throws IOException {
/* 1146 */     stream.defaultWriteObject();
/* 1147 */     SerialUtilities.writePaint(this.advanceLinePaint, stream);
/* 1148 */     SerialUtilities.writeStroke(this.advanceLineStroke, stream);
/*      */   }
/*      */   
/*      */   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
/* 1163 */     stream.defaultReadObject();
/* 1164 */     this.advanceLinePaint = SerialUtilities.readPaint(stream);
/* 1165 */     this.advanceLineStroke = SerialUtilities.readStroke(stream);
/*      */   }
/*      */   
/*      */   public boolean equals(Object obj) {
/* 1178 */     if (obj == this)
/* 1179 */       return true; 
/* 1181 */     if (!(obj instanceof CyclicNumberAxis))
/* 1182 */       return false; 
/* 1184 */     if (!super.equals(obj))
/* 1185 */       return false; 
/* 1187 */     CyclicNumberAxis that = (CyclicNumberAxis)obj;
/* 1188 */     if (this.period != that.period)
/* 1189 */       return false; 
/* 1191 */     if (this.offset != that.offset)
/* 1192 */       return false; 
/* 1194 */     if (!PaintUtilities.equal(this.advanceLinePaint, that.advanceLinePaint))
/* 1196 */       return false; 
/* 1198 */     if (!ObjectUtilities.equal(this.advanceLineStroke, that.advanceLineStroke))
/* 1200 */       return false; 
/* 1202 */     if (this.advanceLineVisible != that.advanceLineVisible)
/* 1203 */       return false; 
/* 1205 */     if (this.boundMappedToLastCycle != that.boundMappedToLastCycle)
/* 1206 */       return false; 
/* 1208 */     return true;
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\axis\CyclicNumberAxis.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */