/*     */ package org.jfree.chart.title;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Shape;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import org.jfree.chart.block.AbstractBlock;
/*     */ import org.jfree.chart.block.Block;
/*     */ import org.jfree.chart.block.LengthConstraintType;
/*     */ import org.jfree.chart.block.RectangleConstraint;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.ui.RectangleAnchor;
/*     */ import org.jfree.ui.Size2D;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ import org.jfree.util.PaintUtilities;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ import org.jfree.util.ShapeUtilities;
/*     */ 
/*     */ public class LegendGraphic extends AbstractBlock implements Block, PublicCloneable {
/*     */   private boolean shapeVisible;
/*     */   
/*     */   private transient Shape shape;
/*     */   
/*     */   private RectangleAnchor shapeLocation;
/*     */   
/*     */   private RectangleAnchor shapeAnchor;
/*     */   
/*     */   private boolean shapeFilled;
/*     */   
/*     */   private transient Paint fillPaint;
/*     */   
/*     */   private boolean shapeOutlineVisible;
/*     */   
/*     */   private transient Paint outlinePaint;
/*     */   
/*     */   private transient Stroke outlineStroke;
/*     */   
/*     */   private boolean lineVisible;
/*     */   
/*     */   private transient Shape line;
/*     */   
/*     */   private transient Stroke lineStroke;
/*     */   
/*     */   private transient Paint linePaint;
/*     */   
/*     */   public LegendGraphic(Shape shape, Paint fillPaint) {
/* 138 */     if (shape == null)
/* 139 */       throw new IllegalArgumentException("Null 'shape' argument."); 
/* 141 */     if (fillPaint == null)
/* 142 */       throw new IllegalArgumentException("Null 'fillPaint' argument."); 
/* 144 */     this.shapeVisible = true;
/* 145 */     this.shape = shape;
/* 146 */     this.shapeAnchor = RectangleAnchor.CENTER;
/* 147 */     this.shapeLocation = RectangleAnchor.CENTER;
/* 148 */     this.shapeFilled = true;
/* 149 */     this.fillPaint = fillPaint;
/* 150 */     setPadding(2.0D, 2.0D, 2.0D, 2.0D);
/*     */   }
/*     */   
/*     */   public boolean isShapeVisible() {
/* 160 */     return this.shapeVisible;
/*     */   }
/*     */   
/*     */   public void setShapeVisible(boolean visible) {
/* 170 */     this.shapeVisible = visible;
/*     */   }
/*     */   
/*     */   public Shape getShape() {
/* 179 */     return this.shape;
/*     */   }
/*     */   
/*     */   public void setShape(Shape shape) {
/* 188 */     this.shape = shape;
/*     */   }
/*     */   
/*     */   public boolean isShapeFilled() {
/* 198 */     return this.shapeFilled;
/*     */   }
/*     */   
/*     */   public void setShapeFilled(boolean filled) {
/* 208 */     this.shapeFilled = filled;
/*     */   }
/*     */   
/*     */   public Paint getFillPaint() {
/* 217 */     return this.fillPaint;
/*     */   }
/*     */   
/*     */   public void setFillPaint(Paint paint) {
/* 226 */     this.fillPaint = paint;
/*     */   }
/*     */   
/*     */   public boolean isShapeOutlineVisible() {
/* 235 */     return this.shapeOutlineVisible;
/*     */   }
/*     */   
/*     */   public void setShapeOutlineVisible(boolean visible) {
/* 245 */     this.shapeOutlineVisible = visible;
/*     */   }
/*     */   
/*     */   public Paint getOutlinePaint() {
/* 254 */     return this.outlinePaint;
/*     */   }
/*     */   
/*     */   public void setOutlinePaint(Paint paint) {
/* 263 */     this.outlinePaint = paint;
/*     */   }
/*     */   
/*     */   public Stroke getOutlineStroke() {
/* 272 */     return this.outlineStroke;
/*     */   }
/*     */   
/*     */   public void setOutlineStroke(Stroke stroke) {
/* 281 */     this.outlineStroke = stroke;
/*     */   }
/*     */   
/*     */   public RectangleAnchor getShapeAnchor() {
/* 290 */     return this.shapeAnchor;
/*     */   }
/*     */   
/*     */   public void setShapeAnchor(RectangleAnchor anchor) {
/* 300 */     if (anchor == null)
/* 301 */       throw new IllegalArgumentException("Null 'anchor' argument."); 
/* 303 */     this.shapeAnchor = anchor;
/*     */   }
/*     */   
/*     */   public RectangleAnchor getShapeLocation() {
/* 312 */     return this.shapeLocation;
/*     */   }
/*     */   
/*     */   public void setShapeLocation(RectangleAnchor location) {
/* 322 */     if (location == null)
/* 323 */       throw new IllegalArgumentException("Null 'location' argument."); 
/* 325 */     this.shapeLocation = location;
/*     */   }
/*     */   
/*     */   public boolean isLineVisible() {
/* 334 */     return this.lineVisible;
/*     */   }
/*     */   
/*     */   public void setLineVisible(boolean visible) {
/* 343 */     this.lineVisible = visible;
/*     */   }
/*     */   
/*     */   public Shape getLine() {
/* 352 */     return this.line;
/*     */   }
/*     */   
/*     */   public void setLine(Shape line) {
/* 362 */     this.line = line;
/*     */   }
/*     */   
/*     */   public Paint getLinePaint() {
/* 371 */     return this.linePaint;
/*     */   }
/*     */   
/*     */   public void setLinePaint(Paint paint) {
/* 380 */     this.linePaint = paint;
/*     */   }
/*     */   
/*     */   public Stroke getLineStroke() {
/* 389 */     return this.lineStroke;
/*     */   }
/*     */   
/*     */   public void setLineStroke(Stroke stroke) {
/* 398 */     this.lineStroke = stroke;
/*     */   }
/*     */   
/*     */   public Size2D arrange(Graphics2D g2, RectangleConstraint constraint) {
/* 411 */     RectangleConstraint contentConstraint = toContentConstraint(constraint);
/* 412 */     LengthConstraintType w = contentConstraint.getWidthConstraintType();
/* 413 */     LengthConstraintType h = contentConstraint.getHeightConstraintType();
/* 414 */     Size2D contentSize = null;
/* 415 */     if (w == LengthConstraintType.NONE) {
/* 416 */       if (h == LengthConstraintType.NONE) {
/* 417 */         contentSize = arrangeNN(g2);
/*     */       } else {
/* 419 */         if (h == LengthConstraintType.RANGE)
/* 420 */           throw new RuntimeException("Not yet implemented."); 
/* 422 */         if (h == LengthConstraintType.FIXED)
/* 423 */           throw new RuntimeException("Not yet implemented."); 
/*     */       } 
/* 426 */     } else if (w == LengthConstraintType.RANGE) {
/* 427 */       if (h == LengthConstraintType.NONE)
/* 428 */         throw new RuntimeException("Not yet implemented."); 
/* 430 */       if (h == LengthConstraintType.RANGE)
/* 431 */         throw new RuntimeException("Not yet implemented."); 
/* 433 */       if (h == LengthConstraintType.FIXED)
/* 434 */         throw new RuntimeException("Not yet implemented."); 
/* 437 */     } else if (w == LengthConstraintType.FIXED) {
/* 438 */       if (h == LengthConstraintType.NONE)
/* 439 */         throw new RuntimeException("Not yet implemented."); 
/* 441 */       if (h == LengthConstraintType.RANGE)
/* 442 */         throw new RuntimeException("Not yet implemented."); 
/* 444 */       if (h == LengthConstraintType.FIXED)
/* 445 */         contentSize = new Size2D(contentConstraint.getWidth(), contentConstraint.getHeight()); 
/*     */     } 
/* 451 */     return new Size2D(calculateTotalWidth(contentSize.getWidth()), calculateTotalHeight(contentSize.getHeight()));
/*     */   }
/*     */   
/*     */   protected Size2D arrangeNN(Graphics2D g2) {
/* 467 */     Rectangle2D contentSize = new Rectangle2D.Double();
/* 468 */     if (this.line != null)
/* 469 */       contentSize.setRect(this.line.getBounds2D()); 
/* 471 */     if (this.shape != null)
/* 472 */       contentSize = contentSize.createUnion(this.shape.getBounds2D()); 
/* 474 */     return new Size2D(contentSize.getWidth(), contentSize.getHeight());
/*     */   }
/*     */   
/*     */   public void draw(Graphics2D g2, Rectangle2D area) {
/* 485 */     area = trimMargin(area);
/* 486 */     drawBorder(g2, area);
/* 487 */     area = trimBorder(area);
/* 488 */     area = trimPadding(area);
/* 490 */     if (this.lineVisible) {
/* 491 */       Point2D location = RectangleAnchor.coordinates(area, this.shapeLocation);
/* 494 */       Shape aLine = ShapeUtilities.createTranslatedShape(getLine(), this.shapeAnchor, location.getX(), location.getY());
/* 497 */       g2.setPaint(this.linePaint);
/* 498 */       g2.setStroke(this.lineStroke);
/* 499 */       g2.draw(aLine);
/*     */     } 
/* 502 */     if (this.shapeVisible) {
/* 503 */       Point2D location = RectangleAnchor.coordinates(area, this.shapeLocation);
/* 507 */       Shape s = ShapeUtilities.createTranslatedShape(this.shape, this.shapeAnchor, location.getX(), location.getY());
/* 510 */       if (this.shapeFilled) {
/* 511 */         g2.setPaint(this.fillPaint);
/* 512 */         g2.fill(s);
/*     */       } 
/* 514 */       if (this.shapeOutlineVisible) {
/* 515 */         g2.setPaint(this.outlinePaint);
/* 516 */         g2.setStroke(this.outlineStroke);
/* 517 */         g2.draw(s);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object draw(Graphics2D g2, Rectangle2D area, Object params) {
/* 533 */     draw(g2, area);
/* 534 */     return null;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 546 */     if (!(obj instanceof LegendGraphic))
/* 547 */       return false; 
/* 549 */     LegendGraphic that = (LegendGraphic)obj;
/* 550 */     if (this.shapeVisible != that.shapeVisible)
/* 551 */       return false; 
/* 553 */     if (!ShapeUtilities.equal(this.shape, that.shape))
/* 554 */       return false; 
/* 556 */     if (this.shapeFilled != that.shapeFilled)
/* 557 */       return false; 
/* 559 */     if (!PaintUtilities.equal(this.fillPaint, that.fillPaint))
/* 560 */       return false; 
/* 562 */     if (this.shapeOutlineVisible != that.shapeOutlineVisible)
/* 563 */       return false; 
/* 565 */     if (!PaintUtilities.equal(this.outlinePaint, that.outlinePaint))
/* 566 */       return false; 
/* 568 */     if (!ObjectUtilities.equal(this.outlineStroke, that.outlineStroke))
/* 569 */       return false; 
/* 571 */     if (this.shapeAnchor != that.shapeAnchor)
/* 572 */       return false; 
/* 574 */     if (this.shapeLocation != that.shapeLocation)
/* 575 */       return false; 
/* 577 */     if (this.lineVisible != that.lineVisible)
/* 578 */       return false; 
/* 580 */     if (!ShapeUtilities.equal(this.line, that.line))
/* 581 */       return false; 
/* 583 */     if (!PaintUtilities.equal(this.linePaint, that.linePaint))
/* 584 */       return false; 
/* 586 */     if (!ObjectUtilities.equal(this.lineStroke, that.lineStroke))
/* 587 */       return false; 
/* 589 */     if (!super.equals(obj))
/* 590 */       return false; 
/* 592 */     return true;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 603 */     return super.clone();
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream stream) throws IOException {
/* 614 */     stream.defaultWriteObject();
/* 615 */     SerialUtilities.writeShape(this.shape, stream);
/* 616 */     SerialUtilities.writePaint(this.fillPaint, stream);
/* 617 */     SerialUtilities.writePaint(this.outlinePaint, stream);
/* 618 */     SerialUtilities.writeStroke(this.outlineStroke, stream);
/* 619 */     SerialUtilities.writeShape(this.line, stream);
/* 620 */     SerialUtilities.writePaint(this.linePaint, stream);
/* 621 */     SerialUtilities.writeStroke(this.lineStroke, stream);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
/* 635 */     stream.defaultReadObject();
/* 636 */     this.shape = SerialUtilities.readShape(stream);
/* 637 */     this.fillPaint = SerialUtilities.readPaint(stream);
/* 638 */     this.outlinePaint = SerialUtilities.readPaint(stream);
/* 639 */     this.outlineStroke = SerialUtilities.readStroke(stream);
/* 640 */     this.line = SerialUtilities.readShape(stream);
/* 641 */     this.linePaint = SerialUtilities.readPaint(stream);
/* 642 */     this.lineStroke = SerialUtilities.readStroke(stream);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\title\LegendGraphic.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */