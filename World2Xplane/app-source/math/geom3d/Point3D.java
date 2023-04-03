/*     */ package math.geom3d;
/*     */ 
/*     */ import math.geom3d.transform.AffineTransform3D;
/*     */ 
/*     */ public class Point3D implements Shape3D {
/*  36 */   private double x = 0.0D;
/*     */   
/*  37 */   private double y = 0.0D;
/*     */   
/*  38 */   private double z = 0.0D;
/*     */   
/*     */   public Point3D() {
/*  44 */     this(0.0D, 0.0D, 0.0D);
/*     */   }
/*     */   
/*     */   public Point3D(double x, double y, double z) {
/*  48 */     this.x = x;
/*  49 */     this.y = y;
/*  50 */     this.z = z;
/*     */   }
/*     */   
/*     */   public double getX() {
/*  54 */     return this.x;
/*     */   }
/*     */   
/*     */   public double getY() {
/*  58 */     return this.y;
/*     */   }
/*     */   
/*     */   public double getZ() {
/*  62 */     return this.z;
/*     */   }
/*     */   
/*     */   public double distance(Point3D point) {
/*  66 */     double dx = point.x - this.x;
/*  67 */     double dy = point.y - this.y;
/*  68 */     double dz = point.z - this.z;
/*  70 */     return Math.hypot(Math.hypot(dx, dy), dz);
/*     */   }
/*     */   
/*     */   public boolean contains(Point3D point) {
/*  78 */     if (distance(point) > 1.0E-12D)
/*  79 */       return false; 
/*  80 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*  84 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isBounded() {
/*  88 */     return true;
/*     */   }
/*     */   
/*     */   public Box3D boundingBox() {
/*  92 */     return new Box3D(this.x, this.x, this.y, this.y, this.z, this.z);
/*     */   }
/*     */   
/*     */   public Shape3D clip(Box3D box) {
/*  99 */     if (this.x < box.getMinX() || this.x > box.getMaxX())
/* 100 */       return null; 
/* 101 */     if (this.y < box.getMinY() || this.y > box.getMaxY())
/* 102 */       return null; 
/* 103 */     if (this.z < box.getMinZ() || this.z > box.getMaxZ())
/* 104 */       return null; 
/* 105 */     return this;
/*     */   }
/*     */   
/*     */   public Point3D transform(AffineTransform3D trans) {
/* 109 */     double[] coef = trans.coefficients();
/* 110 */     return new Point3D(
/* 111 */         this.x * coef[0] + this.y * coef[1] + this.z * coef[2] + coef[3], 
/* 112 */         this.x * coef[4] + this.y * coef[5] + this.z * coef[6] + coef[7], 
/* 113 */         this.x * coef[8] + this.y * coef[9] + this.z * coef[10] + coef[12]);
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 123 */     if (!(obj instanceof Point3D))
/* 124 */       return false; 
/* 125 */     Point3D point = (Point3D)obj;
/* 127 */     if (Math.abs(point.x - this.x) > 1.0E-12D)
/* 128 */       return false; 
/* 129 */     if (Math.abs(point.y - this.y) > 1.0E-12D)
/* 130 */       return false; 
/* 131 */     if (Math.abs(point.z - this.z) > 1.0E-12D)
/* 132 */       return false; 
/* 133 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom3d\Point3D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */