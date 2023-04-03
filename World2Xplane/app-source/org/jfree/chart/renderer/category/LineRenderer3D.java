/*     */ package org.jfree.chart.renderer.category;
/*     */ 
/*     */ import java.awt.AlphaComposite;
/*     */ import java.awt.Color;
/*     */ import java.awt.Composite;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Image;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Shape;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.Line2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.Effect3D;
/*     */ import org.jfree.chart.axis.CategoryAxis;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.entity.EntityCollection;
/*     */ import org.jfree.chart.event.RendererChangeEvent;
/*     */ import org.jfree.chart.plot.CategoryPlot;
/*     */ import org.jfree.chart.plot.Marker;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.chart.plot.ValueMarker;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.data.category.CategoryDataset;
/*     */ import org.jfree.util.ShapeUtilities;
/*     */ 
/*     */ public class LineRenderer3D extends LineAndShapeRenderer implements Effect3D, Serializable {
/*     */   private static final long serialVersionUID = 5467931468380928736L;
/*     */   
/*     */   public static final double DEFAULT_X_OFFSET = 12.0D;
/*     */   
/*     */   public static final double DEFAULT_Y_OFFSET = 8.0D;
/*     */   
/*  96 */   public static final Paint DEFAULT_WALL_PAINT = new Color(221, 221, 221);
/*     */   
/*     */   private double xOffset;
/*     */   
/*     */   private double yOffset;
/*     */   
/*     */   private transient Paint wallPaint;
/*     */   
/*     */   public LineRenderer3D() {
/* 111 */     super(true, false);
/* 112 */     this.xOffset = 12.0D;
/* 113 */     this.yOffset = 8.0D;
/* 114 */     this.wallPaint = DEFAULT_WALL_PAINT;
/*     */   }
/*     */   
/*     */   public double getXOffset() {
/* 123 */     return this.xOffset;
/*     */   }
/*     */   
/*     */   public double getYOffset() {
/* 132 */     return this.yOffset;
/*     */   }
/*     */   
/*     */   public void setXOffset(double xOffset) {
/* 141 */     this.xOffset = xOffset;
/* 142 */     notifyListeners(new RendererChangeEvent(this));
/*     */   }
/*     */   
/*     */   public void setYOffset(double yOffset) {
/* 151 */     this.yOffset = yOffset;
/* 152 */     notifyListeners(new RendererChangeEvent(this));
/*     */   }
/*     */   
/*     */   public Paint getWallPaint() {
/* 162 */     return this.wallPaint;
/*     */   }
/*     */   
/*     */   public void setWallPaint(Paint paint) {
/* 172 */     this.wallPaint = paint;
/* 173 */     notifyListeners(new RendererChangeEvent(this));
/*     */   }
/*     */   
/*     */   public void drawBackground(Graphics2D g2, CategoryPlot plot, Rectangle2D dataArea) {
/* 186 */     float x0 = (float)dataArea.getX();
/* 187 */     float x1 = x0 + (float)Math.abs(this.xOffset);
/* 188 */     float x3 = (float)dataArea.getMaxX();
/* 189 */     float x2 = x3 - (float)Math.abs(this.xOffset);
/* 191 */     float y0 = (float)dataArea.getMaxY();
/* 192 */     float y1 = y0 - (float)Math.abs(this.yOffset);
/* 193 */     float y3 = (float)dataArea.getMinY();
/* 194 */     float y2 = y3 + (float)Math.abs(this.yOffset);
/* 196 */     GeneralPath clip = new GeneralPath();
/* 197 */     clip.moveTo(x0, y0);
/* 198 */     clip.lineTo(x0, y2);
/* 199 */     clip.lineTo(x1, y3);
/* 200 */     clip.lineTo(x3, y3);
/* 201 */     clip.lineTo(x3, y1);
/* 202 */     clip.lineTo(x2, y0);
/* 203 */     clip.closePath();
/* 206 */     Paint backgroundPaint = plot.getBackgroundPaint();
/* 207 */     if (backgroundPaint != null) {
/* 208 */       g2.setPaint(backgroundPaint);
/* 209 */       g2.fill(clip);
/*     */     } 
/* 212 */     GeneralPath leftWall = new GeneralPath();
/* 213 */     leftWall.moveTo(x0, y0);
/* 214 */     leftWall.lineTo(x0, y2);
/* 215 */     leftWall.lineTo(x1, y3);
/* 216 */     leftWall.lineTo(x1, y1);
/* 217 */     leftWall.closePath();
/* 218 */     g2.setPaint(getWallPaint());
/* 219 */     g2.fill(leftWall);
/* 221 */     GeneralPath bottomWall = new GeneralPath();
/* 222 */     bottomWall.moveTo(x0, y0);
/* 223 */     bottomWall.lineTo(x1, y1);
/* 224 */     bottomWall.lineTo(x3, y1);
/* 225 */     bottomWall.lineTo(x2, y0);
/* 226 */     bottomWall.closePath();
/* 227 */     g2.setPaint(getWallPaint());
/* 228 */     g2.fill(bottomWall);
/* 231 */     g2.setPaint(Color.lightGray);
/* 232 */     Line2D corner = new Line2D.Double(x0, y0, x1, y1);
/* 233 */     g2.draw(corner);
/* 234 */     corner.setLine(x1, y1, x1, y3);
/* 235 */     g2.draw(corner);
/* 236 */     corner.setLine(x1, y1, x3, y1);
/* 237 */     g2.draw(corner);
/* 240 */     Image backgroundImage = plot.getBackgroundImage();
/* 241 */     if (backgroundImage != null) {
/* 242 */       Composite originalComposite = g2.getComposite();
/* 243 */       g2.setComposite(AlphaComposite.getInstance(2, plot.getBackgroundAlpha()));
/* 246 */       g2.drawImage(backgroundImage, (int)x1, (int)y3, (int)(x3 - x1 + 1.0F), (int)(y1 - y3 + 1.0F), null);
/* 252 */       g2.setComposite(originalComposite);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void drawOutline(Graphics2D g2, CategoryPlot plot, Rectangle2D dataArea) {
/* 267 */     float x0 = (float)dataArea.getX();
/* 268 */     float x1 = x0 + (float)Math.abs(this.xOffset);
/* 269 */     float x3 = (float)dataArea.getMaxX();
/* 270 */     float x2 = x3 - (float)Math.abs(this.xOffset);
/* 272 */     float y0 = (float)dataArea.getMaxY();
/* 273 */     float y1 = y0 - (float)Math.abs(this.yOffset);
/* 274 */     float y3 = (float)dataArea.getMinY();
/* 275 */     float y2 = y3 + (float)Math.abs(this.yOffset);
/* 277 */     GeneralPath clip = new GeneralPath();
/* 278 */     clip.moveTo(x0, y0);
/* 279 */     clip.lineTo(x0, y2);
/* 280 */     clip.lineTo(x1, y3);
/* 281 */     clip.lineTo(x3, y3);
/* 282 */     clip.lineTo(x3, y1);
/* 283 */     clip.lineTo(x2, y0);
/* 284 */     clip.closePath();
/* 287 */     Stroke outlineStroke = plot.getOutlineStroke();
/* 288 */     Paint outlinePaint = plot.getOutlinePaint();
/* 289 */     if (outlineStroke != null && outlinePaint != null) {
/* 290 */       g2.setStroke(outlineStroke);
/* 291 */       g2.setPaint(outlinePaint);
/* 292 */       g2.draw(clip);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void drawDomainGridline(Graphics2D g2, CategoryPlot plot, Rectangle2D dataArea, double value) {
/* 312 */     Line2D line1 = null;
/* 313 */     Line2D line2 = null;
/* 314 */     PlotOrientation orientation = plot.getOrientation();
/* 315 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 316 */       double y0 = value;
/* 317 */       double y1 = value - getYOffset();
/* 318 */       double x0 = dataArea.getMinX();
/* 319 */       double x1 = x0 + getXOffset();
/* 320 */       double x2 = dataArea.getMaxY();
/* 321 */       line1 = new Line2D.Double(x0, y0, x1, y1);
/* 322 */       line2 = new Line2D.Double(x1, y1, x2, y1);
/* 324 */     } else if (orientation == PlotOrientation.VERTICAL) {
/* 325 */       double x0 = value;
/* 326 */       double x1 = value + getXOffset();
/* 327 */       double y0 = dataArea.getMaxY();
/* 328 */       double y1 = y0 - getYOffset();
/* 329 */       double y2 = dataArea.getMinY();
/* 330 */       line1 = new Line2D.Double(x0, y0, x1, y1);
/* 331 */       line2 = new Line2D.Double(x1, y1, x1, y2);
/*     */     } 
/* 333 */     g2.setPaint(plot.getDomainGridlinePaint());
/* 334 */     g2.setStroke(plot.getDomainGridlineStroke());
/* 335 */     g2.draw(line1);
/* 336 */     g2.draw(line2);
/*     */   }
/*     */   
/*     */   public void drawRangeGridline(Graphics2D g2, CategoryPlot plot, ValueAxis axis, Rectangle2D dataArea, double value) {
/* 357 */     Range range = axis.getRange();
/* 359 */     if (!range.contains(value))
/*     */       return; 
/* 363 */     Rectangle2D adjusted = new Rectangle2D.Double(dataArea.getX(), dataArea.getY() + getYOffset(), dataArea.getWidth() - getXOffset(), dataArea.getHeight() - getYOffset());
/* 370 */     Line2D line1 = null;
/* 371 */     Line2D line2 = null;
/* 372 */     PlotOrientation orientation = plot.getOrientation();
/* 373 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 374 */       double x0 = axis.valueToJava2D(value, adjusted, plot.getRangeAxisEdge());
/* 376 */       double x1 = x0 + getXOffset();
/* 377 */       double y0 = dataArea.getMaxY();
/* 378 */       double y1 = y0 - getYOffset();
/* 379 */       double y2 = dataArea.getMinY();
/* 380 */       line1 = new Line2D.Double(x0, y0, x1, y1);
/* 381 */       line2 = new Line2D.Double(x1, y1, x1, y2);
/* 383 */     } else if (orientation == PlotOrientation.VERTICAL) {
/* 384 */       double y0 = axis.valueToJava2D(value, adjusted, plot.getRangeAxisEdge());
/* 386 */       double y1 = y0 - getYOffset();
/* 387 */       double x0 = dataArea.getMinX();
/* 388 */       double x1 = x0 + getXOffset();
/* 389 */       double x2 = dataArea.getMaxX();
/* 390 */       line1 = new Line2D.Double(x0, y0, x1, y1);
/* 391 */       line2 = new Line2D.Double(x1, y1, x2, y1);
/*     */     } 
/* 393 */     g2.setPaint(plot.getRangeGridlinePaint());
/* 394 */     g2.setStroke(plot.getRangeGridlineStroke());
/* 395 */     g2.draw(line1);
/* 396 */     g2.draw(line2);
/*     */   }
/*     */   
/*     */   public void drawRangeMarker(Graphics2D g2, CategoryPlot plot, ValueAxis axis, Marker marker, Rectangle2D dataArea) {
/* 415 */     if (marker instanceof ValueMarker) {
/* 416 */       ValueMarker vm = (ValueMarker)marker;
/* 417 */       double value = vm.getValue();
/* 418 */       Range range = axis.getRange();
/* 419 */       if (!range.contains(value))
/*     */         return; 
/* 423 */       Rectangle2D adjusted = new Rectangle2D.Double(dataArea.getX(), dataArea.getY() + getYOffset(), dataArea.getWidth() - getXOffset(), dataArea.getHeight() - getYOffset());
/* 429 */       GeneralPath path = null;
/* 430 */       PlotOrientation orientation = plot.getOrientation();
/* 431 */       if (orientation == PlotOrientation.HORIZONTAL) {
/* 432 */         float x = (float)axis.valueToJava2D(value, adjusted, plot.getRangeAxisEdge());
/* 435 */         float y = (float)adjusted.getMaxY();
/* 436 */         path = new GeneralPath();
/* 437 */         path.moveTo(x, y);
/* 438 */         path.lineTo((float)(x + getXOffset()), y - (float)getYOffset());
/* 440 */         path.lineTo((float)(x + getXOffset()), (float)(adjusted.getMinY() - getYOffset()));
/* 444 */         path.lineTo(x, (float)adjusted.getMinY());
/* 445 */         path.closePath();
/* 447 */       } else if (orientation == PlotOrientation.VERTICAL) {
/* 448 */         float y = (float)axis.valueToJava2D(value, adjusted, plot.getRangeAxisEdge());
/* 451 */         float x = (float)dataArea.getX();
/* 452 */         path = new GeneralPath();
/* 453 */         path.moveTo(x, y);
/* 454 */         path.lineTo(x + (float)this.xOffset, y - (float)this.yOffset);
/* 455 */         path.lineTo((float)(adjusted.getMaxX() + this.xOffset), y - (float)this.yOffset);
/* 457 */         path.lineTo((float)adjusted.getMaxX(), y);
/* 458 */         path.closePath();
/*     */       } 
/* 460 */       g2.setPaint(marker.getPaint());
/* 461 */       g2.fill(path);
/* 462 */       g2.setPaint(marker.getOutlinePaint());
/* 463 */       g2.draw(path);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void drawItem(Graphics2D g2, CategoryItemRendererState state, Rectangle2D dataArea, CategoryPlot plot, CategoryAxis domainAxis, ValueAxis rangeAxis, CategoryDataset dataset, int row, int column, int pass) {
/* 492 */     if (!getItemVisible(row, column))
/*     */       return; 
/* 497 */     Number v = dataset.getValue(row, column);
/* 498 */     if (v == null)
/*     */       return; 
/* 502 */     Rectangle2D adjusted = new Rectangle2D.Double(dataArea.getX(), dataArea.getY() + getYOffset(), dataArea.getWidth() - getXOffset(), dataArea.getHeight() - getYOffset());
/* 509 */     PlotOrientation orientation = plot.getOrientation();
/* 512 */     double x1 = domainAxis.getCategoryMiddle(column, getColumnCount(), adjusted, plot.getDomainAxisEdge());
/* 515 */     double value = v.doubleValue();
/* 516 */     double y1 = rangeAxis.valueToJava2D(value, adjusted, plot.getRangeAxisEdge());
/* 519 */     Shape shape = getItemShape(row, column);
/* 520 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 521 */       shape = ShapeUtilities.createTranslatedShape(shape, y1, x1);
/* 523 */     } else if (orientation == PlotOrientation.VERTICAL) {
/* 524 */       shape = ShapeUtilities.createTranslatedShape(shape, x1, y1);
/*     */     } 
/* 527 */     if (getItemLineVisible(row, column) && 
/* 528 */       column != 0) {
/* 530 */       Number previousValue = dataset.getValue(row, column - 1);
/* 531 */       if (previousValue != null) {
/* 534 */         double previous = previousValue.doubleValue();
/* 535 */         double x0 = domainAxis.getCategoryMiddle(column - 1, getColumnCount(), adjusted, plot.getDomainAxisEdge());
/* 539 */         double y0 = rangeAxis.valueToJava2D(previous, adjusted, plot.getRangeAxisEdge());
/* 543 */         double x2 = x0 + getXOffset();
/* 544 */         double y2 = y0 - getYOffset();
/* 545 */         double x3 = x1 + getXOffset();
/* 546 */         double y3 = y1 - getYOffset();
/* 548 */         GeneralPath clip = new GeneralPath();
/* 550 */         if (orientation == PlotOrientation.HORIZONTAL) {
/* 551 */           clip.moveTo((float)y0, (float)x0);
/* 552 */           clip.lineTo((float)y1, (float)x1);
/* 553 */           clip.lineTo((float)y3, (float)x3);
/* 554 */           clip.lineTo((float)y2, (float)x2);
/* 555 */           clip.lineTo((float)y0, (float)x0);
/* 556 */           clip.closePath();
/* 558 */         } else if (orientation == PlotOrientation.VERTICAL) {
/* 559 */           clip.moveTo((float)x0, (float)y0);
/* 560 */           clip.lineTo((float)x1, (float)y1);
/* 561 */           clip.lineTo((float)x3, (float)y3);
/* 562 */           clip.lineTo((float)x2, (float)y2);
/* 563 */           clip.lineTo((float)x0, (float)y0);
/* 564 */           clip.closePath();
/*     */         } 
/* 567 */         g2.setPaint(getItemPaint(row, column));
/* 568 */         g2.fill(clip);
/* 569 */         g2.setStroke(getItemOutlineStroke(row, column));
/* 570 */         g2.setPaint(getItemOutlinePaint(row, column));
/* 571 */         g2.draw(clip);
/*     */       } 
/*     */     } 
/* 577 */     if (isItemLabelVisible(row, column))
/* 578 */       drawItemLabel(g2, orientation, dataset, row, column, x1, y1, (value < 0.0D)); 
/* 584 */     EntityCollection entities = state.getEntityCollection();
/* 585 */     if (entities != null)
/* 586 */       addItemEntity(entities, dataset, row, column, shape); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\renderer\category\LineRenderer3D.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */