/*     */ package org.jfree.chart.renderer.category;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.geom.Line2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.axis.CategoryAxis;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.event.RendererChangeEvent;
/*     */ import org.jfree.chart.plot.CategoryPlot;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.data.category.CategoryDataset;
/*     */ import org.jfree.data.statistics.StatisticalCategoryDataset;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.util.PaintUtilities;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ public class StatisticalBarRenderer extends BarRenderer implements CategoryItemRenderer, Cloneable, PublicCloneable, Serializable {
/*     */   private static final long serialVersionUID = -4986038395414039117L;
/*     */   
/*  99 */   private transient Paint errorIndicatorPaint = Color.gray;
/*     */   
/*     */   public Paint getErrorIndicatorPaint() {
/* 109 */     return this.errorIndicatorPaint;
/*     */   }
/*     */   
/*     */   public void setErrorIndicatorPaint(Paint paint) {
/* 119 */     this.errorIndicatorPaint = paint;
/* 120 */     notifyListeners(new RendererChangeEvent(this));
/*     */   }
/*     */   
/*     */   public void drawItem(Graphics2D g2, CategoryItemRendererState state, Rectangle2D dataArea, CategoryPlot plot, CategoryAxis domainAxis, ValueAxis rangeAxis, CategoryDataset data, int row, int column, int pass) {
/* 150 */     if (!(data instanceof StatisticalCategoryDataset))
/* 151 */       throw new IllegalArgumentException("Requires StatisticalCategoryDataset."); 
/* 154 */     StatisticalCategoryDataset statData = (StatisticalCategoryDataset)data;
/* 156 */     PlotOrientation orientation = plot.getOrientation();
/* 157 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 158 */       drawHorizontalItem(g2, state, dataArea, plot, domainAxis, rangeAxis, statData, row, column);
/* 161 */     } else if (orientation == PlotOrientation.VERTICAL) {
/* 162 */       drawVerticalItem(g2, state, dataArea, plot, domainAxis, rangeAxis, statData, row, column);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void drawHorizontalItem(Graphics2D g2, CategoryItemRendererState state, Rectangle2D dataArea, CategoryPlot plot, CategoryAxis domainAxis, ValueAxis rangeAxis, StatisticalCategoryDataset dataset, int row, int column) {
/* 190 */     RectangleEdge xAxisLocation = plot.getDomainAxisEdge();
/* 193 */     double rectY = domainAxis.getCategoryStart(column, getColumnCount(), dataArea, xAxisLocation);
/* 197 */     int seriesCount = getRowCount();
/* 198 */     int categoryCount = getColumnCount();
/* 199 */     if (seriesCount > 1) {
/* 200 */       double seriesGap = dataArea.getHeight() * getItemMargin() / (categoryCount * (seriesCount - 1));
/* 202 */       rectY += row * (state.getBarWidth() + seriesGap);
/*     */     } else {
/* 205 */       rectY += row * state.getBarWidth();
/*     */     } 
/* 209 */     Number meanValue = dataset.getMeanValue(row, column);
/* 211 */     double value = meanValue.doubleValue();
/* 212 */     double base = 0.0D;
/* 213 */     double lclip = getLowerClip();
/* 214 */     double uclip = getUpperClip();
/* 216 */     if (uclip <= 0.0D) {
/* 217 */       if (value >= uclip)
/*     */         return; 
/* 220 */       base = uclip;
/* 221 */       if (value <= lclip)
/* 222 */         value = lclip; 
/* 225 */     } else if (lclip <= 0.0D) {
/* 226 */       if (value >= uclip) {
/* 227 */         value = uclip;
/* 230 */       } else if (value <= lclip) {
/* 231 */         value = lclip;
/*     */       } 
/*     */     } else {
/* 236 */       if (value <= lclip)
/*     */         return; 
/* 239 */       base = getLowerClip();
/* 240 */       if (value >= uclip)
/* 241 */         value = uclip; 
/*     */     } 
/* 245 */     RectangleEdge yAxisLocation = plot.getRangeAxisEdge();
/* 246 */     double transY1 = rangeAxis.valueToJava2D(base, dataArea, yAxisLocation);
/* 247 */     double transY2 = rangeAxis.valueToJava2D(value, dataArea, yAxisLocation);
/* 250 */     double rectX = Math.min(transY2, transY1);
/* 252 */     double rectHeight = state.getBarWidth();
/* 253 */     double rectWidth = Math.abs(transY2 - transY1);
/* 255 */     Rectangle2D bar = new Rectangle2D.Double(rectX, rectY, rectWidth, rectHeight);
/* 257 */     Paint seriesPaint = getItemPaint(row, column);
/* 258 */     g2.setPaint(seriesPaint);
/* 259 */     g2.fill(bar);
/* 260 */     if (state.getBarWidth() > 3.0D) {
/* 261 */       g2.setStroke(getItemStroke(row, column));
/* 262 */       g2.setPaint(getItemOutlinePaint(row, column));
/* 263 */       g2.draw(bar);
/*     */     } 
/* 267 */     double valueDelta = dataset.getStdDevValue(row, column).doubleValue();
/* 268 */     double highVal = rangeAxis.valueToJava2D(meanValue.doubleValue() + valueDelta, dataArea, yAxisLocation);
/* 271 */     double lowVal = rangeAxis.valueToJava2D(meanValue.doubleValue() - valueDelta, dataArea, yAxisLocation);
/* 275 */     if (this.errorIndicatorPaint != null) {
/* 276 */       g2.setPaint(this.errorIndicatorPaint);
/*     */     } else {
/* 279 */       g2.setPaint(getItemOutlinePaint(row, column));
/*     */     } 
/* 281 */     Line2D line = null;
/* 282 */     line = new Line2D.Double(lowVal, rectY + rectHeight / 2.0D, highVal, rectY + rectHeight / 2.0D);
/* 284 */     g2.draw(line);
/* 285 */     line = new Line2D.Double(highVal, rectY + rectHeight * 0.25D, highVal, rectY + rectHeight * 0.75D);
/* 287 */     g2.draw(line);
/* 288 */     line = new Line2D.Double(lowVal, rectY + rectHeight * 0.25D, lowVal, rectY + rectHeight * 0.75D);
/* 290 */     g2.draw(line);
/*     */   }
/*     */   
/*     */   protected void drawVerticalItem(Graphics2D g2, CategoryItemRendererState state, Rectangle2D dataArea, CategoryPlot plot, CategoryAxis domainAxis, ValueAxis rangeAxis, StatisticalCategoryDataset dataset, int row, int column) {
/* 316 */     RectangleEdge xAxisLocation = plot.getDomainAxisEdge();
/* 319 */     double rectX = domainAxis.getCategoryStart(column, getColumnCount(), dataArea, xAxisLocation);
/* 323 */     int seriesCount = getRowCount();
/* 324 */     int categoryCount = getColumnCount();
/* 325 */     if (seriesCount > 1) {
/* 326 */       double seriesGap = dataArea.getWidth() * getItemMargin() / (categoryCount * (seriesCount - 1));
/* 328 */       rectX += row * (state.getBarWidth() + seriesGap);
/*     */     } else {
/* 331 */       rectX += row * state.getBarWidth();
/*     */     } 
/* 335 */     Number meanValue = dataset.getMeanValue(row, column);
/* 337 */     double value = meanValue.doubleValue();
/* 338 */     double base = 0.0D;
/* 339 */     double lclip = getLowerClip();
/* 340 */     double uclip = getUpperClip();
/* 342 */     if (uclip <= 0.0D) {
/* 343 */       if (value >= uclip)
/*     */         return; 
/* 346 */       base = uclip;
/* 347 */       if (value <= lclip)
/* 348 */         value = lclip; 
/* 351 */     } else if (lclip <= 0.0D) {
/* 352 */       if (value >= uclip) {
/* 353 */         value = uclip;
/* 356 */       } else if (value <= lclip) {
/* 357 */         value = lclip;
/*     */       } 
/*     */     } else {
/* 362 */       if (value <= lclip)
/*     */         return; 
/* 365 */       base = getLowerClip();
/* 366 */       if (value >= uclip)
/* 367 */         value = uclip; 
/*     */     } 
/* 371 */     RectangleEdge yAxisLocation = plot.getRangeAxisEdge();
/* 372 */     double transY1 = rangeAxis.valueToJava2D(base, dataArea, yAxisLocation);
/* 373 */     double transY2 = rangeAxis.valueToJava2D(value, dataArea, yAxisLocation);
/* 376 */     double rectY = Math.min(transY2, transY1);
/* 378 */     double rectWidth = state.getBarWidth();
/* 379 */     double rectHeight = Math.abs(transY2 - transY1);
/* 381 */     Rectangle2D bar = new Rectangle2D.Double(rectX, rectY, rectWidth, rectHeight);
/* 383 */     Paint seriesPaint = getItemPaint(row, column);
/* 384 */     g2.setPaint(seriesPaint);
/* 385 */     g2.fill(bar);
/* 386 */     if (state.getBarWidth() > 3.0D) {
/* 387 */       g2.setStroke(getItemStroke(row, column));
/* 388 */       g2.setPaint(getItemOutlinePaint(row, column));
/* 389 */       g2.draw(bar);
/*     */     } 
/* 393 */     double valueDelta = dataset.getStdDevValue(row, column).doubleValue();
/* 394 */     double highVal = rangeAxis.valueToJava2D(meanValue.doubleValue() + valueDelta, dataArea, yAxisLocation);
/* 397 */     double lowVal = rangeAxis.valueToJava2D(meanValue.doubleValue() - valueDelta, dataArea, yAxisLocation);
/* 401 */     if (this.errorIndicatorPaint != null) {
/* 402 */       g2.setPaint(this.errorIndicatorPaint);
/*     */     } else {
/* 405 */       g2.setPaint(getItemOutlinePaint(row, column));
/*     */     } 
/* 407 */     Line2D line = null;
/* 408 */     line = new Line2D.Double(rectX + rectWidth / 2.0D, lowVal, rectX + rectWidth / 2.0D, highVal);
/* 410 */     g2.draw(line);
/* 411 */     line = new Line2D.Double(rectX + rectWidth / 2.0D - 5.0D, highVal, rectX + rectWidth / 2.0D + 5.0D, highVal);
/* 413 */     g2.draw(line);
/* 414 */     line = new Line2D.Double(rectX + rectWidth / 2.0D - 5.0D, lowVal, rectX + rectWidth / 2.0D + 5.0D, lowVal);
/* 416 */     g2.draw(line);
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 427 */     if (obj == this)
/* 428 */       return true; 
/* 430 */     if (!(obj instanceof StatisticalBarRenderer))
/* 431 */       return false; 
/* 433 */     if (!super.equals(obj))
/* 434 */       return false; 
/* 436 */     StatisticalBarRenderer that = (StatisticalBarRenderer)obj;
/* 437 */     if (!PaintUtilities.equal(this.errorIndicatorPaint, that.errorIndicatorPaint))
/* 439 */       return false; 
/* 441 */     return true;
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream stream) throws IOException {
/* 452 */     stream.defaultWriteObject();
/* 453 */     SerialUtilities.writePaint(this.errorIndicatorPaint, stream);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
/* 466 */     stream.defaultReadObject();
/* 467 */     this.errorIndicatorPaint = SerialUtilities.readPaint(stream);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\renderer\category\StatisticalBarRenderer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */