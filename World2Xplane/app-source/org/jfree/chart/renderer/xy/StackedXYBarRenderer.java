/*     */ package org.jfree.chart.renderer.xy;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.entity.ChartEntity;
/*     */ import org.jfree.chart.entity.EntityCollection;
/*     */ import org.jfree.chart.entity.XYItemEntity;
/*     */ import org.jfree.chart.labels.XYToolTipGenerator;
/*     */ import org.jfree.chart.plot.CrosshairState;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.chart.plot.PlotRenderingInfo;
/*     */ import org.jfree.chart.plot.XYPlot;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.data.general.DatasetUtilities;
/*     */ import org.jfree.data.xy.IntervalXYDataset;
/*     */ import org.jfree.data.xy.TableXYDataset;
/*     */ import org.jfree.data.xy.XYDataset;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ 
/*     */ public class StackedXYBarRenderer extends XYBarRenderer implements Serializable {
/*     */   private static final long serialVersionUID = -7049101055533436444L;
/*     */   
/*     */   public StackedXYBarRenderer() {}
/*     */   
/*     */   public StackedXYBarRenderer(double margin) {
/* 102 */     super(margin);
/*     */   }
/*     */   
/*     */   public XYItemRendererState initialise(Graphics2D g2, Rectangle2D dataArea, XYPlot plot, XYDataset data, PlotRenderingInfo info) {
/* 124 */     return new XYBarRenderer.XYBarRendererState(this, info);
/*     */   }
/*     */   
/*     */   public Range findRangeBounds(XYDataset dataset) {
/* 137 */     if (dataset != null)
/* 138 */       return DatasetUtilities.findStackedRangeBounds((TableXYDataset)dataset); 
/* 143 */     return null;
/*     */   }
/*     */   
/*     */   public void drawItem(Graphics2D g2, XYItemRendererState state, Rectangle2D dataArea, PlotRenderingInfo info, XYPlot plot, ValueAxis domainAxis, ValueAxis rangeAxis, XYDataset dataset, int series, int item, CrosshairState crosshairState, int pass) {
/*     */     double translatedBase, translatedValue;
/* 177 */     if (!(dataset instanceof IntervalXYDataset) || !(dataset instanceof TableXYDataset)) {
/* 179 */       String message = "dataset (type " + dataset.getClass().getName() + ") has wrong type:";
/* 181 */       boolean and = false;
/* 182 */       if (!IntervalXYDataset.class.isAssignableFrom(dataset.getClass())) {
/* 183 */         message = message + " it is no IntervalXYDataset";
/* 184 */         and = true;
/*     */       } 
/* 186 */       if (!TableXYDataset.class.isAssignableFrom(dataset.getClass())) {
/* 187 */         if (and)
/* 188 */           message = message + " and"; 
/* 190 */         message = message + " it is no TableXYDataset";
/*     */       } 
/* 193 */       throw new IllegalArgumentException(message);
/*     */     } 
/* 196 */     IntervalXYDataset intervalDataset = (IntervalXYDataset)dataset;
/* 197 */     double value = intervalDataset.getYValue(series, item);
/* 198 */     if (Double.isNaN(value))
/*     */       return; 
/* 202 */     double positiveBase = 0.0D;
/* 203 */     double negativeBase = 0.0D;
/* 205 */     for (int i = 0; i < series; i++) {
/* 206 */       double v = dataset.getYValue(i, item);
/* 207 */       if (!Double.isNaN(v))
/* 208 */         if (v > 0.0D) {
/* 209 */           positiveBase += v;
/*     */         } else {
/* 212 */           negativeBase += v;
/*     */         }  
/*     */     } 
/* 219 */     RectangleEdge edgeR = plot.getRangeAxisEdge();
/* 220 */     if (value > 0.0D) {
/* 221 */       translatedBase = rangeAxis.valueToJava2D(positiveBase, dataArea, edgeR);
/* 224 */       translatedValue = rangeAxis.valueToJava2D(positiveBase + value, dataArea, edgeR);
/*     */     } else {
/* 229 */       translatedBase = rangeAxis.valueToJava2D(negativeBase, dataArea, edgeR);
/* 232 */       translatedValue = rangeAxis.valueToJava2D(negativeBase + value, dataArea, edgeR);
/*     */     } 
/* 237 */     RectangleEdge edgeD = plot.getDomainAxisEdge();
/* 238 */     double startX = intervalDataset.getStartXValue(series, item);
/* 239 */     if (Double.isNaN(startX))
/*     */       return; 
/* 242 */     double translatedStartX = domainAxis.valueToJava2D(startX, dataArea, edgeD);
/* 246 */     double endX = intervalDataset.getEndXValue(series, item);
/* 247 */     if (Double.isNaN(endX))
/*     */       return; 
/* 250 */     double translatedEndX = domainAxis.valueToJava2D(endX, dataArea, edgeD);
/* 252 */     double translatedWidth = Math.max(1.0D, Math.abs(translatedEndX - translatedStartX));
/* 255 */     double translatedHeight = Math.abs(translatedValue - translatedBase);
/* 256 */     if (getMargin() > 0.0D) {
/* 257 */       double cut = translatedWidth * getMargin();
/* 258 */       translatedWidth -= cut;
/* 259 */       translatedStartX += cut / 2.0D;
/*     */     } 
/* 262 */     Rectangle2D bar = null;
/* 263 */     PlotOrientation orientation = plot.getOrientation();
/* 264 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 265 */       bar = new Rectangle2D.Double(Math.min(translatedBase, translatedValue), translatedEndX, translatedHeight, translatedWidth);
/* 272 */     } else if (orientation == PlotOrientation.VERTICAL) {
/* 273 */       bar = new Rectangle2D.Double(translatedStartX, Math.min(translatedBase, translatedValue), translatedWidth, translatedHeight);
/*     */     } 
/* 281 */     g2.setPaint(getItemPaint(series, item));
/* 282 */     g2.fill(bar);
/* 283 */     if (isDrawBarOutline() && Math.abs(translatedEndX - translatedStartX) > 3.0D) {
/* 285 */       g2.setStroke(getItemStroke(series, item));
/* 286 */       g2.setPaint(getItemOutlinePaint(series, item));
/* 287 */       g2.draw(bar);
/*     */     } 
/* 291 */     if (info != null) {
/* 292 */       EntityCollection entities = info.getOwner().getEntityCollection();
/* 293 */       if (entities != null) {
/* 294 */         String tip = null;
/* 295 */         XYToolTipGenerator generator = getToolTipGenerator(series, item);
/* 297 */         if (generator != null)
/* 298 */           tip = generator.generateToolTip(dataset, series, item); 
/* 300 */         String url = null;
/* 301 */         if (getURLGenerator() != null)
/* 302 */           url = getURLGenerator().generateURL(dataset, series, item); 
/* 304 */         XYItemEntity entity = new XYItemEntity(bar, dataset, series, item, tip, url);
/* 307 */         entities.add((ChartEntity)entity);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\renderer\xy\StackedXYBarRenderer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */