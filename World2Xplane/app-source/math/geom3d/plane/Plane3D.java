/*     */ package math.geom3d.plane;
/*     */ 
/*     */ import math.geom2d.Point2D;
/*     */ import math.geom3d.Box3D;
/*     */ import math.geom3d.Point3D;
/*     */ import math.geom3d.Shape3D;
/*     */ import math.geom3d.Vector3D;
/*     */ import math.geom3d.line.StraightLine3D;
/*     */ import math.geom3d.transform.AffineTransform3D;
/*     */ 
/*     */ public class Plane3D implements Shape3D {
/*  23 */   protected double x0 = 0.0D;
/*     */   
/*  24 */   protected double y0 = 0.0D;
/*     */   
/*  25 */   protected double z0 = 0.0D;
/*     */   
/*  26 */   protected double dx1 = 1.0D;
/*     */   
/*  27 */   protected double dy1 = 0.0D;
/*     */   
/*  28 */   protected double dz1 = 0.0D;
/*     */   
/*  29 */   protected double dx2 = 0.0D;
/*     */   
/*  30 */   protected double dy2 = 1.0D;
/*     */   
/*  31 */   protected double dz2 = 0.0D;
/*     */   
/*     */   public static final Plane3D createXYPlane() {
/*  37 */     return new Plane3D(new Point3D(0.0D, 0.0D, 0.0D), new Vector3D(1.0D, 0.0D, 0.0D), 
/*  38 */         new Vector3D(0.0D, 1.0D, 0.0D));
/*     */   }
/*     */   
/*     */   public static final Plane3D createXZPlane() {
/*  42 */     return new Plane3D(new Point3D(0.0D, 0.0D, 0.0D), new Vector3D(1.0D, 0.0D, 0.0D), 
/*  43 */         new Vector3D(0.0D, 0.0D, 1.0D));
/*     */   }
/*     */   
/*     */   public static final Plane3D createYZPlane() {
/*  47 */     return new Plane3D(new Point3D(0.0D, 0.0D, 0.0D), new Vector3D(0.0D, 1.0D, 0.0D), 
/*  48 */         new Vector3D(0.0D, 0.0D, 1.0D));
/*     */   }
/*     */   
/*     */   public Plane3D() {}
/*     */   
/*     */   public Plane3D(Point3D point, Vector3D vector1, Vector3D vector2) {
/*  58 */     this.x0 = point.getX();
/*  59 */     this.y0 = point.getY();
/*  60 */     this.z0 = point.getZ();
/*  61 */     this.dx1 = vector1.getX();
/*  62 */     this.dy1 = vector1.getY();
/*  63 */     this.dz1 = vector1.getZ();
/*  64 */     this.dx2 = vector2.getX();
/*  65 */     this.dy2 = vector2.getY();
/*  66 */     this.dz2 = vector2.getZ();
/*     */   }
/*     */   
/*     */   public Point3D origin() {
/*  73 */     return new Point3D(this.x0, this.y0, this.z0);
/*     */   }
/*     */   
/*     */   public Vector3D vector1() {
/*  77 */     return new Vector3D(this.dx1, this.dy1, this.dz1);
/*     */   }
/*     */   
/*     */   public Vector3D vector2() {
/*  81 */     return new Vector3D(this.dx2, this.dy2, this.dz2);
/*     */   }
/*     */   
/*     */   public Vector3D normal() {
/*  90 */     return Vector3D.crossProduct(vector1(), vector2())
/*  91 */       .opposite();
/*     */   }
/*     */   
/*     */   public Point3D lineIntersection(StraightLine3D line) {
/* 104 */     Vector3D n = normal();
/* 107 */     Vector3D dp = new Vector3D(line.origin(), origin());
/* 111 */     double t = Vector3D.dotProduct(n, dp) / 
/* 112 */       Vector3D.dotProduct(n, line.direction());
/* 114 */     return line.point(t);
/*     */   }
/*     */   
/*     */   public Point3D projectPoint(Point3D point) {
/* 118 */     StraightLine3D line = new StraightLine3D(point, normal());
/* 119 */     return lineIntersection(line);
/*     */   }
/*     */   
/*     */   public Vector3D projectVector(Vector3D vect) {
/* 123 */     Point3D point = new Point3D(this.x0 + vect.getX(), this.y0 + vect.getY(), this.z0 + 
/* 124 */         vect.getZ());
/* 125 */     point = projectPoint(point);
/* 126 */     return new Vector3D(point.getX() - this.x0, point.getY() - this.y0, point.getZ() - this.z0);
/*     */   }
/*     */   
/*     */   public Point3D point(double u, double v) {
/* 130 */     return new Point3D(this.x0 + u * this.dx1 + v * this.dx2, this.y0 + u * this.dy1 + v * this.dy2, this.z0 + u * this.dz1 + v * this.dz2);
/*     */   }
/*     */   
/*     */   public Point2D pointPosition(Point3D point) {
/* 134 */     point = projectPoint(point);
/* 136 */     return null;
/*     */   }
/*     */   
/*     */   public Shape3D clip(Box3D box) {
/* 149 */     return null;
/*     */   }
/*     */   
/*     */   public boolean contains(Point3D point) {
/* 158 */     Point3D proj = projectPoint(point);
/* 159 */     return (point.distance(proj) < 1.0E-12D);
/*     */   }
/*     */   
/*     */   public Box3D boundingBox() {
/* 169 */     if (Math.abs(this.dz1) < 1.0E-12D && Math.abs(this.dz2) < 1.0E-12D)
/* 170 */       return new Box3D(Double.NEGATIVE_INFINITY, 
/* 171 */           Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY, 
/* 172 */           Double.POSITIVE_INFINITY, this.z0, this.z0); 
/* 175 */     if (Math.abs(this.dx1) < 1.0E-12D && Math.abs(this.dx2) < 1.0E-12D)
/* 176 */       return new Box3D(this.x0, this.x0, Double.NEGATIVE_INFINITY, 
/* 177 */           Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY, 
/* 178 */           Double.POSITIVE_INFINITY); 
/* 181 */     if (Math.abs(this.dy1) < 1.0E-12D && Math.abs(this.dy2) < 1.0E-12D)
/* 182 */       return new Box3D(Double.NEGATIVE_INFINITY, 
/* 183 */           Double.POSITIVE_INFINITY, this.y0, this.y0, Double.NEGATIVE_INFINITY, 
/* 184 */           Double.POSITIVE_INFINITY); 
/* 186 */     return new Box3D(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 
/* 187 */         Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 
/* 188 */         Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
/*     */   }
/*     */   
/*     */   public double distance(Point3D point) {
/* 197 */     return point.distance(projectPoint(point));
/*     */   }
/*     */   
/*     */   public boolean isBounded() {
/* 206 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 215 */     return false;
/*     */   }
/*     */   
/*     */   public Shape3D transform(AffineTransform3D trans) {
/* 224 */     return new Plane3D(origin().transform(trans), vector1()
/* 225 */         .transform(trans), vector2().transform(trans));
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 233 */     if (!(obj instanceof Plane3D))
/* 234 */       return false; 
/* 235 */     Plane3D plane = (Plane3D)obj;
/* 237 */     if (Math.abs(this.x0 - plane.x0) > 1.0E-12D)
/* 238 */       return false; 
/* 239 */     if (Math.abs(this.y0 - plane.y0) > 1.0E-12D)
/* 240 */       return false; 
/* 241 */     if (Math.abs(this.z0 - plane.z0) > 1.0E-12D)
/* 242 */       return false; 
/* 243 */     if (Math.abs(this.dx1 - plane.dx1) > 1.0E-12D)
/* 244 */       return false; 
/* 245 */     if (Math.abs(this.dy1 - plane.dy1) > 1.0E-12D)
/* 246 */       return false; 
/* 247 */     if (Math.abs(this.dz1 - plane.dz1) > 1.0E-12D)
/* 248 */       return false; 
/* 249 */     if (Math.abs(this.dx2 - plane.dx2) > 1.0E-12D)
/* 250 */       return false; 
/* 251 */     if (Math.abs(this.dy2 - plane.dy2) > 1.0E-12D)
/* 252 */       return false; 
/* 253 */     if (Math.abs(this.dz2 - plane.dz2) > 1.0E-12D)
/* 254 */       return false; 
/* 255 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom3d\plane\Plane3D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */