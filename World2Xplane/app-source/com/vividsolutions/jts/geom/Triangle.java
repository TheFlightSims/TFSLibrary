/*     */ package com.vividsolutions.jts.geom;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.Angle;
/*     */ import com.vividsolutions.jts.algorithm.HCoordinate;
/*     */ 
/*     */ public class Triangle {
/*     */   public Coordinate p0;
/*     */   
/*     */   public Coordinate p1;
/*     */   
/*     */   public Coordinate p2;
/*     */   
/*     */   public static boolean isAcute(Coordinate a, Coordinate b, Coordinate c) {
/*  64 */     if (!Angle.isAcute(a, b, c))
/*  65 */       return false; 
/*  66 */     if (!Angle.isAcute(b, c, a))
/*  67 */       return false; 
/*  68 */     if (!Angle.isAcute(c, a, b))
/*  69 */       return false; 
/*  70 */     return true;
/*     */   }
/*     */   
/*     */   public static HCoordinate perpendicularBisector(Coordinate a, Coordinate b) {
/*  86 */     double dx = b.x - a.x;
/*  87 */     double dy = b.y - a.y;
/*  88 */     HCoordinate l1 = new HCoordinate(a.x + dx / 2.0D, a.y + dy / 2.0D, 1.0D);
/*  89 */     HCoordinate l2 = new HCoordinate(a.x - dy + dx / 2.0D, a.y + dx + dy / 2.0D, 1.0D);
/*  91 */     return new HCoordinate(l1, l2);
/*     */   }
/*     */   
/*     */   public static Coordinate circumcentre(Coordinate a, Coordinate b, Coordinate c) {
/* 152 */     double cx = c.x;
/* 153 */     double cy = c.y;
/* 154 */     double ax = a.x - cx;
/* 155 */     double ay = a.y - cy;
/* 156 */     double bx = b.x - cx;
/* 157 */     double by = b.y - cy;
/* 159 */     double denom = 2.0D * det(ax, ay, bx, by);
/* 160 */     double numx = det(ay, ax * ax + ay * ay, by, bx * bx + by * by);
/* 161 */     double numy = det(ax, ax * ax + ay * ay, bx, bx * bx + by * by);
/* 163 */     double ccx = cx - numx / denom;
/* 164 */     double ccy = cy + numy / denom;
/* 166 */     return new Coordinate(ccx, ccy);
/*     */   }
/*     */   
/*     */   private static double det(double m00, double m01, double m10, double m11) {
/* 185 */     return m00 * m11 - m01 * m10;
/*     */   }
/*     */   
/*     */   public static Coordinate inCentre(Coordinate a, Coordinate b, Coordinate c) {
/* 208 */     double len0 = b.distance(c);
/* 209 */     double len1 = a.distance(c);
/* 210 */     double len2 = a.distance(b);
/* 211 */     double circum = len0 + len1 + len2;
/* 213 */     double inCentreX = (len0 * a.x + len1 * b.x + len2 * c.x) / circum;
/* 214 */     double inCentreY = (len0 * a.y + len1 * b.y + len2 * c.y) / circum;
/* 215 */     return new Coordinate(inCentreX, inCentreY);
/*     */   }
/*     */   
/*     */   public static Coordinate centroid(Coordinate a, Coordinate b, Coordinate c) {
/* 237 */     double x = (a.x + b.x + c.x) / 3.0D;
/* 238 */     double y = (a.y + b.y + c.y) / 3.0D;
/* 239 */     return new Coordinate(x, y);
/*     */   }
/*     */   
/*     */   public static double longestSideLength(Coordinate a, Coordinate b, Coordinate c) {
/* 256 */     double lenAB = a.distance(b);
/* 257 */     double lenBC = b.distance(c);
/* 258 */     double lenCA = c.distance(a);
/* 259 */     double maxLen = lenAB;
/* 260 */     if (lenBC > maxLen)
/* 261 */       maxLen = lenBC; 
/* 262 */     if (lenCA > maxLen)
/* 263 */       maxLen = lenCA; 
/* 264 */     return maxLen;
/*     */   }
/*     */   
/*     */   public static Coordinate angleBisector(Coordinate a, Coordinate b, Coordinate c) {
/* 286 */     double len0 = b.distance(a);
/* 287 */     double len2 = b.distance(c);
/* 288 */     double frac = len0 / (len0 + len2);
/* 289 */     double dx = c.x - a.x;
/* 290 */     double dy = c.y - a.y;
/* 292 */     Coordinate splitPt = new Coordinate(a.x + frac * dx, a.y + frac * dy);
/* 293 */     return splitPt;
/*     */   }
/*     */   
/*     */   public static double area(Coordinate a, Coordinate b, Coordinate c) {
/* 311 */     return Math.abs(((c.x - a.x) * (b.y - a.y) - (b.x - a.x) * (c.y - a.y)) / 2.0D);
/*     */   }
/*     */   
/*     */   public static double signedArea(Coordinate a, Coordinate b, Coordinate c) {
/* 341 */     return ((c.x - a.x) * (b.y - a.y) - (b.x - a.x) * (c.y - a.y)) / 2.0D;
/*     */   }
/*     */   
/*     */   public static double area3D(Coordinate a, Coordinate b, Coordinate c) {
/* 363 */     double ux = b.x - a.x;
/* 364 */     double uy = b.y - a.y;
/* 365 */     double uz = b.z - a.z;
/* 367 */     double vx = c.x - a.x;
/* 368 */     double vy = c.y - a.y;
/* 369 */     double vz = c.z - a.z;
/* 372 */     double crossx = uy * vz - uz * vy;
/* 373 */     double crossy = uz * vx - ux * vz;
/* 374 */     double crossz = ux * vy - uy * vx;
/* 377 */     double absSq = crossx * crossx + crossy * crossy + crossz * crossz;
/* 378 */     double area3D = Math.sqrt(absSq) / 2.0D;
/* 380 */     return area3D;
/*     */   }
/*     */   
/*     */   public static double interpolateZ(Coordinate p, Coordinate v0, Coordinate v1, Coordinate v2) {
/* 405 */     double x0 = v0.x;
/* 406 */     double y0 = v0.y;
/* 407 */     double a = v1.x - x0;
/* 408 */     double b = v2.x - x0;
/* 409 */     double c = v1.y - y0;
/* 410 */     double d = v2.y - y0;
/* 411 */     double det = a * d - b * c;
/* 412 */     double dx = p.x - x0;
/* 413 */     double dy = p.y - y0;
/* 414 */     double t = (d * dx - b * dy) / det;
/* 415 */     double u = (-c * dx + a * dy) / det;
/* 416 */     double z = v0.z + t * (v1.z - v0.z) + u * (v2.z - v0.z);
/* 417 */     return z;
/*     */   }
/*     */   
/*     */   public Triangle(Coordinate p0, Coordinate p1, Coordinate p2) {
/* 437 */     this.p0 = p0;
/* 438 */     this.p1 = p1;
/* 439 */     this.p2 = p2;
/*     */   }
/*     */   
/*     */   public Coordinate inCentre() {
/* 453 */     return inCentre(this.p0, this.p1, this.p2);
/*     */   }
/*     */   
/*     */   public boolean isAcute() {
/* 468 */     return isAcute(this.p0, this.p1, this.p2);
/*     */   }
/*     */   
/*     */   public Coordinate circumcentre() {
/* 488 */     return circumcentre(this.p0, this.p1, this.p2);
/*     */   }
/*     */   
/*     */   public Coordinate centroid() {
/* 503 */     return centroid(this.p0, this.p1, this.p2);
/*     */   }
/*     */   
/*     */   public double longestSideLength() {
/* 513 */     return longestSideLength(this.p0, this.p1, this.p2);
/*     */   }
/*     */   
/*     */   public double area() {
/* 526 */     return area(this.p0, this.p1, this.p2);
/*     */   }
/*     */   
/*     */   public double signedArea() {
/* 544 */     return signedArea(this.p0, this.p1, this.p2);
/*     */   }
/*     */   
/*     */   public double area3D() {
/* 555 */     return area3D(this.p0, this.p1, this.p2);
/*     */   }
/*     */   
/*     */   public double interpolateZ(Coordinate p) {
/* 573 */     if (p == null)
/* 574 */       throw new IllegalArgumentException("Supplied point is null."); 
/* 575 */     return interpolateZ(p, this.p0, this.p1, this.p2);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geom\Triangle.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */