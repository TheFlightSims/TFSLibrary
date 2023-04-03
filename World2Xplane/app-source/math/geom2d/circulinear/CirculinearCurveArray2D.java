/*     */ package math.geom2d.circulinear;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import math.geom2d.Box2D;
/*     */ import math.geom2d.Shape2D;
/*     */ import math.geom2d.circulinear.buffer.BufferCalculator;
/*     */ import math.geom2d.curve.Curve2D;
/*     */ import math.geom2d.curve.CurveArray2D;
/*     */ import math.geom2d.curve.CurveSet2D;
/*     */ import math.geom2d.curve.Curves2D;
/*     */ import math.geom2d.transform.CircleInversion2D;
/*     */ 
/*     */ public class CirculinearCurveArray2D<T extends CirculinearCurve2D> extends CurveArray2D<T> implements CirculinearCurveSet2D<T> {
/*     */   public static <T extends CirculinearCurve2D> CirculinearCurveArray2D<T> create(Collection<T> curves) {
/*  51 */     return new CirculinearCurveArray2D<T>(curves);
/*     */   }
/*     */   
/*     */   public static <T extends CirculinearCurve2D> CirculinearCurveArray2D<T> create(CirculinearCurve2D... curves) {
/*  61 */     return new CirculinearCurveArray2D<T>((T[])curves);
/*     */   }
/*     */   
/*     */   public CirculinearCurveArray2D() {
/*  72 */     this.curves = new ArrayList();
/*     */   }
/*     */   
/*     */   public CirculinearCurveArray2D(int n) {
/*  80 */     this.curves = new ArrayList(n);
/*     */   }
/*     */   
/*     */   public CirculinearCurveArray2D(CirculinearCurve2D... curves) {
/*  89 */     this.curves = new ArrayList(curves.length);
/*     */     byte b;
/*     */     int i;
/*     */     CirculinearCurve2D[] arrayOfCirculinearCurve2D;
/*  90 */     for (i = (arrayOfCirculinearCurve2D = curves).length, b = 0; b < i; ) {
/*  90 */       CirculinearCurve2D circulinearCurve2D = arrayOfCirculinearCurve2D[b];
/*  91 */       add(circulinearCurve2D);
/*     */       b++;
/*     */     } 
/*     */   }
/*     */   
/*     */   public CirculinearCurveArray2D(Collection<? extends T> curves) {
/* 101 */     this.curves = new ArrayList(curves.size());
/* 102 */     this.curves.addAll(curves);
/*     */   }
/*     */   
/*     */   public double length() {
/* 113 */     double sum = 0.0D;
/* 114 */     for (CirculinearCurve2D curve : curves())
/* 115 */       sum += curve.length(); 
/* 116 */     return sum;
/*     */   }
/*     */   
/*     */   public double length(double pos) {
/* 123 */     return CirculinearCurves2D.getLength(this, pos);
/*     */   }
/*     */   
/*     */   public double position(double length) {
/* 130 */     return CirculinearCurves2D.getPosition(this, length);
/*     */   }
/*     */   
/*     */   public CirculinearDomain2D buffer(double dist) {
/* 137 */     BufferCalculator bc = BufferCalculator.getDefaultInstance();
/* 138 */     return bc.computeBuffer(this, dist);
/*     */   }
/*     */   
/*     */   public CirculinearCurve2D parallel(double d) {
/* 145 */     BufferCalculator bc = BufferCalculator.getDefaultInstance();
/* 146 */     return bc.createParallel(this, d);
/*     */   }
/*     */   
/*     */   public CirculinearCurveArray2D<CirculinearCurve2D> transform(CircleInversion2D inv) {
/* 154 */     CirculinearCurveArray2D<CirculinearCurve2D> result = 
/* 155 */       new CirculinearCurveArray2D(this.curves.size());
/* 158 */     for (CirculinearCurve2D curve : this.curves)
/* 159 */       result.add(curve.transform(inv)); 
/* 160 */     return result;
/*     */   }
/*     */   
/*     */   public Collection<? extends CirculinearContinuousCurve2D> continuousCurves() {
/* 170 */     ArrayList<CirculinearContinuousCurve2D> result = 
/* 171 */       new ArrayList<CirculinearContinuousCurve2D>();
/* 174 */     for (CirculinearCurve2D curve : this.curves)
/* 175 */       result.addAll(curve.continuousCurves()); 
/* 178 */     return result;
/*     */   }
/*     */   
/*     */   public CirculinearCurveArray2D<? extends CirculinearCurve2D> clip(Box2D box) {
/* 184 */     CurveSet2D<? extends Curve2D> set = Curves2D.clipCurve(this, box);
/* 187 */     int n = set.size();
/* 188 */     CirculinearCurveArray2D<CirculinearCurve2D> result = 
/* 189 */       new CirculinearCurveArray2D(n);
/* 192 */     for (Curve2D curve : set.curves()) {
/* 193 */       if (curve instanceof CirculinearCurve2D)
/* 194 */         result.add(curve); 
/*     */     } 
/* 198 */     return result;
/*     */   }
/*     */   
/*     */   public CirculinearCurveArray2D<? extends CirculinearCurve2D> subCurve(double t0, double t1) {
/* 205 */     CurveSet2D<? extends Curve2D> subcurve = super.subCurve(t0, t1);
/* 208 */     CirculinearCurveArray2D<CirculinearCurve2D> result = 
/* 209 */       new CirculinearCurveArray2D(subcurve.size());
/* 212 */     for (Curve2D curve : subcurve) {
/* 213 */       if (curve instanceof CirculinearCurve2D) {
/* 214 */         result.add(curve);
/*     */         continue;
/*     */       } 
/* 216 */       System.err.println("CirculinearCurveArray2D.getSubCurve: error in class cast");
/*     */     } 
/* 220 */     return result;
/*     */   }
/*     */   
/*     */   public CirculinearCurveArray2D<? extends CirculinearCurve2D> reverse() {
/* 226 */     int n = this.curves.size();
/* 228 */     CirculinearCurve2D[] curves2 = new CirculinearCurve2D[n];
/* 231 */     for (int i = 0; i < n; i++)
/* 232 */       curves2[i] = ((CirculinearCurve2D)this.curves.get(n - 1 - i)).reverse(); 
/* 235 */     return new CirculinearCurveArray2D((T[])curves2);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\circulinear\CirculinearCurveArray2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */