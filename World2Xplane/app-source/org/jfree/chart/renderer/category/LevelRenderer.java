/*     */ package org.jfree.chart.renderer.category;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.Line2D;
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
/*     */ import org.jfree.chart.plot.PlotRenderingInfo;
/*     */ import org.jfree.data.category.CategoryDataset;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ public class LevelRenderer extends AbstractCategoryItemRenderer implements Cloneable, PublicCloneable, Serializable {
/*     */   private static final long serialVersionUID = -8204856624355025117L;
/*     */   
/*     */   public static final double DEFAULT_ITEM_MARGIN = 0.2D;
/*     */   
/*  93 */   private double itemMargin = 0.2D;
/*     */   
/*  94 */   private double maxItemWidth = 1.0D;
/*     */   
/*     */   public double getItemMargin() {
/* 104 */     return this.itemMargin;
/*     */   }
/*     */   
/*     */   public void setItemMargin(double percent) {
/* 115 */     this.itemMargin = percent;
/* 116 */     notifyListeners(new RendererChangeEvent(this));
/*     */   }
/*     */   
/*     */   public double getMaxItemWidth() {
/* 126 */     return this.maxItemWidth;
/*     */   }
/*     */   
/*     */   public void setMaxItemWidth(double percent) {
/* 137 */     this.maxItemWidth = percent;
/* 138 */     notifyListeners(new RendererChangeEvent(this));
/*     */   }
/*     */   
/*     */   public CategoryItemRendererState initialise(Graphics2D g2, Rectangle2D dataArea, CategoryPlot plot, int rendererIndex, PlotRenderingInfo info) {
/* 163 */     CategoryItemRendererState state = super.initialise(g2, dataArea, plot, rendererIndex, info);
/* 166 */     calculateItemWidth(plot, dataArea, rendererIndex, state);
/* 167 */     return state;
/*     */   }
/*     */   
/*     */   protected void calculateItemWidth(CategoryPlot plot, Rectangle2D dataArea, int rendererIndex, CategoryItemRendererState state) {
/* 184 */     CategoryAxis domainAxis = getDomainAxis(plot, rendererIndex);
/* 185 */     CategoryDataset dataset = plot.getDataset(rendererIndex);
/* 186 */     if (dataset != null) {
/* 187 */       int columns = dataset.getColumnCount();
/* 188 */       int rows = dataset.getRowCount();
/* 189 */       double space = 0.0D;
/* 190 */       PlotOrientation orientation = plot.getOrientation();
/* 191 */       if (orientation == PlotOrientation.HORIZONTAL) {
/* 192 */         space = dataArea.getHeight();
/* 194 */       } else if (orientation == PlotOrientation.VERTICAL) {
/* 195 */         space = dataArea.getWidth();
/*     */       } 
/* 197 */       double maxWidth = space * getMaxItemWidth();
/* 198 */       double categoryMargin = 0.0D;
/* 199 */       double currentItemMargin = 0.0D;
/* 200 */       if (columns > 1)
/* 201 */         categoryMargin = domainAxis.getCategoryMargin(); 
/* 203 */       if (rows > 1)
/* 204 */         currentItemMargin = getItemMargin(); 
/* 206 */       double used = space * (1.0D - domainAxis.getLowerMargin() - domainAxis.getUpperMargin() - categoryMargin - currentItemMargin);
/* 209 */       if (rows * columns > 0) {
/* 210 */         state.setBarWidth(Math.min(used / (rows * columns), maxWidth));
/*     */       } else {
/* 213 */         state.setBarWidth(Math.min(used, maxWidth));
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected double calculateBarW0(CategoryPlot plot, PlotOrientation orientation, Rectangle2D dataArea, CategoryAxis domainAxis, CategoryItemRendererState state, int row, int column) {
/* 241 */     double space = 0.0D;
/* 242 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 243 */       space = dataArea.getHeight();
/*     */     } else {
/* 246 */       space = dataArea.getWidth();
/*     */     } 
/* 248 */     double barW0 = domainAxis.getCategoryStart(column, getColumnCount(), dataArea, plot.getDomainAxisEdge());
/* 251 */     int seriesCount = getRowCount();
/* 252 */     int categoryCount = getColumnCount();
/* 253 */     if (seriesCount > 1) {
/* 254 */       double seriesGap = space * getItemMargin() / (categoryCount * (seriesCount - 1));
/* 256 */       double seriesW = calculateSeriesWidth(space, domainAxis, categoryCount, seriesCount);
/* 259 */       barW0 = barW0 + row * (seriesW + seriesGap) + seriesW / 2.0D - state.getBarWidth() / 2.0D;
/*     */     } else {
/* 263 */       barW0 = domainAxis.getCategoryMiddle(column, getColumnCount(), dataArea, plot.getDomainAxisEdge()) - state.getBarWidth() / 2.0D;
/*     */     } 
/* 269 */     return barW0;
/*     */   }
/*     */   
/*     */   public void drawItem(Graphics2D g2, CategoryItemRendererState state, Rectangle2D dataArea, CategoryPlot plot, CategoryAxis domainAxis, ValueAxis rangeAxis, CategoryDataset dataset, int row, int column, int pass) {
/* 298 */     Number dataValue = dataset.getValue(row, column);
/* 299 */     if (dataValue == null)
/*     */       return; 
/* 303 */     double value = dataValue.doubleValue();
/* 305 */     PlotOrientation orientation = plot.getOrientation();
/* 306 */     double barW0 = calculateBarW0(plot, orientation, dataArea, domainAxis, state, row, column);
/* 309 */     RectangleEdge edge = plot.getRangeAxisEdge();
/* 310 */     double barL = rangeAxis.valueToJava2D(value, dataArea, edge);
/* 313 */     Line2D line = null;
/* 314 */     double x = 0.0D;
/* 315 */     double y = 0.0D;
/* 316 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 317 */       x = barL;
/* 318 */       y = barW0 + state.getBarWidth() / 2.0D;
/* 319 */       line = new Line2D.Double(barL, barW0, barL, barW0 + state.getBarWidth());
/*     */     } else {
/* 324 */       x = barW0 + state.getBarWidth() / 2.0D;
/* 325 */       y = barL;
/* 326 */       line = new Line2D.Double(barW0, barL, barW0 + state.getBarWidth(), barL);
/*     */     } 
/* 330 */     Stroke itemStroke = getItemStroke(row, column);
/* 331 */     Paint itemPaint = getItemPaint(row, column);
/* 332 */     g2.setStroke(itemStroke);
/* 333 */     g2.setPaint(itemPaint);
/* 334 */     g2.draw(line);
/* 336 */     CategoryItemLabelGenerator generator = getItemLabelGenerator(row, column);
/* 338 */     if (generator != null && isItemLabelVisible(row, column))
/* 339 */       drawItemLabel(g2, orientation, dataset, row, column, x, y, (value < 0.0D)); 
/* 345 */     if (state.getInfo() != null) {
/* 346 */       EntityCollection entities = state.getEntityCollection();
/* 347 */       if (entities != null) {
/* 348 */         String tip = null;
/* 349 */         CategoryToolTipGenerator tipster = getToolTipGenerator(row, column);
/* 351 */         if (tipster != null)
/* 352 */           tip = tipster.generateToolTip(dataset, row, column); 
/* 354 */         String url = null;
/* 355 */         if (getItemURLGenerator(row, column) != null)
/* 356 */           url = getItemURLGenerator(row, column).generateURL(dataset, row, column); 
/* 360 */         CategoryItemEntity entity = new CategoryItemEntity(line.getBounds(), tip, url, dataset, row, dataset.getColumnKey(column), column);
/* 364 */         entities.add((ChartEntity)entity);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected double calculateSeriesWidth(double space, CategoryAxis axis, int categories, int series) {
/* 383 */     double factor = 1.0D - getItemMargin() - axis.getLowerMargin() - axis.getUpperMargin();
/* 385 */     if (categories > 1)
/* 386 */       factor -= axis.getCategoryMargin(); 
/* 388 */     return space * factor / (categories * series);
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 399 */     if (obj == this)
/* 400 */       return true; 
/* 402 */     if (!(obj instanceof LevelRenderer))
/* 403 */       return false; 
/* 405 */     if (!super.equals(obj))
/* 406 */       return false; 
/* 408 */     LevelRenderer that = (LevelRenderer)obj;
/* 409 */     if (this.itemMargin != that.itemMargin)
/* 410 */       return false; 
/* 412 */     if (this.maxItemWidth != that.maxItemWidth)
/* 413 */       return false; 
/* 415 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\renderer\category\LevelRenderer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */