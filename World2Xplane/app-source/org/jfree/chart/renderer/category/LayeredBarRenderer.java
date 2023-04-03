/*     */ package org.jfree.chart.renderer.category;
/*     */ 
/*     */ import java.awt.GradientPaint;
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
/*     */ import org.jfree.chart.labels.CategoryItemLabelGenerator;
/*     */ import org.jfree.chart.labels.CategoryToolTipGenerator;
/*     */ import org.jfree.chart.plot.CategoryPlot;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.data.category.CategoryDataset;
/*     */ import org.jfree.ui.GradientPaintTransformer;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.util.ObjectList;
/*     */ 
/*     */ public class LayeredBarRenderer extends BarRenderer implements Serializable {
/*     */   private static final long serialVersionUID = -8716572894780469487L;
/*     */   
/*  90 */   protected ObjectList seriesBarWidthList = new ObjectList();
/*     */   
/*     */   public double getSeriesBarWidth(int series) {
/* 102 */     double result = Double.NaN;
/* 103 */     Number n = (Number)this.seriesBarWidthList.get(series);
/* 104 */     if (n != null)
/* 105 */       result = n.doubleValue(); 
/* 107 */     return result;
/*     */   }
/*     */   
/*     */   public void setSeriesBarWidth(int series, double width) {
/* 118 */     this.seriesBarWidthList.set(series, new Double(width));
/*     */   }
/*     */   
/*     */   protected void calculateBarWidth(CategoryPlot plot, Rectangle2D dataArea, int rendererIndex, CategoryItemRendererState state) {
/* 135 */     CategoryAxis domainAxis = getDomainAxis(plot, rendererIndex);
/* 136 */     CategoryDataset dataset = plot.getDataset(rendererIndex);
/* 137 */     if (dataset != null) {
/* 138 */       int columns = dataset.getColumnCount();
/* 139 */       int rows = dataset.getRowCount();
/* 140 */       double space = 0.0D;
/* 141 */       PlotOrientation orientation = plot.getOrientation();
/* 142 */       if (orientation == PlotOrientation.HORIZONTAL) {
/* 143 */         space = dataArea.getHeight();
/* 145 */       } else if (orientation == PlotOrientation.VERTICAL) {
/* 146 */         space = dataArea.getWidth();
/*     */       } 
/* 148 */       double categoryMargin = 0.0D;
/* 149 */       if (columns > 1)
/* 150 */         categoryMargin = domainAxis.getCategoryMargin(); 
/* 152 */       double used = space * (1.0D - domainAxis.getLowerMargin() - domainAxis.getUpperMargin() - categoryMargin);
/* 154 */       if (rows * columns > 0) {
/* 155 */         state.setBarWidth(used / dataset.getColumnCount());
/*     */       } else {
/* 158 */         state.setBarWidth(used);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void drawItem(Graphics2D g2, CategoryItemRendererState state, Rectangle2D dataArea, CategoryPlot plot, CategoryAxis domainAxis, ValueAxis rangeAxis, CategoryDataset data, int row, int column, int pass) {
/* 188 */     PlotOrientation orientation = plot.getOrientation();
/* 189 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 190 */       drawHorizontalItem(g2, state, dataArea, plot, domainAxis, rangeAxis, data, row, column);
/* 193 */     } else if (orientation == PlotOrientation.VERTICAL) {
/* 194 */       drawVerticalItem(g2, state, dataArea, plot, domainAxis, rangeAxis, data, row, column);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void drawHorizontalItem(Graphics2D g2, CategoryItemRendererState state, Rectangle2D dataArea, CategoryPlot plot, CategoryAxis domainAxis, ValueAxis rangeAxis, CategoryDataset data, int row, int column) {
/* 224 */     Number dataValue = data.getValue(row, column);
/* 225 */     if (dataValue == null)
/*     */       return; 
/* 230 */     double value = dataValue.doubleValue();
/* 231 */     double base = 0.0D;
/* 232 */     double lclip = getLowerClip();
/* 233 */     double uclip = getUpperClip();
/* 234 */     if (uclip <= 0.0D) {
/* 235 */       if (value >= uclip)
/*     */         return; 
/* 238 */       base = uclip;
/* 239 */       if (value <= lclip)
/* 240 */         value = lclip; 
/* 243 */     } else if (lclip <= 0.0D) {
/* 244 */       if (value >= uclip) {
/* 245 */         value = uclip;
/* 248 */       } else if (value <= lclip) {
/* 249 */         value = lclip;
/*     */       } 
/*     */     } else {
/* 254 */       if (value <= lclip)
/*     */         return; 
/* 257 */       base = lclip;
/* 258 */       if (value >= uclip)
/* 259 */         value = uclip; 
/*     */     } 
/* 263 */     RectangleEdge edge = plot.getRangeAxisEdge();
/* 264 */     double transX1 = rangeAxis.valueToJava2D(base, dataArea, edge);
/* 265 */     double transX2 = rangeAxis.valueToJava2D(value, dataArea, edge);
/* 266 */     double rectX = Math.min(transX1, transX2);
/* 267 */     double rectWidth = Math.abs(transX2 - transX1);
/* 270 */     double rectY = domainAxis.getCategoryMiddle(column, getColumnCount(), dataArea, plot.getDomainAxisEdge()) - state.getBarWidth() / 2.0D;
/* 273 */     int seriesCount = getRowCount();
/* 276 */     double shift = 0.0D;
/* 277 */     double rectHeight = 0.0D;
/* 278 */     double widthFactor = 1.0D;
/* 279 */     double seriesBarWidth = getSeriesBarWidth(row);
/* 280 */     if (!Double.isNaN(seriesBarWidth))
/* 281 */       widthFactor = seriesBarWidth; 
/* 283 */     rectHeight = widthFactor * state.getBarWidth();
/* 284 */     rectY += (1.0D - widthFactor) * state.getBarWidth() / 2.0D;
/* 285 */     if (seriesCount > 1)
/* 286 */       shift = rectHeight * 0.2D / (seriesCount - 1); 
/* 289 */     Rectangle2D bar = new Rectangle2D.Double(rectX, rectY + (seriesCount - 1 - row) * shift, rectWidth, rectHeight - (seriesCount - 1 - row) * shift * 2.0D);
/* 293 */     Paint itemPaint = getItemPaint(row, column);
/* 294 */     GradientPaintTransformer t = getGradientPaintTransformer();
/* 295 */     if (t != null && itemPaint instanceof GradientPaint)
/* 296 */       itemPaint = t.transform((GradientPaint)itemPaint, bar); 
/* 298 */     g2.setPaint(itemPaint);
/* 299 */     g2.fill(bar);
/* 302 */     if (isDrawBarOutline() && state.getBarWidth() > 3.0D) {
/* 304 */       Stroke stroke = getItemOutlineStroke(row, column);
/* 305 */       Paint paint = getItemOutlinePaint(row, column);
/* 306 */       if (stroke != null && paint != null) {
/* 307 */         g2.setStroke(stroke);
/* 308 */         g2.setPaint(paint);
/* 309 */         g2.draw(bar);
/*     */       } 
/*     */     } 
/* 313 */     CategoryItemLabelGenerator generator = getItemLabelGenerator(row, column);
/* 315 */     if (generator != null && isItemLabelVisible(row, column))
/* 316 */       drawItemLabel(g2, data, row, column, plot, generator, bar, (transX1 > transX2)); 
/* 321 */     if (state.getInfo() != null) {
/* 322 */       EntityCollection entities = state.getEntityCollection();
/* 323 */       if (entities != null) {
/* 324 */         String tip = null;
/* 325 */         CategoryToolTipGenerator tipster = getToolTipGenerator(row, column);
/* 327 */         if (tipster != null)
/* 328 */           tip = tipster.generateToolTip(data, row, column); 
/* 330 */         String url = null;
/* 331 */         if (getItemURLGenerator(row, column) != null)
/* 332 */           url = getItemURLGenerator(row, column).generateURL(data, row, column); 
/* 335 */         CategoryItemEntity entity = new CategoryItemEntity(bar, tip, url, data, row, data.getColumnKey(column), column);
/* 337 */         entities.add((ChartEntity)entity);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void drawVerticalItem(Graphics2D g2, CategoryItemRendererState state, Rectangle2D dataArea, CategoryPlot plot, CategoryAxis domainAxis, ValueAxis rangeAxis, CategoryDataset data, int row, int column) {
/* 366 */     Number dataValue = data.getValue(row, column);
/* 367 */     if (dataValue == null)
/*     */       return; 
/* 372 */     double rectX = domainAxis.getCategoryMiddle(column, getColumnCount(), dataArea, plot.getDomainAxisEdge()) - state.getBarWidth() / 2.0D;
/* 375 */     int seriesCount = getRowCount();
/* 378 */     double value = dataValue.doubleValue();
/* 379 */     double base = 0.0D;
/* 380 */     double lclip = getLowerClip();
/* 381 */     double uclip = getUpperClip();
/* 383 */     if (uclip <= 0.0D) {
/* 384 */       if (value >= uclip)
/*     */         return; 
/* 387 */       base = uclip;
/* 388 */       if (value <= lclip)
/* 389 */         value = lclip; 
/* 392 */     } else if (lclip <= 0.0D) {
/* 393 */       if (value >= uclip) {
/* 394 */         value = uclip;
/* 397 */       } else if (value <= lclip) {
/* 398 */         value = lclip;
/*     */       } 
/*     */     } else {
/* 403 */       if (value <= lclip)
/*     */         return; 
/* 406 */       base = getLowerClip();
/* 407 */       if (value >= uclip)
/* 408 */         value = uclip; 
/*     */     } 
/* 412 */     RectangleEdge edge = plot.getRangeAxisEdge();
/* 413 */     double transY1 = rangeAxis.valueToJava2D(base, dataArea, edge);
/* 414 */     double transY2 = rangeAxis.valueToJava2D(value, dataArea, edge);
/* 415 */     double rectY = Math.min(transY2, transY1);
/* 417 */     double rectWidth = state.getBarWidth();
/* 418 */     double rectHeight = Math.abs(transY2 - transY1);
/* 421 */     double shift = 0.0D;
/* 422 */     rectWidth = 0.0D;
/* 423 */     double widthFactor = 1.0D;
/* 424 */     double seriesBarWidth = getSeriesBarWidth(row);
/* 425 */     if (!Double.isNaN(seriesBarWidth))
/* 426 */       widthFactor = seriesBarWidth; 
/* 428 */     rectWidth = widthFactor * state.getBarWidth();
/* 429 */     rectX += (1.0D - widthFactor) * state.getBarWidth() / 2.0D;
/* 430 */     if (seriesCount > 1)
/* 432 */       shift = rectWidth * 0.2D / (seriesCount - 1); 
/* 435 */     Rectangle2D bar = new Rectangle2D.Double(rectX + (seriesCount - 1 - row) * shift, rectY, rectWidth - (seriesCount - 1 - row) * shift * 2.0D, rectHeight);
/* 438 */     Paint itemPaint = getItemPaint(row, column);
/* 439 */     GradientPaintTransformer t = getGradientPaintTransformer();
/* 440 */     if (t != null && itemPaint instanceof GradientPaint)
/* 441 */       itemPaint = t.transform((GradientPaint)itemPaint, bar); 
/* 443 */     g2.setPaint(itemPaint);
/* 444 */     g2.fill(bar);
/* 447 */     if (isDrawBarOutline() && state.getBarWidth() > 3.0D) {
/* 449 */       Stroke stroke = getItemOutlineStroke(row, column);
/* 450 */       Paint paint = getItemOutlinePaint(row, column);
/* 451 */       if (stroke != null && paint != null) {
/* 452 */         g2.setStroke(stroke);
/* 453 */         g2.setPaint(paint);
/* 454 */         g2.draw(bar);
/*     */       } 
/*     */     } 
/* 459 */     double transX1 = rangeAxis.valueToJava2D(base, dataArea, edge);
/* 460 */     double transX2 = rangeAxis.valueToJava2D(value, dataArea, edge);
/* 462 */     CategoryItemLabelGenerator generator = getItemLabelGenerator(row, column);
/* 464 */     if (generator != null && isItemLabelVisible(row, column))
/* 465 */       drawItemLabel(g2, data, row, column, plot, generator, bar, (transX1 > transX2)); 
/* 470 */     if (state.getInfo() != null) {
/* 471 */       EntityCollection entities = state.getEntityCollection();
/* 472 */       if (entities != null) {
/* 473 */         String tip = null;
/* 474 */         CategoryToolTipGenerator tipster = getToolTipGenerator(row, column);
/* 476 */         if (tipster != null)
/* 477 */           tip = tipster.generateToolTip(data, row, column); 
/* 479 */         String url = null;
/* 480 */         if (getItemURLGenerator(row, column) != null)
/* 481 */           url = getItemURLGenerator(row, column).generateURL(data, row, column); 
/* 484 */         CategoryItemEntity entity = new CategoryItemEntity(bar, tip, url, data, row, data.getColumnKey(column), column);
/* 486 */         entities.add((ChartEntity)entity);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\renderer\category\LayeredBarRenderer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */