/*     */ package org.jfree.chart.block;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.ui.RectangleInsets;
/*     */ import org.jfree.ui.Size2D;
/*     */ 
/*     */ public class AbstractBlock implements Serializable {
/* 104 */   private String id = null;
/*     */   
/* 105 */   private double width = 0.0D;
/*     */   
/* 106 */   private double height = 0.0D;
/*     */   
/* 107 */   private transient Rectangle2D bounds = new Rectangle2D.Float();
/*     */   
/* 108 */   private RectangleInsets margin = RectangleInsets.ZERO_INSETS;
/*     */   
/* 109 */   private BlockBorder border = BlockBorder.NONE;
/*     */   
/* 110 */   private RectangleInsets padding = RectangleInsets.ZERO_INSETS;
/*     */   
/*     */   private static final long serialVersionUID = 7689852412141274563L;
/*     */   
/*     */   public String getID() {
/* 119 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setID(String id) {
/* 128 */     this.id = id;
/*     */   }
/*     */   
/*     */   public double getWidth() {
/* 139 */     return this.width;
/*     */   }
/*     */   
/*     */   public void setWidth(double width) {
/* 148 */     this.width = width;
/*     */   }
/*     */   
/*     */   public double getHeight() {
/* 159 */     return this.height;
/*     */   }
/*     */   
/*     */   public void setHeight(double height) {
/* 168 */     this.height = height;
/*     */   }
/*     */   
/*     */   public RectangleInsets getMargin() {
/* 177 */     return this.margin;
/*     */   }
/*     */   
/*     */   public void setMargin(RectangleInsets margin) {
/* 187 */     if (margin == null)
/* 188 */       throw new IllegalArgumentException("Null 'margin' argument."); 
/* 190 */     this.margin = margin;
/*     */   }
/*     */   
/*     */   public void setMargin(double top, double left, double bottom, double right) {
/* 203 */     setMargin(new RectangleInsets(top, left, bottom, right));
/*     */   }
/*     */   
/*     */   public BlockBorder getBorder() {
/* 212 */     return this.border;
/*     */   }
/*     */   
/*     */   public void setBorder(BlockBorder border) {
/* 222 */     if (border == null)
/* 223 */       throw new IllegalArgumentException("Null 'border' argument."); 
/* 225 */     this.border = border;
/*     */   }
/*     */   
/*     */   public void setBorder(double top, double left, double bottom, double right) {
/* 238 */     setBorder(new BlockBorder(top, left, bottom, right));
/*     */   }
/*     */   
/*     */   public RectangleInsets getPadding() {
/* 247 */     return this.padding;
/*     */   }
/*     */   
/*     */   public void setPadding(RectangleInsets padding) {
/* 257 */     if (padding == null)
/* 258 */       throw new IllegalArgumentException("Null 'padding' argument."); 
/* 260 */     this.padding = padding;
/*     */   }
/*     */   
/*     */   public double getContentXOffset() {
/* 264 */     return this.margin.getLeft() + this.border.getInsets().getLeft() + this.padding.getLeft();
/*     */   }
/*     */   
/*     */   public double getContentYOffset() {
/* 268 */     return this.margin.getTop() + this.border.getInsets().getTop() + this.padding.getTop();
/*     */   }
/*     */   
/*     */   public void setPadding(double top, double left, double bottom, double right) {
/* 281 */     setPadding(new RectangleInsets(top, left, bottom, right));
/*     */   }
/*     */   
/*     */   public Size2D arrange(Graphics2D g2) {
/* 293 */     return arrange(g2, RectangleConstraint.NONE);
/*     */   }
/*     */   
/*     */   public Size2D arrange(Graphics2D g2, RectangleConstraint constraint) {
/* 306 */     Size2D base = new Size2D(getWidth(), getHeight());
/* 307 */     return constraint.calculateConstrainedSize(base);
/*     */   }
/*     */   
/*     */   public Rectangle2D getBounds() {
/* 316 */     return this.bounds;
/*     */   }
/*     */   
/*     */   public void setBounds(Rectangle2D bounds) {
/* 325 */     if (bounds == null)
/* 326 */       throw new IllegalArgumentException("Null 'bounds' argument."); 
/* 328 */     this.bounds = bounds;
/*     */   }
/*     */   
/*     */   protected double trimToContentWidth(double fixedWidth) {
/* 341 */     double result = this.margin.trimWidth(fixedWidth);
/* 342 */     result = this.border.getInsets().trimWidth(result);
/* 343 */     result = this.padding.trimWidth(result);
/* 344 */     return Math.max(result, 0.0D);
/*     */   }
/*     */   
/*     */   protected double trimToContentHeight(double fixedHeight) {
/* 357 */     double result = this.margin.trimHeight(fixedHeight);
/* 358 */     result = this.border.getInsets().trimHeight(result);
/* 359 */     result = this.padding.trimHeight(result);
/* 360 */     return Math.max(result, 0.0D);
/*     */   }
/*     */   
/*     */   protected RectangleConstraint toContentConstraint(RectangleConstraint c) {
/* 372 */     if (c == null)
/* 373 */       throw new IllegalArgumentException("Null 'c' argument."); 
/* 375 */     if (c.equals(RectangleConstraint.NONE))
/* 376 */       return c; 
/* 378 */     double w = c.getWidth();
/* 379 */     Range wr = c.getWidthRange();
/* 380 */     double h = c.getHeight();
/* 381 */     Range hr = c.getHeightRange();
/* 382 */     double ww = trimToContentWidth(w);
/* 383 */     double hh = trimToContentHeight(h);
/* 384 */     Range wwr = trimToContentWidth(wr);
/* 385 */     Range hhr = trimToContentHeight(hr);
/* 386 */     return new RectangleConstraint(ww, wwr, c.getWidthConstraintType(), hh, hhr, c.getHeightConstraintType());
/*     */   }
/*     */   
/*     */   private Range trimToContentWidth(Range r) {
/* 393 */     if (r == null)
/* 394 */       return null; 
/* 396 */     double lowerBound = 0.0D;
/* 397 */     double upperBound = Double.POSITIVE_INFINITY;
/* 398 */     if (r.getLowerBound() > 0.0D)
/* 399 */       lowerBound = trimToContentWidth(r.getLowerBound()); 
/* 401 */     if (r.getUpperBound() < Double.POSITIVE_INFINITY)
/* 402 */       upperBound = trimToContentWidth(r.getUpperBound()); 
/* 404 */     return new Range(lowerBound, upperBound);
/*     */   }
/*     */   
/*     */   private Range trimToContentHeight(Range r) {
/* 408 */     if (r == null)
/* 409 */       return null; 
/* 411 */     double lowerBound = 0.0D;
/* 412 */     double upperBound = Double.POSITIVE_INFINITY;
/* 413 */     if (r.getLowerBound() > 0.0D)
/* 414 */       lowerBound = trimToContentHeight(r.getLowerBound()); 
/* 416 */     if (r.getUpperBound() < Double.POSITIVE_INFINITY)
/* 417 */       upperBound = trimToContentHeight(r.getUpperBound()); 
/* 419 */     return new Range(lowerBound, upperBound);
/*     */   }
/*     */   
/*     */   protected double calculateTotalWidth(double contentWidth) {
/* 430 */     double result = contentWidth;
/* 431 */     result = this.padding.extendWidth(result);
/* 432 */     result = this.border.getInsets().extendWidth(result);
/* 433 */     result = this.margin.extendWidth(result);
/* 434 */     return result;
/*     */   }
/*     */   
/*     */   protected double calculateTotalHeight(double contentHeight) {
/* 445 */     double result = contentHeight;
/* 446 */     result = this.padding.extendHeight(result);
/* 447 */     result = this.border.getInsets().extendHeight(result);
/* 448 */     result = this.margin.extendHeight(result);
/* 449 */     return result;
/*     */   }
/*     */   
/*     */   protected Rectangle2D trimMargin(Rectangle2D area) {
/* 462 */     this.margin.trim(area);
/* 463 */     return area;
/*     */   }
/*     */   
/*     */   protected Rectangle2D trimBorder(Rectangle2D area) {
/* 476 */     this.border.getInsets().trim(area);
/* 477 */     return area;
/*     */   }
/*     */   
/*     */   protected Rectangle2D trimPadding(Rectangle2D area) {
/* 490 */     this.padding.trim(area);
/* 491 */     return area;
/*     */   }
/*     */   
/*     */   protected void drawBorder(Graphics2D g2, Rectangle2D area) {
/* 501 */     this.border.draw(g2, area);
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 512 */     if (obj == this)
/* 513 */       return true; 
/* 515 */     if (!(obj instanceof AbstractBlock))
/* 516 */       return false; 
/* 518 */     AbstractBlock that = (AbstractBlock)obj;
/* 519 */     if (!this.border.equals(that.border))
/* 520 */       return false; 
/* 522 */     if (!this.bounds.equals(that.bounds))
/* 523 */       return false; 
/* 525 */     if (!this.margin.equals(that.margin))
/* 526 */       return false; 
/* 528 */     if (!this.padding.equals(that.padding))
/* 529 */       return false; 
/* 531 */     if (this.height != that.height)
/* 532 */       return false; 
/* 534 */     if (this.width != that.width)
/* 535 */       return false; 
/* 537 */     return true;
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream stream) throws IOException {
/* 548 */     stream.defaultWriteObject();
/* 549 */     SerialUtilities.writeShape(this.bounds, stream);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
/* 562 */     stream.defaultReadObject();
/* 563 */     this.bounds = (Rectangle2D)SerialUtilities.readShape(stream);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\block\AbstractBlock.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */