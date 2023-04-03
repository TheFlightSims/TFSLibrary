/*     */ package math.geom3d.line;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import math.geom2d.line.StraightLine2D;
/*     */ import math.geom3d.Box3D;
/*     */ import math.geom3d.Point3D;
/*     */ import math.geom3d.Shape3D;
/*     */ import math.geom3d.Vector3D;
/*     */ import math.geom3d.curve.ContinuousCurve3D;
/*     */ import math.geom3d.curve.Curve3D;
/*     */ import math.geom3d.plane.Plane3D;
/*     */ import math.geom3d.transform.AffineTransform3D;
/*     */ 
/*     */ public class StraightLine3D implements ContinuousCurve3D {
/*  28 */   protected double x0 = 0.0D;
/*     */   
/*  29 */   protected double y0 = 0.0D;
/*     */   
/*  30 */   protected double z0 = 0.0D;
/*     */   
/*  31 */   protected double dx = 1.0D;
/*     */   
/*  32 */   protected double dy = 0.0D;
/*     */   
/*  33 */   protected double dz = 0.0D;
/*     */   
/*     */   public StraightLine3D(Point3D origin, Vector3D direction) {
/*  42 */     this.x0 = origin.getX();
/*  43 */     this.y0 = origin.getY();
/*  44 */     this.z0 = origin.getZ();
/*  45 */     this.dx = direction.getX();
/*  46 */     this.dy = direction.getY();
/*  47 */     this.dz = direction.getZ();
/*     */   }
/*     */   
/*     */   public StraightLine3D(Point3D p1, Point3D p2) {
/*  57 */     this(p1, new Vector3D(p1, p2));
/*     */   }
/*     */   
/*     */   public StraightLine3D(double x0, double y0, double z0, double dx, double dy, double dz) {
/*  62 */     this.x0 = x0;
/*  63 */     this.y0 = y0;
/*  64 */     this.z0 = z0;
/*  65 */     this.dx = dx;
/*  66 */     this.dy = dy;
/*  67 */     this.dz = dz;
/*     */   }
/*     */   
/*     */   public Point3D origin() {
/*  74 */     return new Point3D(this.x0, this.y0, this.z0);
/*     */   }
/*     */   
/*     */   public Vector3D direction() {
/*  78 */     return new Vector3D(this.dx, this.dy, this.dz);
/*     */   }
/*     */   
/*     */   public StraightLine2D project(Plane3D plane) {
/*  83 */     return null;
/*     */   }
/*     */   
/*     */   public Shape3D clip(Box3D box) {
/*  96 */     return null;
/*     */   }
/*     */   
/*     */   public boolean contains(Point3D point) {
/* 105 */     return (distance(point) < 1.0E-12D);
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 109 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isBounded() {
/* 113 */     return false;
/*     */   }
/*     */   
/*     */   public Box3D boundingBox() {
/* 122 */     Vector3D v = direction();
/* 125 */     if (Math.hypot(v.getY(), v.getZ()) < 1.0E-12D)
/* 126 */       return new Box3D(this.x0, this.x0, Double.NEGATIVE_INFINITY, 
/* 127 */           Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY, 
/* 128 */           Double.POSITIVE_INFINITY); 
/* 131 */     if (Math.hypot(v.getX(), v.getZ()) < 1.0E-12D)
/* 132 */       return new Box3D(Double.NEGATIVE_INFINITY, 
/* 133 */           Double.POSITIVE_INFINITY, this.y0, this.y0, Double.NEGATIVE_INFINITY, 
/* 134 */           Double.POSITIVE_INFINITY); 
/* 137 */     if (Math.hypot(v.getX(), v.getY()) < 1.0E-12D)
/* 138 */       return new Box3D(Double.NEGATIVE_INFINITY, 
/* 139 */           Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY, 
/* 140 */           Double.POSITIVE_INFINITY, this.z0, this.z0); 
/* 142 */     return new Box3D(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 
/* 143 */         Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 
/* 144 */         Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
/*     */   }
/*     */   
/*     */   public double distance(Point3D p) {
/* 153 */     Vector3D vl = direction();
/* 154 */     Vector3D vp = new Vector3D(origin(), p);
/* 155 */     return Vector3D.crossProduct(vl, vp).norm() / vl.norm();
/*     */   }
/*     */   
/*     */   public StraightLine3D transform(AffineTransform3D trans) {
/* 164 */     return new StraightLine3D(
/* 165 */         origin().transform(trans), 
/* 166 */         direction().transform(trans));
/*     */   }
/*     */   
/*     */   public Point3D firstPoint() {
/* 170 */     return new Point3D(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 
/* 171 */         Double.POSITIVE_INFINITY);
/*     */   }
/*     */   
/*     */   public Point3D lastPoint() {
/* 175 */     return new Point3D(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 
/* 176 */         Double.POSITIVE_INFINITY);
/*     */   }
/*     */   
/*     */   public Point3D point(double t) {
/* 180 */     return new Point3D(this.x0 + t * this.dx, this.y0 + t * this.dy, this.z0 + t * this.dz);
/*     */   }
/*     */   
/*     */   public double position(Point3D point) {
/* 184 */     return project(point);
/*     */   }
/*     */   
/*     */   public StraightLine3D reverseCurve() {
/* 188 */     return new StraightLine3D(origin(), direction().opposite());
/*     */   }
/*     */   
/*     */   public Collection<Point3D> singularPoints() {
/* 195 */     return new ArrayList<Point3D>(0);
/*     */   }
/*     */   
/*     */   public Curve3D subCurve(double t0, double t1) {
/* 200 */     return null;
/*     */   }
/*     */   
/*     */   public double getT0() {
/* 207 */     return Double.NEGATIVE_INFINITY;
/*     */   }
/*     */   
/*     */   public double getT1() {
/* 214 */     return Double.POSITIVE_INFINITY;
/*     */   }
/*     */   
/*     */   public double project(Point3D point) {
/* 222 */     Vector3D vl = direction();
/* 223 */     Vector3D vp = new Vector3D(origin(), point);
/* 224 */     return Vector3D.dotProduct(vl, vp) / vl.normSq();
/*     */   }
/*     */   
/*     */   public Collection<StraightLine3D> continuousCurves() {
/* 228 */     ArrayList<StraightLine3D> array = new ArrayList<StraightLine3D>(1);
/* 229 */     array.add(this);
/* 230 */     return array;
/*     */   }
/*     */   
/*     */   public StraightLine3D() {}
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom3d\line\StraightLine3D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */