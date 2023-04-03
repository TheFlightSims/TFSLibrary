/*     */ package math.geom2d.transform;
/*     */ 
/*     */ import math.geom2d.Angle2D;
/*     */ import math.geom2d.Point2D;
/*     */ import math.geom2d.conic.Circle2D;
/*     */ 
/*     */ public class CircleInversion2D implements Bijection2D {
/*     */   protected Point2D center;
/*     */   
/*     */   protected double radius;
/*     */   
/*     */   public static CircleInversion2D create(Point2D center, double radius) {
/*  44 */     return new CircleInversion2D(center, radius);
/*     */   }
/*     */   
/*     */   public static CircleInversion2D create(Circle2D circle) {
/*  48 */     return new CircleInversion2D(circle);
/*     */   }
/*     */   
/*     */   public CircleInversion2D() {
/*  65 */     this.center = new Point2D();
/*  66 */     this.radius = 1.0D;
/*     */   }
/*     */   
/*     */   public CircleInversion2D(Circle2D circle) {
/*  70 */     this.center = circle.center().clone();
/*  71 */     this.radius = circle.radius();
/*     */   }
/*     */   
/*     */   public CircleInversion2D(Point2D center, double radius) {
/*  75 */     this.center = center.clone();
/*  76 */     this.radius = radius;
/*     */   }
/*     */   
/*     */   public CircleInversion2D(double xc, double yc, double radius) {
/*  80 */     this.center = new Point2D(xc, yc);
/*  81 */     this.radius = radius;
/*     */   }
/*     */   
/*     */   public Point2D center() {
/*  88 */     return this.center;
/*     */   }
/*     */   
/*     */   public double radius() {
/*  92 */     return this.radius;
/*     */   }
/*     */   
/*     */   public CircleInversion2D invert() {
/* 103 */     return this;
/*     */   }
/*     */   
/*     */   public Point2D transform(Point2D pt) {
/* 110 */     double r = this.radius;
/* 112 */     double d = r * r / Point2D.distance(pt, this.center);
/* 113 */     double theta = Angle2D.horizontalAngle(this.center, pt);
/* 114 */     return Point2D.createPolar(this.center, d, theta);
/*     */   }
/*     */   
/*     */   public Point2D[] transform(Point2D[] src, Point2D[] dst) {
/* 124 */     if (dst == null)
/* 125 */       dst = new Point2D[src.length]; 
/* 128 */     if (dst[0] == null)
/* 129 */       for (int j = 0; j < dst.length; j++)
/* 130 */         dst[j] = new Point2D();  
/* 132 */     double xc = this.center.x();
/* 133 */     double yc = this.center.y();
/* 134 */     double r = this.radius;
/* 137 */     for (int i = 0; i < src.length; i++) {
/* 138 */       double d = Point2D.distance(src[i].x(), src[i].y(), xc, yc);
/* 139 */       d = r * r / d;
/* 140 */       double theta = Math.atan2(src[i].y() - yc, src[i].x() - xc);
/* 141 */       dst[i] = new Point2D(d * Math.cos(theta), d * Math.sin(theta));
/*     */     } 
/* 144 */     return dst;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\transform\CircleInversion2D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */