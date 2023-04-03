/*     */ package org.jfree.chart.plot;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Polygon;
/*     */ import java.awt.Shape;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.Ellipse2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Arrays;
/*     */ import org.jfree.chart.ChartColor;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ import org.jfree.util.ShapeUtilities;
/*     */ 
/*     */ public class DefaultDrawingSupplier implements DrawingSupplier, Cloneable, PublicCloneable, Serializable {
/*     */   private static final long serialVersionUID = -7339847061039422538L;
/*     */   
/*  83 */   public static final Paint[] DEFAULT_PAINT_SEQUENCE = ChartColor.createDefaultPaintArray();
/*     */   
/*  87 */   public static final Paint[] DEFAULT_OUTLINE_PAINT_SEQUENCE = new Paint[] { Color.lightGray };
/*     */   
/*  92 */   public static final Stroke[] DEFAULT_STROKE_SEQUENCE = new Stroke[] { new BasicStroke(1.0F, 2, 2) };
/*     */   
/*  99 */   public static final Stroke[] DEFAULT_OUTLINE_STROKE_SEQUENCE = new Stroke[] { new BasicStroke(1.0F, 2, 2) };
/*     */   
/* 107 */   public static final Shape[] DEFAULT_SHAPE_SEQUENCE = createStandardSeriesShapes();
/*     */   
/*     */   private transient Paint[] paintSequence;
/*     */   
/*     */   private int paintIndex;
/*     */   
/*     */   private transient Paint[] outlinePaintSequence;
/*     */   
/*     */   private int outlinePaintIndex;
/*     */   
/*     */   private transient Stroke[] strokeSequence;
/*     */   
/*     */   private int strokeIndex;
/*     */   
/*     */   private transient Stroke[] outlineStrokeSequence;
/*     */   
/*     */   private int outlineStrokeIndex;
/*     */   
/*     */   private transient Shape[] shapeSequence;
/*     */   
/*     */   private int shapeIndex;
/*     */   
/*     */   public DefaultDrawingSupplier() {
/* 146 */     this(DEFAULT_PAINT_SEQUENCE, DEFAULT_OUTLINE_PAINT_SEQUENCE, DEFAULT_STROKE_SEQUENCE, DEFAULT_OUTLINE_STROKE_SEQUENCE, DEFAULT_SHAPE_SEQUENCE);
/*     */   }
/*     */   
/*     */   public DefaultDrawingSupplier(Paint[] paintSequence, Paint[] outlinePaintSequence, Stroke[] strokeSequence, Stroke[] outlineStrokeSequence, Shape[] shapeSequence) {
/* 169 */     this.paintSequence = paintSequence;
/* 170 */     this.outlinePaintSequence = outlinePaintSequence;
/* 171 */     this.strokeSequence = strokeSequence;
/* 172 */     this.outlineStrokeSequence = outlineStrokeSequence;
/* 173 */     this.shapeSequence = shapeSequence;
/*     */   }
/*     */   
/*     */   public Paint getNextPaint() {
/* 183 */     Paint result = this.paintSequence[this.paintIndex % this.paintSequence.length];
/* 185 */     this.paintIndex++;
/* 186 */     return result;
/*     */   }
/*     */   
/*     */   public Paint getNextOutlinePaint() {
/* 195 */     Paint result = this.outlinePaintSequence[this.outlinePaintIndex % this.outlinePaintSequence.length];
/* 198 */     this.outlinePaintIndex++;
/* 199 */     return result;
/*     */   }
/*     */   
/*     */   public Stroke getNextStroke() {
/* 208 */     Stroke result = this.strokeSequence[this.strokeIndex % this.strokeSequence.length];
/* 211 */     this.strokeIndex++;
/* 212 */     return result;
/*     */   }
/*     */   
/*     */   public Stroke getNextOutlineStroke() {
/* 221 */     Stroke result = this.outlineStrokeSequence[this.outlineStrokeIndex % this.outlineStrokeSequence.length];
/* 224 */     this.outlineStrokeIndex++;
/* 225 */     return result;
/*     */   }
/*     */   
/*     */   public Shape getNextShape() {
/* 234 */     Shape result = this.shapeSequence[this.shapeIndex % this.shapeSequence.length];
/* 237 */     this.shapeIndex++;
/* 238 */     return result;
/*     */   }
/*     */   
/*     */   public static Shape[] createStandardSeriesShapes() {
/* 249 */     Shape[] result = new Shape[10];
/* 251 */     double size = 6.0D;
/* 252 */     double delta = size / 2.0D;
/* 253 */     int[] xpoints = null;
/* 254 */     int[] ypoints = null;
/* 257 */     result[0] = new Rectangle2D.Double(-delta, -delta, size, size);
/* 259 */     result[1] = new Ellipse2D.Double(-delta, -delta, size, size);
/* 262 */     xpoints = intArray(0.0D, delta, -delta);
/* 263 */     ypoints = intArray(-delta, delta, delta);
/* 264 */     result[2] = new Polygon(xpoints, ypoints, 3);
/* 267 */     xpoints = intArray(0.0D, delta, 0.0D, -delta);
/* 268 */     ypoints = intArray(-delta, 0.0D, delta, 0.0D);
/* 269 */     result[3] = new Polygon(xpoints, ypoints, 4);
/* 272 */     result[4] = new Rectangle2D.Double(-delta, -delta / 2.0D, size, size / 2.0D);
/* 275 */     xpoints = intArray(-delta, delta, 0.0D);
/* 276 */     ypoints = intArray(-delta, -delta, delta);
/* 277 */     result[5] = new Polygon(xpoints, ypoints, 3);
/* 280 */     result[6] = new Ellipse2D.Double(-delta, -delta / 2.0D, size, size / 2.0D);
/* 283 */     xpoints = intArray(-delta, delta, -delta);
/* 284 */     ypoints = intArray(-delta, 0.0D, delta);
/* 285 */     result[7] = new Polygon(xpoints, ypoints, 3);
/* 288 */     result[8] = new Rectangle2D.Double(-delta / 2.0D, -delta, size / 2.0D, size);
/* 291 */     xpoints = intArray(-delta, delta, delta);
/* 292 */     ypoints = intArray(0.0D, -delta, delta);
/* 293 */     result[9] = new Polygon(xpoints, ypoints, 3);
/* 295 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 308 */     if (obj == this)
/* 309 */       return true; 
/* 312 */     if (!(obj instanceof DefaultDrawingSupplier))
/* 313 */       return false; 
/* 316 */     DefaultDrawingSupplier that = (DefaultDrawingSupplier)obj;
/* 318 */     if (!Arrays.equals((Object[])this.paintSequence, (Object[])that.paintSequence))
/* 319 */       return false; 
/* 321 */     if (this.paintIndex != that.paintIndex)
/* 322 */       return false; 
/* 324 */     if (!Arrays.equals((Object[])this.outlinePaintSequence, (Object[])that.outlinePaintSequence))
/* 326 */       return false; 
/* 328 */     if (this.outlinePaintIndex != that.outlinePaintIndex)
/* 329 */       return false; 
/* 331 */     if (!Arrays.equals((Object[])this.strokeSequence, (Object[])that.strokeSequence))
/* 332 */       return false; 
/* 334 */     if (this.strokeIndex != that.strokeIndex)
/* 335 */       return false; 
/* 337 */     if (!Arrays.equals((Object[])this.outlineStrokeSequence, (Object[])that.outlineStrokeSequence))
/* 339 */       return false; 
/* 341 */     if (this.outlineStrokeIndex != that.outlineStrokeIndex)
/* 342 */       return false; 
/* 344 */     if (!equalShapes(this.shapeSequence, that.shapeSequence))
/* 345 */       return false; 
/* 347 */     if (this.shapeIndex != that.shapeIndex)
/* 348 */       return false; 
/* 350 */     return true;
/*     */   }
/*     */   
/*     */   private boolean equalShapes(Shape[] s1, Shape[] s2) {
/* 363 */     if (s1 == null)
/* 364 */       return (s2 == null); 
/* 366 */     if (s2 == null)
/* 367 */       return false; 
/* 369 */     if (s1.length != s2.length)
/* 370 */       return false; 
/* 372 */     for (int i = 0; i < s1.length; i++) {
/* 373 */       if (!ShapeUtilities.equal(s1[i], s2[i]))
/* 374 */         return false; 
/*     */     } 
/* 377 */     return true;
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream stream) throws IOException {
/* 388 */     stream.defaultWriteObject();
/* 390 */     int paintCount = this.paintSequence.length;
/* 391 */     stream.writeInt(paintCount);
/* 392 */     for (int i = 0; i < paintCount; i++)
/* 393 */       SerialUtilities.writePaint(this.paintSequence[i], stream); 
/* 396 */     int outlinePaintCount = this.outlinePaintSequence.length;
/* 397 */     stream.writeInt(outlinePaintCount);
/* 398 */     for (int j = 0; j < outlinePaintCount; j++)
/* 399 */       SerialUtilities.writePaint(this.outlinePaintSequence[j], stream); 
/* 402 */     int strokeCount = this.strokeSequence.length;
/* 403 */     stream.writeInt(strokeCount);
/* 404 */     for (int k = 0; k < strokeCount; k++)
/* 405 */       SerialUtilities.writeStroke(this.strokeSequence[k], stream); 
/* 408 */     int outlineStrokeCount = this.outlineStrokeSequence.length;
/* 409 */     stream.writeInt(outlineStrokeCount);
/* 410 */     for (int m = 0; m < outlineStrokeCount; m++)
/* 411 */       SerialUtilities.writeStroke(this.outlineStrokeSequence[m], stream); 
/* 414 */     int shapeCount = this.shapeSequence.length;
/* 415 */     stream.writeInt(shapeCount);
/* 416 */     for (int n = 0; n < shapeCount; n++)
/* 417 */       SerialUtilities.writeShape(this.shapeSequence[n], stream); 
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
/* 432 */     stream.defaultReadObject();
/* 434 */     int paintCount = stream.readInt();
/* 435 */     this.paintSequence = new Paint[paintCount];
/* 436 */     for (int i = 0; i < paintCount; i++)
/* 437 */       this.paintSequence[i] = SerialUtilities.readPaint(stream); 
/* 440 */     int outlinePaintCount = stream.readInt();
/* 441 */     this.outlinePaintSequence = new Paint[outlinePaintCount];
/* 442 */     for (int j = 0; j < outlinePaintCount; j++)
/* 443 */       this.outlinePaintSequence[j] = SerialUtilities.readPaint(stream); 
/* 446 */     int strokeCount = stream.readInt();
/* 447 */     this.strokeSequence = new Stroke[strokeCount];
/* 448 */     for (int k = 0; k < strokeCount; k++)
/* 449 */       this.strokeSequence[k] = SerialUtilities.readStroke(stream); 
/* 452 */     int outlineStrokeCount = stream.readInt();
/* 453 */     this.outlineStrokeSequence = new Stroke[outlineStrokeCount];
/* 454 */     for (int m = 0; m < outlineStrokeCount; m++)
/* 455 */       this.outlineStrokeSequence[m] = SerialUtilities.readStroke(stream); 
/* 458 */     int shapeCount = stream.readInt();
/* 459 */     this.shapeSequence = new Shape[shapeCount];
/* 460 */     for (int n = 0; n < shapeCount; n++)
/* 461 */       this.shapeSequence[n] = SerialUtilities.readShape(stream); 
/*     */   }
/*     */   
/*     */   private static int[] intArray(double a, double b, double c) {
/* 477 */     return new int[] { (int)a, (int)b, (int)c };
/*     */   }
/*     */   
/*     */   private static int[] intArray(double a, double b, double c, double d) {
/* 492 */     return new int[] { (int)a, (int)b, (int)c, (int)d };
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 504 */     DefaultDrawingSupplier clone = (DefaultDrawingSupplier)super.clone();
/* 505 */     return clone;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\plot\DefaultDrawingSupplier.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */