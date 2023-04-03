/*     */ package math.geom2d.circulinear;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import math.geom2d.Box2D;
/*     */ import math.geom2d.Shape2D;
/*     */ import math.geom2d.circulinear.buffer.BufferCalculator;
/*     */ import math.geom2d.curve.Curve2D;
/*     */ import math.geom2d.curve.CurveSet2D;
/*     */ import math.geom2d.curve.Curves2D;
/*     */ import math.geom2d.domain.Boundary2D;
/*     */ import math.geom2d.domain.ContinuousOrientedCurve2D;
/*     */ import math.geom2d.domain.ContourArray2D;
/*     */ import math.geom2d.domain.Domain2D;
/*     */ import math.geom2d.domain.OrientedCurve2D;
/*     */ import math.geom2d.transform.CircleInversion2D;
/*     */ 
/*     */ public class CirculinearContourArray2D<T extends CirculinearContour2D> extends ContourArray2D<T> implements CirculinearBoundary2D {
/*     */   public static <T extends CirculinearContour2D> CirculinearContourArray2D<T> create(Collection<T> curves) {
/*  51 */     return new CirculinearContourArray2D<T>(curves);
/*     */   }
/*     */   
/*     */   public static <T extends CirculinearContour2D> CirculinearContourArray2D<T> create(CirculinearContour2D... curves) {
/*  61 */     return new CirculinearContourArray2D<T>((T[])curves);
/*     */   }
/*     */   
/*     */   public CirculinearContourArray2D() {
/*  73 */     this.curves = new ArrayList();
/*     */   }
/*     */   
/*     */   public CirculinearContourArray2D(int n) {
/*  81 */     this.curves = new ArrayList(n);
/*     */   }
/*     */   
/*     */   public CirculinearContourArray2D(CirculinearContour2D... curves) {
/*  90 */     this.curves = new ArrayList(curves.length);
/*     */     byte b;
/*     */     int i;
/*     */     CirculinearContour2D[] arrayOfCirculinearContour2D;
/*  91 */     for (i = (arrayOfCirculinearContour2D = curves).length, b = 0; b < i; ) {
/*  91 */       CirculinearContour2D circulinearContour2D = arrayOfCirculinearContour2D[b];
/*  92 */       add(circulinearContour2D);
/*     */       b++;
/*     */     } 
/*     */   }
/*     */   
/*     */   public CirculinearContourArray2D(T curve) {
/* 101 */     this.curves = new ArrayList();
/* 102 */     this.curves.add(curve);
/*     */   }
/*     */   
/*     */   public CirculinearContourArray2D(Collection<? extends T> curves) {
/* 112 */     this.curves = new ArrayList(curves.size());
/* 113 */     this.curves.addAll(curves);
/*     */   }
/*     */   
/*     */   public CirculinearDomain2D domain() {
/* 124 */     return new GenericCirculinearDomain2D(this);
/*     */   }
/*     */   
/*     */   public double length() {
/* 135 */     double sum = 0.0D;
/* 136 */     for (CirculinearCurve2D curve : curves())
/* 137 */       sum += curve.length(); 
/* 138 */     return sum;
/*     */   }
/*     */   
/*     */   public double length(double pos) {
/* 145 */     return CirculinearCurves2D.getLength((CurveSet2D)this, pos);
/*     */   }
/*     */   
/*     */   public double position(double length) {
/* 152 */     return CirculinearCurves2D.getPosition((CurveSet2D)this, length);
/*     */   }
/*     */   
/*     */   public CirculinearDomain2D buffer(double dist) {
/* 159 */     BufferCalculator bc = BufferCalculator.getDefaultInstance();
/* 160 */     return bc.computeBuffer(this, dist);
/*     */   }
/*     */   
/*     */   public CirculinearBoundary2D parallel(double d) {
/* 167 */     BufferCalculator bc = BufferCalculator.getDefaultInstance();
/* 168 */     return bc.createParallelBoundary(this, d);
/*     */   }
/*     */   
/*     */   public CirculinearContourArray2D<? extends CirculinearContour2D> transform(CircleInversion2D inv) {
/* 177 */     CirculinearContourArray2D<CirculinearContour2D> result = 
/* 178 */       new CirculinearContourArray2D(
/* 179 */         this.curves.size());
/* 182 */     for (CirculinearContour2D curve : this.curves)
/* 183 */       result.add(curve.transform(inv)); 
/* 184 */     return result;
/*     */   }
/*     */   
/*     */   public Collection<T> continuousCurves() {
/* 193 */     return Collections.unmodifiableCollection(this.curves);
/*     */   }
/*     */   
/*     */   public CirculinearCurveSet2D<? extends CirculinearContinuousCurve2D> clip(Box2D box) {
/* 200 */     CurveSet2D<? extends Curve2D> set = Curves2D.clipCurve(this, box);
/* 203 */     int n = set.size();
/* 204 */     CirculinearCurveArray2D<CirculinearContinuousCurve2D> result = 
/* 205 */       new CirculinearCurveArray2D<CirculinearContinuousCurve2D>(n);
/* 208 */     for (Curve2D curve : set.curves()) {
/* 209 */       if (curve instanceof CirculinearContinuousCurve2D)
/* 210 */         result.add(curve); 
/*     */     } 
/* 214 */     return result;
/*     */   }
/*     */   
/*     */   public CirculinearContourArray2D<? extends CirculinearContour2D> reverse() {
/* 220 */     int n = this.curves.size();
/* 222 */     CirculinearContour2D[] curves2 = new CirculinearContour2D[n];
/* 225 */     for (int i = 0; i < n; i++)
/* 226 */       curves2[i] = ((CirculinearContour2D)this.curves.get(n - 1 - i)).reverse(); 
/* 229 */     return new CirculinearContourArray2D((T[])curves2);
/*     */   }
/*     */   
/*     */   public CirculinearCurveSet2D<? extends CirculinearContinuousCurve2D> subCurve(double t0, double t1) {
/* 236 */     CurveSet2D<? extends ContinuousOrientedCurve2D> curveSet = 
/* 237 */       super.subCurve(t0, t1);
/* 240 */     ArrayList<CirculinearContinuousCurve2D> curves = 
/* 241 */       new ArrayList<CirculinearContinuousCurve2D>(
/* 242 */         curveSet.size());
/* 245 */     for (Curve2D curve : curveSet.curves())
/* 246 */       curves.add((CirculinearContinuousCurve2D)curve); 
/* 249 */     return CirculinearCurveArray2D.create(curves);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\circulinear\CirculinearContourArray2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */