/*     */ package math.geom3d;
/*     */ 
/*     */ public class Box3D {
/*  15 */   private double xmin = 0.0D;
/*     */   
/*  16 */   private double xmax = 0.0D;
/*     */   
/*  17 */   private double ymin = 0.0D;
/*     */   
/*  18 */   private double ymax = 0.0D;
/*     */   
/*  19 */   private double zmin = 0.0D;
/*     */   
/*  20 */   private double zmax = 0.0D;
/*     */   
/*     */   public Box3D() {
/*  24 */     this(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
/*     */   }
/*     */   
/*     */   public Box3D(double x0, double x1, double y0, double y1, double z0, double z1) {
/*  34 */     this.xmin = Math.min(x0, x1);
/*  35 */     this.xmax = Math.max(x0, x1);
/*  36 */     this.ymin = Math.min(y0, y1);
/*  37 */     this.ymax = Math.max(y0, y1);
/*  38 */     this.zmin = Math.min(z0, z1);
/*  39 */     this.zmax = Math.max(z0, z1);
/*     */   }
/*     */   
/*     */   public Box3D(Point3D p1, Point3D p2) {
/*  44 */     this(p1.getX(), p2.getX(), p1.getY(), p2.getY(), p1.getZ(), p2.getZ());
/*     */   }
/*     */   
/*     */   public double getMinX() {
/*  51 */     return this.xmin;
/*     */   }
/*     */   
/*     */   public double getMaxX() {
/*  55 */     return this.xmax;
/*     */   }
/*     */   
/*     */   public double getMinY() {
/*  59 */     return this.ymin;
/*     */   }
/*     */   
/*     */   public double getMaxY() {
/*  63 */     return this.ymax;
/*     */   }
/*     */   
/*     */   public double getMinZ() {
/*  67 */     return this.zmin;
/*     */   }
/*     */   
/*     */   public double getMaxZ() {
/*  71 */     return this.zmax;
/*     */   }
/*     */   
/*     */   public double getWidth() {
/*  76 */     return this.xmax - this.xmin;
/*     */   }
/*     */   
/*     */   public double getHeight() {
/*  81 */     return this.ymax - this.ymin;
/*     */   }
/*     */   
/*     */   public double getDepth() {
/*  86 */     return this.zmax - this.zmin;
/*     */   }
/*     */   
/*     */   public Box3D union(Box3D box) {
/*  96 */     double xmin = Math.min(this.xmin, box.xmin);
/*  97 */     double xmax = Math.max(this.xmax, box.xmax);
/*  98 */     double ymin = Math.min(this.ymin, box.ymin);
/*  99 */     double ymax = Math.max(this.ymax, box.ymax);
/* 100 */     double zmin = Math.min(this.zmin, box.zmin);
/* 101 */     double zmax = Math.max(this.zmax, box.zmax);
/* 102 */     return new Box3D(xmin, xmax, ymin, ymax, zmin, zmax);
/*     */   }
/*     */   
/*     */   public Box3D intersection(Box3D box) {
/* 113 */     double xmin = Math.max(this.xmin, box.xmin);
/* 114 */     double xmax = Math.min(this.xmax, box.xmax);
/* 115 */     double ymin = Math.max(this.ymin, box.ymin);
/* 116 */     double ymax = Math.min(this.ymax, box.ymax);
/* 117 */     double zmin = Math.max(this.zmin, box.zmin);
/* 118 */     double zmax = Math.min(this.zmax, box.zmax);
/* 119 */     return new Box3D(xmin, xmax, ymin, ymax, zmin, zmax);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom3d\Box3D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */