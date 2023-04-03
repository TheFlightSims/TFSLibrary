/*     */ package org.jfree.chart.renderer.xy;
/*     */ 
/*     */ import java.awt.AlphaComposite;
/*     */ import java.awt.Color;
/*     */ import java.awt.Composite;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Shape;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.Line2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.entity.ChartEntity;
/*     */ import org.jfree.chart.entity.EntityCollection;
/*     */ import org.jfree.chart.entity.XYItemEntity;
/*     */ import org.jfree.chart.event.RendererChangeEvent;
/*     */ import org.jfree.chart.labels.HighLowItemLabelGenerator;
/*     */ import org.jfree.chart.labels.XYToolTipGenerator;
/*     */ import org.jfree.chart.plot.CrosshairState;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.chart.plot.PlotRenderingInfo;
/*     */ import org.jfree.chart.plot.XYPlot;
/*     */ import org.jfree.data.xy.IntervalXYDataset;
/*     */ import org.jfree.data.xy.OHLCDataset;
/*     */ import org.jfree.data.xy.XYDataset;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ public class CandlestickRenderer extends AbstractXYItemRenderer implements XYItemRenderer, Cloneable, PublicCloneable, Serializable {
/*     */   private static final long serialVersionUID = 50390395841817121L;
/*     */   
/*     */   public static final int WIDTHMETHOD_AVERAGE = 0;
/*     */   
/*     */   public static final int WIDTHMETHOD_SMALLEST = 1;
/*     */   
/*     */   public static final int WIDTHMETHOD_INTERVALDATA = 2;
/*     */   
/* 141 */   private int autoWidthMethod = 0;
/*     */   
/* 148 */   private double autoWidthFactor = 0.6428571428571429D;
/*     */   
/* 151 */   private double autoWidthGap = 0.0D;
/*     */   
/*     */   private double candleWidth;
/*     */   
/* 157 */   private double maxCandleWidthInMilliseconds = 7.2E7D;
/*     */   
/*     */   private double maxCandleWidth;
/*     */   
/*     */   private transient Paint upPaint;
/*     */   
/*     */   private transient Paint downPaint;
/*     */   
/*     */   private boolean drawVolume;
/*     */   
/*     */   private transient double maxVolume;
/*     */   
/*     */   public CandlestickRenderer() {
/* 184 */     this(-1.0D);
/*     */   }
/*     */   
/*     */   public CandlestickRenderer(double candleWidth) {
/* 196 */     this(candleWidth, true, (XYToolTipGenerator)new HighLowItemLabelGenerator());
/*     */   }
/*     */   
/*     */   public CandlestickRenderer(double candleWidth, boolean drawVolume, XYToolTipGenerator toolTipGenerator) {
/* 215 */     setToolTipGenerator(toolTipGenerator);
/* 216 */     this.candleWidth = candleWidth;
/* 217 */     this.drawVolume = drawVolume;
/* 218 */     this.upPaint = Color.green;
/* 219 */     this.downPaint = Color.red;
/*     */   }
/*     */   
/*     */   public double getCandleWidth() {
/* 231 */     return this.candleWidth;
/*     */   }
/*     */   
/*     */   public void setCandleWidth(double width) {
/* 247 */     if (width != this.candleWidth) {
/* 248 */       this.candleWidth = width;
/* 249 */       notifyListeners(new RendererChangeEvent(this));
/*     */     } 
/*     */   }
/*     */   
/*     */   public double getMaxCandleWidthInMilliseconds() {
/* 259 */     return this.maxCandleWidthInMilliseconds;
/*     */   }
/*     */   
/*     */   public void setMaxCandleWidthInMilliseconds(double millis) {
/* 272 */     this.maxCandleWidthInMilliseconds = millis;
/* 273 */     notifyListeners(new RendererChangeEvent(this));
/*     */   }
/*     */   
/*     */   public int getAutoWidthMethod() {
/* 282 */     return this.autoWidthMethod;
/*     */   }
/*     */   
/*     */   public void setAutoWidthMethod(int autoWidthMethod) {
/* 310 */     if (this.autoWidthMethod != autoWidthMethod) {
/* 311 */       this.autoWidthMethod = autoWidthMethod;
/* 312 */       notifyListeners(new RendererChangeEvent(this));
/*     */     } 
/*     */   }
/*     */   
/*     */   public double getAutoWidthFactor() {
/* 324 */     return this.autoWidthFactor;
/*     */   }
/*     */   
/*     */   public void setAutoWidthFactor(double autoWidthFactor) {
/* 338 */     if (this.autoWidthFactor != autoWidthFactor) {
/* 339 */       this.autoWidthFactor = autoWidthFactor;
/* 340 */       notifyListeners(new RendererChangeEvent(this));
/*     */     } 
/*     */   }
/*     */   
/*     */   public double getAutoWidthGap() {
/* 351 */     return this.autoWidthGap;
/*     */   }
/*     */   
/*     */   public void setAutoWidthGap(double autoWidthGap) {
/* 365 */     if (this.autoWidthGap != autoWidthGap) {
/* 366 */       this.autoWidthGap = autoWidthGap;
/* 367 */       notifyListeners(new RendererChangeEvent(this));
/*     */     } 
/*     */   }
/*     */   
/*     */   public Paint getUpPaint() {
/* 378 */     return this.upPaint;
/*     */   }
/*     */   
/*     */   public void setUpPaint(Paint paint) {
/* 391 */     this.upPaint = paint;
/* 392 */     notifyListeners(new RendererChangeEvent(this));
/*     */   }
/*     */   
/*     */   public Paint getDownPaint() {
/* 402 */     return this.downPaint;
/*     */   }
/*     */   
/*     */   public void setDownPaint(Paint paint) {
/* 415 */     this.downPaint = paint;
/* 416 */     notifyListeners(new RendererChangeEvent(this));
/*     */   }
/*     */   
/*     */   public boolean drawVolume() {
/* 426 */     return this.drawVolume;
/*     */   }
/*     */   
/*     */   public void setDrawVolume(boolean flag) {
/* 436 */     if (this.drawVolume != flag) {
/* 437 */       this.drawVolume = flag;
/* 438 */       notifyListeners(new RendererChangeEvent(this));
/*     */     } 
/*     */   }
/*     */   
/*     */   public XYItemRendererState initialise(Graphics2D g2, Rectangle2D dataArea, XYPlot plot, XYDataset dataset, PlotRenderingInfo info) {
/* 465 */     ValueAxis axis = plot.getDomainAxis();
/* 466 */     double x1 = axis.getLowerBound();
/* 467 */     double x2 = x1 + this.maxCandleWidthInMilliseconds;
/* 468 */     RectangleEdge edge = plot.getDomainAxisEdge();
/* 469 */     double xx1 = axis.valueToJava2D(x1, dataArea, edge);
/* 470 */     double xx2 = axis.valueToJava2D(x2, dataArea, edge);
/* 471 */     this.maxCandleWidth = Math.abs(xx2 - xx1);
/* 476 */     if (this.drawVolume) {
/* 477 */       OHLCDataset highLowDataset = (OHLCDataset)dataset;
/* 478 */       this.maxVolume = 0.0D;
/* 479 */       for (int series = 0; series < highLowDataset.getSeriesCount(); 
/* 480 */         series++) {
/* 481 */         for (int item = 0; item < highLowDataset.getItemCount(series); 
/* 482 */           item++) {
/* 483 */           double volume = highLowDataset.getVolumeValue(series, item);
/* 484 */           if (volume > this.maxVolume)
/* 485 */             this.maxVolume = volume; 
/*     */         } 
/*     */       } 
/*     */     } 
/* 492 */     return new XYItemRendererState(info);
/*     */   }
/*     */   
/*     */   public void drawItem(Graphics2D g2, XYItemRendererState state, Rectangle2D dataArea, PlotRenderingInfo info, XYPlot plot, ValueAxis domainAxis, ValueAxis rangeAxis, XYDataset dataset, int series, int item, CrosshairState crosshairState, int pass) {
/*     */     boolean horiz;
/*     */     double volumeWidth, stickWidth;
/* 527 */     PlotOrientation orientation = plot.getOrientation();
/* 528 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 529 */       horiz = true;
/* 531 */     } else if (orientation == PlotOrientation.VERTICAL) {
/* 532 */       horiz = false;
/*     */     } else {
/*     */       return;
/*     */     } 
/* 539 */     EntityCollection entities = null;
/* 540 */     if (info != null)
/* 541 */       entities = info.getOwner().getEntityCollection(); 
/* 544 */     OHLCDataset highLowData = (OHLCDataset)dataset;
/* 546 */     Number x = highLowData.getX(series, item);
/* 547 */     Number yHigh = highLowData.getHigh(series, item);
/* 548 */     Number yLow = highLowData.getLow(series, item);
/* 549 */     Number yOpen = highLowData.getOpen(series, item);
/* 550 */     Number yClose = highLowData.getClose(series, item);
/* 552 */     RectangleEdge domainEdge = plot.getDomainAxisEdge();
/* 553 */     double xx = domainAxis.valueToJava2D(x.doubleValue(), dataArea, domainEdge);
/* 556 */     RectangleEdge edge = plot.getRangeAxisEdge();
/* 557 */     double yyHigh = rangeAxis.valueToJava2D(yHigh.doubleValue(), dataArea, edge);
/* 559 */     double yyLow = rangeAxis.valueToJava2D(yLow.doubleValue(), dataArea, edge);
/* 561 */     double yyOpen = rangeAxis.valueToJava2D(yOpen.doubleValue(), dataArea, edge);
/* 563 */     double yyClose = rangeAxis.valueToJava2D(yClose.doubleValue(), dataArea, edge);
/* 568 */     if (this.candleWidth > 0.0D) {
/* 571 */       volumeWidth = this.candleWidth;
/* 572 */       stickWidth = this.candleWidth;
/*     */     } else {
/*     */       int itemCount;
/*     */       double lastPos;
/*     */       int i;
/*     */       IntervalXYDataset intervalXYData;
/* 575 */       double startPos, endPos, xxWidth = 0.0D;
/* 577 */       switch (this.autoWidthMethod) {
/*     */         case 0:
/* 580 */           itemCount = highLowData.getItemCount(series);
/* 581 */           if (horiz) {
/* 582 */             xxWidth = dataArea.getHeight() / itemCount;
/*     */             break;
/*     */           } 
/* 585 */           xxWidth = dataArea.getWidth() / itemCount;
/*     */           break;
/*     */         case 1:
/* 591 */           itemCount = highLowData.getItemCount(series);
/* 592 */           lastPos = -1.0D;
/* 593 */           xxWidth = dataArea.getWidth();
/* 594 */           for (i = 0; i < itemCount; i++) {
/* 595 */             double pos = domainAxis.valueToJava2D(highLowData.getXValue(series, i), dataArea, domainEdge);
/* 599 */             if (lastPos != -1.0D)
/* 600 */               xxWidth = Math.min(xxWidth, Math.abs(pos - lastPos)); 
/* 604 */             lastPos = pos;
/*     */           } 
/*     */           break;
/*     */         case 2:
/* 609 */           intervalXYData = (IntervalXYDataset)dataset;
/* 611 */           startPos = domainAxis.valueToJava2D(intervalXYData.getStartXValue(series, item), dataArea, plot.getDomainAxisEdge());
/* 615 */           endPos = domainAxis.valueToJava2D(intervalXYData.getEndXValue(series, item), dataArea, plot.getDomainAxisEdge());
/* 619 */           xxWidth = Math.abs(endPos - startPos);
/*     */           break;
/*     */       } 
/* 623 */       xxWidth -= 2.0D * this.autoWidthGap;
/* 624 */       xxWidth *= this.autoWidthFactor;
/* 625 */       xxWidth = Math.min(xxWidth, this.maxCandleWidth);
/* 626 */       volumeWidth = Math.max(Math.min(1.0D, this.maxCandleWidth), xxWidth);
/* 627 */       stickWidth = Math.max(Math.min(3.0D, this.maxCandleWidth), xxWidth);
/*     */     } 
/* 630 */     Paint p = getItemPaint(series, item);
/* 631 */     Stroke s = getItemStroke(series, item);
/* 633 */     g2.setStroke(s);
/* 635 */     if (this.drawVolume) {
/*     */       double min, max;
/* 636 */       int volume = (int)highLowData.getVolumeValue(series, item);
/* 637 */       double volumeHeight = volume / this.maxVolume;
/* 640 */       if (horiz) {
/* 641 */         min = dataArea.getMinX();
/* 642 */         max = dataArea.getMaxX();
/*     */       } else {
/* 645 */         min = dataArea.getMinY();
/* 646 */         max = dataArea.getMaxY();
/*     */       } 
/* 649 */       double zzVolume = volumeHeight * (max - min);
/* 651 */       g2.setPaint(Color.gray);
/* 652 */       Composite originalComposite = g2.getComposite();
/* 653 */       g2.setComposite(AlphaComposite.getInstance(3, 0.3F));
/* 657 */       if (horiz) {
/* 658 */         g2.fill(new Rectangle2D.Double(min, xx - volumeWidth / 2.0D, zzVolume, volumeWidth));
/*     */       } else {
/* 663 */         g2.fill(new Rectangle2D.Double(xx - volumeWidth / 2.0D, max - zzVolume, volumeWidth, zzVolume));
/*     */       } 
/* 671 */       g2.setComposite(originalComposite);
/*     */     } 
/* 674 */     g2.setPaint(p);
/* 676 */     double yyMaxOpenClose = Math.max(yyOpen, yyClose);
/* 677 */     double yyMinOpenClose = Math.min(yyOpen, yyClose);
/* 678 */     double maxOpenClose = Math.max(yOpen.doubleValue(), yClose.doubleValue());
/* 680 */     double minOpenClose = Math.min(yOpen.doubleValue(), yClose.doubleValue());
/* 684 */     if (yHigh.doubleValue() > maxOpenClose)
/* 685 */       if (horiz) {
/* 686 */         g2.draw(new Line2D.Double(yyHigh, xx, yyMaxOpenClose, xx));
/*     */       } else {
/* 689 */         g2.draw(new Line2D.Double(xx, yyHigh, xx, yyMaxOpenClose));
/*     */       }  
/* 694 */     if (yLow.doubleValue() < minOpenClose)
/* 695 */       if (horiz) {
/* 696 */         g2.draw(new Line2D.Double(yyLow, xx, yyMinOpenClose, xx));
/*     */       } else {
/* 699 */         g2.draw(new Line2D.Double(xx, yyLow, xx, yyMinOpenClose));
/*     */       }  
/* 704 */     Shape body = null;
/* 705 */     if (horiz) {
/* 706 */       body = new Rectangle2D.Double(yyMinOpenClose, xx - stickWidth / 2.0D, yyMaxOpenClose - yyMinOpenClose, stickWidth);
/*     */     } else {
/* 712 */       body = new Rectangle2D.Double(xx - stickWidth / 2.0D, yyMinOpenClose, stickWidth, yyMaxOpenClose - yyMinOpenClose);
/*     */     } 
/* 717 */     if (yClose.doubleValue() > yOpen.doubleValue()) {
/* 718 */       if (this.upPaint != null) {
/* 719 */         g2.setPaint(this.upPaint);
/* 720 */         g2.fill(body);
/*     */       } 
/*     */     } else {
/* 724 */       if (this.downPaint != null)
/* 725 */         g2.setPaint(this.downPaint); 
/* 727 */       g2.fill(body);
/*     */     } 
/* 729 */     g2.setPaint(p);
/* 730 */     g2.draw(body);
/* 733 */     if (entities != null) {
/* 734 */       String tip = null;
/* 735 */       XYToolTipGenerator generator = getToolTipGenerator(series, item);
/* 736 */       if (generator != null)
/* 737 */         tip = generator.generateToolTip(dataset, series, item); 
/* 739 */       String url = null;
/* 740 */       if (getURLGenerator() != null)
/* 741 */         url = getURLGenerator().generateURL(dataset, series, item); 
/* 743 */       XYItemEntity entity = new XYItemEntity(body, dataset, series, item, tip, url);
/* 745 */       entities.add((ChartEntity)entity);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 759 */     if (obj == null)
/* 760 */       return false; 
/* 763 */     if (obj == this)
/* 764 */       return true; 
/* 767 */     if (obj instanceof CandlestickRenderer) {
/* 768 */       CandlestickRenderer renderer = (CandlestickRenderer)obj;
/* 769 */       boolean result = super.equals(obj);
/* 770 */       result = (result && this.candleWidth == renderer.getCandleWidth());
/* 771 */       result = (result && this.upPaint.equals(renderer.getUpPaint()));
/* 772 */       result = (result && this.downPaint.equals(renderer.getDownPaint()));
/* 773 */       result = (result && this.drawVolume == renderer.drawVolume);
/* 774 */       return result;
/*     */     } 
/* 777 */     return false;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 789 */     return super.clone();
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream stream) throws IOException {
/* 800 */     stream.defaultWriteObject();
/* 801 */     SerialUtilities.writePaint(this.upPaint, stream);
/* 802 */     SerialUtilities.writePaint(this.downPaint, stream);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
/* 815 */     stream.defaultReadObject();
/* 816 */     this.upPaint = SerialUtilities.readPaint(stream);
/* 817 */     this.downPaint = SerialUtilities.readPaint(stream);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\renderer\xy\CandlestickRenderer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */