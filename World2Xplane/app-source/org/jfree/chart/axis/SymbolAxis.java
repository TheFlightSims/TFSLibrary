/*     */ package org.jfree.chart.axis;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.text.NumberFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.jfree.chart.event.AxisChangeEvent;
/*     */ import org.jfree.chart.plot.Plot;
/*     */ import org.jfree.chart.plot.PlotRenderingInfo;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.text.TextUtilities;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.ui.TextAnchor;
/*     */ import org.jfree.util.PaintUtilities;
/*     */ 
/*     */ public class SymbolAxis extends NumberAxis implements Serializable {
/*     */   private static final long serialVersionUID = 7216330468770619716L;
/*     */   
/* 116 */   public static final Paint DEFAULT_GRID_BAND_PAINT = new Color(232, 234, 232, 128);
/*     */   
/*     */   private List symbols;
/*     */   
/*     */   private transient Paint gridBandPaint;
/*     */   
/*     */   private boolean gridBandsVisible;
/*     */   
/*     */   public SymbolAxis(String label, String[] sv) {
/* 137 */     super(label);
/* 138 */     this.symbols = Arrays.asList(sv);
/* 139 */     this.gridBandsVisible = true;
/* 140 */     this.gridBandPaint = DEFAULT_GRID_BAND_PAINT;
/* 142 */     setAutoTickUnitSelection(false, false);
/* 143 */     setAutoRangeStickyZero(false);
/*     */   }
/*     */   
/*     */   public String[] getSymbols() {
/* 153 */     String[] result = new String[this.symbols.size()];
/* 154 */     result = (String[])this.symbols.toArray((Object[])result);
/* 155 */     return result;
/*     */   }
/*     */   
/*     */   public Paint getGridBandPaint() {
/* 166 */     return this.gridBandPaint;
/*     */   }
/*     */   
/*     */   public void setGridBandPaint(Paint paint) {
/* 176 */     if (paint == null)
/* 177 */       throw new IllegalArgumentException("Null 'paint' argument."); 
/* 179 */     this.gridBandPaint = paint;
/* 180 */     notifyListeners(new AxisChangeEvent(this));
/*     */   }
/*     */   
/*     */   public boolean isGridBandsVisible() {
/* 191 */     return this.gridBandsVisible;
/*     */   }
/*     */   
/*     */   public void setGridBandsVisible(boolean flag) {
/* 201 */     if (this.gridBandsVisible != flag) {
/* 202 */       this.gridBandsVisible = flag;
/* 203 */       notifyListeners(new AxisChangeEvent(this));
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void selectAutoTickUnit(Graphics2D g2, Rectangle2D dataArea, RectangleEdge edge) {
/* 216 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public AxisState draw(Graphics2D g2, double cursor, Rectangle2D plotArea, Rectangle2D dataArea, RectangleEdge edge, PlotRenderingInfo plotState) {
/* 242 */     AxisState info = new AxisState(cursor);
/* 243 */     if (isVisible())
/* 244 */       info = super.draw(g2, cursor, plotArea, dataArea, edge, plotState); 
/* 246 */     if (this.gridBandsVisible)
/* 247 */       drawGridBands(g2, plotArea, dataArea, edge, info.getTicks()); 
/* 249 */     return info;
/*     */   }
/*     */   
/*     */   protected void drawGridBands(Graphics2D g2, Rectangle2D plotArea, Rectangle2D dataArea, RectangleEdge edge, List ticks) {
/* 271 */     Shape savedClip = g2.getClip();
/* 272 */     g2.clip(dataArea);
/* 273 */     if (RectangleEdge.isTopOrBottom(edge)) {
/* 274 */       drawGridBandsHorizontal(g2, plotArea, dataArea, true, ticks);
/* 276 */     } else if (RectangleEdge.isLeftOrRight(edge)) {
/* 277 */       drawGridBandsVertical(g2, plotArea, dataArea, true, ticks);
/*     */     } 
/* 279 */     g2.setClip(savedClip);
/*     */   }
/*     */   
/*     */   protected void drawGridBandsHorizontal(Graphics2D g2, Rectangle2D plotArea, Rectangle2D dataArea, boolean firstGridBandIsDark, List ticks) {
/*     */     double outlineStrokeWidth;
/* 303 */     boolean currentGridBandIsDark = firstGridBandIsDark;
/* 304 */     double yy = dataArea.getY();
/* 309 */     if (getPlot().getOutlineStroke() != null) {
/* 310 */       outlineStrokeWidth = ((BasicStroke)getPlot().getOutlineStroke()).getLineWidth();
/*     */     } else {
/* 314 */       outlineStrokeWidth = 1.0D;
/*     */     } 
/* 317 */     Iterator iterator = ticks.iterator();
/* 320 */     while (iterator.hasNext()) {
/* 321 */       ValueTick tick = iterator.next();
/* 322 */       double xx1 = valueToJava2D(tick.getValue() - 0.5D, dataArea, RectangleEdge.BOTTOM);
/* 325 */       double xx2 = valueToJava2D(tick.getValue() + 0.5D, dataArea, RectangleEdge.BOTTOM);
/* 328 */       if (currentGridBandIsDark) {
/* 329 */         g2.setPaint(this.gridBandPaint);
/*     */       } else {
/* 332 */         g2.setPaint(Color.white);
/*     */       } 
/* 334 */       Rectangle2D band = new Rectangle2D.Double(xx1, yy + outlineStrokeWidth, xx2 - xx1, dataArea.getMaxY() - yy - outlineStrokeWidth);
/* 336 */       g2.fill(band);
/* 337 */       currentGridBandIsDark = !currentGridBandIsDark;
/*     */     } 
/* 339 */     g2.setPaintMode();
/*     */   }
/*     */   
/*     */   protected void drawGridBandsVertical(Graphics2D g2, Rectangle2D drawArea, Rectangle2D plotArea, boolean firstGridBandIsDark, List ticks) {
/*     */     double outlineStrokeWidth;
/* 362 */     boolean currentGridBandIsDark = firstGridBandIsDark;
/* 363 */     double xx = plotArea.getX();
/* 368 */     if (getPlot().getOutlineStroke() != null) {
/* 369 */       outlineStrokeWidth = ((BasicStroke)getPlot().getOutlineStroke()).getLineWidth();
/*     */     } else {
/* 373 */       outlineStrokeWidth = 1.0D;
/*     */     } 
/* 376 */     Iterator iterator = ticks.iterator();
/* 379 */     while (iterator.hasNext()) {
/* 380 */       ValueTick tick = iterator.next();
/* 381 */       double yy1 = valueToJava2D(tick.getValue() + 0.5D, plotArea, RectangleEdge.LEFT);
/* 384 */       double yy2 = valueToJava2D(tick.getValue() - 0.5D, plotArea, RectangleEdge.LEFT);
/* 387 */       if (currentGridBandIsDark) {
/* 388 */         g2.setPaint(this.gridBandPaint);
/*     */       } else {
/* 391 */         g2.setPaint(Color.white);
/*     */       } 
/* 393 */       Rectangle2D band = new Rectangle2D.Double(xx + outlineStrokeWidth, yy1, plotArea.getMaxX() - xx - outlineStrokeWidth, yy2 - yy1);
/* 395 */       g2.fill(band);
/* 396 */       currentGridBandIsDark = !currentGridBandIsDark;
/*     */     } 
/* 398 */     g2.setPaintMode();
/*     */   }
/*     */   
/*     */   protected void autoAdjustRange() {
/* 406 */     Plot plot = getPlot();
/* 407 */     if (plot == null)
/*     */       return; 
/* 411 */     if (plot instanceof org.jfree.chart.plot.ValueAxisPlot) {
/* 414 */       double upper = (this.symbols.size() - 1);
/* 415 */       double lower = 0.0D;
/* 416 */       double range = upper - lower;
/* 419 */       double minRange = getAutoRangeMinimumSize();
/* 420 */       if (range < minRange) {
/* 421 */         upper = (upper + lower + minRange) / 2.0D;
/* 422 */         lower = (upper + lower - minRange) / 2.0D;
/*     */       } 
/* 426 */       double upperMargin = 0.5D;
/* 427 */       double lowerMargin = 0.5D;
/* 429 */       if (getAutoRangeIncludesZero()) {
/* 430 */         if (getAutoRangeStickyZero()) {
/* 431 */           if (upper <= 0.0D) {
/* 432 */             upper = 0.0D;
/*     */           } else {
/* 435 */             upper += upperMargin;
/*     */           } 
/* 437 */           if (lower >= 0.0D) {
/* 438 */             lower = 0.0D;
/*     */           } else {
/* 441 */             lower -= lowerMargin;
/*     */           } 
/*     */         } else {
/* 445 */           upper = Math.max(0.0D, upper + upperMargin);
/* 446 */           lower = Math.min(0.0D, lower - lowerMargin);
/*     */         } 
/* 450 */       } else if (getAutoRangeStickyZero()) {
/* 451 */         if (upper <= 0.0D) {
/* 452 */           upper = Math.min(0.0D, upper + upperMargin);
/*     */         } else {
/* 455 */           upper += upperMargin * range;
/*     */         } 
/* 457 */         if (lower >= 0.0D) {
/* 458 */           lower = Math.max(0.0D, lower - lowerMargin);
/*     */         } else {
/* 461 */           lower -= lowerMargin;
/*     */         } 
/*     */       } else {
/* 465 */         upper += upperMargin;
/* 466 */         lower -= lowerMargin;
/*     */       } 
/* 470 */       setRange(new Range(lower, upper), false, false);
/*     */     } 
/*     */   }
/*     */   
/*     */   public List refreshTicks(Graphics2D g2, AxisState state, Rectangle2D dataArea, RectangleEdge edge) {
/* 491 */     List ticks = null;
/* 492 */     if (RectangleEdge.isTopOrBottom(edge)) {
/* 493 */       ticks = refreshTicksHorizontal(g2, dataArea, edge);
/* 495 */     } else if (RectangleEdge.isLeftOrRight(edge)) {
/* 496 */       ticks = refreshTicksVertical(g2, dataArea, edge);
/*     */     } 
/* 498 */     return ticks;
/*     */   }
/*     */   
/*     */   protected List refreshTicksHorizontal(Graphics2D g2, Rectangle2D dataArea, RectangleEdge edge) {
/* 515 */     List ticks = new ArrayList();
/* 517 */     Font tickLabelFont = getTickLabelFont();
/* 518 */     g2.setFont(tickLabelFont);
/* 520 */     double size = getTickUnit().getSize();
/* 521 */     int count = calculateVisibleTickCount();
/* 522 */     double lowestTickValue = calculateLowestVisibleTickValue();
/* 524 */     double previousDrawnTickLabelPos = 0.0D;
/* 525 */     double previousDrawnTickLabelLength = 0.0D;
/* 527 */     if (count <= 500)
/* 528 */       for (int i = 0; i < count; i++) {
/*     */         String tickLabel;
/* 529 */         double currentTickValue = lowestTickValue + i * size;
/* 530 */         double xx = valueToJava2D(currentTickValue, dataArea, edge);
/* 532 */         NumberFormat formatter = getNumberFormatOverride();
/* 533 */         if (formatter != null) {
/* 534 */           tickLabel = formatter.format(currentTickValue);
/*     */         } else {
/* 537 */           tickLabel = valueToString(currentTickValue);
/*     */         } 
/* 541 */         Rectangle2D bounds = TextUtilities.getTextBounds(tickLabel, g2, g2.getFontMetrics());
/* 544 */         double tickLabelLength = isVerticalTickLabels() ? bounds.getHeight() : bounds.getWidth();
/* 546 */         boolean tickLabelsOverlapping = false;
/* 547 */         if (i > 0) {
/* 548 */           double avgTickLabelLength = (previousDrawnTickLabelLength + tickLabelLength) / 2.0D;
/* 550 */           if (Math.abs(xx - previousDrawnTickLabelPos) < avgTickLabelLength)
/* 552 */             tickLabelsOverlapping = true; 
/*     */         } 
/* 555 */         if (tickLabelsOverlapping) {
/* 556 */           tickLabel = "";
/*     */         } else {
/* 560 */           previousDrawnTickLabelPos = xx;
/* 561 */           previousDrawnTickLabelLength = tickLabelLength;
/*     */         } 
/* 564 */         TextAnchor anchor = null;
/* 565 */         TextAnchor rotationAnchor = null;
/* 566 */         double angle = 0.0D;
/* 567 */         if (isVerticalTickLabels()) {
/* 568 */           anchor = TextAnchor.CENTER_RIGHT;
/* 569 */           rotationAnchor = TextAnchor.CENTER_RIGHT;
/* 570 */           if (edge == RectangleEdge.TOP) {
/* 571 */             angle = 1.5707963267948966D;
/*     */           } else {
/* 574 */             angle = -1.5707963267948966D;
/*     */           } 
/* 578 */         } else if (edge == RectangleEdge.TOP) {
/* 579 */           anchor = TextAnchor.BOTTOM_CENTER;
/* 580 */           rotationAnchor = TextAnchor.BOTTOM_CENTER;
/*     */         } else {
/* 583 */           anchor = TextAnchor.TOP_CENTER;
/* 584 */           rotationAnchor = TextAnchor.TOP_CENTER;
/*     */         } 
/* 587 */         Tick tick = new NumberTick(new Double(currentTickValue), tickLabel, anchor, rotationAnchor, angle);
/* 591 */         ticks.add(tick);
/*     */       }  
/* 594 */     return ticks;
/*     */   }
/*     */   
/*     */   protected List refreshTicksVertical(Graphics2D g2, Rectangle2D dataArea, RectangleEdge edge) {
/* 612 */     List ticks = new ArrayList();
/* 614 */     Font tickLabelFont = getTickLabelFont();
/* 615 */     g2.setFont(tickLabelFont);
/* 617 */     double size = getTickUnit().getSize();
/* 618 */     int count = calculateVisibleTickCount();
/* 619 */     double lowestTickValue = calculateLowestVisibleTickValue();
/* 621 */     double previousDrawnTickLabelPos = 0.0D;
/* 622 */     double previousDrawnTickLabelLength = 0.0D;
/* 624 */     if (count <= 500)
/* 625 */       for (int i = 0; i < count; i++) {
/*     */         String tickLabel;
/* 626 */         double currentTickValue = lowestTickValue + i * size;
/* 627 */         double yy = valueToJava2D(currentTickValue, dataArea, edge);
/* 629 */         NumberFormat formatter = getNumberFormatOverride();
/* 630 */         if (formatter != null) {
/* 631 */           tickLabel = formatter.format(currentTickValue);
/*     */         } else {
/* 634 */           tickLabel = valueToString(currentTickValue);
/*     */         } 
/* 638 */         Rectangle2D bounds = TextUtilities.getTextBounds(tickLabel, g2, g2.getFontMetrics());
/* 641 */         double tickLabelLength = isVerticalTickLabels() ? bounds.getWidth() : bounds.getHeight();
/* 643 */         boolean tickLabelsOverlapping = false;
/* 644 */         if (i > 0) {
/* 645 */           double avgTickLabelLength = (previousDrawnTickLabelLength + tickLabelLength) / 2.0D;
/* 647 */           if (Math.abs(yy - previousDrawnTickLabelPos) < avgTickLabelLength)
/* 649 */             tickLabelsOverlapping = true; 
/* 651 */           if (tickLabelsOverlapping) {
/* 652 */             tickLabel = "";
/*     */           } else {
/* 656 */             previousDrawnTickLabelPos = yy;
/* 657 */             previousDrawnTickLabelLength = tickLabelLength;
/*     */           } 
/*     */         } 
/* 660 */         TextAnchor anchor = null;
/* 661 */         TextAnchor rotationAnchor = null;
/* 662 */         double angle = 0.0D;
/* 663 */         if (isVerticalTickLabels()) {
/* 664 */           anchor = TextAnchor.BOTTOM_CENTER;
/* 665 */           rotationAnchor = TextAnchor.BOTTOM_CENTER;
/* 666 */           if (edge == RectangleEdge.LEFT) {
/* 667 */             angle = -1.5707963267948966D;
/*     */           } else {
/* 670 */             angle = 1.5707963267948966D;
/*     */           } 
/* 674 */         } else if (edge == RectangleEdge.LEFT) {
/* 675 */           anchor = TextAnchor.CENTER_RIGHT;
/* 676 */           rotationAnchor = TextAnchor.CENTER_RIGHT;
/*     */         } else {
/* 679 */           anchor = TextAnchor.CENTER_LEFT;
/* 680 */           rotationAnchor = TextAnchor.CENTER_LEFT;
/*     */         } 
/* 683 */         Tick tick = new NumberTick(new Double(currentTickValue), tickLabel, anchor, rotationAnchor, angle);
/* 687 */         ticks.add(tick);
/*     */       }  
/* 690 */     return ticks;
/*     */   }
/*     */   
/*     */   public String valueToString(double value) {
/*     */     String strToReturn;
/*     */     try {
/* 704 */       strToReturn = this.symbols.get((int)value);
/* 706 */     } catch (IndexOutOfBoundsException ex) {
/* 707 */       strToReturn = "";
/*     */     } 
/* 709 */     return strToReturn;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 720 */     if (obj == this)
/* 721 */       return true; 
/* 723 */     if (!(obj instanceof SymbolAxis))
/* 724 */       return false; 
/* 726 */     SymbolAxis that = (SymbolAxis)obj;
/* 727 */     if (!this.symbols.equals(that.symbols))
/* 728 */       return false; 
/* 730 */     if (this.gridBandsVisible != that.gridBandsVisible)
/* 731 */       return false; 
/* 733 */     if (!PaintUtilities.equal(this.gridBandPaint, that.gridBandPaint))
/* 734 */       return false; 
/* 736 */     if (!super.equals(obj))
/* 737 */       return false; 
/* 739 */     return true;
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream stream) throws IOException {
/* 750 */     stream.defaultWriteObject();
/* 751 */     SerialUtilities.writePaint(this.gridBandPaint, stream);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
/* 764 */     stream.defaultReadObject();
/* 765 */     this.gridBandPaint = SerialUtilities.readPaint(stream);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\axis\SymbolAxis.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */