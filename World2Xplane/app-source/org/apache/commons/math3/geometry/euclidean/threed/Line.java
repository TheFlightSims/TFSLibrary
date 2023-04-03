/*     */ package org.apache.commons.math3.geometry.euclidean.threed;
/*     */ 
/*     */ import org.apache.commons.math3.exception.MathIllegalArgumentException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.geometry.Vector;
/*     */ import org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D;
/*     */ import org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet;
/*     */ import org.apache.commons.math3.geometry.euclidean.oned.Vector1D;
/*     */ import org.apache.commons.math3.geometry.partitioning.Embedding;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class Line implements Embedding<Euclidean3D, Euclidean1D> {
/*     */   private Vector3D direction;
/*     */   
/*     */   private Vector3D zero;
/*     */   
/*     */   public Line(Vector3D p1, Vector3D p2) {
/*  55 */     reset(p1, p2);
/*     */   }
/*     */   
/*     */   public Line(Line line) {
/*  64 */     this.direction = line.direction;
/*  65 */     this.zero = line.zero;
/*     */   }
/*     */   
/*     */   public void reset(Vector3D p1, Vector3D p2) {
/*  74 */     Vector3D delta = p2.subtract(p1);
/*  75 */     double norm2 = delta.getNormSq();
/*  76 */     if (norm2 == 0.0D)
/*  77 */       throw new MathIllegalArgumentException(LocalizedFormats.ZERO_NORM, new Object[0]); 
/*  79 */     this.direction = new Vector3D(1.0D / FastMath.sqrt(norm2), delta);
/*  80 */     this.zero = new Vector3D(1.0D, p1, -p1.dotProduct(delta) / norm2, delta);
/*     */   }
/*     */   
/*     */   public Line revert() {
/*  87 */     return new Line(this.zero, this.zero.subtract(this.direction));
/*     */   }
/*     */   
/*     */   public Vector3D getDirection() {
/*  94 */     return this.direction;
/*     */   }
/*     */   
/*     */   public Vector3D getOrigin() {
/* 101 */     return this.zero;
/*     */   }
/*     */   
/*     */   public double getAbscissa(Vector3D point) {
/* 112 */     return point.subtract(this.zero).dotProduct(this.direction);
/*     */   }
/*     */   
/*     */   public Vector3D pointAt(double abscissa) {
/* 120 */     return new Vector3D(1.0D, this.zero, abscissa, this.direction);
/*     */   }
/*     */   
/*     */   public Vector1D toSubSpace(Vector<Euclidean3D> point) {
/* 127 */     return new Vector1D(getAbscissa((Vector3D)point));
/*     */   }
/*     */   
/*     */   public Vector3D toSpace(Vector<Euclidean1D> point) {
/* 134 */     return pointAt(((Vector1D)point).getX());
/*     */   }
/*     */   
/*     */   public boolean isSimilarTo(Line line) {
/* 145 */     double angle = Vector3D.angle(this.direction, line.direction);
/* 146 */     return ((angle < 1.0E-10D || angle > 3.141592653489793D) && contains(line.zero));
/*     */   }
/*     */   
/*     */   public boolean contains(Vector3D p) {
/* 154 */     return (distance(p) < 1.0E-10D);
/*     */   }
/*     */   
/*     */   public double distance(Vector3D p) {
/* 162 */     Vector3D d = p.subtract(this.zero);
/* 163 */     Vector3D n = new Vector3D(1.0D, d, -d.dotProduct(this.direction), this.direction);
/* 164 */     return n.getNorm();
/*     */   }
/*     */   
/*     */   public double distance(Line line) {
/* 173 */     Vector3D normal = Vector3D.crossProduct(this.direction, line.direction);
/* 174 */     double n = normal.getNorm();
/* 175 */     if (n < 2.2250738585072014E-308D)
/* 177 */       return distance(line.zero); 
/* 181 */     double offset = line.zero.subtract(this.zero).dotProduct(normal) / n;
/* 183 */     return FastMath.abs(offset);
/*     */   }
/*     */   
/*     */   public Vector3D closestPoint(Line line) {
/* 193 */     double cos = this.direction.dotProduct(line.direction);
/* 194 */     double n = 1.0D - cos * cos;
/* 195 */     if (n < 1.1102230246251565E-16D)
/* 197 */       return this.zero; 
/* 200 */     Vector3D delta0 = line.zero.subtract(this.zero);
/* 201 */     double a = delta0.dotProduct(this.direction);
/* 202 */     double b = delta0.dotProduct(line.direction);
/* 204 */     return new Vector3D(1.0D, this.zero, (a - b * cos) / n, this.direction);
/*     */   }
/*     */   
/*     */   public Vector3D intersection(Line line) {
/* 214 */     Vector3D closest = closestPoint(line);
/* 215 */     return line.contains(closest) ? closest : null;
/*     */   }
/*     */   
/*     */   public SubLine wholeLine() {
/* 222 */     return new SubLine(this, new IntervalsSet());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\geometry\euclidean\threed\Line.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */