/*     */ package org.apache.commons.math3.geometry.euclidean.threed;
/*     */ 
/*     */ import org.apache.commons.math3.exception.MathArithmeticException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.geometry.Vector;
/*     */ import org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D;
/*     */ import org.apache.commons.math3.geometry.euclidean.oned.Vector1D;
/*     */ import org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D;
/*     */ import org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet;
/*     */ import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
/*     */ import org.apache.commons.math3.geometry.partitioning.Embedding;
/*     */ import org.apache.commons.math3.geometry.partitioning.Hyperplane;
/*     */ import org.apache.commons.math3.geometry.partitioning.Region;
/*     */ import org.apache.commons.math3.geometry.partitioning.SubHyperplane;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class Plane implements Hyperplane<Euclidean3D>, Embedding<Euclidean3D, Euclidean2D> {
/*     */   private double originOffset;
/*     */   
/*     */   private Vector3D origin;
/*     */   
/*     */   private Vector3D u;
/*     */   
/*     */   private Vector3D v;
/*     */   
/*     */   private Vector3D w;
/*     */   
/*     */   public Plane(Vector3D normal) {
/*  56 */     setNormal(normal);
/*  57 */     this.originOffset = 0.0D;
/*  58 */     setFrame();
/*     */   }
/*     */   
/*     */   public Plane(Vector3D p, Vector3D normal) {
/*  67 */     setNormal(normal);
/*  68 */     this.originOffset = -p.dotProduct(this.w);
/*  69 */     setFrame();
/*     */   }
/*     */   
/*     */   public Plane(Vector3D p1, Vector3D p2, Vector3D p3) {
/*  81 */     this(p1, p2.subtract(p1).crossProduct(p3.subtract(p1)));
/*     */   }
/*     */   
/*     */   public Plane(Plane plane) {
/*  91 */     this.originOffset = plane.originOffset;
/*  92 */     this.origin = plane.origin;
/*  93 */     this.u = plane.u;
/*  94 */     this.v = plane.v;
/*  95 */     this.w = plane.w;
/*     */   }
/*     */   
/*     */   public Plane copySelf() {
/* 105 */     return new Plane(this);
/*     */   }
/*     */   
/*     */   public void reset(Vector3D p, Vector3D normal) {
/* 113 */     setNormal(normal);
/* 114 */     this.originOffset = -p.dotProduct(this.w);
/* 115 */     setFrame();
/*     */   }
/*     */   
/*     */   public void reset(Plane original) {
/* 125 */     this.originOffset = original.originOffset;
/* 126 */     this.origin = original.origin;
/* 127 */     this.u = original.u;
/* 128 */     this.v = original.v;
/* 129 */     this.w = original.w;
/*     */   }
/*     */   
/*     */   private void setNormal(Vector3D normal) {
/* 137 */     double norm = normal.getNorm();
/* 138 */     if (norm < 1.0E-10D)
/* 139 */       throw new MathArithmeticException(LocalizedFormats.ZERO_NORM, new Object[0]); 
/* 141 */     this.w = new Vector3D(1.0D / norm, normal);
/*     */   }
/*     */   
/*     */   private void setFrame() {
/* 147 */     this.origin = new Vector3D(-this.originOffset, this.w);
/* 148 */     this.u = this.w.orthogonal();
/* 149 */     this.v = Vector3D.crossProduct(this.w, this.u);
/*     */   }
/*     */   
/*     */   public Vector3D getOrigin() {
/* 159 */     return this.origin;
/*     */   }
/*     */   
/*     */   public Vector3D getNormal() {
/* 171 */     return this.w;
/*     */   }
/*     */   
/*     */   public Vector3D getU() {
/* 183 */     return this.u;
/*     */   }
/*     */   
/*     */   public Vector3D getV() {
/* 195 */     return this.v;
/*     */   }
/*     */   
/*     */   public void revertSelf() {
/* 210 */     Vector3D tmp = this.u;
/* 211 */     this.u = this.v;
/* 212 */     this.v = tmp;
/* 213 */     this.w = this.w.negate();
/* 214 */     this.originOffset = -this.originOffset;
/*     */   }
/*     */   
/*     */   public Vector2D toSubSpace(Vector<Euclidean3D> point) {
/* 225 */     return new Vector2D(point.dotProduct(this.u), point.dotProduct(this.v));
/*     */   }
/*     */   
/*     */   public Vector3D toSpace(Vector<Euclidean2D> point) {
/* 235 */     Vector2D p2D = (Vector2D)point;
/* 236 */     return new Vector3D(p2D.getX(), this.u, p2D.getY(), this.v, -this.originOffset, this.w);
/*     */   }
/*     */   
/*     */   public Vector3D getPointAt(Vector2D inPlane, double offset) {
/* 247 */     return new Vector3D(inPlane.getX(), this.u, inPlane.getY(), this.v, offset - this.originOffset, this.w);
/*     */   }
/*     */   
/*     */   public boolean isSimilarTo(Plane plane) {
/* 258 */     double angle = Vector3D.angle(this.w, plane.w);
/* 259 */     return ((angle < 1.0E-10D && FastMath.abs(this.originOffset - plane.originOffset) < 1.0E-10D) || (angle > 3.141592653489793D && FastMath.abs(this.originOffset + plane.originOffset) < 1.0E-10D));
/*     */   }
/*     */   
/*     */   public Plane rotate(Vector3D center, Rotation rotation) {
/* 271 */     Vector3D delta = this.origin.subtract(center);
/* 272 */     Plane plane = new Plane(center.add(rotation.applyTo(delta)), rotation.applyTo(this.w));
/* 276 */     plane.u = rotation.applyTo(this.u);
/* 277 */     plane.v = rotation.applyTo(this.v);
/* 279 */     return plane;
/*     */   }
/*     */   
/*     */   public Plane translate(Vector3D translation) {
/* 290 */     Plane plane = new Plane(this.origin.add(translation), this.w);
/* 293 */     plane.u = this.u;
/* 294 */     plane.v = this.v;
/* 296 */     return plane;
/*     */   }
/*     */   
/*     */   public Vector3D intersection(Line line) {
/* 306 */     Vector3D direction = line.getDirection();
/* 307 */     double dot = this.w.dotProduct(direction);
/* 308 */     if (FastMath.abs(dot) < 1.0E-10D)
/* 309 */       return null; 
/* 311 */     Vector3D point = line.toSpace((Vector<Euclidean1D>)Vector1D.ZERO);
/* 312 */     double k = -(this.originOffset + this.w.dotProduct(point)) / dot;
/* 313 */     return new Vector3D(1.0D, point, k, direction);
/*     */   }
/*     */   
/*     */   public Line intersection(Plane other) {
/* 322 */     Vector3D direction = Vector3D.crossProduct(this.w, other.w);
/* 323 */     if (direction.getNorm() < 1.0E-10D)
/* 324 */       return null; 
/* 326 */     Vector3D point = intersection(this, other, new Plane(direction));
/* 327 */     return new Line(point, point.add(direction));
/*     */   }
/*     */   
/*     */   public static Vector3D intersection(Plane plane1, Plane plane2, Plane plane3) {
/* 339 */     double a1 = plane1.w.getX();
/* 340 */     double b1 = plane1.w.getY();
/* 341 */     double c1 = plane1.w.getZ();
/* 342 */     double d1 = plane1.originOffset;
/* 344 */     double a2 = plane2.w.getX();
/* 345 */     double b2 = plane2.w.getY();
/* 346 */     double c2 = plane2.w.getZ();
/* 347 */     double d2 = plane2.originOffset;
/* 349 */     double a3 = plane3.w.getX();
/* 350 */     double b3 = plane3.w.getY();
/* 351 */     double c3 = plane3.w.getZ();
/* 352 */     double d3 = plane3.originOffset;
/* 356 */     double a23 = b2 * c3 - b3 * c2;
/* 357 */     double b23 = c2 * a3 - c3 * a2;
/* 358 */     double c23 = a2 * b3 - a3 * b2;
/* 359 */     double determinant = a1 * a23 + b1 * b23 + c1 * c23;
/* 360 */     if (FastMath.abs(determinant) < 1.0E-10D)
/* 361 */       return null; 
/* 364 */     double r = 1.0D / determinant;
/* 365 */     return new Vector3D((-a23 * d1 - (c1 * b3 - c3 * b1) * d2 - (c2 * b1 - c1 * b2) * d3) * r, (-b23 * d1 - (c3 * a1 - c1 * a3) * d2 - (c1 * a2 - c2 * a1) * d3) * r, (-c23 * d1 - (b1 * a3 - b3 * a1) * d2 - (b2 * a1 - b1 * a2) * d3) * r);
/*     */   }
/*     */   
/*     */   public SubPlane wholeHyperplane() {
/* 376 */     return new SubPlane(this, (Region<Euclidean2D>)new PolygonsSet());
/*     */   }
/*     */   
/*     */   public PolyhedronsSet wholeSpace() {
/* 384 */     return new PolyhedronsSet();
/*     */   }
/*     */   
/*     */   public boolean contains(Vector3D p) {
/* 392 */     return (FastMath.abs(getOffset(p)) < 1.0E-10D);
/*     */   }
/*     */   
/*     */   public double getOffset(Plane plane) {
/* 406 */     return this.originOffset + (sameOrientationAs(plane) ? -plane.originOffset : plane.originOffset);
/*     */   }
/*     */   
/*     */   public double getOffset(Vector<Euclidean3D> point) {
/* 418 */     return point.dotProduct(this.w) + this.originOffset;
/*     */   }
/*     */   
/*     */   public boolean sameOrientationAs(Hyperplane<Euclidean3D> other) {
/* 427 */     return (((Plane)other).w.dotProduct(this.w) > 0.0D);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\geometry\euclidean\threed\Plane.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */