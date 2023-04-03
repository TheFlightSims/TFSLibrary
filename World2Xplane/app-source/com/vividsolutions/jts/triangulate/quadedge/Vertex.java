/*     */ package com.vividsolutions.jts.triangulate.quadedge;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.HCoordinate;
/*     */ import com.vividsolutions.jts.algorithm.NotRepresentableException;
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ 
/*     */ public class Vertex {
/*     */   public static final int LEFT = 0;
/*     */   
/*     */   public static final int RIGHT = 1;
/*     */   
/*     */   public static final int BEYOND = 2;
/*     */   
/*     */   public static final int BEHIND = 3;
/*     */   
/*     */   public static final int BETWEEN = 4;
/*     */   
/*     */   public static final int ORIGIN = 5;
/*     */   
/*     */   public static final int DESTINATION = 6;
/*     */   
/*     */   private Coordinate p;
/*     */   
/*     */   public Vertex(double _x, double _y) {
/*  74 */     this.p = new Coordinate(_x, _y);
/*     */   }
/*     */   
/*     */   public Vertex(double _x, double _y, double _z) {
/*  78 */     this.p = new Coordinate(_x, _y, _z);
/*     */   }
/*     */   
/*     */   public Vertex(Coordinate _p) {
/*  82 */     this.p = new Coordinate(_p);
/*     */   }
/*     */   
/*     */   public double getX() {
/*  86 */     return this.p.x;
/*     */   }
/*     */   
/*     */   public double getY() {
/*  90 */     return this.p.y;
/*     */   }
/*     */   
/*     */   public double getZ() {
/*  94 */     return this.p.z;
/*     */   }
/*     */   
/*     */   public void setZ(double _z) {
/*  98 */     this.p.z = _z;
/*     */   }
/*     */   
/*     */   public Coordinate getCoordinate() {
/* 102 */     return this.p;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 106 */     return "POINT (" + this.p.x + " " + this.p.y + ")";
/*     */   }
/*     */   
/*     */   public boolean equals(Vertex _x) {
/* 110 */     if (this.p.x == _x.getX() && this.p.y == _x.getY())
/* 111 */       return true; 
/* 113 */     return false;
/*     */   }
/*     */   
/*     */   public boolean equals(Vertex _x, double tolerance) {
/* 118 */     if (this.p.distance(_x.getCoordinate()) < tolerance)
/* 119 */       return true; 
/* 121 */     return false;
/*     */   }
/*     */   
/*     */   public int classify(Vertex p0, Vertex p1) {
/* 126 */     Vertex p2 = this;
/* 127 */     Vertex a = p1.sub(p0);
/* 128 */     Vertex b = p2.sub(p0);
/* 129 */     double sa = a.crossProduct(b);
/* 130 */     if (sa > 0.0D)
/* 131 */       return 0; 
/* 132 */     if (sa < 0.0D)
/* 133 */       return 1; 
/* 134 */     if (a.getX() * b.getX() < 0.0D || a.getY() * b.getY() < 0.0D)
/* 135 */       return 3; 
/* 136 */     if (a.magn() < b.magn())
/* 137 */       return 2; 
/* 138 */     if (p0.equals(p2))
/* 139 */       return 5; 
/* 140 */     if (p1.equals(p2))
/* 141 */       return 6; 
/* 142 */     return 4;
/*     */   }
/*     */   
/*     */   double crossProduct(Vertex v) {
/* 152 */     return this.p.x * v.getY() - this.p.y * v.getX();
/*     */   }
/*     */   
/*     */   double dot(Vertex v) {
/* 162 */     return this.p.x * v.getX() + this.p.y * v.getY();
/*     */   }
/*     */   
/*     */   Vertex times(double c) {
/* 172 */     return new Vertex(c * this.p.x, c * this.p.y);
/*     */   }
/*     */   
/*     */   Vertex sum(Vertex v) {
/* 177 */     return new Vertex(this.p.x + v.getX(), this.p.y + v.getY());
/*     */   }
/*     */   
/*     */   Vertex sub(Vertex v) {
/* 182 */     return new Vertex(this.p.x - v.getX(), this.p.y - v.getY());
/*     */   }
/*     */   
/*     */   double magn() {
/* 187 */     return Math.sqrt(this.p.x * this.p.x + this.p.y * this.p.y);
/*     */   }
/*     */   
/*     */   Vertex cross() {
/* 192 */     return new Vertex(this.p.y, -this.p.x);
/*     */   }
/*     */   
/*     */   public boolean isInCircle(Vertex a, Vertex b, Vertex c) {
/* 211 */     return TrianglePredicate.isInCircleRobust(a.p, b.p, c.p, this.p);
/*     */   }
/*     */   
/*     */   public final boolean isCCW(Vertex b, Vertex c) {
/* 238 */     return ((b.p.x - this.p.x) * (c.p.y - this.p.y) - (b.p.y - this.p.y) * (c.p.x - this.p.x) > 0.0D);
/*     */   }
/*     */   
/*     */   public final boolean rightOf(QuadEdge e) {
/* 248 */     return isCCW(e.dest(), e.orig());
/*     */   }
/*     */   
/*     */   public final boolean leftOf(QuadEdge e) {
/* 252 */     return isCCW(e.orig(), e.dest());
/*     */   }
/*     */   
/*     */   private HCoordinate bisector(Vertex a, Vertex b) {
/* 257 */     double dx = b.getX() - a.getX();
/* 258 */     double dy = b.getY() - a.getY();
/* 259 */     HCoordinate l1 = new HCoordinate(a.getX() + dx / 2.0D, a.getY() + dy / 2.0D, 1.0D);
/* 260 */     HCoordinate l2 = new HCoordinate(a.getX() - dy + dx / 2.0D, a.getY() + dx + dy / 2.0D, 1.0D);
/* 261 */     return new HCoordinate(l1, l2);
/*     */   }
/*     */   
/*     */   private double distance(Vertex v1, Vertex v2) {
/* 265 */     return Math.sqrt(Math.pow(v2.getX() - v1.getX(), 2.0D) + Math.pow(v2.getY() - v1.getY(), 2.0D));
/*     */   }
/*     */   
/*     */   public double circumRadiusRatio(Vertex b, Vertex c) {
/* 280 */     Vertex x = circleCenter(b, c);
/* 281 */     double radius = distance(x, b);
/* 282 */     double edgeLength = distance(this, b);
/* 283 */     double el = distance(b, c);
/* 284 */     if (el < edgeLength)
/* 285 */       edgeLength = el; 
/* 287 */     el = distance(c, this);
/* 288 */     if (el < edgeLength)
/* 289 */       edgeLength = el; 
/* 291 */     return radius / edgeLength;
/*     */   }
/*     */   
/*     */   public Vertex midPoint(Vertex a) {
/* 301 */     double xm = (this.p.x + a.getX()) / 2.0D;
/* 302 */     double ym = (this.p.y + a.getY()) / 2.0D;
/* 303 */     double zm = (this.p.z + a.getZ()) / 2.0D;
/* 304 */     return new Vertex(xm, ym, zm);
/*     */   }
/*     */   
/*     */   public Vertex circleCenter(Vertex b, Vertex c) {
/* 315 */     Vertex a = new Vertex(getX(), getY());
/* 317 */     HCoordinate cab = bisector(a, b);
/* 319 */     HCoordinate cbc = bisector(b, c);
/* 321 */     HCoordinate hcc = new HCoordinate(cab, cbc);
/* 322 */     Vertex cc = null;
/*     */     try {
/* 324 */       cc = new Vertex(hcc.getX(), hcc.getY());
/* 325 */     } catch (NotRepresentableException nre) {
/* 326 */       System.err.println("a: " + a + "  b: " + b + "  c: " + c);
/* 327 */       System.err.println(nre);
/*     */     } 
/* 329 */     return cc;
/*     */   }
/*     */   
/*     */   public double interpolateZValue(Vertex v0, Vertex v1, Vertex v2) {
/* 337 */     double x0 = v0.getX();
/* 338 */     double y0 = v0.getY();
/* 339 */     double a = v1.getX() - x0;
/* 340 */     double b = v2.getX() - x0;
/* 341 */     double c = v1.getY() - y0;
/* 342 */     double d = v2.getY() - y0;
/* 343 */     double det = a * d - b * c;
/* 344 */     double dx = getX() - x0;
/* 345 */     double dy = getY() - y0;
/* 346 */     double t = (d * dx - b * dy) / det;
/* 347 */     double u = (-c * dx + a * dy) / det;
/* 348 */     double z = v0.getZ() + t * (v1.getZ() - v0.getZ()) + u * (v2.getZ() - v0.getZ());
/* 349 */     return z;
/*     */   }
/*     */   
/*     */   public static double interpolateZ(Coordinate p, Coordinate v0, Coordinate v1, Coordinate v2) {
/* 366 */     double x0 = v0.x;
/* 367 */     double y0 = v0.y;
/* 368 */     double a = v1.x - x0;
/* 369 */     double b = v2.x - x0;
/* 370 */     double c = v1.y - y0;
/* 371 */     double d = v2.y - y0;
/* 372 */     double det = a * d - b * c;
/* 373 */     double dx = p.x - x0;
/* 374 */     double dy = p.y - y0;
/* 375 */     double t = (d * dx - b * dy) / det;
/* 376 */     double u = (-c * dx + a * dy) / det;
/* 377 */     double z = v0.z + t * (v1.z - v0.z) + u * (v2.z - v0.z);
/* 378 */     return z;
/*     */   }
/*     */   
/*     */   public static double interpolateZ(Coordinate p, Coordinate p0, Coordinate p1) {
/* 390 */     double segLen = p0.distance(p1);
/* 391 */     double ptLen = p.distance(p0);
/* 392 */     double dz = p1.z - p0.z;
/* 393 */     double pz = p0.z + dz * ptLen / segLen;
/* 394 */     return pz;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\triangulate\quadedge\Vertex.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */