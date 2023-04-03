/*     */ package com.vividsolutions.jts.geom;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.CGAlgorithms;
/*     */ import com.vividsolutions.jts.algorithm.HCoordinate;
/*     */ import com.vividsolutions.jts.algorithm.NotRepresentableException;
/*     */ import com.vividsolutions.jts.algorithm.RobustLineIntersector;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class LineSegment implements Comparable, Serializable {
/*     */   private static final long serialVersionUID = 3252005833466256227L;
/*     */   
/*     */   public Coordinate p0;
/*     */   
/*     */   public Coordinate p1;
/*     */   
/*     */   public LineSegment(Coordinate p0, Coordinate p1) {
/*  62 */     this.p0 = p0;
/*  63 */     this.p1 = p1;
/*     */   }
/*     */   
/*     */   public LineSegment(double x0, double y0, double x1, double y1) {
/*  67 */     this(new Coordinate(x0, y0), new Coordinate(x1, y1));
/*     */   }
/*     */   
/*     */   public LineSegment(LineSegment ls) {
/*  71 */     this(ls.p0, ls.p1);
/*     */   }
/*     */   
/*     */   public LineSegment() {
/*  75 */     this(new Coordinate(), new Coordinate());
/*     */   }
/*     */   
/*     */   public Coordinate getCoordinate(int i) {
/*  80 */     if (i == 0)
/*  80 */       return this.p0; 
/*  81 */     return this.p1;
/*     */   }
/*     */   
/*     */   public void setCoordinates(LineSegment ls) {
/*  86 */     setCoordinates(ls.p0, ls.p1);
/*     */   }
/*     */   
/*     */   public void setCoordinates(Coordinate p0, Coordinate p1) {
/*  91 */     this.p0.x = p0.x;
/*  92 */     this.p0.y = p0.y;
/*  93 */     this.p1.x = p1.x;
/*  94 */     this.p1.y = p1.y;
/*     */   }
/*     */   
/*     */   public double getLength() {
/* 103 */     return this.p0.distance(this.p1);
/*     */   }
/*     */   
/*     */   public boolean isHorizontal() {
/* 111 */     return (this.p0.y == this.p1.y);
/*     */   }
/*     */   
/*     */   public boolean isVertical() {
/* 118 */     return (this.p0.x == this.p1.x);
/*     */   }
/*     */   
/*     */   public int orientationIndex(LineSegment seg) {
/* 141 */     int orient0 = CGAlgorithms.orientationIndex(this.p0, this.p1, seg.p0);
/* 142 */     int orient1 = CGAlgorithms.orientationIndex(this.p0, this.p1, seg.p1);
/* 144 */     if (orient0 >= 0 && orient1 >= 0)
/* 145 */       return Math.max(orient0, orient1); 
/* 147 */     if (orient0 <= 0 && orient1 <= 0)
/* 148 */       return Math.max(orient0, orient1); 
/* 150 */     return 0;
/*     */   }
/*     */   
/*     */   public int orientationIndex(Coordinate p) {
/* 167 */     return CGAlgorithms.orientationIndex(this.p0, this.p1, p);
/*     */   }
/*     */   
/*     */   public void reverse() {
/* 175 */     Coordinate temp = this.p0;
/* 176 */     this.p0 = this.p1;
/* 177 */     this.p1 = temp;
/*     */   }
/*     */   
/*     */   public void normalize() {
/* 189 */     if (this.p1.compareTo(this.p0) < 0)
/* 189 */       reverse(); 
/*     */   }
/*     */   
/*     */   public double angle() {
/* 201 */     return Math.atan2(this.p1.y - this.p0.y, this.p1.x - this.p0.x);
/*     */   }
/*     */   
/*     */   public Coordinate midPoint() {
/* 211 */     return midPoint(this.p0, this.p1);
/*     */   }
/*     */   
/*     */   public static Coordinate midPoint(Coordinate p0, Coordinate p1) {
/* 221 */     return new Coordinate((p0.x + p1.x) / 2.0D, (p0.y + p1.y) / 2.0D);
/*     */   }
/*     */   
/*     */   public double distance(LineSegment ls) {
/* 232 */     return CGAlgorithms.distanceLineLine(this.p0, this.p1, ls.p0, ls.p1);
/*     */   }
/*     */   
/*     */   public double distance(Coordinate p) {
/* 242 */     return CGAlgorithms.distancePointLine(p, this.p0, this.p1);
/*     */   }
/*     */   
/*     */   public double distancePerpendicular(Coordinate p) {
/* 253 */     return CGAlgorithms.distancePointLinePerpendicular(p, this.p0, this.p1);
/*     */   }
/*     */   
/*     */   public Coordinate pointAlong(double segmentLengthFraction) {
/* 269 */     Coordinate coord = new Coordinate();
/* 270 */     this.p0.x += segmentLengthFraction * (this.p1.x - this.p0.x);
/* 271 */     this.p0.y += segmentLengthFraction * (this.p1.y - this.p0.y);
/* 272 */     return coord;
/*     */   }
/*     */   
/*     */   public Coordinate pointAlongOffset(double segmentLengthFraction, double offsetDistance) {
/* 294 */     double segx = this.p0.x + segmentLengthFraction * (this.p1.x - this.p0.x);
/* 295 */     double segy = this.p0.y + segmentLengthFraction * (this.p1.y - this.p0.y);
/* 297 */     double dx = this.p1.x - this.p0.x;
/* 298 */     double dy = this.p1.y - this.p0.y;
/* 299 */     double len = Math.sqrt(dx * dx + dy * dy);
/* 300 */     double ux = 0.0D;
/* 301 */     double uy = 0.0D;
/* 302 */     if (offsetDistance != 0.0D) {
/* 303 */       if (len <= 0.0D)
/* 304 */         throw new IllegalStateException("Cannot compute offset from zero-length line segment"); 
/* 307 */       ux = offsetDistance * dx / len;
/* 308 */       uy = offsetDistance * dy / len;
/*     */     } 
/* 312 */     double offsetx = segx - uy;
/* 313 */     double offsety = segy + ux;
/* 315 */     Coordinate coord = new Coordinate(offsetx, offsety);
/* 316 */     return coord;
/*     */   }
/*     */   
/*     */   public double projectionFactor(Coordinate p) {
/* 334 */     if (p.equals(this.p0))
/* 334 */       return 0.0D; 
/* 335 */     if (p.equals(this.p1))
/* 335 */       return 1.0D; 
/* 347 */     double dx = this.p1.x - this.p0.x;
/* 348 */     double dy = this.p1.y - this.p0.y;
/* 349 */     double len = dx * dx + dy * dy;
/* 352 */     if (len <= 0.0D)
/* 352 */       return Double.NaN; 
/* 354 */     double r = ((p.x - this.p0.x) * dx + (p.y - this.p0.y) * dy) / len;
/* 356 */     return r;
/*     */   }
/*     */   
/*     */   public double segmentFraction(Coordinate inputPt) {
/* 375 */     double segFrac = projectionFactor(inputPt);
/* 376 */     if (segFrac < 0.0D) {
/* 377 */       segFrac = 0.0D;
/* 378 */     } else if (segFrac > 1.0D || Double.isNaN(segFrac)) {
/* 379 */       segFrac = 1.0D;
/*     */     } 
/* 380 */     return segFrac;
/*     */   }
/*     */   
/*     */   public Coordinate project(Coordinate p) {
/* 393 */     if (p.equals(this.p0) || p.equals(this.p1))
/* 393 */       return new Coordinate(p); 
/* 395 */     double r = projectionFactor(p);
/* 396 */     Coordinate coord = new Coordinate();
/* 397 */     this.p0.x += r * (this.p1.x - this.p0.x);
/* 398 */     this.p0.y += r * (this.p1.y - this.p0.y);
/* 399 */     return coord;
/*     */   }
/*     */   
/*     */   public LineSegment project(LineSegment seg) {
/* 415 */     double pf0 = projectionFactor(seg.p0);
/* 416 */     double pf1 = projectionFactor(seg.p1);
/* 418 */     if (pf0 >= 1.0D && pf1 >= 1.0D)
/* 418 */       return null; 
/* 419 */     if (pf0 <= 0.0D && pf1 <= 0.0D)
/* 419 */       return null; 
/* 421 */     Coordinate newp0 = project(seg.p0);
/* 422 */     if (pf0 < 0.0D)
/* 422 */       newp0 = this.p0; 
/* 423 */     if (pf0 > 1.0D)
/* 423 */       newp0 = this.p1; 
/* 425 */     Coordinate newp1 = project(seg.p1);
/* 426 */     if (pf1 < 0.0D)
/* 426 */       newp1 = this.p0; 
/* 427 */     if (pf1 > 1.0D)
/* 427 */       newp1 = this.p1; 
/* 429 */     return new LineSegment(newp0, newp1);
/*     */   }
/*     */   
/*     */   public Coordinate closestPoint(Coordinate p) {
/* 438 */     double factor = projectionFactor(p);
/* 439 */     if (factor > 0.0D && factor < 1.0D)
/* 440 */       return project(p); 
/* 442 */     double dist0 = this.p0.distance(p);
/* 443 */     double dist1 = this.p1.distance(p);
/* 444 */     if (dist0 < dist1)
/* 445 */       return this.p0; 
/* 446 */     return this.p1;
/*     */   }
/*     */   
/*     */   public Coordinate[] closestPoints(LineSegment line) {
/* 457 */     Coordinate intPt = intersection(line);
/* 458 */     if (intPt != null)
/* 459 */       return new Coordinate[] { intPt, intPt }; 
/* 466 */     Coordinate[] closestPt = new Coordinate[2];
/* 467 */     double minDistance = Double.MAX_VALUE;
/* 470 */     Coordinate close00 = closestPoint(line.p0);
/* 471 */     minDistance = close00.distance(line.p0);
/* 472 */     closestPt[0] = close00;
/* 473 */     closestPt[1] = line.p0;
/* 475 */     Coordinate close01 = closestPoint(line.p1);
/* 476 */     double dist = close01.distance(line.p1);
/* 477 */     if (dist < minDistance) {
/* 478 */       minDistance = dist;
/* 479 */       closestPt[0] = close01;
/* 480 */       closestPt[1] = line.p1;
/*     */     } 
/* 483 */     Coordinate close10 = line.closestPoint(this.p0);
/* 484 */     dist = close10.distance(this.p0);
/* 485 */     if (dist < minDistance) {
/* 486 */       minDistance = dist;
/* 487 */       closestPt[0] = this.p0;
/* 488 */       closestPt[1] = close10;
/*     */     } 
/* 491 */     Coordinate close11 = line.closestPoint(this.p1);
/* 492 */     dist = close11.distance(this.p1);
/* 493 */     if (dist < minDistance) {
/* 494 */       minDistance = dist;
/* 495 */       closestPt[0] = this.p1;
/* 496 */       closestPt[1] = close11;
/*     */     } 
/* 499 */     return closestPt;
/*     */   }
/*     */   
/*     */   public Coordinate intersection(LineSegment line) {
/* 518 */     RobustLineIntersector robustLineIntersector = new RobustLineIntersector();
/* 519 */     robustLineIntersector.computeIntersection(this.p0, this.p1, line.p0, line.p1);
/* 520 */     if (robustLineIntersector.hasIntersection())
/* 521 */       return robustLineIntersector.getIntersection(0); 
/* 522 */     return null;
/*     */   }
/*     */   
/*     */   public Coordinate lineIntersection(LineSegment line) {
/*     */     try {
/* 545 */       Coordinate intPt = HCoordinate.intersection(this.p0, this.p1, line.p0, line.p1);
/* 546 */       return intPt;
/* 548 */     } catch (NotRepresentableException ex) {
/* 551 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public LineString toGeometry(GeometryFactory geomFactory) {
/* 562 */     return geomFactory.createLineString(new Coordinate[] { this.p0, this.p1 });
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/* 574 */     if (!(o instanceof LineSegment))
/* 575 */       return false; 
/* 577 */     LineSegment other = (LineSegment)o;
/* 578 */     return (this.p0.equals(other.p0) && this.p1.equals(other.p1));
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 587 */     long bits0 = Double.doubleToLongBits(this.p0.x);
/* 588 */     bits0 ^= Double.doubleToLongBits(this.p0.y) * 31L;
/* 589 */     int hash0 = (int)bits0 ^ (int)(bits0 >> 32L);
/* 591 */     long bits1 = Double.doubleToLongBits(this.p1.x);
/* 592 */     bits1 ^= Double.doubleToLongBits(this.p1.y) * 31L;
/* 593 */     int hash1 = (int)bits1 ^ (int)(bits1 >> 32L);
/* 596 */     return hash0 ^ hash1;
/*     */   }
/*     */   
/*     */   public int compareTo(Object o) {
/* 609 */     LineSegment other = (LineSegment)o;
/* 610 */     int comp0 = this.p0.compareTo(other.p0);
/* 611 */     if (comp0 != 0)
/* 611 */       return comp0; 
/* 612 */     return this.p1.compareTo(other.p1);
/*     */   }
/*     */   
/*     */   public boolean equalsTopo(LineSegment other) {
/* 626 */     return ((this.p0.equals(other.p0) && this.p1.equals(other.p1)) || (this.p0.equals(other.p1) && this.p1.equals(other.p0)));
/*     */   }
/*     */   
/*     */   public String toString() {
/* 633 */     return "LINESTRING( " + this.p0.x + " " + this.p0.y + ", " + this.p1.x + " " + this.p1.y + ")";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geom\LineSegment.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */