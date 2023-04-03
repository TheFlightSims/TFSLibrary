/*     */ package org.geotools.math;
/*     */ 
/*     */ import java.awt.geom.Line2D;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.io.Serializable;
/*     */ import javax.vecmath.MismatchedSizeException;
/*     */ import org.opengis.util.Cloneable;
/*     */ 
/*     */ public class Line implements Cloneable, Serializable {
/*     */   private static final long serialVersionUID = 2185952238314399110L;
/*     */   
/*     */   private static final double EPS = 1.0E-12D;
/*     */   
/*     */   private double slope;
/*     */   
/*     */   private double y0;
/*     */   
/*     */   private double x0;
/*     */   
/*     */   public Line() {
/*  80 */     this.slope = this.y0 = this.x0 = Double.NaN;
/*     */   }
/*     */   
/*     */   public Line(double slope, double y0) {
/*  93 */     this.slope = slope;
/*  94 */     this.y0 = y0;
/*  95 */     this.x0 = -y0 / slope;
/*     */   }
/*     */   
/*     */   public void setLine(double slope, double y0) {
/* 110 */     this.slope = slope;
/* 111 */     this.y0 = y0;
/* 112 */     this.x0 = -y0 / slope;
/*     */   }
/*     */   
/*     */   public void setLine(Line2D line) {
/* 124 */     setLine(line.getX1(), line.getY1(), line.getX2(), line.getY2());
/*     */   }
/*     */   
/*     */   public void setLine(Point2D p1, Point2D p2) {
/* 137 */     setLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
/*     */   }
/*     */   
/*     */   private void setLine(double x1, double y1, double x2, double y2) {
/* 153 */     this.slope = (y2 - y1) / (x2 - x1);
/* 154 */     this.x0 = x2 - y2 / this.slope;
/* 155 */     this.y0 = y2 - this.slope * x2;
/* 156 */     if (Double.isNaN(this.x0) && this.slope == 0.0D)
/* 158 */       this.x0 = Double.POSITIVE_INFINITY; 
/* 160 */     if (Double.isNaN(this.y0) && Double.isInfinite(this.slope))
/* 162 */       this.y0 = Double.POSITIVE_INFINITY; 
/*     */   }
/*     */   
/*     */   public double setLine(double[] x, double[] y) throws MismatchedSizeException {
/* 184 */     int N = x.length;
/* 185 */     if (N != y.length)
/* 186 */       throw new MismatchedSizeException(); 
/* 188 */     int count = 0;
/* 189 */     double mean_x = 0.0D;
/* 190 */     double mean_y = 0.0D;
/* 191 */     for (int i = 0; i < N; i++) {
/* 192 */       double xi = x[i];
/* 193 */       double yi = y[i];
/* 194 */       if (!Double.isNaN(xi) && !Double.isNaN(yi)) {
/* 195 */         mean_x += xi;
/* 196 */         mean_y += yi;
/* 197 */         count++;
/*     */       } 
/*     */     } 
/* 200 */     mean_x /= count;
/* 201 */     mean_y /= count;
/* 216 */     double mean_x2 = 0.0D;
/* 217 */     double mean_y2 = 0.0D;
/* 218 */     double mean_xy = 0.0D;
/* 219 */     for (int j = 0; j < N; j++) {
/* 220 */       double xi = x[j];
/* 221 */       double yi = y[j];
/* 222 */       if (!Double.isNaN(xi) && !Double.isNaN(yi)) {
/* 223 */         xi -= mean_x;
/* 224 */         mean_x2 += xi * xi;
/* 225 */         mean_y2 += yi * yi;
/* 226 */         mean_xy += xi * yi;
/*     */       } 
/*     */     } 
/* 229 */     mean_x2 /= count;
/* 230 */     mean_y2 /= count;
/* 231 */     mean_xy /= count;
/* 238 */     this.slope = mean_xy / mean_x2;
/* 239 */     this.y0 = mean_y - mean_x * this.slope;
/* 240 */     return mean_xy / Math.sqrt(mean_x2 * (mean_y2 - mean_y * mean_y));
/*     */   }
/*     */   
/*     */   public void translate(double dx, double dy) {
/* 250 */     if (this.slope == 0.0D || Double.isInfinite(this.slope)) {
/* 251 */       this.x0 += dx;
/* 252 */       this.y0 += dy;
/*     */     } else {
/* 254 */       this.x0 += dx - dy / this.slope;
/* 255 */       this.y0 += dy - this.slope * dx;
/*     */     } 
/*     */   }
/*     */   
/*     */   public final double y(double x) {
/* 270 */     return this.slope * x + this.y0;
/*     */   }
/*     */   
/*     */   public final double x(double y) {
/* 284 */     return y / this.slope + this.x0;
/*     */   }
/*     */   
/*     */   public final double getY0() {
/* 292 */     return this.y0;
/*     */   }
/*     */   
/*     */   public final double getX0() {
/* 300 */     return this.x0;
/*     */   }
/*     */   
/*     */   public final double getSlope() {
/* 307 */     return this.slope;
/*     */   }
/*     */   
/*     */   public Point2D intersectionPoint(Line line) {
/*     */     double x;
/*     */     double y;
/* 319 */     if (Double.isInfinite(this.slope)) {
/* 320 */       if (Double.isInfinite(line.slope))
/* 321 */         return null; 
/* 323 */       x = this.x0;
/* 324 */       y = x * line.slope + line.y0;
/*     */     } else {
/* 326 */       if (!Double.isInfinite(line.slope)) {
/* 327 */         x = (this.y0 - line.y0) / (line.slope - this.slope);
/* 328 */         if (Double.isInfinite(x))
/* 329 */           return null; 
/*     */       } else {
/* 332 */         x = line.x0;
/*     */       } 
/* 334 */       y = x * this.slope + this.y0;
/*     */     } 
/* 336 */     return new Point2D.Double(x, y);
/*     */   }
/*     */   
/*     */   public Point2D intersectionPoint(Line2D line) {
/* 349 */     double x, y, x1 = line.getX1();
/* 350 */     double y1 = line.getY1();
/* 351 */     double x2 = line.getX2();
/* 352 */     double y2 = line.getY2();
/* 354 */     double m = (y2 - y1) / (x2 - x1);
/* 355 */     if (Double.isInfinite(this.slope)) {
/* 356 */       x = this.x0;
/* 357 */       y = x * m + y2 - m * x2;
/*     */     } else {
/* 359 */       if (!Double.isInfinite(m)) {
/* 360 */         x = (this.y0 - y2 - m * x2) / (m - this.slope);
/*     */       } else {
/* 362 */         x = 0.5D * (x1 + x2);
/*     */       } 
/* 364 */       y = x * this.slope + this.y0;
/*     */     } 
/* 370 */     double eps = 1.0E-12D * Math.abs(x);
/* 371 */     if (x1 <= x2) {
/* 372 */       if (x < x1 - eps || x > x2 + eps)
/* 373 */         return null; 
/* 376 */     } else if (x > x1 + eps || x < x2 - eps) {
/* 377 */       return null;
/*     */     } 
/* 383 */     eps = 1.0E-12D * Math.abs(y);
/* 384 */     if (y1 <= y2) {
/* 385 */       if (y < y1 - eps || y > y2 + eps)
/* 386 */         return null; 
/* 389 */     } else if (y > y1 - eps || y < y2 + eps) {
/* 390 */       return null;
/*     */     } 
/* 393 */     return new Point2D.Double(x, y);
/*     */   }
/*     */   
/*     */   public Point2D nearestColinearPoint(Point2D point) {
/* 403 */     if (!Double.isInfinite(this.slope)) {
/* 404 */       double x = ((point.getY() - this.y0) * this.slope + point.getX()) / (this.slope * this.slope + 1.0D);
/* 405 */       return new Point2D.Double(x, x * this.slope + this.y0);
/*     */     } 
/* 407 */     return new Point2D.Double(this.x0, point.getY());
/*     */   }
/*     */   
/*     */   public Line2D isoscelesTriangleBase(Point2D summit, double sideLength) {
/* 430 */     sideLength *= sideLength;
/* 431 */     if (this.slope == 0.0D) {
/* 432 */       double d1 = summit.getX();
/* 433 */       double d2 = this.y0 - summit.getY();
/* 434 */       double dx = Math.sqrt(sideLength - d2 * d2);
/* 435 */       if (Double.isNaN(dx))
/* 436 */         return null; 
/* 438 */       return new Line2D.Double(d1 + dx, this.y0, d1 - dx, this.y0);
/*     */     } 
/* 440 */     if (Double.isInfinite(this.slope)) {
/* 441 */       double d1 = summit.getY();
/* 442 */       double dx = this.x0 - summit.getX();
/* 443 */       double d2 = Math.sqrt(sideLength - dx * dx);
/* 444 */       if (Double.isNaN(d2))
/* 445 */         return null; 
/* 447 */       return new Line2D.Double(this.x0, d1 + d2, this.x0, d1 - d2);
/*     */     } 
/* 449 */     double x = summit.getX();
/* 450 */     double y = summit.getY();
/* 451 */     double dy = this.y0 - y + this.slope * x;
/* 452 */     double B = -this.slope * dy;
/* 453 */     double A = this.slope * this.slope + 1.0D;
/* 454 */     double C = Math.sqrt(B * B + A * (sideLength - dy * dy));
/* 455 */     if (Double.isNaN(C))
/* 456 */       return null; 
/* 458 */     double x1 = (B + C) / A + x;
/* 459 */     double x2 = (B - C) / A + x;
/* 460 */     return new Line2D.Double(x1, this.slope * x1 + this.y0, x2, this.slope * x2 + this.y0);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 471 */     if (!Double.isInfinite(this.slope)) {
/* 472 */       StringBuilder buffer = new StringBuilder("y= ");
/* 473 */       if (this.slope != 0.0D) {
/* 474 */         buffer.append(this.slope).append("*x");
/* 475 */         if (this.y0 != 0.0D) {
/* 476 */           buffer.append(" + ");
/*     */         } else {
/* 478 */           return buffer.toString();
/*     */         } 
/*     */       } 
/* 481 */       return buffer.append(this.y0).toString();
/*     */     } 
/* 483 */     return "x= " + this.x0;
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 492 */     if (object != null && getClass().equals(object.getClass())) {
/* 493 */       Line that = (Line)object;
/* 494 */       return (Double.doubleToLongBits(this.slope) == Double.doubleToLongBits(that.slope) && Double.doubleToLongBits(this.y0) == Double.doubleToLongBits(that.y0) && Double.doubleToLongBits(this.x0) == Double.doubleToLongBits(that.x0));
/*     */     } 
/* 498 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 507 */     long code = Double.doubleToLongBits(this.slope) + 37L * Double.doubleToLongBits(this.y0);
/* 508 */     return (int)code ^ (int)(code >>> 32L);
/*     */   }
/*     */   
/*     */   public Line clone() {
/*     */     try {
/* 517 */       return (Line)super.clone();
/* 518 */     } catch (CloneNotSupportedException exception) {
/* 519 */       throw new AssertionError(exception);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\math\Line.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */