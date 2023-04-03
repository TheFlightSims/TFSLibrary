/*     */ package math.geom3d.line;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import math.geom3d.Box3D;
/*     */ import math.geom3d.Point3D;
/*     */ import math.geom3d.Shape3D;
/*     */ import math.geom3d.curve.ContinuousCurve3D;
/*     */ import math.geom3d.curve.Curve3D;
/*     */ import math.geom3d.transform.AffineTransform3D;
/*     */ 
/*     */ public class LineSegment3D implements ContinuousCurve3D {
/*  25 */   protected double x1 = 0.0D;
/*     */   
/*  26 */   protected double y1 = 0.0D;
/*     */   
/*  27 */   protected double z1 = 0.0D;
/*     */   
/*  28 */   protected double x2 = 1.0D;
/*     */   
/*  29 */   protected double y2 = 0.0D;
/*     */   
/*  30 */   protected double z2 = 0.0D;
/*     */   
/*     */   public LineSegment3D(Point3D p1, Point3D p2) {
/*  36 */     this.x1 = p1.getX();
/*  37 */     this.y1 = p1.getY();
/*  38 */     this.z1 = p1.getZ();
/*  39 */     this.x2 = p2.getX();
/*  40 */     this.y2 = p2.getY();
/*  41 */     this.z2 = p2.getZ();
/*     */   }
/*     */   
/*     */   public StraightLine3D supportingLine() {
/*  48 */     return new StraightLine3D(this.x1, this.y1, this.z1, this.x2 - this.x1, this.y2 - this.y1, this.z2 - this.z1);
/*     */   }
/*     */   
/*     */   public Collection<LineSegment3D> continuousCurves() {
/*  60 */     ArrayList<LineSegment3D> array = new ArrayList<LineSegment3D>(1);
/*  61 */     array.add(this);
/*  62 */     return array;
/*     */   }
/*     */   
/*     */   public Point3D firstPoint() {
/*  71 */     return new Point3D(this.x1, this.y1, this.z1);
/*     */   }
/*     */   
/*     */   public Point3D lastPoint() {
/*  80 */     return new Point3D(this.x2, this.y2, this.z2);
/*     */   }
/*     */   
/*     */   public Point3D point(double t) {
/*  89 */     t = Math.max(Math.min(t, 1.0D), 0.0D);
/*  90 */     return new Point3D(
/*  91 */         this.x1 + (this.x2 - this.x1) * t, 
/*  92 */         this.y1 + (this.y2 - this.y1) * t, 
/*  93 */         this.z1 + (this.z2 - this.z1) * t);
/*     */   }
/*     */   
/*     */   public double position(Point3D point) {
/* 102 */     double t = supportingLine().position(point);
/* 103 */     if (t > 1.0D)
/* 104 */       return Double.NaN; 
/* 105 */     if (t < 0.0D)
/* 106 */       return Double.NaN; 
/* 107 */     return t;
/*     */   }
/*     */   
/*     */   public Curve3D reverseCurve() {
/* 116 */     return (Curve3D)new StraightLine3D(lastPoint(), firstPoint());
/*     */   }
/*     */   
/*     */   public Collection<Point3D> singularPoints() {
/* 125 */     ArrayList<Point3D> points = new ArrayList<Point3D>(2);
/* 126 */     points.add(firstPoint());
/* 127 */     points.add(lastPoint());
/* 128 */     return points;
/*     */   }
/*     */   
/*     */   public LineSegment3D subCurve(double t0, double t1) {
/* 137 */     t0 = Math.max(t0, 0.0D);
/* 138 */     t1 = Math.min(t1, 1.0D);
/* 139 */     return new LineSegment3D(point(t0), point(t1));
/*     */   }
/*     */   
/*     */   public double getT0() {
/* 148 */     return 0.0D;
/*     */   }
/*     */   
/*     */   public double getT1() {
/* 157 */     return 1.0D;
/*     */   }
/*     */   
/*     */   public double project(Point3D point) {
/* 166 */     double t = supportingLine().project(point);
/* 167 */     return Math.min(Math.max(t, 0.0D), 1.0D);
/*     */   }
/*     */   
/*     */   public Curve3D transform(AffineTransform3D trans) {
/* 176 */     return (Curve3D)new LineSegment3D((new Point3D(this.x1, this.y1, this.z1)).transform(trans), (
/* 177 */         new Point3D(this.x2, this.y2, this.z2)).transform(trans));
/*     */   }
/*     */   
/*     */   public Shape3D clip(Box3D box) {
/* 190 */     return null;
/*     */   }
/*     */   
/*     */   public boolean contains(Point3D point) {
/* 199 */     StraightLine3D line = supportingLine();
/* 200 */     if (!line.contains(point))
/* 201 */       return false; 
/* 202 */     double t = line.position(point);
/* 203 */     if (t < -1.0E-12D)
/* 204 */       return false; 
/* 205 */     if (t > 1.000000000001D)
/* 206 */       return false; 
/* 207 */     return true;
/*     */   }
/*     */   
/*     */   public Box3D boundingBox() {
/* 216 */     return new Box3D(this.x1, this.x2, this.y1, this.y2, this.z1, this.z2);
/*     */   }
/*     */   
/*     */   public double distance(Point3D point) {
/* 225 */     double t = project(point);
/* 226 */     return point(t).distance(point);
/*     */   }
/*     */   
/*     */   public boolean isBounded() {
/* 235 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 244 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom3d\line\LineSegment3D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */