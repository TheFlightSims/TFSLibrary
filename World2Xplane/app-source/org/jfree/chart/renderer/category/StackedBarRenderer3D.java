/*     */ package org.jfree.chart.renderer.category;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.axis.CategoryAxis;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.entity.EntityCollection;
/*     */ import org.jfree.chart.labels.CategoryItemLabelGenerator;
/*     */ import org.jfree.chart.plot.CategoryPlot;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.data.category.CategoryDataset;
/*     */ import org.jfree.data.general.DatasetUtilities;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ public class StackedBarRenderer3D extends BarRenderer3D implements Cloneable, PublicCloneable, Serializable {
/*     */   private static final long serialVersionUID = -5832945916493247123L;
/*     */   
/*     */   public StackedBarRenderer3D() {}
/*     */   
/*     */   public StackedBarRenderer3D(double xOffset, double yOffset) {
/* 128 */     super(xOffset, yOffset);
/*     */   }
/*     */   
/*     */   public Range findRangeBounds(CategoryDataset dataset) {
/* 140 */     return DatasetUtilities.findStackedRangeBounds(dataset);
/*     */   }
/*     */   
/*     */   protected void calculateBarWidth(CategoryPlot plot, Rectangle2D dataArea, int rendererIndex, CategoryItemRendererState state) {
/* 157 */     CategoryAxis domainAxis = getDomainAxis(plot, rendererIndex);
/* 158 */     CategoryDataset data = plot.getDataset(rendererIndex);
/* 159 */     if (data != null) {
/* 160 */       PlotOrientation orientation = plot.getOrientation();
/* 161 */       double space = 0.0D;
/* 162 */       if (orientation == PlotOrientation.HORIZONTAL) {
/* 163 */         space = dataArea.getHeight();
/* 165 */       } else if (orientation == PlotOrientation.VERTICAL) {
/* 166 */         space = dataArea.getWidth();
/*     */       } 
/* 168 */       double maxWidth = space * getMaximumBarWidth();
/* 169 */       int columns = data.getColumnCount();
/* 170 */       double categoryMargin = 0.0D;
/* 171 */       if (columns > 1)
/* 172 */         categoryMargin = domainAxis.getCategoryMargin(); 
/* 175 */       double used = space * (1.0D - domainAxis.getLowerMargin() - domainAxis.getUpperMargin() - categoryMargin);
/* 178 */       if (columns > 0) {
/* 179 */         state.setBarWidth(Math.min(used / columns, maxWidth));
/*     */       } else {
/* 182 */         state.setBarWidth(Math.min(used, maxWidth));
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void drawItem(Graphics2D g2, CategoryItemRendererState state, Rectangle2D dataArea, CategoryPlot plot, CategoryAxis domainAxis, ValueAxis rangeAxis, CategoryDataset dataset, int row, int column, int pass) {
/*     */     double translatedBase, translatedValue;
/* 214 */     Number dataValue = dataset.getValue(row, column);
/* 215 */     if (dataValue == null)
/*     */       return; 
/* 219 */     double value = dataValue.doubleValue();
/* 221 */     Rectangle2D adjusted = new Rectangle2D.Double(dataArea.getX(), dataArea.getY() + getYOffset(), dataArea.getWidth() - getXOffset(), dataArea.getHeight() - getYOffset());
/* 227 */     PlotOrientation orientation = plot.getOrientation();
/* 229 */     double barW0 = domainAxis.getCategoryMiddle(column, getColumnCount(), adjusted, plot.getDomainAxisEdge()) - state.getBarWidth() / 2.0D;
/* 233 */     double positiveBase = getBase();
/* 234 */     double negativeBase = positiveBase;
/* 235 */     for (int i = 0; i < row; i++) {
/* 236 */       Number v = dataset.getValue(i, column);
/* 237 */       if (v != null) {
/* 238 */         double d = v.doubleValue();
/* 239 */         if (d > 0.0D) {
/* 240 */           positiveBase += d;
/*     */         } else {
/* 243 */           negativeBase += d;
/*     */         } 
/*     */       } 
/*     */     } 
/* 250 */     RectangleEdge location = plot.getRangeAxisEdge();
/* 251 */     if (value > 0.0D) {
/* 252 */       translatedBase = rangeAxis.valueToJava2D(positiveBase, adjusted, location);
/* 254 */       translatedValue = rangeAxis.valueToJava2D(positiveBase + value, adjusted, location);
/*     */     } else {
/* 258 */       translatedBase = rangeAxis.valueToJava2D(negativeBase, adjusted, location);
/* 260 */       translatedValue = rangeAxis.valueToJava2D(negativeBase + value, adjusted, location);
/*     */     } 
/* 263 */     double barL0 = Math.min(translatedBase, translatedValue);
/* 264 */     double barLength = Math.max(Math.abs(translatedValue - translatedBase), getMinimumBarLength());
/* 268 */     Rectangle2D bar = null;
/* 269 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 270 */       bar = new Rectangle2D.Double(barL0, barW0, barLength, state.getBarWidth());
/*     */     } else {
/* 274 */       bar = new Rectangle2D.Double(barW0, barL0, state.getBarWidth(), barLength);
/*     */     } 
/* 277 */     Paint itemPaint = getItemPaint(row, column);
/* 278 */     g2.setPaint(itemPaint);
/* 279 */     g2.fill(bar);
/* 281 */     if (pass == 0) {
/* 282 */       double x0 = bar.getMinX();
/* 283 */       double x1 = x0 + getXOffset();
/* 284 */       double x2 = bar.getMaxX();
/* 285 */       double x3 = x2 + getXOffset();
/* 287 */       double y0 = bar.getMinY() - getYOffset();
/* 288 */       double y1 = bar.getMinY();
/* 289 */       double y2 = bar.getMaxY() - getYOffset();
/* 290 */       double y3 = bar.getMaxY();
/* 292 */       GeneralPath bar3dRight = null;
/* 293 */       GeneralPath bar3dTop = null;
/* 294 */       if (value > 0.0D || orientation == PlotOrientation.VERTICAL) {
/* 295 */         bar3dRight = new GeneralPath();
/* 296 */         bar3dRight.moveTo((float)x2, (float)y3);
/* 297 */         bar3dRight.lineTo((float)x2, (float)y1);
/* 298 */         bar3dRight.lineTo((float)x3, (float)y0);
/* 299 */         bar3dRight.lineTo((float)x3, (float)y2);
/* 300 */         bar3dRight.closePath();
/* 302 */         if (itemPaint instanceof Color)
/* 303 */           g2.setPaint(((Color)itemPaint).darker()); 
/* 305 */         g2.fill(bar3dRight);
/*     */       } 
/* 308 */       if (value > 0.0D || orientation == PlotOrientation.HORIZONTAL) {
/* 309 */         bar3dTop = new GeneralPath();
/* 310 */         bar3dTop.moveTo((float)x0, (float)y1);
/* 311 */         bar3dTop.lineTo((float)x1, (float)y0);
/* 312 */         bar3dTop.lineTo((float)x3, (float)y0);
/* 313 */         bar3dTop.lineTo((float)x2, (float)y1);
/* 314 */         bar3dTop.closePath();
/* 315 */         g2.fill(bar3dTop);
/*     */       } 
/* 318 */       if (isDrawBarOutline() && state.getBarWidth() > 3.0D) {
/* 319 */         g2.setStroke(getItemOutlineStroke(row, column));
/* 320 */         g2.setPaint(getItemOutlinePaint(row, column));
/* 321 */         g2.draw(bar);
/* 322 */         if (bar3dRight != null)
/* 323 */           g2.draw(bar3dRight); 
/* 325 */         if (bar3dTop != null)
/* 326 */           g2.draw(bar3dTop); 
/*     */       } 
/* 331 */       EntityCollection entities = state.getEntityCollection();
/* 332 */       if (entities != null)
/* 333 */         addItemEntity(entities, dataset, row, column, bar); 
/* 336 */     } else if (pass == 1) {
/* 337 */       CategoryItemLabelGenerator generator = getItemLabelGenerator(row, column);
/* 339 */       if (generator != null && isItemLabelVisible(row, column))
/* 340 */         drawItemLabel(g2, dataset, row, column, plot, generator, bar, (value < 0.0D)); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getPassCount() {
/* 357 */     return 2;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\renderer\category\StackedBarRenderer3D.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */