/*     */ package org.jfree.chart.renderer.category;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Polygon;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.axis.CategoryAxis;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.entity.EntityCollection;
/*     */ import org.jfree.chart.plot.CategoryPlot;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.data.category.CategoryDataset;
/*     */ import org.jfree.data.general.DatasetUtilities;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ public class StackedAreaRenderer extends AreaRenderer implements Cloneable, PublicCloneable, Serializable {
/*     */   private static final long serialVersionUID = -3595635038460823663L;
/*     */   
/*     */   public Range findRangeBounds(CategoryDataset dataset) {
/* 110 */     return DatasetUtilities.findStackedRangeBounds(dataset);
/*     */   }
/*     */   
/*     */   public void drawItem(Graphics2D g2, CategoryItemRendererState state, Rectangle2D dataArea, CategoryPlot plot, CategoryAxis domainAxis, ValueAxis rangeAxis, CategoryDataset dataset, int row, int column, int pass) {
/* 139 */     Number value = dataset.getValue(row, column);
/* 140 */     if (value == null)
/*     */       return; 
/* 147 */     double x1 = domainAxis.getCategoryMiddle(column, getColumnCount(), dataArea, plot.getDomainAxisEdge());
/* 150 */     double y1 = 0.0D;
/* 151 */     double y1Untranslated = value.doubleValue();
/* 153 */     g2.setPaint(getItemPaint(row, column));
/* 154 */     g2.setStroke(getItemStroke(row, column));
/* 156 */     if (column != 0) {
/* 158 */       Number previousValue = dataset.getValue(row, column - 1);
/* 159 */       if (previousValue != null) {
/* 161 */         double x0 = domainAxis.getCategoryMiddle(column - 1, getColumnCount(), dataArea, plot.getDomainAxisEdge());
/* 165 */         double y0Untranslated = previousValue.doubleValue();
/* 169 */         double previousHeightx0Untranslated = getPreviousHeight(dataset, row, column - 1);
/* 171 */         double previousHeightx1Untranslated = getPreviousHeight(dataset, row, column);
/* 175 */         y0Untranslated += previousHeightx0Untranslated;
/* 176 */         y1Untranslated += previousHeightx1Untranslated;
/* 179 */         RectangleEdge location = plot.getRangeAxisEdge();
/* 180 */         double previousHeightx0 = rangeAxis.valueToJava2D(previousHeightx0Untranslated, dataArea, location);
/* 183 */         double previousHeightx1 = rangeAxis.valueToJava2D(previousHeightx1Untranslated, dataArea, location);
/* 188 */         double y0 = rangeAxis.valueToJava2D(y0Untranslated, dataArea, location);
/* 191 */         y1 = rangeAxis.valueToJava2D(y1Untranslated, dataArea, location);
/* 195 */         Polygon p = null;
/* 196 */         PlotOrientation orientation = plot.getOrientation();
/* 197 */         if (orientation == PlotOrientation.HORIZONTAL) {
/* 198 */           p = new Polygon();
/* 199 */           p.addPoint((int)y0, (int)x0);
/* 200 */           p.addPoint((int)y1, (int)x1);
/* 201 */           p.addPoint((int)previousHeightx1, (int)x1);
/* 202 */           p.addPoint((int)previousHeightx0, (int)x0);
/* 204 */         } else if (orientation == PlotOrientation.VERTICAL) {
/* 205 */           p = new Polygon();
/* 206 */           p.addPoint((int)x0, (int)y0);
/* 207 */           p.addPoint((int)x1, (int)y1);
/* 208 */           p.addPoint((int)x1, (int)previousHeightx1);
/* 209 */           p.addPoint((int)x0, (int)previousHeightx0);
/*     */         } 
/* 211 */         g2.setPaint(getItemPaint(row, column));
/* 212 */         g2.setStroke(getItemStroke(row, column));
/* 213 */         g2.fill(p);
/*     */       } 
/*     */     } 
/* 219 */     EntityCollection entities = state.getEntityCollection();
/* 220 */     if (entities != null) {
/* 221 */       Shape shape = new Rectangle2D.Double(x1 - 3.0D, y1 - 3.0D, 6.0D, 6.0D);
/* 222 */       addItemEntity(entities, dataset, row, column, shape);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected double getPreviousHeight(CategoryDataset data, int series, int category) {
/* 243 */     double result = 0.0D;
/* 245 */     for (int i = 0; i < series; i++) {
/* 246 */       Number tmp = data.getValue(i, category);
/* 247 */       if (tmp != null)
/* 248 */         result += tmp.doubleValue(); 
/*     */     } 
/* 251 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\renderer\category\StackedAreaRenderer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */