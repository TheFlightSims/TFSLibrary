/*     */ package org.jfree.chart.plot;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Stroke;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.ui.LengthAdjustmentType;
/*     */ import org.jfree.ui.RectangleAnchor;
/*     */ import org.jfree.ui.RectangleInsets;
/*     */ import org.jfree.ui.TextAnchor;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ import org.jfree.util.PaintUtilities;
/*     */ 
/*     */ public abstract class Marker implements Cloneable, Serializable {
/*     */   private static final long serialVersionUID = -734389651405327166L;
/*     */   
/*     */   private transient Paint paint;
/*     */   
/*     */   private transient Stroke stroke;
/*     */   
/*     */   private transient Paint outlinePaint;
/*     */   
/*     */   private transient Stroke outlineStroke;
/*     */   
/*     */   private float alpha;
/*     */   
/* 105 */   private String label = null;
/*     */   
/*     */   private Font labelFont;
/*     */   
/*     */   private transient Paint labelPaint;
/*     */   
/*     */   private RectangleAnchor labelAnchor;
/*     */   
/*     */   private TextAnchor labelTextAnchor;
/*     */   
/*     */   private RectangleInsets labelOffset;
/*     */   
/*     */   private LengthAdjustmentType labelOffsetType;
/*     */   
/*     */   protected Marker() {
/* 131 */     this(Color.gray);
/*     */   }
/*     */   
/*     */   protected Marker(Paint paint) {
/* 140 */     this(paint, new BasicStroke(0.5F), Color.gray, new BasicStroke(0.5F), 0.8F);
/*     */   }
/*     */   
/*     */   protected Marker(Paint paint, Stroke stroke, Paint outlinePaint, Stroke outlineStroke, float alpha) {
/* 159 */     if (paint == null)
/* 160 */       throw new IllegalArgumentException("Null 'paint' argument."); 
/* 162 */     if (stroke == null)
/* 163 */       throw new IllegalArgumentException("Null 'stroke' argument."); 
/* 166 */     this.paint = paint;
/* 167 */     this.stroke = stroke;
/* 168 */     this.outlinePaint = outlinePaint;
/* 169 */     this.outlineStroke = outlineStroke;
/* 170 */     this.alpha = alpha;
/* 172 */     this.labelFont = new Font("SansSerif", 0, 9);
/* 173 */     this.labelPaint = Color.black;
/* 174 */     this.labelAnchor = RectangleAnchor.TOP_LEFT;
/* 175 */     this.labelOffset = new RectangleInsets(3.0D, 3.0D, 3.0D, 3.0D);
/* 176 */     this.labelOffsetType = LengthAdjustmentType.CONTRACT;
/* 177 */     this.labelTextAnchor = TextAnchor.CENTER;
/*     */   }
/*     */   
/*     */   public Paint getPaint() {
/* 187 */     return this.paint;
/*     */   }
/*     */   
/*     */   public void setPaint(Paint paint) {
/* 196 */     if (paint == null)
/* 197 */       throw new IllegalArgumentException("Null 'paint' argument."); 
/* 199 */     this.paint = paint;
/*     */   }
/*     */   
/*     */   public Stroke getStroke() {
/* 208 */     return this.stroke;
/*     */   }
/*     */   
/*     */   public void setStroke(Stroke stroke) {
/* 217 */     if (stroke == null)
/* 218 */       throw new IllegalArgumentException("Null 'stroke' argument."); 
/* 220 */     this.stroke = stroke;
/*     */   }
/*     */   
/*     */   public Paint getOutlinePaint() {
/* 229 */     return this.outlinePaint;
/*     */   }
/*     */   
/*     */   public void setOutlinePaint(Paint paint) {
/* 238 */     this.outlinePaint = paint;
/*     */   }
/*     */   
/*     */   public Stroke getOutlineStroke() {
/* 247 */     return this.outlineStroke;
/*     */   }
/*     */   
/*     */   public void setOutlineStroke(Stroke stroke) {
/* 256 */     this.outlineStroke = stroke;
/*     */   }
/*     */   
/*     */   public float getAlpha() {
/* 265 */     return this.alpha;
/*     */   }
/*     */   
/*     */   public void setAlpha(float alpha) {
/* 274 */     this.alpha = alpha;
/*     */   }
/*     */   
/*     */   public String getLabel() {
/* 283 */     return this.label;
/*     */   }
/*     */   
/*     */   public void setLabel(String label) {
/* 292 */     this.label = label;
/*     */   }
/*     */   
/*     */   public Font getLabelFont() {
/* 301 */     return this.labelFont;
/*     */   }
/*     */   
/*     */   public void setLabelFont(Font font) {
/* 310 */     if (font == null)
/* 311 */       throw new IllegalArgumentException("Null 'font' argument."); 
/* 313 */     this.labelFont = font;
/*     */   }
/*     */   
/*     */   public Paint getLabelPaint() {
/* 322 */     return this.labelPaint;
/*     */   }
/*     */   
/*     */   public void setLabelPaint(Paint paint) {
/* 331 */     if (paint == null)
/* 332 */       throw new IllegalArgumentException("Null 'paint' argument."); 
/* 334 */     this.labelPaint = paint;
/*     */   }
/*     */   
/*     */   public RectangleAnchor getLabelAnchor() {
/* 343 */     return this.labelAnchor;
/*     */   }
/*     */   
/*     */   public void setLabelAnchor(RectangleAnchor anchor) {
/* 352 */     if (anchor == null)
/* 353 */       throw new IllegalArgumentException("Null 'anchor' argument."); 
/* 355 */     this.labelAnchor = anchor;
/*     */   }
/*     */   
/*     */   public RectangleInsets getLabelOffset() {
/* 364 */     return this.labelOffset;
/*     */   }
/*     */   
/*     */   public void setLabelOffset(RectangleInsets offset) {
/* 373 */     if (offset == null)
/* 374 */       throw new IllegalArgumentException("Null 'offset' argument."); 
/* 376 */     this.labelOffset = offset;
/*     */   }
/*     */   
/*     */   public LengthAdjustmentType getLabelOffsetType() {
/* 385 */     return this.labelOffsetType;
/*     */   }
/*     */   
/*     */   public void setLabelOffsetType(LengthAdjustmentType adj) {
/* 394 */     if (adj == null)
/* 395 */       throw new IllegalArgumentException("Null 'adj' argument."); 
/* 397 */     this.labelOffsetType = adj;
/*     */   }
/*     */   
/*     */   public TextAnchor getLabelTextAnchor() {
/* 406 */     return this.labelTextAnchor;
/*     */   }
/*     */   
/*     */   public void setLabelTextAnchor(TextAnchor anchor) {
/* 415 */     if (anchor == null)
/* 416 */       throw new IllegalArgumentException("Null 'anchor' argument."); 
/* 418 */     this.labelTextAnchor = anchor;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 429 */     if (obj == this)
/* 430 */       return true; 
/* 432 */     if (!(obj instanceof Marker))
/* 433 */       return false; 
/* 435 */     Marker that = (Marker)obj;
/* 436 */     if (!PaintUtilities.equal(this.paint, that.paint))
/* 437 */       return false; 
/* 439 */     if (!ObjectUtilities.equal(this.stroke, that.stroke))
/* 440 */       return false; 
/* 442 */     if (!PaintUtilities.equal(this.outlinePaint, that.outlinePaint))
/* 443 */       return false; 
/* 445 */     if (!ObjectUtilities.equal(this.outlineStroke, that.outlineStroke))
/* 446 */       return false; 
/* 448 */     if (this.alpha != that.alpha)
/* 449 */       return false; 
/* 451 */     if (!ObjectUtilities.equal(this.label, that.label))
/* 452 */       return false; 
/* 454 */     if (!ObjectUtilities.equal(this.labelFont, that.labelFont))
/* 455 */       return false; 
/* 457 */     if (!PaintUtilities.equal(this.labelPaint, that.labelPaint))
/* 458 */       return false; 
/* 460 */     if (this.labelAnchor != that.labelAnchor)
/* 461 */       return false; 
/* 463 */     if (this.labelTextAnchor != that.labelTextAnchor)
/* 464 */       return false; 
/* 466 */     if (!ObjectUtilities.equal(this.labelOffset, that.labelOffset))
/* 467 */       return false; 
/* 469 */     if (!this.labelOffsetType.equals(that.labelOffsetType))
/* 470 */       return false; 
/* 472 */     return true;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 483 */     return super.clone();
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream stream) throws IOException {
/* 494 */     stream.defaultWriteObject();
/* 495 */     SerialUtilities.writePaint(this.paint, stream);
/* 496 */     SerialUtilities.writeStroke(this.stroke, stream);
/* 497 */     SerialUtilities.writePaint(this.outlinePaint, stream);
/* 498 */     SerialUtilities.writeStroke(this.outlineStroke, stream);
/* 499 */     SerialUtilities.writePaint(this.labelPaint, stream);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
/* 512 */     stream.defaultReadObject();
/* 513 */     this.paint = SerialUtilities.readPaint(stream);
/* 514 */     this.stroke = SerialUtilities.readStroke(stream);
/* 515 */     this.outlinePaint = SerialUtilities.readPaint(stream);
/* 516 */     this.outlineStroke = SerialUtilities.readStroke(stream);
/* 517 */     this.labelPaint = SerialUtilities.readPaint(stream);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\plot\Marker.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */