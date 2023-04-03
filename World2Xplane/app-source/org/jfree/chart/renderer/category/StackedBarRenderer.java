/*     */ package org.jfree.chart.renderer.category;
/*     */ 
/*     */ import java.awt.GradientPaint;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.axis.CategoryAxis;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.entity.EntityCollection;
/*     */ import org.jfree.chart.event.RendererChangeEvent;
/*     */ import org.jfree.chart.labels.CategoryItemLabelGenerator;
/*     */ import org.jfree.chart.labels.ItemLabelAnchor;
/*     */ import org.jfree.chart.labels.ItemLabelPosition;
/*     */ import org.jfree.chart.plot.CategoryPlot;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.data.DataUtilities;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.data.Values2D;
/*     */ import org.jfree.data.category.CategoryDataset;
/*     */ import org.jfree.data.general.DatasetUtilities;
/*     */ import org.jfree.ui.GradientPaintTransformer;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.ui.TextAnchor;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ public class StackedBarRenderer extends BarRenderer implements Cloneable, PublicCloneable, Serializable {
/*     */   static final long serialVersionUID = 6402943811500067531L;
/*     */   
/*     */   private boolean renderAsPercentages;
/*     */   
/*     */   public StackedBarRenderer() {
/* 140 */     this(false);
/*     */   }
/*     */   
/*     */   public StackedBarRenderer(boolean renderAsPercentages) {
/* 151 */     this.renderAsPercentages = renderAsPercentages;
/* 155 */     ItemLabelPosition p = new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER);
/* 158 */     setBasePositiveItemLabelPosition(p);
/* 159 */     setBaseNegativeItemLabelPosition(p);
/* 160 */     setPositiveItemLabelPositionFallback((ItemLabelPosition)null);
/* 161 */     setNegativeItemLabelPositionFallback((ItemLabelPosition)null);
/*     */   }
/*     */   
/*     */   public boolean getRenderAsPercentages() {
/* 172 */     return this.renderAsPercentages;
/*     */   }
/*     */   
/*     */   public void setRenderAsPercentages(boolean asPercentages) {
/* 183 */     this.renderAsPercentages = asPercentages;
/* 184 */     notifyListeners(new RendererChangeEvent(this));
/*     */   }
/*     */   
/*     */   public int getPassCount() {
/* 195 */     return 2;
/*     */   }
/*     */   
/*     */   public Range findRangeBounds(CategoryDataset dataset) {
/* 207 */     if (this.renderAsPercentages)
/* 208 */       return new Range(0.0D, 1.0D); 
/* 211 */     return DatasetUtilities.findStackedRangeBounds(dataset, getBase());
/*     */   }
/*     */   
/*     */   protected void calculateBarWidth(CategoryPlot plot, Rectangle2D dataArea, int rendererIndex, CategoryItemRendererState state) {
/* 229 */     CategoryAxis xAxis = plot.getDomainAxisForDataset(rendererIndex);
/* 230 */     CategoryDataset data = plot.getDataset(rendererIndex);
/* 231 */     if (data != null) {
/* 232 */       PlotOrientation orientation = plot.getOrientation();
/* 233 */       double space = 0.0D;
/* 234 */       if (orientation == PlotOrientation.HORIZONTAL) {
/* 235 */         space = dataArea.getHeight();
/* 237 */       } else if (orientation == PlotOrientation.VERTICAL) {
/* 238 */         space = dataArea.getWidth();
/*     */       } 
/* 240 */       double maxWidth = space * getMaximumBarWidth();
/* 241 */       int columns = data.getColumnCount();
/* 242 */       double categoryMargin = 0.0D;
/* 243 */       if (columns > 1)
/* 244 */         categoryMargin = xAxis.getCategoryMargin(); 
/* 247 */       double used = space * (1.0D - xAxis.getLowerMargin() - xAxis.getUpperMargin() - categoryMargin);
/* 250 */       if (columns > 0) {
/* 251 */         state.setBarWidth(Math.min(used / columns, maxWidth));
/*     */       } else {
/* 254 */         state.setBarWidth(Math.min(used, maxWidth));
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void drawItem(Graphics2D g2, CategoryItemRendererState state, Rectangle2D dataArea, CategoryPlot plot, CategoryAxis domainAxis, ValueAxis rangeAxis, CategoryDataset dataset, int row, int column, int pass) {
/*     */     double translatedBase, translatedValue;
/* 286 */     Number dataValue = dataset.getValue(row, column);
/* 287 */     if (dataValue == null)
/*     */       return; 
/* 291 */     double value = dataValue.doubleValue();
/* 292 */     double total = 0.0D;
/* 293 */     if (this.renderAsPercentages) {
/* 294 */       total = DataUtilities.calculateColumnTotal((Values2D)dataset, column);
/* 295 */       value /= total;
/*     */     } 
/* 298 */     PlotOrientation orientation = plot.getOrientation();
/* 299 */     double barW0 = domainAxis.getCategoryMiddle(column, getColumnCount(), dataArea, plot.getDomainAxisEdge()) - state.getBarWidth() / 2.0D;
/* 303 */     double positiveBase = getBase();
/* 304 */     double negativeBase = positiveBase;
/* 306 */     for (int i = 0; i < row; i++) {
/* 307 */       Number v = dataset.getValue(i, column);
/* 308 */       if (v != null) {
/* 309 */         double d = v.doubleValue();
/* 310 */         if (this.renderAsPercentages)
/* 311 */           d /= total; 
/* 313 */         if (d > 0.0D) {
/* 314 */           positiveBase += d;
/*     */         } else {
/* 317 */           negativeBase += d;
/*     */         } 
/*     */       } 
/*     */     } 
/* 324 */     RectangleEdge location = plot.getRangeAxisEdge();
/* 325 */     if (value >= 0.0D) {
/* 326 */       translatedBase = rangeAxis.valueToJava2D(positiveBase, dataArea, location);
/* 329 */       translatedValue = rangeAxis.valueToJava2D(positiveBase + value, dataArea, location);
/*     */     } else {
/* 334 */       translatedBase = rangeAxis.valueToJava2D(negativeBase, dataArea, location);
/* 337 */       translatedValue = rangeAxis.valueToJava2D(negativeBase + value, dataArea, location);
/*     */     } 
/* 341 */     double barL0 = Math.min(translatedBase, translatedValue);
/* 342 */     double barLength = Math.max(Math.abs(translatedValue - translatedBase), getMinimumBarLength());
/* 346 */     Rectangle2D bar = null;
/* 347 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 348 */       bar = new Rectangle2D.Double(barL0, barW0, barLength, state.getBarWidth());
/*     */     } else {
/* 352 */       bar = new Rectangle2D.Double(barW0, barL0, state.getBarWidth(), barLength);
/*     */     } 
/* 356 */     if (pass == 0) {
/* 357 */       Paint itemPaint = getItemPaint(row, column);
/* 358 */       GradientPaintTransformer t = getGradientPaintTransformer();
/* 359 */       if (t != null && itemPaint instanceof GradientPaint)
/* 360 */         itemPaint = t.transform((GradientPaint)itemPaint, bar); 
/* 362 */       g2.setPaint(itemPaint);
/* 363 */       g2.fill(bar);
/* 364 */       if (isDrawBarOutline() && state.getBarWidth() > 3.0D) {
/* 366 */         g2.setStroke(getItemOutlineStroke(row, column));
/* 367 */         g2.setPaint(getItemOutlinePaint(row, column));
/* 368 */         g2.draw(bar);
/*     */       } 
/* 372 */       EntityCollection entities = state.getEntityCollection();
/* 373 */       if (entities != null)
/* 374 */         addItemEntity(entities, dataset, row, column, bar); 
/* 377 */     } else if (pass == 1) {
/* 378 */       CategoryItemLabelGenerator generator = getItemLabelGenerator(row, column);
/* 380 */       if (generator != null && isItemLabelVisible(row, column))
/* 381 */         drawItemLabel(g2, dataset, row, column, plot, generator, bar, (value < 0.0D)); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 397 */     if (obj == this)
/* 398 */       return true; 
/* 400 */     if (!(obj instanceof StackedBarRenderer))
/* 401 */       return false; 
/* 403 */     if (!super.equals(obj))
/* 404 */       return false; 
/* 406 */     StackedBarRenderer that = (StackedBarRenderer)obj;
/* 407 */     if (this.renderAsPercentages != that.renderAsPercentages)
/* 408 */       return false; 
/* 410 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\renderer\category\StackedBarRenderer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */