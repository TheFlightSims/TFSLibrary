/*     */ package org.jfree.chart.renderer.category;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.axis.CategoryAxis;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.entity.CategoryItemEntity;
/*     */ import org.jfree.chart.entity.ChartEntity;
/*     */ import org.jfree.chart.entity.EntityCollection;
/*     */ import org.jfree.chart.event.RendererChangeEvent;
/*     */ import org.jfree.chart.labels.CategoryItemLabelGenerator;
/*     */ import org.jfree.chart.labels.CategoryToolTipGenerator;
/*     */ import org.jfree.chart.plot.CategoryPlot;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.data.category.CategoryDataset;
/*     */ import org.jfree.data.gantt.GanttCategoryDataset;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ 
/*     */ public class GanttRenderer extends IntervalBarRenderer implements Serializable {
/*     */   private static final long serialVersionUID = -4010349116350119512L;
/*     */   
/* 105 */   private Paint completePaint = Color.green;
/*     */   
/* 106 */   private Paint incompletePaint = Color.red;
/*     */   
/* 107 */   private double startPercent = 0.35D;
/*     */   
/* 108 */   private double endPercent = 0.65D;
/*     */   
/*     */   public Paint getCompletePaint() {
/* 117 */     return this.completePaint;
/*     */   }
/*     */   
/*     */   public void setCompletePaint(Paint paint) {
/* 127 */     if (paint == null)
/* 128 */       throw new IllegalArgumentException("Null 'paint' argument."); 
/* 130 */     this.completePaint = paint;
/* 131 */     notifyListeners(new RendererChangeEvent(this));
/*     */   }
/*     */   
/*     */   public Paint getIncompletePaint() {
/* 140 */     return this.incompletePaint;
/*     */   }
/*     */   
/*     */   public void setIncompletePaint(Paint paint) {
/* 150 */     if (paint == null)
/* 151 */       throw new IllegalArgumentException("Null 'paint' argument."); 
/* 153 */     this.incompletePaint = paint;
/* 154 */     notifyListeners(new RendererChangeEvent(this));
/*     */   }
/*     */   
/*     */   public double getStartPercent() {
/* 164 */     return this.startPercent;
/*     */   }
/*     */   
/*     */   public void setStartPercent(double percent) {
/* 174 */     this.startPercent = percent;
/* 175 */     notifyListeners(new RendererChangeEvent(this));
/*     */   }
/*     */   
/*     */   public double getEndPercent() {
/* 185 */     return this.endPercent;
/*     */   }
/*     */   
/*     */   public void setEndPercent(double percent) {
/* 195 */     this.endPercent = percent;
/* 196 */     notifyListeners(new RendererChangeEvent(this));
/*     */   }
/*     */   
/*     */   public void drawItem(Graphics2D g2, CategoryItemRendererState state, Rectangle2D dataArea, CategoryPlot plot, CategoryAxis domainAxis, ValueAxis rangeAxis, CategoryDataset dataset, int row, int column, int pass) {
/* 224 */     if (dataset instanceof GanttCategoryDataset) {
/* 225 */       GanttCategoryDataset gcd = (GanttCategoryDataset)dataset;
/* 226 */       drawTasks(g2, state, dataArea, plot, domainAxis, rangeAxis, gcd, row, column);
/*     */     } else {
/* 232 */       super.drawItem(g2, state, dataArea, plot, domainAxis, rangeAxis, dataset, row, column, pass);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void drawTasks(Graphics2D g2, CategoryItemRendererState state, Rectangle2D dataArea, CategoryPlot plot, CategoryAxis domainAxis, ValueAxis rangeAxis, GanttCategoryDataset dataset, int row, int column) {
/* 263 */     int count = dataset.getSubIntervalCount(row, column);
/* 264 */     if (count == 0)
/* 265 */       drawTask(g2, state, dataArea, plot, domainAxis, rangeAxis, dataset, row, column); 
/* 271 */     for (int subinterval = 0; subinterval < count; subinterval++) {
/* 273 */       RectangleEdge rangeAxisLocation = plot.getRangeAxisEdge();
/* 276 */       Number value0 = dataset.getStartValue(row, column, subinterval);
/* 277 */       if (value0 == null)
/*     */         return; 
/* 280 */       double translatedValue0 = rangeAxis.valueToJava2D(value0.doubleValue(), dataArea, rangeAxisLocation);
/* 285 */       Number value1 = dataset.getEndValue(row, column, subinterval);
/* 286 */       if (value1 == null)
/*     */         return; 
/* 289 */       double translatedValue1 = rangeAxis.valueToJava2D(value1.doubleValue(), dataArea, rangeAxisLocation);
/* 293 */       if (translatedValue1 < translatedValue0) {
/* 294 */         double temp = translatedValue1;
/* 295 */         translatedValue1 = translatedValue0;
/* 296 */         translatedValue0 = temp;
/*     */       } 
/* 299 */       double rectStart = calculateBarW0(plot, plot.getOrientation(), dataArea, domainAxis, state, row, column);
/* 303 */       double rectLength = Math.abs(translatedValue1 - translatedValue0);
/* 304 */       double rectBreadth = state.getBarWidth();
/* 307 */       Rectangle2D bar = null;
/* 309 */       if (plot.getOrientation() == PlotOrientation.HORIZONTAL) {
/* 310 */         bar = new Rectangle2D.Double(translatedValue0, rectStart, rectLength, rectBreadth);
/* 314 */       } else if (plot.getOrientation() == PlotOrientation.VERTICAL) {
/* 315 */         bar = new Rectangle2D.Double(rectStart, translatedValue0, rectBreadth, rectLength);
/*     */       } 
/* 320 */       Rectangle2D completeBar = null;
/* 321 */       Rectangle2D incompleteBar = null;
/* 322 */       Number percent = dataset.getPercentComplete(row, column, subinterval);
/* 325 */       double start = getStartPercent();
/* 326 */       double end = getEndPercent();
/* 327 */       if (percent != null) {
/* 328 */         double p = percent.doubleValue();
/* 329 */         if (plot.getOrientation() == PlotOrientation.HORIZONTAL) {
/* 330 */           completeBar = new Rectangle2D.Double(translatedValue0, rectStart + start * rectBreadth, rectLength * p, rectBreadth * (end - start));
/* 336 */           incompleteBar = new Rectangle2D.Double(translatedValue0 + rectLength * p, rectStart + start * rectBreadth, rectLength * (1.0D - p), rectBreadth * (end - start));
/* 343 */         } else if (plot.getOrientation() == PlotOrientation.VERTICAL) {
/* 344 */           completeBar = new Rectangle2D.Double(rectStart + start * rectBreadth, translatedValue0 + rectLength * (1.0D - p), rectBreadth * (end - start), rectLength * p);
/* 350 */           incompleteBar = new Rectangle2D.Double(rectStart + start * rectBreadth, translatedValue0, rectBreadth * (end - start), rectLength * (1.0D - p));
/*     */         } 
/*     */       } 
/* 360 */       Paint seriesPaint = getItemPaint(row, column);
/* 361 */       g2.setPaint(seriesPaint);
/* 362 */       g2.fill(bar);
/* 363 */       if (completeBar != null) {
/* 364 */         g2.setPaint(getCompletePaint());
/* 365 */         g2.fill(completeBar);
/*     */       } 
/* 367 */       if (incompleteBar != null) {
/* 368 */         g2.setPaint(getIncompletePaint());
/* 369 */         g2.fill(incompleteBar);
/*     */       } 
/* 371 */       if (isDrawBarOutline() && state.getBarWidth() > 3.0D) {
/* 373 */         g2.setStroke(getItemStroke(row, column));
/* 374 */         g2.setPaint(getItemOutlinePaint(row, column));
/* 375 */         g2.draw(bar);
/*     */       } 
/* 379 */       if (state.getInfo() != null) {
/* 380 */         EntityCollection entities = state.getEntityCollection();
/* 381 */         if (entities != null) {
/* 382 */           String tip = null;
/* 383 */           if (getToolTipGenerator(row, column) != null)
/* 384 */             tip = getToolTipGenerator(row, column).generateToolTip((CategoryDataset)dataset, row, column); 
/* 388 */           String url = null;
/* 389 */           if (getItemURLGenerator(row, column) != null)
/* 390 */             url = getItemURLGenerator(row, column).generateURL((CategoryDataset)dataset, row, column); 
/* 394 */           CategoryItemEntity entity = new CategoryItemEntity(bar, tip, url, (CategoryDataset)dataset, row, dataset.getColumnKey(column), column);
/* 398 */           entities.add((ChartEntity)entity);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void drawTask(Graphics2D g2, CategoryItemRendererState state, Rectangle2D dataArea, CategoryPlot plot, CategoryAxis domainAxis, ValueAxis rangeAxis, GanttCategoryDataset dataset, int row, int column) {
/* 427 */     PlotOrientation orientation = plot.getOrientation();
/* 429 */     RectangleEdge rangeAxisLocation = plot.getRangeAxisEdge();
/* 432 */     Number value0 = dataset.getEndValue(row, column);
/* 433 */     if (value0 == null)
/*     */       return; 
/* 436 */     double java2dValue0 = rangeAxis.valueToJava2D(value0.doubleValue(), dataArea, rangeAxisLocation);
/* 441 */     Number value1 = dataset.getStartValue(row, column);
/* 442 */     if (value1 == null)
/*     */       return; 
/* 445 */     double java2dValue1 = rangeAxis.valueToJava2D(value1.doubleValue(), dataArea, rangeAxisLocation);
/* 449 */     if (java2dValue1 < java2dValue0) {
/* 450 */       double temp = java2dValue1;
/* 451 */       java2dValue1 = java2dValue0;
/* 452 */       java2dValue0 = temp;
/* 453 */       Number tempNum = value1;
/* 454 */       value1 = value0;
/* 455 */       value0 = tempNum;
/*     */     } 
/* 458 */     double rectStart = calculateBarW0(plot, orientation, dataArea, domainAxis, state, row, column);
/* 461 */     double rectBreadth = state.getBarWidth();
/* 462 */     double rectLength = Math.abs(java2dValue1 - java2dValue0);
/* 464 */     Rectangle2D bar = null;
/* 465 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 466 */       bar = new Rectangle2D.Double(java2dValue0, rectStart, rectLength, rectBreadth);
/* 470 */     } else if (orientation == PlotOrientation.VERTICAL) {
/* 471 */       bar = new Rectangle2D.Double(rectStart, java2dValue1, rectBreadth, rectLength);
/*     */     } 
/* 476 */     Rectangle2D completeBar = null;
/* 477 */     Rectangle2D incompleteBar = null;
/* 478 */     Number percent = dataset.getPercentComplete(row, column);
/* 479 */     double start = getStartPercent();
/* 480 */     double end = getEndPercent();
/* 481 */     if (percent != null) {
/* 482 */       double p = percent.doubleValue();
/* 483 */       if (plot.getOrientation() == PlotOrientation.HORIZONTAL) {
/* 484 */         completeBar = new Rectangle2D.Double(java2dValue0, rectStart + start * rectBreadth, rectLength * p, rectBreadth * (end - start));
/* 490 */         incompleteBar = new Rectangle2D.Double(java2dValue0 + rectLength * p, rectStart + start * rectBreadth, rectLength * (1.0D - p), rectBreadth * (end - start));
/* 497 */       } else if (plot.getOrientation() == PlotOrientation.VERTICAL) {
/* 498 */         completeBar = new Rectangle2D.Double(rectStart + start * rectBreadth, java2dValue1 + rectLength * (1.0D - p), rectBreadth * (end - start), rectLength * p);
/* 504 */         incompleteBar = new Rectangle2D.Double(rectStart + start * rectBreadth, java2dValue1, rectBreadth * (end - start), rectLength * (1.0D - p));
/*     */       } 
/*     */     } 
/* 514 */     Paint seriesPaint = getItemPaint(row, column);
/* 515 */     g2.setPaint(seriesPaint);
/* 516 */     g2.fill(bar);
/* 518 */     if (completeBar != null) {
/* 519 */       g2.setPaint(getCompletePaint());
/* 520 */       g2.fill(completeBar);
/*     */     } 
/* 522 */     if (incompleteBar != null) {
/* 523 */       g2.setPaint(getIncompletePaint());
/* 524 */       g2.fill(incompleteBar);
/*     */     } 
/* 528 */     if (isDrawBarOutline() && state.getBarWidth() > 3.0D) {
/* 530 */       Stroke stroke = getItemOutlineStroke(row, column);
/* 531 */       Paint paint = getItemOutlinePaint(row, column);
/* 532 */       if (stroke != null && paint != null) {
/* 533 */         g2.setStroke(stroke);
/* 534 */         g2.setPaint(paint);
/* 535 */         g2.draw(bar);
/*     */       } 
/*     */     } 
/* 539 */     CategoryItemLabelGenerator generator = getItemLabelGenerator(row, column);
/* 541 */     if (generator != null && isItemLabelVisible(row, column))
/* 542 */       drawItemLabel(g2, (CategoryDataset)dataset, row, column, plot, generator, bar, false); 
/* 548 */     if (state.getInfo() != null) {
/* 549 */       EntityCollection entities = state.getEntityCollection();
/* 550 */       if (entities != null) {
/* 551 */         String tip = null;
/* 552 */         CategoryToolTipGenerator tipster = getToolTipGenerator(row, column);
/* 555 */         if (tipster != null)
/* 556 */           tip = tipster.generateToolTip((CategoryDataset)dataset, row, column); 
/* 558 */         String url = null;
/* 559 */         if (getItemURLGenerator(row, column) != null)
/* 560 */           url = getItemURLGenerator(row, column).generateURL((CategoryDataset)dataset, row, column); 
/* 564 */         CategoryItemEntity entity = new CategoryItemEntity(bar, tip, url, (CategoryDataset)dataset, row, dataset.getColumnKey(column), column);
/* 568 */         entities.add((ChartEntity)entity);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\renderer\category\GanttRenderer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */