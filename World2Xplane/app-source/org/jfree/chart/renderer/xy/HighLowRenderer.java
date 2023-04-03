/*     */ package org.jfree.chart.renderer.xy;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Shape;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.Line2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.entity.ChartEntity;
/*     */ import org.jfree.chart.entity.EntityCollection;
/*     */ import org.jfree.chart.entity.XYItemEntity;
/*     */ import org.jfree.chart.event.RendererChangeEvent;
/*     */ import org.jfree.chart.labels.XYToolTipGenerator;
/*     */ import org.jfree.chart.plot.CrosshairState;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.chart.plot.PlotRenderingInfo;
/*     */ import org.jfree.chart.plot.XYPlot;
/*     */ import org.jfree.data.xy.OHLCDataset;
/*     */ import org.jfree.data.xy.XYDataset;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.util.PaintUtilities;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ public class HighLowRenderer extends AbstractXYItemRenderer implements XYItemRenderer, Cloneable, PublicCloneable, Serializable {
/*     */   private static final long serialVersionUID = -8135673815876552516L;
/*     */   
/*     */   private boolean drawOpenTicks = true;
/*     */   
/*     */   private boolean drawCloseTicks = true;
/*     */   
/*     */   private transient Paint openTickPaint;
/*     */   
/*     */   private transient Paint closeTickPaint;
/*     */   
/*     */   public boolean getDrawOpenTicks() {
/* 142 */     return this.drawOpenTicks;
/*     */   }
/*     */   
/*     */   public void setDrawOpenTicks(boolean draw) {
/* 152 */     this.drawOpenTicks = draw;
/* 153 */     notifyListeners(new RendererChangeEvent(this));
/*     */   }
/*     */   
/*     */   public boolean getDrawCloseTicks() {
/* 162 */     return this.drawCloseTicks;
/*     */   }
/*     */   
/*     */   public void setDrawCloseTicks(boolean draw) {
/* 172 */     this.drawCloseTicks = draw;
/* 173 */     notifyListeners(new RendererChangeEvent(this));
/*     */   }
/*     */   
/*     */   public Paint getOpenTickPaint() {
/* 183 */     return this.openTickPaint;
/*     */   }
/*     */   
/*     */   public void setOpenTickPaint(Paint paint) {
/* 195 */     this.openTickPaint = paint;
/* 196 */     notifyListeners(new RendererChangeEvent(this));
/*     */   }
/*     */   
/*     */   public Paint getCloseTickPaint() {
/* 206 */     return this.closeTickPaint;
/*     */   }
/*     */   
/*     */   public void setCloseTickPaint(Paint paint) {
/* 218 */     this.closeTickPaint = paint;
/* 219 */     notifyListeners(new RendererChangeEvent(this));
/*     */   }
/*     */   
/*     */   public void drawItem(Graphics2D g2, XYItemRendererState state, Rectangle2D dataArea, PlotRenderingInfo info, XYPlot plot, ValueAxis domainAxis, ValueAxis rangeAxis, XYDataset dataset, int series, int item, CrosshairState crosshairState, int pass) {
/* 254 */     Number x = dataset.getX(series, item);
/* 255 */     if (x == null)
/*     */       return; 
/* 258 */     double xdouble = x.doubleValue();
/* 259 */     if (!domainAxis.getRange().contains(xdouble))
/*     */       return; 
/* 262 */     double xx = domainAxis.valueToJava2D(xdouble, dataArea, plot.getDomainAxisEdge());
/* 266 */     Shape entityArea = null;
/* 267 */     EntityCollection entities = null;
/* 268 */     if (info != null)
/* 269 */       entities = info.getOwner().getEntityCollection(); 
/* 272 */     PlotOrientation orientation = plot.getOrientation();
/* 273 */     RectangleEdge location = plot.getRangeAxisEdge();
/* 275 */     Paint itemPaint = getItemPaint(series, item);
/* 276 */     Stroke itemStroke = getItemStroke(series, item);
/* 277 */     g2.setPaint(itemPaint);
/* 278 */     g2.setStroke(itemStroke);
/* 280 */     if (dataset instanceof OHLCDataset) {
/* 281 */       OHLCDataset hld = (OHLCDataset)dataset;
/* 283 */       double yHigh = hld.getHighValue(series, item);
/* 284 */       double yLow = hld.getLowValue(series, item);
/* 285 */       if (!Double.isNaN(yHigh) && !Double.isNaN(yLow)) {
/* 286 */         double yyHigh = rangeAxis.valueToJava2D(yHigh, dataArea, location);
/* 288 */         double yyLow = rangeAxis.valueToJava2D(yLow, dataArea, location);
/* 290 */         if (orientation == PlotOrientation.HORIZONTAL) {
/* 291 */           g2.draw(new Line2D.Double(yyLow, xx, yyHigh, xx));
/* 292 */           entityArea = new Rectangle2D.Double(Math.min(yyLow, yyHigh), xx - 1.0D, Math.abs(yyHigh - yyLow), 2.0D);
/* 295 */         } else if (orientation == PlotOrientation.VERTICAL) {
/* 296 */           g2.draw(new Line2D.Double(xx, yyLow, xx, yyHigh));
/* 297 */           entityArea = new Rectangle2D.Double(xx - 1.0D, Math.min(yyLow, yyHigh), 2.0D, Math.abs(yyHigh - yyLow));
/*     */         } 
/*     */       } 
/* 303 */       double delta = 2.0D;
/* 304 */       if (domainAxis.isInverted())
/* 305 */         delta = -delta; 
/* 307 */       if (getDrawOpenTicks()) {
/* 308 */         double yOpen = hld.getOpenValue(series, item);
/* 309 */         if (!Double.isNaN(yOpen)) {
/* 310 */           double yyOpen = rangeAxis.valueToJava2D(yOpen, dataArea, location);
/* 312 */           if (this.openTickPaint != null) {
/* 313 */             g2.setPaint(this.openTickPaint);
/*     */           } else {
/* 316 */             g2.setPaint(itemPaint);
/*     */           } 
/* 318 */           if (orientation == PlotOrientation.HORIZONTAL) {
/* 319 */             g2.draw(new Line2D.Double(yyOpen, xx + delta, yyOpen, xx));
/* 322 */           } else if (orientation == PlotOrientation.VERTICAL) {
/* 323 */             g2.draw(new Line2D.Double(xx - delta, yyOpen, xx, yyOpen));
/*     */           } 
/*     */         } 
/*     */       } 
/* 329 */       if (getDrawCloseTicks()) {
/* 330 */         double yClose = hld.getCloseValue(series, item);
/* 331 */         if (!Double.isNaN(yClose)) {
/* 332 */           double yyClose = rangeAxis.valueToJava2D(yClose, dataArea, location);
/* 334 */           if (this.closeTickPaint != null) {
/* 335 */             g2.setPaint(this.closeTickPaint);
/*     */           } else {
/* 338 */             g2.setPaint(itemPaint);
/*     */           } 
/* 340 */           if (orientation == PlotOrientation.HORIZONTAL) {
/* 341 */             g2.draw(new Line2D.Double(yyClose, xx, yyClose, xx - delta));
/* 344 */           } else if (orientation == PlotOrientation.VERTICAL) {
/* 345 */             g2.draw(new Line2D.Double(xx, yyClose, xx + delta, yyClose));
/*     */           } 
/*     */         } 
/*     */       } 
/* 355 */     } else if (item > 0) {
/* 356 */       Number x0 = dataset.getX(series, item - 1);
/* 357 */       Number y0 = dataset.getY(series, item - 1);
/* 358 */       Number y = dataset.getY(series, item);
/* 359 */       if (x0 == null || y0 == null || y == null)
/*     */         return; 
/* 362 */       double xx0 = domainAxis.valueToJava2D(x0.doubleValue(), dataArea, plot.getDomainAxisEdge());
/* 364 */       double yy0 = rangeAxis.valueToJava2D(y0.doubleValue(), dataArea, location);
/* 366 */       double yy = rangeAxis.valueToJava2D(y.doubleValue(), dataArea, location);
/* 368 */       if (orientation == PlotOrientation.HORIZONTAL) {
/* 369 */         g2.draw(new Line2D.Double(yy0, xx0, yy, xx));
/* 371 */       } else if (orientation == PlotOrientation.VERTICAL) {
/* 372 */         g2.draw(new Line2D.Double(xx0, yy0, xx, yy));
/*     */       } 
/*     */     } 
/* 378 */     if (entities != null) {
/* 379 */       String tip = null;
/* 380 */       XYToolTipGenerator generator = getToolTipGenerator(series, item);
/* 381 */       if (generator != null)
/* 382 */         tip = generator.generateToolTip(dataset, series, item); 
/* 384 */       String url = null;
/* 385 */       if (getURLGenerator() != null)
/* 386 */         url = getURLGenerator().generateURL(dataset, series, item); 
/* 388 */       XYItemEntity entity = new XYItemEntity(entityArea, dataset, series, item, tip, url);
/* 390 */       entities.add((ChartEntity)entity);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 403 */     return super.clone();
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 414 */     if (this == obj)
/* 415 */       return true; 
/* 417 */     if (!(obj instanceof HighLowRenderer))
/* 418 */       return false; 
/* 420 */     HighLowRenderer that = (HighLowRenderer)obj;
/* 421 */     if (this.drawOpenTicks != that.drawOpenTicks)
/* 422 */       return false; 
/* 424 */     if (this.drawCloseTicks != that.drawCloseTicks)
/* 425 */       return false; 
/* 427 */     if (!PaintUtilities.equal(this.openTickPaint, that.openTickPaint))
/* 428 */       return false; 
/* 430 */     if (!PaintUtilities.equal(this.closeTickPaint, that.closeTickPaint))
/* 431 */       return false; 
/* 433 */     if (!super.equals(obj))
/* 434 */       return false; 
/* 436 */     return true;
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
/* 449 */     stream.defaultReadObject();
/* 450 */     this.openTickPaint = SerialUtilities.readPaint(stream);
/* 451 */     this.closeTickPaint = SerialUtilities.readPaint(stream);
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream stream) throws IOException {
/* 462 */     stream.defaultWriteObject();
/* 463 */     SerialUtilities.writePaint(this.openTickPaint, stream);
/* 464 */     SerialUtilities.writePaint(this.closeTickPaint, stream);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\renderer\xy\HighLowRenderer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */