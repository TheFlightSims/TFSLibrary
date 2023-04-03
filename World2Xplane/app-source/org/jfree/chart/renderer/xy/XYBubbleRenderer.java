/*     */ package org.jfree.chart.renderer.xy;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Shape;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.Ellipse2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.LegendItem;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.entity.ChartEntity;
/*     */ import org.jfree.chart.entity.EntityCollection;
/*     */ import org.jfree.chart.entity.XYItemEntity;
/*     */ import org.jfree.chart.labels.XYToolTipGenerator;
/*     */ import org.jfree.chart.plot.CrosshairState;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.chart.plot.PlotRenderingInfo;
/*     */ import org.jfree.chart.plot.XYPlot;
/*     */ import org.jfree.data.xy.XYDataset;
/*     */ import org.jfree.data.xy.XYZDataset;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ public class XYBubbleRenderer extends AbstractXYItemRenderer implements XYItemRenderer, Cloneable, PublicCloneable, Serializable {
/*     */   public static final long serialVersionUID = -5221991598674249125L;
/*     */   
/*     */   public static final int SCALE_ON_BOTH_AXES = 0;
/*     */   
/*     */   public static final int SCALE_ON_DOMAIN_AXIS = 1;
/*     */   
/*     */   public static final int SCALE_ON_RANGE_AXIS = 2;
/*     */   
/*     */   private int scaleType;
/*     */   
/*     */   public XYBubbleRenderer() {
/* 111 */     this(0);
/*     */   }
/*     */   
/*     */   public XYBubbleRenderer(int scaleType) {
/* 121 */     this.scaleType = scaleType;
/*     */   }
/*     */   
/*     */   public int getScaleType() {
/* 130 */     return this.scaleType;
/*     */   }
/*     */   
/*     */   public void drawItem(Graphics2D g2, XYItemRendererState state, Rectangle2D dataArea, PlotRenderingInfo info, XYPlot plot, ValueAxis domainAxis, ValueAxis rangeAxis, XYDataset dataset, int series, int item, CrosshairState crosshairState, int pass) {
/* 164 */     PlotOrientation orientation = plot.getOrientation();
/* 167 */     double x = dataset.getXValue(series, item);
/* 168 */     double y = dataset.getYValue(series, item);
/* 169 */     double z = Double.NaN;
/* 170 */     if (dataset instanceof XYZDataset) {
/* 171 */       XYZDataset xyzData = (XYZDataset)dataset;
/* 172 */       z = xyzData.getZValue(series, item);
/*     */     } 
/* 174 */     if (!Double.isNaN(z)) {
/*     */       double zero, zero1, zero2;
/* 175 */       RectangleEdge domainAxisLocation = plot.getDomainAxisEdge();
/* 176 */       RectangleEdge rangeAxisLocation = plot.getRangeAxisEdge();
/* 177 */       double transX = domainAxis.valueToJava2D(x, dataArea, domainAxisLocation);
/* 180 */       double transY = rangeAxis.valueToJava2D(y, dataArea, rangeAxisLocation);
/* 184 */       double transDomain = 0.0D;
/* 185 */       double transRange = 0.0D;
/* 188 */       switch (getScaleType()) {
/*     */         case 1:
/* 190 */           zero = domainAxis.valueToJava2D(0.0D, dataArea, domainAxisLocation);
/* 193 */           transDomain = domainAxis.valueToJava2D(z, dataArea, domainAxisLocation) - zero;
/* 196 */           transRange = transDomain;
/*     */           break;
/*     */         case 2:
/* 199 */           zero = rangeAxis.valueToJava2D(0.0D, dataArea, rangeAxisLocation);
/* 202 */           transRange = zero - rangeAxis.valueToJava2D(z, dataArea, rangeAxisLocation);
/* 205 */           transDomain = transRange;
/*     */           break;
/*     */         default:
/* 208 */           zero1 = domainAxis.valueToJava2D(0.0D, dataArea, domainAxisLocation);
/* 211 */           zero2 = rangeAxis.valueToJava2D(0.0D, dataArea, rangeAxisLocation);
/* 214 */           transDomain = domainAxis.valueToJava2D(z, dataArea, domainAxisLocation) - zero1;
/* 217 */           transRange = zero2 - rangeAxis.valueToJava2D(z, dataArea, rangeAxisLocation);
/*     */           break;
/*     */       } 
/* 221 */       transDomain = Math.abs(transDomain);
/* 222 */       transRange = Math.abs(transRange);
/* 223 */       Ellipse2D circle = null;
/* 224 */       if (orientation == PlotOrientation.VERTICAL) {
/* 225 */         circle = new Ellipse2D.Double(transX - transDomain / 2.0D, transY - transRange / 2.0D, transDomain, transRange);
/* 230 */       } else if (orientation == PlotOrientation.HORIZONTAL) {
/* 231 */         circle = new Ellipse2D.Double(transY - transRange / 2.0D, transX - transDomain / 2.0D, transRange, transDomain);
/*     */       } 
/* 236 */       g2.setPaint(getItemPaint(series, item));
/* 237 */       g2.fill(circle);
/* 238 */       g2.setStroke(new BasicStroke(1.0F));
/* 239 */       g2.setPaint(Color.lightGray);
/* 240 */       g2.draw(circle);
/* 243 */       EntityCollection entities = null;
/* 244 */       if (info != null)
/* 245 */         entities = info.getOwner().getEntityCollection(); 
/* 249 */       if (entities != null) {
/* 250 */         String tip = null;
/* 251 */         XYToolTipGenerator generator = getToolTipGenerator(series, item);
/* 253 */         if (generator != null)
/* 254 */           tip = generator.generateToolTip(dataset, series, item); 
/* 256 */         String url = null;
/* 257 */         if (getURLGenerator() != null)
/* 258 */           url = getURLGenerator().generateURL(dataset, series, item); 
/* 260 */         XYItemEntity entity = new XYItemEntity(circle, dataset, series, item, tip, url);
/* 263 */         entities.add((ChartEntity)entity);
/*     */       } 
/* 266 */       updateCrosshairValues(crosshairState, x, y, transX, transY, orientation);
/*     */     } 
/*     */   }
/*     */   
/*     */   public LegendItem getLegendItem(int datasetIndex, int series) {
/* 283 */     LegendItem result = null;
/* 284 */     XYPlot xyplot = getPlot();
/* 285 */     if (xyplot != null) {
/* 286 */       XYDataset dataset = xyplot.getDataset(datasetIndex);
/* 287 */       if (dataset != null && 
/* 288 */         getItemVisible(series, 0)) {
/* 289 */         String label = getLegendItemLabelGenerator().generateLabel(dataset, series);
/* 292 */         String description = label;
/* 293 */         String toolTipText = null;
/* 294 */         if (getLegendItemToolTipGenerator() != null)
/* 295 */           toolTipText = getLegendItemToolTipGenerator().generateLabel(dataset, series); 
/* 300 */         String urlText = null;
/* 301 */         if (getLegendItemURLGenerator() != null)
/* 302 */           urlText = getLegendItemURLGenerator().generateLabel(dataset, series); 
/* 306 */         Shape shape = new Ellipse2D.Double(-4.0D, -4.0D, 8.0D, 8.0D);
/* 307 */         Paint paint = getSeriesPaint(series);
/* 308 */         Paint outlinePaint = getSeriesOutlinePaint(series);
/* 309 */         Stroke outlineStroke = getSeriesOutlineStroke(series);
/* 310 */         result = new LegendItem(label, description, toolTipText, urlText, shape, paint, outlineStroke, outlinePaint);
/*     */       } 
/*     */     } 
/* 317 */     return result;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 328 */     return super.clone();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\renderer\xy\XYBubbleRenderer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */