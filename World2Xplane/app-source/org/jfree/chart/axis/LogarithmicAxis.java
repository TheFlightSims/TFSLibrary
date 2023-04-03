/*      */ package org.jfree.chart.axis;
/*      */ 
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.text.DecimalFormat;
/*      */ import java.text.NumberFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.List;
/*      */ import org.jfree.chart.plot.Plot;
/*      */ import org.jfree.chart.plot.ValueAxisPlot;
/*      */ import org.jfree.data.Range;
/*      */ import org.jfree.ui.RectangleEdge;
/*      */ import org.jfree.ui.TextAnchor;
/*      */ 
/*      */ public class LogarithmicAxis extends NumberAxis {
/*      */   private static final long serialVersionUID = 2502918599004103054L;
/*      */   
/*  117 */   public static final double LOG10_VALUE = Math.log(10.0D);
/*      */   
/*      */   public static final double SMALL_LOG_VALUE = 1.0E-100D;
/*      */   
/*      */   protected boolean allowNegativesFlag = false;
/*      */   
/*      */   protected boolean strictValuesFlag = true;
/*      */   
/*  130 */   protected final NumberFormat numberFormatterObj = NumberFormat.getInstance();
/*      */   
/*      */   protected boolean expTickLabelsFlag = false;
/*      */   
/*      */   protected boolean log10TickLabelsFlag = false;
/*      */   
/*      */   protected boolean autoRangeNextLogFlag = false;
/*      */   
/*      */   protected boolean smallLogFlag = false;
/*      */   
/*      */   public LogarithmicAxis(String label) {
/*  151 */     super(label);
/*  152 */     setupNumberFmtObj();
/*      */   }
/*      */   
/*      */   public void setAllowNegativesFlag(boolean flgVal) {
/*  163 */     this.allowNegativesFlag = flgVal;
/*      */   }
/*      */   
/*      */   public boolean getAllowNegativesFlag() {
/*  174 */     return this.allowNegativesFlag;
/*      */   }
/*      */   
/*      */   public void setStrictValuesFlag(boolean flgVal) {
/*  186 */     this.strictValuesFlag = flgVal;
/*      */   }
/*      */   
/*      */   public boolean getStrictValuesFlag() {
/*  198 */     return this.strictValuesFlag;
/*      */   }
/*      */   
/*      */   public void setExpTickLabelsFlag(boolean flgVal) {
/*  210 */     this.expTickLabelsFlag = flgVal;
/*  211 */     setupNumberFmtObj();
/*      */   }
/*      */   
/*      */   public boolean getExpTickLabelsFlag() {
/*  221 */     return this.expTickLabelsFlag;
/*      */   }
/*      */   
/*      */   public void setLog10TickLabelsFlag(boolean flag) {
/*  231 */     this.log10TickLabelsFlag = flag;
/*      */   }
/*      */   
/*      */   public boolean getLog10TickLabelsFlag() {
/*  242 */     return this.log10TickLabelsFlag;
/*      */   }
/*      */   
/*      */   public void setAutoRangeNextLogFlag(boolean flag) {
/*  255 */     this.autoRangeNextLogFlag = flag;
/*      */   }
/*      */   
/*      */   public boolean getAutoRangeNextLogFlag() {
/*  265 */     return this.autoRangeNextLogFlag;
/*      */   }
/*      */   
/*      */   public void setRange(Range range) {
/*  275 */     super.setRange(range);
/*  276 */     setupSmallLogFlag();
/*      */   }
/*      */   
/*      */   protected void setupSmallLogFlag() {
/*  286 */     double lowerVal = getRange().getLowerBound();
/*  287 */     this.smallLogFlag = (!this.allowNegativesFlag && lowerVal < 10.0D && lowerVal > 0.0D);
/*      */   }
/*      */   
/*      */   protected void setupNumberFmtObj() {
/*  296 */     if (this.numberFormatterObj instanceof DecimalFormat)
/*  299 */       ((DecimalFormat)this.numberFormatterObj).applyPattern(this.expTickLabelsFlag ? "0E0" : "0.###"); 
/*      */   }
/*      */   
/*      */   protected double switchedLog10(double val) {
/*  317 */     return this.smallLogFlag ? (Math.log(val) / LOG10_VALUE) : adjustedLog10(val);
/*      */   }
/*      */   
/*      */   public double adjustedLog10(double val) {
/*  334 */     boolean negFlag = (val < 0.0D);
/*  335 */     if (negFlag)
/*  336 */       val = -val; 
/*  338 */     if (val < 10.0D)
/*  339 */       val += (10.0D - val) / 10.0D; 
/*  342 */     return negFlag ? -(Math.log(val) / LOG10_VALUE) : (Math.log(val) / LOG10_VALUE);
/*      */   }
/*      */   
/*      */   protected double computeLogFloor(double lower) {
/*      */     double logFloor;
/*  359 */     if (this.allowNegativesFlag) {
/*  361 */       if (lower > 10.0D) {
/*  363 */         logFloor = Math.log(lower) / LOG10_VALUE;
/*  364 */         logFloor = Math.floor(logFloor);
/*  365 */         logFloor = Math.pow(10.0D, logFloor);
/*  367 */       } else if (lower < -10.0D) {
/*  369 */         logFloor = Math.log(-lower) / LOG10_VALUE;
/*  371 */         logFloor = Math.floor(-logFloor);
/*  373 */         logFloor = -Math.pow(10.0D, -logFloor);
/*      */       } else {
/*  377 */         logFloor = Math.floor(lower);
/*      */       } 
/*  382 */     } else if (lower > 0.0D) {
/*  384 */       logFloor = Math.log(lower) / LOG10_VALUE;
/*  385 */       logFloor = Math.floor(logFloor);
/*  386 */       logFloor = Math.pow(10.0D, logFloor);
/*      */     } else {
/*  390 */       logFloor = Math.floor(lower);
/*      */     } 
/*  393 */     return logFloor;
/*      */   }
/*      */   
/*      */   protected double computeLogCeil(double upper) {
/*      */     double logCeil;
/*  409 */     if (this.allowNegativesFlag) {
/*  411 */       if (upper > 10.0D) {
/*  414 */         logCeil = Math.log(upper) / LOG10_VALUE;
/*  415 */         logCeil = Math.ceil(logCeil);
/*  416 */         logCeil = Math.pow(10.0D, logCeil);
/*  418 */       } else if (upper < -10.0D) {
/*  421 */         logCeil = Math.log(-upper) / LOG10_VALUE;
/*  423 */         logCeil = Math.ceil(-logCeil);
/*  425 */         logCeil = -Math.pow(10.0D, -logCeil);
/*      */       } else {
/*  429 */         logCeil = Math.ceil(upper);
/*      */       } 
/*  434 */     } else if (upper > 0.0D) {
/*  437 */       logCeil = Math.log(upper) / LOG10_VALUE;
/*  438 */       logCeil = Math.ceil(logCeil);
/*  439 */       logCeil = Math.pow(10.0D, logCeil);
/*      */     } else {
/*  443 */       logCeil = Math.ceil(upper);
/*      */     } 
/*  446 */     return logCeil;
/*      */   }
/*      */   
/*      */   public void autoAdjustRange() {
/*  454 */     Plot plot = getPlot();
/*  455 */     if (plot == null)
/*      */       return; 
/*  459 */     if (plot instanceof ValueAxisPlot) {
/*      */       double lower;
/*  460 */       ValueAxisPlot vap = (ValueAxisPlot)plot;
/*  463 */       Range r = vap.getDataRange(this);
/*  464 */       if (r == null) {
/*  466 */         r = new Range(0.0D, 1.0D);
/*  467 */         lower = r.getLowerBound();
/*      */       } else {
/*  471 */         lower = r.getLowerBound();
/*  472 */         if (this.strictValuesFlag && !this.allowNegativesFlag && lower <= 0.0D)
/*  475 */           throw new RuntimeException("Values less than or equal to zero not allowed with logarithmic axis"); 
/*      */       } 
/*      */       double lowerMargin;
/*  484 */       if (lower > 0.0D && (lowerMargin = getLowerMargin()) > 0.0D) {
/*  486 */         double logLower = Math.log(lower) / LOG10_VALUE;
/*      */         double logAbs;
/*  488 */         if ((logAbs = Math.abs(logLower)) < 1.0D)
/*  489 */           logAbs = 1.0D; 
/*  491 */         lower = Math.pow(10.0D, logLower - logAbs * lowerMargin);
/*      */       } 
/*  496 */       if (this.autoRangeNextLogFlag)
/*  497 */         lower = computeLogFloor(lower); 
/*  500 */       if (!this.allowNegativesFlag && lower >= 0.0D && lower < 1.0E-100D)
/*  503 */         lower = r.getLowerBound(); 
/*  506 */       double upper = r.getUpperBound();
/*      */       double upperMargin;
/*  510 */       if (upper > 0.0D && (upperMargin = getUpperMargin()) > 0.0D) {
/*  512 */         double logUpper = Math.log(upper) / LOG10_VALUE;
/*      */         double logAbs;
/*  514 */         if ((logAbs = Math.abs(logUpper)) < 1.0D)
/*  515 */           logAbs = 1.0D; 
/*  517 */         upper = Math.pow(10.0D, logUpper + logAbs * upperMargin);
/*      */       } 
/*  520 */       if (!this.allowNegativesFlag && upper < 1.0D && upper > 0.0D && lower > 0.0D) {
/*  525 */         double expVal = Math.log(upper) / LOG10_VALUE;
/*  526 */         expVal = Math.ceil(-expVal + 0.001D);
/*  527 */         expVal = Math.pow(10.0D, expVal);
/*  529 */         upper = (expVal > 0.0D) ? (Math.ceil(upper * expVal) / expVal) : Math.ceil(upper);
/*      */       } else {
/*  536 */         upper = this.autoRangeNextLogFlag ? computeLogCeil(upper) : Math.ceil(upper);
/*      */       } 
/*  540 */       double minRange = getAutoRangeMinimumSize();
/*  541 */       if (upper - lower < minRange) {
/*  542 */         upper = (upper + lower + minRange) / 2.0D;
/*  543 */         lower = (upper + lower - minRange) / 2.0D;
/*  546 */         if (upper - lower < minRange) {
/*  547 */           double absUpper = Math.abs(upper);
/*  549 */           double adjVal = (absUpper > 1.0E-100D) ? (absUpper / 100.0D) : 0.01D;
/*  551 */           upper = (upper + lower + adjVal) / 2.0D;
/*  552 */           lower = (upper + lower - adjVal) / 2.0D;
/*      */         } 
/*      */       } 
/*  556 */       setRange(new Range(lower, upper), false, false);
/*  557 */       setupSmallLogFlag();
/*      */     } 
/*      */   }
/*      */   
/*      */   public double valueToJava2D(double value, Rectangle2D plotArea, RectangleEdge edge) {
/*  576 */     Range range = getRange();
/*  577 */     double axisMin = switchedLog10(range.getLowerBound());
/*  578 */     double axisMax = switchedLog10(range.getUpperBound());
/*  580 */     double min = 0.0D;
/*  581 */     double max = 0.0D;
/*  582 */     if (RectangleEdge.isTopOrBottom(edge)) {
/*  583 */       min = plotArea.getMinX();
/*  584 */       max = plotArea.getMaxX();
/*  586 */     } else if (RectangleEdge.isLeftOrRight(edge)) {
/*  587 */       min = plotArea.getMaxY();
/*  588 */       max = plotArea.getMinY();
/*      */     } 
/*  591 */     value = switchedLog10(value);
/*  593 */     if (isInverted())
/*  594 */       return max - (value - axisMin) / (axisMax - axisMin) * (max - min); 
/*  598 */     return min + (value - axisMin) / (axisMax - axisMin) * (max - min);
/*      */   }
/*      */   
/*      */   public double java2DToValue(double java2DValue, Rectangle2D plotArea, RectangleEdge edge) {
/*  618 */     Range range = getRange();
/*  619 */     double axisMin = switchedLog10(range.getLowerBound());
/*  620 */     double axisMax = switchedLog10(range.getUpperBound());
/*  622 */     double plotMin = 0.0D;
/*  623 */     double plotMax = 0.0D;
/*  624 */     if (RectangleEdge.isTopOrBottom(edge)) {
/*  625 */       plotMin = plotArea.getX();
/*  626 */       plotMax = plotArea.getMaxX();
/*  628 */     } else if (RectangleEdge.isLeftOrRight(edge)) {
/*  629 */       plotMin = plotArea.getMaxY();
/*  630 */       plotMax = plotArea.getMinY();
/*      */     } 
/*  633 */     if (isInverted())
/*  634 */       return Math.pow(10.0D, axisMax - (java2DValue - plotMin) / (plotMax - plotMin) * (axisMax - axisMin)); 
/*  640 */     return Math.pow(10.0D, axisMin + (java2DValue - plotMin) / (plotMax - plotMin) * (axisMax - axisMin));
/*      */   }
/*      */   
/*      */   protected List refreshTicksHorizontal(Graphics2D g2, Rectangle2D dataArea, RectangleEdge edge) {
/*  661 */     List ticks = new ArrayList();
/*  662 */     Range range = getRange();
/*  665 */     double lowerBoundVal = range.getLowerBound();
/*  668 */     if (this.smallLogFlag && lowerBoundVal < 1.0E-100D)
/*  669 */       lowerBoundVal = 1.0E-100D; 
/*  673 */     double upperBoundVal = range.getUpperBound();
/*  676 */     int iBegCount = (int)Math.rint(switchedLog10(lowerBoundVal));
/*  678 */     int iEndCount = (int)Math.rint(switchedLog10(upperBoundVal));
/*  680 */     if (iBegCount == iEndCount && iBegCount > 0 && Math.pow(10.0D, iBegCount) > lowerBoundVal)
/*  684 */       iBegCount--; 
/*  689 */     boolean zeroTickFlag = false;
/*  690 */     for (int i = iBegCount; i <= iEndCount; i++) {
/*  692 */       for (int j = 0; j < 10; j++) {
/*      */         double currentTickValue;
/*      */         String tickLabel;
/*  694 */         if (this.smallLogFlag) {
/*  696 */           currentTickValue = Math.pow(10.0D, i) + Math.pow(10.0D, i) * j;
/*  697 */           if (this.expTickLabelsFlag || (i < 0 && currentTickValue > 0.0D && currentTickValue < 1.0D)) {
/*  702 */             if (j == 0 || (i > -4 && j < 2) || currentTickValue >= upperBoundVal) {
/*  708 */               this.numberFormatterObj.setMaximumFractionDigits(-i);
/*  711 */               tickLabel = makeTickLabel(currentTickValue, true);
/*      */             } else {
/*  714 */               tickLabel = "";
/*      */             } 
/*      */           } else {
/*  721 */             tickLabel = (j < 1 || (i < 1 && j < 5) || j < 4 - i || currentTickValue >= upperBoundVal) ? makeTickLabel(currentTickValue) : "";
/*      */           } 
/*      */         } else {
/*  727 */           if (zeroTickFlag)
/*  728 */             j--; 
/*  730 */           currentTickValue = (i >= 0) ? (Math.pow(10.0D, i) + Math.pow(10.0D, i) * j) : -(Math.pow(10.0D, -i) - Math.pow(10.0D, (-i - 1)) * j);
/*  733 */           if (!zeroTickFlag) {
/*  734 */             if (Math.abs(currentTickValue - 1.0D) < 1.0E-4D && lowerBoundVal <= 0.0D && upperBoundVal >= 0.0D) {
/*  737 */               currentTickValue = 0.0D;
/*  738 */               zeroTickFlag = true;
/*      */             } 
/*      */           } else {
/*  742 */             zeroTickFlag = false;
/*      */           } 
/*  748 */           tickLabel = ((this.expTickLabelsFlag && j < 2) || j < 1 || (i < 1 && j < 5) || j < 4 - i || currentTickValue >= upperBoundVal) ? makeTickLabel(currentTickValue) : "";
/*      */         } 
/*  755 */         if (currentTickValue > upperBoundVal)
/*  756 */           return ticks; 
/*  760 */         if (currentTickValue >= lowerBoundVal - 1.0E-100D) {
/*  762 */           TextAnchor anchor = null;
/*  763 */           TextAnchor rotationAnchor = null;
/*  764 */           double angle = 0.0D;
/*  765 */           if (isVerticalTickLabels()) {
/*  766 */             anchor = TextAnchor.CENTER_RIGHT;
/*  767 */             rotationAnchor = TextAnchor.CENTER_RIGHT;
/*  768 */             if (edge == RectangleEdge.TOP) {
/*  769 */               angle = 1.5707963267948966D;
/*      */             } else {
/*  772 */               angle = -1.5707963267948966D;
/*      */             } 
/*  776 */           } else if (edge == RectangleEdge.TOP) {
/*  777 */             anchor = TextAnchor.BOTTOM_CENTER;
/*  778 */             rotationAnchor = TextAnchor.BOTTOM_CENTER;
/*      */           } else {
/*  781 */             anchor = TextAnchor.TOP_CENTER;
/*  782 */             rotationAnchor = TextAnchor.TOP_CENTER;
/*      */           } 
/*  786 */           Tick tick = new NumberTick(new Double(currentTickValue), tickLabel, anchor, rotationAnchor, angle);
/*  790 */           ticks.add(tick);
/*      */         } 
/*      */       } 
/*      */     } 
/*  794 */     return ticks;
/*      */   }
/*      */   
/*      */   protected List refreshTicksVertical(Graphics2D g2, Rectangle2D dataArea, RectangleEdge edge) {
/*  812 */     List ticks = new ArrayList();
/*  815 */     double lowerBoundVal = getRange().getLowerBound();
/*  818 */     if (this.smallLogFlag && lowerBoundVal < 1.0E-100D)
/*  819 */       lowerBoundVal = 1.0E-100D; 
/*  822 */     double upperBoundVal = getRange().getUpperBound();
/*  825 */     int iBegCount = (int)Math.rint(switchedLog10(lowerBoundVal));
/*  827 */     int iEndCount = (int)Math.rint(switchedLog10(upperBoundVal));
/*  829 */     if (iBegCount == iEndCount && iBegCount > 0 && Math.pow(10.0D, iBegCount) > lowerBoundVal)
/*  833 */       iBegCount--; 
/*  838 */     boolean zeroTickFlag = false;
/*  839 */     for (int i = iBegCount; i <= iEndCount; i++) {
/*  841 */       int jEndCount = 10;
/*  842 */       if (i == iEndCount)
/*  843 */         jEndCount = 1; 
/*  846 */       for (int j = 0; j < jEndCount; j++) {
/*      */         double tickVal;
/*      */         String tickLabel;
/*  848 */         if (this.smallLogFlag) {
/*  850 */           tickVal = Math.pow(10.0D, i) + Math.pow(10.0D, i) * j;
/*  851 */           if (j == 0) {
/*  853 */             if (this.log10TickLabelsFlag) {
/*  855 */               tickLabel = "10^" + i;
/*  858 */             } else if (this.expTickLabelsFlag) {
/*  860 */               tickLabel = "1e" + i;
/*  863 */             } else if (i >= 0) {
/*  865 */               NumberFormat format = getNumberFormatOverride();
/*  867 */               if (format != null) {
/*  868 */                 tickLabel = format.format(tickVal);
/*      */               } else {
/*  871 */                 tickLabel = Long.toString((long)Math.rint(tickVal));
/*      */               } 
/*      */             } else {
/*  879 */               this.numberFormatterObj.setMaximumFractionDigits(-i);
/*  882 */               tickLabel = this.numberFormatterObj.format(tickVal);
/*      */             } 
/*      */           } else {
/*  890 */             tickLabel = "";
/*      */           } 
/*      */         } else {
/*  894 */           if (zeroTickFlag)
/*  895 */             j--; 
/*  897 */           tickVal = (i >= 0) ? (Math.pow(10.0D, i) + Math.pow(10.0D, i) * j) : -(Math.pow(10.0D, -i) - Math.pow(10.0D, (-i - 1)) * j);
/*  899 */           if (j == 0) {
/*  900 */             if (!zeroTickFlag) {
/*  902 */               if (i > iBegCount && i < iEndCount && Math.abs(tickVal - 1.0D) < 1.0E-4D) {
/*  906 */                 tickVal = 0.0D;
/*  907 */                 zeroTickFlag = true;
/*  908 */                 tickLabel = "0";
/*  913 */               } else if (this.log10TickLabelsFlag) {
/*  915 */                 tickLabel = ((i < 0) ? "-" : "") + "10^" + Math.abs(i);
/*  919 */               } else if (this.expTickLabelsFlag) {
/*  921 */                 tickLabel = ((i < 0) ? "-" : "") + "1e" + Math.abs(i);
/*      */               } else {
/*  925 */                 NumberFormat format = getNumberFormatOverride();
/*  927 */                 if (format != null) {
/*  928 */                   tickLabel = format.format(tickVal);
/*      */                 } else {
/*  931 */                   tickLabel = Long.toString((long)Math.rint(tickVal));
/*      */                 } 
/*      */               } 
/*      */             } else {
/*  940 */               tickLabel = "";
/*  941 */               zeroTickFlag = false;
/*      */             } 
/*      */           } else {
/*  945 */             tickLabel = "";
/*  946 */             zeroTickFlag = false;
/*      */           } 
/*      */         } 
/*  950 */         if (tickVal > upperBoundVal)
/*  951 */           return ticks; 
/*  954 */         if (tickVal >= lowerBoundVal - 1.0E-100D) {
/*  956 */           TextAnchor anchor = null;
/*  957 */           TextAnchor rotationAnchor = null;
/*  958 */           double angle = 0.0D;
/*  959 */           if (isVerticalTickLabels()) {
/*  960 */             if (edge == RectangleEdge.LEFT) {
/*  961 */               anchor = TextAnchor.BOTTOM_CENTER;
/*  962 */               rotationAnchor = TextAnchor.BOTTOM_CENTER;
/*  963 */               angle = -1.5707963267948966D;
/*      */             } else {
/*  966 */               anchor = TextAnchor.BOTTOM_CENTER;
/*  967 */               rotationAnchor = TextAnchor.BOTTOM_CENTER;
/*  968 */               angle = 1.5707963267948966D;
/*      */             } 
/*  972 */           } else if (edge == RectangleEdge.LEFT) {
/*  973 */             anchor = TextAnchor.CENTER_RIGHT;
/*  974 */             rotationAnchor = TextAnchor.CENTER_RIGHT;
/*      */           } else {
/*  977 */             anchor = TextAnchor.CENTER_LEFT;
/*  978 */             rotationAnchor = TextAnchor.CENTER_LEFT;
/*      */           } 
/*  982 */           ticks.add(new NumberTick(new Double(tickVal), tickLabel, anchor, rotationAnchor, angle));
/*      */         } 
/*      */       } 
/*      */     } 
/*  992 */     return ticks;
/*      */   }
/*      */   
/*      */   protected String makeTickLabel(double val, boolean forceFmtFlag) {
/* 1005 */     if (this.expTickLabelsFlag || forceFmtFlag)
/* 1008 */       return this.numberFormatterObj.format(val).toLowerCase(); 
/* 1010 */     return getTickUnit().valueToString(val);
/*      */   }
/*      */   
/*      */   protected String makeTickLabel(double val) {
/* 1020 */     return makeTickLabel(val, false);
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\axis\LogarithmicAxis.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */