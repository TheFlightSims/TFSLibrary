/*     */ package org.jfree.chart.renderer.category;
/*     */ 
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
/*     */ import org.jfree.data.category.IntervalCategoryDataset;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ public class IntervalBarRenderer extends BarRenderer implements CategoryItemRenderer, Cloneable, PublicCloneable, Serializable {
/*     */   private static final long serialVersionUID = -5068857361615528725L;
/*     */   
/*     */   public void drawItem(Graphics2D g2, CategoryItemRendererState state, Rectangle2D dataArea, CategoryPlot plot, CategoryAxis domainAxis, ValueAxis rangeAxis, CategoryDataset dataset, int row, int column, int pass) {
/* 132 */     if (dataset instanceof IntervalCategoryDataset) {
/* 133 */       IntervalCategoryDataset d = (IntervalCategoryDataset)dataset;
/* 134 */       drawInterval(g2, state, dataArea, plot, domainAxis, rangeAxis, d, row, column);
/*     */     } else {
/* 140 */       super.drawItem(g2, state, dataArea, plot, domainAxis, rangeAxis, dataset, row, column, pass);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void drawInterval(Graphics2D g2, CategoryItemRendererState state, Rectangle2D dataArea, CategoryPlot plot, CategoryAxis domainAxis, ValueAxis rangeAxis, IntervalCategoryDataset dataset, int row, int column) {
/* 171 */     int seriesCount = getRowCount();
/* 172 */     int categoryCount = getColumnCount();
/* 174 */     PlotOrientation orientation = plot.getOrientation();
/* 176 */     double rectX = 0.0D;
/* 177 */     double rectY = 0.0D;
/* 179 */     RectangleEdge domainAxisLocation = plot.getDomainAxisEdge();
/* 180 */     RectangleEdge rangeAxisLocation = plot.getRangeAxisEdge();
/* 183 */     Number value0 = dataset.getEndValue(row, column);
/* 184 */     if (value0 == null)
/*     */       return; 
/* 187 */     double java2dValue0 = rangeAxis.valueToJava2D(value0.doubleValue(), dataArea, rangeAxisLocation);
/* 192 */     Number value1 = dataset.getStartValue(row, column);
/* 193 */     if (value1 == null)
/*     */       return; 
/* 196 */     double java2dValue1 = rangeAxis.valueToJava2D(value1.doubleValue(), dataArea, rangeAxisLocation);
/* 200 */     if (java2dValue1 < java2dValue0) {
/* 201 */       double temp = java2dValue1;
/* 202 */       java2dValue1 = java2dValue0;
/* 203 */       java2dValue0 = temp;
/* 204 */       Number tempNum = value1;
/* 205 */       value1 = value0;
/* 206 */       value0 = tempNum;
/*     */     } 
/* 210 */     double rectWidth = state.getBarWidth();
/* 213 */     double rectHeight = Math.abs(java2dValue1 - java2dValue0);
/* 215 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 217 */       rectY = domainAxis.getCategoryStart(column, getColumnCount(), dataArea, domainAxisLocation);
/* 220 */       if (seriesCount > 1) {
/* 221 */         double seriesGap = dataArea.getHeight() * getItemMargin() / (categoryCount * (seriesCount - 1));
/* 223 */         rectY += row * (state.getBarWidth() + seriesGap);
/*     */       } else {
/* 226 */         rectY += row * state.getBarWidth();
/*     */       } 
/* 229 */       rectX = java2dValue0;
/* 231 */       rectHeight = state.getBarWidth();
/* 232 */       rectWidth = Math.abs(java2dValue1 - java2dValue0);
/* 235 */     } else if (orientation == PlotOrientation.VERTICAL) {
/* 237 */       rectX = domainAxis.getCategoryStart(column, getColumnCount(), dataArea, domainAxisLocation);
/* 241 */       if (seriesCount > 1) {
/* 242 */         double seriesGap = dataArea.getWidth() * getItemMargin() / (categoryCount * (seriesCount - 1));
/* 244 */         rectX += row * (state.getBarWidth() + seriesGap);
/*     */       } else {
/* 247 */         rectX += row * state.getBarWidth();
/*     */       } 
/* 250 */       rectY = java2dValue0;
/*     */     } 
/* 253 */     Rectangle2D bar = new Rectangle2D.Double(rectX, rectY, rectWidth, rectHeight);
/* 256 */     Paint seriesPaint = getItemPaint(row, column);
/* 257 */     g2.setPaint(seriesPaint);
/* 258 */     g2.fill(bar);
/* 261 */     if (state.getBarWidth() > 3.0D) {
/* 262 */       Stroke stroke = getItemOutlineStroke(row, column);
/* 263 */       Paint paint = getItemOutlinePaint(row, column);
/* 264 */       if (stroke != null && paint != null) {
/* 265 */         g2.setStroke(stroke);
/* 266 */         g2.setPaint(paint);
/* 267 */         g2.draw(bar);
/*     */       } 
/*     */     } 
/* 271 */     CategoryItemLabelGenerator generator = getItemLabelGenerator(row, column);
/* 273 */     if (generator != null && isItemLabelVisible(row, column))
/* 274 */       drawItemLabel(g2, (CategoryDataset)dataset, row, column, plot, generator, bar, false); 
/* 280 */     if (state.getInfo() != null) {
/* 281 */       EntityCollection entities = state.getEntityCollection();
/* 282 */       if (entities != null) {
/* 283 */         String tip = null;
/* 284 */         CategoryToolTipGenerator tipster = getToolTipGenerator(row, column);
/* 286 */         if (tipster != null)
/* 287 */           tip = tipster.generateToolTip((CategoryDataset)dataset, row, column); 
/* 289 */         String url = null;
/* 290 */         if (getItemURLGenerator(row, column) != null)
/* 291 */           url = getItemURLGenerator(row, column).generateURL((CategoryDataset)dataset, row, column); 
/* 295 */         CategoryItemEntity entity = new CategoryItemEntity(bar, tip, url, (CategoryDataset)dataset, row, dataset.getColumnKey(column), column);
/* 299 */         entities.add((ChartEntity)entity);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\renderer\category\IntervalBarRenderer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */