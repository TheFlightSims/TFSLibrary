/*     */ package org.jfree.util;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Polygon;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Arc2D;
/*     */ import java.awt.geom.Ellipse2D;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.Line2D;
/*     */ import java.awt.geom.PathIterator;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.util.Arrays;
/*     */ import org.jfree.ui.RectangleAnchor;
/*     */ 
/*     */ public class ShapeUtilities {
/*     */   public static Shape clone(Shape shape) {
/* 108 */     if (shape instanceof Cloneable)
/*     */       try {
/* 110 */         return (Shape)ObjectUtilities.clone(shape);
/* 112 */       } catch (CloneNotSupportedException cnse) {} 
/* 115 */     Shape result = null;
/* 116 */     return result;
/*     */   }
/*     */   
/*     */   public static boolean equal(Shape s1, Shape s2) {
/* 133 */     if (s1 instanceof Line2D && s2 instanceof Line2D)
/* 134 */       return equal((Line2D)s1, (Line2D)s2); 
/* 136 */     if (s1 instanceof Ellipse2D && s2 instanceof Ellipse2D)
/* 137 */       return equal((Ellipse2D)s1, (Ellipse2D)s2); 
/* 139 */     if (s1 instanceof Arc2D && s2 instanceof Arc2D)
/* 140 */       return equal((Arc2D)s1, (Arc2D)s2); 
/* 142 */     if (s1 instanceof Polygon && s2 instanceof Polygon)
/* 143 */       return equal((Polygon)s1, (Polygon)s2); 
/* 145 */     if (s1 instanceof GeneralPath && s2 instanceof GeneralPath)
/* 146 */       return equal((GeneralPath)s1, (GeneralPath)s2); 
/* 150 */     return ObjectUtilities.equal(s1, s2);
/*     */   }
/*     */   
/*     */   public static boolean equal(Line2D l1, Line2D l2) {
/* 164 */     if (l1 == null)
/* 165 */       return (l2 == null); 
/* 167 */     if (l2 == null)
/* 168 */       return false; 
/* 170 */     if (!l1.getP1().equals(l2.getP1()))
/* 171 */       return false; 
/* 173 */     if (!l1.getP2().equals(l2.getP2()))
/* 174 */       return false; 
/* 176 */     return true;
/*     */   }
/*     */   
/*     */   public static boolean equal(Ellipse2D e1, Ellipse2D e2) {
/* 189 */     if (e1 == null)
/* 190 */       return (e2 == null); 
/* 192 */     if (e2 == null)
/* 193 */       return false; 
/* 195 */     if (!e1.getFrame().equals(e2.getFrame()))
/* 196 */       return false; 
/* 198 */     return true;
/*     */   }
/*     */   
/*     */   public static boolean equal(Arc2D a1, Arc2D a2) {
/* 211 */     if (a1 == null)
/* 212 */       return (a2 == null); 
/* 214 */     if (a2 == null)
/* 215 */       return false; 
/* 217 */     if (!a1.getFrame().equals(a2.getFrame()))
/* 218 */       return false; 
/* 220 */     if (a1.getAngleStart() != a2.getAngleStart())
/* 221 */       return false; 
/* 223 */     if (a1.getAngleExtent() != a2.getAngleExtent())
/* 224 */       return false; 
/* 226 */     if (a1.getArcType() != a2.getArcType())
/* 227 */       return false; 
/* 229 */     return true;
/*     */   }
/*     */   
/*     */   public static boolean equal(Polygon p1, Polygon p2) {
/* 242 */     if (p1 == null)
/* 243 */       return (p2 == null); 
/* 245 */     if (p2 == null)
/* 246 */       return false; 
/* 248 */     if (p1.npoints != p2.npoints)
/* 249 */       return false; 
/* 251 */     if (!Arrays.equals(p1.xpoints, p2.xpoints))
/* 252 */       return false; 
/* 254 */     if (!Arrays.equals(p1.ypoints, p2.ypoints))
/* 255 */       return false; 
/* 257 */     return true;
/*     */   }
/*     */   
/*     */   public static boolean equal(GeneralPath p1, GeneralPath p2) {
/* 270 */     if (p1 == null)
/* 271 */       return (p2 == null); 
/* 273 */     if (p2 == null)
/* 274 */       return false; 
/* 276 */     if (p1.getWindingRule() != p2.getWindingRule())
/* 277 */       return false; 
/* 279 */     PathIterator iterator1 = p1.getPathIterator((AffineTransform)null);
/* 280 */     PathIterator iterator2 = p1.getPathIterator((AffineTransform)null);
/* 281 */     double[] d1 = new double[6];
/* 282 */     double[] d2 = new double[6];
/* 283 */     boolean done = (iterator1.isDone() && iterator2.isDone());
/* 284 */     while (!done) {
/* 285 */       if (iterator1.isDone() != iterator2.isDone())
/* 286 */         return false; 
/* 288 */       int seg1 = iterator1.currentSegment(d1);
/* 289 */       int seg2 = iterator2.currentSegment(d2);
/* 290 */       if (seg1 != seg2)
/* 291 */         return false; 
/* 293 */       if (!Arrays.equals(d1, d2))
/* 294 */         return false; 
/* 296 */       iterator1.next();
/* 297 */       iterator2.next();
/* 298 */       done = (iterator1.isDone() && iterator2.isDone());
/*     */     } 
/* 300 */     return true;
/*     */   }
/*     */   
/*     */   public static Shape createTranslatedShape(Shape shape, double transX, double transY) {
/* 315 */     if (shape == null)
/* 316 */       throw new IllegalArgumentException("Null 'shape' argument."); 
/* 318 */     AffineTransform transform = AffineTransform.getTranslateInstance(transX, transY);
/* 321 */     return transform.createTransformedShape(shape);
/*     */   }
/*     */   
/*     */   public static Shape createTranslatedShape(Shape shape, RectangleAnchor anchor, double locationX, double locationY) {
/* 340 */     if (shape == null)
/* 341 */       throw new IllegalArgumentException("Null 'shape' argument."); 
/* 343 */     if (anchor == null)
/* 344 */       throw new IllegalArgumentException("Null 'anchor' argument."); 
/* 346 */     Point2D anchorPoint = RectangleAnchor.coordinates(shape.getBounds2D(), anchor);
/* 349 */     AffineTransform transform = AffineTransform.getTranslateInstance(locationX - anchorPoint.getX(), locationY - anchorPoint.getY());
/* 352 */     return transform.createTransformedShape(shape);
/*     */   }
/*     */   
/*     */   public static Shape rotateShape(Shape base, double angle, float x, float y) {
/* 368 */     if (base == null)
/* 369 */       return null; 
/* 371 */     AffineTransform rotate = AffineTransform.getRotateInstance(angle, x, y);
/* 374 */     Shape result = rotate.createTransformedShape(base);
/* 375 */     return result;
/*     */   }
/*     */   
/*     */   public static void drawRotatedShape(Graphics2D g2, Shape shape, double angle, float x, float y) {
/* 391 */     AffineTransform saved = g2.getTransform();
/* 392 */     AffineTransform rotate = AffineTransform.getRotateInstance(angle, x, y);
/* 395 */     g2.transform(rotate);
/* 396 */     g2.draw(shape);
/* 397 */     g2.setTransform(saved);
/*     */   }
/*     */   
/* 402 */   private static final float SQRT2 = (float)Math.pow(2.0D, 0.5D);
/*     */   
/*     */   public static Shape createDiagonalCross(float l, float t) {
/* 413 */     GeneralPath p0 = new GeneralPath();
/* 414 */     p0.moveTo(-l - t, -l + t);
/* 415 */     p0.lineTo(-l + t, -l - t);
/* 416 */     p0.lineTo(0.0F, -t * SQRT2);
/* 417 */     p0.lineTo(l - t, -l - t);
/* 418 */     p0.lineTo(l + t, -l + t);
/* 419 */     p0.lineTo(t * SQRT2, 0.0F);
/* 420 */     p0.lineTo(l + t, l - t);
/* 421 */     p0.lineTo(l - t, l + t);
/* 422 */     p0.lineTo(0.0F, t * SQRT2);
/* 423 */     p0.lineTo(-l + t, l + t);
/* 424 */     p0.lineTo(-l - t, l - t);
/* 425 */     p0.lineTo(-t * SQRT2, 0.0F);
/* 426 */     p0.closePath();
/* 427 */     return p0;
/*     */   }
/*     */   
/*     */   public static Shape createRegularCross(float l, float t) {
/* 439 */     GeneralPath p0 = new GeneralPath();
/* 440 */     p0.moveTo(-l, t);
/* 441 */     p0.lineTo(-t, t);
/* 442 */     p0.lineTo(-t, l);
/* 443 */     p0.lineTo(t, l);
/* 444 */     p0.lineTo(t, t);
/* 445 */     p0.lineTo(l, t);
/* 446 */     p0.lineTo(l, -t);
/* 447 */     p0.lineTo(t, -t);
/* 448 */     p0.lineTo(t, -l);
/* 449 */     p0.lineTo(-t, -l);
/* 450 */     p0.lineTo(-t, -t);
/* 451 */     p0.lineTo(-l, -t);
/* 452 */     p0.closePath();
/* 453 */     return p0;
/*     */   }
/*     */   
/*     */   public static Shape createDiamond(float s) {
/* 464 */     GeneralPath p0 = new GeneralPath();
/* 465 */     p0.moveTo(0.0F, -s);
/* 466 */     p0.lineTo(s, 0.0F);
/* 467 */     p0.lineTo(0.0F, s);
/* 468 */     p0.lineTo(-s, 0.0F);
/* 469 */     p0.closePath();
/* 470 */     return p0;
/*     */   }
/*     */   
/*     */   public static Shape createUpTriangle(float s) {
/* 481 */     GeneralPath p0 = new GeneralPath();
/* 482 */     p0.moveTo(0.0F, -s);
/* 483 */     p0.lineTo(s, s);
/* 484 */     p0.lineTo(-s, s);
/* 485 */     p0.closePath();
/* 486 */     return p0;
/*     */   }
/*     */   
/*     */   public static Shape createDownTriangle(float s) {
/* 497 */     GeneralPath p0 = new GeneralPath();
/* 498 */     p0.moveTo(0.0F, s);
/* 499 */     p0.lineTo(s, -s);
/* 500 */     p0.lineTo(-s, -s);
/* 501 */     p0.closePath();
/* 502 */     return p0;
/*     */   }
/*     */   
/*     */   public static Shape createLineRegion(Line2D line, float width) {
/* 516 */     GeneralPath result = new GeneralPath();
/* 517 */     float x1 = (float)line.getX1();
/* 518 */     float x2 = (float)line.getX2();
/* 519 */     float y1 = (float)line.getY1();
/* 520 */     float y2 = (float)line.getY2();
/* 521 */     if ((x2 - x1) != 0.0D) {
/* 522 */       double theta = Math.atan(((y2 - y1) / (x2 - x1)));
/* 523 */       float dx = (float)Math.sin(theta) * width;
/* 524 */       float dy = (float)Math.cos(theta) * width;
/* 525 */       result.moveTo(x1 - dx, y1 + dy);
/* 526 */       result.lineTo(x1 + dx, y1 - dy);
/* 527 */       result.lineTo(x2 + dx, y2 - dy);
/* 528 */       result.lineTo(x2 - dx, y2 + dy);
/* 529 */       result.closePath();
/*     */     } else {
/* 533 */       result.moveTo(x1 - width / 2.0F, y1);
/* 534 */       result.lineTo(x1 + width / 2.0F, y1);
/* 535 */       result.lineTo(x2 + width / 2.0F, y2);
/* 536 */       result.lineTo(x2 - width / 2.0F, y2);
/* 537 */       result.closePath();
/*     */     } 
/* 539 */     return result;
/*     */   }
/*     */   
/*     */   public static Point2D getPointInRectangle(double x, double y, Rectangle2D area) {
/* 558 */     x = Math.max(area.getMinX(), Math.min(x, area.getMaxX()));
/* 559 */     y = Math.max(area.getMinY(), Math.min(y, area.getMaxY()));
/* 560 */     return new Point2D.Double(x, y);
/*     */   }
/*     */   
/*     */   public static boolean contains(Rectangle2D rect1, Rectangle2D rect2) {
/* 576 */     double x0 = rect1.getX();
/* 577 */     double y0 = rect1.getY();
/* 578 */     double x = rect2.getX();
/* 579 */     double y = rect2.getY();
/* 580 */     double w = rect2.getWidth();
/* 581 */     double h = rect2.getHeight();
/* 583 */     return (x >= x0 && y >= y0 && x + w <= x0 + rect1.getWidth() && y + h <= y0 + rect1.getHeight());
/*     */   }
/*     */   
/*     */   public static boolean intersects(Rectangle2D rect1, Rectangle2D rect2) {
/* 602 */     double x0 = rect1.getX();
/* 603 */     double y0 = rect1.getY();
/* 605 */     double x = rect2.getX();
/* 606 */     double width = rect2.getWidth();
/* 607 */     double y = rect2.getY();
/* 608 */     double height = rect2.getHeight();
/* 609 */     return (x + width >= x0 && y + height >= y0 && x <= x0 + rect1.getWidth() && y <= y0 + rect1.getHeight());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\util\ShapeUtilities.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */