/*     */ package math.geom2d.curve;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import math.geom2d.Point2D;
/*     */ import math.geom2d.UnboundedShape2DException;
/*     */ import math.geom2d.polygon.LinearCurve2D;
/*     */ import math.geom2d.polygon.LinearRing2D;
/*     */ import math.geom2d.polygon.Polyline2D;
/*     */ 
/*     */ public abstract class AbstractContinuousCurve2D implements ContinuousCurve2D, Cloneable {
/*     */   protected static <T extends ContinuousCurve2D> Collection<T> wrapCurve(T curve) {
/*  34 */     ArrayList<T> list = new ArrayList<T>(1);
/*  35 */     list.add(curve);
/*  36 */     return list;
/*     */   }
/*     */   
/*     */   public LinearCurve2D asPolyline(int n) {
/*  49 */     if (!isBounded())
/*  50 */       throw new UnboundedShape2DException(this); 
/*  52 */     if (isClosed())
/*  53 */       return (LinearCurve2D)asPolylineClosed(n); 
/*  55 */     return (LinearCurve2D)asPolylineOpen(n);
/*     */   }
/*     */   
/*     */   protected Polyline2D asPolylineOpen(int n) {
/*  66 */     if (!isBounded())
/*  67 */       throw new UnboundedShape2DException(this); 
/*  70 */     double t0 = t0();
/*  71 */     double dt = (t1() - t0) / n;
/*  75 */     Point2D[] points = new Point2D[n + 1];
/*  76 */     for (int i = 0; i < n + 1; i++)
/*  77 */       points[i] = point(t0 + i * dt); 
/*  79 */     return new Polyline2D(points);
/*     */   }
/*     */   
/*     */   protected LinearRing2D asPolylineClosed(int n) {
/*  89 */     if (!isBounded())
/*  90 */       throw new UnboundedShape2DException(this); 
/*  93 */     double t0 = t0();
/*  94 */     double dt = (t1() - t0) / n;
/*  98 */     Point2D[] points = new Point2D[n];
/*  99 */     for (int i = 0; i < n; i++)
/* 100 */       points[i] = point(t0 + i * dt); 
/* 102 */     return new LinearRing2D(points);
/*     */   }
/*     */   
/*     */   public Collection<? extends ContinuousCurve2D> continuousCurves() {
/* 109 */     return wrapCurve(this);
/*     */   }
/*     */   
/*     */   public Point2D firstPoint() {
/* 116 */     double t0 = t0();
/* 117 */     if (Double.isInfinite(t0))
/* 118 */       throw new UnboundedShape2DException(this); 
/* 119 */     return point(t0);
/*     */   }
/*     */   
/*     */   public Point2D lastPoint() {
/* 127 */     double t1 = t1();
/* 128 */     if (Double.isInfinite(t1))
/* 129 */       throw new UnboundedShape2DException(this); 
/* 130 */     return point(t1);
/*     */   }
/*     */   
/*     */   public void draw(Graphics2D g2) {
/* 137 */     g2.draw(asAwtShape());
/*     */   }
/*     */   
/*     */   public Shape asAwtShape() {
/* 145 */     if (!isBounded())
/* 146 */       throw new UnboundedShape2DException(this); 
/* 148 */     GeneralPath path = new GeneralPath();
/* 150 */     Point2D point = firstPoint();
/* 151 */     path.moveTo((float)point.x(), (float)point.y());
/* 152 */     path = appendPath(path);
/* 153 */     return path;
/*     */   }
/*     */   
/*     */   public abstract ContinuousCurve2D clone();
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\curve\AbstractContinuousCurve2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */