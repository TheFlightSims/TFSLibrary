/*     */ package math.geom2d.circulinear;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.util.Collection;
/*     */ import math.geom2d.AffineTransform2D;
/*     */ import math.geom2d.Box2D;
/*     */ import math.geom2d.Shape2D;
/*     */ import math.geom2d.circulinear.buffer.BufferCalculator;
/*     */ import math.geom2d.curve.ContinuousCurve2D;
/*     */ import math.geom2d.curve.Curve2D;
/*     */ import math.geom2d.curve.CurveSet2D;
/*     */ import math.geom2d.domain.Boundary2D;
/*     */ import math.geom2d.domain.BoundaryPolyCurve2D;
/*     */ import math.geom2d.domain.ContinuousOrientedCurve2D;
/*     */ import math.geom2d.domain.Contour2D;
/*     */ import math.geom2d.domain.Domain2D;
/*     */ import math.geom2d.domain.OrientedCurve2D;
/*     */ import math.geom2d.domain.PolyOrientedCurve2D;
/*     */ import math.geom2d.transform.CircleInversion2D;
/*     */ 
/*     */ public class GenericCirculinearRing2D extends PolyCirculinearCurve2D<CirculinearElement2D> implements CirculinearRing2D {
/*     */   public static <T extends CirculinearElement2D> GenericCirculinearRing2D create(Collection<T> curves) {
/*  42 */     return new GenericCirculinearRing2D(curves);
/*     */   }
/*     */   
/*     */   public static GenericCirculinearRing2D create(CirculinearElement2D... curves) {
/*  52 */     return new GenericCirculinearRing2D(curves);
/*     */   }
/*     */   
/*     */   public GenericCirculinearRing2D() {
/*  61 */     this.closed = true;
/*     */   }
/*     */   
/*     */   public GenericCirculinearRing2D(int size) {
/*  65 */     super(size);
/*  66 */     this.closed = true;
/*     */   }
/*     */   
/*     */   public GenericCirculinearRing2D(CirculinearElement2D... curves) {
/*  70 */     super(curves);
/*  71 */     this.closed = true;
/*     */   }
/*     */   
/*     */   public GenericCirculinearRing2D(Collection<? extends CirculinearElement2D> curves) {
/*  76 */     super(curves);
/*  77 */     this.closed = true;
/*     */   }
/*     */   
/*     */   public CirculinearRing2D parallel(double dist) {
/*  86 */     BufferCalculator bc = BufferCalculator.getDefaultInstance();
/*  88 */     return new GenericCirculinearRing2D(
/*  89 */         bc.createContinuousParallel(this, dist).smoothPieces());
/*     */   }
/*     */   
/*     */   public Collection<? extends GenericCirculinearRing2D> continuousCurves() {
/*  93 */     return wrapCurve((ContinuousCurve2D)this);
/*     */   }
/*     */   
/*     */   public GenericCirculinearRing2D transform(CircleInversion2D inv) {
/*  99 */     GenericCirculinearRing2D result = 
/* 100 */       new GenericCirculinearRing2D(this.curves.size());
/* 103 */     for (CirculinearElement2D element : this.curves)
/* 104 */       result.add(element.transform(inv)); 
/* 105 */     return result;
/*     */   }
/*     */   
/*     */   public void fill(Graphics2D g2) {
/* 112 */     g2.fill(getGeneralPath());
/*     */   }
/*     */   
/*     */   public CirculinearDomain2D domain() {
/* 119 */     return new GenericCirculinearDomain2D(this);
/*     */   }
/*     */   
/*     */   public GenericCirculinearRing2D reverse() {
/* 124 */     int n = this.curves.size();
/* 126 */     CirculinearElement2D[] curves2 = new CirculinearElement2D[n];
/* 129 */     for (int i = 0; i < n; i++)
/* 130 */       curves2[i] = ((CirculinearElement2D)this.curves.get(n - 1 - i)).reverse(); 
/* 133 */     return new GenericCirculinearRing2D(curves2);
/*     */   }
/*     */   
/*     */   public BoundaryPolyCurve2D<ContinuousOrientedCurve2D> transform(AffineTransform2D trans) {
/* 140 */     int n = size();
/* 143 */     BoundaryPolyCurve2D<ContinuousOrientedCurve2D> result = 
/* 144 */       new BoundaryPolyCurve2D(n);
/* 147 */     for (ContinuousOrientedCurve2D curve : this.curves)
/* 148 */       result.add((Curve2D)curve.transform(trans)); 
/* 149 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\circulinear\GenericCirculinearRing2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */