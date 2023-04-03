/*     */ package math.geom2d.circulinear;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import math.geom2d.AffineTransform2D;
/*     */ import math.geom2d.Box2D;
/*     */ import math.geom2d.Shape2D;
/*     */ import math.geom2d.circulinear.buffer.BufferCalculator;
/*     */ import math.geom2d.curve.ContinuousCurve2D;
/*     */ import math.geom2d.curve.Curve2D;
/*     */ import math.geom2d.curve.CurveSet2D;
/*     */ import math.geom2d.curve.Curves2D;
/*     */ import math.geom2d.domain.Boundary2D;
/*     */ import math.geom2d.domain.BoundaryPolyCurve2D;
/*     */ import math.geom2d.domain.ContinuousOrientedCurve2D;
/*     */ import math.geom2d.domain.Contour2D;
/*     */ import math.geom2d.domain.Domain2D;
/*     */ import math.geom2d.domain.OrientedCurve2D;
/*     */ import math.geom2d.domain.PolyOrientedCurve2D;
/*     */ import math.geom2d.transform.CircleInversion2D;
/*     */ 
/*     */ public class BoundaryPolyCirculinearCurve2D<T extends CirculinearContinuousCurve2D> extends PolyCirculinearCurve2D<T> implements CirculinearContinuousCurve2D, CirculinearContour2D {
/*     */   public static <T extends CirculinearContinuousCurve2D> BoundaryPolyCirculinearCurve2D<T> create(Collection<T> curves) {
/*  50 */     return new BoundaryPolyCirculinearCurve2D<T>(curves);
/*     */   }
/*     */   
/*     */   public static <T extends CirculinearContinuousCurve2D> BoundaryPolyCirculinearCurve2D<T> create(Collection<T> curves, boolean closed) {
/*  61 */     return new BoundaryPolyCirculinearCurve2D<T>(curves, closed);
/*     */   }
/*     */   
/*     */   public static <T extends CirculinearContinuousCurve2D> BoundaryPolyCirculinearCurve2D<T> create(CirculinearContinuousCurve2D... curves) {
/*  71 */     return new BoundaryPolyCirculinearCurve2D<T>((T[])curves);
/*     */   }
/*     */   
/*     */   public static <T extends CirculinearContinuousCurve2D> BoundaryPolyCirculinearCurve2D<T> create(CirculinearContinuousCurve2D[] curves, boolean closed) {
/*  81 */     return new BoundaryPolyCirculinearCurve2D<T>((T[])curves, closed);
/*     */   }
/*     */   
/*     */   public static <T extends CirculinearContinuousCurve2D> BoundaryPolyCirculinearCurve2D<T> createClosed(CirculinearContinuousCurve2D... curves) {
/*  91 */     return new BoundaryPolyCirculinearCurve2D<T>((T[])curves, true);
/*     */   }
/*     */   
/*     */   public BoundaryPolyCirculinearCurve2D() {}
/*     */   
/*     */   public BoundaryPolyCirculinearCurve2D(int size) {
/* 103 */     super(size);
/*     */   }
/*     */   
/*     */   public BoundaryPolyCirculinearCurve2D(CirculinearContinuousCurve2D[] curves) {
/* 107 */     super((T[])curves);
/*     */   }
/*     */   
/*     */   public BoundaryPolyCirculinearCurve2D(CirculinearContinuousCurve2D[] curves, boolean closed) {
/* 111 */     super((T[])curves, closed);
/*     */   }
/*     */   
/*     */   public BoundaryPolyCirculinearCurve2D(Collection<? extends T> curves) {
/* 115 */     super(curves);
/*     */   }
/*     */   
/*     */   public BoundaryPolyCirculinearCurve2D(Collection<? extends T> curves, boolean closed) {
/* 119 */     super(curves, closed);
/*     */   }
/*     */   
/*     */   public double length() {
/* 131 */     double sum = 0.0D;
/* 132 */     for (CirculinearCurve2D curve : curves())
/* 133 */       sum += curve.length(); 
/* 134 */     return sum;
/*     */   }
/*     */   
/*     */   public double length(double pos) {
/* 142 */     return CirculinearCurves2D.getLength((CurveSet2D)this, pos);
/*     */   }
/*     */   
/*     */   public double position(double length) {
/* 150 */     return CirculinearCurves2D.getPosition((CurveSet2D)this, length);
/*     */   }
/*     */   
/*     */   public CirculinearRing2D parallel(double dist) {
/* 158 */     BufferCalculator bc = BufferCalculator.getDefaultInstance();
/* 160 */     return GenericCirculinearRing2D.create(
/* 161 */         bc.createContinuousParallel(this, dist).smoothPieces());
/*     */   }
/*     */   
/*     */   public BoundaryPolyCirculinearCurve2D<? extends CirculinearContinuousCurve2D> transform(CircleInversion2D inv) {
/* 172 */     int n = this.curves.size();
/* 173 */     BoundaryPolyCirculinearCurve2D<CirculinearContinuousCurve2D> result = 
/* 174 */       new BoundaryPolyCirculinearCurve2D(n);
/* 177 */     for (CirculinearContinuousCurve2D curve : this.curves)
/* 178 */       result.add(curve.transform(inv)); 
/* 179 */     return result;
/*     */   }
/*     */   
/*     */   public void fill(Graphics2D g2) {
/* 189 */     g2.fill(getGeneralPath());
/*     */   }
/*     */   
/*     */   public CirculinearDomain2D domain() {
/* 196 */     return new GenericCirculinearDomain2D(this);
/*     */   }
/*     */   
/*     */   public Collection<? extends CirculinearElement2D> smoothPieces() {
/* 210 */     ArrayList<CirculinearElement2D> result = 
/* 211 */       new ArrayList<CirculinearElement2D>();
/* 214 */     for (CirculinearContinuousCurve2D curve : this.curves)
/* 215 */       result.addAll(curve.smoothPieces()); 
/* 218 */     return result;
/*     */   }
/*     */   
/*     */   public Collection<? extends BoundaryPolyCirculinearCurve2D<?>> continuousCurves() {
/* 227 */     return wrapCurve((ContinuousCurve2D)this);
/*     */   }
/*     */   
/*     */   public BoundaryPolyCirculinearCurve2D<? extends CirculinearContinuousCurve2D> reverse() {
/* 233 */     int n = this.curves.size();
/* 235 */     CirculinearContinuousCurve2D[] curves2 = 
/* 236 */       new CirculinearContinuousCurve2D[n];
/* 239 */     for (int i = 0; i < n; i++)
/* 240 */       curves2[i] = ((CirculinearContinuousCurve2D)this.curves.get(n - 1 - i)).reverse(); 
/* 243 */     return new BoundaryPolyCirculinearCurve2D((T[])curves2);
/*     */   }
/*     */   
/*     */   public PolyCirculinearCurve2D<? extends CirculinearContinuousCurve2D> subCurve(double t0, double t1) {
/* 250 */     PolyOrientedCurve2D<? extends ContinuousOrientedCurve2D> subcurve = 
/* 251 */       (PolyOrientedCurve2D)super.subCurve(t0, t1);
/* 254 */     int n = subcurve.size();
/* 255 */     PolyCirculinearCurve2D<CirculinearContinuousCurve2D> result = 
/* 256 */       new PolyCirculinearCurve2D<CirculinearContinuousCurve2D>(n);
/* 259 */     for (Curve2D curve : subcurve) {
/* 260 */       if (curve instanceof CirculinearContinuousCurve2D)
/* 261 */         result.add(curve); 
/*     */     } 
/* 265 */     return result;
/*     */   }
/*     */   
/*     */   public CirculinearCurveSet2D<? extends CirculinearContinuousCurve2D> clip(Box2D box) {
/* 275 */     CurveSet2D<? extends Curve2D> set = Curves2D.clipCurve(this, box);
/* 278 */     int n = set.size();
/* 279 */     CirculinearCurveArray2D<CirculinearContinuousCurve2D> result = 
/* 280 */       new CirculinearCurveArray2D<CirculinearContinuousCurve2D>(n);
/* 283 */     for (Curve2D curve : set.curves()) {
/* 284 */       if (curve instanceof CirculinearContinuousCurve2D)
/* 285 */         result.add(curve); 
/*     */     } 
/* 289 */     return result;
/*     */   }
/*     */   
/*     */   public BoundaryPolyCurve2D<? extends ContinuousOrientedCurve2D> transform(AffineTransform2D trans) {
/* 296 */     int n = size();
/* 299 */     BoundaryPolyCurve2D<ContinuousOrientedCurve2D> result = 
/* 300 */       new BoundaryPolyCurve2D(n);
/* 303 */     for (ContinuousOrientedCurve2D curve : this.curves)
/* 304 */       result.add((Curve2D)curve.transform(trans)); 
/* 306 */     result.setClosed(isClosed());
/* 307 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\circulinear\BoundaryPolyCirculinearCurve2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */