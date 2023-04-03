/*     */ package math.geom2d.circulinear;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import math.geom2d.Box2D;
/*     */ import math.geom2d.Shape2D;
/*     */ import math.geom2d.circulinear.buffer.BufferCalculator;
/*     */ import math.geom2d.curve.ContinuousCurve2D;
/*     */ import math.geom2d.curve.Curve2D;
/*     */ import math.geom2d.curve.CurveSet2D;
/*     */ import math.geom2d.curve.Curves2D;
/*     */ import math.geom2d.domain.ContinuousOrientedCurve2D;
/*     */ import math.geom2d.domain.OrientedCurve2D;
/*     */ import math.geom2d.domain.PolyOrientedCurve2D;
/*     */ import math.geom2d.transform.CircleInversion2D;
/*     */ 
/*     */ public class PolyCirculinearCurve2D<T extends CirculinearContinuousCurve2D> extends PolyOrientedCurve2D<T> implements CirculinearContinuousCurve2D {
/*     */   public static <T extends CirculinearContinuousCurve2D> PolyCirculinearCurve2D<T> create(Collection<T> curves) {
/*  41 */     return new PolyCirculinearCurve2D<T>(curves);
/*     */   }
/*     */   
/*     */   public static <T extends CirculinearContinuousCurve2D> PolyCirculinearCurve2D<T> create(CirculinearContinuousCurve2D... curves) {
/*  51 */     return new PolyCirculinearCurve2D<T>((T[])curves);
/*     */   }
/*     */   
/*     */   public static <T extends CirculinearContinuousCurve2D> PolyCirculinearCurve2D<T> create(Collection<T> curves, boolean closed) {
/*  61 */     return new PolyCirculinearCurve2D<T>(curves, closed);
/*     */   }
/*     */   
/*     */   public static <T extends CirculinearContinuousCurve2D> PolyCirculinearCurve2D<T> create(CirculinearContinuousCurve2D[] curves, boolean closed) {
/*  71 */     return new PolyCirculinearCurve2D<T>((T[])curves, closed);
/*     */   }
/*     */   
/*     */   public static <T extends CirculinearContinuousCurve2D> PolyCirculinearCurve2D<T> createClosed(CirculinearContinuousCurve2D... curves) {
/*  81 */     return new PolyCirculinearCurve2D<T>((T[])curves, true);
/*     */   }
/*     */   
/*     */   public PolyCirculinearCurve2D() {}
/*     */   
/*     */   public PolyCirculinearCurve2D(int size) {
/*  93 */     super(size);
/*     */   }
/*     */   
/*     */   public PolyCirculinearCurve2D(CirculinearContinuousCurve2D[] curves) {
/*  97 */     super((ContinuousOrientedCurve2D[])curves);
/*     */   }
/*     */   
/*     */   public PolyCirculinearCurve2D(CirculinearContinuousCurve2D[] curves, boolean closed) {
/* 101 */     super((ContinuousOrientedCurve2D[])curves, closed);
/*     */   }
/*     */   
/*     */   public PolyCirculinearCurve2D(Collection<? extends T> curves) {
/* 105 */     super(curves);
/*     */   }
/*     */   
/*     */   public PolyCirculinearCurve2D(Collection<? extends T> curves, boolean closed) {
/* 109 */     super(curves, closed);
/*     */   }
/*     */   
/*     */   public double length() {
/* 119 */     double sum = 0.0D;
/* 120 */     for (CirculinearCurve2D curve : curves())
/* 121 */       sum += curve.length(); 
/* 122 */     return sum;
/*     */   }
/*     */   
/*     */   public double length(double pos) {
/* 129 */     return CirculinearCurves2D.getLength((CurveSet2D)this, pos);
/*     */   }
/*     */   
/*     */   public double position(double length) {
/* 136 */     return CirculinearCurves2D.getPosition((CurveSet2D)this, length);
/*     */   }
/*     */   
/*     */   public CirculinearDomain2D buffer(double dist) {
/* 143 */     BufferCalculator bc = BufferCalculator.getDefaultInstance();
/* 144 */     return bc.computeBuffer(this, dist);
/*     */   }
/*     */   
/*     */   public CirculinearContinuousCurve2D parallel(double d) {
/* 151 */     BufferCalculator bc = BufferCalculator.getDefaultInstance();
/* 152 */     return bc.createContinuousParallel(this, d);
/*     */   }
/*     */   
/*     */   public PolyCirculinearCurve2D<? extends CirculinearContinuousCurve2D> transform(CircleInversion2D inv) {
/* 161 */     int n = this.curves.size();
/* 162 */     PolyCirculinearCurve2D<CirculinearContinuousCurve2D> result = 
/* 163 */       new PolyCirculinearCurve2D(n);
/* 166 */     for (CirculinearContinuousCurve2D curve : this.curves)
/* 167 */       result.add(curve.transform(inv)); 
/* 168 */     return result;
/*     */   }
/*     */   
/*     */   public Collection<? extends CirculinearElement2D> smoothPieces() {
/* 182 */     ArrayList<CirculinearElement2D> result = 
/* 183 */       new ArrayList<CirculinearElement2D>();
/* 186 */     for (CirculinearContinuousCurve2D curve : this.curves)
/* 187 */       result.addAll(curve.smoothPieces()); 
/* 190 */     return result;
/*     */   }
/*     */   
/*     */   public Collection<? extends PolyCirculinearCurve2D<?>> continuousCurves() {
/* 199 */     return wrapCurve((ContinuousCurve2D)this);
/*     */   }
/*     */   
/*     */   public CirculinearCurveSet2D<? extends CirculinearContinuousCurve2D> clip(Box2D box) {
/* 206 */     CurveSet2D<? extends Curve2D> set = Curves2D.clipCurve(this, box);
/* 209 */     int n = set.size();
/* 210 */     CirculinearCurveArray2D<CirculinearContinuousCurve2D> result = 
/* 211 */       new CirculinearCurveArray2D<CirculinearContinuousCurve2D>(n);
/* 214 */     for (Curve2D curve : set.curves()) {
/* 215 */       if (curve instanceof CirculinearContinuousCurve2D)
/* 216 */         result.add(curve); 
/*     */     } 
/* 220 */     return result;
/*     */   }
/*     */   
/*     */   public PolyCirculinearCurve2D<? extends CirculinearContinuousCurve2D> reverse() {
/* 227 */     int n = this.curves.size();
/* 228 */     CirculinearContinuousCurve2D[] curves2 = 
/* 229 */       new CirculinearContinuousCurve2D[n];
/* 232 */     for (int i = 0; i < n; i++)
/* 233 */       curves2[i] = ((CirculinearContinuousCurve2D)this.curves.get(n - 1 - i)).reverse(); 
/* 236 */     return create(curves2, this.closed);
/*     */   }
/*     */   
/*     */   public PolyCirculinearCurve2D<? extends CirculinearContinuousCurve2D> subCurve(double t0, double t1) {
/* 243 */     PolyOrientedCurve2D<? extends ContinuousOrientedCurve2D> subcurve = 
/* 244 */       super.subCurve(t0, t1);
/* 247 */     int n = subcurve.size();
/* 248 */     PolyCirculinearCurve2D<CirculinearContinuousCurve2D> result = 
/* 249 */       new PolyCirculinearCurve2D(n);
/* 252 */     for (Curve2D curve : subcurve) {
/* 253 */       if (curve instanceof CirculinearContinuousCurve2D)
/* 254 */         result.add(curve); 
/*     */     } 
/* 258 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\circulinear\PolyCirculinearCurve2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */