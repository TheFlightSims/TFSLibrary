/*     */ package org.apache.commons.math3.geometry.euclidean.twod;
/*     */ 
/*     */ import java.awt.geom.AffineTransform;
/*     */ import org.apache.commons.math3.exception.MathIllegalArgumentException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.geometry.Vector;
/*     */ import org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D;
/*     */ import org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet;
/*     */ import org.apache.commons.math3.geometry.euclidean.oned.OrientedPoint;
/*     */ import org.apache.commons.math3.geometry.euclidean.oned.Vector1D;
/*     */ import org.apache.commons.math3.geometry.partitioning.Embedding;
/*     */ import org.apache.commons.math3.geometry.partitioning.Hyperplane;
/*     */ import org.apache.commons.math3.geometry.partitioning.Region;
/*     */ import org.apache.commons.math3.geometry.partitioning.SubHyperplane;
/*     */ import org.apache.commons.math3.geometry.partitioning.Transform;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ import org.apache.commons.math3.util.MathUtils;
/*     */ 
/*     */ public class Line implements Hyperplane<Euclidean2D>, Embedding<Euclidean2D, Euclidean1D> {
/*     */   private double angle;
/*     */   
/*     */   private double cos;
/*     */   
/*     */   private double sin;
/*     */   
/*     */   private double originOffset;
/*     */   
/*     */   public Line(Vector2D p1, Vector2D p2) {
/*  82 */     reset(p1, p2);
/*     */   }
/*     */   
/*     */   public Line(Vector2D p, double angle) {
/*  90 */     reset(p, angle);
/*     */   }
/*     */   
/*     */   private Line(double angle, double cos, double sin, double originOffset) {
/* 100 */     this.angle = angle;
/* 101 */     this.cos = cos;
/* 102 */     this.sin = sin;
/* 103 */     this.originOffset = originOffset;
/*     */   }
/*     */   
/*     */   public Line(Line line) {
/* 112 */     this.angle = MathUtils.normalizeAngle(line.angle, Math.PI);
/* 113 */     this.cos = FastMath.cos(this.angle);
/* 114 */     this.sin = FastMath.sin(this.angle);
/* 115 */     this.originOffset = line.originOffset;
/*     */   }
/*     */   
/*     */   public Line copySelf() {
/* 120 */     return new Line(this);
/*     */   }
/*     */   
/*     */   public void reset(Vector2D p1, Vector2D p2) {
/* 129 */     double dx = p2.getX() - p1.getX();
/* 130 */     double dy = p2.getY() - p1.getY();
/* 131 */     double d = FastMath.hypot(dx, dy);
/* 132 */     if (d == 0.0D) {
/* 133 */       this.angle = 0.0D;
/* 134 */       this.cos = 1.0D;
/* 135 */       this.sin = 0.0D;
/* 136 */       this.originOffset = p1.getY();
/*     */     } else {
/* 138 */       this.angle = Math.PI + FastMath.atan2(-dy, -dx);
/* 139 */       this.cos = FastMath.cos(this.angle);
/* 140 */       this.sin = FastMath.sin(this.angle);
/* 141 */       this.originOffset = (p2.getX() * p1.getY() - p1.getX() * p2.getY()) / d;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void reset(Vector2D p, double alpha) {
/* 150 */     this.angle = MathUtils.normalizeAngle(alpha, Math.PI);
/* 151 */     this.cos = FastMath.cos(this.angle);
/* 152 */     this.sin = FastMath.sin(this.angle);
/* 153 */     this.originOffset = this.cos * p.getY() - this.sin * p.getX();
/*     */   }
/*     */   
/*     */   public void revertSelf() {
/* 159 */     if (this.angle < Math.PI) {
/* 160 */       this.angle += Math.PI;
/*     */     } else {
/* 162 */       this.angle -= Math.PI;
/*     */     } 
/* 164 */     this.cos = -this.cos;
/* 165 */     this.sin = -this.sin;
/* 166 */     this.originOffset = -this.originOffset;
/*     */   }
/*     */   
/*     */   public Line getReverse() {
/* 175 */     return new Line((this.angle < Math.PI) ? (this.angle + Math.PI) : (this.angle - Math.PI), -this.cos, -this.sin, -this.originOffset);
/*     */   }
/*     */   
/*     */   public Vector1D toSubSpace(Vector<Euclidean2D> point) {
/* 181 */     Vector2D p2 = (Vector2D)point;
/* 182 */     return new Vector1D(this.cos * p2.getX() + this.sin * p2.getY());
/*     */   }
/*     */   
/*     */   public Vector2D toSpace(Vector<Euclidean1D> point) {
/* 187 */     double abscissa = ((Vector1D)point).getX();
/* 188 */     return new Vector2D(abscissa * this.cos - this.originOffset * this.sin, abscissa * this.sin + this.originOffset * this.cos);
/*     */   }
/*     */   
/*     */   public Vector2D intersection(Line other) {
/* 198 */     double d = this.sin * other.cos - other.sin * this.cos;
/* 199 */     if (FastMath.abs(d) < 1.0E-10D)
/* 200 */       return null; 
/* 202 */     return new Vector2D((this.cos * other.originOffset - other.cos * this.originOffset) / d, (this.sin * other.originOffset - other.sin * this.originOffset) / d);
/*     */   }
/*     */   
/*     */   public SubLine wholeHyperplane() {
/* 208 */     return new SubLine(this, (Region<Euclidean1D>)new IntervalsSet());
/*     */   }
/*     */   
/*     */   public PolygonsSet wholeSpace() {
/* 216 */     return new PolygonsSet();
/*     */   }
/*     */   
/*     */   public double getOffset(Line line) {
/* 230 */     return this.originOffset + ((this.cos * line.cos + this.sin * line.sin > 0.0D) ? -line.originOffset : line.originOffset);
/*     */   }
/*     */   
/*     */   public double getOffset(Vector<Euclidean2D> point) {
/* 236 */     Vector2D p2 = (Vector2D)point;
/* 237 */     return this.sin * p2.getX() - this.cos * p2.getY() + this.originOffset;
/*     */   }
/*     */   
/*     */   public boolean sameOrientationAs(Hyperplane<Euclidean2D> other) {
/* 242 */     Line otherL = (Line)other;
/* 243 */     return (this.sin * otherL.sin + this.cos * otherL.cos >= 0.0D);
/*     */   }
/*     */   
/*     */   public Vector2D getPointAt(Vector1D abscissa, double offset) {
/* 253 */     double x = abscissa.getX();
/* 254 */     double dOffset = offset - this.originOffset;
/* 255 */     return new Vector2D(x * this.cos + dOffset * this.sin, x * this.sin - dOffset * this.cos);
/*     */   }
/*     */   
/*     */   public boolean contains(Vector2D p) {
/* 263 */     return (FastMath.abs(getOffset(p)) < 1.0E-10D);
/*     */   }
/*     */   
/*     */   public boolean isParallelTo(Line line) {
/* 272 */     return (FastMath.abs(this.sin * line.cos - this.cos * line.sin) < 1.0E-10D);
/*     */   }
/*     */   
/*     */   public void translateToPoint(Vector2D p) {
/* 279 */     this.originOffset = this.cos * p.getY() - this.sin * p.getX();
/*     */   }
/*     */   
/*     */   public double getAngle() {
/* 286 */     return MathUtils.normalizeAngle(this.angle, Math.PI);
/*     */   }
/*     */   
/*     */   public void setAngle(double angle) {
/* 293 */     this.angle = MathUtils.normalizeAngle(angle, Math.PI);
/* 294 */     this.cos = FastMath.cos(this.angle);
/* 295 */     this.sin = FastMath.sin(this.angle);
/*     */   }
/*     */   
/*     */   public double getOriginOffset() {
/* 302 */     return this.originOffset;
/*     */   }
/*     */   
/*     */   public void setOriginOffset(double offset) {
/* 309 */     this.originOffset = offset;
/*     */   }
/*     */   
/*     */   public static Transform<Euclidean2D, Euclidean1D> getTransform(AffineTransform transform) throws MathIllegalArgumentException {
/* 327 */     return new LineTransform(transform);
/*     */   }
/*     */   
/*     */   private static class LineTransform implements Transform<Euclidean2D, Euclidean1D> {
/*     */     private double cXX;
/*     */     
/*     */     private double cXY;
/*     */     
/*     */     private double cX1;
/*     */     
/*     */     private double cYX;
/*     */     
/*     */     private double cYY;
/*     */     
/*     */     private double cY1;
/*     */     
/*     */     private double c1Y;
/*     */     
/*     */     private double c1X;
/*     */     
/*     */     private double c11;
/*     */     
/*     */     public LineTransform(AffineTransform transform) throws MathIllegalArgumentException {
/* 360 */       double[] m = new double[6];
/* 361 */       transform.getMatrix(m);
/* 362 */       this.cXX = m[0];
/* 363 */       this.cXY = m[2];
/* 364 */       this.cX1 = m[4];
/* 365 */       this.cYX = m[1];
/* 366 */       this.cYY = m[3];
/* 367 */       this.cY1 = m[5];
/* 369 */       this.c1Y = this.cXY * this.cY1 - this.cYY * this.cX1;
/* 370 */       this.c1X = this.cXX * this.cY1 - this.cYX * this.cX1;
/* 371 */       this.c11 = this.cXX * this.cYY - this.cYX * this.cXY;
/* 373 */       if (FastMath.abs(this.c11) < 1.0E-20D)
/* 374 */         throw new MathIllegalArgumentException(LocalizedFormats.NON_INVERTIBLE_TRANSFORM, new Object[0]); 
/*     */     }
/*     */     
/*     */     public Vector2D apply(Vector<Euclidean2D> point) {
/* 381 */       Vector2D p2D = (Vector2D)point;
/* 382 */       double x = p2D.getX();
/* 383 */       double y = p2D.getY();
/* 384 */       return new Vector2D(this.cXX * x + this.cXY * y + this.cX1, this.cYX * x + this.cYY * y + this.cY1);
/*     */     }
/*     */     
/*     */     public Line apply(Hyperplane<Euclidean2D> hyperplane) {
/* 390 */       Line line = (Line)hyperplane;
/* 391 */       double rOffset = this.c1X * line.cos + this.c1Y * line.sin + this.c11 * line.originOffset;
/* 392 */       double rCos = this.cXX * line.cos + this.cXY * line.sin;
/* 393 */       double rSin = this.cYX * line.cos + this.cYY * line.sin;
/* 394 */       double inv = 1.0D / FastMath.sqrt(rSin * rSin + rCos * rCos);
/* 395 */       return new Line(Math.PI + FastMath.atan2(-rSin, -rCos), inv * rCos, inv * rSin, inv * rOffset);
/*     */     }
/*     */     
/*     */     public SubHyperplane<Euclidean1D> apply(SubHyperplane<Euclidean1D> sub, Hyperplane<Euclidean2D> original, Hyperplane<Euclidean2D> transformed) {
/* 404 */       OrientedPoint op = (OrientedPoint)sub.getHyperplane();
/* 405 */       Line originalLine = (Line)original;
/* 406 */       Line transformedLine = (Line)transformed;
/* 407 */       Vector1D newLoc = transformedLine.toSubSpace(apply(originalLine.toSpace((Vector<Euclidean1D>)op.getLocation())));
/* 409 */       return (SubHyperplane<Euclidean1D>)(new OrientedPoint(newLoc, op.isDirect())).wholeHyperplane();
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\geometry\euclidean\twod\Line.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */