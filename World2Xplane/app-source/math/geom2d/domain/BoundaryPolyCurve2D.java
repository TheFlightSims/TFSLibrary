/*     */ package math.geom2d.domain;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.util.Collection;
/*     */ import math.geom2d.AffineTransform2D;
/*     */ import math.geom2d.Shape2D;
/*     */ import math.geom2d.curve.ContinuousCurve2D;
/*     */ import math.geom2d.curve.Curve2D;
/*     */ import math.geom2d.curve.CurveSet2D;
/*     */ 
/*     */ public class BoundaryPolyCurve2D<T extends ContinuousOrientedCurve2D> extends PolyOrientedCurve2D<T> implements Contour2D {
/*     */   public static <T extends ContinuousOrientedCurve2D> BoundaryPolyCurve2D<T> create(Collection<T> curves) {
/*  55 */     return new BoundaryPolyCurve2D<T>(curves);
/*     */   }
/*     */   
/*     */   public static <T extends ContinuousOrientedCurve2D> BoundaryPolyCurve2D<T> create(ContinuousOrientedCurve2D... curves) {
/*  65 */     return new BoundaryPolyCurve2D<T>((T[])curves);
/*     */   }
/*     */   
/*     */   public BoundaryPolyCurve2D() {}
/*     */   
/*     */   public BoundaryPolyCurve2D(int n) {
/*  84 */     super(n);
/*     */   }
/*     */   
/*     */   public BoundaryPolyCurve2D(ContinuousOrientedCurve2D... curves) {
/*  91 */     super((T[])curves);
/*     */   }
/*     */   
/*     */   public BoundaryPolyCurve2D(Collection<? extends T> curves) {
/*  98 */     super(curves);
/*     */   }
/*     */   
/*     */   public boolean isClosed() {
/* 111 */     for (ContinuousOrientedCurve2D continuousOrientedCurve2D : this.curves) {
/* 112 */       if (!continuousOrientedCurve2D.isBounded())
/* 113 */         return false; 
/*     */     } 
/* 115 */     return true;
/*     */   }
/*     */   
/*     */   public Collection<BoundaryPolyCurve2D<T>> continuousCurves() {
/* 122 */     return wrapCurve(this);
/*     */   }
/*     */   
/*     */   public Domain2D domain() {
/* 126 */     return new GenericDomain2D(this);
/*     */   }
/*     */   
/*     */   public void fill(Graphics2D g2) {
/* 130 */     g2.fill(getGeneralPath());
/*     */   }
/*     */   
/*     */   public BoundaryPolyCurve2D<? extends ContinuousOrientedCurve2D> reverse() {
/* 138 */     ContinuousOrientedCurve2D[] curves2 = 
/* 139 */       new ContinuousOrientedCurve2D[this.curves.size()];
/* 140 */     int n = this.curves.size();
/* 141 */     for (int i = 0; i < n; i++)
/* 142 */       curves2[i] = ((ContinuousOrientedCurve2D)this.curves.get(n - 1 - i)).reverse(); 
/* 143 */     return new BoundaryPolyCurve2D((T[])curves2);
/*     */   }
/*     */   
/*     */   public BoundaryPolyCurve2D<ContinuousOrientedCurve2D> transform(AffineTransform2D trans) {
/* 150 */     BoundaryPolyCurve2D<ContinuousOrientedCurve2D> result = 
/* 151 */       new BoundaryPolyCurve2D(this.curves.size());
/* 154 */     for (ContinuousOrientedCurve2D curve : this.curves)
/* 155 */       result.add(curve.transform(trans)); 
/* 156 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\domain\BoundaryPolyCurve2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */