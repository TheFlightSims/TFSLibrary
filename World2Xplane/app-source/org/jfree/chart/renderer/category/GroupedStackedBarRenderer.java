/*     */ package org.jfree.chart.renderer.category;
/*     */ 
/*     */ import java.awt.GradientPaint;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
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
/*     */ import org.jfree.data.KeyToGroupMap;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.data.category.CategoryDataset;
/*     */ import org.jfree.data.general.DatasetUtilities;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ public class GroupedStackedBarRenderer extends StackedBarRenderer implements Cloneable, PublicCloneable, Serializable {
/*     */   private static final long serialVersionUID = -2725921399005922939L;
/*     */   
/*  92 */   private KeyToGroupMap seriesToGroupMap = new KeyToGroupMap();
/*     */   
/*     */   public void setSeriesToGroupMap(KeyToGroupMap map) {
/* 101 */     if (map == null)
/* 102 */       throw new IllegalArgumentException("Null 'map' argument."); 
/* 104 */     this.seriesToGroupMap = map;
/* 105 */     notifyListeners(new RendererChangeEvent(this));
/*     */   }
/*     */   
/*     */   public Range findRangeBounds(CategoryDataset dataset) {
/* 118 */     Range r = DatasetUtilities.findStackedRangeBounds(dataset, this.seriesToGroupMap);
/* 121 */     return r;
/*     */   }
/*     */   
/*     */   protected void calculateBarWidth(CategoryPlot plot, Rectangle2D dataArea, int rendererIndex, CategoryItemRendererState state) {
/* 140 */     CategoryAxis xAxis = plot.getDomainAxisForDataset(rendererIndex);
/* 141 */     CategoryDataset data = plot.getDataset(rendererIndex);
/* 142 */     if (data != null) {
/* 143 */       PlotOrientation orientation = plot.getOrientation();
/* 144 */       double space = 0.0D;
/* 145 */       if (orientation == PlotOrientation.HORIZONTAL) {
/* 146 */         space = dataArea.getHeight();
/* 148 */       } else if (orientation == PlotOrientation.VERTICAL) {
/* 149 */         space = dataArea.getWidth();
/*     */       } 
/* 151 */       double maxWidth = space * getMaximumBarWidth();
/* 152 */       int groups = this.seriesToGroupMap.getGroupCount();
/* 153 */       int categories = data.getColumnCount();
/* 154 */       int columns = groups * categories;
/* 155 */       double categoryMargin = 0.0D;
/* 156 */       double itemMargin = 0.0D;
/* 157 */       if (categories > 1)
/* 158 */         categoryMargin = xAxis.getCategoryMargin(); 
/* 160 */       if (groups > 1)
/* 161 */         itemMargin = getItemMargin(); 
/* 164 */       double used = space * (1.0D - xAxis.getLowerMargin() - xAxis.getUpperMargin() - categoryMargin - itemMargin);
/* 167 */       if (columns > 0) {
/* 168 */         state.setBarWidth(Math.min(used / columns, maxWidth));
/*     */       } else {
/* 171 */         state.setBarWidth(Math.min(used, maxWidth));
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected double calculateBarW0(CategoryPlot plot, PlotOrientation orientation, Rectangle2D dataArea, CategoryAxis domainAxis, CategoryItemRendererState state, int row, int column) {
/* 200 */     double space = 0.0D;
/* 201 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 202 */       space = dataArea.getHeight();
/*     */     } else {
/* 205 */       space = dataArea.getWidth();
/*     */     } 
/* 207 */     double barW0 = domainAxis.getCategoryStart(column, getColumnCount(), dataArea, plot.getDomainAxisEdge());
/* 210 */     int groupCount = this.seriesToGroupMap.getGroupCount();
/* 211 */     int groupIndex = this.seriesToGroupMap.getGroupIndex(this.seriesToGroupMap.getGroup(plot.getDataset().getRowKey(row)));
/* 214 */     int categoryCount = getColumnCount();
/* 215 */     if (groupCount > 1) {
/* 216 */       double groupGap = space * getItemMargin() / (categoryCount * (groupCount - 1));
/* 218 */       double groupW = calculateSeriesWidth(space, domainAxis, categoryCount, groupCount);
/* 221 */       barW0 = barW0 + groupIndex * (groupW + groupGap) + groupW / 2.0D - state.getBarWidth() / 2.0D;
/*     */     } else {
/* 225 */       barW0 = domainAxis.getCategoryMiddle(column, getColumnCount(), dataArea, plot.getDomainAxisEdge()) - state.getBarWidth() / 2.0D;
/*     */     } 
/* 229 */     return barW0;
/*     */   }
/*     */   
/*     */   public void drawItem(Graphics2D g2, CategoryItemRendererState state, Rectangle2D dataArea, CategoryPlot plot, CategoryAxis domainAxis, ValueAxis rangeAxis, CategoryDataset dataset, int row, int column, int pass) {
/*     */     double translatedBase, translatedValue;
/* 258 */     Number dataValue = dataset.getValue(row, column);
/* 259 */     if (dataValue == null)
/*     */       return; 
/* 263 */     double value = dataValue.doubleValue();
/* 264 */     Comparable group = this.seriesToGroupMap.getGroup(dataset.getRowKey(row));
/* 266 */     PlotOrientation orientation = plot.getOrientation();
/* 267 */     double barW0 = calculateBarW0(plot, orientation, dataArea, domainAxis, state, row, column);
/* 272 */     double positiveBase = 0.0D;
/* 273 */     double negativeBase = 0.0D;
/* 275 */     for (int i = 0; i < row; i++) {
/* 276 */       if (group.equals(this.seriesToGroupMap.getGroup(dataset.getRowKey(i)))) {
/* 279 */         Number v = dataset.getValue(i, column);
/* 280 */         if (v != null) {
/* 281 */           double d = v.doubleValue();
/* 282 */           if (d > 0.0D) {
/* 283 */             positiveBase += d;
/*     */           } else {
/* 286 */             negativeBase += d;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 294 */     RectangleEdge location = plot.getRangeAxisEdge();
/* 295 */     if (value > 0.0D) {
/* 296 */       translatedBase = rangeAxis.valueToJava2D(positiveBase, dataArea, location);
/* 298 */       translatedValue = rangeAxis.valueToJava2D(positiveBase + value, dataArea, location);
/*     */     } else {
/* 303 */       translatedBase = rangeAxis.valueToJava2D(negativeBase, dataArea, location);
/* 306 */       translatedValue = rangeAxis.valueToJava2D(negativeBase + value, dataArea, location);
/*     */     } 
/* 310 */     double barL0 = Math.min(translatedBase, translatedValue);
/* 311 */     double barLength = Math.max(Math.abs(translatedValue - translatedBase), getMinimumBarLength());
/* 315 */     Rectangle2D bar = null;
/* 316 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 317 */       bar = new Rectangle2D.Double(barL0, barW0, barLength, state.getBarWidth());
/*     */     } else {
/* 322 */       bar = new Rectangle2D.Double(barW0, barL0, state.getBarWidth(), barLength);
/*     */     } 
/* 326 */     Paint itemPaint = getItemPaint(row, column);
/* 327 */     if (getGradientPaintTransformer() != null && itemPaint instanceof GradientPaint) {
/* 329 */       GradientPaint gp = (GradientPaint)itemPaint;
/* 330 */       itemPaint = getGradientPaintTransformer().transform(gp, bar);
/*     */     } 
/* 332 */     g2.setPaint(itemPaint);
/* 333 */     g2.fill(bar);
/* 334 */     if (isDrawBarOutline() && state.getBarWidth() > 3.0D) {
/* 336 */       g2.setStroke(getItemStroke(row, column));
/* 337 */       g2.setPaint(getItemOutlinePaint(row, column));
/* 338 */       g2.draw(bar);
/*     */     } 
/* 341 */     CategoryItemLabelGenerator generator = getItemLabelGenerator(row, column);
/* 343 */     if (generator != null && isItemLabelVisible(row, column))
/* 344 */       drawItemLabel(g2, dataset, row, column, plot, generator, bar, (value < 0.0D)); 
/* 351 */     if (state.getInfo() != null) {
/* 352 */       EntityCollection entities = state.getEntityCollection();
/* 353 */       if (entities != null) {
/* 354 */         String tip = null;
/* 355 */         CategoryToolTipGenerator tipster = getToolTipGenerator(row, column);
/* 357 */         if (tipster != null)
/* 358 */           tip = tipster.generateToolTip(dataset, row, column); 
/* 360 */         String url = null;
/* 361 */         if (getItemURLGenerator(row, column) != null)
/* 362 */           url = getItemURLGenerator(row, column).generateURL(dataset, row, column); 
/* 366 */         CategoryItemEntity entity = new CategoryItemEntity(bar, tip, url, dataset, row, dataset.getColumnKey(column), column);
/* 370 */         entities.add((ChartEntity)entity);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 384 */     if (obj == this)
/* 385 */       return true; 
/* 387 */     if (obj instanceof GroupedStackedBarRenderer && super.equals(obj)) {
/* 388 */       GroupedStackedBarRenderer r = (GroupedStackedBarRenderer)obj;
/* 389 */       if (!r.seriesToGroupMap.equals(this.seriesToGroupMap))
/* 390 */         return false; 
/* 392 */       return true;
/*     */     } 
/* 394 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\renderer\category\GroupedStackedBarRenderer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */