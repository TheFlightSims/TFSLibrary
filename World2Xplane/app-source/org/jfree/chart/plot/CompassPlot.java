/*     */ package org.jfree.chart.plot;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Polygon;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.Area;
/*     */ import java.awt.geom.Ellipse2D;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.Serializable;
/*     */ import java.util.Arrays;
/*     */ import java.util.ResourceBundle;
/*     */ import org.jfree.chart.LegendItemCollection;
/*     */ import org.jfree.chart.event.PlotChangeEvent;
/*     */ import org.jfree.chart.needle.ArrowNeedle;
/*     */ import org.jfree.chart.needle.LineNeedle;
/*     */ import org.jfree.chart.needle.LongNeedle;
/*     */ import org.jfree.chart.needle.MeterNeedle;
/*     */ import org.jfree.chart.needle.MiddlePinNeedle;
/*     */ import org.jfree.chart.needle.PinNeedle;
/*     */ import org.jfree.chart.needle.PlumNeedle;
/*     */ import org.jfree.chart.needle.PointerNeedle;
/*     */ import org.jfree.chart.needle.ShipNeedle;
/*     */ import org.jfree.chart.needle.WindNeedle;
/*     */ import org.jfree.data.general.DefaultValueDataset;
/*     */ import org.jfree.data.general.ValueDataset;
/*     */ import org.jfree.ui.RectangleInsets;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ import org.jfree.util.PaintUtilities;
/*     */ 
/*     */ public class CompassPlot extends Plot implements Cloneable, Serializable {
/*     */   private static final long serialVersionUID = 6924382802125527395L;
/*     */   
/* 110 */   public static final Font DEFAULT_LABEL_FONT = new Font("SansSerif", 1, 10);
/*     */   
/*     */   public static final int NO_LABELS = 0;
/*     */   
/*     */   public static final int VALUE_LABELS = 1;
/*     */   
/*     */   private int labelType;
/*     */   
/*     */   private Font labelFont;
/*     */   
/*     */   private boolean drawBorder = false;
/*     */   
/* 129 */   private Paint roseHighlightPaint = Color.black;
/*     */   
/* 132 */   private Paint rosePaint = Color.yellow;
/*     */   
/* 135 */   private Paint roseCenterPaint = Color.white;
/*     */   
/* 138 */   private Font compassFont = new Font("Arial", 0, 10);
/*     */   
/*     */   private transient Ellipse2D circle1;
/*     */   
/*     */   private transient Ellipse2D circle2;
/*     */   
/*     */   private transient Area a1;
/*     */   
/*     */   private transient Area a2;
/*     */   
/*     */   private transient Rectangle2D rect1;
/*     */   
/* 156 */   private ValueDataset[] datasets = new ValueDataset[1];
/*     */   
/* 159 */   private MeterNeedle[] seriesNeedle = new MeterNeedle[1];
/*     */   
/* 162 */   protected static ResourceBundle localizationResources = ResourceBundle.getBundle("org.jfree.chart.plot.LocalizationBundle");
/*     */   
/* 168 */   protected double revolutionDistance = 360.0D;
/*     */   
/*     */   public CompassPlot() {
/* 174 */     this((ValueDataset)new DefaultValueDataset());
/*     */   }
/*     */   
/*     */   public CompassPlot(ValueDataset dataset) {
/* 184 */     if (dataset != null) {
/* 185 */       this.datasets[0] = dataset;
/* 186 */       dataset.addChangeListener(this);
/*     */     } 
/* 188 */     this.circle1 = new Ellipse2D.Double();
/* 189 */     this.circle2 = new Ellipse2D.Double();
/* 190 */     this.rect1 = new Rectangle2D.Double();
/* 191 */     setSeriesNeedle(0);
/*     */   }
/*     */   
/*     */   public int getLabelType() {
/* 201 */     return this.labelType;
/*     */   }
/*     */   
/*     */   public void setLabelType(int type) {
/* 210 */     if (type != 0 && type != 1)
/* 211 */       throw new IllegalArgumentException("MeterPlot.setLabelType(int): unrecognised type."); 
/* 215 */     if (this.labelType != type) {
/* 216 */       this.labelType = type;
/* 217 */       notifyListeners(new PlotChangeEvent(this));
/*     */     } 
/*     */   }
/*     */   
/*     */   public Font getLabelFont() {
/* 227 */     return this.labelFont;
/*     */   }
/*     */   
/*     */   public void setLabelFont(Font font) {
/* 237 */     if (font == null)
/* 238 */       throw new IllegalArgumentException("Null 'font' not allowed."); 
/* 240 */     this.labelFont = font;
/* 241 */     notifyListeners(new PlotChangeEvent(this));
/*     */   }
/*     */   
/*     */   public Paint getRosePaint() {
/* 250 */     return this.rosePaint;
/*     */   }
/*     */   
/*     */   public void setRosePaint(Paint paint) {
/* 260 */     if (paint == null)
/* 261 */       throw new IllegalArgumentException("Null 'paint' argument."); 
/* 263 */     this.rosePaint = paint;
/* 264 */     notifyListeners(new PlotChangeEvent(this));
/*     */   }
/*     */   
/*     */   public Paint getRoseCenterPaint() {
/* 274 */     return this.roseCenterPaint;
/*     */   }
/*     */   
/*     */   public void setRoseCenterPaint(Paint paint) {
/* 284 */     if (paint == null)
/* 285 */       throw new IllegalArgumentException("Null 'paint' argument."); 
/* 287 */     this.roseCenterPaint = paint;
/* 288 */     notifyListeners(new PlotChangeEvent(this));
/*     */   }
/*     */   
/*     */   public Paint getRoseHighlightPaint() {
/* 298 */     return this.roseHighlightPaint;
/*     */   }
/*     */   
/*     */   public void setRoseHighlightPaint(Paint paint) {
/* 308 */     if (paint == null)
/* 309 */       throw new IllegalArgumentException("Null 'paint' argument."); 
/* 311 */     this.roseHighlightPaint = paint;
/* 312 */     notifyListeners(new PlotChangeEvent(this));
/*     */   }
/*     */   
/*     */   public boolean getDrawBorder() {
/* 321 */     return this.drawBorder;
/*     */   }
/*     */   
/*     */   public void setDrawBorder(boolean status) {
/* 330 */     this.drawBorder = status;
/*     */   }
/*     */   
/*     */   public void setSeriesPaint(int series, Paint paint) {
/* 341 */     if (series >= 0 && series < this.seriesNeedle.length)
/* 342 */       this.seriesNeedle[series].setFillPaint(paint); 
/*     */   }
/*     */   
/*     */   public void setSeriesOutlinePaint(int series, Paint p) {
/* 354 */     if (series >= 0 && series < this.seriesNeedle.length)
/* 355 */       this.seriesNeedle[series].setOutlinePaint(p); 
/*     */   }
/*     */   
/*     */   public void setSeriesOutlineStroke(int series, Stroke stroke) {
/* 368 */     if (series >= 0 && series < this.seriesNeedle.length)
/* 369 */       this.seriesNeedle[series].setOutlineStroke(stroke); 
/*     */   }
/*     */   
/*     */   public void setSeriesNeedle(int type) {
/* 380 */     setSeriesNeedle(0, type);
/*     */   }
/*     */   
/*     */   public void setSeriesNeedle(int index, int type) {
/*     */     LongNeedle longNeedle;
/* 401 */     switch (type) {
/*     */       case 0:
/* 403 */         setSeriesNeedle(index, (MeterNeedle)new ArrowNeedle(true));
/* 404 */         setSeriesPaint(index, Color.red);
/* 405 */         this.seriesNeedle[index].setHighlightPaint(Color.white);
/*     */         return;
/*     */       case 1:
/* 408 */         setSeriesNeedle(index, (MeterNeedle)new LineNeedle());
/*     */         return;
/*     */       case 2:
/* 411 */         longNeedle = new LongNeedle();
/* 412 */         longNeedle.setRotateY(0.5D);
/* 413 */         setSeriesNeedle(index, (MeterNeedle)longNeedle);
/*     */         return;
/*     */       case 3:
/* 416 */         setSeriesNeedle(index, (MeterNeedle)new PinNeedle());
/*     */         return;
/*     */       case 4:
/* 419 */         setSeriesNeedle(index, (MeterNeedle)new PlumNeedle());
/*     */         return;
/*     */       case 5:
/* 422 */         setSeriesNeedle(index, (MeterNeedle)new PointerNeedle());
/*     */         return;
/*     */       case 6:
/* 425 */         setSeriesPaint(index, (Paint)null);
/* 426 */         setSeriesOutlineStroke(index, new BasicStroke(3.0F));
/* 427 */         setSeriesNeedle(index, (MeterNeedle)new ShipNeedle());
/*     */         return;
/*     */       case 7:
/* 430 */         setSeriesPaint(index, Color.blue);
/* 431 */         setSeriesNeedle(index, (MeterNeedle)new WindNeedle());
/*     */         return;
/*     */       case 8:
/* 434 */         setSeriesNeedle(index, (MeterNeedle)new ArrowNeedle(true));
/*     */         return;
/*     */       case 9:
/* 437 */         setSeriesNeedle(index, (MeterNeedle)new MiddlePinNeedle());
/*     */         return;
/*     */     } 
/* 441 */     throw new IllegalArgumentException("Unrecognised type.");
/*     */   }
/*     */   
/*     */   public void setSeriesNeedle(int index, MeterNeedle needle) {
/* 454 */     if (needle != null && index < this.seriesNeedle.length)
/* 455 */       this.seriesNeedle[index] = needle; 
/* 457 */     notifyListeners(new PlotChangeEvent(this));
/*     */   }
/*     */   
/*     */   public ValueDataset[] getDatasets() {
/* 469 */     return this.datasets;
/*     */   }
/*     */   
/*     */   public void addDataset(ValueDataset dataset) {
/* 478 */     addDataset(dataset, (MeterNeedle)null);
/*     */   }
/*     */   
/*     */   public void addDataset(ValueDataset dataset, MeterNeedle needle) {
/* 489 */     if (dataset != null) {
/* 490 */       int i = this.datasets.length + 1;
/* 491 */       ValueDataset[] t = new ValueDataset[i];
/* 492 */       MeterNeedle[] p = new MeterNeedle[i];
/* 493 */       i -= 2;
/* 494 */       for (; i >= 0; i--) {
/* 495 */         t[i] = this.datasets[i];
/* 496 */         p[i] = this.seriesNeedle[i];
/*     */       } 
/* 498 */       i = this.datasets.length;
/* 499 */       t[i] = dataset;
/* 500 */       p[i] = (needle != null) ? needle : p[i - 1];
/* 502 */       ValueDataset[] a = this.datasets;
/* 503 */       MeterNeedle[] b = this.seriesNeedle;
/* 504 */       this.datasets = t;
/* 505 */       this.seriesNeedle = p;
/* 507 */       for (; --i >= 0; i--) {
/* 508 */         a[i] = null;
/* 509 */         b[i] = null;
/*     */       } 
/* 511 */       dataset.addChangeListener(this);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void draw(Graphics2D g2, Rectangle2D area, Point2D anchor, PlotState parentState, PlotRenderingInfo info) {
/* 529 */     int outerRadius = 0;
/* 530 */     int innerRadius = 0;
/* 534 */     if (info != null)
/* 535 */       info.setPlotArea(area); 
/* 539 */     RectangleInsets insets = getInsets();
/* 540 */     insets.trim(area);
/* 543 */     if (this.drawBorder)
/* 544 */       drawBackground(g2, area); 
/* 547 */     int midX = (int)(area.getWidth() / 2.0D);
/* 548 */     int midY = (int)(area.getHeight() / 2.0D);
/* 549 */     int radius = midX;
/* 550 */     if (midY < midX)
/* 551 */       radius = midY; 
/* 553 */     radius--;
/* 554 */     int diameter = 2 * radius;
/* 556 */     midX += (int)area.getMinX();
/* 557 */     midY += (int)area.getMinY();
/* 559 */     this.circle1.setFrame((midX - radius), (midY - radius), diameter, diameter);
/* 560 */     this.circle2.setFrame((midX - radius + 15), (midY - radius + 15), (diameter - 30), (diameter - 30));
/* 564 */     g2.setPaint(this.rosePaint);
/* 565 */     this.a1 = new Area(this.circle1);
/* 566 */     this.a2 = new Area(this.circle2);
/* 567 */     this.a1.subtract(this.a2);
/* 568 */     g2.fill(this.a1);
/* 570 */     g2.setPaint(this.roseCenterPaint);
/* 571 */     int x1 = diameter - 30;
/* 572 */     g2.fillOval(midX - radius + 15, midY - radius + 15, x1, x1);
/* 573 */     g2.setPaint(this.roseHighlightPaint);
/* 574 */     g2.drawOval(midX - radius, midY - radius, diameter, diameter);
/* 575 */     x1 = diameter - 20;
/* 576 */     g2.drawOval(midX - radius + 10, midY - radius + 10, x1, x1);
/* 577 */     x1 = diameter - 30;
/* 578 */     g2.drawOval(midX - radius + 15, midY - radius + 15, x1, x1);
/* 579 */     x1 = diameter - 80;
/* 580 */     g2.drawOval(midX - radius + 40, midY - radius + 40, x1, x1);
/* 582 */     outerRadius = radius - 20;
/* 583 */     innerRadius = radius - 32;
/*     */     int w;
/* 584 */     for (w = 0; w < 360; w += 15) {
/* 585 */       double a = Math.toRadians(w);
/* 586 */       x1 = midX - (int)(Math.sin(a) * innerRadius);
/* 587 */       int x2 = midX - (int)(Math.sin(a) * outerRadius);
/* 588 */       int j = midY - (int)(Math.cos(a) * innerRadius);
/* 589 */       int y2 = midY - (int)(Math.cos(a) * outerRadius);
/* 590 */       g2.drawLine(x1, j, x2, y2);
/*     */     } 
/* 593 */     g2.setPaint(this.roseHighlightPaint);
/* 594 */     innerRadius = radius - 26;
/* 595 */     outerRadius = 7;
/* 596 */     for (w = 45; w < 360; w += 90) {
/* 597 */       double a = Math.toRadians(w);
/* 598 */       x1 = midX - (int)(Math.sin(a) * innerRadius);
/* 599 */       int j = midY - (int)(Math.cos(a) * innerRadius);
/* 600 */       g2.fillOval(x1 - outerRadius, j - outerRadius, 2 * outerRadius, 2 * outerRadius);
/*     */     } 
/* 607 */     for (w = 0; w < 360; w += 90) {
/* 608 */       double a = Math.toRadians(w);
/* 609 */       x1 = midX - (int)(Math.sin(a) * innerRadius);
/* 610 */       int j = midY - (int)(Math.cos(a) * innerRadius);
/* 612 */       Polygon p = new Polygon();
/* 613 */       p.addPoint(x1 - outerRadius, j);
/* 614 */       p.addPoint(x1, j + outerRadius);
/* 615 */       p.addPoint(x1 + outerRadius, j);
/* 616 */       p.addPoint(x1, j - outerRadius);
/* 617 */       g2.fillPolygon(p);
/*     */     } 
/* 621 */     innerRadius = radius - 42;
/* 622 */     Font f = getCompassFont(radius);
/* 623 */     g2.setFont(f);
/* 624 */     g2.drawString("N", midX - 5, midY - innerRadius + f.getSize());
/* 625 */     g2.drawString("S", midX - 5, midY + innerRadius - 5);
/* 626 */     g2.drawString("W", midX - innerRadius + 5, midY + 5);
/* 627 */     g2.drawString("E", midX + innerRadius - f.getSize(), midY + 5);
/* 630 */     int y1 = radius / 2;
/* 631 */     x1 = radius / 6;
/* 632 */     Rectangle2D needleArea = new Rectangle2D.Double((midX - x1), (midY - y1), (2 * x1), (2 * y1));
/* 635 */     int x = this.seriesNeedle.length;
/* 636 */     int current = 0;
/* 637 */     double value = 0.0D;
/* 638 */     int i = this.datasets.length - 1;
/* 639 */     for (; i >= 0; i--) {
/* 640 */       ValueDataset data = this.datasets[i];
/* 642 */       if (data != null && data.getValue() != null) {
/* 643 */         value = data.getValue().doubleValue() % this.revolutionDistance;
/* 645 */         value = value / this.revolutionDistance * 360.0D;
/* 646 */         current = i % x;
/* 647 */         this.seriesNeedle[current].draw(g2, needleArea, value);
/*     */       } 
/*     */     } 
/* 651 */     if (this.drawBorder)
/* 652 */       drawOutline(g2, area); 
/*     */   }
/*     */   
/*     */   public String getPlotType() {
/* 663 */     return localizationResources.getString("Compass_Plot");
/*     */   }
/*     */   
/*     */   public LegendItemCollection getLegendItems() {
/* 673 */     return null;
/*     */   }
/*     */   
/*     */   public void zoom(double percent) {}
/*     */   
/*     */   protected Font getCompassFont(int radius) {
/* 693 */     float fontSize = radius / 10.0F;
/* 694 */     if (fontSize < 8.0F)
/* 695 */       fontSize = 8.0F; 
/* 697 */     Font newFont = this.compassFont.deriveFont(fontSize);
/* 698 */     return newFont;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 709 */     if (obj == this)
/* 710 */       return true; 
/* 712 */     if (!(obj instanceof CompassPlot))
/* 713 */       return false; 
/* 715 */     if (!super.equals(obj))
/* 716 */       return false; 
/* 718 */     CompassPlot that = (CompassPlot)obj;
/* 719 */     if (this.labelType != that.labelType)
/* 720 */       return false; 
/* 722 */     if (!ObjectUtilities.equal(this.labelFont, that.labelFont))
/* 723 */       return false; 
/* 725 */     if (this.drawBorder != that.drawBorder)
/* 726 */       return false; 
/* 728 */     if (!PaintUtilities.equal(this.roseHighlightPaint, that.roseHighlightPaint))
/* 730 */       return false; 
/* 732 */     if (!PaintUtilities.equal(this.rosePaint, that.rosePaint))
/* 733 */       return false; 
/* 735 */     if (!PaintUtilities.equal(this.roseCenterPaint, that.roseCenterPaint))
/* 737 */       return false; 
/* 739 */     if (!ObjectUtilities.equal(this.compassFont, that.compassFont))
/* 740 */       return false; 
/* 742 */     if (!Arrays.equals((Object[])this.seriesNeedle, (Object[])that.seriesNeedle))
/* 743 */       return false; 
/* 745 */     if (getRevolutionDistance() != that.getRevolutionDistance())
/* 746 */       return false; 
/* 748 */     return true;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 762 */     CompassPlot clone = (CompassPlot)super.clone();
/* 770 */     if (this.circle1 != null)
/* 771 */       clone.circle1 = (Ellipse2D)this.circle1.clone(); 
/* 773 */     if (this.circle2 != null)
/* 774 */       clone.circle2 = (Ellipse2D)this.circle2.clone(); 
/* 776 */     if (this.a1 != null)
/* 777 */       clone.a1 = (Area)this.a1.clone(); 
/* 779 */     if (this.a2 != null)
/* 780 */       clone.a2 = (Area)this.a2.clone(); 
/* 782 */     if (this.rect1 != null)
/* 783 */       clone.rect1 = (Rectangle2D)this.rect1.clone(); 
/* 785 */     clone.datasets = (ValueDataset[])this.datasets.clone();
/* 786 */     clone.seriesNeedle = (MeterNeedle[])this.seriesNeedle.clone();
/* 789 */     for (int i = 0; i < this.datasets.length; i++) {
/* 790 */       if (clone.datasets[i] != null)
/* 791 */         clone.datasets[i].addChangeListener(clone); 
/*     */     } 
/* 794 */     return clone;
/*     */   }
/*     */   
/*     */   public void setRevolutionDistance(double size) {
/* 805 */     if (size > 0.0D)
/* 806 */       this.revolutionDistance = size; 
/*     */   }
/*     */   
/*     */   public double getRevolutionDistance() {
/* 816 */     return this.revolutionDistance;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\plot\CompassPlot.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */