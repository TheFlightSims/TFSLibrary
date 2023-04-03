/*     */ package org.jfree.chart.renderer.category;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.GradientPaint;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.axis.CategoryAxis;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.entity.EntityCollection;
/*     */ import org.jfree.chart.event.RendererChangeEvent;
/*     */ import org.jfree.chart.labels.CategoryItemLabelGenerator;
/*     */ import org.jfree.chart.plot.CategoryPlot;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.data.category.CategoryDataset;
/*     */ import org.jfree.data.general.DatasetUtilities;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.ui.GradientPaintTransformType;
/*     */ import org.jfree.ui.GradientPaintTransformer;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.ui.StandardGradientPaintTransformer;
/*     */ import org.jfree.util.PaintUtilities;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ public class WaterfallBarRenderer extends BarRenderer implements Cloneable, PublicCloneable, Serializable {
/*     */   private static final long serialVersionUID = -2482910643727230911L;
/*     */   
/*     */   private transient Paint firstBarPaint;
/*     */   
/*     */   private transient Paint lastBarPaint;
/*     */   
/*     */   private transient Paint positiveBarPaint;
/*     */   
/*     */   private transient Paint negativeBarPaint;
/*     */   
/*     */   public WaterfallBarRenderer() {
/* 115 */     this(new GradientPaint(0.0F, 0.0F, new Color(34, 34, 255), 0.0F, 0.0F, new Color(102, 102, 255)), new GradientPaint(0.0F, 0.0F, new Color(34, 255, 34), 0.0F, 0.0F, new Color(102, 255, 102)), new GradientPaint(0.0F, 0.0F, new Color(255, 34, 34), 0.0F, 0.0F, new Color(255, 102, 102)), new GradientPaint(0.0F, 0.0F, new Color(255, 255, 34), 0.0F, 0.0F, new Color(255, 255, 102)));
/*     */   }
/*     */   
/*     */   public WaterfallBarRenderer(Paint firstBarPaint, Paint positiveBarPaint, Paint negativeBarPaint, Paint lastBarPaint) {
/* 152 */     if (firstBarPaint == null)
/* 153 */       throw new IllegalArgumentException("Null 'firstBarPaint' argument"); 
/* 155 */     if (positiveBarPaint == null)
/* 156 */       throw new IllegalArgumentException("Null 'positiveBarPaint' argument"); 
/* 160 */     if (negativeBarPaint == null)
/* 161 */       throw new IllegalArgumentException("Null 'negativeBarPaint' argument"); 
/* 165 */     if (lastBarPaint == null)
/* 166 */       throw new IllegalArgumentException("Null 'lastBarPaint' argument"); 
/* 168 */     this.firstBarPaint = firstBarPaint;
/* 169 */     this.lastBarPaint = lastBarPaint;
/* 170 */     this.positiveBarPaint = positiveBarPaint;
/* 171 */     this.negativeBarPaint = negativeBarPaint;
/* 172 */     setGradientPaintTransformer((GradientPaintTransformer)new StandardGradientPaintTransformer(GradientPaintTransformType.CENTER_VERTICAL));
/* 177 */     setMinimumBarLength(1.0D);
/*     */   }
/*     */   
/*     */   public Range findRangeBounds(CategoryDataset dataset) {
/* 189 */     return DatasetUtilities.findCumulativeRangeBounds(dataset);
/*     */   }
/*     */   
/*     */   public Paint getFirstBarPaint() {
/* 198 */     return this.firstBarPaint;
/*     */   }
/*     */   
/*     */   public void setFirstBarPaint(Paint paint) {
/* 208 */     if (paint == null)
/* 209 */       throw new IllegalArgumentException("Null 'paint' argument"); 
/* 211 */     this.firstBarPaint = paint;
/* 212 */     notifyListeners(new RendererChangeEvent(this));
/*     */   }
/*     */   
/*     */   public Paint getLastBarPaint() {
/* 221 */     return this.lastBarPaint;
/*     */   }
/*     */   
/*     */   public void setLastBarPaint(Paint paint) {
/* 230 */     if (paint == null)
/* 231 */       throw new IllegalArgumentException("Null 'paint' argument"); 
/* 233 */     this.lastBarPaint = paint;
/* 234 */     notifyListeners(new RendererChangeEvent(this));
/*     */   }
/*     */   
/*     */   public Paint getPositiveBarPaint() {
/* 243 */     return this.positiveBarPaint;
/*     */   }
/*     */   
/*     */   public void setPositiveBarPaint(Paint paint) {
/* 252 */     if (paint == null)
/* 253 */       throw new IllegalArgumentException("Null 'paint' argument"); 
/* 255 */     this.positiveBarPaint = paint;
/* 256 */     notifyListeners(new RendererChangeEvent(this));
/*     */   }
/*     */   
/*     */   public Paint getNegativeBarPaint() {
/* 265 */     return this.negativeBarPaint;
/*     */   }
/*     */   
/*     */   public void setNegativeBarPaint(Paint paint) {
/* 274 */     if (paint == null)
/* 275 */       throw new IllegalArgumentException("Null 'paint' argument"); 
/* 277 */     this.negativeBarPaint = paint;
/* 278 */     notifyListeners(new RendererChangeEvent(this));
/*     */   }
/*     */   
/*     */   public void drawItem(Graphics2D g2, CategoryItemRendererState state, Rectangle2D dataArea, CategoryPlot plot, CategoryAxis domainAxis, ValueAxis rangeAxis, CategoryDataset dataset, int row, int column, int pass) {
/* 306 */     double previous = state.getSeriesRunningTotal();
/* 307 */     if (column == dataset.getColumnCount() - 1)
/* 308 */       previous = 0.0D; 
/* 310 */     double current = 0.0D;
/* 311 */     Number n = dataset.getValue(row, column);
/* 312 */     if (n != null)
/* 313 */       current = previous + n.doubleValue(); 
/* 315 */     state.setSeriesRunningTotal(current);
/* 317 */     int seriesCount = getRowCount();
/* 318 */     int categoryCount = getColumnCount();
/* 319 */     PlotOrientation orientation = plot.getOrientation();
/* 321 */     double rectX = 0.0D;
/* 322 */     double rectY = 0.0D;
/* 324 */     RectangleEdge domainAxisLocation = plot.getDomainAxisEdge();
/* 325 */     RectangleEdge rangeAxisLocation = plot.getRangeAxisEdge();
/* 328 */     double j2dy0 = rangeAxis.valueToJava2D(previous, dataArea, rangeAxisLocation);
/* 333 */     double j2dy1 = rangeAxis.valueToJava2D(current, dataArea, rangeAxisLocation);
/* 337 */     double valDiff = current - previous;
/* 338 */     if (j2dy1 < j2dy0) {
/* 339 */       double temp = j2dy1;
/* 340 */       j2dy1 = j2dy0;
/* 341 */       j2dy0 = temp;
/*     */     } 
/* 345 */     double rectWidth = state.getBarWidth();
/* 348 */     double rectHeight = Math.max(getMinimumBarLength(), Math.abs(j2dy1 - j2dy0));
/* 352 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 354 */       rectY = domainAxis.getCategoryStart(column, getColumnCount(), dataArea, domainAxisLocation);
/* 357 */       if (seriesCount > 1) {
/* 358 */         double seriesGap = dataArea.getHeight() * getItemMargin() / (categoryCount * (seriesCount - 1));
/* 360 */         rectY += row * (state.getBarWidth() + seriesGap);
/*     */       } else {
/* 363 */         rectY += row * state.getBarWidth();
/*     */       } 
/* 366 */       rectX = j2dy0;
/* 367 */       rectHeight = state.getBarWidth();
/* 368 */       rectWidth = Math.max(getMinimumBarLength(), Math.abs(j2dy1 - j2dy0));
/* 373 */     } else if (orientation == PlotOrientation.VERTICAL) {
/* 375 */       rectX = domainAxis.getCategoryStart(column, getColumnCount(), dataArea, domainAxisLocation);
/* 379 */       if (seriesCount > 1) {
/* 380 */         double seriesGap = dataArea.getWidth() * getItemMargin() / (categoryCount * (seriesCount - 1));
/* 382 */         rectX += row * (state.getBarWidth() + seriesGap);
/*     */       } else {
/* 385 */         rectX += row * state.getBarWidth();
/*     */       } 
/* 388 */       rectY = j2dy0;
/*     */     } 
/* 390 */     Rectangle2D bar = new Rectangle2D.Double(rectX, rectY, rectWidth, rectHeight);
/* 393 */     Paint seriesPaint = getFirstBarPaint();
/* 394 */     if (column == 0) {
/* 395 */       seriesPaint = getFirstBarPaint();
/* 397 */     } else if (column == categoryCount - 1) {
/* 398 */       seriesPaint = getLastBarPaint();
/* 401 */     } else if (valDiff < 0.0D) {
/* 402 */       seriesPaint = getNegativeBarPaint();
/* 404 */     } else if (valDiff > 0.0D) {
/* 405 */       seriesPaint = getPositiveBarPaint();
/*     */     } else {
/* 408 */       seriesPaint = getLastBarPaint();
/*     */     } 
/* 411 */     if (getGradientPaintTransformer() != null && seriesPaint instanceof GradientPaint) {
/* 413 */       GradientPaint gp = (GradientPaint)seriesPaint;
/* 414 */       seriesPaint = getGradientPaintTransformer().transform(gp, bar);
/*     */     } 
/* 416 */     g2.setPaint(seriesPaint);
/* 417 */     g2.fill(bar);
/* 420 */     if (isDrawBarOutline() && state.getBarWidth() > 3.0D) {
/* 422 */       Stroke stroke = getItemOutlineStroke(row, column);
/* 423 */       Paint paint = getItemOutlinePaint(row, column);
/* 424 */       if (stroke != null && paint != null) {
/* 425 */         g2.setStroke(stroke);
/* 426 */         g2.setPaint(paint);
/* 427 */         g2.draw(bar);
/*     */       } 
/*     */     } 
/* 431 */     CategoryItemLabelGenerator generator = getItemLabelGenerator(row, column);
/* 433 */     if (generator != null && isItemLabelVisible(row, column))
/* 434 */       drawItemLabel(g2, dataset, row, column, plot, generator, bar, (valDiff < 0.0D)); 
/* 440 */     EntityCollection entities = state.getEntityCollection();
/* 441 */     if (entities != null)
/* 442 */       addItemEntity(entities, dataset, row, column, bar); 
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 456 */     if (obj == this)
/* 457 */       return true; 
/* 459 */     if (!super.equals(obj))
/* 460 */       return false; 
/* 462 */     if (!(obj instanceof WaterfallBarRenderer))
/* 463 */       return false; 
/* 465 */     WaterfallBarRenderer that = (WaterfallBarRenderer)obj;
/* 466 */     if (!PaintUtilities.equal(this.firstBarPaint, that.firstBarPaint))
/* 467 */       return false; 
/* 469 */     if (!PaintUtilities.equal(this.lastBarPaint, that.lastBarPaint))
/* 470 */       return false; 
/* 472 */     if (!PaintUtilities.equal(this.positiveBarPaint, that.positiveBarPaint))
/* 474 */       return false; 
/* 476 */     if (!PaintUtilities.equal(this.negativeBarPaint, that.negativeBarPaint))
/* 478 */       return false; 
/* 480 */     return true;
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream stream) throws IOException {
/* 492 */     stream.defaultWriteObject();
/* 493 */     SerialUtilities.writePaint(this.firstBarPaint, stream);
/* 494 */     SerialUtilities.writePaint(this.lastBarPaint, stream);
/* 495 */     SerialUtilities.writePaint(this.positiveBarPaint, stream);
/* 496 */     SerialUtilities.writePaint(this.negativeBarPaint, stream);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
/* 509 */     stream.defaultReadObject();
/* 510 */     this.firstBarPaint = SerialUtilities.readPaint(stream);
/* 511 */     this.lastBarPaint = SerialUtilities.readPaint(stream);
/* 512 */     this.positiveBarPaint = SerialUtilities.readPaint(stream);
/* 513 */     this.negativeBarPaint = SerialUtilities.readPaint(stream);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\renderer\category\WaterfallBarRenderer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */