/*     */ package org.jfree.chart.needle;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Shape;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ import org.jfree.util.PaintUtilities;
/*     */ 
/*     */ public abstract class MeterNeedle implements Serializable {
/*     */   private static final long serialVersionUID = 5203064851510951052L;
/*     */   
/*  82 */   private transient Paint outlinePaint = Color.black;
/*     */   
/*  85 */   private transient Stroke outlineStroke = new BasicStroke(2.0F);
/*     */   
/*  88 */   private transient Paint fillPaint = null;
/*     */   
/*  91 */   private transient Paint highlightPaint = null;
/*     */   
/*  94 */   private int size = 5;
/*     */   
/*  97 */   private double rotateX = 0.5D;
/*     */   
/* 100 */   private double rotateY = 0.5D;
/*     */   
/* 103 */   protected static AffineTransform transform = new AffineTransform();
/*     */   
/*     */   public MeterNeedle() {
/* 109 */     this(null, null, null);
/*     */   }
/*     */   
/*     */   public MeterNeedle(Paint outline, Paint fill, Paint highlight) {
/* 120 */     this.fillPaint = fill;
/* 121 */     this.highlightPaint = highlight;
/* 122 */     this.outlinePaint = outline;
/*     */   }
/*     */   
/*     */   public Paint getOutlinePaint() {
/* 131 */     return this.outlinePaint;
/*     */   }
/*     */   
/*     */   public void setOutlinePaint(Paint p) {
/* 140 */     if (p != null)
/* 141 */       this.outlinePaint = p; 
/*     */   }
/*     */   
/*     */   public Stroke getOutlineStroke() {
/* 151 */     return this.outlineStroke;
/*     */   }
/*     */   
/*     */   public void setOutlineStroke(Stroke s) {
/* 160 */     if (s != null)
/* 161 */       this.outlineStroke = s; 
/*     */   }
/*     */   
/*     */   public Paint getFillPaint() {
/* 171 */     return this.fillPaint;
/*     */   }
/*     */   
/*     */   public void setFillPaint(Paint p) {
/* 180 */     if (p != null)
/* 181 */       this.fillPaint = p; 
/*     */   }
/*     */   
/*     */   public Paint getHighlightPaint() {
/* 191 */     return this.highlightPaint;
/*     */   }
/*     */   
/*     */   public void setHighlightPaint(Paint p) {
/* 200 */     if (p != null)
/* 201 */       this.highlightPaint = p; 
/*     */   }
/*     */   
/*     */   public double getRotateX() {
/* 211 */     return this.rotateX;
/*     */   }
/*     */   
/*     */   public void setRotateX(double x) {
/* 220 */     this.rotateX = x;
/*     */   }
/*     */   
/*     */   public void setRotateY(double y) {
/* 229 */     this.rotateY = y;
/*     */   }
/*     */   
/*     */   public double getRotateY() {
/* 238 */     return this.rotateY;
/*     */   }
/*     */   
/*     */   public void draw(Graphics2D g2, Rectangle2D plotArea) {
/* 248 */     draw(g2, plotArea, 0.0D);
/*     */   }
/*     */   
/*     */   public void draw(Graphics2D g2, Rectangle2D plotArea, double angle) {
/* 260 */     Point2D.Double pt = new Point2D.Double();
/* 261 */     pt.setLocation(plotArea.getMinX() + this.rotateX * plotArea.getWidth(), plotArea.getMinY() + this.rotateY * plotArea.getHeight());
/* 265 */     draw(g2, plotArea, pt, angle);
/*     */   }
/*     */   
/*     */   public void draw(Graphics2D g2, Rectangle2D plotArea, Point2D rotate, double angle) {
/* 280 */     Paint savePaint = g2.getColor();
/* 281 */     Stroke saveStroke = g2.getStroke();
/* 283 */     drawNeedle(g2, plotArea, rotate, Math.toRadians(angle));
/* 285 */     g2.setStroke(saveStroke);
/* 286 */     g2.setPaint(savePaint);
/*     */   }
/*     */   
/*     */   protected abstract void drawNeedle(Graphics2D paramGraphics2D, Rectangle2D paramRectangle2D, Point2D paramPoint2D, double paramDouble);
/*     */   
/*     */   protected void defaultDisplay(Graphics2D g2, Shape shape) {
/* 310 */     if (this.fillPaint != null) {
/* 311 */       g2.setPaint(this.fillPaint);
/* 312 */       g2.fill(shape);
/*     */     } 
/* 315 */     if (this.outlinePaint != null) {
/* 316 */       g2.setStroke(this.outlineStroke);
/* 317 */       g2.setPaint(this.outlinePaint);
/* 318 */       g2.draw(shape);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getSize() {
/* 329 */     return this.size;
/*     */   }
/*     */   
/*     */   public void setSize(int pixels) {
/* 338 */     this.size = pixels;
/*     */   }
/*     */   
/*     */   public AffineTransform getTransform() {
/* 347 */     return transform;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 358 */     if (obj == this)
/* 359 */       return true; 
/* 361 */     if (!(obj instanceof MeterNeedle))
/* 362 */       return false; 
/* 364 */     MeterNeedle that = (MeterNeedle)obj;
/* 365 */     if (!PaintUtilities.equal(this.outlinePaint, that.outlinePaint))
/* 366 */       return false; 
/* 368 */     if (!ObjectUtilities.equal(this.outlineStroke, that.outlineStroke))
/* 369 */       return false; 
/* 371 */     if (!PaintUtilities.equal(this.fillPaint, that.fillPaint))
/* 372 */       return false; 
/* 374 */     if (!PaintUtilities.equal(this.highlightPaint, that.highlightPaint))
/* 375 */       return false; 
/* 377 */     if (this.size != that.size)
/* 378 */       return false; 
/* 380 */     if (this.rotateX != that.rotateX)
/* 381 */       return false; 
/* 383 */     if (this.rotateY != that.rotateY)
/* 384 */       return false; 
/* 386 */     return true;
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream stream) throws IOException {
/* 397 */     stream.defaultWriteObject();
/* 398 */     SerialUtilities.writeStroke(this.outlineStroke, stream);
/* 399 */     SerialUtilities.writePaint(this.outlinePaint, stream);
/* 400 */     SerialUtilities.writePaint(this.fillPaint, stream);
/* 401 */     SerialUtilities.writePaint(this.highlightPaint, stream);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
/* 414 */     stream.defaultReadObject();
/* 415 */     this.outlineStroke = SerialUtilities.readStroke(stream);
/* 416 */     this.outlinePaint = SerialUtilities.readPaint(stream);
/* 417 */     this.fillPaint = SerialUtilities.readPaint(stream);
/* 418 */     this.highlightPaint = SerialUtilities.readPaint(stream);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\needle\MeterNeedle.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */