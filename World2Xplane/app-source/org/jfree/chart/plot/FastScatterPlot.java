/*     */ package org.jfree.chart.plot;
/*     */ 
/*     */ import java.awt.AlphaComposite;
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Composite;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Shape;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.Line2D;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.ResourceBundle;
/*     */ import org.jfree.chart.axis.AxisSpace;
/*     */ import org.jfree.chart.axis.AxisState;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.axis.ValueTick;
/*     */ import org.jfree.chart.event.PlotChangeEvent;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.ui.RectangleInsets;
/*     */ import org.jfree.util.ArrayUtilities;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ import org.jfree.util.PaintUtilities;
/*     */ 
/*     */ public class FastScatterPlot extends Plot implements ValueAxisPlot, Zoomable, Cloneable, Serializable {
/*     */   private static final long serialVersionUID = 7871545897358563521L;
/*     */   
/* 104 */   public static final Stroke DEFAULT_GRIDLINE_STROKE = new BasicStroke(0.5F, 0, 2, 0.0F, new float[] { 2.0F, 2.0F }, 0.0F);
/*     */   
/* 112 */   public static final Paint DEFAULT_GRIDLINE_PAINT = Color.lightGray;
/*     */   
/*     */   private float[][] data;
/*     */   
/*     */   private Range xDataRange;
/*     */   
/*     */   private Range yDataRange;
/*     */   
/*     */   private ValueAxis domainAxis;
/*     */   
/*     */   private ValueAxis rangeAxis;
/*     */   
/*     */   private transient Paint paint;
/*     */   
/*     */   private boolean domainGridlinesVisible;
/*     */   
/*     */   private transient Stroke domainGridlineStroke;
/*     */   
/*     */   private transient Paint domainGridlinePaint;
/*     */   
/*     */   private boolean rangeGridlinesVisible;
/*     */   
/*     */   private transient Stroke rangeGridlineStroke;
/*     */   
/*     */   private transient Paint rangeGridlinePaint;
/*     */   
/* 151 */   protected static ResourceBundle localizationResources = ResourceBundle.getBundle("org.jfree.chart.plot.LocalizationBundle");
/*     */   
/*     */   public FastScatterPlot() {
/* 158 */     this((float[][])null, (ValueAxis)null, (ValueAxis)null);
/*     */   }
/*     */   
/*     */   public FastScatterPlot(float[][] data, ValueAxis domainAxis, ValueAxis rangeAxis) {
/* 175 */     this.data = data;
/* 176 */     this.xDataRange = calculateXDataRange(data);
/* 177 */     this.yDataRange = calculateYDataRange(data);
/* 178 */     this.domainAxis = domainAxis;
/* 179 */     if (domainAxis != null) {
/* 180 */       domainAxis.setPlot(this);
/* 181 */       domainAxis.addChangeListener(this);
/*     */     } 
/* 184 */     this.rangeAxis = rangeAxis;
/* 185 */     if (rangeAxis != null) {
/* 186 */       rangeAxis.setPlot(this);
/* 187 */       rangeAxis.addChangeListener(this);
/*     */     } 
/* 190 */     this.paint = Color.red;
/* 192 */     this.domainGridlinesVisible = true;
/* 193 */     this.domainGridlinePaint = DEFAULT_GRIDLINE_PAINT;
/* 194 */     this.domainGridlineStroke = DEFAULT_GRIDLINE_STROKE;
/* 196 */     this.rangeGridlinesVisible = true;
/* 197 */     this.rangeGridlinePaint = DEFAULT_GRIDLINE_PAINT;
/* 198 */     this.rangeGridlineStroke = DEFAULT_GRIDLINE_STROKE;
/*     */   }
/*     */   
/*     */   public String getPlotType() {
/* 208 */     return localizationResources.getString("Fast_Scatter_Plot");
/*     */   }
/*     */   
/*     */   public float[][] getData() {
/* 217 */     return this.data;
/*     */   }
/*     */   
/*     */   public void setData(float[][] data) {
/* 227 */     this.data = data;
/* 228 */     notifyListeners(new PlotChangeEvent(this));
/*     */   }
/*     */   
/*     */   public PlotOrientation getOrientation() {
/* 237 */     return PlotOrientation.VERTICAL;
/*     */   }
/*     */   
/*     */   public ValueAxis getDomainAxis() {
/* 248 */     return this.domainAxis;
/*     */   }
/*     */   
/*     */   public ValueAxis getRangeAxis() {
/* 259 */     return this.rangeAxis;
/*     */   }
/*     */   
/*     */   public Paint getPaint() {
/* 268 */     return this.paint;
/*     */   }
/*     */   
/*     */   public void setPaint(Paint paint) {
/* 278 */     if (paint == null)
/* 279 */       throw new IllegalArgumentException("Null 'paint' argument."); 
/* 281 */     this.paint = paint;
/* 282 */     notifyListeners(new PlotChangeEvent(this));
/*     */   }
/*     */   
/*     */   public boolean isDomainGridlinesVisible() {
/* 292 */     return this.domainGridlinesVisible;
/*     */   }
/*     */   
/*     */   public void setDomainGridlinesVisible(boolean visible) {
/* 303 */     if (this.domainGridlinesVisible != visible) {
/* 304 */       this.domainGridlinesVisible = visible;
/* 305 */       notifyListeners(new PlotChangeEvent(this));
/*     */     } 
/*     */   }
/*     */   
/*     */   public Stroke getDomainGridlineStroke() {
/* 316 */     return this.domainGridlineStroke;
/*     */   }
/*     */   
/*     */   public void setDomainGridlineStroke(Stroke stroke) {
/* 327 */     this.domainGridlineStroke = stroke;
/* 328 */     notifyListeners(new PlotChangeEvent(this));
/*     */   }
/*     */   
/*     */   public Paint getDomainGridlinePaint() {
/* 338 */     return this.domainGridlinePaint;
/*     */   }
/*     */   
/*     */   public void setDomainGridlinePaint(Paint paint) {
/* 349 */     this.domainGridlinePaint = paint;
/* 350 */     notifyListeners(new PlotChangeEvent(this));
/*     */   }
/*     */   
/*     */   public boolean isRangeGridlinesVisible() {
/* 360 */     return this.rangeGridlinesVisible;
/*     */   }
/*     */   
/*     */   public void setRangeGridlinesVisible(boolean visible) {
/* 371 */     if (this.rangeGridlinesVisible != visible) {
/* 372 */       this.rangeGridlinesVisible = visible;
/* 373 */       notifyListeners(new PlotChangeEvent(this));
/*     */     } 
/*     */   }
/*     */   
/*     */   public Stroke getRangeGridlineStroke() {
/* 384 */     return this.rangeGridlineStroke;
/*     */   }
/*     */   
/*     */   public void setRangeGridlineStroke(Stroke stroke) {
/* 395 */     this.rangeGridlineStroke = stroke;
/* 396 */     notifyListeners(new PlotChangeEvent(this));
/*     */   }
/*     */   
/*     */   public Paint getRangeGridlinePaint() {
/* 406 */     return this.rangeGridlinePaint;
/*     */   }
/*     */   
/*     */   public void setRangeGridlinePaint(Paint paint) {
/* 417 */     this.rangeGridlinePaint = paint;
/* 418 */     notifyListeners(new PlotChangeEvent(this));
/*     */   }
/*     */   
/*     */   public void draw(Graphics2D g2, Rectangle2D area, Point2D anchor, PlotState parentState, PlotRenderingInfo info) {
/* 438 */     if (info != null)
/* 439 */       info.setPlotArea(area); 
/* 443 */     RectangleInsets insets = getInsets();
/* 444 */     insets.trim(area);
/* 446 */     AxisSpace space = new AxisSpace();
/* 447 */     space = this.domainAxis.reserveSpace(g2, this, area, RectangleEdge.BOTTOM, space);
/* 450 */     space = this.rangeAxis.reserveSpace(g2, this, area, RectangleEdge.LEFT, space);
/* 453 */     Rectangle2D dataArea = space.shrink(area, null);
/* 455 */     if (info != null)
/* 456 */       info.setDataArea(dataArea); 
/* 460 */     drawBackground(g2, dataArea);
/* 462 */     AxisState domainAxisState = null;
/* 463 */     AxisState rangeAxisState = null;
/* 464 */     if (this.domainAxis != null)
/* 465 */       domainAxisState = this.domainAxis.draw(g2, dataArea.getMaxY(), area, dataArea, RectangleEdge.BOTTOM, info); 
/* 470 */     if (this.rangeAxis != null)
/* 471 */       rangeAxisState = this.rangeAxis.draw(g2, dataArea.getMinX(), area, dataArea, RectangleEdge.LEFT, info); 
/* 476 */     drawDomainGridlines(g2, dataArea, domainAxisState.getTicks());
/* 477 */     drawRangeGridlines(g2, dataArea, rangeAxisState.getTicks());
/* 479 */     Shape originalClip = g2.getClip();
/* 480 */     Composite originalComposite = g2.getComposite();
/* 482 */     g2.clip(dataArea);
/* 483 */     g2.setComposite(AlphaComposite.getInstance(3, getForegroundAlpha()));
/* 489 */     render(g2, dataArea, info, (CrosshairState)null);
/* 491 */     g2.setClip(originalClip);
/* 492 */     g2.setComposite(originalComposite);
/* 493 */     drawOutline(g2, dataArea);
/*     */   }
/*     */   
/*     */   public void render(Graphics2D g2, Rectangle2D dataArea, PlotRenderingInfo info, CrosshairState crosshairState) {
/* 514 */     g2.setPaint(this.paint);
/* 529 */     if (this.data != null)
/* 530 */       for (int i = 0; i < (this.data[0]).length; i++) {
/* 531 */         float x = this.data[0][i];
/* 532 */         float y = this.data[1][i];
/* 536 */         int transX = (int)this.domainAxis.valueToJava2D(x, dataArea, RectangleEdge.BOTTOM);
/* 539 */         int transY = (int)this.rangeAxis.valueToJava2D(y, dataArea, RectangleEdge.LEFT);
/* 542 */         g2.fillRect(transX, transY, 1, 1);
/*     */       }  
/*     */   }
/*     */   
/*     */   protected void drawDomainGridlines(Graphics2D g2, Rectangle2D dataArea, List ticks) {
/* 562 */     if (isDomainGridlinesVisible()) {
/* 563 */       Stroke gridStroke = getDomainGridlineStroke();
/* 564 */       Paint gridPaint = getDomainGridlinePaint();
/* 565 */       if (gridStroke != null && gridPaint != null) {
/* 566 */         Iterator iterator = ticks.iterator();
/* 567 */         while (iterator.hasNext()) {
/* 568 */           ValueTick tick = iterator.next();
/* 569 */           double v = this.domainAxis.valueToJava2D(tick.getValue(), dataArea, RectangleEdge.BOTTOM);
/* 572 */           Line2D line = new Line2D.Double(v, dataArea.getMinY(), v, dataArea.getMaxY());
/* 575 */           g2.setPaint(gridPaint);
/* 576 */           g2.setStroke(gridStroke);
/* 577 */           g2.draw(line);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void drawRangeGridlines(Graphics2D g2, Rectangle2D dataArea, List ticks) {
/* 594 */     if (isRangeGridlinesVisible()) {
/* 595 */       Stroke gridStroke = getRangeGridlineStroke();
/* 596 */       Paint gridPaint = getRangeGridlinePaint();
/* 597 */       if (gridStroke != null && gridPaint != null) {
/* 598 */         Iterator iterator = ticks.iterator();
/* 599 */         while (iterator.hasNext()) {
/* 600 */           ValueTick tick = iterator.next();
/* 601 */           double v = this.rangeAxis.valueToJava2D(tick.getValue(), dataArea, RectangleEdge.LEFT);
/* 604 */           Line2D line = new Line2D.Double(dataArea.getMinX(), v, dataArea.getMaxX(), v);
/* 607 */           g2.setPaint(gridPaint);
/* 608 */           g2.setStroke(gridStroke);
/* 609 */           g2.draw(line);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Range getDataRange(ValueAxis axis) {
/* 625 */     Range result = null;
/* 626 */     if (axis == this.domainAxis) {
/* 627 */       result = this.xDataRange;
/* 629 */     } else if (axis == this.rangeAxis) {
/* 630 */       result = this.yDataRange;
/*     */     } 
/* 632 */     return result;
/*     */   }
/*     */   
/*     */   private Range calculateXDataRange(float[][] data) {
/* 644 */     Range result = null;
/* 646 */     if (data != null) {
/* 647 */       float lowest = Float.POSITIVE_INFINITY;
/* 648 */       float highest = Float.NEGATIVE_INFINITY;
/* 649 */       for (int i = 0; i < (data[0]).length; i++) {
/* 650 */         float v = data[0][i];
/* 651 */         if (v < lowest)
/* 652 */           lowest = v; 
/* 654 */         if (v > highest)
/* 655 */           highest = v; 
/*     */       } 
/* 658 */       if (lowest <= highest)
/* 659 */         result = new Range(lowest, highest); 
/*     */     } 
/* 663 */     return result;
/*     */   }
/*     */   
/*     */   private Range calculateYDataRange(float[][] data) {
/* 676 */     Range result = null;
/* 678 */     if (data != null) {
/* 679 */       float lowest = Float.POSITIVE_INFINITY;
/* 680 */       float highest = Float.NEGATIVE_INFINITY;
/* 681 */       for (int i = 0; i < (data[0]).length; i++) {
/* 682 */         float v = data[1][i];
/* 683 */         if (v < lowest)
/* 684 */           lowest = v; 
/* 686 */         if (v > highest)
/* 687 */           highest = v; 
/*     */       } 
/* 690 */       if (lowest <= highest)
/* 691 */         result = new Range(lowest, highest); 
/*     */     } 
/* 694 */     return result;
/*     */   }
/*     */   
/*     */   public void zoomDomainAxes(double factor, PlotRenderingInfo info, Point2D source) {
/* 707 */     this.domainAxis.resizeRange(factor);
/*     */   }
/*     */   
/*     */   public void zoomDomainAxes(double lowerPercent, double upperPercent, PlotRenderingInfo info, Point2D source) {
/* 722 */     this.domainAxis.zoomRange(lowerPercent, upperPercent);
/*     */   }
/*     */   
/*     */   public void zoomRangeAxes(double factor, PlotRenderingInfo info, Point2D source) {
/* 734 */     this.rangeAxis.resizeRange(factor);
/*     */   }
/*     */   
/*     */   public void zoomRangeAxes(double lowerPercent, double upperPercent, PlotRenderingInfo info, Point2D source) {
/* 749 */     this.rangeAxis.zoomRange(lowerPercent, upperPercent);
/*     */   }
/*     */   
/*     */   public boolean isDomainZoomable() {
/* 758 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isRangeZoomable() {
/* 767 */     return true;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 778 */     if (obj == this)
/* 779 */       return true; 
/* 781 */     if (!super.equals(obj))
/* 782 */       return false; 
/* 784 */     if (!(obj instanceof FastScatterPlot))
/* 785 */       return false; 
/* 787 */     FastScatterPlot that = (FastScatterPlot)obj;
/* 788 */     if (!ArrayUtilities.equal(this.data, that.data))
/* 789 */       return false; 
/* 791 */     if (!ObjectUtilities.equal(this.domainAxis, that.domainAxis))
/* 792 */       return false; 
/* 794 */     if (!ObjectUtilities.equal(this.rangeAxis, that.rangeAxis))
/* 795 */       return false; 
/* 797 */     if (!PaintUtilities.equal(this.paint, that.paint))
/* 798 */       return false; 
/* 800 */     if (this.domainGridlinesVisible != that.domainGridlinesVisible)
/* 801 */       return false; 
/* 803 */     if (!PaintUtilities.equal(this.domainGridlinePaint, that.domainGridlinePaint))
/* 805 */       return false; 
/* 807 */     if (!ObjectUtilities.equal(this.domainGridlineStroke, that.domainGridlineStroke))
/* 809 */       return false; 
/* 811 */     if ((!this.rangeGridlinesVisible) == that.rangeGridlinesVisible)
/* 812 */       return false; 
/* 814 */     if (!PaintUtilities.equal(this.rangeGridlinePaint, that.rangeGridlinePaint))
/* 816 */       return false; 
/* 818 */     if (!ObjectUtilities.equal(this.rangeGridlineStroke, that.rangeGridlineStroke))
/* 820 */       return false; 
/* 822 */     return true;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 835 */     FastScatterPlot clone = (FastScatterPlot)super.clone();
/* 837 */     if (this.data != null)
/* 838 */       clone.data = ArrayUtilities.clone(this.data); 
/* 841 */     if (this.domainAxis != null) {
/* 842 */       clone.domainAxis = (ValueAxis)this.domainAxis.clone();
/* 843 */       clone.domainAxis.setPlot(clone);
/* 844 */       clone.domainAxis.addChangeListener(clone);
/*     */     } 
/* 847 */     if (this.rangeAxis != null) {
/* 848 */       clone.rangeAxis = (ValueAxis)this.rangeAxis.clone();
/* 849 */       clone.rangeAxis.setPlot(clone);
/* 850 */       clone.rangeAxis.addChangeListener(clone);
/*     */     } 
/* 853 */     return clone;
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream stream) throws IOException {
/* 865 */     stream.defaultWriteObject();
/* 866 */     SerialUtilities.writePaint(this.paint, stream);
/* 867 */     SerialUtilities.writeStroke(this.domainGridlineStroke, stream);
/* 868 */     SerialUtilities.writePaint(this.domainGridlinePaint, stream);
/* 869 */     SerialUtilities.writeStroke(this.rangeGridlineStroke, stream);
/* 870 */     SerialUtilities.writePaint(this.rangeGridlinePaint, stream);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
/* 883 */     stream.defaultReadObject();
/* 885 */     this.paint = SerialUtilities.readPaint(stream);
/* 886 */     this.domainGridlineStroke = SerialUtilities.readStroke(stream);
/* 887 */     this.domainGridlinePaint = SerialUtilities.readPaint(stream);
/* 889 */     this.rangeGridlineStroke = SerialUtilities.readStroke(stream);
/* 890 */     this.rangeGridlinePaint = SerialUtilities.readPaint(stream);
/* 892 */     if (this.domainAxis != null)
/* 893 */       this.domainAxis.addChangeListener(this); 
/* 896 */     if (this.rangeAxis != null)
/* 897 */       this.rangeAxis.addChangeListener(this); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\plot\FastScatterPlot.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */