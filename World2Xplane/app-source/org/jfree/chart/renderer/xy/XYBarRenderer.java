/*     */ package org.jfree.chart.renderer.xy;
/*     */ 
/*     */ import java.awt.GradientPaint;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Shape;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.LegendItem;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.entity.ChartEntity;
/*     */ import org.jfree.chart.entity.EntityCollection;
/*     */ import org.jfree.chart.entity.XYItemEntity;
/*     */ import org.jfree.chart.event.RendererChangeEvent;
/*     */ import org.jfree.chart.labels.XYSeriesLabelGenerator;
/*     */ import org.jfree.chart.labels.XYToolTipGenerator;
/*     */ import org.jfree.chart.plot.CrosshairState;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.chart.plot.PlotRenderingInfo;
/*     */ import org.jfree.chart.plot.XYPlot;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.data.general.DatasetUtilities;
/*     */ import org.jfree.data.xy.IntervalXYDataset;
/*     */ import org.jfree.data.xy.XYDataset;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.ui.GradientPaintTransformer;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.ui.StandardGradientPaintTransformer;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ import org.jfree.util.ShapeUtilities;
/*     */ 
/*     */ public class XYBarRenderer extends AbstractXYItemRenderer implements XYItemRenderer, Cloneable, PublicCloneable, Serializable {
/*     */   private static final long serialVersionUID = 770559577251370036L;
/*     */   
/*     */   private double base;
/*     */   
/*     */   private boolean useYInterval;
/*     */   
/*     */   private double margin;
/*     */   
/*     */   private boolean drawBarOutline;
/*     */   
/*     */   private GradientPaintTransformer gradientPaintTransformer;
/*     */   
/*     */   private transient Shape legendBar;
/*     */   
/*     */   protected class XYBarRendererState extends XYItemRendererState {
/*     */     private double g2Base;
/*     */     
/*     */     private final XYBarRenderer this$0;
/*     */     
/*     */     public XYBarRendererState(XYBarRenderer this$0, PlotRenderingInfo info) {
/* 142 */       super(info);
/*     */       this.this$0 = this$0;
/*     */     }
/*     */     
/*     */     public double getG2Base() {
/* 151 */       return this.g2Base;
/*     */     }
/*     */     
/*     */     public void setG2Base(double value) {
/* 160 */       this.g2Base = value;
/*     */     }
/*     */   }
/*     */   
/*     */   public XYBarRenderer() {
/* 195 */     this(0.0D);
/*     */   }
/*     */   
/*     */   public XYBarRenderer(double margin) {
/* 205 */     this.margin = margin;
/* 206 */     this.base = 0.0D;
/* 207 */     this.useYInterval = false;
/* 208 */     this.gradientPaintTransformer = (GradientPaintTransformer)new StandardGradientPaintTransformer();
/* 209 */     this.drawBarOutline = true;
/* 210 */     this.legendBar = new Rectangle2D.Double(-3.0D, -5.0D, 6.0D, 10.0D);
/*     */   }
/*     */   
/*     */   public double getBase() {
/* 219 */     return this.base;
/*     */   }
/*     */   
/*     */   public void setBase(double base) {
/* 230 */     this.base = base;
/* 231 */     notifyListeners(new RendererChangeEvent(this));
/*     */   }
/*     */   
/*     */   public boolean getUseYInterval() {
/* 241 */     return this.useYInterval;
/*     */   }
/*     */   
/*     */   public void setUseYInterval(boolean use) {
/* 252 */     this.useYInterval = use;
/* 253 */     notifyListeners(new RendererChangeEvent(this));
/*     */   }
/*     */   
/*     */   public double getMargin() {
/* 263 */     return this.margin;
/*     */   }
/*     */   
/*     */   public void setMargin(double margin) {
/* 273 */     this.margin = margin;
/* 274 */     notifyListeners(new RendererChangeEvent(this));
/*     */   }
/*     */   
/*     */   public boolean isDrawBarOutline() {
/* 283 */     return this.drawBarOutline;
/*     */   }
/*     */   
/*     */   public void setDrawBarOutline(boolean draw) {
/* 293 */     this.drawBarOutline = draw;
/* 294 */     notifyListeners(new RendererChangeEvent(this));
/*     */   }
/*     */   
/*     */   public GradientPaintTransformer getGradientPaintTransformer() {
/* 304 */     return this.gradientPaintTransformer;
/*     */   }
/*     */   
/*     */   public void setGradientPaintTransformer(GradientPaintTransformer transformer) {
/* 315 */     this.gradientPaintTransformer = transformer;
/* 316 */     notifyListeners(new RendererChangeEvent(this));
/*     */   }
/*     */   
/*     */   public Shape getLegendBar() {
/* 326 */     return this.legendBar;
/*     */   }
/*     */   
/*     */   public void setLegendBar(Shape bar) {
/* 335 */     if (bar == null)
/* 336 */       throw new IllegalArgumentException("Null 'bar' argument."); 
/* 338 */     this.legendBar = bar;
/* 339 */     notifyListeners(new RendererChangeEvent(this));
/*     */   }
/*     */   
/*     */   public XYItemRendererState initialise(Graphics2D g2, Rectangle2D dataArea, XYPlot plot, XYDataset dataset, PlotRenderingInfo info) {
/* 363 */     XYBarRendererState state = new XYBarRendererState(this, info);
/* 364 */     ValueAxis rangeAxis = plot.getRangeAxisForDataset(plot.indexOf(dataset));
/* 366 */     state.setG2Base(rangeAxis.valueToJava2D(this.base, dataArea, plot.getRangeAxisEdge()));
/* 371 */     return state;
/*     */   }
/*     */   
/*     */   public LegendItem getLegendItem(int datasetIndex, int series) {
/* 385 */     LegendItem result = null;
/* 386 */     XYPlot xyplot = getPlot();
/* 387 */     if (xyplot != null) {
/* 388 */       XYDataset dataset = xyplot.getDataset(datasetIndex);
/* 389 */       if (dataset != null) {
/* 390 */         XYSeriesLabelGenerator lg = getLegendItemLabelGenerator();
/* 391 */         String label = lg.generateLabel(dataset, series);
/* 392 */         String description = label;
/* 393 */         String toolTipText = null;
/* 394 */         if (getLegendItemToolTipGenerator() != null)
/* 395 */           toolTipText = getLegendItemToolTipGenerator().generateLabel(dataset, series); 
/* 399 */         String urlText = null;
/* 400 */         if (getLegendItemURLGenerator() != null)
/* 401 */           urlText = getLegendItemURLGenerator().generateLabel(dataset, series); 
/* 405 */         Shape shape = this.legendBar;
/* 406 */         Paint paint = getSeriesPaint(series);
/* 407 */         Paint outlinePaint = getSeriesOutlinePaint(series);
/* 408 */         Stroke outlineStroke = getSeriesOutlineStroke(series);
/* 409 */         result = new LegendItem(label, description, toolTipText, urlText, shape, paint, outlineStroke, outlinePaint);
/*     */       } 
/*     */     } 
/* 413 */     return result;
/*     */   }
/*     */   
/*     */   public void drawItem(Graphics2D g2, XYItemRendererState state, Rectangle2D dataArea, PlotRenderingInfo info, XYPlot plot, ValueAxis domainAxis, ValueAxis rangeAxis, XYDataset dataset, int series, int item, CrosshairState crosshairState, int pass) {
/*     */     double value0, value1;
/* 447 */     if (!getItemVisible(series, item))
/*     */       return; 
/* 450 */     IntervalXYDataset intervalDataset = (IntervalXYDataset)dataset;
/* 454 */     if (this.useYInterval) {
/* 455 */       value0 = intervalDataset.getStartYValue(series, item);
/* 456 */       value1 = intervalDataset.getEndYValue(series, item);
/*     */     } else {
/* 459 */       value0 = this.base;
/* 460 */       value1 = intervalDataset.getYValue(series, item);
/*     */     } 
/* 462 */     if (Double.isNaN(value0) || Double.isNaN(value1))
/*     */       return; 
/* 466 */     double translatedValue0 = rangeAxis.valueToJava2D(value0, dataArea, plot.getRangeAxisEdge());
/* 469 */     double translatedValue1 = rangeAxis.valueToJava2D(value1, dataArea, plot.getRangeAxisEdge());
/* 473 */     RectangleEdge location = plot.getDomainAxisEdge();
/* 474 */     Number startXNumber = intervalDataset.getStartX(series, item);
/* 475 */     if (startXNumber == null)
/*     */       return; 
/* 478 */     double translatedStartX = domainAxis.valueToJava2D(startXNumber.doubleValue(), dataArea, location);
/* 482 */     Number endXNumber = intervalDataset.getEndX(series, item);
/* 483 */     if (endXNumber == null)
/*     */       return; 
/* 486 */     double translatedEndX = domainAxis.valueToJava2D(endXNumber.doubleValue(), dataArea, location);
/* 490 */     double translatedWidth = Math.max(1.0D, Math.abs(translatedEndX - translatedStartX));
/* 493 */     double translatedHeight = Math.abs(translatedValue1 - translatedValue0);
/* 495 */     if (getMargin() > 0.0D) {
/* 496 */       double cut = translatedWidth * getMargin();
/* 497 */       translatedWidth -= cut;
/* 498 */       translatedStartX += cut / 2.0D;
/*     */     } 
/* 501 */     Rectangle2D bar = null;
/* 502 */     PlotOrientation orientation = plot.getOrientation();
/* 503 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 504 */       bar = new Rectangle2D.Double(Math.min(translatedValue0, translatedValue1), Math.min(translatedStartX, translatedEndX), translatedHeight, translatedWidth);
/* 509 */     } else if (orientation == PlotOrientation.VERTICAL) {
/* 510 */       bar = new Rectangle2D.Double(Math.min(translatedStartX, translatedEndX), Math.min(translatedValue0, translatedValue1), translatedWidth, translatedHeight);
/*     */     } 
/* 516 */     Paint itemPaint = getItemPaint(series, item);
/* 517 */     if (getGradientPaintTransformer() != null && itemPaint instanceof GradientPaint) {
/* 519 */       GradientPaint gp = (GradientPaint)itemPaint;
/* 520 */       itemPaint = getGradientPaintTransformer().transform(gp, bar);
/*     */     } 
/* 522 */     g2.setPaint(itemPaint);
/* 523 */     g2.fill(bar);
/* 524 */     if (isDrawBarOutline() && Math.abs(translatedEndX - translatedStartX) > 3.0D) {
/* 526 */       Stroke stroke = getItemOutlineStroke(series, item);
/* 527 */       Paint paint = getItemOutlinePaint(series, item);
/* 528 */       if (stroke != null && paint != null) {
/* 529 */         g2.setStroke(stroke);
/* 530 */         g2.setPaint(paint);
/* 531 */         g2.draw(bar);
/*     */       } 
/*     */     } 
/* 536 */     if (isItemLabelVisible(series, item))
/* 537 */       drawItemLabel(g2, orientation, dataset, series, item, bar.getCenterX(), bar.getY(), (value1 < 0.0D)); 
/* 544 */     if (info != null) {
/* 545 */       EntityCollection entities = info.getOwner().getEntityCollection();
/* 546 */       if (entities != null) {
/* 547 */         String tip = null;
/* 548 */         XYToolTipGenerator generator = getToolTipGenerator(series, item);
/* 550 */         if (generator != null)
/* 551 */           tip = generator.generateToolTip(dataset, series, item); 
/* 553 */         String url = null;
/* 554 */         if (getURLGenerator() != null)
/* 555 */           url = getURLGenerator().generateURL(dataset, series, item); 
/* 557 */         XYItemEntity entity = new XYItemEntity(bar, dataset, series, item, tip, url);
/* 560 */         entities.add((ChartEntity)entity);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Range findDomainBounds(XYDataset dataset) {
/* 577 */     if (dataset != null)
/* 578 */       return DatasetUtilities.findDomainBounds(dataset, true); 
/* 581 */     return null;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 593 */     XYBarRenderer result = (XYBarRenderer)super.clone();
/* 594 */     if (this.gradientPaintTransformer != null)
/* 595 */       result.gradientPaintTransformer = (GradientPaintTransformer)ObjectUtilities.clone(this.gradientPaintTransformer); 
/* 598 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 609 */     if (obj == this)
/* 610 */       return true; 
/* 612 */     if (!(obj instanceof XYBarRenderer))
/* 613 */       return false; 
/* 615 */     if (!super.equals(obj))
/* 616 */       return false; 
/* 618 */     XYBarRenderer that = (XYBarRenderer)obj;
/* 619 */     if (this.base != that.base)
/* 620 */       return false; 
/* 622 */     if (this.drawBarOutline != that.drawBarOutline)
/* 623 */       return false; 
/* 625 */     if (this.margin != that.margin)
/* 626 */       return false; 
/* 628 */     if (this.useYInterval != that.useYInterval)
/* 629 */       return false; 
/* 631 */     if (!ObjectUtilities.equal(this.gradientPaintTransformer, that.gradientPaintTransformer))
/* 634 */       return false; 
/* 636 */     if (!ShapeUtilities.equal(this.legendBar, that.legendBar))
/* 637 */       return false; 
/* 639 */     return true;
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
/* 652 */     stream.defaultReadObject();
/* 653 */     this.legendBar = SerialUtilities.readShape(stream);
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream stream) throws IOException {
/* 664 */     stream.defaultWriteObject();
/* 665 */     SerialUtilities.writeShape(this.legendBar, stream);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\renderer\xy\XYBarRenderer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */