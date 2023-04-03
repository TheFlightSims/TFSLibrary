/*     */ package org.jfree.text;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.ui.RectangleAnchor;
/*     */ import org.jfree.ui.RectangleInsets;
/*     */ import org.jfree.ui.Size2D;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ 
/*     */ public class TextBox implements Serializable {
/*     */   private static final long serialVersionUID = 3360220213180203706L;
/*     */   
/*     */   private transient Paint outlinePaint;
/*     */   
/*     */   private transient Stroke outlineStroke;
/*     */   
/*     */   private RectangleInsets interiorGap;
/*     */   
/*     */   private transient Paint backgroundPaint;
/*     */   
/*     */   private transient Paint shadowPaint;
/*     */   
/*  93 */   private double shadowXOffset = 2.0D;
/*     */   
/*  96 */   private double shadowYOffset = 2.0D;
/*     */   
/*     */   private TextBlock textBlock;
/*     */   
/*     */   public TextBox() {
/* 105 */     this((TextBlock)null);
/*     */   }
/*     */   
/*     */   public TextBox(String text) {
/* 114 */     this((TextBlock)null);
/* 115 */     if (text != null) {
/* 116 */       this.textBlock = new TextBlock();
/* 117 */       this.textBlock.addLine(text, new Font("SansSerif", 0, 10), Color.black);
/*     */     } 
/*     */   }
/*     */   
/*     */   public TextBox(TextBlock block) {
/* 130 */     this.outlinePaint = Color.black;
/* 131 */     this.outlineStroke = new BasicStroke(1.0F);
/* 132 */     this.interiorGap = new RectangleInsets(1.0D, 3.0D, 1.0D, 3.0D);
/* 133 */     this.backgroundPaint = new Color(255, 255, 192);
/* 134 */     this.shadowPaint = Color.gray;
/* 135 */     this.shadowXOffset = 2.0D;
/* 136 */     this.shadowYOffset = 2.0D;
/* 137 */     this.textBlock = block;
/*     */   }
/*     */   
/*     */   public Paint getOutlinePaint() {
/* 146 */     return this.outlinePaint;
/*     */   }
/*     */   
/*     */   public void setOutlinePaint(Paint paint) {
/* 155 */     this.outlinePaint = paint;
/*     */   }
/*     */   
/*     */   public Stroke getOutlineStroke() {
/* 164 */     return this.outlineStroke;
/*     */   }
/*     */   
/*     */   public void setOutlineStroke(Stroke stroke) {
/* 173 */     this.outlineStroke = stroke;
/*     */   }
/*     */   
/*     */   public RectangleInsets getInteriorGap() {
/* 182 */     return this.interiorGap;
/*     */   }
/*     */   
/*     */   public void setInteriorGap(RectangleInsets gap) {
/* 191 */     this.interiorGap = gap;
/*     */   }
/*     */   
/*     */   public Paint getBackgroundPaint() {
/* 200 */     return this.backgroundPaint;
/*     */   }
/*     */   
/*     */   public void setBackgroundPaint(Paint paint) {
/* 209 */     this.backgroundPaint = paint;
/*     */   }
/*     */   
/*     */   public Paint getShadowPaint() {
/* 218 */     return this.shadowPaint;
/*     */   }
/*     */   
/*     */   public void setShadowPaint(Paint paint) {
/* 227 */     this.shadowPaint = paint;
/*     */   }
/*     */   
/*     */   public double getShadowXOffset() {
/* 236 */     return this.shadowXOffset;
/*     */   }
/*     */   
/*     */   public void setShadowXOffset(double offset) {
/* 245 */     this.shadowXOffset = offset;
/*     */   }
/*     */   
/*     */   public double getShadowYOffset() {
/* 254 */     return this.shadowYOffset;
/*     */   }
/*     */   
/*     */   public void setShadowYOffset(double offset) {
/* 263 */     this.shadowYOffset = offset;
/*     */   }
/*     */   
/*     */   public TextBlock getTextBlock() {
/* 272 */     return this.textBlock;
/*     */   }
/*     */   
/*     */   public void setTextBlock(TextBlock block) {
/* 281 */     this.textBlock = block;
/*     */   }
/*     */   
/*     */   public void draw(Graphics2D g2, float x, float y, RectangleAnchor anchor) {
/* 295 */     Size2D d1 = this.textBlock.calculateDimensions(g2);
/* 296 */     double w = this.interiorGap.extendWidth(d1.getWidth());
/* 297 */     double h = this.interiorGap.extendHeight(d1.getHeight());
/* 298 */     Size2D d2 = new Size2D(w, h);
/* 299 */     Rectangle2D bounds = RectangleAnchor.createRectangle(d2, x, y, anchor);
/* 302 */     if (this.shadowPaint != null) {
/* 303 */       Rectangle2D shadow = new Rectangle2D.Double(bounds.getX() + this.shadowXOffset, bounds.getY() + this.shadowYOffset, bounds.getWidth(), bounds.getHeight());
/* 308 */       g2.setPaint(this.shadowPaint);
/* 309 */       g2.fill(shadow);
/*     */     } 
/* 311 */     if (this.backgroundPaint != null) {
/* 312 */       g2.setPaint(this.backgroundPaint);
/* 313 */       g2.fill(bounds);
/*     */     } 
/* 316 */     if (this.outlinePaint != null && this.outlineStroke != null) {
/* 317 */       g2.setPaint(this.outlinePaint);
/* 318 */       g2.setStroke(this.outlineStroke);
/* 319 */       g2.draw(bounds);
/*     */     } 
/* 322 */     this.textBlock.draw(g2, (float)bounds.getCenterX(), (float)bounds.getCenterY(), TextBlockAnchor.CENTER);
/*     */   }
/*     */   
/*     */   public double getHeight(Graphics2D g2) {
/* 337 */     Size2D d = this.textBlock.calculateDimensions(g2);
/* 338 */     return this.interiorGap.extendHeight(d.getHeight());
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 349 */     if (obj == this)
/* 350 */       return true; 
/* 352 */     if (!(obj instanceof TextBox))
/* 353 */       return false; 
/* 355 */     TextBox that = (TextBox)obj;
/* 356 */     if (!ObjectUtilities.equal(this.outlinePaint, that.outlinePaint))
/* 357 */       return false; 
/* 359 */     if (!ObjectUtilities.equal(this.outlineStroke, that.outlineStroke))
/* 360 */       return false; 
/* 362 */     if (!ObjectUtilities.equal(this.interiorGap, that.interiorGap))
/* 363 */       return false; 
/* 365 */     if (!ObjectUtilities.equal(this.backgroundPaint, that.backgroundPaint))
/* 367 */       return false; 
/* 369 */     if (!ObjectUtilities.equal(this.shadowPaint, that.shadowPaint))
/* 370 */       return false; 
/* 372 */     if (this.shadowXOffset != that.shadowXOffset)
/* 373 */       return false; 
/* 375 */     if (this.shadowYOffset != that.shadowYOffset)
/* 376 */       return false; 
/* 378 */     if (!ObjectUtilities.equal(this.textBlock, that.textBlock))
/* 379 */       return false; 
/* 382 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 393 */     int result = (this.outlinePaint != null) ? this.outlinePaint.hashCode() : 0;
/* 394 */     result = 29 * result + ((this.outlineStroke != null) ? this.outlineStroke.hashCode() : 0);
/* 396 */     result = 29 * result + ((this.interiorGap != null) ? this.interiorGap.hashCode() : 0);
/* 398 */     result = 29 * result + ((this.backgroundPaint != null) ? this.backgroundPaint.hashCode() : 0);
/* 400 */     result = 29 * result + ((this.shadowPaint != null) ? this.shadowPaint.hashCode() : 0);
/* 402 */     long temp = (this.shadowXOffset != 0.0D) ? Double.doubleToLongBits(this.shadowXOffset) : 0L;
/* 404 */     result = 29 * result + (int)(temp ^ temp >>> 32L);
/* 405 */     temp = (this.shadowYOffset != 0.0D) ? Double.doubleToLongBits(this.shadowYOffset) : 0L;
/* 407 */     result = 29 * result + (int)(temp ^ temp >>> 32L);
/* 408 */     result = 29 * result + ((this.textBlock != null) ? this.textBlock.hashCode() : 0);
/* 410 */     return result;
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream stream) throws IOException {
/* 422 */     stream.defaultWriteObject();
/* 423 */     SerialUtilities.writePaint(this.outlinePaint, stream);
/* 424 */     SerialUtilities.writeStroke(this.outlineStroke, stream);
/* 425 */     SerialUtilities.writePaint(this.backgroundPaint, stream);
/* 426 */     SerialUtilities.writePaint(this.shadowPaint, stream);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
/* 439 */     stream.defaultReadObject();
/* 440 */     this.outlinePaint = SerialUtilities.readPaint(stream);
/* 441 */     this.outlineStroke = SerialUtilities.readStroke(stream);
/* 442 */     this.backgroundPaint = SerialUtilities.readPaint(stream);
/* 443 */     this.shadowPaint = SerialUtilities.readPaint(stream);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\text\TextBox.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */