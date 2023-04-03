/*     */ package math.geom3d;
/*     */ 
/*     */ import math.geom3d.transform.AffineTransform3D;
/*     */ 
/*     */ public class Vector3D {
/*  18 */   protected double x = 1.0D;
/*     */   
/*  19 */   protected double y = 0.0D;
/*     */   
/*  20 */   protected double z = 0.0D;
/*     */   
/*     */   public static final double dotProduct(Vector3D v1, Vector3D v2) {
/*  35 */     return v1.getX() * v2.getX() + v1.getY() * v2.getY() + v1.getZ() * v2.getZ();
/*     */   }
/*     */   
/*     */   public static final Vector3D crossProduct(Vector3D v1, Vector3D v2) {
/*  44 */     return new Vector3D(v1.y * v2.z - v1.z * v2.y, v1.z * v2.x - v1.x * v2.z, v1.x * v2.y - 
/*  45 */         v1.y * v2.x);
/*     */   }
/*     */   
/*     */   public static final boolean isColinear(Vector3D v1, Vector3D v2) {
/*  54 */     return 
/*  55 */       (crossProduct(v1.normalize(), v2.normalize()).norm() < 1.0E-12D);
/*     */   }
/*     */   
/*     */   public static final boolean isOrthogonal(Vector3D v1, Vector3D v2) {
/*  64 */     return 
/*  65 */       (dotProduct(v1.normalize(), v2.normalize()) < 1.0E-12D);
/*     */   }
/*     */   
/*     */   public Vector3D() {
/*  73 */     this(1.0D, 0.0D, 0.0D);
/*     */   }
/*     */   
/*     */   public Vector3D(double x, double y, double z) {
/*  78 */     this.x = x;
/*  79 */     this.y = y;
/*  80 */     this.z = z;
/*     */   }
/*     */   
/*     */   public Vector3D(Point3D point) {
/*  87 */     this(point.getX(), point.getY(), point.getZ());
/*     */   }
/*     */   
/*     */   public Vector3D(Point3D point1, Point3D point2) {
/*  96 */     this(point2.getX() - point1.getX(), point2.getY() - point1.getY(), point2.getZ() - point1.getZ());
/*     */   }
/*     */   
/*     */   public double getX() {
/* 103 */     return this.x;
/*     */   }
/*     */   
/*     */   public double getY() {
/* 107 */     return this.y;
/*     */   }
/*     */   
/*     */   public double getZ() {
/* 111 */     return this.z;
/*     */   }
/*     */   
/*     */   public Vector3D plus(Vector3D v) {
/* 122 */     return new Vector3D(this.x + v.x, this.y + v.y, this.z + v.z);
/*     */   }
/*     */   
/*     */   public Vector3D minus(Vector3D v) {
/* 130 */     return new Vector3D(this.x - v.x, this.y - v.y, this.z - v.z);
/*     */   }
/*     */   
/*     */   public Vector3D times(double k) {
/* 137 */     return new Vector3D(k * this.x, k * this.y, k * this.z);
/*     */   }
/*     */   
/*     */   public Vector3D opposite() {
/* 150 */     return new Vector3D(-this.x, -this.y, -this.z);
/*     */   }
/*     */   
/*     */   public double norm() {
/* 159 */     return Math.hypot(Math.hypot(this.x, this.y), this.z);
/*     */   }
/*     */   
/*     */   public double normSq() {
/* 169 */     return this.x * this.x + this.y * this.y + this.z * this.z;
/*     */   }
/*     */   
/*     */   public Vector3D normalize() {
/* 187 */     double r = norm();
/* 188 */     return new Vector3D(this.x / r, this.y / r, this.z / r);
/*     */   }
/*     */   
/*     */   public Vector3D transform(AffineTransform3D trans) {
/* 199 */     double[] tab = trans.coefficients();
/* 200 */     return new Vector3D(
/* 201 */         this.x * tab[0] + this.y * tab[1] + this.z * tab[2], 
/* 202 */         this.x * tab[4] + this.y * tab[5] + this.z * tab[6], 
/* 203 */         this.x * tab[8] + this.y * tab[9] + this.z * tab[10]);
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 211 */     if (!(obj instanceof Vector3D))
/* 212 */       return false; 
/* 214 */     Vector3D v = (Vector3D)obj;
/* 215 */     if (Math.abs(this.x - v.x) > 1.0E-12D)
/* 216 */       return false; 
/* 217 */     if (Math.abs(this.y - v.y) > 1.0E-12D)
/* 218 */       return false; 
/* 219 */     if (Math.abs(this.z - v.z) > 1.0E-12D)
/* 220 */       return false; 
/* 221 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom3d\Vector3D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */