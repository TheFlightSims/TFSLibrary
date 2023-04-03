/*     */ package org.jfree.chart.renderer.category;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Shape;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.LegendItem;
/*     */ import org.jfree.chart.axis.CategoryAxis;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.entity.EntityCollection;
/*     */ import org.jfree.chart.event.RendererChangeEvent;
/*     */ import org.jfree.chart.plot.CategoryPlot;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.chart.renderer.AreaRendererEndType;
/*     */ import org.jfree.data.category.CategoryDataset;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ public class AreaRenderer extends AbstractCategoryItemRenderer implements Cloneable, PublicCloneable, Serializable {
/*     */   private static final long serialVersionUID = -4231878281385812757L;
/*     */   
/* 108 */   private AreaRendererEndType endType = AreaRendererEndType.TAPER;
/*     */   
/*     */   public AreaRendererEndType getEndType() {
/* 117 */     return this.endType;
/*     */   }
/*     */   
/*     */   public void setEndType(AreaRendererEndType type) {
/* 127 */     if (type == null)
/* 128 */       throw new IllegalArgumentException("Null 'type' argument."); 
/* 130 */     this.endType = type;
/* 131 */     notifyListeners(new RendererChangeEvent(this));
/*     */   }
/*     */   
/*     */   public LegendItem getLegendItem(int datasetIndex, int series) {
/* 144 */     CategoryPlot cp = getPlot();
/* 145 */     if (cp == null)
/* 146 */       return null; 
/* 150 */     CategoryDataset dataset = cp.getDataset(datasetIndex);
/* 151 */     String label = getLegendItemLabelGenerator().generateLabel(dataset, series);
/* 154 */     String description = label;
/* 155 */     String toolTipText = null;
/* 156 */     if (getLegendItemToolTipGenerator() != null)
/* 157 */       toolTipText = getLegendItemToolTipGenerator().generateLabel(dataset, series); 
/* 161 */     String urlText = null;
/* 162 */     if (getLegendItemURLGenerator() != null)
/* 163 */       urlText = getLegendItemURLGenerator().generateLabel(dataset, series); 
/* 167 */     Shape shape = new Rectangle2D.Double(-4.0D, -4.0D, 8.0D, 8.0D);
/* 168 */     Paint paint = getSeriesPaint(series);
/* 169 */     Paint outlinePaint = getSeriesOutlinePaint(series);
/* 170 */     Stroke outlineStroke = getSeriesOutlineStroke(series);
/* 172 */     return new LegendItem(label, description, toolTipText, urlText, shape, paint, outlineStroke, outlinePaint);
/*     */   }
/*     */   
/*     */   public void drawItem(Graphics2D g2, CategoryItemRendererState state, Rectangle2D dataArea, CategoryPlot plot, CategoryAxis domainAxis, ValueAxis rangeAxis, CategoryDataset dataset, int row, int column, int pass) {
/* 203 */     Number value = dataset.getValue(row, column);
/* 204 */     if (value != null) {
/* 205 */       PlotOrientation orientation = plot.getOrientation();
/* 206 */       RectangleEdge axisEdge = plot.getDomainAxisEdge();
/* 207 */       int count = dataset.getColumnCount();
/* 208 */       float x0 = (float)domainAxis.getCategoryStart(column, count, dataArea, axisEdge);
/* 211 */       float x1 = (float)domainAxis.getCategoryMiddle(column, count, dataArea, axisEdge);
/* 214 */       float x2 = (float)domainAxis.getCategoryEnd(column, count, dataArea, axisEdge);
/* 218 */       x0 = Math.round(x0);
/* 219 */       x1 = Math.round(x1);
/* 220 */       x2 = Math.round(x2);
/* 222 */       if (this.endType == AreaRendererEndType.TRUNCATE)
/* 223 */         if (column == 0) {
/* 224 */           x0 = x1;
/* 226 */         } else if (column == getColumnCount() - 1) {
/* 227 */           x2 = x1;
/*     */         }  
/* 231 */       double yy1 = value.doubleValue();
/* 233 */       double yy0 = 0.0D;
/* 234 */       if (column > 0) {
/* 235 */         Number n0 = dataset.getValue(row, column - 1);
/* 236 */         if (n0 != null)
/* 237 */           yy0 = (n0.doubleValue() + yy1) / 2.0D; 
/*     */       } 
/* 241 */       double yy2 = 0.0D;
/* 242 */       if (column < dataset.getColumnCount() - 1) {
/* 243 */         Number n2 = dataset.getValue(row, column + 1);
/* 244 */         if (n2 != null)
/* 245 */           yy2 = (n2.doubleValue() + yy1) / 2.0D; 
/*     */       } 
/* 249 */       RectangleEdge edge = plot.getRangeAxisEdge();
/* 250 */       float y0 = (float)rangeAxis.valueToJava2D(yy0, dataArea, edge);
/* 251 */       float y1 = (float)rangeAxis.valueToJava2D(yy1, dataArea, edge);
/* 252 */       float y2 = (float)rangeAxis.valueToJava2D(yy2, dataArea, edge);
/* 253 */       float yz = (float)rangeAxis.valueToJava2D(0.0D, dataArea, edge);
/* 255 */       g2.setPaint(getItemPaint(row, column));
/* 256 */       g2.setStroke(getItemStroke(row, column));
/* 258 */       GeneralPath area = new GeneralPath();
/* 260 */       if (orientation == PlotOrientation.VERTICAL) {
/* 261 */         area.moveTo(x0, yz);
/* 262 */         area.lineTo(x0, y0);
/* 263 */         area.lineTo(x1, y1);
/* 264 */         area.lineTo(x2, y2);
/* 265 */         area.lineTo(x2, yz);
/* 267 */       } else if (orientation == PlotOrientation.HORIZONTAL) {
/* 268 */         area.moveTo(yz, x0);
/* 269 */         area.lineTo(y0, x0);
/* 270 */         area.lineTo(y1, x1);
/* 271 */         area.lineTo(y2, x2);
/* 272 */         area.lineTo(yz, x2);
/*     */       } 
/* 274 */       area.closePath();
/* 276 */       g2.setPaint(getItemPaint(row, column));
/* 277 */       g2.fill(area);
/* 280 */       if (isItemLabelVisible(row, column))
/* 281 */         drawItemLabel(g2, orientation, dataset, row, column, x1, y1, (value.doubleValue() < 0.0D)); 
/* 288 */       EntityCollection entities = state.getEntityCollection();
/* 289 */       if (entities != null)
/* 290 */         addItemEntity(entities, dataset, row, column, area); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 304 */     return super.clone();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\renderer\category\AreaRenderer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */