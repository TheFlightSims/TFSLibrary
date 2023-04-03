/*     */ package org.jfree.chart;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Shape;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.Line2D;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.text.AttributedString;
/*     */ import java.text.CharacterIterator;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.util.AttributedStringUtilities;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ import org.jfree.util.ShapeUtilities;
/*     */ 
/*     */ public class LegendItem implements Serializable {
/*     */   private static final long serialVersionUID = -797214582948827144L;
/*     */   
/*     */   private String label;
/*     */   
/*     */   private AttributedString attributedLabel;
/*     */   
/*     */   private String description;
/*     */   
/*     */   private String toolTipText;
/*     */   
/*     */   private String urlText;
/*     */   
/*     */   private boolean shapeVisible;
/*     */   
/*     */   private transient Shape shape;
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
/* 142 */   private static final Shape UNUSED_SHAPE = new Line2D.Float();
/*     */   
/* 148 */   private static final Stroke UNUSED_STROKE = new BasicStroke(0.0F);
/*     */   
/*     */   public LegendItem(String label, String description, String toolTipText, String urlText, Shape shape, Paint fillPaint) {
/* 166 */     this(label, description, toolTipText, urlText, true, shape, true, fillPaint, false, Color.black, UNUSED_STROKE, false, UNUSED_SHAPE, UNUSED_STROKE, Color.black);
/*     */   }
/*     */   
/*     */   public LegendItem(String label, String description, String toolTipText, String urlText, Shape shape, Paint fillPaint, Stroke outlineStroke, Paint outlinePaint) {
/* 195 */     this(label, description, toolTipText, urlText, true, shape, true, fillPaint, true, outlinePaint, outlineStroke, false, UNUSED_SHAPE, UNUSED_STROKE, Color.black);
/*     */   }
/*     */   
/*     */   public LegendItem(String label, String description, String toolTipText, String urlText, Shape line, Stroke lineStroke, Paint linePaint) {
/* 219 */     this(label, description, toolTipText, urlText, false, UNUSED_SHAPE, false, Color.black, false, Color.black, UNUSED_STROKE, true, line, lineStroke, linePaint);
/*     */   }
/*     */   
/*     */   public LegendItem(String label, String description, String toolTipText, String urlText, boolean shapeVisible, Shape shape, boolean shapeFilled, Paint fillPaint, boolean shapeOutlineVisible, Paint outlinePaint, Stroke outlineStroke, boolean lineVisible, Shape line, Stroke lineStroke, Paint linePaint) {
/* 260 */     if (label == null)
/* 261 */       throw new IllegalArgumentException("Null 'label' argument."); 
/* 263 */     if (fillPaint == null)
/* 264 */       throw new IllegalArgumentException("Null 'fillPaint' argument."); 
/* 266 */     if (lineStroke == null)
/* 267 */       throw new IllegalArgumentException("Null 'lineStroke' argument."); 
/* 269 */     if (outlinePaint == null)
/* 270 */       throw new IllegalArgumentException("Null 'outlinePaint' argument."); 
/* 272 */     if (outlineStroke == null)
/* 273 */       throw new IllegalArgumentException("Null 'outlineStroke' argument."); 
/* 276 */     this.label = label;
/* 277 */     this.attributedLabel = null;
/* 278 */     this.description = description;
/* 279 */     this.shapeVisible = shapeVisible;
/* 280 */     this.shape = shape;
/* 281 */     this.shapeFilled = shapeFilled;
/* 282 */     this.fillPaint = fillPaint;
/* 283 */     this.shapeOutlineVisible = shapeOutlineVisible;
/* 284 */     this.outlinePaint = outlinePaint;
/* 285 */     this.outlineStroke = outlineStroke;
/* 286 */     this.lineVisible = lineVisible;
/* 287 */     this.line = line;
/* 288 */     this.lineStroke = lineStroke;
/* 289 */     this.linePaint = linePaint;
/* 290 */     this.toolTipText = toolTipText;
/* 291 */     this.urlText = urlText;
/*     */   }
/*     */   
/*     */   public LegendItem(AttributedString label, String description, String toolTipText, String urlText, Shape shape, Paint fillPaint) {
/* 310 */     this(label, description, toolTipText, urlText, true, shape, true, fillPaint, false, Color.black, UNUSED_STROKE, false, UNUSED_SHAPE, UNUSED_STROKE, Color.black);
/*     */   }
/*     */   
/*     */   public LegendItem(AttributedString label, String description, String toolTipText, String urlText, Shape shape, Paint fillPaint, Stroke outlineStroke, Paint outlinePaint) {
/* 339 */     this(label, description, toolTipText, urlText, true, shape, true, fillPaint, true, outlinePaint, outlineStroke, false, UNUSED_SHAPE, UNUSED_STROKE, Color.black);
/*     */   }
/*     */   
/*     */   public LegendItem(AttributedString label, String description, String toolTipText, String urlText, Shape line, Stroke lineStroke, Paint linePaint) {
/* 362 */     this(label, description, toolTipText, urlText, false, UNUSED_SHAPE, false, Color.black, false, Color.black, UNUSED_STROKE, true, line, lineStroke, linePaint);
/*     */   }
/*     */   
/*     */   public LegendItem(AttributedString label, String description, String toolTipText, String urlText, boolean shapeVisible, Shape shape, boolean shapeFilled, Paint fillPaint, boolean shapeOutlineVisible, Paint outlinePaint, Stroke outlineStroke, boolean lineVisible, Shape line, Stroke lineStroke, Paint linePaint) {
/* 404 */     if (label == null)
/* 405 */       throw new IllegalArgumentException("Null 'label' argument."); 
/* 407 */     if (fillPaint == null)
/* 408 */       throw new IllegalArgumentException("Null 'fillPaint' argument."); 
/* 410 */     if (lineStroke == null)
/* 411 */       throw new IllegalArgumentException("Null 'lineStroke' argument."); 
/* 413 */     if (outlinePaint == null)
/* 414 */       throw new IllegalArgumentException("Null 'outlinePaint' argument."); 
/* 416 */     if (outlineStroke == null)
/* 417 */       throw new IllegalArgumentException("Null 'outlineStroke' argument."); 
/* 420 */     this.label = characterIteratorToString(label.getIterator());
/* 421 */     this.attributedLabel = label;
/* 422 */     this.description = description;
/* 423 */     this.shapeVisible = shapeVisible;
/* 424 */     this.shape = shape;
/* 425 */     this.shapeFilled = shapeFilled;
/* 426 */     this.fillPaint = fillPaint;
/* 427 */     this.shapeOutlineVisible = shapeOutlineVisible;
/* 428 */     this.outlinePaint = outlinePaint;
/* 429 */     this.outlineStroke = outlineStroke;
/* 430 */     this.lineVisible = lineVisible;
/* 431 */     this.line = line;
/* 432 */     this.lineStroke = lineStroke;
/* 433 */     this.linePaint = linePaint;
/* 434 */     this.toolTipText = toolTipText;
/* 435 */     this.urlText = urlText;
/*     */   }
/*     */   
/*     */   private String characterIteratorToString(CharacterIterator iterator) {
/* 446 */     int endIndex = iterator.getEndIndex();
/* 447 */     int beginIndex = iterator.getBeginIndex();
/* 448 */     int count = endIndex - beginIndex;
/* 449 */     if (count <= 0)
/* 450 */       return ""; 
/* 452 */     char[] chars = new char[count];
/* 453 */     int i = 0;
/* 454 */     char c = iterator.first();
/* 455 */     while (c != Character.MAX_VALUE) {
/* 456 */       chars[i] = c;
/* 457 */       i++;
/* 458 */       c = iterator.next();
/*     */     } 
/* 460 */     return new String(chars);
/*     */   }
/*     */   
/*     */   public String getLabel() {
/* 469 */     return this.label;
/*     */   }
/*     */   
/*     */   public AttributedString getAttributedLabel() {
/* 478 */     return this.attributedLabel;
/*     */   }
/*     */   
/*     */   public String getDescription() {
/* 487 */     return this.description;
/*     */   }
/*     */   
/*     */   public String getToolTipText() {
/* 496 */     return this.toolTipText;
/*     */   }
/*     */   
/*     */   public String getURLText() {
/* 505 */     return this.urlText;
/*     */   }
/*     */   
/*     */   public boolean isShapeVisible() {
/* 514 */     return this.shapeVisible;
/*     */   }
/*     */   
/*     */   public Shape getShape() {
/* 524 */     return this.shape;
/*     */   }
/*     */   
/*     */   public boolean isShapeFilled() {
/* 533 */     return this.shapeFilled;
/*     */   }
/*     */   
/*     */   public Paint getFillPaint() {
/* 542 */     return this.fillPaint;
/*     */   }
/*     */   
/*     */   public boolean isShapeOutlineVisible() {
/* 552 */     return this.shapeOutlineVisible;
/*     */   }
/*     */   
/*     */   public Stroke getLineStroke() {
/* 561 */     return this.lineStroke;
/*     */   }
/*     */   
/*     */   public Paint getLinePaint() {
/* 570 */     return this.linePaint;
/*     */   }
/*     */   
/*     */   public Paint getOutlinePaint() {
/* 579 */     return this.outlinePaint;
/*     */   }
/*     */   
/*     */   public Stroke getOutlineStroke() {
/* 588 */     return this.outlineStroke;
/*     */   }
/*     */   
/*     */   public boolean isLineVisible() {
/* 597 */     return this.lineVisible;
/*     */   }
/*     */   
/*     */   public Shape getLine() {
/* 606 */     return this.line;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 617 */     if (obj == this)
/* 618 */       return true; 
/* 620 */     if (!(obj instanceof LegendItem))
/* 621 */       return false; 
/* 623 */     LegendItem that = (LegendItem)obj;
/* 624 */     if (!this.label.equals(that.label))
/* 625 */       return false; 
/* 627 */     if (!AttributedStringUtilities.equal(this.attributedLabel, that.attributedLabel))
/* 629 */       return false; 
/* 631 */     if (!ObjectUtilities.equal(this.description, that.description))
/* 632 */       return false; 
/* 634 */     if (this.shapeVisible != that.shapeVisible)
/* 635 */       return false; 
/* 637 */     if (!ShapeUtilities.equal(this.shape, that.shape))
/* 638 */       return false; 
/* 640 */     if (this.shapeFilled != that.shapeFilled)
/* 641 */       return false; 
/* 643 */     if (!this.fillPaint.equals(that.fillPaint))
/* 644 */       return false; 
/* 646 */     if (this.shapeOutlineVisible != that.shapeOutlineVisible)
/* 647 */       return false; 
/* 649 */     if (!this.outlineStroke.equals(that.outlineStroke))
/* 650 */       return false; 
/* 652 */     if (!this.outlinePaint.equals(that.outlinePaint))
/* 653 */       return false; 
/* 655 */     if ((!this.lineVisible) == that.lineVisible)
/* 656 */       return false; 
/* 658 */     if (!ShapeUtilities.equal(this.line, that.line))
/* 659 */       return false; 
/* 661 */     if (!this.lineStroke.equals(that.lineStroke))
/* 662 */       return false; 
/* 664 */     if (!this.linePaint.equals(that.linePaint))
/* 665 */       return false; 
/* 667 */     return true;
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream stream) throws IOException {
/* 678 */     stream.defaultWriteObject();
/* 679 */     SerialUtilities.writeShape(this.shape, stream);
/* 680 */     SerialUtilities.writePaint(this.fillPaint, stream);
/* 681 */     SerialUtilities.writeStroke(this.outlineStroke, stream);
/* 682 */     SerialUtilities.writePaint(this.outlinePaint, stream);
/* 683 */     SerialUtilities.writeShape(this.line, stream);
/* 684 */     SerialUtilities.writeStroke(this.lineStroke, stream);
/* 685 */     SerialUtilities.writePaint(this.linePaint, stream);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
/* 698 */     stream.defaultReadObject();
/* 699 */     this.shape = SerialUtilities.readShape(stream);
/* 700 */     this.fillPaint = SerialUtilities.readPaint(stream);
/* 701 */     this.outlineStroke = SerialUtilities.readStroke(stream);
/* 702 */     this.outlinePaint = SerialUtilities.readPaint(stream);
/* 703 */     this.line = SerialUtilities.readShape(stream);
/* 704 */     this.lineStroke = SerialUtilities.readStroke(stream);
/* 705 */     this.linePaint = SerialUtilities.readPaint(stream);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\LegendItem.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */