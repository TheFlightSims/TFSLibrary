/*     */ package math.geom2d.domain;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import math.geom2d.AffineTransform2D;
/*     */ import math.geom2d.Box2D;
/*     */ import math.geom2d.Point2D;
/*     */ import math.geom2d.Shape2D;
/*     */ import math.geom2d.curve.Curve2D;
/*     */ import math.geom2d.curve.CurveArray2D;
/*     */ import math.geom2d.curve.CurveSet2D;
/*     */ import math.geom2d.curve.Curves2D;
/*     */ 
/*     */ public class ContourArray2D<T extends Contour2D> extends CurveArray2D<T> implements Boundary2D {
/*     */   public static <T extends Contour2D> ContourArray2D<T> create(Collection<T> curves) {
/*  59 */     return new ContourArray2D<T>(curves);
/*     */   }
/*     */   
/*     */   public static <T extends Contour2D> ContourArray2D<T> create(Contour2D... curves) {
/*  69 */     return new ContourArray2D<T>((T[])curves);
/*     */   }
/*     */   
/*     */   public ContourArray2D() {}
/*     */   
/*     */   public ContourArray2D(int size) {
/*  79 */     super(size);
/*     */   }
/*     */   
/*     */   public ContourArray2D(Contour2D... curves) {
/*  83 */     super((Curve2D[])curves);
/*     */   }
/*     */   
/*     */   public ContourArray2D(Collection<? extends T> curves) {
/*  87 */     super(curves);
/*     */   }
/*     */   
/*     */   public ContourArray2D(T curve) {
/*  92 */     add((Curve2D)curve);
/*     */   }
/*     */   
/*     */   public Collection<? extends T> continuousCurves() {
/* 100 */     return Collections.unmodifiableCollection(this.curves);
/*     */   }
/*     */   
/*     */   public Domain2D domain() {
/* 104 */     return new GenericDomain2D(this);
/*     */   }
/*     */   
/*     */   public void fill(Graphics2D g2) {
/* 108 */     g2.fill(getGeneralPath());
/*     */   }
/*     */   
/*     */   public double windingAngle(Point2D point) {
/* 115 */     double angle = 0.0D;
/* 116 */     for (OrientedCurve2D curve : curves())
/* 117 */       angle += curve.windingAngle(point); 
/* 118 */     return angle;
/*     */   }
/*     */   
/*     */   public double signedDistance(Point2D p) {
/* 122 */     return signedDistance(p.x(), p.y());
/*     */   }
/*     */   
/*     */   public double signedDistance(double x, double y) {
/* 131 */     double minDist = Double.POSITIVE_INFINITY;
/* 132 */     double dist = Double.POSITIVE_INFINITY;
/* 134 */     for (OrientedCurve2D curve : curves()) {
/* 135 */       dist = Math.min(dist, curve.signedDistance(x, y));
/* 136 */       if (Math.abs(dist) < Math.abs(minDist))
/* 137 */         minDist = dist; 
/*     */     } 
/* 139 */     return minDist;
/*     */   }
/*     */   
/*     */   public boolean isInside(Point2D point) {
/* 143 */     return (signedDistance(point.x(), point.y()) < 0.0D);
/*     */   }
/*     */   
/*     */   public ContourArray2D<? extends Contour2D> reverse() {
/* 151 */     Contour2D[] curves2 = new Contour2D[this.curves.size()];
/* 152 */     int n = this.curves.size();
/* 153 */     for (int i = 0; i < n; i++)
/* 154 */       curves2[i] = ((Contour2D)this.curves.get(n - 1 - i)).reverse(); 
/* 155 */     return new ContourArray2D((T[])curves2);
/*     */   }
/*     */   
/*     */   public CurveSet2D<? extends ContinuousOrientedCurve2D> subCurve(double t0, double t1) {
/* 162 */     CurveSet2D<? extends Curve2D> curveSet = super.subCurve(t0, t1);
/* 165 */     ArrayList<ContinuousOrientedCurve2D> curves = 
/* 166 */       new ArrayList<ContinuousOrientedCurve2D>();
/* 167 */     for (Curve2D curve : curveSet.curves())
/* 168 */       curves.add((ContinuousOrientedCurve2D)curve); 
/* 171 */     return (CurveSet2D<? extends ContinuousOrientedCurve2D>)new CurveArray2D(curves);
/*     */   }
/*     */   
/*     */   public CurveSet2D<? extends ContinuousOrientedCurve2D> clip(Box2D box) {
/* 187 */     CurveSet2D<? extends Curve2D> set = Curves2D.clipCurve(this, box);
/* 190 */     CurveArray2D<ContinuousOrientedCurve2D> result = 
/* 191 */       new CurveArray2D(set.size());
/* 194 */     for (Curve2D curve : set.curves()) {
/* 195 */       if (curve instanceof ContinuousOrientedCurve2D)
/* 196 */         result.add(curve); 
/*     */     } 
/* 198 */     return (CurveSet2D<? extends ContinuousOrientedCurve2D>)result;
/*     */   }
/*     */   
/*     */   public ContourArray2D<? extends Contour2D> transform(AffineTransform2D trans) {
/* 204 */     ContourArray2D<Contour2D> result = 
/* 205 */       new ContourArray2D(this.curves.size());
/* 206 */     for (Curve2D curve : this.curves)
/* 207 */       result.add(curve.transform(trans)); 
/* 208 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 214 */     if (!(obj instanceof ContourArray2D))
/* 215 */       return false; 
/* 217 */     return super.equals(obj);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\domain\ContourArray2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */