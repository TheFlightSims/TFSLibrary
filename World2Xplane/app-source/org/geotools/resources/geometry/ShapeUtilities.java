/*     */ package org.geotools.resources.geometry;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.CubicCurve2D;
/*     */ import java.awt.geom.Ellipse2D;
/*     */ import java.awt.geom.Line2D;
/*     */ import java.awt.geom.PathIterator;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.QuadCurve2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ 
/*     */ public final class ShapeUtilities {
/*     */   private static final double EPS = 1.0E-6D;
/*     */   
/*     */   public static final int PARALLEL = 0;
/*     */   
/*     */   public static final int HORIZONTAL = 1;
/*     */   
/*     */   public static Point2D intersectionPoint(Line2D a, Line2D b) {
/*  82 */     return intersectionPoint(a.getX1(), a.getY1(), a.getX2(), a.getY2(), b.getX1(), b.getY1(), b.getX2(), b.getY2());
/*     */   }
/*     */   
/*     */   public static Point2D intersectionPoint(double ax1, double ay1, double ax2, double ay2, double bx1, double by1, double bx2, double by2) {
/* 108 */     ax2 -= ax1;
/* 109 */     ay2 -= ay1;
/* 110 */     bx2 -= bx1;
/* 111 */     by2 -= by1;
/* 112 */     double x = ay2 * bx2;
/* 113 */     double y = ax2 * by2;
/* 119 */     x = ((by1 - ay1) * ax2 * bx2 + x * ax1 - y * bx1) / (x - y);
/* 120 */     y = (Math.abs(bx2) > Math.abs(ax2)) ? (by2 / bx2 * (x - bx1) + by1) : (ay2 / ax2 * (x - ax1) + ay1);
/* 128 */     if (ax2 == 0.0D || ((ax2 < 0.0D) ? (x <= ax1 && x >= ax1 + ax2) : (x >= ax1 && x <= ax1 + ax2))) {
/* 129 */       if (bx2 == 0.0D || ((bx2 < 0.0D) ? (x <= bx1 && x >= bx1 + bx2) : (x >= bx1 && x <= bx1 + bx2))) {
/* 130 */         if (ay2 == 0.0D || ((ay2 < 0.0D) ? (y <= ay1 && y >= ay1 + ay2) : (y >= ay1 && y <= ay1 + ay2))) {
/* 131 */           if (by2 == 0.0D || ((by2 < 0.0D) ? (y <= by1 && y >= by1 + by2) : (y >= by1 && y <= by1 + by2)))
/* 132 */             return new Point2D.Double(x, y); 
/*     */           return null;
/*     */         } 
/*     */         return null;
/*     */       } 
/*     */       return null;
/*     */     } 
/*     */     return null;
/*     */   }
/*     */   
/*     */   public static Point2D nearestColinearPoint(Line2D segment, Point2D point) {
/* 157 */     return nearestColinearPoint(segment.getX1(), segment.getY1(), segment.getX2(), segment.getY2(), point.getX(), point.getY());
/*     */   }
/*     */   
/*     */   public static Point2D nearestColinearPoint(double x1, double y1, double x2, double y2, double x, double y) {
/* 193 */     double slope = (y2 - y1) / (x2 - x1);
/* 194 */     if (!Double.isInfinite(slope)) {
/* 195 */       double y0 = y2 - slope * x2;
/* 196 */       x = ((y - y0) * slope + x) / (slope * slope + 1.0D);
/* 197 */       y = x * slope + y0;
/*     */     } else {
/* 199 */       x = x2;
/*     */     } 
/* 201 */     if (x1 <= x2) {
/* 202 */       if (x < x1)
/* 202 */         x = x1; 
/* 203 */       if (x > x2)
/* 203 */         x = x2; 
/*     */     } else {
/* 205 */       if (x > x1)
/* 205 */         x = x1; 
/* 206 */       if (x < x2)
/* 206 */         x = x2; 
/*     */     } 
/* 208 */     if (y1 <= y2) {
/* 209 */       if (y < y1)
/* 209 */         y = y1; 
/* 210 */       if (y > y2)
/* 210 */         y = y2; 
/*     */     } else {
/* 212 */       if (y > y1)
/* 212 */         y = y1; 
/* 213 */       if (y < y2)
/* 213 */         y = y2; 
/*     */     } 
/* 215 */     return new Point2D.Double(x, y);
/*     */   }
/*     */   
/*     */   public static Point2D colinearPoint(Line2D line, Point2D point, double distance) {
/* 245 */     return colinearPoint(line.getX1(), line.getY1(), line.getX2(), line.getY2(), point.getX(), point.getY(), distance);
/*     */   }
/*     */   
/*     */   public static Point2D colinearPoint(double x1, double y1, double x2, double y2, double x, double y, double distance) {
/*     */     boolean in1;
/*     */     int i;
/*     */     boolean in2;
/*     */     int j;
/* 283 */     double ox1 = x1;
/* 284 */     double oy1 = y1;
/* 285 */     double ox2 = x2;
/* 286 */     double oy2 = y2;
/* 287 */     distance *= distance;
/* 288 */     if (x1 == x2) {
/* 289 */       double dy = x1 - x;
/* 290 */       dy = Math.sqrt(distance - dy * dy);
/* 291 */       y1 = y - dy;
/* 292 */       y2 = y + dy;
/* 293 */     } else if (y1 == y2) {
/* 294 */       double dx = y1 - y;
/* 295 */       dx = Math.sqrt(distance - dx * dx);
/* 296 */       x1 = x - dx;
/* 297 */       x2 = x + dx;
/*     */     } else {
/* 299 */       double m = (y1 - y2) / (x2 - x1);
/* 300 */       double y0 = y2 - y + m * (x2 - x);
/* 301 */       double B = m * y0;
/* 302 */       double A = m * m + 1.0D;
/* 303 */       double C = Math.sqrt(B * B + A * (distance - y0 * y0));
/* 304 */       x1 = (B + C) / A;
/* 305 */       x2 = (B - C) / A;
/* 306 */       y1 = y + y0 - m * x1;
/* 307 */       y2 = y + y0 - m * x2;
/* 308 */       x1 += x;
/* 309 */       x2 += x;
/*     */     } 
/* 312 */     if (oy1 > oy2) {
/* 313 */       in1 = (y1 <= oy1 && y1 >= oy2);
/* 314 */       in2 = (y2 <= oy1 && y2 >= oy2);
/*     */     } else {
/* 316 */       in1 = (y1 >= oy1 && y1 <= oy2);
/* 317 */       in2 = (y2 >= oy1 && y2 <= oy2);
/*     */     } 
/* 319 */     if (ox1 > ox2) {
/* 320 */       i = in1 & ((x1 <= ox1 && x1 >= ox2) ? 1 : 0);
/* 321 */       j = in2 & ((x2 <= ox1 && x2 >= ox2) ? 1 : 0);
/*     */     } else {
/* 323 */       i &= (x1 >= ox1 && x1 <= ox2) ? 1 : 0;
/* 324 */       j &= (x2 >= ox1 && x2 <= ox2) ? 1 : 0;
/*     */     } 
/* 326 */     if (i == 0 && j == 0)
/* 326 */       return null; 
/* 327 */     if (i == 0)
/* 327 */       return new Point2D.Double(x2, y2); 
/* 328 */     if (j == 0)
/* 328 */       return new Point2D.Double(x1, y1); 
/* 329 */     x = x1 - ox1;
/* 330 */     y = y1 - oy1;
/* 331 */     double d1 = x * x + y * y;
/* 332 */     x = x2 - ox1;
/* 333 */     y = y2 - oy1;
/* 334 */     double d2 = x * x + y * y;
/* 335 */     if (d1 > d2)
/* 335 */       return new Point2D.Double(x2, y2); 
/* 336 */     return new Point2D.Double(x1, y1);
/*     */   }
/*     */   
/*     */   public static QuadCurve2D fitParabol(Point2D P0, Point2D P1, Point2D P2, int orientation) throws IllegalArgumentException {
/* 364 */     return fitParabol(P0.getX(), P0.getY(), P1.getX(), P1.getY(), P2.getX(), P2.getY(), orientation);
/*     */   }
/*     */   
/*     */   public static QuadCurve2D fitParabol(double x0, double y0, double x1, double y1, double x2, double y2, int orientation) throws IllegalArgumentException {
/* 397 */     Point2D p = parabolicControlPoint(x0, y0, x1, y1, x2, y2, orientation, null);
/* 398 */     return (p != null) ? new QuadCurve2D.Double(x0, y0, p.getX(), p.getY(), x2, y2) : null;
/*     */   }
/*     */   
/*     */   public static Point2D parabolicControlPoint(double x0, double y0, double x1, double y1, double x2, double y2, int orientation, Point2D dest) throws IllegalArgumentException {
/*     */     double rx2, a, ry2, check, x, b, y, d1;
/* 442 */     x1 -= x0;
/* 443 */     y1 -= y0;
/* 444 */     x2 -= x0;
/* 445 */     y2 -= y0;
/* 446 */     switch (orientation) {
/*     */       case 0:
/* 452 */         rx2 = x2;
/* 453 */         ry2 = y2;
/* 454 */         x2 = Math.hypot(x2, y2);
/* 455 */         y2 = (x1 * rx2 + y1 * ry2) / x2;
/* 456 */         y1 = (y1 * rx2 - x1 * ry2) / x2;
/* 457 */         x1 = y2;
/* 458 */         y2 = 0.0D;
/* 463 */         x = 0.5D;
/* 464 */         y = y1 * 0.5D * x2 / x1 * (x2 - x1);
/* 465 */         d1 = Math.abs(y);
/* 466 */         if (d1 > 1000000.0D)
/* 466 */           return null; 
/* 467 */         if (d1 < 1.0E-6D)
/* 467 */           return null; 
/* 472 */         x1 = 0.5D * rx2 - y * ry2 + x0;
/* 473 */         y1 = y * rx2 + 0.5D * ry2 + y0;
/*     */         break;
/*     */       case 1:
/* 477 */         a = (y2 - y1 * x2 / x1) / (x2 - x1);
/* 478 */         check = Math.abs(a);
/* 479 */         if (check > 1000000.0D)
/* 479 */           return null; 
/* 480 */         if (check < 1.0E-6D)
/* 480 */           return null; 
/* 481 */         b = y2 / x2 - a;
/* 482 */         x1 = (1.0D + b / 2.0D * a) * x2 - y2 / 2.0D * a;
/* 483 */         y1 = y0 + b * x1;
/* 484 */         x1 += x0;
/*     */         break;
/*     */       default:
/* 487 */         throw new IllegalArgumentException();
/*     */     } 
/* 489 */     if (dest != null) {
/* 490 */       dest.setLocation(x1, y1);
/* 491 */       return dest;
/*     */     } 
/* 493 */     return new Point2D.Double(x1, y1);
/*     */   }
/*     */   
/*     */   public static Ellipse2D fitCircle(Point2D P1, Point2D P2, Point2D P3) {
/* 507 */     Point2D center = circleCentre(P1.getX(), P1.getY(), P2.getX(), P2.getY(), P3.getX(), P3.getY());
/* 510 */     double radius = center.distance(P2);
/* 511 */     return new Ellipse2D.Double(center.getX() - radius, center.getY() - radius, 2.0D * radius, 2.0D * radius);
/*     */   }
/*     */   
/*     */   public static Point2D circleCentre(double x1, double y1, double x2, double y2, double x3, double y3) {
/* 539 */     x2 -= x1;
/* 540 */     x3 -= x1;
/* 541 */     y2 -= y1;
/* 542 */     y3 -= y1;
/* 543 */     double sq2 = x2 * x2 + y2 * y2;
/* 544 */     double sq3 = x3 * x3 + y3 * y3;
/* 545 */     double x = (y2 * sq3 - y3 * sq2) / (y2 * x3 - y3 * x2);
/* 546 */     return new Point2D.Double(x1 + 0.5D * x, y1 + 0.5D * (sq2 - x * x2) / y2);
/*     */   }
/*     */   
/*     */   public static Shape toPrimitive(Shape path) {
/* 559 */     float[] buffer = new float[6];
/* 560 */     PathIterator it = path.getPathIterator(null);
/* 561 */     if (!it.isDone() && it.currentSegment(buffer) == 0 && !it.isDone()) {
/* 562 */       float x1 = buffer[0];
/* 563 */       float y1 = buffer[1];
/* 564 */       int code = it.currentSegment(buffer);
/* 565 */       if (it.isDone())
/* 566 */         switch (code) {
/*     */           case 1:
/* 567 */             return new Line2D.Float(x1, y1, buffer[0], buffer[1]);
/*     */           case 2:
/* 568 */             return new QuadCurve2D.Float(x1, y1, buffer[0], buffer[1], buffer[2], buffer[3]);
/*     */           case 3:
/* 569 */             return new CubicCurve2D.Float(x1, y1, buffer[0], buffer[1], buffer[2], buffer[3], buffer[4], buffer[5]);
/*     */         }  
/*     */     } 
/* 573 */     return path;
/*     */   }
/*     */   
/*     */   public static double getFlatness(Shape shape) {
/* 584 */     Rectangle2D bounds = shape.getBounds2D();
/* 585 */     double dx = bounds.getWidth();
/* 586 */     double dy = bounds.getHeight();
/* 587 */     return Math.max(0.025D * Math.min(dx, dy), 0.001D * Math.max(dx, dy));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\resources\geometry\ShapeUtilities.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */