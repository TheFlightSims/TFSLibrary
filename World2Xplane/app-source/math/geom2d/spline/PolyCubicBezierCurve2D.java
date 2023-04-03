/*     */ package math.geom2d.spline;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import math.geom2d.AffineTransform2D;
/*     */ import math.geom2d.Box2D;
/*     */ import math.geom2d.Point2D;
/*     */ import math.geom2d.Shape2D;
/*     */ import math.geom2d.Vector2D;
/*     */ import math.geom2d.curve.ContinuousCurve2D;
/*     */ import math.geom2d.curve.Curve2D;
/*     */ import math.geom2d.curve.CurveArray2D;
/*     */ import math.geom2d.curve.CurveSet2D;
/*     */ import math.geom2d.curve.Curves2D;
/*     */ import math.geom2d.curve.PolyCurve2D;
/*     */ 
/*     */ public class PolyCubicBezierCurve2D extends PolyCurve2D<CubicBezierCurve2D> {
/*     */   public static final PolyCubicBezierCurve2D create(Point2D... points) {
/*  54 */     int np = points.length;
/*  57 */     int nc = (np - 1) / 3;
/*  60 */     PolyCubicBezierCurve2D polyBezier = new PolyCubicBezierCurve2D(nc);
/*  63 */     for (int i = 0; i < np - 3; i += 3)
/*  64 */       polyBezier.add((Curve2D)new CubicBezierCurve2D(
/*  65 */             points[i], 
/*  66 */             points[i + 1], 
/*  67 */             points[i + 2], 
/*  68 */             points[i + 3])); 
/*  72 */     return polyBezier;
/*     */   }
/*     */   
/*     */   public static final PolyCubicBezierCurve2D create(Point2D[] points, Vector2D[] vectors) {
/*  84 */     int np = Math.min(points.length, vectors.length);
/*  87 */     int nc = (np - 1) / 2;
/*  90 */     PolyCubicBezierCurve2D polyBezier = new PolyCubicBezierCurve2D(nc);
/*  93 */     for (int i = 0; i < nc - 1; i += 2)
/*  94 */       polyBezier.add((Curve2D)new CubicBezierCurve2D(
/*  95 */             points[i], 
/*  96 */             vectors[i], 
/*  97 */             points[i + 1], 
/*  98 */             vectors[i + 1])); 
/* 102 */     return polyBezier;
/*     */   }
/*     */   
/*     */   public PolyCubicBezierCurve2D() {}
/*     */   
/*     */   public PolyCubicBezierCurve2D(int n) {
/* 114 */     super(n);
/*     */   }
/*     */   
/*     */   public PolyCubicBezierCurve2D(CubicBezierCurve2D... curves) {
/* 118 */     super((ContinuousCurve2D[])curves);
/*     */   }
/*     */   
/*     */   public PolyCubicBezierCurve2D(Collection<CubicBezierCurve2D> curves) {
/* 122 */     super(curves);
/*     */   }
/*     */   
/*     */   public CurveSet2D<? extends PolyCubicBezierCurve2D> clip(Box2D box) {
/* 135 */     CurveSet2D<? extends Curve2D> set = Curves2D.clipCurve((Curve2D)this, box);
/* 138 */     CurveArray2D curveArray2D = 
/* 139 */       new CurveArray2D(set.size());
/* 142 */     for (Curve2D curve : set.curves()) {
/* 143 */       if (curve instanceof PolyCubicBezierCurve2D)
/* 144 */         curveArray2D.add((Shape2D)curve); 
/*     */     } 
/* 146 */     return (CurveSet2D<? extends PolyCubicBezierCurve2D>)curveArray2D;
/*     */   }
/*     */   
/*     */   public PolyCubicBezierCurve2D transform(AffineTransform2D trans) {
/* 151 */     PolyCubicBezierCurve2D result = new PolyCubicBezierCurve2D(this.curves.size());
/* 152 */     for (CubicBezierCurve2D curve : this.curves)
/* 153 */       result.add((Curve2D)curve.transform(trans)); 
/* 154 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\spline\PolyCubicBezierCurve2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */