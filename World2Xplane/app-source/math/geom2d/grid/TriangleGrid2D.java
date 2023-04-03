/*     */ package math.geom2d.grid;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import math.geom2d.Box2D;
/*     */ import math.geom2d.Point2D;
/*     */ import math.geom2d.line.LineSegment2D;
/*     */ import math.geom2d.line.LinearShape2D;
/*     */ import math.geom2d.line.StraightLine2D;
/*     */ import math.geom2d.point.PointArray2D;
/*     */ import math.geom2d.point.PointSet2D;
/*     */ 
/*     */ public class TriangleGrid2D implements Grid2D {
/*  26 */   double x0 = 0.0D;
/*     */   
/*  27 */   double y0 = 0.0D;
/*     */   
/*  28 */   double s = 1.0D;
/*     */   
/*  30 */   double theta = 0.0D;
/*     */   
/*     */   private static final boolean isEven(int n) {
/*  39 */     return (Math.abs(n * 0.5D - Math.floor(n * 0.5D)) < 0.25D);
/*     */   }
/*     */   
/*     */   public TriangleGrid2D() {
/*  43 */     this(0.0D, 0.0D, 1.0D, 0.0D);
/*     */   }
/*     */   
/*     */   public TriangleGrid2D(double s) {
/*  50 */     this(0.0D, 0.0D, s, 0.0D);
/*     */   }
/*     */   
/*     */   public TriangleGrid2D(double x0, double y0) {
/*  58 */     this(x0, y0, 1.0D, 0.0D);
/*     */   }
/*     */   
/*     */   public TriangleGrid2D(double x0, double y0, double s) {
/*  67 */     this(x0, y0, s, 0.0D);
/*     */   }
/*     */   
/*     */   public TriangleGrid2D(double x0, double y0, double s, double theta) {
/*  77 */     this.x0 = x0;
/*  78 */     this.y0 = y0;
/*  79 */     this.s = s;
/*  80 */     this.theta = theta;
/*     */   }
/*     */   
/*     */   public TriangleGrid2D(Point2D point) {
/*  89 */     this(point.x(), point.y(), 1.0D, 0.0D);
/*     */   }
/*     */   
/*     */   public TriangleGrid2D(Point2D point, double s) {
/*  97 */     this(point.x(), point.y(), s, 0.0D);
/*     */   }
/*     */   
/*     */   public TriangleGrid2D(Point2D point, double s, double theta) {
/* 106 */     this(point.x(), point.y(), s, theta);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public void setOrigin(Point2D point) {
/* 114 */     this.x0 = point.getX();
/* 115 */     this.y0 = point.getY();
/*     */   }
/*     */   
/*     */   public Point2D getOrigin() {
/* 119 */     return new Point2D(this.x0, this.y0);
/*     */   }
/*     */   
/*     */   public double getSize() {
/* 123 */     return this.s;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public void setSize(double s) {
/* 131 */     this.s = s;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public void setAngle(double theta) {
/* 139 */     this.theta = theta;
/*     */   }
/*     */   
/*     */   public double getTheta() {
/* 143 */     return this.theta;
/*     */   }
/*     */   
/*     */   public Point2D getClosestVertex(Point2D point) {
/*     */     Point2D p1, p2, p3;
/* 153 */     double cot = Math.cos(this.theta);
/* 154 */     double sit = Math.sin(this.theta);
/* 155 */     StraightLine2D baseLine = new StraightLine2D(this.x0, this.y0, cot, sit);
/* 158 */     double s2 = this.s * Math.sqrt(3.0D) / 2.0D;
/* 159 */     double d = baseLine.signedDistance(point);
/* 160 */     int n1 = (int)Math.floor(d / s2);
/* 161 */     int n2 = (int)Math.ceil(d / s2);
/* 164 */     StraightLine2D line1 = baseLine.parallel(n1 * s2);
/* 165 */     StraightLine2D line2 = baseLine.parallel(n2 * s2);
/* 168 */     double t = line1.project(new Point2D(point));
/* 171 */     if (isEven(n1)) {
/* 172 */       p1 = line1.point(Math.floor(t / this.s) * this.s);
/* 173 */       p2 = line1.point(Math.ceil(t / this.s) * this.s);
/* 174 */       p3 = line2.point((Math.floor(t / this.s) + 0.5D) * this.s);
/*     */     } else {
/* 176 */       p1 = line1.point((Math.floor(t / this.s) + 0.5D) * this.s);
/* 177 */       p2 = line2.point(Math.floor(t / this.s) * this.s);
/* 178 */       p3 = line2.point(Math.ceil(t / this.s) * this.s);
/*     */     } 
/* 181 */     Point2D res = p1;
/* 182 */     double minDist = res.distance(point);
/* 184 */     double d2 = p2.distance(point);
/* 185 */     if (d2 < minDist) {
/* 186 */       res = p2;
/* 187 */       minDist = d2;
/*     */     } 
/* 190 */     double d3 = p3.distance(point);
/* 191 */     if (d3 < minDist) {
/* 192 */       res = p3;
/* 193 */       minDist = d3;
/*     */     } 
/* 195 */     return res;
/*     */   }
/*     */   
/*     */   public Collection<LineSegment2D> getEdges(Box2D box) {
/* 206 */     ArrayList<LineSegment2D> array = new ArrayList<LineSegment2D>();
/* 208 */     double d = this.s * Math.sqrt(3.0D) / 2.0D;
/* 211 */     for (int k = 0; k < 3; k++) {
/* 213 */       double theta2 = this.theta + Math.PI * k / 3.0D;
/* 214 */       double cot = Math.cos(theta2);
/* 215 */       double sit = Math.sin(theta2);
/* 216 */       StraightLine2D baseLine = new StraightLine2D(this.x0, this.y0, cot, sit);
/* 219 */       double dmin = Double.POSITIVE_INFINITY;
/* 220 */       double dmax = Double.NEGATIVE_INFINITY;
/* 221 */       for (Point2D point : box.vertices()) {
/* 222 */         double dist = baseLine.signedDistance(point);
/* 223 */         dmin = Math.min(dmin, dist);
/* 224 */         dmax = Math.max(dmax, dist);
/*     */       } 
/* 228 */       double s2 = this.s * Math.sqrt(3.0D) / 2.0D;
/* 229 */       int i0 = (int)Math.ceil(dmin / s2);
/* 230 */       int i1 = (int)Math.floor(dmax / s2);
/* 233 */       for (int i = i0; i <= i1; i++) {
/* 234 */         StraightLine2D line = baseLine.parallel(d * i);
/* 235 */         for (LinearShape2D arc : line.clip(box)) {
/* 236 */           if (arc instanceof LineSegment2D)
/* 237 */             array.add((LineSegment2D)arc); 
/*     */         } 
/*     */       } 
/*     */     } 
/* 241 */     return array;
/*     */   }
/*     */   
/*     */   public PointSet2D getVertices(Box2D box) {
/* 252 */     ArrayList<Point2D> array = new ArrayList<Point2D>();
/* 254 */     double d = this.s * Math.sqrt(3.0D) / 2.0D;
/* 258 */     double cot = Math.cos(this.theta);
/* 259 */     double sit = Math.sin(this.theta);
/* 260 */     StraightLine2D baseLine = new StraightLine2D(this.x0, this.y0, cot, sit);
/* 263 */     double dmin = Double.POSITIVE_INFINITY;
/* 264 */     double dmax = Double.NEGATIVE_INFINITY;
/* 265 */     for (Point2D point : box.vertices()) {
/* 266 */       double dist = baseLine.signedDistance(point);
/* 267 */       dmin = Math.min(dmin, dist);
/* 268 */       dmax = Math.max(dmax, dist);
/*     */     } 
/* 272 */     int i0 = (int)Math.ceil(dmin / this.s);
/* 273 */     int i1 = (int)Math.floor(dmax / this.s);
/* 276 */     for (int i = i0; i <= i1; i++) {
/* 279 */       StraightLine2D line = baseLine.parallel(d * i);
/* 282 */       LineSegment2D seg = (LineSegment2D)line.clip(box).firstCurve();
/* 286 */       double t1 = line.position(seg.firstPoint());
/* 287 */       double t2 = line.position(seg.lastPoint());
/* 290 */       double t0 = isEven(i) ? 0.0D : (this.s * 0.5D);
/* 293 */       int j0 = (int)Math.ceil((t1 - t0) / this.s);
/* 294 */       int j1 = (int)Math.floor((t2 - t0) / this.s);
/* 297 */       if (j1 >= j0)
/* 299 */         for (int j = j0; j <= j1; j++)
/* 300 */           array.add(line.point(j * this.s + t0));  
/*     */     } 
/* 303 */     return (PointSet2D)new PointArray2D(array);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\grid\TriangleGrid2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */