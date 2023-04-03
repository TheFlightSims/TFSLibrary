/*     */ package org.jfree.chart.renderer.category;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.Line2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.axis.CategoryAxis;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.event.RendererChangeEvent;
/*     */ import org.jfree.chart.plot.CategoryPlot;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.data.category.CategoryDataset;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ public class CategoryStepRenderer extends AbstractCategoryItemRenderer implements Cloneable, PublicCloneable, Serializable {
/*     */   private static final long serialVersionUID = -5121079703118261470L;
/*     */   
/*     */   public static final int STAGGER_WIDTH = 5;
/*     */   
/*     */   private boolean stagger = false;
/*     */   
/*  86 */   private transient Line2D line = new Line2D.Double(0.0D, 0.0D, 0.0D, 0.0D);
/*     */   
/*     */   public CategoryStepRenderer() {
/*  92 */     this(false);
/*     */   }
/*     */   
/*     */   public CategoryStepRenderer(boolean stagger) {
/* 102 */     this.stagger = stagger;
/*     */   }
/*     */   
/*     */   public boolean getStagger() {
/* 111 */     return this.stagger;
/*     */   }
/*     */   
/*     */   public void setStagger(boolean shouldStagger) {
/* 122 */     this.stagger = shouldStagger;
/* 123 */     notifyListeners(new RendererChangeEvent(this));
/*     */   }
/*     */   
/*     */   protected void drawLine(Graphics2D g2, PlotOrientation orientation, double x0, double y0, double x1, double y1) {
/* 139 */     if (orientation == PlotOrientation.VERTICAL) {
/* 140 */       this.line.setLine(x0, y0, x1, y1);
/* 141 */       g2.draw(this.line);
/* 143 */     } else if (orientation == PlotOrientation.HORIZONTAL) {
/* 144 */       this.line.setLine(y0, x0, y1, x1);
/* 145 */       g2.draw(this.line);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void drawItem(Graphics2D g2, CategoryItemRendererState state, Rectangle2D dataArea, CategoryPlot plot, CategoryAxis domainAxis, ValueAxis rangeAxis, CategoryDataset dataset, int row, int column, int pass) {
/* 175 */     Number value = dataset.getValue(row, column);
/* 176 */     if (value == null)
/*     */       return; 
/* 179 */     PlotOrientation orientation = plot.getOrientation();
/* 182 */     double x1s = domainAxis.getCategoryStart(column, getColumnCount(), dataArea, plot.getDomainAxisEdge());
/* 185 */     double x1 = domainAxis.getCategoryMiddle(column, getColumnCount(), dataArea, plot.getDomainAxisEdge());
/* 188 */     double x1e = 2.0D * x1 - x1s;
/* 189 */     double y1 = rangeAxis.valueToJava2D(value.doubleValue(), dataArea, plot.getRangeAxisEdge());
/* 192 */     g2.setPaint(getItemPaint(row, column));
/* 193 */     g2.setStroke(getItemStroke(row, column));
/* 195 */     if (column != 0) {
/* 196 */       Number previousValue = dataset.getValue(row, column - 1);
/* 197 */       if (previousValue != null) {
/* 199 */         double previous = previousValue.doubleValue();
/* 200 */         double x0s = domainAxis.getCategoryStart(column - 1, getColumnCount(), dataArea, plot.getDomainAxisEdge());
/* 204 */         double x0 = domainAxis.getCategoryMiddle(column - 1, getColumnCount(), dataArea, plot.getDomainAxisEdge());
/* 208 */         double x0e = 2.0D * x0 - x0s;
/* 209 */         double y0 = rangeAxis.valueToJava2D(previous, dataArea, plot.getRangeAxisEdge());
/* 212 */         if (getStagger()) {
/* 213 */           int xStagger = row * 5;
/* 214 */           if (xStagger > x1s - x0e)
/* 215 */             xStagger = (int)(x1s - x0e); 
/* 217 */           x1s = x0e + xStagger;
/*     */         } 
/* 219 */         drawLine(g2, orientation, x0e, y0, x1s, y0);
/* 222 */         drawLine(g2, orientation, x1s, y0, x1s, y1);
/*     */       } 
/*     */     } 
/* 225 */     drawLine(g2, orientation, x1s, y1, x1e, y1);
/* 228 */     if (isItemLabelVisible(row, column))
/* 229 */       drawItemLabel(g2, orientation, dataset, row, column, x1, y1, (value.doubleValue() < 0.0D)); 
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 272 */     if (obj == this)
/* 273 */       return true; 
/* 275 */     if (!(obj instanceof CategoryStepRenderer))
/* 276 */       return false; 
/* 278 */     if (!super.equals(obj))
/* 279 */       return false; 
/* 281 */     CategoryStepRenderer that = (CategoryStepRenderer)obj;
/* 282 */     if (this.stagger != that.stagger)
/* 283 */       return false; 
/* 285 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\renderer\category\CategoryStepRenderer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */