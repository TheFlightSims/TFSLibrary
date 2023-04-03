/*     */ package org.jfree.chart.renderer.category;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.Line2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.axis.CategoryAxis;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.entity.CategoryItemEntity;
/*     */ import org.jfree.chart.entity.ChartEntity;
/*     */ import org.jfree.chart.entity.EntityCollection;
/*     */ import org.jfree.chart.event.RendererChangeEvent;
/*     */ import org.jfree.chart.labels.CategoryToolTipGenerator;
/*     */ import org.jfree.chart.plot.CategoryPlot;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.data.category.CategoryDataset;
/*     */ import org.jfree.data.statistics.StatisticalCategoryDataset;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.util.PaintUtilities;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ import org.jfree.util.ShapeUtilities;
/*     */ 
/*     */ public class StatisticalLineAndShapeRenderer extends LineAndShapeRenderer implements Cloneable, PublicCloneable, Serializable {
/*     */   private static final long serialVersionUID = -3557517173697777579L;
/*     */   
/*     */   private transient Paint errorIndicatorPaint;
/*     */   
/*     */   public StatisticalLineAndShapeRenderer() {
/*  91 */     this(true, true);
/*     */   }
/*     */   
/*     */   public StatisticalLineAndShapeRenderer(boolean linesVisible, boolean shapesVisible) {
/* 102 */     super(true, true);
/* 103 */     this.errorIndicatorPaint = null;
/*     */   }
/*     */   
/*     */   public Paint getErrorIndicatorPaint() {
/* 113 */     return this.errorIndicatorPaint;
/*     */   }
/*     */   
/*     */   public void setErrorIndicatorPaint(Paint paint) {
/* 123 */     this.errorIndicatorPaint = paint;
/* 124 */     notifyListeners(new RendererChangeEvent(this));
/*     */   }
/*     */   
/*     */   public void drawItem(Graphics2D g2, CategoryItemRendererState state, Rectangle2D dataArea, CategoryPlot plot, CategoryAxis domainAxis, ValueAxis rangeAxis, CategoryDataset dataset, int row, int column, int pass) {
/*     */     double highVal, lowVal;
/* 153 */     Number v = dataset.getValue(row, column);
/* 154 */     if (v == null)
/*     */       return; 
/* 158 */     StatisticalCategoryDataset statData = (StatisticalCategoryDataset)dataset;
/* 161 */     Number meanValue = statData.getMeanValue(row, column);
/* 163 */     PlotOrientation orientation = plot.getOrientation();
/* 166 */     double x1 = domainAxis.getCategoryMiddle(column, getColumnCount(), dataArea, plot.getDomainAxisEdge());
/* 169 */     double y1 = rangeAxis.valueToJava2D(meanValue.doubleValue(), dataArea, plot.getRangeAxisEdge());
/* 172 */     Shape shape = getItemShape(row, column);
/* 173 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 174 */       shape = ShapeUtilities.createTranslatedShape(shape, y1, x1);
/* 176 */     } else if (orientation == PlotOrientation.VERTICAL) {
/* 177 */       shape = ShapeUtilities.createTranslatedShape(shape, x1, y1);
/*     */     } 
/* 179 */     if (getItemShapeVisible(row, column))
/* 181 */       if (getItemShapeFilled(row, column)) {
/* 182 */         g2.setPaint(getItemPaint(row, column));
/* 183 */         g2.fill(shape);
/*     */       } else {
/* 186 */         if (getUseOutlinePaint()) {
/* 187 */           g2.setPaint(getItemOutlinePaint(row, column));
/*     */         } else {
/* 190 */           g2.setPaint(getItemPaint(row, column));
/*     */         } 
/* 192 */         g2.setStroke(getItemOutlineStroke(row, column));
/* 193 */         g2.draw(shape);
/*     */       }  
/* 197 */     if (getItemLineVisible(row, column) && 
/* 198 */       column != 0) {
/* 200 */       Number previousValue = statData.getValue(row, column - 1);
/* 201 */       if (previousValue != null) {
/* 204 */         double previous = previousValue.doubleValue();
/* 205 */         double x0 = domainAxis.getCategoryMiddle(column - 1, getColumnCount(), dataArea, plot.getDomainAxisEdge());
/* 208 */         double y0 = rangeAxis.valueToJava2D(previous, dataArea, plot.getRangeAxisEdge());
/* 211 */         Line2D line2D = null;
/* 212 */         if (orientation == PlotOrientation.HORIZONTAL) {
/* 213 */           line2D = new Line2D.Double(y0, x0, y1, x1);
/* 215 */         } else if (orientation == PlotOrientation.VERTICAL) {
/* 216 */           line2D = new Line2D.Double(x0, y0, x1, y1);
/*     */         } 
/* 218 */         g2.setPaint(getItemPaint(row, column));
/* 219 */         g2.setStroke(getItemStroke(row, column));
/* 220 */         g2.draw(line2D);
/*     */       } 
/*     */     } 
/* 225 */     RectangleEdge yAxisLocation = plot.getRangeAxisEdge();
/* 226 */     RectangleEdge xAxisLocation = plot.getDomainAxisEdge();
/* 227 */     double rectX = domainAxis.getCategoryStart(column, getColumnCount(), dataArea, xAxisLocation);
/* 230 */     rectX += row * state.getBarWidth();
/* 232 */     g2.setPaint(getItemPaint(row, column));
/* 234 */     double valueDelta = statData.getStdDevValue(row, column).doubleValue();
/* 237 */     if (meanValue.doubleValue() + valueDelta > rangeAxis.getRange().getUpperBound()) {
/* 239 */       highVal = rangeAxis.valueToJava2D(rangeAxis.getRange().getUpperBound(), dataArea, yAxisLocation);
/*     */     } else {
/* 244 */       highVal = rangeAxis.valueToJava2D(meanValue.doubleValue() + valueDelta, dataArea, yAxisLocation);
/*     */     } 
/* 248 */     if (meanValue.doubleValue() + valueDelta < rangeAxis.getRange().getLowerBound()) {
/* 250 */       lowVal = rangeAxis.valueToJava2D(rangeAxis.getRange().getLowerBound(), dataArea, yAxisLocation);
/*     */     } else {
/* 255 */       lowVal = rangeAxis.valueToJava2D(meanValue.doubleValue() - valueDelta, dataArea, yAxisLocation);
/*     */     } 
/* 259 */     if (this.errorIndicatorPaint != null) {
/* 260 */       g2.setPaint(this.errorIndicatorPaint);
/*     */     } else {
/* 263 */       g2.setPaint(getItemPaint(row, column));
/*     */     } 
/* 265 */     Line2D line = null;
/* 266 */     line = new Line2D.Double(x1, lowVal, x1, highVal);
/* 267 */     g2.draw(line);
/* 268 */     line = new Line2D.Double(x1 - 5.0D, highVal, x1 + 5.0D, highVal);
/* 269 */     g2.draw(line);
/* 270 */     line = new Line2D.Double(x1 - 5.0D, lowVal, x1 + 5.0D, lowVal);
/* 271 */     g2.draw(line);
/* 274 */     if (isItemLabelVisible(row, column))
/* 275 */       if (orientation == PlotOrientation.HORIZONTAL) {
/* 276 */         drawItemLabel(g2, orientation, dataset, row, column, y1, x1, (meanValue.doubleValue() < 0.0D));
/* 279 */       } else if (orientation == PlotOrientation.VERTICAL) {
/* 280 */         drawItemLabel(g2, orientation, dataset, row, column, x1, y1, (meanValue.doubleValue() < 0.0D));
/*     */       }  
/* 286 */     if (state.getInfo() != null) {
/* 287 */       EntityCollection entities = state.getEntityCollection();
/* 288 */       if (entities != null && shape != null) {
/* 289 */         String tip = null;
/* 290 */         CategoryToolTipGenerator tipster = getToolTipGenerator(row, column);
/* 292 */         if (tipster != null)
/* 293 */           tip = tipster.generateToolTip(dataset, row, column); 
/* 295 */         String url = null;
/* 296 */         if (getItemURLGenerator(row, column) != null)
/* 297 */           url = getItemURLGenerator(row, column).generateURL(dataset, row, column); 
/* 300 */         CategoryItemEntity entity = new CategoryItemEntity(shape, tip, url, dataset, row, dataset.getColumnKey(column), column);
/* 303 */         entities.add((ChartEntity)entity);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 319 */     if (obj == this)
/* 320 */       return true; 
/* 322 */     if (!(obj instanceof StatisticalLineAndShapeRenderer))
/* 323 */       return false; 
/* 325 */     if (!super.equals(obj))
/* 326 */       return false; 
/* 328 */     StatisticalLineAndShapeRenderer that = (StatisticalLineAndShapeRenderer)obj;
/* 330 */     if (!PaintUtilities.equal(this.errorIndicatorPaint, that.errorIndicatorPaint))
/* 332 */       return false; 
/* 334 */     return true;
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream stream) throws IOException {
/* 345 */     stream.defaultWriteObject();
/* 346 */     SerialUtilities.writePaint(this.errorIndicatorPaint, stream);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
/* 359 */     stream.defaultReadObject();
/* 360 */     this.errorIndicatorPaint = SerialUtilities.readPaint(stream);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\renderer\category\StatisticalLineAndShapeRenderer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */