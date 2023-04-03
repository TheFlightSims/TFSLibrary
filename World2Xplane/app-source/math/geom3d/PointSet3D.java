/*     */ package math.geom3d;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import math.geom3d.transform.AffineTransform3D;
/*     */ 
/*     */ public class PointSet3D implements Shape3D, Iterable<Point3D> {
/*  18 */   protected Collection<Point3D> points = new ArrayList<Point3D>();
/*     */   
/*     */   public PointSet3D() {}
/*     */   
/*     */   public PointSet3D(int n) {
/*  28 */     this.points = new ArrayList<Point3D>(n);
/*     */   }
/*     */   
/*     */   public PointSet3D(Point3D[] points) {
/*     */     byte b;
/*     */     int i;
/*     */     Point3D[] arrayOfPoint3D;
/*  36 */     for (i = (arrayOfPoint3D = points).length, b = 0; b < i; ) {
/*  36 */       Point3D element = arrayOfPoint3D[b];
/*  37 */       this.points.add(element);
/*     */       b++;
/*     */     } 
/*     */   }
/*     */   
/*     */   public PointSet3D(Collection<? extends Point3D> points) {
/*  48 */     for (Point3D point : points)
/*  49 */       this.points.add(point); 
/*     */   }
/*     */   
/*     */   public void addPoint(Point3D point) {
/*  60 */     this.points.add(point);
/*     */   }
/*     */   
/*     */   public void addPoints(Point3D[] points) {
/*     */     byte b;
/*     */     int i;
/*     */     Point3D[] arrayOfPoint3D;
/*  69 */     for (i = (arrayOfPoint3D = points).length, b = 0; b < i; ) {
/*  69 */       Point3D element = arrayOfPoint3D[b];
/*  70 */       addPoint(element);
/*     */       b++;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addPoints(Collection<Point3D> points) {
/*  74 */     this.points.addAll(points);
/*     */   }
/*     */   
/*     */   public Iterator<Point3D> getPoints() {
/*  83 */     return this.points.iterator();
/*     */   }
/*     */   
/*     */   public void clearPoints() {
/*  90 */     this.points.clear();
/*     */   }
/*     */   
/*     */   public int pointNumber() {
/*  99 */     return this.points.size();
/*     */   }
/*     */   
/*     */   public Shape3D clip(Box3D box) {
/* 111 */     PointSet3D res = new PointSet3D(this.points.size());
/* 113 */     for (Point3D point : this.points) {
/* 114 */       Shape3D clipped = point.clip(box);
/* 115 */       if (clipped != null)
/* 116 */         res.addPoint(point); 
/*     */     } 
/* 118 */     return res;
/*     */   }
/*     */   
/*     */   public Box3D boundingBox() {
/* 122 */     double xmin = Double.MAX_VALUE;
/* 123 */     double ymin = Double.MAX_VALUE;
/* 124 */     double zmin = Double.MAX_VALUE;
/* 125 */     double xmax = Double.MIN_VALUE;
/* 126 */     double ymax = Double.MIN_VALUE;
/* 127 */     double zmax = Double.MIN_VALUE;
/* 129 */     for (Point3D point : this.points) {
/* 130 */       xmin = Math.min(xmin, point.getX());
/* 131 */       ymin = Math.min(ymin, point.getY());
/* 132 */       zmin = Math.min(zmin, point.getZ());
/* 133 */       xmax = Math.max(xmax, point.getX());
/* 134 */       ymax = Math.max(ymax, point.getY());
/* 135 */       zmax = Math.max(zmax, point.getZ());
/*     */     } 
/* 137 */     return new Box3D(xmin, xmax, ymin, ymax, zmin, zmax);
/*     */   }
/*     */   
/*     */   public double distance(Point3D p) {
/* 146 */     if (this.points.isEmpty())
/* 147 */       return Double.POSITIVE_INFINITY; 
/* 148 */     double dist = Double.POSITIVE_INFINITY;
/* 149 */     for (Point3D point : this.points)
/* 150 */       dist = Math.min(dist, point.distance(p)); 
/* 151 */     return dist;
/*     */   }
/*     */   
/*     */   public boolean contains(Point3D point) {
/* 155 */     for (Point3D p : this.points) {
/* 156 */       if (point.distance(p) < 1.0E-12D)
/* 157 */         return true; 
/*     */     } 
/* 158 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 162 */     return (this.points.size() == 0);
/*     */   }
/*     */   
/*     */   public boolean isBounded() {
/* 166 */     return true;
/*     */   }
/*     */   
/*     */   public Shape3D transform(AffineTransform3D trans) {
/* 175 */     PointSet3D res = new PointSet3D();
/* 176 */     for (Point3D point : this.points)
/* 177 */       res.addPoint(point.transform(trans)); 
/* 178 */     return res;
/*     */   }
/*     */   
/*     */   public Iterator<Point3D> iterator() {
/* 190 */     return this.points.iterator();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom3d\PointSet3D.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */