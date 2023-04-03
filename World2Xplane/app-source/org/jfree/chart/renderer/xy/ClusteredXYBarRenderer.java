/*     */ package org.jfree.chart.renderer.xy;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
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
/*     */ import org.jfree.data.xy.IntervalXYDataset;
/*     */ import org.jfree.data.xy.XYDataset;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ public class ClusteredXYBarRenderer extends XYBarRenderer implements Cloneable, PublicCloneable, Serializable {
/*     */   private static final long serialVersionUID = 5864462149177133147L;
/*     */   
/*     */   private boolean centerBarAtStartValue;
/*     */   
/*     */   public ClusteredXYBarRenderer() {
/* 107 */     this(0.0D, false);
/*     */   }
/*     */   
/*     */   public ClusteredXYBarRenderer(double margin, boolean centerBarAtStartValue) {
/* 119 */     super(margin);
/* 120 */     this.centerBarAtStartValue = centerBarAtStartValue;
/*     */   }
/*     */   
/*     */   public void drawItem(Graphics2D g2, XYItemRendererState state, Rectangle2D dataArea, PlotRenderingInfo info, XYPlot plot, ValueAxis domainAxis, ValueAxis rangeAxis, XYDataset dataset, int series, int item, CrosshairState crosshairState, int pass) {
/*     */     double value0, value1;
/* 157 */     IntervalXYDataset intervalDataset = (IntervalXYDataset)dataset;
/* 159 */     Paint seriesPaint = getItemPaint(series, item);
/* 163 */     if (getUseYInterval()) {
/* 164 */       value0 = intervalDataset.getStartYValue(series, item);
/* 165 */       value1 = intervalDataset.getEndYValue(series, item);
/*     */     } else {
/* 168 */       value0 = getBase();
/* 169 */       value1 = intervalDataset.getYValue(series, item);
/*     */     } 
/* 171 */     if (Double.isNaN(value0) || Double.isNaN(value1))
/*     */       return; 
/* 175 */     double translatedValue0 = rangeAxis.valueToJava2D(value0, dataArea, plot.getRangeAxisEdge());
/* 178 */     double translatedValue1 = rangeAxis.valueToJava2D(value1, dataArea, plot.getRangeAxisEdge());
/* 182 */     RectangleEdge xAxisLocation = plot.getDomainAxisEdge();
/* 183 */     double x1 = intervalDataset.getStartXValue(series, item);
/* 184 */     double translatedX1 = domainAxis.valueToJava2D(x1, dataArea, xAxisLocation);
/* 188 */     double x2 = intervalDataset.getEndXValue(series, item);
/* 189 */     double translatedX2 = domainAxis.valueToJava2D(x2, dataArea, xAxisLocation);
/* 193 */     double translatedWidth = Math.max(1.0D, Math.abs(translatedX2 - translatedX1));
/* 196 */     double translatedHeight = Math.abs(translatedValue0 - translatedValue1);
/* 198 */     if (this.centerBarAtStartValue)
/* 199 */       translatedX1 -= translatedWidth / 2.0D; 
/* 202 */     if (getMargin() > 0.0D) {
/* 203 */       double cut = translatedWidth * getMargin();
/* 204 */       translatedWidth -= cut;
/* 205 */       translatedX1 += cut / 2.0D;
/*     */     } 
/* 208 */     int numSeries = dataset.getSeriesCount();
/* 209 */     double seriesBarWidth = translatedWidth / numSeries;
/* 211 */     Rectangle2D bar = null;
/* 212 */     PlotOrientation orientation = plot.getOrientation();
/* 213 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 214 */       bar = new Rectangle2D.Double(Math.min(translatedValue0, translatedValue1), translatedX1 - seriesBarWidth * (numSeries - series), translatedHeight, seriesBarWidth);
/* 220 */     } else if (orientation == PlotOrientation.VERTICAL) {
/* 222 */       bar = new Rectangle2D.Double(translatedX1 + seriesBarWidth * series, Math.min(translatedValue0, translatedValue1), seriesBarWidth, translatedHeight);
/*     */     } 
/* 229 */     g2.setPaint(seriesPaint);
/* 230 */     g2.fill(bar);
/* 231 */     if (isDrawBarOutline() && Math.abs(translatedX2 - translatedX1) > 3.0D) {
/* 232 */       g2.setStroke(getItemOutlineStroke(series, item));
/* 233 */       g2.setPaint(getItemOutlinePaint(series, item));
/* 234 */       g2.draw(bar);
/*     */     } 
/* 238 */     if (isItemLabelVisible(series, item))
/* 239 */       drawItemLabel(g2, orientation, dataset, series, item, bar.getCenterX(), bar.getY(), (value1 < 0.0D)); 
/* 246 */     if (info != null) {
/* 247 */       EntityCollection entities = info.getOwner().getEntityCollection();
/* 248 */       if (entities != null) {
/* 249 */         String tip = null;
/* 250 */         XYToolTipGenerator generator = getToolTipGenerator(series, item);
/* 252 */         if (generator != null)
/* 253 */           tip = generator.generateToolTip(dataset, series, item); 
/* 255 */         String url = null;
/* 256 */         if (getURLGenerator() != null)
/* 257 */           url = getURLGenerator().generateURL(dataset, series, item); 
/* 259 */         XYItemEntity entity = new XYItemEntity(bar, dataset, series, item, tip, url);
/* 262 */         entities.add((ChartEntity)entity);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 279 */     if (obj == this)
/* 280 */       return true; 
/* 282 */     if (!(obj instanceof ClusteredXYBarRenderer))
/* 283 */       return false; 
/* 285 */     if (!super.equals(obj))
/* 286 */       return false; 
/* 288 */     ClusteredXYBarRenderer that = (ClusteredXYBarRenderer)obj;
/* 289 */     if (this.centerBarAtStartValue != that.centerBarAtStartValue)
/* 290 */       return false; 
/* 292 */     return true;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 303 */     return super.clone();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\renderer\xy\ClusteredXYBarRenderer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */